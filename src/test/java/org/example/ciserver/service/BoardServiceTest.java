package org.example.ciserver.service;

import org.example.ciserver.domain.entity.Board;
import org.example.ciserver.domain.repository.BoardRepository;
import org.example.ciserver.dto.request.BoardRequest;
import org.example.ciserver.dto.response.BoardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @InjectMocks
    private BoardServiceImpl boardService;
    @Mock
    private BoardRepository boardRepository;

    @Test
    void insertBoard() {
        BoardRequest userRequest = new BoardRequest(1L,"test1", "test1");
        Mockito.when(boardRepository.save(any(Board.class))).then(AdditionalAnswers.returnsFirstArg());

        boardService.insertBoard(userRequest);

        assertEquals("test1", userRequest.name());
        assertEquals("test1", userRequest.text());

        Mockito.verify(boardRepository).save(any(Board.class));
    }

    @Test
    void getAllBoards() {
        BDDMockito.given(boardRepository.findAll()).willReturn(
                List.of(new Board(1L,"test1", "test1"),new Board(2L,"test2", "test2")));

        List<BoardResponse> all = boardService.getAllBoards();

        assertEquals(2, all.size());
        assertEquals("test1", all.get(0).name());
        Mockito.verify(boardRepository).findAll();
    }

    @Test
    void deleteById() {
        BDDMockito.doNothing().when(boardRepository).deleteById(1L);
        BDDMockito.given(boardRepository.findById(1L)).willReturn(
                Optional.empty()
        );

        boardService.deleteById(1L);

        assertEquals(Optional.empty(), boardRepository.findById(1L));
    }

    @Test
    void getById() {
        BDDMockito.given(boardRepository.findById(1L)).willReturn(
                Optional.of(new Board(1L, "test1", "test1"))
        );

        BoardResponse user = boardService.getById(1L);

        assertEquals("test1", user.name());
        Mockito.verify(boardRepository).findById(1L);
    }
}