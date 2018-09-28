package cn.test.sys.utils;

import java.util.UUID;

public class RandomUtils {
    public static String createRandomUUID() {
    	String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
    }
    public static void main(String[] args) {
    	System.out.println(createRandomUUID());
	}
}
