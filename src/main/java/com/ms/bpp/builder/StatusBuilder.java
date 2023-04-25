package com.ms.bpp.builder;

import com.ms.bpp.dao.BppDao;
import com.ms.bpp.dao.OrderRepo;
import com.ms.bpp.entity.Users;
import com.ms.bpp.exception.ApiException;
import com.ms.bpp.util.CommonUtil;
import com.ms.common.dto.CancellationOrRejectionDto;
import com.ms.common.dto.OrderDto;
import com.ms.common.dto.SessionAttendeeDetailsDto;
import com.ms.common.dto.SkillDto;
import com.ms.common.enums.ContextAction;
import com.ms.common.enums.OrderStatus;
import com.ms.common.model.common.*;
import com.ms.common.model.onstatus.OnStatusMessage;
import com.ms.common.model.onstatus.OnStatusRequest;
import com.ms.common.model.status.StatusMessage;
import com.ms.common.model.status.StatusRequest;
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
import java.util.Optional;

@Service
public class StatusBuilder {

    private static final Logger logger = LoggerFactory.getLogger(StatusBuilder.class);
    @Autowired
    OrderRepo orderRepo;


    @Value("${beckn.seller.id}")
    private String bppId;

    @Autowired
    private ResponseBuilder responseBuilder;

    @Autowired
    BppDao bppDao;

    public OnStatusRequest getStatus(StatusRequest request) throws UnknownHostException {
        OnStatusRequest response = new OnStatusRequest();
        Context context = this.responseBuilder.buildContext(request.getContext(), ContextAction.STATUS.value());
        context.setBppId(this.bppId);
        context.setBapUri(InetAddress.getLocalHost().getHostAddress());
        OnStatusMessage onStatusMessage = new OnStatusMessage();
        try {
            status(request.getMessage(), onStatusMessage);
            response.setMessage(onStatusMessage);
        } catch (Exception e) {
            logger.error("Unable to process application request ", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to init application request");
        }
        context.setTimestamp(CommonUtil.getDateTimeString(new Date()));
        response.setContext(context);
        return response;
    }

    private void status(StatusMessage message, OnStatusMessage response) {
        String mailid = message.getMailId();
        List<Order> orderResponseList = new ArrayList<>();
        try {
            List<com.ms.bpp.entity.Order> orderList = bppDao.getOrderDetailsByAttendeeMailId(mailid);
            orderList.forEach(order -> {
                Order orderResponse = new Order();
                OrderDto orderDto = new OrderDto();
                orderDto.setOrderId(order.getOrderId());
                orderDto.setStatus(OrderStatus.getStatus(order.getStatus()));
                if (!ObjectUtils.isEmpty(order.getCancellation())) {
                    CancellationOrRejectionDto cancellationDto = new CancellationOrRejectionDto();
                    cancellationDto.setReasonDesc(order.getCancellation().getReasonDesc());
                    orderDto.setCancellationOrRejection(cancellationDto);
                }
                SessionAttendeeDetailsDto attendeeDetails = new SessionAttendeeDetailsDto();
                attendeeDetails.setMailId(order.getSessionAttendeeDetails().getEmail());
                attendeeDetails.setAge(order.getSessionAttendeeDetails().getAge());
                attendeeDetails.setName(order.getSessionAttendeeDetails().getName());
                attendeeDetails.setGender(order.getSessionAttendeeDetails().getGender());
                attendeeDetails.setSkills(order.getSessionAttendeeDetails().getSkills());
                attendeeDetails.setSchoolOrInstitution(order.getSessionAttendeeDetails().getSchoolOrInstitution());
                orderDto.setMenteesDetails(attendeeDetails);
                orderDto.setOrderCreatedOn(order.getCreatedAt().toString());
                if (OrderStatus.getStatus(order.getStatus()) == OrderStatus.ACCEPTED || OrderStatus.getStatus(order.getStatus()) == OrderStatus.REJECTED || OrderStatus.getStatus(order.getStatus()) == OrderStatus.CANCELLED)
                    orderDto.setOrderUpdatedOn(order.getUpdatedAt().toString());
                SkillDto skillDto = new SkillDto();
                skillDto.setName(order.getSkillRegisterFor());
                List<SkillDto> skillDtos = new ArrayList<>();
                skillDtos.add(skillDto);
                orderDto.setSkill(skillDtos);
                orderResponse.setOrderDetails(orderDto);
                Optional<Users> users = bppDao.getMentorDetails(order.getMentorId());
                List<Fulfillment> fulfillmentList = new ArrayList<>();
                Fulfillment fulfillment = new Fulfillment();
                Agent agent = new Agent();
                Person person = new Person();
                person.setName(users.get().getFirstName() + " " + users.get().getLastName());
                person.setId(users.get().getUserId());
                if (OrderStatus.ACCEPTED==OrderStatus.getStatus(order.getStatus())) {
                    Contact contact = new Contact();
                    contact.setEmail(users.get().getEmail());
                    contact.setPhone(users.get().getMobile());
                    agent.setContact(contact);
                }
                agent.setPerson(person);
                fulfillment.setAgent(agent);
                fulfillmentList.add(fulfillment);
                orderResponse.setFulfillments(fulfillmentList);
                orderResponseList.add(orderResponse);
            });
            response.setOrder(orderResponseList);
        } catch (
                Exception e) {
            logger.error("Unable to process application request ", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to init application request");
        }
    }
}
