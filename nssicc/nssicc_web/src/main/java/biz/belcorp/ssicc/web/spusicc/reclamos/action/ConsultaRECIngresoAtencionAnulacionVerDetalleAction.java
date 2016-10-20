/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ConsultaRECIngresoAtencionAnulacionForm;

/**
 * @author Carlos Bazalar
 */
@ManagedBean
@SessionScoped
public class ConsultaRECIngresoAtencionAnulacionVerDetalleAction extends BasePopupAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7696714433459679961L;
	private String flagVistaProcesoBatch; 
	private String numeroLote;
	private String tipoConsulta;
	private String accion;
	private String flagDelete;
	private List listaPadre;
	private String BANULARATENCIONES;
	

	@Override
	protected void setViewAtributes() throws Exception {
		//
		ConsultaRECIngresoAtencionAnulacionForm f = 
				(ConsultaRECIngresoAtencionAnulacionForm)this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		//this.flagDelete = Constants.SI;
		GenericoService genericoService = (GenericoService)getBean("genericoService");
		String flagVerBoton = genericoService.getParametroPais(pais.getCodigo(), Constants.SISTEMA_REC, Constants.BOTON_ANULAR_ATENCIONES);		
		f.setFlagRolDelete(flagVerBoton);	
		//se cambia el valor, para que el usuario pueda probar
	}
	
	public void confirmarPersonalizado(ActionEvent evt){
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String parametroVentana= externalContext.getRequestParameterMap().get("parametroVentana");
			if(StringUtils.equalsIgnoreCase(parametroVentana, "confirmarDialogEliminar")){
				if(validarDelete()){
					
				}else{
					String ventana = "confirmarDialogEliminar";
					String ventanaConfirmar = "PF('" + ventana
							+ "_confirmationDialogConfirmar').show()";
					this.getRequestContext().execute(ventanaConfirmar);
				}
				
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
	

	
	public void deleteAtencion(ActionEvent evt){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarDelete' method");
		}
		try {
			ConsultaRECIngresoAtencionAnulacionForm f = 
					(ConsultaRECIngresoAtencionAnulacionForm)this.formBusqueda;
				
				MantenimientoRECIngresoAtencionesService service = 
					(MantenimientoRECIngresoAtencionesService)getBean("spusicc.mantenimientoRECIngresoAtencionesService");
				
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
				
				Map map = new HashMap();
				map.put("numeroLote", f.getNumeroLote());
				map.put("codUsuario", usuario.getLogin());
				
				service.eliminarLoteAtencion(map);
				String mensajeError = (String) map.get("mensajeError");
				String mensajeGeneneral = this.getResourceMessage(mensajeError);
				if(!StringUtils.isBlank(mensajeError)){
					this.addInfo("Info", mensajeGeneneral);
				}
				f.setFlagDelete(Constants.NO);
				return;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

		
	}
	
	public Boolean validarDelete() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarDelete' method");
		}
		try {
			ConsultaRECIngresoAtencionAnulacionForm f = 
					(ConsultaRECIngresoAtencionAnulacionForm)this.formBusqueda;
				f.setNumeroLote(this.numeroLote);
				MantenimientoRECIngresoAtencionesService service = 
					(MantenimientoRECIngresoAtencionesService)getBean("spusicc.mantenimientoRECIngresoAtencionesService");
				
				Map map = new HashMap();
				map.put("numeroLote", f.getNumeroLote());
				
				service.validarEliminacionLote(map);
				
				String mensajeError = (String) map.get("mensajeError");
				String usuarioElim = (String) map.get("usuarioElim");
				Object[] objetoAMostrar = new Object[1];
				objetoAMostrar[0] = usuarioElim;
				String primerMensaje = this.getResourceMessage(mensajeError);
				
				if(mensajeError!=null){
					if(!StringUtils.isBlank(usuarioElim)){
						 primerMensaje = this.getResourceMessage(mensajeError,objetoAMostrar);
						 this.addInfo("Info :", primerMensaje); 
						 return true;
					}
					else{
						this.addError("Info : ",primerMensaje);
						return false;
					}
					
				}
				else{
					f.setFlagDelete(Constants.SI);
				}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		return false;

		
	}
	
	
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		this.flagDelete = Constants.NO;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		MantenimientoRECIngresoAtencionesService service = 	(MantenimientoRECIngresoAtencionesService)getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		this.accion = externalContext.getRequestParameterMap().get("accion"); 
		String rowIndex =  externalContext.getRequestParameterMap().get("parametro"); 
		this.tipoConsulta = externalContext.getRequestParameterMap().get("parametro2");
		this.flagVistaProcesoBatch = externalContext.getRequestParameterMap().get("parametro3");
		this.numeroLote = externalContext.getRequestParameterMap().get("parametro4");
		
		//Obtenes valor del parametro para la pantalla de Digitacion Simplificada
		GenericoService genericoService1 = (GenericoService) getBean("genericoService");
		
		ParametroPais parametroPais1 = new ParametroPais();
		parametroPais1.setCodigoPais(pais.getCodigo());
		parametroPais1.setCodigoSistema(Constants.CODIGO_SISTEMA_REC);
		parametroPais1.setNombreParametro("indGenerarArchivoAnul");
		
		List lstParametros1 = genericoService1.getParametrosPais(parametroPais1);
		String mostrarEnvioApe = Constants.NUMERO_CERO;
		
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros1.get(0);
			mostrarEnvioApe = ps.getValorParametro();
		}
		
		List resultado = new ArrayList();
		if(StringUtils.equals(this.flagVistaProcesoBatch, Constants.UNO)) {
			//Detalles dew Anulaciones
			Map map = new HashMap();
			map.put("numeroLote", this.numeroLote);
			
			resultado = service.getDetalleAnulacion(map);
		}
		else {
			Integer index = new Integer(rowIndex);
			
			List list = this.listaPadre;
			Map map = (Map)list.get(Integer.parseInt(rowIndex));
			resultado = new ArrayList();
			
			this.numeroLote = map.get("numeroLote").toString();
			
			if(Constants.NUMERO_CERO.equals(this.tipoConsulta)){
				//ATENCIONES
				resultado = service.getDetalleAtencion(map);
			}
			else{
				resultado = service.getDetalleAnulacion(map);
			}
		}
		return resultado;
	}


	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		ConsultaRECIngresoAtencionAnulacionForm form = new ConsultaRECIngresoAtencionAnulacionForm();
		return form;
	}
	
	


	/**
	 * @return the flagVistaProcesoBatch
	 */
	public String getFlagVistaProcesoBatch() {
		return flagVistaProcesoBatch;
	}


	/**
	 * @param flagVistaProcesoBatch the flagVistaProcesoBatch to set
	 */
	public void setFlagVistaProcesoBatch(String flagVistaProcesoBatch) {
		this.flagVistaProcesoBatch = flagVistaProcesoBatch;
	}


	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}


	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}


	/**
	 * @return the tipoConsulta
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}


	/**
	 * @param tipoConsulta the tipoConsulta to set
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}


	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}


	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}


	/**
	 * @return the flagDelete
	 */
	public String getFlagDelete() {
		return flagDelete;
	}


	/**
	 * @param flagDelete the flagDelete to set
	 */
	public void setFlagDelete(String flagDelete) {
		this.flagDelete = flagDelete;
	}


	/**
	 * @return the listaPadre
	 */
	public List getListaPadre() {
		return listaPadre;
	}


	/**
	 * @param listaPadre the listaPadre to set
	 */
	public void setListaPadre(List listaPadre) {
		this.listaPadre = listaPadre;
	}

	/**
	 * @return the bANULARATENCIONES
	 */
	public String getBANULARATENCIONES() {
		return BANULARATENCIONES;
	}

	/**
	 * @param bANULARATENCIONES the bANULARATENCIONES to set
	 */
	public void setBANULARATENCIONES(String bANULARATENCIONES) {
		BANULARATENCIONES = bANULARATENCIONES;
	}
	
	
	

}
