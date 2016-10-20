package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCierreProcesosCampannaDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author <a href="mailto: yrivas@sigcomt.com">Yahir Rivas L.</a>
 *
 */
@Service("spusicc.procesoGENActualizaComisionVentaRetailService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENActualizaComisionVentaRetailServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name = "spusicc.procesoCOMCierreProcesosCampannaDAO")
	private ProcesoCOMCierreProcesosCampannaDAO procesoCOMCierreProcesosCampannaDAO;


	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException,
			Exception {
		
		log.debug(">> ProcesoGENActualizaComisionVentaRetailServiceImpl.executeStoreProcedure");
		String tipoProceso = (String) params.get("tipoProceso");
		log.debug(">>tipoProceso: "+tipoProceso);
		
		if(tipoProceso == null){
			new InterfazException(">>La variable tipoProceso no se cargo de session correctamente.");
		}else{
			if(tipoProceso.equals("R")){
				String[] codigoRegiones = (String[])params.get("codigoRegionList");
				for(int i=0; i<codigoRegiones.length; i++){
					params.put("codigoRegion", codigoRegiones[i]);
					procesoCOMCierreProcesosCampannaDAO.executeCierreProcesosCampanna(params);
								
				}
			}else{
				params.put("codigoRegion", "");
				procesoCOMCierreProcesosCampannaDAO.executeCierreProcesosCampanna(params);						
			}
		}
	}

	
}
