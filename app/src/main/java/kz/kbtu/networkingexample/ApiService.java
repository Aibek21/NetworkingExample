package kz.kbtu.networkingexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by aibekkuralbaev on 25.09.17.
 */

public interface ApiService {

    @GET("posts")
    Call<List<Post>> getPosts();


    @GET("posts")
    Call<List<Post>> getPostsById(@Query("userId") int userId);


    @GET("posts/{id}/comments")
    Call<List<Comment>> getCommentsByPost(@Path("id") int postId);


    @POST("posts")
    Call<Post> createPost();


    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPostWithParams(@Field("title") String title,
                                    @Field("body") String body,
                                    @Field("userId") int userId);



    @FormUrlEncoded
    @PUT("posts/{postId}")
    Call<Post> updatePost(@Field("title") String title, @Path("postId") int postId);


    @DELETE("posts/{postId}")
    Call<Post> deletePost(@Path("postId") int postId);



    @Headers({"Cache-Control: max-age=640000",
            "User-Agent: Retrofit-Sample-App"})
    @POST("posts")
    Call<Post> createPostWithJson(@Body Post post, @Header("Authorization") String auth);



}
