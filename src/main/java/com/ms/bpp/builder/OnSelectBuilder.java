package com.ms.bpp.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.bpp.common.model.common.*;
import com.ms.bpp.common.model.onselect.OnSelectMessage;
import com.ms.bpp.common.model.onselect.OnSelectRequest;
import com.ms.bpp.common.model.select.SelectMessage;
import com.ms.bpp.common.model.select.SelectRequest;
import com.ms.bpp.dao.BppDao;
import com.ms.bpp.dao.OrderRepo;
import com.ms.bpp.entity.Users;
import com.ms.bpp.exception.ApiException;
import com.ms.bpp.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    OrderRepo orderRepo;
    @Autowired
    BppDao bppDao;

    @Value("${beckn.seller.id}")
    private String bppId;

    public OnSelectRequest buildOnSelect(SelectRequest request) throws UnknownHostException {
        OnSelectRequest response = new OnSelectRequest();
        Context context = responseBuilder.buildContext(request.getContext(), request.getContext().getAction());
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
            //Fulfillment fulfillment = selectMessage.getOrder().getFulfillments().get(0);
            Optional<Users> users = bppDao.getMentorDetails(items.get(0));
           /* String status = "Not Registered";
            onSelectMessage.getOrder().setStatus(status);*/
           /* if (!ObjectUtils.isEmpty(selectMessage.getOrder().getFulfillments()) && !ObjectUtils.isEmpty(selectMessage.getOrder().getFulfillments().get(0).getCustomer())) {
                List<com.ms.bpp.entity.Order> orderList = orderRepo.getAllBySessionAttendeeDetails_Email(fulfillment.getCustomer().getPerson().getMailid());
                status = orderList.stream().filter(order -> fulfillment.getAgent().getPerson().getSkills().get(0).getName().equalsIgnoreCase(order.getSkillRegisterFor())).map(com.ms.bpp.entity.Order::getStatus).toString();
            }*/
            Provider response = responseBuilder.getFullMentorDetails(users);
            onSelectMessage.getOrder().setProvider(response);
            /*if (!ObjectUtils.isEmpty(status))
                onSelectMessage.getOrder().setStatus(status);*/
        } catch (Exception e) {
            logger.error("Select : Failed to get data from database ", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get data from database");
        }
    }
}
