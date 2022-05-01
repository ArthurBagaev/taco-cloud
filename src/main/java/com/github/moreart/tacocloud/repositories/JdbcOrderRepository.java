package com.github.moreart.tacocloud.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.moreart.tacocloud.models.Order;
import com.github.moreart.tacocloud.models.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository {

    private SimpleJdbcInsert orderInserter; //for "Taco_Order table
    private SimpleJdbcInsert orderTacoInserter; // for Taco_Order_Tacos table
    private ObjectMapper objectMapper;


    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order_Tacos");
        this.objectMapper = new ObjectMapper();
    }




/*
    @Override
    public Order save(Order order) {

        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        for (Taco taco: tacos) {
            saveTacoToOrder(taco, orderId);
        }

        return order;
    }*/

    private long saveOrderDetails(Order order) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());

        long orderId = orderInserter
                .executeAndReturnKey(values)
                .longValue();

        return orderId;

    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco);
        orderTacoInserter.execute(values);
    }
}
