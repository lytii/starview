package com.example.longlam.starview;


import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Arrays;

public class CropInfo {
   private String title;
   private String description;
   private String seeds;
   private String growthTime;
   private String season;
   private String healing;
   private String basePrice;
   private String tillerPrice;
   private String artisanPrice;
   private String proArtisanPrice;
   private String titleImageUrl;
   private String energyImageUrl;
   private String healthImageUrl;
   private String silverImageUrl;
   private String goldQualityImageUrl;
   private String kegImageUrl;
   private String iridiumImageUrl;
   private String jarImageUrl;

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

   public String getGoldQualityImageUrl() {
      return goldQualityImageUrl;
   }

   public void setGoldQualityImageUrl(String goldQualityImageUrl) {
      this.goldQualityImageUrl = goldQualityImageUrl;
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

   public ArrayList<String> ref = new ArrayList<>(Arrays.asList(description, seeds, growthTime, season, healing, basePrice, tillerPrice, artisanPrice, proArtisanPrice));

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getTitleImage() {
      return titleImageUrl;
   }

   public void setTitleImage(String titleImageUrl) {
      this.titleImageUrl = titleImageUrl;
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

   public String getHealing() {
      return healing;
   }

   public void setHealing(String healing) {
      this.healing = healing;
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
}
