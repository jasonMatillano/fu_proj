package w.fujiko.model.masters.products;

public class ProductExtractionModel {
	
	private String productCode;
	private String productName;
	private boolean productEnd;
	private String productItemCode;
	private String productItemName;
	private boolean productItemEnd;
	
	public ProductExtractionModel(String productCode, String productName, boolean productEnd,
			String productItemCode, String productItemName, boolean productItemEnd) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productEnd = productEnd;
		this.productItemCode = productItemCode;
		this.productItemName = productItemName;
		this.productItemEnd = productItemEnd;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public boolean isProductEnd() {
		return productEnd;
	}
	public void setProductEnd(boolean productEnd) {
		this.productEnd = productEnd;
	}
	public String getProductItemCode() {
		return productItemCode;
	}
	public void setProductItemCode(String productItemCode) {
		this.productItemCode = productItemCode;
	}
	public String getProductItemName() {
		return productItemName;
	}
	public void setProductItemName(String productItemName) {
		this.productItemName = productItemName;
	}
	public boolean isProductItemEnd() {
		return productItemEnd;
	}
	public void setProductItemEnd(boolean productItemEnd) {
		this.productItemEnd = productItemEnd;
	}
	
}