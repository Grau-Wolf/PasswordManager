package password_manager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.BufferedReader;
public class Login {
	
	private byte[] salt = new byte[32];
	private int iterations = 50;
	private int keySize = 512;
	Charset set = Charset.defaultCharset();
	
	public Login() throws DecoderException{
	}
	
	public boolean checkLogin(String username, char[] password) throws DecoderException{
		Encrypter encoder = new Encrypter();
		try{
			File userInfo = new File(username + "File.txt");
			if(userInfo.exists()){

				BufferedReader reader = new BufferedReader(new FileReader(userInfo));
				
				String hash = reader.readLine();											//reads password hash
				char[] saltString = reader.readLine().toCharArray();						//reads salt Hex string as char array
				salt = Hex.decodeHex(saltString);											//converts salt Hex back to byte[]
				String encoded = encoder.hashPassword(password, salt, iterations, keySize);	//encodes entered password w/ salt
				reader.close();
				//verifies entered password
				if(hash.equals(encoded)){
					//login successful
					return true;
				}
				else{
					//login failed
					return false;
				}
				
			}
			else{
				System.out.println("User does not Exist. Create a New Account.");
			}
		} catch(IOException e){
			System.out.println("Login File Error!!!");
		}
		return false;
	}
	
}
