package org.example.ciserver.service;

import org.example.ciserver.dto.request.BoardRequest;
import org.example.ciserver.dto.response.BoardResponse;

import java.util.List;

public interface BoardService {
    void insertBoard(BoardRequest request);

    List<BoardResponse> getAllBoards();

    void deleteById(Long id);

    BoardResponse getById(Long id);
}