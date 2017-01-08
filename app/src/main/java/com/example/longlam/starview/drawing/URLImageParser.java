package com.example.longlam.starview.drawing;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLImageParser implements Html.ImageGetter {
   View container;

   /***
    * Construct the URLImageParser which will execute AsyncTask and refresh the container
    * @param t
    */
   public URLImageParser(View t) {
      this.container = t;
   }

   public Drawable getDrawable(String source) {
      URLDrawable urlDrawable = new URLDrawable();

      // get the actual source
      ImageGetterAsyncTask asyncTask =
            new ImageGetterAsyncTask( urlDrawable);

      asyncTask.execute(source);

      // return reference to URLDrawable where I will change with actual image from
      // the src tag
      return urlDrawable;
   }

   public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
      URLDrawable urlDrawable;

      public ImageGetterAsyncTask(URLDrawable d) {
         this.urlDrawable = d;
      }

      @Override
      protected Drawable doInBackground(String... params) {
         String source = params[0];
         return fetchDrawable("http://stardewvalleywiki.com/"+source);
      }

      @Override
      protected void onPostExecute(Drawable result) {
         // set the correct bound according to the result from HTTP call
         urlDrawable.setBounds(0, 0, 0 + result.getIntrinsicWidth()*10, 0  + result.getIntrinsicHeight()*10);

         // change the reference of the current drawable to the result
         // from the HTTP call
         urlDrawable.drawable = result;

         // redraw the image by invalidating the container
         URLImageParser.this.container.invalidate();
      }

      /***
       * Get the Drawable from URL
       * @param urlString
       * @return
       */
      public Drawable fetchDrawable(String urlString) {
         try {
            InputStream is = fetch(urlString);
            Drawable drawable = Drawable.createFromStream(is, "src");
            drawable.setBounds(0, 0, 0 + drawable.getIntrinsicWidth()*10, 0
                  + drawable.getIntrinsicHeight()*10);
            return drawable;
         } catch (Exception e) {
            return null;
         }
      }

      private InputStream fetch(String urlString) throws MalformedURLException, IOException {

         URL url = new URL(urlString);
         URLConnection urlConnection = url.openConnection();
         return urlConnection.getInputStream();
      }
   }
}