package com.spend.track.transaction;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue(value = "1")
@EqualsAndHashCode(callSuper = true)
public class Earning extends Transaction {
}
