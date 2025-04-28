package com.erp.jwt.service.kanban.impl;

import com.erp.jwt.dao.BoardDaoService;
import com.erp.jwt.entity.kanban.BoardEntity;
import com.erp.jwt.mapper.GlobalMapper;
import com.erp.jwt.request.kanban.BoardRequest;
import com.erp.jwt.request.kanban.ColumnRequest;
import com.erp.jwt.response.kanban.BoardResponse;
import com.erp.jwt.service.kanban.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {


    private final BoardDaoService boardDaoService;
    private final GlobalMapper globalMapper;

    public BoardServiceImpl(BoardDaoService boardDaoService, GlobalMapper globalMapper) {
        this.boardDaoService = boardDaoService;
        this.globalMapper = globalMapper;
    }

    @Override
    public Object getAllBoards(String authorizationHeader) {
        return null;
    }

    @Override
    public BoardResponse getBoardByUserId(Long userId) {
        BoardResponse boardResponse = new BoardResponse();
        try{
            BoardEntity boardEntity = boardDaoService.getBoardByUserId(userId);
            BoardRequest boardRequest = globalMapper.entityToDto(boardEntity, BoardRequest.class);
            boardResponse.setData(boardRequest);
            boardResponse.setStatus(HttpStatus.OK.value());
            boardResponse.setMessage("Board fetched successfully");
            log.info("board fetched successfully {}",boardRequest);
            return boardResponse;
        }catch (Exception e){
            boardResponse.setMessage(e.getMessage());
            boardResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            log.error("Error fetching board {}",e.getMessage());
            return boardResponse;
        }
    }

    @Override
    public BoardResponse createBoard(BoardRequest boardRequest) {
        BoardResponse boardResponse = new BoardResponse();
        try{
            createDefaultColumns(boardRequest);
            BoardEntity boardEntity = globalMapper.dtoToEntity(boardRequest, BoardEntity.class);
            boardEntity.getColumns().forEach(columnEntity -> columnEntity.setBoardEntity(boardEntity));
            BoardEntity savedEntity = boardDaoService.createBoard(boardEntity);
            BoardRequest savedRequest = globalMapper.entityToDto(savedEntity, BoardRequest.class);
            boardResponse.setData(savedRequest);
            log.info("created board {}",savedRequest);
            return boardResponse;
        }catch (Exception e){
            boardResponse.setMessage(e.getMessage());
            boardResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return boardResponse;
        }
    }

    private static void createDefaultColumns(BoardRequest boardRequest) {
        List<ColumnRequest> columnRequestList = new ArrayList<>();
        ColumnRequest toDoCol = new ColumnRequest();
        toDoCol.setColumnName("TO DO");
        columnRequestList.add(toDoCol);
        ColumnRequest inProgCol = new ColumnRequest();
        inProgCol.setColumnName("IN PROGRESS");
        columnRequestList.add(inProgCol);
        ColumnRequest inRevCol = new ColumnRequest();
        inRevCol.setColumnName("IN REVIEW");
        columnRequestList.add(inRevCol);
        ColumnRequest doneCol = new ColumnRequest();
        doneCol.setColumnName("DONE");
        columnRequestList.add(doneCol);
        boardRequest.setColumns(columnRequestList);
    }

    @Override
    public Object updateBoard(String authorizationHeader, Long boardId, Object boardRequest) {
        return null;
    }

    @Override
    public Object deleteBoard(String authorizationHeader, Long boardId) {
        return null;
    }
}
