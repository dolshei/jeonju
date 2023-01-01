package site.dolshei.jeonju.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.dolshei.jeonju.dto.MemberDetailDTO;
import site.dolshei.jeonju.dto.MemberLoginDTO;
import site.dolshei.jeonju.dto.MemberSaveDTO;
import site.dolshei.jeonju.entity.MemberEntity;
import site.dolshei.jeonju.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    // Repository 생성자 주입
    private final MemberRepository memberRepository;

    // 회원 가입 정보 저장
    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {
        // JPARepository 는 무조건 Entity 타입만 받기 때문에 Entity 타입으로 바꿔줘야 함.
        MemberEntity memberEntity = MemberEntity.saveMember(memberSaveDTO);

        Long memberId = memberRepository.save(memberEntity).getId();

        return memberId;
    }

    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        // MemberEntity 타입의 객체 생성 후 JPA 의 findBy 메서드 호출 및 정보 저장
        // MemberLoginDTO 의 memberEmail 을 보내 값을 memberEntity 에 담는것임.
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberLoginDTO.getMemberEmail());

        if (memberEntity != null) {
            // 로그인을 시도한 데이터의 비밀번호와 JPA 에서 받아온 데이터의 비밀번호를 비교
            if (memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<MemberDetailDTO> findAll() {
        // findAll 이라는 메서드 호출 및 Entity 타입의 List 에 호출 결과 저장
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        // Entity 타입의 List 를 DTO 타입의 List 로 변환
        // 반드시 변환해 줄 필요는 없음.
        List<MemberDetailDTO> memberDetailDTOList = MemberDetailDTO.change(memberEntityList);
        return memberDetailDTOList;
    }

    @Override
    public MemberDetailDTO findById(Long memberId) {
        // findById 를 하게 되면 JPA 에서 Optional 타입으로 줌.
        // Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberId);

        // Optional 타입을 Entity 타입으로 바꾸기 위해 .get() 을 붙임
        // MemberEntity memberEntity = memberEntityOptional.get();

        // Entity 타입을 DTO 타입으로 바꾸기 위해 DTO 에 만들어 놓은 메서드를 호출해 변환
        // MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(memberEntity);

        // 변환된 DTO 타입을 리턴함.
        // return memberDetailDTO;

        // 위 코드를 아래 한줄로 요약 가능
        return MemberDetailDTO.toMemberDetailDTO(memberRepository.findById(memberId).get());
    }

    @Override
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
