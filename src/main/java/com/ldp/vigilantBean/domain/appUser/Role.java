package com.ldp.vigilantBean.domain.appUser;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long roleId;

    @Column(name = "name")
    private String name;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
