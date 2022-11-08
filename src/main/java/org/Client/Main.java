package org.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        // Set up the body data
        String message = "Hello servlet";
        byte[] body = message.getBytes(StandardCharsets.UTF_8);

        URL myURL = new URL("http://localhost:8080/mgmServlet/patients");
        HttpURLConnection conn = null;

        conn = (HttpURLConnection) myURL.openConnection();
// Set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "text/html");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(body.length));
        conn.setDoOutput(true);

// Write the body of the request
        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body, 0, body.length);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

        String inputLine;
// Read the body of the response
        while ((inputLine = bufferedReader.readLine()) != null) {
            System.out.println(inputLine);
        }
    }
}