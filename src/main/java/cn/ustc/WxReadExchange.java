package cn.ustc;

import cn.ustc.constant.Constant;
import cn.ustc.domain.User;
import cn.ustc.worker.Exchange;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WxReadExchange {
    public static void main(String[] args) {
        String userJsonPath = args.length < 1 ? Constant.DEFAULT_USER_JSON_PATH : args[0];
        List<User> users = User.init(userJsonPath);

        ExecutorService executorService = Executors.newFixedThreadPool(users.size());
        for (User user : users) {
            executorService.submit(() -> new Exchange(user.getWxReaderHeader(), user.getExchangeAward()).exchange());
        }
        executorService.shutdown();
    }
}
