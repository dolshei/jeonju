package site.dolshei.jeonju.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import site.dolshei.jeonju.dto.MemberSaveDTO;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {

    @Id                                                         // PK 로 아래 컬럼을 사용하겠다는 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // auto_increment 와 같은 역할
    @Column(name = "member_id")                                 // 컬럼 이름을 member_id 로 지정
    private Long id;

    @Column(length = 50, unique = true)                         // varchar(50) 에 unique 속성을 부여
    private String memberEmail;

    // Entity 에서는 _(언더바) 절대 금물. 차후에 오류 발생 가능성 높음.
    // 카멜케이스로 작성한 컬럼은 member_email 로 자동으로 바뀜.

    @Column(length = 20)
    private String memberPassword;

    @Column()                                                   // 아무 지정을 안해주면 varchar(255) 크기로 생성됨.
    private String memberName;

    // MemberSaveDTO -> MemberEntity 객체로 변환하기 위한 메서드
    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO) {
        // MemberEntity 타입의 객체를 보내기 위해 memberEntity 라는 객체 선언
        MemberEntity memberEntity = new MemberEntity();

        // memberEntity 객체에 memberSaveDTO 객체 안에 담긴 각 요소를 담아줌.
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());

        // 변환이 완료된 memberEntity 객체를 넘겨줌
        return memberEntity;
    }


}
