package com.interview.pets.service;


import com.interview.pets.client.PetClient;
import com.interview.pets.model.Pet;
import com.interview.pets.model.PostPetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final PetClient petClient;

    public Pet getPet(Long petId) {
        Pet pet = petClient.getPet(petId);
        log.info("Getting Pet for ID:{}", pet.getId());
        return pet;
    }

    public PostPetResponse createPet(Pet pet) {
        Pet createdPet = petClient.createPet(pet);

        log.info("Creating Pet: {}", createdPet);
        PostPetResponse response = new PostPetResponse();
        response.setTransactionId(UUID.randomUUID().toString());
        response.setDateCreated(LocalDateTime.now());
        response.setName(createdPet.getName());
        response.setStatus(createdPet.getStatus());

        return response;
    }
}
