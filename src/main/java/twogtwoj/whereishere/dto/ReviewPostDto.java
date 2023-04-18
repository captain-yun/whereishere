package twogtwoj.whereishere.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
public class ReviewPostDto {

    private Long reviewPostId;

    private Member member;

    private Company company;

    private String reviewPostTitle;

    private String reviewPostContent;

    private String reviewPostImg1;

    private String reviewPostImg2;

    private LocalDate reviewPostDate;

    public ReviewPostDto(Company company, String reviewPostTitle, String reviewPostContent, String reviewPostImg1, String reviewPostImg2, LocalDate reviewPostDate) {
        this.company = company;
        this.reviewPostTitle = reviewPostTitle;
        this.reviewPostContent = reviewPostContent;
        this.reviewPostImg1 = reviewPostImg1;
        this.reviewPostImg2 = reviewPostImg2;
        this.reviewPostDate = reviewPostDate;
    }
}
