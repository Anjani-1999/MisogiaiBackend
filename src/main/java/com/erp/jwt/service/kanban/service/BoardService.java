package com.erp.jwt.service.kanban.service;

import com.erp.jwt.request.kanban.BoardRequest;
import com.erp.jwt.response.kanban.BoardResponse;

public interface BoardService {

    Object getAllBoards(String authorizationHeader);

    BoardResponse getBoardByUserId(Long userId);

    BoardResponse createBoard(BoardRequest boardRequest);

    Object updateBoard(String authorizationHeader, Long boardId, Object boardRequest);

    Object deleteBoard(String authorizationHeader, Long boardId);


}
