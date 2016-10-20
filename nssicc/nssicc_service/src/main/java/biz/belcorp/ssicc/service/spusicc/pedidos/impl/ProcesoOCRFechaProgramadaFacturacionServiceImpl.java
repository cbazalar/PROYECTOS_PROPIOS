package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoOCRFechaProgramadaFacturacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoOCRFechaProgramadaFacturacionService;


/**
 * @author avillavicencio
 */

@Service("spusicc.pedidos.procesoOCRFechaProgramadaFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoOCRFechaProgramadaFacturacionServiceImpl extends BaseService implements ProcesoOCRFechaProgramadaFacturacionService{
	
	@Resource(name="spusicc.procesoOCRFechaProgramadaFacturacionDAO")
	private ProcesoOCRFechaProgramadaFacturacionDAO fechaProgramadaFacturacionDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoOCRFechaProgramadaFacturacionService#getTiposSolicitud()
	 */
	public List getTiposSolicitudOcr() {
		return fechaProgramadaFacturacionDAO.getTiposSolicitudOcr();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoOCRFechaProgramadaFacturacionService#updateFechaProgramada(java.util.Map)
	 */
	public void updateFechaProgramada(Map parametros) {
		fechaProgramadaFacturacionDAO.updateFechaProgramada(parametros);
	}
	
	public void getExeProcFech(Map parametros) {
        String metodo = getNomMetodoProcFecha(parametros);
		
		if(StringUtils.isNotBlank(metodo)){
			parametros.put("updateFecha", metodo);
			updateFechaProgramada(parametros);
		}
		parametros.put("updateFecha", "updateFechaProgramada");
		updateFechaProgramada(parametros);
		
		
	}

	
	/**
	 * @param tipoSolicitud
	 * @return
	 */
	private String getNomMetodoProcFecha(Map parametros) {
		return fechaProgramadaFacturacionDAO.getNomMetodoProcFecha(parametros);
	}

}