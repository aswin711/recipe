package com.example.cedex.recipe.data.s;

import android.util.Log;

import com.example.cedex.recipe.data.s.models.Category;
import com.example.cedex.recipe.data.s.models.CategoryData;
import com.example.cedex.recipe.data.s.models.ItemFetch;
import com.example.cedex.recipe.data.s.models.ItemFetchData;
import com.example.cedex.recipe.service.APIService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingDeque;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cedex on 4/4/2017.
 */

public class ApiHelper {
    private APIService apiService;
    private CategoryData categorydata;



    public ApiHelper(){
        this.apiService = APIClient.getClient().create(APIService.class);
    }

    public void getCategories(final ExtractValue callback){

        Call<CategoryData> call = apiService.getCategories();
        call.enqueue(new Callback<CategoryData>() {

            @Override
            public void onResponse(Call<CategoryData> call, Response<CategoryData> response) {

               CategoryData c = response.body();

               callback.getCategoryDetails(c);

               /* List<Category> list = new ArrayList<Category>();

                Iterator<Category> i = c.getCategories().iterator();
                while (i.hasNext()){
                    Category cat = new Category();
                    cat.setId(i.next().getId());
                    cat.setName(i.next().getName());
                    cat.setDescription(i.next().getDescription());
                    cat.setName(i.next().getImage());
                    //Log.d("category",i.next().getName()+"\n");
                    list.add(cat);
                }*/


            }

            @Override
            public void onFailure(Call<CategoryData> call, Throwable t) {

            }
        });
    }

    public void getItems(int id,final ExtractValue fetch){
        Call<ItemFetchData> call = apiService.getItems(id);
        call.enqueue(new Callback<ItemFetchData>() {
            @Override
            public void onResponse(Call<ItemFetchData> call, Response<ItemFetchData> response) {

            }

            @Override
            public void onFailure(Call<ItemFetchData> call, Throwable t) {

            }
        });
    }

 public interface ExtractValue{
     void getCategoryDetails(CategoryData cat);

 }

}
