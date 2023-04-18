package twogtwoj.whereishere.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

//    @Range(min = 5, max = 15)
   // @Column(unique = true) // 중복 불가
    private String companyLoginId;

//    @Range(min = 8, max = 20)
    private String companyLoginPw;

    private Long companyBusinessId; // 사업자 번호

//    @Range(min = 2, max = 100)
    private String companyName;

    private String companyImg;

    @Column(length = 10000)
    private String companyIntroduction;

    private String companyCategory;

    private String companyAddress;

    @OneToMany(mappedBy = "company")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Star> stars = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<ReviewPost> reviewPosts = new ArrayList<>();

    public Company(String companyLoginId, String companyLoginPw, Long companyBusinessId, String companyName, String companyImg, String companyIntroduction, String companyCategory, String companyAddress) {
        this.companyLoginId = companyLoginId;
        this.companyLoginPw = companyLoginPw;
        this.companyBusinessId = companyBusinessId;
        this.companyName = companyName;
        this.companyImg = companyImg;
        this.companyIntroduction = companyIntroduction;
        this.companyCategory = companyCategory;
        this.companyAddress = companyAddress;
    }

    public Company() {}

    // 연관관계 편의 메서드
    public void addReviewPost(ReviewPost reviewPost) {
        this.reviewPosts.add(reviewPost);
        if (reviewPost.getCompany() != this) {
            reviewPost.setCompany(this);
        }
    }
}
