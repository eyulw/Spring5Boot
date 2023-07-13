package minha.hello.boot.spring5boot.board;

import minha.hello.boot.spring5boot.dao.BoardDAO;
import minha.hello.boot.spring5boot.dao.BoardDAOImpl;
import minha.hello.boot.spring5boot.model.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(BoardDAOImpl.class)
public class BoardDAOUnitTest {

    @Autowired
    private BoardDAO bdao;

    @Test
    @DisplayName("BoardDAO insert Test")
    @Transactional
    void insertBoard(){
        Board b = new Board();
        b.setUserid("abc123");
        b.setTitle("test");
        b.setContents("testtest");
        b.setIpaddr("127.0.0.1");
        int result = bdao.insertBoard(b);
        System.out.println(result);
        assertEquals(result,1);
    }

    @Test
    @DisplayName("BoardDAO select Test")
    void selectBoard(){
        int cpg=1;
        int stnum=(cpg-1)*25;
        List<Board> results = bdao.selectBoard(stnum);
        assertNotNull(results);
        System.out.println(results);
    }

    @Test
    @DisplayName("BoardDAO selectOneBoard Test")
    void selectOneBoard(){
        String bno="1000";
        Board result = bdao.selectOneBoard(bno);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    @DisplayName("BoardDAO delete Test")
    @Transactional
    void deleteBoard(){
        String bno ="1";
        int result = bdao.deleteBoard(bno);
        System.out.println(result);
        assertEquals(result,1);
    }

    @Test
    @DisplayName("BoardDAO countPages Test")
    void countPages(){
        int result = bdao.countPages();
        assertNotNull(result);
        System.out.println(result);
    }

}
