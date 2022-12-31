package site.dolshei.jeonju.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.dolshei.jeonju.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
