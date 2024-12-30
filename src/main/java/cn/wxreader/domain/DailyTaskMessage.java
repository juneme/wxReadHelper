package cn.wxreader.domain;

import java.time.LocalDateTime;

public class DailyTaskMessage {
    private String friendLikeName;
    private LocalDateTime loginTime;

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
            strb.append("，已完成互动评论，今日点赞好友：【" + friendLikeName + "】");
        }
        if (loginTime != null) {
            strb.append("，已完成每日登录，今日首次登录时间：【" + loginTime + "】");
        }
        if (friendLikeName == null && loginTime == null) {
            strb.append("，今日未执行任何任务，请查看相关日志");
        }
        return strb.toString();
    }
}
