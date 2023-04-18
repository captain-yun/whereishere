package twogtwoj.whereishere.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import twogtwoj.whereishere.domain.Member;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinDto {
    @NotEmpty
    private String loginId;

    private String password;
    @NotEmpty
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .loginId(loginId)
                .password(password)
                .birthDate(birthDate)
                .build();
    }
}
