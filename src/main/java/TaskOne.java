import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class TaskOne {

    public static final String USERS_URL =
            "https://jsonplaceholder.typicode.com/users/";

    public static void main(String[] args) throws Exception {


//        System.out.println(createUser(USERS_URL));                             // -1
//        System.out.println(updateUser(USERS_URL + "1"));                   // -2

//        String userToDelete = "10";    // Selecting 10-th user to delete     // -3
//        Integer statusCode = deleteUser(USERS_URL + "/10");
//        if ((statusCode >= 200) && (statusCode <= 299)) {
//            System.out.println("TaskThree.User is deleted");
//        } else System.out.println("TaskThree.User is not deleted");
//
//        System.out.println(getUserInfo(USERS_URL));       //  -4
//
//        String userId = "9";                    //  -5
//        System.out.println(getUserInfo(USERS_URL + "?id=" + userId));
////
          String userName = "Moriah.Stanton";                       //  -6
          System.out.println(getUserInfo(USERS_URL + "?username=" + userName));

    }

    public static String createUser(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofFile(Path.of("user.json")))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }


    public static String updateUser(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .PUT(HttpRequest.BodyPublishers.ofFile(Path.of("userUpdate.json")))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() + "\n" + response.body();
    }

    public static Integer deleteUser(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .DELETE()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        return Integer.valueOf(response.statusCode());

    }

    public static String getUserInfo(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


}
