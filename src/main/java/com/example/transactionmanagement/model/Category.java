package com.example.transactionmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String categoryName;
    private String  description;
}
