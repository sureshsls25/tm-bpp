package com.ms.bpp.dao;

import com.ms.bpp.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, String> {

    List<Order> findAll();
    List<Order> getAllBySessionAttendeeDetails_Email(String mailId);

    List<Order> getOrderByMentorId(String mentorid);

}
