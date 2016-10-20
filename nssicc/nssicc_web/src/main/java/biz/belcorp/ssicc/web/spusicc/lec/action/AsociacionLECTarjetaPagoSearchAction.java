package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TarjetaLider;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TarjetaPago;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECTarjetaPagoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.lec.form.AsociacionLECTarjetaPagoForm;
import biz.belcorp.ssicc.web.spusicc.lec.form.AsociacionLECTarjetaPagoSearchForm;

@SuppressWarnings({"rawtypes","unchecked"})
@ManagedBean
@SessionScoped
public class AsociacionLECTarjetaPagoSearchAction extends BaseMantenimientoSearchAbstractAction {

	/**
	 * JPPS
	 */
	private static final long serialVersionUID = -4232544803809067321L;
	private List lecEstadTarjeList;
	private List lecTarjetaPagoTarjetaPagoList;
	private List lecTarjetaPagoLiderList;

	
	private String NUMERO_UNO  = Constants.NUMERO_UNO;
	private String NUMERO_CERO = Constants.NUMERO_CERO;
	
	private LabelValue[] lecRegionesList;
	private LabelValue[] lecZonasList;
	
	private DataTableModel tableModel;
	

	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "asociacionLECTarjetaPagoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "asociacionLECTarjetaPagoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		AsociacionLECTarjetaPagoSearchForm a = new AsociacionLECTarjetaPagoSearchForm();
		return a;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		
//		retorno="view";
		Map criteriaPeriodo = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
	     
		MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = controlFactService.getControlFacturacionById(criteriaPeriodo);
		
		AsociacionLECTarjetaPagoSearchForm f = (AsociacionLECTarjetaPagoSearchForm) this.formBusqueda;	
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		Map params = BeanUtils.describe(f);
		MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService) getBean("spusicc.mantenimientoLECTarjetaPagoService");
		
		List lista = new ArrayList();
		
		this.lecTarjetaPagoTarjetaPagoList = new ArrayList();
		this.lecTarjetaPagoLiderList = new ArrayList();
		//this.lecRegionesList = new LabelValue[]{};
		
		if(f.getTipoAsociacion().equals(Constants.NUMERO_UNO)){
			 lista = service.getLecAsociacionTarjetaPagoPorTarjetaPagoByCriteria(params);
			 lecTarjetaPagoTarjetaPagoList = lista;
			 tableModel = new DataTableModel(lecTarjetaPagoTarjetaPagoList);
		}
		if(f.getTipoAsociacion().equals(Constants.NUMERO_CERO)){
			 lista = service.getLecAsociacionTarjetaPagoPorLiderByCriteria(params);
			 lecTarjetaPagoLiderList = lista;
			 tableModel = new DataTableModel(lecTarjetaPagoLiderList);
		}					
		
		return lista;
	
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setSaveFindBeforeAttributes() throws Exception {
		this.mostrarErrorNoExistenRegistroBusqueda = false;
	}
	
	@Override
	public void setSalirFindAfterAttributes() throws Exception {
		this.mostrarErrorNoExistenRegistroBusqueda = true;
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - AsociacionLECTarjetaPagoAction");
		}
		
		AsociacionLECTarjetaPagoForm f = (AsociacionLECTarjetaPagoForm) this.formMantenimiento;
		MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService)getBean("spusicc.mantenimientoLECTarjetaPagoService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
//		f.setCodigoLiderGrilla(f.getCodigoLider());
		
		TarjetaLider tarjetaLider = new TarjetaLider();
		
		BeanUtils.copyProperties(tarjetaLider, f);
		
        try {
	        	Map criteriaPeriodo = new HashMap();
				criteriaPeriodo.put("codigoPais", pais.getCodigo());
				criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
				criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			     
				MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
				PedidoControlFacturacion controlFacturacion = controlFactService.getControlFacturacionById(criteriaPeriodo);
        	
        		if(f.getTipoAsociacion().equals(Constants.POR_TARJETA)){
        			
        			if(!f.getCodigoLider().equals(f.getCodigoLiderGrilla())){
        				//Si COD_ESTA = 02  ( Tarjeta Asignada )
        				//Buscar registro en Tarjeta Líder con COD_TARJ y actualizar registro con IND_ACTI = 9 y datos auditoría
        				Map params = new HashMap();
        				      
        				if(f.getnAsig().equals("1")){
        					
        					params = new HashMap();
        					params.put("estado", Constants.LEC_ESTADO_TARJETA_ASIGNADA); 
        					params.put("codigoTarjeta", f.getCodigoTarjetaGrilla()); 
        					
        					TarjetaLider objTarjeta = service.getTarjetaLider(params);
        					if(objTarjeta!=null){
        						/*objTarjeta.setUsuarioModificacion(usuario.getAuditInfo().getUpdatedBy());
            					objTarjeta.setFechaModificacion(usuario.getAuditInfo().getLastUpdated());
            					objTarjeta.setIndicarActivo(new Integer(9));
            					
            					params = BeanUtils.describe(objTarjeta);
            					
            					params.put("fechaModificacion", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, objTarjeta.getFechaModificacion()));
            					params.put("fechaCreacion", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, objTarjeta.getFechaCreacion()));
            					
            					params.put("codigoLiderAntiguo", objTarjeta.getCodigoLider()); 
            					params.put("codigoTarjetaAntiguo", objTarjeta.getCodigoTarjeta());
            					
            				
            					
            					service.updateTarjetaLider(params);*/
        						
        						//ahora se eliminara fisicamente ya no se cambiara a estado inactivo
        						Map paramsDelete = new HashMap();
        						paramsDelete.put("codigoLider", objTarjeta.getCodigoLider()); 
        						paramsDelete.put("codigoTarjeta", objTarjeta.getCodigoTarjeta());
            					service.deleteTarjetaLider(paramsDelete);
            					
            					//PONER NUEVO METODO (updateClienteLider)
            					Map parametrosCL = new HashMap();
            					parametrosCL.put("numLiah", null); 
            					parametrosCL.put("codigoManteClieLider", objTarjeta.getCodigoLider()); 
            					
            					service.updateClienteLider(parametrosCL);
        					}
        					
        				}
        				
        				if(f.getCodigoLiderGrilla()!=null && f.getCodigoLiderGrilla().trim().length()>0
        						&& f.getCodigoTarjetaGrilla()!=null && f.getCodigoTarjetaGrilla().trim().length()>0){
        					params = new HashMap();
        					params.put("codigoLider", f.getCodigoLiderGrilla().trim()); 
        					params.put("codigoTarjeta", f.getCodigoTarjetaGrilla().trim());
        					params.put("estado", Constants.LEC_ESTADO_TARJETA_ASIGNADA);
        					TarjetaLider tajetaLiderAntigua = service.getTarjetaLider(params);
        					if(tajetaLiderAntigua!=null){
        						//tajetaLiderAntigua.setUsuarioModificacion(usuario.getAuditInfo().getUpdatedBy());
        						//tajetaLiderAntigua.setFechaModificacion(usuario.getAuditInfo().getLastUpdated());
        						//tajetaLiderAntigua.setIndicarActivo(new Integer(9));
        						//params = BeanUtils.describe(tajetaLiderAntigua);
        						//params.put("codigoLiderAntiguo", tajetaLiderAntigua.getCodigoLider()); 
        						//params.put("codigoTarjetaAntiguo", tajetaLiderAntigua.getCodigoTarjeta());
            					//service.updateTarjetaLider(params);
        						
            					//ahora se eliminara fisicamente ya no se cambiara a estado inactivo
        						Map paramsDelete = new HashMap();
        						paramsDelete.put("codigoLider", tajetaLiderAntigua.getCodigoLider()); 
        						paramsDelete.put("codigoTarjeta", tajetaLiderAntigua.getCodigoTarjeta());
            					service.deleteTarjetaLider(paramsDelete);
            					
            					//PONER NUEVO METODO (updateClienteLider)

            					Map parametrosCL = new HashMap();
            					parametrosCL.put("numLiah", null); 
            					parametrosCL.put("codigoManteClieLider", tajetaLiderAntigua.getCodigoLider()); 
            					
            					service.updateClienteLider(parametrosCL);
            					
        					}	
        				}

        				
	
        				tarjetaLider.setCodigoTarjeta(f.getCodigoTarjetaGrilla());
                		
            			if(service.validaCodigoLider(f.getCodigoLider())){
            				
            				//Si nAsig y NnEnvi = 0   ( Lider no tiene otra tarjeta )
            				if(f.getnAsig().equals("0") && f.getnEnvi().equals("0")){
            					
            					//Actualizar estado de tarjeta  con 02’ en Tabla tarjetas
            					
            					TarjetaPago tarjetaPago= service.getTarjetaPago(tarjetaLider.getCodigoTarjeta());
            					tarjetaPago.setCodigoTarjeta(tarjetaLider.getCodigoTarjeta());
            					tarjetaPago.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_ASIGNADA);
            					
            					
            					tarjetaLider.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_ASIGNADA);
            					
            					service.updateEstadoTarjetaPago(tarjetaPago, usuario);
            					
            					//Crear Registro en Tarjeta Líder con Tarjeta seleccionada, CAM_CREA = 
            					//Campaña Activa y Datos Auditoría

            					
            					service.insertTarjetaLider(tarjetaLider, usuario);
            					
            					//PONER NUEVO METODO (updateClienteLider)
            					Map parametrosCL = new HashMap();
            					parametrosCL.put("numLiah", tarjetaPago.getNumeroTarjeta()); 
            					parametrosCL.put("codigoManteClieLider", tarjetaLider.getCodigoLider()); 
            					
            					service.updateClienteLider(parametrosCL);
            					
            					
            				}
            				//Si nAsig = 1   ( Lider tiene otra tarjeta asignada )
            				if(StringUtils.isNotBlank(f.getnAsig()) && f.getnAsig().equals("1")){
            					
            					//Actualizar estado de tarjeta encontrada para líder en Tabla Tarjetas con 01.
            					
            					params = new HashMap();
            					params.put("estado", Constants.LEC_ESTADO_TARJETA_ASIGNADA); 
            					params.put("codigoLider", tarjetaLider.getCodigoLider()); 
            					
            					TarjetaLider tarjetaLiderAntigua = service.getTarjetaLider(params);
            					
            					if(tarjetaLiderAntigua!=null){
            						TarjetaPago tarjetaPago= service.getTarjetaPago(tarjetaLiderAntigua.getCodigoTarjeta());
                					tarjetaPago.setCodigoTarjeta(tarjetaLiderAntigua.getCodigoTarjeta());
                					tarjetaPago.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_DISPONIBLE);
                					
                					service.updateEstadoTarjetaPago(tarjetaPago, usuario);
            					}
            					
            					//Actualizar Tarjeta Líder con código de tarjeta seleccionada, Actualizar Tabla 
            					//de Tarjeta con COD_ESTA = 02, CAMP-CREA = Campaña Activa y Datos Auditoría.
            					
            					TarjetaPago tarjetaPago= new TarjetaPago();
            					tarjetaPago.setCodigoTarjeta(tarjetaLider.getCodigoTarjeta());
            					tarjetaPago.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_ASIGNADA);
            					tarjetaPago.setCampanaCreacion(controlFacturacion.getCodigoPeriodo());
            					service.updateEstadoTarjetaPago(tarjetaPago, usuario);
            					
            					
            					params = BeanUtils.describe(tarjetaLiderAntigua);
            					params.put("codigoLiderAntiguo", tarjetaLiderAntigua.getCodigoLider()); 
            					params.put("codigoTarjetaAntiguo", tarjetaLiderAntigua.getCodigoTarjeta());
            					params.put("fechaModificacion", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, tarjetaLiderAntigua.getFechaModificacion()));
            					params.put("fechaCreacion", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, tarjetaLiderAntigua.getFechaCreacion()));
            					params.put("fechaEnvio", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, tarjetaLiderAntigua.getFechaEnvio()));
            					params.put("fechaBloqueo", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, new Date()));
            					params.put("codigoTarjeta", tarjetaLider.getCodigoTarjeta());
            					
            					
            					service.updateTarjetaLider(params);
            					//PONER NUEVO METODO (updateClienteLider)
            					Map parametrosCL = new HashMap();
            					TarjetaPago tarjePag= service.getTarjetaPago(tarjetaLider.getCodigoTarjeta());
            					parametrosCL.put("numLiah", tarjePag.getNumeroTarjeta()); 
            					parametrosCL.put("codigoManteClieLider", tarjetaLider.getCodigoLider()); 
            					
            					service.updateClienteLider(parametrosCL);
            					
//            					tarjetaLider.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_ASIGNADA);
//        						service.insertTarjetaLider(tarjetaLider, usuario);
            				}
            				
            				//Si nEnvi = 1  ( Lider tiene otra tarjeta enviada )
            				if(StringUtils.isNotBlank(f.getnEnvi()) && f.getnEnvi().equals("1")){
            					
            					//Actualizar estado de tarjeta encontrada para líder en Tabla Tarjetas con 04
            					
            					params = new HashMap();
            					params.put("estado", Constants.LEC_ESTADO_TARJETA_ENVIADA); 
            					params.put("codigoLider", tarjetaLider.getCodigoLider()); 
            					
            					TarjetaLider tarjetaLiderAntigua = service.getTarjetaLider(params);
            					
            					if(tarjetaLiderAntigua!=null){
            						TarjetaPago tarjetaPago= service.getTarjetaPago(tarjetaLiderAntigua.getCodigoTarjeta());
                					tarjetaPago.setCodigoTarjeta(tarjetaLiderAntigua.getCodigoTarjeta());
                					tarjetaPago.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_BLOQUEADA);
                					
                					service.updateEstadoTarjetaPago(tarjetaPago, usuario);
            					}
            					
            					//Actualizar Tarjeta Líder econtrada para líder, actualizar datos correspondiente a Bloqueo           					            					
            					           					
            					params = BeanUtils.describe(tarjetaLiderAntigua);
            					params.put("codigoLiderAntiguo", tarjetaLiderAntigua.getCodigoLider()); 
            					params.put("codigoTarjetaAntiguo", tarjetaLiderAntigua.getCodigoTarjeta());
            					params.put("campanaBloqueo", controlFacturacion.getCodigoPeriodo());
            					params.put("usuarioBloqueo", (usuario.getAuditInfo().getUpdatedBy()));
            					
            					params.put("fechaModificacion", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, tarjetaLiderAntigua.getFechaModificacion()));
            					params.put("fechaCreacion", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, tarjetaLiderAntigua.getFechaCreacion()));
            					params.put("fechaEnvio", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, tarjetaLiderAntigua.getFechaEnvio()));
            					params.put("fechaBloqueo", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, new Date()));
            					
            					service.updateTarjetaLider(params);
            					
            					//Crear Registro en Tarjeta Líder con Tarjeta seleccionada, CAM_CREA = Campaña Activa y Datos Auditoría.

            					//Cambia de estado la tarjeta nuevo
            					
            					if(tarjetaLider!=null){
            						TarjetaPago tarjetaPago= service.getTarjetaPago(tarjetaLider.getCodigoTarjeta());
                					tarjetaPago.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_ASIGNADA);
                					
                					service.updateEstadoTarjetaPago(tarjetaPago, usuario);
            					}
            					
            					service.insertTarjetaLider(tarjetaLider, usuario);
            					
            					//PONER NUEVO METODO (updateClienteLider)
            					Map parametrosCL = new HashMap();
            					TarjetaPago tarjePag= service.getTarjetaPago(tarjetaLider.getCodigoTarjeta());
            					parametrosCL.put("numLiah", tarjePag.getNumeroTarjeta()); 
            					parametrosCL.put("codigoManteClieLider", tarjetaLider.getCodigoLider()); 
            					
            					service.updateClienteLider(parametrosCL);
            				}
            				
	        			}else{
	        				addWarn("Mensaje: ", getResourceMessage("asociacionLECTarjetaPagoForm.error.lider") );  				    				        			        			
	            			return false;
	        			}
//            				addInfo("Mensaje: ", getResourceMessage("asociacionLECTarjetaPagoForm.created")); comente yo
                		
        			}else{
//        				addInfo("Mensaje: ", getResourceMessage("asociacionLECTarjetaPagoForm.updated")); comente yo
                		
        			}
        			
        		}else if(f.getTipoAsociacion().trim().equalsIgnoreCase(Constants.POR_LIDER)){
        			TarjetaPago tarjetaPagoAntigua =service.getTarjetaPago(f.getCodigoTarjetaGrilla());
        			String numeroTarjetaAntigua="";
        			if(tarjetaPagoAntigua!=null){
        				numeroTarjetaAntigua=tarjetaPagoAntigua.getNumeroTarjeta();
        			}
        			
        			//	Sólo actualizar si código de tarjeta ingresado es diferente a la grilla
        			
        			if(!f.getNumeroTarjeta().equals(numeroTarjetaAntigua)){
        				
        				if(service.validaNumeroTarjeta(f.getNumeroTarjeta())){
        					
        					Map params = new HashMap();
        					params.put("codigoLider", f.getCodigoLiderGrilla()); 
        					params.put("codigoTarjeta", f.getCodigoTarjetaGrilla());
        					
        					TarjetaLider objTarjetaLider = service.getTarjetaLider(params);
        					
//        					Si líder ya tiene tarjeta asignada en Tarjeta Lider con COD_ESTA = ‘02’
        					if(objTarjetaLider!=null){
        						
        						if(objTarjetaLider.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_ASIGNADA)){
            						
            						//Actualizar Tarjeta Líder con IND_ACTI = 9 y datos Auditoria. (A)
            						
                					if(objTarjetaLider!=null){
                						/*objTarjetaLider.setUsuarioModificacion(usuario.getAuditInfo().getUpdatedBy());
                						objTarjetaLider.setFechaModificacion(usuario.getAuditInfo().getLastUpdated());
                						objTarjetaLider.setIndicarActivo(new Integer(9));
                    					
                    					params = BeanUtils.describe(objTarjetaLider);
                    					params.put("codigoLiderAntiguo", objTarjetaLider.getCodigoLider()); 
                    					params.put("codigoTarjetaAntiguo", objTarjetaLider.getCodigoTarjeta());
                    					
                    					service.updateTarjetaLider(params);*/
                    					
                    					//ahora se eliminara fisicamente ya no se cambiara a estado inactivo
                						Map paramsDelete = new HashMap();
                						paramsDelete.put("codigoLider", objTarjetaLider.getCodigoLider()); 
                						paramsDelete.put("codigoTarjeta", objTarjetaLider.getCodigoTarjeta());
                    					service.deleteTarjetaLider(paramsDelete);
                    					
                    					//PONER NUEVO METODO (updateClienteLider)

                    					Map parametrosCL = new HashMap();
                    					parametrosCL.put("numLiah", null); 
                    					parametrosCL.put("codigoManteClieLider", objTarjetaLider.getCodigoLider()); 
                    					
                    					service.updateClienteLider(parametrosCL);
                					}

                					//Actualizar estado de tarjeta encontrada para l´der en Tabla tarjetas con 01.
                					tarjetaPagoAntigua.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_DISPONIBLE);   					
                					service.updateEstadoTarjetaPago(tarjetaPagoAntigua, usuario);
            					}
        						//	Si líder ya tiene tarjeta asignada en Tarjeta L´der con COD_ESTA = ‘03’
            					if(objTarjetaLider.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_ENVIADA)){
            						
            						//Actualizar estado de tarjeta encontrada para l´der en Tabla tarjetas con 04
            						
            						tarjetaPagoAntigua.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_BLOQUEADA);   					
                					service.updateEstadoTarjetaPago(tarjetaPagoAntigua, usuario);
                					
                					//Actualizar Tarjeta L´der encontarda para Líder, actualizar datos correspondientes a Bloqueo.
                					
                					if(objTarjetaLider!=null){
                						objTarjetaLider.setUsuarioBloqueo(usuario.getAuditInfo().getUpdatedBy());
                						objTarjetaLider.setFechaBloqueo(usuario.getAuditInfo().getCreated());
                						objTarjetaLider.setCampanaBloqueo(controlFacturacion.getCodigoPeriodo());
                    					
                    					params = BeanUtils.describe(objTarjetaLider);
                    					params.put("codigoLiderAntiguo", objTarjetaLider.getCodigoLider()); 
                    					params.put("codigoTarjetaAntiguo", objTarjetaLider.getCodigoTarjeta());
                    					
                    					service.updateTarjetaLider(params);
                					}
                					
            					}
            					
        					}
        					
        					
        					//Obtener estado de tarjeta ingresada
        					TarjetaPago tarjetaPagoNueva =service.getTarjetaPagoByNumeroTarjeta(f.getNumeroTarjeta());
        					
        					if(tarjetaPagoNueva.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_DISPONIBLE)){
        						
        						//Actualizar estado de tarjeta en 02
        						TarjetaPago obj=new TarjetaPago();
        						obj.setCampanaCreacion(tarjetaPagoNueva.getCampanaCreacion());
        						obj.setCodigoTarjeta(tarjetaPagoNueva.getCodigoTarjeta());
        						obj.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_ASIGNADA);
            					service.updateEstadoTarjetaPago(obj, usuario);
            					
            					//Crear registro en Tarjeta Lider con tarjeta seleccionada, CAM_CREA = Campaña Activa y datos Auditoria.
            					tarjetaLider.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_ASIGNADA);
            					tarjetaLider.setCodigoTarjeta(tarjetaPagoNueva.getCodigoTarjeta());
            					service.insertTarjetaLider(tarjetaLider, usuario);
            					
            					//PONER NUEVO METODO (updateClienteLider)
            					Map parametrosCL = new HashMap();
            					TarjetaPago tarjePag= service.getTarjetaPago(tarjetaLider.getCodigoTarjeta());
            					parametrosCL.put("numLiah", tarjePag.getNumeroTarjeta()); 
            					parametrosCL.put("codigoManteClieLider", tarjetaLider.getCodigoLider()); 
            					
            					service.updateClienteLider(parametrosCL);
        					}
        					
        					if(tarjetaPagoNueva.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_ASIGNADA)){
        						//Actualizar Tarjeta Lider, de anterior líder, con IND_ACTI = 9 (A)
        						
        						TarjetaPago tarjetaPago= service.getTarjetaPagoByNumeroTarjeta(f.getNumeroTarjeta());

        						
        						params = new HashMap();
        						params.put("estado", Constants.LEC_ESTADO_TARJETA_ASIGNADA);      					
            					params.put("codigoTarjeta", tarjetaPago.getCodigoTarjeta());
            					
            					TarjetaLider objTarjetaLiderAntiguo = service.getTarjetaLider(params);
            					
            					if(objTarjetaLiderAntiguo!=null){
            						/*objTarjetaLiderAntiguo.setUsuarioModificacion(usuario.getAuditInfo().getUpdatedBy());
            						objTarjetaLiderAntiguo.setFechaModificacion(usuario.getAuditInfo().getLastUpdated());
            						objTarjetaLiderAntiguo.setIndicarActivo(new Integer(9));
                					
                					params = BeanUtils.describe(objTarjetaLiderAntiguo);
                					params.put("codigoLiderAntiguo", objTarjetaLiderAntiguo.getCodigoLider()); 
                					params.put("codigoTarjetaAntiguo", objTarjetaLiderAntiguo.getCodigoTarjeta());
                					
                					service.updateTarjetaLider(params);*/
            						
            						//ahora se eliminara fisicamente ya no se cambiara a estado inactivo
            						Map paramsDelete = new HashMap();
            						paramsDelete.put("codigoLider", objTarjetaLiderAntiguo.getCodigoLider()); 
            						paramsDelete.put("codigoTarjeta", objTarjetaLiderAntiguo.getCodigoTarjeta());
                					service.deleteTarjetaLider(paramsDelete);
                					
                					//PONER NUEVO METODO (updateClienteLider)
                					Map parametrosCL = new HashMap();
                					parametrosCL.put("numLiah", null); 
                					parametrosCL.put("codigoManteClieLider", objTarjetaLiderAntiguo.getCodigoLider()); 
                					
                					service.updateClienteLider(parametrosCL);
            					}

        						//Crear registro en Tarjeta Lider con tarjeta seleccionada, CAM_CREA = Campaña Activa y Datos Auditoria.
        						tarjetaLider.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_ASIGNADA);
        						tarjetaLider.setCodigoTarjeta(tarjetaPagoNueva.getCodigoTarjeta());
        						service.insertTarjetaLider(tarjetaLider, usuario);
        						
        						//PONER NUEVO METODO (updateClienteLider)
        						Map parametrosCL = new HashMap();
        						TarjetaPago tarjePag= service.getTarjetaPago(tarjetaLider.getCodigoTarjeta());
            					parametrosCL.put("numLiah", tarjePag.getNumeroTarjeta()); 
            					parametrosCL.put("codigoManteClieLider", tarjetaLider.getCodigoLider()); 
            					
            					service.updateClienteLider(parametrosCL);
        					}
        				}else{
        					addWarn("Mensaje: ", getResourceMessage("asociacionLECTarjetaPagoForm.error.numeroTarjeta"));   				    				        			        			
	            			return false;
        				}
//        					addInfo("Mensaje: ", getResourceMessage("asociacionLECTarjetaPagoForm.created")); comente yo
        			}else{
//        					addInfo("Mensaje: ", getResourceMessage("asociacionLECTarjetaPagoForm.updated")); comente yo
        					}
        		}
        }
        catch (InvalidIdentifierException iie) {
            String codigo = iie.getIdentifier().toString();
            addError("Error: ", getResourceMessage("errors.invalid.id") + codigo);
            return false;
        }
        catch (InvalidDescriptionException ide) {
            String descripcion = ide.getDescription();
            addError("Error: ", getResourceMessage("errors.invalid.description"));
            return false;
        }
				
		return true;
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		AsociacionLECTarjetaPagoForm f = (AsociacionLECTarjetaPagoForm) this.formMantenimiento;
		MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService) getBean("spusicc.mantenimientoLECTarjetaPagoService");

		if (f.getTipoAsociacion().equals(Constants.POR_TARJETA)) 
		{
			if (!f.getCodigoLider().equals(f.getCodigoLiderGrilla())) 
			{
				return "asociacionLECTarjetaPagoForm.created";
			} else
				return "asociacionLECTarjetaPagoForm.updated";
		} else 
		{
			if (f.getTipoAsociacion().trim().equalsIgnoreCase(Constants.POR_LIDER)) 
			{
				TarjetaPago tarjetaPagoAntigua = service.getTarjetaPago(f.getCodigoTarjetaGrilla());
				String numeroTarjetaAntigua = "";
				if (tarjetaPagoAntigua != null) {
					numeroTarjetaAntigua = tarjetaPagoAntigua.getNumeroTarjeta();
				}
				if (!f.getNumeroTarjeta().equals(numeroTarjetaAntigua)) 
				{
					return "asociacionLECTarjetaPagoForm.created";
				} else 
				{
					return "asociacionLECTarjetaPagoForm.updated";
				}
			}else
				return "";
		}
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {		
		return null;		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		if(log.isDebugEnabled()){
			log.debug("setViewAttributes - AsociacionLECTarjetaPagoSearchAction");
		}		
//		retorno="view";
		this.mostrarListaBusqueda = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		
		MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService) getBean("spusicc.mantenimientoLECTarjetaPagoService"); 
		
		this.lecEstadTarjeList = service.getEstadoTarjetaPago();
		AsociacionLECTarjetaPagoSearchForm f = (AsociacionLECTarjetaPagoSearchForm) this.formBusqueda;
		this.lecTarjetaPagoTarjetaPagoList = new ArrayList();
		this.lecTarjetaPagoLiderList = new ArrayList();
		f.setTipoAsociacion("");
	
		LabelValue todos = new LabelValue();
		todos.setLabel("Todos");
		todos.setValue("");
		this.lecZonasList = new LabelValue[]{todos};		
	}
	
	public void bloquearAsociacionLECTarjetaPago(ActionEvent event) throws Exception {
		
		try{
			MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService)getBean("spusicc.mantenimientoLECTarjetaPagoService");
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser(); 
			
			Map bean = (Map) this.beanRegistroSeleccionado;
			String codigo = bean.get("codigo").toString();;
			
			if(StringUtils.isNotBlank(codigo))
			{				
				String[] codigoCompuesto= codigo.split("-");
				String codigoLider="";
				String codigoTarjeta="";
				if(codigoCompuesto.length>1){
					if(codigoCompuesto[0]!=null && codigoCompuesto[0].length()>0){
						codigoLider = codigoCompuesto[0];
					}
					if(codigoCompuesto[1]!=null && codigoCompuesto[1].length()>0){
						codigoTarjeta = codigoCompuesto[1];
						TarjetaPago tarjetaPago = service.getTarjetaPago(codigoTarjeta); 
						
						//17.	Sólo permitir Bloquear Tarjeta si tiene estado de Enviado  (COD_ESTA = ‘03’ )
						
						if(tarjetaPago.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_ENVIADA)){
							

							//actualizar Tabla de Tarjetas con estado 04
	    					tarjetaPago.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_BLOQUEADA);
	    					service.updateEstadoTarjetaPago(tarjetaPago, usuario);
							
							//20.	Actualizar Tarjeta Lider,  Campaña Bloqueo con Campaña Activa.
	    					Map params = new HashMap();
	    					params.put("codigoLider", codigoLider); 
	    					params.put("codigoTarjeta", codigoTarjeta);
	    					
							TarjetaLider objTarjetaLider = service.getTarjetaLider(params);
	    					
							if(objTarjetaLider!=null){
								
								Map criteriaPeriodo = new HashMap();
								criteriaPeriodo.put("codigoPais", pais.getCodigo());
								criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
								criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
							     
								MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
								PedidoControlFacturacion controlFacturacion = controlFactService.getControlFacturacionById(criteriaPeriodo);
								
	    						objTarjetaLider.setUsuarioModificacion(usuario.getAuditInfo().getUpdatedBy());
	    						objTarjetaLider.setFechaModificacion(new Date());
	    						objTarjetaLider.setCampanaBloqueo(controlFacturacion.getCodigoPeriodo());
	    						objTarjetaLider.setFechaBloqueo(new Date());
	    						objTarjetaLider.setUsuarioBloqueo(usuario.getAuditInfo().getUpdatedBy());
	        					
	        					params = BeanUtils.describe(objTarjetaLider);
	        					
	        					
	        					params.put("fechaEnvio", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, objTarjetaLider.getFechaEnvio()));
	        					params.put("fechaModificacion", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, objTarjetaLider.getFechaModificacion()));
	        					
	        					params.put("fechaCreacion", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, objTarjetaLider.getFechaCreacion()));
	        					params.put("fechaBloqueo", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, objTarjetaLider.getFechaBloqueo()));
	        					
	        					
	        					params.put("codigoLiderAntiguo", objTarjetaLider.getCodigoLider()); 
	        					params.put("codigoTarjetaAntiguo", objTarjetaLider.getCodigoTarjeta());
	        					
	        					service.updateTarjetaLider(params);
	        					
	        					//PONER NUEVO METODO (updateClienteLider)
	        					Map parametrosCL = new HashMap();
	        					parametrosCL.put("numLiah", null); 
	        					parametrosCL.put("codigoManteClieLider", objTarjetaLider.getCodigoLider()); 
	        					
	        					service.updateClienteLider(parametrosCL);
	    					}
							addInfo("Mensaje: ", getResourceMessage("asociacionLECTarjetaPagoForm.bloquear"));
							
						}else{
							
							addError("Error: ", getResourceMessage("asociacionLECTarjetaPagoForm.error.bloquear"));
						}
						
					}
				}else{
							addError("Error: ", getResourceMessage("asociacionLECTarjetaPagoForm.error.bloquear")); 
				}
			
			}
			
//			this.formMantenimiento =f;
//			this.redireccionarPagina("asociacionLECTarjetaPagoForm");
			
		}catch (Exception e) {
			
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			addError("Error: ", getResourceMessage("errors.detail")+error);			
		}
		
	}
	
	public void validaSeleccion(ValueChangeEvent event) 
	{
		AsociacionLECTarjetaPagoSearchForm f = (AsociacionLECTarjetaPagoSearchForm) this.formBusqueda;
		loadRegiones(f);
		f.setCodigoLider("");
		f.setNumeroTarjeta("");
		f.setEstadoTarjeta("");
		
		this.listaBusqueda = new ArrayList();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		
//		Constants.LEC_TARJETA_PAGO_LIDER_LIST;
//		Constants.LEC_TARJETA_PAGO_TARJETA_PAGO_LIST;
	}
	
	private void loadRegiones(AsociacionLECTarjetaPagoSearchForm f)
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		this.lecRegionesList = ajax.getRegionesCodigoByPais(f.getCodigoPais());
	}
	
	/**
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val){
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String[] regiones = new String[1];
		String region = (String)val.getNewValue();
		if (StringUtils.isNotBlank(region)) {
			regiones[0] = region;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if(regiones.length > 0){				
				this.lecZonasList = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD",  regiones,"");
			}						
		}else{
			LabelValue todos = new LabelValue();
			todos.setLabel("Todos");
			todos.setValue("");
			this.lecZonasList = new LabelValue[]{todos};
		}	
	}

	/**
	 * @param event
	 * @throws Exception
	 */
	public void editar(ActionEvent event) throws Exception 
	{

		AsociacionLECTarjetaPagoSearchForm form = (AsociacionLECTarjetaPagoSearchForm) this.formBusqueda;
		AsociacionLECTarjetaPagoForm f = new AsociacionLECTarjetaPagoForm() ;	
		
		MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService)getBean("spusicc.mantenimientoLECTarjetaPagoService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		try{
			
			
			Map bean = (Map) this.beanRegistroSeleccionado;
			String id = bean.get("codigo").toString();
			String tipoAsociacion = form.getTipoAsociacion();
			
			if(log.isDebugEnabled()){
				log.debug("Tipo Asociacion "+tipoAsociacion);
			}
	
			TarjetaPago tarjetaPago=null;
			TarjetaLider objTarjetaLider=null;
			if(StringUtils.isNotBlank(id))
			{				
				String[] codigoCompuesto= id.split("-");
				String codigoLider="";
				String codigoTarjeta="";
				if(codigoCompuesto.length>1){
					if(codigoCompuesto[0]!=null && codigoCompuesto[0].length()>0){
						codigoLider = codigoCompuesto[0];
					}
					if(codigoCompuesto[1]!=null && codigoCompuesto[1].length()>0){
						codigoTarjeta = codigoCompuesto[1];
						tarjetaPago = service.getTarjetaPago(codigoTarjeta);
						
						if(form.getTipoAsociacion() != null && form.getTipoAsociacion().equals(Constants.NUMERO_UNO))
						{
							
							f.setTipoAsociacion(Constants.POR_TARJETA);
							tipoAsociacion = f.getTipoAsociacion();
						}
						else
							f.setTipoAsociacion(Constants.POR_LIDER);
						
						if(f.getTipoAsociacion().equals(Constants.POR_TARJETA)){
							
							if(!tarjetaPago.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_DISPONIBLE) &&
									!tarjetaPago.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_ASIGNADA)){
								   
								addError("Error: ", getResourceMessage("asociacionLECTarjetaPagoForm.error.editar"));
								return;
							}
						}
						else if(f.getTipoAsociacion().trim().equalsIgnoreCase(Constants.POR_LIDER)){
						
							if(codigoLider!=null && codigoLider.trim().length()>0 && codigoTarjeta!=null && codigoTarjeta.trim().length()>0){
								Map params = new HashMap();
		        				params.put("codigoLider", codigoLider); 
		        				params.put("codigoTarjeta", codigoTarjeta);       
		 	
								objTarjetaLider = service.getTarjetaLider(params);
								
								
							}							
				
						}
			
					}
				}else{
					if(codigoCompuesto[0]!=null && codigoCompuesto[0].length()>0){
						codigoLider = codigoCompuesto[0];
					}
				}
			if(tarjetaPago!=null){
				f.setNumeroTarjeta(tarjetaPago.getNumeroTarjeta());
			}
			f.setTipoAsociacion(tipoAsociacion);
			f.setCodigoLiderGrilla(codigoLider);
			f.setCodigoLider(codigoLider);
			f.setCodigoTarjetaGrilla(codigoTarjeta);
			
			if(f.getCodigoLider()!=null && f.getCodigoLider().trim().length()>0){
				f.setNombreLider(service.getNombreLider(f.getCodigoLider()));
			}
			
			
			if(objTarjetaLider!=null){
				if(!objTarjetaLider.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_ASIGNADA)
					&& !objTarjetaLider.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_ENVIADA)){
						f.setNumeroTarjeta("");						
					}
			}
			
			if(tarjetaPago!=null){
				if(!tarjetaPago.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_ASIGNADA)
					&& !tarjetaPago.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_ENVIADA)){
						f.setCodigoLider("");		
						f.setNombreLider("");
					}
			}
			
			
		}
		else
			{	addInfo("Mensaje: ", "Primero seleccione un elemento de la grilla");
				return;
			}
		
		this.formMantenimiento =f;
		this.redireccionarPagina("asociacionLECTarjetaPagoForm");
		
	}
		catch(Exception e)
				{
					addError("Error: ", getResourceMessage("")+e);
				}
		
	
	}
	
	public void delete (ActionEvent event)
	{
		try{
			
			MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService)getBean("spusicc.mantenimientoLECTarjetaPagoService");
			
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			Map bean = (Map) this.beanRegistroSeleccionado;
			String codigo = bean.get("codigo").toString();
			
			if(StringUtils.isNotBlank(codigo))
			{				
				String[] codigoCompuesto= codigo.split("-");
				String codigoLider="";
				String codigoTarjeta="";
				
				if(codigoCompuesto.length>1){
					if(codigoCompuesto[0]!=null && codigoCompuesto[0].length()>0){
						codigoLider = codigoCompuesto[0];
					}
					if(codigoCompuesto[1]!=null && codigoCompuesto[1].length()>0){
						codigoTarjeta = codigoCompuesto[1];
						TarjetaPago tarjetaPago = service.getTarjetaPago(codigoTarjeta); 
						
						//El sistema sólo permitirá Desvncular tarjeta si COD-ESTA = ‘02’
						
						if(tarjetaPago.getCodigoEstado().equals(Constants.LEC_ESTADO_TARJETA_ASIGNADA)){
							

							//Actualizar estado de tarjeta en ‘01’.
	    					tarjetaPago.setCodigoEstado(Constants.LEC_ESTADO_TARJETA_DISPONIBLE);
	    					service.updateEstadoTarjetaPago(tarjetaPago, usuario);
							
							//Actualizar la entidad Tarjeta Líder actualizando IND_ACTI = 9 y datos auditoría
	    					Map params = new HashMap();
	    					params.put("codigoLider", codigoLider); 
	    					params.put("codigoTarjeta", codigoTarjeta);
	    					
							TarjetaLider objTarjetaLider = service.getTarjetaLider(params);
	    					
							if(objTarjetaLider!=null){
	    						objTarjetaLider.setUsuarioModificacion(usuario.getAuditInfo().getUpdatedBy());
	    						objTarjetaLider.setFechaModificacion(new Date());
	    						objTarjetaLider.setIndicarActivo(new Integer(9));
	        					
	        					params = BeanUtils.describe(objTarjetaLider);
	        					params.put("codigoLiderAntiguo", objTarjetaLider.getCodigoLider()); 
	        					params.put("codigoTarjetaAntiguo", objTarjetaLider.getCodigoTarjeta());
	        					
	        					
	        					params.put("fechaModificacion", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, objTarjetaLider.getFechaModificacion()));
	        					
	        					params.put("fechaCreacion", DateUtil.convertDateToString(Constants.DEFAULT_DATE_TIME_FORMAT, objTarjetaLider.getFechaCreacion()));
	        					
	        					//cambio 30/11/2015
	        					//service.updateTarjetaLider(params);
	        					service.deleteTarjetaLider(params);
	        					
	        					//PONER NUEVO METODO (updateClienteLider)
	        					Map parametrosCL = new HashMap();
	        					parametrosCL.put("numLiah", null); 
	        					parametrosCL.put("codigoManteClieLider", objTarjetaLider.getCodigoLider()); 
	        					
	        					service.updateClienteLider(parametrosCL);
	    					}
								addInfo("Mensaje: ", getResourceMessage("asociacionLECTarjetaPagoForm.desvincular"));
								this.listaBusqueda = this.setFindAttributes();
								this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
						}else{
								addError("Error: ", getResourceMessage("asociacionLECTarjetaPagoForm.error.desvincular"));	
								return;
						}					
					}
				}else{
					addError("Error: ", getResourceMessage("asociacionLECTarjetaPagoForm.error.desvincular"));
					return;
				}
			}	
		}
		catch(Exception e)
		{
			addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	private void completarCaracteres(AsociacionLECTarjetaPagoForm f)
	{		
		f.setCodigoLider(StringUtils.leftPad(f.getCodigoLider(), 9, "0"));
	}
	
	private void seteaFocoCodigoLider (String valor)throws Exception {
		
		AsociacionLECTarjetaPagoForm f = (AsociacionLECTarjetaPagoForm) this.formMantenimiento; 

		try{
				if(f.getCodigoLider().length()<10 && f.getCodigoLider()!= null)
				{
					this.completarCaracteres(f);
					AjaxService ajax =(AjaxService)getBean("ajaxService");
					String nombreClie = ajax.getNombreLider(f.getCodigoLider());
					if(nombreClie !=null)
						f.setNombreLider(nombreClie);
					else{
						addWarn("Error: ", "El código de Lider no existe");
						return;}
				}
		}catch(Exception e)
			{
				addError("Error: ", this.obtieneMensajeErrorException(e));
			}
	}
	
	public void metodoKeyPressSeteaFocoCodigoLider() throws Exception
	{
		AsociacionLECTarjetaPagoForm f = (AsociacionLECTarjetaPagoForm) this.formMantenimiento; 
		if(StringUtils.isNotBlank(f.getCodigoLider()))
		{
			completarCaracteres(f);
			seteaFocoCodigoLider(f.getCodigoLider());		
		}		
	}
	
	public void metodoKeyUpSeteaFocoCodigoLider() throws Exception
	{
		AsociacionLECTarjetaPagoForm f = (AsociacionLECTarjetaPagoForm) this.formMantenimiento;
		if(StringUtils.isNotBlank(f.getCodigoLider()))
		{
			if(f.getCodigoLider().length() == 9)
				seteaFocoCodigoLider(f.getCodigoLider());
		}
	}
	
	public void validaNumeroTarjeta(){
		AsociacionLECTarjetaPagoForm f = (AsociacionLECTarjetaPagoForm) this.formMantenimiento; 
		AjaxService ajax =(AjaxService)getBean("ajaxService");
		String num = ajax.obtenerTarjetaPago(f.getNumeroTarjeta());
		if(StringUtils.isNotBlank(num))
			f.setNumeroTarjeta(num);
		else
			addWarn("Error: ", "El número de tarjeta no existe");
	}
	
	@Override
	public String setValidarMantenimiento() {
		
		AsociacionLECTarjetaPagoSearchForm form = (AsociacionLECTarjetaPagoSearchForm) this.formBusqueda;
		AsociacionLECTarjetaPagoForm f = (AsociacionLECTarjetaPagoForm) this.formMantenimiento;
		AjaxService ajax =(AjaxService)getBean("ajaxService");
		String mensaje = null;
		f.setnAsig("0");
		f.setnEnvi("0");
		if(form.getTipoAsociacion().equals(Constants.POR_TARJETA))
		{
			if(!f.getCodigoLider().equals(f.getCodigoLiderGrilla()))
			{
				String codigo = ajax.validaLiderTarjetaAsociada(f.getCodigoLider());
				if(codigo != null){
					String[] valor;
					valor = codigo.split(";");
					form.setEstadoTarjeta(valor[0]);
					form.setNumeroTarjeta(valor[1]);
					
					if(form.getEstadoTarjeta().equals("02"))
						f.setnAsig("1");	    		
					else 
						{if(form.getEstadoTarjeta().equals("03"))	  
							f.setnEnvi("1");
						}
					if(f.getnAsig().equals("1")){
						//addWarn("Mensaje", getResourceMessage("confirm.save.estadoTarjetaAsignada") + form.getNumeroTarjeta()+ "¿Desea Continuar?"); 
		    		}else 
		    			if(f.getnEnvi().equals("1"))
		    			{
		    				//mensaje = getResourceMessage("confirm.save.estadoTarjetaEnviada") + "" + f.getNumeroTarjeta();
		   	    		}
				}
		    	else{
		    		f.setnAsig("0");
		    		f.setnEnvi("0");
		    		return mensaje = "";
		    	}
			 }
		}
		else{
			if(validaNumeroDigitosTarjeta()){
			String num = ajax.validaTarjetaPagoAsociada(f.getNumeroTarjeta());
				if(num != null)
				{
					String[] valor;
					valor = num.split(";");
					String codigoEstado = valor[0];
					f.setCodigoLider(valor[1]);
					
					if(codigoEstado.equals("02"))
					{ 
						//addWarn("Mensaje: ", getResourceMessage("confirm.save.tarjetaAsignadalider")+f.getCodigoLider() +"¿Desea Continuar?");
						
		    		}
					else{ 
		    			if(codigoEstado.equals("03") || codigoEstado.equals("04"))
		    				{
				    			mensaje = getResourceMessage("asociacionLECTarjetaPagoForm.error.tarjetaPagoEnviadaBloqueada");
		    				}
		    			}
				}
			}else
			{
				mensaje = getResourceMessage("asociacionLECTarjetaPagoForm.error.numeroTarjeta");
			}
				
		}
		
		return mensaje;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#cambiarMensajeConfirmacionSave()
	 */
	public String cambiarMensajeConfirmacionSave() {
		AsociacionLECTarjetaPagoSearchForm form = (AsociacionLECTarjetaPagoSearchForm) this.formBusqueda;
		AsociacionLECTarjetaPagoForm f = (AsociacionLECTarjetaPagoForm) this.formMantenimiento;
		AjaxService ajax =(AjaxService)getBean("ajaxService");
		String mensaje = null;
		f.setnAsig("0");
		f.setnEnvi("0");
		if(form.getTipoAsociacion().equals(Constants.POR_TARJETA))
		{
			if(!f.getCodigoLider().equals(f.getCodigoLiderGrilla()))
			{
				String codigo = ajax.validaLiderTarjetaAsociada(f.getCodigoLider());
				if(codigo != null){
					String[] valor;
					valor = codigo.split(";");
					form.setEstadoTarjeta(valor[0]);
					form.setNumeroTarjeta(valor[1]);
					
					if(form.getEstadoTarjeta().equals("02"))
						f.setnAsig("1");	    		
					else 
						{if(form.getEstadoTarjeta().equals("03"))	  
							f.setnEnvi("1");
						}
					if(f.getnAsig().equals("1")){
						mensaje = getResourceMessage("confirm.save.estadoTarjetaAsignada") + form.getNumeroTarjeta()+ " ¿Desea Continuar?"; 
		    		}
					if(f.getnEnvi().equals("1")){
						mensaje = getResourceMessage("confirm.save.estadoTarjetaEnviada") + form.getNumeroTarjeta()+ " ¿Desea Continuar?"; 
		    		}
					
				}
		    	
			 }
		}
		else{
			if(validaNumeroDigitosTarjeta()){
			String num = ajax.validaTarjetaPagoAsociada(f.getNumeroTarjeta());
				if(num != null)
				{
					String[] valor;
					valor = num.split(";");
					String codigoEstado = valor[0];
					f.setCodigoLider(valor[1]);
					
					if(codigoEstado.equals("02"))
					{ 
						mensaje = getResourceMessage("confirm.save.tarjetaAsignadalider")+f.getCodigoLider() +" ¿Desea Continuar?";
		    		}
					
				}
			}
				
		}
		
		return mensaje;
	}
	
	/**
	 * 
	 */
	public void customAsociacionLECTarjetaPagoForm()
	{	
		AsociacionLECTarjetaPagoSearchForm form = (AsociacionLECTarjetaPagoSearchForm) this.formBusqueda;
		AjaxService ajax =(AjaxService)getBean("ajaxService");
		if(form.getTipoAsociacion().equals(Constants.POR_TARJETA))
		{
			String codigo = ajax.validaLiderTarjetaAsociada(form.getCodigoLider());
			if(StringUtils.isNotBlank(codigo))
				form.setCodigoLider(codigo);
			else
				addWarn("Error: ", "El código de lider no existe");
		}
		else
		{
			String num = ajax.validaTarjetaPagoAsociada(form.getNumeroTarjeta());
			form.setNumeroTarjeta(num);
		}
	}
	
	private boolean validaNumeroDigitosTarjeta()
	{
		AsociacionLECTarjetaPagoForm f = (AsociacionLECTarjetaPagoForm) this.formMantenimiento;
		String numeroTarjeta = f.getNumeroTarjeta();

		if(numeroTarjeta.length() < 16)
		{
			addWarn("Mensaje: ", getResourceMessage("mantenimientoLECTarjetaPagoForm.error.numeroTarjeta.menor"));	
			f.setNumeroTarjeta("");
			return false;
		}else{
			return true;
		}
	}
	
	public List getLecEstadTarjeList() {
		return lecEstadTarjeList;
	}

	public void setLecEstadTarjeList(List lecEstadTarjeList) {
		this.lecEstadTarjeList = lecEstadTarjeList;
	}

	public List getLecTarjetaPagoTarjetaPagoList() {
		return lecTarjetaPagoTarjetaPagoList;
	}

	public void setLecTarjetaPagoTarjetaPagoList(List lecTarjetaPagoTarjetaPagoList) {
		this.lecTarjetaPagoTarjetaPagoList = lecTarjetaPagoTarjetaPagoList;
	}

	public List getLecTarjetaPagoLiderList() {
		return lecTarjetaPagoLiderList;
	}

	public void setLecTarjetaPagoLiderList(List lecTarjetaPagoLiderList) {
		this.lecTarjetaPagoLiderList = lecTarjetaPagoLiderList;
	}

	public String getNUMERO_UNO() {
		return NUMERO_UNO;
	}

	public void setNUMERO_UNO(String nUMERO_UNO) {
		NUMERO_UNO = nUMERO_UNO;
	}

	public String getNUMERO_CERO() {
		return NUMERO_CERO;
	}

	public void setNUMERO_CERO(String nUMERO_CERO) {
		NUMERO_CERO = nUMERO_CERO;
	}

	public LabelValue[] getLecRegionesList() {
		return lecRegionesList;
	}

	public void setLecRegionesList(LabelValue[] lecRegionesList) {
		this.lecRegionesList = lecRegionesList;
	}

	public LabelValue[] getLecZonasList() {
		return lecZonasList;
	}

	public void setLecZonasList(LabelValue[] lecZonasList) {
		this.lecZonasList = lecZonasList;
	}

	public DataTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DataTableModel tableModel) {
		this.tableModel = tableModel;
	}
}
