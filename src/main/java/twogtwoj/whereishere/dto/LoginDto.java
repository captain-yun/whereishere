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
public class LoginDto {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;

}
