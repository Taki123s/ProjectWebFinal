package model;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;


public class Encode {
 public String toSHA1(String st) {
	 String result = null;
	 String begin = "hhtqiDkue;]asdwqvbt[dawe,.123";
	 String end = "dasrfwetyqwertyuivbnm@!$^^";
	 st = begin + st + end;
	 try {
		byte[] dataBytes =st.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		result = Base64.encodeBase64String(md.digest(dataBytes));
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return result;
 }
 public static void main(String[] args) {

}
}
