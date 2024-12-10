package cn.wxreader.domain;

import cn.wxreader.constant.Constant;

public class ExchangeAwardReq {
    private Integer awardLevelId;
    private Integer awardChoiceType;
    private Integer isExchangeAward;
    private String pf;

    public ExchangeAwardReq(Integer awardLevelId, Integer awardChoiceType, Integer isExchangeAward) {
        this.awardLevelId = awardLevelId;
        this.awardChoiceType = awardChoiceType;
        this.isExchangeAward = isExchangeAward;
        this.pf = Constant.Android_PLATFORM;
    }

    public ExchangeAwardReq() {
    }

    public static ExchangeAwardReq init(String platForm) {
        return new ExchangeAwardReq(0,0,0);
    }

    public Integer getAwardLevelId() {
        return awardLevelId;
    }

    public void setAwardLevelId(Integer awardLevelId) {
        this.awardLevelId = awardLevelId;
    }

    public Integer getAwardChoiceType() {
        return awardChoiceType;
    }

    public void setAwardChoiceType(Integer awardChoiceType) {
        this.awardChoiceType = awardChoiceType;
    }

    public Integer getIsExchangeAward() {
        return isExchangeAward;
    }

    public void setIsExchangeAward(Integer isExchangeAward) {
        this.isExchangeAward = isExchangeAward;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }
}
