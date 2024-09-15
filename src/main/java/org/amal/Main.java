package org.amal;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");

    OkHttpClient client = new OkHttpClient();
    String url = "https://fakestoreapi.com/products/1";
    Request request = new Request.Builder().url(url).build();

    try(Response response = client.newCall(request).execute()){
      if(!response.isSuccessful()) System.out.println("Not Successfull");
      System.out.println(response.body().string());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}