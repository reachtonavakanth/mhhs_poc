package utils;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtils {

    /*
   Description: To read the json object from json file
     */
    public JSONObject getJsonObject(String jsonFilePath, String jsonObjectKey) throws IOException {
        InputStream inputstream = null;
        JSONObject jsonObj = null;
        try {
            inputstream = new FileInputStream(jsonFilePath);
            JSONTokener jsonTokener = new JSONTokener(inputstream);
            jsonObj = new JSONObject(jsonTokener);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (inputstream != null) {
                inputstream.close();
            }
        }
        return jsonObj.getJSONObject(jsonObjectKey);
    }

    public Properties getPropValue(String filepath) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(filepath);
            prop.load(input);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return prop;
    }
}
