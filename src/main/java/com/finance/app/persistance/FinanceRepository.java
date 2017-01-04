package com.finance.app.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FinanceRepository<T> {

    T find (String id);

    Page<T> findAll(Pageable pageable, String search);

    void  remove(T object);

    void save (T object);


}