package biz.belcorp.ssicc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.OpcionDAO;
import biz.belcorp.ssicc.dao.model.Opcion;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.OpcionService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

@Service("opcionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class OpcionServiceImpl extends BaseService implements OpcionService {

	@Resource(name="opcionDAO")
	private OpcionDAO opcionDAO;
	
	public List getOpcionesByCriteria(Opcion criteria){
		return opcionDAO.getOpcionesByCriteria(criteria);
	}
	
	public void insertOpcion(Opcion opcion, Usuario usuario){
		
		opcionDAO.insertOpcion(opcion,usuario);
	}
	
	public void updateOpcion(Opcion opcion, Usuario usuario){
		opcionDAO.updateOpcion(opcion,usuario);
	}
	
	public void deleteOpcion(final String codigo){
		opcionDAO.deleteOpcion(codigo);
	}
	
}
