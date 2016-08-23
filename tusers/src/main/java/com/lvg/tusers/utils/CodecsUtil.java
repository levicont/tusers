package com.lvg.tusers.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class CodecsUtil {
	
	public static String getMD5(String str){
		return DigestUtils.md5Hex(str);
	}
	
	public static boolean isValidStr(String md5Str, String key){
		if(md5Str == null || key == null)
			return false;
		return md5Str.equals(getMD5(key));
	}
	
}
