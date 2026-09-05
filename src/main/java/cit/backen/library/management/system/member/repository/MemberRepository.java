package cit.backen.library.management.system.member.repository;

import cit.backen.library.management.system.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

}
