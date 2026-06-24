package com.interview.pets.service;

import static org.junit.jupiter.api.Assertions.*;

import com.interview.pets.client.PetClient;
import com.interview.pets.model.Pet;
import com.interview.pets.model.PostPetResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetClient petClient;

    @InjectMocks
    private PetService petService;

    @Test
    void shouldReturnPetWhenGetPetIsSuccessful() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("doggie");
        pet.setStatus("available");

        when(petClient.getPet(1L)).thenReturn(pet);

        Pet result = petService.getPet(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("doggie", result.getName());
        assertEquals("available", result.getStatus());

        verify(petClient, times(1)).getPet(1L);
    }

    @Test
    void shouldReturnEnrichedResponseWhenCreatePetIsSuccessful() {
        Pet request = new Pet();
        request.setId(12345L);
        request.setName("myPet");
        request.setStatus("available");

        Pet externalResponse = new Pet();
        externalResponse.setId(12345L);
        externalResponse.setName("myPet");
        externalResponse.setStatus("available");

        when(petClient.createPet(request)).thenReturn(externalResponse);

        PostPetResponse result = petService.createPet(request);

        assertNotNull(result);
        assertNotNull(result.getTransactionId());
        assertDoesNotThrow(() -> UUID.fromString(result.getTransactionId()));
        assertNotNull(result.getDateCreated());
        assertEquals("myPet", result.getName());
        assertEquals("available", result.getStatus());

        verify(petClient, times(1)).createPet(request);
    }
}