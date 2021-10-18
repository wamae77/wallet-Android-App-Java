package com.example.walletapplication.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Transaction {
    @Id(autoincrement = true)
    private long Id;
    private String accountNo;
    private String amount;

    public Transaction(String accountNo, String amount) {
        this.accountNo = accountNo;
        this.amount = amount;
    }

    @Generated(hash = 1709764052)
    public Transaction(long Id, String accountNo, String amount) {
        this.Id = Id;
        this.accountNo = accountNo;
        this.amount = amount;
    }

    @Generated(hash = 750986268)
    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "Id=" + Id +
                ", accountNo='" + accountNo + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    public long getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setId(long Id) {
        this.Id = Id;
    }
}
