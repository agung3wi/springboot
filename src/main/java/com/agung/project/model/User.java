package com.agung.project.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")

public class User implements Serializable {
	@Id
	@Column (name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users_id_seq")
    @SequenceGenerator(name="users_id_seq", sequenceName="users_id_seq", allocationSize=1)
    public long id;
	
	@Column(name = "username")
	public String username;
	
//	@OneToOne(mappedBy = "user")
//    private Divisi divisi;
}
