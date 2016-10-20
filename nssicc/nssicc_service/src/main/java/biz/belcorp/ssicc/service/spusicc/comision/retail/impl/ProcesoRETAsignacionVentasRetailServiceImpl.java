package biz.belcorp.ssicc.service.spusicc.comision.retail.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.comision.retail.ProcesoRETAsignacionVentasRetailDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.retail.ProcesoRETAsignacionVentasRetailService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoRETAsignacionVentasRetailServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */	
@Service("spusicc.procesoRETAsignacionVentasRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRETAsignacionVentasRetailServiceImpl extends BaseService
		implements ProcesoRETAsignacionVentasRetailService {
	
	@Resource(name="spusicc.procesoRETAsignacionVentasRetailDAO")
	private ProcesoRETAsignacionVentasRetailDAO procesoRETAsignacionVentasRetailDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.service.ProcesoRETAsignacionVentasRetailService#executeAsignacionVentasRetail(java.util.Map)
	 */
	public void executeAsignacionVentasRetail(Map criteria) {
		procesoRETAsignacionVentasRetailDAO.executeAsignacionVentasRetail(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.service.ProcesoRETAsignacionVentasRetailService#saveConsolidadoRetail(java.util.List)
	 */
	public void saveConsolidadoRetail(List retailConsolidado) throws Exception{
		
		Iterator it = retailConsolidado.iterator();
		int i=0;
		while(it.hasNext()){
			Map map = (Map)it.next();

			try{
				//buscamos si existe
				int cont=procesoRETAsignacionVentasRetailDAO.getExisteConsolidadoRetail(map);
				if(cont==0){//no existe
					//grabando en la entidad cabecera				
					procesoRETAsignacionVentasRetailDAO.saveConsolidadoRetail(map);	
				}else{
					//actualizamos
					procesoRETAsignacionVentasRetailDAO.updateConsolidadoRetail(map);
				}									
				//si hay error
			}catch(Exception e){
				String codigoIsoIdioma = (String)map.get("codigoIsoIdioma");
				String mensajeError=//"Fila "+(i+1)+": "+e.getMessage();				
						 messageSource.
						   getMessage("procesoRETVentasRetail.error.carga.consolidado",
								   new String[]{""+(i+1)},getLocaleIdioma(codigoIsoIdioma));				
				throw new Exception(mensajeError+" "+e.getMessage());
				
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.service.ProcesoRETAsignacionVentasRetailService#saveVentaRetail(java.util.List, java.util.List)
	 */
	public void saveVentaRetail(List retailCabecera, List retailDetalle, String tipoProceso) throws Exception{
		
		Iterator it = retailCabecera.iterator();
		int i = 0;
		
		while(it.hasNext()){
			Map map = (Map)it.next();
			if(StringUtils.equals(StringUtils.trim(tipoProceso), Constants.PARAMETRO_TIPO_PROCESO_1)){
				//elimando  detalle y cabecera por fecha de documento
				procesoRETAsignacionVentasRetailDAO.deleteVentaRetailDetalle(map);
				procesoRETAsignacionVentasRetailDAO.deleteVentaRetailCabecera(map);				
			}	
			//grabando en la entidad cabecera
			try{	
				
				if(procesoRETAsignacionVentasRetailDAO.getExisteVentaRetailCabec(map)){
				procesoRETAsignacionVentasRetailDAO.saveVentaRetailCabec(map);
				}
								
				//si hay error
			}catch(Exception e){
				String codigoIsoIdioma = (String)map.get("codigoIsoIdioma");
				String mensajeError = messageSource.getMessage("procesoRETVentasRetail.error.carga.cabecera", new String[]{""+(i+1)},getLocaleIdioma(codigoIsoIdioma));
				throw new Exception(mensajeError + " " + e.getMessage());
			}
			i++;
		}
		
		//insertando los detalles
		it = retailDetalle.iterator();
		while(it.hasNext()){
			Map map = (Map)it.next();
			//grabando en la entidad detalle
			try{	
				if(procesoRETAsignacionVentasRetailDAO.getExisteVentaRetailCabec(map)){
				procesoRETAsignacionVentasRetailDAO.saveVentaRetailDetalle(map);
				}
				//si hay error
			}catch(Exception e){
				//String mensajeError="Fila "+(i+1)+": "+e.getMessage();
				String codigoIsoIdioma = (String)map.get("codigoIsoIdioma");
				String mensajeError=//"Fila "+(i+1)+": "+e.getMessage();				
						 messageSource.
						   getMessage("procesoRETVentasRetail.error.carga.detalle",
								   new String[]{""+(i+1)},getLocaleIdioma(codigoIsoIdioma));				
				throw new Exception(mensajeError+" "+e.getMessage());				
			}
		}		
		
	}


	/**
	 * Retorna la descripcion por idioma
	 * @param codigoIsoIdioma
	 * @return
	 */
	protected Locale getLocaleIdioma(String codigoIsoIdioma) {
	    //mantenimientoEDUGenericoService.getIdiomaISO(codigoPais);
		if(StringUtils.isNotEmpty(codigoIsoIdioma)){
		    if(Constants.EDU_IDIOMA_DEFAULT_ES.equals(codigoIsoIdioma.toLowerCase()))
			  return  new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
		    else{
		    	log.debug("codigoIsoIdioma " + codigoIsoIdioma);		
		      return new Locale(codigoIsoIdioma.toLowerCase());
		    }
		}
		log.debug("default " + codigoIsoIdioma);
		return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);		
	}
	
	/* INI JJ PER-SiCC-2012-0348 */
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.service.ProcesoRETAsignacionVentasRetailService#deleteVentaRetailCabecera(java.util.Map)
	 */
	public void deleteVentaRetailCabecera(Map map) {
		procesoRETAsignacionVentasRetailDAO.deleteVentaRetailCabecera(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.retail.service.ProcesoRETAsignacionVentasRetailService#deleteVentaRetailDetalle(java.util.Map)
	 */
	public void deleteVentaRetailDetalle(Map map) {
		procesoRETAsignacionVentasRetailDAO.deleteVentaRetailDetalle(map);
	}
	/**
	 * Lista los registros de la venta Retail cabecera 
	 * @param map
	 * @return 
	 */
	public List listaRetailCabecera(Map map) {
		return procesoRETAsignacionVentasRetailDAO.listaRetailCabecera(map);
	}
	/**
	 * Lista los registros de la venta Retail detalle 
	 * @param map
	 * @return 
	 */
	public List listaRetailDetalle(Map map) {
		return procesoRETAsignacionVentasRetailDAO.listaRetailDetalle(map);
	}
	/**
	 * Lista los campaa y fecha de Proceso
	 * @param map
	 * @return 
	 */
	public List listaCampanaFechaProceso() {
		return procesoRETAsignacionVentasRetailDAO.listaCampanaFechaProceso();
	}
	public List listaPais(Map map){
		return procesoRETAsignacionVentasRetailDAO.listaPais(map);
	}
	/* FIN JJ PER-SiCC-2012-0348 */
}