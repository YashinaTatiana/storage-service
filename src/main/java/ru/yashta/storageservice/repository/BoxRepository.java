package ru.yashta.storageservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yashta.storageservice.entity.Box;

import java.util.List;

@Repository
public interface BoxRepository extends CrudRepository<Box, Integer> {

    @Override
    List<Box> findAll();
}
