package com.project.dmstodolist.service;

import com.project.dmstodolist.domain.member.Member;
import com.project.dmstodolist.domain.member.MemberRepository;
import com.project.dmstodolist.dto.memberdto.MemberSignUpDto;
import com.project.dmstodolist.dto.memberdto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MessageResponse joinMember(MemberSignUpDto memberSignUpDto) {
        Member member = Member.builder()
                .account_id(memberSignUpDto.getAccount_id())
                .password(passwordEncoder.encode(memberSignUpDto.getPassword()))
                .name(memberSignUpDto.getName())
                .age(memberSignUpDto.getAge())
                .build();
        memberRepository.save(member);
        return MessageResponse.builder()
                .message(" : 회원가입 성공")
                .name("\"" + member.getName() + "\"")
                .build();
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
/*
    public MessageResponse readMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);


    }

 */


}
