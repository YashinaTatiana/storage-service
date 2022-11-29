package ru.yashta.storageservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yashta.storageservice.entity.Item;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

    @Query(value = """
            WITH RECURSIVE box_tree AS (
            SELECT id FROM box WHERE id = :boxId
            UNION ALL
            SELECT box.id FROM box JOIN box_tree ON box.contained_in = box_tree.id
            )
            SELECT item.id FROM item
            JOIN box_tree
            ON contained_in = box_tree.id
            WHERE color=:color
            """, nativeQuery = true)
    List<Integer> findItems(String color, Integer boxId);

    @Override
    List<Item> findAll();
}
