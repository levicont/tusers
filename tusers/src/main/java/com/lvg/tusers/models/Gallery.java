package com.lvg.tusers.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="tusers.gallery")
public class Gallery implements Serializable{
		
	private static final long serialVersionUID = 3908459790931477660L;
	
	
	private Long id;
	private int version;
	private String name;	
	private User user;
	
	
	private Set<Image> images = new HashSet<>();
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tusers.gallery_id_seq")
    @SequenceGenerator(name="tusers.gallery_id_seq", sequenceName="tusers.gallery_id_seq", allocationSize=1)    
	@Column(name="id_gallery")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
		
	@Version
	@Column(name="version")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=User.class)
	@JoinColumn(name="id_user", nullable=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(mappedBy="gallery")
	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gallery other = (Gallery) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {		
		return "GALLERY: \t id: "+id+"\n"+
				"\t name: "+name+"\n";
	}
	
	
	
	
	
}
