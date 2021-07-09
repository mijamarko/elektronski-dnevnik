package com.iktpreobuka.elektronski_dnevnik.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "razredi")
public class RazredEntity {
	@Id
	private Integer id;

}
