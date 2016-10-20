/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.reclamos.ConsultaRECCDRsDigitadosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.ConsultaRECCDRsDigitadosService;


/**
 * @author peextcroman
 *
 */
@Service("spusicc.consultaRECCDRsDigitadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaRECCDRsDigitadosServiceImpl extends BaseService implements ConsultaRECCDRsDigitadosService {
	
	@Resource(name="spusicc.consultaRECCDRsDigitadosDAO")
	ConsultaRECCDRsDigitadosDAO consultaRECCDRsDigitadosDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ConsultaRECCDRsDigitadosService#getListaCabeceras(java.util.Map)
	 */
	public List getListaCabeceras(Map params){
		return consultaRECCDRsDigitadosDAO.getListaCabeceras(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ConsultaRECCDRsDigitadosService#getListaDetalles(java.util.Map)
	 */
	public List getListaDetalles(Map params){
		return consultaRECCDRsDigitadosDAO.getListaDetalles(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ConsultaRECCDRsDigitadosService#deleteDetallesDigitados(java.util.Map)
	 */
	public void deleteDetallesDigitados(Map params){
		consultaRECCDRsDigitadosDAO.deleteDetallesDigitados(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ConsultaRECCDRsDigitadosService#deleteCabeceraDigitada(java.util.Map)
	 */
	public void deleteCabeceraDigitada(Map params){
		consultaRECCDRsDigitadosDAO.deleteCabeceraDigitada(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ConsultaRECCDRsDigitadosService#getSecuenciaZonaDiaria()
	 */
	public List getSecuenciaZonaDiaria(){
		return consultaRECCDRsDigitadosDAO.getSecuenciaZonaDiaria();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ConsultaRECCDRsDigitadosService#getListaCabecerasHistorico(java.util.Map)
	 */
	public List getListaCabecerasHistorico(Map params){
		return consultaRECCDRsDigitadosDAO.getListaCabecerasHistorico(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ConsultaRECCDRsDigitadosService#getListaDetallesHistoricos(java.util.Map)
	 */
	public List getListaDetallesHistoricos(Map params){
		return consultaRECCDRsDigitadosDAO.getListaDetallesHistoricos(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ConsultaRECCDRsDigitadosService#deleteDetallesDigitadosHistoricos(java.util.Map)
	 */
	public void deleteDetallesDigitadosHistoricos(Map params){
		consultaRECCDRsDigitadosDAO.deleteDetallesDigitadosHistoricos(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ConsultaRECCDRsDigitadosService#deleteCabeceraDigitadaHistoricos(java.util.Map)
	 */
	public void deleteCabeceraDigitadaHistoricos(Map params){
		consultaRECCDRsDigitadosDAO.deleteCabeceraDigitadaHistoricos(params);
	}
}
