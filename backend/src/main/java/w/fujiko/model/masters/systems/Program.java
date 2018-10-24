/**
 * 
 */
package w.fujiko.model.masters.systems;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.RoleProgram;
/**
 * 
 * @author yagami
 *
 */
@Entity
@Table(name="sys_prog")
public class Program implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@Id
	@Column(name="sys_prog_id",columnDefinition="char(7)")
	private String id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="sys_prog_category_id",insertable=false, updatable = false)
	@JsonBackReference(value="programCategory")
	private ProgramCategory category;	

	@Column(name="sys_prog_category_id",columnDefinition="char(1)")

	private String category_id;

	@Column(name="menu_sequence", columnDefinition = "int")
	private Integer menu_sequence;
	
	@NotNull
	@Column(name="program_name", columnDefinition = "nvarchar(100)", unique=true)
	private String name;

	@Column(name="program_text", columnDefinition = "varchar(30)")
	private String text;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="program")
	@JsonManagedReference(value="programCommand")
	private List<ProgramCommand> commands;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="program")
	@JsonManagedReference(value="roleProgram")
	private Set<RoleProgram> roles = new HashSet<>();

	@NotNull
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_created;

	@Column(name="created_by")
	private User created_by;
	
	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_updated;

	@Column(name="updated_by")
	private User updated_by;

	public String getId() {
		return id;
	}

	public void setId(String program_id) {
		this.id = program_id;
	}

	public ProgramCategory getCategory() {
		return category;
	}

	public void setCategory(ProgramCategory category) {
		this.category = category;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public Integer getMenu_sequence() {
		return menu_sequence;
	}

	public void setMenu_sequence(Integer menu_sequence) {
		this.menu_sequence = menu_sequence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<RoleProgram> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleProgram> roles) {
		this.roles = roles;
	}

	public List<ProgramCommand> getCommands() {
		return commands;
	}

	public void setCommands(List<ProgramCommand> commands) {
		this.commands = commands;
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
}