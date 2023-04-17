package twogtwoj.whereishere.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
