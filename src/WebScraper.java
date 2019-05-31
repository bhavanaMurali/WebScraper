import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// @author Bhavana Murali

// Class used to HTMl parse from url using Jsoup
public class WebScraper {
    public static void main(String args[]){
        print("Running web scraper");

        Document document;
        try {

            // pass the url as the input argument
            document = Jsoup.connect("https://www.ebay.com/b/Fiction-Literature-Books/171228/bn_1865444").get();
            // get the title of the url page
            String title = document.title();
            print("Title: " + title);
            print("\n");
                // To get the prices
              Elements price = document.select(".s-item__price:contains($)");
              // to get the book titles
              Elements bookTitle = document.select(".s-item__title");

              // Exporting the results to a csv file
              FileOutputStream fout = new FileOutputStream("output_ebay.csv");
              PrintStream csv = new PrintStream(fout);
              csv.println("Book_Title Price");

              for (int i=0; i < price.size()-2; i++) {
                csv.println(bookTitle.get(i).text() + "	" + price.get(i).text());
            }
            fout.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void print(String string){
        System.out.println(string);
    }
}
