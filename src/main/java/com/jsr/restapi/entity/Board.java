package com.jsr.restapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "board")
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //작성자 ID 객체값
    @ManyToOne
    @Column(name = "usrId", nullable=false)
    private User usrId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    //작성시간
    @Column(name = "createTime", nullable = false)
    private Timestamp createTime;

    //수정시간
    @Column(name = "modifyTime")
    private Timestamp modifyTime;

    //삭제시간
    @Column(name = "delTime")
    private Timestamp delTime;

    //삭제 처리 플래그
    @Column(name = "dl_fl")
    private boolean dl_fl;

}