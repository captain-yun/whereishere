package twogtwoj.whereishere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.ReviewPost;
import twogtwoj.whereishere.repository.ReviewPostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewPostService {
    /*
    게시글 등록
    수정
    삭제
    리스트
    게시글 찾기(
     */
    private final ReviewPostRepository reviewPostRepository;

    public ReviewPost save(ReviewPost reviewPost) {
        return reviewPostRepository.save(reviewPost);
    }

    public ReviewPost findByReviewId(Long reviewPostId) {
        return reviewPostRepository.findById(reviewPostId).get();
    }

    ReviewPost findByReviewPostTitle(String reviewPostTitle) {
        return reviewPostRepository.findByReviewPostTitle(reviewPostTitle);
    }
    public List<ReviewPost> findAllReviewPost() {
        return reviewPostRepository.findAll();
    }

    public void update(Long reviewPostId, ReviewPost updateReviewPost) {

        ReviewPost reviewPost = reviewPostRepository.findById(reviewPostId).get();

        reviewPost.setMember(updateReviewPost.getMember());
        reviewPost.setCompany(updateReviewPost.getCompany());
        reviewPost.setReviewPostDate(updateReviewPost.getReviewPostDate());
        reviewPost.setReviewPostImg1(updateReviewPost.getReviewPostImg1());
        reviewPost.setReviewPostImg2(updateReviewPost.getReviewPostImg2());
        reviewPost.setReviewPostTitle(updateReviewPost.getReviewPostTitle());
        reviewPost.setReviewPostContent(updateReviewPost.getReviewPostContent());
    }

    public void delete(Long reviewPostId) {
        ReviewPost reviewPost = reviewPostRepository.findById(reviewPostId).get();
        reviewPostRepository.delete(reviewPost);
    }

}
