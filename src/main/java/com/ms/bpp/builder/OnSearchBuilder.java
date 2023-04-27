package com.ms.bpp.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.bpp.dao.BppDao;
import com.ms.bpp.entity.Users;
import com.ms.bpp.exception.ApiException;
import com.ms.bpp.util.ApplicationConstant;
import com.ms.bpp.util.CommonUtil;
import com.ms.bpp.common.enums.ContextAction;
import com.ms.bpp.common.model.common.Catalog;
import com.ms.bpp.common.model.common.Context;
import com.ms.bpp.common.model.onsearch.OnSearchMessage;
import com.ms.bpp.common.model.onsearch.OnSearchRequest;
import com.ms.bpp.common.model.search.SearchMessage;
import com.ms.bpp.common.model.search.SearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OnSearchBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OnSearchBuilder.class);

    @Autowired
    private ResponseBuilder responseBuilder;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BppDao bppDao;

    @Value("${beckn.seller.id}")
    private String bppId;

    public OnSearchRequest buildOnSearch(SearchRequest request) throws UnknownHostException {
        OnSearchRequest response = new OnSearchRequest();
        OnSearchMessage message = new OnSearchMessage();
        try {
            Context context = responseBuilder.buildContext(request.getContext(), request.getContext().getAction());
            response.setContext(context);
            message.setCatalog(buildCatalog(request.getMessage()));
            response.setMessage(message);
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to search application request");
        }
        request.getContext().setTimestamp(CommonUtil.getDateTimeString(new Date()));
        response.setContext(request.getContext());
        return response;
    }

    private Catalog buildCatalog(SearchMessage searchMessage) {
        Catalog catalog = new Catalog();
        try {
            catalog.setBppDescriptor(CommonUtil.getBPPDetails());
            List<String> intentDtls = getIntentAndType(searchMessage);
            List<Users> users = bppDao.groupBySessionsTitleOrMentor(intentDtls.get(0), intentDtls.get(1));
            if (!ObjectUtils.isEmpty(users)) {
                catalog.setBppProviders(responseBuilder.getUsersDetailsForSearch(users));
            } else {
                catalog.setNoResult("No results found for the given search, Please try again with different search");
            }
        } catch (Exception e) {
            logger.error("Unable to process search application request", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to process search application request");
        }
        return catalog;
    }

    private List<String> getIntentAndType(SearchMessage message) {
        List<String> intentDtls = new ArrayList<>();
        String intent = null;
        String intentType = null;
        if (message != null && message.getIntent() != null) {
            if (message.getIntent().getItem() != null && message.getIntent().getItem().getDescriptor() != null) {
                intent = message.getIntent().getItem().getDescriptor().getName();
                intentType = ApplicationConstant.SESSION_TITLE;
            } else if (message.getIntent().getAgent() != null && message.getIntent().getAgent().getPerson().getName() != null) {
                intent = message.getIntent().getAgent().getPerson().getName();
                intentType = ApplicationConstant.MENTOR_NAME;
            }
        }
        intentDtls.add(intent);
        intentDtls.add(intentType);
        return intentDtls;
    }
}
