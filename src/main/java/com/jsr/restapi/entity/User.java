package com.jsr.restapi.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickName", nullable=false)
    private String nickName;

    @Column(name = "nicksName", nullable=false)
    private String nicksName;

    @Column(name = "userName", nullable=false)
    private String userName;

    @Column(name = "accountType", nullable=false)
    private String accountType;

    @Column(name = "quit")
    private boolean quit;

}