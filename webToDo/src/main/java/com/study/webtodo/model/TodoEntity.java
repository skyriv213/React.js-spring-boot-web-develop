package com.study.webtodo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Todo")
public class TodoEntity {
    @Id  // 기본 키가 될 필드 저장
    @GeneratedValue(generator = "system-uuid") // Id를 자동 생성
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id; // 해당 오브젝트의 아이디
    private String userId; // 오브젝트를 생성한 사용자의 아이디
    private String title; // Todo 타이틀
    private boolean done; // true - todo를 완료한 경우(check)
}
