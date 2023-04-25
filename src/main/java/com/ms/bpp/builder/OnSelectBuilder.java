package com.ms.bpp.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.bpp.dao.BppDao;
import com.ms.bpp.entity.Users;
import com.ms.bpp.exception.ApiException;
import com.ms.bpp.util.CommonUtil;
import com.ms.common.enums.ContextAction;
import com.ms.common.model.common.Context;
import com.ms.common.model.common.Item;
import com.ms.common.model.common.Order;
import com.ms.common.model.common.Provider;
import com.ms.common.model.onselect.OnSelectMessage;
import com.ms.common.model.onselect.OnSelectRequest;
import com.ms.common.model.select.SelectMessage;
import com.ms.common.model.select.SelectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OnSelectBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OnSelectBuilder.class);

    @Autowired
    private ResponseBuilder responseBuilder;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BppDao bppDao;

    @Value("${beckn.seller.id}")
    private String bppId;

    public OnSelectRequest buildOnSelect(SelectRequest request) throws UnknownHostException {
        OnSelectRequest response = new OnSelectRequest();
        Context context = responseBuilder.buildContext(request.getContext(), ContextAction.ON_SELECT.value());
        context.setBppId(bppId);
        context.setBapUri(InetAddress.getLocalHost().getHostAddress());
        OnSelectMessage selectMessage = new OnSelectMessage();
        selectMessage.setOrder(new Order());
        try {
            buildOrder(request.getMessage(), selectMessage);
            response.setMessage(selectMessage);
        } catch (Exception e) {
            logger.error("Unable to search application request ", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to search application request");
        }
        context.setTimestamp(CommonUtil.getDateTimeString(new Date()));
        response.setContext(context);
        return response;
    }

    private void buildOrder(SelectMessage selectMessage, OnSelectMessage onSelectMessage) {
        try {
            List<String> items = selectMessage.getOrder().getItems().stream().map(Item::getId).collect(Collectors.toList());
            Optional<Users> users = bppDao.getMentorDetails(items.get(0));
            Provider response = responseBuilder.getFullMentorDetails(users);
            onSelectMessage.getOrder().setProvider(response);
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get data from database");
        }
    }
}
