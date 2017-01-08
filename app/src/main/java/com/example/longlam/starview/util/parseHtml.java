package com.example.longlam.starview.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.longlam.starview.CropInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class parseHtml {

   public static CropInfo gettingHtml(String crop) {
      CropInfo so = new CropInfo();
      Document doc = null;
      Document images = null;

      try {
         String cropName = crop.replace(" ", "_");
         doc = Jsoup.connect("http://stardewvalleywiki.com/"+ cropName).get();
         images = Jsoup.connect("http://stardewvalleywiki.com/mediawiki/api.php?action=parse&page="
                                      + cropName + "&section=0&format=xml&prop=images").get();
      } catch (IOException e) {
         e.printStackTrace();
      }

      if (doc == null || images == null) return null;
      Elements infoBoxElements = doc.getAllElements();
      System.out.println(so.getImageUrls(infoBoxElements, images));
      so.getInfoboxText(infoBoxElements);
      return so;
   }

   public static Drawable getDrawableFromURL(String imgURL) {
      URL url = null;
      try {
         url = new URL(imgURL);
         Drawable drawable = new BitmapDrawable(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
         drawable.setBounds(0, 0, drawable.getIntrinsicWidth() * 10, drawable.getIntrinsicHeight() * 10);
         return drawable;
      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
   }
}
