package org.example.ciserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.ciserver.dto.request.BoardRequest;
import org.example.ciserver.dto.response.BoardResponse;
import org.example.ciserver.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public void insertBoard(
        @RequestBody BoardRequest request
    ) {
        boardService.insertBoard(request);
    }

    @GetMapping
    public List<BoardResponse> getAllBoards() {
        return boardService.getAllBoards();
    }

    @DeleteMapping("/{id}")
    public void deleteById(
        @PathVariable("id") Long id
    ) {
        boardService.deleteById(id);
    }
}