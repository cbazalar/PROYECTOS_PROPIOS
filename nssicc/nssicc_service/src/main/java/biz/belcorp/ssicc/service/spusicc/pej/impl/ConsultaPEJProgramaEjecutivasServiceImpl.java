package biz.belcorp.ssicc.service.spusicc.pej.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pej.ConsultaPEJProgramaEjecutivasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pej.ConsultaPEJProgramaEjecutivasService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.consultaPEJProgramaEjecutivasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaPEJProgramaEjecutivasServiceImpl extends BaseService implements ConsultaPEJProgramaEjecutivasService{
	
	@Resource(name="spusicc.consultaPEJProgramaEjecutivasDAO")
	private ConsultaPEJProgramaEjecutivasDAO consultaPEJProgramaEjecutivasDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getProgramaEjecutivasList(java.util.Map)
	 */
	public List getProgramaEjecutivasList(Map criteria) {
		return consultaPEJProgramaEjecutivasDAO.getProgramaEjecutivasList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getMetaPedido(java.util.Map)
	 */
	public Integer getMetaPedido(Map criteria) {
		return consultaPEJProgramaEjecutivasDAO.getMetaPedido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getDescripcionNivelByEjecuEtap(java.util.Map)
	 */
	public String getDescripcionNivelByEjecuEtap(Map criteria) {
		return consultaPEJProgramaEjecutivasDAO.getDescripcionNivelByEjecuEtap(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getCampanhasByEtapa(java.util.Map)
	 */
	public String[] getCampanhasByEtapa(Map criteria){
		
		String codigoPais = (String)criteria.get("codigoPais");
		String codigoEtapa = (String)criteria.get("codigoEtapa");
		String codigoPeriodo = (String)criteria.get("codigoPeriodo");
		
		Integer cantidadCampanha = this.getCantidadCampanhaEtapa(codigoPais,codigoEtapa);
		
		String[] campanhas = new String[cantidadCampanha];
		
		String campanhaInicial = this.getCampanhaInicialEtapa(codigoPais,codigoEtapa);
		
		String periodoInicial = codigoPeriodo.substring(0, 4) + campanhaInicial;
		
		for(int i = 0 ; i < cantidadCampanha ; i++){
			campanhas[i] = this.getObtienePeriodo(codigoPais,periodoInicial,i);
		}
		
		return campanhas;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getCantidadCampanhaEtapa(java.lang.String, java.lang.String)
	 */
	public Integer getCantidadCampanhaEtapa(String codigoPais,String codigoEtapa) {
		return consultaPEJProgramaEjecutivasDAO.getCantidadCampanhaEtapa(codigoPais,codigoEtapa);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getCampanhaInicialEtapa(java.lang.String, java.lang.String)
	 */
	public String getCampanhaInicialEtapa(String codigoPais, String codigoEtapa) {
		return consultaPEJProgramaEjecutivasDAO.getCampanhaInicialEtapa(codigoPais,codigoEtapa);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getCampanhaFinalEtapa(java.lang.String, java.lang.String)
	 */
	public String getCampanhaFinalEtapa(String codigoPais, String codigoEtapa) {
		return consultaPEJProgramaEjecutivasDAO.getCampanhaFinalEtapa(codigoPais,codigoEtapa);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getObtienePeriodo(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public String getObtienePeriodo(String codigoPais, String periodo,Integer valorRegistro) {
		return consultaPEJProgramaEjecutivasDAO.getObtienePeriodo(codigoPais,periodo,valorRegistro);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getVariablesEjecutivasCampaByPeriodoEjec(java.util.Map)
	 */
	public HashMap getVariablesEjecutivasCampaByPeriodoEjec(Map criteria) {
		return consultaPEJProgramaEjecutivasDAO.getVariablesEjecutivasCampaByPeriodoEjec(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getComisionByEjecutivaPeriodo(java.util.Map)
	 */
	public HashMap getComisionByEjecutivaPeriodo(Map criteria) {
		return consultaPEJProgramaEjecutivasDAO.getComisionByEjecutivaPeriodo(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getMontosBonosEjecAnhioInicEtapa(java.util.Map)
	 */
	public HashMap getMontosBonosEjecAnhioInicEtapa(Map criteria) {
		return consultaPEJProgramaEjecutivasDAO.getMontosBonosEjecAnhioInicEtapa(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ConsultaPEJProgramaEjecutivasService#getPagosEjecEtapaList(java.util.Map)
	 */
	public List getPagosEjecEtapaList(Map criteria) {
		return consultaPEJProgramaEjecutivasDAO.getPagosEjecEtapaList(criteria);
	}
}