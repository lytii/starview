package com.example.longlam.starview.util;

import org.jsoup.select.Elements;

public class parseHtml {

   public static String getInfoTitle(Elements newsHeadlines) {
      String title = newsHeadlines.get(0).getElementById("infoboxheader").text();
      return title;
   }

   public static String getInfoTitleImageURL(Elements newsHeadlines) {
      String titleImageUrl = newsHeadlines.get(0).getElementById("infoboxtable")
                                          .getElementsByAttribute("href").get(0).getElementsByAttribute("src").attr("src");
      if (titleImageUrl.equals("")) {
         titleImageUrl = newsHeadlines.get(0).getElementById("infoboxtable").getElementsByAttribute("src").get(0).attr("src");
      }
      return "http://stardewvalleywiki.com" + titleImageUrl;
   }

   public static String getDescription(Elements newsHeadlines) {
      return newsHeadlines.get(0).getElementById("infoboxdetail").text();
   }

   public static String getStardewUrl(String url) {
      return "http://stardewvalleywiki.com" + url;
   }

}
