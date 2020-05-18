package com.monika.rentaladder.User.MailSender;

import com.monika.rentaladder.User.UserDTOs.UserEntity;
import com.monika.rentaladder.User.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RentalEventHandler {

    private MailService mailService;

    private UserFacade userFacade;

    @Autowired
    public RentalEventHandler(MailService mailService, UserFacade userFacade) {
        this.mailService = mailService;
        this.userFacade = userFacade;
    }

    @EventListener
    @Async("sendingEmailTaskExecutor")
    public void eventListener(RentalEvent rentalEvent) {
        UserEntity user = userFacade.getUserByName(rentalEvent.getUserName());
        UserEntity owner = userFacade.getUserByName(rentalEvent.getOwnerName());
        RentalEvent.Type type = rentalEvent.getMassageType();
        switch (type) {
            case REQUEST:
                mailService.sendRequestMail(user, owner,
                        rentalEvent.getItemName(), rentalEvent.getRentalId());
                break;
            case CONTACT:
                owner.setPoints(owner.getPoints() + 2);
                userFacade.updateUser(owner);
                mailService.sendOwnerDataMail(user, owner,
                        rentalEvent.getItemName(), rentalEvent.getRentalId());
                break;
            case SORRY:
                owner.setPoints(owner.getPoints() - 1);
                userFacade.updateUser(owner);
                break;
            case RETURN_IN_TIME:
                user.setPoints(user.getPoints() + 1);
                userFacade.updateUser(user);
                break;
            case RETURN_DELAYED:
                user.setPoints(user.getPoints() - 10);
                userFacade.updateUser(user);
                break;
        }
    }
}
