package com.agung.project.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Entity
@Table(name = "m_divisi")

public class Divisi implements Serializable {
	
	@Id
	@Column (name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="m_divisi_id_seq")
    @SequenceGenerator(name="m_divisi_id_seq", sequenceName="m_divisi_id_seq", allocationSize=1)
    public long id;
	
	@Column (name = "div_name")
	public String div_name;
	
	@Column (name = "div_code")
	public String divCode;
	
	@Column (name = "created_by")
	public long createdBy;
	
	@Column (name = "updated_by")
	public long updatedBy;
	
	@Column (name = "created_at")
	public String createdAt;
	
	@Column (name = "updated_at")
	public String updatedAt;
	
	@Column (name = "version")
    public long version;
	
//	@OneToOne(optional=true)
//    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = true, insertable=false, updatable=false)
//    private User user;

}
