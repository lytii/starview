package com.example.longlam.starview;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

public class CropImage {

   private Drawable cropImage;
   private Drawable silverCropImage;
   private Drawable goldCropImage;
   private Drawable seedDrawable;
   private Drawable energyDrawable;
   private Drawable silverEnergyDrawable;
   private Drawable goldEnergyDrawable;
   private Drawable healthDrawable;
   private Drawable silverHealthDrawable;
   private Drawable goldHealthDrawable;

   private Drawable silverDrawable;
   private Drawable goldDrawable;
   private Drawable kegDrawable;
   private Drawable iridiumDrawable;
   private Drawable jarDrawable;

   private Drawable silverKegDrawable;
   private Drawable goldKegDrawable;
   private Drawable iridiumKegDrawable;

   private String titleImageUrl;
   private String seedImageUrl;
   private String energyImageUrl;
   private String healthImageUrl;
   private String silverImageUrl;
   private String goldImageUrl;
   private String kegImageUrl;
   private String iridiumImageUrl;
   private String jarImageUrl;


   public String getSeedImageUrl() {
      return seedImageUrl;
   }

   public void setSeedImageUrl(String seedImageUrl) {
      this.seedImageUrl = seedImageUrl;
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

   public Drawable getSeedDrawable() {
      return seedDrawable;
   }

   public void setSeedDrawable(Drawable seedDrawable) {
      this.seedDrawable = seedDrawable;
   }

   public Drawable getEnergyDrawable() {
      return energyDrawable;
   }

   public Drawable getSilverEnergyDrawable() {
      if (silverEnergyDrawable == null) {
         silverEnergyDrawable = getLayeredDrawable(energyDrawable, silverDrawable);
      }
      return silverEnergyDrawable;
   }

   public Drawable getGoldEnergyDrawable() {
      if (goldEnergyDrawable == null) {
         goldEnergyDrawable = getLayeredDrawable(energyDrawable, goldDrawable);
      }
      return goldEnergyDrawable;
   }

   public void setEnergyDrawable(Drawable energyDrawable) {
      this.energyDrawable = energyDrawable;
   }

   public Drawable getHealthDrawable() {
      return healthDrawable;
   }

   public Drawable getSilverHealthDrawable() {
      if (silverHealthDrawable == null) {
         silverHealthDrawable = getLayeredDrawable(healthDrawable, silverDrawable);
      }
      return silverHealthDrawable;
   }

   public Drawable getGoldHealthDrawable() {
      if (goldHealthDrawable == null) {
         goldHealthDrawable = getLayeredDrawable(healthDrawable, goldDrawable);
      }
      return goldHealthDrawable;
   }

   public void setHealthDrawable(Drawable healthDrawable) {
      this.healthDrawable = healthDrawable;
   }

   public void setSilverDrawable(Drawable silverDrawable) {
      this.silverDrawable = silverDrawable;
   }

   public void setGoldDrawable(Drawable goldDrawable) {
      this.goldDrawable = goldDrawable;
   }

   public Drawable getKegDrawable() {
      return kegDrawable;
   }

   public void setKegDrawable(Drawable kegDrawable) {
      this.kegDrawable = kegDrawable;
   }

   protected Drawable getSilverKegDrawable() {
      if (silverKegDrawable == null) {
         silverKegDrawable = getLayeredDrawable(kegDrawable, silverDrawable);
      }
      return silverKegDrawable;
   }

   protected Drawable getGoldKegDrawable() {
      if (goldKegDrawable == null) {
         goldKegDrawable = getLayeredDrawable(kegDrawable, goldDrawable);
      }
      return goldKegDrawable;
   }

   protected Drawable getIridiumKegDrawable() {
      if (iridiumKegDrawable == null) {
         iridiumKegDrawable = getLayeredDrawable(kegDrawable, iridiumDrawable);
      }
      return iridiumKegDrawable;
   }

   public void setIridiumDrawable(Drawable iridiumDrawable) {
      this.iridiumDrawable = iridiumDrawable;
   }

   public Drawable getJarDrawable() {
      return jarDrawable;
   }

   public void setJarDrawable(Drawable jarDrawable) {
      this.jarDrawable = jarDrawable;
   }

   public Drawable getCropImage() {
      return cropImage;
   }

   public Drawable getSilverCropImage() {
      if (silverCropImage == null) {
         silverCropImage = getLayeredDrawable(cropImage, silverDrawable);
      }
      return silverCropImage;
   }

   public Drawable getGoldCropImage() {
      if (goldCropImage == null) {
         goldCropImage = getLayeredDrawable(cropImage, goldDrawable);
      }
      return goldCropImage;
   }

   public void setCropImage(Drawable cropImage) {
      this.cropImage = cropImage;
   }

   private Drawable getLayeredDrawable(Drawable main, Drawable quality) {
      LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{main, quality});
      layerDrawable.setBounds(0, 0, layerDrawable.getIntrinsicWidth() * 10, layerDrawable.getIntrinsicHeight() * 10);
      return layerDrawable;
   }

   public String toString() {
      return getCropImageUrl() +
            '\n' + getEnergyImageUrl() +
            '\n' + getHealthImageUrl() +
            '\n' + getSilverImageUrl() +
            '\n' + getGoldImageUrl() +
            '\n' + getKegImageUrl() +
            '\n' + getIridiumImageUrl() +
            '\n' + getJarImageUrl();
   }
}
