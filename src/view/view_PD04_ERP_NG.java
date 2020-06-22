package view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModelList;
import connect.SqlSelection_erp_21;
import connect.SqlSelection;
import general.DateTime;
import general.ExportExcel;
import model.model_erp_item_ng;

public class view_PD04_ERP_NG {

	private List<model_erp_item_ng> model_list_item;
	private model_erp_item_ng select_item;
	
	private ListModelList<model_erp_item_ng> model_list_itemGroup;
	private model_erp_item_ng select_itemGroup;
	
	private String select_itemGroup_name="";
	private String get_item_no;
	private Date startDate;
	private Date endDate;
	private String startDate_string;
	private String endDate_string;
	private String searchValue="";

	
	public ListModelList<model_erp_item_ng> getModel_list_itemGroup() {
		return model_list_itemGroup;
	}
	public void setModel_list_itemGroup(ListModelList<model_erp_item_ng> model_list_itemGroup) {
		this.model_list_itemGroup = model_list_itemGroup;
	}
	public model_erp_item_ng getSelect_itemGroup() {
		return select_itemGroup;
	}
	public void setSelect_itemGroup(model_erp_item_ng select_itemGroup) {
		this.select_itemGroup = select_itemGroup;
	}
	public List<model_erp_item_ng> getModel_list_item() {
		return model_list_item;
	}
	public void setModel_list_item(List<model_erp_item_ng> model_list_item) {
		this.model_list_item = model_list_item;
	}
	public model_erp_item_ng getSelect_item() {
		return select_item;
	}
	public void setSelect_item(model_erp_item_ng select_item) {
		this.select_item = select_item;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStartDate_string() {
		return startDate_string;
	}
	public void setStartDate_string(String startDate_string) {
		this.startDate_string = startDate_string;
	}
	public String getEndDate_string() {
		return endDate_string;
	}
	public void setEndDate_string(String endDate_string) {
		this.endDate_string = endDate_string;
	}
	public String getSelect_itemGroup_name() {
		return select_itemGroup_name;
	}
	public void setSelect_itemGroup_name(String select_itemGroup_name) {
		this.select_itemGroup_name = select_itemGroup_name;
	}
	public String getGet_item_no() {
		return get_item_no;
	}
	public void setGet_item_no(String get_item_no) {
		this.get_item_no = get_item_no;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	@Init
	public void init() {
		  System.out.println("@Init");
		  load_itemGroup();
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
        System.out.println("@AfterCompose");        
        
        Calendar c = Calendar.getInstance();
		endDate = c.getTime();
		c.set(Calendar.DAY_OF_MONTH, 1);				
		startDate = c.getTime();
		
        set_date();
    }
	
	public void set_date(){		
		  startDate_string = DateTime.set_DateString(startDate);
		  endDate_string = DateTime.set_DateString(endDate);
	}
	
	public void load_itemGroup()
	{		
		ResultSet rs_ = null;
        List<model_erp_item_ng> model_list = new ArrayList<>();
        SqlSelection sqlsel = new SqlSelection();
        String StrSQL = "EXEC [SUSPD5001_PRD_GROUP] ";	
        System.out.println(StrSQL);
        try {
        	rs_ = sqlsel.getReSultSQL(StrSQL);      	
            while (rs_.next()) {
            	model_erp_item_ng model = new model_erp_item_ng();    
            	model.setItem_product_group(rs_.getString("ITEM_PRODUCT_GROUP"));                       		                 	 
            	model_list.add(model);            	
            }
            model_list_itemGroup = new ListModelList<model_erp_item_ng>(model_list);
            setModel_list_itemGroup(model_list_itemGroup);          
       
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				sqlsel.closeConnection();
			} catch (Exception e) {			
				e.printStackTrace();
			}
        }
	}
	
	public static String formatSring(int stock) {
		  NumberFormat formatter = new DecimalFormat("#,##0");             
	      return formatter.format(stock);
	}
	
	public static String formatSring2(double stock) {
		  NumberFormat formatter = new DecimalFormat("#,###.##");             
	      return formatter.format(stock);
	}
	
	public static String formatDateString(Date value){
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return  df.format(value);
	}
	
	@Command
	@NotifyChange("*")
	public void get_data(){	
		set_date();
        ResultSet rs_result = null;	          
        SqlSelection_erp_21 sqlsel = new SqlSelection_erp_21();
        String StrSQL = "EXEC [SUS_NG_DETAIL] @START_DATE = '"+startDate_string+"',"
        		+ "@END_DATE = '"+endDate_string+"',"
        		+ "@ITEM_PRODUCT_GROUP='"+ select_itemGroup_name +"',"
        		+ "@SearchValue='"+searchValue+"'";
        
        System.out.println(StrSQL);
       
        try {
        	int i = 1;
        	rs_result = sqlsel.getReSultSQL(StrSQL);
        	List<model_erp_item_ng> model_list = new ArrayList<>();
            while (rs_result.next()) {                     	        		            		                    	            		            		            			            		            	
		            	
            	model_erp_item_ng model = new model_erp_item_ng();  
		            	model.setRow_number(i+"");
		            	model.setNg_group(rs_result.getString("NG_GROUP"));  
		            	model.setItem_no(rs_result.getString("ITEM_NO")); 
		            	model.setType_NG(rs_result.getString("Type_NG"));
		            	model.setSlip_number(rs_result.getString("Slip_number"));
		            	model.setProcess_Name(rs_result.getString("Process_Name"));
		            	model.setNg_qty(rs_result.getInt("NG_QTY"));
		            	model.setDefec_Date_d(rs_result.getDate("NG_Date"));
		            	model_list.add(model); 
		            	i++;
		            	
            }   
        	
            model_list_item = new ListModelList<model_erp_item_ng>(model_list);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				sqlsel.closeConnection();
			} catch (Exception e) {			
				e.printStackTrace();
			}
        }		 
    }
	
	@Command
	@NotifyChange("*")
	public void onCallExcel() {						
		if(model_list_item.size() > 0)
			{	
			try {
				PrintToXls();
			} catch (IOException e) {				
					e.printStackTrace();
			} 
		}                
	}
	
	private void PrintToXls() throws IOException{								
		
		String title_text = formatDateString(startDate)+" To "+formatDateString(endDate);
		String title = "NG_Detail_"+select_itemGroup_name;
				
		ExportExcel excel = new ExportExcel();
		excel.createSheet(title);
			
			excel.setColumnWidth(0, 2);
			excel.setColumnWidth(1, 5);
			excel.setColumnWidth(2, 5);
			excel.setColumnWidth(3, 10);
			excel.setColumnWidth(4, 4);
			excel.setColumnWidth(5, 12);
			excel.setColumnWidth(6, 6);
			excel.setColumnWidth(7, 3);
			
			excel.setStyle("H", ExportExcel.CenterStyle, ExportExcel.FontHeaderStyle);
			excel.setStyle("HD", ExportExcel.CenterStyle, ExportExcel.FontColumnStyle);
			excel.setStyle("HEAD", ExportExcel.CenterStyle, ExportExcel.FontColumnStyle, "BTLR");			
			excel.setStyle("PL", ExportExcel.LeftStyle, ExportExcel.FontDetailStyle);
			excel.setStyle("DL", ExportExcel.LeftStyle, ExportExcel.FontDetailStyle,"LR");
			excel.setStyle("DC", ExportExcel.CenterStyle, ExportExcel.FontDetailStyle,"LR");
			excel.setStyle("DR", ExportExcel.RightStyle, ExportExcel.FontDetailStyle,"LR");
			excel.setStyle("LB", ExportExcel.CenterStyle, ExportExcel.FontDetailStyle, "BTLR");	
			excel.setStyle("T", ExportExcel.CenterStyle, ExportExcel.FontColumnStyle, "T");
			excel.setStyle("TB", ExportExcel.CenterStyle, ExportExcel.FontColumnStyle, "TB");
			excel.setStyle("TLR", ExportExcel.CenterStyle, ExportExcel.FontColumnStyle, "TLR");
			excel.setStyle("DRD", ExportExcel.DoubleStyle, ExportExcel.FontDetailStyle,"LR");
			
			
			excel.setCellValue(1, 1, title, excel.getStyle("H"));
			excel.setCellValue(2, 1, title_text, excel.getStyle("HD"));
			excel.setMergedRegion(1,(short)1 ,1 ,(short)7);
			excel.setMergedRegion(2,(short)1 ,2 ,(short)7);
			
			excel.setCellValue(3, 0, "#", excel.getStyle("HEAD"));
			excel.setCellValue(3, 1, "NG_GROUP", excel.getStyle("HEAD"));
			excel.setCellValue(3, 2, "ITEM_NO", excel.getStyle("HEAD"));
			excel.setCellValue(3, 3, "Type NG", excel.getStyle("HEAD"));
			excel.setCellValue(3, 4, "Date", excel.getStyle("HEAD"));
			excel.setCellValue(3, 5, "Process_Name", excel.getStyle("HEAD"));			
			excel.setCellValue(3, 6, "Slip_number", excel.getStyle("HEAD"));	
			excel.setCellValue(3, 7, "NG", excel.getStyle("HEAD"));
			
			int rowNum=4;			
	
			for(model_erp_item_ng report : model_list_item){
				
				excel.setCellValue(rowNum, 0, report.getRow_number(), excel.getStyle("DC"));
				excel.setCellValue(rowNum, 1, report.getNg_group(), excel.getStyle("DL"));
				excel.setCellValue(rowNum, 2, report.getItem_no(), excel.getStyle("DL"));
				excel.setCellValue(rowNum, 3, report.getType_NG(), excel.getStyle("DL"));
				excel.setCellValue(rowNum, 4, formatDateString(report.getDefec_Date_d()), excel.getStyle("DC"));	
				excel.setCellValue(rowNum, 5, report.getProcess_Name(), excel.getStyle("DL"));
				excel.setCellValue(rowNum, 6, report.getSlip_number(), excel.getStyle("DL"));				
				excel.setCellValue(rowNum, 7, report.getNg_qty(), excel.getStyle("DRD"));	
				rowNum ++;
				
			}
			
			 excel.setBlankRow(rowNum, 0, 7, excel.getStyle("LB"));
			 rowNum++;
			 
			 String prefix = title +"_"+ title_text;
			 String fileName = prefix + ".xls";
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 excel.getHssfWorkbook().write(bos);
	         ByteArrayInputStream pis = new ByteArrayInputStream(bos.toByteArray());
	         AMedia a = new AMedia(fileName, "xls", "application/vnd.ms-excel;charset=UTF-8", pis);  
	         Filedownload.save(a);
		
	}
	
	
}
