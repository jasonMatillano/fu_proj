/**
 *
 */
package w.fujiko.model.masters.systems;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import w.fujiko.model.masters.users.User;

/**
 * @author yagami
 *
 */
@Entity
@Table(name="sys_prog_category")
public class ProgramCategory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@Id
	@Column(name="sys_prog_category_id", columnDefinition = "char(1)")
	private String id;
	
	@NotNull
	@Column(name="category_name", columnDefinition = "nvarchar(100)", unique=true)
	private String category_name;	
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="category")	
	@JsonManagedReference(value="programCategory")
	private List<Program> programs;

	@NotNull
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_created;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_by", insertable=true, updatable = false)
	//@JsonBackReference(value="creatorUser")
	private User created_by;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = true)
	//@JsonBackReference(value="creatorProgram")
	private Program created_at;

	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_updated;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	//@JsonBackReference(value="updatorUser")
	private User updated_by;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	//@JsonBackReference(value="updatorProgram")
	private Program updated_at;

	public String getId() {
		return id;
	}

	public void setId(String category_id) {
		this.id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
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

	public Program getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Program created_at) {
		this.created_at = created_at;
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

	public Program getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Program updated_at) {
		this.updated_at = updated_at;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}