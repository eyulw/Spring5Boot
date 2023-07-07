package minha.hello.boot.spring5boot.dao;

import minha.hello.boot.spring5boot.model.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO{

    @Autowired
    private SqlSession sqlSession;
    @Override
    public int insertMember(Member m) {
        //insert(insert관련맵핑,매개변수)
        //네임스페이스.(id)
        return sqlSession.insert("member.insertMember",m);
    }

    @Override
    public List<Member> selectMember() {
        return null;
    }
}
