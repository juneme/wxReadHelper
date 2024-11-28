package cn.ustc;

import cn.ustc.worker.Read;

public class WxReadHelp {
    public static void main(String[] args) {
        Read read = new Read();
        read.initEnv();
        read.startRead();
    }
}
