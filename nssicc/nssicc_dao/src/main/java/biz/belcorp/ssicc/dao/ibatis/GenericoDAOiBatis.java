package biz.belcorp.ssicc.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.ParametroPais;

@Repository("genericoDAO")
public class GenericoDAOiBatis extends BaseDAOiBatis implements GenericoDAO{


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.GenericoDAO#getParametrosPais(biz.belcorp.ssicc.model.ParametroPais)
	 */
	public List getParametrosPais(ParametroPais parametroPais) {
		
		return getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getParametrosPais", parametroPais);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.GenericoDAO#getParametroPais(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getParametroPais(String codigoPais, String codigoSistema,String codigoParametro) {
		
		ParametroPais parametroPais = new ParametroPais();
        parametroPais.setCodigoPais(codigoPais);
        parametroPais.setCodigoSistema(codigoSistema);        
        parametroPais.setCodigoParametro(codigoParametro);
        
		List listaParametros = getSqlMapClientTemplate().queryForList("sisicc.GenericoSQL.getParametrosPais", parametroPais);
		
		String valorParametro = null;
		if (listaParametros.size()>0) {
        	ParametroPais parametroPaisresult =  (ParametroPais)listaParametros.get(0);
        	valorParametro = parametroPaisresult.getValorParametro();
        }
        
		return valorParametro;
	}
	
	/**
	 * @param criteria
	 * @return
	 */
	public String getPeriodoNSiguiente(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getPeriodoNSiguiente",criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public String getPeriodoByFecha(Map criteria){
		String ret = "";

		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			ret = (String) getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getPeriodoByFechaFox",criteria);
		else	
			ret = (String) getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getPeriodoByFecha",criteria);

		return ret;

	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.GenericoDAO#executeAuditMenu(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void executeAuditMenu(String codigoPais,String codigoMenu, String url, String login,String ip) {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> executeAuditMenu ");
		
		Map map = new HashMap();
		map.put("codigoPais", codigoPais);
		map.put("codigoMenu", codigoMenu);
		map.put("url", url);
		map.put("login", login);
		map.put("ip", ip);
		getSqlMapClientTemplate().update("sisicc.GenericoSQL.executeAuditMenu",map);
		
	}
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.GenericoDAO#getValidacionDentroRangoPeriodoVigente(java.util.Map)
	 */
	public boolean getValidacionDentroRangoPeriodoVigente(Map criteria) {
		boolean valida=false;
		Integer cantidad = (Integer) getSqlMapClientTemplate().queryForObject("sisicc.GenericoSQL.getValidacionDentroRangoPeriodoVigente",criteria);
		if(cantidad.intValue()>0){
			valida=true;
		}
		return valida;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.GenericoDAO#executeProcesoBloqueoEliminacionUsuarioMasiva(java.util.Map)
	 */
	public void executeProcesoBloqueoEliminacionUsuarioMasiva(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.GenericoSQL.executeProcesoBloqueoEliminacionUsuarioMasiva", criteria);
		
	}

	
}
