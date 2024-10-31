package com.manager.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserLogin {

    private Integer id;
    private String username;
    private String password;
    private String token;
    private String name;
    private String picture;

}
