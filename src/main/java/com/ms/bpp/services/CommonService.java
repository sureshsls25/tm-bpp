package com.ms.bpp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.bpp.builder.OnConfirmBuilder;
import com.ms.bpp.builder.OnSearchBuilder;
import com.ms.bpp.builder.OnSelectBuilder;
import com.ms.bpp.builder.StatusBuilder;
import com.ms.bpp.util.CommonUtil;
import com.ms.bpp.common.dto.MessageResponse;
import com.ms.bpp.common.enums.ContextAction;
import com.ms.bpp.common.model.confirm.ConfirmRequest;
import com.ms.bpp.common.model.onconfirm.OnConfirmRequest;
import com.ms.bpp.common.model.onsearch.OnSearchRequest;
import com.ms.bpp.common.model.onselect.OnSelectRequest;
import com.ms.bpp.common.model.onstatus.OnStatusRequest;
import com.ms.bpp.common.model.search.SearchRequest;
import com.ms.bpp.common.model.select.SelectRequest;
import com.ms.bpp.common.model.status.StatusRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.UnknownHostException;

@Service
@Transactional
public class CommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    private OnSearchBuilder onSearchBuilder;

    @Autowired
    private OnSelectBuilder onSelectBuilder;
    @Autowired
    private OnConfirmBuilder onConfirmBuilder;

    @Autowired
    private StatusBuilder statusBuilder;


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Sender sender;

    public String send(SearchRequest searchRequest, HttpHeaders httpHeaders) throws JsonProcessingException, UnknownHostException {
        if (searchRequest == null || searchRequest.getMessage() == null || searchRequest.getMessage().getIntent() == null)
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid search request"));
        if (searchRequest.getMessage().getIntent().getItem() == null || searchRequest.getMessage().getIntent().getItem().getDescriptor() == null)
            return objectMapper.writeValueAsString(new MessageResponse("Search request can not be null"));
        OnSearchRequest response = onSearchBuilder.buildOnSearch(searchRequest);
        String url = response.getContext().getBapUri().concat("/" + ContextAction.ON_SEARCH);
        String json = objectMapper.writeValueAsString(response);
        logger.info("Sending Response to caller {}", json);
        sender.send(url, httpHeaders, json);
        return json;
    }

    public String send(SelectRequest selectRequest, HttpHeaders httpHeaders) throws
            JsonProcessingException, UnknownHostException {
        if (selectRequest == null || selectRequest.getMessage() == null || selectRequest.getMessage().getOrder() == null)
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid select request"));
        if (selectRequest.getMessage().getOrder().getItems() == null || selectRequest.getMessage().getOrder().getItems().get(0) == null)
            return objectMapper.writeValueAsString(new MessageResponse("Mentor id can not be null"));
        OnSelectRequest response = onSelectBuilder.buildOnSelect(selectRequest);
        String url = response.getContext().getBapUri().concat("/" + ContextAction.ON_SELECT.value());
        String json = objectMapper.writeValueAsString(response);
        logger.info("Sending Response to caller {}", json);
        // sender.send(url, httpHeaders, json);
        return json;
    }

    public String send(ConfirmRequest confirmRequest, HttpHeaders httpHeaders) throws
            JsonProcessingException, UnknownHostException {
        if (CommonUtil.isEmpty(confirmRequest) || CommonUtil.isEmpty(confirmRequest.getMessage()) || CommonUtil.isEmpty(confirmRequest.getMessage().getOrder()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid confirm request"));
        if (CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider()) || CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider().getFulfillments()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid confirm request"));
        if (CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider().getAgent()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid confirm request"));
        if (CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider().getAgent().getPerson()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid confirm request"));
        if (CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider().getAgent().getPerson().getId()))
            return objectMapper.writeValueAsString(new MessageResponse("Mentor id can not be null"));
        if (CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider().getSkill()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid skill details"));
        if (CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider().getSkill().get(0).getName()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid skill details"));
        if (CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider().getFulfillments().get(0)))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid mentees details"));
        if (CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider().getFulfillments().get(0).getCustomer()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid mentees details"));
        if (CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider().getFulfillments().get(0).getCustomer().getPerson()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid mentees details"));
        if (CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider().getFulfillments().get(0).getCustomer().getPerson().getMailid()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid mentees mail id details"));
        if (CommonUtil.isEmpty(confirmRequest.getMessage().getOrder().getProvider().getFulfillments().get(0).getCustomer().getPerson().getName()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid mentees name details"));

        OnConfirmRequest response = onConfirmBuilder.buildOnConfirm(confirmRequest);
        String url = response.getContext().getBapUri().concat("/" + ContextAction.CONFIRM.value());
        String json = objectMapper.writeValueAsString(response);
        logger.info("Sending Response to caller {}", json);
        // sender.send(url, httpHeaders, json);
        return json;
    }

    public String send(StatusRequest statusRequest,HttpHeaders httpHeaders) throws JsonProcessingException, UnknownHostException {
        if (CommonUtil.isEmpty(statusRequest.getMessage()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid mentees details"));
        if (CommonUtil.isEmpty(statusRequest.getMessage().getMailId()))
            return objectMapper.writeValueAsString(new MessageResponse("Please provide valid mentees mail id"));
        OnStatusRequest response = statusBuilder.getStatus(statusRequest);
        String url = response.getContext().getBapUri().concat("/" + ContextAction.ON_STATUS.value());
        String json = objectMapper.writeValueAsString(response);
        logger.info("Sending Response to caller {}", json);
        sender.send(url, httpHeaders, json);
        return json;
    }

    public <T> String convertObjectToString(T response) throws JsonProcessingException {
        return objectMapper.writeValueAsString(response);
    }
}
