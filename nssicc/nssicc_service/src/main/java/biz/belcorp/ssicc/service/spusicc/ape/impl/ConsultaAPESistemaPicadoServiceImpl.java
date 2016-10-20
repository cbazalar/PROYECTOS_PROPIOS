package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.ConsultaAPESistemaPicadoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.ConsultaAPESistemaPicadoService;

/**
 *  
 * <p>
 * <a href="ConsultaAPESistemaPicadoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.consultaAPESistemaPicadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaAPESistemaPicadoServiceImpl extends BaseService implements ConsultaAPESistemaPicadoService {

	@Resource(name="spusicc.consultaAPESistemaPicadoDAO")
	private ConsultaAPESistemaPicadoDAO consultaAPESistemaPicadoDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ConsultaAPESistemaPicadoService#getSistemaPicadoLista(java.util.Map)
	*/
	public List getSistemaPicadoLista(Map criteria) {
		return consultaAPESistemaPicadoDAO.getSistemaPicadoLista(criteria);
	}

}
