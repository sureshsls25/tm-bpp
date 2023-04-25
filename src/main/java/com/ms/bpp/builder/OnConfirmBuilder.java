package com.ms.bpp.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.bpp.dao.BppDao;
import com.ms.bpp.dao.OrderRepo;
import com.ms.bpp.dao.UsersRepo;
import com.ms.bpp.entity.SessionAttendeeDetails;
import com.ms.bpp.entity.Users;
import com.ms.bpp.exception.ApiException;
import com.ms.bpp.util.CommonUtil;
import com.ms.bpp.common.dto.CancellationOrRejectionDto;
import com.ms.bpp.common.dto.OrderDto;
import com.ms.bpp.common.dto.SessionAttendeeDetailsDto;
import com.ms.bpp.common.enums.ContextAction;
import com.ms.bpp.common.enums.OrderStatus;
import com.ms.bpp.common.model.common.*;
import com.ms.bpp.common.model.confirm.ConfirmMessage;
import com.ms.bpp.common.model.confirm.ConfirmRequest;
import com.ms.bpp.common.model.onconfirm.OnConfirmMessage;
import com.ms.bpp.common.model.onconfirm.OnConfirmRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OnConfirmBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OnConfirmBuilder.class);

    @Autowired
    private ResponseBuilder responseBuilder;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BppDao bppDao;

    @Value("${beckn.seller.id}")
    private String bppId;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    UsersRepo usersRepo;

    public OnConfirmRequest buildOnConfirm(ConfirmRequest request) throws UnknownHostException {
        OnConfirmRequest response = new OnConfirmRequest();
        Context context = this.responseBuilder.buildContext(request.getContext(), ContextAction.CONFIRM.value());
        context.setBppId(this.bppId);
        context.setBapUri(InetAddress.getLocalHost().getHostAddress());
        OnConfirmMessage onConfirmMessage = new OnConfirmMessage();
        onConfirmMessage.setOrder(new Order());
        try {
            buildOrder(request.getMessage(), onConfirmMessage);
            response.setMessage(onConfirmMessage);
        } catch (Exception e) {
            logger.error("Unable to process application request ", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to init application request");
        }
        context.setTimestamp(CommonUtil.getDateTimeString(new Date()));
        response.setContext(context);
        return response;
    }

    private void buildOrder(ConfirmMessage message, OnConfirmMessage response) {
        Provider provider = message.getOrder().getProvider();
        Fulfillment fulfillment = provider.getFulfillments().get(0);
        try {
            Optional<Users> users = bppDao.getMentorDetails(provider.getAgent().getPerson().getId());
            com.ms.bpp.entity.Order order = new com.ms.bpp.entity.Order();
            order.setStatus(OrderStatus.PENDING_APPROVAL.value());
            order.setMentorId(users.get().getUserId());
            Person personFromRequest = fulfillment.getCustomer().getPerson();
            SessionAttendeeDetails sessionAttendeeDetails = new SessionAttendeeDetails();
            sessionAttendeeDetails.setName(personFromRequest.getName());
            sessionAttendeeDetails.setAge(personFromRequest.getAge());
            sessionAttendeeDetails.setGender(personFromRequest.getGender());
            sessionAttendeeDetails.setSchoolOrInstitution(personFromRequest.getSchoolOrInstitution());
            sessionAttendeeDetails.setEmail(personFromRequest.getMailid());
            //sessionAttendeeDetails.setSkills(personFromRequest.getSkills().get(0).getName());
            order.setSessionAttendeeDetails(sessionAttendeeDetails);
            order.setSkillRegisterFor(provider.getSkill().get(0).getName());
            com.ms.bpp.entity.Order savedOrder = orderRepo.save(order);
            Customer customer = new Customer();
            Person person = new Person();
            person.setName(personFromRequest.getName());
            person.setAge(personFromRequest.getAge());
            person.setGender(personFromRequest.getGender());
            person.setSchoolOrInstitution(personFromRequest.getName());
            person.setMailid(personFromRequest.getMailid());
            customer.setPerson(person);
            response.getOrder().setId(savedOrder.getOrderId());
            List<Fulfillment> fulfillmentList = new ArrayList<>();
            Fulfillment fulfillment1 = new Fulfillment();
            fulfillment1.setCustomer(customer);
            fulfillment1.setStatus(OrderStatus.PENDING_APPROVAL);
            fulfillmentList.add(fulfillment1);
            response.getOrder().setFulfillments(fulfillmentList);
        } catch (Exception e) {
            logger.error("Unable to process application request ", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to init application request");
        }
    }




}
