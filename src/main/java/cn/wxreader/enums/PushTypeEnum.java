package cn.wxreader.enums;

public enum PushTypeEnum {
    PUSH_PLUS("pushPlus", "Push Plus");

    private String type;
    private String desc;

    PushTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static PushTypeEnum findByType(String type) {
        for (PushTypeEnum pushTypeEnum : PushTypeEnum.values()) {
            if (pushTypeEnum.getType().equalsIgnoreCase(type)) {
                return pushTypeEnum;
            }
        }
        return null;
    }
}
