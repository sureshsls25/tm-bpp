/*
package com.ms.bpp.controller;

import com.ms.bpp.dao.ProvidersRepo;
import com.ms.bpp.entity.Providers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProvidersController {

    @Autowired
    ProvidersRepo providersRepo;


    @GetMapping("all")
    public ResponseEntity<List<Providers>> providersResponseEntity() {

        return new ResponseEntity<>(providersRepo.findAll(), HttpStatus.OK);
    }

}
*/
