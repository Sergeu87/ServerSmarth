package pl.wsinf.smarthome.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.wsinf.smarthome.server.model.HomeConfigEntity;
import pl.wsinf.smarthome.server.model.User;
import pl.wsinf.smarthome.server.util.HomeConfigHandler;

import java.util.List;

/**
 * The type Users controller.
 */
@Slf4j
@Controller
public class UsersController {

    @Autowired
    private HomeConfigHandler homeConfigHandler;

    /**
     * Login response entity.
     *
     * @param username the username
     * @param password the password
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        log.info("login() request: username={}, password={}", username, password);
        if (username == null || password == null) {
            log.error("login() response BAD REQUESRT: Wrong login or password");
            return ResponseEntity.badRequest().build();
        } else {
            List<User> usersFromXml = homeConfigHandler.getHomeConfigEntity().getUserList();
            for (User user : usersFromXml) {
                if (user.getName().equals(username) && user.getPassword().equals(password)) {
                    log.info("login() response OK: {}", user);
                    return ResponseEntity.ok(user);
                }
            }
        }
        log.error("performLogin() response BAD REQUESRT: Something went wrong");
        return ResponseEntity.badRequest().build();
    }

    /**
     * Register response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST, value = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<User> register(@RequestBody User user) {
        log.info("register() request: user={}", user);

        final HomeConfigEntity homeConfig = homeConfigHandler.getHomeConfigEntity();

        final int newUserId = homeConfig.getUserList().size() + 1;
        user.setId(String.valueOf(newUserId));
        homeConfig.getUserList().add(user);

        homeConfigHandler.updateHomeConfig(homeConfig);

        log.error("register() response OK: {}", user);
        return ResponseEntity.ok(user);
    }

}
