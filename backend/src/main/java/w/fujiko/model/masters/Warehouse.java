package w.fujiko.model.masters;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import w.fujiko.model.masters.systems.Program;

/**
 * 
 * @author johnl
 *
 */

@Entity
@Table(name="mst_warehouse")
public class Warehouse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7266566357830949853L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mst_whs_id")
	private Integer id;
	
	@NotNull
	@Column(name="whs_cd", columnDefinition = "nvarchar(10)")
	private String code;
	
	@NotNull
	@Column(name="whs_name", columnDefinition = "nvarchar(30)")
	private String name;
	
	@Column(name="whs_kana", columnDefinition = "nvarchar(30)")
	private String kana;
	
	@Column(name="whs_shortname", columnDefinition = "nvarchar(30)")
	private String shortname;
	
	@Column(name="whs_postal_code", columnDefinition = "varchar(15)")
	private String postal_code;
	
	@Column(name="whs_address_ichi", columnDefinition = "nvarchar(30)")
	private String addr_one;
	
	@Column(name="whs_address_ni", columnDefinition = "nvarchar(30)")
	private String addr_two;
	
	@Column(name="whs_phone_number", columnDefinition = "varchar(15)")
	private String phone_num;
	
	@Column(name="whs_fax_number", columnDefinition = "varchar(15)")
	private String fax_num;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getAddr_one() {
		return addr_one;
	}

	public void setAddr_one(String addr_one) {
		this.addr_one = addr_one;
	}

	public String getAddr_two() {
		return addr_two;
	}

	public void setAddr_two(String addr_two) {
		this.addr_two = addr_two;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getFax_num() {
		return fax_num;
	}

	public void setFax_num(String fax_num) {
		this.fax_num = fax_num;
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
	
	
}
