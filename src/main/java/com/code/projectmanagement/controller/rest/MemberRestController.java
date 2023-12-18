package com.code.projectmanagement.controller.rest;

import com.code.projectmanagement.dto.MemberDTO;
import com.code.projectmanagement.model.Member;
import com.code.projectmanagement.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//Controller that provides a web service to associate people and projects

@RestController
@RequestMapping(value = "/members")
public class MemberRestController {

    @Autowired
    private IMemberService memberService;

    @PostMapping
    public ResponseEntity<Member> insert(@Valid @RequestBody MemberDTO memberDtoObj) {
        Member member = memberService.saveMember(memberDtoObj.getProject(), memberDtoObj.getPerson());

        //project or non-existent person
        if (member == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(member);
    }
}
