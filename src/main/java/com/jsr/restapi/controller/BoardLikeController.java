package com.jsr.restapi.controller;

import com.jsr.restapi.entity.Board;
import com.jsr.restapi.entity.BoardLike;
import com.jsr.restapi.service.ARIA;
import com.jsr.restapi.service.BoardLikeService;
import com.jsr.restapi.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/boardLike")
public class BoardLikeController {

    @Autowired
    BoardLikeService boardLikeService;

    @Autowired
    BoardService boardService;

    @RequestMapping(value="/like")
    public Boolean likeBoard(
            @RequestHeader(value="Authorization", defaultValue = "outUser") String accountType,
            @RequestParam(value = "boardId") long boardId){
        long userId =  Long.parseLong(accountType.split(" ")[1]);
        Boolean result = false;

        if(boardId != 0 && userId != 0) {
           Optional<BoardLike> optLikeCheck = boardLikeService.findByBoardIdAndUserId(boardId, userId);
           Optional <Board> optBoard = boardService.findById(boardId);

           if(optLikeCheck.isPresent() || optBoard.isPresent()){
               result = false;
           }else{
               Board board = optBoard.get();
               board.setLikeCount(board.getLikeCount() + 1);
               BoardLike newLike = new BoardLike();
               newLike.setBoardId(boardId);
               newLike.setUserId(userId);
               boardLikeService.saveLike(newLike);

           result = true;
           }
        }
        return result;
    }

    //사용자 자신의 좋아요 리스트
    @RequestMapping(value="/likeList")
    public ResponseEntity<List<BoardLike>> likeList(
            @RequestHeader(value="Authorization", defaultValue = "outUser") String accountType) throws Exception {

        List<BoardLike> userLikeList = new ArrayList<>();

        if (!accountType.equalsIgnoreCase("outUser")) {
            Long userId = Long.parseLong(accountType.split(" ")[1]);
            Optional<List<BoardLike>> optUserLikeList = boardLikeService.findByUserId(userId);

            if (!optUserLikeList.isPresent()) {
                throw new Exception();
            }else{
                userLikeList = optUserLikeList.get();
            }
        }
        return new ResponseEntity(userLikeList, HttpStatus.OK);
    }
}
