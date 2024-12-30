package cn.wxreader.domain;

public class DailyTaskMessage {
    private String friendLikeName;

    public String getFriendLikeName() {
        return friendLikeName;
    }

    public void setFriendLikeName(String friendLikeName) {
        this.friendLikeName = friendLikeName;
    }

    public DailyTaskMessage() {
    }

    public String formatMsg(String wrName) {
        StringBuilder strb = new StringBuilder();
        strb.append("【每日任务】" + wrName);
        if (friendLikeName != null) {
            strb.append("，已完成互动评论，今日点赞好友：" + friendLikeName);
        }
        return strb.toString();
    }
}
