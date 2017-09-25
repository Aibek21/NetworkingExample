package kz.kbtu.networkingexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().
                readTimeout(60, TimeUnit.SECONDS).
                connectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

//        Call<List<Post>> postCall = service.getPosts();
//
//        postCall.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                if (response.isSuccessful()) {
//                    Log.e("Response", response.body().toString());
//                    List<Post> posts = response.body();
//                    for (Post post : posts) {
//                        Log.e("Post", post.getTitle());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                Log.e("onFailure", t.getMessage());
//
//            }
//        });


//        Call<List<Post>> postCallById = service.getPostsById(1);
//
//        postCallById.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                if (response.isSuccessful()) {
//                    List<Post> posts = response.body();
//                    for (Post post : posts) {
//                        Log.e("Post", post.getTitle());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                Log.e("onFailure", t.getMessage());
//
//            }
//        });


//        Call<List<Comment>> commentsCall = service.getCommentsByPost(1);
//
//        commentsCall.enqueue(new Callback<List<Comment>>() {
//            @Override
//            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
//                List<Comment> comments = response.body();
//                for (Comment comment: comments)
//                    Log.e("Comment", comment.getName());
//            }
//
//            @Override
//            public void onFailure(Call<List<Comment>> call, Throwable t) {
//                Log.e("onFailure", "OK");
//            }
//        });


//        Call<Post> postCreateCall = service.createPost();
//
//        postCreateCall.enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                Post post = response.body();
//                Log.e("CreatedPost", post.getId()+"");
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//
//            }
//        });


//
//        Call<Post> postCreateWithParamsCall = service.createPostWithParams("Title1", "Body1", 1);
//
//        postCreateWithParamsCall.enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                Post post = response.body();
//                Log.e("CreatedPost", post.getId()+"");
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//
//            }
//        });



//        Call<Post> updatePostCall = service.updatePost("Title2", 1);
//
//        updatePostCall.enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                Post post = response.body();
//                Log.e("UpdatedPost", post.getId()+"");
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//
//            }
//        });


//        Call<Post> deletePostCall = service.deletePost(1);
//
//        deletePostCall.enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                Post post = response.body();
//                Log.e("UpdatedPost", post.getId()+"");
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//
//            }
//        });



//        Post post = new Post();
//        post.setTitle("MyTitle");
//        post.setBody("MyBody");
//        post.setUserId(1);
//
//        Call<Post> postCreateWithJsonCall = service.createPostWithJson(post, "authoriztion");
//
//        postCreateWithJsonCall.enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                Post post = response.body();
//                Log.e("CreatedPostJson", post.getTitle()+"");
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//
//            }
//        });

    }
}
