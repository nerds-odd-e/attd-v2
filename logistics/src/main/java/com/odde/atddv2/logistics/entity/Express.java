package com.odde.atddv2.logistics.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "express")
public class Express {
    @Id
    private String number;
    private String type;
    private String typename;
    private String logo;
    private List<ExpressStatus> list;
    private Integer deliverystatus;
    private Integer issign;
}
