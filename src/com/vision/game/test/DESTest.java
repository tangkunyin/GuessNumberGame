package com.vision.game.test;

import com.vision.game.utils.DESUtil;

public class DESTest {
	public static void main(String[] args) throws Exception {
		String src="tt";
		String key="who@8075";
		byte[] b=DESUtil.encrypt(src.getBytes(), key.getBytes());
		System.out.println(DESUtil.byte2hex(b));
	}
}
