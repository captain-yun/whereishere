package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class ReviewPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPostId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="COMPANY_ID")
    private Company company;

    private String reviewPostTitle;

    private String reviewPostContent;

    private String reviewPostImg1;

    private String reviewPostImg2;

    private LocalDate reviewPostDate;

    public ReviewPost(Company company, String reviewPostTitle, String reviewPostContent, String reviewPostImg1, String reviewPostImg2, LocalDate reviewPostDate) {
        this.company = company;
        this.reviewPostTitle = reviewPostTitle;
        this.reviewPostContent = reviewPostContent;
        this.reviewPostImg1 = reviewPostImg1;
        this.reviewPostImg2 = reviewPostImg2;
        this.reviewPostDate = reviewPostDate;
    }

    // 연관관계 편의 메서드
    public void setCompany(Company company) {
        if (this.company != null) {
            this.company.getReviewPosts().remove(this);
        }
        this.company = company;
        company.getReviewPosts().add(this);
    }
}
