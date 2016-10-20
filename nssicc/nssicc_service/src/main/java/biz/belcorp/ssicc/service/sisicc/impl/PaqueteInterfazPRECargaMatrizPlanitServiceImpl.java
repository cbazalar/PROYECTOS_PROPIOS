package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.InterfazPREService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

@Service("sisicc.paqueteInterfazPRECargaMatrizPlanitService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazPRECargaMatrizPlanitServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl 
{
	@Resource(name="sisicc.interfazPREService")
	private InterfazPREService interfazPREService;
	
	@Override
	protected void beforeExecuteInterfaz(Map params) throws Exception {
		// TODO Auto-generated method stub
		super.beforeExecuteInterfaz(params);
		log.debug("Entering 'before' method");
		
		params.put("tipoValidacion", "1");		
		String[] aux = (String[]) params.get("listaNombresArchivos");
		
		String [] aux1 = aux[0].split("\\_");
		params.put("numeroLoteArch", aux1[1].substring(0, aux1[1].length()-4));
		
		params.put("codigoPeriodo", "");
		params.put("codigoCatalogo", "");
		
		interfazPREService.executeValidacionMatrizPlanit(params);
	}
	
	@Override
	protected void afterExecuteInterfaz(Map params) throws Exception 
	{
		super.afterExecuteInterfaz(params);
		
		String[] aux = (String[]) params.get("listaNombresArchivos");
		String nombre = "", errorGeneral = "";		
		String [] aux1 = aux[0].split("\\_");
		boolean errorNoControlado = false;
		
		for (String string : aux) {
			nombre = nombre + string + ";";			
		}
		
		params.put("numeroLoteArch", aux1[1].substring(0, aux1[1].length()-4));
		params.put("tipoValidacion", "2");
		params.put("psnombre", nombre);
		
		params.put("codigoPeriodo", "");
		params.put("codigoCatalogo", "");
		
		try{
			String eliminarArchivos = MapUtils.getString(params, "eliminarArchivos");
			
			if(!StringUtils.equals(eliminarArchivos, Constants.SI)){
				interfazPREService.executeValidacionMatrizPlanit(params);
			}
		}catch(Exception e)
		{
			errorGeneral = e.getMessage();
			params.put("errorNoControlado", errorGeneral);
			errorNoControlado = true;
		}
		
		int cantiError = interfazPREService.verificarErrorMatrizPlanit(params);
		
		if(cantiError == 0 && !errorNoControlado)
		{
			try{
			//interfazPREService.executeInterfazPrCargaMatrizPlan(params);
			}catch (Exception e) {
				errorGeneral = e.getMessage();
				params.put("errorNoControlado", errorGeneral);
			}
		}
	}
}
