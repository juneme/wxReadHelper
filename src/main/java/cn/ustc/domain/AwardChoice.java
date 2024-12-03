package cn.ustc.domain;

public class AwardChoice {
    private Integer choiceType;
    private Integer awardNum;
    private Integer canChoice;

    public AwardChoice() {
    }

    public Integer getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(Integer choiceType) {
        this.choiceType = choiceType;
    }

    public Integer getAwardNum() {
        return awardNum;
    }

    public void setAwardNum(Integer awardNum) {
        this.awardNum = awardNum;
    }

    public Integer getCanChoice() {
        return canChoice;
    }

    public void setCanChoice(Integer canChoice) {
        this.canChoice = canChoice;
    }

    public AwardChoice(Integer choiceType, Integer awardNum, Integer canChoice) {
        this.choiceType = choiceType;
        this.awardNum = awardNum;
        this.canChoice = canChoice;
    }
}
