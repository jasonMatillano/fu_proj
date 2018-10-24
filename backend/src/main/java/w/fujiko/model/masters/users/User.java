/**
 * 
 */
package w.fujiko.model.masters.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.masters.systems.Program;

/**
 * @author yagami
 *
 */
@Entity
@Table(name="mst_u_user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_u_user_id")
	private Integer id;
	
	@NotNull
	@Column(name="mst_u_user_code", columnDefinition = "nvarchar(3)")
	private String user_code;
	
	@NotNull
	@Column(name="mst_u_username", columnDefinition = "nvarchar(30)", unique=true)
	private String username;
	
	@NotNull
	@Column(name="username_kana", columnDefinition = "nvarchar(30)")
	private String username_kana;
	
	@Column(name="password", columnDefinition = "nvarchar(100)")
	private String password;
	
	@NotNull
	@Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean isResigned;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="mst_dept_id", insertable = true)
	private Department department;
	
	@CreationTimestamp
	@Column(name="date_created", columnDefinition = "datetime", nullable = false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_created;
	
	@NotNull
	@Column(name="created_by")
	private Integer created_by;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="created_at", insertable=true, updatable = false)
	@NotNull
	private Program created_at;

	@UpdateTimestamp
	@Column(name="date_updated", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_updated;
	
	@NotNull
	@Column(name="updated_by")
	private Integer updated_by;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="updated_at", insertable=true, updatable = true)
	@NotNull
	private Program updated_at;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername_kana() {
		return username_kana;
	}

	public void setUsername_kana(String username_kana) {
		this.username_kana = username_kana;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsResigned() {
		return isResigned;
	}

	public void setIsResigned(Boolean isResigned) {
		this.isResigned = isResigned;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
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

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
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
