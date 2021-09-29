package com.spend.track.transaction;

import com.spend.track.common.BaseController;
import com.spend.track.user.AccountRepository;
import com.spend.track.user.TransactionProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController extends BaseController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> get(Long accountId) {
        TransactionProfile profile = accountRepository.findById(accountId).orElseThrow();
        return ResponseEntity.ok(transactionRepository.getAllByProfile(profile).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<Transaction> post(@RequestBody Transaction transaction, Long accountId) {
        TransactionProfile profile = accountRepository.findById(accountId).orElseThrow();
        transaction.setProfile(profile);
        return ResponseEntity.ok(transactionRepository.save(transaction));
    }
}
