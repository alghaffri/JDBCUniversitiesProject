package fetchData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import storeData.DatabaseHandler;

public class dbHandler {
    private static final String API_URL = "http://universities.hipolabs.com/search";

    private DatabaseHandler dbManager;

    public dbHandler(DatabaseHandler dbManager) {
        this.dbManager = dbManager;
    }

    public void fetchUniversitiesByCountry(String countryName) {
        String apiUrl = API_URL + "?country=" + countryName;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder responseBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }

                reader.close();
                String response = responseBuilder.toString();

                // Parse the JSON response using Gson
                Gson gson = new Gson();
                University[] universities = gson.fromJson(response, University[].class);

                // Insert the universities into the database
                List<University> universityList = Arrays.asList(universities);
                dbManager.insertUniversities(universityList);

                System.out.println("Data fetched from API and stored in database successfully.");
            } else {
                System.out.println("Error fetching data from API. Response code: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
