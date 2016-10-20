package biz.belcorp.ssicc.service.spusicc.mav.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.mav.MantenimientoMAVConfiguracionDAO;
import biz.belcorp.ssicc.dao.spusicc.mav.model.ActividadTipoOferta;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mav.MantenimientoMAVConfiguracionService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoMAVConfiguracionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoMAVConfiguracionServiceImpl extends BaseService implements
	MantenimientoMAVConfiguracionService {
	
	@Resource(name="spusicc.mantenimientoMAVConfiguracionDAO")
	private MantenimientoMAVConfiguracionDAO mantenimientoMAVConfiguracionDAO;
	
	@Resource(name="spusicc.mantenimientoMAEClienteDAO")
	private MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;
	
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getActividades()
	 */
	public List getActividades() {
		return mantenimientoMAVConfiguracionDAO.getActividades();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getTiposOferta(java.lang.String)
	 */
	public List getTiposOferta(String oidActividad) {
		return mantenimientoMAVConfiguracionDAO.getTiposOferta(oidActividad);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getConfiguracionesByCriteria(java.util.Map)
	 */
	public List getConfiguracionesByCriteria(Map criteria) {
		return mantenimientoMAVConfiguracionDAO.getConfiguracionesByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#insertConfiguracion(java.util.Map)
	 */
	public void insertConfiguracion(Map params) {
		String correlativo = mantenimientoMAVConfiguracionDAO.getMaxConfiguracion();
		String codigoPeriodo = MapUtils.getString(params, "codigoPeriodo", "");
		String numeroUnidadesPopupUniDifNO = MapUtils.getString(params, "numeroUnidades", "");
		String indicadorUnidad =  MapUtils.getString(params, "indicadorUnidad", "");
		
		params.put("correlativo", correlativo);
		
		mantenimientoMAVConfiguracionDAO.insertConfiguracion(params);
		
		//se graba las consideraciones y restricciones
		List<Map> listConsi=(List)params.get("listConsi");
		List<Map> listRestri =(List)params.get("listRestri");
		String login = (String)params.get("codigoUsuario");
		
		for(Map bean : listConsi){
			String indicadorTipo = (String)bean.get("indicadorTipo");
			bean.put("correlativoMAV", correlativo);
			String codigoConsRest = (String)bean.get("codigoConsideracion");
			String indicadorAccionConRes = (String)bean.get("indicadorAccion");						
			bean.put("indicadorConsideracion",Constants.MAV_TIPO_CONSIDERACION);
			bean.put("codigoConsRest", codigoConsRest);
			bean.put("login", login);
			bean.put("codigoPeriodo", codigoPeriodo); 
			
			if(Constants.NUMERO_CERO.equals(indicadorAccionConRes)){		
				if(Constants.MAV_TIPO_SIN_CONDICION.equals(indicadorTipo) || (indicadorTipo==null)){
					String correlativoConsideracion=Constants.NUMERO_UNO;
					bean.put("correlativoConsideracion", correlativoConsideracion);
					mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
				}
				
				if(Constants.MAV_TIPO_UNA_CONDICION.equals(indicadorTipo)){
					String correlativoConsideracion=Constants.NUMERO_UNO;
					bean.put("correlativoConsideracion", correlativoConsideracion);
					mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
				}
			
				if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
					String correlativoConsideracion=Constants.NUMERO_UNO;
					bean.put("correlativoConsideracion", correlativoConsideracion);
					mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);					
					mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
				}
				
				if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(indicadorTipo)){
					String correlativoConsideracion=Constants.NUMERO_UNO;
					bean.put("correlativoConsideracion", correlativoConsideracion);
					mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);					
					mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
				}
									
				if(Constants.MAV_TIPO_LISTA_CONDICION.equals(indicadorTipo)
					|| Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo)){
				
					if(Constants.MAV_CONRES_LISTA_CONSU == Integer.parseInt(codigoConsRest)){
						List<Map> consultoraList=(List)bean.get("consultoraList");
						log.debug("consultoraList >>>>>>> "+consultoraList.size());
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
						
						int index=1;
						for(Map m: consultoraList){									
							bean.put("correlativoConsideracion", ""+index);
							bean.put("condicion1", (String)m.get("codigoPais"));
							bean.put("condicion2", (String)m.get("codigoCliente"));
							bean.put("numeroUnidades", (String)m.get("numeroUnidades"));
							String indAccion = (String)m.get("indicadorAccion");
							if(Constants.NUMERO_CERO.equals(indAccion)){
								mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
							}
							index++;
						}
					}
					
					if(Constants.MAV_CONRES_LISTA_REGION_ZONA == Integer.parseInt(codigoConsRest)){
						List<Map> regionesList=(List)bean.get("regionesList");
						log.debug("regionesList >>>>>>> "+regionesList.size());
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
						
						int index=1;
						for(Map m: regionesList){									
							bean.put("correlativoConsideracion", ""+index);
							String codigoRegion = (String)m.get("codigoRegion");
							String codigoZona = (String)m.get("codigoZona");
							String codigoCapacitadora = (String)m.get("codigoCapacitadora");
							bean.put("condicion1", null);
							bean.put("condicion2", null);
							if (StringUtils.isNotBlank(codigoRegion))
								bean.put("condicion1", (String)m.get("codigoRegion"));
							if (StringUtils.isNotBlank(codigoZona))
								bean.put("condicion2", (String)m.get("codigoZona"));
							if (StringUtils.isNotBlank(codigoCapacitadora))
								bean.put("condicion3", (String)m.get("codigoCapacitadora"));
							
							bean.put("numeroUnidades", (String)m.get("numeroUnidades"));							
							if(StringUtils.equals(indicadorUnidad, Constants.NO))
								bean.put("numeroUnidades", numeroUnidadesPopupUniDifNO);
							
							String indAccion = (String)m.get("indicadorAccion");
							if(Constants.NUMERO_CERO.equals(indAccion)){
								mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
							}
							index++;
						}
					}

					if(Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE == Integer.parseInt(codigoConsRest)){
						List<Map> listC =(List)bean.get("listClasificaciones");
						log.debug("listClasificaciones >>>>>>> "+listC.size());
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
						int index=1;
						for(Map m: listC){									
							bean.put("correlativoConsideracion", ""+index);
							bean.put("condicion1", (String)m.get("codigoTipoCliente"));
							bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
							bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
							bean.put("condicion4", (String)m.get("codigoClasificacion"));
							bean.put("numeroUnidades", (String)m.get("numeroUnidades"));
							String indAccion = (String)m.get("indicadorAccion");
							
							if(Constants.NUMERO_CERO.equals(indAccion)){
								mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
							}
							index++;
						}
					}
					
					if(Constants.MAV_CONRES_UNIDAD_ADM == Integer.parseInt(codigoConsRest)){
						List<Map> listC =(List)bean.get("listUnidades");
						log.debug("listUnidades >>>>>>> "+listC.size());
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
						int index=1;
						for(Map m: listC){									
							bean.put("correlativoConsideracion", ""+index);
							bean.put("condicion1", (String)m.get("codigoRegion"));
							bean.put("condicion2", (String)m.get("codigoZona"));
							bean.put("condicion3", (String)m.get("codigoSeccion"));
							bean.put("condicion4", (String)m.get("codigoTerritorio"));
							bean.put("numeroUnidades", (String)m.get("numeroUnidades"));
							String indAccion = (String)m.get("indicadorAccion");
							if(Constants.NUMERO_CERO.equals(indAccion)){
								mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
							}
							index++;
						}
					}
					
					/* INI SA PER-SiCC-2013-0440 */
					if(Constants.MAV_CONRES_ESTATUS_CLIENTE == Integer.parseInt(codigoConsRest)){
						List<Map> list=(List)bean.get("listEstatus");
						log.debug("listEstatus "+list.size());
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
						int index=1;
						for(Map m: list){									
							//bean.put("correlativoConsideracion", ""+index);
							bean.put("condicion1", (String)m.get("codigoEstado"));
							String indAccion = (String)m.get("indicadorAccion");
							if(Constants.NUMERO_CERO.equals(indAccion)){
								mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
							}
							index++;
						}
					}
					/* FIN SA PER-SiCC-2013-0440 */
					
				}//fin lista
			}
		}
		
		for(Map bean : listRestri){
			String indicadorTipo = (String)bean.get("indicadorTipo");
			bean.put("correlativoMAV", correlativo);
			bean.put("indicadorConsideracion",Constants.MAV_TIPO_RESTRICCION);
			String codigoConsRest = (String)bean.get("codigoRestriccion");
			String indicadorAccionConRes = (String)bean.get("indicadorAccion");							
			bean.put("codigoConsRest", codigoConsRest);
			bean.put("login", login);
			bean.put("codigoPeriodo", codigoPeriodo);
			
			if(Constants.NUMERO_CERO.equals(indicadorAccionConRes)){		
				if(Constants.MAV_TIPO_SIN_CONDICION.equals(indicadorTipo) || (indicadorTipo==null)){
					String correlativoConsideracion=Constants.NUMERO_UNO;
					bean.put("correlativoConsideracion", correlativoConsideracion);
					mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
				}
				if(Constants.MAV_TIPO_UNA_CONDICION.equals(indicadorTipo)){
					String correlativoConsideracion=Constants.NUMERO_UNO;
					bean.put("correlativoConsideracion", correlativoConsideracion);
					mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
				}
			
				if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
					String correlativoConsideracion=Constants.NUMERO_UNO;
					bean.put("correlativoConsideracion", correlativoConsideracion);
					mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);					
					mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
				}
				
				if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(indicadorTipo)){
					String correlativoConsideracion=Constants.NUMERO_UNO;
					bean.put("correlativoConsideracion", correlativoConsideracion);
					mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);					
					mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
				}
			
				if(Constants.MAV_TIPO_LISTA_CONDICION.equals(indicadorTipo)
					|| Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo)){

					if(Constants.MAV_CONRES_LISTA_CONSU_REST == Integer.parseInt(codigoConsRest)){
						List<Map> consultoraList=(List)bean.get("consultoraListR");
						log.debug("consultoraListR >>>>>>> "+consultoraList.size());
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					
						int index=1;
						for(Map m: consultoraList){									
							bean.put("correlativoConsideracion", ""+index);
							bean.put("condicion1", (String)m.get("codigoPais"));
							bean.put("condicion2", (String)m.get("codigoCliente"));
							bean.put("numeroUnidades", (String)m.get("numeroUnidades"));
							String indAccion = (String)m.get("indicadorAccion");
							if(Constants.NUMERO_CERO.equals(indAccion)){
								mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
							}
							index++;
						}
					}						
				
					if(Constants.MAV_CONRES_LISTA_REGION_ZONA_REST == Integer.parseInt(codigoConsRest)){
						List<Map> regionesList=(List)bean.get("regionesListR");
						log.debug("regionesListR >>>>>>> "+regionesList.size());
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
						
						int index=1;
						for(Map m: regionesList){									
							bean.put("correlativoConsideracion", ""+index);
							String codigoRegion = (String)m.get("codigoRegion");
							String codigoZona = (String)m.get("codigoZona");
							String codigoCapacitadora = (String)m.get("codigoCapacitadora");
							bean.put("condicion1", null);
							bean.put("condicion2", null);
							if (StringUtils.isNotBlank(codigoRegion))
								bean.put("condicion1", (String)m.get("codigoRegion"));
							if (StringUtils.isNotBlank(codigoZona))
								bean.put("condicion2", (String)m.get("codigoZona"));
							if (StringUtils.isNotBlank(codigoCapacitadora))
								bean.put("condicion3", (String)m.get("codigoCapacitadora"));

							bean.put("numeroUnidades", (String)m.get("numeroUnidades"));
							if(StringUtils.equals(indicadorUnidad, Constants.NO))
								bean.put("numeroUnidades", numeroUnidadesPopupUniDifNO);
							
							String indAccion = (String)m.get("indicadorAccion");
							if(Constants.NUMERO_CERO.equals(indAccion)){
								mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
							}
							index++;
						}
					}
					
					if(Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
						List<Map> listC =(List)bean.get("listClasificacionesR");
						log.debug("listClasificacionesR >>>>>>> "+listC.size());
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
						int index=1;
						for(Map m: listC){									
							bean.put("correlativoConsideracion", ""+index);
							bean.put("condicion1", (String)m.get("codigoTipoCliente"));
							bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
							bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
							bean.put("condicion4", (String)m.get("codigoClasificacion"));
							bean.put("numeroUnidades", (String)m.get("numeroUnidades"));
							String indAccion = (String)m.get("indicadorAccion");
							if(Constants.NUMERO_CERO.equals(indAccion)){
								mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
							}
							index++;
						}
					}
					
					//MAV_CONRES_UNIDAD_ADM
					if(Constants.MAV_CONRES_UNIDAD_ADM_REST == Integer.parseInt(codigoConsRest)){
						List<Map> listC =(List)bean.get("listUnidadesR");
						log.debug("listUnidadesR >>>>>>> "+listC.size());
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
						int index=1;
						for(Map m: listC){									
							bean.put("correlativoConsideracion", ""+index);
							bean.put("condicion1", (String)m.get("codigoRegion"));
							bean.put("condicion2", (String)m.get("codigoZona"));
							bean.put("condicion3", (String)m.get("codigoSeccion"));
							bean.put("condicion4", (String)m.get("codigoTerritorio"));
							bean.put("numeroUnidades", (String)m.get("numeroUnidades"));
							String indAccion = (String)m.get("indicadorAccion");
							if(Constants.NUMERO_CERO.equals(indAccion)){
								mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
							}
							index++;
						}
					}	
					
					/* INI SA PER-SiCC-2013-0440 */
					if(Constants.MAV_CONRES_ESTATUS_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
						List<Map> list=(List)bean.get("listEstatusR");
						log.debug("listEstatusR "+list.size());
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
						int index=1;
						for(Map m: list){									
							//bean.put("correlativoConsideracion", ""+index);
							bean.put("condicion1", (String)m.get("codigoEstado"));
							String indAccion = (String)m.get("indicadorAccion");
							if(Constants.NUMERO_CERO.equals(indAccion)){
								mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
							}
							index++;
						}
					}
					/* FIN SA PER-SiCC-2013-0440 */
					
				}//fin tipo lista R									
			}	
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#updateConfiguracion(java.util.Map)
	 */
	public void updateConfiguracion(Map params) {
		mantenimientoMAVConfiguracionDAO.updateConfiguracion(params);
		String correlativo = params.get("correlativo").toString();
		
		//se graba las consideraciones y restricciones
		List<Map> listConsi=(List)params.get("listConsi");
		List<Map> listRestri =(List)params.get("listRestri");
		String login = (String)params.get("codigoUsuario");
		String codigoPeriodo = MapUtils.getString(params, "codigoPeriodo", ""); 
		String numeroUnidadesPopupUniDifNO = MapUtils.getString(params, "numeroUnidades", "");
		String indicadorUnidad =  MapUtils.getString(params, "indicadorUnidad", "");
		
		for(Map bean : listConsi){
			String indicadorTipo = (String)bean.get("indicadorTipo");
			bean.put("correlativoMAV", correlativo);
			String codigoConsRest = (String)bean.get("codigoConsideracion");
			String indicadorAccionConRes = (String)bean.get("indicadorAccion");						
			bean.put("indicadorConsideracion",Constants.MAV_TIPO_CONSIDERACION);
			bean.put("codigoConsRest", codigoConsRest);
			bean.put("login", login);			
			bean.put("codigoPeriodo", codigoPeriodo);
			
		 if(Constants.NUMERO_CERO.equals(indicadorAccionConRes)){	//CONDICION 0:INSERTAR	
			if(Constants.MAV_TIPO_SIN_CONDICION.equals(indicadorTipo) || (indicadorTipo==null)){
				String correlativoConsideracion=Constants.NUMERO_UNO;
				bean.put("correlativoConsideracion", correlativoConsideracion);
				mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
				mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
			}
			if(Constants.MAV_TIPO_UNA_CONDICION.equals(indicadorTipo)){
				String correlativoConsideracion=Constants.NUMERO_UNO;
				bean.put("correlativoConsideracion", correlativoConsideracion);
				mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
				mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
			}
			
			if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
				String correlativoConsideracion=Constants.NUMERO_UNO;
				bean.put("correlativoConsideracion", correlativoConsideracion);
				mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);					
				mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
			}
			
			if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(indicadorTipo)){
				String correlativoConsideracion=Constants.NUMERO_UNO;
				bean.put("correlativoConsideracion", correlativoConsideracion);
				mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);					
				mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
			}
			
			if(Constants.MAV_TIPO_LISTA_CONDICION.equals(indicadorTipo)
					|| Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo)){
				
				if(Constants.MAV_CONRES_LISTA_CONSU == Integer.parseInt(codigoConsRest)){
					List<Map> listC =(List)bean.get("consultoraList")!= null ? (List)bean.get("consultoraList"): new ArrayList();
					log.debug("consultoraList >>>>>>> "+listC.size());
					if(listC.size()>=0){
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					}
					
					int index=1;
					for(Map m: listC){									
						bean.put("correlativoConsideracion", ""+index);
						bean.put("condicion1", (String)m.get("codigoPais"));
						bean.put("condicion2", (String)m.get("codigoCliente"));
						
						String numeroUnidades = (String)m.get("numeroUnidades");
						if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
						bean.put("numeroUnidades", numeroUnidades);
						
						String indAccion = (String)m.get("indicadorAccion");
						if(Constants.NUMERO_CERO.equals(indAccion)){
							mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
						}
						index++;
					}
				}
				
				if(Constants.MAV_CONRES_LISTA_REGION_ZONA == Integer.parseInt(codigoConsRest)){
					List<Map> listC =(List)bean.get("regionesList")!= null ? (List)bean.get("regionesList"): new ArrayList();
					log.debug("regionesList >>>>>>> "+listC.size());
					if(listC.size()>=0){
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					}
					
					int index=1;
					for(Map m: listC){									
						bean.put("correlativoConsideracion", ""+index);
						bean.put("condicion1", (String)m.get("codigoRegion"));
						bean.put("condicion2", (String)m.get("codigoZona"));
						bean.put("condicion3", (String)m.get("codigoCapacitadora"));
						String numeroUnidades = (String)m.get("numeroUnidades");
						if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;

						bean.put("numeroUnidades", numeroUnidades);
						if(StringUtils.equals(indicadorUnidad, Constants.NO))
							bean.put("numeroUnidades", numeroUnidadesPopupUniDifNO);
						
						String indAccion = (String)m.get("indicadorAccion");
						if(Constants.NUMERO_CERO.equals(indAccion)){
							mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
						}
						index++;
					}
				}

				if(Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE == Integer.parseInt(codigoConsRest)){
					List<Map> listC =(List)bean.get("listClasificaciones")!= null ? (List)bean.get("listClasificaciones"): new ArrayList();
					log.debug("listClasificaciones >>>>>>> "+listC.size());
					if(listC.size()>=0){
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					}
					int index=1;
					for(Map m: listC){									
						bean.put("correlativoConsideracion", ""+index);
						bean.put("condicion1", (String)m.get("codigoTipoCliente"));
						bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
						bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
						bean.put("condicion4", (String)m.get("codigoClasificacion"));
						
						String numeroUnidades = (String)m.get("numeroUnidades");
						if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
						bean.put("numeroUnidades", numeroUnidades);
						String indAccion = (String)m.get("indicadorAccion");
						if(Constants.NUMERO_CERO.equals(indAccion)){
							mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
						}
						index++;
					}
				}
				if(Constants.MAV_CONRES_UNIDAD_ADM == Integer.parseInt(codigoConsRest)){
					List<Map> listC =(List)bean.get("listUnidades")!= null ? (List)bean.get("listUnidades"): new ArrayList();
					log.debug("listUnidades >>>>>>> "+listC.size());
					if(listC.size()>=0){
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					}
					int index=1;
					for(Map m: listC){									
						bean.put("correlativoConsideracion", ""+index);
						bean.put("condicion1", (String)m.get("codigoRegion"));
						bean.put("condicion2", (String)m.get("codigoZona"));
						bean.put("condicion3", (String)m.get("codigoSeccion"));
						bean.put("condicion4", (String)m.get("codigoTerritorio"));
						String numeroUnidades = (String)m.get("numeroUnidades");
						if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
						bean.put("numeroUnidades", numeroUnidades);
						
						String indAccion = (String)m.get("indicadorAccion");
						if(Constants.NUMERO_CERO.equals(indAccion)){
							mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
						}
						index++;
					}
				}											
				
				/* INI SA PER-SiCC-2013-0440 */
				if(Constants.MAV_CONRES_ESTATUS_CLIENTE == Integer.parseInt(codigoConsRest)){
					List<Map> list=(List)bean.get("listEstatus")!= null ? (List)bean.get("listEstatus"): new ArrayList();
					log.debug("listEstatus "+list.size());
					if(list.size()>0){
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					}
					int index=1;
					for(Map m: list){									
						//bean.put("correlativoConsideracion", ""+index);
						bean.put("condicion1", (String)m.get("codigoEstado"));
						String indAccion = (String)m.get("indicadorAccion");
						if(Constants.NUMERO_CERO.equals(indAccion)){
							mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
						}
						index++;
					}
				}
				/* FIN SA PER-SiCC-2013-0440 */
				
			}//fin sublistas 
		   }//fin accion:0
		 
		 	//accion : 1
			 String indicadorModificado = (String)bean.get("indicadorModificado");
			 if(Constants.NUMERO_UNO.equals(indicadorAccionConRes)){	
				 
				 bean.put("indicadorActivo", Constants.ESTADO_ACTIVO);
					
				   if(Constants.NUMERO_UNO.equals(indicadorModificado)){ 
						if(Constants.MAV_TIPO_SIN_CONDICION.equals(indicadorTipo) || (indicadorTipo==null)){
							String correlativoConsideracion=Constants.NUMERO_UNO;
							bean.put("correlativoConsideracion", correlativoConsideracion);
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
						}
						if(Constants.MAV_TIPO_UNA_CONDICION.equals(indicadorTipo)){
							String correlativoConsideracion=Constants.NUMERO_UNO;
							bean.put("correlativoConsideracion", correlativoConsideracion);
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
						}
						
						if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
							String correlativoConsideracion=Constants.NUMERO_UNO;
							bean.put("correlativoConsideracion", correlativoConsideracion);
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);					
							mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
						}
						
						if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(indicadorTipo)){
							String correlativoConsideracion=Constants.NUMERO_UNO;
							bean.put("correlativoConsideracion", correlativoConsideracion);
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);					
							mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
						}
						
				   }else{							
					if((Constants.MAV_TIPO_LISTA_CONDICION.equals(indicadorTipo) || Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo) )){
						
						if(Constants.MAV_CONRES_LISTA_CONSU == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("consultoraList")!= null ? (List)bean.get("consultoraList"): new ArrayList();
							log.debug("consultoraList >>>>>>> "+listC.size());
							
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							
							for(Map m: listC){									
								bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
								bean.put("condicion1", (String)m.get("codigoPais"));
								bean.put("condicion2", (String)m.get("codigoCliente"));
								String numeroUnidades = (String)m.get("numeroUnidades");
								if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
								bean.put("numeroUnidades", numeroUnidades);
								
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
								}
								if(Constants.NUMERO_DOS.equals(indAccion) && (m.get("correlativoConsideracion")!=null)){
									bean.put("indicadorActivo", Constants.NUMERO_CERO);
									bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);			
									mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
									bean.put("indicadorActivo", Constants.NUMERO_UNO);
									bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
								}
							}
						}
						
						if(Constants.MAV_CONRES_LISTA_REGION_ZONA == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("regionesList")!= null ? (List)bean.get("regionesList"): new ArrayList();
							log.debug("regionesList >>>>>>> "+listC.size());
							
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							
							for(Map m: listC){									
								bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
								bean.put("condicion1", (String)m.get("codigoRegion"));
								bean.put("condicion2", (String)m.get("codigoZona"));
								bean.put("condicion3", (String)m.get("codigoCapacitadora"));
								String numeroUnidades = (String)m.get("numeroUnidades");
								if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
								bean.put("numeroUnidades", numeroUnidades);
								if(StringUtils.equals(indicadorUnidad, Constants.NO))
									bean.put("numeroUnidades", numeroUnidadesPopupUniDifNO);								
								
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
								}
								if(Constants.NUMERO_DOS.equals(indAccion) && (m.get("correlativoConsideracion")!=null)){
									bean.put("indicadorActivo", Constants.NUMERO_CERO);
									bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);			
									mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
									bean.put("indicadorActivo", Constants.NUMERO_UNO);
									bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
								}
							}
						}
						
						if(Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listClasificaciones")!= null ? (List)bean.get("listClasificaciones"): new ArrayList();
							log.debug("listClasificaciones >>>>>>> "+listC.size());
							
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							
							for(Map m: listC){									
								
								bean.put("condicion1", (String)m.get("codigoTipoCliente"));
								bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
								bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
								bean.put("condicion4", (String)m.get("codigoClasificacion"));
								String numeroUnidades = (String)m.get("numeroUnidades");
								if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
								bean.put("numeroUnidades", numeroUnidades);
								
								bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
								}
								if(Constants.NUMERO_DOS.equals(indAccion) && (m.get("correlativoConsideracion")!=null)){
									bean.put("indicadorActivo", Constants.NUMERO_CERO);
									bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
									mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
									bean.put("indicadorActivo", Constants.NUMERO_UNO);
									bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
								}																
								
							}
						}
						if(Constants.MAV_CONRES_UNIDAD_ADM == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listUnidades")!= null ? (List)bean.get("listUnidades"): new ArrayList();
							log.debug("listUnidades >>>>>>> "+listC.size());
							
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							
							for(Map m: listC){									
								String codigoSeccion = (String)m.get("codigoSeccion");
								String codigoTerritorio = (String)m.get("codigoTerritorio");
								String numeroUnidades = (String)m.get("numeroUnidades");
								if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
								bean.put("condicion1", (String)m.get("codigoRegion"));
								bean.put("condicion2", (String)m.get("codigoZona"));
								bean.put("condicion3", codigoSeccion);
								bean.put("condicion4", codigoTerritorio);
								bean.put("numeroUnidades", numeroUnidades);
								bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
								}
								
								if(Constants.NUMERO_DOS.equals(indAccion) && (m.get("correlativoConsideracion")!=null)){
									bean.put("indicadorActivo", Constants.NUMERO_CERO);
									bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
									mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
									bean.put("indicadorActivo", Constants.NUMERO_UNO);
									bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
								}																
							}
						}		
						
						/* INI SA PER-SiCC-2013-0440 */
						if(Constants.MAV_CONRES_ESTATUS_CLIENTE == Integer.parseInt(codigoConsRest)){
							List<Map> list=(List)bean.get("listEstatus")!= null ? (List)bean.get("listEstatus"): new ArrayList();
							log.debug("listEstatus "+list.size());
							
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							
							for(Map m: list){																			
								bean.put("condicion1", (String)m.get("codigoEstado"));
								bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
								}
								if(Constants.NUMERO_DOS.equals(indAccion) && (m.get("correlativoConsideracion")!=null)){
									bean.put("indicadorActivo", Constants.NUMERO_CERO);
									bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
									mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
									bean.put("indicadorActivo", Constants.NUMERO_UNO);
									bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
								}										
								
							}
						}
						/* FIN SA PER-SiCC-2013-0440 */
						
					}//fin sublistas cuando consideraciones es 1						    
				   }//fin del else
					
				   //Actualizamos la campaa de la cabecera y los detalles
				   mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
				   mantenimientoMAVConfiguracionDAO.updateConRestDetalleCampanya(bean);
				   //	
					
			 }//fin accion:1
			 
			 //accion:2
			if(Constants.NUMERO_DOS.equals(indicadorAccionConRes)){
				    bean.put("indicadorActivo", Constants.NUMERO_CERO);
				    bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);
				    bean.put("correlativoConsideracion", null);
				    mantenimientoMAVConfiguracionDAO.deleteConRestDetalle(bean);
				    mantenimientoMAVConfiguracionDAO.deleteConRestCabecera(bean);							
			}//fin accion :2							 
		 			 
															
		}//fin recorrido lista de consideraciones
		
		for(Map bean : listRestri){
			String indicadorTipo = (String)bean.get("indicadorTipo");
			bean.put("correlativoMAV", correlativo);
			bean.put("indicadorConsideracion",Constants.MAV_TIPO_RESTRICCION);
			String codigoConsRest = (String)bean.get("codigoRestriccion");
			String indicadorAccionConRes = (String)bean.get("indicadorAccion");							
			bean.put("codigoConsRest", codigoConsRest);
			bean.put("login", login);			
			bean.put("codigoPeriodo", codigoPeriodo);
			
		  if(Constants.NUMERO_CERO.equals(indicadorAccionConRes)){		
			if(Constants.MAV_TIPO_SIN_CONDICION.equals(indicadorTipo) || (indicadorTipo==null)){
				String correlativoConsideracion=Constants.NUMERO_UNO;
				bean.put("correlativoConsideracion", correlativoConsideracion);
				mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
				mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
			}
			if(Constants.MAV_TIPO_UNA_CONDICION.equals(indicadorTipo)){
				String correlativoConsideracion=Constants.NUMERO_UNO;
				bean.put("correlativoConsideracion", correlativoConsideracion);
				mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
				mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
			}
			
			if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
				String correlativoConsideracion=Constants.NUMERO_UNO;
				bean.put("correlativoConsideracion", correlativoConsideracion);
				mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);					
				mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
			}	
			
			if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(indicadorTipo)){
				String correlativoConsideracion=Constants.NUMERO_UNO;
				bean.put("correlativoConsideracion", correlativoConsideracion);
				mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);					
				mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
			}
			
			if(Constants.MAV_TIPO_LISTA_CONDICION.equals(indicadorTipo)
					|| Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo)){
				if(Constants.MAV_CONRES_LISTA_CONSU_REST == Integer.parseInt(codigoConsRest)){
					List<Map> listC =(List)bean.get("consultoraListR")!= null ? (List)bean.get("consultoraListR"): new ArrayList();
					log.debug("consultoraListR >>>>>>> "+listC.size());
					if(listC.size()>=0){
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					}
					
					int index=1;
					for(Map m: listC){									
						bean.put("correlativoConsideracion", ""+index);
						bean.put("condicion1", (String)m.get("codigoPais"));
						bean.put("condicion2", (String)m.get("codigoCliente"));
						String numeroUnidades = (String)m.get("numeroUnidades");
						if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
						bean.put("numeroUnidades", numeroUnidades);
						String indAccion = (String)m.get("indicadorAccion");
						if(Constants.NUMERO_CERO.equals(indAccion)){
							mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
						}
						index++;
					}
				}

				if(Constants.MAV_CONRES_LISTA_REGION_ZONA_REST == Integer.parseInt(codigoConsRest)){
					List<Map> listC =(List)bean.get("regionesListR")!= null ? (List)bean.get("regionesListR"): new ArrayList();
					log.debug("regionesListR >>>>>>> "+listC.size());
					if(listC.size()>=0){
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					}
					
					int index=1;
					for(Map m: listC){									
						bean.put("correlativoConsideracion", ""+index);
						bean.put("condicion1", (String)m.get("codigoRegion"));
						bean.put("condicion2", (String)m.get("codigoZona"));
						String numeroUnidades = (String)m.get("numeroUnidades");
						if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
						
						bean.put("numeroUnidades", numeroUnidades);
						if(StringUtils.equals(indicadorUnidad, Constants.NO))
							bean.put("numeroUnidades", numeroUnidadesPopupUniDifNO);
						
						String indAccion = (String)m.get("indicadorAccion");
						if(Constants.NUMERO_CERO.equals(indAccion)){
							mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
						}
						index++;
					}
				}
				
				if(Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
					List<Map> listC =(List)bean.get("listClasificacionesR")!= null ? (List)bean.get("listClasificacionesR"): new ArrayList();
					log.debug("listClasificacionesR >>>>>>> "+listC.size());
					if(listC.size()>=0){
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					}
					int index=1;
					for(Map m: listC){									
						bean.put("correlativoConsideracion", ""+index);
						bean.put("condicion1", (String)m.get("codigoTipoCliente"));
						bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
						bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
						bean.put("condicion4", (String)m.get("codigoClasificacion"));
						String numeroUnidades = (String)m.get("numeroUnidades");
						if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
						bean.put("numeroUnidades", numeroUnidades);
						String indAccion = (String)m.get("indicadorAccion");
						if(Constants.NUMERO_CERO.equals(indAccion)){
							mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
						}
						index++;
					}
				}
				//MAV_CONRES_UNIDAD_ADM
				if(Constants.MAV_CONRES_UNIDAD_ADM_REST == Integer.parseInt(codigoConsRest)){
					List<Map> listC =(List)bean.get("listUnidadesR")!= null ? (List)bean.get("listUnidadesR"): new ArrayList();
					log.debug("listUnidadesR >>>>>>> "+listC.size());
					if(listC.size()>=0){
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					}
					int index=1;
					for(Map m: listC){									
						bean.put("correlativoConsideracion", ""+index);
						bean.put("condicion1", (String)m.get("codigoRegion"));
						bean.put("condicion2", (String)m.get("codigoZona"));
						bean.put("condicion3", (String)m.get("codigoSeccion"));
						bean.put("condicion4", (String)m.get("codigoTerritorio"));
						String numeroUnidades = (String)m.get("numeroUnidades");
						if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
						bean.put("numeroUnidades", numeroUnidades);
						String indAccion = (String)m.get("indicadorAccion");
						if(Constants.NUMERO_CERO.equals(indAccion)){
							mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
						}
						index++;
					}
				}	
				
				/* INI SA PER-SiCC-2013-0440 */
				if(Constants.MAV_CONRES_ESTATUS_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
					List<Map> list=(List)bean.get("listEstatusR")!= null ? (List)bean.get("listEstatusR"): new ArrayList();
					log.debug("listEstatusR "+list.size());
					if(list.size()>0){
						mantenimientoMAVConfiguracionDAO.saveConRestCabecera(bean);
					}
					int index=1;
					for(Map m: list){									
						//bean.put("correlativoConsideracion", ""+index);
						bean.put("condicion1", (String)m.get("codigoEstado"));
						String indAccion = (String)m.get("indicadorAccion");
						if(Constants.NUMERO_CERO.equals(indAccion)){
							mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
						}
						index++;
					}
				}
				/* FIN SA PER-SiCC-2013-0440 */
			  	
			}//fin tipo lista R									
		  }//fin accion :0
		  
		 	//accion : 1
			 String indicadorModificado = (String)bean.get("indicadorModificado");
			 if(Constants.NUMERO_UNO.equals(indicadorAccionConRes)){	
				    bean.put("indicadorActivo", Constants.ESTADO_ACTIVO);
					
				   if(Constants.NUMERO_UNO.equals(indicadorModificado) || (indicadorTipo==null)){ 
						if(Constants.MAV_TIPO_SIN_CONDICION.equals(indicadorTipo)){
							String correlativoConsideracion=Constants.NUMERO_UNO;
							bean.put("correlativoConsideracion", correlativoConsideracion);
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
						}
						if(Constants.MAV_TIPO_UNA_CONDICION.equals(indicadorTipo)){
							String correlativoConsideracion=Constants.NUMERO_UNO;
							bean.put("correlativoConsideracion", correlativoConsideracion);
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
						}
						
						if(Constants.MAV_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
							String correlativoConsideracion=Constants.NUMERO_UNO;
							bean.put("correlativoConsideracion", correlativoConsideracion);
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);					
							mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
						}
						
						if(Constants.MAV_TIPO_CONDICIONES_MIXTAS.equals(indicadorTipo)){
							String correlativoConsideracion=Constants.NUMERO_UNO;
							bean.put("correlativoConsideracion", correlativoConsideracion);
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);					
							mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
						}
						
				   }else{							
					if((Constants.MAV_TIPO_LISTA_CONDICION.equals(indicadorTipo) 
							|| Constants.MAV_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo) )){
						
						if(Constants.MAV_CONRES_LISTA_CONSU_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("consultoraListR")!= null ? (List)bean.get("consultoraListR"): new ArrayList();
							log.debug("consultoraListR >>>>>>> "+listC.size());
							
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							
							for(Map m: listC){									
								bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
								bean.put("condicion1", (String)m.get("codigoPais"));
								bean.put("condicion2", (String)m.get("codigoCliente"));
								String numeroUnidades = (String)m.get("numeroUnidades");
								if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
								bean.put("numeroUnidades", numeroUnidades);
								
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
								}
								if(Constants.NUMERO_DOS.equals(indAccion) && (m.get("correlativoConsideracion")!=null)){
									bean.put("indicadorActivo", Constants.NUMERO_CERO);
									bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
									mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
									bean.put("indicadorActivo", Constants.NUMERO_UNO);
									bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
								}
							}
						}
						
						if(Constants.MAV_CONRES_LISTA_REGION_ZONA_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("regionesListR")!= null ? (List)bean.get("regionesListR"): new ArrayList();
							log.debug("regionesListR >>>>>>> "+listC.size());
							
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);

							for(Map m: listC){									
								bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
								bean.put("condicion1", (String)m.get("codigoRegion"));
								bean.put("condicion2", (String)m.get("codigoZona"));
								String numeroUnidades = (String)m.get("numeroUnidades");
								if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;

								bean.put("numeroUnidades", numeroUnidades);
								if(StringUtils.equals(indicadorUnidad, Constants.NO))
									bean.put("numeroUnidades", numeroUnidadesPopupUniDifNO);
								
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
								}
								if(Constants.NUMERO_DOS.equals(indAccion) && (m.get("correlativoConsideracion")!=null)){
									bean.put("indicadorActivo", Constants.NUMERO_CERO);
									bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);			
									mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
									bean.put("indicadorActivo", Constants.NUMERO_UNO);
									bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
								}
							}
						}
						
						if(Constants.MAV_CONRES_CLASIFICACIONES_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listClasificacionesR")!= null ? (List)bean.get("listClasificacionesR"): new ArrayList();
							log.debug("listClasificaciones >>>>>>> "+listC.size());
							
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							
							for(Map m: listC){									
								
								bean.put("condicion1", (String)m.get("codigoTipoCliente"));
								bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
								bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
								bean.put("condicion4", (String)m.get("codigoClasificacion"));
								String numeroUnidades = (String)m.get("numeroUnidades");
								if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
								bean.put("numeroUnidades", numeroUnidades);
								
								bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
								}
								if(Constants.NUMERO_DOS.equals(indAccion) && (m.get("correlativoConsideracion")!=null)){
									bean.put("indicadorActivo", Constants.NUMERO_CERO);
									bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
									mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
									bean.put("indicadorActivo", Constants.NUMERO_UNO);
									bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
								}																
								
							}
						}
						if(Constants.MAV_CONRES_UNIDAD_ADM_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listUnidadesR")!= null ? (List)bean.get("listUnidadesR"): new ArrayList();
							log.debug("listUnidades >>>>>>> "+listC.size());
							
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							
							for(Map m: listC){									
								
								bean.put("condicion1", (String)m.get("codigoRegion"));
								bean.put("condicion2", (String)m.get("codigoZona"));
								bean.put("condicion3", (String)m.get("codigoSeccion"));
								bean.put("condicion4", (String)m.get("codigoTerritorio"));
								String numeroUnidades = (String)m.get("numeroUnidades");
								if (StringUtils.isBlank(numeroUnidades) || numeroUnidades == "null") numeroUnidades = null;
								bean.put("numeroUnidades", numeroUnidades);
								
								bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
								}
								if(Constants.NUMERO_DOS.equals(indAccion) && (m.get("correlativoConsideracion")!=null)){
									bean.put("indicadorActivo", Constants.NUMERO_CERO);
									bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
									mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
									bean.put("indicadorActivo", Constants.NUMERO_UNO);
									bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
								}																
							}
						}
						
						/* INI SA PER-SiCC-2013-0440 */
						if(Constants.MAV_CONRES_ESTATUS_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
							List<Map> list=(List)bean.get("listEstatusR")!= null ? (List)bean.get("listEstatusR"): new ArrayList();
							log.debug("listEstatus "+list.size());
							
							mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
							
							for(Map m: list){									
								
								bean.put("condicion1", (String)m.get("codigoEstado"));
								bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
								}
								if(Constants.NUMERO_DOS.equals(indAccion) && (m.get("correlativoConsideracion")!=null)){
									bean.put("indicadorActivo", Constants.NUMERO_CERO);
									bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
									mantenimientoMAVConfiguracionDAO.updateConRestDetalle(bean);
									bean.put("indicadorActivo", Constants.NUMERO_UNO);
									bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
								}										
								
							}
						}
						/* FIN SA PER-SiCC-2013-0440 */
						
					}//fin sublistas cuando consideraciones es 1						    
				   }//fin del else
									
				   //Actualizamos la campaa de la cabecera y los detalles
				   mantenimientoMAVConfiguracionDAO.updateConRestCabecera(bean);
				   mantenimientoMAVConfiguracionDAO.updateConRestDetalleCampanya(bean);
				   //	
				   
			 }//fin accion:1				
			 			 
			   //accion :2	 
		   if(Constants.NUMERO_DOS.equals(indicadorAccionConRes)){
				    bean.put("indicadorActivo", Constants.NUMERO_CERO);
				    bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);
				    bean.put("correlativoConsideracion", null);
				    mantenimientoMAVConfiguracionDAO.deleteConRestDetalle(bean);
				    mantenimientoMAVConfiguracionDAO.deleteConRestCabecera(bean);									
		   }//fin accion :2					 
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#deleteConfiguracion(java.util.Map)
	 */
	public void deleteConfiguracion(Map params) throws Exception {
		Usuario usuario = (Usuario)params.get("usuario");
		
		mantenimientoMAVConfiguracionDAO.deleteConfiguracion(params);
		
		mantenimientoMAVConfiguracionDAO.updateEnviosNoAtendidos(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getConfiguracion(java.util.Map)
	 */
	public Map getConfiguracion(Map criteria) {
		return mantenimientoMAVConfiguracionDAO.getConfiguracion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getConsideracion(java.util.Map)
	 */
	public List getConsideracion(Map criteria) {
		return mantenimientoMAVConfiguracionDAO.getConsideracion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getRestriccion(java.util.Map)
	 */
	public List getRestriccion(Map criteria) {
		return mantenimientoMAVConfiguracionDAO.getRestriccion(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getRestConsideracion(java.util.Map)
	 */
	public List getRestConsideracion(Map criteria) {
		return mantenimientoMAVConfiguracionDAO.getRestConsideracion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getDetalleConsRest(java.util.Map)
	 */
	public List getDetalleConsRest(Map criteria) {
		String codigoConsRest = (String)criteria.get("codigoConsRest");
		
		boolean esListaClientes = false;
		if(Constants.MAV_CONRES_LISTA_CONSU == Integer.parseInt(codigoConsRest) || 
				Constants.MAV_CONRES_LISTA_CONSU_REST == Integer.parseInt(codigoConsRest))
			esListaClientes = true;

		if(esListaClientes)
			return mantenimientoMAVConfiguracionDAO.getDetalleClientesConsRest(criteria);
		else
			return mantenimientoMAVConfiguracionDAO.getDetalleConsRest(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#cargarArchivoExcel(java.util.Map)
	 */
	public List cargarArchivoExcel(Map criteria) throws Exception {
		List listResultado = new ArrayList();
		List listMapFila = new ArrayList();
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String oidPais = String.valueOf(criteria.get("oidPais"));
		Map mapClientes = (Map)criteria.get("mapClientes");
		
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		int fila=0;
		int numFilaError=0;
		
		Map mapClientesAux = new HashMap();
		
		boolean errorPorFila =false; 
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			fila +=1;
			errorPorFila= false;

			//obtenemos el codigo de cliente	
			String codigoPais=(String)mapRow.get("0");
			String codigoCliente=(String)mapRow.get("1");
			String numeroUnidades=(String)mapRow.get("2");
			
			String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data
			 //
			 while(fila<Integer.parseInt(filaExcel)){
				 numFilaError+=1;
				 anhadirFilaVacia(listMapFila,fila,numFilaError,usuario);				 
				 fila +=1;
			 }
			 
			 //validaciones
			 String mensajeError=null;
			 
			 if(StringUtils.isEmpty(codigoPais)){
				 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.sinValorCodigoPais",null,
						 									getLocale(usuario));
				 errorPorFila=true;
			 }
			 if(!errorPorFila && StringUtils.isEmpty(codigoCliente)){
				 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.sinValorCodigoConsultora",null,
						 									getLocale(usuario));
				 errorPorFila=true;
			 }
			 if(!errorPorFila && StringUtils.isEmpty(numeroUnidades)){
				 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.sinValorCantidad",null,
						 									getLocale(usuario));
				 errorPorFila=true;
			 }	 
			 
			 /* INI SA PER-SiCC-2013-0440 */
			 if(!errorPorFila && StringUtils.isNotEmpty(codigoPais)){	
				 oidPais = obtenerOidPais(codigoPais);
				 if(oidPais == null){
					 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.noExistePais",null,
							 								getLocale(usuario));
					 errorPorFila=true;
				 }
			 }
			 /* FIN SA PER-SiCC-2013-0440 */
			 
			 if(!errorPorFila && StringUtils.isNotEmpty(codigoCliente)){			
				 if(!existeConsultora(oidPais,codigoCliente)){
					 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.noExisteConsultora",null,
							 								getLocale(usuario));
					 errorPorFila=true;
				 }
			 }
			 
			 if(!errorPorFila && StringUtils.isNotEmpty(numeroUnidades)){
				 if(!validoFormatoNumerico(numeroUnidades)){
				    mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.noFormatoCantidad",null,
						 									getLocale(usuario));
				    errorPorFila=true;
				 } else {
					int varIntDato = numeroUnidades.indexOf('.');
					if (varIntDato >= 0 ){
						numeroUnidades = numeroUnidades.substring(0, varIntDato);
					} 
				 }
			 }
			 if(!errorPorFila && StringUtils.isNotEmpty(numeroUnidades)){
				 Integer valor = Integer.parseInt(numeroUnidades);
				 if(valor == 0) {
				    mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.validarCantidad",null,
						 									getLocale(usuario));
				    errorPorFila=true;
				 } 
			 }
			 
			 if(!errorPorFila && mapClientes.get(codigoCliente)!=null){
				 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.consultoraDuplicada",null,
							getLocale(usuario));
				 errorPorFila=true;
			 }
			 
			 if(!errorPorFila && mapClientesAux.get(codigoCliente)!=null){
				 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaClientesForm.error.consultoraDuplicada",null,
							getLocale(usuario));
				 errorPorFila=true;
			 }
			 
			//Lo almacenamos en una lista temporal
			Map mapFila = new HashMap();
			mapFila.put("numeroFila", String.valueOf(fila));
			mapFila.put("codigoPais", codigoPais);
			mapFila.put("codigoCliente", codigoCliente);
			
			if(!errorPorFila) {
				mapFila.put("nombreCliente", obtenerNombreCliente(oidPais, codigoCliente));
				mapClientesAux.put(codigoCliente, "");
			} else {
				numFilaError+=1;
			}

			mapFila.put("numeroUnidades", numeroUnidades);
			mapFila.put("mensajeError", mensajeError);
			mapFila.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
			mapFila.put("numErrores", String.valueOf(numFilaError));
			listMapFila.add(mapFila);
		}
		
		excelUtil.cerrar();
		
		listResultado.add(listMapFila);
		listResultado.add(mapClientesAux);
		
		return listResultado;
	}

	/**
	 * Anhade fila vacia del excel a la lista que contiene informacion de excel procesado
	 * @param listMapFila
	 * @param numLote 
	 * @param fila
	 * @param numFilaError 
	 * @param usuario
	 */
	private void anhadirFilaVacia(List listMapFila, int fila, int numFilaError, Usuario usuario) {
		Map mapFila = new HashMap();
		mapFila.put("numeroFila", String.valueOf(fila));
		mapFila.put("codigoCliente", "");
		mapFila.put("mensajeError", messageSource.getMessage("procesoINCDarPorAtendidoBolsaFaltantesForm.error.noRegistroFila",
											null,getLocale(usuario)));
		mapFila.put("numErrores", String.valueOf(numFilaError));
		listMapFila.add(mapFila);		
		
	}

	/**
	 * Retorna true si la consultora existe, false caso contrario
	 * @param oidPais
	 * @param codigoCliente
	 * @return
	 */
	private boolean existeConsultora(String oidPais, String codigoCliente) {
		Map map = new HashMap();
		map.put("oidPais", oidPais);
		map.put("codigoCliente", codigoCliente);
		String codCliente = mantenimientoMAEClienteDAO.getExisteCodigoCliente(map);
		log.debug("existe consultora " + codCliente);
		return (codigoCliente.equals(codCliente)?true:false);
	}
	
	

	/**
	 * @param numero
	 * @return
	 */
	private boolean validoFormatoNumerico(String numero) {
		boolean valor=true;
		try{
			Double.parseDouble(numero);
		}catch(Exception e){
			valor=false;
		}
		return valor;
	}

	/**
	 * @param oidPais
	 * @param codigoCliente
	 * @return
	 */
	private String obtenerNombreCliente(String oidPais, String codigoCliente) {
		Map criteria = new HashMap();

		criteria.put("oidPais", oidPais);
		criteria.put("codigoCliente", codigoCliente);
		
		Cliente cliente = mantenimientoMAEClienteDAO.getExisteCodigoClienteByCodPais(criteria);
		String nombreCliente = (cliente.getApellido1() != null ? cliente
						.getApellido1() : " ")
				+ " "
				+ (cliente.getApellido2() != null ? cliente
						.getApellido2() : " ")
				+ " "
				+ (cliente.getNombre1() != null ? cliente.getNombre1()
						: " ")
				+ " "
				+ (cliente.getNombre2() != null ? cliente.getNombre2()
						: " ");
		
		return nombreCliente;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getEstados()
	 */
	public List getEstados() {
		return mantenimientoMAVConfiguracionDAO.getEstados();		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#cargarRegionesArchivoExcel(java.util.Map)
	 */
	public List cargarRegionesArchivoExcel(Map criteria) throws Exception {
		List listResultado = new ArrayList();
		List listMapFila = new ArrayList();
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String oidPais = String.valueOf(criteria.get("oidPais"));
		String indicadorUnidadPopup = String.valueOf(criteria.get("indicadorUnidadPopup"));
		String indicadorCapacitadora = (String)criteria.get("indicadorCapacitadora");
		String numeroUnidadesPopupUniDifNO = MapUtils.getString(criteria, "numeroUnidadesPopupUniDifNO", "0");
		
		Map mapRegiones = (Map)criteria.get("mapRegiones");
		Map mapCapacitadoras = (Map)criteria.get("mapCapacitadoras");
		
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		int fila=0;
		int numFilaError=0;
		
		Map mapRegionesAux = new HashMap();
		Map mapCapacitadorasAux = new HashMap();
		boolean errorPorFila =false; 
		
		/* CASO NORMAL: REGION / ZONA Columna0: REGION / Columna1: ZONA / Columna2: UNIDADES */
		if (StringUtils.equals(indicadorCapacitadora, Constants.NO)) {
			while(excelUtil.hasNext()) {
				Map mapRow = excelUtil.next();
				fila +=1;
				errorPorFila= false;
	
				//obtenemos el codigo de cliente	
				String codigoRegion=obtenerValorLimpio((String)mapRow.get("0"));
				String codigoZona=obtenerValorLimpio((String)mapRow.get("1"));
				String numeroUnidades="";
				
				if(Constants.SI.equals(indicadorUnidadPopup))
					numeroUnidades=(String)mapRow.get("2");
				else
					numeroUnidades = numeroUnidadesPopupUniDifNO;
				
				String codigoRegionZona = codigoRegion;
				if(StringUtils.isNotEmpty(codigoZona)) {
					codigoRegionZona = codigoRegionZona + "-" + codigoZona;
				}
				
				String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data
				 //
				 while(fila<Integer.parseInt(filaExcel)){
					 numFilaError+=1;
					 anhadirFilaVaciaRegiones(listMapFila,fila,numFilaError,usuario);				 
					 fila +=1;
				 }
				 
				 //validaciones
				 String mensajeError=null;
				 
				 if(StringUtils.isEmpty(codigoRegion)){
					 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.sinValorCodigoRegion",null,
							 									getLocale(usuario));
					 errorPorFila=true;
				 }
				 if(!errorPorFila && Constants.SI.equals(indicadorUnidadPopup) && StringUtils.isEmpty(numeroUnidades)){
					 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.sinValorCantidad",null,
							 									getLocale(usuario));
					 errorPorFila=true;
				 }	 
				 
				 if(!errorPorFila && StringUtils.isNotEmpty(codigoRegion)){			
					 if(!mantenimientoMAVConfiguracionDAO.existeRegion(codigoRegion)){
						 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noExisteRegion",null,
								 								getLocale(usuario));
						 errorPorFila=true;
					 }
				 }
				 
				 if(!errorPorFila && StringUtils.isNotEmpty(codigoZona)){			
					 if(!mantenimientoMAVConfiguracionDAO.existeZona(codigoZona)){
						 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noExisteZona",null,
								 								getLocale(usuario));
						 errorPorFila=true;
					 }
				 }
				 
				 if(!errorPorFila && StringUtils.isNotEmpty(codigoRegion) && StringUtils.isNotEmpty(codigoZona)){			
					 if(!mantenimientoMAVConfiguracionDAO.existeRelacionRegionZona(codigoRegion, codigoZona)){
						 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noExisteRelacionRegionZona",null,
								 								getLocale(usuario));
						 errorPorFila=true;
					 }
				 }
				 
				 if(!errorPorFila && Constants.SI.equals(indicadorUnidadPopup) && StringUtils.isNotEmpty(numeroUnidades)){
					 if(!validoFormatoNumerico(numeroUnidades)){
					    mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noFormatoCantidad",null,
							 									getLocale(usuario));
					    errorPorFila=true;
					 } else {
						int varIntDato = numeroUnidades.indexOf('.');
						if (varIntDato >= 0 ){
							numeroUnidades = numeroUnidades.substring(0, varIntDato);
						} 
					 }
				 }
				 
				 if(!errorPorFila && Constants.SI.equals(indicadorUnidadPopup) && StringUtils.isNotEmpty(numeroUnidades)){
					 Integer valor = Integer.parseInt(numeroUnidades);
					 if(valor == 0) {
					    mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.validarCantidad",null,
							 									getLocale(usuario));
					    errorPorFila=true;
					 } 
				 }
				 
				 if(!errorPorFila && mapRegiones.get(codigoRegionZona)!=null){
					 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.regionZonaDuplicada",null,
								getLocale(usuario));
					 errorPorFila=true;
				 }
				 
				 if(!errorPorFila && mapRegionesAux.get(codigoRegionZona)!=null){
					 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.regionZonaDuplicada",null,
								getLocale(usuario));
					 errorPorFila=true;
				 }
				 
				//Lo almacenamos en una lista temporal
				Map mapFila = new HashMap();
				mapFila.put("numeroFila", String.valueOf(fila));
				mapFila.put("codigoRegion", codigoRegion);
				mapFila.put("codigoZona", codigoZona);
				mapFila.put("codigoCapacitadora", null);
				if(!errorPorFila) {
					mapRegionesAux.put(codigoRegionZona, "");
				} else {
					numFilaError+=1;
				}
	
				if(Constants.SI.equals(indicadorUnidadPopup))
					mapFila.put("numeroUnidades", numeroUnidades);
				else
					mapFila.put("numeroUnidades", numeroUnidadesPopupUniDifNO);
				
				mapFila.put("mensajeError", mensajeError);
				mapFila.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
				mapFila.put("numErrores", String.valueOf(numFilaError));
				listMapFila.add(mapFila);
			}
		}
		else {
			/* CASO CON CAPACITADORA  Columna0: REGION / Columna1: ZONA / Columna2: CAPACITADORA / Columna3: UNIDADES */
			while(excelUtil.hasNext()) {
				Map mapRow = excelUtil.next();
				fila +=1;
				errorPorFila= false;
	
				//obtenemos el codigo de cliente	
				String codigoRegion=obtenerValorLimpio((String)mapRow.get("0"));
				String codigoZona=obtenerValorLimpio((String)mapRow.get("1"));
				String codigoCapacitadora=obtenerValorLimpio((String)mapRow.get("2"));
				String numeroUnidades="";
				
				if(Constants.SI.equals(indicadorUnidadPopup))
					numeroUnidades=(String)mapRow.get("3");
				else
					numeroUnidades = numeroUnidadesPopupUniDifNO;
				
				String codigoRegionZona = codigoRegion;
				String mensajeError=null;
				
				// En caso de Region / Zona
				if (StringUtils.isBlank(codigoCapacitadora)) {
					
					if(StringUtils.isNotEmpty(codigoZona)) {
						codigoRegionZona = codigoRegionZona + "-" + codigoZona;
					}
					
					String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data
					 //
					 while(fila<Integer.parseInt(filaExcel)){
						 numFilaError+=1;
						 anhadirFilaVaciaRegiones(listMapFila,fila,numFilaError,usuario);				 
						 fila +=1;
					 }
					 
					 //validaciones
					 mensajeError=null;
					 
					 if(StringUtils.isEmpty(codigoRegion)){
						 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.sinValorCodigoRegion",null,
								 									getLocale(usuario));
						 errorPorFila=true;
					 }
					 if(!errorPorFila && Constants.SI.equals(indicadorUnidadPopup) && StringUtils.isEmpty(numeroUnidades)){
						 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.sinValorCantidad",null,
								 									getLocale(usuario));
						 errorPorFila=true;
					 }	 
					 
					 if(!errorPorFila && StringUtils.isNotEmpty(codigoRegion)){			
						 if(!mantenimientoMAVConfiguracionDAO.existeRegion(codigoRegion)){
							 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noExisteRegion",null,
									 								getLocale(usuario));
							 errorPorFila=true;
						 }
					 }
					 
					 if(!errorPorFila && StringUtils.isNotEmpty(codigoZona)){			
						 if(!mantenimientoMAVConfiguracionDAO.existeZona(codigoZona)){
							 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noExisteZona",null,
									 								getLocale(usuario));
							 errorPorFila=true;
						 }
					 }
					 
					 if(!errorPorFila && StringUtils.isNotEmpty(codigoRegion) && StringUtils.isNotEmpty(codigoZona)){			
						 if(!mantenimientoMAVConfiguracionDAO.existeRelacionRegionZona(codigoRegion, codigoZona)){
							 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noExisteRelacionRegionZona",null,
									 								getLocale(usuario));
							 errorPorFila=true;
						 }
					 }
					 
					 if(!errorPorFila && Constants.SI.equals(indicadorUnidadPopup) && StringUtils.isNotEmpty(numeroUnidades)){
						 if(!validoFormatoNumerico(numeroUnidades)){
						    mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noFormatoCantidad",null,
								 									getLocale(usuario));
						    errorPorFila=true;
						 } else {
							int varIntDato = numeroUnidades.indexOf('.');
							if (varIntDato >= 0 ){
								numeroUnidades = numeroUnidades.substring(0, varIntDato);
							} 
						 }
					 }
					 
					 if(!errorPorFila && Constants.SI.equals(indicadorUnidadPopup) && StringUtils.isNotEmpty(numeroUnidades)){
						 Integer valor = Integer.parseInt(numeroUnidades);
						 if(valor == 0) {
						    mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.validarCantidad",null,
								 									getLocale(usuario));
						    errorPorFila=true;
						 } 
					 }
					 
					 if(!errorPorFila && mapRegiones.get(codigoRegionZona)!=null){
						 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.regionZonaDuplicada",null,
									getLocale(usuario));
						 errorPorFila=true;
					 }
					 
					 if(!errorPorFila && mapRegionesAux.get(codigoRegionZona)!=null){
						 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.regionZonaDuplicada",null,
									getLocale(usuario));
						 errorPorFila=true;
					 }
					 
					//Lo almacenamos en una lista temporal
					Map mapFila = new HashMap();
					mapFila.put("numeroFila", String.valueOf(fila));
					mapFila.put("codigoRegion", codigoRegion);
					mapFila.put("codigoZona", codigoZona);
					mapFila.put("codigoCapacitadora", null);
					
					if(!errorPorFila) {
						mapRegionesAux.put(codigoRegionZona, "");
					} else {
						numFilaError+=1;
					}
		
					if(Constants.SI.equals(indicadorUnidadPopup))
						mapFila.put("numeroUnidades", numeroUnidades);
					else
						mapFila.put("numeroUnidades", numeroUnidadesPopupUniDifNO);
					
					mapFila.put("mensajeError", mensajeError);
					mapFila.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
					mapFila.put("numErrores", String.valueOf(numFilaError));
					listMapFila.add(mapFila);
						
				}
				// En caso de Capacitadora
				else {
									
					String filaExcel = (String)mapRow.get("rowNum");//obtiene la fila con data
					 //
					 while(fila<Integer.parseInt(filaExcel)){
						 numFilaError+=1;
						 anhadirFilaVaciaRegiones(listMapFila,fila,numFilaError,usuario);				 
						 fila +=1;
					 }
					 
					 //validaciones
					 mensajeError=null;
					 
					 if(StringUtils.isEmpty(codigoCapacitadora)){
						 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.sinValorCodigoCapacitadora",null,
								 									getLocale(usuario));
						 errorPorFila=true;
					 }
					 if(!errorPorFila && Constants.SI.equals(indicadorUnidadPopup) && StringUtils.isEmpty(numeroUnidades)){
						 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.sinValorCantidad",null,
								 									getLocale(usuario));
						 errorPorFila=true;
					 }	 
					 
					 if(!errorPorFila && StringUtils.isNotEmpty(codigoCapacitadora)){			
						 if(!mantenimientoMAVConfiguracionDAO.existeRegion(codigoCapacitadora)){
							 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noExisteCapacitadora",null,
									 								getLocale(usuario));
							 errorPorFila=true;
						 }
					 }
					 
					 if(!errorPorFila && Constants.SI.equals(indicadorUnidadPopup) && StringUtils.isNotEmpty(numeroUnidades)){
						 if(!validoFormatoNumerico(numeroUnidades)){
						    mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.noFormatoCantidad",null,
								 									getLocale(usuario));
						    errorPorFila=true;
						 } else {
							int varIntDato = numeroUnidades.indexOf('.');
							if (varIntDato >= 0 ){
								numeroUnidades = numeroUnidades.substring(0, varIntDato);
							} 
						 }
					 }
					 
					 if(!errorPorFila && Constants.SI.equals(indicadorUnidadPopup) && StringUtils.isNotEmpty(numeroUnidades)){
						 Integer valor = Integer.parseInt(numeroUnidades);
						 if(valor == 0) {
						    mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.validarCantidad",null,
								 									getLocale(usuario));
						    errorPorFila=true;
						 } 
					 }
					 
					 if(!errorPorFila && mapCapacitadoras.get(codigoCapacitadora)!=null){
						 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.capacitadoraDuplicada",null,
									getLocale(usuario));
						 errorPorFila=true;
					 }
					 
					 if(!errorPorFila && mapCapacitadorasAux.get(codigoCapacitadora)!=null){
						 mensajeError = messageSource.getMessage("mantenimientoMAVConfiguracionCargaRegionesForm.error.capacitadoraDuplicada",null,
									getLocale(usuario));
						 errorPorFila=true;
					 }
					 
					//Lo almacenamos en una lista temporal
					Map mapFila = new HashMap();
					mapFila.put("numeroFila", String.valueOf(fila));
					mapFila.put("codigoRegion", null);
					mapFila.put("codigoZona", null);
					mapFila.put("codigoCapacitadora", codigoCapacitadora);
					if(!errorPorFila) {
						mapCapacitadorasAux.put(codigoCapacitadora, "");
					} else {
						numFilaError+=1;
					}
		
					if(Constants.SI.equals(indicadorUnidadPopup))
						mapFila.put("numeroUnidades", numeroUnidades);
					else
						mapFila.put("numeroUnidades", numeroUnidadesPopupUniDifNO);

					mapFila.put("mensajeError", mensajeError);
					mapFila.put("indicadorAccion",Constants.MAV_ESTADO_TMP_INSERTAR);
					mapFila.put("numErrores", String.valueOf(numFilaError));
					listMapFila.add(mapFila);				
				}
					
				
			}
			
		}
		
		excelUtil.cerrar();
		listResultado.add(listMapFila);
		return listResultado;
	}

	/**
	 * Anhade fila vacia del excel a la lista que contiene informacion de excel procesado
	 * @param listMapFila
	 * @param numLote 
	 * @param fila
	 * @param numFilaError 
	 * @param usuario
	 */
	private void anhadirFilaVaciaRegiones(List listMapFila, int fila, int numFilaError, Usuario usuario) {
		Map mapFila = new HashMap();
		mapFila.put("numeroFila", String.valueOf(fila));
		mapFila.put("codigoRegion", "");
		mapFila.put("codigoZona", "");
		mapFila.put("mensajeError", messageSource.getMessage("procesoINCDarPorAtendidoBolsaFaltantesForm.error.noRegistroFila",
											null,getLocale(usuario)));
		mapFila.put("numErrores", String.valueOf(numFilaError));
		listMapFila.add(mapFila);		
		
	}

	/**
	 * Retorna el valor quitando el caracter (.0)
	 * @param valor
	 * @return
	 */
	private String obtenerValorLimpio(String valor) {
		String valorAux = valor;
		
		if(valor != null) {
			int posicion = valor.indexOf(".0");
			if (posicion >= 0 ){
				valorAux = valor.substring(0, posicion);
			} 
		}  
		
		return valorAux;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getCatalogos()
	 */
	public List getCatalogos() {
		return mantenimientoMAVConfiguracionDAO.getCatalogos();		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getActividadesGerente()
	 */
	public List getActividadesGerente() {
		return mantenimientoMAVConfiguracionDAO.getActividadesGerente();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getTiposVenta()
	 */
	public List getTiposVenta() {
		List lista = new ArrayList();
		Base base = new Base();
		base.setCodigo(Constants.MAV_CODIGO_UNIDAD_DEMANDADA);
		base.setDescripcion(Constants.MAV_DESCRIPCION_UNIDAD_DEMANDADA);
		lista.add(base);
		
		base = new Base();
		base.setCodigo(Constants.MAV_CODIGO_UNIDAD_DESPACHADA);
		base.setDescripcion(Constants.MAV_DESCRIPCION_UNIDAD_DESPACHADA);
		lista.add(base);
		
		return lista;		
	}
	

	/* INI SA PER-SiCC-2013-0440 */
	/**
	 * Retorna true si la consultora existe, false caso contrario
	 * @param oidPais
	 * @param codigoCliente
	 * @return
	 */
	private String obtenerOidPais(String codigoPais) {
		Map criteria = new HashMap();
        criteria.put("codigoPais", codigoPais);
		
        String oidPais = mantenimientoMAEClienteDAO.getOidPais(criteria);
		return oidPais;
	}
	/* FIN SA PER-SiCC-2013-0440 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getDetalleConsRestEnvio(java.util.Map)
	 */
	public List getDetalleConsRestEnvio(Map criteria) {
		return mantenimientoMAVConfiguracionDAO.getDetalleConsRestEnvio(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#updateConfiguracionUnidadesListaRegionZona(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConfiguracionUnidadesListaRegionZona(List listaUnidades, Usuario usuario) {
		
		for(int i=0; i<listaUnidades.size(); i++)
		{
			Map params = (Map)listaUnidades.get(i);
			params.put("usuario", usuario.getLogin());
			params.put("codigoPais", usuario.getCodigoPais());
			
			mantenimientoMAVConfiguracionDAO.updateConRestDetalleUnidades(params);
			mantenimientoMAVConfiguracionDAO.updateEnvioUnidades(params);
		}
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#insertListaRegionZona(java.util.List)
	 */
	public void insertListaRegionZona(List listaRegionZona) {
		int index=1;
		for(int i=0; i<listaRegionZona.size(); i++){
			Map bean = (Map)listaRegionZona.get(i);
			bean.put("correlativoConsideracion", ""+index);
			bean.put("condicion1", (String)bean.get("codigoRegion"));
			bean.put("condicion2", (String)bean.get("codigoZona"));
			bean.put("condicion3", (String)bean.get("codigoCapacitadora"));
			bean.put("numeroUnidades", (String)bean.get("numeroUnidades"));

			mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
			index++;
			
			mantenimientoMAVConfiguracionDAO.executeInsertarEnvioGerente(bean);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getDetalleClientesConsRestEnvio(java.util.Map)
	 */
	public List getDetalleClientesConsRestEnvio(Map criteria) {
		return mantenimientoMAVConfiguracionDAO.getDetalleClientesConsRestEnvio(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#insertListaConsultora(java.util.List)
	 */
	public void insertListaConsultora(List listaConsultora) {
		int index=1;
		for(int i=0; i<listaConsultora.size(); i++){
			Map bean = (Map)listaConsultora.get(i);
			bean.put("correlativoConsideracion", ""+index);
			bean.put("condicion1", (String)bean.get("codigoPais"));
			bean.put("condicion2", (String)bean.get("codigoCliente"));
			bean.put("numeroUnidades", (String)bean.get("numeroUnidades"));

			mantenimientoMAVConfiguracionDAO.saveConRestDetalle(bean);
			index++;
			
			mantenimientoMAVConfiguracionDAO.executeInsertarEnvioConsultora(bean);
		}
	}

	public List getActividadesTipoOferta(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoMAVConfiguracionDAO.getActividadesTipoOferta(criteria);
	}

	public void insertActividadesTipoOferta(Map params, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAVConfiguracionDAO.insertActividadesTipoOferta(params, usuario);
	}

	public void updateActividadesTipoOferta(Map params, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAVConfiguracionDAO.updateActividadesTipoOferta(params, usuario);
	}

	public ActividadTipoOferta getObtieneActividadesTipoOferta(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAVConfiguracionDAO.getObtieneActividadesTipoOferta(params);
	}

	public boolean getExisteActividadesTipoOferta(Map params) {
		// TODO Auto-generated method stub
		String oid = mantenimientoMAVConfiguracionDAO.getExisteActividadesTipoOferta(params);
		boolean existe = false;
		String idEnv = params.get("oidTipoOfer").toString();
		if(StringUtils.isNotBlank(oid)){
			
			if(oid.equals(idEnv)){
				existe = false;
			}else{
				existe = true;
			}
		}
		
		return existe;
	}

	public String getDescripcionTipoActividad(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAVConfiguracionDAO.getDescripcionTipoActividad(params);
	}

	public String getDescripcionActividad(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAVConfiguracionDAO.getDescripcionActividad(params);
	}

	public String getDescripcionTipoOferta(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAVConfiguracionDAO.getDescripcionTipoOferta(params);
	}

	public boolean getValidaEditActividadOferta(Map params) {
		// TODO Auto-generated method stub
		String oid = mantenimientoMAVConfiguracionDAO.getValidaEditActividadOferta(params);
		boolean existe = false;
		if(!oid.equals("0")){
				existe = true;
		}
		
		return existe;
	}

	public String getCodigoTipoOferta(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAVConfiguracionDAO.getCodigoTipoOferta(params);
	}

	public void DesactivarActividadTipoOferta(Map params, Usuario usuario) {
		// TODO Auto-generated method stub
		mantenimientoMAVConfiguracionDAO.DesactivarActividadTipoOferta(params, usuario);
	}

	public List getTiposOfertaId(String oidActividad) {
		// TODO Auto-generated method stub
		return mantenimientoMAVConfiguracionDAO.getTiposOfertaId(oidActividad);
	}

	public int cargarArchivoCsv(Map criteria) throws Exception {
		List listResultado = new ArrayList();
		List listMapFila = new ArrayList();
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String oidPais = String.valueOf(criteria.get("oidPais"));
		//Map mapClientes = (Map)criteria.get("mapClientes");
		
		mantenimientoMAVConfiguracionDAO.deleteExternaCultora(usuario.getLogin());
		
		int fila = 0;
		Map params = new HashMap();
	    params.put("codigoUsuario", usuario.getLogin());
		
		BufferedReader br = null;
		try {	
			
			//Abrimos el archivo CSV para su procedimiento
			String linea;
			File file = new File(directorioTemporal, nombreArchivo);
			br = new BufferedReader(new FileReader(file));
			
			while((linea = br.readLine()) != null) {
				String tokens[] = StringUtils.splitPreserveAllTokens(linea, ",");
				fila ++;
				
				//obtenemos el codigo de cliente	
				String codigoPais=  tokens[0];   
				String codigoCliente=  tokens[1];
				String numeroUnidades= tokens[2];
								 
				 params.put("codigoPais", StringUtils.trim(codigoPais));
		         params.put("codigoCliente", StringUtils.trim(codigoCliente));
		         params.put("numeroFila", Integer.valueOf(fila));		    
		         params.put("numeroUnidades", numeroUnidades);
		            
		         mantenimientoMAVConfiguracionDAO.insertExternaConsultora(params);

			}	
			
		} catch (Exception e) {
			log.error(e);
		}
		finally {
			try 
			{
				if (br != null)
					br.close();
			} 
			catch (IOException ex) 
			{
				log.error(ex);
			}
		}
		
		return fila;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#executeValidarExternaConsultora(java.lang.String)
	 */
	public List executeValidarExternaConsultora(String codigoUsuario) {
		mantenimientoMAVConfiguracionDAO.executeValidarExternaConsultora(codigoUsuario);
		List lista = mantenimientoMAVConfiguracionDAO.getCargarExternaConsultora(codigoUsuario);
				
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.MantenimientoMAVConfiguracionService#getErroresTempoExterConsul(java.lang.String)
	 */
	public Integer getErroresTempoExterConsul(String codigoUsuario) {
		Integer errores = mantenimientoMAVConfiguracionDAO.getErroresTempoExterConsul(codigoUsuario);;
		return errores;
	}
	
	
	
	
	
	
	
}
