package org.toys.storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.toys.models.UserToy;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    public static final String FILE_USER_TOYS = "user_won.json";

    public static List<UserToy> getToys() {
        JSONParser parser = new JSONParser();

        List<UserToy> data = new ArrayList<>();

        try (FileReader f = new FileReader(FILE_USER_TOYS)) {
            JSONArray json = (JSONArray) parser.parse(f);

            for (Object o : json) {
                JSONObject item = (JSONObject) o;

                data.add(new UserToy(
                        ((Long) item.get("id")).intValue(),
                        (String) item.get("name"),
                        ((Long) item.get("count")).intValue()
                ));

            }
        } catch (IOException | ParseException ignore) {
        }

        return data;
    }

    public static void updateToys(List<UserToy> toys) {
        JSONArray data = new JSONArray();

        for (UserToy toy : toys) {
            JSONObject json = new JSONObject();
            json.put("id", toy.getId());
            json.put("name", toy.getName());
            json.put("count", toy.getCount());

            data.add(json);
        }

        try (FileWriter fileWriter = new FileWriter(FILE_USER_TOYS)) {
            data.writeJSONString(fileWriter);
        } catch (IOException err) {
            System.err.println(err.getMessage());
        }
    }
}

