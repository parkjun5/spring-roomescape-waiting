package roomescape.apply.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.AUTO;


@Entity
public class Member {
    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;

    protected Member() {

    }

    public Member(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static Member of(String name, String email, String password) {
        Member member = new Member();
        member.name = name;
        member.email = email;
        member.password = password;
        return member;
    }

    public void changeId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
