package com.study.webtodo.dto;

import com.study.webtodo.model.TodoEntity;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoDTO {
    private String id;
    private String title;
    private boolean done;

    // entity를 dto로 만드는 메서드
    public TodoDTO(final TodoEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();

    }

    // TodoDto를 입력받아 TodoEntity를 만드는 메서드
    public static TodoEntity toEntity(final TodoDTO dto) {
        return TodoEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .build();
    }

}
