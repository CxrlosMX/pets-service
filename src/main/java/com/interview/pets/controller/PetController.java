package com.interview.pets.controller;


import com.interview.pets.model.Pet;
import com.interview.pets.model.PostPetResponse;
import com.interview.pets.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping("/{petId}")
    public Pet getPet(@PathVariable Long petId) {
        return petService.getPet(petId);
    }

    @PostMapping
    public PostPetResponse createPet(@RequestBody Pet pet) {
        return petService.createPet(pet);
    }
}

