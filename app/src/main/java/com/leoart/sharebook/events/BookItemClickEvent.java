package com.leoart.sharebook.events;

import android.widget.ImageView;

/**
 * Created by bogdan
 */
public class BookItemClickEvent {
    public ImageView bookCover;

    public BookItemClickEvent(ImageView bookCover) {
        this.bookCover = bookCover;
    }
}
