package site.dolshei.jeonju.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.dolshei.jeonju.entity.MemberEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor              // 기본 생성자
@AllArgsConstructor             // 모든 필드를 매개변수로 하는 생성자
// 생성자는 쓰이는 일이 없더라도 만들어 놓아도 문제 없음.
public class MemberDetailDTO {

    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    // MemberEntity -> MemberDetailDTO
    // return type : MemberDetailDTO
    // parameter type : MemberEntity
    public static MemberDetailDTO toMemberDetailDTO(MemberEntity memberEntity) {
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();

        memberDetailDTO.setMemberId(memberEntity.getId());
        memberDetailDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDetailDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDetailDTO.setMemberName(memberEntity.getMemberName());

        return memberDetailDTO;
    }

    // Entity 타입의 List 를 DTO 타입의 List 로 변환하는 메서드
    // 위에 있는 Entity 를 DTO 로 변환해 주는 메서드를 사용해 더 간단하게 구현
    public static List<MemberDetailDTO> change(List<MemberEntity> memberEntityList) {
        List<MemberDetailDTO> memberDetailDTOList = new ArrayList<>();

        for (MemberEntity m : memberEntityList) {
            memberDetailDTOList.add(toMemberDetailDTO(m));
        }
        return memberDetailDTOList;
    }
}
