package com.tenghu.financial.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 安全密码工具类
 * @author Arvin_Li
 *
 */
public class SecurityPwdUtil {
	private static final Logger log=LoggerFactory.getLogger(SecurityPwdUtil.class);
	private SecurityPwdUtil(){}
	
	/**
	 * 验证密码
	 * @param attemptedPassword 需要验证的密码
	 * @param password 原密码
	 * @param salt 密码盐
	 * @return
	 */
	public static boolean authenticate(String attemptedPassword,String password,String salt){
		String pwd=getSecurityPassword(attemptedPassword, salt);
		return pwd.trim().equals(password.trim());
	}
	
	/**
	 * 获取加密后的密码
	 * @param password 明文密码
	 * @param salt 密码盐
	 * @return
	 */
	public static String getSecurityPassword(String password,String salt){
		String algorithm = "PBKDF2WithHmacSHA1";
		int derivedKeyLength = 160;
		int iterations = 20000;
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(),
				iterations, derivedKeyLength);
		byte[] pwds = null;
		SecretKeyFactory sdf;
		try {
			sdf = SecretKeyFactory.getInstance(algorithm);
			pwds = sdf.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			log.debug("SecurityPwdUtil->getSecurityPassword:"+e.getMessage());
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			log.debug("SecurityPwdUtil->getSecurityPassword:"+e.getMessage());
		}
		
		return pwds == null ? "" : StringUtil.byteToStr(pwds);
	}
	
	/**
	 * 获取密码盐
	 * @return
	 */
	public static String generateSale(){
		byte[] salt=new byte[8];
		try {
			SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
			random.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			log.debug("SecurityPwdUtil->generateSale:"+e.getMessage());
		}
		return StringUtil.byteToStr(salt);
	}
}
