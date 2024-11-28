package cn.ustc;

import cn.hutool.core.io.FileUtil;
import cn.ustc.domain.User;
import cn.ustc.worker.Read;
import com.alibaba.fastjson.JSONArray;

import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleUsersReadHelp {
    private static final String DEFAULT_USER_JSON_PATH = "src/main/resources/user.json";

    public static void main(String[] args) {
        String userJsonPath = args.length < 1 ? DEFAULT_USER_JSON_PATH : args[0];
        String usersJson = FileUtil.readString(Paths.get(userJsonPath).toFile(), Charset.defaultCharset());
        List<User> users = JSONArray.parseArray(usersJson, User.class);

        ExecutorService executorService = Executors.newFixedThreadPool(users.size());
        for (User user : users) {
            executorService.submit(() -> new Read(user.getWxReaderData(), user.getWxReaderHeader(), user.getReadNum()).startRead());
        }
        executorService.shutdown();
    }
}