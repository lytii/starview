package com.example.longlam.starview;

import android.os.AsyncTask;

import com.example.longlam.starview.util.ParseHtml;

import java.io.IOException;

import static com.example.longlam.starview.util.ParseHtml.getDrawableFromURL;

public class CropPresenter {

   CropActivity cropActivity;
   CropInfo cropInfo;
   CropImage cropImage;
   private boolean isProArtisan = false;
   private boolean isProTiller = false;


   public CropPresenter(CropActivity cropActivity) {
      this.cropActivity = cropActivity;
      cropInfo = new CropInfo();
      cropImage = new CropImage();
//      requestJson("starfruit");
   }

   public CropPresenter getPresenter() {
      return this;
   }

   private void setCropInfo() {
      cropActivity.setCropTitleTextView(cropInfo.getCropTitle(), cropImage.getCropImage());
      cropActivity.setDescriptionTextView(cropInfo.getDescription());
      cropActivity.setSeedsInfoTextView(cropInfo.getSeeds(), cropImage.getSeedDrawable());
      cropActivity.setGrowthInfoTextView(cropInfo.getGrowthTime());
      cropActivity.setSeasonsInfoTextView(cropInfo.getSeason());
   }

   private void setHealingEffects() {
//      System.out.println(cropInfo);
//      System.out.println(cropImage);
      cropActivity.setHealthTextView(cropInfo.getHealth(), cropImage.getHealthDrawable());
      cropActivity.setSilverHealthTextView(cropInfo.getSilverHealth(), cropImage.getSilverHealthDrawable());
      cropActivity.setGoldHealthTextView(cropInfo.getGoldHealth(), cropImage.getGoldHealthDrawable());
      cropActivity.setEnergyTextView(cropInfo.getEnergy(), cropImage.getEnergyDrawable());
      cropActivity.setSilverEnergyTextView(cropInfo.getSilverEnergy(), cropImage.getSilverEnergyDrawable());
      cropActivity.setGoldEnergyTextView(cropInfo.getGoldEnergy(), cropImage.getGoldEnergyDrawable());
   }

   public void toggleTillerPrices() {
      isProTiller = !isProTiller;
      setSellingPrices();
   }

   private void setSellingPrices() {
      String[] basePrices;
      if (isProTiller) {
         basePrices = cropInfo.getSellingSkillPrices();
         cropActivity.setSellingPricesHeader(R.string.selling_skill_price_header);
      } else {
         basePrices = cropInfo.getSellingBasePrices();
         cropActivity.setSellingPricesHeader(R.string.selling_base_price_header);

      }
      cropActivity.setSellingPriceTextView(basePrices[1], cropImage.getCropImage());
      cropActivity.setSilverSellingPriceTextView(basePrices[2], cropImage.getSilverCropImage());
      cropActivity.setGoldSellingPriceTextView(basePrices[3], cropImage.getGoldCropImage());
   }

   public void toggleArtisanPrices() {
      isProArtisan = !isProArtisan;
      setArtisanPrices();
   }

   public void setArtisanPrices() {
      String[] artisanPrices;
      if (isProArtisan) {
         artisanPrices = cropInfo.getArtisanSkillPrice();
         cropActivity.setArtisanPriceHeader(R.string.artisan_skill_price_header);
      } else {
         artisanPrices = cropInfo.getArtisanBasePrices();
         cropActivity.setArtisanPriceHeader(R.string.artisan_base_price_header);
      }
      if(artisanPrices == null) {
         return;
      }
      cropActivity.setViewTextImage(artisanPrices[1], cropImage.getKegDrawable());
      cropActivity.setSilverArtisanPriceTextView(artisanPrices[2], cropImage.getSilverKegDrawable());
      cropActivity.setGoldArtisanPriceTextView(artisanPrices[3], cropImage.getGoldKegDrawable());
      cropActivity.setIridiumArtisanPriceTextView(artisanPrices[4], cropImage.getIridiumKegDrawable());
      cropActivity.setJarPriceTextView(artisanPrices[5], cropImage.getJarDrawable());
   }

   protected void requestJson(final String cropName) {
      new AsyncTask<Void, Void, Void>() {
         String text;

         @Override
         protected Void doInBackground(Void... voids) {
            try {
               parseHtml(cropName);
            } catch (Exception e) {
               e.printStackTrace();
            }
            return null;
         }

         @Override
         protected void onPostExecute(Void aVoid) {
            setCropInfo();
            setSellingPrices();
            setArtisanPrices();
            setHealingEffects();
         }
      }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
   }

   // set cropInfo after parsing HTML
   private void parseHtml(String cropName) throws IOException {
      cropInfo = new CropInfo();
      cropImage = new CropImage();

      ParseHtml htmlParser = new ParseHtml();
      htmlParser.gettingHtmlForCrop(cropName);
      cropInfo = htmlParser.getCropInfo();
      cropImage = htmlParser.getImageUrls();

      if (cropInfo == null) {
         return;
      }
      cropImage.setCropImage(getDrawableFromURL(cropImage.getCropImageUrl()));
      // TODO get seed image from seed url
      String seedImg = "http://stardewvalleywiki.com/mediawiki/images/e/e0/Starfruit_Seeds.png";
      cropImage.setSeedImageUrl(seedImg);
      cropImage.setSeedDrawable(getDrawableFromURL(cropImage.getSeedImageUrl()));
      // TODO these can be reused once getting different crop info is available
      cropImage.setEnergyDrawable(getDrawableFromURL(cropImage.getEnergyImageUrl()));
      cropImage.setHealthDrawable(getDrawableFromURL(cropImage.getHealthImageUrl()));
      cropImage.setSilverDrawable(getDrawableFromURL(cropImage.getSilverImageUrl()));
      cropImage.setGoldDrawable(getDrawableFromURL(cropImage.getGoldImageUrl()));
      cropImage.setKegDrawable(getDrawableFromURL(cropImage.getKegImageUrl()));
      cropImage.setIridiumDrawable(getDrawableFromURL(cropImage.getIridiumImageUrl()));
      cropImage.setJarDrawable(getDrawableFromURL(cropImage.getJarImageUrl()));
      System.out.println(cropImage);
   }
}