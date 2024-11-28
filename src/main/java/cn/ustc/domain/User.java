package cn.ustc.domain;

import com.alibaba.fastjson.JSONObject;

public class User {
    private JSONObject wxReaderHeader;
    private JSONObject wxReaderCookie;
    private JSONObject wxReaderData;
    private Integer readNum;

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

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }
}
