package com.monika.rentaladder.User;

import com.monika.rentaladder.User.UserDTOs.UserEntity;
import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<UserEntity, Long> {

    UserEntity save(UserEntity userEntity);

    UserEntity findByName(String name);

}
