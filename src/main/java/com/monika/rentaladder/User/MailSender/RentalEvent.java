package com.monika.rentaladder.User.MailSender;

public class RentalEvent {

    public enum Type
    {
        REQUEST, CONTACT, SORRY, RETURN_IN_TIME, RETURN_DELAYED
    }

    private Type messageType;

    private String userName;

    private String itemName;

    private String ownerName;

    private Long rentalId;

    public RentalEvent(Type messageType, String userName, String ownerName, String itemName, Long rentalId){
        this.messageType = messageType;
        this.userName = userName;
        this.itemName = itemName;
        this.ownerName = ownerName;
        this.rentalId = rentalId;
    }

    public String getUserName() {
        return userName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public RentalEvent.Type getMassageType() {
        return messageType;
    }

}
