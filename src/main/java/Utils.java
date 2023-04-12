package main.java;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    //    JSON Reader
    public static Object jsonReadAndWrite(String function, String key, Object var, String type, String filepath) {
        if (function.contains("read")) {
            try {
                FileReader reader = new FileReader(filepath);
                JSONTokener tokener = new JSONTokener(reader);
                JSONArray jsonArray = new JSONArray(tokener);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (type.contains("int")) {
                        return jsonObject.getInt(key);
                    } else if (type.contains("string")) {
                        return jsonObject.getString(key);
                    }
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (function.contains("write")) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(key, var);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            try {
                FileWriter fileWriter = new FileWriter("src/resources/data/data.json");
                fileWriter.write(jsonArray.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
