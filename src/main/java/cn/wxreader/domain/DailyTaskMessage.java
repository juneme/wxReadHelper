package cn.wxreader.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTaskMessage {
    private String friendLikeRes;
    private LocalDateTime loginTime;
    private LocalDate lastReadDate;

    public String getFriendLikeRes() {
        return friendLikeRes;
    }

    public void setFriendLikeRes(String friendLikeRes) {
        this.friendLikeRes = friendLikeRes;
    }

    public LocalDate getLastReadDate() {
        return lastReadDate;
    }

    public void setLastReadDate(LocalDate lastReadDate) {
        this.lastReadDate = lastReadDate;
    }

    public DailyTaskMessage() {
    }

    public String formatMsg(String wrName) {
        StringBuilder strb = new StringBuilder();
        strb.append("【每日任务】").append(wrName);
        if (friendLikeRes != null) {
            strb.append("，已完成互动评论，今日点赞好友：【").append(friendLikeRes).append("】");
        }
        if (loginTime != null) {
            strb.append("，已完成每日登录，今日首次登录时间：【").append(loginTime).append("】");
        }
        if (lastReadDate != null) {
            if (LocalDate.now().isEqual(lastReadDate)) {
                strb.append("，今日已完成阅读，最后阅读日期：【").append(lastReadDate).append("】");
            } else {
                strb.append("，今日未完成阅读，最后阅读日期：【").append(lastReadDate).append("】");
            }
        }
        if (friendLikeRes == null && loginTime == null && lastReadDate == null) {
            strb.append("，今日未执行任何任务，请查看相关日志");
        }
        return strb.toString();
    }
}
