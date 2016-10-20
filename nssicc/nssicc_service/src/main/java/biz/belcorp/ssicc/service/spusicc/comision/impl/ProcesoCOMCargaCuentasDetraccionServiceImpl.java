package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCargaCuentasDetraccionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCargaCuentasDetraccionService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoCOMCargaCuentasDetraccionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOMCargaCuentasDetraccionServiceImpl extends BaseService implements
	ProcesoCOMCargaCuentasDetraccionService {

	@Resource(name="spusicc.procesoCOMCargaCuentasDetraccionDAO")
	private ProcesoCOMCargaCuentasDetraccionDAO procesoCOMCargaCuentasDetraccionDAO;
	
	public boolean validarArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		boolean valor = true;
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();		
			 if(mapRow.size()>3) {//las columnas recogidas + el numero de fila de fila procesda
				 valor = false;
				 break;
			 }			
		}
		excelUtil.cerrar();
		return valor;
	}
	
	public boolean validarCargaExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String codigoPais =(String)criteria.get("codigoPais");
		
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			
			String codigoCliente = (String)mapRow.get("0");
			String cuentaDetraccion = (String)mapRow.get("1");
			String fila = (String)mapRow.get("rowNum");
					
			 //validaciones
			 String mensajeError=null;
			 
			//Validar que  el cdigo de Ejecutiva se encuentre registrado en el maestro de lderes
			if(!procesoCOMCargaCuentasDetraccionDAO.isValidaEjecutiva(codigoCliente)) {
			 	mensajeError = messageSource.getMessage("procesoCOMCargaCuentasDetraccionForm.msg.ejecutivaNoExiste",
			 						new String[]{fila},getLocale(usuario));
			 	log.debug(mensajeError);
			 	excelUtil.cerrar();
			 	throw new Exception(mensajeError);
			}
			
			//Validar que el nmero de cuenta sea de 12 caracteres
			if(cuentaDetraccion.length() != 12) {
				mensajeError = messageSource.getMessage("procesoCOMCargaCuentasDetraccionForm.msg.tamanioCuentaIncorrecto",
 						new String[]{fila},getLocale(usuario));
			 	log.debug(mensajeError);
			 	excelUtil.cerrar();
			 	throw new Exception(mensajeError);
			}
			
			//Validar que el cdigo de cuenta, los tres primeros caracteres sean "000"
			if(!cuentaDetraccion.substring(0, 3).equals("000")) {
				mensajeError = messageSource.getMessage("procesoCOMCargaCuentasDetraccionForm.msg.inicioCuentaIncorrecto",
 						new String[]{fila},getLocale(usuario));
			 	log.debug(mensajeError);
			 	excelUtil.cerrar();
			 	throw new Exception(mensajeError);
			}

			//Validar que el nmero de cuenta sea numrico
			if(!validoFormatoNumerico(cuentaDetraccion.substring(3))) {
				mensajeError = messageSource.getMessage("procesoCOMCargaCuentasDetraccionForm.msg.formatoCuentaIncorrecto",
 						new String[]{fila},getLocale(usuario));
			 	log.debug(mensajeError);
			 	excelUtil.cerrar();
			 	throw new Exception(mensajeError);
			}
			
		}
		
		excelUtil.cerrar();
		return true;
	}	

	public void executeCargaArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String codigoPais =(String)criteria.get("codigoPais");
		
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
				
		List lista = new ArrayList();
		int numfila=0;
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			
			String codigoCliente = (String)mapRow.get("0");
			String cuentaDetraccion = (String)mapRow.get("1");
			
			Map mapFila = new HashMap();
			mapFila.put("codigoCliente", codigoCliente);
			mapFila.put("cuentaDetraccion", cuentaDetraccion);
			
			lista.add(mapFila);
		}
		excelUtil.cerrar();
		
		//hacemos el proceso de registro en batch
		procesoCOMCargaCuentasDetraccionDAO.updateListCuentasDetraccion(lista);
	}

	/**
	 * Valida Formato Numerico
	 * 
	 * @param numero
	 * @return
	 */
	private boolean validoFormatoNumerico(String numero) {
		boolean valor=true;
		try{
			Long.parseLong(numero);
		}catch(Exception e){
			valor=false;
		}
		return valor;
	}
	
	

}
