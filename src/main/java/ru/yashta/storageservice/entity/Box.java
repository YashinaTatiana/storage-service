package ru.yashta.storageservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Box {
    @Id
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTAINED_IN")
    @ToString.Exclude
    private Box containedIn;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "box", fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private List<Item> itemList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Box box = (Box) o;
        return id != null && Objects.equals(id, box.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addItem(Item item) {
        itemList.add(item);
        item.setBox(this);
    }
}
