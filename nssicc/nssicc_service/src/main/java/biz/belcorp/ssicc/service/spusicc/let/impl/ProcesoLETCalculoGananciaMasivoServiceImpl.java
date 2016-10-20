package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoGananciaMasivoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * <p>
 * <a href="ProcesoLETCalculoGananciaMasivoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Juan Altamirano
 *         
 */
@Service("spusicc.procesoLETCalculoGananciaMasivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETCalculoGananciaMasivoServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name="spusicc.procesoLETCalculoGananciaMasivoDAO")
	private ProcesoLETCalculoGananciaMasivoDAO procesoLETCalculoGananciaMasivoDAO; 

	/**
	 * @param procesoLETCalculoGananciaMasivoDAO the procesoLETCalculoGananciaMasivoDAO to set
	 */
	public void setProcesoLETCalculoGananciaMasivoDAO(
			ProcesoLETCalculoGananciaMasivoDAO procesoLETCalculoGananciaMasivoDAO) {
		this.procesoLETCalculoGananciaMasivoDAO = procesoLETCalculoGananciaMasivoDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		log.debug(">>ProcesoLETCalculoGananciaMasivoServiceImpl.executeStoreProcedure");
		String tipoProceso = (String) params.get("tipoProceso");
		log.debug(">>tipoProceso: "+tipoProceso);
		
		if(tipoProceso == null){
			new InterfazException(">>La variable tipoProceso no se cargo de session correctamente.");
			
		}else{
			if(tipoProceso.equals("R")){
				String[] codigoRegiones = (String[])params.get("codigoRegionList");
				for(int i=0; i<codigoRegiones.length; i++){
					params.put("codigoRegion", codigoRegiones[i]);
					procesoLETCalculoGananciaMasivoDAO.executeProcesoLETCalculoGananciaMasivo(params);
				}
			}else{
				procesoLETCalculoGananciaMasivoDAO.executeProcesoLETCalculoGananciaMasivo(params);
			}
			
		}
		
	}
}