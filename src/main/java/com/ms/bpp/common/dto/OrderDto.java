package com.ms.bpp.common.dto;

import com.ms.bpp.common.enums.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private String orderId;
    private SessionAttendeeDetailsDto menteesDetails;
    private OrderStatus status;
    private CancellationOrRejectionDto cancellationOrRejection;
    private String orderCreatedOn;
    private String orderUpdatedOn;
    private List<SkillDto> skill;
}
