package minha.hello.boot.spring5boot.board;

import minha.hello.boot.spring5boot.model.Board;
import minha.hello.boot.spring5boot.mybatis.BoardMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        b.setIpaddr("127.0.0.1");
        int result = boardMapper.insertBoard(b);
        System.out.println(result);
        assertEquals(result,1);
    }

    @Test
    @DisplayName("BoardMapper select Test")
    void selectBoard(){
        int cpg=1;
        int stnum=(cpg-1)*25;
        List<Board> results = boardMapper.selectBoard(stnum);
        assertNotNull(results);
        System.out.println(results);
    }

    @Test
    @DisplayName("BoardMapper selectOne Test")
    void selectOneBoard(){
        String bno="1000";
        Board result = boardMapper.selectOneBoard(bno);
        assertNotNull(result);
        System.out.println(result);
    }
    @Test
    @DisplayName("BoardMapper delete Test")
    @Transactional
    void deleteBoard(){
        String bno ="1";
        int result = boardMapper.deleteBoard(bno);
        System.out.println(result);
        assertEquals(result,1);
    }

    @Test
    @DisplayName("BoardMapper updateViews Test")
    @Transactional
    void updateBoardViews(){
        String bno="1297";
        int result = boardMapper.updateViewBoard(bno);
        System.out.println(result);
        assertEquals(result,1);
    }

    @Test
    @DisplayName("BoardMapper countPages Test")
    void countPages(){
        int result = boardMapper.countPages();
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    @DisplayName("BoardMapper findBoard Test")
    void findBoard(){
        Map<String, Object> params=new HashMap<>();
        params.put("findtype","titcont");
        params.put("findkey","개발");
        params.put("stnum",0);
        List<Board> results = boardMapper.selectFindBoard(params);
        assertNotNull(results);
        System.out.println(results);
    }
}
