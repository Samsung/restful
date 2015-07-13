package com.samsung.ax.restful.crypt.aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.samsung.ax.restful.crypt.AxCryptException;

/**
 *
 * @author heesik.jeon
 *
 */

public class AxCrypt {

    private static final String key = "MNx/VbJWxnYHIbWZLnfD4A==";

    private AxCrypt() {
    }

    /**
     * @param data
     * @return
     * @throws AxCryptException
     */
    public static final String encrypt(StringBuffer data) throws AxCryptException {
        return encrypt(data.toString());
    }

    /**
     * @param data
     * @return
     * @throws AxCryptException
     */
    public static final String encrypt(String data) throws AxCryptException {
        
        if (data == null) {
            throw new AxCryptException("String is null or keystring is null in AxCrypt.encrypt(String)");
        } else {
            return encrypt(key, data);
        }
    
    }

    /**
     * @param key
     * @param data
     * @return
     * @throws AxCryptException
     */
    public static final String encrypt(String key, String data) throws AxCryptException {
        
        if (key == null || data == null) {
            throw new AxCryptException("String is null or keystring is null in AxCrypt.encrypt(String, String)");
        } else {
            SecretKeySpec keySpec = new SecretKeySpec(Base64.decodeBase64(key), "AES");
            return encrypt(keySpec, data);
        }
    
    }

    /**
     * @param keySpec
     * @param data
     * @return
     * @throws AxCryptException
     */
    private static String encrypt(SecretKeySpec keySpec, String data) throws AxCryptException {
        
        if (keySpec == null || data == null) {
            throw new AxCryptException("Key is null or data is null in AxCrypt.encrypt(String, String)");
        }
        
        byte[] abyte;

        try {
            
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            abyte = cipher.doFinal(data.getBytes("UTF-8"));
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            throw new AxCryptException("Encrypt Exception", e);
        }

        return Base64.encodeBase64String(abyte);

    }
    
    /**
     * @param data
     * @return
     * @throws AxCryptException
     */
    public static final String decrypt(StringBuffer data) throws AxCryptException {
        return decrypt(data.toString());
    }

    /**
     * @param data
     * @return
     * @throws AxCryptException
     */
    public static final String decrypt(String data) throws AxCryptException {
        
        if (data == null) {
            throw new AxCryptException("String is null or keystring is null in AxCrypt.decrypt(String)");
        } else {
            return decrypt(key, data);
        }
    
    }

    /**
     * @param key
     * @param data
     * @return
     * @throws AxCryptException
     */
    public static final String decrypt(String key, String data) throws AxCryptException {
        
        if (data == null || key == null) {
            throw new AxCryptException("String is null or keystring is null in AxCrypt.decrypt(String, String)");
        } else {
            SecretKeySpec keySpec = new SecretKeySpec(Base64.decodeBase64(key), "AES");
            return decrypt(keySpec, data);
        }
    
    }

    /**
     * @param keySpec
     * @param data
     * @return
     * @throws AxCryptException
     */
    private static String decrypt(SecretKeySpec keySpec, String data) throws AxCryptException {
        
        if (keySpec == null || data == null) {
            throw new AxCryptException("Key is null or data is null in AxCrypt.decrypt(String, String)");
        }
        
        byte abyte[] = null;
        
        try {
            
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] decrypt = Base64.decodeBase64(data);
            
            abyte = cipher.doFinal(decrypt);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | IllegalArgumentException | BadPaddingException e) {
            throw new AxCryptException("Input data has been changed illegally.");
        }

        try {
            return new String(abyte, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new AxCryptException("Decrypt Exception", e);
        }
        
    }

}
