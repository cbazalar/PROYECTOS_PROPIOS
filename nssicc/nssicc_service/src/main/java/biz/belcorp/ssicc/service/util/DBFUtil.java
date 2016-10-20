package biz.belcorp.ssicc.service.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.belcorp.ssicc.dao.sisicc.model.Base;

/**
 * Metodos utilitarios para la lectura de archivos DBF.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
/**
 * @author pecbazalar
 *
 */
public class DBFUtil {
	protected final Log log = LogFactory.getLog(DBFUtil.class);
	
	private Connection con = null;
	private Statement stmt = null; 
	private ResultSet rs = null;
	
	private String path;
	private String fileName;
	private int iNumCols;
	
	/**
	 * @param path
	 * @param fileName
	 * @throws Exception
	 */
	public DBFUtil(String path, String fileName) throws Exception {
		this.path = path;
		this.fileName = fileName;
		
		Class.forName("com.hxtt.sql.dbf.DBFDriver");
		con = DriverManager.getConnection("jdbc:dbf:/" + path, "", "");
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List obtenerCampos() throws Exception {
		List listaCampos = new ArrayList();
		String sql = "select top 1 tabla.* from " + fileName + " as tabla";
		
		Statement stmtAux = con.createStatement();
		ResultSet rsAux = stmtAux.executeQuery(sql);
		
		ResultSetMetaData resultSetMetaData = rsAux.getMetaData();
		this.iNumCols = resultSetMetaData.getColumnCount();
		for (int i = 1; i <= this.iNumCols; i++) {
			log.debug(resultSetMetaData.getColumnLabel(i)
		               + "  " + resultSetMetaData.getColumnTypeName(i));
		}
		
		//Leyemos la primera Fila del archivo DBF
		Object colval;
		while (rsAux.next()) {
		    for (int i = 1; i <= iNumCols; i++) {
		        colval = rsAux.getObject(i);
		        log.debug(colval + ",");
		    }
		}
		
		return listaCampos;
	}	

	/**
	 * @return
	 * @throws Exception
	 */
	public List obtenerBaseCampos() throws Exception {
		List listaBaseCampos = new ArrayList();
		String sql = "select top 1 tabla.* from " + fileName + " as tabla";
		
		Statement stmtAux = con.createStatement();
		ResultSet rsAux = stmtAux.executeQuery(sql);
		
		ResultSetMetaData resultSetMetaData = rsAux.getMetaData();
		int iNumCols = resultSetMetaData.getColumnCount();
		for (int i = 1; i <= iNumCols; i++) {
			Base base = new Base();
			base.setCodigo(String.valueOf(i));
			base.setDescripcion(resultSetMetaData.getColumnLabel(i));
			
			log.debug(resultSetMetaData.getColumnLabel(i)
		               + "  " + resultSetMetaData.getColumnTypeName(i));
			listaBaseCampos.add(base);
		}
		
		rsAux.close();
		stmtAux.close();
		
		return listaBaseCampos;
	}	

	/**
	 * @return
	 * @throws Exception
	 */
	public boolean hasNext() throws Exception {
		if(rs == null) {
			leerTabla();
		}
		
		return rs.next();
	}
	
	/**
	 * @throws Exception
	 */
	private void leerTabla() throws Exception {
		String sql = "select tabla.* from " + fileName + " as tabla";
		
		this.stmt = con.createStatement();
		this.rs = stmt.executeQuery(sql);
		 
		ResultSetMetaData resultSetMetaData = rs.getMetaData();
		this.iNumCols = resultSetMetaData.getColumnCount();
	}
	
	/**
	 * Obtener datos del registro
	 * @return
	 * @throws Exception
	 */
	public Map next() throws Exception{
		//Aqui se colocan todos los valores almacenados en dicha fila.       
		Map rowMap = new HashMap(); 
		
		for (int i = 1; i <= iNumCols; i++) {
			rowMap.put(String.valueOf(i), rs.getObject(i));	
		}
		
		return rowMap;
	}	
	
	/**
	 * Cerrar conexion a la BD
	 */
	public void cerrar() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		}catch(Exception ex) {}	
	}
	
}
