package com.bank.service;

import com.bank.model.Bank;

public class BankService {
private Bank bank;
private FileService fileService;

    public BankService(FileService fileService) {
        this.fileService = fileService;
    }

    public void setupBank(String name,
                          String address,
                          String phone,
                          String webUrl) {
        this.bank = new Bank(name,
                address, phone, webUrl);
        fileService.saveBank(bank);
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void updateName(String name) {
        bank.setName(name);
        fileService.saveBank(bank);
    }

    public void updateAddress(String address) {
        bank.setAddress(address);
        fileService.saveBank(bank);
    }

    public void updatePhone(String phone) {
        bank.setPhone(phone);
        fileService.saveBank(bank);
    }

    public void updateWebUrl(String webUrl) {
        bank.setWebUrl(webUrl);
        fileService.saveBank(bank);
    }
    public void saveBank() {
        fileService.saveBank(bank);
    }

    public void loadBank() {
        this.bank = fileService.readBank();
    }

    public void showBank() {
        System.out.println(bank);
    }


}

