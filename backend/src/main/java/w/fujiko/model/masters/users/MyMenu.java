/**
 * 
 */
package w.fujiko.model.masters.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumns;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.systems.Program;
/**
 * @author yagami
 *
 */
@Entity
@Table(name="mst_mymenu")
public class MyMenu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_mymenu_id",columnDefinition = "int")
	private Integer id;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="mst_mymenu_prog_id",referencedColumnName="mst_role_prog_id"),
		@JoinColumn(name="authorized_user_id",referencedColumnName="authorized_user_id")
	})
	private RoleProgram role_program;

	@NotNull
	@Column(name="menu_sequence", columnDefinition = "int")
	private Integer menu_sequence;

	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_created;
	
	@ManyToOne
	@JoinColumn(name="created_by", insertable=true, updatable = true)
	@NotNull
	private User created_by;

	@ManyToOne
	@JoinColumn(name="created_at", insertable=true, updatable = true)
	@NotNull
	private Program created_at;

	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_updated;

	@ManyToOne
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	@NotNull
	private User updated_by;

	@ManyToOne
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	@NotNull
	private Program updated_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleProgram getRole_program() {
		return role_program;
	}

	public void setRole_program(RoleProgram role_program) {
		this.role_program = role_program;
	}

	public Integer getMenu_sequence() {
		return menu_sequence;
	}

	public void setMenu_sequence(Integer menu_sequence) {
		this.menu_sequence = menu_sequence;
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
}