package biz.belcorp.ssicc.service.spusicc.pre.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREEstimadosDAO;
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREMatrizAlternativosDAO;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativo;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativoAuditoria;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizRecuperacion;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPREEstimadosService;
import biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPREMatrizAlternativosService;

/**
 * 
 * @author Sigcomt
 *
 */
@Service("spusicc.mantenimientoPREEstimadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPREEstimadosServiceImpl extends BaseService implements  MantenimientoPREEstimadosService{

	@Resource(name="spusicc.mantenimientoPREEstimadosDAO")
	MantenimientoPREEstimadosDAO mantenimientoPREEstimadosDAO;

	@Override
	public List getManPREEstimadosList(Map param) {
	
		return mantenimientoPREEstimadosDAO.getManPREEstimadosList(param);
	}

	@Override
	public void deleteManPREEstimados(Map param) {
		
		mantenimientoPREEstimadosDAO.deleteManPREEstimados(param);
	}

	@Override
	public List getManPREEstimadosCatalogoList() {
	
		return mantenimientoPREEstimadosDAO.getManPREEstimadosCatalogoList();
	}

	
	

	
}






















