package com.tenghu.financial.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串工具类
 * @author Arvin_Li
 *
 */
public class StringUtil {
	private StringUtil(){}
	private static final Logger log =LoggerFactory.getLogger(StringUtil.class);
	
	/**
	 * Md5加密
	 * @param needStr 加密字符串
	 * @return
	 */
	public static String md5Encryption(String needStr){
		MessageDigest md=null;
		String encryStr=null;
		try {
			md=MessageDigest.getInstance("MD5");//创建MessageDigest对象
			//进行MD5加密
			byte[] bytes=md.digest(needStr.getBytes());
			//将字节数组转为字符串
			encryStr=byteToStr(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			log.debug("StringUtil->md5Encryption"+e.getMessage());
		}
		return encryStr;
	}
	
	/**
	 * 将字节数组转为十六进制字符串
	 * @param bytes
	 * @return
	 */
	public static String byteToStr(byte[] bytes){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<bytes.length;i++){
			sb.append(byteToHexStr(bytes[i]));
		}
		return sb.toString();
	}
	
	/**
	 * 将字节码转为16进制字符串
	 * @param mByte
	 * @return
	 */
	public static String byteToHexStr(byte mByte){
		char[] digit={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tmpArr=new char[2];
		tmpArr[0]=digit[(mByte>>>4)&0X0F];
		tmpArr[1]=digit[mByte&0X0F];
		return new String(tmpArr).toString().toLowerCase();
	}
}
