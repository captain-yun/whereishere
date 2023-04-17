package twogtwoj.whereishere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Star;
import twogtwoj.whereishere.repository.CompanyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;


    public Company save(Company company){
        return companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company CompanyByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }

    public Company findById(Long companyId) {
        return companyRepository.findById(companyId).get();
    }

    public Double findAverageStarRating(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        List<Star> stars = company.getStars();
        double sum = stars.stream().mapToDouble(star -> star.getStarPoint()).sum();
        return sum / stars.size();
    }
}
