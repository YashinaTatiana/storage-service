package ru.yashta.storageservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import ru.yashta.storageservice.model.ItemDto;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @Id
    private Integer id;
    private String color;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTAINED_IN")
    @ToString.Exclude
    private Box box;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Item item = (Item) o;
        return id != null && Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static Item of(ItemDto itemDto) {
        return Item.builder()
                .color(itemDto.getColor())
                .id(itemDto.getId())
                .build();
    }
}
