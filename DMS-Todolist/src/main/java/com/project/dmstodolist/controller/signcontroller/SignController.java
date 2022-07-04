package com.project.dmstodolist.controller.signcontroller;

import com.project.dmstodolist.dto.memberdto.MemberSignInDto;
import com.project.dmstodolist.dto.memberdto.MemberSignUpDto;
import com.project.dmstodolist.dto.memberdto.MessageResponse;
import com.project.dmstodolist.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class SignController {

   private final MemberService memberService;

    @PostMapping("/signup")
    public MessageResponse create(@Valid @RequestBody MemberSignUpDto memberSignUpDto) {
        return memberService.joinMember(memberSignUpDto);
    }

    @PostMapping("/signin")
    public MessageResponse login(@Valid @RequestBody MemberSignInDto memberSignInDto) {


    }




    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        memberService.delete(id);
    }



}
