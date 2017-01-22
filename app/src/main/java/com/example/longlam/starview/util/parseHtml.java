package com.example.longlam.starview.util;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.longlam.starview.CropImage;
import com.example.longlam.starview.CropInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

public class ParseHtml {

   private Elements elements = null;

   public void gettingHtmlForCrop(String crop) {
      try {
         elements = getInfoBoxHTML(crop.replace(" ", "_"));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static Drawable getDrawableFromURL(String imgURL) {
      long now = System.currentTimeMillis();
      URL url = null;
      try {
         // Decode Drawable from Bitmap Stream from URL connection
         url = new URL(imgURL);
         Drawable drawable = new BitmapDrawable(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
         drawable.setBounds(0, 0, drawable.getIntrinsicWidth() * 10, drawable.getIntrinsicHeight() * 10);
         System.out.println("TIME: " + (System.currentTimeMillis() - now) + " " + imgURL);
         return drawable;
      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
   }

   private static Elements getInfoBoxHTML(String cropName) throws IOException {
      String noseg = Jsoup.connect(
            "http://stardewvalleywiki.com/mediawiki/api.php?action=parse&page=" + cropName + "&format=xml")
                          .get().text();
      return Jsoup.parse(noseg).select("div[id=infoboxborder]");
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

   public CropImage getImageUrls() {
      CropImage cropImage = new CropImage();
      String[] drawableUrls = getDrawableUrls(elements.select("img"));
      int i = 0;
      System.out.println(drawableUrls[4]);
      cropImage.setTitleImageUrl(drawableUrls[i++]);
      cropImage.setEnergyImageUrl(drawableUrls[i++]);
      cropImage.setHealthImageUrl(drawableUrls[i++]);
      cropImage.setSilverImageUrl(drawableUrls[i++]);
      cropImage.setGoldImageUrl(drawableUrls[i++]);
      if(drawableUrls.length > 5) {
         cropImage.setKegImageUrl(drawableUrls[i++]);
         cropImage.setIridiumImageUrl(drawableUrls[i++]);
         cropImage.setJarImageUrl(drawableUrls[i++]);
      }
      return cropImage;
   }

   public CropInfo getCropInfo() {
      CropInfo cropInfo = new CropInfo();
      String hrf = elements.select("a[href*=seeds]").attr("href");
      System.out.println(hrf);
      Elements infoboxTable = elements.get(0).getElementsByAttributeValue("id", "infoboxdetail");
      String title = elements.get(0).getElementsByAttributeValue("id", "infoboxheader").text();
      cropInfo.setCropTitle(title);
      int i = 0;
      cropInfo.setDescription(infoboxTable.get(i++).text());
      cropInfo.setSeeds(infoboxTable.get(i++).text());
      cropInfo.setGrowthTime(infoboxTable.get(i++).text());
      cropInfo.setSeason(infoboxTable.get(i++).text());
      cropInfo.setHealing(infoboxTable.get(i++).text());
      cropInfo.setBasePrice(infoboxTable.get(i++).text());
      cropInfo.setSellingSkillPrices(infoboxTable.get(i++).text());
      if(infoboxTable.size() > 7) {
         cropInfo.setArtisanBasePrices(infoboxTable.get(i++).text());
         cropInfo.setArtisanSkillPrice(infoboxTable.get(i++).text());
      }
      return cropInfo;
   }
}
