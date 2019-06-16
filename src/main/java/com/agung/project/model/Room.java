package com.agung.project.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Entity
@Table(name = "m_room")

public class Room implements Serializable {
	
	@Id
	@Column (name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="m_room_id_seq")
    @SequenceGenerator(name="m_room_id_seq", sequenceName="m_room_id_seq", allocationSize=1)
	public long id;
	
	@Column (name = "room_name")
	public String roomName;
	
	@Column (name = "gh_id")
	public int ghId;
	
	@Column (name = "n_row")
	public int nRow;
	
	@Column (name = "n_column")
	public int nColumn;
	
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
