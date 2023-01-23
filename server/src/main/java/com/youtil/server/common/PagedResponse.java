package com.youtil.server.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class PagedResponse<T> {
    private List<T> data;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PagedResponse(List<T> data, int page, int size, long totalElements, int totalPages, boolean last) {
//        setContent(content);
        this.data = data;
        this.page = page; // ban
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages; // ban
        this.last = last;
    }

    public PagedResponse(List<T> data, int page, int size,int totalPages, boolean last) {
//        setContent(content);
        this.data = data;
        this.page = page; // ban
        this.size = size;
        this.totalPages = totalPages; // ban
        this.last = last;
    }


//    public List<T> getContent() {
//        return content == null ? null : new ArrayList<>(content);
//    }
//
//    public final void setContent(List<T> content) {
//        if (content == null) {
//            this.content = null;
//        } else {
//            this.content = Collections.unmodifiableList(content);
//        }
//    }
}
