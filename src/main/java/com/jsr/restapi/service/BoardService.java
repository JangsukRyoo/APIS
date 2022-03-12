package com.jsr.restapi.service;

import com.jsr.restapi.entity.Board;
import com.jsr.restapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface BoardService {

        Board saveBoard(Board board);

        Optional<Board> findById(long boardId);

        Optional<List<Board>> findAll();


}
