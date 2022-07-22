import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorNasa implements ContentExtractor{

    public List<Content> contentExtractor(String json){
        JsonParser parser = new JsonParser();
        List<Map<String,String>> attributesList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        for(Map<String, String> attributes: attributesList) {

            String title = attributes.get("title");
            String urlImage = attributes.get("url");

            var content = new Content(title,urlImage);

            contents.add(content);
        }

        return contents;

    }

}
