package com.example.pdkrestaurant.controller;


import com.example.pdkrestaurant.dtos.AccountDto;
import com.example.pdkrestaurant.dtos.TokenDetails;
import com.example.pdkrestaurant.dtos.user.RegisterDto;
import com.example.pdkrestaurant.entities.TaiKhoan;
import com.example.pdkrestaurant.exceptions.InternalServerErrorException;
import com.example.pdkrestaurant.exceptions.InvalidException;
import com.example.pdkrestaurant.exceptions.UserNotFoundAuthenticationException;
import com.example.pdkrestaurant.securities.CustomUserDetailsService;
import com.example.pdkrestaurant.securities.JwtTokenUtils;
import com.example.pdkrestaurant.securities.JwtUserDetails;
import com.example.pdkrestaurant.securities.UserAuthenticationToken;
import com.example.pdkrestaurant.service.User.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

/**
 * Created by: IntelliJ IDEA
 * User      : thangpx
 * Date      : 3/15/23
 * Time      : 9:38 AM
 * Filename  : AuthenticationController
 */
@Slf4j
@RestController
@RequestMapping("/pdkrestaurant")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtTokenUtils jwtTokenUtils;

    //@Value("${google.verifyUrl}")
   // private String googleVerifyUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final UserService userService;
    public AuthenticationController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService,
                                    JwtTokenUtils jwtTokenUtils, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userService = userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<TaiKhoan> signup(@Valid @RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(userService.signup(registerDto), HttpStatus.OK);
    }
    @ApiOperation(value = "login form (username, password), avatar null")
    @PostMapping("/login")
    public ResponseEntity<TokenDetails> login(@Valid @RequestBody AccountDto dto) {
        UserAuthenticationToken authenticationToken = new UserAuthenticationToken(
                dto.getUsername(),
                dto.getPassword(),
                true
        );
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (UserNotFoundAuthenticationException | BadCredentialsException ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        final JwtUserDetails userDetails = customUserDetailsService
                .loadUserByUsername(dto.getUsername());
        final TokenDetails result = jwtTokenUtils.getTokenDetails(userDetails, null);
        log.info(String.format("User %s login at %s", dto.getUsername(), new Date()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> sayHello(Principal principal) {
        return new ResponseEntity<>(String.format("Hello %s", principal.getName()), HttpStatus.OK);
    }

}
