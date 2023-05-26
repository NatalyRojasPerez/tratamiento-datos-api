package com.example.demo.entidad;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "mascota")
public class Mascota {
    private long id;
    private String name;
}
