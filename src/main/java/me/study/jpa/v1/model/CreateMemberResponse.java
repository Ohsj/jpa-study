package me.study.jpa.v1.model;

import lombok.Data;

@Data
public class CreateMemberResponse {
    private Long id;

    public CreateMemberResponse(Long id) {
        this.id = id;
    }
}
