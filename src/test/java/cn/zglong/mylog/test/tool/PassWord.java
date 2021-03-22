package cn.zglong.mylog.test.tool;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

public class PassWord {
	public static void main(String[] args) {
		String content = "test中文";
		System.out.println(content);
		//随机生成密钥
		byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
		System.out.println(key.length);
//		byte[] key = {1,2,3,6,7};
		System.out.println(key.toString());
		//转换为字符串
		String a= new String(key);
		System.out.println("String(key)"+a.toString());
		String strArray = Convert.toStr(key);
		System.out.println("key"+strArray);
		//字符串转key
		Byte[] byteArray = Convert.toByteArray(strArray);
		System.out.println(byteArray);
		for (Byte byte1 : byteArray) {
			System.out.println(byte1);
		}
		/*//构建
		SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
		System.out.println(aes.toString());
		//aes转换为json对象
		JSONObject jsonObject = new JSONObject(aes);
		String string = jsonObject.toString();
		System.out.println(string);
		//加密
		byte[] encrypt = aes.encrypt(content);
		//解密
		byte[] decrypt = aes.decrypt(encrypt);
		System.out.println(decrypt);
		//加密为16进制表示
		String encryptHex = aes.encryptHex(content);
		//解密为字符串 
		//解密同时用到
		//aes通过key二次加密后的的密钥 把key存到数据库
		//解密时拿到key,逆向解密(通过key构建aes后解密)后获得明文
		//encryptHex加密后的16进制从数据库取出
		String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
		System.out.println(decryptStr);*/
		/*
		 * test中文
[B@73035e27
cn.hutool.crypto.symmetric.SymmetricCrypto@2fc14f68
[B@6adca536
a3c7966f8c05407ff3d7163badfec640
test中文*/
	}
}
