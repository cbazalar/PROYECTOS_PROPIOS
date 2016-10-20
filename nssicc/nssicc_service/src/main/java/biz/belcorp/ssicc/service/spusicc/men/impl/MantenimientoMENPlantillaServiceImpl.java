package biz.belcorp.ssicc.service.spusicc.men.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENPlantillaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENPlantillaService;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Service("spusicc.mantenimientoMENPlantillaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoMENPlantillaServiceImpl extends BaseService implements
		MantenimientoMENPlantillaService {

	@Resource(name="spusicc.mantenimientoMENPlantillaDAO")
	MantenimientoMENPlantillaDAO mantenimientoMENPlantillaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getParametrosPlantilla()
	 */
	public List getParametrosPlantilla() {	
		return mantenimientoMENPlantillaDAO.getParametrosPlantilla();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getSelectPlantilla(java.util.Map)
	 */
	public List getSelectPlantilla(Map map) {
		return mantenimientoMENPlantillaDAO.getSelectPlantilla(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#insertPlantilla(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPlantilla(List listPlantilla, Usuario usuario) {
		Iterator it = listPlantilla.iterator();
		while(it.hasNext()){
			Map mapCabecera = (Map)it.next();
			mapCabecera.put("login", usuario.getLogin());
			mantenimientoMENPlantillaDAO.insertPlantilla(mapCabecera);
			List listDetalle = (List)mapCabecera.get("mapDetallePlantilla");
			Iterator itDetalle = listDetalle.iterator();
			while(itDetalle.hasNext()){
				Map mapDetalle = (Map)itDetalle.next();
				mapDetalle.put("login", usuario.getLogin());
				//mapDetalle.put("codigoPlantilla", (String)mapCabecera.get("codigoPlantilla"));
				mantenimientoMENPlantillaDAO.insertPlantillaDetalle(mapDetalle);
			}			
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#deletePlantilla(java.util.Map)
	 */
	public void deletePlantilla(Map map) {
		mantenimientoMENPlantillaDAO.deletePlantilla(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getPlantillaCabecera(java.util.Map)
	 */
	public List getPlantillaCabecera(Map map) {
		return mantenimientoMENPlantillaDAO.getPlantillaCabecera(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getPlantillaDetalle(java.util.Map)
	 */
	public List getPlantillaDetalle(Map map) {
		return mantenimientoMENPlantillaDAO.getPlantillaDetalle(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#updatePlantilla(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePlantilla(List listPlantilla, Usuario usuario) {
			
		Iterator it = listPlantilla.iterator();
		while(it.hasNext()){
			Map mapCabecera = (Map)it.next();
			mapCabecera.put("login", usuario.getLogin());
			mantenimientoMENPlantillaDAO.updatePlantilla(mapCabecera);
			mantenimientoMENPlantillaDAO.deletePlantillaDetalle(mapCabecera);
			List listDetalle = (List)mapCabecera.get("mapDetallePlantilla");
			Iterator itDetalle = listDetalle.iterator();
			while(itDetalle.hasNext()){
				Map mapDetalle = (Map)itDetalle.next();
				mapDetalle.put("login", usuario.getLogin());
				//mapDetalle.put("codigoPlantilla", (String)mapCabecera.get("codigoPlantilla"));
				mantenimientoMENPlantillaDAO.insertPlantillaDetalle(mapDetalle);
			}			
		}	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getProcesos(java.util.Map)
	 */
	public List getProcesos(Map map) {
		return mantenimientoMENPlantillaDAO.getProcesos(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getProcesosConfigurados(java.util.Map)
	 */
	public List getProcesosConfigurados(Map map) {
		return mantenimientoMENPlantillaDAO.getProcesosConfigurados(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getParametrosPlantillaDetalleVisibles()
	 */
	public List getParametrosPlantillaDetalleVisibles(Map map) {
		return mantenimientoMENPlantillaDAO.getParametrosPlantillaDetalleVisibles(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getSelectPlantillaSeleccionado(java.util.Map)
	 */
	public List getSelectPlantillaSeleccionado(Map map) {
		return mantenimientoMENPlantillaDAO.getSelectPlantillaSeleccionado(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#insertProcesoPlantilla(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertProcesoPlantilla(List listProcesoCabec, Usuario usuario) {
		Iterator it = listProcesoCabec.iterator();
		while(it.hasNext()){
			Map mapCabecera = (Map)it.next();
			mapCabecera.put("login", usuario.getLogin());
			mantenimientoMENPlantillaDAO.insertProcesoPlantilla(mapCabecera);
			mantenimientoMENPlantillaDAO.updateProceso(mapCabecera);
			List listDetalle = (List)mapCabecera.get("mapDetallePlantilla");
			Iterator itDetalle = listDetalle.iterator();
			while(itDetalle.hasNext()){
				Map mapDetalle = (Map)itDetalle.next();
				mapDetalle.put("codigoPais", mapCabecera.get("codigoPais"));
				mapDetalle.put("login", usuario.getLogin());
				mapDetalle.put("codigoProceso", mapCabecera.get("codigoProceso"));
				mapDetalle.put("tipoProceso", mapCabecera.get("tipoProceso"));
				mapDetalle.put("ordenEjecucion", mapCabecera.get("ordenEjecucion"));
				//mapDetalle.put("codigoPlantilla", (String)mapCabecera.get("codigoPlantilla"));
				mantenimientoMENPlantillaDAO.insertProcesoPlantillaDetalle(mapDetalle);
			}			
		}		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#updateProcesoPlantilla(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateProcesoPlantilla(List listProcesoCabec, Usuario usuario) {
		Iterator it = listProcesoCabec.iterator();
		while(it.hasNext()){
			Map mapCabecera = (Map)it.next();
			mapCabecera.put("login", usuario.getLogin());
			mantenimientoMENPlantillaDAO.updateProcesoPlantilla(mapCabecera);
			mantenimientoMENPlantillaDAO.deleteProcesoPlantillaDetalle(mapCabecera);
			mantenimientoMENPlantillaDAO.updateProceso(mapCabecera);
			List listDetalle = (List)mapCabecera.get("mapDetallePlantilla");
			Iterator itDetalle = listDetalle.iterator();
			while(itDetalle.hasNext()){
				Map mapDetalle = (Map)itDetalle.next();
				mapDetalle.put("codigoPais", mapCabecera.get("codigoPais"));
				mapDetalle.put("login", usuario.getLogin());
				mapDetalle.put("codigoProceso", mapCabecera.get("codigoProceso"));
				mapDetalle.put("tipoProceso", mapCabecera.get("tipoProceso"));
				mapDetalle.put("ordenEjecucion", mapCabecera.get("ordenEjecucion"));
				//mapDetalle.put("codigoPlantilla", (String)mapCabecera.get("codigoPlantilla"));
				mantenimientoMENPlantillaDAO.insertProcesoPlantillaDetalle(mapDetalle);
			}			
		}	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getlistProcesoHabilitado()
	 */
	public List getListProcesoHabilitado() {
		return mantenimientoMENPlantillaDAO.getListProcesoHabilitado();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#deleteProcesoHabilitado()
	 */
	public void deleteProcesoHabilitado(Map map) {
		mantenimientoMENPlantillaDAO.deleteProcesoHabilitado(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#insertProcesoHabilitado(java.util.Map)
	 */
	public void insertProcesoHabilitado(Map criteria) {
		 //lista de oid seleccionado para insertar los que ya no estan 
		List listaProcesoSeleccionado = (List) criteria.get("listaProceso");
		String login = (String)criteria.get("login");
		//obteniendo lista de Procesos ya habilitados 
		List listProcesosHabilitado = mantenimientoMENPlantillaDAO.getListProcesoHabilitado();
		
		if(listProcesosHabilitado.size()==0){
			insertProcesoHabilitado(criteria,listaProcesoSeleccionado,login);
			return ;
		}	
		
		setProcesoSeleccionados(criteria,listaProcesoSeleccionado,listProcesosHabilitado,login);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#setProcesoPaquete(java.util.Map)
	 */
	public void setProcesoPaquete(Map criteria) {
		mantenimientoMENPlantillaDAO.setProcesoPaquete(criteria);
		
	}

	/**
	 * Inserta todos los procesos disponibles seleccionados
	 * @param listaProcesoSeleccionado
	 * @param login
	 */
	private void insertProcesoHabilitado(Map map,List listaProcesoSeleccionado,
			String login) {
		mantenimientoMENPlantillaDAO.insertProcesoHabilitado(map,listaProcesoSeleccionado,login);
	}

	/**
	 * Compara la lista para saber si el proceso debe seer elimina, registrado o no hacer nada con el 
	 * @param criteria 
	 * @param listaProcesoSeleccionado
	 * @param listProcesosHabilitado
	 */
	private void setProcesoSeleccionados(Map criteria, List listaProcesoSeleccionado,
			List listProcesosHabilitado,String login) {
		int sizeProcesoSeleccionado = listaProcesoSeleccionado.size();
		int sizeProcesoHabilitado = listProcesosHabilitado.size();
		
		for(int i=0;i<sizeProcesoHabilitado;i++){
			Map base = (Map)listProcesosHabilitado.get(i);
			eliminaProcesoHabilitados(criteria,(String)base.get("codigoProceso"),listaProcesoSeleccionado,login);			
		}	
		
		for(int i=0;i<sizeProcesoSeleccionado;i++){
			String codigoProceso = (String)listaProcesoSeleccionado.get(i);
			insertaProcesoSeleccionado(criteria,codigoProceso,listProcesosHabilitado,login);			
		}
		
	}

	/**
	 * Realiza la insercion del proceos seleccionado
	 * @param codigoProceso
	 * @param listProcesosHabilitado
	 * @param codigoUsuario
	 */
	private void insertaProcesoSeleccionado(Map map,String codigoProceso,
			List listProcesosHabilitado,String codigoUsuario) {
		boolean valor=false;
		Iterator it = listProcesosHabilitado.iterator();
		while(it.hasNext()){
			Map b= (Map)it.next();
			if(codigoProceso.equals((String)b.get("codigoProceso"))){
				valor=true;
				break;
			}
		}
		if(!valor){
			//Map map = new HashMap();
			map.put("codigoProceso", codigoProceso);
			map.put("codigoUsuario", codigoUsuario);
			mantenimientoMENPlantillaDAO.insertProcesoHabilitado(map);
			map.put("codigoProcesoAnt", "");
			map.put("codigoProcesoAct", codigoProceso);
			mantenimientoMENPlantillaDAO.insertProcesoHabilitadoHisto(map);
		}else{
			map.put("codigoProceso", codigoProceso);
			map.put("codigoUsuario", codigoUsuario);
			mantenimientoMENPlantillaDAO.updateProcesoHabilitado(map);	
			map.put("codigoProcesoAnt", codigoProceso);
			map.put("codigoProcesoAct", codigoProceso);
			mantenimientoMENPlantillaDAO.insertProcesoHabilitadoHisto(map);
		}
		
	}

	/**
	 * Realiza la eliminacion de procesos habilitados
	 * @param map 
	 * @param codigoProceso
	 * @param listaProcesoSeleccionado
	 * @param login 
	 */
	private void eliminaProcesoHabilitados(Map map, String codigoProceso,
			List listaProcesoSeleccionado, String login) {
		boolean valor=false;
		Iterator it = listaProcesoSeleccionado.iterator();
		while(it.hasNext()){
			String codigo= (String)it.next();
			if(codigoProceso.equals(codigo)){
				valor=true;
				break;
			}
		}
		if(!valor){
			//Map map= new HashMap();
			map.put("codigoProceso", codigoProceso);
			mantenimientoMENPlantillaDAO.deleteProcesoHabilitado(map);
			//
			map.put("codigoProcesoAnt", codigoProceso);
			map.put("codigoProcesoAct", "");
			map.put("codigoUsuario", login);
			mantenimientoMENPlantillaDAO.insertProcesoHabilitadoHisto(map);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getProcesosDisponibles(java.util.Map)
	 */
	public List getProcesosDisponibles(Map map) {
		return mantenimientoMENPlantillaDAO.getProcesosDisponibles(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getIndicadorProcesoActivo()
	 */
	public String getIndicadorProcesoActivo() {
		return mantenimientoMENPlantillaDAO.getIndicadorProcesoActivo();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getProcesosHabilitados(java.util.Map)
	 */
	public List getProcesosHabilitados(Map map) {
		return mantenimientoMENPlantillaDAO.getProcesosHabilitados(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#deleteProcesoPlantilla(java.util.Map)
	 */
	public void deleteProcesoPlantilla(Map map) {
		mantenimientoMENPlantillaDAO.deleteProcesoPlantilla(map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#executePedidoCliente(java.util.Map)
	 */
	public void executePedidoCliente(Map map) {
		mantenimientoMENPlantillaDAO.executePedidoCliente(map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getDevuelveCampanaIndicadorCruce(java.util.Map)
	 */
	public Integer getDevuelveCampanaIndicadorCruce(Map map) {
		return mantenimientoMENPlantillaDAO.getDevuelveCampanaIndicadorCruce(map);		
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#getDevuelveCampanaAnterior(java.util.Map)
	 */
	public String getDevuelveCampanaAnterior(Map map) {
	  return	mantenimientoMENPlantillaDAO.getDevuelveCampanaAnterior(map);		
	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENPlantillaService#executeActuaMensajesCampana(java.util.Map)
	 */
	public void executeProcesoActualizarMensajes(Map map) {
		mantenimientoMENPlantillaDAO.executeProcesoActualizarMensajes(map);		
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENPlantillaService#getVerRegionesZonasAprobadasNoProc(java.util.Map)
	 */
	public Integer getVerRegionesZonasAprobadasNoProc(Map map) {
		return mantenimientoMENPlantillaDAO.getVerRegionesZonasAprobadasNoProc(map);	
	}
}
