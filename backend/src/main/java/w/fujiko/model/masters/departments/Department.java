/**
 * 
 */
package w.fujiko.model.masters.departments;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import w.fujiko.model.masters.branches.Branch;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import fte.api.Defaults;
/**
 * 
 * @author yagami
 *
 */
@Entity
@Table(name="mst_u_department")
public class Department extends Defaults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

    @NotNull
    @ManyToOne
	@JoinColumn(name="mst_u_dept_branch_id", insertable=true, updatable = true)
    private Branch branch;

    @JsonIgnore
    @ManyToOne
	@JoinColumn(name="mst_u_parent_dept_id", insertable=true, updatable = true)	
    private Department parent_department;

	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent_department")		
    private List<Department> sub_departments;
	
	@NotNull
	@Column(name="mst_u_dept_cd", columnDefinition = "char(6)")
	private String code;

	@NotNull
	@Column(name="dept_name", columnDefinition = "nvarchar(100)")
    private String name;
    
    @NotNull
	@Column(name="dept_name_kana", columnDefinition = "nvarchar(100)")
    private String name_kana;

    @NotNull
	@Column(name="dept_shortname", columnDefinition = "nvarchar(50)")
    private String short_name;
    
	@Column(name="dept_sales_flg", columnDefinition = "bit default 0")
    private Boolean sales_flag;

	@Column(name="dept_order", columnDefinition = "int")
    private Integer sequence;
    
    @NotNull
    @Column(name="dept_postalcode", columnDefinition = "nvarchar(20)")
    private String postal_code;
    
    @NotNull
    @Column(name="dept_address1", columnDefinition = "nvarchar(250)")
    private String address1;
    
    @NotNull
    @Column(name="dept_address2", columnDefinition = "nvarchar(250)")
    private String address2;
    
    @NotNull
    @Column(name="dept_telno", columnDefinition = "nvarchar(50)")
    private String telephone_number;

    @Column(name="dept_faxno", columnDefinition = "nvarchar(50)")
    private String fax_number;

    @Column(name="dept_receipt_seqno", columnDefinition = "nvarchar(50)")
    private String receipt_sequence_number;

    @Column(name="dept_receipt_symbol", columnDefinition = "nvarchar(30)")
    private String receipt_symbol;

    @Column(name="is_end", columnDefinition = "bit default 0")
    private Boolean is_end;

    public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Department getParent_department() {
		return parent_department;
	}

	public void setParent_department(Department parent_department) {
		this.parent_department = parent_department;
	}

	public List<Department> getSub_departments() {
		return sub_departments;
	}

	public void setSub_departments(List<Department> sub_departments) {
		this.sub_departments = sub_departments;
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
    
}
