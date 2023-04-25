package com.ms.bpp.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class CancellationOrRejection {

    private String reasonDesc;
}
