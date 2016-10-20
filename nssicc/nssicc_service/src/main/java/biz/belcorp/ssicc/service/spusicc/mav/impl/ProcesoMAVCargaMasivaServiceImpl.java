package biz.belcorp.ssicc.service.spusicc.mav.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mav.ProcesoMAVCargaMasivaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mav.ProcesoMAVCargaMasivaService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoMAVCargaMasivaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAVCargaMasivaServiceImpl extends BaseService implements
	ProcesoMAVCargaMasivaService {
	
	@Resource(name="spusicc.procesoMAVCargaMasivaDAO")
	private ProcesoMAVCargaMasivaDAO procesoMAVCargaMasivaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPuntajeBonificadoService#cargarArchivoExcel(java.util.Map)
	 */
	public List executeValidarCargaMasiva(Map criteria) throws Exception {
		List listResultado = new ArrayList();
		List listMapFila = new ArrayList();
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		int cantidadFilas = 0; 
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();

			//obtenemos la fila en el excel
			String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data
			
			//obtenemos el codigo de cliente	
			String codigoPais=(String)mapRow.get("0");
			String campanaDespacho=obtenerValorLimpio((String)mapRow.get("1"));
			String actividad=obtenerValorLimpio((String)mapRow.get("2"));
			String tipoOferta=obtenerValorLimpio((String)mapRow.get("3"));
			String codigoProducto=(String)mapRow.get("4");
			String indEnviaMensaje=(String)mapRow.get("5");
			String cantidad=obtenerValorLimpio((String)mapRow.get("6"));
			String codigoConsultora=(String)mapRow.get("7");
			String codigoRegion=obtenerValorLimpio((String)mapRow.get("8"));
			String codigoZona=obtenerValorLimpio((String)mapRow.get("9"));
			
			//Lo almacenamos en una lista temporal
			Map mapFila = new HashMap();
			mapFila.put("numeroFila", filaExcel);
			mapFila.put("codigoPais", codigoPais);
			mapFila.put("campanaDespacho", campanaDespacho);
			mapFila.put("actividad", actividad);
			mapFila.put("tipoOferta", tipoOferta);
			mapFila.put("codigoProducto", codigoProducto);
			mapFila.put("indEnviaMensaje", indEnviaMensaje);
			mapFila.put("cantidad", cantidad);
			mapFila.put("codigoConsultora", codigoConsultora);
			mapFila.put("codigoRegion", codigoRegion);
			mapFila.put("codigoZona", codigoZona);
			
			listMapFila.add(mapFila);
			cantidadFilas++;
		}
		
		excelUtil.cerrar();
		
		//limpiamos la tabla temporal		
		procesoMAVCargaMasivaDAO.deleteCargaMasiva();
		
		//inserta los datos en tabla temporal
		procesoMAVCargaMasivaDAO.insertCargaMasivaBatch(listMapFila);
		
		//realiza los procesos de validacion a los registros cargados en la tabla temporal
		procesoMAVCargaMasivaDAO.executeValidarCargaMasiva(criteria);
		
		//obtiene la lista resumen de los MAV que van a ser cargados a BD
		List listResumen = procesoMAVCargaMasivaDAO.getResumenCargaMasiva(criteria);
		
		//obtiene la lista detalle de los registros con errores, que no seran cargados a BD
		List listErrores = procesoMAVCargaMasivaDAO.getErroresCargaMasiva(criteria);
		
		listResultado.add(listResumen);
		listResultado.add(listErrores);
		listResultado.add(String.valueOf(cantidadFilas));
		listResultado.add(String.valueOf(listErrores.size()));
		
		return listResultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVCargaMasivaService#executeActualizarCargaMasiva(java.util.Map)
	 */
	public void executeActualizarCargaMasiva(Map params) {
		procesoMAVCargaMasivaDAO.executeActualizarCargaMasiva(params);
	}
	
	/**
	 * Retorna el valor quitando el caracter (.0)
	 * @param valor
	 * @return
	 */
	private String obtenerValorLimpio(String valor) {
		String valorAux = valor;
		
		if(valor != null) {
			int posicion = valor.indexOf(".0");
			if (posicion >= 0 ){
				valorAux = valor.substring(0, posicion);
			} 
		}  
		
		return valorAux;
	}
	
	/**
	 * Retorna el numero de carga
	 * @return
	 */
	private String getNumeroCarga() {
		return procesoMAVCargaMasivaDAO.getNumeroCarga();
	}
	
}

