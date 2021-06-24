package com.brm.mycollegues.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RegisterDto implements Serializable {

    private String username;
    private String password;
    private String name;


}
