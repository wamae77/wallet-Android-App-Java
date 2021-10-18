package com.example.walletapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Account {
    @Id(autoincrement = true)
    private long Id;
    private String accountNo;
    private String customerId;
    private Double balance;

    public Account(String accountNo, String customerId, Double balance) {
        this.accountNo = accountNo;
        this.customerId = customerId;
        this.balance = balance;
    }

    @Generated(hash = 33613580)
    public Account(long Id, String accountNo, String customerId, Double balance) {
        this.Id = Id;
        this.accountNo = accountNo;
        this.customerId = customerId;
        this.balance = balance;
    }

    @Generated(hash = 882125521)
    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + Id +
                ", accountNo='" + accountNo + '\'' +
                ", customerId='" + customerId + '\'' +
                ", balance=" + balance +
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setId(long Id) {
        this.Id = Id;
    }
}
