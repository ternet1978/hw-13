package taskTwo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskTwo {


    public static final String SITE_URL =
            "https://jsonplaceholder.typicode.com/";


    public static void main(String[] args) throws Exception {

        String user = "3";    // Select number of the TaskThree.User

        String postsUserX = getCommentsForLastPost(SITE_URL + "users/" + user + "/posts");
        Gson gson2 = new Gson();
        Posts posts = gson2.fromJson(postsUserX, Posts.class);

        Long lastPostId = posts.stream()
                .map(a -> a.getId())
                .reduce(Long::max).get();
        System.out.println("\nAll comments for the last post (" + lastPostId + ") for selected user (" + user + ")\n");

        String commentsForUserX = getCommentsForLastPost(SITE_URL + "posts/" + lastPostId + "/comments");
        Gson gson3 = new Gson();
        Comments comments = gson3.fromJson(commentsForUserX, Comments.class);
        System.out.println(comments.stream()

                .map(a -> a.getBody())
                .collect(Collectors.joining(";\n\n", "", "."))
        );


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("user-" + user + "-post-" + lastPostId + "-comments.json"), comments);

    }

    public static String getCommentsForLastPost(String uri) throws Exception {


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}

