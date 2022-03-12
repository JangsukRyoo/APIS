package com.jsr.restapi.service;

import com.jsr.restapi.entity.BoardLike;
import com.jsr.restapi.entity.BoardLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardLikeServiceImpl implements BoardLikeService{

    @Autowired
    BoardLikeRepository boardLikeRepository;

    @Override
    public Optional<List<BoardLike>> findByBoardId(long boardId) {
        return boardLikeRepository.findByBoardId(boardId);
    }

    @Override
    public Optional<BoardLike> findByBoardIdAndUserId(long boardId, long userId) {
        return boardLikeRepository.findByBoardIdAndUserId(boardId, userId);
    }

    @Override
    public BoardLike saveLike(BoardLike boardLike) {
        return boardLikeRepository.save(boardLike);
    }

    @Override
    public Optional<List<BoardLike>> findByUserId(long userId) {
        return boardLikeRepository.findByUserId(userId);
    }
}
