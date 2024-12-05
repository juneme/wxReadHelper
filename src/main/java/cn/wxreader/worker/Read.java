package cn.wxreader.worker;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.wxreader.constant.Constant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class Read {
    private static final Logger log = LoggerFactory.getLogger(Read.class);
    private JSONObject wxReaderData;
    private JSONObject wxReaderHeader;
    private Integer readNum = 2;
    private static final Integer SLEEP_INTERVAL = 30;
    private static final String KEY = "3c5c8717f3daf09iop3423zafeqoi";
    private RefreshToken refreshToken = new RefreshToken();
    public Read() {
    }

    public Read(JSONObject wxReaderData, JSONObject wxReaderHeader, Integer readMinute) {
        this.wxReaderData = wxReaderData;
        this.wxReaderHeader = wxReaderHeader;
        this.readNum = readMinute * 2;
    }

    public Read(JSONObject wxReaderData, JSONObject wxReaderHeader) {
        this(wxReaderData, wxReaderHeader, 1);
    }

    /**
     * URL encode the data
     * @param wxReaderData Data to be encoded
     * @return Encoded string
     */
    private String encodeData(JSONObject wxReaderData) {
        return wxReaderData.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    try {
                        return URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()) +
                                "=" +
                                URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8.toString());
                    } catch (Exception e) {
                        throw new RuntimeException("Error encoding key or value", e);
                    }
                })
                .collect(Collectors.joining("&"));
    }

    /**
     * Calculate the hash value of a string
     * @param inputStr Input string
     * @return Hash value
     */
    private String calHash(String inputStr) {
        long _7032f5 = 0x15051505L;
        long _cc1055 = _7032f5;
        int length = inputStr.length();
        int _19094e = length - 1;

        while (_19094e > 0) {
            _7032f5 = 0x7fffffffL & (_7032f5 ^ (long) inputStr.charAt(_19094e) << (length - _19094e) % 30);
            _cc1055 = 0x7fffffffL & (_cc1055 ^ (long) inputStr.charAt(_19094e - 1) << _19094e % 30);
            _19094e -= 2;
        }

        return Long.toHexString(_7032f5 + _cc1055).toLowerCase();
    }

    private Map<String, String> jsonToMap(JSONObject wxReaderHeader) {
        Map<String, String> headerMap = new HashMap<>();
        wxReaderHeader.getInnerMap().forEach((key, value) -> {
            headerMap.put(key, value.toString());
        });
        return headerMap;
    }

    /**
     * Calculate the SHA-256 value of a string
     * @param input Input string
     * @return SHA-256 value
     */
    private String calSha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error calculating SHA-256", e);
        }
    }

    /**
     * Read the book
     * @return Whether the reading was successful
     */
    private Boolean readBook() {
        wxReaderData.put("ct", Instant.now().getEpochSecond());
        wxReaderData.put("ts", Instant.now().toEpochMilli());
        wxReaderData.put("rn", RandomUtil.randomInt(0, 1000));
        wxReaderData.put("sg", this.calSha256(wxReaderData.getString("ts") + wxReaderData.getString("rn") + KEY));
        wxReaderData.put("s", calHash(encodeData(wxReaderData)));
        try(HttpResponse response = HttpRequest.post(Constant.READ_URL)
                .headerMap(jsonToMap(wxReaderHeader), true)
                .body(wxReaderData.toString())
                .execute()) {
            if (response.body() != null) {
                JSONObject resData = JSON.parseObject(response.body());
                if (resData.isEmpty()) {
                    return null;
                }
                if (resData.containsKey("succ")) {
                    return true;
                } else if (-2010 == resData.getInteger("errCode")) {
                    throw new RuntimeException("User does not exist, please check the user information.");
                } else if (-2012 == resData.getInteger("errCode")) {
                    return refreshToken.refreshCookie(wxReaderHeader);
                }
                return false;
            } else {
                throw new RuntimeException("Response body is null");
            }
        } catch (Exception e) {
            throw new RuntimeException("An unexpected exception occurs during reading.", e);
        } finally {;
            wxReaderData.remove("s");
        }
    }

    /**
     * Start reading
     */
    public void startRead() {
        refreshToken.refreshCookie(wxReaderHeader);
        int failCount = 0;
        for (int i = 0; i < readNum; ) {
            Boolean readRes = readBook();
            if (readRes == null && failCount < 3) {
                failCount++;
                ThreadUtil.sleep(SLEEP_INTERVAL + RandomUtil.randomInt(0, 10), TimeUnit.SECONDS);
            } else if (readRes) {
                int sleepTime = SLEEP_INTERVAL + RandomUtil.randomInt(0, 10);
                ThreadUtil.sleep(sleepTime, TimeUnit.SECONDS);
                i++;
                log.info("Read the book successfully, read time: {} seconds", sleepTime);
            } else {
                throw new RuntimeException("Read the book failed, please check the user information.");
            }
        }
    }
}