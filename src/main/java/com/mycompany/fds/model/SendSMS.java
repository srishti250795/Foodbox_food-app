package com.mycompany.fds.model;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SendSMS {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "xxxxxxxxxxx";
    public static final String AUTH_TOKEN = "xxxxxxxxxxx";

    public static void main(String[] args) {
        sendMessage();

    }

    public static void sendMessage() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+xxxxxxxx"),
                new com.twilio.type.PhoneNumber("whatsapp:+xxxxxxxxx"),
                "Bonjour, votre commande est arrivée !")
                .create();
        System.out.println(message.getSid());
        System.out.println("Vous avez reçu un message !");
    }
}
