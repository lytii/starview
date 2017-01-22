package com.example.longlam.starview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CropActivity extends AppCompatActivity {

   @BindView(R.id.cropTitle)
   TextView cropTitleTextView;
   @BindView(R.id.description)
   TextView descriptionTextView;

   @BindView(R.id.info_linear_layout)
   LinearLayout infoLinearLayout;

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

   //Info Views: seeds, growth time, seasons
   @BindView(R.id.seeds_info)
   TextView seedsInfoTextView;
   @BindView(R.id.growth_info)
   TextView growthInfoTextView;
   @BindView(R.id.seasons_info)
   TextView seasonsInfoTextView;

   // Selling Price Views
   @BindView(R.id.tillerprice_header_text_view)
   TextView sellingPricesHeader;
   @BindView(R.id.baseprice_text_view)
   TextView basepriceTextView;
   @BindView(R.id.silver_baseprice_text_view)
   TextView silverBasepriceTextView;
   @BindView(R.id.gold_baseprice_text_view)
   TextView goldBasepriceTextView;

   // Artisan Price Views
   @BindView((R.id.artisanprice_header_text_view))
   TextView artisanPriceHeaderTextView;
   @BindView(R.id.artisanprice_text_view)
   TextView artisanBaseTextView;
   @BindView(R.id.silver_artisanprice_text_view)
   TextView silverArtisanPriceTextView;
   @BindView(R.id.gold_artisanprice_text_view)
   TextView goldArtisanPriceTextView;
   @BindView(R.id.iridium_artisanprice_text_view)
   TextView iridiumArtisanPriceTextView;
   @BindView(R.id.jar_baseprice)
   TextView jarPriceTextView;

   CropInfo crop;

   CropPresenter cropPresenter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      cropPresenter = new CropPresenter(this);
      cropPresenter.requestJson("tulip");
      ButterKnife.bind(this);
   }

   @Override
   protected void onResume() {
      super.onResume();
   }

   protected void setCropTitleTextView(String text, Drawable drawable) {
      setViewTextImage(cropTitleTextView, text, drawable);
   }

   protected void setDescriptionTextView(String text) {
      descriptionTextView.setText(text);
   }

   protected void setSeedsInfoTextView(String text, Drawable drawable) {
      setViewTextImage(seedsInfoTextView, text, drawable);
   }

   protected void setGrowthInfoTextView(String text) {
      growthInfoTextView.setText(text);
   }

   protected void setSeasonsInfoTextView(String text) {
      seasonsInfoTextView.setText(text);
   }

   protected void setHealthTextView(String text, Drawable drawable) {
      setViewTextImage(healthTextView, text, drawable);
   }

   protected void setGoldHealthTextView(String text, Drawable drawable) {
      setViewTextImage(goldHealthTextView, text, drawable);
   }

   protected void setSilverHealthTextView(String text, Drawable drawable) {
      setViewTextImage(silverHealthTextView, text, drawable);
   }

   protected void setEnergyTextView(String text, Drawable drawable) {
      setViewTextImage(energyTextView, text, drawable);
   }

   protected void setGoldEnergyTextView(String text, Drawable drawable) {
      setViewTextImage(goldEnergyTextView, text, drawable);
   }

   protected void setSilverEnergyTextView(String text, Drawable drawable) {
      setViewTextImage(silverEnergyTextView, text, drawable);
   }

   @OnClick(R.id.selling_prices_section)
   protected void toggleTillerPrices() {
      cropPresenter.toggleTillerPrices();
   }

   protected void setSellingPricesHeader(int stringId) {
      sellingPricesHeader.setText(stringId);
   }

   protected void setSellingPriceTextView(String text, Drawable drawable) {
      setViewTextImage(basepriceTextView, text, drawable);
   }

   protected void setSilverSellingPriceTextView(String text, Drawable drawable) {
      setViewTextImage(silverBasepriceTextView, text, drawable);
   }

   protected void setGoldSellingPriceTextView(String text, Drawable drawable) {
      setViewTextImage(goldBasepriceTextView, text, drawable);
   }

   @OnClick(R.id.artisan_prices_section)
   protected void toggleArtisanPrice() {
      cropPresenter.toggleArtisanPrices();
   }

   protected void setArtisanPriceHeader(int stringId) {
      artisanPriceHeaderTextView.setText(stringId);
   }

   protected void setViewTextImage(String text, Drawable drawable) {
      setViewTextImage(artisanBaseTextView, text, drawable);
   }

   protected void setSilverArtisanPriceTextView(String text, Drawable drawable) {
      setViewTextImage(silverArtisanPriceTextView, text, drawable);
   }

   protected void setGoldArtisanPriceTextView(String text, Drawable drawable) {
      setViewTextImage(goldArtisanPriceTextView, text, drawable);
   }

   protected void setIridiumArtisanPriceTextView(String text, Drawable drawable) {
      setViewTextImage(iridiumArtisanPriceTextView, text, drawable);
   }

   protected void setJarPriceTextView(String text, Drawable drawable) {
      setViewTextImage(jarPriceTextView, text, drawable);
   }

   // Sets text and image to left of text
   private void setViewTextImage(TextView textView, String text, Drawable drawable) {
      textView.setText(text);
      textView.setCompoundDrawables(drawable, null, null, null);
   }
}