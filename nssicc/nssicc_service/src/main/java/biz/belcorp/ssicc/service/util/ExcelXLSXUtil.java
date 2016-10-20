package biz.belcorp.ssicc.service.util;

import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import au.com.bytecode.opencsv.CSVReader;

/**
 * The Class ExcelXLSXUtil.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 21/03/2014
 */
public class ExcelXLSXUtil {
	protected final static Log log = LogFactory.getLog(ExcelXLSXUtil.class);
	
	public ExcelXLSXUtil() {
		super();
	}

	/**
	 * Genera el archivo en formato estandar xlsx.
	 *
	 * @param csvFile the csv file
	 * @param xlsxFile the xlsx file
	 * @throws Exception the exception
	 */
	public void generarExcelEstandarReporte(String csvFile, String xlsxFile)throws Exception{
		ExcelXLSXUtil.convertircsvToxlsx(csvFile, xlsxFile);		
	}
	
	/**
	 * Generara el estilo de cabecera en formato estandar.
	 *
	 * @param cellStyleCab the cell style cab
	 * @return the cell style
	 */
	public static CellStyle generarCabeceraEstandarReporte(SXSSFWorkbook workbook){
		CellStyle cellStyleCab;		
        cellStyleCab = ExcelXLSXUtil.generarCellEstandarReporte(workbook);
        Font fontCab = ExcelXLSXUtil.generarFontEstandarReporte(workbook);
        fontCab.setBoldweight(Font.BOLDWEIGHT_BOLD);
        cellStyleCab.setFont(fontCab);
		return cellStyleCab;
	}
	
	/**
	 * Generara la celda estandar.
	 *
	 * @param workbook the workbook
	 * @return the cell style
	 */
	public static CellStyle generarCellEstandarReporte(SXSSFWorkbook workbook){
		CellStyle cellStyleCab = workbook.createCellStyle();
		cellStyleCab.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleCab.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleCab.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleCab.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleCab.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleCab.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleCab.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleCab.setTopBorderColor(IndexedColors.BLACK.getIndex());
        
        HSSFColor myColor = new HSSFWorkbook().getCustomPalette().findSimilarColor(204, 204, 204);
    	short palIndex = myColor.getIndex();
    	cellStyleCab.setFillForegroundColor(palIndex);
    	cellStyleCab.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    	return cellStyleCab;
	}
	
	/**
	 * Generara el tipo de letra estandar en el reporte.
	 *
	 * @param workbook the workbook
	 * @return the font
	 */
	public static Font generarFontEstandarReporte(SXSSFWorkbook workbook){
		Font fontCab = workbook.createFont();
		fontCab.setFontHeightInPoints((short)10);
        fontCab.setFontName("Arial");        
        fontCab.setColor(HSSFColor.AUTOMATIC.index);
		return fontCab;
	}
	
	/**
	 * Convierte archivo CSV a xlsx en formato estandar.
	 *
	 * @param csvFile the csv file
	 * @param xlsxFile the xlsx file
	 * @throws Exception the exception
	 */
	public static void convertircsvToxlsx(String csvFile, String xlsxFile) throws Exception{
    	SXSSFWorkbook workbook = new SXSSFWorkbook(); 
        Sheet sheet = workbook.createSheet("Hoja1");
        int RowNum=0;
        int numColumnas = 0;
        CSVReader reader = new CSVReader(new FileReader(csvFile),',');
        String [] nextLine;
        CellStyle cellStyleCab = ExcelXLSXUtil.generarCabeceraEstandarReporte(workbook);
        CellStyle cellStyleBody = workbook.createCellStyle();
    	cellStyleBody.setFont(ExcelXLSXUtil.generarFontEstandarReporte(workbook));    	
        while((nextLine = reader.readNext()) !=null){            

            numColumnas = nextLine.length;
            Row currentRow= sheet.createRow(RowNum);
            for(int i=0;i<numColumnas;i++){
            	currentRow.createCell(i);	            	
                if(RowNum == 0){
                	currentRow.getCell(i).setCellStyle(cellStyleCab);
                }else{
                	currentRow.getCell(i).setCellStyle(cellStyleBody);
                }                
                currentRow.getCell(i).setCellValue(StringUtil.extraeValor(nextLine[i]));
            }
            RowNum++;
        }
        for (int i = 0; i < numColumnas; i++) {
    		sheet.autoSizeColumn(i);
		}
        FileOutputStream fileOutputStream =  new FileOutputStream(xlsxFile);
        workbook.write(fileOutputStream);
        reader.close();
        fileOutputStream.close();        
        log.debug("Conversion finalizado");
    }
	
}
