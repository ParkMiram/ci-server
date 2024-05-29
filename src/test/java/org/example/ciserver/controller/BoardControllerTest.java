package org.example.ciserver.controller;

import org.example.ciserver.domain.entity.Board;
import org.example.ciserver.domain.repository.BoardRepository;
import org.example.ciserver.dto.request.BoardRequest;
import org.example.ciserver.dto.response.BoardResponse;
import org.example.ciserver.service.BoardService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @MockBean
    private BoardService boardService;
    @MockBean
    private BoardRepository boardRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void insertBoard() throws Exception {
        BDDMockito.doNothing().when(boardService).insertBoard(
                new BoardRequest(1L,"test1", "test1")
        );
        mockMvc.perform(MockMvcRequestBuilders.post("/api/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"test1\",\"content\":\"test\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAllBoards() throws Exception {
        // given
        BDDMockito.given(boardService.getAllBoards())
                .willReturn(List.of(
                        new BoardResponse(1L, "test1", "test1"),
                        new BoardResponse(2L, "test2", "test2"),
                        new BoardResponse(3L, "test3", "test3")
                ));
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/boards"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1)
                )    // 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3))
                .andExpect(MockMvcResultMatchers.status().isOk())   // status 무조건 해줘야함
                .andDo(MockMvcResultHandlers.print());        // 하는 거
    }

    @Test
    void deleteById() throws Exception {
        boardRepository.save(new Board(1L,"test1","test1"));
        BDDMockito.doNothing().when(boardService).deleteById(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/boards/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}