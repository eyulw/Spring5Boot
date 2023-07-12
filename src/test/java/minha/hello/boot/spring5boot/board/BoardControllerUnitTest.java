package minha.hello.boot.spring5boot.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class BoardControllerUnitTest {

    @Autowired private MockMvc mvc;

    @Test
    @DisplayName("BoardController list Test")
    public void listTest() throws Exception {
        mvc.perform(get("/board/list")
                        .param("cpg","1"))
                        .andExpect(model().attributeExists("allpg"))
                        .andExpect(status().isOk())
                        .andDo(print());
    }

    @Test
    @DisplayName("BoardController view Test")
    public void view() throws Exception {
        mvc.perform(get("/board/view/1"))
                        .andExpect(status().isOk())
                        .andDo(print());
    }
}
