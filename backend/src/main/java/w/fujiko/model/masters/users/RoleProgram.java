/**
 * 
 */
package w.fujiko.model.masters.users;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import w.fujiko.model.masters.systems.Program;
/**
 * @author yagami
 *
 */
@Entity
@Table(name="mst_role_prog")
public class RoleProgram implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@EmbeddedId
	private ProgramPk mst_role_prog;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="mst_role_prog_id", insertable = false, updatable = false)
	@JsonBackReference(value="roleProgram")
	private Program program;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="authorized_user_id", insertable = false, updatable = false)
	@JsonBackReference(value="authorizedUser")
	private User authorized_user;

	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="role_program")	
	@JsonManagedReference(value="roleProgram")
	private Set<RoleProgramCommand> program_commands = new HashSet<>();

	// @OneToOne(mappedBy="program",cascade=CascadeType.REMOVE,orphanRemoval=true)
	// private MyMenu MyMenu;

	@NotNull
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_created;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="created_by", insertable=true, updatable = true)
	@JsonBackReference(value="creatorUser")
	@NotNull
	private User created_by;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="created_at", insertable=true, updatable = true)
	@NotNull
	@JsonBackReference(value="creatorProgram")
	private Program created_at;

	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_updated;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="updated_by", insertable=true, updatable = true)
	@NotNull
	@JsonBackReference(value="updatorUser")
	private User updated_by;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	@NotNull
	@JsonBackReference(value="updatorProgram")
	private Program updated_at;

	public ProgramPk getMst_role_prog() {
		return mst_role_prog;
	}

	public void setMst_role_prog(ProgramPk mst_role_prog) {
		this.mst_role_prog = mst_role_prog;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public User getAuthorized_user() {
		return authorized_user;
	}

	public void setAuthorized_user(User authorized_user) {
		this.authorized_user = authorized_user;
	}

	public Set<RoleProgramCommand> getProgram_commands() {
		return program_commands;
	}

	public void setProgram_commands(Set<RoleProgramCommand> program_commands) {
		this.program_commands = program_commands;
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