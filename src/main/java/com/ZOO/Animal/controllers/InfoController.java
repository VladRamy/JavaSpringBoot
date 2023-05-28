package com.ZOO.Animal.controllers;


import com.ZOO.Animal.Models.AnimalList;
import com.ZOO.Animal.Models.MathExpectation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("info")
public class InfoController {
    AnimalList animalList = new AnimalList();
    MathExpectation mathExpectation = new MathExpectation();


    @GetMapping
    public LinkedHashMap<String, Object> info(HttpServletRequest request) throws FileNotFoundException {
        LinkedHashMap<String, Object> info = new LinkedHashMap<String, Object>();
        animalList.readYaml();
        int count_animals = animalList.getZooList().size();
        HashMap<String , String> math_wait = mathExpectation.getPercentAge(animalList);

        Set<Map.Entry<String, String>> entrySet
                = math_wait.entrySet();

        ArrayList<Map.Entry<String , String> > listOfEntry
                = new ArrayList<>(entrySet);

        String username = request.getHeader("username");

        if(!Objects.equals(username, null))
            info.put("Приветствие", "Привет, " + username);

        info.put("ФИО автора", "Владислав Олегович Чемирис");
        info.put("Вариант", 13);
        info.put("Предметная область", "Зоопарк");
        info.put("Матиматическая статистика", listOfEntry);
        info.put("Кол-во животных", count_animals);
        return info;

    }
}
