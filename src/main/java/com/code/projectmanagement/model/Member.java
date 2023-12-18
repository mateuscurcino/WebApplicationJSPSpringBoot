package com.code.projectmanagement.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "member")
public class Member implements Serializable {

    //composite primary key
    @EmbeddedId
    private MemberKey key;

    public Member() {
        super();
    }

    public Member(MemberKey key) {
        this.key = key;
    }

    public MemberKey getKey() {
        return key;
    }

    public void setKey(MemberKey key) {
        this.key = key;
    }
}
