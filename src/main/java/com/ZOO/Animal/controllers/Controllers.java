package com.ZOO.Animal.controllers;

import com.ZOO.Animal.Models.AnimalList;
import com.ZOO.Animal.Models.Animals;
import com.ZOO.Animal.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("zoo")
public class Controllers {
    AnimalList animalList = new AnimalList();

//    @RequestMapping("/new")
//    public String newAnimalForm(Map<String, Object> model) {
//         AnimalList animal = new AnimalList();
//        model.put("animal", animal);
//        return "new_animal";
//    }

    @GetMapping
    public ArrayList<Animals> list() throws FileNotFoundException {
        animalList.readYaml();
        return animalList.getZooList();
    }

    @GetMapping("{id}")
    public Animals getOne(@PathVariable String id){
        return animalList.getZooList().stream()
                .filter(Animals->Animals.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(id));
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Animals create(@RequestBody Map<String, String> model) throws IOException {
        animalList.readYaml();
        Animals animal = new Animals(model.get("name"), model.get("breed"), model.get("dateB"), model.get("dateR"));
        animalList.getZooList().add(animal);
        animalList.saveYaml();
        return animal;
    }
    @DeleteMapping("{id}")
    public void deleteAnimal(@PathVariable String id) throws IOException {
        animalList.readYaml();
        animalList.deleteAnimal(id);
        animalList.saveYaml();
    }
    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Animals update(@PathVariable String id, @RequestBody Map<String, String> body) throws IOException {
        animalList.readYaml();
        Animals animal = animalList.getZooListById(id);
        for(String key : body.keySet()){
            switch(key){
                case "name"->animal.setName(body.get(key));
                case "breed"->animal.setBreed(body.get(key));
                case "dateB"->animal.setDateB(body.get(key));
                case "dateR"->animal.setDateR(body.get(key));
            }
        }
        animalList.saveYaml();
        return animal;
    }

}
