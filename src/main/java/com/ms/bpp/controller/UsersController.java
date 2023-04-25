package com.ms.bpp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.bpp.dao.BppDao;
import com.ms.bpp.dao.UsersRepo;
import com.ms.bpp.services.CommonService;
import com.ms.bpp.services.UsersService;
import com.ms.bpp.services.auth.UserDetailsImpl;
import com.ms.bpp.util.ApplicationConstant;
import com.ms.bpp.util.CommonUtil;
import com.ms.bpp.util.JsonUtil;
import com.ms.bpp.util.JwtUtils;
import com.ms.bpp.common.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApplicationConstant.CONTEXT_ROOT)
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    JsonUtil jsonUtil;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    BppDao bppDao;

    @Autowired
    CommonService commonService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> users(@RequestBody String users, @RequestHeader HttpHeaders httpHeaders) throws JsonProcessingException {
        UsersDTO usersDTO = (UsersDTO) jsonUtil.toObject(users, UsersDTO.class);
        if (CommonUtil.isEmpty(usersDTO) || CommonUtil.isEmpty(usersDTO.getEmail()))
            return new ResponseEntity<>(objectMapper.writeValueAsString(new MessageResponse("Please provide valid mail id")), HttpStatus.BAD_REQUEST);
        if (CommonUtil.isEmpty(usersDTO) || CommonUtil.isEmpty(usersDTO.getMobile()))
            return new ResponseEntity<>(objectMapper.writeValueAsString(new MessageResponse("Please provide valid contact details")), HttpStatus.BAD_REQUEST);
        if (CommonUtil.isEmpty(usersDTO) || CommonUtil.isEmpty(usersDTO.getMobile()))
            return new ResponseEntity<>(objectMapper.writeValueAsString(new MessageResponse("Please provide valid contact details")), HttpStatus.BAD_REQUEST);
        if (CommonUtil.isEmpty(usersDTO) || CommonUtil.isEmpty(usersDTO.getPassword()))
            return new ResponseEntity<>(objectMapper.writeValueAsString(new MessageResponse("Password can not be null")), HttpStatus.BAD_REQUEST);
        if (CommonUtil.isEmpty(usersDTO) || CommonUtil.isEmpty(usersDTO.getFirstName()))
            return new ResponseEntity<>(objectMapper.writeValueAsString(new MessageResponse("Please provide First name")), HttpStatus.BAD_REQUEST);
        if (CommonUtil.isEmpty(usersDTO) || CommonUtil.isEmpty(usersDTO.getLastName()))
            return new ResponseEntity<>(objectMapper.writeValueAsString(new MessageResponse("Please provide Last name")), HttpStatus.BAD_REQUEST);
        if (usersRepo.existsByEmail(usersDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(objectMapper.writeValueAsString(new MessageResponse("Error: Username is already taken!")));
        }
        if (usersRepo.existsByMobile(usersDTO.getMobile())) {
            return ResponseEntity
                    .badRequest()
                    .body(objectMapper.writeValueAsString(new MessageResponse("Error: Mobile is already in use by other user!")));
        }
        return new ResponseEntity<>(usersService.createUser(usersDTO), HttpStatus.OK);
    }

    @PostMapping(path = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signIn(@Valid @RequestBody LoginRequest loginRequest) throws JsonProcessingException {
        if (CommonUtil.isEmpty(loginRequest) || CommonUtil.isEmpty(loginRequest.getEmail()) || CommonUtil.isEmpty(loginRequest.getPassword()))
            return new ResponseEntity<>(objectMapper.writeValueAsString(new MessageResponse("Please provide valid mail id or password")), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(objectMapper.writeValueAsString(new JwtResponse(jwt,
                userDetails.getEmail(),
                roles)));
    }

    @GetMapping(path = "/users/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> orderDetails(@RequestHeader HttpHeaders httpHeaders) throws JsonProcessingException {
        String response = commonService.convertObjectToString(usersService.getOrderDetailsByMentor());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(path = "/users/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateOrderStatus(@RequestBody List<OrderDto> requestBody, @RequestHeader HttpHeaders httpHeaders) throws JsonProcessingException {
        String response = commonService.convertObjectToString(usersService.orderUpdate(requestBody));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
