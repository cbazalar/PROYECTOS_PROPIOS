package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEActualizaIndProdDentroCajaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEActualizaIndProdDentroCajaService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
/**
 *  
 * <p>
 * <a href="MantenimientoAPEActualizaIndProdDentroCajaServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez Ramos</a>
 * 
 */
@Service("spusicc.mantenimientoAPEActualizaIndProdDentroCajaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEActualizaIndProdDentroCajaServiceImpl extends BaseService implements MantenimientoAPEActualizaIndProdDentroCajaService{
	
	@Resource(name="spusicc.mantenimientoAPEActualizaIndProdDentroCajaDAO")
	private MantenimientoAPEActualizaIndProdDentroCajaDAO mantenimientoAPEActualizaIndProdDentroCajaDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEActualizaIndProdCajaDentroService#getIndProductoCajaDentroList(java.util.Map)
	 */
	public List getIndProductoCajaDentroList(Map criteria) {
		return this.mantenimientoAPEActualizaIndProdDentroCajaDAO.getIndProductoCajaDentroList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEActualizaIndProdCajaDentroService#getOidIndProductoCajaDentro(java.util.Map)
	 */
	public String getOidIndProductoCajaDentro(Map criteria) {
		return this.mantenimientoAPEActualizaIndProdDentroCajaDAO.getOidIndProductoCajaDentro(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEActualizaIndProdDentroCajaService#getProductoIndicadorDentroCajaList(java.util.Map)
	 */
	public List getProductoIndicadorDentroCajaList(Map criteria) {
		return this.mantenimientoAPEActualizaIndProdDentroCajaDAO.getProductoIndicadorDentroCajaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEActualizaIndProdDentroCajaService#getIndProdDentroCajaObject(java.util.Map)
	 */
	public List getIndProdDentroCajaObject(Map criteria) {
		return this.mantenimientoAPEActualizaIndProdDentroCajaDAO.getIndProdDentroCajaObject(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEActualizaIndProdDentroCajaService#updateIndicadorProducto(java.util.Map)
	 */
	public void updateIndicadorProducto(Map criteria) {
		this.mantenimientoAPEActualizaIndProdDentroCajaDAO.updateIndicadorProducto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEActualizaIndProdDentroCajaService#validarArchivoExcel(java.util.Map)
	 */
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
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEActualizaIndProdDentroCajaService#executeCargaArchivoExcel(java.util.Map)
	 */
	public String executeCargaArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String valor = "0";

	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);

		List list = null;
		int numfila=0;

		String codigoProducto="";
		String codigoIndicador="";
		int validacion = 0;

		Map params = null;

		while(excelUtil.hasNext()) {
			params = new HashMap();
			Map mapRow = excelUtil.next();
			++numfila;

			if(list==null) list = new ArrayList();

			if (numfila > 0){
				if(StringUtils.isNotEmpty((String)mapRow.get("0"))){
					codigoProducto = (String)mapRow.get("0"); 
					
					if (mapRow.get("1")!=null){
						codigoIndicador = (String)mapRow.get("1");
					}else{
						codigoIndicador =" ";
					}
					
					params.put("codigoProducto", codigoProducto);
					params.put("codigoIndicador", codigoIndicador);
					
					// Se valida que el indicador de producto exista en la tabla de Indicadores
					validacion = Integer.parseInt(getValidaExisteIndicadorCaja(params));
					
					if( validacion > 0){
						list.add(params);
					}else{
						valor = codigoIndicador;
						break;
					}
					
				}
			}
		}

		if (validacion > 0){
			actualizaIndicadorDentroFueraCaja(list);
			valor="0";
		}
		excelUtil.cerrar();

		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEActualizaIndProdDentroCajaService#getValidaExisteIndicadorCaja(java.util.Map)
	 */
	public String getValidaExisteIndicadorCaja(Map criteria) {
		return this.mantenimientoAPEActualizaIndProdDentroCajaDAO.getValidaExisteIndicadorCaja(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEActualizaIndProdDentroCajaService#actualizaIndicadorDentroFueraCaja(java.util.List)
	 */
	public void actualizaIndicadorDentroFueraCaja(List obj){
		
		Map objeto = null;
		
		// Se recorre el arreglo con los productos a actualizar el indicador
		for (int i=0;i<obj.size();i++){
			objeto = (Map)obj.get(i);
			updateIndProductoDFCaja(objeto);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEActualizaIndProdDentroCajaService#updateIndProductoDFCaja(java.util.Map)
	 */
	public void updateIndProductoDFCaja(Map criteria) {
		this.mantenimientoAPEActualizaIndProdDentroCajaDAO.updateIndProductoDFCaja(criteria);
	}

}