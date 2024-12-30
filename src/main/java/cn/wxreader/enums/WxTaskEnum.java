package cn.wxreader.enums;

public enum WxTaskEnum {
    READ("read", "自动阅读"),
    EXCHANGE("exchange", "自动兑换"),
    DAILY_TASK("dailyTask", "每日任务");
    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    WxTaskEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
