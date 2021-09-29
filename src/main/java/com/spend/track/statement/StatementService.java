package com.spend.track.statement;

import com.spend.track.transaction.Transaction;
import com.spend.track.transaction.TransactionRepository;
import com.spend.track.user.AccountRepository;
import com.spend.track.user.TransactionProfile;
import lombok.extern.log4j.Log4j2;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Log4j2
@Service
public class StatementService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Value("${spend-track.bnc-transaction-prefix}")
    private String prefix;

    public StatementService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> parseStatement(Long accountId, MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);

        String lineSeparator = pdfTextStripper.getLineSeparator();
        String wordSeparator = pdfTextStripper.getWordSeparator();
        TransactionProfile profile = accountRepository.findById(accountId).orElseThrow();
        List<Transaction> transactions = StatementUtil.parseTransactions(text, lineSeparator, wordSeparator, prefix, profile);
        return transactionRepository.saveAll(transactions);
    }
}
