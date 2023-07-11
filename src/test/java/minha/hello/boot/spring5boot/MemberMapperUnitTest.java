package minha.hello.boot.spring5boot;

import minha.hello.boot.spring5boot.model.Member;
import minha.hello.boot.spring5boot.mybatis.MemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberMapperUnitTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    @DisplayName("MemberMapper insert Test")    /*테스트명 지정가능*/
    void insertMember(){
        Member m = new Member(null,"","","","","","","","","",null);
        int result = memberMapper.insertMember(m);
        System.out.println(result);
        assertEquals(result,1);
    }

    @Test
    @DisplayName("MemberMapper select Test")
    void selectMember(){
        List<Member> results = memberMapper.selectMember();
        assertNotNull(results);
        System.out.println(results);
    }
    @Test
    @DisplayName("MemberMapper selectOneMember Test")
    void selectOneMember(){
        Member m=new Member();
        m.setUserid("abc123");
        m.setPasswd("987xyz");
        Member result = memberMapper.selectOneMember(m);
        assertNotNull(result);
        System.out.println(result);
    }

}
