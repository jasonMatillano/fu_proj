/**
 * 
 */
package w.fujiko.model.masters.systems;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.Table;

import fte.api.Defaults;
import w.fujiko.model.masters.users.RoleProgramCommand;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

/**
 * @author yagami
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property = "id",scope=ProgramCommand.class)
@Entity
@Table(name="sys_prog_command")
public class ProgramCommand extends Defaults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="sys_prog_id", insertable=false, updatable = false)
	@JsonBackReference(value="programCommand")
	@NotNull
	private Program program;

	@Column(name="sys_prog_id",columnDefinition="char(7)")
	@NotNull
	private String program_id;

	@NotNull
	@Column(name="prog_command_name", columnDefinition = "nvarchar(100)")
	private String name;

	@NotNull
	@Column(name="prog_command_key", columnDefinition = "nvarchar(15)")
	private String key_name;

	@NotNull
	@Column(name="prog_command_key_code", columnDefinition = "char(3)")
	private String key_code;

	@OneToMany(fetch=FetchType.EAGER,mappedBy="command.command_id")
	@JsonManagedReference(value="programCommand")
	private Set<RoleProgramCommand> role_program_commands = new HashSet<>();
	
	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public String getProgram_id() {
		return program_id;
	}

	public void setProgram_id(String program_id) {
		this.program_id = program_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey_name() {
		return key_name;
	}

	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public Set<RoleProgramCommand> getRole_program_commands() {
		return role_program_commands;
	}

	public void setRole_program_commands(Set<RoleProgramCommand> role_program_commands) {
		this.role_program_commands = role_program_commands;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}