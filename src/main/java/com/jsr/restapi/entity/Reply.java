package com.jsr.restapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;

public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "boardId", nullable = false)
    private long boardid;

    @Column(name = "replyId")
    private long replyId;

    //작성자 ID 객체값
    @ManyToOne
    @JoinColumn(name = "usrId", nullable=false)
    private User usrId;

    //사용자 이름
    @Column(name = "writer", nullable = false)
    private String writer;

    //사용자 어카운트 타입
    @Column(name ="accountType", nullable = false)
    private String accountType;

    //댓글
    @Column(name = "content", nullable = false)
    private String content;

    //작성시간
    @Column(name = "createTime", nullable = false)
    private Timestamp createTime;

    //수정시간
    @Column(name = "modifyTime")
    private Timestamp modifyTime;

    //삭제 처리 플래그
    @Column(name = "delFlag")
    private boolean delFlag;

    //댓글 좋아요수
    @Column(name = "like")
    private int like;
}
