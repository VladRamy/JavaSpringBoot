package com.ZOO.Animal.Models;

import com.ZOO.Animal.exception.NotFoundException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;


public class AnimalList {

    ArrayList<Animals> zooList = new ArrayList<Animals>();

    public void readYaml() throws FileNotFoundException {
        zooList.clear();
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(new File("zoo.yaml"));
        Map<String, Object> obj = yaml.load(inputStream);
        ArrayList<Map<String, String>> zoo;
        zoo = (ArrayList<Map<String, String>>) obj.get("zoo");
        for (Map<String, String> stringStringMap : zoo) {
            if (stringStringMap != null) {
                String id = stringStringMap.get("id");
                String breed = stringStringMap.get("breed");
                String name = stringStringMap.get("name");
                String dateB = stringStringMap.get("dateB");
                String dateR = stringStringMap.get("dateR");

                Animals animal = new Animals(breed, name, dateB, dateR);
                animal.setId(id);
                zooList.add(animal);

            }
        }

    }

    public void saveYaml() throws IOException {
        Map<String, Object> data = new HashMap<String, Object>();
        ArrayList<Map<String, Object>> zoo = new ArrayList<>();
        for(Animals animal : zooList ){
            Map<String, Object> obj = new HashMap<String, Object>();
            obj.put("id", animal.getId());
            obj.put("breed", animal.getBreed());
            obj.put("name", animal.getName());
            obj.put("dateB", animal.getDateB());
            obj.put("dateR", animal.getDateR());
            zoo.add(obj);
        }
        data.put("zoo", zoo);

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        Yaml yaml = new Yaml(options);
        FileWriter writer = new FileWriter("zoo.yaml");
        yaml.dump(data, writer);

    }

    public static void addYAML(){}

    public ArrayList<Animals> getZooList(){
        return zooList;
    }

    public static void main(String[] args) throws IOException {
        AnimalList zoo = new AnimalList();
        zoo.readYaml();
        zoo.saveYaml();
    }
    public Animals getZooListById(String id){
        return zooList.stream()
                .filter(Animals->Animals.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(id));
    }
    public void deleteAnimal(String id){
        Animals animal = getZooListById(id);
        zooList.remove(animal);
    }

}
