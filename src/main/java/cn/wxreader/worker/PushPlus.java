package cn.wxreader.worker;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.wxreader.domain.PushPlusMsg;
import cn.wxreader.enums.WxTaskEnum;
import com.alibaba.fastjson.JSONObject;

public class PushPlus {
    private String pushToken;
    private String pushMsg;

    private String pushPlusUrl = "https://www.pushplus.plus/send";
    private String userAgent = "Apifox/1.0.0 (https://apifox.com)";
    public PushPlus(String pushToken, String pushMsg) {
        this.pushToken = pushToken;
        this.pushMsg = pushMsg;
    }

    public Boolean push(WxTaskEnum task) {
        PushPlusMsg pushPlusMsg = new PushPlusMsg();
        pushPlusMsg.setToken(pushToken);
        pushPlusMsg.setContent(pushMsg);
        pushPlusMsg.setTitle(task.getDesc());
        try {
            HttpResponse response = HttpRequest.post(pushPlusUrl)
                    .header(Header.USER_AGENT, userAgent)
                    .body(JSONObject.toJSONString(pushPlusMsg))
                    .execute();
            if (response.isOk()){
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
