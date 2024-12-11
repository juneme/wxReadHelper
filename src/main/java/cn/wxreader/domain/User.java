package cn.wxreader.domain;

import cn.hutool.core.io.FileUtil;
import cn.wxreader.constant.Constant;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class User {
    private JSONObject wxReaderHeader;
    private JSONObject wxReaderCookie;
    private JSONObject wxReaderData;
    private Integer readMinute = 1;
    private String exchangeAward;
    private String platForm;
    private String userAgent;
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

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        if (platForm == null){
            this.platForm = Constant.Android_PLATFORM;
            this.userAgent = Constant.UserAgentForAndroid;
        } else if ("IOS".equals(platForm.toUpperCase())){
            this.platForm = Constant.IOS_PLATFORM;
            this.userAgent = Constant.UserAgentForIOS;
        } else {
            this.platForm = Constant.Android_PLATFORM;
            this.userAgent = Constant.UserAgentForAndroid;
        }
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getWrName() {
        String keyName = wxReaderHeader.keySet().stream().filter(key -> key.toLowerCase().contains("cookie")).findFirst().orElse(null);
        String wrName = Arrays.stream(wxReaderHeader.getString(keyName).split(";"))
                .filter(s -> s.contains("wr_name"))
                .collect(Collectors.toList())
                .get(0).split("=")[1];
        return URLDecoder.decode(wrName);
    }
}
