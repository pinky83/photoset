import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Yakovenko on 11/27/2017.
 *
 */
public class PhotoParserTest {
    private static final File textFile = new File("test/resources/Telbase.txt");
    private static int numberOfStrings = 1;
    private static final String FILE_PATH = "C:\\files";
    private static final String GOOGLE_SEARCH_URI_PART1 = "https://www.google.com.ua/search?q=";
    private static final String GOOGLE_SEARCH_URI_PART2 =  "&source=lnms&tbm=isch&sa=X&ved=0ahUKEwik_KKfpvDXAhXra5oKHfkuC1cQ_AUICigB&biw=1600&bih=769";

    public void searchForName (String name) throws IOException{
        File htmlDocument = new File(FILE_PATH + "/html/" + "htmlDocument" + "_" + name + ".txt");
        File picture = new File(FILE_PATH + "/images/" + "picture" + "_" + name);

        Document document = Jsoup.connect(GOOGLE_SEARCH_URI_PART1 + name + GOOGLE_SEARCH_URI_PART2).get();

        List<String> parseResult = new ArrayList<>();

        Elements elements = document.getElementsByClass("rg_meta notranslate");
        while (!elements.isEmpty()) parseResult.add(elements.remove(0).childNode(0).toString());

        PrintWriter writer = new PrintWriter(new FileWriter(htmlDocument));
        parseResult.forEach (result -> writer.println(result));
        writer.close();

        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(htmlDocument));
        JsonReader reader = Json.createReader(fis);
        JsonArray personObject = reader.readArray();

        for (int i=0; i < personObject.size(); i++) {
            JsonValue json = personObject.get(i);

        }



    }


    @Test
    public void testOne () throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(textFile), "CP1251"));
        List<String> names = new ArrayList<>();
        while (numberOfStrings > 0) {
            String surname = bufferedReader.readLine().split(";")[3].split(" ")[1];
            names.add(surname);
            numberOfStrings--;
        }

        System.out.println("List size: " + names.size());
        System.out.println(names.get(0));

    }

    @Test
    public void testTwo () throws Exception {
        searchForName("Елена");
    }
}
