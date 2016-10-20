package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEAsignarEstatusClienteDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service que va a Asignar Estatus a Cliente
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoMAEAsignarEstatusClienteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAEAsignarEstatusClienteServiceImpl extends
	BaseInterfazProcesoAbstractService {
	            
	@Resource(name="spusicc.procesoMAEAsignarEstatusClienteDAO")
	private ProcesoMAEAsignarEstatusClienteDAO procesoMAEAsignarEstatusClienteDAO;

	protected void executeStoreProcedure(Map params) {
		String []codigoRegiones = (String[])params.get("codigoRegionList");
		
		for(int i=0; i<codigoRegiones.length; i++) {
			params.put("codigoRegion", codigoRegiones[i]);
			procesoMAEAsignarEstatusClienteDAO.executeAsignarEstatusCliente(params);
		}
	}
	

}
