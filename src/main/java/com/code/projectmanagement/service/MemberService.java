package com.code.projectmanagement.service;

import com.code.projectmanagement.model.Member;
import com.code.projectmanagement.model.MemberKey;
import com.code.projectmanagement.model.Person;
import com.code.projectmanagement.model.Project;
import com.code.projectmanagement.repository.MemberRepository;
import com.code.projectmanagement.repository.PersonRepository;
import com.code.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService implements IMemberService {

    private ProjectRepository projectRepository;
    private PersonRepository personRepository;
    private MemberRepository memberRepository;

    @Autowired
    public MemberService(ProjectRepository projectRepository, PersonRepository personRepository, MemberRepository memberRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Member saveMember(Long project, Long person) {

        Optional<Project> optionalProject = projectRepository.findById(project);

        //the person must be in the category employee
        Optional<Person> optionalPerson = personRepository.findByIdAndEmployeeTrue(person);

        if (optionalProject.isPresent() && optionalPerson.isPresent()) {
            Project projObj = optionalProject.get();
            Person perObj = optionalPerson.get();

            Member member = new Member(new MemberKey(projObj, perObj));

            return memberRepository.save(member);
        }

        return null;
    }
}
