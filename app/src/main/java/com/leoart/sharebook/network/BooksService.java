package com.leoart.sharebook.network;


import com.leoart.sharebook.models.BookModel;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface BooksService {

    @GET("{name}")
    Observable<List<BookModel>> searchBooks(@Path("name") String name);

}
