package password_manager;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Hex;
public class CreateNewUser {
	private byte[] salt = new byte[32];
	private int iterations = 50;
	private int keySize = 512;
	private String encoded;
	
	public CreateNewUser(String username, char[] password){
		try{
			File userInfo = new File(username + "File.txt");
			
			
			
			if(userInfo.createNewFile()){
				
				Encrypter encoder = new Encrypter();
				salt = encoder.generateSalt();						//generates salt for password hashing
				encoded = encoder.hashPassword(password, salt, iterations, keySize);	//generates hashed password
				
				//message pops up confirming successful account creation
				JFrame warningGUI = new JFrame();
				String message = "Account Successfully Created.";
				JOptionPane.showMessageDialog(warningGUI, message);
				
				//writes salt & hashed password to user file
				PrintWriter writer = new PrintWriter(userInfo, "UTF-8");
				writer.println(encoded);
				writer.println(Hex.encodeHexString(salt));	//encodes byte array as Hex string, important to keep integrity of salt
				
				writer.close();
			}
			else{
				
				//If User already Exists, warning pops up in center of screen.
				JFrame warningGUI = new JFrame();
				String message = "User Already Exists.";
				JOptionPane.showMessageDialog(warningGUI, message);
			}
			
			
		} catch(IOException e){
			System.out.println("User File Creation/Read Error!");
		}
		
	}
	
}
