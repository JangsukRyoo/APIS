package com.jsr.restapi.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Board save(Board board);

    Optional<Board> findByIdAndDelFlagIsFalse(long boardId);

    Optional<List<Board>> findAllByDelFlagIsFalse();


}
