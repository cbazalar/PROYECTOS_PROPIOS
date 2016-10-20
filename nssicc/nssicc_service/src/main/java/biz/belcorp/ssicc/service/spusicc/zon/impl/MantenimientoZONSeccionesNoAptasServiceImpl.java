/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONSeccionesNoAptasDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.SeccionNoApta;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONSeccionesNoAptasService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author tokkto
 *
 */
@Service("spusicc.mantenimientoZONSeccionesNoAptasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoZONSeccionesNoAptasServiceImpl extends BaseService
		implements MantenimientoZONSeccionesNoAptasService {

	@Resource(name="spusicc.mantenimientoZONSeccionesNoAptasDAO")
	private MantenimientoZONSeccionesNoAptasDAO mantenimientoZONSeccionesNoAptasDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONSeccionesNoAptasService#executeProcesarArchivoExcel(java.lang.String, java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void executeProcesarArchivoExcel(String directorioTemporal,
			String nombreArchivo, Usuario usuario) throws Exception {
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);

		String codigoRegion = "";
		String codigoZona =  "";
		String codigoSeccion =  "";			
	    String filaExcel =  "";
	    String mensaje = "";
		boolean error = false;
		
		//Borramos los registros anteriores
		mantenimientoZONSeccionesNoAptasDAO.deleteSeccionNoApta();
		//
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			
			codigoRegion = StringUtils.remove((String)mapRow.get("0"), ".0");
			codigoZona = StringUtils.remove((String)mapRow.get("1"), ".0");
			codigoSeccion = (String)mapRow.get("2");			
		    filaExcel = (String)mapRow.get("rowNum");

		    SeccionNoApta seccion = new SeccionNoApta();
		    seccion.setCodigoRegion(codigoRegion);
		    seccion.setCodigoZona(codigoZona);
		    seccion.setCodigoSeccion(codigoSeccion);
		    
		    if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isNotBlank(codigoZona) && StringUtils.isBlank(codigoSeccion))
		    {		    	
		    	Base zona = mantenimientoZONSeccionesNoAptasDAO.getZonaByRegionZona(codigoRegion, codigoZona);
		    	
		    	if(zona == null)
		    	{
		    		mensaje = String.format("Fila: <b>%s</b>; region <b>%s</b>, zona <b>%s</b>, no existe!", filaExcel, codigoRegion, codigoZona);
		    		error = true;
		    		break;
		    	}
		    	
		    	//Obtener todas las secciones de regionzona
		    	List secciones = mantenimientoZONSeccionesNoAptasDAO.getSeccionesByRegionZona(codigoRegion, codigoZona);
		    	
		    	if(secciones != null)
		    	{
		    		for(int i=0; i<secciones.size(); i++)
		    		{
		    			Base sec = (Base)secciones.get(i);
		    			
		    		    SeccionNoApta sna = new SeccionNoApta();
		    		    sna.setCodigoRegion(codigoRegion);
		    		    sna.setCodigoZona(codigoZona);
		    		    sna.setCodigoSeccion(sec.getCodigo());
		    			
		    		    SeccionNoApta snaExistente = mantenimientoZONSeccionesNoAptasDAO.getSeccionNoApta(sna.getCodigoRegion(), sna.getCodigoZona(), sna.getCodigoSeccion());
		    		    
		    		    if(snaExistente == null)
		    		    	mantenimientoZONSeccionesNoAptasDAO.insertSeccionNoApta(sna, usuario);
		    		}
		    	}
		    }
		    else if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isNotBlank(codigoZona) && StringUtils.isNotBlank(codigoSeccion))
		    {
		    	Base secc = mantenimientoZONSeccionesNoAptasDAO.getSeccionByRegionZonaSeccion(codigoRegion, codigoZona, codigoSeccion);
		    	if(secc == null)
		    	{
		    		mensaje = String.format("Fila: <b>%s</b>; region <b>%s</b>, zona <b>%s</b>, seccion <b>%s</b> no existe!", filaExcel, codigoRegion, codigoZona, codigoSeccion);
		    		error = true;
		    		break;
		    	}
		    	
		    	SeccionNoApta snaExistente = mantenimientoZONSeccionesNoAptasDAO.getSeccionNoApta(seccion.getCodigoRegion(), seccion.getCodigoZona(), seccion.getCodigoSeccion());
		    	
		    	if(snaExistente == null)
		    		mantenimientoZONSeccionesNoAptasDAO.insertSeccionNoApta(seccion, usuario);
		    }
		}
		excelUtil.cerrar();
		
		if(error)
		{
			throw new Exception(mensaje);
		}
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONSeccionesNoAptasService#getSeccionesAptasNoAptasByCriteria(java.util.Map)
	 */
	public List getSeccionesAptasNoAptasByCriteria(Map criteria) {
		return mantenimientoZONSeccionesNoAptasDAO.getSeccionesAptasNoAptasByCriteria(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONSeccionesNoAptasService#getSeccionNoApta()
	 */
	public SeccionNoApta getSeccionNoApta(String codigoRegion, String codigoZona, String codigoSeccion) {
		return mantenimientoZONSeccionesNoAptasDAO.getSeccionNoApta(codigoRegion, codigoZona, codigoSeccion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONSeccionesNoAptasService#deleteSeccionNoApta(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void deleteSeccionNoApta(String codigoRegion, String codigoZona,
			String codigoSeccion) {
		mantenimientoZONSeccionesNoAptasDAO.deleteSeccionNoAptaById(codigoRegion, codigoZona, codigoSeccion);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONSeccionesNoAptasService#insertSeccionNoApta(biz.belcorp.ssicc.spusicc.zon.model.SeccionNoApta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertSeccionNoApta(SeccionNoApta seccion, Usuario usuario) {
		mantenimientoZONSeccionesNoAptasDAO.insertSeccionNoApta(seccion, usuario);
	}
}
