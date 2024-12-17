package cn.wxreader.enums;

public enum AwardTypeEnum {
    NONE(0,"无"),
    CARD(1,"体验卡"),
    COIN(2,"书币");
    private Integer code;
    private String desc;

    AwardTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AwardTypeEnum findByCode(Integer code) {
        for (AwardTypeEnum awardTypeEnum : AwardTypeEnum.values()) {
            if (awardTypeEnum.getCode().equals(code)) {
                return awardTypeEnum;
            }
        }
        return null;
    }
}
