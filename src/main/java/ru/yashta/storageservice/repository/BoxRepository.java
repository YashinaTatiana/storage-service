package ru.yashta.storageservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yashta.storageservice.entity.Box;

@Repository
public interface BoxRepository extends CrudRepository<Box, Integer> {
}
