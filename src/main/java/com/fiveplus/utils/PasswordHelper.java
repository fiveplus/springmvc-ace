package com.fiveplus.utils;

import com.fiveplus.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private int hashIterations = 3;
	
	public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator){
		this.randomNumberGenerator = randomNumberGenerator;
	}
	
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}
	
	public User encryptPassword(User user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				hashIterations).toHex();
		user.setPassword(newPassword);
		return user;
	}
	
	public static void main(String[] args) {
		/*String algorithmName = "md5";
		String username = "admin";
		String password = "Wuhan2016";
		String salt1 = username;
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 3;
		SimpleHash hash = new SimpleHash(algorithmName, password,
				salt1 + salt2, hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println(encodedPassword);
		System.out.println(salt2);
		*/
		User u = new User();
		u.setPassword("Wuhan2016");
		u.setLoginName("admin");
		u = new PasswordHelper().encryptPassword(u);
		System.out.println(u.getPassword());
		System.out.println(u.getSalt());
		
	}
	
}
