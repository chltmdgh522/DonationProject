package csh.football.admin.domain.member;

import csh.football.member.domain.member.MemberType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity(name = "Member")
@Data
public class JpaMember {

    @Id
    private String id;

    private String email;

    private String loginId; //로그인 ID

    private String name; //사용자 이름

    private int point;

    private int totalGivePoint;

    private String date;


    public JpaMember() {

    }
}
