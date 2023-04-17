package twogtwoj.whereishere.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.Star;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

}
