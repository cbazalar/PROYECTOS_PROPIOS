package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOFormaPagoClasificacionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOFormaPagoClasificacionService;

/**
 * @author Gonzalo Javier Huertas Agurto
 */
@Service("sto.mantenimientoSTOFormaPagoClasificacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOFormaPagoClasificacionServiceImpl extends BaseService implements MantenimientoSTOFormaPagoClasificacionService{
	
	@Resource(name="spusicc.mantenimientoSTOFormaPagoClasificacionDAO")
	private MantenimientoSTOFormaPagoClasificacionDAO mantenimientoSTOFormaPagoClasificacionDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOFormaPagoClasificacionService#getFormaPagoClasificacionList(java.util.Map)
	 */
	public List getFormaPagoClasificacionList(Map criteria) {
		return mantenimientoSTOFormaPagoClasificacionDAO.getFormaPagoClasificacionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOFormaPagoClasificacionService#deleteFacturaAdicional(java.util.Map)
	 */
	public void deleteFormaPagoClasificacion(Map parametros) {
		
		String[] selectedItems = (String[])parametros.get("idSeleccionados");
		String usuario = (String)parametros.get("usuario");
		
		Map map = new HashMap();
		map.put("codigoPais", parametros.get("codPais").toString());
	
		Map criteria = new HashMap();
		
		for (int i = 0; i < selectedItems.length; i++) {
			String registro = selectedItems[i];
			String[] valores = registro.split("\\|");
			
			criteria.put("oid", valores[0]);
			criteria.put("usuario", usuario);
						
			    mantenimientoSTOFormaPagoClasificacionDAO.deleteFormaPagoClasificacion(criteria);
			
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOFormaPagoClasificacionService#insertFacturaAdicional(java.util.Map)
	 */
	public void insertFormaPagoClasificacion(Map criteria) {
		mantenimientoSTOFormaPagoClasificacionDAO.insertFormaPagoClasificacion(criteria);
	}	
}