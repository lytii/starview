package com.example.longlam.starview;

public class CropInfo {
   private String cropTitle;
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

   public String getCropTitle() {
      return cropTitle;
   }

   public void setCropTitle(String cropTitle) {
      this.cropTitle = cropTitle;
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

   public String[] getSellingBasePrices() {
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
            '\n' + getSellingBasePrices() +
            '\n' + getSellingSkillPrices() +
            '\n' + getArtisanBasePrices() +
            '\n' + getArtisanSkillPrice();
   }
}
