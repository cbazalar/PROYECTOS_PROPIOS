package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * <p>
 * <a href="ProcesoIMPGeneraBoletaHonorariosServiceImpl.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("sisicc.procesoIMPGeneraBoletaHonorariosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPGeneraBoletaHonorariosServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spisicc.procesoImpresionDAO")
	private ProcesoImpresionDAO procesoImpresionDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		procesoImpresionDAO.executeGeneraBoletaHonorarios(params);
	}

	@Override
	protected Map prepareQueryParams(InterfazParams interfazParams)
			throws InterfazException {
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		
		String fechaFacturacion = (String)queryParams.get("fechaFacturacion");
		
		try {
			fechaFacturacion = DateUtil.formatoString(DateUtil.convertStringToDate("dd/MM/yyyy", fechaFacturacion), "yyyyMMdd")
							+ DateUtil.formatoString(new Date(), "_HHmmss");
		}catch(Exception ex) {}
		
		String nombreArchivo = interfazParams.getInterfaz().getNombreArchivoEntradaSalida()+fechaFacturacion;		
		
		
		
		queryParams.put("nombreArchivo", nombreArchivo);
		queryParams.put("directorio", interfazParams.getInterfaz().getDirectorioEntradaSalida());
        
		return queryParams;
	}
	
}

