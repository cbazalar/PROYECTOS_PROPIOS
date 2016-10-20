package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoOCRFechaProgramadaFacturacionDAO;

/**
 * @author avillavicencio
 */
@Repository("spusicc.procesoOCRFechaProgramadaFacturacionDAO")
public class ProcesoOCRFechaProgramadaFacturacionDAOiBatis extends BaseDAOiBatis implements ProcesoOCRFechaProgramadaFacturacionDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoOCRFechaProgramadaFacturacionDAO#getTiposSolicitud()
	 */
	public List getTiposSolicitudOcr() {
		return getSqlMapClientTemplate().queryForList("spusicc.pedidos.PedidosSQL.getTiposSolicitudOcr");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoOCRFechaProgramadaFacturacionDAO#getRegistroByPeriodoTipoSoliFecha(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Integer getRegistroByPeriodoTipoSoliFecha(String periodo,String tipoSolicitud, String fecha, String grupoProceso) {

		log.debug("Entro al getRegistroByPeriodoTipoSoliFecha dao");
		Map parametros = new HashMap();
		Integer resultado = 0;

		parametros.put("periodo", periodo);
		parametros.put("tipoSolicitud", tipoSolicitud);
		parametros.put("fecha", fecha);

		if (grupoProceso.equals(Constants.NUMERO_CERO)){
			resultado = (Integer)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getRegistroByPeriodoTipoSoliFecha", parametros);
		}
		else{
			parametros.put("grupoProceso",grupoProceso);
			resultado = (Integer)getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getRegistroByPeriodoTipoSoliFecha2", parametros);
		}

		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoOCRFechaProgramadaFacturacionDAO#updateFechaProgramada(java.util.Map)
	 */
	public void updateFechaProgramada(Map parametros) {
		
		String metodo = (String)parametros.get("updateFecha");
		
		getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL."+metodo, parametros);
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoOCRFechaProgramadaFacturacionDAO#getNomMetodoProcFecha(java.lang.String)
	 */
	public String getNomMetodoProcFecha(Map parametros) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.pedidos.PedidosSQL.getNomMetodoProcFecha", parametros);		
	}
}