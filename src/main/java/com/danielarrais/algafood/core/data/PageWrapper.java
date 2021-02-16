package com.danielarrais.algafood.core.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PageWrapper<T> extends PageImpl<T> {
    private Pageable pageable;

    public PageWrapper(Page<T> page, Pageable pageable) {
        super(page.getContent(), pageable, page.getTotalElements());
    }

    public static <T> PageWrapper<T> of(Page<T> page, Pageable pageable) {
        return new PageWrapper<T>(page, pageable);
    }

    @Override
    public Pageable getPageable() {
        return this.pageable;
    }
}
