package com.example.longlam.starview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.longlam.starview.util.parseHtml.getDescription;
import static com.example.longlam.starview.util.parseHtml.getInfoTitle;
import static com.example.longlam.starview.util.parseHtml.getInfoTitleImageURL;

public class MainActivity extends AppCompatActivity {

   final String TAG = this.getClass().getSimpleName();
   @BindView(R.id.title)
   TextView titleTextView;

   @BindView(R.id.description)
   TextView descriptionTextView;

   String title;
   Bitmap header = null;
   CropInfo crop;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      crop = new CropInfo();
      ButterKnife.bind(this);
      requestJson();
   }

   @Override
   protected void onResume() {
      super.onResume();
      titleTextView.refreshDrawableState();
   }

   private void parseHtml(String cropName) throws IOException {
      Document doc = Jsoup.connect("http://stardewvalleywiki.com/" + cropName).get();
      Elements newsHeadlines = doc.getAllElements();

      crop.setTitle(getInfoTitle(newsHeadlines));
      URL url = new URL(getInfoTitleImageURL(newsHeadlines));
      header = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//      crop.setTitleImage(new BitmapDrawable(header));
      crop.setDescription(getDescription(newsHeadlines));
   }

   private void requestJson() {
      new AsyncTask<Void, Void, Void>() {
         String text;

         @Override
         protected Void doInBackground(Void... voids) {
            try {
//               parseHtml("Cauliflower");
               parseHtml("Blueberry");
            } catch (Exception e) {
               e.printStackTrace();
            }
            return null;
         }

         @Override
         protected void onPostExecute(Void aVoid) {
            Log.d(TAG, "doInBackground: post");
            Log.d(TAG, "onPostExecute: ");
//            Drawable d = crop.getTitleImage();
//            d.setBounds(0, 0, d.getIntrinsicWidth() * 10, d.getIntrinsicHeight() * 10);
            titleTextView.setText(crop.getTitle());
//            titleTextView.setCompoundDrawables(null, null, null, d);
            descriptionTextView.setText(crop.getDescription());
         }
      }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
   }
}