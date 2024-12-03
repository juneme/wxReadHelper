package cn.ustc.worker;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.ustc.constant.Constant;
import cn.ustc.domain.*;
import cn.ustc.enums.AwardTypeEnum;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exchange {
    private static final Logger log = LoggerFactory.getLogger(Exchange.class);
    private ExchangeHeader exchangeHeader;
    private Map<Integer, Integer> idAndExchangeMap = new HashMap<>();
    private JSONObject wxReaderHeader;
    public static final List<Integer> AWARD_LEVEL_IDS = Arrays.asList(4, 1, 2, 3, 11, 12, 13);

    public Exchange(JSONObject wxReaderHeader, String exchangeAward) {
        this.wxReaderHeader = wxReaderHeader;
        initializeExchangeMap(exchangeAward);
    }

    private void initializeExchangeMap(String exchangeAward) {
        if (exchangeAward == null || exchangeAward.isEmpty()) {
            exchangeAward = "0,0,0,0,0,0,0";
        }
        List<Integer> collect = Arrays.stream(exchangeAward.split(","))
                .map(Integer::parseInt).collect(Collectors.toList());
        for (int i = 0; i < AWARD_LEVEL_IDS.size(); i++) {
            idAndExchangeMap.put(AWARD_LEVEL_IDS.get(i), collect.get(i));
        }
    }

    public void exchange() {
        RefreshToken refreshToken = new RefreshToken();
        if (refreshToken.refreshCookie(wxReaderHeader)) {
            this.exchangeHeader = new ExchangeHeader(wxReaderHeader);
            ExchangeAwardRes awardRes = sendExchange(null);
            List<ReadAward> toExchangeAwards = getToExchangeAwards(awardRes);
            Map<Integer, Integer> rewardMap = new HashMap<>(2);
            for (ReadAward toExchange : toExchangeAwards) {
                exchangeAward(toExchange, rewardMap);
            }
            logExchangeSummary(awardRes, rewardMap);
        }
    }

    private List<ReadAward> getToExchangeAwards(ExchangeAwardRes awardRes) {
        return Stream.concat(awardRes.getReadtimeAwards().stream(), awardRes.getReaddayAwards().stream())
                .filter(a -> a.getAwardStatus() == 1)
                .collect(Collectors.toList());
    }

    private void exchangeAward(ReadAward toExchange, Map<Integer, Integer> rewardMap) {
        Integer awardLevelId = toExchange.getAwardLevelId();
        AwardTypeEnum awardType = AwardTypeEnum.getAwardTypeEnum(idAndExchangeMap.get(awardLevelId));
        if (awardType == AwardTypeEnum.NONE) {
            return;
        }
        AwardChoice awardChoice = toExchange.getAwardChoices().stream()
                .filter(s -> s.getChoiceType() == awardType.getCode())
                .findFirst()
                .orElse(null);
        if (awardChoice == null || awardChoice.getCanChoice() != 1) {
            log.info("{}选择兑换{}失败，此奖励不可兑换", toExchange.getAwardLevelDesc(), awardType.getDesc());
            return;
        }
        ExchangeAwardReq req = new ExchangeAwardReq(awardLevelId, awardType.getCode(), 1);
        ExchangeAwardRes response = sendExchange(req);
        updateRewardMap(response, awardLevelId, awardType, rewardMap, awardChoice);
    }

    private void updateRewardMap(ExchangeAwardRes response, Integer awardLevelId, AwardTypeEnum awardType, Map<Integer, Integer> rewardMap, AwardChoice awardChoice) {
        ReadAward exchangeResult = Stream.concat(response.getReadtimeAwards().stream(), response.getReaddayAwards().stream())
                .filter(a -> a.getAwardLevelId() == awardLevelId)
                .findFirst()
                .orElse(null);
        if (exchangeResult != null && exchangeResult.getAwardStatus() == 2) {
            rewardMap.put(awardType.getCode(), rewardMap.getOrDefault(awardType.getCode(), 0) + awardChoice.getAwardNum());
            log.info("时长兑福利{}兑换成功，此次选择兑换：{}，数量：{}", exchangeResult.getAwardLevelDesc(), awardType.getDesc(), awardChoice.getAwardNum());
        }
    }

    private void logExchangeSummary(ExchangeAwardRes awardRes, Map<Integer, Integer> rewardMap) {
        int card = rewardMap.getOrDefault(AwardTypeEnum.CARD.getCode(), 0);
        int coin = rewardMap.getOrDefault(AwardTypeEnum.COIN.getCode(), 0);
        log.info("本周总计阅读{}天，共{}分钟，此次兑换{}天体验卡，{}个书币", awardRes.getReadingDay(), awardRes.getReadingTime(), card, coin);
    }

    private ExchangeAwardRes sendExchange(ExchangeAwardReq req) {
        if (req == null) {
            req = ExchangeAwardReq.init();
        }
        HttpResponse response = HttpRequest.post(Constant.EXCHANGE_URL)
                .headerMap(exchangeHeader.toMap(), true)
                .body(JSONObject.toJSONString(req))
                .execute();
        return JSONObject.parseObject(response.body(), ExchangeAwardRes.class);
    }
}