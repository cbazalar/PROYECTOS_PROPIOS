package biz.belcorp.ssicc.dao.spusicc.men.ibatis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENPlantillaDAO;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.mantenimientoMENPlantillaDAO")
public class MantenimientoMENPlantillaDAOIbatis extends BaseDAOiBatis implements
						MantenimientoMENPlantillaDAO {


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getParametrosPlantilla()
	 */
	public List getParametrosPlantilla() {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getParametrosPlantilla");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getSelectPlantilla(java.util.Map)
	 */
	public List getSelectPlantilla(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getSelectPlantilla",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#insertPlantilla(java.util.Map)
	 */
	public void insertPlantilla(Map mapCabecera) {
		getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertPlantilla", mapCabecera);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#insertPlantillaDetalle(java.util.Map)
	 */
	public void insertPlantillaDetalle(Map mapDetalle) {
		getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertPlantillaDetalle", mapDetalle);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#deletePlantilla(java.util.Map)
	 */
	public void deletePlantilla(Map map) {
	 try{
		// se elminan los detalles
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deletePlantillaDetalle", map);
		//se elimina la cabecera
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deletePlantillaCabec", map);
	 }catch(Exception e){
		 //ACTULAIZAMOS EL REGISTRO A 9
		 getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateEstadoPlantillaCabec", map);
		 getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateEstadoPlantillaDetalle", map);
	 }
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getPlantillaCabecera(java.util.Map)
	 */
	public List getPlantillaCabecera(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getPlantillaCabecera",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getPlantillaDetalle(java.util.Map)
	 */
	public List getPlantillaDetalle(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getPlantillaDetalle",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#deletePlantillaDetalle(java.util.Map)
	 */
	public void deletePlantillaDetalle(Map map) {
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deletePlantillaDetalle", map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#updatePlantilla(java.util.Map)
	 */
	public void updatePlantilla(Map mapCabecera) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updatePlantilla", mapCabecera);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getProcesos(java.util.Map)
	 */
	public List getProcesos(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getProcesos",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getProcesosConfigurados(java.util.Map)
	 */
	public List getProcesosConfigurados(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getProcesosConfigurados",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getParametrosPlantillaDetalleVisibles()
	 */
	public List getParametrosPlantillaDetalleVisibles(Map map) {
		return getSqlMapClientTemplate().
						queryForList("spusicc.mensaje.MantenimientoMENSQL." +
										"getParametrosPlantillaDetalleVisibles",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getSelectPlantillaSeleccionado(java.util.Map)
	 */
	public List getSelectPlantillaSeleccionado(Map map) {
		return getSqlMapClientTemplate().
		queryForList("spusicc.mensaje.MantenimientoMENSQL." +
						"getSelectPlantillaSeleccionado",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#insertProcesoPlantilla(java.util.Map)
	 */
	public void insertProcesoPlantilla(Map mapCabecera) {
		getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertProcesoPlantilla", mapCabecera);		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#updateProcesoPlantilla(java.util.Map)
	 */
	public void updateProcesoPlantilla(Map mapCabecera) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateProcesoPlantilla", mapCabecera);		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#updatePlantillaDetalle(java.util.Map)
	 */
	public void updatePlantillaDetalle(Map mapDetalle) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updatePlantillaDetalle", mapDetalle);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#deleteProcesoPlantillaDetalle(java.util.Map)
	 */
	public void deleteProcesoPlantillaDetalle(Map mapCabecera) {
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteProcesoPlantillaDetalle", mapCabecera);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#insertProcesoPlantillaDetalle(java.util.Map)
	 */
	public void insertProcesoPlantillaDetalle(Map mapDetalle) {
		getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertProcesoPlantillaDetalle", mapDetalle);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#updateProcesoPlantillaDetalle(java.util.Map)
	 */
	public void updateProcesoPlantillaDetalle(Map mapDetalle) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateProcesoPlantillaDetalle", mapDetalle);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#updateProceso(java.util.Map)
	 */
	public void updateProceso(Map mapCabecera) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateProceso", mapCabecera);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getlistProcesoHabilitado()
	 */
	public List getListProcesoHabilitado() {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getListProcesoHabilitado");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#deleteProcesoHabilitado()
	 */
	public void deleteProcesoHabilitado(Map map) {
		if(StringUtils.equals((String)map.get("flag"), Constants.NUMERO_UNO)){
			getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertProcesoHabilitadoHistorico",map);
		}
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteProcesoHabilitado",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#insertProcesoHabilitado(java.util.Map)
	 */
	public void insertProcesoHabilitado(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertProcesoHabilitado", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#insertProcesoHabilitado(java.util.List, java.lang.String)
	 */
	public void insertProcesoHabilitado(Map map,List listaProcesoSeleccionado,
			String login) {
		Iterator it = listaProcesoSeleccionado.iterator();					
		while (it.hasNext()) {
			String codigoProceso = (String)it.next();			
			map.put("codigoProceso", codigoProceso);
			map.put("codigoUsuario", login);
			insertProcesoHabilitado(map);
			map.put("codigoProcesoAct", codigoProceso);
			map.put("codigoProcesoAnt", "");
			insertProcesoHabilitadoHisto(map);
		}			
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getProcesosDisponibles(java.util.Map)
	 */
	public List getProcesosDisponibles(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getProcesosDisponibles",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#updateProcesoHabilitado(java.util.Map)
	 */
	public void updateProcesoHabilitado(Map map) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateProcesoHabilitado", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#insertProcesoHabilitadoHisto(java.util.Map)
	 */
	public void insertProcesoHabilitadoHisto(Map map) {
		getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertProcesoHistoHabilitado", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#updateProcesoHabilitadoHisto(java.util.Map)
	 */
	public void updateProcesoHabilitadoHisto(Map map) {
		getSqlMapClientTemplate().update("spusicc.mensaje.MantenimientoMENSQL.updateProcesoHistoHabilitado", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getIndicadorProcesoActivo()
	 */
	public String getIndicadorProcesoActivo() {		
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.mensaje.MantenimientoMENSQL.getIndicadorProcesoActivo"); 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getProcesosHabilitados(java.util.Map)
	 */
	public List getProcesosHabilitados(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.mensaje.MantenimientoMENSQL.getProcesosHabilitados",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#setProcesoPaquete(java.util.Map)
	 */
	public void setProcesoPaquete(Map criteria) {	
		//validamos si es multihilo el paquete
		String indicadorMultihilo= (String)getSqlMapClientTemplate().queryForObject("spusicc.mensaje.MantenimientoMENSQL.getIndicadorMultiHilo",criteria);
		//eliminamos en el paquete
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteProcesoPaquete",criteria);
		if(StringUtils.equals(Constants.NO, indicadorMultihilo)){
		//insertamos los nuevos proceoss habilitados
		getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertProcesoPaquete",criteria);
		}else{
			//insertamos los nuevos proceoss hilos habilitados
			getSqlMapClientTemplate().insert("spusicc.mensaje.MantenimientoMENSQL.insertProcesoPaqueteMultHilo",criteria);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#deleteProcesoPlantilla(java.util.Map)
	 */
	public void deleteProcesoPlantilla(Map map) {
		// se elminan los detalles
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteProcesoPlantillaDetalle", map);
		//se elimina la cabecera
		getSqlMapClientTemplate().delete("spusicc.mensaje.MantenimientoMENSQL.deleteProcesoPlantilla", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#executePedidoCliente(java.util.Map)
	 */
	public void executePedidoCliente(Map map) {
		getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.executePedidoCliente", map);		
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getDevuelveCampanaIndicadorCruce(java.util.Map)
	 */
	public Integer getDevuelveCampanaIndicadorCruce(Map map) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.mensaje.ProcesoMENSQL.getDevuelveCampanaIndicadorCruce", map);		
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#getDevuelveCampanaAnterior(java.util.Map)
	 */
	public String getDevuelveCampanaAnterior(Map map) {
	  return	(String)getSqlMapClientTemplate().queryForObject("spusicc.mensaje.ProcesoMENSQL.getDevuelveCampanaAnterior", map);		
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.dao.MantenimientoMENPlantillaDAO#executeActuaMensajesCampana(java.util.Map)
	 */
	public void executeProcesoActualizarMensajes(Map map) {
		if(map.get("indicadorCruce")==null)
			map.put("indicadorCruce", "0");
		getSqlMapClientTemplate().update("spusicc.mensaje.ProcesoMENSQL.executeProcesoActualizarMensajes", map);		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENPlantillaDAO#getVerRegionesZonasAprobadasNoProc(java.util.Map)
	 */
	public Integer getVerRegionesZonasAprobadasNoProc(Map map) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.mensaje.MantenimientoMENSQL.getVerRegionesZonasAprobadasNoProc", map);		
		
	}
	
	
}
