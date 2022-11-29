package ru.yashta.storageservice.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="Item")
@XmlAccessorType(XmlAccessType.NONE)
public class ItemDto implements Serializable {

    @XmlAttribute(name="id")
    private Integer id;

    @XmlAttribute(name="color")
    private String color;
}