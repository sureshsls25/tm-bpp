package com.ms.bpp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ms.bpp.builder.ResponseBuilder;
import com.ms.bpp.common.model.search.SearchRequest;
import com.ms.bpp.services.CommonService;
import com.ms.bpp.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;

@RestController
public class SearchController {


    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    CommonService commonService;

    @Autowired
    ResponseBuilder responseBuilder;

    @PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> search(@RequestBody String body, @RequestHeader HttpHeaders httpHeaders) throws JsonProcessingException, UnknownHostException {
        SearchRequest request = (SearchRequest) jsonUtil.toObject(body, SearchRequest.class);

//        CompletableFuture.runAsync(() -> {
//            try {
                commonService.send(request, httpHeaders);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
        return new ResponseEntity<>(responseBuilder.sendAck(request), HttpStatus.OK);
    }

}
