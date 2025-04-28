package com.erp.jwt.records;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


@ConfigurationProperties(prefix = "jwt")
@Data
public class RSAKeyRecord {
    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;
}
