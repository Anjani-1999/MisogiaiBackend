package com.erp.jwt.dao;

import com.erp.jwt.entity.kanban.BoardEntity;
import com.erp.jwt.repo.kanban.BoardRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardDaoService {

    private final BoardRepo boardRepo;


    public BoardDaoService(BoardRepo boardRepo) {
        this.boardRepo = boardRepo;
    }

    public List<BoardEntity> getAllBoards() {
        return boardRepo.findAll();
    }

    public BoardEntity getBoardById(Long boardId) {
        return boardRepo.findById(boardId).orElse(null);
    }

    public BoardEntity createBoard(BoardEntity board) {
        return boardRepo.save(board);
    }


    public BoardEntity getBoardByUserId(Long userId) {
        return boardRepo.findBoardEntityByUserId(userId);
    }
}
