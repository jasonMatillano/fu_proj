 /**
 * 
 */
package fte.api;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.users.User;

/**
 * @author Try-Parser 
 *
 */
@MappedSuperclass
public abstract class Defaults implements Serializable {
	private static final long serialVersionUID = 9098563142871729601L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", updatable = false, nullable = false)
	private Integer id;

	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_created;
	
	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_updated;
	
	@JsonBackReference(value = "created_by")
	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = false)
	private User created_by;

	@JsonBackReference(value = "updated_by")
	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_by", insertable=true, updatable = false)
	private User updated_by;

	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false)
	private Program created_at;
	
	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = false)
	private Program updated_at;

	@Column(columnDefinition = "BIT default 0", name = "DELETED", length = 1)
	private Boolean deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public User getCreated_by() {
		return created_by;
	}

	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}

	public Date getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(Date date_updated) {
		this.date_updated = date_updated;
	}

	public User getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(User updated_by) {
		this.updated_by = updated_by;
	}

	public Program getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Program created_at) {
		this.created_at = created_at;
	}

	public Program getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Program updated_at) {
		this.updated_at = updated_at;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
