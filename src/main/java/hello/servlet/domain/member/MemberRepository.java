package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrnetHashmap 이나 AtomicLong 사용 고려
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 싱글톤 인스턴스
    private static final MemberRepository instance = new MemberRepository();

    // 외부 조회용
    public static MemberRepository getInstance(){
        return instance;
    }
    // 싱글톤으로 생성시 private으로 생성자를 막는다.
    private MemberRepository() {
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
