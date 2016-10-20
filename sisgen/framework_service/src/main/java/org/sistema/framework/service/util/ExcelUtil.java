package org.sistema.framework.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.sistema.framework.dao.Constants;
import org.sistema.framework.dao.model.Base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Metodos utilitarios para la lectura de archivos excel.
 * 
 */
public class ExcelUtil {

	protected final Log log = LogFactory.getLog(getClass());
	
	/**    
	 * Este objeto de la clase HSSFWorkbook contiene todas las hojas o   
	 * pestaas de un archivo xls   
	 * */  
	private HSSFWorkbook wb;
	private HSSFSheet ws;
	
	/*private XSSFWorkbook wbXSSF;
	private XSSFSheet wsXSSF;*/
	
	private FileInputStream input;
	private FileOutputStream output;
	Iterator itRows;
	
	private HSSFFont font1;
	private HSSFFont font2;
	private HSSFCellStyle cstyle1;
	private HSSFCellStyle cstyle2;
	
	/*private XSSFFont font1XSSF;
	private XSSFFont font2XSSF;
	private XSSFCellStyle cstyle1XSSF;
	private XSSFCellStyle cstyle2XSSF;*/
	private short registro;
	private short columna;
	private int   registroExcelGrande;
	

	private InputStream inputStream;
	
	private boolean manejarConBigDecimal = false;
	
	/**   
	 * Crea una instancia de ExcelUtil con una ruta  y nombre de archivo xls Especfico. 
	 * */  
	 public ExcelUtil(String path, String fileName) throws Exception { 
		 //Esto crea un objeto que lee los bytes del archivo
		 File file = new File(path, fileName);
		 this.input = new FileInputStream(file);      
		 POIFSFileSystem fs = new POIFSFileSystem(input);
		 
		 //Una vez creado el workbook tenemos acceso a las hojas (pestaas)       
		 //incluidas en el archivo de excel    
		 this.wb = new HSSFWorkbook(fs); 
	 }
	 
	/**   
	 * Crea una instancia de ExcelUtil con una ruta a un archivo xls Especfico. 
	 * */  
	 public ExcelUtil(String fileName) throws Exception { 
		 //Esto crea un objeto que lee los bytes del archivo      
		 File file = new File(fileName);
		 this.input = new FileInputStream(file);      
		 POIFSFileSystem fs = new POIFSFileSystem(input);
		 
		 //Una vez creado el workbook tenemos acceso a las hojas (pestaas)       
		 //incluidas en el archivo de excel    
		 this.wb = new HSSFWorkbook(fs);  
	 }

	 /**
	 * Crea una instancia de ExcelUtil de acuerdo a un archivo xls Especfico. 
	 * @param file
	 * @throws Exception
	 */
	public ExcelUtil(File file) throws Exception { 
		 //Esto crea un objeto que lee los bytes del archivo
		 this.input = new FileInputStream(file);      
		 POIFSFileSystem fs = new POIFSFileSystem(input);
		 
		 //Una vez creado el workbook tenemos acceso a las hojas (pestaas)       
		 //incluidas en el archivo de excel    
		 this.wb = new HSSFWorkbook(fs);  
	 }
	 
	 /**
	 * Utilizado para grabar archivos Excel
	 * @param fileInputStream
	 * @throws Exception
	 */
	public ExcelUtil(FileOutputStream FileOutputStream) throws Exception { 
		 //Esto crea un objeto que lee los bytes del archivo
		 this.output = FileOutputStream;    
		 // create a new workbook object; note that the workbook
		 // and the file are two separate things until the very
		 // end, when the workbook is written to the file.
		 //this.wb = new HSSFWorkbook();
		 this.wb = new HSSFWorkbook();

		 // create a new worksheet
		 this.ws = wb.createSheet();  
		 
		 /* Generacion de estilos por default */
		 this.generarEstilosDefault();
		 this.columna  = 0;
		 this.registro = 0;
		 this.registroExcelGrande = 0;
	 }
	
	/**
	 * Utilizado para grabar archivos Excel
	 * @param fileInputStream
	 * @throws Exception
	 */
	public ExcelUtil(FileOutputStream FileOutputStream, String fileName, String nombreHoja) throws Exception { 
		 //Esto crea un objeto que lee los bytes del archivo
		 this.output = FileOutputStream;
		 if (fileName == null) {
			 this.wb = new HSSFWorkbook();
  		     //create a new worksheet
		     this.ws = wb.createSheet();
		 }    
		 else {
			 File file = new File(fileName);
			 this.input = new FileInputStream(file);      
			 POIFSFileSystem fs = new POIFSFileSystem(input);
			 this.wb = new HSSFWorkbook(fs);
			 this.ws = wb.createSheet(nombreHoja);
		 }	 
		 
		 /* Generacion de estilos por default */
		 this.generarEstilosDefault();
		 this.columna  = 0;
		 this.registro = 0;
	 }
	
	/**
	 * @param inputStream
	 * @throws Exception
	 */
	public ExcelUtil(InputStream inputStream) throws Exception { 
		 //Esto crea un objeto que lee los bytes del archivo
		 this.inputStream = inputStream;      
		 POIFSFileSystem fs = new POIFSFileSystem(inputStream);
		 
		 //Una vez creado el workbook tenemos acceso a las hojas (pestanas)       
		 //incluidas en el archivo de excel    
		 this.wb = new HSSFWorkbook(fs);  
	 }
	
	/**
	 * Utilizado para grabar archivos Excel usando XSSF o HSSF
	 * @param fileInputStream
	 * @param xlsx
	 * @throws Exception
	 */
	/*public ExcelUtil(FileOutputStream FileOutputStream, boolean xlsx) throws Exception { 
		 //Esto crea un objeto que lee los bytes del archivo
		 this.output = FileOutputStream;    
		 // create a new workbook object; note that the workbook
		 // and the file are two separate things until the very
		 // end, when the workbook is written to the file.
		 //this.wb = new HSSFWorkbook();
		 if (xlsx) { 
			 this.wbXSSF = new XSSFWorkbook();
			 this.wsXSSF = wbXSSF.createSheet();
			 // Generacion de estilos por default 
			 this.generarEstilosDefaultXSSF();
		 }	 
		 else { 
			 this.wb = new HSSFWorkbook();
			 this.ws = wb.createSheet();
			 // Generacion de estilos por default 
			 this.generarEstilosDefault();
		 }	 
		 this.columna  = 0;
		 this.registro = 0;
	 }*/
	
	/**
	 * Cierra Archivo Output de Formato Excel
	 * @throws Exception
	 */
	public void cerrarOutput() throws Exception  {
		this.wb.write(this.output);
		this.output.close();
	}
	
	/**
	 * Cierra Archivo Output de Formato Excel
	 * @throws Exception
	 */
	/*public void cerrarOutputXSSF() throws Exception  {
		this.wbXSSF.write(this.output);
		this.output.close();
	}*/

	/**
	 * @throws Exception
	 */
	public void generarEstilosDefault() throws Exception { 
		//need to be defined before they are used
		cstyle1 = wb.createCellStyle();
		cstyle2 = wb.createCellStyle();

		// create two font objects for formatting
		font1 = wb.createFont();
		font2 = wb.createFont();
		
		font1.setFontHeightInPoints((short) 10);
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		//set font 2 to 10 point red type
		font2.setFontHeightInPoints((short) 9);
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font2.setColor(HSSFFont.COLOR_NORMAL);
		
		//for cell style 1, use font 1 and set data format
		cstyle1.setFont(font1);

		//for cell style 2, use font 2, set a thin border, text format
		//cstyle2.setBorderBottom(cstyle2.BORDER_THIN);
		cstyle2.setFont(font2);
	}
	
	/**
	 * @throws Exception
	 */
	/*public void generarEstilosDefaultXSSF() throws Exception { 
		//need to be defined before they are used
		cstyle1XSSF = wbXSSF.createCellStyle();
		cstyle2XSSF = wbXSSF.createCellStyle();

		// create two font objects for formatting
		font1XSSF = wbXSSF.createFont();
		font2XSSF = wbXSSF.createFont();
		
		font1XSSF.setFontHeightInPoints((short) 10);
		font1XSSF.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

		//set font 2 to 10 point red type
		font2XSSF.setFontHeightInPoints((short) 10);
		font2XSSF.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		font2XSSF.setColor(XSSFFont.COLOR_NORMAL);
		
		//for cell style 1, use font 1 and set data format
		cstyle1XSSF.setFont(font1XSSF);

		//for cell style 2, use font 2, set a thin border, text format
		cstyle2XSSF.setBorderBottom(cstyle2XSSF.BORDER_THIN);
		cstyle2XSSF.setFont(font2XSSF);
	}*/
	 
	 /**
	 * @param sheetInt
	 * @throws Exception
	 */
	public void initSheet(int sheetInt) throws Exception { 
		//Obtenemos la hoja o pestana elegida       
		HSSFSheet sheet = wb.getSheetAt(sheetInt); 
        		
		//Para recorrer las filas utilizamos el Iterador de filas "rows"      
		this.itRows = sheet.rowIterator();  
	 }
	 
	/**   
	 * Esta funcin se encarga de leer la hoja o pestaiaegida   
	 * Para este caso nos referimos a un nmero especifico   
	 * segn el rden en que aparecen las pestaas, si un archivo   
	 * xls solo cuenta con una hoja, para referirse a esa hoja   
	 * utilizamos el primer ndice que seria cero.   
	 * La funcin devuelve un vector de vectores
	 * */
	public Vector getHoja(int sheetInt) {  
		//Aqui recopilamos todas las filas leidas en la hoja       
		Vector sheetVector = new Vector(); 
		
		//Obtenemos la hoja o pestaiaegida       
		HSSFSheet sheet = wb.getSheetAt(sheetInt); 
		
		//Para recorrer las filas utilizamos el Iterador de filas "rows"      
		Iterator rows = sheet.rowIterator();
		
		//Hay que verificar si hay alguna otra fila que leer      
		while (rows.hasNext()) {     
			//Es por que si hay otra fila que leer que obtenemos la fila que sigue          
			HSSFRow row = (HSSFRow)rows.next();
			
			//Aqui se colocan todos los valores almacenados en dicha fila.         
			Vector rowVector = new Vector();
			
			//recorremosr uno a uno las celdas    
			for (short c=row.getFirstCellNum(); c<row.getLastCellNum(); c++) {
				HSSFCell cell = row.getCell( c );

				if( cell != null ) {
					switch (cell.getCellType()) {              
						// La celda contiene texto        
						case HSSFCell.CELL_TYPE_STRING:                  
							rowVector.add(cell.getStringCellValue());                  
							break;    
							
						// El caso de que la celda contenga un valor nmerico               
						case HSSFCell.CELL_TYPE_NUMERIC:                  
							rowVector.add(String.valueOf(cell.getNumericCellValue()));                  
							break;              
							
						// El caso de que la celda contenga un valor con formula					
						case HSSFCell.CELL_TYPE_FORMULA:                  
							rowVector.add(String.valueOf(cell.getNumericCellValue()));                  
							break;              
						
						// En este caso para este tutorial no guardamos nada */              
						default:                  
							rowVector.add("");
					}
				} else {         
					rowVector.add(null);	
				}
			}	
			System.out.println(rowVector);
			// Una vez leidas las celdas, el vector de la fila es agregado           
			// al vector de vectores           
			sheetVector.add(rowVector);      
		}          
		
		// Se devuevlen los datos leidos en un vector de vectores.      
		return sheetVector;  
	}

	/**   
	 * Esta funcin se encarga de leer una fila de una hoja elegida   
	 * Para este caso nos referimos a un nmero especifico   
	 * segn el rden en que aparecen las pestaas, si un archivo   
	 * xls solo cuenta con una hoja, para referirse a esa hoja   
	 * utilizamos el primer ndice que seria cero e igual seria
	 * el caso para la primera fila   
	 * La funcin devuelve un vector 
	 * */
	public Vector getFila(int sheetInt, int rowInt) {  
		//Aqui se colocan todos los valores almacenados en dicha fila.       
		Vector rowVector = new Vector(); 
		 
		//Obtenemos la hoja o pestaiaegida       
		HSSFSheet sheet = wb.getSheetAt(sheetInt); 
		
		//Para recorrer las filas utilizamos el Iterador de filas "rows"
		HSSFRow row = sheet.getRow(rowInt);
			
		//recorremosr uno a uno las celdas    
		for (short c=row.getFirstCellNum(); c<row.getLastCellNum(); c++) {
			HSSFCell cell = row.getCell( c );

			if( cell != null ) {
				switch (cell.getCellType()) {              
					// La celda contiene texto        
					case HSSFCell.CELL_TYPE_STRING:                  
						rowVector.add(cell.getStringCellValue());                  
						break;    
						
					// El caso de que la celda contenga un valor nmerico               
					case HSSFCell.CELL_TYPE_NUMERIC:                  
						rowVector.add(String.valueOf(cell.getNumericCellValue()));                  
						break;              
						
					// El caso de que la celda contenga un valor con formula					
					case HSSFCell.CELL_TYPE_FORMULA:                  
						rowVector.add(String.valueOf(cell.getNumericCellValue()));                  
						break;              
					
					// En este caso para este tutorial no guardamos nada */              
					default:                  
						rowVector.add("");
				}
			} else {         
				rowVector.add(null);	
			}
		}	
		
		// Se devuevlen los datos leidos en un vector de vectores.      
		return rowVector;  
	}

	/**   
	 * Esta funcin se encarga de leer una fila de una hoja elegida   
	 * Para este caso nos referimos a un nmero especifico   
	 * segn el rden en que aparecen las pestaas, si un archivo   
	 * xls solo cuenta con una hoja, para referirse a esa hoja   
	 * utilizamos el primer ndice que seria cero e igual seria
	 * el caso para la primera fila, trae la informacion de la columna
	 * asi como la posicion de la columna  
	 * La funcin devuelve un vector 
	 * */
	public List getFilaConColumnaNoNulos(int sheetInt, int rowInt) {  
		//Aqui se colocan todos los valores almacenados en dicha fila.       
		List rowList = new ArrayList(); 
		 
		//Obtenemos la hoja o pestaiaegida       
		HSSFSheet sheet = wb.getSheetAt(sheetInt); 
		
		//Obtenemos la fila deseada
		HSSFRow row = sheet.getRow(rowInt);
			
		//recorremos uno a uno las celdas    
		for (short c=row.getFirstCellNum(); c<row.getLastCellNum(); c++) {
			HSSFCell cell = row.getCell( c );
			
			if( cell != null ) {
				Base base = new Base();
				base.setCodigo(String.valueOf(c));
				
				switch (cell.getCellType()) {              
					// La celda contiene texto        
					case HSSFCell.CELL_TYPE_STRING:                  
						base.setDescripcion(cell.getStringCellValue());                  
						break;    
						
					// El caso de que la celda contenga un valor nmerico               
					case HSSFCell.CELL_TYPE_NUMERIC:                  
						base.setDescripcion(String.valueOf(cell.getNumericCellValue()));                  
						break;              
						
					// El caso de que la celda contenga un valor con formula					
					case HSSFCell.CELL_TYPE_FORMULA:                  
						base.setDescripcion(String.valueOf(cell.getNumericCellValue()));                  
						break;              
					
					// En este caso para este tutorial no guardamos nada */              
					default:                  
						base.setDescripcion("");
				}
				rowList.add(base);
			} 
		}	
		
		// Se devuevlen los datos leidos en una lista de clases tipo Base      
		return rowList;  
	}

	/**   
	 * Esta funcin se encarga de leer una fila de una hoja elegida   
	 * Para este caso nos referimos a un nmero especifico   
	 * segn el rden en que aparecen las pestaas, si un archivo   
	 * xls solo cuenta con una hoja, para referirse a esa hoja   
	 * utilizamos el primer ndice que seria cero e igual seria
	 * el caso para la primera fila, trae la informacion de la columna
	 * asi como la posicion de la columna en un objeto Map
	 * */
	public Map getFilaConPosicionColumna(int sheetInt, int rowInt) {  
		//Aqui se colocan todos los valores almacenados en dicha fila.       
		Map rowMap = new HashMap(); 
		 
		//Obtenemos la hoja o pestaiaegida       
		HSSFSheet sheet = wb.getSheetAt(sheetInt); 
		
		//Obtenemos la fila deseada
		HSSFRow row = sheet.getRow(rowInt);
			
		//recorremos uno a uno las celdas    
		for (short c=row.getFirstCellNum(); c<row.getLastCellNum(); c++) {
			HSSFCell cell = row.getCell( c );
			
			if( cell != null ) {
				Base base = new Base();
				base.setCodigo(String.valueOf(c));
				
				switch (cell.getCellType()) {              
					// La celda contiene texto        
					case HSSFCell.CELL_TYPE_STRING:                  
						rowMap.put(String.valueOf(c), cell.getStringCellValue());                  
						break;    
						
					// El caso de que la celda contenga un valor nmerico               
					case HSSFCell.CELL_TYPE_NUMERIC:                  
						rowMap.put(String.valueOf(c), String.valueOf(cell.getNumericCellValue()));
						break;              
						
					// El caso de que la celda contenga un valor con formula					
					case HSSFCell.CELL_TYPE_FORMULA:                  
						rowMap.put(String.valueOf(c), String.valueOf(cell.getNumericCellValue()));
						break;              
					
					// En este caso para este tutorial no guardamos nada */              
					default:                  
						rowMap.put(String.valueOf(c), "");
				}
			} else {         
				rowMap.put(String.valueOf(c), null);	
			}
		}	
		
		// Se devuelven los datos leidos en una lista de clases tipo Base      
		return rowMap;  
	}

	/**   
	 * Esta funcin se encarga de leer una fila de una hoja elegida   
	 * Para este caso nos referimos a un nmero especifico   
	 * segn el rden en que aparecen las pestaas, si un archivo   
	 * xls solo cuenta con una hoja, para referirse a esa hoja   
	 * utilizamos el primer ndice que seria cero e igual seria
	 * el caso para la primera fila, trae la informacion de la columna
	 * asi como la posicion de la columna en un objeto Map
	 * */
	private Map getFilaConPosicionColumna(HSSFRow row) {  
		//Aqui se colocan todos los valores almacenados en dicha fila.       
		Map rowMap = new HashMap(); 
		 
		//recorremos uno a uno las celdas    
		for (short c=row.getFirstCellNum(); c<row.getLastCellNum(); c++) {
			HSSFCell cell = row.getCell( c );
			
			if( cell != null ) {
				switch (cell.getCellType()) {              
					// La celda contiene texto        
					case HSSFCell.CELL_TYPE_STRING:                  
						rowMap.put(String.valueOf(c), cell.getStringCellValue());                  
						break;    
						
					// El caso de que la celda contenga un valor nmerico               
					case HSSFCell.CELL_TYPE_NUMERIC:						
						double resultado = cell.getNumericCellValue();
						
						if(this.manejarConBigDecimal) {
							BigDecimal aux = new BigDecimal(resultado);
							rowMap.put(String.valueOf(c), aux.toString()); 
						} else {
						rowMap.put(String.valueOf(c), String.valueOf(resultado).trim()); 
						}	
						
						break;              
	            
						
					// El caso de que la celda contenga un valor con formula					
					case HSSFCell.CELL_TYPE_FORMULA:    
						try {
							rowMap.put(String.valueOf(c), cell.getStringCellValue().trim());
							break;  
						} catch (Exception e) {
							// TODO: handle exception
							rowMap.put(String.valueOf(c), null);
							break;
						}
						            
					
					// En este caso para este tutorial no guardamos nada */              
					default:                  
						rowMap.put(String.valueOf(c), "");
				}
			} else {         
				rowMap.put(String.valueOf(c), null);	
			}
		}	
		
		// Se devuevlen los datos leidos en una lista de clases tipo Base      
		return rowMap;  
	}
	
	public boolean hasNext() {
		return itRows.hasNext();
	}
	
	public Map next() {
		HSSFRow row = (HSSFRow)itRows.next();
		
		Map mapFila = getFilaConPosicionColumna(row);
		mapFila.put("rowNum", String.valueOf(row.getRowNum()+1));
		
		return mapFila;
	}	
	
	public void cerrar() {
		try {
			input.close();
			
			if(inputStream != null)
				inputStream.close();
			
		}catch(Exception ex) {}	
	}
	
			
	/**
	 * @return the wb
	 */
	public HSSFWorkbook getWb() {
		return wb;
	}

	/**
	 * @param wb the wb to set
	 */
	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	/**
	 * @return the ws
	 */
	public HSSFSheet getWs() {
		return ws;
	}

	/**
	 * @param ws the ws to set
	 */
	public void setWs(HSSFSheet ws) {
		this.ws = ws;
	}

	/**
	 * @return the input
	 */
	public FileInputStream getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(FileInputStream input) {
		this.input = input;
	}

	/**
	 * @return the output
	 */
	public FileOutputStream getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(FileOutputStream output) {
		this.output = output;
	}

	/**
	 * @return the columna
	 */
	public short getColumna() {
		return columna;
	}

	/**
	 * @param columna the columna to set
	 */
	public void setColumna(short columna) {
		this.columna = columna;
	}
	
	
	
	/**
	 * @return the registro
	 */
	public short getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(short registro) {
		this.registro = registro;
	}
	
	

	/**
	 * @return the registroExcelGrande
	 */
	public int getRegistroExcelGrande() {
		return registroExcelGrande;
	}

	/**
	 * @param registroExcelGrande the registroExcelGrande to set
	 */
	public void setRegistroExcelGrande(int registroExcelGrande) {
		this.registroExcelGrande = registroExcelGrande;
	}
	
	/**
	 * Genera celda en base a la informacion enviada con tipo de dato caracter
	 * @param valor
	 * @throws Exception
	 */
	public void generarCelda(String valor) throws Exception {
		this.generarCelda(Constants.TIPO_DATO_CARACTER, valor);
	}

	/**
	 * Genera celda en base a la informacion enviada 
	 * @param tipoDato  
	 * @param valor
	 * @throws Exception
	 */
	public void generarCelda(String tipoDato, String valor) throws Exception {
		
		// create a row object reference for later use
		HSSFRow r = null;
		// create a cell object reference
		HSSFCell c = null;
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFDataFormat df = wb.createDataFormat();
 
		r = ws.createRow(this.registro);
		c = r.createCell(this.columna);
		valor = valor.trim();
		if (tipoDato.equals(Constants.TIPO_DATO_NUMERICO)) {
			c.setCellValue(new Double(valor));
			cellStyle.setDataFormat(df.getFormat("###,###,##0.00"));
			c.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			c.setCellStyle(cellStyle);
		}	
		else {	
			HSSFRichTextString texto = new HSSFRichTextString(valor);
			c.setCellValue(texto);
			cellStyle.setDataFormat(df.getFormat("text"));
			c.setCellType(HSSFCell.CELL_TYPE_STRING);
			c.setCellStyle(cellStyle);
		}	

	}
	
	/**
	 * Genera celda en base a la informacion enviada
	 * @param tipoDato  
	 * @param valor
	 * @throws Exception
	 */
	public void generarCeldaExcelGrande(String tipoDato, String valor) throws Exception {
		
		// create a row object reference for later use
		HSSFRow r = null;
		// create a cell object reference
		HSSFCell c = null;
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFDataFormat df = wb.createDataFormat();
 
		r = ws.createRow(this.registroExcelGrande);
		c = r.createCell(this.columna);
		valor = valor.trim();
		
		if (tipoDato.equals(Constants.TIPO_DATO_NUMERICO)) {
			c.setCellValue(new Double(valor));
			cellStyle.setDataFormat(df.getFormat("###,###,##0.00"));
			cellStyle.setFont(font2);
			c.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			c.setCellStyle(cellStyle);
		}	
		else {	
			HSSFRichTextString texto = new HSSFRichTextString(valor);
			c.setCellValue(texto);
			byte error=0;
			//c.setCellErrorValue(error);
			cellStyle.setDataFormat(df.getFormat("text"));
			cellStyle.setFont(font2);
			c.setCellType(HSSFCell.CELL_TYPE_STRING);
			c.setCellStyle(cellStyle);
			
		}	

	}
	
	/**
	 * @return the manejarConBigDecimal
	 */
	public boolean isManejarConBigDecimal() {
		return manejarConBigDecimal;
	}

	/**
	 * @param manejarConBigDecimal the manejarConBigDecimal to set
	 */
	public void setManejarConBigDecimal(boolean manejarConBigDecimal) {
		this.manejarConBigDecimal = manejarConBigDecimal;
	}
	
	
	/**
	 * Genera celda en base a la informacion enviada
	 * @param tipoDato  
	 * @param valor
	 * @throws Exception
	 */
	/*public void generarCeldaXSSF(String tipoDato, String valor) throws Exception {
		
		// create a row object reference for later use
		XSSFRow r = null;
		// create a cell object reference
		XSSFCell c = null;
		XSSFCellStyle cellStyle = wbXSSF.createCellStyle();
		XSSFDataFormat df = wbXSSF.createDataFormat();
 
		r = wsXSSF.createRow(this.registro);
		c = r.createCell(this.columna);
		valor = valor.trim();
		if (tipoDato.equals(Constants.TIPO_DATO_NUMERICO)) {
			c.setCellValue(new Double(valor));
			cellStyle.setDataFormat(df.getFormat("###,###,##0.00"));
			c.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
			c.setCellStyle(cellStyle);
		}	
		else {	
			c.setCellValue(valor);
			cellStyle.setDataFormat(df.getFormat("text"));
			c.setCellType(XSSFCell.CELL_TYPE_STRING);
			c.setCellStyle(cellStyle);
		}	

	}*/
	
}	


