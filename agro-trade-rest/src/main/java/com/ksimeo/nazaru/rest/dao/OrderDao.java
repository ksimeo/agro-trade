package com.ksimeo.nazaru.rest.dao;

import com.ksimeo.nazaru.core.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ksimeo on 26.01.2015.
 */
@Repository
public interface OrderDao extends CrudRepository<Order, Integer> {

}