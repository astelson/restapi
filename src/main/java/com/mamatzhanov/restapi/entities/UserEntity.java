package com.mamatzhanov.restapi.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<RoleEntity> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AccountEntity> accounts;
}
