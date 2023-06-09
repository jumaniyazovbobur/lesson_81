package org.example.Servise.sms;

public interface SmsService {
    public void send(String phone, String code);

    public void validate(String phone, String code);
}
