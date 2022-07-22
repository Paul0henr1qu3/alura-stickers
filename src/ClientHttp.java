import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ClientHttp {

    public String getData(String url){

        try{
            Properties prop = getProp();
            url = prop.getProperty(url);
            URI endereco = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = (String) response.body();

            return body;

        }catch (IOException | InterruptedException ex){
            throw new RuntimeException(ex);
        }

    }

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(
                "urls.properties");
        props.load(file);
        return props;

    }

}
