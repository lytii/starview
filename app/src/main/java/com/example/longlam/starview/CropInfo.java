package com.example.longlam.starview;


import android.graphics.drawable.Drawable;

import org.jsoup.select.Elements;

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
   private String[] sellingSasePrices;
   private String[] sellingSkillPrices;
   private String[] artisanBasePrices;
   private String[] artisanSkillPrice;
   private String titleImageUrl;
   private String seedImageUrl;
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

   public void setCropImage(Drawable titleImage) {
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

   public String getCropImageUrl() {
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

   public String[] getSellingSasePrices() {
      return sellingSasePrices;
   }

   public void setBasePrice(String basePrice) {
      sellingSasePrices = basePrice.split(" ");
   }

   public String[] getSellingSkillPrices() {
      return sellingSkillPrices;
   }

   public void setSellingSkillPrices(String sellingSkillPrices) {
      this.sellingSkillPrices = sellingSkillPrices.split(" ");
   }

   public String[] getArtisanBasePrices() {
      return artisanBasePrices;
   }

   public void setArtisanBasePrices(String artisanBasePrices) {
      this.artisanBasePrices = artisanBasePrices.split(" ");
   }

   public String[] getArtisanSkillPrice() {
      return artisanSkillPrice;
   }

   public void setArtisanSkillPrice(String proArtisanPrices) {
      this.artisanSkillPrice = proArtisanPrices.split(" ");
   }

   public String getGrowthTime() {
      return growthTime;
   }

   public void setGrowthTime(String growthTime) {
      this.growthTime = growthTime;
   }

   public String toString() {
//      return "";
      return '\n' + getDescription() +
            '\n' + getSeeds() +
            '\n' + getGrowthTime() +
            '\n' + getSeason() +
            '\n' + getSellingSasePrices() +
            '\n' + getSellingSkillPrices() +
            '\n' + getArtisanBasePrices() +
            '\n' + getArtisanSkillPrice() +
            '\n' + getCropImageUrl() +
            '\n' + getEnergyImageUrl() +
            '\n' + getHealthImageUrl() +
            '\n' + getSilverImageUrl() +
            '\n' + getGoldImageUrl() +
            '\n' + getKegImageUrl() +
            '\n' + getIridiumImageUrl() +
            '\n' + getJarImageUrl();
   }

   public String getSeedImageUrl() {
      return seedImageUrl;
   }

   public void setSeedImageUrl(String seedImageUrl) {
      this.seedImageUrl = seedImageUrl;
   }
}
