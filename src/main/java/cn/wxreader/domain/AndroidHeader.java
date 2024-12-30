package cn.wxreader.domain;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AndroidHeader {
    @JSONField(name = "vid")
    private String vid;
    @JSONField(name = "skey")
    private String sKey;
    @JSONField(name = "User-Agent")
    private String userAgent;
    private String baseapi = "35";
    private String appver = "9.0.0.10165035";
    private String osver = "15";
    private String channelId = "0";
    private String basever = "9.0.0.10165034";


    public AndroidHeader() {
    }

    public AndroidHeader(JSONObject wxReaderHeader, String userAgent) {
        String keyName = wxReaderHeader.keySet().stream().filter(key -> key.toLowerCase().contains("cookie")).findFirst().orElse(null);
        String[] splitCookies = wxReaderHeader.getString(keyName).split(";");
        Arrays.stream(splitCookies).forEach(cookie -> {
            cookie = cookie.trim();
            if (cookie.startsWith("wr_vid")) {
                this.vid = cookie.split("=")[1];
            } else if (cookie.startsWith("wr_skey")) {
                this.sKey = cookie.split("=")[1];
            }
        });
        this.userAgent = userAgent;
    }

    public Map<String, String> toMap() {
        Map<String, String> res = new HashMap<>();
        res.put("vid", vid);
        res.put("skey", sKey);
        res.put("User-Agent", userAgent);
        res.put("appver", appver);
        res.put("osver", osver);
        res.put("channelId", channelId);
        res.put("basever", basever);
        res.put("baseapi", baseapi);
        return res;
    }
}
