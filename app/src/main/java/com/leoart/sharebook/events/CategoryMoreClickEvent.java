package com.leoart.sharebook.events;

import com.leoart.sharebook.models.DiscoverModel;

/**
 * Created by bogdan on 1/25/16.
 */
public class CategoryMoreClickEvent {
    public DiscoverModel discoverModel;

    public CategoryMoreClickEvent(DiscoverModel discoverModel) {
        this.discoverModel = discoverModel;
    }

}
