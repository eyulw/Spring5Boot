package minha.hello.boot.spring5boot.service;

import minha.hello.boot.spring5boot.dao.MemberDAO;
import minha.hello.boot.spring5boot.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("msrv")
public class MemberServiceImpl implements MemberService{
    @Autowired private MemberDAO mdao;
    @Override
    public boolean saveMember(Member m) {
        boolean isSaved = false;
        if(mdao.insertMember(m)>0)
            isSaved=true;
        return isSaved;
    }

    @Override
    public List<Member> readMember() {
        return mdao.selectMember();
    }
}
