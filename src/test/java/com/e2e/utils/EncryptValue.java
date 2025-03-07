package com.e2e.utils;

import javax.crypto.SecretKey;
public class EncryptValue
{    public static void main(String[] args) throws Exception
{        String username = "U19670";
    String password = "123";
   SecretKey key = EncryptionUtil.generateKey();
    String encryptedUsername = EncryptionUtil.encrypt(username,key);
    String encryptedPassword = EncryptionUtil.encrypt(password,key);
    System.out.println("Encrypted CC: " + encryptedUsername);
    System.out.println("Encrypted CVV: " + encryptedPassword);
}
}