package cn.wxreader.domain;

import cn.hutool.core.io.FileUtil;
import cn.wxreader.constant.Constant;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class User {
    private JSONObject wxReaderHeader;
    private JSONObject wxReaderCookie;
    private JSONObject wxReaderData;
    private Integer readMinute;
    private String exchangeAward;
    private String pushType;
    private String pushToken;

    public static List<User> init(String userJsonPath) {
        Map<String, String> systemEnvMap = System.getenv();
        String usersJson = null;
        if (systemEnvMap.containsKey(Constant.WX_READ_USERS)) {
            usersJson = systemEnvMap.get(Constant.WX_READ_USERS);
        } else {
            usersJson = FileUtil.readString(Paths.get(userJsonPath).toFile(), Charset.defaultCharset());
        }
        return JSONArray.parseArray(usersJson, User.class);
    }

    public JSONObject getWxReaderHeader() {
        return wxReaderHeader;
    }

    public void setWxReaderHeader(JSONObject wxReaderHeader) {
        this.wxReaderHeader = wxReaderHeader;
    }

    public JSONObject getWxReaderCookie() {
        return wxReaderCookie;
    }

    public void setWxReaderCookie(JSONObject wxReaderCookie) {
        this.wxReaderCookie = wxReaderCookie;
    }

    public JSONObject getWxReaderData() {
        return wxReaderData;
    }

    public void setWxReaderData(JSONObject wxReaderData) {
        this.wxReaderData = wxReaderData;
    }

    public Integer getReadMinute() {
        return readMinute;
    }

    public void setReadMinute(Integer readMinute) {
        this.readMinute = readMinute;
    }

    public String getExchangeAward() {
        return exchangeAward;
    }

    public void setExchangeAward(String exchangeAward) {
        this.exchangeAward = exchangeAward;
    }
}
