package com.lvg.tusers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tusers.image")
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tusers.image_id_seq")
    @SequenceGenerator(name="tusers.image_id_seq", sequenceName="tusers.image_id_seq", allocationSize=1)    
	@Column(name="id_image")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_gallery", nullable=false)
	private Gallery gallery;
	
	@Column(name="src")
	private byte[] source;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Gallery getGallery() {
		return gallery;
	}
	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}
	
	
	public byte[] getSource() {
		return source;
	}
	public void setSource(byte[] source) {
		this.source = source;
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
		Image other = (Image) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
