package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazACCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazACCRecepcionarRecomendantePremio;

/**
 * Implementacion iBatis del DAO de la Interfaz Aplicativo Call Center.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("sisicc.interfazACCDAO")
public class InterfazACCDAOiBatis extends BaseDAOiBatis implements
		InterfazACCDAO {

	public List getInterfazACCConcursoPremio(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazACCConcursoPremio", params);
	}

    public String getIVRCompania(String codigoPais) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"sisicc.InterfazSQL.getIVRCompania", codigoPais);
    }
    
    public Map validarACCInterfazRecomendantePremio(Map criteria) {
        return (Map) getSqlMapClientTemplate().queryForObject(
        		"sisicc.InterfazSQL.validarACCInterfazRecomendantePremio", criteria);
    }    

    public String getACCLongitudDocumento(String codigoPais, String tipoDocumento) {
    	Map criteria = new HashMap();
    	criteria.put("codigoPais", codigoPais);
    	criteria.put("tipoDocumento", tipoDocumento);
    	
        return (String) getSqlMapClientTemplate().queryForObject(
        		"sisicc.InterfazSQL.getACCLongitudDocumento", criteria);
    }

	public void insertInterfazACCRecepcionarRecomendantePremio(InterfazACCRecepcionarRecomendantePremio detalle) {
		getSqlMapClientTemplate().insert("sisicc.InterfazSQL.insertInterfazACCRecepcionarRecomendantePremio", detalle);
	}

    public Map procesarInterfazACCActualizaConcursoRecomendacion(Map criteria) {
        return (Map) getSqlMapClientTemplate().queryForObject(
        		"sisicc.InterfazSQL.procesarInterfazACCActualizaConcursoRecomendacion", criteria);
    }

	public void executeInterfazACCEnviarTablasClientes(Map params) {
		getSqlMapClientTemplate().queryForList("sisicc.InterfazSQL.executeInterfazACCEnviarTablasClientes", params);
	}   

	public void executeInterfazACCEnviarTablasClientesAct(Map params) {
		getSqlMapClientTemplate().queryForList("sisicc.InterfazSQL.executeInterfazACCEnviarTablasClientesAct", params);
	}
	
	public void executeInterfazACCEnviarTablasCDR(Map params) {
		getSqlMapClientTemplate().queryForList("sisicc.InterfazSQL.executeInterfazACCEnviarTablasCDR", params);
	}   

	public void executeInterfazACCRecepcionarRecomendantePremio(Map parametroMap) {
		getSqlMapClientTemplate().insert("sisicc.InterfazSQL.executeInterfazACCRecepcionarRecomendantePremio", parametroMap);
	}

	public int getCorrelativoACCReferidas() {
		Integer correlativo = (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazSQL.getCorrelativoACCReferidas", null);
		if (correlativo == null) {
			correlativo = 1;
		}
		return  correlativo;
	}

	public void insertReferidas(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.InterfazSQL.insertReferidas", criteria);
	}

}
