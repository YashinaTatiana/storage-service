package ru.yashta.storageservice.model;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="Box")
@XmlAccessorType(XmlAccessType.NONE)
public class BoxDto implements Serializable {

    @XmlAttribute(name="id")
    private Integer id;

    @XmlElement(name = "Box")
    private List<BoxDto> boxes = new ArrayList<>();

    @XmlElement(name = "Item")
    private List<ItemDto> items = new ArrayList<>();
}
