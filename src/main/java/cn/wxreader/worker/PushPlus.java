package cn.wxreader.worker;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

public class PushPlus {
    private String pushToken;
    private String pushMsg;

    private String pushPlusUrl = "https://www.pushplus.plus/send";
    private String userAgent = "Apifox/1.0.0 (https://apifox.com)";
    public PushPlus(String pushToken, String pushMsg) {
        this.pushToken = pushToken;
        this.pushMsg = pushMsg;
    }

    public Boolean push() {
        try {
            HttpResponse response = HttpRequest.post(pushPlusUrl)
                    .header(Header.USER_AGENT, userAgent)
                    .body(String.format("{\"token\": \"%s\", \"content\": \"%s\"}", pushToken, pushMsg))
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
