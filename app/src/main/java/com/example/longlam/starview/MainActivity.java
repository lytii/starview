package com.example.longlam.starview;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
   @BindView(R.id.selling_baseprice_header)
   TextView selling_baseprice_header;
   @BindView(R.id.baseprice_text_view)
   TextView basepriceTextView;
   @BindView(R.id.silver_baseprice_text_view)
   TextView silverBasepriceTextView;
   @BindView(R.id.gold_baseprice_text_view)
   TextView goldBasepriceTextView;

   // Artisan Price Views
   @BindView((R.id.artisan_baseprice_header))
   TextView artisanPriceHeaderButton;
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
//
//   @BindView(R.id.proprice_text_view)
//   TextView propriceTextView;
//   @BindView(R.id.silver_proprice_text_view)
//   TextView silverPropriceTextView;
//   @BindView(R.id.gold_proprice_text_view)
//   TextView goldPropriceTextView;
//   @BindView(R.id.iridium_proprice_text_view)
//   TextView iridiumPropriceTextView;
//   @BindView(R.id.jar_proprice)
//   TextView jarPropriceTextView;

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

   boolean isProArtisan = false;
   boolean isProTiller = false;

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

   @OnClick(R.id.tillerprice_header_text_view)
   public void toggleTillerPrices() {
      isProTiller = !isProTiller;
      setSellingPrices();
   }

   private void setSellingPrices() {
      String[] basePrices;
      if(isProTiller) {
         basePrices = crop.getTillerPrices();
         selling_baseprice_header.setText(R.string.selling_pro_price);
      } else {
         basePrices = crop.getBasePrices();
         selling_baseprice_header.setText(R.string.selling_base_price);

      }
      basepriceTextView.setText(basePrices[1]);
      basepriceTextView.setCompoundDrawables(cropImage, null, null, null);

      silverBasepriceTextView.setText(basePrices[2]);
      silverBasepriceTextView.setCompoundDrawables(getLayeredDrawable(cropImage, silverDrawable), null, null, null);

      goldBasepriceTextView.setText(basePrices[3]);
      goldBasepriceTextView.setCompoundDrawables(getLayeredDrawable(cropImage, goldDrawable), null, null, null);
   }

   @OnClick(R.id.artisanprice_header_text_view)
   public void toggleArtisanPrice() {
      isProArtisan = !isProArtisan;
      setArtisanPrices();
   }


   private void setArtisanPrices() {
      String[] artisanPrices;
      if(isProArtisan) {
         artisanPrices = crop.getProArtisanPrice();
         artisanPriceHeaderButton.setText(R.string.pro_artisan_price_header);

      } else {
         artisanPrices = crop.getArtisanPrices();
         artisanPriceHeaderButton.setText(R.string.base_artisan_price_header);
      }
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
   }
   private Drawable getLayeredDrawable(Drawable main, Drawable quality) {
      LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{main, quality});
      layerDrawable.setBounds(0, 0, layerDrawable.getIntrinsicWidth() * 10, layerDrawable.getIntrinsicHeight() * 10);
      return layerDrawable;
   }
}