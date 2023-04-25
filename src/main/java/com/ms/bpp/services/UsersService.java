package com.ms.bpp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.bpp.dao.OrderRepo;
import com.ms.bpp.dao.UsersRepo;
import com.ms.bpp.entity.*;
import com.ms.bpp.exception.ApiException;
import com.ms.bpp.services.auth.UserDetailsImpl;
import com.ms.bpp.util.CommonUtil;
import com.ms.common.dto.*;
import com.ms.common.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersService {

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ObjectMapper objectMapper;


    public String createUser(UsersDTO users) throws JsonProcessingException {
        usersRepo.save(getUsers(users));
        return objectMapper.writeValueAsString(new MessageResponse("User registered successfully"));

    }

    private Users getUsers(UsersDTO request) {

        Users users = new Users();
        try {
            users.setFirstName(request.getFirstName());
            users.setLastName(request.getLastName());
            users.setEmail(request.getEmail());
            users.setMobile(request.getMobile());
            users.setPassword(encoder.encode(request.getPassword()));
            users.setRole(UserRoles.USER);
            Address address = new Address();
            address.setAddressLine1(request.getAddress().getAddressLine1());
            address.setAddressLine2(request.getAddress().getAddressLine2());
            address.setCity(request.getAddress().getCity());
            address.setState(request.getAddress().getState());
            address.setCountry(request.getAddress().getCountry());
            address.setZipCode(request.getAddress().getZipCode());
            users.setAddress(address);
            MentorProfile mentorProfile = new MentorProfile();
            AboutMentor aboutMentor = new AboutMentor();
            aboutMentor.setShortDesc(request.getMentorProfile().getAboutMentor().getShortDesc());
            aboutMentor.setLongDesc(request.getMentorProfile().getAboutMentor().getLongDesc());
            mentorProfile.setAboutMentor(aboutMentor);
            Skill skill = new Skill();
            skill.setName(request.getMentorProfile().getSkill().get(0).getName());
            List<Skill> skills = new ArrayList<>();
            skills.add(skill);
            skill.setMentorProfile(mentorProfile);
            mentorProfile.setSkill(skills);

            List<Education> education = new ArrayList<>();
            request.getMentorProfile().getEducation().forEach(educationDto -> {
                Education education1 = new Education();
                education1.setQualification(educationDto.getQualification());
                education1.setInstitutionName(educationDto.getQualification());
                education.add(education1);
            });
            mentorProfile.setEducation(education);
            List<ProfessionalExperience> professionalExperiences = new ArrayList<>();
            request.getMentorProfile().getProfessionalExperience().forEach(professionalExperienceDto -> {
                ProfessionalExperience professionalExperience = new ProfessionalExperience();
                professionalExperience.setExperience(professionalExperienceDto.getExperience());
                professionalExperiences.add(professionalExperience);
            });
            mentorProfile.setProfessionalExperience(professionalExperiences);
            mentorProfile.setUsers(users);
            users.setMentorProfile(mentorProfile);
        } catch (Exception e) {
            logger.error("Unable to create mentor", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to create mentor");
        }
        return users;
    }

    public List<OrderDto> getOrderDetailsByMentor() {
        List<OrderDto> responseOrders = new ArrayList<>();
        try {
            UserDetailsImpl userDetails = CommonUtil.getCurrentUserDetails();
            List<Order> orderList = orderRepo.getOrderByMentorId(userDetails.getId());
            if (!ObjectUtils.isEmpty(orderList)) {
                orderList.forEach(order -> {
                    OrderDto orderDto = new OrderDto();
                    orderDto.setOrderId(order.getOrderId());
                    orderDto.setStatus(OrderStatus.getStatus(order.getStatus()));
                    SessionAttendeeDetailsDto attendeeDetails = new SessionAttendeeDetailsDto();
                    attendeeDetails.setMailId(order.getSessionAttendeeDetails().getEmail());
                    attendeeDetails.setAge(order.getSessionAttendeeDetails().getAge());
                    attendeeDetails.setName(order.getSessionAttendeeDetails().getName());
                    attendeeDetails.setGender(order.getSessionAttendeeDetails().getGender());
                    attendeeDetails.setSkills(order.getSessionAttendeeDetails().getSkills());
                    attendeeDetails.setSchoolOrInstitution(order.getSessionAttendeeDetails().getSchoolOrInstitution());
                    orderDto.setMenteesDetails(attendeeDetails);
                    if (!ObjectUtils.isEmpty(order.getCancellation())) {
                        CancellationOrRejectionDto cancellationOrRejectionDto = new CancellationOrRejectionDto();
                        cancellationOrRejectionDto.setReasonDesc(order.getCancellation().getReasonDesc());
                        orderDto.setCancellationOrRejection(cancellationOrRejectionDto);
                    }
                    orderDto.setOrderCreatedOn(order.getCreatedAt().toString());
                    if (OrderStatus.getStatus(order.getStatus()) == OrderStatus.ACCEPTED || OrderStatus.getStatus(order.getStatus()) == OrderStatus.REJECTED || OrderStatus.getStatus(order.getStatus()) == OrderStatus.CANCELLED)
                        orderDto.setOrderUpdatedOn(order.getUpdatedAt().toString());
                    SkillDto skillDto = new SkillDto();
                    skillDto.setName(order.getSkillRegisterFor());
                    List<SkillDto> skillDtos = new ArrayList<>();
                    skillDtos.add(skillDto);
                    orderDto.setSkill(skillDtos);
                    responseOrders.add(orderDto);
                });
            }
        } catch (Exception e) {
            logger.error("Unable to get order details", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to get order details");
        }
        return responseOrders;
    }


    public MessageResponse orderUpdate(List<OrderDto> listOrder) {
        List<Order> saveOrder = new ArrayList<>();
        try {
            listOrder.forEach(order -> {
                Optional<Order> savedOrder = orderRepo.findById(order.getOrderId());
                if (savedOrder.isPresent()) {
                    logger.info("Updating Order {}", order.getOrderId());
                    if (order.getStatus() == OrderStatus.ACCEPTED) {
                        savedOrder.get().setStatus(OrderStatus.ACCEPTED.value());
                    } else if (order.getStatus() == OrderStatus.REJECTED) {
                        savedOrder.get().setStatus(OrderStatus.REJECTED.value());
                        UpdateReason(order, savedOrder, "No Rejection Comments Available");
                    } else if (order.getStatus() == OrderStatus.CANCELLED) {
                        savedOrder.get().setStatus(OrderStatus.CANCELLED.value());
                        UpdateReason(order, savedOrder, "No Cancellation Comments Available");
                    }
                    saveOrder.add(savedOrder.get());
                }
            });
        } catch (Exception e) {
            logger.error("Failed to update order", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to update order");
        }
        return new MessageResponse("Orders has been updated");
    }

    private static void UpdateReason(OrderDto order, Optional<Order> savedOrder, String reason) {
        if (!ObjectUtils.isEmpty(order.getCancellationOrRejection())) {
            CancellationOrRejection cancellationOrRejection = new CancellationOrRejection();
            cancellationOrRejection.setReasonDesc(order.getCancellationOrRejection().getReasonDesc());
            savedOrder.get().setCancellation(cancellationOrRejection);
        } else {
            CancellationOrRejection cancellationOrRejection = new CancellationOrRejection();
            cancellationOrRejection.setReasonDesc(reason);
            savedOrder.get().setCancellation(cancellationOrRejection);
        }
    }

}
