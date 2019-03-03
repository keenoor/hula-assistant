package com.keenor.hulaassistant.util;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BizUtils {

    public static String getRandomStr() {
        String seed = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678=";
        int d = seed.length();
        StringBuilder randomStr = new StringBuilder();

        for (int i = 0; i < 13; i++) {
            randomStr.append(seed.charAt((int) Math.floor(Math.random() * d)));
        }

        log.info("randomStr: {}", randomStr.toString());
        return randomStr.toString();
    }

    public static String getSign(String member, Long nowTime, String random) {
        String timeStr = nowTime + "";
        member += timeStr;

        for (int i = 0; i < timeStr.length(); i++) {
            member = member.replaceFirst(
                    timeStr.charAt(i) + "",
                    random.charAt(i) + "");
        }
        return member;
    }

    public static void main(String[] args) {
        String randomStr = getRandomStr();
        System.out.println(randomStr);
        String sign = getSign("5a3957362c36e4cc1c8c1f2a", 1551589680000L, "ntdrBG6kmYdM8");
        System.out.println(sign);
        System.out.println("ta3kd7362c36e4ccncGcrf2a1B515m968YdM8".equals(sign));

    }

}
