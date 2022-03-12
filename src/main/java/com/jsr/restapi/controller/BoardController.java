package com.jsr.restapi.controller;



import com.jsr.restapi.entity.Board;
import com.jsr.restapi.entity.BoardLike;
import com.jsr.restapi.entity.User;
import com.jsr.restapi.service.BoardLikeService;
import com.jsr.restapi.service.BoardService;
import com.jsr.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardLikeService boardLikeService;

    @Autowired
    UserService userService;

    // 해더값을 통한 권한 체크성
    @RequestMapping(value="/authcheck", method= RequestMethod.POST)
    public Boolean authCheck(
            @RequestHeader(value="Authorization", defaultValue = "outUser") String accountType) {
        return !accountType.split(" ")[0].equals("outUser") ? true : false;
    }

    //글쓰기 저장
    @RequestMapping(value="/save")
    public ResponseEntity insertBoard(
            @RequestHeader(value="Authorization", defaultValue = "outUser") String accountType,
            @RequestBody Board board) throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Board newBoard = new Board();

        Optional<User> optUser =  userService.findById(Long.parseLong(accountType.split(" ")[1]));
        User user = optUser.get();

        if(!optUser.isPresent()){
            throw new Exception();
        }else {
            //외부 사용자 체크
            if (accountType.equals("outUser")) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                newBoard.setTitle(board.getTitle());
                newBoard.setUserId(user);
                newBoard.setWriter(board.getWriter());
                newBoard.setAccountType(convertAccountType(accountType));
                newBoard.setContent(board.getContent());
                newBoard.setCreateTime(timestamp);

                boardService.saveBoard(newBoard);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    // 글 삭제처리 api
   @RequestMapping(value="/delete")
    public ResponseEntity delete (
            @RequestParam(value="id") int boardId) throws Exception {

       Optional<Board> optionBoard = boardService.findById(boardId);

       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Board board = optionBoard.get();

       if(!optionBoard.isPresent()) {
           throw new Exception();
       }else {
           board.setDelFlag(true);
           board.setDelTime(timestamp);
           boardService.saveBoard(board);
       }
       return new ResponseEntity(HttpStatus.OK);
   }

   //글 수정하기 저장
    @RequestMapping(value="/modify/save")
    public ResponseEntity modify (
            @RequestBody Board board) throws Exception {

        Optional<Board> optionBoard = boardService.findById(board.getId());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Board newBoard = optionBoard.get();

        if(!optionBoard.isPresent()) {
            throw new Exception();
        }else{
            newBoard.setContent(board.getContent());
            newBoard.setTitle(board.getTitle());
            newBoard.setModifyTime(timestamp);
            boardService.saveBoard(newBoard);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //글 목록
    @RequestMapping(value = "/listAll",  method= RequestMethod.POST)
    public ResponseEntity<List<Board>> listBoards(
            @RequestHeader(value="Authorization", defaultValue = "outUser") String accountType) throws Exception {

        List<Board> allBoards = new ArrayList<>();
        List<BoardLike> likeBoards = new ArrayList<>();

        Long userId = Long.parseLong(accountType.split(" ")[1]);

        Optional<List<BoardLike>> optLikeBoards = boardLikeService.findByUserId(userId);
        Optional<List<Board>> optBoards = boardService.findAll();

        allBoards = optBoards.get();
        likeBoards = optLikeBoards.get();
        if(!optBoards.isPresent() || !optLikeBoards.isPresent()) {
            throw new Exception();
        }

        if(accountType.equals("outUser")) {
            return new ResponseEntity(allBoards,HttpStatus.OK);

        //내가 좋아하는 글 표시 true/false
        }else{
           for(Board bData : allBoards ){
               for(BoardLike blData : likeBoards){
                   if(bData.getId() == blData.getBoardId()){
                       bData.setMyLike(true);
                   }
               }
           }
        }
    return new ResponseEntity(allBoards, HttpStatus.OK);
    }

    // 어카운트 타입 한글변환
    public String convertAccountType (String type){
        String name = type.split(" ")[0];
        String convertName = null;

        if(name.equalsIgnoreCase("Realtor")){
            convertName = "공인중개사";
        }else if(name.equalsIgnoreCase("Lessor")){
            convertName = "임대인";
        }else if(name.equalsIgnoreCase("Lessee")){
            convertName ="임차인";
        }
        return convertName;
    }
}
