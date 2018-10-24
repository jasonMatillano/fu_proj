package w.fujiko.util.common.constants;

public class ProductConstants {
	
	public static final String LONG_PAGE_NAME = "商品分類マスタメンテナンス";
	public static final String SHORT_PAGE_NAME = "商品分類";
	
	/* HEADER CONSTANTS */
	public static final String PRODUCT_CLASS_CODE = "大分類名ＣＤ";
	public static final String PRODUCT_CLASS_NAME = "大分類名";
	public static final String IS_END = "終了";
	public static final String PRODUCT_ITEM_CODE = "中分類名ＣＤ";
	public static final String PRODUCT_ITEM_NAME = "中分類名";
	
	/* EXTRACTED MODEL FIELDS CONSTANTS*/
	public static final String FIELD_PRODUCT_CLASS_CODE = "productCode";
	public static final String FIELD_PRODUCT_CLASS_NAME = "productName";
	public static final String FIELD_PRODUCT_IS_END = "productEnd";
	public static final String FIELD_PRODUCT_ITEM_CODE = "productItemCode";
	public static final String FIELD_PRODUCT_ITEM_NAME = "productItemName";
	public static final String FIELD_PRODUCT_ITEM_IS_END = "productItemEnd";
	
	/* ENTITY FIELDS CONSTANTS */
	public static final String FIELD_CODE = "code";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_PRODUCT_CLASSIFICATION = "productClassification";
	public static final String FIELD_PRODUCT_CLASS_ID	 = "productClassId";
	public static final String FIELD_IS_END = "isEnd";
	public static final String FIELD_PRODUCT_ITEMS = "productItems";
	
	/* ERROR MESSAGES */
	public static final String MSG_CONFLICT = "{ message: The product classification code already exists. }";
	public static final String MSG_CONFLICT_ITEM = "{ message: The product classification item code already exists. }";
	public static final String MSG_500 = "{ status: 500, message: Server Error. }";

}