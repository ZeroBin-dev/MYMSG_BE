package com.myapi.mymsgapi.comm.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtil {

  private static final String key = "54863115488974651235547863215531"; // 32자리난수
  private static final String iv = key.substring(0, 16); // 16byte<

  /**
   * AES256 암호화
   */
  public static String encrypt(String text) {
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
      IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
      cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

      byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
      return Base64.getEncoder().encodeToString(encrypted);
    } catch (Exception e) {
      return "";
    }
  }

  /**
   * AES256 복호화
   */
  public static String decrypt(String cipherText) {
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
      IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
      cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

      byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
      byte[] decrypted = cipher.doFinal(decodedBytes);
      return new String(decrypted, "UTF-8");
    } catch (Exception e) {
      return "";
    }
  }

}
