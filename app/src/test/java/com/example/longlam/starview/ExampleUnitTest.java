package com.example.longlam.starview;

import com.example.longlam.starview.util.parseHtml;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
   private CropInfo so;

   @Test
   public void gettingHtml() {
      CropInfo cropInfo = parseHtml.gettingHtml("Melon");
   }
}