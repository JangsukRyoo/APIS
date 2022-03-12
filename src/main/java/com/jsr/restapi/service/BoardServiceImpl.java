package com.jsr.restapi.service;

import com.jsr.restapi.entity.Board;
import com.jsr.restapi.entity.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Optional<Board> findById(long boardId) {
        return boardRepository.findByIdAndDelFlagIsFalse(boardId);
    }

    @Override
    public Optional<List<Board>> findAll() {
        return boardRepository.findAllByDelFlagIsFalse();
    }


}
