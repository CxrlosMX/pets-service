package com.interview.pets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.pets.model.Pet;
import com.interview.pets.model.PostPetResponse;
import com.interview.pets.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PetService petService;

    @Test
    void shouldReturnPetWhenCallingGetEndpoint() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("doggie");
        pet.setStatus("available");

        when(petService.getPet(1L)).thenReturn(pet);

        mockMvc.perform(get("/api/pet/{petId}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("doggie"))
                .andExpect(jsonPath("$.status").value("available"));

        verify(petService).getPet(1L);
    }

    @Test
    void shouldCreatePetWhenCallingPostEndpoint() throws Exception {
        Pet request = new Pet();
        request.setId(12345L);
        request.setName("myPet");
        request.setStatus("available");

        PostPetResponse response = new PostPetResponse();
        response.setTransactionId("123e4567-e89b-12d3-a456-426614174000");
        response.setDateCreated(LocalDateTime.of(2026, 6, 24, 10, 30, 0));
        response.setName("myPet");
        response.setStatus("available");

        when(petService.createPet(any(Pet.class))).thenReturn(response);


        mockMvc.perform(post("/api/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.transactionId").value("123e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$.dateCreated").value("2026-06-24T10:30:00"))
                .andExpect(jsonPath("$.name").value("myPet"))
                .andExpect(jsonPath("$.status").value("available"));

        verify(petService).createPet(any(Pet.class));
    }
}