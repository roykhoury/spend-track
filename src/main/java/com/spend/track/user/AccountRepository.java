package com.spend.track.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<TransactionProfile, Long> {}
