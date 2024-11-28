package cn.ustc.domain;

public class ReadResult {
    private boolean readFlag;
    private boolean refreshFlag;

    public ReadResult(boolean readFlag, boolean refreshFlag) {
        this.readFlag = readFlag;
        this.refreshFlag = refreshFlag;
    }

    public boolean isSuccess() {
        return readFlag || readFlag;
    }
}
