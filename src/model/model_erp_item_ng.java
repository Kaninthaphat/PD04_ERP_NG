package model;

import java.util.Date;

public class model_erp_item_ng {
	private int product_group_id;
	private String item_product_group;
	private String ng_group;
	private String item_no;
	private String Type_NG;
	private String row_number;
	private String color_text;
	private String Slip_number;
	
	private int id;
	private int process_id;
	private String Process_Name;
	private int process_code;
	private String Defec_Date;
	private Date Defec_Date_d;
	private int In_QTY;
	private int out_qty;
	private int ng_qty;
	
	private String server_name;
	private int server_id;
	
	private int S_count;
	
	public String getItem_product_group() {
		return item_product_group;
	}
	public void setItem_product_group(String item_product_group) {
		this.item_product_group = item_product_group;
	}
	public String getNg_group() {
		return ng_group;
	}
	public void setNg_group(String ng_group) {
		this.ng_group = ng_group;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	
	public int getNg_qty() {
		return ng_qty;
	}
	public void setNg_qty(int ng_qty) {
		this.ng_qty = ng_qty;
	}
	
	public String getColor_text() {
		return color_text;
	}
	public void setColor_text(String color_text) {
		this.color_text = color_text;
	}
	public String getSlip_number() {
		return Slip_number;
	}
	public void setSlip_number(String slip_number) {
		Slip_number = slip_number;
	}
	public String getProcess_Name() {
		return Process_Name;
	}
	public void setProcess_Name(String process_Name) {
		Process_Name = process_Name;
	}
	public String getDefec_Date() {
		return Defec_Date;
	}
	public void setDefec_Date(String defec_Date) {
		Defec_Date = defec_Date;
	}
	public int getIn_QTY() {
		return In_QTY;
	}
	public void setIn_QTY(int in_QTY) {
		In_QTY = in_QTY;
	}
	public String getRow_number() {
		return row_number;
	}
	public void setRow_number(String row_number) {
		this.row_number = row_number;
	}
	public int getOut_qty() {
		return out_qty;
	}
	public void setOut_qty(int out_qty) {
		this.out_qty = out_qty;
	}
	public String getType_NG() {
		return Type_NG;
	}
	public void setType_NG(String type_NG) {
		Type_NG = type_NG;
	}
	public int getS_count() {
		return S_count;
	}
	public void setS_count(int s_count) {
		S_count = s_count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	
	public int getProcess_code() {
		return process_code;
	}
	public void setProcess_code(int process_code) {
		this.process_code = process_code;
	}
	public String getServer_name() {
		return server_name;
	}
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}
	public int getServer_id() {
		return server_id;
	}
	public void setServer_id(int server_id) {
		this.server_id = server_id;
	}
	public Date getDefec_Date_d() {
		return Defec_Date_d;
	}
	public void setDefec_Date_d(Date defec_Date_d) {
		Defec_Date_d = defec_Date_d;
	}
	public int getProduct_group_id() {
		return product_group_id;
	}
	public void setProduct_group_id(int product_group_id) {
		this.product_group_id = product_group_id;
	}
	
	
}

