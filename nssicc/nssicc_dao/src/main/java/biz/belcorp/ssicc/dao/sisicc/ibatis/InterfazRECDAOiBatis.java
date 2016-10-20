/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.sisicc.InterfazRECDAO;

/**
 * 
 * <p>
 * <a href="InterfazRECDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@csigcomt.com">Cristhian Roman</a>
 * 
 */
@Repository("sisicc.interfazRECDAO")
public class InterfazRECDAOiBatis extends BaseDAOiBatis implements	InterfazRECDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazRECDAO#updateInterfazEnvioAlmacenVirtual(java.util.Map)
	 */
	public void updateInterfazEnvioAlmacenVirtual(Map criteria){
		getSqlMapClientTemplate().update("sisicc.InterfazRECSQL.updateInterfazEnvioAlmacenVirtual",criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazRECDAO#updateInterfazEnviarTransferenciaBoletasRecojo(java.util.Map)
	 */
	public void updateInterfazEnviarTransferenciaBoletasRecojo(Map criteria){
		getSqlMapClientTemplate().update("sisicc.InterfazRECSQL.updateInterfazEnviarTransferenciaBoletasRecojo",criteria);
	}
	
	public void insertTablaHistoricaTransferenciaBoletasRecojo(Map criteria){
		getSqlMapClientTemplate().update("sisicc.InterfazRECSQL.insertTablaHistoricaTransferenciaBoletasRecojo",criteria);
	}
	
	public ConexionOCRWrapper getDevuelveConexionOCR(Map params)throws Exception{
		ConexionOCRWrapper conexion = new ConexionOCRWrapper();
		conexion.setCodigoPais(MapUtils.getString(params, "codigoPais"));
		String estado = "";
		
		//Obteniendo el tipo de conexion
		String tipoConexionExterna = Constants.REC_ANULACIONES_PARAMETROS_TIPO_CONEXION_EXTERNA;
		conexion.setTipoConexionExterna(tipoConexionExterna);
		
		//Obteniendo Servidor
		params.put("nombreParametro", Constants.REC_ANULACIONES_PARAMETROS_SERVIDOR);
		String servidor = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazRECSQL.getParametrosConexionORAREC", params);
		if(StringUtils.isNotBlank(servidor))
			estado= Constants.OK_MESSAGE;
		else
			estado="";

		//Obteniendo Puerto
		params.put("nombreParametro", Constants.REC_ANULACIONES_PARAMETROS_PUERTO);
		String puerto = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazRECSQL.getParametrosConexionORAREC", params);
		if(StringUtils.isNotBlank(puerto))
			estado= Constants.OK_MESSAGE;
		else
			estado="";
		
		//Obteniendo sid
		params.put("nombreParametro", Constants.REC_ANULACIONES_PARAMETROS_SID);
		String sid = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazRECSQL.getParametrosConexionORAREC", params);
		
		String valor = Constants.REC_ANULACIONES_PARAMETROS_URL+servidor + ":" + puerto + ":" + sid;
		
		conexion.setHost(valor);
		
		//Obteniendo usuario de conexion 
		params.put("nombreParametro", Constants.REC_ANULACIONES_PARAMETROS_USUARIO);
		valor = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazRECSQL.getParametrosConexionORAREC", params);
		conexion.setUsuario(valor);
		if(StringUtils.isNotBlank(valor))
			estado= Constants.OK_MESSAGE;
		else
			estado="";
		
		//Obteniendo password de conexion
		params.put("nombreParametro", Constants.REC_ANULACIONES_PARAMETROS_PASSWORD);
		valor = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazRECSQL.getParametrosConexionORAREC", params);
		conexion.setPassword(valor);
		if(StringUtils.isNotBlank(valor))
			estado= Constants.OK_MESSAGE;
		else
			estado="";
		
		params.put("nombreParametro", Constants.REC_ANULACIONES_PARAMETROS_PAIS);
		valor = (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazRECSQL.getParametrosConexionORAREC", params);
		params.put("valPais", valor);
		
		params.put("estadoConexion", estado);
		
		return conexion;
	}
}