package com.example.longlam.starview;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.longlam.starview.util.parseHtml.getDrawableFromURL;
import static com.example.longlam.starview.util.parseHtml.gettingHtml;

public class MainActivity extends AppCompatActivity {

   final String TAG = this.getClass().getSimpleName();
   @BindView(R.id.title)
   TextView titleTextView;
   @BindView(R.id.description)
   TextView descriptionTextView;
   // Healing Effect Views
   @BindView(R.id.health_text_view)
   TextView healthTextView;
   @BindView(R.id.energy_text_view)
   TextView energyTextView;
   @BindView(R.id.silver_health_text_view)
   TextView silverHealthTextView;
   @BindView(R.id.silver_energy_text_view)
   TextView silverEnergyTextView;
   @BindView(R.id.gold_health_text_view)
   TextView goldHealthTextView;
   @BindView(R.id.gold_energy_text_view)
   TextView goldEnergyTextView;

   // Selling Price Views
   @BindView(R.id.baseprice_text_view)
   TextView basepriceTextView;
   @BindView(R.id.silver_baseprice_text_view)
   TextView silverBasepriceTextView;
   @BindView(R.id.gold_baseprice_text_view)
   TextView goldBasepriceTextView;

   @BindView(R.id.tillerprice_text_view)
   TextView tillerpriceTextView;
   @BindView(R.id.silver_tillerprice_text_view)
   TextView silverTillerpriceTextView;
   @BindView(R.id.gold_tillerprice_text_view)
   TextView goldTillerpriceTextView;

   // Artisan Price Views
   @BindView(R.id.artisanprice_text_view)
   TextView artisanpriceTextView;
   @BindView(R.id.silver_artisanprice_text_view)
   TextView silverArtisanpriceTextView;
   @BindView(R.id.gold_artisanprice_text_view)
   TextView goldArtisanpriceTextView;
   @BindView(R.id.iridium_artisanprice_text_view)
   TextView iridiumArtisanpriceTextView;
   @BindView(R.id.jar_baseprice)
   TextView jarBasepriceTextView;

   @BindView(R.id.proprice_text_view)
   TextView propriceTextView;
   @BindView(R.id.silver_proprice_text_view)
   TextView silverPropriceTextView;
   @BindView(R.id.gold_proprice_text_view)
   TextView goldPropriceTextView;
   @BindView(R.id.iridium_proprice_text_view)
   TextView iridiumPropriceTextView;
   @BindView(R.id.jar_proprice)
   TextView jarPropriceTextView;

   String title;
   Drawable cropImage = null;
   Drawable energyDrawable = null;
   Drawable healthDrawable = null;
   Drawable silverDrawable = null;
   Drawable goldDrawable = null;
   Drawable kegDrawable = null;
   Drawable iridiumDrawable = null;
   Drawable jarDrawable = null;
   CropInfo crop;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      crop = new CropInfo();
      ButterKnife.bind(this);
   }

   @Override
   protected void onResume() {
      super.onResume();
      titleTextView.refreshDrawableState();
      requestJson();

   }

   // set cropInfo after parsing HTML
   private void parseHtml(String cropName) throws IOException {
      crop = gettingHtml(cropName);
      if (crop == null) {
         return;
      }
      cropImage = getDrawableFromURL(crop.getCropImageUrl());
      energyDrawable = getDrawableFromURL(crop.getEnergyImageUrl());
      healthDrawable = getDrawableFromURL(crop.getHealthImageUrl());
      silverDrawable = getDrawableFromURL(crop.getSilverImageUrl());
      goldDrawable = getDrawableFromURL(crop.getGoldImageUrl());
      kegDrawable = getDrawableFromURL(crop.getKegImageUrl());
      iridiumDrawable = getDrawableFromURL(crop.getIridiumImageUrl());
      jarDrawable = getDrawableFromURL(crop.getJarImageUrl());
      crop.setCropImage(cropImage);
   }

   private void requestJson() {
      new AsyncTask<Void, Void, Void>() {
         String text;

         @Override
         protected Void doInBackground(Void... voids) {
            try {
//               parseHtml("blueberry");
               parseHtml("cauliflower");
            } catch (Exception e) {
               e.printStackTrace();
            }
            return null;
         }

         @Override
         protected void onPostExecute(Void aVoid) {
            Log.d(TAG, "doInBackground: " + crop);
            titleTextView.setText(crop.getTitle());
            titleTextView.setCompoundDrawables(null, null, null, cropImage);
            descriptionTextView.setText(crop.getDescription());
            setHealingEffects();
            setSellingPrices();
            setArtisanPrices();
         }
      }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
   }

   private void setHealingEffects() {
      healthTextView.setText(crop.getHealth());
      healthTextView.setCompoundDrawables(healthDrawable, null, null, null);

      silverHealthTextView.setText(crop.getSilverHealth());
      silverHealthTextView.setCompoundDrawables(
            getLayeredDrawable(healthDrawable, silverDrawable), null, null, null);

      goldHealthTextView.setText(crop.getGoldHealth());
      goldHealthTextView.setCompoundDrawables(getLayeredDrawable(healthDrawable, goldDrawable), null, null, null);

      energyTextView.setText(crop.getEnergy());
      energyTextView.setCompoundDrawables(energyDrawable, null, null, null);

      silverEnergyTextView.setText(crop.getSilverEnergy());
      silverEnergyTextView.setCompoundDrawables(getLayeredDrawable(energyDrawable, silverDrawable), null, null, null);

      goldEnergyTextView.setText(crop.getGoldEnergy());
      goldEnergyTextView.setCompoundDrawables(getLayeredDrawable(energyDrawable, goldDrawable), null, null, null);
   }

   private void setSellingPrices() {
      String[] basePrices = crop.getBasePrices();
      basepriceTextView.setText(basePrices[1]);
      basepriceTextView.setCompoundDrawables(cropImage, null, null, null);

      silverBasepriceTextView.setText(basePrices[2]);
      silverBasepriceTextView.setCompoundDrawables(getLayeredDrawable(cropImage, silverDrawable), null, null, null);

      goldBasepriceTextView.setText(basePrices[3]);
      goldBasepriceTextView.setCompoundDrawables(getLayeredDrawable(cropImage, goldDrawable), null, null, null);

      String[] tillerPrices = crop.getTillerPrices();
      tillerpriceTextView.setText(tillerPrices[1]);
      tillerpriceTextView.setCompoundDrawables(cropImage, null, null, null);

      silverTillerpriceTextView.setText(tillerPrices[2]);
      silverTillerpriceTextView.setCompoundDrawables(getLayeredDrawable(cropImage, silverDrawable), null, null, null);

      goldTillerpriceTextView.setText(tillerPrices[3]);
      goldTillerpriceTextView.setCompoundDrawables(getLayeredDrawable(cropImage, goldDrawable), null, null, null);
   }

   private void setArtisanPrices() {
      String[] artisanPrices = crop.getArtisanPrices();
      artisanpriceTextView.setText(artisanPrices[1]);
      artisanpriceTextView.setCompoundDrawables(kegDrawable, null, null, null);

      silverArtisanpriceTextView.setText(artisanPrices[2]);
      silverArtisanpriceTextView.setCompoundDrawables(getLayeredDrawable(kegDrawable, silverDrawable), null, null, null);

      goldArtisanpriceTextView.setText(artisanPrices[3]);
      goldArtisanpriceTextView.setCompoundDrawables(getLayeredDrawable(kegDrawable, goldDrawable), null, null, null);

      iridiumArtisanpriceTextView.setText(artisanPrices[4]);
      iridiumArtisanpriceTextView.setCompoundDrawables(getLayeredDrawable(kegDrawable, iridiumDrawable), null, null, null);

      jarBasepriceTextView.setText(artisanPrices[5]);
      jarBasepriceTextView.setCompoundDrawables(jarDrawable, null, null, null);

      String[] proArtisanPrices = crop.getProArtisanPrice();
      propriceTextView.setText(proArtisanPrices[1]);
      propriceTextView.setCompoundDrawables(kegDrawable, null, null, null);

      silverPropriceTextView.setText(proArtisanPrices[2]);
      silverPropriceTextView.setCompoundDrawables(getLayeredDrawable(kegDrawable, silverDrawable), null, null, null);

      goldPropriceTextView.setText(proArtisanPrices[3]);
      goldPropriceTextView.setCompoundDrawables(getLayeredDrawable(kegDrawable, goldDrawable), null, null, null);

      iridiumPropriceTextView.setText(proArtisanPrices[4]);
      iridiumPropriceTextView.setCompoundDrawables(getLayeredDrawable(kegDrawable, iridiumDrawable), null, null, null);

      jarPropriceTextView.setText(proArtisanPrices[5]);
      jarPropriceTextView.setCompoundDrawables(jarDrawable, null, null, null);
   }
   private Drawable getLayeredDrawable(Drawable main, Drawable quality) {
      LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{main, quality});
      layerDrawable.setBounds(0, 0, layerDrawable.getIntrinsicWidth() * 10, layerDrawable.getIntrinsicHeight() * 10);
      return layerDrawable;
   }
}