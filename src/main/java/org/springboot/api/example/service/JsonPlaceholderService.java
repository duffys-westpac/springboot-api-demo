package org.springboot.api.example.service;

import org.springboot.api.example.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class JsonPlaceholderService {
    private final JsonPlaceholderApi api;

    @Autowired
    public JsonPlaceholderService(@Value("${JsonPlaceholderService.baseUrl}") String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder() //
                .baseUrl(baseUrl) //
                .addConverterFactory(JacksonConverterFactory.create()) //
                .build(); //
        api = retrofit.create(JsonPlaceholderApi.class);
    }
    @Async
    public Future<List<Post>> getAllPosts() throws IOException {
        List<Post> posts = api.getAllPosts().execute().body();
        return new AsyncResult<>(posts);
    }

    @Async
    public Future<List<Post>> getPostsByUserId(int userId) throws IOException {
        List<Post> posts = api.getPostsByUserId(userId).execute().body();
        return new AsyncResult<>(posts);
    }
}
