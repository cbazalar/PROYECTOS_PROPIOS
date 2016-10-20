package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOLimiteVentaFocalizadoConsejeraService;

/**
 * @author Jesse James Rios Franco
 */
@Service("spusicc.mantenimientoSTOLimiteVentaFocalizadoConsejeraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOLimiteVentaFocalizadoConsejeraServiceImpl extends BaseService implements MantenimientoSTOLimiteVentaFocalizadoConsejeraService{
	
	@Resource(name="spusicc.mantenimientoSTOLimiteVentaFocalizadoConsejeraDAO")
	private MantenimientoSTOLimiteVentaFocalizadoConsejeraDAO mantenimientoSTOLimiteVentaFocalizadoConsejeraDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOLimiteVentaFocalizadoConsejeraService#deleteLimiteVentaFocalizadoConsejera(java.util.Map)
	 */
	public void deleteLimiteVentaFocalizadoConsejera(Map parametros) {
		
		String[] selectedItems = (String[])parametros.get("idSeleccionados");
		String usuario = (String )parametros.get("usuario");
		
		Map criteria = new HashMap();
		
		for(int i=0;i<selectedItems.length;i++){
			
			String codigoPais = selectedItems[i].split("-")[0];
			String codigoPeriodo = selectedItems[i].split("-")[1];
			String codigoCliente = selectedItems[i].split("-")[2];
			
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoCliente", codigoCliente);
			criteria.put("usuario", usuario);
			
			mantenimientoSTOLimiteVentaFocalizadoConsejeraDAO.deleteLimiteVentaFocalizadoConsejera(criteria);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOLimiteVentaFocalizadoConsejeraService#getLimiteVentaFocalizadoConsejeraList(java.util.Map)
	 */
	public List getLimiteVentaFocalizadoConsejeraList(Map criteria) {
		return mantenimientoSTOLimiteVentaFocalizadoConsejeraDAO.getLimiteVentaFocalizadoConsejeraList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOLimiteVentaFocalizadoConsejeraService#insertSTOLimiteVentaFocalizadoConsejera(java.util.Map)
	 */
	public int insertSTOLimiteVentaFocalizadoConsejera(Map criteria) {
		List l = new ArrayList();
		l = (List)criteria.get("clienteList");
		int cont = 0;
		
		for (int i = 0; i < l.size(); i++) {
			criteria.put("codigoCliente", l.get(i));
			
			Map resultado = mantenimientoSTOLimiteVentaFocalizadoConsejeraDAO.getExisteSTOLimiteVentaFocalizadoConsejera(criteria);
			
			if(resultado != null && resultado.get("contador") != null && ((BigDecimal)resultado.get("contador")).intValue() > 0){
				if(resultado.get("estadoRegistro") != null && ((String)resultado.get("estadoRegistro")).equals(Constants.STO_LIMITE_VENTA_FOCALIZADO_CONSEJERA_REG_ANULADO)){
					mantenimientoSTOLimiteVentaFocalizadoConsejeraDAO.updateSTOLimiteVentaFocalizadoConsejeraEstadoUnidadLimite(criteria);
				}
			}else
				mantenimientoSTOLimiteVentaFocalizadoConsejeraDAO.insertSTOLimiteVentaFocalizadoConsejera(criteria);
			
			cont++;
		}
		
		return cont;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOLimiteVentaFocalizadoConsejeraService#getObjectoSTOLimiteVentaFocalizadoConsejera(java.util.Map)
	 */
	public Map getObjectoSTOLimiteVentaFocalizadoConsejera(Map criteria){
		return mantenimientoSTOLimiteVentaFocalizadoConsejeraDAO.getObjectoSTOLimiteVentaFocalizadoConsejera(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOLimiteVentaFocalizadoConsejeraService#updateObjetoSTOLimiteVentaFocalizadoConsejera(java.util.Map)
	 */
	public void updateObjetoSTOLimiteVentaFocalizadoConsejera(Map criteria) {
		mantenimientoSTOLimiteVentaFocalizadoConsejeraDAO.updateObjetoSTOLimiteVentaFocalizadoConsejera(criteria);
	}
}