
## 项目介绍 
本项目基于[findmover](https://github.com/findmover)的[python](https://github.com/findmover/wxread)项目，使用Java进行二次开发，扩展支持更多功能。

该脚本具备以下功能：

- **自动签到**：默认阅读1分钟，完成每日签到，如需刷阅读时长可通过调整`readNum`来控制每日阅读时长。
- **定时运行**：可部署在GitHub Action/服务器上，支持定时运行并推送到指定渠道。
- **Cookie自动更新**：一次抓包后，脚本能自动获取并更新Cookie，支持长时间使用。
- **奖励兑换**：可在每周日晚上根据阅读时长兑换对应的阅读奖励。
- **多用户支持**：支持多用户配置，实现一次运行完成多个用户签到。
- **自定义用户**：本地运行时支持指定用户文件所在路径，方便多用户配置。

***
## 操作步骤

### 1. 数据准备
#### 1.1 用户数据说明

本脚本支持读取的数据格式为JSON数组，其中`wxReaderHeader`和`wxReaderData`为需通过抓包的形式来获取，其格式如下：
```json
[
  {
    "wxReaderHeader" : {
      "Accept": "application/json, text/plain, */*",
      "Accept-Language": "zh-CN,zh;q=0.9",
      "Connection": "keep-alive",
      "Content-Type": "application/json;charset=UTF-8",
      "Origin": "https://weread.qq.com",
      "Referer": "https://weread.qq.com/web/reader/45e32e105cbbdd45e162ff9",
      "Sec-Fetch-Dest": "empty",
      "Sec-Fetch-Mode": "cors",
      "Sec-Fetch-Site": "same-origin",
      "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36",
      "baggage": "",
      "sec-ch-ua": "\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"",
      "sec-ch-ua-mobile": "?0",
      "sec-ch-ua-platform": "\"Windows\"",
      "sentry-trace": "",
      "Cookie": ""
    },
    "wxReaderData": {
      "appId": "",
      "b": "",
      "c": "",
      "ci": 4,
      "co": 1355,
      "sm": "",
      "pr": 0,
      "rt": 27,
      "ps": "",
      "pc": ""
    },
    "readNum": 120,
    "exchangeAward": "1,1,1,1,1,2,2",
    "pushType": "",
    "pushToken": ""
  }
]
```
| Key              | Value           | 说明                   |
|------------------|-----------------|----------------------|
| `wxReaderHeader` | 微信读书Header (必填) | 必须提供有效的header        |
| `wxReaderData`   | 微信读书Data (必填)   | 必须提供有效的data          |
| `readMinute`     | 阅读时长 (选填)       | 阅读时长，默认1分钟，单位分钟      |
| `exchangeAward`  | 兑换奖励 (选填)       | 兑换奖励，默认0,0,0,0,0,0,0 |
| `pushType`       | 推送类型 (选填)       | 推送类型，默认不推送           |
| `pushToken`      | 推送Token (选填)    | 推送Token，默认不推送        |
`exchangeAward`为每周日晚上根据阅读时长兑换对应的阅读奖励，共7个奖励，其中0表示不兑换，1表示兑换为体验卡，2表示为兑换为书币，如：`1,1,1,1,1,2,2`表示前5个奖励兑换体验卡，第6、7个奖励兑换为书币。
#### 1.2 抓包获取用户数据
在微信阅读官网 [微信读书 (qq.com)](https://weread.qq.com/) 搜索任意一本或自己喜欢的书籍点开阅读点击下一页进行抓包，抓到`read`接口 `https://weread.qq.com/web/book/read` 如果返回格式正常（如：
```json
{
  "succ": 1,
  "synckey": 564589834
}
```
右键复制为Bash格式，然后在 [Convert curl commands to JSON (curlconverter.com)](https://curlconverter.com/json/) 转化为Json之后，复制需要的headers与data字段替换JSON字符串中wxReaderHeader和wxReaderData，根据自己需要配置其它字段，获得可执行的用户JSON字符串。
[!image](./.picture/1.png)
### 2. 部署运行
#### 2.1 GitHub Action部署运行（GitHub运行）

Fork这个仓库，在仓库【Settings】-左侧列表【Secrets and variables】-【Actions】-右侧【Secrets】下方【Repository secrets】填入Key和Value。在本仓库【Actions】启用workflow，选择运行即可。

| Key             | Value       | 说明          |
|-----------------|-------------|-------------|
| `WX_READ_USERS` | 微信读书用户 (必填) | 必须提供有效的读者数据 |

#### 2.2 本地运行
