package com.example.longlam.starview;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

import static com.example.longlam.starview.util.parseHtml.getStardewUrl;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
   private CropInfo so;
   @Test
   public void gettingHtml() {
      so = new CropInfo();
      Document doc = null;
      Document images = null;

      try {
         doc = Jsoup.connect("http://stardewvalleywiki.com/Melon").get();
         images = Jsoup.connect("http://stardewvalleywiki.com/mediawiki/api.php?action=parse&page=melon&section=0&format=xml&prop=images").get();
      } catch (IOException e) {
         e.printStackTrace();
      }
      if (doc == null || images == null) return;
      Elements infoBoxElements = doc.getAllElements();
      System.out.println(getImageUrls(infoBoxElements, images));
      getInfoboxText(infoBoxElements);
   }

   private void getInfoboxText(Elements elements) {
      Elements infoboxTable = elements.get(0).getElementsByAttributeValue("id", "infoboxdetail");
      int i = 0;
      so.setDescription(infoboxTable.get(i++).text());
      so.setSeeds(infoboxTable.get(i++).text());
      so.setGrowthTime(infoboxTable.get(i++).text());
      so.setHealing(infoboxTable.get(i++).text());
      so.setBasePrice(infoboxTable.get(i++).text());
      so.setTillerPrice(infoboxTable.get(i++).text());
      so.setArtisanPrice(infoboxTable.get(i++).text());
      so.setProArtisanPrice(infoboxTable.get(i).text());
   }

   // Gets unique infobox images
   public boolean getImageUrls(Elements elements, Element images) {
      Elements infoboxTable = elements.get(0).getElementsByAttributeValue("id", "infoboxdetail");
      Elements imagesItem = images.getElementsByTag("img");
      int i = 0;
      so.setTitleImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      so.setEnergyImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      so.setHealthImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      so.setSilverImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      so.setGoldQualityImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      so.setKegImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      so.setIridiumImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      so.setJarImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i));
      return true;
   }

   private String getInfoBoxItemUrl(Elements infoboxTable, Elements imagesItem, int i) {
      String titleImageName = imagesItem.get(i).text();
      titleImageName = titleImageName.replace("_", " ");
      if (titleImageName.contains("Jelly") || titleImageName.contains("Pickles")) {
         titleImageName = titleImageName.substring(0, titleImageName.length() - 4);
      }
      String imageUrl = infoboxTable.select("img[alt=" + titleImageName + "]").attr("src");
      imageUrl = imageUrl.substring(0, imageUrl.indexOf(".png") + 4)
                         .replace("thumb/", "");
      return getStardewUrl(imageUrl);
   }
}