package com.mikhail.async;

import com.mikhail.model.Offer;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;


public class AsyncCheckIsPictureUrlValid implements Runnable {
    private CountDownLatch latch;
    private Offer offer;

    public AsyncCheckIsPictureUrlValid(Offer offer, CountDownLatch latch) {
        this.latch = latch;
        this.offer = offer;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(offer.getPictureUrl()).openConnection();
            con.setRequestMethod("HEAD");
            //con.setConnectTimeout(100);

            if(con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                offer.setPictureUrlValid("p");
            }

            latch.countDown();
        }
        catch (Exception e) {
            offer.setPictureUrlValid("p");

            e.printStackTrace();

            latch.countDown();
        }
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
}
