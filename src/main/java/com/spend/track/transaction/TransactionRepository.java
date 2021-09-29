package com.spend.track.transaction;

import com.spend.track.user.TransactionProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> getAllByProfile(TransactionProfile profile);
}
