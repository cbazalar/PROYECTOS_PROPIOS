package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCalcularNivelExitoMasivoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;


/**
 * <p>
 * <a href="ProcesoLECCalcularNivelExitoMasivoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Yahir Rivas L.
 *         
 */
@Service("spusicc.procesoLECCalcularNivelExitoMasivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLECCalcularNivelExitoMasivoServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoLECCalcularNivelExitoMasivoDAO")
	private ProcesoLECCalcularNivelExitoMasivoDAO procesoLECCalcularNivelExitoMasivoDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		log.debug(">> ProcesoLECCalcularNivelExitoMasivoServiceImpl.executeStoreProcedure");
		String []codigoRegiones = (String[])params.get("codigoRegionList");
		String tipoProceso = (String) params.get("tipoProceso");
		log.debug(">>tipoProceso: "+tipoProceso);
		if(tipoProceso == null){
			new InterfazException(">>La variable tipoProceso no se cargo de session correctamente. verrificar Parametros del menu : tipoProceso");
			
		}else{
		  if(StringUtils.equals(Constants.CODIGO_TIPO_CIERRE_REGION, tipoProceso)){
			for(int i=0; i<codigoRegiones.length; i++) {
				params.put("codigoRegion", codigoRegiones[i]);
				procesoLECCalcularNivelExitoMasivoDAO.executeProcesoLECCalcularNivelExitoMasivo(params);
			}
		  }else{
			  //es cierre de campanha
			  params.put("codigoRegion", "");
			  procesoLECCalcularNivelExitoMasivoDAO.executeProcesoLECCalcularNivelExitoMasivo(params);
		  }
		}
		
		
		
	}
}