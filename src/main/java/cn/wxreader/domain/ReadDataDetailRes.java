package cn.wxreader.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class ReadDataDetailRes {
    private Map<String, Integer> readTimes;
    private JSONArray readLongest;
    private Map<String, String> rank;
    private Integer registTime;
    private Integer compare;
    private Integer dayAverageReadTime;
    private Integer baseTime;
    private Integer totalReadTime;
    private JSONObject shareInfo;
    private String readRecordsWord;
    private String readDistributionWord;
    private String styleType;

    public Map<String, Integer> getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(Map<String, Integer> readTimes) {
        this.readTimes = readTimes;
    }

    public JSONArray getReadLongest() {
        return readLongest;
    }

    public void setReadLongest(JSONArray readLongest) {
        this.readLongest = readLongest;
    }

    public Map<String, String> getRank() {
        return rank;
    }

    public void setRank(Map<String, String> rank) {
        this.rank = rank;
    }

    public Integer getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Integer registTime) {
        this.registTime = registTime;
    }

    public Integer getCompare() {
        return compare;
    }

    public void setCompare(Integer compare) {
        this.compare = compare;
    }

    public Integer getDayAverageReadTime() {
        return dayAverageReadTime;
    }

    public void setDayAverageReadTime(Integer dayAverageReadTime) {
        this.dayAverageReadTime = dayAverageReadTime;
    }

    public Integer getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(Integer baseTime) {
        this.baseTime = baseTime;
    }

    public Integer getTotalReadTime() {
        return totalReadTime;
    }

    public void setTotalReadTime(Integer totalReadTime) {
        this.totalReadTime = totalReadTime;
    }

    public JSONObject getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(JSONObject shareInfo) {
        this.shareInfo = shareInfo;
    }

    public String getReadRecordsWord() {
        return readRecordsWord;
    }

    public void setReadRecordsWord(String readRecordsWord) {
        this.readRecordsWord = readRecordsWord;
    }

    public String getReadDistributionWord() {
        return readDistributionWord;
    }

    public void setReadDistributionWord(String readDistributionWord) {
        this.readDistributionWord = readDistributionWord;
    }

    public String getStyleType() {
        return styleType;
    }

    public void setStyleType(String styleType) {
        this.styleType = styleType;
    }
}
