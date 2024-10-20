package com.manager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tb_user_past_password")
@Getter @Setter @NoArgsConstructor
public class PastPasswords {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer psw_id;
    @NotNull
    private Integer user_id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String old_password;
    @NotNull
    private Date change_date;

}
