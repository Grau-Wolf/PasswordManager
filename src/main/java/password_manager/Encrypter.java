package password_manager;

import org.apache.commons.codec.binary.Hex;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public class Encrypter {
    public Encrypter(){
    	
    }

    public String hashPassword( char[] password, byte[] salt, int iterations, int keyLength ) {

        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec keySpecs = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = keyFactory.generateSecret(keySpecs);
            byte[] hash = key.getEncoded();
            System.out.println("Hash: " + Hex.encodeHexString(hash));
            return Hex.encodeHexString(hash);
            
            
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }
    
    public byte[] generateSalt(){
    	final Random r = new SecureRandom();
    	byte[] salt = new byte[32];
    	r.nextBytes(salt);
    	
    	return salt;
    }
}
