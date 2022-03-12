package com.jsr.restapi.service;

import com.jsr.restapi.entity.BoardLike;

import java.util.List;
import java.util.Optional;

public interface BoardLikeService {

    public Optional<List<BoardLike>> findByBoardId(long boardId);

    public Optional<List<BoardLike>> findByUserId(long userId);

    public Optional<BoardLike> findByBoardIdAndUserId(long boardId, long userId);

    public BoardLike saveLike(BoardLike boardLike);


}
