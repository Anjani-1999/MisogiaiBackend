package com.erp.jwt.entity.kanban;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="COLUMN_ENTITY")
@JsonIdentityInfo(
        generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator.class,
        property = "columnId"
)
public class ColumnEntity {

    @Id
    @GeneratedValue
    private Long columnId;

    private String columnName;

    @ManyToOne
    @JoinColumn(name = "board_id_fk", referencedColumnName = "boardId")
    private BoardEntity boardEntity;
}
