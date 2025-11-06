
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class SeaConditionsAPIClient {

    public String getSeaConditionsJson(double lat, double lon) {
        try {
            String endpoint = String.format(
                    "https://api.open-meteo.com/v1/marine?latitude=%f&longitude=%f&hourly=wave_height,wind_speed&timezone=UTC",
                    lat, lon);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
            .newBuilder()
            .uri(URI.create(endpoint))
            .GET()
            .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return response.body();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

}
