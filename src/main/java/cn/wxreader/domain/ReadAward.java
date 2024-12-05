package cn.wxreader.domain;

import java.util.List;

public class ReadAward {
    private Integer awardLevelId;
    private Integer awardChooseType;
    private Integer awardStatus;
    private String awardStatusDesc;
    private String awardLevelDesc;
    private String awardChoicesDesc;
    private List<AwardChoice> awardChoices;

    public Integer getAwardLevelId() {
        return awardLevelId;
    }

    public void setAwardLevelId(Integer awardLevelId) {
        this.awardLevelId = awardLevelId;
    }

    public Integer getAwardChooseType() {
        return awardChooseType;
    }

    public void setAwardChooseType(Integer awardChooseType) {
        this.awardChooseType = awardChooseType;
    }

    public Integer getAwardStatus() {
        return awardStatus;
    }

    public void setAwardStatus(Integer awardStatus) {
        this.awardStatus = awardStatus;
    }

    public String getAwardStatusDesc() {
        return awardStatusDesc;
    }

    public void setAwardStatusDesc(String awardStatusDesc) {
        this.awardStatusDesc = awardStatusDesc;
    }

    public String getAwardLevelDesc() {
        return awardLevelDesc;
    }

    public void setAwardLevelDesc(String awardLevelDesc) {
        this.awardLevelDesc = awardLevelDesc;
    }

    public String getAwardChoicesDesc() {
        return awardChoicesDesc;
    }

    public void setAwardChoicesDesc(String awardChoicesDesc) {
        this.awardChoicesDesc = awardChoicesDesc;
    }

    public List<AwardChoice> getAwardChoices() {
        return awardChoices;
    }

    public void setAwardChoices(List<AwardChoice> awardChoices) {
        this.awardChoices = awardChoices;
    }

    public ReadAward() {
    }

    public ReadAward(Integer awardLevelId, Integer awardChooseType, Integer awardStatus, String awardStatusDesc, String awardLevelDesc, String awardChoicesDesc, List<AwardChoice> awardChoices) {
        this.awardLevelId = awardLevelId;
        this.awardChooseType = awardChooseType;
        this.awardStatus = awardStatus;
        this.awardStatusDesc = awardStatusDesc;
        this.awardLevelDesc = awardLevelDesc;
        this.awardChoicesDesc = awardChoicesDesc;
        this.awardChoices = awardChoices;
    }
}
