package biz.belcorp.ssicc.service.spusicc.ape.impl;

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
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEOrdenAnaquelesDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.OrdenAnaquel;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEOrdenAnaquelesService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
/**
 *  
 * <p>
 * <a href="MantenimientoAPEOrdenAnaquelesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEOrdenAnaquelesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEOrdenAnaquelesServiceImpl extends BaseService implements MantenimientoAPEOrdenAnaquelesService{
	
	@Resource(name="spusicc.mantenimientoAPEOrdenAnaquelesDAO")
	private MantenimientoAPEOrdenAnaquelesDAO mantenimientoAPEOrdenAnaquelesDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getMapaOrdenLineaList(java.util.Map)
	 */
	public List getMapaOrdenLineaList(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getMapaOrdenLineaList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getOidOrdenAnaquel(java.util.Map)
	 */
	public String getOidOrdenAnaquel(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getOidOrdenAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getOidMapaZonaByCodMapaZona(java.util.Map)
	 */
	public String getOidMapaZonaByCodMapaZona(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getOidMapaZonaByCodMapaZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getSubLineasNuevoOrdenAnaquel(java.util.Map)
	 */
	public List getSubLineasNuevoOrdenAnaquel(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getSubLineasNuevoOrdenAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#validaExisteOrdeAnaquelDefault(java.util.Map)
	 */
	public int validaExisteOrdeAnaquelDefault(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.validaExisteOrdeAnaquelDefault(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getDescripcionCentroDistribucion(java.util.Map)
	 */
	public String getDescripcionCentroDistribucion(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getDescripcionCentroDistribucion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getDescripcionMapaCentroDistribucion(java.util.Map)
	 */
	public String getDescripcionMapaCentroDistribucion(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getDescripcionMapaCentroDistribucion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getDescripcionMapaZona(java.util.Map)
	 */
	public String getDescripcionMapaZona(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getDescripcionMapaZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getDescripcionMapaAnaquelDefault(java.util.Map)
	 */
	public String getDescripcionMapaAnaquelDefault(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getDescripcionMapaAnaquelDefault(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getOrdenAnaquelObject(java.util.Map)
	 */
	public OrdenAnaquel getOrdenAnaquelObject(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getOrdenAnaquelObject(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getSubLineasModificarOrdenAnaquel(java.util.Map)
	 */
	public List getSubLineasModificarOrdenAnaquel(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getSubLineasModificarOrdenAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getDetalleOrdenAnaquelList(java.util.Map)
	 */
	public List getDetalleOrdenAnaquelList(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getDetalleOrdenAnaquelList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getDetalleOrdenAnaquelConsultaList(java.util.Map)
	 */
	public List getDetalleOrdenAnaquelConsultaList(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getDetalleOrdenAnaquelConsultaList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#insertOrdenAnaquelCabecera(java.util.Map)
	 */
	public void insertOrdenAnaquelCabecera(Map criteria){
		mantenimientoAPEOrdenAnaquelesDAO.insertOrdenAnaquelCabecera(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#insertOrdenAnaquelDetalle(java.util.Map)
	 */
	public void insertOrdenAnaquelDetalle(Map criteria){
		String[] listaNumOrden = (String[]) criteria.get("ListaNumeroOrden");
		String[] listaNumOrdenLista = (String[]) criteria.get("ListaNumeroOrdenLista");
		String[] listaNumAnaquel = (String[]) criteria.get("listaNumAnaquel");
		String oidMapaOrdenCab = (String) criteria.get("oidMapaOrdenCab");
		Integer nNumOrden = 0;
		String sNumAnaquel= "";
		String sNumOrdenLista = "";
		
		Map map = new HashMap();
		for(int i=0; i < listaNumOrden.length; i++ ) {
			nNumOrden = nNumOrden + 1;
			sNumOrdenLista = listaNumOrdenLista[i];
			sNumAnaquel = listaNumAnaquel[i];
			
			map.put("numOrden", nNumOrden.toString());
			map.put("numOrdenLista", sNumOrdenLista);
			map.put("oidMapaOrdenCab", oidMapaOrdenCab);
			map.put("oidMapaCentroDet", sNumAnaquel);
			mantenimientoAPEOrdenAnaquelesDAO.insertOrdenAnaquelDetalle(map);
			
			sNumOrdenLista = ""; 
			sNumAnaquel= "";
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getNextOidOrdenAnaquelCab(java.util.Map)
	 */
	public String getNextOidOrdenAnaquelCab(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getNextOidOrdenAnaquelCab(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getNextOidOrdenAnaquelDet(java.util.Map)
	 */
	public String getNextOidOrdenAnaquelDet(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getNextOidOrdenAnaquelDet(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getMaxCodOrdenAnaquel(java.util.Map)
	 */
	public int getMaxCodOrdenAnaquel(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getMaxCodOrdenAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#updateOrdenAnaquelDetalle(java.util.Map)
	 */
	public void updateOrdenAnaquelDetalle(Map criteria){
		String[] listaNumOrden = (String[]) criteria.get("ListaNumeroOrden");
		String[] listaNumOrdenLista = (String[]) criteria.get("ListaNumeroOrdenLista");
		String[] listaNumAnaquel = (String[]) criteria.get("listaNumAnaquel");
		String oidMapaOrdenCab = (String) criteria.get("oidMapaOrdenCab");
		Integer nNumOrden = 0;
		String sNumAnaquel= "";
		String sNumOrdenLista = "";
		
		Map map = new HashMap();
		
		for(int i=0; i < listaNumOrden.length; i++ ) {
			nNumOrden = nNumOrden + 1;
			sNumOrdenLista = listaNumOrdenLista[i];
			sNumAnaquel = listaNumAnaquel[i];
			
			map.put("numOrden", nNumOrden.toString());
			map.put("numOrdenLista", sNumOrdenLista);
			map.put("oidMapaOrdenCab", oidMapaOrdenCab);
			map.put("oidMapaCentroDet", sNumAnaquel);
			mantenimientoAPEOrdenAnaquelesDAO.updateOrdenAnaquelDetalle(map);

			sNumAnaquel= "";
			sNumOrdenLista = "";
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#deleteOrdenAnaquelCabecera(java.util.Map)
	 */
	public void deleteOrdenAnaquel(Map criteria, String[] items){
		for(int i = 0; i < items.length; i++){
			String id = items[i];
			
			criteria.put("oidCentro", StringUtils.split(id, "|")[0]);
			criteria.put("oidMapaCentro", StringUtils.split(id, "|")[1]);
			criteria.put("oidMapaZona", StringUtils.split(id, "|")[2]);
			criteria.put("oidMapaOrden", StringUtils.split(id, "|")[3]);
			criteria.put("oidLinea", StringUtils.split(id, "|")[4]);
			
			//Eliminamos los detalles
			mantenimientoAPEOrdenAnaquelesDAO.deleteOrdenAnaquelDetalle(criteria);
			//Eliminamos la cabecera
			mantenimientoAPEOrdenAnaquelesDAO.deleteOrdenAnaquelCabecera(criteria);
			
			criteria.put("nombreTablaOrdenAnaquel",Constants.TABLA_ORDEN_ANAQUEL_CAB);
			//Se elimina de la tabla de idoma
			mantenimientoAPEOrdenAnaquelesDAO.deleteIdiomaAnaquel(criteria);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#validaExisteAnaquelDetalle(java.util.Map)
	 */
	public int validaExisteAnaquelDetalle(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.validaExisteAnaquelDetalle(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#updateNumeroOrdenAnaquelDetalle(java.util.Map)
	 */
	public void updateNumeroOrdenAnaquelDetalle(Map criteria){
		mantenimientoAPEOrdenAnaquelesDAO.updateNumeroOrdenAnaquelDetalle(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#deleteOrdenAnaquelDetalle(java.util.Map)
	 */
	public void deleteOrdenAnaquelDetalle(Map criteria){
		String[] listaNumAnaquel = (String[]) criteria.get("listaNumAnaquel");
		String sNumAnaquel= "";
		String oidMapaOrdenCab = (String) criteria.get("oidMapaOrdenCab");
		Map map = new HashMap();
		
		for(int i=0; i < listaNumAnaquel.length; i++ ) {
			map.put("oidMapaOrdenCab", oidMapaOrdenCab);
			map.put("oidMapaCentroDet", sNumAnaquel);
			
			mantenimientoAPEOrdenAnaquelesDAO.deleteOrdenAnaquelDetbyMapaCent(criteria);
			sNumAnaquel= "";
		}	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#updateMapaOrdenAnaquelDefault(java.util.Map)
	 */
	public void updateMapaOrdenAnaquelDefault(Map criteria){
		mantenimientoAPEOrdenAnaquelesDAO.updateMapaOrdenAnaquelDefault(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#validaExisteOrdenAnaquelDefault(java.util.Map)
	 */
	public String validaExisteOrdenAnaquelDefault(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.validaExisteOrdenAnaquelDefault(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#getOidMapaZonaByMapaCentroZona(java.util.Map)
	 */
	public String getOidMapaZonaByMapaCentroZona(Map criteria){
		return mantenimientoAPEOrdenAnaquelesDAO.getOidMapaZonaByMapaCentroZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#validarArchivoExcel(java.util.Map)
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
			 if(mapRow.size()>4) {//las columnas recogidas + el numero de fila de fila procesda
				 valor = false;
				 break;
			 }
		}
		
		excelUtil.cerrar();
		return valor;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEOrdenAnaquelesService#executeCargaArchivoExcel(java.util.Map)
	 */
	public int executeCargaArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String sOidMapaCentro = (String)criteria.get("oidMapaCentro");
		String sOidMapaOrden =(String)criteria.get("oidMapaOrden");

	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);

		List list = null;
		int numfila=0;

		String numeroAnaquel="";
		String numeroOrden="";
		String numeroOrdenLP="";
		String oidMapaCentroDet="";
		String letraSubLinea="";
		int validacion = 0;

		Map map = new HashMap();

		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			++numfila;

			if(list==null) list = new ArrayList();

			if (numfila > 1){
				if(StringUtils.isNotEmpty((String)mapRow.get("0"))){
					numeroAnaquel = (String)mapRow.get("0"); 	
					numeroOrden = (String)mapRow.get("1");
					numeroOrdenLP = (String)mapRow.get("2");
					map.put("oidMapaCentro", sOidMapaCentro);
					map.put("numeroAnaquel", numeroAnaquel);

					oidMapaCentroDet = mantenimientoAPEOrdenAnaquelesDAO.getMapaCentroSubLineaList(map);
					letraSubLinea = StringUtils.substring(numeroAnaquel, 0, 1);
					mapRow.put("oidMapaCentroDet", oidMapaCentroDet);
					mapRow.put("oidMapaOrdenCab", sOidMapaOrden);
					mapRow.put("letraSublinea", letraSubLinea);

					// Se valida que no existan Numero de Orden y Numero de Orden Lista Picado repetido para la sublinea
					validacion = isValidoRegistro(list,mapRow);
					if( validacion == 0){
						list.add(mapRow);	
					}
					else{//existe duplicados
						 break;
					}
				}
			}
		}

		if (validacion == 0){
			actualizaNumeroOrden(list);
		}
		excelUtil.cerrar();

		return validacion;
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
		 String numeroOrden = (String)mapRow.get("1");
		 String numeroOrdenLP =  (String)mapRow.get("2");
		 String letraSubLinea = (String)mapRow.get("letraSublinea");
		 
		 if(list!=null && list.size()>0){
			 Iterator it= list.iterator();
			 while(it.hasNext()){
				 Map params = (Map)it.next();
				 // Se valida que se asigne un numero de orden diferente en la sublinea
				 if(letraSubLinea.equals(params.get("letraSublinea")) &&
						 numeroOrden.equals(params.get("1"))){
					 valor = 1;
					 break;
				 }
				 
				 // Se valida que se asigne un numero de orden de Lista de Picado diferente en la sublinea
				 if(letraSubLinea.equals(params.get("letraSublinea")) &&
						 numeroOrdenLP.equals(params.get("2"))){
					 valor = 2;
					 break;
				 }
			 }	 
		 }else{
			 return valor;
		 }		 
		 
		 return valor;//hay duplicado false , caso contrario true
	}
	
	/**
	 * Actualiza el Numero de Orden del Anaquel
	 * @param list
	 */
	private void actualizaNumeroOrden (List list){
		Iterator listIterator = list.iterator();
		Integer nNumeroOrden = 0;
		String sNumeroOredenLP = "";
		String sLetraSubLinea = "";
		String sLetraSubLineaAnterior = "";

		while (listIterator.hasNext()) {
			Map dataInsert = (Map) listIterator.next();
			log.debug("dataInsewrt " + dataInsert);

			sLetraSubLinea = (String) dataInsert.get("letraSublinea");
			if (sLetraSubLinea.equals(sLetraSubLineaAnterior)){
				nNumeroOrden = nNumeroOrden + 1;
			}
			else{
				nNumeroOrden = 1;
			}
			sNumeroOredenLP = (String) dataInsert.get("2");

			dataInsert.put("1",nNumeroOrden.toString());
			dataInsert.put("2",sNumeroOredenLP.substring(0,sNumeroOredenLP.indexOf(".")!=-1?sNumeroOredenLP.indexOf("."):sNumeroOredenLP.length()));

			mantenimientoAPEOrdenAnaquelesDAO.actualizaNumeroOrden(dataInsert);
			
			sLetraSubLineaAnterior = sLetraSubLinea;
		}
	}
}