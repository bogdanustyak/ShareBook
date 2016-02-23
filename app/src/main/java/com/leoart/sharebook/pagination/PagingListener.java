package com.leoart.sharebook.pagination;

import rx.Observable;

public interface PagingListener<T> {
    Observable<T> onNextPage(int offset);
}
