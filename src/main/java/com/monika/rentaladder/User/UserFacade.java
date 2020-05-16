package com.monika.rentaladder.User;

import javax.mail.MessagingException;

public class UserFacade {

    private UserRepository userRepository;

    private MailService mailService;

    public UserFacade(UserRepository userRepository, MailService mailService){
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    public UserEntity addUser(UserEntity user){
        return userRepository.save(user);
    }

    public UserEntity getUserByName(String name){
        return userRepository.findByName(name);
    }

    public UserDTO authenticateUser(UserDTO user) throws Exception {
        UserEntity userEntity = userRepository.findByName(user.getName());
        if ((!user.getPassword().equals(userEntity.getPassword())) || userEntity==null) {
            throw new Exception("Wrong name or password");
        }
        return user.noPassword();
    }

    public String sendMail() throws MessagingException {
        mailService.sendMail("rob.sadlowski@gmail.com",
                "RentAll wymiata:-)",
                "<b>Super feature:-)!</b><br>:P", true);
        return "E-mail has been sent.";
    }

}
