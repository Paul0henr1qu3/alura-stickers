import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorIMDB implements ContentExtractor{

    public List<Content> contentExtractor(String json){
        JsonParser parser = new JsonParser();
        List<Map<String,String>> attributesList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        for(Map<String, String> attributes: attributesList) {

            String title = attributes.get("title");
            String urlImage = attributes.get("image");
            String urlBigImage = urlImage.substring(0,urlImage.indexOf("@")+1) + ".jpg";

            var content = new Content(title,urlBigImage);

            contents.add(content);
        }

        return contents;


    }

}
