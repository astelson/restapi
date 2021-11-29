package com.mamatzhanov.restapi.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserEntity> userEntities;

}
