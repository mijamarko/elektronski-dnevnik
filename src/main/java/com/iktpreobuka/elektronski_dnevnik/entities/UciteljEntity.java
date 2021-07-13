package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "ucitelji")
@AttributeOverride(name = "user_id", column = @Column(name = "ucitelj_id"))
public class UciteljEntity extends ProsvetniRadnikEntity {
	
	@OneToMany
	private List<OcenaEntity> upisaneOcene = new ArrayList<OcenaEntity>();
	
	

	public UciteljEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<OcenaEntity> getUpisaneOcene() {
		return upisaneOcene;
	}

	public void setUpisaneOcene(List<OcenaEntity> upisaneOcene) {
		this.upisaneOcene = upisaneOcene;
	}

	@Override
	public String toString() {
		return "UciteljEntity [upisaneOcene=" + upisaneOcene + "]";
	}
	
	

}
