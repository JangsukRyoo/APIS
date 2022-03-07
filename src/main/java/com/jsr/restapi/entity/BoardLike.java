package com.jsr.restapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "boardLike")
public class BoardLike{

    @Id
    @Column(name = "boardId", nullable = false)
    private Long boardId;

    @Column(name = "userId", nullable=false)
    private Long usrId;
}