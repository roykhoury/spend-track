package com.spend.track.user;

import com.spend.track.common.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping(value = "/account")
public class AccountController extends BaseController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping()
    public ResponseEntity<TransactionProfile> getById(Long accountId) {
        return ResponseEntity.ok(this.accountRepository.findById(accountId).orElseThrow());
    }

    @PostMapping()
    public ResponseEntity<TransactionProfile> createAccount(@RequestBody TransactionProfile account) {
        accountRepository.save(account);
        return ResponseEntity.ok(account);
    }
}
