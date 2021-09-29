package com.spend.track.statement;

import com.spend.track.common.BaseController;
import com.spend.track.transaction.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/statement")
public class StatementController extends BaseController {

    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @PostMapping
    @RequestMapping(value = "/credit")
    public ResponseEntity<List<Transaction>> parseCreditCardStatement(Long accountId, @RequestBody MultipartFile file) throws IOException {
        return ResponseEntity.ok(statementService.parseCreditCardStatement(accountId, file));
    }
}
