package com.ms.bpp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ms.bpp.services.CommonService;
import com.ms.bpp.util.ApplicationConstant;
import com.ms.bpp.util.JsonUtil;
import com.ms.bpp.common.model.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;

@RestController
public class SearchController {


    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    CommonService commonService;

    @PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> search(@RequestBody String body, @RequestHeader HttpHeaders httpHeaders) throws JsonProcessingException, UnknownHostException {
        SearchRequest request = (SearchRequest) jsonUtil.toObject(body, SearchRequest.class);

//        CompletableFuture.runAsync(() -> {
//            try {
        String response = commonService.send(request, httpHeaders);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
