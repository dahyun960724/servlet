package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest { //테스트는 public없어도됨

    MemberRepository memberRepository = MemberRepository.getInstance(); //싱글통이여서 new 사용불가(스프링쓰면 싱글톤 안해줘도됨 싱글통보장...)

    @AfterEach
    void afterEach() { //테스트끝날때마다 초기화해주기위한 메소드
        memberRepository.clearStore();;
    }

    @Test
    void save() {
        //given:이런게 주어지면
        Member member = new Member("hello", 20);
        //when:이런게실행되어
        Member savedMember = memberRepository.save(member);
        //then:결과가 이래야한다.
        Member findMember = memberRepository.findById(savedMember.getId());//저장했던 아이디에서 멤버들 찾아서
        assertThat(findMember).isEqualTo(savedMember); //가져온멤버와 저장된멤버가 같나 비교
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2); //사이즈비교
        assertThat(result).contains(member1, member2); //데이터비교(result안에 member1, member2 있는지..)
    }
}
