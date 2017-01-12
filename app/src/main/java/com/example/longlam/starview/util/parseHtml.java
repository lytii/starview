package com.example.longlam.starview.util;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.longlam.starview.CropInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

public class parseHtml {

   public static CropInfo gettingHtml(String crop) {
      CropInfo so = new CropInfo();
      Document doc = null;
      Document images = null;

      try {
         String cropName = crop.replace(" ", "_");
         doc = getInfoBoxHTML(cropName);
      } catch (IOException e) {
         e.printStackTrace();
      }

      if (doc == null) return null;
      setImageUrls(so, getDrawableUrls(doc.select("img")));
      setInfoboxText(so, doc.getAllElements());
      return so;
   }

   public static Drawable getDrawableFromURL(String imgURL) {
      long now = System.currentTimeMillis();
      URL url = null;
      try {
         // Decode Drawable from Bitmap Stream from URL connection
         url = new URL(imgURL);
         Drawable drawable = new BitmapDrawable(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
         drawable.setBounds(0, 0, drawable.getIntrinsicWidth() * 10, drawable.getIntrinsicHeight() * 10);
         System.out.println(System.currentTimeMillis() - now);
         return drawable;
      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
   }

   private static Document getInfoBoxHTML(String cropName) throws IOException {
      Document doc = Jsoup.connect(
            "http://stardewvalleywiki.com/mediawiki/api.php?action=parse&page=" + cropName + "&section=0&format=xml")
                          .get();
      String infoBoxHTMLString = doc.getAllElements().get(0).text();
      return Jsoup.parse(infoBoxHTMLString);
   }

   private static String[] getDrawableUrls(Elements elements) {
      final String STARDEWURL = "http://stardewvalleywiki.com";
      Set<String> listDrawableUrls = new LinkedHashSet<String>();
      listDrawableUrls.add(STARDEWURL + elements.get(0).attr("src"));
      for (int i = 1; i < elements.size(); i++) {
         // 2 is the non thumb image
         listDrawableUrls.add(STARDEWURL + elements.get(i).attr("srcset").split(" ")[2]);
      }
      return listDrawableUrls.toArray(new String[listDrawableUrls.size()]);
   }

   public static boolean setImageUrls(CropInfo cropInfo, String[] drawableUrls) {
      int i = 0;
      cropInfo.setTitleImageUrl(drawableUrls[i++]);
      cropInfo.setEnergyImageUrl(drawableUrls[i++]);
      cropInfo.setHealthImageUrl(drawableUrls[i++]);
      cropInfo.setSilverImageUrl(drawableUrls[i++]);
      cropInfo.setGoldImageUrl(drawableUrls[i++]);
      cropInfo.setKegImageUrl(drawableUrls[i++]);
      cropInfo.setIridiumImageUrl(drawableUrls[i++]);
      cropInfo.setJarImageUrl(drawableUrls[i++]);
      return true;
   }

   public static void setInfoboxText(CropInfo cropInfo, Elements elements) {
      Elements infoboxTable = elements.get(0).getElementsByAttributeValue("id", "infoboxdetail");
      String title = elements.get(0).getElementsByAttributeValue("id", "infoboxheader").text();
      cropInfo.setTitle(title);
      int i = 0;
      cropInfo.setDescription(infoboxTable.get(i++).text());
      cropInfo.setSeeds(infoboxTable.get(i++).text());
      cropInfo.setGrowthTime(infoboxTable.get(i++).text());
      cropInfo.setSeason(infoboxTable.get(i++).text());
      cropInfo.setHealing(infoboxTable.get(i++).text());
      cropInfo.setBasePrice(infoboxTable.get(i++).text());
      cropInfo.setSellingSkillPrices(infoboxTable.get(i++).text());
      cropInfo.setArtisanBasePrices(infoboxTable.get(i++).text());
      cropInfo.setArtisanSkillPrice(infoboxTable.get(i++).text());
   }
}
