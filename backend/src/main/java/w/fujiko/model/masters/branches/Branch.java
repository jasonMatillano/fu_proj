/**
 * 
 */
package w.fujiko.model.masters.branches;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import w.fujiko.model.masters.departments.Department;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fte.api.Defaults;
/**
 * 
 * @author yagami
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property = "id")
@Entity
@Table(name="mst_u_branch")
public class Branch extends Defaults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
	
	@NotNull
	@Column(name="mst_u_branch_cd", columnDefinition = "char(6)")
	private String code;
	
	@NotNull
	@Column(name="mst_u_branch_name", columnDefinition = "nvarchar(100)")
    private String name;

    @NotNull
	@Column(name="branch_name_kana", columnDefinition = "nvarchar(100)")
    private String name_kana;

    @NotNull
	@Column(name="branch_shortname", columnDefinition = "nvarchar(50)")
    private String short_name;
    
	@Column(name="branch_sales_flg", columnDefinition = "bit default 0")
    private Boolean sales_flag;

	@Column(name="branch_order", columnDefinition = "int")
    private Integer sequence;
    
    @NotNull
    @Column(name="branch_postalcode", columnDefinition = "nvarchar(20)")
    private String postal_code;
    
    @NotNull
    @Column(name="branch_address1", columnDefinition = "nvarchar(250)")
    private String address1;

    @NotNull
    @Column(name="branch_address2", columnDefinition = "nvarchar(250)")
    private String address2;
    
    @NotNull
    @Column(name="branch_telno", columnDefinition = "nvarchar(50)")
    private String telephone_number;

    @NotNull
    @Column(name="branch_faxno", columnDefinition = "nvarchar(50)")
    private String fax_number;
    
    @NotNull
    @Column(name="mst_u_branch_warehouse_id", columnDefinition = "int")
    private Integer warehouse;

    @Column(name="branch_receipt_seqno", columnDefinition = "nvarchar(50)")
    private String receipt_sequence_number;

    @Column(name="branch_receipt_symbol", columnDefinition = "nvarchar(30)")
    private String receipt_symbol;

    @Column(name="is_end", columnDefinition = "bit default 0")
	private Boolean is_end;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="branch",cascade = CascadeType.ALL)	
	//@JsonBackReference
    private Set<Department> departments = new HashSet<>();

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

	public String getName_kana() {
		return name_kana;
	}

	public void setName_kana(String name_kana) {
		this.name_kana = name_kana;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public Boolean getSales_flag() {
		return sales_flag;
	}

	public void setSales_flag(Boolean sales_flag) {
		this.sales_flag = sales_flag;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTelephone_number() {
		return telephone_number;
	}

	public void setTelephone_number(String telephone_number) {
		this.telephone_number = telephone_number;
	}

	public String getFax_number() {
		return fax_number;
	}

	public void setFax_number(String fax_number) {
		this.fax_number = fax_number;
	}

	public Integer getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
	}

	public String getReceipt_sequence_number() {
		return receipt_sequence_number;
	}

	public void setReceipt_sequence_number(String receipt_sequence_number) {
		this.receipt_sequence_number = receipt_sequence_number;
	}

	public String getReceipt_symbol() {
		return receipt_symbol;
	}

	public void setReceipt_symbol(String receipt_symbol) {
		this.receipt_symbol = receipt_symbol;
    }
    
    public Boolean getIs_end() {
		return is_end;
	}

	public void setIs_end(Boolean is_end) {
		this.is_end = is_end;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	
}