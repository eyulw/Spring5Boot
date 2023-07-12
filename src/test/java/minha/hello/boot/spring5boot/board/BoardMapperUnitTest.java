package minha.hello.boot.spring5boot.board;

import minha.hello.boot.spring5boot.model.Board;
import minha.hello.boot.spring5boot.mybatis.BoardMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardMapperUnitTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    @DisplayName("BoardMapper insert Test")
    @Transactional
    void insertBoard(){
        Board b = new Board();
        b.setUserid("abc123");
        b.setTitle("test");
        b.setContents("testtest");
        b.setIpaddr("test");
        int result = boardMapper.insertBoard(b);
        System.out.println(result);
        assertEquals(result,1);
    }

    @Test
    @DisplayName("BoardMapper select Test")
    void selectBoard(){
        int stnum=1;
        List<Board> results = boardMapper.selectBoard(stnum);
        assertNotNull(results);
        System.out.println(results);
    }

    @Test
    @DisplayName("BoardMapper selectOneBoard Test")
    void selectOneBoard(){
        String bno="1000";
        Board result = boardMapper.selectOneBoard(bno);
        assertNotNull(result);
        System.out.println(result);
    }
}
