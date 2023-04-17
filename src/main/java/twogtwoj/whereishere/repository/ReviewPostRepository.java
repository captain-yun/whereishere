package twogtwoj.whereishere.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twogtwoj.whereishere.domain.ReviewPost;

@Repository
public interface ReviewPostRepository extends JpaRepository<ReviewPost, Long> {

    ReviewPost findByReviewPostTitle(String reviewPostTitle);
}
