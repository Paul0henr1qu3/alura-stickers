import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        /*DESAFIOS
        * Acessar outro serviço do IMDB
        * Acessar a API dos mais populares
        * Melhorar a saída
        * Colocar a senha em um properties
        * Utilizar outra biblioteca como parser
        * Dar uma nota sobre o filme
        * */

        // Pegar os dados do IMDB
        //Extrair os dados
        //Exibir e manipular os dados

        String url = "https://imdb-api.com/en/API/Top250Movies/k_6fbtuuy2";
        URI endereco = URI.create(url);

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = (String) response.body();

       JsonParser parser = new JsonParser();
       List<Map<String,String>> listaDeFilmes = parser.parse(body);

       System.out.println(listaDeFilmes.size());

        for (Map<String,String> filme: listaDeFilmes) {
            System.out.print(filme.get("title"));
            System.out.print(filme.get("image"));
            System.out.print(filme.get("imdbRating"));
        }



    }

}
