package biz.belcorp.ssicc.service.spusicc.sto.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOParametroValidacionDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ParametroValidacion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOParametroValidacionService;


/**
 * Service con metodos para las consultas invocados por la pantalla de Parametro Validacion
 * 
 * <p>
 * <a href="MantenimientoSTOParametroValidacionServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Yahir Rivas Luna</a>
 * 
 */
@Service("spusicc.mantenimientoSTOParametroValidacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOParametroValidacionServiceImpl extends BaseService implements MantenimientoSTOParametroValidacionService {


	@Resource(name="spusicc.mantenimientoSTOParametroValidacionDAO")
	MantenimientoSTOParametroValidacionDAO mantenimientoSTOParametroValidacionDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOParametroValidacionDAO#deleteParametroValidacion(biz.belcorp.ssicc.spusicc.sto.model.ParametroValidacion)
	 */
	public void deleteParametroValidacion(
			ParametroValidacion parametroValidacion) {
		mantenimientoSTOParametroValidacionDAO.insertHistoricoParametroValidacion(parametroValidacion);
		mantenimientoSTOParametroValidacionDAO.deleteParametroValidacion(parametroValidacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOParametroValidacionDAO#getParametroValidacion(biz.belcorp.ssicc.spusicc.sto.model.ParametroValidacion)
	 */
	public List getParametroValidacion(ParametroValidacion parametroValidacion) {
        return mantenimientoSTOParametroValidacionDAO.getParametroValidacion(parametroValidacion);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOParametroValidacionDAO#insertParametrValidacion(biz.belcorp.ssicc.spusicc.sto.model.ParametroValidacion)
	 */
	public void insertParametrValidacion(ParametroValidacion parametroValidacion) {
		mantenimientoSTOParametroValidacionDAO.insertParametrValidacion(parametroValidacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.MantenimientoSTOParametroValidacionDAO#updateParametroValidacion(biz.belcorp.ssicc.spusicc.sto.model.ParametroValidacion)
	 */
	public void updateParametroValidacion(
			ParametroValidacion parametroValidacion) {
		mantenimientoSTOParametroValidacionDAO.updateParametroValidacion(parametroValidacion);
	}

}