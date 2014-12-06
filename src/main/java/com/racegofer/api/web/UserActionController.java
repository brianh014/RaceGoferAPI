package com.racegofer.api.web;

import com.racegofer.api.domain.RaceUser;
import com.racegofer.api.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miles on 11/19/2014.
 */

@Transactional
@RestController
public class UserActionController {
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public void setJdbcUserDetailsManager(JdbcUserDetailsManager jdbcUserDetailsManager){
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping("/RegisterUser")
    public ResponseEntity<String> registerNewUser(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
            @RequestParam(value = "lastName", required = false, defaultValue = "") String lastName,
            @RequestParam(value = "email", required = false, defaultValue = "") String email,
            @RequestParam(value = "phoneNumber", required = false, defaultValue = "") String phoneNumber
    ){
        if(jdbcUserDetailsManager.userExists(userName)){
            return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        User userDetails = new User(userName, passwordEncoder.encode(password), authorities);
        jdbcUserDetailsManager.createUser(userDetails);
        RaceUser user = userRepository.findByUserName(userName);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);

        return new ResponseEntity<String>("User "+userName+" has been created",HttpStatus.OK);
    }
}
