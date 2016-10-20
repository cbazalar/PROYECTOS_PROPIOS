package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEAsignarProductosAnaquelesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEAsignarProductosAnaquelesService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
/**
 *  
 * <p>
 * <a href="MantenimientoAPEAsignarProductosAnaquelesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEAsignarProductosAnaquelesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEAsignarProductosAnaquelesServiceImpl extends BaseService implements MantenimientoAPEAsignarProductosAnaquelesService{
	
	@Resource(name="spusicc.mantenimientoAPEAsignarProductosAnaquelesDAO")
	private MantenimientoAPEAsignarProductosAnaquelesDAO mantenimientoAPEAsignarProductosAnaquelesDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarProductosAnaquelesService#getProductosAnaquelesList(java.util.Map)
	 */
	public List getProductosAnaquelesList(Map criteria) {
		return mantenimientoAPEAsignarProductosAnaquelesDAO.getProductosAnaquelesList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarProductosAnaquelesService#getOidAsignacionVersion(java.util.Map)
	 */
	public String getOidAsignacionVersion(Map criteria){
		return mantenimientoAPEAsignarProductosAnaquelesDAO.getOidAsignacionVersion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarProductosAnaquelesService#desasignarProductoAnaquel(java.util.Map)
	 */
	public void desasignarProductoAnaquel(Map criteria){
		mantenimientoAPEAsignarProductosAnaquelesDAO.desasignarProductoAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarProductosAnaquelesService#getAnaquelesDestinoList(java.util.Map)
	 */
	public List getAnaquelesDestinoList(Map criteria){
		return mantenimientoAPEAsignarProductosAnaquelesDAO.getAnaquelesDestinoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarProductosAnaquelesService#asignarProductoAnaquel(java.util.Map)
	 */
	public void asignarProductoAnaquel(Map criteria){
		mantenimientoAPEAsignarProductosAnaquelesDAO.asignarProductoAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarProductosAnaquelesService#validaProductoAnaquel(java.util.Map)
	 */
	public void validaProductoAnaquel(Map criteria){
		mantenimientoAPEAsignarProductosAnaquelesDAO.validaProductoAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarProductosAnaquelesService#intercambioProductoAnaquel(java.util.Map)
	 */
	public void intercambioProductoAnaquel(Map criteria){
		mantenimientoAPEAsignarProductosAnaquelesDAO.intercambioProductoAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarProductosAnaquelesService#getOidProductoAsignacionDetalle(java.util.Map)
	 */
	public String getOidProductoAsignacionDetalle(Map map){
		return mantenimientoAPEAsignarProductosAnaquelesDAO.getOidProductoAsignacionDetalle(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarProductosAnaquelesService#executeCargaArchivoExcel(java.util.Map)
	 */
	public List executeCargaArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String oidPeriodo = (String)criteria.get("oidPeriodo");
		String oidMapaCentroCab =(String)criteria.get("oidMapaCentroCab");
		String oidAsigProdCab =(String)criteria.get("oidAsigProdCab");
		String oidPais = (String)criteria.get("oidPais");
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		List list = null;
		List listaError = null;
		
		int numfila=0;
		
		String numeroAnaquel = "";
		String codigoProducto = "";
		String oidProducto = "";
		String oidMapaCentroDet = "";
		String oidSubLinea = "";
		String oidAsigOrdenDet = "";
		int validacion = 0;
		BigDecimal codSAP = new BigDecimal(0);
		
		Map map = new HashMap();
		map.put("oidPais", oidPais);
		map.put("oidMapaCentro", oidMapaCentroCab);
		map.put("oidAsigOrdenCab", oidAsigProdCab);
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			++numfila;

			if(list==null) list = new ArrayList();

			if (numfila > 1){
				if(StringUtils.isNotEmpty((String)mapRow.get("0"))){
					numeroAnaquel = (String)mapRow.get("0");
					codigoProducto = (String)mapRow.get("1");
					
					if(codigoProducto == null){
						codigoProducto  = "";	
					}
					
					if (!codigoProducto.equals("")){
						codSAP = new BigDecimal(codigoProducto);
						map.put("codSap", codSAP.longValue());
						mapRow.put("1", codSAP.longValue());
						oidProducto = mantenimientoAPEAsignarProductosAnaquelesDAO.getOidProductoByCodigoyPais(map);
						
					}
					else{
						oidProducto = "";
					}
					
					map.put("oidSAP", oidProducto);
					map.put("numeroAnaquel", numeroAnaquel);
					oidMapaCentroDet = mantenimientoAPEAsignarProductosAnaquelesDAO.getMapaCentroSubLineaList(map);
					map.put("oidMapaCDDet", oidMapaCentroDet);
					oidSubLinea = mantenimientoAPEAsignarProductosAnaquelesDAO.getObtenerSubLineaxOidMapaCDDet(map);
					map.put("oidSubLinea", oidSubLinea);
					oidAsigOrdenDet = mantenimientoAPEAsignarProductosAnaquelesDAO.getOidAsignacionProdAnaDet(map);
					
					mapRow.put("oidPais", oidPais);
					mapRow.put("oidSAP", oidProducto);
					mapRow.put("oidPeriodo", oidPeriodo);
					mapRow.put("codAnaquel", numeroAnaquel);
					mapRow.put("oidSubLinea", oidSubLinea);
					mapRow.put("oidMapaCentroCab", oidMapaCentroCab);
					mapRow.put("oidMapaCentroDet", oidMapaCentroDet);
					mapRow.put("oidAsigOrdenCab", oidAsigProdCab);
					mapRow.put("indIntercambio", Constants.NO);
					mapRow.put("oidAsigOrdenDet", oidAsigOrdenDet);
									
					if (!oidProducto.equals("")){
						validacion = isValidoRegistro(list,mapRow);	
					}
					else{
						validacion =0;
					}
					
					if( validacion == 0){
						list.add(mapRow);	
					}
				}
			}
		}
		
		listaError = asignarAnalquelesProductos(list);
		excelUtil.cerrar();
		
		return listaError;
	}
	
	/**
	 * Realiza la asignacion de los productos de la carga a los anaqueles
	 * @param list
	 * @return
	 */
	private List asignarAnalquelesProductos(List list){
		Iterator listIterator = list.iterator();
		String sValError = "";
		long codigoProducto = 0;
		List listaError = null;
		String oisSap = "";
		int validacion = 0;
		
		while (listIterator.hasNext()) {
			Map dataInsert = (Map) listIterator.next();
			log.debug("dataInsert " + dataInsert);
			
			oisSap = (String)dataInsert.get("oidSAP");
				
			if (!oisSap.equals("")){
				//Se llama al procedure para realizar las validaciones
				mantenimientoAPEAsignarProductosAnaquelesDAO.validaProductoAnaquel(dataInsert);
				sValError = (String) dataInsert.get("valError");	
			}
			else{
				sValError = Constants.NUMERO_CERO;
			}
				
			log.debug("sValError " + sValError);
			
			if (sValError.equals("0")){
				//Si esta todo OK se realiza la asignacion del producto al anaquel
				mantenimientoAPEAsignarProductosAnaquelesDAO.asignarProductoAnaquel(dataInsert);
			}
			else{
				if(listaError==null) listaError = new ArrayList();
				
				Map mapError = new HashMap();
				codigoProducto =  (Long)dataInsert.get("1");
				mapError.put("codigoProducto", codigoProducto);
				mapError.put("descripcionError", sValError);
				
				validacion = errorRepetido(listaError,mapError);	
				
				if ( validacion == 0){
					listaError.add(mapError);	
				}
			}
		}
		
		return listaError;
	}
	
	/**
	 * Verifica si la Validacion ya se encuentra asignada para un mismo producto
	 * @param list
	 * @param mapRow
	 * @return
	 */
	private int errorRepetido(List list, Map mapRow) {
		 int valor = 0;
		 long codigoProducto =  (Long)mapRow.get("codigoProducto");
		 String sValError = (String)mapRow.get("descripcionError") ;
		 
		 if(list!=null && list.size()>0){
			 Iterator it= list.iterator();
			 
			 while(it.hasNext()){
				 Map params = (Map)it.next();
				 // Se valida que se asigne un numero de orden diferente en la sublinea
				 if(sValError.equals(params.get("descripcionError")) &&
						 codigoProducto == (Long)params.get("codigoProducto")){
					 valor = 1;
					 break;
				 }
			 }
			 
		 }else{
			 return valor;
		 }	
		 
		 return valor;//hay duplicado false , caso contrario true
	}
	/**
	 * Retorna true cuando el registro es valido es decir no existe repetido, retorna false
	 * cuando el registro esta duplicado
	 * @param list
	 * @param mapRow
	 * @return
	 */
	private int isValidoRegistro(List list, Map mapRow) {
		 int valor = 0;
		 String numeroAnaquel = (String)mapRow.get("0");
		 long codigoProducto =  (Long)mapRow.get("1");
		 
		 if(list!=null && list.size()>0){
			 Iterator it= list.iterator();
			 while(it.hasNext()){
				 Map params = (Map)it.next();
				 // Se valida que se asigne un numero de orden diferente en la sublinea
				 if(numeroAnaquel.equals(params.get("0")) &&
						 codigoProducto == (Long)params.get("1")){
					 valor = 1;
					 break;
				 }
			 }	 
		 }else{
			 return valor;
		 }		 
		 
		 return valor;//hay duplicado false , caso contrario true
	}
}