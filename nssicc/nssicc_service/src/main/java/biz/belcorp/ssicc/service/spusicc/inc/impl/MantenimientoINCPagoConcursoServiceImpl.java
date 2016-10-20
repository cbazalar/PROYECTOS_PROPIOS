package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCPagoConcursoDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.model.BonoPremioPago;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCPagoConcursoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoINCPagoConcursoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoINCPagoConcursoServiceImpl extends BaseService implements
	MantenimientoINCPagoConcursoService {

	@Resource(name="spusicc.mantenimientoINCPagoConcursoDAO")
	MantenimientoINCPagoConcursoDAO mantenimientoINCPagoConcursoDAO;
	
	public void setMantenimientoINCPagoConcursoDAO(
			MantenimientoINCPagoConcursoDAO mantenimientoINCPagoConcursoDAO) {
		this.mantenimientoINCPagoConcursoDAO = mantenimientoINCPagoConcursoDAO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPagoConcursoService#getListPagoConcurso(java.util.Map)
	 */
	public List getListPagoConcurso(Map criteria) {
		return mantenimientoINCPagoConcursoDAO.getListPagoConcurso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPagoConcursoService#getMaxPagoConcurso()
	 */
	public String getMaxPagoConcurso() {
		return mantenimientoINCPagoConcursoDAO.getMaxPagoConcurso();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPagoConcursoService#insertPagoConcurso(java.util.Map)
	 */
	public void insertPagoConcurso(Map params) {
		String codigoPago = mantenimientoINCPagoConcursoDAO.getMaxPagoConcurso();
		params.put("codigoPago", codigoPago);
		
		mantenimientoINCPagoConcursoDAO.insertPagoConcurso(params);
		
		List listBonoPremio = (List)params.get("listBonoPremio");
		for(int i=0; i<listBonoPremio.size(); i++) {
			BonoPremioPago bonoPremio = (BonoPremioPago)listBonoPremio.get(i);
			
			if("true".equals(bonoPremio.getIndicadorBonoCCC()) || "true".equals(bonoPremio.getIndicadorBonoBanco())) {
				bonoPremio.setCodigoPais(params.get("codigoPais").toString());
				bonoPremio.setCodigoPago(codigoPago);
				bonoPremio.setNumeroConcurso(params.get("numeroConcurso").toString());
				bonoPremio.setCodigoUsuario(params.get("codigoUsuario").toString());
				
				if("true".equals(bonoPremio.getIndicadorBonoCCC())) //Cuenta Corriente
					bonoPremio.setTipoAbono("001");
				if("true".equals(bonoPremio.getIndicadorBonoBanco())) //Abono Banco
					bonoPremio.setTipoAbono("002");
		
				mantenimientoINCPagoConcursoDAO.insertPagoBonoPremio(bonoPremio);
			}	
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPagoConcursoService#updatePagoConcurso(java.util.Map)
	 */
	public void updatePagoConcurso(Map params) {
		mantenimientoINCPagoConcursoDAO.updatePagoConcurso(params);
		
		String indActualizarBonoPremio = (String)params.get("indActualizarBonoPremio");
		if("true".equals(indActualizarBonoPremio)) {
			mantenimientoINCPagoConcursoDAO.deletePagoBonoPremio(params);
			
			List listBonoPremio = (List)params.get("listBonoPremio");
			for(int i=0; i<listBonoPremio.size(); i++) {
				BonoPremioPago bonoPremio = (BonoPremioPago)listBonoPremio.get(i);
				
				if("true".equals(bonoPremio.getIndicadorBonoCCC()) || "true".equals(bonoPremio.getIndicadorBonoBanco())) {
					bonoPremio.setCodigoPais(params.get("codigoPais").toString());
					bonoPremio.setCodigoPago(params.get("codigoPago").toString());
					bonoPremio.setNumeroConcurso(params.get("numeroConcurso").toString());
					bonoPremio.setCodigoUsuario(params.get("codigoUsuario").toString());
					
					if("true".equals(bonoPremio.getIndicadorBonoCCC())) //Cuenta Corriente
						bonoPremio.setTipoAbono("001");
					if("true".equals(bonoPremio.getIndicadorBonoBanco())) //Abono Banco
						bonoPremio.setTipoAbono("002");
					
					mantenimientoINCPagoConcursoDAO.insertPagoBonoPremio(bonoPremio);
				}	
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPagoConcursoService#deletePagoConcurso(java.util.Map)
	 */
	public void deletePagoConcurso(Map params) {
		mantenimientoINCPagoConcursoDAO.deletePagoBonoPremio(params);
		mantenimientoINCPagoConcursoDAO.deletePagoConcurso(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPagoConcursoService#cerrarPagoConcurso(java.util.Map)
	 */
	public void cerrarPagoConcurso(Map params) {
		mantenimientoINCPagoConcursoDAO.cerrarPagoConcurso(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPagoConcursoService#getListClasificacionesPagoConcurso(java.util.Map)
	 */
	public List getListClasificacionesPagoConcurso(Map criteria) {
		return mantenimientoINCPagoConcursoDAO.getListClasificacionesPagoConcurso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPagoConcursoService#getPeriodosDespachoConcurso(java.util.Map)
	 */
	public Map getPeriodosDespachoConcurso(Map criteria) {
		return mantenimientoINCPagoConcursoDAO.getPeriodosDespachoConcurso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPagoConcursoService#getOidPeriodoByCodigoPeriodo(java.lang.String)
	 */
	public String getOidPeriodoByCodigoPeriodo(String codigoPeriodo) {
		return mantenimientoINCPagoConcursoDAO.getOidPeriodoByCodigoPeriodo(codigoPeriodo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPagoConcursoService#getListPagoBonoPremio(java.util.Map)
	 */
	public List getListPagoBonoPremio(Map criteria) {
		return mantenimientoINCPagoConcursoDAO.getListPagoBonoPremio(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPagoConcursoService#getListConcursosPago(java.util.Map)
	 */
	public List getListConcursosPago(Map criteria) {
		return mantenimientoINCPagoConcursoDAO.getListConcursosPago(criteria);
	}
	
}
