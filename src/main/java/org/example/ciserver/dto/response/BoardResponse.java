package org.example.ciserver.dto.response;

import org.example.ciserver.domain.entity.Board;

public record BoardResponse(
        Long id,
        String name,
        String text
) {
    public static BoardResponse from(Board board) {
        return new BoardResponse(
            board.getId(),
            board.getName(),
            board.getText()
        );
    }
}