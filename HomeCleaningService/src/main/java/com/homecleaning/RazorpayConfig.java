package com.homecleaning;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "razorpay")
@Getter
@Setter
public class RazorpayConfig {
    private String keyId;
    private String keySecret;

    // getters and setters
}

