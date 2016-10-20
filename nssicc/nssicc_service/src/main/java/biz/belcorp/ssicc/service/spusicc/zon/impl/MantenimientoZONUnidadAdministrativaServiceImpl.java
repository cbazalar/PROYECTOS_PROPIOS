package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONRegionDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONUnidadAdministrativaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONUnidadAdministrativaService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.mantenimientoZONUnidadAdministrativaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoZONUnidadAdministrativaServiceImpl extends BaseService implements MantenimientoZONUnidadAdministrativaService{
	
	@Resource(name="spusicc.mantenimientoZONRegionDAO")
	private MantenimientoZONRegionDAO mantenimientoZONRegionDAO;
	
	@Resource(name="spusicc.mantenimientoZONUnidadAdministrativaDAO")
	private MantenimientoZONUnidadAdministrativaDAO mantenimientoZONUnidadAdministrativaDAO;;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadAdministrativaService#getRegionesList(java.util.Map)
	 */
	public List getRegionesList(Map criteria) {
		return mantenimientoZONUnidadAdministrativaDAO.getRegionesList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadAdministrativaService#getRegionesZonasList(java.util.Map)
	 */
	public List getRegionesZonasList(Map criteria) {
		return mantenimientoZONUnidadAdministrativaDAO.getRegionesZonasList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadAdministrativaService#insertZona(java.util.Map)
	 */
	public String insertZona(Map params) {
		String resultado = "";
		int zonaEncontrada = mantenimientoZONUnidadAdministrativaDAO.getEncuentraZonaByCodigoZona(params);
		String indicadorManual = (String)params.get("manual");
		
		
		if (StringUtils.equals(indicadorManual, Constants.NUMERO_UNO)) {
			if(zonaEncontrada == 1){
				resultado = "La Zona ya se encuentra registrada";
			}
		}
		
		mantenimientoZONUnidadAdministrativaDAO.insertZona(params);
		
		
		return resultado;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadAdministrativaService#updateZona(java.util.Map)
	 */
	public void updateZona(Map params) {
		mantenimientoZONUnidadAdministrativaDAO.updateZona(params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadAdministrativaService#getUltimoCodigoZona(java.util.Map)
	 */
	public int getUltimoCodigoZona(Map criteria) {
		return mantenimientoZONUnidadAdministrativaDAO.getUltimoCodigoZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadAdministrativaService#deleteRegion(java.util.Map)
	 */
	public String removeRegion(Map criteria) {
		String resultado = "";
		
		int indicadorEliminarRegion = mantenimientoZONUnidadAdministrativaDAO.getIndicadorEliminarRegion(criteria);
		
		if(indicadorEliminarRegion == 0){
			mantenimientoZONUnidadAdministrativaDAO.removeRegion(criteria);
		}else{
			resultado = "region.remove.error";
		}
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadAdministrativaService#deleteZona(java.util.Map)
	 */
	public String removeZona(Map criteria) {
		String resultado = "";
		
		int indicadorEliminarZona = mantenimientoZONUnidadAdministrativaDAO.getIndicadorEliminarZona(criteria);
		
		if(indicadorEliminarZona == 0){
			mantenimientoZONUnidadAdministrativaDAO.removeZona(criteria);
		} else{
			resultado = "zona.remove.error";
		}
		
		return resultado;	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadAdministrativaService#getZona(java.lang.Integer)
	 */
	public Map getZona(Integer oidZona) {
		return mantenimientoZONUnidadAdministrativaDAO.getZona(oidZona);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadAdministrativaService#executeProcesarArchivoExcel(java.lang.String, java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public String executeProcesarArchivoExcel(Map params,
			String directorioTemporal, String nombreArchivo, Usuario usuario) throws Exception{
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);

		String descripcionZona = "";
		String codigoZona =  "";
		String poblacion =  "";			
	    String filaExcel =  "";
	    StringBuilder mensaje = new StringBuilder();
		boolean error = false;
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			
			codigoZona = StringUtils.remove((String)mapRow.get("0"), ".0");
			descripcionZona = StringUtils.remove((String)mapRow.get("1"), ".0");
			poblacion = StringUtils.remove((String)mapRow.get("2"), ".0");
		    filaExcel = (String)mapRow.get("rowNum");

		    params.put("codigoZona", codigoZona);
		    
		    List lista = mantenimientoZONUnidadAdministrativaDAO.getRegionesZonasList(params);
		    
		    if(lista != null && lista.size() > 0)
		    {
		    	try
		    	{
		    		if(StringUtils.isNotBlank(poblacion))
		    		{
		    			Integer nPoblacion = Integer.parseInt(poblacion);
		    		}
		    	}
		    	catch(Exception ex){
		    		mensaje.append(String.format("Fila: <b>%s</b>; zona <b>%s</b>, valor de poblacion no vlida: <b>%s</b> <br/>", filaExcel, codigoZona, poblacion));
		    		poblacion = "";
		    	}
		    	
		    	//Actualizar
		    	Map zonaMap = (Map)lista.get(0);
		    	Map zonaParams = new HashMap();
		    	zonaParams.put("oidZona", MapUtils.getString(zonaMap, "oidZona", ""));
		    	if(!StringUtils.isBlank(descripcionZona) && !StringUtils.isBlank(poblacion))
		    	{
		    		//Actualizar ambos
		    		zonaParams.put("descripcionZona", descripcionZona);
		    		zonaParams.put("poblacion", poblacion);
		    		
		    		mantenimientoZONUnidadAdministrativaDAO.updateZona(zonaParams);
		    	}
		    	else if(!StringUtils.isBlank(descripcionZona) && StringUtils.isBlank(poblacion))
		    	{
		    		//Actualizar solo descripcion
		    		zonaParams.put("descripcionZona", descripcionZona);
		    		mantenimientoZONUnidadAdministrativaDAO.updateZona(zonaParams);
		    	}
		    	else if(StringUtils.isBlank(descripcionZona) && !StringUtils.isBlank(poblacion))
		    	{
		    		//Actualizar solo poblacion
		    		zonaParams.put("descripcionZona", MapUtils.getString(zonaMap, "descZona", ""));
		    		zonaParams.put("poblacion", poblacion);
		    		mantenimientoZONUnidadAdministrativaDAO.updateZona(zonaParams);
		    	}
		    }
		    else
		    {
		    	mensaje.append(String.format("Fila: <b>%s</b>; zona <b>%s</b>, no existe! <br/>", filaExcel, codigoZona));
		    }
		}
		excelUtil.cerrar();

		return mensaje.toString();
	}
	
}