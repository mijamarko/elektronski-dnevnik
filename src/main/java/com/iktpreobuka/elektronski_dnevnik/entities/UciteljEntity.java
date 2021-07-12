package com.iktpreobuka.elektronski_dnevnik.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "ucitelji")
public class UciteljEntity extends ProsvetiteljEntity {

}
