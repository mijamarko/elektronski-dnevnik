package com.iktpreobuka.elektronski_dnevnik.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.security.Views;

@Entity
@Table(name = "admin")
@DiscriminatorValue("admin")
@JsonView(Views.Admin.class)
public class AdminEntity extends KorisnikEntity {

}
