package com.jsr.restapi.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    Optional<List<BoardLike>> findByBoardId(long BoarId);

    Optional<List<BoardLike>> findByUserId(long userId);

    Optional <BoardLike> findByBoardIdAndUserId(long boardId, long userId);

    BoardLike save(BoardLike boardLike);


}
