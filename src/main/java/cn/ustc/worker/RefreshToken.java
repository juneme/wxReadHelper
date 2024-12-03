package cn.ustc.worker;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.ustc.constant.Constant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpCookie;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RefreshToken {
    private static final Logger log = LoggerFactory.getLogger(RefreshToken.class);

    private JSONObject wxReaderHeader;

    /**
     * Refresh the cookie
     * @return Whether the refresh was successful
     */
    public Boolean refreshCookie(JSONObject wxReaderHeader) {
        this.wxReaderHeader = wxReaderHeader;
        String newWrSkey = getWrSkey();
        if (newWrSkey != null) {
            String keyName = wxReaderHeader.keySet().stream().filter(key -> key.toLowerCase().contains("cookie")).findFirst().orElse(null);
            wxReaderHeader.replace(keyName, formatCookie(newWrSkey, keyName));
            log.info("Refresh the token success, the new token：{}", newWrSkey);
            return true;
        } else {
            throw new RuntimeException("Cannot Get the New wr_skey");
        }
    }

    /**
     * Get the new wr_skey
     * @return New wr_skey
     */
    private String getWrSkey() {
        try {
            HttpRequest request = HttpRequest.post(Constant.RENEW_URL)
                    .headerMap(jsonToMap(wxReaderHeader), true)
                    .body(Constant.REFRESH_BODY);
            HttpResponse response = request.execute();
            if (JSON.parseObject(response.body()).containsKey("succ")) {
                List<HttpCookie> cookies = response.getCookies();
                for (HttpCookie cookie : cookies) {
                    if ("wr_skey".equals(cookie.getName())) {
                        response.close();
                        return cookie.getValue();
                    }
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Renew the Cookies Error", e);
        }
    }

    private Map<String, String> jsonToMap(JSONObject wxReaderHeader) {
        Map<String, String> headerMap = new HashMap<>();
        wxReaderHeader.getInnerMap().forEach((key, value) -> {
            headerMap.put(key, value.toString());
        });
        return headerMap;
    }

    /**
     * Format the cookie
     * @param newWrSkey New wr_skey
     * @return Formatted cookie string
     */
    private String formatCookie(String newWrSkey, String keyName) {
        return Arrays.stream(wxReaderHeader.getString(keyName).split(";"))
                .map(cookie -> cookie.contains("wr_skey") ? " wr_skey=" + newWrSkey : cookie)
                .collect(Collectors.joining(";"));
    }
}
