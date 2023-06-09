package twogtwoj.whereishere.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import twogtwoj.whereishere.domain.Comment;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.domain.Star;
import twogtwoj.whereishere.service.CommentService;
import twogtwoj.whereishere.service.CompanyService;
import twogtwoj.whereishere.service.MemberService;
import twogtwoj.whereishere.service.StarService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    private final CompanyService companyService;

    private final StarService starService;

    private final CommentService commentService;

    @GetMapping("/home")
    public String enterHome(Model model) {
        List<Company> companyList = companyService.findAll();
        companyList.clear();
        model.addAttribute("companyList", companyList);
        return "/home/home";
    }

    @PostMapping("/home")
    public String searchPlace(@RequestParam String search, Model model) {

        if(search.trim().equals("")){
            return "error_";
        }

        List<Company> companies = companyService.findAll();

        List<Company> companyList = companies.stream().filter(n -> n.getCompanyIntroduction().contains(search)).collect(Collectors.toUnmodifiableList());

        model.addAttribute("companyList", companyList);

        return "/home/home";
    }

    // 검색한 업체 정보 바로가기
    @GetMapping("/companies/{companyId}")
    public String informationCompany(@PathVariable Long companyId, Model model) {

        Company company = companyService.findById(companyId);

        // 업체에 등록된 별점객체들 찾기 (별점이 등록된 것이 없으면 0 반환)
        Double averageStarRating = companyService.findAverageStarRating(companyId);

        //Double starsPoint = starService.findStarsPointByCompany(company);

        if (averageStarRating.isNaN()) {
            averageStarRating = 0.0;
        }
        String starsPointToString = String.format("%.1f", averageStarRating);

        // 업체에 등록된 코멘트 리스트 찾기
        List<Comment> commentList = commentService.findCommentListByCompany(company);
        
        model.addAttribute("company",company);
        model.addAttribute("starsPoint", starsPointToString);
        model.addAttribute("commentList", commentList);

        return "company/company-info";
    }


//    ***

    // 별점 매기기 (파라미터가 false로 왔을때, 별점등록이 실패했다, true일때 성공했다라는 알람이 울릴 수있게 만들기)
    @PostMapping("/companies/{companyId}/star")
    public String saveStarPoint(@PathVariable Long companyId, @RequestParam Long starPoint, RedirectAttributes redirectAttributes) {
        // 맴버는 변경해야합니다! (로그인 기능 구현후, 현재는 임시 데이터 적용)
        Long memberId = 1L;
        Member member = memberService.findById(memberId);

        // 해당 컴퍼니
        Company company = companyService.findById(companyId);

        // 현재, 이맴버가 이 컴페니에 별점을 남겼더라면, 적용이 되지않고 리턴할 수 있게 만들기
        List<Star> allStars = starService.findAll();

        for (int i = 0; i < allStars.size(); i++) {
            if(allStars.get(i).getMember().equals(member) && allStars.get(i).getCompany().equals(company)) {
                // 저장을 실패하고 되돌아가기
                redirectAttributes.addAttribute("companyId",companyId);
                redirectAttributes.addAttribute("status",false);
                return "redirect:/home/{companyId}";

            }
        }

        //starPoint 와 함께 새로운 stars 객체 저장
        starService.save(new Star(member,company,starPoint.doubleValue()));

        // 저장을 성공적으로 하고 되돌아가기
        redirectAttributes.addAttribute("companyId",companyId);
        redirectAttributes.addAttribute("status",true);
        return "redirect:/companies/{companyId}";
    }


    // 코멘트 입력시 추가하고, 다시 검색한 업체 정보
    @PostMapping("/companies/{companyId}/comment")
    public String saveComment(@PathVariable Long companyId, @RequestParam String commentContent, RedirectAttributes redirectAttributes) {

        // 맴버는 변경해야합니다! (로그인 기능 구현후, 현재는 임시 데이터 적용)
        Long memberId = 1L;
        Member member = memberService.findById(memberId);

        // 회사 객체를 찾아, commentContent 와 함께, 새로운 코멘트객체 저장
        Company company = companyService.findById(companyId);
        commentService.save(new Comment(member,company,commentContent, LocalDateTime.now()));

        // 되돌아가기
        redirectAttributes.addAttribute("companyId",companyId);
        return "redirect:/companies/{companyId}";
    }

    // 업체 리뷰
    @GetMapping("/companies/{companyId}/reviews")
    public String showReviewsOfCompany(@PathVariable Long companyId, RedirectAttributes redirectAttributes) {

        companyService.getReviewPosts(companyId);

        // 되돌아가기
        redirectAttributes.addAttribute("companyId",companyId);
        return "review/reviews";
    }

}
