package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCalcularResultadosMasivosDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

@Service("spusicc.procesoLECCalcularResultadosMasivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLECCalcularResultadosMasivoServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name="spusicc.procesoLECCalcularResultadosMasivosDAO")
	private ProcesoLECCalcularResultadosMasivosDAO procesoLECCalcularResultadosMasivosDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		log.debug(">> ProcesoLECCalcularResultadosMasivoServiceImpl.executeStoreProcedure");
		String tipoProceso = (String) params.get("tipoProceso");
		log.debug(">>tipoProceso: "+tipoProceso);
		
		if(tipoProceso == null){
			new InterfazException(">>La variable tipoProceso no se cargo de session correctamente.");
		}else{
			if(tipoProceso.equals("R")){
				String[] codigoRegiones = (String[])params.get("codigoRegionList");
				for(int i=0; i<codigoRegiones.length; i++){
					params.put("codigoRegion", codigoRegiones[i]);
					procesoLECCalcularResultadosMasivosDAO.executeProcesoLECCalcularResultadosMasivos(params);					
				}
			}else{
				params.put("codigoRegion", "");
				procesoLECCalcularResultadosMasivosDAO.executeProcesoLECCalcularResultadosMasivos(params);				
			}
		}
	}


	public ProcesoLECCalcularResultadosMasivosDAO getProcesoLECCalcularResultadosMasivosDAO() {
		return procesoLECCalcularResultadosMasivosDAO;
	}


	public void setProcesoLECCalcularResultadosMasivosDAO(
			ProcesoLECCalcularResultadosMasivosDAO procesoLECCalcularResultadosMasivosDAO) {
		this.procesoLECCalcularResultadosMasivosDAO = procesoLECCalcularResultadosMasivosDAO;
	}
}
