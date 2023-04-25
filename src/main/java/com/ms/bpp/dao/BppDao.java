package com.ms.bpp.dao;

import com.ms.bpp.entity.EntitySpecification;
import com.ms.bpp.entity.Order;
import com.ms.bpp.entity.Skill;
import com.ms.bpp.entity.Users;
import com.ms.bpp.util.ApplicationConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BppDao {

    private static final Logger logger = LoggerFactory.getLogger(BppDao.class);

    @Autowired
    SkillRepo skillRepo;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    OrderRepo orderRepo;

    public List<Users> groupBySessionsTitleOrMentor(String intent, String intentType) {
        List<Users> users = new ArrayList<>();
        List<String> usersColumns = Arrays.asList("firstName", "lastName");
        List<String> skillColumns = List.of("name");
        EntitySpecification<Users> usersSpecification = new EntitySpecification<>(usersColumns, intent);
        EntitySpecification<Skill> skillSpecification = new EntitySpecification<>(skillColumns, intent);
        if (intentType.equalsIgnoreCase(ApplicationConstant.SESSION_TITLE)) {
            List<Skill> skills = skillRepo.findAll(skillSpecification);
            skills.forEach(skill -> {
                Users user = skill.getMentorProfile().getUsers();
                users.add(user);
            });
        } else if (intentType.equalsIgnoreCase(ApplicationConstant.MENTOR_NAME)) {
            List<Users> list = usersRepo.findAll(usersSpecification);
            users.addAll(list);
        }
        return users;
    }

    public Optional<Users> getMentorDetails(String id) {
        return usersRepo.findById(id);
    }

    public List<Order> getOrderDetailsByAttendeeMailId(String id) {
        return orderRepo.getAllBySessionAttendeeDetails_Email(id);
    }

}
