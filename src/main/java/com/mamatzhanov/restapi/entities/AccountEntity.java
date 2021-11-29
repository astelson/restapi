package com.mamatzhanov.restapi.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "account")
public class AccountEntity extends BaseEntity {

    private Double balance;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<PaymnetEntity> paymnents;
}
