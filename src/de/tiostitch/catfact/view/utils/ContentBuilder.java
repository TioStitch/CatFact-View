package de.tiostitch.catfact.view.utils;

import com.google.gson.Gson;
import de.tiostitch.catfact.view.objects.CatFact;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public final class ContentBuilder {

    private static String readContent(BufferedReader reader) {
        try {
            final StringBuilder builder = new StringBuilder();
            String inputLine = "";

            while ((inputLine = reader.readLine()) != null) {
                builder.append(inputLine);
                break;
            }

            return builder.toString();
        } catch (IOException ignored) {
            return "";
        }
    }

    public static String getContent(String url) {
        try {

            final InputStream stream = new URL(url).openStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            return readContent(bufferedReader);

        } catch (IOException ignored) {
            return "";
        }
    }

    public static CatFact getCatFact() {
        try {
            final String URL_LINK = "https://catfact.ninja/fact";
            final URL url = new URL(URL_LINK);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            final String content = ContentBuilder.getContent(URL_LINK);
            connection.disconnect();

            final Gson gson = new Gson();
            return gson.fromJson(content, CatFact.class);
        } catch (IOException ignored) {
            return null;
        }
    }

    public static ArrayList<String> getFact() {
        final String fact = getCatFact().getFact();
        final ArrayList<String> fact_line = new ArrayList<>();

        if (fact == null) {
            fact_line.add("Cats are small tigers...");
            return fact_line;
        }

        final String[] fact_split = fact.split("\\.");
        fact_line.addAll(Arrays.asList(fact_split));

        return fact_line;
    }
}