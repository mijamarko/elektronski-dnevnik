package com.iktpreobuka.elektronski_dnevnik.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "predmeti")
public class PredmetEntity {
	@Id
	private Integer id;

}
