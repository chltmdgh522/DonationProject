package csh.football.member.domain.mypage;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MyPageMember {
    @NotEmpty
    @Size(max = 16, message = "길이가 15자 이하여야 됩니다.")
    String name;

    @NotEmpty
    String description;
}
