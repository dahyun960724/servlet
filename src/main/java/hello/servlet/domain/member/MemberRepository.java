package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
동시성 문제가 고려되어 있지않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용고려
 */
public class MemberRepository {

    //싱글톤으로 한개만 보장되어 static필요없으나 그냥해놈...
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //회원가입될때마다 id값 증가

    //(싱글톤으로만듬)static으로 하여 MemberRepository가 다른데서 new 객체로 생성되어도 얘네는 딱한번만 생성되있는것
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){ //private 생성자를 받아오는역할(무조건 getInstance()얘로 조회해야함)
        return instance;
    }

    private MemberRepository() { //싱글톤 만들땐 private으로 생성자를 막아야한다.

    }

    public Member save(Member member) { //저장
        member.setId(++sequence); //id 하나당 시퀀스 1씩증가
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) { //찾음
        return store.get(id); //id로 찾음
    }

    public List<Member> findAll() { //전체조회
        return new ArrayList<>(store.values());
        //store에있는 값들을 새로운 ArrayList에 넣어줌
        //=>새로운ArrayList에 넣는이유:new ArrayList에 값을 넣거나 조작해도 store에 있는 값리스트를 건들고싶지않아서..
    }

    public void clearStore() {
        store.clear();
    }

}
