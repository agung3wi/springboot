package com.agung.project.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "m_divisi")

public class Divisi extends BaseModel implements Serializable {

	@Id
	@Column (name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="m_divisi_id_seq")
    @SequenceGenerator(name="m_divisi_id_seq", sequenceName="m_divisi_id_seq", allocationSize=1)
    public long id;
	
	@Column (name = "div_name")
	public String divName;
	
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

}
