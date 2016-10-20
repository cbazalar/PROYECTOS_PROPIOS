package biz.belcorp.ssicc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.GenerarLiderDAO;
import biz.belcorp.ssicc.service.GenerarLiderService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

@Service("generarLiderService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class GenerarLiderServiceImpl extends BaseService implements
		GenerarLiderService {

	@Resource(name="sisicc.generarLiderDAO")	
	GenerarLiderDAO generarLiderDAO;

	public List getLideresGeneradosByCriteria(Map criteria) {
		List resultado = null;
		try {
			resultado = generarLiderDAO.getLideresGeneradosByCriteria(criteria);
		} catch (Exception e) {
			log.error(e);
		}
		return resultado;
	}

	public void insertLideresGenerados(Map criteria, Usuario usuario) {
		generarLiderDAO.insertLideresGenerados(criteria, usuario);
	}

	

}
