package com.spend.track.statement;

import com.spend.track.transaction.Earning;
import com.spend.track.transaction.Spending;
import com.spend.track.transaction.Transaction;
import com.spend.track.transaction.category.TransactionCategory;
import com.spend.track.user.TransactionProfile;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@UtilityClass
public class StatementUtil {
    public List<Transaction> parseCreditCardStatement(String pdfString, String lineSeparator, String transactionSectionSeparator, String transactionPrefix, TransactionProfile profile) {
        List<Transaction> result = new ArrayList<>();
        for (String pdfLine : pdfString.split(lineSeparator)) {
            if (!pdfLine.matches(transactionPrefix))
                continue;

            String[] transactionString = pdfLine.split(transactionSectionSeparator);
            String description = parseDescription(transactionString[5], pdfLine);
            String amountStr = transactionString[transactionString.length - 1];
            int year = LocalDate.now().getYear();
            int month = Integer.parseInt(transactionString[0]);
            int day = Integer.parseInt(transactionString[1]);

            // Earning or Spending
            if (amountStr.contains("-")) {
                amountStr = amountStr.replace("-", "");
                result.add(createEarning(Double.parseDouble(amountStr), profile, description, year, month, day, null));
            } else {
                result.add(createSpending(Double.parseDouble(amountStr), profile, description, year, month, day, null));
            }
        }
        return result;
    }

    private static Earning createEarning(double amount, TransactionProfile profile, String description, int year, int month, int day, TransactionCategory category) {
        Earning earning = new Earning();
        earning.setAmount(amount);
        earning.setProfile(profile);
        earning.setCategory(category);
        earning.setDescription(description);
        earning.setDate(LocalDate.of(year, month, day));
        return earning;
    }

    private static Spending createSpending(double amount, TransactionProfile profile, String description, int year, int month, int day, TransactionCategory category) {
        Spending spending = new Spending();
        spending.setAmount(amount);
        spending.setProfile(profile);
        spending.setCategory(category);
        spending.setDescription(description);
        spending.setDate(LocalDate.of(year, month, day));
        return spending;
    }

    private static String parseDescription(String startWord, String line) {
        int startIndex = line.indexOf(startWord);
        line = line.substring(startIndex);

        Pattern pattern = Pattern.compile("[A-Za-z]*.* ");
        Matcher matcher = pattern.matcher(line);
        return matcher.find() ? matcher.group().replaceAll(",", " ") : "";
    }
}
