package com.code.projectmanagement.service;

import com.code.projectmanagement.model.Member;

public interface IMemberService {
    Member saveMember(Long project, Long person);
}
