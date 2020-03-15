package com.monika.rentaladder.User;

import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<UserEntity, Long> {

    UserEntity save(UserEntity userEntity);

    UserEntity findByName(String name);

}
