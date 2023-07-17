package minha.hello.boot.spring5boot.pds;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PdsControllerUnitTest {

    @Autowired private MockMvc mvc;

    @Test
    @DisplayName("PdsController writeok Test")
    public void writeok() throws Exception {
        String fpath="C:\\Java\\bootupload\\test.jpg";
        FileInputStream fis=new FileInputStream(fpath);     //파일 읽어서 내용을 fis에 넣음
        //MockMultipartFile(폼이름, 파일명, MIME,파일객체)
        MockMultipartFile attach=new MockMultipartFile("attach","test.jpg","image/png",fis);
        mvc.perform(multipart("/pds/write")
                        .file(attach)
                        .param("title","aaa")
                        .param("userid","abc123")
                        .param("contents","bbb")
                        .param("ipaddr","127.0.0.1"))
                        .andExpect(status().is3xxRedirection())
                        .andDo(print());
    }


    @Test
    @DisplayName("PdsController list Test")
    public void list() throws Exception {
        mvc.perform(get("/pds/list/1")
                        .param("cpg","1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("PdsController view Test")
    public void view() throws Exception {
        mvc.perform(get("/pds/view/6"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
