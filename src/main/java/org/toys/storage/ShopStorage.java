package org.toys.storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.toys.models.Toy;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopStorage {
    private static final String FILE_TOYS = "toys.json";

    public static List<Toy> getToys() {
        JSONParser parser = new JSONParser();

        List<Toy> data = new ArrayList<>();

        try (FileReader f = new FileReader(FILE_TOYS)) {
            JSONArray json = (JSONArray) parser.parse(f);

            for (Object o : json) {
                JSONObject item = (JSONObject) o;

                data.add(new Toy(
                        ((Long) item.get("id")).intValue(),
                        (String) item.get("name"),
                        ((Long) item.get("count")).intValue(),
                        ((Long) item.get("weight")).intValue()
                ));

            }
        } catch (IOException | ParseException ignore) {
        }

        return data;
    }

    public static void updateToys(List<Toy> toys) {
        JSONArray data = new JSONArray();

        for (Toy toy : toys) {
            JSONObject json = new JSONObject();
            json.put("id", toy.getId());
            json.put("name", toy.getName());
            json.put("count", toy.getCount());
            json.put("weight", toy.getWeight());

            data.add(json);
        }

        try (FileWriter fileWriter = new FileWriter(FILE_TOYS)) {
            data.writeJSONString(fileWriter);
        } catch (IOException err) {
            System.err.println(err.getMessage());
        }
    }
}
