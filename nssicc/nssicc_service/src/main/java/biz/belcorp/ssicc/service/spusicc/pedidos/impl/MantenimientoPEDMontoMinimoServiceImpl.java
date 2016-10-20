package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDMontoMinimoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MontoMinimo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDMontoMinimoService;


/**
 * @author Jose Luis Rodriguez
 */

@Service("spusicc.mantenimientoPEDMontoMinimoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDMontoMinimoServiceImpl extends BaseService implements MantenimientoPEDMontoMinimoService{

	@Resource(name="spusicc.mantenimientoPEDMontoMinimoDAO")
	MantenimientoPEDMontoMinimoDAO mantenimientoPEDMontoMinimoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMinimoService#getTipoSolicitud()
	 */
	public List getTipoSolicitud() {
		return mantenimientoPEDMontoMinimoDAO.getTipoSolicitud();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMinimoService#getMontominimoList(java.util.Map)
	 */
	public List getMontominimoList(Map criteria){
		return mantenimientoPEDMontoMinimoDAO.getMontominimoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMinimoService#getMontoMinimoObject(java.util.Map)
	 */
	public MontoMinimo getMontoMinimoObject(Map criteria){
		return mantenimientoPEDMontoMinimoDAO.getMontoMinimoObject(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMinimoService#insertMontoMinimo(java.util.Map)
	 */
	public void insertMontoMinimo(Map criteria){
		mantenimientoPEDMontoMinimoDAO.insertMontoMinimo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMinimoService#updateMontoMinimo(java.util.Map)
	 */
	public void updateMontoMinimo(Map criteria){
		mantenimientoPEDMontoMinimoDAO.updateMontoMinimo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMinimoService#deleteMontoMinimo(java.util.Map, java.lang.String[])
	 */
	public void deleteMontoMinimo(Map criteria, String[] items) {

		for(int i = 0; i < items.length; i++){

			String id = items[i];

			criteria.put("oidMontoMinimo", StringUtils.split(id, "|")[0]);

			//insertando en la tabla de auditoria
			MontoMinimo montoMinimo = mantenimientoPEDMontoMinimoDAO.getMontoMinimoObject(criteria);
			
			criteria.put("oidTipoSolicitud", montoMinimo.getOidTipoSolicitud());
			criteria.put("oidTipoCliente", montoMinimo.getOidTipoCliente());
			criteria.put("oidSubTipoCliente", montoMinimo.getOidSubTipoCliente());
			criteria.put("oidTipoClasificacion", montoMinimo.getOidTipoClasificacion());
			criteria.put("oidClasificacion", montoMinimo.getOidClasificacion());
			criteria.put("nivel1", montoMinimo.getNivel1());
			criteria.put("nivel2", montoMinimo.getNivel2());
			criteria.put("nivel3", montoMinimo.getNivel3());
			criteria.put("recargo", montoMinimo.getRecargo());
			criteria.put("nominal", montoMinimo.getNominal());
			criteria.put("codigoRegion", montoMinimo.getCodigoRegion());
			criteria.put("codigoZona", montoMinimo.getCodigoZona());
			criteria.put("oidRegion", montoMinimo.getOidRegion());
			criteria.put("oidZona", montoMinimo.getOidZona());

			//Insettando en la tabla de auditoria
			criteria.put("codigoAccion", Constants.PED_MOTO_MINIMO_ACCION_ELIMINACION);
			criteria.put("codigoStatusRegistro", Constants.PED_MOTO_MINIMO_STATUS_ANTES);			
			mantenimientoPEDMontoMinimoDAO.insertAuditoriaMontoMinimo(criteria);
			
			//Eliminando
			mantenimientoPEDMontoMinimoDAO.deleteMontoMinimo(criteria);

		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMinimoService#getIndicadorActualizaMontominimo(java.util.Map)
	 */
	public String getIndicadorActualizaMontominimo(Map criteria){
		return mantenimientoPEDMontoMinimoDAO.getIndicadorActualizaMontominimo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMinimoService#updateErrorMontoMinimo(java.util.Map)
	 */
	public void updateErrorMontoMinimo(Map criteria){
		mantenimientoPEDMontoMinimoDAO.updateErrorMontoMinimo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMinimoService#insertAuditoriaMontoMinimo(java.util.Map)
	 */
	public void insertAuditoriaMontoMinimo(Map criteria){
		mantenimientoPEDMontoMinimoDAO.insertAuditoriaMontoMinimo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMinimoService#getNextOidMontoMinimo(java.util.Map)
	 */
	public int getNextOidMontoMinimo(Map criteria){
		return mantenimientoPEDMontoMinimoDAO.getNextOidMontoMinimo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMinimoService#getMontominimoHistoricoList(java.util.Map)
	 */
	public List getMontominimoHistoricoList(Map criteria) {
		return mantenimientoPEDMontoMinimoDAO.getMontominimoHistoricoList(criteria);
	}
}