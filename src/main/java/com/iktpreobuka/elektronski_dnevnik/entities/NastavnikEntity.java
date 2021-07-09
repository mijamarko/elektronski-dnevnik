package com.iktpreobuka.elektronski_dnevnik.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })

@Entity
@Table(name = "nastavnici")
@PrimaryKeyJoinColumn(name = "user_id")
public class NastavnikEntity extends KorisnikEntity {

}
