package minha.hello.boot.spring5boot.board;

import minha.hello.boot.spring5boot.dao.BoardDAOImpl;
import minha.hello.boot.spring5boot.model.Board;
import minha.hello.boot.spring5boot.service.BoardService;
import minha.hello.boot.spring5boot.service.BoardServiceImpl;
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
@Import({BoardServiceImpl.class, BoardDAOImpl.class})
public class BoardServiceUnitTest {

    @Autowired
    private BoardService bsrv;

    @Test
    @DisplayName("BoardService save Test")
    @Transactional
    void saveBoard(){
        Board b = new Board();
        b.setUserid("abc123");
        b.setTitle("test");
        b.setContents("testtest");
        b.setIpaddr("127.0.0.1");
        boolean result = bsrv.saveBoard(b);
        System.out.println(result);
        assertEquals(result,true);
    }

    @Test
    @DisplayName("BoardService read Test")
    void readBoard(){
        int cpg=1;
        List<Board> results = bsrv.readBoard(cpg);
        assertNotNull(results);
        System.out.println(results);
    }

    @Test
    @DisplayName("BoardService readOne Test")
    void readOneBoard(){
        String bno="1000";
        Board result = bsrv.readOneBoard(bno);
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    @DisplayName("BoardService remove Test")
    @Transactional
    void removeBoard(){
        String bno="1";
        boolean result = bsrv.removeBoard(bno);
        System.out.println(result);
        assertEquals(result,true);
    }

    @Test
    @DisplayName("BoardService countPages Test")
    void getCountPages(){
        int result = bsrv.getCountPages();
        assertNotNull(result);
        System.out.println(result);
    }
}
