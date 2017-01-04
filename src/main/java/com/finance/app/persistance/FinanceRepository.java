package com.finance.app.persistance;

import com.mongodb.WriteResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinanceRepository<T> {

    T find (String id);

    Page<T> findAll(Pageable pageable, String search);

    void  remove(T object);

    void save (T object);


}