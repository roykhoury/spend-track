package com.spend.track.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class TransactionProfile extends Account {
    private String financialInstitution;
}
