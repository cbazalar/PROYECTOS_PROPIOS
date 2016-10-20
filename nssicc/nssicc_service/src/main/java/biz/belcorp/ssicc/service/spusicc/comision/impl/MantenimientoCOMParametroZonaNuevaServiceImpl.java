/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMParametroZonaNuevaDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ParametroZonaNueva;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMParametroZonaNuevaService;

/**
 * @author <a href="mailto:ttataje@csigcomt.com">Telly Tataje Tirado</a>
 *
 */
@Service("spusicc.mantenimientoCOMParametroZonaNuevaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCOMParametroZonaNuevaServiceImpl extends BaseService 
    implements MantenimientoCOMParametroZonaNuevaService {
	
	@Resource(name="spusicc.mantenimientoCOMParametroZonaNuevaDAO")
	MantenimientoCOMParametroZonaNuevaDAO mantenimientoCOMParametroZonaNuevaDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMParametroZonaNuevaService#getListaParametroZonaNueva(biz.belcorp.ssicc.spusicc.comision.dao.model.ParametroZonaNueva)
	 */
	public List getListaParametroZonaNueva(ParametroZonaNueva bean) {
		return this.mantenimientoCOMParametroZonaNuevaDAO.getListaParametroZonaNueva(bean);
	}
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMParametroZonaNuevaService#getParametroZonaNueva(biz.belcorp.ssicc.spusicc.comision.dao.model.ParametroZonaNueva)
	 */
	public ParametroZonaNueva getParametroZonaNueva(ParametroZonaNueva bean) {
		return this.mantenimientoCOMParametroZonaNuevaDAO.getParametroZonaNueva(bean);
	}



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#updateComisionPeriodoGerenteZona(biz.belcorp.ssicc.spusicc.comision.dao.model.ComisionPeriodoGerenteZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateParametroZonaNueva(ParametroZonaNueva bean, Usuario usuario) {
		this.mantenimientoCOMParametroZonaNuevaDAO.updateParametroZonaNueva(bean, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMParametroZonaNuevaService#insertParametroZonaNueva(biz.belcorp.ssicc.spusicc.comision.dao.model.ParametroZonaNueva, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertParametroZonaNueva(ParametroZonaNueva bean,Usuario usuario) {
		this.mantenimientoCOMParametroZonaNuevaDAO.insertParametroZonaNueva(bean, usuario);
	}
}