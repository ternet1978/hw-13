package taskThree;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

public class TaskThree {

    public static final String TEST_URL =
            "https://jsonplaceholder.typicode.com/users/";

    public static void main(String[] args) throws Exception {
        String user = "4";
        System.out.println("\nAll opened tasks for user " + user + ":\n");

        String allTasks = getAllTasksOfUserX(user);
        Gson gson = new Gson();
        Users users = gson.fromJson(allTasks, Users.class);

        System.out.println(users.stream()
                .filter(a -> a.isCompleted() == false)
                .map(a -> a.getTitle())
                .collect(Collectors.joining(";\n", "", "."))
        );


    }


    public static String getAllTasksOfUserX(String user) throws Exception {
        String uri = "https://jsonplaceholder.typicode.com/users/" + user + "/todos";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}








