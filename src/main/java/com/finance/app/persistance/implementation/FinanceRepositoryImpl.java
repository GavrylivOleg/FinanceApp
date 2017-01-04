package com.finance.app.persistance.implementation;

import com.finance.app.infrastructute.exception.CurrencyExchangeNotFoundException;
import com.finance.app.persistance.FinanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public class FinanceRepositoryImpl<T> implements FinanceRepository<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FinanceRepositoryImpl.class);

    private MongoRepository<T, String> mongoRepository;

    @Autowired
    public void setMongoRepository(MongoRepository<T, String> mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public T find(String id) {
        LOGGER.info("FinanceBaseServiceImpl.find(), id = {}", id);
        final T object = mongoRepository.findOne(id);
        if (object == null) {
            final String message = "Object not found";
            LOGGER.error("FinanceBaseServiceImpl.find(), id={}, message={}", id, message);
            throw new CurrencyExchangeNotFoundException(message);
        }
       LOGGER.info("FinanceBaseServiceImpl.find() finished");
        return object;
    }

    @Override
    public Page<T> findAll(Pageable pageable, String search) {
        throw new CurrencyExchangeNotFoundException("not yet implemented");
    }

    @Override
    public void save(T object) {
        LOGGER.info("FinanceBaseServiceImpl.saveUser()");
        mongoRepository.save(object);
        LOGGER.info("FinanceBaseServiceImpl.saveUser() finished");
    }

    @Override
    public void remove(T object) {
        LOGGER.info("FinanceBaseServiceImpl.remove()");
        mongoRepository.delete(object);
        LOGGER.info("FinanceBaseServiceImpl.remove() finished");
    }
}
