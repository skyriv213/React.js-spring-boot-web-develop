package com.study.webtodo.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
/***
 * TodoDTO뿐만 아니라 이후 다른 모델의 응답도 리턴할 수 있도록 generic을 사용하여 설계
 * Todo를 하나만 반환하는 것보단 리스트로 반환하는 경우가 많아서 데이터를 리스트로 반환
 */
public class ResponseDTO<T> {
    private String error;
    private List<T> data;
}
