package biz.belcorp.ssicc.service.spusicc.men.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENIngresoGerenteZonalesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENIngresoGerenteZonalesService;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Service("spusicc.mantenimientoMENIngresoGerenteZonalesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoMENIngresoGerenteZonalesServiceImpl extends BaseService implements
		MantenimientoMENIngresoGerenteZonalesService {

	@Resource(name="spusicc.mantenimientoMENIngresoGerenteZonalesDAO")
	MantenimientoMENIngresoGerenteZonalesDAO mantenimientoMENIngresoGerenteZonalesDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#getMensajeGerenteZonales(java.util.Map)
	 */
	public List getMensajeGerenteZonales(Map map) {
		return mantenimientoMENIngresoGerenteZonalesDAO.getMensajeGerenteZonales(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#updateAnulacionMensaje(java.util.List, java.lang.String[], biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateAnulacionMensaje(List listMensajeGerenteZonales,
			String[] seleccion, Usuario usuario) {
		for(int i = 0; i< seleccion.length;i++){
			Map map = (Map) listMensajeGerenteZonales.get(Integer.parseInt(seleccion[i])-1);
			map.put("login", usuario.getLogin());
			map.put("indicadorAnulado", Constants.NUMERO_UNO);
			updateAnulacionMensaje(map);	
		}
		
		
	}
	private void updateAnulacionMensaje(Map map) {
		mantenimientoMENIngresoGerenteZonalesDAO.updateAnulacionMensaje(map);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#insertMensajeGerenteZonales(java.util.Map)
	 */
	public void insertMensajeGerenteZonales(Map map) {
		String campanhaActividad= (String) map.get("campanhaActividad");
		mantenimientoMENIngresoGerenteZonalesDAO.insertMensajeGerenteZonales(map,campanhaActividad);
			
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#getZonasFromRegion(java.util.Map)
	 */
	public List getZonasFromRegion(Map criteria) {
		return mantenimientoMENIngresoGerenteZonalesDAO.getZonasFromRegion(criteria);
		
	}
	public void updateMensajeGerenteZonales(Map map) {
		mantenimientoMENIngresoGerenteZonalesDAO.updateMensajeGerenteZonales(map);
		
	}
	public void deleteMensajeGerenteZonales(List listMensajeGerenteZonales,
			String[] seleccion, Usuario usuario) {
		for(int i = 0; i< seleccion.length;i++){
			Map map = (Map) listMensajeGerenteZonales.get(Integer.parseInt(seleccion[i])-1);
			map.put("login", usuario.getLogin());
			map.put("indicadorAnulado", Constants.NUMERO_UNO);
			map.put("zonas", null);
			mantenimientoMENIngresoGerenteZonalesDAO.deleteMensajeGerenteZonales(map);	
		}
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#getCodigosActividad(java.util.Map)
	 */
	public List getCodigosActividad(Map map) {		
		return mantenimientoMENIngresoGerenteZonalesDAO.getCodigosActividad(map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#getCodigosMensaje(java.util.Map)
	 */
	public List getCodigosMensaje(Map map) {	
		return mantenimientoMENIngresoGerenteZonalesDAO.getCodigosMensaje(map);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#getParametrosActividadMensaje(java.util.Map)
	 */
	public List getParametrosActividadMensaje(Map map) {
		return mantenimientoMENIngresoGerenteZonalesDAO.getParametrosActividadMensaje(map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#insertParametrosActividadMensaje(java.util.Map)
	 */
	public void insertParametrosActividadMensaje(Map map) {
		mantenimientoMENIngresoGerenteZonalesDAO.insertParametrosActividadMensaje(map);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#updateParametrosActividadMensaje(java.util.Map)
	 */
	public void updateParametrosActividadMensaje(Map map) {
		mantenimientoMENIngresoGerenteZonalesDAO.updateParametrosActividadMensaje(map);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#deleteParametrosActividadMensaje(java.util.Map)
	 */
	public void deleteParametrosActividadMensaje(Map map) {
		mantenimientoMENIngresoGerenteZonalesDAO.deleteParametrosActividadMensaje(map);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#deleteEstadoGananciaMensaje(java.util.Map)
	 */
	public void deleteEstadoGananciaMensaje(Map map) {
		mantenimientoMENIngresoGerenteZonalesDAO.deleteEstadoGananciaMensaje(map);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#getEscaleraGanaciaMensaje(java.util.Map)
	 */
	public List getEscaleraGanaciaMensaje(Map map) {
		return mantenimientoMENIngresoGerenteZonalesDAO.getEscaleraGanaciaMensaje(map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#getExisteMensajeRangos(java.util.Map)
	 */
	public boolean getExisteMensajeRangos(Map map) {
		return mantenimientoMENIngresoGerenteZonalesDAO.getExisteMensajeRangos(map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#getExisteRangoTraslapado(java.util.Map)
	 */
	public boolean getExisteRangoTraslapado(Map map) {	
		return mantenimientoMENIngresoGerenteZonalesDAO.getExisteRangoTraslapado(map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#insertEscaleraGananciaMensaje(java.util.Map)
	 */
	public void insertEscaleraGananciaMensaje(Map map) {
		mantenimientoMENIngresoGerenteZonalesDAO.insertEscaleraGananciaMensaje(map);		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#updateEscaleraGananciaMensaje(java.util.Map)
	 */
	public void updateEscaleraGananciaMensaje(Map map) {
		mantenimientoMENIngresoGerenteZonalesDAO.updateEscaleraGananciaMensaje(map);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#deleteMensajeCodigoVenta(java.util.Map)
	 */
	public void deleteMensajeCodigoVenta(Map map) {
		mantenimientoMENIngresoGerenteZonalesDAO.deleteMensajeCodigoVenta(map);	
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#getMensajeCodigoVenta(java.util.Map)
	 */
	public List getMensajeCodigoVenta(Map map) {	
		return mantenimientoMENIngresoGerenteZonalesDAO.getMensajeCodigoVenta(map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#insertMensajeCodigoVentaMensaje(java.util.Map)
	 */
	public void insertMensajeCodigoVentaMensaje(Map map) throws Exception {
		List list = (List)map.get("list");
		Iterator it = list.iterator();
		Map mp=null;
		while(it.hasNext()){
			mp=(Map)it.next();
			mp.put("login", map.get("login"));
			try{
			 mantenimientoMENIngresoGerenteZonalesDAO.insertMensajeCodigoVentaMensaje(mp);
			}catch(Exception e){
			   String periodo=(String)mp.get("campanhaProceso");
			   String codigoVenta=(String)mp.get("codigoVenta");
			   String oidMensa=(String)mp.get("oidMensaje");
			   throw new Exception(periodo+'_'+oidMensa+'_'+codigoVenta);	
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENIngresoGerenteZonalesService#updateMensajeCodigoVentaMensaje(java.util.Map)
	 */
	public void updateMensajeCodigoVentaMensaje(Map map) throws Exception {
		mantenimientoMENIngresoGerenteZonalesDAO.updateMensajeCodigoVentaMensaje(map);
	}
}
