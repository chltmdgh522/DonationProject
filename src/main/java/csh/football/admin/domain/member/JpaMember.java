package csh.football.admin.domain.member;

import csh.football.member.domain.member.MemberType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity(name="Member")
@Data
public class JpaMember {

    @Id
    private String id;

    @NotEmpty
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotEmpty
    @Size(max = 16, message = "15자 이하여야 됩니다.")
    private String loginId; //로그인 ID
    @NotEmpty
    private String name; //사용자 이름

    private int point;

    private int totalGivePoint;



    public JpaMember(){

    }
}
