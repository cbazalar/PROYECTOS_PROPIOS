package biz.belcorp.ssicc.web;

import java.io.File;
import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import biz.belcorp.ssicc.web.framework.base.action.MBaseAbstractJSF;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

@ManagedBean
@ApplicationScoped
public class MDataExporterBean extends MBaseAbstractJSF {

    private static final long serialVersionUID = 4421693389464428017L;

	/**
	 * Metodo usado para el Post Procesamiento de la Exportacion de los XLS usando p:dataExporter
	 * @param document
	 */
	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
			header.getCell(i).setCellStyle(cellStyle);
			sheet.autoSizeColumn(i);
		}
	}
	
	/**
	 * Metodo usado para el Pre Procesamiento de la Exportacion de los PDF usando p:dataExporter
	 * @param document
	 * @throws IOException
	 * @throws BadElementException
	 * @throws DocumentException
	 */
	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.setPageSize(PageSize.A4.rotate());
	    pdf.open();  
	    pdf.add(getImage(File.separator + "resources" + File.separator + "images" + File.separator + "belcorp.jpg"));
	}
	
	/**
	 * Metodo usado para el Post Procesamiento de la Exportacion de los PDF usando p:dataExporter
	 * @param document
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void postProcessPDF(Object document) throws IOException, DocumentException {
	    final Document pdf = (Document) document;
	    pdf.setPageSize(PageSize.A4.rotate());
	     
	}
	
	
	/**
	 * Metodo usado para obtener una imagen
	 * @param imageName
	 * @return
	 * @throws IOException
	 * @throws BadElementException
	 */
	private Image getImage(String imageName) throws IOException, BadElementException {
	    final Image image = Image.getInstance(getAbsolutePath(imageName));
	    return image;
	}
	
	/**
	 * Metodo para obtener la URL de un resource
	 * @param imageName
	 * @return
	 */
	private String getAbsolutePath(String imageName) {
	    final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	    final StringBuilder logo = new StringBuilder().append(servletContext.getRealPath(""));
	    logo.append(imageName);
	    return logo.toString();
	}

		
    	
}
