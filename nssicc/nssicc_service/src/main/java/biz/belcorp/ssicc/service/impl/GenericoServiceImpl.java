package biz.belcorp.ssicc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * <p>
 * <a href="GenericoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Cairampoma</a>
 */
@Service("genericoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class GenericoServiceImpl extends BaseService implements GenericoService {

	@Resource(name="genericoDAO")	
	private GenericoDAO genericoDAO;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.GenericoService#getParametrosPais(biz.belcorp
	 * .ssicc.model.ParametroPais)
	 */
	public List getParametrosPais(ParametroPais parametroPais) {
		return genericoDAO.getParametrosPais(parametroPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.GenericoService#getParametroPais(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	public String getParametroPais(String codigoPais, String codigoSistema,String codigoParametro) {
		
		return genericoDAO.getParametroPais(codigoPais, codigoSistema,
				codigoParametro);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.GenericoService#getPeriodoNSiguiente(Map)
	 */
	public String getPeriodoNSiguiente(Map criteria) {
		return genericoDAO.getPeriodoNSiguiente(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.GenericoService#getPeriodoByFecha(Map)
	 */
	public String getPeriodoByFecha(Map criteria) {

		return genericoDAO.getPeriodoByFecha(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.GenericoService#executeAuditMenu(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void executeAuditMenu(String codigoPais,String codigoMenu, String url, String login,String ip) {
		if(StringUtils.isNotEmpty(codigoMenu))
	        genericoDAO.executeAuditMenu(codigoPais,codigoMenu,url,login,ip);
	}
	

	public boolean getValidacionDentroRangoPeriodoVigente(Map criteria) {
		return genericoDAO.getValidacionDentroRangoPeriodoVigente(criteria);
	}

}
