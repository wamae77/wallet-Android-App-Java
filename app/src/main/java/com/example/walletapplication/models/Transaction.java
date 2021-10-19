package com.example.walletapplication.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Transaction {
    @Id(autoincrement = true)
    private Long Id;
    private String accountNo;
    private String amount;
    private String accountTo;

    public Transaction(String accountNo, String amount,String accountTo) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.accountTo=accountTo;
    }

    @Generated(hash = 189628579)
    public Transaction(Long Id, String accountNo, String amount, String accountTo) {
        this.Id = Id;
        this.accountNo = accountNo;
        this.amount = amount;
        this.accountTo = accountTo;
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
                ", accountTo='" + accountTo + '\'' +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
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

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }
}
