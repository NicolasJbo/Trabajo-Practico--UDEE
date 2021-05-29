package com.utn.tpFinal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utn.tpFinal.model.User;
import com.utn.tpFinal.model.dto.LoginRequestDto;
import com.utn.tpFinal.model.dto.LoginResponseDto;
import com.utn.tpFinal.model.dto.UserDto;
import com.utn.tpFinal.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.utn.tpFinal.util.Constants.*;

@RestController
@RequestMapping("/login")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) throws JsonProcessingException {
        User user = userService.findByMailAndPassword(loginRequest.getMail(), loginRequest.getPassword());
        System.out.println(user);
        if(user != null){
            UserDto userDTO = modelMapper.map(user, UserDto.class);
            return ResponseEntity.ok(LoginResponseDto.builder().token(this.generateToken(userDTO)).build());
        } else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid username or password.");
        }
    }
    private String generateToken(UserDto user) throws JsonProcessingException {
        String role = user.getIsClient() ? "CLIENT" : "EMPLOYEE";

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);
        String token = Jwts.builder()
                .setId("JWT")
                .setSubject(user.getMail())
                .claim("user",objectMapper.writeValueAsString(user))
                .claim("authorities",grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis() + 2000000 ))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes()).compact();

        return token;
    }

}