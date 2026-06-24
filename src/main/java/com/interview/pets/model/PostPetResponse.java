package com.interview.pets.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostPetResponse {
    private String transactionId;
    private LocalDateTime dateCreated;
    private String name;
    private String status;
}
