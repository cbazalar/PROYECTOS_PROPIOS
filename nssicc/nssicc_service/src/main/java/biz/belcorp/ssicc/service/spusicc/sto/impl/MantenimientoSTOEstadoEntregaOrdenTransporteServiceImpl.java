package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOEstadoEntregaOrdenTransporteDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOEstadoEntregaOrdenTransporteService;

/**
 * @author peextdoliva
 */
@Service("spusicc.mantenimientoSTOEstadoEntregaOrdenTransporteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOEstadoEntregaOrdenTransporteServiceImpl extends BaseService implements MantenimientoSTOEstadoEntregaOrdenTransporteService{
	
	@Resource(name="spusicc.mantenimientoSTOEstadoEntregaOrdenTransporteDAO")
	private MantenimientoSTOEstadoEntregaOrdenTransporteDAO mantenimientoSTOEstadoEntregaOrdenTransporteDAO;	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOEstadoEntregaOrdenTransporteService#getTiposOrdenTransporte()
	 */
	public List getTiposOrdenTransporte(){
		return mantenimientoSTOEstadoEntregaOrdenTransporteDAO.getTiposOrdenTransporte();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOEstadoEntregaOrdenTransporteService#getEstadosOrdenTransporte(java.util.Map)
	 */
	public List getEstadosOrdenTransporte(Map criteria){
		return mantenimientoSTOEstadoEntregaOrdenTransporteDAO.getEstadosOrdenTransporte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOEstadoEntregaOrdenTransporteService#insertEstadoOrdenTransporte(java.util.Map)
	 */
	public void insertEstadoOrdenTransporte(Map criteria) {
		mantenimientoSTOEstadoEntregaOrdenTransporteDAO.insertEstadoOrdenTransporte(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOEstadoEntregaOrdenTransporteService#updateEstadoOrdenTransporte(java.util.Map)
	 */
	public void updateEstadoOrdenTransporte(Map criteria){
		mantenimientoSTOEstadoEntregaOrdenTransporteDAO.updateEstadoOrdenTransporte(criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOEstadoEntregaOrdenTransporteService#deleteEstadoOrdenTransporte(java.lang.String[])
	 */
	public void deleteEstadoOrdenTransporte(String[] items){		
		Map criteria = new HashMap();
		for(int i = 0; i < items.length; i++){			
			String id = items[i];			
			criteria.put("codigoTipoOrdenTransporte", StringUtils.split(id, "|")[0]);
			criteria.put("estadoEntrega", StringUtils.split(id, "|")[1]);
			mantenimientoSTOEstadoEntregaOrdenTransporteDAO.deleteEstadoOrdenTransporte(criteria);
		}
	}		
	
}