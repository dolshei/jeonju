package site.dolshei.jeonju.service;

import site.dolshei.jeonju.dto.MemberDetailDTO;
import site.dolshei.jeonju.dto.MemberLoginDTO;
import site.dolshei.jeonju.dto.MemberSaveDTO;

import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO);

    boolean login(MemberLoginDTO memberLoginDTO);

    List<MemberDetailDTO> findAll();

    MemberDetailDTO findById(Long memberId);

    void deleteById(Long memberId);
}
