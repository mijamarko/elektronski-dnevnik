package com.iktpreobuka.elektronski_dnevnik.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
@DiscriminatorValue("admin")
public class AdminEntity extends KorisnikEntity {

}
