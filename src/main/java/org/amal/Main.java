package org.amal;

import okhttp3.OkHttpClient;
import okhttp3.Request;
//import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;

public class Main {

  public class Product{
    String id;
    String title;
    Double price;
    String description;
    String category;
    String image;
  }

  public interface ProductService {
    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") String productId);
  }

  public static void main(String[] args) {
    System.out.println("Hello world!");

//    OkHttpClient client = new OkHttpClient();
//    String url = "https://fakestoreapi.com/products/1";
//    Request request = new Request.Builder().url(url).build();
//
//    try(Response response = client.newCall(request).execute()){
//      if(!response.isSuccessful()) System.out.println("Not Successfull");
//      System.out.println(response.body().string());
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }

    String baseUrl = "https://fakestoreapi.com/";
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    ProductService service = retrofit.create(ProductService.class);

    Call<Product> call = service.getProductById("1");
    call.enqueue(new Callback<>() {
      @Override
      public void onResponse(Call<Product> call, Response<Product> response) {
        Product product = response.body();
        System.out.println(product.id + " " + product.description);

      }

      @Override
      public void onFailure(Call<Product> call, Throwable throwable) {
        System.out.println("cancelled");
        System.out.println(throwable);
        call.cancel();
      }
    });


  }
}