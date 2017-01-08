package com.example.longlam.starview;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
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


   String title;
   Drawable titleDrawable  = null;
   Drawable energyDrawable  = null;
   Drawable healthDrawable  = null;
   Drawable silverDrawable  = null;
   Drawable goldDrawable  = null;
   Drawable kegDrawable  = null;
   Drawable iridiumDrawable  = null;
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
      if(crop == null) {
         return;
      }
      titleDrawable = getDrawableFromURL(crop.getTitleImageUrl());
      energyDrawable = getDrawableFromURL(crop.getEnergyImageUrl());
      healthDrawable = getDrawableFromURL(crop.getHealthImageUrl());
      silverDrawable = getDrawableFromURL(crop.getSilverImageUrl());
      goldDrawable = getDrawableFromURL(crop.getGoldImageUrl());
      kegDrawable = getDrawableFromURL(crop.getKegImageUrl());
      iridiumDrawable = getDrawableFromURL(crop.getIridiumImageUrl());
      crop.setTitleImage(titleDrawable);
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
            Log.d(TAG, "doInBackground: "+ crop);
            titleTextView.setText(crop.getTitle());
            titleTextView.setCompoundDrawables(null, null, null, titleDrawable);
            descriptionTextView.setText(crop.getDescription());

            healthTextView.setText(crop.getHealth());
            healthTextView.setCompoundDrawables(healthDrawable, null, null, null);

            silverHealthTextView.setText(crop.getSilverHealth());
            LayerDrawable silverHPDrawable = new LayerDrawable(new Drawable[]{healthDrawable, silverDrawable});
            silverHPDrawable.setBounds(0, 0, silverHPDrawable.getIntrinsicWidth() * 10, silverHPDrawable.getIntrinsicHeight() * 10);
            silverHealthTextView.setCompoundDrawables(silverHPDrawable, null, null, null);

            goldHealthTextView.setText(crop.getGoldHealth());
            LayerDrawable goldHPDrawable = new LayerDrawable(new Drawable[]{healthDrawable, goldDrawable});
            goldHPDrawable.setBounds(0, 0, goldHPDrawable.getIntrinsicWidth() * 10, goldHPDrawable.getIntrinsicHeight() * 10);
            goldHealthTextView.setCompoundDrawables(goldHPDrawable, null, null, null);


            energyTextView.setText(crop.getEnergy());
            energyTextView.setCompoundDrawables(energyDrawable, null, null, null);

            silverEnergyTextView.setText(crop.getSilverEnergy());
            LayerDrawable silverEPDrawable = new LayerDrawable(new Drawable[]{energyDrawable, silverDrawable});
            silverEPDrawable.setBounds(0, 0, silverEPDrawable.getIntrinsicWidth() * 10, silverEPDrawable.getIntrinsicHeight() * 10);
            silverEnergyTextView.setCompoundDrawables(silverEPDrawable, null, null, null);

            goldEnergyTextView.setText(crop.getGoldEnergy());
            LayerDrawable goldEPDrawable = new LayerDrawable(new Drawable[]{energyDrawable, goldDrawable});
            goldEPDrawable.setBounds(0, 0, goldEPDrawable.getIntrinsicWidth() * 10, goldEPDrawable.getIntrinsicHeight() * 10);
            goldEnergyTextView.setCompoundDrawables(goldEPDrawable, null, null, null);
         }
      }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
   }
}