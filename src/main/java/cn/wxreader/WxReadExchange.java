package cn.wxreader;

import cn.wxreader.constant.Constant;
import cn.wxreader.domain.User;
import cn.wxreader.worker.Exchange;
import cn.wxreader.worker.Push;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WxReadExchange {
    private static final Logger log = LoggerFactory.getLogger(WxReadExchange.class);
    public static void main(String[] args) {
        String userJsonPath = args.length < 1 ? Constant.DEFAULT_USER_JSON_PATH : args[0];
        List<User> users = User.init(userJsonPath);

        ExecutorService executorService = Executors.newFixedThreadPool(users.size());
        for (User user : users) {
            executorService.submit(() -> {
                try {
                    String exchangeRes = new Exchange(user).exchange();
                    new Push(user, exchangeRes).push();
                } catch (Exception e) {
                    log.error("Error reading for user: {}", user, e);
                }
            });
        }
        executorService.shutdown();
    }
}
