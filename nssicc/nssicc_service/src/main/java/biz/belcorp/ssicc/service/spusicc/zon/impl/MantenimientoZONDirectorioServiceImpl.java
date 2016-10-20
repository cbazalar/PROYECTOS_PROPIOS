package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONDirectorioDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.HistoricoDirectorioVenta;
import biz.belcorp.ssicc.dao.spusicc.zon.model.PerfilDirectorio;
import biz.belcorp.ssicc.dao.spusicc.zon.model.RolDirectorio;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;

@Service("spusicc.mantenimientoZonDirectorioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoZONDirectorioServiceImpl extends BaseService implements MantenimientoZONDirectorioService {
	
	@Resource(name="spusicc.mantenimientoZONDirectorioDAO")
	private MantenimientoZONDirectorioDAO mantenimientoZONDirectorioDAO;	
	
	@Resource(name="sic.mailReporteGenerarDirectorioVenta")
	private BaseMailService mailReporteGenerarDirectorioVenta;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getTipoCargo(java.util.Map)
	 */
	public List getTipoCargo(Map criteria) {
		return mantenimientoZONDirectorioDAO.getTipoCargo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#deleteCargo(java.util.Map)
	 */
	public void deleteCargo(Map map) {		
		mantenimientoZONDirectorioDAO.deleteCargo(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getDirectorioVentas(java.util.Map)
	 */
	public List getDirectorioVentas(Map map) {
		return mantenimientoZONDirectorioDAO.getDirectorioVentas(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#insertCargo(java.util.Map)
	 */
	public void insertCargo(Map map) {
		mantenimientoZONDirectorioDAO.insertCargo(map);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#updateCargo(java.util.Map)
	 */
	public void updateCargo(Map map) {
		mantenimientoZONDirectorioDAO.updateCargo(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#executeValidacionDirectorio(java.util.Map)
	 */
	public void executeValidacionDirectorio(Map map) throws Exception {
	     String tipoUnidad=(String)map.get("tipoUnidad");
	     String cantidadUnidad=(String)map.get("cantidadUnidad");
	     
	     if(Constants.TIPO_TOTAL_ZONA.equals(tipoUnidad)){
	    	 if(Constants.TIPO_GENERACION_UNITARIA.equals(cantidadUnidad)){
	    		 //String codZona= (String)map.get("codigoZona");	    		 
	    		 mantenimientoZONDirectorioDAO.executeValidacionDirectorio(map);
	    		 String mensaje = (String)map.get("mensajeError");
    			 if(StringUtils.isNotEmpty(mensaje)){
    				 throw new Exception(mensaje);
    			 }
	    	 }else{
	    		 map.put("codigoRegion", null);
	    		 List listCodZona= (List)map.get("codigoZona");
	    		 Iterator it = listCodZona.iterator();
	    		 int index=0;
	    		 while(it.hasNext()){
	    			 index++;
	    			 String codigoZona =(String)it.next();
	    			 map.put("codigoZona", codigoZona);
	    			 map.put("fila", String.valueOf(index));
	    			 mantenimientoZONDirectorioDAO.executeValidacionDirectorio(map);
	    			 String mensaje = (String)map.get("mensajeError");
	    			 if(StringUtils.isNotEmpty(mensaje)){
	    				 throw new Exception(mensaje);
	    			 }
	    		 }
	    	 }
	    	 	    	 	    	 
	     }else{//es region
	    	 
	    	 if(Constants.TIPO_GENERACION_UNITARIA.equals(cantidadUnidad)){
	    		 //String codRegi= (String)map.get("codigoRegion");
	    		 mantenimientoZONDirectorioDAO.executeValidacionDirectorio(map);
	    		 String mensaje = (String)map.get("mensajeError");
    			 if(StringUtils.isNotEmpty(mensaje)){
    				 throw new Exception(mensaje);
    			 }
	    	 }else{

	    		 List listCodRegi= (List)map.get("codigoRegion");	    		 
	    		 Iterator it = listCodRegi.iterator();
	    		 int index=0;
	    		 while(it.hasNext()){
	    			 index++;
	    			 String codigoRegi =(String)it.next();
	    			 map.put("codigoRegion", codigoRegi);
	    			 map.put("fila", String.valueOf(index));
	    			 mantenimientoZONDirectorioDAO.executeValidacionDirectorio(map);
	    			 String mensaje = (String)map.get("mensajeError");
	    			 if(StringUtils.isNotEmpty(mensaje)){
	    				 throw new Exception(mensaje);
	    			 }
	    		 }
	    		 
	    	 }
	     }
	     	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getDirectorioVentasDetalle(java.util.Map)
	 */
	public List getDirectorioVentasDetalle(Map mapCabecera) {
		return mantenimientoZONDirectorioDAO.getDirectorioVentasDetalle(mapCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#executeValidacionDirectorioNombramiento(java.util.Map)
	 */
	public void executeValidacionDirectorioNombramiento(Map map) throws Exception{
	     String tipoUnidad=(String)map.get("tipoUnidad");
	     String cantidadUnidad=(String)map.get("cantidadUnidad");
	     
	     if(Constants.TIPO_TOTAL_ZONA.equals(tipoUnidad)){
	    	 if(Constants.TIPO_GENERACION_UNITARIA.equals(cantidadUnidad)){
	    		 //String codZona= (String)map.get("codigoZona");
	    		 
	    		 mantenimientoZONDirectorioDAO.executeValidacionDirectorioNombramiento(map);
	    		 String mensaje = (String)map.get("mensajeError");
    			 if(StringUtils.isNotEmpty(mensaje)){
    				 throw new Exception(mensaje);
    			 }
	    	 }else{
	    		 map.put("codigoRegion", null);
	    		 List listCodZona= (List)map.get("codigoZona");
	    		 Iterator it = listCodZona.iterator();
	    		 int index=0;
	    		 while(it.hasNext()){
	    			 index++;
	    			 String codigoZona =(String)it.next();
	    			 map.put("codigoZona", codigoZona);
	    			 map.put("fila", String.valueOf(index));
	    			 mantenimientoZONDirectorioDAO.executeValidacionDirectorioNombramiento(map);
	    			 String mensaje = (String)map.get("mensajeError");
	    			 if(StringUtils.isNotEmpty(mensaje)){
	    				 throw new Exception(mensaje);
	    			 }
	    		 }
	    	 }
	    	 	    	 	    	 
	     }else{//es region
	    	 
	    	 if(Constants.TIPO_GENERACION_UNITARIA.equals(cantidadUnidad)){
	    		 //String codRegi= (String)map.get("codigoRegion");
	    		 mantenimientoZONDirectorioDAO.executeValidacionDirectorioNombramiento(map);
	    		 String mensaje = (String)map.get("mensajeError");
    			 if(StringUtils.isNotEmpty(mensaje)){
    				 throw new Exception(mensaje);
    			 }
	    	 }else{

	    		 List listCodRegi= (List)map.get("codigoRegion");	    		 
	    		 Iterator it = listCodRegi.iterator();
	    		 int index=0;
	    		 while(it.hasNext()){
	    			 index++;
	    			 String codigoRegi =(String)it.next();
	    			 map.put("codigoRegion", codigoRegi);
	    			 map.put("fila", String.valueOf(index));
	    			 mantenimientoZONDirectorioDAO.executeValidacionDirectorioNombramiento(map);
	    			 String mensaje = (String)map.get("mensajeError");
	    			 if(StringUtils.isNotEmpty(mensaje)){
	    				 throw new Exception(mensaje);
	    			 }
	    		 }
	    		 
	    	 }
	     }
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getResponsableRegionZona(java.util.Map)
	 */
	public Map getResponsableRegionZona(Map map) {
		return mantenimientoZONDirectorioDAO.getResponsableRegionZona(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getSubgerenciaRegion(java.util.Map)
	 */
	public String getSubgerenciaRegion(Map map) {
		return mantenimientoZONDirectorioDAO.getSubgerenciaRegion(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getSubgerenciaZona(java.util.Map)
	 */
	public String getSubgerenciaZona(Map map) {
		return mantenimientoZONDirectorioDAO.getSubgerenciaZona(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#executeRotacionDirectorio(java.util.Map)
	 */
	public void executeRotacionDirectorio(Map map) throws Exception {
	     String tipoUnidad=(String)map.get("tipoUnidad");
	     List listDetalle = (List)map.get("listDetalle");
	     String [] regiones = (String [])map.get("region");
		 String [] zona = (String [])map.get("zona");
		 //
		 Iterator it = listDetalle.iterator();
		 int index=0;		
		 while(it.hasNext()){
			 index++;
			 Map mapDetalle =(Map)it.next();
			 
			 log.info(((String)mapDetalle.get("codigoRegion")).substring(0, 2));
			 map.put("codigoCliente", (String)mapDetalle.get("codigoCliente"));			
			 map.put("codigoRegionAnterior", ((String)mapDetalle.get("codigoRegion")).substring(0, 2));			 			 			 
			 map.put("codigoRegionNuevo", regiones[index]);
			
             if(Constants.TIPO_TOTAL_ZONA.equals(tipoUnidad)){			 
            	 map.put("codigoZonaAnterior", (String)mapDetalle.get("codigoZona"));
            	 map.put("codigoZonaNuevo", zona[index]);
            	    	 
             }
             map.put("fila", String.valueOf(index));
             mantenimientoZONDirectorioDAO.executeRotacionDirectorio(map);
			 String mensaje = (String)map.get("mensajeError");
			 
			 if(StringUtils.isNotEmpty(mensaje)){
				 throw new Exception(mensaje);
			 }
			
		 }
		 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getMotivoLicencia(java.util.Map)
	 */
	public List getMotivoLicencia(Map map) {
		return mantenimientoZONDirectorioDAO.getMotivoLicencia(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#executeLicenciaPersona(java.util.Map)
	 */
	public void executeLicenciaPersona(Map map) throws Exception {

		 String tipoUnidadReemplazo=(String)map.get("tipoUnidadReemplazo");
	     String tipoUnidadReemplazado=(String)map.get("tipoUnidad");
	     List listDetalleReemplazo = (List)map.get("listDetalleReemplazo");	     
	     List listDetalleReemplazado = (List)map.get("listDetalleReemplazado");
	     //map.put("codigoRegion", value)
	     Iterator itReemplazo = listDetalleReemplazo.iterator();
	     Iterator itReemplazado=listDetalleReemplazado.iterator();
		 int index=0;	
		 log.info("tipo unidad administrativa");
		 log.info(tipoUnidadReemplazo);
		 log.info(tipoUnidadReemplazado);
		 Map mapDetalleReemplazado =(Map)itReemplazado.next();
		 map.put("codigoRegionReemplazado",  mapDetalleReemplazado.get("codigoRegion").toString().substring(0, 2));
		 if(Constants.TIPO_TOTAL_ZONA.equals(tipoUnidadReemplazado)){
			 map.put("codigoZona", mapDetalleReemplazado.get("codigoZona").toString().substring(0, 4));
		 }
		
		 log.info(map.get("codigoRegionReemplazado"));
		 
		 while(itReemplazo.hasNext()){
			 index++;			
		 
			
			 Map mapDetalleReemplazo=(Map)itReemplazo.next();
			 log.info(mapDetalleReemplazo.get("codigoRegion").toString().substring(0, 2));
			 map.put("codigoRegionReemplazo", mapDetalleReemplazo.get("codigoRegion").toString().substring(0, 2));			 			 
             if(Constants.TIPO_TOTAL_ZONA.equals(tipoUnidadReemplazo)){
            
            	 map.put("codigoZonaReemplazo", mapDetalleReemplazo.get("codigoZona").toString().substring(0, 4)); 
        
             }                          
             map.put("fila", String.valueOf(index));            
                                 		                         
             
             mantenimientoZONDirectorioDAO.executeLicenciaPersona(map);
             
			 String mensaje = (String)map.get("mensajeError");
			 if(StringUtils.isNotEmpty(mensaje)){				 
				 throw new Exception(mensaje);
			 }
		 }
	  
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#executeReactivacionLicenciaPersona(java.util.Map)
	 */
	public void executeReactivacionLicenciaPersona(Map map) throws Exception{
	     String tipoUnidad=(String)map.get("tipoUnidad");
	     List listDetalle = (List)map.get("listDetalle");
	     Iterator it = listDetalle.iterator();
		 int index=0;
		 while(it.hasNext()){
			 index++;
			 Map mapDetalle =(Map)it.next();
			 map.put("codigoCliente", (String)mapDetalle.get("codigoCliente"));
			 map.put("codigoRegion", (String)mapDetalle.get("codigoRegion"));			 			 
             if(Constants.TIPO_TOTAL_ZONA.equals(tipoUnidad)){			 
            	 map.put("codigoZona", (String)mapDetalle.get("codigoZona")); 
        
             }
             map.put("fila", String.valueOf(index));
             mantenimientoZONDirectorioDAO.executeReactivacionLicenciaPersona(map);
			 String mensaje = (String)map.get("mensajeError");
			 if(StringUtils.isNotEmpty(mensaje)){
				 throw new Exception(mensaje);
			 }
		 }
				
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#executeRetiroDirectorio(java.util.Map)
	 */
	public void executeRetiroDirectorio(Map map) throws Exception{
	     String tipoUnidad=(String)map.get("tipoUnidad");
	     List listDetalle = (List)map.get("listDetalle");
	     Iterator it = listDetalle.iterator();
		 int index=0;
		 while(it.hasNext()){
			 index++;
			 Map mapDetalle =(Map)it.next();
			 map.put("codigoCliente", (String)mapDetalle.get("codigoCliente"));
			 map.put("codigoRegion", ((String)mapDetalle.get("codigoRegion")).substring(0, 2));			 			 
             if(Constants.TIPO_TOTAL_ZONA.equals(tipoUnidad)){			 
            	 map.put("codigoZona", (String)mapDetalle.get("codigoZona")); 
        
             }
             map.put("fila", String.valueOf(index));
             mantenimientoZONDirectorioDAO.executeRetiroDirectorio(map);
			 String mensaje = (String)map.get("mensajeError");
			 if(StringUtils.isNotEmpty(mensaje)){
				 throw new Exception(mensaje);
			 }
			
		 }
				
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getOperaciones(java.util.Map)
	 */
	public List getOperaciones(Map map) {
		return mantenimientoZONDirectorioDAO.getOperaciones(map);
	}
	

	public void updateParametroRutaEnvioCorreo(Map criteria) {
		mantenimientoZONDirectorioDAO.updateParametroRutaEnvioCorreo(criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getLicenciasActivas(java.util.Map)
	 */
	public List getLicenciasActivas(Map map) {
		return mantenimientoZONDirectorioDAO.getLicenciasActivas(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#executeReactivacionLicencia(java.util.Map)
	 */
	public void executeReactivacionLicencia(Map params) {	
		mantenimientoZONDirectorioDAO.executeReactivacionLicencia(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getDescripcionCargo(java.util.Map)
	 */
	public List getDescripcionCargo(Map criteria) {
		return mantenimientoZONDirectorioDAO.getDescripcionCargo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getClientesByCriteria(java.util.Map)
	 */
	public List getClientesByCriteria(Map criteria) {
		return mantenimientoZONDirectorioDAO.getClientesByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getLicenciaList(java.util.Map)
	 */
	public List getLicenciaList(Map criteria) {
		return mantenimientoZONDirectorioDAO.getLicenciaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#executeAprobarOperacion(java.util.Map)
	 */
	public String executeAprobarOperacion(Map criteria) {		
		mantenimientoZONDirectorioDAO.executeAprobarOperacion(criteria);
		String resul=criteria.get("codigoError").toString();
		return resul;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getAprobarOperacionIN(java.util.Map)
	 */
	public List getAprobarOperacionIN(Map criteria) {
		return mantenimientoZONDirectorioDAO.getAprobarOperacionIN(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getAprobarOperacionLI(java.util.Map)
	 */
	public List getAprobarOperacionLI(Map criteria) {
		return mantenimientoZONDirectorioDAO.getAprobarOperacionLI(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getAprobarOperacionNM(java.util.Map)
	 */
	public List getAprobarOperacionNM(Map criteria) {
		return mantenimientoZONDirectorioDAO.getAprobarOperacionNM(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getAprobarOperacionRE(java.util.Map)
	 */
	public List getAprobarOperacionRE(Map criteria) {
		return mantenimientoZONDirectorioDAO.getAprobarOperacionRE(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getAprobarOperacionRO(java.util.Map)
	 */
	public List getAprobarOperacionRO(Map criteria) {
		return mantenimientoZONDirectorioDAO.getAprobarOperacionRO(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getClientesReemplazoByCriteria(java.util.Map)
	 */
	public List getClientesReemplazoByCriteria(Map criteria) {		
		return mantenimientoZONDirectorioDAO.getClientesReemplazoByCriteria(criteria);
	}

	public void deleteLicencia(String[] items, String codigoUsuario) {
			Map criteria = new HashMap();
			criteria.put("codigoUsuario", codigoUsuario);
			for(int i = 0; i < items.length; i++){	
				String id = items[i];
				
				criteria.put("codigoCliente", StringUtils.split(id, "|")[0]);
				criteria.put("codigoCargo", StringUtils.split(id, "|")[1]);
				criteria.put("codigoOperacion", StringUtils.split(id,"|")[2]);
				criteria.put("fechaOperacion", StringUtils.split(id,"|")[6]);
				mantenimientoZONDirectorioDAO.deleteLicenciaCab(criteria);
				mantenimientoZONDirectorioDAO.deleteLicenciaDet(criteria);
				
				
			}
		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getDirectorioVentasList(java.util.Map)
	 */
	public List getDirectorioVentasList(Map criteria) {
		return mantenimientoZONDirectorioDAO.getDirectorioVentasList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getObtenerGerentexTipo(java.util.Map)
	 */
	public String getObtenerGerentexTipo(Map criteria) {
		return mantenimientoZONDirectorioDAO.getObtenerGerentexTipo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getExisteUsuarioDirectorioVenta(java.util.Map)
	 */
	public String getExisteUsuarioDirectorioVenta(Map criteria) {
		return mantenimientoZONDirectorioDAO.getExisteUsuarioDirectorioVenta(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getValidaEstadoCargo(java.util.Map)
	 */
	public Map getValidaEstadoCargo(Map criteria) {
		return mantenimientoZONDirectorioDAO.getValidaEstadoCargo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#obtenerDirectorioVentaCabecera(java.util.Map)
	 */
	public Map obtenerDirectorioVentaCabecera(Map criteria) {
		return mantenimientoZONDirectorioDAO.obtenerDirectorioVentaCabecera(criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#obtenerDirectorioVentaDetalle(java.util.Map)
	 */
	public Map obtenerDirectorioVentaDetalle(Map criteria) {
		return mantenimientoZONDirectorioDAO.obtenerDirectorioVentaDetalle(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getTipoLicenciaList()
	 */
	public List getTipoLicenciaList() {
		return mantenimientoZONDirectorioDAO.getTipoLicenciaList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#enviarCorreo(java.util.Map)
	 */
	public void enviarCorreo(Map criteria) {
		//en listaUAS, tipoUA viene la lista de zonas o regiones que fuero asignados
		
		String tipoOperacion = (String)criteria.get("tipoOperacion");
		
		if (StringUtils.equals(tipoOperacion, Constants.ZON_MANT_CODIGO_OPERACION_IN)) {			
			criteria.put("bodyHtml","MailHtmlReporteZONGenerarAsignarCambiarCargo.vm");
			criteria.put("bodyTxt","MailHtmlReporteZONGenerarAsignarCambiarCargo.vm");
			
			//parametros de Velocity
			//${listaUAs} ${nombresCompletosConsultora} ${CargoConsultora} ${fechaRegistro}
			
			criteria.put("fechaRegistro", DateUtil.convertDateToString("dd/MM/yyyy", new Date()));
			
		}else if (StringUtils.equals(tipoOperacion, Constants.ZON_MANT_CODIGO_OPERACION_NM)){
			criteria.put("bodyHtml","MailHtmlReporteZONGenerarCambiarCargo.vm");
			criteria.put("bodyTxt","MailHtmlReporteZONGenerarCambiarCargo.vm");
			
			//parametros de Velocity
			//${listaUAs} ${nombresCompletosConsultora} ${CargoConsultora} ${fechaRegistro}
			
			criteria.put("fechaRegistro", DateUtil.convertDateToString("dd/MM/yyyy", new Date()));
			
		}else if (StringUtils.equals(tipoOperacion, Constants.ZON_MANT_CODIGO_OPERACION_LI)) {
			
			criteria.put("bodyHtml","MailHtmlReporteZONGenerarLicenciaConsultoraReemplazoCargo.vm");
			criteria.put("bodyTxt","MailHtmlReporteZONGenerarLicenciaConsultoraReemplazoCargo.vm");
			
		}else if (StringUtils.equals(tipoOperacion, Constants.ZON_MANT_CODIGO_OPERACION_RO)) {
			
			criteria.put("bodyHtml","MailHtmlReporteZONGenerarRotarCargos.vm");
			criteria.put("bodyTxt", "MailHtmlReporteZONGenerarRotarCargos.vm");
			
			criteria.put("fechaRegistro", DateUtil.convertDateToString("dd/MM/yyyy", new Date()));
		}else if (StringUtils.equals(tipoOperacion, Constants.ZON_MANT_CODIGO_OPERACION_RE)) {
			
			criteria.put("bodyHtml","MailHtmlReporteZONGenerarRetirarPersonal.vm");
			criteria.put("bodyTxt", "MailHtmlReporteZONGenerarRetirarPersonal.vm");
			
			criteria.put("ingresoZona", criteria.get("codigoZona"));
			criteria.put("fechaRetiro", DateUtil.convertDateToString("dd/MM/yyyy", new Date()));
			
		}
		
		criteria.put("codigoCargo", MapUtils.getString(criteria, "codigoTipoCargo"));		
		Map map = (Map)mantenimientoZONDirectorioDAO.getTipoCargo(criteria).get(0);
		criteria.put("CargoConsultora", MapUtils.getString(criteria, "codigoTipoCargo") +" - "+ MapUtils.getString(map, "descripcion"));
		
		//
		//Cargamos los datos de la consultora siempre, ya que todos los metodos reutilizan esta funcionalidad.
		//if(StringUtils.isBlank(MapUtils.getString(criteria, "nombresCompletosConsultora", "")))
		//{
			String datos = mantenimientoZONDirectorioDAO.obtenerDatosConsultora(criteria);
			criteria.put("nombresCompletosConsultora", StringUtils.split(datos,"_")[0]);
		//}
		//		
		
		//Datos GENERALES para el envio de correo
		List listCorreosMap = mantenimientoZONDirectorioDAO.getObtenerDatosCorreos(criteria);
		
		if(listCorreosMap != null && listCorreosMap.size() > 0)
		{
			Map correosMap = (Map)listCorreosMap.get(0);
			
			criteria.put("correoOrigen", correosMap.get("emailOrigen"));
			
			criteria.put("tipoNovedad", correosMap.get("valorAsunto").toString().toUpperCase());
			
			String subject = (String)correosMap.get("valorAsunto");
			
			criteria.put("subject", subject);
			criteria.put("correosDestino", correosMap.get("emailUsuarioAprobado"));
			criteria.put("listaCorreosDestinos", correosMap.get("emailListDestino"));
			
			//-- Variables
			MailParams mailParams = new MailParams();
			//-- Configurar Parametros
			criteria.put("parameterMap",criteria);
			mailParams.setQueryParams(criteria);
			
			//-- Envio de correo
			try {
				mailReporteGenerarDirectorioVenta.enviarMail(mailParams);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			log.warn("No existen destinatarios para enviar correo.");
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getCargosList()
	 */
	public List getCargosList(Map criteria) {
		return mantenimientoZONDirectorioDAO.getCargosList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getZonDirecVentaCabecList(java.util.Map)
	 */
	public List getZonDirecVentaCabecList(Map criteria) {		
		return mantenimientoZONDirectorioDAO.getZonDirecVentaCabecList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getZonDirecVentaDetalList(java.util.Map)
	 */
	public List getZonDirecVentaDetalList(Map criteria) {
		return mantenimientoZONDirectorioDAO.getZonDirecVentaDetalList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getRegionesDisp(java.util.Map)
	 */
	public List getRegionesDisp(Map map) {
		return mantenimientoZONDirectorioDAO.getRegionesDisp(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getHistoricoList(java.util.Map)
	 */
	public List getHistoricoList(Map criteria) {
		return mantenimientoZONDirectorioDAO.getHistoricoList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getZonaDisp(java.util.Map)
	 */
	public List getZonaDisp(Map criteria) {
		return mantenimientoZONDirectorioDAO.getZonaDisp(criteria);
	}

	public void insertDirectorioVentaDetalleRot(Map criteria){
		mantenimientoZONDirectorioDAO.insertDirectorioVentaDetalle(criteria);
//		enviarCorreo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#updateDirectorioVentaDetalleRot(java.util.Map)
	 */
	public void updateDirectorioVentaDetalleRot(Map criteria) {
		
		mantenimientoZONDirectorioDAO.updateDirectorioVentaDetalle(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getZonDirecVentaDetalSoloList(java.util.Map)
	 */
	public List getZonDirecVentaDetalSoloList(Map map) {
		return mantenimientoZONDirectorioDAO.getZonDirecVentaDetalSoloList(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getMaeCliente(java.util.Map)
	 */
	public List getMaeCliente(Map map) {
		return mantenimientoZONDirectorioDAO.getMaeCliente(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#insertDirectorioVenta(java.util.Map)
	 */
	public void insertDirectorioVenta(Map criteria) {
		
		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		String correlativoCabeceraAnterior = MapUtils.getString(criteria, "correlativoCabecera", "");
		
		//Obtenemos el correlativo del directorio
		String correlativoCabecera = mantenimientoZONDirectorioDAO.getCorrelativoCabeceraDirectorio();
		criteria.put("correlativoCabecera", correlativoCabecera);
		
		
		mantenimientoZONDirectorioDAO.insertDirectorioVentaCabecera(criteria);
		
		String[] regionList = (String[])criteria.get("codigoRegion");
		String[] zonaList = (String[]) criteria.get("codigoZona");
		String codigoSubGerencia = (String)criteria.get("codigoSubGerencia");
		String tipoOperacion = (String) criteria.get("tipoOperacion");
		String codigoSubgerenciaZona = "";
		String envioCorreo = (String)criteria.get("envioCorreo");
		String codigoTipoCargo = (String) criteria.get("codigoTipoCargo");
		String codigoCargoAnterior = (String) criteria.get("codigoCargoAnterior");
									
		List listaUAs = new ArrayList();
		
		if(zonaList != null)
		{
			for (int i = 0; i < regionList.length; i++) {
				for (int j = 0; j < zonaList.length; j++) {
					criteria.put("codigoRegion", regionList[i].toString());
					criteria.put("codigoZona", zonaList[j].toString());
					
					//1 = Region Pertenece a Periodo, 0 = Region no pertenece a Region
					int validarRegionZona = 0;
					
					if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
						validarRegionZona = mantenimientoZONDirectorioDAO.getValidaRegionxZonaFOX(criteria);
					else
						validarRegionZona = mantenimientoZONDirectorioDAO.getValidaRegionxZona(criteria);
					
					if (validarRegionZona == 0) 
						continue;
					
					if(StringUtils.isEmpty(codigoSubGerencia)){
						if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
						{
							criteria.put("codigoSubGerencia", Constants.ZON_CODIGO_SUBGERENCIA_DEFAULT);
						}
						else
						{
							codigoSubgerenciaZona = mantenimientoZONDirectorioDAO.getSubgerenciaZona(criteria);
							criteria.put("codigoSubGerencia", codigoSubgerenciaZona.substring(0, 2));
						}
					}
					//Agregamos a la lista para que sea enviada por correo
					listaUAs.add(String.format("%s - %s", regionList[i].toString(), zonaList[j].toString()));
					
					mantenimientoZONDirectorioDAO.insertDirectorioVentaDetalle(criteria);
					
					//Si es Gerente de Zona actualiza el id del gerente en la tabla ZON_ZONA
					if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
						if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
						{
							mantenimientoZONDirectorioDAO.updateGerenciaZonaFOX(criteria);
							
							criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
							criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
							criteria.put("codigoZona",(String)criteria.get("codigoZona"));
							criteria.put("estadoRegistro", Constants.NUMERO_UNO);
							
							mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegionFOX(criteria);
						}
						else
						{
							mantenimientoZONDirectorioDAO.updateGerenciaZona(criteria);
							
							criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
							criteria.put("codigoSubGerencia",(String)criteria.get("codigoSubGerencia"));
							criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
							criteria.put("codigoZona",(String)criteria.get("codigoZona"));
							
							mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(criteria);
						}						
					}
					
					//Si es Gerente de Region actualiza el id del gerente en la tabla ZON_REGIO
					if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
						if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
						{
							mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(criteria);
							
							criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
							criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
							criteria.put("estadoRegistro", Constants.NUMERO_UNO);
							
							mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegionFOX(criteria);
						}
						else
						{
							mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteria);
							
							criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
							criteria.put("codigoSubGerencia",(String)criteria.get("codigoSubGerencia"));
							criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
							
							mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(criteria);
						}
					}
					
					//Actualizar Historicos del cliente cuando existe cambio de cargo
					if(!StringUtils.isEmpty(codigoCargoAnterior)){						
						
						//Si el cargo Anterior es Gerente de Zona o Region
						if(codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_ZONA)||codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_REGION)){

							//Baja de Cliente de Directorio de Ventas
							String unidadAdministrativa = MapUtils.getString(criteria, "codigoSubGerencia", "") + MapUtils.getString(criteria, "codigoRegionAnterior", "") + MapUtils.getString(criteria, "codigoZonaAnterior", "");
							criteria.put("unidadAdministrativa",unidadAdministrativa);							
							
							if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
							{
								List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByClienteFOX(criteria);

								if(listaHistorico != null && listaHistorico.size()>0){
									Map ultimoHistorico = (Map)listaHistorico.get(0);
									String corHistoricoGerente = (String)ultimoHistorico.get("corHistoricoGerente");
									String camDesde = (String)ultimoHistorico.get("campDesde");
									
									String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
										Map map1 = new HashMap();
										map1.put("codigoPais", (String)criteria.get("codigoPais"));
										map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
										map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
										map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
										map1.put("fecha", fechaIngreso);
									String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
									
									if(camDesde.equals(campIngreso)){
										criteria.put("indicadorResta1", null);
										criteria.put("corHistoricoGerente", corHistoricoGerente);
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteria);
										
										//Se elimina el registro cuando campaaDesde es igual a campaaIngreso(Obtenida con la fechaIngreso). 
										criteria.put("unidadAdministrativa", unidadAdministrativa);
										criteria.put("campDesde", (String)ultimoHistorico.get("campDesde"));
										mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByClienteFOX(criteria);
									}else{
										criteria.put("indicadorResta1", "1");
										criteria.put("corHistoricoGerente", corHistoricoGerente);
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteria);
									}
									
								}						
								if(codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_ZONA)){
									Map criteriaZona = new HashMap();
									criteriaZona.put("codigoPais", (String)criteria.get("codigoPais"));
									criteriaZona.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
									criteriaZona.put("codigoZona", (String)criteria.get("codigoZonaAnterior"));
									criteriaZona.put("codigoConsultora", null);
									criteriaZona.put("usuarioLogin", (String)criteria.get("usuarioLogin"));									
									mantenimientoZONDirectorioDAO.updateGerenciaZonaFOX(criteriaZona);
								}else if(codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_REGION)){
									Map criteriaRegion = new HashMap();
									criteriaRegion.put("codigoPais", (String)criteria.get("codigoPais"));									
									criteriaRegion.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
									criteriaRegion.put("codigoConsultora", null);
									criteriaRegion.put("usuarioLogin", (String)criteria.get("usuarioLogin"));									
									mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(criteriaRegion);
								}
							}
							else
							{
								List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByCliente(criteria);
								
								if(listaHistorico != null && listaHistorico.size()>0){
									Map ultimoHistorico = (Map)listaHistorico.get(0);
									String oidUltimoHistorico = (String)ultimoHistorico.get("oidHistoricoGerente"); 
									String fechaDesde = (String)ultimoHistorico.get("fechaDesde"); //Para obtener la campaaDesde
										Map map1 = new HashMap();
										map1.put("codigoPais", (String)criteria.get("codigoPais"));
										map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
										map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
										map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
										map1.put("fecha", fechaDesde);
									String campDesde = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
									
									String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
										Map map2 = new HashMap();
										map2.put("codigoPais", (String)criteria.get("codigoPais"));
										map2.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
										map2.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
										map2.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
										map2.put("fecha", fechaIngreso);
									String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map2);
									
									if(campDesde.equals(campIngreso)){ //Se comparan las campaas
										criteria.put("indicadorResta1", null);
										criteria.put("oidHistoricoGerencia", oidUltimoHistorico);
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteria);
										
										//Se elimina el registro cuando campaaDesde es igual a campaaIngreso(Obtenida con la fechaIngreso). 
										Map map3 = new HashMap();
										map3.put("codigoPais",(String)criteria.get("codigoPais"));
										map3.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
										map3.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
										map3.put("unidadAdministrativa", unidadAdministrativa);
										map3.put("fechaIngreso", (String)ultimoHistorico.get("fechaDesde"));
										mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByCliente(map3);
									}else{
										criteria.put("indicadorResta1", "1");
										criteria.put("oidHistoricoGerencia", oidUltimoHistorico);
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteria);
									}
									
								}		
								
								if(codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_ZONA)){
									Map criteriaZona = new HashMap();
									criteriaZona.put("codigoZona", (String)criteria.get("codigoZonaAnterior"));
									criteriaZona.put("oidCliente", null);
									mantenimientoZONDirectorioDAO.updateGerenciaZona(criteriaZona);
								}else if(codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_REGION)){
									Map criteriaRegion = new HashMap();
									criteriaRegion.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
									criteriaRegion.put("oidCliente", null);
									mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteriaRegion);
								}
							}							
						}
						
						//Obtenemos la fechaRegiFin y fechaRegi de la Cabecera
						//Si no existe fechaRegiFin, y si la fechaRegi = fechaIngreso ,
						//se actualiza la fechaRegiFin anterior con la novedad(fechaIngreso -1). 
						Map cabec = new HashMap();
						cabec.put("correlativoCabecera", correlativoCabeceraAnterior);
						cabec.put("estadoRegistro", Constants.ESTADO_ACTIVO);
						cabec.put("indicadorEstado", Constants.ESTADO_DIRECTORIO_ACTIVO);
						Map cabecResul = mantenimientoZONDirectorioDAO.obtenerDirectorioVentaCabecera(cabec);
						String fechaRegi = DateUtil.convertDateToString("dd/MM/yyyy", (Date)cabecResul.get("fechaRegistro"));
						String fechaRegiFin = DateUtil.convertDateToString("dd/MM/yyyy", (Date)cabecResul.get("fechaRegiFin"));
						
						//Actualizar Directorio de Ventas
						Map criteriaDirectorio = new HashMap();
						criteriaDirectorio.put("codigoConsultora", (String)criteria.get("codigoConsultora"));
						criteriaDirectorio.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
						criteriaDirectorio.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_INACTIVA);
						criteriaDirectorio.put("fechaIngreso", (String)criteria.get("fechaRegistroCargoAnterior"));
						criteriaDirectorio.put("codigoTipoCargo", (String)criteria.get("codigoCargoAnterior"));
						criteriaDirectorio.put("codigoPais", (String)criteria.get("codigoPais"));
						criteriaDirectorio.put("correlativoCabecera", correlativoCabeceraAnterior);
						
						if(StringUtils.isBlank(fechaRegiFin)){
							if(!StringUtils.isBlank(fechaRegi) && !fechaRegi.equals((String)criteria.get("fechaIngreso"))){
								
								int diferenciaEnDias = 1;
								Date fi;
								try {
									fi = DateUtil.convertStringToDate("dd/MM/yyyy", (String)criteria.get("fechaIngreso"));
									long tiempoFI = fi.getTime();
									long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;
									Date fechaAyer = new Date(tiempoFI - unDia);
									System.out.println(fechaAyer.toString());
									String fecNoveMenos1 = DateUtil.convertDateToString("dd/MM/yyyy", fechaAyer);
									criteriaDirectorio.put("fechaIngresoHasta", fecNoveMenos1);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}else{
								criteriaDirectorio.put("fechaIngresoHasta", (String)criteria.get("fechaIngreso"));
							}
							
						}
						mantenimientoZONDirectorioDAO.updateDirectorioVentaCabecera(criteriaDirectorio);
												
					}
				}
			}
			
			//Se usa en las plantillas
			criteria.put("tipoUA", Constants.ZON_MANT_GERENTE_ZONA);
		}
		else
		{
			for (int i = 0; i < regionList.length; i++) {
				criteria.put("codigoRegion", regionList[i].toString());
				criteria.put("codigoZona", null);
				if(StringUtils.isEmpty(codigoSubGerencia)){
					
					if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
					{
						criteria.put("codigoSubGerencia", Constants.ZON_CODIGO_SUBGERENCIA_DEFAULT);
					}
					else
					{
						codigoSubgerenciaZona = mantenimientoZONDirectorioDAO.getSubgerenciaRegion(criteria);
						criteria.put("codigoSubGerencia", codigoSubgerenciaZona.substring(0, 2));
					}
				}
				
				mantenimientoZONDirectorioDAO.insertDirectorioVentaDetalle(criteria);
				
				//Si es Gerente de Region actualiza el id del gerente en la tabla ZON_REGIO
				if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
					
					if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
					{
						mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(criteria);	
						
						criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
						criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
						criteria.put("estadoRegistro", Constants.NUMERO_UNO);
						
						mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegionFOX(criteria);						
					}
					else
					{
						mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteria);
						
						criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
						criteria.put("codigoSubGerencia",(String)criteria.get("codigoSubGerencia"));
						criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
						
						mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(criteria);
					}
				}
				
				//Actualizar Historicos del cliente cuando existe cambio de cargo
				if(!StringUtils.isEmpty(codigoCargoAnterior)){						
					
					//Si el cargo Anterior es Gerente de Zona o Region
					if(codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_ZONA)||codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_REGION)){
						
						String unidadAdministrativa = MapUtils.getString(criteria, "codigoSubGerencia", "") + MapUtils.getString(criteria, "codigoRegionAnterior", "") + MapUtils.getString(criteria, "codigoZonaAnterior", "");
						criteria.put("unidadAdministrativa",unidadAdministrativa);
						
						//Baja de Cliente de Directorio de Ventas
						if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
						{
							List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByClienteFOX(criteria);
							if(listaHistorico != null && listaHistorico.size()>0){
								Map ultimoHistorico = (Map)listaHistorico.get(0);
								String corHistoricoGerente = (String)ultimoHistorico.get("corHistoricoGerente");
								String camDesde = (String)ultimoHistorico.get("campDesde");
								String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
									Map map1 = new HashMap();
									map1.put("codigoPais", (String)criteria.get("codigoPais"));
									map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
									map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
									map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
									map1.put("fecha", fechaIngreso);
								String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);

								if(camDesde.equals(campIngreso)){
									criteria.put("indicadorResta1", null);
									criteria.put("corHistoricoGerente", corHistoricoGerente);
									mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteria);
									
									//Se elimina el registro cuando campaaDesde es igual a campaaIngreso(Obtenida con la fechaIngreso). 
									criteria.put("unidadAdministrativa", unidadAdministrativa);
									criteria.put("campDesde", (String)ultimoHistorico.get("campDesde"));
									mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByClienteFOX(criteria);
								}else{
									criteria.put("indicadorResta1", "1");
									criteria.put("corHistoricoGerente", corHistoricoGerente);
									mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteria);
								}
								
							}						
							if(codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_ZONA)){
								Map criteriaZona = new HashMap();
								criteriaZona.put("codigoPais", (String)criteria.get("codigoPais"));
								criteriaZona.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
								criteriaZona.put("codigoZona", (String)criteria.get("codigoZonaAnterior"));
								criteriaZona.put("codigoConsultora", null);
								criteriaZona.put("usuarioLogin", (String)criteria.get("usuarioLogin"));									
								mantenimientoZONDirectorioDAO.updateGerenciaZonaFOX(criteriaZona);
							}else if(codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_REGION)){
								Map criteriaRegion = new HashMap();
								criteriaRegion.put("codigoPais", (String)criteria.get("codigoPais"));									
								criteriaRegion.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
								criteriaRegion.put("codigoConsultora", null);
								criteriaRegion.put("usuarioLogin", (String)criteria.get("usuarioLogin"));									
								mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(criteriaRegion);
							}							
						}
						else
						{							
							List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByCliente(criteria);
							if(listaHistorico != null && listaHistorico.size()>0){
								Map ultimoHistorico = (Map)listaHistorico.get(0);
								String oidUltimoHistorico = (String)ultimoHistorico.get("oidHistoricoGerente"); 
								String fechaDesde = (String)ultimoHistorico.get("fechaDesde"); //Para obtener la campaaDesde
									Map map1 = new HashMap();
									map1.put("codigoPais", (String)criteria.get("codigoPais"));
									map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
									map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
									map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
									map1.put("fecha", fechaDesde);
								String campDesde = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
								
								String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
									Map map2 = new HashMap();
									map2.put("codigoPais", (String)criteria.get("codigoPais"));
									map2.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
									map2.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
									map2.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
									map2.put("fecha", fechaIngreso);
								String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map2);
								
								if(campDesde.equals(campIngreso)){ //Se comparan las campaas
									criteria.put("indicadorResta1", null);
									criteria.put("oidHistoricoGerencia", oidUltimoHistorico);
									mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteria);
									
									//Se elimina el registro cuando campaaDesde es igual a campaaIngreso(Obtenida con la fechaIngreso). 
									Map map3 = new HashMap();
									map3.put("codigoPais",(String)criteria.get("codigoPais"));
									map3.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
									map3.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
									map3.put("unidadAdministrativa", unidadAdministrativa);
									map3.put("fechaIngreso", (String)ultimoHistorico.get("fechaDesde"));
									mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByCliente(map3);
								}else{
									criteria.put("indicadorResta1", "1");
									criteria.put("oidHistoricoGerencia", oidUltimoHistorico);
									mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteria);
								}
								
							}	
							
							if(codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_ZONA)){
								Map criteriaZona = new HashMap();
								criteriaZona.put("codigoZona", (String)criteria.get("codigoZonaAnterior"));
								criteriaZona.put("oidCliente", null);
								mantenimientoZONDirectorioDAO.updateGerenciaZona(criteriaZona);
							}else if(codigoCargoAnterior.equals(Constants.ZON_MANT_GERENTE_REGION)){
								Map criteriaRegion = new HashMap();
								criteriaRegion.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
								criteriaRegion.put("oidCliente", null);
								mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteriaRegion);
							}
						}
					}		
					
					//Obtenemos la fechaRegiFin y fechaRegi de la Cabecera
					//Si no existe fechaRegiFin, y si la fechaRegi = fechaIngreso ,
					//se actualiza la fechaRegiFin anterior con la novedad(fechaIngreso -1).  
					Map cabec = new HashMap();
					cabec.put("correlativoCabecera", correlativoCabeceraAnterior);
					cabec.put("estadoRegistro", Constants.ESTADO_ACTIVO);
					cabec.put("indicadorEstado", Constants.ESTADO_DIRECTORIO_ACTIVO);

					Map cabecResul = mantenimientoZONDirectorioDAO.obtenerDirectorioVentaCabecera(cabec);
					Date fechaRegi = (Date)cabecResul.get("fechaRegistro");
					Date fechaRegiFin = (Date)cabecResul.get("fechaRegiFin");
										
					//Actualizar Directorio de Ventas
					Map criteriaDirectorio = new HashMap();
					criteriaDirectorio.put("codigoConsultora", (String)criteria.get("codigoConsultora"));
					criteriaDirectorio.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
					criteriaDirectorio.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_INACTIVA);
					criteriaDirectorio.put("fechaIngreso", (String)criteria.get("fechaRegistroCargoAnterior"));
					criteriaDirectorio.put("codigoTipoCargo", (String)criteria.get("codigoCargoAnterior"));
					criteriaDirectorio.put("codigoPais", (String)criteria.get("codigoPais"));
					criteriaDirectorio.put("correlativoCabecera", correlativoCabeceraAnterior);
					
					if(StringUtils.isBlank(DateUtil.convertDateToString("dd/MM/yyyy",fechaRegiFin))){
						if(!StringUtils.isBlank(DateUtil.convertDateToString("dd/MM/yyyy",fechaRegi)) &&
								!DateUtil.convertDateToString("dd/MM/yyyy",fechaRegi).equals((String)criteria.get("fechaIngreso"))){
							
							int diferenciaEnDias = 1;
							Date fi;
							try {
								fi = DateUtil.convertStringToDate("dd/MM/yyyy", (String)criteria.get("fechaIngreso"));
								long tiempoFI = fi.getTime();
								long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;
								Date fechaAyer = new Date(tiempoFI - unDia);
								System.out.println(fechaAyer.toString());
								String fecNoveMenos1 = DateUtil.convertDateToString("dd/MM/yyyy", fechaAyer);
								criteriaDirectorio.put("fechaIngresoHasta", fecNoveMenos1);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}else{
							criteriaDirectorio.put("fechaIngresoHasta", (String)criteria.get("fechaIngreso"));
						}

					}
					mantenimientoZONDirectorioDAO.updateDirectorioVentaCabecera(criteriaDirectorio);
											
				}
				
				listaUAs.add(String.format(regionList[i].toString()));
			}
			//Se usa en las plantillas
			criteria.put("tipoUA", Constants.ZON_MANT_GERENTE_REGION);
		}				

		//Seteamos el valor de UAS
		//Se usa en las plantillas
		criteria.put("listaUAs", listaUAs);
		//
				
		if (StringUtils.equals(envioCorreo, Constants.SI)) {
			
			enviarCorreo(criteria);
			//actualizar en la entidad Parmetros de Ruta el atributo 
			//Indicador de Activar Envo a Destinatarios con 0 (cero = Desactivar ) para el tipo de operacion
			mantenimientoZONDirectorioDAO.updateParametroRutaEnvioCorreo(criteria);
		}		
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#insertDirectorioVentaRotacion(java.util.Map, java.util.List)
	 */
	public void insertDirectorioVentaRotacion(Map criteria) {
		
		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		String correlativoCabeceraAnterior = MapUtils.getString(criteria, "correlativoCabecera");
		
		String correlativoCabecera = mantenimientoZONDirectorioDAO.getCorrelativoCabeceraDirectorio();
		criteria.put("correlativoCabecera", correlativoCabecera);
		
		mantenimientoZONDirectorioDAO.insertDirectorioVentaCabecera(criteria);
		
		String[] regionList = (String[])criteria.get("codigoRegion");
		String[] zonaList = (String[]) criteria.get("codigoZona");
		String codigoSubGerencia = (String)criteria.get("codigoSubGerencia");
		String tipoOperacion = (String) criteria.get("tipoOperacion");
		String codigoSubgerenciaZona = "";
		String envioCorreo = (String)criteria.get("envioCorreo");
		String codigoTipoCargo = (String) criteria.get("codigoTipoCargo");
		String codigoCargoAnterior = (String) criteria.get("codigoCargoAnterior");
		String codigoOperacion = (String) criteria.get("codigoOperacion");
											
		List listaUAs = new ArrayList();
		
		if(zonaList != null)
		{
			for (int i = 0; i < regionList.length; i++) {
				for (int j = 0; j < zonaList.length; j++) {
					criteria.put("codigoRegion", regionList[i].toString());
					criteria.put("codigoZona", zonaList[j].toString());
					//1 = Region Pertenece a Periodo, 0 = Region no pertenece a Region
					
					int validarRegionZona = 0;
					
					if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
						validarRegionZona = mantenimientoZONDirectorioDAO.getValidaRegionxZonaFOX(criteria);
					else
						validarRegionZona = mantenimientoZONDirectorioDAO.getValidaRegionxZona(criteria);
					
					if (validarRegionZona == 0) 
						continue;
										
					if(StringUtils.isEmpty(codigoSubGerencia)){
						if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
						{
							criteria.put("codigoSubGerencia", Constants.ZON_CODIGO_SUBGERENCIA_DEFAULT);
						}
						else
						{
							codigoSubgerenciaZona = mantenimientoZONDirectorioDAO.getSubgerenciaZona(criteria);
							criteria.put("codigoSubGerencia", codigoSubgerenciaZona.substring(0, 2));
						}
					}
					//Agregamos a la lista para que sea enviada por correo
					listaUAs.add(String.format("%s - %s", regionList[i].toString(), zonaList[j].toString()));
					
					mantenimientoZONDirectorioDAO.insertDirectorioVentaDetalle(criteria);
					
					String unidadAdministrativaAnterior = "";
					//Si es Gerente de Zona actualiza el id del gerente en la tabla ZON_ZONA
					if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){						
						criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
						unidadAdministrativaAnterior=(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegionAnterior")+(String)criteria.get("codigoZonaAnterior");
					}
					
					//Si es Gerente de Region actualiza el id del gerente en la tabla ZON_REGIO
					if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){					
						criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
						unidadAdministrativaAnterior=(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegionAnterior");
					}
					
					//Baja de Cliente de Directorio de Ventas
					Map criteriaHistorico = new HashMap();
					criteriaHistorico.put("codigoConsultora", (String)criteria.get("codigoConsultora"));
					criteriaHistorico.put("unidadAdministrativa", unidadAdministrativaAnterior);
					criteriaHistorico.put("codigoPais", MapUtils.getString(criteria, "codigoPais", ""));
					
					if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
					{
						List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByClienteFOX(criteriaHistorico);
						
						if(listaHistorico != null && listaHistorico.size()>0){
							Map ultimoHistorico = (Map)listaHistorico.get(0);
							String corHistoricoGerente = (String)ultimoHistorico.get("corHistoricoGerente"); 
							String camDesde = (String)ultimoHistorico.get("campDesde");
							String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
								Map map1 = new HashMap();
								map1.put("codigoPais", (String)criteria.get("codigoPais"));
								map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
								map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
								map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
								map1.put("fecha", fechaIngreso);
							String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);

							if(camDesde.equals(campIngreso)){
								criteria.put("indicadorResta1", null);
								criteria.put("corHistoricoGerente", corHistoricoGerente);
								mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteria);
								
								//Se elimina el registro cuando campaaDesde es igual a campaaIngreso(Obtenida con la fechaIngreso). 
								criteria.put("unidadAdministrativa", unidadAdministrativaAnterior);
								criteria.put("campDesde", (String)ultimoHistorico.get("campDesde"));
								mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByClienteFOX(criteria);
							}else{
								criteria.put("indicadorResta1", "1");
								criteria.put("corHistoricoGerente", corHistoricoGerente);
								mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteria);
							}
							
						}
						
						//Baja de Gerencia de Region y Zona
						if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
							Map criteriaZona = new HashMap();
							criteriaZona.put("codigoPais", (String)criteria.get("codigoPais"));
							criteriaZona.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
							criteriaZona.put("codigoZona", (String)criteria.get("codigoZonaAnterior"));
							criteriaZona.put("codigoConsultora", null);
							criteriaZona.put("usuarioLogin", (String)criteria.get("usuarioLogin"));									
							mantenimientoZONDirectorioDAO.updateGerenciaZonaFOX(criteriaZona);
							
						}else if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
							Map criteriaRegion = new HashMap();
							criteriaRegion.put("codigoPais", (String)criteria.get("codigoPais"));									
							criteriaRegion.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
							criteriaRegion.put("codigoConsultora", null);
							criteriaRegion.put("usuarioLogin", (String)criteria.get("usuarioLogin"));									
							mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(criteriaRegion);
						}
					}
					else
					{
						List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByCliente(criteriaHistorico);
						
						if(listaHistorico != null && listaHistorico.size()>0){
							Map ultimoHistorico = (Map)listaHistorico.get(0);
							String oidUltimoHistorico = (String)ultimoHistorico.get("oidHistoricoGerente");
							String fechaDesde = (String)ultimoHistorico.get("fechaDesde"); //Para obtener la campaaDesde
								Map map1 = new HashMap();
								map1.put("codigoPais", (String)criteria.get("codigoPais"));
								map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
								map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
								map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
								map1.put("fecha", fechaDesde);
							String campDesde = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
							
							String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
								Map map2 = new HashMap();
								map2.put("codigoPais", (String)criteria.get("codigoPais"));
								map2.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
								map2.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
								map2.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
								map2.put("fecha", fechaIngreso);
							String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map2);
							
							if(campDesde.equals(campIngreso)){ //Se comparan las campaas
								criteria.put("indicadorResta1", null);
								criteria.put("oidHistoricoGerencia", oidUltimoHistorico);
								mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteria);
								
								//Se borra del zon_histo_geren al reemplazante cuando las campaas son iguales.
								Map map3 = new HashMap();
								map3.put("codigoPais", (String)criteria.get("codigoPais"));
								map3.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
								map3.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
								map3.put("unidadAdministrativa", unidadAdministrativaAnterior);
								map3.put("fechaIngreso", (String)ultimoHistorico.get("fechaDesde"));
								mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByCliente(map3);
							}else{
								criteria.put("indicadorResta1", "1");
								criteria.put("oidHistoricoGerencia", oidUltimoHistorico);
								mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteria);
							}
							
						}
						
						//Baja de Gerencia de Region y Zona
						if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
							Map criteriaZona = new HashMap();
							criteriaZona.put("codigoZona", (String)criteria.get("codigoZonaAnterior"));
							criteriaZona.put("oidCliente", null);
							mantenimientoZONDirectorioDAO.updateGerenciaZona(criteriaZona);
						}else if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
							Map criteriaRegion = new HashMap();
							criteriaRegion.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
							criteriaRegion.put("oidCliente", null);
							mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteriaRegion);
						}
					}
					
					
					//Obtenemos la fechaRegiFin y fechaRegi de la Cabecera
					//Si no existe fechaRegiFin, y si la fechaRegi = fechaIngreso ,
					//se actualiza la fechaRegiFin anterior con la novedad(fechaIngreso -1). 
					Map cabec = new HashMap();
					cabec.put("correlativoCabecera", correlativoCabeceraAnterior);
					cabec.put("estadoRegistro", Constants.ESTADO_ACTIVO);
					cabec.put("indicadorEstado", Constants.ESTADO_DIRECTORIO_ACTIVO);
					Map cabecResul = mantenimientoZONDirectorioDAO.obtenerDirectorioVentaCabecera(cabec);
					Date fechaRegi = (Date)cabecResul.get("fechaRegistro");
					Date fechaRegiFin = (Date)cabecResul.get("fechaRegiFin");
					
					//Actualizar Directorio de Ventas
					Map criteriaDirectorio = new HashMap();
					criteriaDirectorio.put("codigoConsultora", (String)criteria.get("codigoConsultora"));
					criteriaDirectorio.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
					criteriaDirectorio.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_INACTIVA);
					criteriaDirectorio.put("fechaIngreso", (String)criteria.get("fechaRegistro"));
					criteriaDirectorio.put("codigoTipoCargo", (String)criteria.get("codigoTipoCargo"));
					criteriaDirectorio.put("codigoPais", (String)criteria.get("codigoPais"));
					criteriaDirectorio.put("correlativoCabecera", correlativoCabeceraAnterior);

					if(StringUtils.isBlank(DateUtil.convertDateToString("dd/MM/yyyy",fechaRegiFin))){
						if(!StringUtils.isBlank(DateUtil.convertDateToString("dd/MM/yyyy",fechaRegi)) &&
								!DateUtil.convertDateToString("dd/MM/yyyy",fechaRegi).equals((String)criteria.get("fechaIngreso"))){
							
							int diferenciaEnDias = 1;
							Date fi;
							try {
								fi = DateUtil.convertStringToDate("dd/MM/yyyy", (String)criteria.get("fechaIngreso"));
								long tiempoFI = fi.getTime();
								long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;
								Date fechaAyer = new Date(tiempoFI - unDia);
								System.out.println(fechaAyer.toString());
								String fecNoveMenos1 = DateUtil.convertDateToString("dd/MM/yyyy", fechaAyer);
								criteriaDirectorio.put("fechaIngresoHasta", fecNoveMenos1);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}else{
							criteriaDirectorio.put("fechaIngresoHasta", (String)criteria.get("fechaIngreso"));
						}

					}
				
					mantenimientoZONDirectorioDAO.updateDirectorioVentaCabecera(criteriaDirectorio);
					
					//Si es Gerente de Zona actualiza el id del gerente en la tabla ZON_ZONA
					if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
						if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
						{
							mantenimientoZONDirectorioDAO.updateGerenciaZonaFOX(criteria);
							
							criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));						
							criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
							criteria.put("codigoZona",(String)criteria.get("codigoZona"));
							criteria.put("estadoRegistro", Constants.NUMERO_UNO);
							
							mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegionFOX(criteria);
						}
						else
						{
							mantenimientoZONDirectorioDAO.updateGerenciaZona(criteria);
							
							criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
							criteria.put("codigoSubGerencia",(String)criteria.get("codigoSubGerencia"));
							criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
							criteria.put("codigoZona",(String)criteria.get("codigoZona"));
							
							mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(criteria);	
						}
					}
					
					//Si es Gerente de Region actualiza el id del gerente en la tabla ZON_REGIO
					if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
						if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
						{
							mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(criteria);
							
							criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
							criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
							criteria.put("estadoRegistro", Constants.NUMERO_UNO);
							
							mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegionFOX(criteria);
						}
						else
						{
							mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteria);
							
							criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
							criteria.put("codigoSubGerencia",(String)criteria.get("codigoSubGerencia"));
							criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
							
							mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(criteria);
						}
					}
				}
			}
			
			//Se usa en las plantillas
			criteria.put("tipoUA", Constants.ZON_MANT_GERENTE_ZONA);
		}
		else
		{
			for (int i = 0; i < regionList.length; i++) {
				criteria.put("codigoRegion", regionList[i].toString());
				criteria.put("codigoZona", null);
				if(StringUtils.isEmpty(codigoSubGerencia)){
					if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
					{
						criteria.put("codigoSubGerencia", Constants.ZON_CODIGO_SUBGERENCIA_DEFAULT);
					}
					else
					{
						codigoSubgerenciaZona = mantenimientoZONDirectorioDAO.getSubgerenciaRegion(criteria);
						criteria.put("codigoSubGerencia", codigoSubgerenciaZona.substring(0, 2));
					}
				}
				
				mantenimientoZONDirectorioDAO.insertDirectorioVentaDetalle(criteria);
				
				String unidadAdministrativaAnterior = "";
				//Si es Gerente de Zona actualiza el id del gerente en la tabla ZON_ZONA
				if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){						
					criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
					unidadAdministrativaAnterior=(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegionAnterior")+(String)criteria.get("codigoZonaAnterior");
				}
				
				//Si es Gerente de Region actualiza el id del gerente en la tabla ZON_REGIO
				if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){					
					criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
					unidadAdministrativaAnterior=(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegionAnterior");
				}
				
				//Baja de Cliente de Directorio de Ventas
				Map criteriaHistorico = new HashMap();
				criteriaHistorico.put("codigoConsultora", (String)criteria.get("codigoConsultora"));
				criteriaHistorico.put("unidadAdministrativa", unidadAdministrativaAnterior);
				criteriaHistorico.put("codigoPais", MapUtils.getString(criteria, "codigoPais", ""));
				
				if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
				{
					List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByClienteFOX(criteriaHistorico);
					if(listaHistorico != null && listaHistorico.size()>0){
						Map ultimoHistorico = (Map)listaHistorico.get(0);
						String corHistoricoGerente = (String)ultimoHistorico.get("corHistoricoGerente");
						String camDesde = (String)ultimoHistorico.get("campDesde");
						String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
							Map map1 = new HashMap();
							map1.put("codigoPais", (String)criteria.get("codigoPais"));
							map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
							map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
							map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
							map1.put("fecha", fechaIngreso);
						String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);

						if(camDesde.equals(campIngreso)){
							criteria.put("indicadorResta1", null);
							criteria.put("corHistoricoGerente", corHistoricoGerente);
							mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteria);
							
							//Se elimina el registro cuando campaaDesde es igual a campaaIngreso(Obtenida con la fechaIngreso). 
							criteria.put("unidadAdministrativa", unidadAdministrativaAnterior);
							criteria.put("campDesde", (String)ultimoHistorico.get("campDesde"));
							mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByClienteFOX(criteria);
							
						}else{
							criteria.put("indicadorResta1", "1");
							criteria.put("corHistoricoGerente", corHistoricoGerente);
							mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteria);
						}
						
					}
					
					//Baja de Gerencia de Region y Zona
					if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
						Map criteriaZona = new HashMap();
						criteriaZona.put("codigoPais", (String)criteria.get("codigoPais"));
						criteriaZona.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
						criteriaZona.put("codigoZona", (String)criteria.get("codigoZonaAnterior"));
						criteriaZona.put("codigoConsultora", null);
						criteriaZona.put("usuarioLogin", (String)criteria.get("usuarioLogin"));									
						mantenimientoZONDirectorioDAO.updateGerenciaZonaFOX(criteriaZona);
					}else if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
						Map criteriaRegion = new HashMap();
						criteriaRegion.put("codigoPais", (String)criteria.get("codigoPais"));									
						criteriaRegion.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
						criteriaRegion.put("codigoConsultora", null);
						criteriaRegion.put("usuarioLogin", (String)criteria.get("usuarioLogin"));									
						mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(criteriaRegion);
					}					
				}
				else
				{
					List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByCliente(criteriaHistorico);
					if(listaHistorico != null && listaHistorico.size()>0){
						Map ultimoHistorico = (Map)listaHistorico.get(0);
						String oidUltimoHistorico = (String)ultimoHistorico.get("oidHistoricoGerente"); 
						String fechaDesde = (String)ultimoHistorico.get("fechaDesde"); //Para obtener la campaaDesde
							Map map1 = new HashMap();
							map1.put("codigoPais", (String)criteria.get("codigoPais"));
							map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
							map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
							map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
							map1.put("fecha", fechaDesde);
						String campDesde = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
						
						String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
							Map map2 = new HashMap();
							map2.put("codigoPais", (String)criteria.get("codigoPais"));
							map2.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
							map2.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
							map2.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
							map2.put("fecha", fechaIngreso);
						String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map2);
						
						if(campDesde.equals(campIngreso)){ //Se comparan las campaas
							criteria.put("indicadorResta1", null);
							criteria.put("oidHistoricoGerencia", oidUltimoHistorico);
							mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteria);
							
							//Se borra del zon_histo_geren al reemplazante cuando las campaas son iguales.
							Map map3 = new HashMap();
							map3.put("codigoPais", (String)criteria.get("codigoPais"));
							map3.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
							map3.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
							map3.put("unidadAdministrativa", unidadAdministrativaAnterior);
							map3.put("fechaIngreso", (String)ultimoHistorico.get("fechaDesde"));
							mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByCliente(map3);
						}else{
							criteria.put("indicadorResta1", "1");
							criteria.put("oidHistoricoGerencia", oidUltimoHistorico);
							mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteria);
						}
						
					}
					
					//Baja de Gerencia de Region y Zona
					if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
						Map criteriaZona = new HashMap();
						criteriaZona.put("codigoZona", (String)criteria.get("codigoZonaAnterior"));
						criteriaZona.put("oidCliente", null);
						mantenimientoZONDirectorioDAO.updateGerenciaZona(criteriaZona);
					}else if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
						Map criteriaRegion = new HashMap();
						criteriaRegion.put("codigoRegion", (String)criteria.get("codigoRegionAnterior"));
						criteriaRegion.put("oidCliente", null);
						mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteriaRegion);
					}
				}
				
				//Obtenemos la fechaRegiFin y fechaRegi de la Cabecera
				//Si no existe fechaRegiFin, y si la fechaRegi = fechaIngreso ,
				//se actualiza la fechaRegiFin anterior con la novedad(fechaIngreso -1).
				Map cabec = new HashMap();
				cabec.put("correlativoCabecera", correlativoCabeceraAnterior);
				cabec.put("estadoRegistro", Constants.ESTADO_ACTIVO);
				cabec.put("indicadorEstado", Constants.ESTADO_DIRECTORIO_ACTIVO);
				Map cabecResul = mantenimientoZONDirectorioDAO.obtenerDirectorioVentaCabecera(cabec);
				Date fechaRegi = (Date)cabecResul.get("fechaRegistro");
				Date fechaRegiFin = (Date)cabecResul.get("fechaRegiFin");
				
				//Actualizar Directorio de Ventas
				Map criteriaDirectorio = new HashMap();
				criteriaDirectorio.put("codigoConsultora", (String)criteria.get("codigoConsultora"));
				criteriaDirectorio.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
				criteriaDirectorio.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_INACTIVA);
				criteriaDirectorio.put("fechaIngreso", (String)criteria.get("fechaRegistro"));
				criteriaDirectorio.put("codigoTipoCargo", (String)criteria.get("codigoTipoCargo"));
				criteriaDirectorio.put("codigoPais", (String)criteria.get("codigoPais"));
				criteriaDirectorio.put("correlativoCabecera", correlativoCabeceraAnterior);
				
				if(StringUtils.isBlank(DateUtil.convertDateToString("dd/MM/yyyy",fechaRegiFin))){
					if(!StringUtils.isBlank(DateUtil.convertDateToString("dd/MM/yyyy",fechaRegi)) &&
							!DateUtil.convertDateToString("dd/MM/yyyy",fechaRegi).equals((String)criteria.get("fechaIngreso"))){
						
						int diferenciaEnDias = 1;
						Date fi;
						try {
							fi = DateUtil.convertStringToDate("dd/MM/yyyy", (String)criteria.get("fechaIngreso"));
							long tiempoFI = fi.getTime();
							long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;
							Date fechaAyer = new Date(tiempoFI - unDia);
							System.out.println(fechaAyer.toString());
							String fecNoveMenos1 = DateUtil.convertDateToString("dd/MM/yyyy", fechaAyer);
							criteriaDirectorio.put("fechaIngresoHasta", fecNoveMenos1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						criteriaDirectorio.put("fechaIngresoHasta", (String)criteria.get("fechaIngreso"));
					}

				}
				mantenimientoZONDirectorioDAO.updateDirectorioVentaCabecera(criteriaDirectorio);
									
				//Si es Gerente de Zona actualiza el id del gerente en la tabla ZON_ZONA
				//Si es Gerente de Zona tambin actualiza el id del gerente en la tabla MAV_ENVIO_CONFI
				if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){					
					if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
					{
						mantenimientoZONDirectorioDAO.updateGerenciaZonaFOX(criteria);
						
						criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
						criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
						criteria.put("codigoZona",(String)criteria.get("codigoZona"));
						criteria.put("estadoRegistro", Constants.NUMERO_UNO);
						
						mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegionFOX(criteria);						
					}
					else
					{
						mantenimientoZONDirectorioDAO.updateGerenciaZona(criteria);
						
						criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
						criteria.put("codigoSubGerencia",(String)criteria.get("codigoSubGerencia"));
						criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
						criteria.put("codigoZona",(String)criteria.get("codigoZona"));
						mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(criteria);	
					}
				}
				
				//Si es Gerente de Region actualiza el id del gerente en la tabla ZON_REGIO
				//Si es Gerente de Region tambin actualiza el id del gerente en la tabla MAV_ENVIO_CONFI
				if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
					if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
					{
						mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(criteria);
						
						criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
						criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
						criteria.put("estadoRegistro", Constants.NUMERO_UNO);
						
						mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegionFOX(criteria);
					}
					else
					{
						mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteria);
						
						criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
						criteria.put("codigoSubGerencia",(String)criteria.get("codigoSubGerencia"));
						criteria.put("codigoRegion",(String)criteria.get("codigoRegion"));
						
						mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(criteria);
					}
				}

				listaUAs.add(String.format(regionList[i].toString()));			
			
			}//fin del bucle.
			
			//Se usa en las plantillas
			criteria.put("tipoUA", Constants.ZON_MANT_GERENTE_REGION);
		}				

		//Seteamos el valor de UAS
		//Se usa en las plantillas
		criteria.put("listaUAs", listaUAs);
		//
				
		if (StringUtils.equals(envioCorreo, Constants.SI)) {
			
//			enviarCorreo(criteria);
			//actualizar en la entidad Parmetros de Ruta el atributo 
			//Indicador de Activar Envo a Destinatarios con 0 (cero = Desactivar ) para el tipo de operacion
			mantenimientoZONDirectorioDAO.updateParametroRutaEnvioCorreo(criteria);
		}	
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#updateDirectorioVenta(java.util.Map)
	 */
	public void updateDirectorioVenta(Map criteria) {
		mantenimientoZONDirectorioDAO.updateDirectorioVentaCabecera(criteria);
		
		String[] regionList = (String[])criteria.get("codigoRegion");
		String[] zonaList = (String[]) criteria.get("codigoZona");
		String codigoTipoCargo = (String) criteria.get("codigoTipoCargo");
		
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		if(zonaList != null)
		{
			for (int i = 0; i < regionList.length; i++) {
				Map map = new HashMap();
				map.put("codigoRegion", regionList[i].toString());
				
				String codigoSubGerencia = mantenimientoZONDirectorioDAO.getSubgerenciaRegion(map);				
				criteria.put("codigoSubGerencia", StringUtils.left(codigoSubGerencia, 2));				
				
				for (int j = 0; j < zonaList.length; j++) {
					criteria.put("codigoRegion", regionList[i].toString());
					criteria.put("codigoZona", zonaList[j].toString());
					//1 = Region Pertenece a Periodo, 0 = Region no pertenece a Region
					if (mantenimientoZONDirectorioDAO.getValidaRegionxZona(criteria) == 0) 
						continue;

					if (!mantenimientoZONDirectorioDAO.getValidarRegistroExiste(criteria)){
						mantenimientoZONDirectorioDAO.insertDirectorioVentaDetalle(criteria);
					}else {
						mantenimientoZONDirectorioDAO.updateDirectorioVentaDetalle(criteria);
					}
					
					//Si es Gerente de Zona actualiza el id del gerente en la tabla ZON_ZONA
					if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
						mantenimientoZONDirectorioDAO.updateGerenciaZona(criteria);	
						criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
						mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(criteria);						
					}
					
					//Si es Gerente de Region actualiza el id del gerente en la tabla ZON_REGIO
					if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
						mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteria);
						criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
						mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(criteria);
					}
					
				}
			}		
		}
		else
		{
			for (int i = 0; i < regionList.length; i++) {
				
				Map map = new HashMap();
				map.put("codigoRegion", regionList[i].toString());
				
				String codigoSubGerencia = mantenimientoZONDirectorioDAO.getSubgerenciaRegion(map);				
				criteria.put("codigoSubGerencia", StringUtils.left(codigoSubGerencia, 2));				
				
				criteria.put("codigoRegion", regionList[i].toString());
				criteria.put("codigoZona", null);
				if (!mantenimientoZONDirectorioDAO.getValidarRegistroExiste(criteria)){
					mantenimientoZONDirectorioDAO.insertDirectorioVentaDetalle(criteria);
				}else {
					mantenimientoZONDirectorioDAO.updateDirectorioVentaDetalle(criteria);
				}
				//Si es Gerente de Region actualiza el id del gerente en la tabla ZON_REGIO
				if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
					mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteria);	
					criteria.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
					mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(criteria);
				}
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#insertDirectorioVentaCabecera(java.util.Map)
	 */
	public void insertDirectorioVentaCabecera(Map criteria) {
		mantenimientoZONDirectorioDAO.insertDirectorioVentaCabecera(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#updateDirectorioVentaCabecera(java.util.Map)
	 */
	public void updateDirectorioVentaCabecera(Map criteria) {
		mantenimientoZONDirectorioDAO.updateDirectorioVentaCabecera(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getResponsableUA(java.util.Map)
	 */
	public List getResponsableUA(Map criteria) {
		return mantenimientoZONDirectorioDAO.getResponsableUA(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#insertDirectorioVenta(java.util.Map, java.util.List)
	 */
	public void insertDirectorioVenta(Map cabecera, List detalle) {
		String envioCorreo = (String)cabecera.get("envioCorreo");
		
		mantenimientoZONDirectorioDAO.insertDirectorioVentaCabecera(cabecera);
		for(int i = 0 ;i<detalle.size();i++){
			Map mapDet = (Map)detalle.get(i);
			
			cabecera.put("codigoRegion", MapUtils.getString(mapDet, "codigoRegion", ""));
			cabecera.put("codigoZona", StringUtils.isBlank(MapUtils.getString(mapDet, "codigoZona", ""))?null:MapUtils.getString(mapDet, "codigoZona", ""));
			cabecera.put("codigoSubGerencia", MapUtils.getString(mapDet, "codigoSubgerencia", ""));
			
			mantenimientoZONDirectorioDAO.insertDirectorioVentaDetalle(cabecera);
		}
		
		if (StringUtils.equals(envioCorreo, Constants.SI)) {
			cabecera.put("listaRetiro", detalle);
//			enviarCorreo(cabecera);
			//actualizar en la entidad Parmetros de Ruta el atributo 
			//Indicador de Activar Envo a Destinatarios con 0 (cero = Desactivar ) para el tipo de operacion
			mantenimientoZONDirectorioDAO.updateParametroRutaEnvioCorreo(cabecera);
		}		
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#insertDirectorioVentaRetiro(java.util.Map, java.util.List)
	 */
	public void insertDirectorioVentaRetiro(Map cabecera, List detalle) {
				
		String codigoConexionExterna = MapUtils.getString(cabecera, "codigoConexionExterna", "");
		
		String envioCorreo = (String)cabecera.get("envioCorreo");
		String codigoTipoCargo = (String)cabecera.get("codigoTipoCargo");
		String codigoConsultora = (String)cabecera.get("codigoConsultora");
		String correlativoCabeceraAnterior = MapUtils.getString(cabecera, "correlativoCabecera", "");
				
		
		Map historicoDirectorio = new HashMap();
		historicoDirectorio.put("codigoConsultora", codigoConsultora);
		historicoDirectorio.put("codigoTipoCargo", codigoTipoCargo);
		historicoDirectorio.put("codigoPais", MapUtils.getString(cabecera, "codigoPais", ""));
				
		List listaHistoricoDV = mantenimientoZONDirectorioDAO.getDirectorioVentaCabeceraActivo(historicoDirectorio);
		
		String correlativoCabecera = mantenimientoZONDirectorioDAO.getCorrelativoCabeceraDirectorio();
		cabecera.put("correlativoCabecera", correlativoCabecera);
		
		//SE GRABA EL RETIRO EN EL DIRECTORIO
		mantenimientoZONDirectorioDAO.insertDirectorioVentaCabecera(cabecera);
								
		for(int i = 0 ;i<detalle.size();i++){
			Map mapDet = (Map)detalle.get(i);
			
			cabecera.put("codigoRegion", MapUtils.getString(mapDet, "codigoRegion", ""));
			cabecera.put("codigoZona", StringUtils.isBlank(MapUtils.getString(mapDet, "codigoZona", ""))?null:MapUtils.getString(mapDet, "codigoZona", ""));
			cabecera.put("codigoSubGerencia", MapUtils.getString(mapDet, "codigoSubgerencia", ""));
			
			mantenimientoZONDirectorioDAO.insertDirectorioVentaDetalle(cabecera);
			
			String unidadAdministrativa = "";
			//Si es Gerente de Zona actualiza el id del gerente en la tabla ZON_ZONA
			if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){	
				unidadAdministrativa = MapUtils.getString(mapDet, "codigoSubgerencia", "")+MapUtils.getString(mapDet, "codigoRegion", "")+MapUtils.getString(mapDet, "codigoZona", "");				
			}
			
			//Si es Gerente de Region actualiza el id del gerente en la tabla ZON_REGIO
			if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){					
				unidadAdministrativa = MapUtils.getString(mapDet, "codigoSubgerencia", "")+MapUtils.getString(mapDet, "codigoRegion", "");
			}
			
			//Actualizar baja de cliente en Historico de Gerentes
			Map criteriaHistorico = new HashMap();
			criteriaHistorico.put("codigoConsultora", codigoConsultora);
			criteriaHistorico.put("unidadAdministrativa", unidadAdministrativa);
			criteriaHistorico.put("codigoPais", MapUtils.getString(cabecera, "codigoPais", ""));
			
			if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			{
				List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByClienteFOX(criteriaHistorico);			
				if(listaHistorico != null && listaHistorico.size()>0){
					Map ultimoHistorico = (Map)listaHistorico.get(0);
					String corHistoricoGerente = (String)ultimoHistorico.get("corHistoricoGerente"); 
					String camDesde = (String)ultimoHistorico.get("campDesde");
					String fechaIngreso = (String)cabecera.get("fechaIngreso"); //Para obtener la campaaIngreso
						Map map1 = new HashMap();
						map1.put("codigoPais", (String)cabecera.get("codigoPais"));
						map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
						map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
						map1.put("codigoConexionExterna", (String)cabecera.get("codigoConexionExterna"));
						map1.put("fecha", fechaIngreso);
					String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);

					if(camDesde.equals(campIngreso)){
						cabecera.put("indicadorResta1", null);
						cabecera.put("corHistoricoGerente", corHistoricoGerente);
						mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(cabecera);
						
						//Se elimina el registro cuando campaaDesde es igual a campaaIngreso(Obtenida con la fechaIngreso). 
						cabecera.put("unidadAdministrativa", unidadAdministrativa);
						cabecera.put("campDesde", (String)ultimoHistorico.get("campDesde"));
						mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByClienteFOX(cabecera);
					}else{
						cabecera.put("indicadorResta1", "1");
						cabecera.put("corHistoricoGerente", corHistoricoGerente);
						mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(cabecera);
					}
					
				}
				
				//Baja de Gerencia de Region y Zona
				if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
					Map criteriaZona = new HashMap();
					criteriaZona.put("codigoZona", MapUtils.getString(mapDet, "codigoZona", ""));
					criteriaZona.put("codigoRegion", MapUtils.getString(mapDet, "codigoRegion", ""));
					criteriaZona.put("codigoPais", MapUtils.getString(cabecera, "codigoPais", ""));
					criteriaZona.put("codigoConsultora", null);
					criteriaZona.put("usuarioLogin", MapUtils.getString(cabecera, "usuarioLogin", ""));					
					mantenimientoZONDirectorioDAO.updateGerenciaZonaFOX(criteriaZona);
				}else if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
					Map criteriaRegion = new HashMap();
					criteriaRegion.put("codigoPais", MapUtils.getString(cabecera, "codigoPais", ""));
					criteriaRegion.put("codigoRegion", MapUtils.getString(mapDet, "codigoRegion", ""));
					criteriaRegion.put("codigoConsultora", null);
					criteriaRegion.put("usuarioLogin", MapUtils.getString(cabecera, "usuarioLogin", ""));
					mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(criteriaRegion);
				}
			}
			else
			{
				List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByCliente(criteriaHistorico);			
				if(listaHistorico != null && listaHistorico.size()>0){
					Map ultimoHistorico = (Map)listaHistorico.get(0);
					String oidUltimoHistorico = (String)ultimoHistorico.get("oidHistoricoGerente"); 
					String fechaDesde = (String)ultimoHistorico.get("fechaDesde"); //Para obtener la campaaDesde
						Map map1 = new HashMap();
						map1.put("codigoPais", (String)cabecera.get("codigoPais"));
						map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
						map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
						map1.put("codigoConexionExterna", (String)cabecera.get("codigoConexionExterna"));
						map1.put("fecha", fechaDesde);
					String campDesde = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
					
					String fechaIngreso = (String)cabecera.get("fechaIngreso"); //Para obtener la campaaIngreso
						Map map2 = new HashMap();
						map2.put("codigoPais", (String)cabecera.get("codigoPais"));
						map2.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
						map2.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
						map2.put("codigoConexionExterna", (String)cabecera.get("codigoConexionExterna"));
						map2.put("fecha", fechaIngreso);
					String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map2);
					
					if(campDesde.equals(campIngreso)){ //Se comparan las campaas
						cabecera.put("indicadorResta1", null);
						cabecera.put("oidHistoricoGerencia", oidUltimoHistorico);
						mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(cabecera);
						
						//Se borra del zon_histo_geren al reemplazante cuando las campaas son iguales.
						Map map3 = new HashMap();
						map3.put("codigoPais", (String)cabecera.get("codigoPais"));
						map3.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
						map3.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
						map3.put("unidadAdministrativa", unidadAdministrativa);
						map3.put("fechaIngreso", (String)ultimoHistorico.get("fechaDesde"));
						mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByCliente(map3);
					}else{
						cabecera.put("indicadorResta1", "1");
						cabecera.put("oidHistoricoGerencia", oidUltimoHistorico);
						mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(cabecera);
					}
					
				}
				
				//Baja de Gerencia de Region y Zona
				if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
					Map criteriaZona = new HashMap();
					criteriaZona.put("codigoZona", MapUtils.getString(mapDet, "codigoZona", ""));
					criteriaZona.put("oidCliente", null);
					mantenimientoZONDirectorioDAO.updateGerenciaZona(criteriaZona);
				}else if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
					Map criteriaRegion = new HashMap();
					criteriaRegion.put("codigoRegion", MapUtils.getString(mapDet, "codigoRegion", ""));
					criteriaRegion.put("oidCliente", null);
					mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteriaRegion);
				}
			}
			
			//Obtenemos la fechaRegiFin y fechaRegi de la Cabecera
			//Si no existe fechaRegiFin, y si la fechaRegi = fechaIngreso ,
			//se actualiza la fechaRegiFin anterior con la novedad(fechaIngreso -1).
			Map cabec = new HashMap();
			cabec.put("correlativoCabecera", correlativoCabeceraAnterior);
			cabec.put("estadoRegistro", Constants.ESTADO_ACTIVO);
			cabec.put("indicadorEstado", Constants.ESTADO_DIRECTORIO_ACTIVO);
			Map cabecResul = mantenimientoZONDirectorioDAO.obtenerDirectorioVentaCabecera(cabec);
			Date fechaRegi = (Date)cabecResul.get("fechaRegistro");
			Date fechaRegiFin = (Date)cabecResul.get("fechaRegiFin");
			
			//Actualizar Directorio de Ventas
			if(listaHistoricoDV != null && listaHistoricoDV.size()>0){
				
				Map mapHistoricoDV = (Map)listaHistoricoDV.get(0);

				Map criteriaDirectorio = new HashMap();
				criteriaDirectorio.put("codigoConsultora", codigoConsultora);
				criteriaDirectorio.put("usuarioLogin", (String)cabecera.get("usuarioLogin"));
				criteriaDirectorio.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_INACTIVA);
				criteriaDirectorio.put("fechaIngreso", DateUtil.convertDateToString("dd/MM/yyyy", (Date)mapHistoricoDV.get("fechaRegistro")));				
				criteriaDirectorio.put("codigoTipoCargo", codigoTipoCargo);
				criteriaDirectorio.put("codigoPais", MapUtils.getString(cabecera, "codigoPais", ""));
				criteriaDirectorio.put("correlativoCabecera", correlativoCabeceraAnterior);
				
				if(StringUtils.isBlank(DateUtil.convertDateToString("dd/MM/yyyy",fechaRegiFin))){
					if(!StringUtils.isBlank(DateUtil.convertDateToString("dd/MM/yyyy",fechaRegi)) &&
							!DateUtil.convertDateToString("dd/MM/yyyy",fechaRegi).equals((String)cabecera.get("fechaIngreso"))){
						
						int diferenciaEnDias = 1;
						Date fi;
						try {
							fi = DateUtil.convertStringToDate("dd/MM/yyyy", (String)cabecera.get("fechaIngreso"));
							long tiempoFI = fi.getTime();
							long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;
							Date fechaAyer = new Date(tiempoFI - unDia);
							System.out.println(fechaAyer.toString());
							String fecNoveMenos1 = DateUtil.convertDateToString("dd/MM/yyyy", fechaAyer);
							criteriaDirectorio.put("fechaIngresoHasta", fecNoveMenos1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						criteriaDirectorio.put("fechaIngresoHasta", (String)cabecera.get("fechaIngreso"));
					}

				}
				
				mantenimientoZONDirectorioDAO.updateDirectorioVentaCabecera(criteriaDirectorio);
			}
						
		}
		
		Map directorioDetalle = (HashMap)cabecera.get("directorioDetalle");
		Map criteriaActualiza = new HashMap();
		criteriaActualiza.put("codigoCliente", MapUtils.getString(directorioDetalle,"codigoCliente"));
		criteriaActualiza.put("codigoPais", MapUtils.getString(directorioDetalle,"codigoPais"));
		criteriaActualiza.put("codigoCargo", MapUtils.getString(directorioDetalle,"codigoCargo"));
		criteriaActualiza.put("correlativoCabecera", MapUtils.getString(directorioDetalle,"correlativoCabecera"));
		criteriaActualiza.put("codigoOperacion", MapUtils.getString(directorioDetalle,"codigoOperacion"));
		criteriaActualiza.put("campanaProceso", MapUtils.getString(directorioDetalle,"campanaProceso"));
		criteriaActualiza.put("fechaRegistro", MapUtils.getString(directorioDetalle,"fechaRegistro"));
		criteriaActualiza.put("estadoRegistro", Constants.ESTADO_INACTIVO);
		//SE ACTUALIZA REGISTRO CON ESTATUS INACTIVO
		mantenimientoZONDirectorioDAO.updateEstadoDirectotioVenta(criteriaActualiza);
		
		criteriaActualiza.put("estadoInactivo", Constants.ESTADO_ENTIDAD_INACTIVO);
		criteriaActualiza.put("usuarioLogin", MapUtils.getString(cabecera, "usuarioLogin", ""));
		//SE ACTUALIZA REGISTRO CON ESTATUS INACTIVO
		mantenimientoZONDirectorioDAO.updateEstadoCargoDirectotioVenta(criteriaActualiza);
		
		if (StringUtils.equals(envioCorreo, Constants.SI)) {
			cabecera.put("listaRetiro", detalle);
			enviarCorreo(cabecera);
			//actualizar en la entidad Parmetros de Ruta el atributo 
			//Indicador de Activar Envo a Destinatarios con 0 (cero = Desactivar ) para el tipo de operacion
			mantenimientoZONDirectorioDAO.updateParametroRutaEnvioCorreo(cabecera);
		}
		
	}
		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#insertDirectorioVentaLicencia(java.util.Map)
	 */
	public void insertDirectorioVentaLicencia(Map criteria) {
		
		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		String fechaIngresoHasta = MapUtils.getString(criteria, "fechaRegreso");
		
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		List listaUAs = new ArrayList();
		
		//Personal de Licencia
		String codigoTipoCargo = (String)criteria.get("codigoTipoCargo");
		String codigoConsultora = (String)criteria.get("codigoConsultora");
		String correlativoCabeceraAnterior = MapUtils.getString(criteria, "correlativoCabecera", "");
		String correlativoCabecera = "";
			
		String unidadAdministrativa = "";
		//Si es Gerente de Zona actualiza el id del gerente en la tabla ZON_ZONA
		if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){	
			unidadAdministrativa = MapUtils.getString(criteria, "codigoSubGerencia", "")+MapUtils.getString(criteria, "codigoRegion", "")+MapUtils.getString(criteria, "codigoZona", "");
		    //Se usa en las plantillas
			criteria.put("tipoUA", Constants.ZON_MANT_GERENTE_ZONA);
		}
		
		//Si es Gerente de Region actualiza el id del gerente en la tabla ZON_REGIO
		if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
			unidadAdministrativa = MapUtils.getString(criteria, "codigoSubGerencia", "")+MapUtils.getString(criteria, "codigoRegion", "");
			//Se usa en las plantillas
			criteria.put("tipoUA", Constants.ZON_MANT_GERENTE_REGION);
		}
		
		//Actualizar baja de cliente en Historico de Gerentes
		Map criteriaHistorico = new HashMap();
				
		criteriaHistorico.put("codigoConsultora", codigoConsultora);
		criteriaHistorico.put("unidadAdministrativa", unidadAdministrativa);
		criteriaHistorico.put("codigoPais", MapUtils.getString(criteria, "codigoPais", ""));
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
		{
			List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByClienteFOX(criteriaHistorico);
			if(listaHistorico != null && listaHistorico.size()>0){
				Map ultimoHistorico = (Map)listaHistorico.get(0);
				String corHistoricoGerente = (String)ultimoHistorico.get("corHistoricoGerente");
				String camDesde = (String)ultimoHistorico.get("campDesde");
				String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
					Map map1 = new HashMap();
					map1.put("codigoPais", (String)criteria.get("codigoPais"));
					map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
					map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
					map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
					map1.put("fecha", fechaIngreso);
				String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
				
				Map criteriaHistoricoUpdate = new HashMap();
				if(camDesde.equals(campIngreso)){
					criteriaHistoricoUpdate.put("indicadorResta1", null);
					criteriaHistoricoUpdate.put("corHistoricoGerente", corHistoricoGerente);
					criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaSalida"));
					criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
					criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
					mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteriaHistoricoUpdate);
					
					//Se elimina el registro cuando campaaDesde es igual a campaaIngreso(Obtenida con la fechaIngreso). 
					criteriaHistoricoUpdate.put("unidadAdministrativa", unidadAdministrativa);
					criteriaHistoricoUpdate.put("campDesde", (String)ultimoHistorico.get("campDesde"));
					mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByClienteFOX(criteriaHistoricoUpdate);
					
				}else{
					criteriaHistoricoUpdate.put("indicadorResta1", "1");
					criteriaHistoricoUpdate.put("corHistoricoGerente", corHistoricoGerente);
					criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaSalida"));
					criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
					criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
					mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteriaHistoricoUpdate);
				}
				
				
			}
			
			//Baja de Gerencia de Region y Zona
			if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
				Map criteriaZona = new HashMap();
				criteriaZona.put("codigoRegion", MapUtils.getString(criteria, "codigoRegion", ""));
				criteriaZona.put("codigoZona", MapUtils.getString(criteria, "codigoZona", ""));
				criteriaZona.put("codigoPais", MapUtils.getString(criteria, "codigoPais", ""));
				criteriaZona.put("codigoConsultora", null);		
				criteriaZona.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
				mantenimientoZONDirectorioDAO.updateGerenciaZonaFOX(criteriaZona);
				listaUAs.add(String.format("%s - %s", MapUtils.getString(criteria, "codigoRegion", ""), MapUtils.getString(criteria, "codigoZona", "")));
			}else if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
				Map criteriaRegion = new HashMap();
				criteriaRegion.put("codigoRegion", MapUtils.getString(criteria, "codigoRegion", ""));
				criteriaRegion.put("codigoPais", MapUtils.getString(criteria, "codigoPais", ""));
				criteriaRegion.put("codigoConsultora", null);
				criteriaRegion.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
				mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(criteriaRegion);
				listaUAs.add(MapUtils.getString(criteria, "codigoRegion", ""));
			}
		}
		else
		{
			List listaHistorico = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByCliente(criteriaHistorico);
			if(listaHistorico != null && listaHistorico.size()>0){
				Map ultimoHistorico = (Map)listaHistorico.get(0);
				String oidUltimoHistorico = (String)ultimoHistorico.get("oidHistoricoGerente"); 
				String fechaDesde = (String)ultimoHistorico.get("fechaDesde"); //Para obtener la campaaDesde
					Map map1 = new HashMap();
					map1.put("codigoPais", (String)criteria.get("codigoPais"));
					map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
					map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
					map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
					map1.put("fecha", fechaDesde);
				String campDesde = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
				
				String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
					Map map2 = new HashMap();
					map2.put("codigoPais", (String)criteria.get("codigoPais"));
					map2.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
					map2.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
					map2.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
					map2.put("fecha", fechaIngreso);
				String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map2);
				
				
				Map criteriaHistoricoUpdate = new HashMap();
				if(campDesde.equals(campIngreso)){ //Se comparan las campaas
					criteriaHistoricoUpdate.put("indicadorResta1", null);
					criteriaHistoricoUpdate.put("oidHistoricoGerencia", oidUltimoHistorico);
					criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaSalida"));
					criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
					criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
					mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteriaHistoricoUpdate);
					
					//Se borra del zon_histo_geren al reemplazante cuando las campaas son iguales.
					criteriaHistoricoUpdate.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
					criteriaHistoricoUpdate.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
					criteriaHistoricoUpdate.put("unidadAdministrativa", unidadAdministrativa);
					criteriaHistoricoUpdate.put("fechaIngreso", (String)ultimoHistorico.get("fechaDesde"));
					mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByCliente(criteriaHistoricoUpdate);
				}else{
					criteriaHistoricoUpdate.put("indicadorResta1", "1");
					criteriaHistoricoUpdate.put("oidHistoricoGerencia", oidUltimoHistorico);
					criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaSalida"));
					criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
					criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
					mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteriaHistoricoUpdate);
				}
				
				
			}
			
			//Baja de Gerencia de Region y Zona
			if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
				Map criteriaZona = new HashMap();
				criteriaZona.put("codigoZona", MapUtils.getString(criteria, "codigoZona", ""));
				criteriaZona.put("oidCliente", null);
				mantenimientoZONDirectorioDAO.updateGerenciaZona(criteriaZona);
				listaUAs.add(String.format("%s - %s", MapUtils.getString(criteria, "codigoRegion", ""), MapUtils.getString(criteria, "codigoZona", "")));
			}else if(codigoTipoCargo.equals(Constants.ZON_MANT_GERENTE_REGION)){
				Map criteriaRegion = new HashMap();
				criteriaRegion.put("codigoRegion", MapUtils.getString(criteria, "codigoRegion", ""));
				criteriaRegion.put("oidCliente", null);
				mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteriaRegion);
				listaUAs.add(MapUtils.getString(criteria, "codigoRegion", ""));
			}
		}
		
		Map historicoDirectorio = new HashMap();
		historicoDirectorio.put("codigoConsultora", codigoConsultora);
		historicoDirectorio.put("codigoTipoCargo", codigoTipoCargo);
		historicoDirectorio.put("correlativoCabecera", correlativoCabeceraAnterior);
		historicoDirectorio.put("codigoPais", MapUtils.getString(criteria, "codigoPais", ""));
		List listaHistoricoDV = mantenimientoZONDirectorioDAO.getDirectorioVentaCabeceraActivo(historicoDirectorio);
		
		
		//Actualizar Directorio de Ventas - BAJA AL QUE SE VA DE LICENCIA
		if(listaHistoricoDV != null && listaHistoricoDV.size()>0){
			
			Map mapHistoricoDV = (Map)listaHistoricoDV.get(0);

			Map criteriaDirectorio = new HashMap();
			criteriaDirectorio.put("codigoConsultora", codigoConsultora);
			criteriaDirectorio.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
			criteriaDirectorio.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_INACTIVA);
			criteriaDirectorio.put("fechaIngreso", DateUtil.convertDateToString("dd/MM/yyyy", (Date)mapHistoricoDV.get("fechaRegistro")));				
			criteriaDirectorio.put("codigoTipoCargo", codigoTipoCargo);	
			criteriaDirectorio.put("correlativoCabecera", correlativoCabeceraAnterior);
			criteriaDirectorio.put("codigoPais", MapUtils.getString(criteria, "codigoPais", ""));
			
			//Obtenemos la fechaRegiFin y fechaRegi de la Cabecera
			//Si no existe fechaRegiFin, y si la fechaRegi = fechaIngreso ,
			//se actualiza la fechaRegiFin anterior con la novedad(fechaIngreso -1).
			String fechaRegi = DateUtil.convertDateToString("dd/MM/yyyy", (Date)mapHistoricoDV.get("fechaRegistro"));
			String fechaRegiFin = DateUtil.convertDateToString("dd/MM/yyyy", (Date)mapHistoricoDV.get("fechaRegistroFin"));
			
			if(StringUtils.isBlank(fechaRegiFin)){
				if(!StringUtils.isBlank(fechaRegi) && !fechaRegi.equals((String)criteria.get("fechaIngreso"))){
					
					int diferenciaEnDias = 1;
					Date fi;
					try {
						fi = DateUtil.convertStringToDate("dd/MM/yyyy", (String)criteria.get("fechaIngreso"));
						long tiempoFI = fi.getTime();
						long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;
						Date fechaAyer = new Date(tiempoFI - unDia);
						System.out.println(fechaAyer.toString());
						String fecNoveMenos1 = DateUtil.convertDateToString("dd/MM/yyyy", fechaAyer);
						criteriaDirectorio.put("fechaIngresoHasta", fecNoveMenos1); //Para inactivar al que se va de Licencia			
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{
					criteriaDirectorio.put("fechaIngresoHasta", (String)criteria.get("fechaIngreso")); //Para inactivar al que se va de Licencia
				}

			}
											
			mantenimientoZONDirectorioDAO.updateDirectorioVentaCabecera(criteriaDirectorio);
			
			//Registrar en Directorio de Ventas - REGISTRO DE LA LICENCIA
			correlativoCabecera = mantenimientoZONDirectorioDAO.getCorrelativoCabeceraDirectorio();
			criteria.put("tipoOperacion", Constants.ZON_MANT_CODIGO_OPERACION_LI);
			criteria.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_INACTIVA_TEMPORAL);
			criteria.put("correlativoCabecera", correlativoCabecera);
			criteria.put("fechaIngresoHasta", (String)criteria.get("fechaIngreso")); //Para registrar la licencia ya no se resta 1
			mantenimientoZONDirectorioDAO.insertDirectorioVentaCabecera(criteria);
			mantenimientoZONDirectorioDAO.insertDirectorioVentaDetalle(criteria);
			
		}
		
		//Personal de Reemplazo
		String codigoTipoCargoReemplazo = MapUtils.getString(criteria, "codigoTipoCargoReemplazo", "");		
		String codigoClienteReemplazante = MapUtils.getString(criteria, "codigoConsultoraReemplazante", "");
		Map mapClienteReemplazante = new HashMap();
		mapClienteReemplazante.put("codigoConsultora", codigoClienteReemplazante);		
		mapClienteReemplazante.put("codigoPais", (String)criteria.get("codigoPais"));
		mapClienteReemplazante.put("codigoConexionExterna", codigoConexionExterna);
		List datosUnidadesAdministrativasList = mantenimientoZONDirectorioDAO.getDatosUnidaddesAdministrativasConsultora(mapClienteReemplazante);
		
		StringBuilder codigoRegionReemplaan = new StringBuilder();
		StringBuilder codigoZonaReemplaan = new StringBuilder();
		StringBuilder descripcionCargoReemplaan = new StringBuilder();
        String tipoUACargoReemplaan = Constants.ZON_TIPO_UA_REGION;
		
		if(datosUnidadesAdministrativasList!=null && datosUnidadesAdministrativasList.size()>0){
			
			String[] codigosCorre = (String[]) MapUtils.getObject(criteria, "codigoCorrrelativoCabeceraReemplazo");

			  for (int j = 0; j < codigosCorre.length; j++) { //for para recorrer los cargos reemplazantes
				  
			    for(int i = 0; i<datosUnidadesAdministrativasList.size(); i++){ //for para recorrer las UA del codigoClienteReemplazante
				    Map unidadZona = (Map)datosUnidadesAdministrativasList.get(i);
				   
				    Integer oidCorrelativoCabecera = MapUtils.getInteger(unidadZona, "correlativoCabecera");
					
				    if (Integer.parseInt(codigosCorre[j]) == oidCorrelativoCabecera) {
				    	/*Inicio Calculo de la variable TipoUA del Cargo Reemplazante(R o Z), para el envio de correo*/
						String cargoReemp = (String)unidadZona.get("codigoCargo");
						Map criteriaTipoUaCargoReemplaan = new HashMap();
						criteriaTipoUaCargoReemplaan.put("codigoCargo", cargoReemp);
						String tuacr = mantenimientoZONDirectorioDAO.getVerificarCargoTitular(criteriaTipoUaCargoReemplaan);
						if (StringUtils.equals(StringUtils.split(tuacr, "|")[2], Constants.ZON_TIPO_UA_ZONA)) {
							tipoUACargoReemplaan = StringUtils.split(tuacr, "|")[2];
						}
						
						codigoRegionReemplaan.append(MapUtils.getString(unidadZona, "codigoRegion", "")).append("<br/>");
						codigoZonaReemplaan.append(MapUtils.getString(unidadZona, "codigoZona", "")).append("<br/>");
						descripcionCargoReemplaan.append(MapUtils.getString(unidadZona, "descripcionCargo", "")).append("<br/>");
						/*Fin calculo variable para el envio de correo*/
						
						//Si es Gerente de Zona cierra su registro respectivo en ZON_HISTO_GEREN, y actualiza el id del gerente en la tabla ZON_ZONA										
						if(cargoReemp.equals(Constants.ZON_MANT_GERENTE_ZONA)){
							
							criteria.put("codigoRegionReem", MapUtils.getString(unidadZona, "codigoRegion", ""));
							criteria.put("codigoZonaReem", MapUtils.getString(unidadZona, "codigoZona", ""));
													
							unidadAdministrativa = MapUtils.getString(criteria, "codigoSubGerencia", "")+MapUtils.getString(unidadZona, "codigoRegion", "")+MapUtils.getString(unidadZona, "codigoZona", "");
							
							//Actualizar baja de cliente en Historico de Gerentes
							Map criteriaHistoricoZona = new HashMap();
							criteriaHistorico.put("codigoConsultora", codigoClienteReemplazante);
							criteriaHistorico.put("unidadAdministrativa", unidadAdministrativa);
							criteriaHistorico.put("codigoPais", MapUtils.getString(criteria, "codigoPais", ""));
							
							if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX)){
								List listaHistoricoZona = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByClienteFOX(criteriaHistorico);						
								if(listaHistoricoZona != null && listaHistoricoZona.size()>0){
									Map ultimoHistorico = (Map)listaHistoricoZona.get(0);
									String corHistoricoGerente = (String)ultimoHistorico.get("corHistoricoGerente"); 
									String camDesde = (String)ultimoHistorico.get("campDesde");
									String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
										Map map1 = new HashMap();
										map1.put("codigoPais", (String)criteria.get("codigoPais"));
										map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
										map1.put("fecha", fechaIngreso);
									String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
									
									Map criteriaHistoricoUpdate = new HashMap();
									if(camDesde.equals(campIngreso)){
										criteriaHistoricoUpdate.put("indicadorResta1", null);
										criteriaHistoricoUpdate.put("corHistoricoGerente", corHistoricoGerente);
										criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaIngreso"));
										criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
										criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteriaHistoricoUpdate);
										
										//Se elimina el registro cuando campaaDesde es igual a campaaIngreso(Obtenida con la fechaIngreso). 
										criteriaHistoricoUpdate.put("unidadAdministrativa", unidadAdministrativa);
										criteriaHistoricoUpdate.put("campDesde", (String)ultimoHistorico.get("campDesde"));
										mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByClienteFOX(criteriaHistoricoUpdate);
									}else{
										criteriaHistoricoUpdate.put("indicadorResta1", "1");
										criteriaHistoricoUpdate.put("corHistoricoGerente", corHistoricoGerente);
										criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaIngreso"));
										criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
										criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteriaHistoricoUpdate);
									}
									
									
								}
								
								Map criteriaZona = new HashMap();
								criteriaZona.put("codigoPais", MapUtils.getString(criteria, "codigoPais", ""));
								criteriaZona.put("codigoRegion", MapUtils.getString(unidadZona, "codigoRegion", ""));
								criteriaZona.put("codigoZona", MapUtils.getString(unidadZona, "codigoZona", ""));
								criteriaZona.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
								criteriaZona.put("codigoConsultora", null);
								mantenimientoZONDirectorioDAO.updateGerenciaZonaFOX(criteriaZona);
								
							}else{
								List listaHistoricoZona = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByCliente(criteriaHistorico);						
								if(listaHistoricoZona != null && listaHistoricoZona.size()>0){
									Map ultimoHistorico = (Map)listaHistoricoZona.get(0);
									String oidUltimoHistorico = (String)ultimoHistorico.get("oidHistoricoGerente");
									String fechaDesde = (String)ultimoHistorico.get("fechaDesde"); //Para obtener la campaaDesde
										Map map1 = new HashMap();
										map1.put("codigoPais", (String)criteria.get("codigoPais"));
										map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
										map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
										map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
										map1.put("fecha", fechaDesde);
									String campDesde = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
									
									String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
										Map map2 = new HashMap();
										map2.put("codigoPais", (String)criteria.get("codigoPais"));
										map2.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
										map2.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
										map2.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
										map2.put("fecha", fechaIngreso);
									String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map2);
									
									Map criteriaHistoricoUpdate = new HashMap();
									if(campDesde.equals(campIngreso)){ //Se comparan las campaas
										criteriaHistoricoUpdate.put("indicadorResta1", null);
										criteriaHistoricoUpdate.put("oidHistoricoGerencia", oidUltimoHistorico);
										criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaIngreso"));
										criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
										criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
										
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteriaHistoricoUpdate);
										
										//Se borra del zon_histo_geren al reemplazante cuando las campaas son iguales.
										criteriaHistoricoUpdate.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
										criteriaHistoricoUpdate.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
										criteriaHistoricoUpdate.put("unidadAdministrativa", unidadAdministrativa);
										criteriaHistoricoUpdate.put("fechaIngreso", (String)ultimoHistorico.get("fechaDesde"));
										mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByCliente(criteriaHistoricoUpdate);
									}else{
										criteriaHistoricoUpdate.put("indicadorResta1", "1");
										criteriaHistoricoUpdate.put("oidHistoricoGerencia", oidUltimoHistorico);
										criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaIngreso"));
										criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
										criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
										
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteriaHistoricoUpdate);
									}
									
									
								}
								
								Map criteriaZona = new HashMap();
								criteriaZona.put("codigoZona", MapUtils.getString(unidadZona, "codigoZona", ""));
								criteriaZona.put("oidCliente", null);
								mantenimientoZONDirectorioDAO.updateGerenciaZona(criteriaZona);
							}
						}
						//Si es Gerente de Region cierra su registro respectivo en ZON_HISTO_GEREN, y actualiza el id del gerente en la tabla ZON_REGIO
						else if(cargoReemp.equals(Constants.ZON_MANT_GERENTE_REGION)){
						
							Map unidadRegion = (Map)datosUnidadesAdministrativasList.get(i);
					
							criteria.put("codigoRegionReem", MapUtils.getString(unidadRegion, "codigoRegion", ""));
							criteria.put("codigoZonaReem", "");
							
							unidadAdministrativa = MapUtils.getString(criteria, "codigoSubGerencia", "")+MapUtils.getString(unidadRegion, "codigoRegion", "");
							
							//Actualizar baja de cliente en Historico de Gerentes
							Map criteriaHistoricoZona = new HashMap();
							criteriaHistorico.put("codigoConsultora", codigoClienteReemplazante);
							criteriaHistorico.put("unidadAdministrativa", unidadAdministrativa);
							criteriaHistorico.put("codigoPais",  MapUtils.getString(criteria, "codigoPais", ""));
							
							if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX)){
								List listaHistoricoRegion = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByClienteFOX(criteriaHistorico);						
								if(listaHistoricoRegion != null && listaHistoricoRegion.size()>0){
									Map ultimoHistorico = (Map)listaHistoricoRegion.get(0);
									String corHistoricoGerente = (String)ultimoHistorico.get("corHistoricoGerente"); 
									String camDesde = (String)ultimoHistorico.get("campDesde");
									String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
										Map map1 = new HashMap();
										map1.put("codigoPais", (String)criteria.get("codigoPais"));
										map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
										map1.put("fecha", fechaIngreso);
									String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
									
									Map criteriaHistoricoUpdate = new HashMap();
									if(camDesde.equals(campIngreso)){
										criteriaHistoricoUpdate.put("indicadorResta1", null);
										criteriaHistoricoUpdate.put("corHistoricoGerente", corHistoricoGerente);
										criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaIngreso"));
										criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
										criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteriaHistoricoUpdate);
										
										//Se elimina el registro cuando campaaDesde es igual a campaaIngreso(Obtenida con la fechaIngreso). 
										criteriaHistoricoUpdate.put("unidadAdministrativa", unidadAdministrativa);
										criteriaHistoricoUpdate.put("campDesde", (String)ultimoHistorico.get("campDesde"));
										mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByClienteFOX(criteriaHistoricoUpdate);
									}else{
										criteriaHistoricoUpdate.put("indicadorResta1", "1");
										criteriaHistoricoUpdate.put("corHistoricoGerente", corHistoricoGerente);
										criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaIngreso"));
										criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
										criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByClienteFOX(criteriaHistoricoUpdate);
									}
									
									
								}
								
								Map criteriaRegion = new HashMap();
								criteriaRegion.put("codigoRegion", MapUtils.getString(unidadRegion, "codigoRegion", ""));
								criteriaRegion.put("codigoConsultora", null);
								criteriaRegion.put("codigoPais", (String)criteria.get("codigoPais"));
								criteriaRegion.put("usuarioLogin", (String)criteria.get("usuarioLogin"));							
								mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(criteriaRegion);
								
							}else{
								List listaHistoricoRegion = mantenimientoZONDirectorioDAO.getLastHistoricoGerenteByCliente(criteriaHistorico);						
								if(listaHistoricoRegion != null && listaHistoricoRegion.size()>0){
									Map ultimoHistorico = (Map)listaHistoricoRegion.get(0);
									String oidUltimoHistorico = (String)ultimoHistorico.get("oidHistoricoGerente"); 
									String fechaDesde = (String)ultimoHistorico.get("fechaDesde"); //Para obtener la campaaDesde
										Map map1 = new HashMap();
										map1.put("codigoPais", (String)criteria.get("codigoPais"));
										map1.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
										map1.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
										map1.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
										map1.put("fecha", fechaDesde);
									String campDesde = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map1);
									
									String fechaIngreso = (String)criteria.get("fechaIngreso"); //Para obtener la campaaIngreso
										Map map2 = new HashMap();
										map2.put("codigoPais", (String)criteria.get("codigoPais"));
										map2.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
										map2.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
										map2.put("codigoConexionExterna", (String)criteria.get("codigoConexionExterna"));
										map2.put("fecha", fechaIngreso);
									String campIngreso = mantenimientoZONDirectorioDAO.getPeriodoByFecha(map2);
									
									Map criteriaHistoricoUpdate = new HashMap();
									if(campDesde.equals(campIngreso)){ //Se comparan las campaas
										criteriaHistoricoUpdate.put("indicadorResta1", null);
										criteriaHistoricoUpdate.put("oidHistoricoGerencia", oidUltimoHistorico);
										criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaIngreso"));
										criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
										criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
										
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteriaHistoricoUpdate);
										
										//Se borra del zon_histo_geren al reemplazante cuando las campaas son iguales.
										criteriaHistoricoUpdate.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
										criteriaHistoricoUpdate.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
										criteriaHistoricoUpdate.put("unidadAdministrativa", unidadAdministrativa);
										criteriaHistoricoUpdate.put("fechaIngreso", (String)ultimoHistorico.get("fechaDesde"));
										mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByCliente(criteriaHistoricoUpdate);
										
									}else{
										criteriaHistoricoUpdate.put("indicadorResta1", "1");
										criteriaHistoricoUpdate.put("oidHistoricoGerencia", oidUltimoHistorico);
										criteriaHistoricoUpdate.put("fechaIngresoLicencia", (String)criteria.get("fechaIngreso"));
										criteriaHistoricoUpdate.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
										criteriaHistoricoUpdate.put("codigoPais", (String)criteria.get("codigoPais"));
										
										mantenimientoZONDirectorioDAO.updateLastHistoricoGerenteByCliente(criteriaHistoricoUpdate);
										
									}
									
									
								}
								
								Map criteriaRegion = new HashMap();
								criteriaRegion.put("codigoRegion", MapUtils.getString(unidadRegion, "codigoRegion", ""));
								criteriaRegion.put("oidCliente", null);
								mantenimientoZONDirectorioDAO.updateGerenciaRegion(criteriaRegion);
							}
							
						}
						
					
						
						//Actualizar Directorio de Ventas
						//BUSCA AL REEMPLAZANTE Y LE DA DE BAJA
	
						Map criteriaDirectorio = new HashMap();
						criteriaDirectorio.put("codigoConsultora", codigoClienteReemplazante);
						criteriaDirectorio.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_INACTIVA);
						criteriaDirectorio.put("usuarioLogin", MapUtils.getString(criteria, "usuarioLogin"));		
						criteriaDirectorio.put("codigoTipoCargo", cargoReemp);
						criteriaDirectorio.put("correlativoCabecera", oidCorrelativoCabecera);
						criteriaDirectorio.put("codigoPais", MapUtils.getString(criteria, "codigoPais", ""));
						
						Map historicoDirectorioReem = new HashMap();
						historicoDirectorio.put("codigoConsultora", codigoClienteReemplazante);
						historicoDirectorio.put("codigoTipoCargo", cargoReemp);
						historicoDirectorio.put("correlativoCabecera", oidCorrelativoCabecera);
						historicoDirectorio.put("codigoPais", MapUtils.getString(criteria, "codigoPais", ""));
						List listaHistoricoDVReem = mantenimientoZONDirectorioDAO.getDirectorioVentaCabeceraActivo(historicoDirectorio);
							
						if(listaHistoricoDVReem != null || listaHistoricoDVReem.size() > 0){
							Map mapHistoricoDVReem = (Map)listaHistoricoDVReem.get(0);
							String fechaRegiReem = DateUtil.convertDateToString("dd/MM/yyyy", (Date)mapHistoricoDVReem.get("fechaRegistro"));
							String fechaRegiFinReem = DateUtil.convertDateToString("dd/MM/yyyy", (Date)mapHistoricoDVReem.get("fechaRegistroFin"));
							
							if(StringUtils.isBlank(fechaRegiFinReem)){
								if(!StringUtils.isBlank(fechaRegiReem) && !fechaRegiReem.equals((String)criteria.get("fechaIngreso"))){
									
									int diferenciaEnDias = 1;
									Date fi;
									try {
										fi = DateUtil.convertStringToDate("dd/MM/yyyy", (String)criteria.get("fechaIngreso"));
										long tiempoFI = fi.getTime();
										long unDia = diferenciaEnDias * 24 * 60 * 60 * 1000;
										Date fechaAyer = new Date(tiempoFI - unDia);
										System.out.println(fechaAyer.toString());
										String fecNoveMenos1 = DateUtil.convertDateToString("dd/MM/yyyy", fechaAyer);
										criteriaDirectorio.put("fechaIngresoHasta", fecNoveMenos1); //Para inactivar al reemplazante
										
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}else{
									criteriaDirectorio.put("fechaIngresoHasta", (String)criteria.get("fechaIngreso")); //Para inactivar al reemplazante
								}
	
							}
							
						}
						
						
						mantenimientoZONDirectorioDAO.updateDirectorioVentaCabecera(criteriaDirectorio);
					
				    }	
				}	
			}			
		}
	
		//
		
		//Alta de Reemplazo
		if(StringUtils.isNotBlank(codigoClienteReemplazante)){
		//Si es Gerente de Zona actualiza el id del gerente en la tabla ZON_ZONA
		  if(codigoTipoCargoReemplazo.equals(Constants.ZON_MANT_GERENTE_ZONA)){
			
			if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			{
				Map mapReemplazoZona = new HashMap();
				mapReemplazoZona.put("codigoConsultora", codigoClienteReemplazante);
				mapReemplazoZona.put("codigoRegion", (String)criteria.get("codigoRegion"));
				mapReemplazoZona.put("codigoZona", (String)criteria.get("codigoZona"));
				mapReemplazoZona.put("codigoPais", (String)criteria.get("codigoPais"));
				mapReemplazoZona.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
				
				mantenimientoZONDirectorioDAO.updateGerenciaZonaFOX(mapReemplazoZona);
				
				Map mapReemplazoHistoGeren = new HashMap();
				if(codigoTipoCargo.equals("GR")||codigoTipoCargo.equals("MVR")||codigoTipoCargo.equals("EE")){
					mapReemplazoHistoGeren.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
					mapReemplazoHistoGeren.put("codigoRegion",(String)criteria.get("codigoRegion"));
				}else{
					mapReemplazoHistoGeren.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
					mapReemplazoHistoGeren.put("codigoZona",(String)criteria.get("codigoZona"));
					mapReemplazoHistoGeren.put("codigoRegion",(String)criteria.get("codigoRegion"));
				}
				mapReemplazoHistoGeren.put("codigoConsultora", codigoClienteReemplazante);
				mapReemplazoHistoGeren.put("fechaIngreso", (String)criteria.get("fechaIngreso"));
				mapReemplazoHistoGeren.put("codigoPais", (String)criteria.get("codigoPais"));
				mapReemplazoHistoGeren.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
				mapReemplazoHistoGeren.put("estadoRegistro", Constants.NUMERO_UNO);
				
				mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByClienteFOX(mapReemplazoHistoGeren);
				
				mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegionFOX(mapReemplazoHistoGeren);						
			}
			else
			{
				Map mapReemplazoZona = new HashMap();
				mapReemplazoZona.put("oidCliente", (String)criteria.get("oidCliente"));
				mapReemplazoZona.put("codigoZona", (String)criteria.get("codigoZona"));
				mantenimientoZONDirectorioDAO.updateGerenciaZona(mapReemplazoZona);
				
				Map mapReemplazoHistoGeren = new HashMap();
				mapReemplazoHistoGeren.put("codigoMarca", (String)criteria.get("codigoMarca"));
				mapReemplazoHistoGeren.put("codigoCanal", (String)criteria.get("codigoCanal"));			
				if(codigoTipoCargo.equals("GR")||codigoTipoCargo.equals("MVR")||codigoTipoCargo.equals("EE")){
					mapReemplazoHistoGeren.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
					mapReemplazoHistoGeren.put("codigoSubGerencia",(String)criteria.get("codigoSubGerencia"));
					mapReemplazoHistoGeren.put("codigoRegion",(String)criteria.get("codigoRegion"));
				}else{
					mapReemplazoHistoGeren.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
					mapReemplazoHistoGeren.put("codigoSubGerencia",(String)criteria.get("codigoSubGerencia"));
					mapReemplazoHistoGeren.put("codigoZona",(String)criteria.get("codigoZona"));
					mapReemplazoHistoGeren.put("codigoRegion",(String)criteria.get("codigoRegion"));
				}
				mapReemplazoHistoGeren.put("codigoConsultora", codigoClienteReemplazante);
				mapReemplazoHistoGeren.put("fechaIngreso", (String)criteria.get("fechaIngreso"));
				mapReemplazoHistoGeren.put("codigoPais", (String)criteria.get("codigoPais"));
				mapReemplazoHistoGeren.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
				
				mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByCliente(mapReemplazoHistoGeren);
				
				mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(mapReemplazoHistoGeren);						
			}
		  }
		}
		
		if(StringUtils.isNotBlank(codigoClienteReemplazante)){
		//Si es Gerente de Region actualiza el id del gerente en la tabla ZON_REGIO
		if(codigoTipoCargoReemplazo.equals(Constants.ZON_MANT_GERENTE_REGION)){
			if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			{
				Map mapReemplazoRegion = new HashMap();
				mapReemplazoRegion.put("codigoConsultora", codigoClienteReemplazante);
				mapReemplazoRegion.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
				mapReemplazoRegion.put("codigoRegion", (String)criteria.get("codigoRegion"));
				mapReemplazoRegion.put("codigoPais", (String)criteria.get("codigoPais"));
				mantenimientoZONDirectorioDAO.updateGerenciaRegionFOX(mapReemplazoRegion);
				
				Map mapReemplazoHistoGeren = new HashMap();			
				if(codigoTipoCargo.equals("GR")||codigoTipoCargo.equals("MVR")||codigoTipoCargo.equals("EE")){
					mapReemplazoHistoGeren.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
					mapReemplazoHistoGeren.put("codigoRegion",(String)criteria.get("codigoRegion"));
				}else{
					mapReemplazoHistoGeren.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
					mapReemplazoHistoGeren.put("codigoZona",(String)criteria.get("codigoZona"));
					mapReemplazoHistoGeren.put("codigoRegion",(String)criteria.get("codigoRegion"));
				}
				mapReemplazoHistoGeren.put("codigoConsultora", codigoClienteReemplazante);
				mapReemplazoHistoGeren.put("fechaIngreso", (String)criteria.get("fechaIngreso"));
				mapReemplazoHistoGeren.put("codigoPais", (String)criteria.get("codigoPais"));
				mapReemplazoHistoGeren.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
				mapReemplazoHistoGeren.put("estadoRegistro", Constants.NUMERO_UNO);
				
				mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByClienteFOX(mapReemplazoHistoGeren);
				
				mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegionFOX(mapReemplazoHistoGeren);			
			}
			else
			{
				Map mapReemplazoRegion = new HashMap();
				mapReemplazoRegion.put("oidCliente", (String)criteria.get("oidCliente"));
				mapReemplazoRegion.put("codigoRegion", (String)criteria.get("codigoRegion"));
				mantenimientoZONDirectorioDAO.updateGerenciaRegion(mapReemplazoRegion);
				
				Map mapReemplazoHistoGeren = new HashMap();
				mapReemplazoHistoGeren.put("codigoMarca", (String)criteria.get("codigoMarca"));
				mapReemplazoHistoGeren.put("codigoCanal", (String)criteria.get("codigoCanal"));			
				if(codigoTipoCargo.equals(codigoTipoCargoReemplazo)){
					mapReemplazoHistoGeren.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion"));
					mapReemplazoHistoGeren.put("codigoSubGerencia",(String)criteria.get("codigoSubGerencia"));
					mapReemplazoHistoGeren.put("codigoRegion",(String)criteria.get("codigoRegion"));
				}else{
					mapReemplazoHistoGeren.put("unidadAdministrativa",(String)criteria.get("codigoSubGerencia")+(String)criteria.get("codigoRegion")+(String)criteria.get("codigoZona"));
					mapReemplazoHistoGeren.put("codigoSubGerencia",(String)criteria.get("codigoSubGerencia"));
					mapReemplazoHistoGeren.put("codigoZona",(String)criteria.get("codigoZona"));
					mapReemplazoHistoGeren.put("codigoRegion",(String)criteria.get("codigoRegion"));
				}
				mapReemplazoHistoGeren.put("codigoConsultora", codigoClienteReemplazante);
				mapReemplazoHistoGeren.put("fechaIngreso", (String)criteria.get("fechaIngreso"));
				mapReemplazoHistoGeren.put("codigoPais", (String)criteria.get("codigoPais"));
				mapReemplazoHistoGeren.put("usuarioLogin", (String)criteria.get("usuarioLogin"));
				
				mantenimientoZONDirectorioDAO.deleteHistoricoGerenteByCliente(mapReemplazoHistoGeren);
				
				mantenimientoZONDirectorioDAO.insertHistoricoGerenteZonaRegion(mapReemplazoHistoGeren);			
			}
		  } 
		}

		/*Asimismo, crear otro registro la entidad Directoro Ventas 
		Cabecera y Directorio Ventas Detalle con con cdigo de 
		operacin RZ  y estatus Activo con el cdigo del cliente 
		que realiza el reemplazo*/
		
		criteria.put("tipoOperacion", Constants.ZON_MANT_CODIGO_OPERACION_RZ);
		criteria.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_ACTIVA);
		criteria.put("codigoConsultora", (String)criteria.get("codigoConsultoraReemplazante")); 
						
		criteria.put("tipoLicencia", null);
		criteria.put("codigoConsultoraReemplazante", null);
		criteria.put("fechaRegreso", null);
		
		criteria.put("envioCorreo", Constants.SI);
		criteria.put("enviarDatosReemplazante", Constants.NO);
				
		if(StringUtils.isNotBlank(codigoClienteReemplazante))
		{
			correlativoCabecera = mantenimientoZONDirectorioDAO.getCorrelativoCabeceraDirectorio();		
			criteria.put("correlativoCabecera", correlativoCabecera);
			criteria.put("fechaIngresoHasta", fechaIngresoHasta);
			mantenimientoZONDirectorioDAO.insertDirectorioVentaCabecera(criteria);
			mantenimientoZONDirectorioDAO.insertDirectorioVentaDetalle(criteria);
			
			criteria.put("enviarDatosReemplazante", Constants.SI);
		}
		
		criteria.put("listaUAs", listaUAs);
		criteria.put("codigoConsultora", codigoConsultora);
		criteria.put("tipoOperacion", Constants.ZON_MANT_CODIGO_OPERACION_LI);
		criteria.put("codigoConsultoraReemplazante", codigoClienteReemplazante);
		
		criteria.put("codigoRegionReem", codigoRegionReemplaan.toString().length()==0 ? " ":codigoRegionReemplaan.toString());
		criteria.put("descripcionCargoReemplaan", descripcionCargoReemplaan.toString().length()==0 ? " ":descripcionCargoReemplaan.toString());
		criteria.put("codigoZonaReem", codigoZonaReemplaan.toString().length()==0 ? " ": codigoZonaReemplaan.toString());		
		criteria.put("tipoUACargoReemplaan", tipoUACargoReemplaan);
		
		enviarCorreo(criteria);
		
		//actualizar en la entidad Parmetros de Ruta el atributo 
		//Indicador de Activar Envo a Destinatarios con 0 (cero = Desactivar ) para el tipo de operacion
		mantenimientoZONDirectorioDAO.updateParametroRutaEnvioCorreo(criteria);			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getDirectorioHistorioNovedades(java.util.Map)
	 */
	public List getDirectorioHistorioNovedades(Map criteria) {
		return mantenimientoZONDirectorioDAO.getDirectorioHistorioNovedades(criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getConsultarDirectorioVentas(java.util.Map)
	 */
	public List getConsultarDirectorioVentas(Map criteria) {
		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		List result = null;
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			result = mantenimientoZONDirectorioDAO.getConsultarDirectorioVentasFOX(criteria);
		else
			result = mantenimientoZONDirectorioDAO.getConsultarDirectorioVentas(criteria);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getTipoCargoList()
	 */
	public List getTipoCargoList() {
		return mantenimientoZONDirectorioDAO.getTipoCargoList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getValidarTipoCargoDirectorioVenta(java.util.Map)
	 */
	public int getValidarTipoCargoDirectorioVenta(Map criteria) {
		return mantenimientoZONDirectorioDAO.getValidarTipoCargoDirectorioVenta(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getRolesByCriteria(java.util.Map)
	 */
	public List getRolesByCriteria(Map params) {
		return mantenimientoZONDirectorioDAO.getRolesByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#removeRol(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeRol(String id, Usuario usuario) {
		RolDirectorio rol = mantenimientoZONDirectorioDAO.getRol(id);
		
		if(rol != null)
		{
			rol.setEstado(Constants.ESTADO_INACTIVO);
			
			mantenimientoZONDirectorioDAO.updateRol(rol, usuario);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getPerfilesByCriteria(java.util.Map)
	 */
	public List getPerfilesByCriteria(Map params) {
		return mantenimientoZONDirectorioDAO.getPerfilesByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#removePerfil(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removePerfil(String id, Usuario usuario) {
		PerfilDirectorio perfil = mantenimientoZONDirectorioDAO.getPerfil(id);
		
		if(perfil != null)
		{
			perfil.setEstado(Constants.ESTADO_INACTIVO);
			
			mantenimientoZONDirectorioDAO.updatePerfil(perfil, usuario);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getPerfil(java.lang.String)
	 */
	public PerfilDirectorio getPerfil(String id) {
		return mantenimientoZONDirectorioDAO.getPerfil(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getRol(java.lang.String)
	 */
	public RolDirectorio getRol(String id) {
		return mantenimientoZONDirectorioDAO.getRol(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#insertRol(biz.belcorp.ssicc.spusicc.zon.model.RolDirectorio, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRol(RolDirectorio rol, Usuario usuario) {
        // Verificamos que no exista un rol con el mismo codigo
        try {
        	mantenimientoZONDirectorioDAO.getRol(rol.getCodigo());
            throw new InvalidIdentifierException(RolDirectorio.class, rol.getCodigo());
        }
        catch (ObjectRetrievalFailureException orfe) {
            // ignore
        }
        
        // Verificamos que no exista un rol con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
        
        Map criteria = new HashMap();        
        criteria.put("descripcion", rol.getDescripcion());
        
        List roles = mantenimientoZONDirectorioDAO.getRolesByCriteria(criteria);
        if (roles != null && roles.size() > 0) {
            throw new InvalidDescriptionException(RolDirectorio.class, rol.getDescripcion());
        }
        // Seteamos los valores por defecto
        rol.setEstado(Constants.ESTADO_ACTIVO);
        mantenimientoZONDirectorioDAO.insertRol(rol, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#updateRol(biz.belcorp.ssicc.spusicc.zon.model.RolDirectorio, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRol(RolDirectorio rol, Usuario usuario) {
        // Verificamos que no exista un rol con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
        Map criteria = new HashMap();        
        criteria.put("descripcion", rol.getDescripcion());
        
        List roles = mantenimientoZONDirectorioDAO.getRolesByCriteria(criteria);
        
        if (roles != null && roles.size() > 0) {
            for (int i = 0; i < roles.size(); i++) {
                RolDirectorio o = (RolDirectorio) roles.get(i);
                if (!o.getCodigo().equals(rol.getCodigo())) {
                    throw new InvalidDescriptionException(RolDirectorio.class, rol.getDescripcion());
                }
            }
        }

        mantenimientoZONDirectorioDAO.updateRol(rol, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#insertPerfil(biz.belcorp.ssicc.spusicc.zon.model.PerfilDirectorio, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPerfil(PerfilDirectorio perfil, Usuario usuario) {
        // Verificamos que no exista un rol con el mismo codigo
        try {
        	mantenimientoZONDirectorioDAO.getPerfil(perfil.getCodigo());
            throw new InvalidIdentifierException(PerfilDirectorio.class, perfil.getCodigo());
        }
        catch (ObjectRetrievalFailureException orfe) {
            // ignore
        }
        
        // Verificamos que no exista un rol con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
        
        Map criteria = new HashMap();        
        criteria.put("descripcion", perfil.getDescripcion());
        
        List perfiles = mantenimientoZONDirectorioDAO.getPerfilesByCriteria(criteria);
        if (perfiles != null && perfiles.size() > 0) {
            throw new InvalidDescriptionException(PerfilDirectorio.class, perfil.getDescripcion());
        }
        // Seteamos los valores por defecto
        perfil.setEstado(Constants.ESTADO_ACTIVO);
        mantenimientoZONDirectorioDAO.insertPerfil(perfil, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#updatePerfil(biz.belcorp.ssicc.spusicc.zon.model.PerfilDirectorio, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePerfil(PerfilDirectorio perfil, Usuario usuario) {
        // Verificamos que no exista un perfil con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
        Map criteria = new HashMap();        
        criteria.put("descripcion", perfil.getDescripcion());
        
        List perfiles = mantenimientoZONDirectorioDAO.getPerfilesByCriteria(criteria);
        
        if (perfiles != null && perfiles.size() > 0) {
            for (int i = 0; i < perfiles.size(); i++) {
                PerfilDirectorio o = (PerfilDirectorio) perfiles.get(i);
                if (!o.getCodigo().equals(perfil.getCodigo())) {
                    throw new InvalidDescriptionException(PerfilDirectorio.class, perfil.getDescripcion());
                }
            }
        }

        mantenimientoZONDirectorioDAO.updatePerfil(perfil, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#insertHistoricoDirectorioVenta(biz.belcorp.ssicc.spusicc.zon.model.HistoricoDirectorioVenta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoDirectorioVenta(HistoricoDirectorioVenta historico, Usuario usuario) {
		
		try
		{
			//Buscamos si ya existe el registro
			Map params = new HashMap();
			params.put("codigoPais", historico.getCodigoPais());
			params.put("codigoCliente", historico.getCodigoCliente());

			HistoricoDirectorioVenta historicoExistente = mantenimientoZONDirectorioDAO.getHistoricoDirectorioVenta(params);
			
			if(historicoExistente == null)
			{
				//Preparamos los datos para realizar la insercin
				Map datos = mantenimientoZONDirectorioDAO.getDetalleDatosCliente(params);
				BeanUtils.copyProperties(historico, datos);
				//
				
				mantenimientoZONDirectorioDAO.insertHistoricoDirectorioVenta(historico, usuario);
			}
		}
		catch(Exception ex)
		{
			log.warn("No se ha podido grabar en el histrico");
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getDetalleDatosCliente(java.util.Map)
	 */
	public Map getDetalleDatosCliente(Map params) {
		return mantenimientoZONDirectorioDAO.getDetalleDatosCliente(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#insertHistoricoDirectorioVentaDetalle(biz.belcorp.ssicc.spusicc.zon.model.HistoricoDirectorioVenta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoDirectorioVentaDetalle(HistoricoDirectorioVenta datosClienteAntes, Usuario usuario) {
		try
		{
			// El parametro tiene los datos del cliente antes de ser modificados
			//Buscamos si ya existe el registro
			Map params = new HashMap();
			params.put("codigoPais", datosClienteAntes.getCodigoPais());
			params.put("codigoCliente", datosClienteAntes.getCodigoCliente());

			HistoricoDirectorioVenta historicoExistente = mantenimientoZONDirectorioDAO.getHistoricoDirectorioVenta(params);
			
			if(historicoExistente != null)
			{
				//Obtenemos los datos recientes del cliente
				Map datos = mantenimientoZONDirectorioDAO.getDetalleDatosCliente(params);				
				HistoricoDirectorioVenta datosClienteAhora = new HistoricoDirectorioVenta();				
				BeanUtils.copyProperties(datosClienteAhora, datos);
				//
				
				//Verificamos si hubo cambios en los campos auditables y cargamos solo los valores que fueron
				//modificados para enviarlo al historico
				HistoricoDirectorioVenta nuevoHistorico = new HistoricoDirectorioVenta();
				nuevoHistorico.setCodigoPais(historicoExistente.getCodigoPais());
				nuevoHistorico.setCodigoCliente(historicoExistente.getCodigoCliente());
				nuevoHistorico.setOidPadre(historicoExistente.getOid());
				
				if(!StringUtils.equals(datosClienteAntes.getPrimerNombre(), datosClienteAhora.getPrimerNombre()))
				{
					nuevoHistorico.setPrimerNombre(datosClienteAhora.getPrimerNombre());
				}
				if(!StringUtils.equals(datosClienteAntes.getSegundoNombre(), datosClienteAhora.getSegundoNombre()))
				{
					nuevoHistorico.setSegundoNombre(datosClienteAhora.getSegundoNombre());
				}
				if(!StringUtils.equals(datosClienteAntes.getApellidoPaterno(), datosClienteAhora.getApellidoPaterno()))
				{
					nuevoHistorico.setApellidoPaterno(datosClienteAhora.getApellidoPaterno());
				}
				if(!StringUtils.equals(datosClienteAntes.getApellidoMaterno(), datosClienteAhora.getApellidoMaterno()))
				{
					nuevoHistorico.setApellidoMaterno(datosClienteAhora.getApellidoMaterno());
				}
				if(!StringUtils.equals(datosClienteAntes.getCodigoBelcorp(), datosClienteAhora.getCodigoBelcorp()))
				{
					nuevoHistorico.setCodigoBelcorp(datosClienteAhora.getCodigoBelcorp());
				}
				if(!StringUtils.equals(datosClienteAntes.getOidTipoDocumentoIdentidad(), datosClienteAhora.getOidTipoDocumentoIdentidad()))
				{
					nuevoHistorico.setOidTipoDocumentoIdentidad(datosClienteAhora.getOidTipoDocumentoIdentidad());
				}
				if(!StringUtils.equals(datosClienteAntes.getNumeroDocumentoIdentidad(), datosClienteAhora.getNumeroDocumentoIdentidad()))
				{
					nuevoHistorico.setNumeroDocumentoIdentidad(datosClienteAhora.getNumeroDocumentoIdentidad());
				}
				if(!StringUtils.equals(datosClienteAntes.getFechaNacimiento(), datosClienteAhora.getFechaNacimiento()))
				{
					nuevoHistorico.setFechaNacimiento(datosClienteAhora.getFechaNacimiento());
				}
				if(!StringUtils.equals(datosClienteAntes.getCodigoRegion(), datosClienteAhora.getCodigoRegion()))
				{
					nuevoHistorico.setCodigoRegion(datosClienteAhora.getCodigoRegion());
				}
				if(!StringUtils.equals(datosClienteAntes.getCodigoZona(), datosClienteAhora.getCodigoZona()))
				{
					nuevoHistorico.setCodigoZona(datosClienteAhora.getCodigoZona());
				}
				if(!StringUtils.equals(datosClienteAntes.getNumeroCelularEmpresa(), datosClienteAhora.getNumeroCelularEmpresa()))
				{
					nuevoHistorico.setNumeroCelularEmpresa(datosClienteAhora.getNumeroCelularEmpresa());
				}
				if(!StringUtils.equals(datosClienteAntes.getNumeroCelularPersonal(), datosClienteAhora.getNumeroCelularPersonal()))
				{
					nuevoHistorico.setNumeroCelularPersonal(datosClienteAhora.getNumeroCelularPersonal());
				}
				if(!StringUtils.equals(datosClienteAntes.getDireccionDomicilio(), datosClienteAhora.getDireccionDomicilio()))
				{
					nuevoHistorico.setDireccionDomicilio(datosClienteAhora.getDireccionDomicilio());
				}
				if(!StringUtils.equals(datosClienteAntes.getNumeroTelefonoCasa(), datosClienteAhora.getNumeroTelefonoCasa()))
				{
					nuevoHistorico.setNumeroTelefonoCasa(datosClienteAhora.getNumeroTelefonoCasa());
				}
				if(!StringUtils.equals(datosClienteAntes.getEmail(), datosClienteAhora.getEmail()))
				{
					nuevoHistorico.setEmail(datosClienteAhora.getEmail());
				}
				
				mantenimientoZONDirectorioDAO.insertHistoricoDirectorioVenta(nuevoHistorico, usuario);
				//
			}
		}
		catch(Exception ex)
		{
			log.warn("No se ha podido grabar en el histrico");
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getCodigoCargosList(java.util.Map)
	 */
	public List getCodigoCargosList(Map criteria) {
		return mantenimientoZONDirectorioDAO.getCodigoCargosList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getTipoCargoFuturas(java.util.Map)
	 */
	public Map getTipoCargoFuturas(Map criteria){
		return mantenimientoZONDirectorioDAO.getTipoCargoFuturas(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getVerificaClienteSSiCC(java.util.Map)
	 */
	public String getVerificaClienteSSiCC(Map criteria) {
		return mantenimientoZONDirectorioDAO.getVerificaClienteSSiCC(criteria);
	}
	 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getVerificaClienteFOX(java.util.Map)
	 */
	public String getVerificaClienteFOX(Map criteria) {
		return  mantenimientoZONDirectorioDAO.getVerificaClienteFOX(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getObtenerAsignacionesConsultoraSSiCC(java.util.Map)
	 */
	public List getObtenerAsignacionesConsultoraSSiCC(Map criteria) {
		return mantenimientoZONDirectorioDAO.getObtenerAsignacionesConsultoraSSiCC(criteria);
	}
	 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getObtenerAsignacionesConsultoraFOX(java.util.Map)
	 */
	public List getObtenerAsignacionesConsultoraFOX(Map criteria) {
		return  mantenimientoZONDirectorioDAO.getObtenerAsignacionesConsultoraFOX(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONDirectorioService#getControlFacturacionByCriteriaFOX(java.util.Map)
	 */
	public Map getControlFacturacionByCriteriaFOX(Map criteria) {
		
		return mantenimientoZONDirectorioDAO.getControlFacturacionByCriteriaFOX(criteria);
	}

	

	

}
