package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOMotivoRechazoDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.MotivoRechazo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOMotivoRechazoService;

/**
 * Service con metodos para las consultas invocados por la pantalla de Mantenimiento de Motivos de Rechazo
 * 
 * <p>
 * <a href="MantenimientoSTOMotivoRechazoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href=""> </a>
 * 
 */
@Service("spusicc.mantenimientoSTOMotivoRechazoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOMotivoRechazoServiceImpl extends BaseService implements MantenimientoSTOMotivoRechazoService
{
	@Resource(name="spusicc.mantenimientoSTOMotivoRechazoDAO")
	MantenimientoSTOMotivoRechazoDAO mantenimientoSTOMotivoRechazoDAO; 

	@Override
	public List getMotivoRechazoList(Map criteria) {
		return mantenimientoSTOMotivoRechazoDAO.getMotivoRechazoList(criteria);
	}

	@Override
	public void insertMotivoRechazo(MotivoRechazo rechazo, Usuario usuario) {
		mantenimientoSTOMotivoRechazoDAO.insertMotivoRechazo(rechazo, usuario);
	}

	@Override
	public void updateMotivoRechazo(Map criteria, Usuario usuario) {
		mantenimientoSTOMotivoRechazoDAO.updateMotivoRechazo(criteria, usuario);
	}

	@Override
	public void deleteMotivoRechazo(Map criteria) {
		mantenimientoSTOMotivoRechazoDAO.deleteMotivoRechazo(criteria);		
	}

	@Override
	public MotivoRechazo getMotivoRechazo(Map criteria) {
		return mantenimientoSTOMotivoRechazoDAO.getMotivoRechazo(criteria);
	}
}
