package org.example.ciserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.ciserver.domain.entity.Board;
import org.example.ciserver.domain.repository.BoardRepository;
import org.example.ciserver.dto.request.BoardRequest;
import org.example.ciserver.dto.response.BoardResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// test
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public void insertBoard(BoardRequest request) {
        boardRepository.save(request.toEntity());
    }

    @Override
    public List<BoardResponse> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(BoardResponse::from).toList();
    }

    @Override
    public void deleteById(Long id) {
//        Optional<Board> byId = boardRepository.findById(id);
//        byId.orElseThrow(IllegalArgumentException::new);
        boardRepository.deleteById(id);
    }

    @Override
    public BoardResponse getById(Long id) {
        Optional<Board> byId = boardRepository.findById(id);
        return BoardResponse.from(byId.orElseThrow(IllegalArgumentException::new));
    }
}