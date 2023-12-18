package com.code.projectmanagement.repository;

import com.code.projectmanagement.model.Member;
import com.code.projectmanagement.model.MemberKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, MemberKey> {

}
