package com.interview.pets.client;


import com.interview.pets.model.Pet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class PetClient {
    private final RestClient petStoreRestClient;

    public Pet getPet(Long id) {
        log.info("Starting get Pet for id: {}", id);

        return petStoreRestClient.get()
                .uri("/pet/{id}", id)
                .retrieve()
                .body(Pet.class);
    }

    public Pet createPet(Pet pet) {
        log.info("Starting creation of Pet for id: {}", pet.getId());
        return petStoreRestClient.post()
                .uri("/pet")
                .body(pet)
                .retrieve()
                .body(Pet.class);
    }
}
