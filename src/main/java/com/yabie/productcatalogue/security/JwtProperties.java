package com.yabie.productcatalogue.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security")
@Data
public class JwtProperties {

    private String secretKey = "mySuperSecretKey&mySuperSecretKey";

    // validity in milliseconds
    private long validityInMs = 3600000; // 1h

}
