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

    public static void main(String[] args) throws IOException, InterruptedException {

        var client = new ClientHttp();
        String json = client.getData("url.nasa.alura");

        ContentExtractor extractor = new ContentExtractorNasa();
        var stickerGenerator= new StickerGenerator();
        List<Content> contents =  extractor.contentExtractor(json);

        for (Content content: contents) {

            String stickerName = content.getTitle() + ".png";
            stickerGenerator.setNameImage(content.getTitle());

            try{
                InputStream inputStream = new URL(content.getUrlImage()).openStream();
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
