package com.mamatzhanov.restapi.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "paymnets")
public class PaymnetEntity extends BaseEntity {

    private Double amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private AccountEntity account;
}
