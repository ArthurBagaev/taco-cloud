package com.github.moreart.tacocloud.repositories;

import com.github.moreart.tacocloud.models.Taco;
import org.springframework.data.repository.CrudRepository;


public interface TacoRepository extends CrudRepository<Taco, Long> {

}
