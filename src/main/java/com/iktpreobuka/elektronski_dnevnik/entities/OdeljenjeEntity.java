package com.iktpreobuka.elektronski_dnevnik.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "odeljenja")
public class OdeljenjeEntity {
	@Id
	private Integer id;

}
