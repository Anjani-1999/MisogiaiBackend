package com.erp.jwt.repo.kanban;

import com.erp.jwt.entity.kanban.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<BoardEntity,Long> {

    BoardEntity findBoardEntityByUserId(Long userId);
}
