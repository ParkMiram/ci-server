package org.example.ciserver.dto.request;

import org.example.ciserver.domain.entity.Board;

public record BoardRequest(
        Long id,
        String name,
        String text
) {
    public Board toEntity() {
        return Board.builder()
                .name(name)
                .text(text)
                .build();
    }
}