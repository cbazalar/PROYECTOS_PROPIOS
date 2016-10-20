/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoCabecera;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoDetalle;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECIngresoMasivoOperacionesDAO;

/**
 * @author vcupe
 *
 */
@Repository("spusicc.mantenimientoRECIngresoMasivoOperacionesDAO")
public class MantenimientoRECIngresoMasivoOperacionesDAOIbatis extends BaseDAOiBatis implements  MantenimientoRECIngresoMasivoOperacionesDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoMasivoOperacionesDAO#getTipoOperacionList(java.util.Map)
	 */
	public List getTipoOperacionList(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getTipoOperacionList",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoMasivoOperacionesDAO#getObtenerCampahniaActual(java.util.Map)
	 */
	public String getObtenerCampahniaActual(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getObtenerCampahniaActual", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoMasivoOperacionesDAO#getObtenerCampahniaActiva(java.util.Map)
	 */
	public String getObtenerCampahniaActiva(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getObtenerCampahniaActiva", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoMasivoOperacionesDAO#getTipoMasivoOperacionesList()
	 */
	public List getTipoMasivoOperacionesList(){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getTipoMasivoOperacionesList");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionCDRDAO#insertIngresoMasivoOperaciones(biz.belcorp.ssicc.model.ReclamoDigitadoCabecera, java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertIngresoMasivoOperaciones(List cabeceraList, List detalleList, Map params) {		
		String numeroLote = (String)params.get("numLoteSTO");
		
		for (int i = 0; i < cabeceraList.size(); i++) {
			ReclamoDigitadoCabecera reclamoDigitCabec = (ReclamoDigitadoCabecera)cabeceraList.get(i);					
			reclamoDigitCabec.setNumeroLote(numeroLote);			
			getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertCabeceraServiciosPostventas", reclamoDigitCabec);				
		}		
		
		for (int i = 0; i < detalleList.size(); i++) {
			ReclamoDigitadoDetalle recDigitDetal = (ReclamoDigitadoDetalle)detalleList.get(i);					
			recDigitDetal.setNumeroLote(numeroLote);			
			getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertDetalleServiciosPostventas", recDigitDetal);				
		}
		
		getSqlMapClientTemplate().insert("sisicc.InterfazOCRSQL.executeCalcularNumeroDocumentoPostVenta", params);
		
		getSqlMapClientTemplate().insert("sisicc.InterfazOCRSQL.executeConsolidadoPostVenta", params);		
	}
	
	public String getIndExpress(Map criteria){
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getIndExpress", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECIngresoMasivoOperacionesDAO#getCuv(java.util.Map)
	 */
	public List getCuv(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCuv", criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECIngresoMasivoOperacionesDAO#getMotivoReclamoList(java.util.Map)
	 */
	public List getMotivoReclamoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getMotivoReclamoList",criteria);		
	}
}