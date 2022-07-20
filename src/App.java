import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(
                "urls.properties");
        props.load(file);
        return props;

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Properties prop = getProp();

        String url = prop.getProperty("url.imdb.alura");
        URI endereco = URI.create(url);

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = (String) response.body();

        JsonParser parser = new JsonParser();
        List<Map<String,String>> listaDeFilmes = parser.parse(body);

        var stickerGenerator= new StickerGenerator();

        for (Map<String,String> movie: listaDeFilmes) {

            String urlMovieImage = movie.get("image");
            String urlMovieBigImage = urlMovieImage.substring(0,urlMovieImage.indexOf("@")+1) + ".jpg";
            String title = movie.get("title");
            String stickerName = title + ".png";

            try{
                InputStream inputStream = new URL(urlMovieBigImage).openStream();
                stickerGenerator.create(inputStream,stickerName);
            }catch(Exception e){
                System.out.println(e);
            }

        }

    }

    public void moviesImageAndRating(List<Map<String,String>> movieList){

        for (Map<String,String> movie: movieList) {

            System.out.println("\u001b[30m \u001b[45m"+movie.get("title")+"\u001b[m");
            System.out.println(movie.get("image"));
            System.out.println(movie.get("imDbRating"));

            int rating = Math.round(Float.parseFloat(movie.get("imDbRating")));

            for(int i = 0; i < rating; i++){
                System.out.print("⭐️");
            }

            System.out.println();
        }

    }

}
