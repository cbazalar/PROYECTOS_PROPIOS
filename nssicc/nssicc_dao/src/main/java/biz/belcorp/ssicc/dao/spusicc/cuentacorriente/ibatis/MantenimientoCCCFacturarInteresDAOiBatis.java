package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCFacturarInteresDAO;


/**
 * Implementacion del DAO que la facturazion de intereses
 * <p>
 * <a href="MantenimientoCCCFacturarInteresDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:dtorres@sigcomt.com">Diego Torres Loyola</a>
 */
@Repository("spusicc.mantenimientoCCCFacturarInteresDAO")
public class MantenimientoCCCFacturarInteresDAOiBatis extends BaseDAOiBatis implements MantenimientoCCCFacturarInteresDAO {
    	

	public List getConsoCalcuInteMoralList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getConsoCalcuInteMoralList", criteria);
	}

	public String obtenerPathUpload(String codigoPais) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getPathUploadFacturarInteres", codigoPais);	
	}

	public void deleteConsolidadoInterMoraCCC(String codigoUsuario) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.deleteConsolidadoInterMoraCCC", codigoUsuario);
	}
	
	public void insertConsolidadoInterMoraCCC(Map params) {
		getSqlMapClientTemplate().insert("spusicc.cuentacorriente.procesosCCCSQL.insertConsolidadoInterMoraCCC", params);
	}

	public void executeValidarCargaConsolidadoInterMoraCCC(String codigoUsuario) {
		Map params = new HashMap();
		params.put("codigoUsuario", codigoUsuario);
		
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeValidarCargaConsolidadoInterMoraCCC", params);
		
	}
	
	public List getCargarConsolidadoInterMoraCCC(String codigoUsuario) {
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getCargarConsolidadoInterMoraCCC", codigoUsuario);
	}
	
	public void executeProcesarCargaFactuInterMora(String codigoUsuario) {
		Map params = new HashMap();
		params.put("codigoUsuario", codigoUsuario);
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeProcesarCargaFactuInterMora", params);
	}
}
