package biz.belcorp.ssicc.service.spusicc.men.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.dao.spusicc.men.MantenimientoMENGenericoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENGenericoService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Service("spusicc.mantenimientoMENGenericoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoMENGenericoServiceImpl extends BaseService implements
		MantenimientoMENGenericoService {

	@Resource(name="spusicc.mantenimientoMENGenericoDAO")
	MantenimientoMENGenericoDAO mantenimientoMENGenericoDAO;
	
	@Resource(name="spusicc.mantenimientoMAEClienteDAO")
	MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#deletePatronMensaje(java.util.Map)
	 */
	public void deletePatronMensaje(Map map) {
		mantenimientoMENGenericoDAO.deletePatronMensaje(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getDocumentosMensaje(java.util.Map)
	 */
	public List getDocumentosMensaje(Map hmap) {
		return mantenimientoMENGenericoDAO.getDocumentosMensaje(hmap);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getPatronMensaje(java.util.Map)
	 */
	public List getPatronMensaje(Map map) {		
		return mantenimientoMENGenericoDAO.getPatronMensaje(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getModulos(java.util.Map)
	 */
	public List getModulos(Map map) {
		return mantenimientoMENGenericoDAO.getModulos(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getSeccionPatron(java.util.Map)
	 */
	public List getSeccionPatron(Map map) {
		return mantenimientoMENGenericoDAO.getSeccionPatron(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getConsideracion(java.util.Map)
	 */
	public List getConsideracion(Map map) {
		return mantenimientoMENGenericoDAO.getConsideracion(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getRestriccion(java.util.Map)
	 */
	public List getRestriccion(Map map) {
		return mantenimientoMENGenericoDAO.getRestriccion(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getMensajePatron(java.util.Map)
	 */
	public List getMensajePatron(Map map) {
		return mantenimientoMENGenericoDAO.getMensajePatron(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getOidPeriodoCorpo(java.util.Map)
	 */
	public String getOidPeriodoCorpo(Map map) {
		return mantenimientoMENGenericoDAO.getOidPeriodoCorpo(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#savePatronMensansaje(java.util.Map)
	 */
	public void savePatronMensaje(Map map) {		
		List<String> listaImagenes;
		String descripcionPatron = (String)map.get("descripcionPatron");
		String login = (String)map.get("login");
		List<Map> listMensajePatron = (List)map.get("listMensajePatron"); 
		String codigoPais = (String)map.get("codigoPais");
		log.debug("pais "+codigoPais);
//		String mensajeResultado="";
		String oidPatronAux="";
		int cont=0;

		for(Map beanPatron : listMensajePatron){			
			String indicadorAccion = (String)beanPatron.get("indicadorAccion");
			String indicadorParaActualizar = (String)beanPatron.get("indicadorParaActualizar");
			
			/* Verificando si hay imagenes en el mensaje */
			String textoMensaje = (String)beanPatron.get("textoMensaje");
			String mensajeTemp = textoMensaje;
			String valorImagen = "";
			listaImagenes = new ArrayList<String>();
			while(true) {
				int pos = StringUtils.indexOf(mensajeTemp, "<img>");
				if (pos >= 0) {
					mensajeTemp = StringUtils.substring(mensajeTemp, pos + 5);
					
					int posFin = StringUtils.indexOf(mensajeTemp, "</img>");
					valorImagen = StringUtils.substring(mensajeTemp, 0,posFin);
					listaImagenes.add(valorImagen);
					mensajeTemp = StringUtils.substring(mensajeTemp, posFin);
				}
				else {
					break;
				}
			}
			beanPatron.put("listaImagenes", listaImagenes);
			
			beanPatron.put("descripcionPatron", descripcionPatron);
			beanPatron.put("login", login);
			boolean actualizarAsignacion=false;
			if(Constants.NUMERO_CERO.equals(indicadorAccion)){
				if(StringUtils.isNotEmpty(oidPatronAux)){
					beanPatron.put("oidPatron",oidPatronAux);
				}
				savePatronMensajeDetalle(beanPatron);
				actualizarAsignacion=true;	
			}
			if(Constants.NUMERO_UNO.equals(indicadorAccion)){			
				if(Constants.NUMERO_UNO.equals(indicadorParaActualizar)){
					actualizarAsignacion=true;
					updatePatronMensajeDetalle(beanPatron);
				}else{					
					actualizarAsignacion=false;
					if(cont == 0) mantenimientoMENGenericoDAO.updatePatron(beanPatron);
					cont++;
				}
							
			}
			if(Constants.NUMERO_DOS.equals(indicadorAccion)){
				actualizarAsignacion=true;
				deletePatronMensajeDetalle(beanPatron);							
			}
			
			//PER-SiCC-2012-0697 mensajes fijo anhadir en sicc
			beanPatron.put("codigoPais",codigoPais);
			if(actualizarAsignacion){				
				mantenimientoMENGenericoDAO.updateAsignacion(beanPatron);
			}
			//fin mensajes fijos
			
			oidPatronAux= String.valueOf(beanPatron.get("oidPatron"));		
		}
		
	}

	/**
	 * @param beanPatron
	 */
	private void deletePatronMensajeDetalle(Map beanPatron) {
		beanPatron.put("indicadorActualizar", Constants.NRO_CERO);
		mantenimientoMENGenericoDAO.deletePatronMensajeDetalle(beanPatron);					
	}

	/**
	 * @param beanPatron
	 */
	private void savePatronMensajeDetalle(Map beanPatron) {
		String mensajeResultado;
		String oidPatronAux;
			mantenimientoMENGenericoDAO.savePatronMensaje(beanPatron);	
			
			mantenimientoMENGenericoDAO.updateAnulacionMensajeImagen(beanPatron);
			List listaImagenes = (List) beanPatron.get("listaImagenes");
			for(int i=0; i < listaImagenes.size(); i++) {
				String archivoImagen = (String) listaImagenes.get(i);
				beanPatron.put("archivoImagen", archivoImagen);
				beanPatron.put("indicadorImagenFondo", Constants.NO);
				mantenimientoMENGenericoDAO.insertaMensajeImagen(beanPatron);
			}
			String nombreImagenFondo = (String) beanPatron.get("nombreImagenFondo");
			if (StringUtils.isNotBlank(nombreImagenFondo)) {
				beanPatron.put("archivoImagen", nombreImagenFondo);
				beanPatron.put("indicadorImagenFondo", Constants.SI);
				mantenimientoMENGenericoDAO.insertaMensajeImagen(beanPatron);
			}
			
			mensajeResultado= (String)beanPatron.get("mensajeResultado");
			log.debug("savePatronMensajeDetalle "+mensajeResultado);
			
			oidPatronAux = mensajeResultado.split(",")[0];
			beanPatron.put("oidPatron", oidPatronAux);
			
			if(StringUtils.isNotEmpty(mensajeResultado)){
				//se graba las consideraciones y restricciones
				List<Map> listConsi=(List)beanPatron.get("listConsi");
				List<Map> listRestri =(List)beanPatron.get("listRestri");
				String login = (String)beanPatron.get("login");
				
				for(Map bean : listConsi){
					String indicadorTipo = (String)bean.get("indicadorTipo");
					bean.put("oidMensaje", mensajeResultado.split(",")[1]);
					String codigoConsRest = (String)bean.get("codigoConsideracion");
					String indicadorAccionConRes = (String)bean.get("indicadorAccion");						
					bean.put("indicadorConsideracion",Constants.MEN_TIPO_CONSIDERACION);
					bean.put("codigoConsRest", codigoConsRest);
					bean.put("login", login);
				 if(Constants.NUMERO_CERO.equals(indicadorAccionConRes)){		
					if(Constants.MEN_TIPO_SIN_CONDICION.equals(indicadorTipo)){
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
					}
					if(Constants.MEN_TIPO_UNA_CONDICION.equals(indicadorTipo)){
						String correlativoConsideracion=Constants.NUMERO_UNO;
						bean.put("correlativoConsideracion", correlativoConsideracion);
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
						mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
					}
					
					if(Constants.MEN_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
						String correlativoConsideracion=Constants.NUMERO_UNO;
						bean.put("correlativoConsideracion", correlativoConsideracion);
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);					
						mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
					}
											
					if(Constants.MEN_TIPO_LISTA_CONDICION.equals(indicadorTipo)
							|| Constants.MEN_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo)){
						if(Constants.MEN_CONRES_CODIGO_VENTA == Integer.parseInt(codigoConsRest)){
							List<Map> listVenta=(List)bean.get("listVenta");
							log.debug("listVentas xxx "+listVenta.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: listVenta){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoVenta"));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}
						
						if(Constants.MEN_CONRES_CUV_FALTANTE == Integer.parseInt(codigoConsRest)){
							List<Map> listVenta=(List)bean.get("listCUVFaltante");
							log.debug("listVentas xxx "+listVenta.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: listVenta){
								bean.put("condicion1", (String)m.get("codigoCuvFaltante"));
								if(StringUtils.isNotBlank(m.get("codigoCuvFaltante").toString()))
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}
						
						if(Constants.MEN_CONRES_CODIGO_PREMIO == Integer.parseInt(codigoConsRest)){
							List<Map> list=(List)bean.get("listPremio");
							log.debug("listPremio "+list.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: list){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoPremio"));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}	
						
						if(Constants.MEN_CONRES_ESTATUS_CLIENTE == Integer.parseInt(codigoConsRest)){
							List<Map> list=(List)bean.get("listEstatus");
							log.debug("listEstatus "+list.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: list){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoEstado"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}										

						if(Constants.MEN_CONRES_CUV_REEMPLAZO == Integer.parseInt(codigoConsRest)){
							List<Map> listVenta=(List)bean.get("listVenta");
							List<Map> listVentaReemplazo=(List)bean.get("listVentaReemplazo");
							log.debug("listVentas xxx "+listVenta.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: listVenta){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoVenta"));
								bean.put("condicion2", (String)(listVentaReemplazo.get(index-1).get("codigoVenta")));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}
						
						if(Constants.MEN_CONRES_LISTA_CONSU == Integer.parseInt(codigoConsRest)){
							List<Map> consultoraList=(List)bean.get("consultoraList");
							log.debug("consultoraList >>>>>>> "+consultoraList.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							String numeroLote="";
							synchronized (this) {
								numeroLote =mantenimientoMENGenericoDAO.getNumeroLote();
							}							  
							 for(Map m: consultoraList){									
							    m.put("oidMensaje", bean.get("oidMensaje"));
							    m.put("codigoConsRest", bean.get("codigoConsRest"));		
								m.put("oidPeriodoCorpo",bean.get("oidPeriodoCorpo"));
								m.put("oidModulo",bean.get("oidModulo"));	
								m.put("numeroLote",numeroLote);
								mantenimientoMENGenericoDAO.saveConRestBuzonDetalle(m);
							 }
							
						}	

						if(Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listClasificaciones");
							log.debug("listClasificaciones >>>>>>> "+listC.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: listC){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoTipoCliente"));
								bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
								bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
								bean.put("condicion4", (String)m.get("codigoClasificacion"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}
						if(Constants.MEN_CONRES_UNIDAD_ADM == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listUnidades");
							log.debug("listUnidades >>>>>>> "+listC.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: listC){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoRegion"));
								bean.put("condicion2", (String)m.get("codigoZona"));
								bean.put("condicion3", (String)m.get("codigoSeccion"));
								bean.put("condicion4", (String)m.get("codigoTerritorio"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}											
						
					}//fin lista
				 }
																	
				}
				
				for(Map bean : listRestri){
					String indicadorTipo = (String)bean.get("indicadorTipo");
					bean.put("oidMensaje", mensajeResultado.split(",")[1]);
					bean.put("indicadorConsideracion",Constants.MEN_TIPO_RESTRICCION);
					String codigoConsRest = (String)bean.get("codigoRestriccion");
					String indicadorAccionConRes = (String)bean.get("indicadorAccion");							
					bean.put("codigoConsRest", codigoConsRest);
					bean.put("login", login);
				  if(Constants.NUMERO_CERO.equals(indicadorAccionConRes)){		
					if(Constants.MEN_TIPO_SIN_CONDICION.equals(indicadorTipo)){
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
					}
					if(Constants.MEN_TIPO_UNA_CONDICION.equals(indicadorTipo)){
						String correlativoConsideracion=Constants.NUMERO_UNO;
						bean.put("correlativoConsideracion", correlativoConsideracion);
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
						mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
					}
					
					if(Constants.MEN_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
						String correlativoConsideracion=Constants.NUMERO_UNO;
						bean.put("correlativoConsideracion", correlativoConsideracion);
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);					
						mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
					}	
					
					if(Constants.MEN_TIPO_LISTA_CONDICION.equals(indicadorTipo)
							|| Constants.MEN_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo)){
						if(Constants.MEN_CONRES_CODIGO_VENTA_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listVenta=(List)bean.get("listVentaR");
							log.debug("listVentas Rest "+listVenta.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: listVenta){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoVenta"));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}
						
						if(Constants.MEN_CONRES_CODIGO_PREMIO_REST == Integer.parseInt(codigoConsRest)){
							List<Map> list=(List)bean.get("listPremioR");
							log.debug("listPremioR Rest "+list.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: list){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoPremio"));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}

						if(Constants.MEN_CONRES_CUV_REEMPLAZO_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listVenta=(List)bean.get("listVentaR");
							List<Map> listVentaReemplazo=(List)bean.get("listVentaReemplazoR");
							log.debug("listVentasR xxx "+listVenta.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: listVenta){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoVenta"));
								bean.put("condicion2", (String)(listVentaReemplazo.get(index-1).get("codigoVenta")));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}

						if(Constants.MEN_CONRES_LISTA_CONSU_REST == Integer.parseInt(codigoConsRest)){
							List<Map> consultoraList=(List)bean.get("consultoraListR");
							log.debug("consultoraListR >>>>>>> "+consultoraList.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							
							String numeroLote="";
							synchronized (this) {
								numeroLote =mantenimientoMENGenericoDAO.getNumeroLote();
							}
							
							for(Map m: consultoraList){									
								m.put("oidMensaje", bean.get("oidMensaje"));
								m.put("codigoConsRest", bean.get("codigoConsRest"));
								m.put("oidPeriodoCorpo",bean.get("oidPeriodoCorpo"));
								m.put("oidModulo",bean.get("oidModulo"));
								m.put("numeroLote", numeroLote);
								mantenimientoMENGenericoDAO.saveConRestBuzonDetalle(m);
							}
						}						
						
						if(Constants.MEN_CONRES_ESTATUS_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
							List<Map> list=(List)bean.get("listEstatusR");
							log.debug("listEstatusR "+list.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: list){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoEstado"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}										

						
						if(Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listClasificacionesR");
							log.debug("listClasificacionesR >>>>>>> "+listC.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: listC){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoTipoCliente"));
								bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
								bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
								bean.put("condicion4", (String)m.get("codigoClasificacion"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}
						//MEN_CONRES_UNIDAD_ADM
						if(Constants.MEN_CONRES_UNIDAD_ADM_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listUnidadesR");
							log.debug("listUnidadesR >>>>>>> "+listC.size());
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							int index=1;
							for(Map m: listC){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoRegion"));
								bean.put("condicion2", (String)m.get("codigoZona"));
								bean.put("condicion3", (String)m.get("codigoSeccion"));
								bean.put("condicion4", (String)m.get("codigoTerritorio"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}							
					  	
					}//fin tipo lista R									
				  }	
				}
				
			}
		//termina ACCION : 0
	}
	
	
	/**
	 * @param beanPatron
	 */
	private void updatePatronMensajeDetalle(Map beanPatron) {
		String mensajeResultado;
		String oidPatronAux;
			beanPatron.put("indicadorActualizar", Constants.NRO_UNO);
			mantenimientoMENGenericoDAO.updatePatronMensaje(beanPatron);				
			mensajeResultado= (String)beanPatron.get("mensajeResultado");
			log.debug("savePatronMensajeDetalle "+mensajeResultado);
			
			mantenimientoMENGenericoDAO.updateAnulacionMensajeImagen(beanPatron);
			List listaImagenes = (List) beanPatron.get("listaImagenes");
			for(int i=0; i < listaImagenes.size(); i++) {
				String archivoImagen = (String) listaImagenes.get(i);
				beanPatron.put("archivoImagen", archivoImagen);
				beanPatron.put("indicadorImagenFondo", Constants.NO);
				mantenimientoMENGenericoDAO.insertaMensajeImagen(beanPatron);
			}
			String nombreImagenFondo = (String) beanPatron.get("nombreImagenFondo");
			if (StringUtils.isNotBlank(nombreImagenFondo)) {
				beanPatron.put("archivoImagen", nombreImagenFondo);
				beanPatron.put("indicadorImagenFondo", Constants.SI);
				mantenimientoMENGenericoDAO.insertaMensajeImagen(beanPatron);
			}
			
			oidPatronAux = mensajeResultado.split(",")[0];
			beanPatron.put("oidPatron", oidPatronAux);
			
			if(StringUtils.isNotEmpty(mensajeResultado)){
				//se graba las consideraciones y restricciones
				List<Map> listConsi=(List)beanPatron.get("listConsi");
				List<Map> listRestri =(List)beanPatron.get("listRestri");
				String login = (String)beanPatron.get("login");
				
				for(Map bean : listConsi){
					String indicadorTipo = (String)bean.get("indicadorTipo");
					bean.put("oidMensaje", mensajeResultado.split(",")[1]);
					String codigoConsRest = (String)bean.get("codigoConsideracion");
					String indicadorAccionConRes = (String)bean.get("indicadorAccion");						
					bean.put("indicadorConsideracion",Constants.MEN_TIPO_CONSIDERACION);
					bean.put("codigoConsRest", codigoConsRest);
					bean.put("login", login);
				 if(Constants.NUMERO_CERO.equals(indicadorAccionConRes)){	//CONDICION 0:INSERTAR	
					if(Constants.MEN_TIPO_SIN_CONDICION.equals(indicadorTipo)){
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
					}
					if(Constants.MEN_TIPO_UNA_CONDICION.equals(indicadorTipo)){
						String correlativoConsideracion=Constants.NUMERO_UNO;
						bean.put("correlativoConsideracion", correlativoConsideracion);
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
						mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
					}
					
					if(Constants.MEN_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
						String correlativoConsideracion=Constants.NUMERO_UNO;
						bean.put("correlativoConsideracion", correlativoConsideracion);
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);					
						mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
					}
											
					if(Constants.MEN_TIPO_LISTA_CONDICION.equals(indicadorTipo)
							|| Constants.MEN_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo)){
						if(Constants.MEN_CONRES_CODIGO_VENTA == Integer.parseInt(codigoConsRest)){
							List<Map> listVenta=(List)bean.get("listVenta") != null ? (List)bean.get("listVenta"): new ArrayList();
							log.debug("listVentas xxx "+listVenta.size());
							if(listVenta.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: listVenta){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoVenta"));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}
						
						if(Constants.MEN_CONRES_CODIGO_VENTA == Integer.parseInt(codigoConsRest)){
							List<Map> listVenta=(List)bean.get("listVenta") != null ? (List)bean.get("listVenta"): new ArrayList();
							log.debug("listVentas xxx "+listVenta.size());
							if(listVenta.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: listVenta){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoVenta"));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}
						
						if(Constants.MEN_CONRES_CUV_FALTANTE == Integer.parseInt(codigoConsRest)){
							List<Map> listVenta=(List)bean.get("listCUVFaltante") != null ? (List)bean.get("listCUVFaltante"): new ArrayList();
							log.debug("listVentas xxx "+listVenta.size());
							if(listVenta.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: listVenta){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoCuvFaltante"));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}
						
						if(Constants.MEN_CONRES_CODIGO_PREMIO == Integer.parseInt(codigoConsRest)){
							List<Map> list=(List)bean.get("listPremio") != null ? (List)bean.get("listPremio"): new ArrayList();
							log.debug("listPremio "+list.size());
							if(list.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: list){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoPremio"));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}	
						
						if(Constants.MEN_CONRES_ESTATUS_CLIENTE == Integer.parseInt(codigoConsRest)){
							List<Map> list=(List)bean.get("listEstatus")!= null ? (List)bean.get("listEstatus"): new ArrayList();
							log.debug("listEstatus "+list.size());
							if(list.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: list){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoEstado"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}
						
						if(Constants.MEN_CONRES_LISTA_CONSU == Integer.parseInt(codigoConsRest)){
							List<Map> consultoraList=(List)bean.get("consultoraList");
							log.debug("consultoraList ==>>>> "+consultoraList.size());
													   	
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							
						   							
							String numeroLote="";
							synchronized (this) {
								numeroLote =mantenimientoMENGenericoDAO.getNumeroLote();
							}
							
							for(Map m: consultoraList){									
								m.put("oidMensaje", bean.get("oidMensaje"));
								m.put("codigoConsRest", bean.get("codigoConsRest"));
								m.put("oidPeriodoCorpo",bean.get("oidPeriodoCorpo"));
								m.put("oidModulo",bean.get("oidModulo"));
								m.put("numeroLote", numeroLote);
								mantenimientoMENGenericoDAO.saveConRestBuzonDetalle(m);
							}
							
							int numReg= mantenimientoMENGenericoDAO.getNumRegLoteBuzon(bean);
							log.debug("numReg "+numReg);
							bean.put("numReg", numReg);
							bean.put("indicadorActivo", Constants.NRO_UNO);
							mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
						}						

						if(Constants.MEN_CONRES_CUV_REEMPLAZO == Integer.parseInt(codigoConsRest)){
							List<Map> listVenta=(List)bean.get("listVenta")!= null ? (List)bean.get("listVenta"): new ArrayList();
							List<Map> listVentaReemplazo=(List)bean.get("listVentaReemplazo")!= null ? (List)bean.get("listVentaReemplazo"): new ArrayList();
							log.debug("listVentas xxx "+listVenta.size());
							if(listVenta.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: listVenta){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoVenta"));
								bean.put("condicion2", (String)(listVentaReemplazo.get(index-1).get("codigoVenta")));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}

						if(Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listClasificaciones")!= null ? (List)bean.get("listClasificaciones"): new ArrayList();
							log.debug("listClasificaciones >>>>>>> "+listC.size());
							if(listC.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: listC){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoTipoCliente"));
								bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
								bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
								bean.put("condicion4", (String)m.get("codigoClasificacion"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}
						if(Constants.MEN_CONRES_UNIDAD_ADM == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listUnidades")!= null ? (List)bean.get("listUnidades"): new ArrayList();
							log.debug("listUnidades >>>>>>> "+listC.size());
							if(listC.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: listC){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoRegion"));
								bean.put("condicion2", (String)m.get("codigoZona"));
								bean.put("condicion3", (String)m.get("codigoSeccion"));
								bean.put("condicion4", (String)m.get("codigoTerritorio"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}											
						
					}//fin sublistas 
				   }//fin accion:0
				 
				 	//accion : 1
					 String indicadorModificado = (String)bean.get("indicadorModificado");
					 String indicadorModificadoPopup = (String)bean.get("indicadorModificadoPopup");
					 if(Constants.NUMERO_UNO.equals(indicadorAccionConRes)){	
						    bean.put("indicadorActivo", Constants.ESTADO_ACTIVO);
							
						   if(Constants.NUMERO_UNO.equals(indicadorModificado)){ 
								if(Constants.MEN_TIPO_SIN_CONDICION.equals(indicadorTipo)){
									mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
								}
								if(Constants.MEN_TIPO_UNA_CONDICION.equals(indicadorTipo)){
									String correlativoConsideracion=Constants.NUMERO_UNO;
									bean.put("correlativoConsideracion", correlativoConsideracion);
									mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
								}
								
								if(Constants.MEN_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
									String correlativoConsideracion=Constants.NUMERO_UNO;
									bean.put("correlativoConsideracion", correlativoConsideracion);
									mantenimientoMENGenericoDAO.updateConRestCabecera(bean);					
									mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
								}
								
						   }else{							
							if((Constants.MEN_TIPO_LISTA_CONDICION.equals(indicadorTipo) 
									|| Constants.MEN_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo) )
									 && Constants.NUMERO_UNO.equals(indicadorModificadoPopup)){
								if(Constants.MEN_CONRES_CODIGO_VENTA == Integer.parseInt(codigoConsRest)){
									List<Map> listVenta=(List)bean.get("listVenta")!= null ? (List)bean.get("listVenta"): new ArrayList();
									log.debug("listVentas  "+ listVenta.size());
									if(listVenta.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}									
									List <Map>listResultado = getListResultado(bean,listVenta); 
									for(Map m: listResultado){																		
										bean.put("condicion1", (String)m.get("codigoVenta"));
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}																
									}
									
								}
								
								if(Constants.MEN_CONRES_CUV_FALTANTE == Integer.parseInt(codigoConsRest)){
									List<Map> listVenta=(List)bean.get("listCUVFaltante")!= null ? (List)bean.get("listCUVFaltante"): new ArrayList();
									log.debug("listVentas  "+ listVenta.size());
									if(listVenta.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}									
									List <Map>listResultado = getListResultado(bean,listVenta); 
									for(Map m: listResultado){																		
										bean.put("condicion1", (String)m.get("codigoCuvFaltante"));
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}																
									}
									
								}
								
								if(Constants.MEN_CONRES_LISTA_CONSU == Integer.parseInt(codigoConsRest)){
									List<Map> consultoraList=(List)bean.get("consultoraList");
									log.debug("consultoraList ==>>>> "+consultoraList.size());
									
									List<Map> buzonLoteList = (List)bean.get("buzonLoteList");
									if(buzonLoteList != null){
										for(Map buz: buzonLoteList){
											String indicadorAccion = (String)buz.get("indicadorAccion");
											if(indicadorAccion.equals(Constants.NUMERO_DOS)){
												buz.put("login", login);
												mantenimientoMENGenericoDAO.updateConRestBuzonDetalle(buz);
											}
										}
									}
									
									if(consultoraList.size()>0){
										String numeroLote="";
										synchronized (this) {
											numeroLote =mantenimientoMENGenericoDAO.getNumeroLote();
										}
										
										for(Map m: consultoraList){									
											m.put("oidMensaje", bean.get("oidMensaje"));
											m.put("codigoConsRest", bean.get("codigoConsRest"));
											m.put("oidPeriodoCorpo",bean.get("oidPeriodoCorpo"));
											m.put("oidModulo",bean.get("oidModulo"));
											m.put("numeroLote", numeroLote);
											mantenimientoMENGenericoDAO.saveConRestBuzonDetalle(m);
										}
										
									}
									
									int numReg= mantenimientoMENGenericoDAO.getNumRegLoteBuzon(bean);
									bean.put("numReg", numReg);
									bean.put("indicadorActivo", Constants.NRO_UNO);
									mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
								}	
								
								if(Constants.MEN_CONRES_CODIGO_PREMIO == Integer.parseInt(codigoConsRest)){
									List<Map> list=(List)bean.get("listPremio")!= null ? (List)bean.get("listPremio"): new ArrayList();
									log.debug("listPremio "+list.size());
									if(list.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}
									List <Map>listResultado = getListResultado(bean,list);
									for(Map m: listResultado){									
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										bean.put("condicion1", (String)m.get("codigoPremio"));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}		
										
									}
								}	
								
								if(Constants.MEN_CONRES_ESTATUS_CLIENTE == Integer.parseInt(codigoConsRest)){
									List<Map> list=(List)bean.get("listEstatus")!= null ? (List)bean.get("listEstatus"): new ArrayList();
									log.debug("listEstatus "+list.size());
									if(list.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}
									
									for(Map m: list){																			
										bean.put("condicion1", (String)m.get("codigoEstado"));
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}										
										
									}
								}										

								if(Constants.MEN_CONRES_CUV_REEMPLAZO == Integer.parseInt(codigoConsRest)){
									List<Map> listVenta=(List)bean.get("listVenta")!= null ? (List)bean.get("listVenta"): new ArrayList();
									List<Map> listVentaReemplazo=(List)bean.get("listVentaReemplazo")!= null ? (List)bean.get("listVentaReemplazo"): new ArrayList();
									log.debug("listVentas xxx "+listVenta.size());
									if(listVenta.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}
									List <Map>listResultado = getListResultado(bean,listVenta);
									List <Map>listResultado2 = getListResultado2(bean,listVentaReemplazo);
									int index=0;
									for(Map m: listResultado){									
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										bean.put("condicion1", (String)m.get("codigoVenta"));
										bean.put("condicion2", (String)(listResultado2.get(index).get("codigoVenta")));
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}		
										index++;
									}
								}

								if(Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE == Integer.parseInt(codigoConsRest)){
									List<Map> listC =(List)bean.get("listClasificaciones")!= null ? (List)bean.get("listClasificaciones"): new ArrayList();
									log.debug("listClasificaciones >>>>>>> "+listC.size());
									if(listC.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}
									
									for(Map m: listC){									
										
										bean.put("condicion1", (String)m.get("codigoTipoCliente"));
										bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
										bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
										bean.put("condicion4", (String)m.get("codigoClasificacion"));
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}																
										
									}
								}
								if(Constants.MEN_CONRES_UNIDAD_ADM == Integer.parseInt(codigoConsRest)){
									List<Map> listC =(List)bean.get("listUnidades")!= null ? (List)bean.get("listUnidades"): new ArrayList();
									log.debug("listUnidades >>>>>>> "+listC.size());
									if(listC.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}
									
									for(Map m: listC){									
										
										bean.put("condicion1", (String)m.get("codigoRegion"));
										bean.put("condicion2", (String)m.get("codigoZona"));
										bean.put("condicion3", (String)m.get("codigoSeccion"));
										bean.put("condicion4", (String)m.get("codigoTerritorio"));
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}																
									}
								}											
								
							}//fin sublistas cuando consideraciones es 1						    
						   }//fin del else
												
					 }//fin accion:1
					 
					 //accion:2
					if(Constants.NUMERO_DOS.equals(indicadorAccionConRes)){
						    bean.put("indicadorActivo", Constants.NUMERO_CERO);
						    bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);
						    bean.put("correlativoConsideracion", null);
						    bean.put("numLote", null);
						    mantenimientoMENGenericoDAO.deleteConRestDetalle(bean);
						    mantenimientoMENGenericoDAO.deleteConRestCabecera(bean);							
							mantenimientoMENGenericoDAO.updateConRestBuzonDetalle(bean);
					}//fin accion :2							 
				 			 
																	
				}//fin recorrido lista de consideraciones
				
				for(Map bean : listRestri){
					String indicadorTipo = (String)bean.get("indicadorTipo");
					bean.put("oidMensaje", mensajeResultado.split(",")[1]);
					bean.put("indicadorConsideracion",Constants.MEN_TIPO_RESTRICCION);
					String codigoConsRest = (String)bean.get("codigoRestriccion");
					String indicadorAccionConRes = (String)bean.get("indicadorAccion");							
					bean.put("codigoConsRest", codigoConsRest);
					bean.put("login", login);
				  if(Constants.NUMERO_CERO.equals(indicadorAccionConRes)){		
					if(Constants.MEN_TIPO_SIN_CONDICION.equals(indicadorTipo)){
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
					}
					if(Constants.MEN_TIPO_UNA_CONDICION.equals(indicadorTipo)){
						String correlativoConsideracion=Constants.NUMERO_UNO;
						bean.put("correlativoConsideracion", correlativoConsideracion);
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
						mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
					}
					
					if(Constants.MEN_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
						String correlativoConsideracion=Constants.NUMERO_UNO;
						bean.put("correlativoConsideracion", correlativoConsideracion);
						mantenimientoMENGenericoDAO.saveConRestCabecera(bean);					
						mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
					}	
					
					if(Constants.MEN_TIPO_LISTA_CONDICION.equals(indicadorTipo)
							|| Constants.MEN_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo)){
						if(Constants.MEN_CONRES_CODIGO_VENTA_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listVenta=(List)bean.get("listVentaR")!= null ? (List)bean.get("listVentaR"): new ArrayList();
							log.debug("listVentas Rest "+listVenta.size());
							if(listVenta.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: listVenta){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoVenta"));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}
						
						if(Constants.MEN_CONRES_CODIGO_PREMIO_REST == Integer.parseInt(codigoConsRest)){
							List<Map> list=(List)bean.get("listPremioR")!= null ? (List)bean.get("listPremioR"): new ArrayList();
							log.debug("listPremioR Rest "+list.size());
							if(list.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: list){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoPremio"));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}

						if(Constants.MEN_CONRES_CUV_REEMPLAZO_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listVenta=(List)bean.get("listVentaR")!= null ? (List)bean.get("listVentaR"): new ArrayList();
							List<Map> listVentaReemplazo=(List)bean.get("listVentaReemplazoR")!= null ? (List)bean.get("listVentaReemplazoR"): new ArrayList();
							log.debug("listVentasR xxx "+listVenta.size());
							if(listVenta.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: listVenta){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoVenta"));
								bean.put("condicion2", (String)(listVentaReemplazo.get(index-1).get("codigoVenta")));
								mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								index++;
							}
						}

						
						if(Constants.MEN_CONRES_ESTATUS_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
							List<Map> list=(List)bean.get("listEstatusR")!= null ? (List)bean.get("listEstatusR"): new ArrayList();
							log.debug("listEstatusR "+list.size());
							if(list.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: list){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoEstado"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}	

						if(Constants.MEN_CONRES_LISTA_CONSU_REST == Integer.parseInt(codigoConsRest)){
							List<Map> consultoraList=(List)bean.get("consultoraListR");
							log.debug("consultoraListR ===>>>> "+consultoraList.size());
							
											   	
							mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							
							
							String numeroLote="";
							synchronized (this) {
								numeroLote =mantenimientoMENGenericoDAO.getNumeroLote();
							}
							
							for(Map m: consultoraList){									
								m.put("oidMensaje", bean.get("oidMensaje"));
								m.put("codigoConsRest", bean.get("codigoConsRest"));
								m.put("oidPeriodoCorpo",bean.get("oidPeriodoCorpo"));
								m.put("oidModulo",bean.get("oidModulo"));	
								m.put("numeroLote", numeroLote);
								mantenimientoMENGenericoDAO.saveConRestBuzonDetalle(m);
							}
							
							int numReg= mantenimientoMENGenericoDAO.getNumRegLoteBuzon(bean);
							bean.put("numReg", numReg);
							bean.put("indicadorActivo", Constants.NRO_UNO);
							mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
						}

						
						if(Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listClasificacionesR")!= null ? (List)bean.get("listClasificacionesR"): new ArrayList();
							log.debug("listClasificacionesR >>>>>>> "+listC.size());
							if(listC.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: listC){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoTipoCliente"));
								bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
								bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
								bean.put("condicion4", (String)m.get("codigoClasificacion"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}
						//MEN_CONRES_UNIDAD_ADM
						if(Constants.MEN_CONRES_UNIDAD_ADM_REST == Integer.parseInt(codigoConsRest)){
							List<Map> listC =(List)bean.get("listUnidadesR")!= null ? (List)bean.get("listUnidadesR"): new ArrayList();
							log.debug("listUnidadesR >>>>>>> "+listC.size());
							if(listC.size()>0){
								mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
							}
							int index=1;
							for(Map m: listC){									
								//bean.put("correlativoConsideracion", ""+index);
								bean.put("condicion1", (String)m.get("codigoRegion"));
								bean.put("condicion2", (String)m.get("codigoZona"));
								bean.put("condicion3", (String)m.get("codigoSeccion"));
								bean.put("condicion4", (String)m.get("codigoTerritorio"));
								String indAccion = (String)m.get("indicadorAccion");
								if(Constants.NUMERO_CERO.equals(indAccion)){
									mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
								}
								index++;
							}
						}							
					  	
					}//fin tipo lista R									
				  }//fin accion :0
				  
				 	//accion : 1
					 String indicadorModificado = (String)bean.get("indicadorModificado");
					 String indicadorModificadoPopup = (String)bean.get("indicadorModificadoPopup");
					 if(Constants.NUMERO_UNO.equals(indicadorAccionConRes)){	
						    bean.put("indicadorActivo", Constants.ESTADO_ACTIVO);
							
						   if(Constants.NUMERO_UNO.equals(indicadorModificado)){ 
								if(Constants.MEN_TIPO_SIN_CONDICION.equals(indicadorTipo)){
									mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
								}
								if(Constants.MEN_TIPO_UNA_CONDICION.equals(indicadorTipo)){
									String correlativoConsideracion=Constants.NUMERO_UNO;
									bean.put("correlativoConsideracion", correlativoConsideracion);
									mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
								}
								
								if(Constants.MEN_TIPO_UNA_CONDICION_DOBLE.equals(indicadorTipo)){
									String correlativoConsideracion=Constants.NUMERO_UNO;
									bean.put("correlativoConsideracion", correlativoConsideracion);
									mantenimientoMENGenericoDAO.updateConRestCabecera(bean);					
									mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
								}
								
						   }else{							
							if((Constants.MEN_TIPO_LISTA_CONDICION.equals(indicadorTipo) 
									|| Constants.MEN_TIPO_LISTA_EXTERNA_CONDICION.equals(indicadorTipo) )
								 && Constants.NUMERO_UNO.equals(indicadorModificadoPopup)){
								if(Constants.MEN_CONRES_CODIGO_VENTA_REST == Integer.parseInt(codigoConsRest)){
									List<Map> listVenta=(List)bean.get("listVentaR")!= null ? (List)bean.get("listVentaR"): new ArrayList();
									log.debug("listVentas  "+listVenta.size());
									if(listVenta.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}
									List <Map>listResultado = getListResultado(bean,listVenta); 
									for(Map m: listResultado){																		
										bean.put("condicion1", (String)m.get("codigoVenta"));
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}																
									}
									
								}
								
								if(Constants.MEN_CONRES_CODIGO_PREMIO_REST == Integer.parseInt(codigoConsRest)){
									List<Map> list=(List)bean.get("listPremioR")!= null ? (List)bean.get("listPremioR"): new ArrayList();
									log.debug("listPremio "+list.size());
									if(list.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}
									List <Map>listResultado = getListResultado(bean,list);
									for(Map m: listResultado){									
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										bean.put("condicion1", (String)m.get("codigoPremio"));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}		
										
									}
								}
								
								if(Constants.MEN_CONRES_LISTA_CONSU_REST == Integer.parseInt(codigoConsRest)){
									List<Map> consultoraList=(List)bean.get("consultoraListR");
									log.debug("consultoraListR ===>>>> "+consultoraList.size());
									
									//mantenimientoMENGenericoDAO.saveConRestCabecera(bean);
									
									//recorremos los lote para ver si hay eliminados
									List<Map> buzonLoteList = (List)bean.get("buzonLoteListR");
									if(buzonLoteList != null){
										for(Map buz: buzonLoteList){
											String indicadorAccion = (String)buz.get("indicadorAccion");
											if(indicadorAccion.equals(Constants.NUMERO_DOS)){
												buz.put("login", login);
												mantenimientoMENGenericoDAO.updateConRestBuzonDetalle(buz);
											}
										}
									}									
									
									if(consultoraList.size()>0){
										String numeroLote="";
										synchronized (this) {
											numeroLote =mantenimientoMENGenericoDAO.getNumeroLote();
										}
										
										for(Map m: consultoraList){									
											m.put("oidMensaje", bean.get("oidMensaje"));
											m.put("codigoConsRest", bean.get("codigoConsRest"));
											m.put("oidPeriodoCorpo",bean.get("oidPeriodoCorpo"));
											m.put("oidModulo",bean.get("oidModulo"));	
											m.put("numeroLote", numeroLote);
											mantenimientoMENGenericoDAO.saveConRestBuzonDetalle(m);
										}
										
									}
 
									int numReg= mantenimientoMENGenericoDAO.getNumRegLoteBuzon(bean);
									bean.put("numReg", numReg);
									bean.put("indicadorActivo", Constants.NRO_UNO);
									mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
								}								
								
								if(Constants.MEN_CONRES_ESTATUS_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
									List<Map> list=(List)bean.get("listEstatusR")!= null ? (List)bean.get("listEstatusR"): new ArrayList();
									log.debug("listEstatus "+list.size());
									if(list.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}
									
									for(Map m: list){									
										
										bean.put("condicion1", (String)m.get("codigoEstado"));
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}										
										
									}
								}										

								if(Constants.MEN_CONRES_CUV_REEMPLAZO_REST == Integer.parseInt(codigoConsRest)){
									List<Map> listVenta=(List)bean.get("listVentaR")!= null ? (List)bean.get("listVentaR"): new ArrayList();
									List<Map> listVentaReemplazo=(List)bean.get("listVentaReemplazoR")!= null ? (List)bean.get("listVentaReemplazoR"): new ArrayList();
									log.debug("listVentas xxx "+listVenta.size());
									if(listVenta.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}
									List <Map>listResultado = getListResultado(bean,listVenta);
									List <Map>listResultado2 = getListResultado2(bean,listVentaReemplazo);
									int index=0;
									for(Map m: listResultado){									
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										bean.put("condicion1", (String)m.get("codigoVenta"));
										bean.put("condicion2", (String)(listResultado2.get(index).get("codigoVenta")));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}		
										index++;
									}
								}

								if(Constants.MEN_CONRES_CLASIFICACIONES_CLIENTE_REST == Integer.parseInt(codigoConsRest)){
									List<Map> listC =(List)bean.get("listClasificacionesR")!= null ? (List)bean.get("listClasificacionesR"): new ArrayList();
									log.debug("listClasificaciones >>>>>>> "+listC.size());
									if(listC.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}
									
									for(Map m: listC){									
										
										bean.put("condicion1", (String)m.get("codigoTipoCliente"));
										bean.put("condicion2", (String)m.get("codigoSubTipoCliente"));
										bean.put("condicion3", (String)m.get("codigoTipoClasificacion"));
										bean.put("condicion4", (String)m.get("codigoClasificacion"));
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}																
										
									}
								}
								if(Constants.MEN_CONRES_UNIDAD_ADM_REST == Integer.parseInt(codigoConsRest)){
									List<Map> listC =(List)bean.get("listUnidadesR")!= null ? (List)bean.get("listUnidadesR"): new ArrayList();
									log.debug("listUnidades >>>>>>> "+listC.size());
									if(listC.size()>0){
										mantenimientoMENGenericoDAO.updateConRestCabecera(bean);
									}
									
									for(Map m: listC){									
										
										bean.put("condicion1", (String)m.get("codigoRegion"));
										bean.put("condicion2", (String)m.get("codigoZona"));
										bean.put("condicion3", (String)m.get("codigoSeccion"));
										bean.put("condicion4", (String)m.get("codigoTerritorio"));
										bean.put("correlativoConsideracion", String.valueOf(m.get("correlativoConsideracion")));
										String indAccion = (String)m.get("indicadorAccion");
										if(Constants.NUMERO_CERO.equals(indAccion)){
											mantenimientoMENGenericoDAO.saveConRestDetalle(bean);
										}
										if(Constants.NUMERO_DOS.equals(indAccion)){
											bean.put("indicadorActivo", Constants.NUMERO_CERO);
											bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);											
											mantenimientoMENGenericoDAO.updateConRestDetalle(bean);
											bean.put("indicadorActivo", Constants.NUMERO_UNO);
											bean.put("estadoRegistro", Constants.ESTADO_ACTIVO);
										}																
									}
								}											
								
							}//fin sublistas cuando consideraciones es 1						    
						   }//fin del else
												
					 }//fin accion:1				
					 
					 
   				   //accion :2	 
				   if(Constants.NUMERO_DOS.equals(indicadorAccionConRes)){
						    bean.put("indicadorActivo", Constants.NUMERO_CERO);
						    bean.put("estadoRegistro", Constants.ESTADO_INACTIVO);
						    bean.put("correlativoConsideracion", null);
						    bean.put("numLote", null);
						    mantenimientoMENGenericoDAO.deleteConRestDetalle(bean);
						    mantenimientoMENGenericoDAO.deleteConRestCabecera(bean);									
							mantenimientoMENGenericoDAO.updateConRestBuzonDetalle(bean);
				   }//fin accion :2					 
				}
				
			}
		//termina ACCION : 1
	}

	/**
	 * obtiene las variales de condicion1
	 * @param bean
	 * @param listVentaReemplazo
	 * @return
	 */
	private List<Map> getListResultado(Map bean, List<Map> list) {
		List <Map>listResultado = new ArrayList<Map>(); 
		Map criteria = new HashMap();
		criteria.put("indicadorTipo", bean.get("indicadorConsideracion"));
		criteria.put("codigoMensaje", bean.get("codigoMensaje"));
		criteria.put("codigoConsRest", bean.get("codigoConsRest"));
		List <Map>listBD = (List)mantenimientoMENGenericoDAO.getDetalleConsRest(criteria);
		String codigoConsRest = (String)bean.get("codigoConsRest");
		int codigo = Integer.parseInt(codigoConsRest);
		switch (codigo) {
		case Constants.MEN_CONRES_CODIGO_PREMIO:
				for (Map map : listBD) {
					String codigoPremio=(String)map.get("valCondi1");
					String correlativoConsideracion =String.valueOf(map.get("correlativoConsideracion"));
					boolean existe =false;
					int index=0;
					for (Map m : list) {
						String aux=(String)m.get("codigoPremio");
						if(StringUtils.equals(codigoPremio, aux)){
							existe= true;
							break;
						}
					  index++;
					}
					if(!existe){//x q ha sido eliminado
						Map resultado = new HashMap();
						resultado.put("codigoPremio",codigoPremio);
						resultado.put("indicadorAccion",Constants.NUMERO_DOS);
						resultado.put("correlativoConsideracion", correlativoConsideracion);
						listResultado.add(resultado);
					}else{
						//marcarlo en la lista actual para no tomarlo
						Map m = list.get(index);
						m.put("indicadorExiste", Constants.NRO_UNO);
						
					}
				}
				
				for (Map map : list) {
					String indicadorExiste= (String)map.get("indicadorExiste");
					String aux=(String)map.get("codigoPremio");
					if(!Constants.NRO_UNO.equals(indicadorExiste)){
						Map resultado = new HashMap();
						resultado.put("codigoPremio",aux);
						resultado.put("indicadorAccion",Constants.NUMERO_CERO);
						listResultado.add(resultado);		
					}
				}
				
			break;
		case Constants.MEN_CONRES_CODIGO_VENTA:
					for (Map map : listBD) {
						String codigoVenta=(String)map.get("valCondi1");
						String correlativoConsideracion =String.valueOf(map.get("correlativoConsideracion"));
						boolean existe =false;
						int index=0;
						for (Map m : list) {
							String aux=(String)m.get("codigoVenta");
							if(StringUtils.equals(codigoVenta, aux)){
								existe= true;
								break;
							}
						  index++;
						}
						if(!existe){//es x que lo han eliminado
							Map resultado = new HashMap();
							resultado.put("codigoVenta",codigoVenta);
							resultado.put("indicadorAccion",Constants.NUMERO_DOS);
							resultado.put("correlativoConsideracion", correlativoConsideracion);
							listResultado.add(resultado);
						}else{
							//marcarlo en la lista actual para no tomarlo
							Map m = list.get(index);
							m.put("indicadorExiste", Constants.NRO_UNO);
						}
					}
					
					for (Map map : list) {
						String indicadorExiste= (String)map.get("indicadorExiste");
						String aux=(String)map.get("codigoVenta");
						if(!Constants.NRO_UNO.equals(indicadorExiste)){
							Map resultado = new HashMap();
							resultado.put("codigoVenta",aux);
							resultado.put("indicadorAccion",Constants.NUMERO_CERO);
							listResultado.add(resultado);		
						}
					}					
				
			break;
		case Constants.MEN_CONRES_CUV_REEMPLAZO:
			for (Map map : listBD) {
				String codigoVenta=(String)map.get("valCondi1");
				String correlativoConsideracion =String.valueOf(map.get("correlativoConsideracion"));
				boolean existe =false;
				int index=0;
				for (Map m : list) {
					String aux=(String)m.get("codigoVenta");
					if(StringUtils.equals(codigoVenta, aux)){
						existe= true;
						break;
					}
				  index++;
				}
				if(!existe){//es x que lo han eliminado
					Map resultado = new HashMap();
					resultado.put("codigoVenta",codigoVenta);
					resultado.put("indicadorAccion",Constants.NUMERO_DOS);
					resultado.put("correlativoConsideracion", correlativoConsideracion);
					listResultado.add(resultado);
				}else{
					//marcarlo en la lista actual para no tomarlo
					Map m = list.get(index);
					m.put("indicadorExiste", Constants.NRO_UNO);
				}
			}
			
			for (Map map : list) {
				String indicadorExiste= (String)map.get("indicadorExiste");
				String aux=(String)map.get("codigoVenta");
				if(!Constants.NRO_UNO.equals(indicadorExiste)){
					Map resultado = new HashMap();
					resultado.put("codigoVenta",aux);
					resultado.put("indicadorAccion",Constants.NUMERO_CERO);
					listResultado.add(resultado);		
				}
			}					
			break;			
		case Constants.MEN_CONRES_CODIGO_PREMIO_REST:	
			for (Map map : listBD) {
				String codigoPremio=(String)map.get("valCondi1");
				String correlativoConsideracion =String.valueOf(map.get("correlativoConsideracion"));
				boolean existe =false;
				int index=0;
				for (Map m : list) {
					String aux=(String)m.get("codigoPremio");
					if(StringUtils.equals(codigoPremio, aux)){
						existe= true;
						break;
					}
				  index++;
				}
				if(!existe){
					Map resultado = new HashMap();
					resultado.put("codigoPremio",codigoPremio);
					resultado.put("indicadorAccion",Constants.NUMERO_DOS);
					resultado.put("correlativoConsideracion", correlativoConsideracion);
					listResultado.add(resultado);
				}else{
					//marcarlo en la lista actual para no tomarlo
					Map m = list.get(index);
					m.put("indicadorExiste", Constants.NRO_UNO);
				}
			}
			
			for (Map map : list) {
				String indicadorExiste= (String)map.get("indicadorExiste");
				String aux=(String)map.get("codigoPremio");
				if(!Constants.NRO_UNO.equals(indicadorExiste)){
					Map resultado = new HashMap();
					resultado.put("codigoPremio",aux);
					resultado.put("indicadorAccion",Constants.NUMERO_CERO);
					listResultado.add(resultado);		
				}
			}			
			break;			
		case Constants.MEN_CONRES_CODIGO_VENTA_REST:
			for (Map map : listBD) {
				String codigoVenta=(String)map.get("valCondi1");
				String correlativoConsideracion =String.valueOf(map.get("correlativoConsideracion"));
				boolean existe =false;
				int index=0;
				for (Map m : list) {
					String aux=(String)m.get("codigoVenta");
					if(StringUtils.equals(codigoVenta, aux)){
						existe= true;
						break;
					}
				  index++;
				}
				if(!existe){//es x que lo han eliminado
					Map resultado = new HashMap();
					resultado.put("codigoVenta",codigoVenta);
					resultado.put("indicadorAccion",Constants.NUMERO_DOS);
					resultado.put("correlativoConsideracion", correlativoConsideracion);
					listResultado.add(resultado);
				}else{
					//marcarlo en la lista actual para no tomarlo
					Map m = list.get(index);
					m.put("indicadorExiste", Constants.NRO_UNO);
				}
			}
			
			for (Map map : list) {
				String indicadorExiste= (String)map.get("indicadorExiste");
				String aux=(String)map.get("codigoVenta");
				if(!Constants.NRO_UNO.equals(indicadorExiste)){
					Map resultado = new HashMap();
					resultado.put("codigoVenta",aux);
					resultado.put("indicadorAccion",Constants.NUMERO_CERO);
					listResultado.add(resultado);		
				}
			}									
			break;
		case Constants.MEN_CONRES_CUV_REEMPLAZO_REST:
			for (Map map : listBD) {
				String codigoVenta=(String)map.get("valCondi1");
				String correlativoConsideracion =String.valueOf(map.get("correlativoConsideracion"));
				boolean existe =false;
				int index=0;
				for (Map m : list) {
					String aux=(String)m.get("codigoVenta");
					if(StringUtils.equals(codigoVenta, aux)){
						existe= true;
						break;
					}
				  index++;
				}
				if(!existe){//es x que lo han eliminado
					Map resultado = new HashMap();
					resultado.put("codigoVenta",codigoVenta);
					resultado.put("indicadorAccion",Constants.NUMERO_DOS);
					resultado.put("correlativoConsideracion", correlativoConsideracion);
					listResultado.add(resultado);
				}else{
					//marcarlo en la lista actual para no tomarlo
					Map m = list.get(index);
					m.put("indicadorExiste", Constants.NRO_UNO);
				}
			}
			
			for (Map map : list) {
				String indicadorExiste= (String)map.get("indicadorExiste");
				String aux=(String)map.get("codigoVenta");
				if(!Constants.NRO_UNO.equals(indicadorExiste)){
					Map resultado = new HashMap();
					resultado.put("codigoVenta",aux);
					resultado.put("indicadorAccion",Constants.NUMERO_CERO);
					listResultado.add(resultado);		
				}
			}								
			break;
		}		
		return listResultado;
	}

	/**
	 * obtien las variables de condicion2
	 * @param bean
	 * @param listVenta
	 * @return
	 */
	private List<Map> getListResultado2(Map bean, List<Map> list) {
		List <Map>listResultado = new ArrayList<Map>(); 
		Map criteria = new HashMap();
		criteria.put("indicadorTipo", bean.get("indicadorConsideracion"));
		criteria.put("codigoMensaje", bean.get("codigoMensaje"));
		criteria.put("codigoConsRest", bean.get("codigoConsRest"));		
		List <Map>listBD = (List)mantenimientoMENGenericoDAO.getDetalleConsRest(criteria);
		String codigoConsRest = (String)bean.get("codigoConsRest");
		int codigo = Integer.parseInt(codigoConsRest);
		switch (codigo) {
		case Constants.MEN_CONRES_CODIGO_PREMIO:
			break;
		case Constants.MEN_CONRES_CODIGO_VENTA:
				
			break;
		case Constants.MEN_CONRES_CUV_REEMPLAZO:
			for (Map map : listBD) {
				String codigoVenta=(String)map.get("valCondi2");
				String correlativoConsideracion =String.valueOf(map.get("correlativoConsideracion"));
				boolean existe =false;
				int index=0;
				for (Map m : list) {
					String aux=(String)m.get("codigoVenta");
					if(StringUtils.equals(codigoVenta, aux)){
						existe= true;
						break;
					}
				  index++;
				}
				if(!existe){//es x que lo han eliminado
					Map resultado = new HashMap();
					resultado.put("codigoVenta",codigoVenta);
					resultado.put("indicadorAccion",Constants.NUMERO_DOS);
					resultado.put("correlativoConsideracion", correlativoConsideracion);
					listResultado.add(resultado);
				}else{
					//marcarlo en la lista actual para no tomarlo
					Map m = list.get(index);
					m.put("indicadorExiste", Constants.NRO_UNO);
				}
			}
			
			for (Map map : list) {
				String indicadorExiste= (String)map.get("indicadorExiste");
				String aux=(String)map.get("codigoVenta");
				if(!Constants.NRO_UNO.equals(indicadorExiste)){
					Map resultado = new HashMap();
					resultado.put("codigoVenta",aux);
					resultado.put("indicadorAccion",Constants.NUMERO_CERO);
					listResultado.add(resultado);		
				}
			}					
			break;			
		case Constants.MEN_CONRES_CODIGO_PREMIO_REST:	
			break;			
		case Constants.MEN_CONRES_CODIGO_VENTA_REST:
			break;
		case Constants.MEN_CONRES_CUV_REEMPLAZO_REST:
			for (Map map : listBD) {
				String codigoVenta=(String)map.get("valCondi2");
				String correlativoConsideracion =String.valueOf(map.get("correlativoConsideracion"));
				boolean existe =false;
				int index=0;
				for (Map m : list) {
					String aux=(String)m.get("codigoVenta");
					if(StringUtils.equals(codigoVenta, aux)){
						existe= true;
						break;
					}
				  index++;
				}
				if(!existe){//es x que lo han eliminado
					Map resultado = new HashMap();
					resultado.put("codigoVenta",codigoVenta);
					resultado.put("indicadorAccion",Constants.NUMERO_DOS);
					resultado.put("correlativoConsideracion", correlativoConsideracion);
					listResultado.add(resultado);
				}else{
					//marcarlo en la lista actual para no tomarlo
					Map m = list.get(index);
					m.put("indicadorExiste", Constants.NRO_UNO);
				}
			}
			
			for (Map map : list) {
				String indicadorExiste= (String)map.get("indicadorExiste");
				String aux=(String)map.get("codigoVenta");
				if(!Constants.NRO_UNO.equals(indicadorExiste)){
					Map resultado = new HashMap();
					resultado.put("codigoVenta",aux);
					resultado.put("indicadorAccion",Constants.NUMERO_CERO);
					listResultado.add(resultado);		
				}
			}								
			break;
		}		
		return listResultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#updatePatronMensaje(java.util.Map)
	 */
	public void updatePatronMensaje(Map map) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getAbrevSeccion(java.util.Map)
	 */
	public String getAbrevSeccion(Map map) {		
		return mantenimientoMENGenericoDAO.getAbrevSeccion(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getExisteCodigoMensaje(java.lang.String)
	 */
	public int getExisteCodigoMensaje(String codigoMensaje,String campanhaProceso) {
		return mantenimientoMENGenericoDAO.getExisteCodigoMensaje(codigoMensaje,campanhaProceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getRestConsideracion(java.util.Map)
	 */
	public List getRestConsideracion(Map map) {
		return mantenimientoMENGenericoDAO.getRestConsideracion(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getClasificaciones(java.util.Map)
	 */
	public List getClasificaciones(Map criteria) {
		return mantenimientoMENGenericoDAO.getClasificaciones(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getDetalleConsRest(java.util.Map)
	 */
	public List getDetalleConsRest(Map criteria) {
		return mantenimientoMENGenericoDAO.getDetalleConsRest(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getSeccion(java.util.Map)
	 */
	public List getSeccion(Map criteria) {
		return mantenimientoMENGenericoDAO.getSeccion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getSubTiposClientes(java.util.Map)
	 */
	public List getSubTiposClientes(Map criteria) {
		return mantenimientoMENGenericoDAO.getSubTiposClientes(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getTerritorio(java.util.Map)
	 */
	public List getTerritorio(Map criteria) {
		return mantenimientoMENGenericoDAO.getTerritorio(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getTiposClasificaciones(java.util.Map)
	 */
	public List getTiposClasificaciones(Map criteria) {
		return mantenimientoMENGenericoDAO.getTiposClasificaciones(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getZona(java.util.Map)
	 */
	public List getZona(Map criteria) {
		return mantenimientoMENGenericoDAO.getZona(criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#executeCargaArchivoExcel(java.util.Map)
	 */
	public void executeCargaArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");		
		String oidPais = (String)criteria.get("oidPais");
		String indicadorTipo = (String)criteria.get("indicadorTipo");
		String oidPeriodoCorpo =(String)criteria.get("oidPeriodoCorpo");
		String oidModulo =(String)criteria.get("oidModulo");
		String login =(String)criteria.get("login");
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
				
		List list = null;
		int numfila=0;
		String mensajeError=null;
		while(excelUtil.hasNext()) {
			++numfila;
			Map mapRow = excelUtil.next();
			String codigoCliente =(String)mapRow.get("0");
			if(!existeConsultora(oidPais,codigoCliente)){
				mensajeError ="mantenimientoMENPatronMensajeForm.no.existen.consultoras";
				break;
			}
			 
			//Recuperamos  el registro del excel
			if(list==null) list = new ArrayList();
  		    //validaciones			 
			   if(isValidoRegistro(list,mapRow)){//es valido cuando el registro no existe en la lista
				   mapRow.put("oidModulo", oidModulo);
				   mapRow.put("oidPeriodoCorpo", oidPeriodoCorpo);
				   mapRow.put("login", login);
				   list.add(mapRow);				    
			   }else{//existe duplicados
				   mensajeError ="mantenimientoMENPatronMensajeForm.existen.duplicado";
					break; 
			   }	   
		}
		criteria.put("mensajeResultado", mensajeError);
		if(numfila == 0){
			mensajeError ="mantenimientoMENPatronMensajeForm.no.existen.lineas";
			criteria.put("mensajeResultado", mensajeError);
		}
		if(Constants.MEN_TIPO_CONSIDERACION.equals(indicadorTipo)){
			criteria.put("consultoraList", list);
		}else{
			criteria.put("consultoraListR", list);	
		}
		
		excelUtil.cerrar();
	}

	
	/**
	 * Retorna true cuando el registro es valido es decir no existe repetido, retorna false
	 * cuando el registro esta duplicado
	 * @param list
	 * @param mapRow
	 * @return
	 */
	public boolean isValidoRegistro(List list, Map mapRow) {
		 boolean valor=true;
		 String codigoCliente = (String)mapRow.get("0");
		 if(list!=null && list.size()>0){
			 Iterator it= list.iterator();
			 while(it.hasNext()){
				 Map params = (Map)it.next();
				 if(codigoCliente.equals(params.get("0"))){
					 valor=false;
					 break;
				 }
			 }	 
		 }	 
		 return valor;//hay duplicado false , caso contrario true
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaPuntajeBonificadoService#validarArchivoExcel(java.util.Map)
	 */
	public boolean validarArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		boolean valor = true;
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();		
			 if(mapRow.size()>22) {//las columnas recogidas + el numero de fila de fila procesda
				 valor = false;
				 break;
			 }			
		}
		excelUtil.cerrar();
		return valor;
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
		String codCliente =mantenimientoMAEClienteDAO.getExisteCodigoCliente(map);
		log.debug("existe consultora >>" + codCliente);
		return (codigoCliente.equals(codCliente)?true:false);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getDocumentosPatron(java.util.Map)
	 */
	public List getDocumentosPatron(Map criteria) {		
		return mantenimientoMENGenericoDAO.getDocumentosPatron(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getMensajesSeccionDocumentoPatron(java.util.Map)
	 */
	public List getMensajesSeccionDocumentoPatron(Map criteria) {
		return mantenimientoMENGenericoDAO.getMensajesSeccionDocumentoPatron(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getSeccionDocumentoPatron(java.util.Map)
	 */
	public List getSeccionDocumentoPatron(Map criteria) {
		return mantenimientoMENGenericoDAO.getSeccionDocumentoPatron(criteria);
	}

	public void replicarPatron(Map map) {
		String [] indexSeleccionados = (String []) map.get("indexSeleccionados");
		List arbol = (List)map.get("arbol");
		for(int i=0;i<indexSeleccionados.length;i++){
			int index = Integer.parseInt(indexSeleccionados[i]) - 1;
			Map bean = (Map)arbol.get(index);
			
			bean.put("codigoPais", map.get("codigoPais"));
			bean.put("login", map.get("login"));
			bean.put("campanhaOrigen",map.get("campanhaOrigen"));
			bean.put("campanhaDestino",map.get("campanhaDestino"));
			bean.put("cont", i);
			
			mantenimientoMENGenericoDAO.replicarPatron(bean);
		}		
		
		mantenimientoMENGenericoDAO.executeReplicarPatron();
	}	
	
	/**
	 * @param mantenimientoMAEClienteDAO the mantenimientoMAEClienteDAO to set
	 */
	public void setMantenimientoMAEClienteDAO(
			MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO) {
		this.mantenimientoMAEClienteDAO = mantenimientoMAEClienteDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#ordenarPatron(java.util.Map)
	 */
	public void ordenarPatron(Map map) {	
		List arbol = (List)map.get("arbol");
		Iterator<Map> it = arbol.iterator();
		int ordenMensaje=1;
		int ordenSeccion=1;
		String oidDocumento=null;
		String oidSeccion=null;
		String oidMensaje=null;
		while(it.hasNext()){
			Map o=it.next();
			String indModi = (String)o.get("indModi");
			String nivel = (String)o.get("nivel");
			String oid = o.get("oid").toString();
			
			String indAccion = (String)o.get("indAccion");//1:insertar 2:eliminar 0:seguir
			
			//if(Constants.NRO_CERO.equals(indModi)) continue;
				
				if(Constants.NRO_CERO.equals(nivel)){
					oidDocumento = oid;
					ordenMensaje=1;				
					ordenSeccion=1;
					continue;
				}
				
				if(Constants.NRO_UNO.equals(nivel)){
					oidSeccion = oid;
					ordenMensaje=1;
					Map criteria = new HashMap();
					criteria.put("oidDocumento", oidDocumento);
					criteria.put("oidSeccion", oidSeccion);
					criteria.put("orden", ordenSeccion);
					criteria.put("login", map.get("login"));
					if(Constants.NRO_UNO.equals(indModi))
						mantenimientoMENGenericoDAO.updateImpresionPatronSeccion(criteria);				
					ordenSeccion++;
				}
				
				if(Constants.NUMERO_DOS.equals(nivel)){				
					oidMensaje = oid;
					Map criteria = new HashMap();
					criteria.put("oidDocumento", oidDocumento);
					criteria.put("oidSeccion", oidSeccion);
					criteria.put("oidMensaje", oidMensaje);
					//CUANDO SE TRATA DE ELIMINACION NO SE NAHDE ORDEN
					if(!Constants.NUMERO_DOS.equals(indModi)) 
						criteria.put("orden", ordenMensaje);
					
					criteria.put("login", map.get("login"));
					criteria.put("codigoPais",map.get("codigoPais"));
					criteria.put("campanha",map.get("campanha"));
					
					if((Constants.NRO_CERO.equals(indModi) && Constants.NRO_UNO.equals(indAccion))
							|| (Constants.NRO_UNO.equals(indModi) && Constants.NRO_UNO.equals(indAccion))){										
						mantenimientoMENGenericoDAO.anahdeMensajePatronMensaje(criteria);			
					}
										
					if(Constants.NRO_UNO.equals(indModi) && Constants.NRO_CERO.equals(indAccion))
						mantenimientoMENGenericoDAO.updateImpresionPatronMensaje(criteria);
					
					if(Constants.NUMERO_DOS.equals(indModi)) 
						mantenimientoMENGenericoDAO.deleteImpresionPatronMensaje(criteria);
					//CUANDO SE TRATA DE ELIMINACION NO SE INCREMENTA ORDEN
					if(!Constants.NUMERO_DOS.equals(indModi))
						ordenMensaje++;
				}
			
		}
		
					
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getMensajeByOid(java.util.Map)
	 */
	public List getMensajeByOid(Map map) {
		return mantenimientoMENGenericoDAO.getMensajeByOid(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getRegistrosBuzon(java.util.Map)
	 */
	public int getRegistrosBuzon(Map map) {
		return mantenimientoMENGenericoDAO.getRegistrosBuzon(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getLoteBuzon(java.util.Map)
	 */
	public List getLoteBuzon(Map map) {
		return mantenimientoMENGenericoDAO.getLoteBuzon(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.men.service.MantenimientoMENGenericoService#getMensajesDisponibles(java.util.Map)
	 */
	public List getMensajesDisponibles(Map criteria) {		
		return mantenimientoMENGenericoDAO.getMensajesDisponibles(criteria);
	}
	
	public void leerMensajeImagen(Map criteria) {
		mantenimientoMENGenericoDAO.leerMensajeImagen(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENGenericoService#getListaBandejaFlyers(java.util.Map)
	 */
	public List getListaBandejaFlyers(Map criteria) {
		return mantenimientoMENGenericoDAO.getListaBandejaFlyers(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENGenericoService#executaSavePatronFlyersMensaje(java.util.Map)
	 */
	public void executaSavePatronFlyersMensaje(Map criteria) {
		mantenimientoMENGenericoDAO.executaSavePatronFlyersMensaje(criteria); 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENGenericoService#getListaFlyers(java.util.Map)
	 */
	public List getListaFlyers(Map criteria) {
		return mantenimientoMENGenericoDAO.getListaFlyers(criteria); 
	}
}
