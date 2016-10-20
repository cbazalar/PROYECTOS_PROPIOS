package biz.belcorp.ssicc.dao.spusicc.men.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENIngresoGerenteZonalesDAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.mantenimientoMENIngresoGerenteZonalesDAO")
public class MantenimientoMENIngresoGerenteZonalesDAOIbatis extends BaseDAOiBatis implements
				MantenimientoMENIngresoGerenteZonalesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#getMensajeGerenteZonales(java.util.Map)
	 */
	public List getMensajeGerenteZonales(Map map) {
		return getSqlMapClientTemplate().
					queryForList("spusicc.mensaje.MantenimientoMENSQL.getMensajeGerenteZonales",
								 map);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#updateAnulacionMensaje(java.util.List, java.lang.String[], biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateAnulacionMensaje(Map map) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateAnulacionMensaje", map);
		
	}

	public List getRangoFechaActividad(Map map) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getRangoFechaActividad",
					 map);
	}

	public Integer getVerificaZonasAprobadasNoProcesadas(Map map){
		return (Integer)getSqlMapClientTemplate().
		queryForObject("spusicc.mensaje.MantenimientoMENSQL.getVerZonasAprobadasNoProc",
					 map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#insertMensajeGerenteZonales(java.util.Map)
	 */
	public void insertMensajeGerenteZonales(Map map,String campanhaActividad) {
		//primero eliminamos si ya existen regiones y zonas para la campanha de actividad
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteMensajeGerenteZonales", map);
		if(StringUtils.isEmpty(campanhaActividad))
			getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertMensajeGerenteZonales", map);
		else{
			String [] zonas = (String [])map.get("zonas");
			for(int i =0;i<zonas.length;i++){
				map.put("codigoZona", zonas[i]);
				getSqlMapClientTemplate().
				         insert("spusicc.mensaje.MantenimientoMENSQL.insertMensajeGerenteZona", map);
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#getZonasFromRegion(java.util.Map)
	 */
	public List getZonasFromRegion(Map criteria) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getZonasFromRegion",
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#updateMensajeGerenteZonales(java.util.Map)
	 */
	public void updateMensajeGerenteZonales(Map map) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateMensajeGerenteZonales", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#deleteMensajeGerenteZonales(java.util.Map)
	 */
	public void deleteMensajeGerenteZonales(Map map) {
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteMensajeGerenteZonales", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#getFechaActividad(java.util.Map)
	 */
	public String getFechaActividad(Map map) {
		return (String)getSqlMapClientTemplate().
		queryForObject("spusicc.mensaje.MantenimientoMENSQL.getFechaActividad",
					 map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#getCodigosActividad(java.util.Map)
	 */
	public List getCodigosActividad(Map map) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getCodigosActividad",
				map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#getCodigosMensaje(java.util.Map)
	 */
	public List getCodigosMensaje(Map map) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getCodigosMensaje",
				map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#getParametrosActividadMensaje(java.util.Map)
	 */
	public List getParametrosActividadMensaje(Map map) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL.getParametrosActividadMensaje",
				map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#insertParametrosActividadMensaje(java.util.Map)
	 */
	public void insertParametrosActividadMensaje(Map map) {
		getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertParametrosActividadMensaje", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#updateParametrosActividadMensaje(java.util.Map)
	 */
	public void updateParametrosActividadMensaje(Map map) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateParametrosActividadMensaje", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#deleteParametrosActividadMensaje(java.util.Map)
	 */
	public void deleteParametrosActividadMensaje(Map map) {
	   	try{	   		
	   		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteParametrosActividadMensaje", map);
	   		//getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteMensajeGerenteZonalesByParamActi", map);
	   	}catch(Exception e){
	   		log.debug("error en constraint existe integridad se pasa actualziar");
	   		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateEstadoParametrosActividadMensaje", map);
	   	}
	   	
		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#getNombreProgramaProceso(java.lang.String)
	 */
	public String getNombreProgramaProceso(String codigoProceso) {
		String nombre="";
		if(StringUtils.isNotEmpty(codigoProceso)){
			Map map = new HashMap();
			map.put("codigoProceso", codigoProceso);
			List list= getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getProcesos",map);
			if(list.size()>0){
				Map result = (Map)list.get(0);
				nombre= (String)result.get("nombreProgramaEjecutar");
			}
		}
		return nombre;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#deleteEstadoGananciaMensaje(java.util.Map)
	 */
	public void deleteEstadoGananciaMensaje(Map map) {
   		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteEstadoGananciaMensaje", map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#getEscaleraGanaciaMensaje(java.util.Map)
	 */
	public List getEscaleraGanaciaMensaje(Map map) {
		List list= getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getEscaleraGanaciaMensaje",map);
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#getExisteMensajeRangos(java.util.Map)
	 */
	public boolean getExisteMensajeRangos(Map map) {
		Integer val= (Integer)getSqlMapClientTemplate().queryForObject("spusicc.mensaje.MantenimientoMENSQL.getExisteMensajeRangos",map);
		return val==0?false:true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#getExisteRangoTraslapado(java.util.Map)
	 */
	public boolean getExisteRangoTraslapado(Map map) {
		Integer val= (Integer)getSqlMapClientTemplate().queryForObject("spusicc.mensaje.MantenimientoMENSQL.getExisteRangoTraslapado",map);
		return val==0?false:true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#insertEscaleraGananciaMensaje(java.util.Map)
	 */
	public void insertEscaleraGananciaMensaje(Map map) {
   		getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertEscaleraGananciaMensaje", map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#updateEscaleraGananciaMensaje(java.util.Map)
	 */
	public void updateEscaleraGananciaMensaje(Map map) {
   		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateEscaleraGananciaMensaje", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#deleteMensajeCodigoVenta(java.util.Map)
	 */
	public void deleteMensajeCodigoVenta(Map map) {
   		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.deleteMensajeCodigoVenta", map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#getMensajeCodigoVenta(java.util.Map)
	 */
	public List getMensajeCodigoVenta(Map map) {
		List list= getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getMensajeCodigoVenta",map);
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#insertMensajeCodigoVentaMensaje(java.util.Map)
	 */
	public void insertMensajeCodigoVentaMensaje(Map map) throws Exception {
		getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertMensajeCodigoVentaMensaje", map);	
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENIngresoGerenteZonalesDAO#updateMensajeCodigoVentaMensaje(java.util.Map)
	 */
	public void updateMensajeCodigoVentaMensaje(Map map) throws Exception {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateMensajeCodigoVentaMensaje", map);		
		
	}
	
	
}
