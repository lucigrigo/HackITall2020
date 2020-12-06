package sample;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Requests {
    public static String postDetectRequest(String link) throws IOException, InterruptedException {
        var body = HttpRequest.BodyPublishers.ofString("{\"url\": \"" + link + "\"}");

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                URI.create("https://westeurope.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=false&recognitionModel=recognition_03&returnRecognitionModel=false&detectionModel=detection_02"))
                .version(HttpClient.Version.HTTP_1_1)
                .header("Content-Type", "application/json")
                .header("Ocp-Apim-Subscription-Key", "f6ab47f64bf641629f38d7eca07eda51")
                .POST(body)
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body().substring(1);
        JSONObject obj = new JSONObject(jsonString);
        String faceId = obj.getString("faceId");

        //System.out.println(faceId);
        return faceId;
    }

    public static boolean postVerifyRequest(String faceId1, String faceId2) throws IOException, InterruptedException {
        var body = HttpRequest.BodyPublishers.ofString("{\"faceId1\": \"" + faceId1 + "\"," +
                "\n\"faceId2\": \"" + faceId2 + "\"}");

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                URI.create("https://westeurope.api.cognitive.microsoft.com/face/v1.0/verify"))
                .version(HttpClient.Version.HTTP_1_1)
                .header("Content-Type", "application/json")
                .header("Ocp-Apim-Subscription-Key", "f6ab47f64bf641629f38d7eca07eda51")
                .POST(body)
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        HttpHeaders headers = response.headers();
        //System.out.println(response.statusCode());
        //System.out.println(response.body());

        String jsonString = response.body();
        JSONObject obj = new JSONObject(jsonString);
        boolean isIdentical = obj.getBoolean("isIdentical");

        //System.out.println(isIdentical);
        return isIdentical;
    }

    public static String postImageReferenceRequest(String location) throws IOException, InterruptedException {
        Path path = Paths.get(location);
        var body = HttpRequest.BodyPublishers.ofFile(path);

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                URI.create("https://www.googleapis.com/upload/drive/v3/files?uploadType=media"))
                .header("Content-Type", "image/jpeg")
                .header("Content-Lenght", String.valueOf(body.contentLength()))
                .POST(body)
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        HttpHeaders headers = response.headers();
        System.out.println(response.statusCode());
        System.out.println(response.body());
        return null;
    }
}
