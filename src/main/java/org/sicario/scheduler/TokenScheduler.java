package org.sicario.scheduler;

import org.sicario.model.entities.User;
import org.sicario.service.UserService;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenScheduler {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private final UserService userService = new UserService();
    public void startScheduler(){
        scheduler.scheduleAtFixedRate(this::checkAndUpdateTokenDelete, 0, 30, TimeUnit.DAYS);
        scheduler.scheduleAtFixedRate(this::checkAndUpdateTokenRefuse,0,1,TimeUnit.DAYS);
    }

    private void checkAndUpdateTokenDelete(){
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            if (user.getTokenDelete() < 1) {
                while (user.getTokenDelete() < 1) {
                    user.setTokenDelete(user.getTokenDelete() + 1);
                    userService.updateUser(user);
                }
            }
        }
    }
    private void checkAndUpdateTokenRefuse(){
        List<User> users = userService.getAllUsers();
        for (User user :users){
            if (user.getTokenRefuse()<2){
                while (user.getTokenRefuse()<2){
                    user.setTokenRefuse(user.getTokenRefuse()+2);
                    userService.updateUser(user);
                }
            }
        }
    }
}
