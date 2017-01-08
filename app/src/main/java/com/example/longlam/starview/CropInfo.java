package com.example.longlam.starview;


import android.graphics.drawable.Drawable;
import android.util.Log;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;

import static com.android.volley.VolleyLog.TAG;

public class CropInfo {
   private String title;
   private String description;
   private String seeds;
   private String growthTime;
   private String season;
   private String health;
   private String energy;
   private String silverHealth;
   private String silverEnergy;
   private String goldHealth;
   private String goldEnergy;
   private String basePrice;
   private String tillerPrice;
   private String artisanPrice;
   private String proArtisanPrice;
   private String titleImageUrl;
   private String energyImageUrl;
   private String healthImageUrl;
   private String silverImageUrl;
   private String goldImageUrl;
   private String kegImageUrl;
   private String iridiumImageUrl;
   private String jarImageUrl;

   private Drawable titleImage;

   public Drawable getTitleImage() {
      return titleImage;
   }

   public void setTitleImage(Drawable titleImage) {
      this.titleImage = titleImage;
   }

   public CropInfo getCropInfo() {
      return this;
   }

   public String getKegImageUrl() {
      return kegImageUrl;
   }

   public void setKegImageUrl(String kegImageUrl) {
      this.kegImageUrl = kegImageUrl;
   }

   public String getJarImageUrl() {
      return jarImageUrl;
   }

   public void setJarImageUrl(String jarImageUrl) {
      this.jarImageUrl = jarImageUrl;
   }

   public String getGoldImageUrl() {
      return goldImageUrl;
   }

   public void setGoldImageUrl(String goldImageUrl) {
      this.goldImageUrl = goldImageUrl;
   }

   public String getIridiumImageUrl() {
      return iridiumImageUrl;
   }

   public void setIridiumImageUrl(String iridiumImageUrl) {
      this.iridiumImageUrl = iridiumImageUrl;
   }

   public String getTitleImageUrl() {
      return titleImageUrl;
   }

   public void setTitleImageUrl(String titleImageUrl) {
      this.titleImageUrl = titleImageUrl;
   }

   public String getEnergyImageUrl() {
      return energyImageUrl;
   }

   public void setEnergyImageUrl(String energyImageUrl) {
      this.energyImageUrl = energyImageUrl;
   }

   public String getHealthImageUrl() {
      return healthImageUrl;
   }

   public void setHealthImageUrl(String healthImageUrl) {
      this.healthImageUrl = healthImageUrl;
   }

   public String getSilverImageUrl() {
      return silverImageUrl;
   }

   public void setSilverImageUrl(String silverImageUrl) {
      this.silverImageUrl = silverImageUrl;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getSeeds() {
      return seeds;
   }

   public void setSeeds(String seeds) {
      this.seeds = seeds;
   }

   public String getSeason() {
      return season;
   }

   public void setSeason(String season) {
      this.season = season;
   }

   public void setHealing(String healing) {
      String[] split = healing.split(" ");
      energy = split[1].substring(1);
      health = split[3].substring(1);
      silverEnergy = split[5].substring(1);
      silverHealth = split[7].substring(1);
      goldEnergy = split[9].substring(1);
      goldHealth = split[11].substring(1);
   }

   public String getHealth() {
      return health;
   }

   public String getEnergy() {
      return energy;
   }

   public String getSilverHealth() {
      return silverHealth;
   }

   public String getSilverEnergy() {
      return silverEnergy;
   }

   public String getGoldHealth() {
      return goldHealth;
   }

   public String getGoldEnergy() {
      return goldEnergy;
   }

   public String getBasePrice() {
      return basePrice;
   }

   public void setBasePrice(String basePrice) {
      this.basePrice = basePrice;
   }

   public String getTillerPrice() {
      return tillerPrice;
   }

   public void setTillerPrice(String tillerPrice) {
      this.tillerPrice = tillerPrice;
   }

   public String getArtisanPrice() {
      return artisanPrice;
   }

   public void setArtisanPrice(String artisanPrice) {
      this.artisanPrice = artisanPrice;
   }

   public String getProArtisanPrice() {
      return proArtisanPrice;
   }

   public void setProArtisanPrice(String proArtisanPrice) {
      this.proArtisanPrice = proArtisanPrice;
   }

   public String getGrowthTime() {
      return growthTime;
   }

   public void setGrowthTime(String growthTime) {
      this.growthTime = growthTime;
   }

   public void getInfoboxText(Elements elements) {
      Elements infoboxTable = elements.get(0).getElementsByAttributeValue("id", "infoboxdetail");
      String title = elements.get(0).getElementsByAttributeValue("id", "infoboxheader").text();
      setTitle(title);
      int i = 0;
      setDescription(infoboxTable.get(i++).text());
      setSeeds(infoboxTable.get(i++).text());
      setGrowthTime(infoboxTable.get(i++).text());
      setSeason(infoboxTable.get(i++).text());
      setHealing(infoboxTable.get(i++).text());
      setBasePrice(infoboxTable.get(i++).text());
      setTillerPrice(infoboxTable.get(i++).text());
      setArtisanPrice(infoboxTable.get(i++).text());
      setProArtisanPrice(infoboxTable.get(i++).text());
   }

   // Gets unique infobox images
   public boolean getImageUrls(Elements elements, Element images) {
      Elements infoboxTable = elements.get(0).getElementsByAttributeValue("id", "infoboxdetail");
      Elements imagesItem = images.getElementsByTag("img");
      int i = 0;
      setTitleImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      setEnergyImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      setHealthImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      setSilverImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      setGoldImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      setKegImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      setIridiumImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i++));
      setJarImageUrl(getInfoBoxItemUrl(infoboxTable, imagesItem, i));
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

   public static String getStardewUrl(String url) {
      return "http://stardewvalleywiki.com" + url;
   }

   public String toString() {
      return "";
//      return '\n' + getDescription() +
//            '\n' + getSeeds() +
//            '\n' + getGrowthTime() +
//            '\n' + getSeason() +
//            '\n' + getHealing() +
//            '\n' + getBasePrice() +
//            '\n' + getTillerPrice() +
//            '\n' + getArtisanPrice() +
//            '\n' + getProArtisanPrice() +
//            '\n' + getTitleImageUrl() +
//            '\n' + getEnergyImageUrl() +
//            '\n' + getHealthImageUrl() +
//            '\n' + getSilverImageUrl() +
//            '\n' + getGoldImageUrl() +
//            '\n' + getKegImageUrl() +
//            '\n' + getIridiumImageUrl() +
//            '\n' + getJarImageUrl();
   }
}
