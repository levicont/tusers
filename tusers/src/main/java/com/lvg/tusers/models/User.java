package com.lvg.tusers.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.lvg.tusers.config.R;

@Entity
@Table(name = "tusers.user")
public class User {
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tusers.user_id_seq")
    @SequenceGenerator(name="tusers.user_id_seq", sequenceName="tusers.user_id_seq", allocationSize=1)    
	@Column(name="id_user")
	private Long id;
	
	@NotNull(message=R.Exceptions.ERROR_INVALID_USR_NAME)
	private String name;
	
	@NotNull(message=R.Exceptions.ERROR_INVALID_USR_SURNAME)
	private String surname;
	
	@NotNull(message=R.Exceptions.ERROR_INVALID_USR_EMAIL)
	private String email;
	private String info;	
	
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@NotNull(message=R.Exceptions.ERROR_INVALID_USR_PASSWORD)
	private String password;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity=Gallery.class)
	private Set<Gallery> galleries = new HashSet<>();
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
		
	public Set<Gallery> getGalleries() {
		return galleries;
	}
	public void setGalleries(Set<Gallery> galleries) {
		this.galleries = galleries;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
}
