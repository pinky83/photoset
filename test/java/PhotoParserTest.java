import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final String[] NAMES = {"Марина","Катерина", "София", "Алина", "Вика", "Татьяна", "Люба", "Ирина", "Виктория", "Диана", "Александра",
                                            "Людмила", "Ксения", "Оксана", "Мария", "Валентина", "Юлия", "Дарья", "Карина", "Кристина", "Прасковья"};
    private static final String[] NAMES_2  = {"Константин", "Иван", "Федор", "Максим", "Егор", "Андрей", "Сергей", "Игорь", "Дмитрий", "Александр", "Владимир",
                                            "Василий", "Кирилл", "Трофим", "Виктор", "Владислав", "Юрий", "Николай", "Борис"};

    public static String extractUrl(String text) {
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        if (urlMatcher.find()) return text.substring(urlMatcher.start(0), urlMatcher.end(0));

        return null;
    }

    public void searchForName (String name) throws IOException{
        File htmlDocument = new File(FILE_PATH + "/html/" + "htmlDocument" + "_" + name + ".txt");
        String picture = FILE_PATH + "/images/" + "picture" + "_" + name;
        Image image;
        int count =1;

        Document document = Jsoup.connect(GOOGLE_SEARCH_URI_PART1 + name + GOOGLE_SEARCH_URI_PART2).get();

        List<String> parseResult = new ArrayList<>();

        Elements elements = document.getElementsByClass("rg_meta notranslate");
        while (!elements.isEmpty()) parseResult.add(elements.remove(0).childNode(0).toString());

        PrintWriter writer = new PrintWriter(new FileWriter(htmlDocument));
        parseResult.forEach (result -> writer.println(result));
        writer.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH + "/html/" + "htmlDocument" + "_" + name + ".txt")));
        while (reader.ready()) {
            String link = reader.readLine();
            link = extractUrl(link);
            if (link!= null) {
                try {
                    URL url = new URL(link);
                    InputStream in = new BufferedInputStream(url.openStream());

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    int n;
                    while (-1!=(n=in.read(buf))) out.write(buf, 0, n);

                    out.close();
                    in.close();

                    byte[] response = out.toByteArray();

                    FileOutputStream fos = new FileOutputStream(picture + "_" + count++ + ".jpg");
                    fos.write(response);
                    fos.close();
                }catch (Exception e){System.out.println(e.getMessage());}
            }
        }
        reader.close();
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
        for (String name : NAMES_2) {searchForName(name);}
//        searchForName("Инна");
    }
}
