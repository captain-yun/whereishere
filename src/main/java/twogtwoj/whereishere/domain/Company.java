package twogtwoj.whereishere.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

//    @Range(min = 5, max = 15)
   // @Column(unique = true) // 중복 불가
    private String loginId;

//    @Range(min = 8, max = 20)
    private String password;

    private Long businessId; // 사업자 번호

//    @Range(min = 2, max = 100)
    private String name;

    private String img;

    @Column(length = 10000)
    private String intro;

    private String category;

    private String address;

    @OneToMany(mappedBy = "company")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<Star> stars = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    private List<ReviewPost> reviewPosts = new ArrayList<>();

    public Company(String loginId, String password, Long businessId, String name, String img, String companyIntroduction, String category, String address) {
        this.loginId = loginId;
        this.password = password;
        this.businessId = businessId;
        this.name = name;
        this.img = img;
        this.intro = companyIntroduction;
        this.category = category;
        this.address = address;
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
