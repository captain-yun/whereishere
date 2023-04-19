package twogtwoj.whereishere.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.repository.CompanyRepository;
import twogtwoj.whereishere.repository.MemberRepository;



@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public Member save(Member member){
        return memberRepository.save(member);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

        String[] split = input.split(":");
        String username = split[0];
        String role = split[1];

        if (role.equals("member")) {
            Member member = memberRepository.findByLoginId(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not found member :" + username));
            return User.builder()
                    .username(member.getLoginId())
                    .password(passwordEncoder.encode(member.getPassword()))
                    .roles("USER")
                    .build();
        } else {
            Company company = companyRepository.findByLoginId(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not found member :" + username));
            return User.builder()
                    .username(company.getLoginId())
                    .password(passwordEncoder.encode(company.getPassword()))
                    .roles("USER")
                    .build();
        }

    }
}
