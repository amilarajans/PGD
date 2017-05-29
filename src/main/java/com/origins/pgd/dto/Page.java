package com.origins.pgd.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Amila-Kumara on 3/19/2016.
 */
@Getter
@Setter
public class Page {
    private Long count;
    private Long pageSize;

    public Page(Long count, Long pageSize) {
        this.count = count;
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return String.format("Page{count=%d, pageSize=%d}", count, pageSize);
    }
}
