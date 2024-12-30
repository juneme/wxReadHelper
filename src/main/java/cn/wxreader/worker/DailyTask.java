package cn.wxreader.worker;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.wxreader.WxReadDailyTask;
import cn.wxreader.constant.Constant;
import cn.wxreader.domain.*;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;


public class DailyTask {
    private static final Logger log = LoggerFactory.getLogger(DailyTask.class);

    private AndroidHeader androidHeader;
    private JSONObject wxReaderHeader;

    private String wrName;
    private String userAgent;
    public DailyTask(User user) {
        this.wxReaderHeader = user.getWxReaderHeader();
        this.userAgent = user.getUserAgent();
        this.wrName = user.getWrName();
    }

    public String dailyTask() {
        RefreshToken refreshToken = new RefreshToken();
        DailyTaskMessage dailyTaskMessage = new DailyTaskMessage();
        if (refreshToken.refreshCookie(wxReaderHeader)) {
            this.androidHeader = new AndroidHeader(wxReaderHeader, userAgent);
            dailyTaskMessage.setFriendLikeName(friendLike());
        }
        return dailyTaskMessage.formatMsg(wrName);
    }
    private String friendLike() {
        HttpResponse response = HttpRequest.get(String.format(Constant.FRIEND_RANKING_URL, System.currentTimeMillis()))
                .headerMap(androidHeader.toMap(), true)
                .execute();
        FriendRankRes friendRankRes = JSONObject.parseObject(response.body(), FriendRankRes.class);
        List<Rank> friendRankList = friendRankRes.getRanking().stream()
                .filter(f -> !f.getUser().getName().equals(wrName))
                .filter(f -> f.getReadingTime() > 0)
                .collect(Collectors.toList());
        if (friendRankList.isEmpty()) {
            log.warn("【每日任务】{}: 本周暂无好友开始阅读，无法进行点赞", wrName);
            return null;
        }
        List<Rank> unLikedFriends = friendRankList.stream().filter(f -> f.getIsLiked() == 0).collect(Collectors.toList());
        Rank toLikeFriend = unLikedFriends.isEmpty() ? friendRankList.get(0) : unLikedFriends.get(0);
        if (toLikeFriend.getIsLiked() == 1) {
            log.info("【每日任务】{}: 已对好友 {} 进行过点赞，将取消后重新点赞", wrName, toLikeFriend.getUser().getName());
            if (!sendLike(new FriendLikeReq(0, 0, toLikeFriend.getUser().getUserVid()))) {
                return null;
            }
        }
        if (!sendLike(new FriendLikeReq(1, 0, toLikeFriend.getUser().getUserVid()))) {
            return null;
        }
        log.info("【每日任务】{}: 已完成对好友 {} 阅读记录的点赞", wrName, toLikeFriend.getUser().getName());
        return toLikeFriend.getUser().getName();
    }

    private boolean sendLike(FriendLikeReq req) {
        HttpResponse likeRes = HttpRequest.post(Constant.FRIEND_LIKE_URL)
                .headerMap(androidHeader.toMap(), true)
                .body(JSONObject.toJSONString(req))
                .execute();
        if (likeRes.isOk() && likeRes.body().contains("succ")) {
            return true;
        }
        return false;
    }
}
