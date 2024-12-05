package cn.wxreader.domain;

import java.util.List;

public class ExchangeAwardRes {
    private Integer readingTime;
    private Integer readingDay;
    private Integer isMCardVip;
    private List<ReadAward> readtimeAwards;
    private List<ReadAward> readdayAwards;

    public Integer getReadingTime() {
        return readingTime / 60;
    }

    public void setReadingTime(Integer readingTime) {
        this.readingTime = readingTime;
    }

    public Integer getReadingDay() {
        return readingDay;
    }

    public void setReadingDay(Integer readingDay) {
        this.readingDay = readingDay;
    }

    public Integer getIsMCardVip() {
        return isMCardVip;
    }

    public void setIsMCardVip(Integer isMCardVip) {
        this.isMCardVip = isMCardVip;
    }

    public List<ReadAward> getReadtimeAwards() {
        return readtimeAwards;
    }

    public void setReadtimeAwards(List<ReadAward> readtimeAwards) {
        this.readtimeAwards = readtimeAwards;
    }

    public List<ReadAward> getReaddayAwards() {
        return readdayAwards;
    }

    public void setReaddayAwards(List<ReadAward> readdayAwards) {
        this.readdayAwards = readdayAwards;
    }

    public ExchangeAwardRes() {
    }

    public ExchangeAwardRes(Integer readingTime, Integer readingDay, Integer isMCardVip, List<ReadAward> readtimeAwards, List<ReadAward> readdayAwards) {
        this.readingTime = readingTime;
        this.readingDay = readingDay;
        this.isMCardVip = isMCardVip;
        this.readtimeAwards = readtimeAwards;
        this.readdayAwards = readdayAwards;
    }
}
