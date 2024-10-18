package com.manager.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tb_manssage")
@Getter @Setter @NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;
    private String menssage;
    private String answer;
    @ManyToOne
    @JsonIgnoreProperties("menssage")
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    private  Date date = new Date(System.currentTimeMillis());
}
