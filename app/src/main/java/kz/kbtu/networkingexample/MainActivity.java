package kz.kbtu.networkingexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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



        Interceptor interceptor1 = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                okhttp3.Response response = chain.proceed(request);

                // todo deal with the issues the way you need to
                if (response.code() == 500) {
                    startActivity(
                            new Intent(
                                    ErrorHandlingActivity.this,
                                    ServerIsBrokenActivity.class
                            )
                    );

                    return response;
                }

                return response;
            }
        };

        okHttpClient.addInterceptor(interceptor1);



        Interceptor tokenInterceptor =  new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder;
                Request request;
                Response response;

                User user = getUser();
                if (user != null) {
                    requestBuilder = original.newBuilder()
                            .header("Authorization", "Token token=" + user.getAuthenticationToken())
                            .method(original.method(), original.body());

                    request = requestBuilder.build();
                    response = chain.proceed(request);

                } else
                    response = chain.proceed(original);

                boolean unauthorized = (response.code() == 401);
                if (unauthorized) {
                    new Helper().setUser(sharedPreferences, null);
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
//                ErrorResponse body = gson.fromJson(response.body().string(), ErrorResponse.class);
//                throw new AuthorizeException(body.error());
                }

//            Log.e("RESPONSE", response.body().);
                return response;
            }
        };

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(okHttpClient.build())
                .addConverterFactory(ResponseConverterFactory.create())
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
