package roomescape.apply.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.apply.auth.application.PasswordHasher;
import roomescape.apply.auth.application.exception.IllegalTokenException;
import roomescape.apply.auth.ui.dto.LoginMember;
import roomescape.apply.auth.ui.dto.LoginRequest;
import roomescape.apply.auth.ui.dto.LoginResponse;
import roomescape.apply.member.domain.Member;
import roomescape.apply.member.domain.MemberRoleName;
import roomescape.apply.member.ui.dto.MemberRoleNamesResponse;
import roomescape.apply.member.domain.MemberRepository;
import roomescape.apply.member.ui.dto.MemberResponse;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class MemberFinder {

    private static final String LOGIN_FAIL_MESSAGE = "아이디 혹은 비밀번호가 잘못되었습니다.";

    private final PasswordHasher passwordHasher;
    private final MemberRepository memberRepository;
    private final MemberRoleFinder memberRoleFinder;

    public MemberFinder(PasswordHasher passwordHasher, MemberRepository memberRepository, MemberRoleFinder memberRoleFinder) {
        this.passwordHasher = passwordHasher;
        this.memberRepository = memberRepository;
        this.memberRoleFinder = memberRoleFinder;
    }

    public List<MemberResponse> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(member -> MemberResponse.from(member.getId(), member.getName()))
                .toList();
    }

    public LoginResponse findByLoginRequest(LoginRequest request) {
        String hashPassword = passwordHasher.hashPassword(request.password());

        Member member = memberRepository.findByEmailAndPassword(request.email(), hashPassword)
                .orElseThrow(() -> new IllegalArgumentException(LOGIN_FAIL_MESSAGE));

        Set<MemberRoleName> rolesInMember = memberRoleFinder.findRolesInMember(member.getId());

        return LoginResponse.from(member, MemberRoleNamesResponse.of(rolesInMember));
    }

    public boolean isDuplicateEmail(String email) {
        return memberRepository.findIdByEmail(email).isPresent();
    }

    public LoginMember getLoginMemberByEmail(String email) {
        Member member = memberRepository.findOneByEmail(email)
                .orElseThrow(() -> new IllegalTokenException("이메일이 존재하지 않습니다. 다시 로그인해주세요."));
        Set<MemberRoleName> rolesInMember = memberRoleFinder.findRolesInMember(member.getId());
        return LoginMember.from(member, MemberRoleNamesResponse.of(rolesInMember));
    }

    public Member findOneNameById(long memberId) {
        return memberRepository.findOneById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("맴버가 존재하지 않습니다"));
    }
}
