package com.github.moreart.tacocloud.repositories;

import com.github.moreart.tacocloud.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


public interface OrderRepository extends CrudRepository<Order, Long> {
   /* List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
    List<Order> findByDeliveryZip(String deliveryZip);*/
}
