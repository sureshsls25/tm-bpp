package com.ms.bpp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ms.bpp.services.CommonService;
import com.ms.bpp.util.ApplicationConstant;
import com.ms.bpp.util.JsonUtil;
import com.ms.common.model.confirm.ConfirmRequest;
import com.ms.common.model.status.StatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;

@RestController
@RequestMapping(ApplicationConstant.EXTERNAL_CONTEXT_ROOT)
public class StatusController {

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    CommonService commonService;

    @PostMapping(path = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> search(@RequestBody String body, @RequestHeader HttpHeaders httpHeaders) throws JsonProcessingException, UnknownHostException {
        StatusRequest request = (StatusRequest) jsonUtil.toObject(body, StatusRequest.class);

//        CompletableFuture.runAsync(() -> {
//            try {
        String response = commonService.send(request);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
