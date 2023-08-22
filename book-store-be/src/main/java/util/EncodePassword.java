package util;

import org.apache.commons.codec.binary.Base64;

public class EncodePassword {
	public static String encodePassword(String password) {
		String salt = "c1/B4a^n?|hG&8%d$8#c2@6&B*4^Dvw4#gd";
		String result = "";
		password = password+salt;
		byte[] enCodeBytes = Base64.encodeBase64(password.getBytes());
		result = new String(enCodeBytes);
		return result;
	}

}
