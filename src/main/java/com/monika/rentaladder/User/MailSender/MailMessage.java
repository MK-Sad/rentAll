package com.monika.rentaladder.User.MailSender;

public class MailMessage {

    public enum Type
    {
        REQUEST, CONTACT
    }

    private Type messageType;

    private String sendToName;

    private String itemName;

    private String itemOwnerName;

    private Long rentalId;

    public MailMessage(Type messageType, String sendToName, String itemName, String itemOwnerName, Long rentalId){
        this.messageType = messageType;
        this.sendToName = sendToName;
        this.itemName = itemName;
        this.itemOwnerName = itemOwnerName;
        this.rentalId = rentalId;
    }

    public String getSendToName() {
        return sendToName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemOwnerName() {
        return itemOwnerName;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public MailMessage.Type getMassageType() {
        return messageType;
    }

}
