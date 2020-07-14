package com.anjuxing.platform.code.util;

import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) {
		String s = "abc, ddc, dfv, aad, \r\n";
		System.out.println(s.replaceFirst(", $", ""));
	}

	public static void test(Object obj, String idfeild) {
		String methodName = "get" + idfeild.substring(0, 1).toUpperCase() + idfeild.substring(1);
		Method method;
		try {
			method = obj.getClass().getMethod(methodName);
			Object value = method.invoke(obj, null);
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
