package com.monika.rentaladder.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Profiles;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class UserConfiguration {

    UserFacade userFacade(){
        return new UserFacade(new InMemoryUserRepository());
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository){
        return new UserFacade(userRepository);
    }

    @Bean
    //@Profile("!" + Profiles.TEST)
    TaskExecutor sendingEmailTaskExecutor() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setThreadGroupName("sendingEmailTaskExecutor");
        threadPoolTaskScheduler.setPoolSize(20);
        return threadPoolTaskScheduler;
    }

    //TODO for tests
    //    //@Bean(name="sendingEmailTaskExecutor")
    //    //@Profile(Profiles.TEST)
    //    //TaskExecutor singleThreadBonusPointsTaskExecutor() {
    //        //return (Runnable task) -> task.run();
    //    //}

}
