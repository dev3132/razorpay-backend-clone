package com.razorpay.vault.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor.CipherAlgorithm;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

@Configuration
public class VaultEncryptionConfig {
	
	public static BytesEncryptor panEncrypter(byte[] dek) {
        SecretKeySpec decKey = new SecretKeySpec(dek, "AES");
        return new AesBytesEncryptor(decKey, KeyGenerators.secureRandom(12),
                CipherAlgorithm.GCM);
    }

}
