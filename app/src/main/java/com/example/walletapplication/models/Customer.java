package com.example.walletapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Customer {
    @Id(autoincrement = true)
    private Long Id;
    private String customerId;
    private String email;
    private String firstName;
    private String lastName;
    private String pin;
    private String accountNo;

    public Customer(String customerId, String email, String firstname, String lastname, String pin,String accountNo) {
        this.customerId = customerId;
        this.email = email;
        this.firstName = firstname;
        this.lastName = lastname;
        this.pin = pin;
        this.accountNo =accountNo;
    }

    public Customer(String customerId,String pin) {
        this.customerId = customerId;
        this.pin = pin;
    }

    @Generated(hash = 168766276)
    public Customer(Long Id, String customerId, String email, String firstName, String lastName, String pin,
            String accountNo) {
        this.Id = Id;
        this.customerId = customerId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.accountNo = accountNo;
    }

    @Generated(hash = 60841032)
    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id=" + Id +
                ", customerId='" + customerId + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pin='" + pin + '\'' +
                ", accountNo='" + accountNo + '\'' +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
