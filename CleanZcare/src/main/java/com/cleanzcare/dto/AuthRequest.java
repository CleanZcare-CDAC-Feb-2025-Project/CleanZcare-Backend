// AuthRequest.java
package com.cleanzcare.dto;

import lombok.*;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
