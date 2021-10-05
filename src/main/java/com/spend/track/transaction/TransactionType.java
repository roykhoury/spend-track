package com.spend.track.transaction;

public enum TransactionType {
    SPENDING(0),
    EARNING(1);

    int val;
    TransactionType(int val) {
        this.val = val;
    }
}
