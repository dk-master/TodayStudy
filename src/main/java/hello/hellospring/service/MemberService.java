package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService
{
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /* 회원가입 */
    public Long join(Member member){
        // 같은 이름 중복이 있으면 안된다.
        validateCheck(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateCheck(Member member) {
        memberRepository.findByName(member.getName())
        // Optional이라는 것으로 한번 감쌌기 때문에 ifPresent라는 함수를 사용해서 예외처리 해줄 수 있다.
        .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        } );
    }
    /* 전체회원 조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne (Long memberId) {
        return memberRepository.findById(memberId);
    }
}
