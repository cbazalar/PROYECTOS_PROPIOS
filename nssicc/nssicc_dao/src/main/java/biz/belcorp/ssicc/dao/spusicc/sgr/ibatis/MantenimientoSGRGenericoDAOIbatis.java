package biz.belcorp.ssicc.dao.spusicc.sgr.ibatis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sgr.MantenimientoSGRGenericoDAO;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.mantenimientoSGRGenericoDAO")
public class MantenimientoSGRGenericoDAOIbatis extends BaseDAOiBatis implements MantenimientoSGRGenericoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getPoliza(java.util.Map)
	 */
	public List getPoliza(Map criteria) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.famsegura.MantenimientoSGRSQL.getPoliza",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#deletePoliza(java.util.Map)
	 */
	public void deletePoliza(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deletePoliza",map);
		//elimina si tuviera la vigencia , los descuetos y los kits
		
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteVigencia",map);
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteDescuento",map);
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteKit",map);
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteEstatus",map);
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteCampaniaGratuitas",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#savePoliza(java.util.Map)
	 */
	public void savePoliza(Map map) {
		String indicadorAccionPoliza = (String)map.get("indicadorAccionPoliza");
		if(Constants.NUMERO_CERO.equals(indicadorAccionPoliza))
			getSqlMapClientTemplate().insert("spusicc.famsegura.MantenimientoSGRSQL.savePoliza",map);
		else
			getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.updatePoliza",map);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#deleteParametros(java.util.Map)
	 */
	public void deleteParametros(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteParametros",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#insertParametros(java.util.Map)
	 */
	public void insertParametros(Map map) {
		getSqlMapClientTemplate().insert("spusicc.famsegura.MantenimientoSGRSQL.insertParametros",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#saveDescto(java.util.Map)
	 */
	public void saveDescto(Map map) {
		getSqlMapClientTemplate().insert("spusicc.famsegura.MantenimientoSGRSQL.saveDescto",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#saveKit(java.util.Map)
	 */
	public void saveKit(Map map) {
		getSqlMapClientTemplate().insert("spusicc.famsegura.MantenimientoSGRSQL.saveKit",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#saveVigencia(java.util.Map)
	 */
	public void saveVigencia(Map map) {
		getSqlMapClientTemplate().insert("spusicc.famsegura.MantenimientoSGRSQL.saveVigencia",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getDscto(java.util.Map)
	 */
	public List getDscto(Map bean) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.famsegura.MantenimientoSGRSQL.getDscto",bean);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getKit(java.util.Map)
	 */
	public List getKit(Map bean) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.famsegura.MantenimientoSGRSQL.getKit",bean);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getVigencia(java.util.Map)
	 */
	public List getVigencia(Map bean) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.famsegura.MantenimientoSGRSQL.getVigencia",bean);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getNumPolizasRegistradas(java.util.Map)
	 */
	public Integer getNumPolizasRegistradas(Map map) {
		Integer result =(Integer)getSqlMapClientTemplate().
											queryForObject("spusicc.famsegura.MantenimientoSGRSQL.getNumPolizasRegistradas",map);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#deleteDescto(java.util.Map)
	 */
	public void deleteDescto(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteDesctoByCorre",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#deleteKit(java.util.Map)
	 */
	public void deleteKit(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteKitByCorre",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#deleteVigencia(java.util.Map)
	 */
	public void deleteVigencia(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteVigenciaByCorre",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#updateDescto(java.util.Map)
	 */
	public void updateDescto(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.updateDesctoByCorre",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#updateKit(java.util.Map)
	 */
	public void updateKit(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.updateKitByCorre",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#updateVigencia(java.util.Map)
	 */
	public void updateVigencia(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.updateVigenciaByCorre",map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getValidarTraslapeDescuento(java.util.Map)
	 */
	public Integer getValidarTraslapeDescuento(Map map) {
		Integer result =(Integer)getSqlMapClientTemplate().
				queryForObject("spusicc.famsegura.MantenimientoSGRSQL.getValidarTraslapeDescuento",map);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getValidarTraslapeFechas(java.util.Map)
	 */
	public Integer getValidarTraslapeFechas(Map map) {
		Integer result =(Integer)getSqlMapClientTemplate().
				queryForObject("spusicc.famsegura.MantenimientoSGRSQL.getValidarTraslapeFechas",map);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getExisteConsultoraPolizaActiva(java.lang.String)
	 */
	public Integer getExisteConsultoraPolizaActiva(String codigoCliente) {		
		Integer result =(Integer)getSqlMapClientTemplate().
					queryForObject("spusicc.famsegura.MantenimientoSGRSQL.getExisteConsultoraPolizaActiva",codigoCliente);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#executeValidacionesInscripcionPoliza(java.util.Map)
	 */
	public void executeValidacionesInscripcionPoliza(Map map) {
		String indicadorAccion = (String) map.get("indicadorAccion");
		List list = (List)map.get("listBenef");
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.executeValidacionesInscripcionPoliza",map);
		if(Constants.NRO_UNO.equals(indicadorAccion)){
			//se actualiza los beneficiarios de la poliza en el consolidado de rechazos
			String resultado = (String)map.get("mensajeResultado");
			String []parametros =StringUtils.split(resultado, ",");
			map.put("indicadorRechazoSTO", parametros[0]);
			map.put("numSecuencia", parametros[1]);
			map.put("numLote", parametros[2]);
			if(list.size()>0)
				getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.updateBenefPolizaSTO",map);
			
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#saveInscripcionPoliza(java.util.Map)
	 */
	public void saveInscripcionPoliza(Map map) {
		String login = (String)map.get("login");
		getSqlMapClientTemplate().insert("spusicc.famsegura.MantenimientoSGRSQL.saveInscripcionPoliza",map);
		List list = (List)map.get("listBenef");
		Iterator it = list.iterator();
		while(it.hasNext()){
			Map mapBene= (Map)it.next();
			mapBene.put("login", login);
			getSqlMapClientTemplate().insert("spusicc.famsegura.MantenimientoSGRSQL.saveBenefPoliza",mapBene);
		}						
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getBeneficiarios(java.util.Map)
	 */
	public List getBeneficiarios(Map map) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.famsegura.MantenimientoSGRSQL.getBeneficiarios",map);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#updateInscripcionPoliza(java.util.Map)
	 */
	public void updateInscripcionPoliza(Map map){
		String login = (String)map.get("login");
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.updateInscripcionPoliza",map);
		getSqlMapClientTemplate().delete("spusicc.famsegura.MantenimientoSGRSQL.deleteBenefPolizaFis",map);		
		List list = (List)map.get("listBenef");
		Iterator it = list.iterator();
		while(it.hasNext()){
			Map mapBene= (Map)it.next();
			mapBene.put("login", login);
			getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.saveBenefPoliza",mapBene);
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#deleteInscripcionPoliza(java.util.Map)
	 */
	public void deleteInscripcionPoliza(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteBenefPoliza",map);		
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteInscripcionPoliza",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getInscripcionPoliza(java.util.Map)
	 */
	public List getInscripcionPoliza(Map criteria) {
		List result =(List)getSqlMapClientTemplate().queryForList("spusicc.famsegura.MantenimientoSGRSQL.getInscripcionPoliza",criteria);
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#executeGenerarReporteControlAbonos(java.util.Map)
	 */
	public void executeGenerarReporteControlAbonos(Map criteria){
		getSqlMapClientTemplate().update(
				"spusicc.famsegura.MantenimientoSGRSQL.executeGenerarReporteControlAbonos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getLongitudTipoDocumentoByCodigo(java.lang.String, java.lang.String)
	 */
	public String getLongitudTipoDocumentoByCodigo(String oidPais,
			String codigoTipoDocumento) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("codigoTipoDocumento", codigoTipoDocumento);		
		Integer result =(Integer)getSqlMapClientTemplate().
								queryForObject("spusicc.famsegura.MantenimientoSGRSQL.getLongitudTipoDocumentoByCodigo",criteria);
		
		
		return String.valueOf(result);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#saveEstatus(java.util.Map)
	 */
	public void saveEstatus(Map map) {
		getSqlMapClientTemplate().insert("spusicc.famsegura.MantenimientoSGRSQL.saveEstatus",map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#deleteEstatus(java.util.Map)
	 */
	public void deleteEstatus(Map map) {
		getSqlMapClientTemplate().delete("spusicc.famsegura.MantenimientoSGRSQL.deleteEstatusByCorre",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getEstatus(java.util.Map)
	 */
	public List getEstatus(Map bean) {
		return getSqlMapClientTemplate().queryForList("spusicc.famsegura.MantenimientoSGRSQL.getEstatus", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getNumDocumentoIdentByCodigoCliente(java.lang.String, java.lang.String)
	 */
	public String getNumDocumentoIdentByCodigoCliente(String codigoCliente,String codigoTipoDocu) {
		Map criteria = new HashMap();
		
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("codigoTipoDocu", codigoTipoDocu);
		
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.famsegura.MantenimientoSGRSQL.getNumDocumentoIdentByCodigoCliente", criteria);
	}

	public List getCampaniaGratuita(Map bean) {
		return getSqlMapClientTemplate().queryForList("spusicc.famsegura.MantenimientoSGRSQL.getCampaniaGratuita", bean);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#deleteCampaniaGratuitas(java.util.Map)
	 */
	public void deleteCampaniaGratuitas(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.deleteCampaniaGratuitasByCorre",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#saveCampaniaGratuitas(java.util.Map)
	 */
	public void saveCampaniaGratuitas(Map map) {
		getSqlMapClientTemplate().insert("spusicc.famsegura.MantenimientoSGRSQL.saveCampaniaGratuitas",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#updateCampaniaGratuitas(java.util.Map)
	 */
	public void updateCampaniaGratuitas(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.updateCampaniaGratuitas",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#getLongitudDefaultTipoDocumento(java.util.Map)
	 */
	public String getLongitudDefaultTipoDocumento(String oidPais) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);	
		Integer result =(Integer)getSqlMapClientTemplate().
								queryForObject("spusicc.famsegura.MantenimientoSGRSQL.getLongitudDefaultTipoDocumento",criteria);
		
		
		return String.valueOf(result);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sgr.dao.MantenimientoSGRGenericoDAO#updateInscripcionPolizaActiva(java.util.Map)
	 */
	public void updateInscripcionPolizaActiva(Map map) {
		getSqlMapClientTemplate().update("spusicc.famsegura.MantenimientoSGRSQL.updateInscripcionPolizaActiva",map);
	}
}