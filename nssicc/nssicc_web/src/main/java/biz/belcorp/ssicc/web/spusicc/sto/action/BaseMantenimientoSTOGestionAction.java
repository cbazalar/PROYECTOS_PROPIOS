package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.BaseMantenimientoSTOGestionForm;

public class BaseMantenimientoSTOGestionAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = 7829706083380468544L;
	
	public static final String STO_MENSAJE_SEPARATOR = "\n";
	public String nombreConsultora;
	private List stoMensajeErrorList;
	private Object registroSeleccionado;

	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		BaseMantenimientoSTOGestionForm form = new BaseMantenimientoSTOGestionForm();
		return form;
	}
	
	public void obtenerValores()throws Exception{
		
		GestionDocumento gestion=(GestionDocumento)this.registroSeleccionado;
		
		List messageList = new ArrayList();		
		String message= gestion.getMensaje();
		if (StringUtils.isNotEmpty(message)){
			String [] splitMessage =  message.split(STO_MENSAJE_SEPARATOR);
			
			for (int i = 0; i < splitMessage.length; i++) {
				messageList.add(splitMessage[i]);
			}
		}
		
		//request.removeAttribute(Constants.STO_GESTION_DATA);
		//request.setAttribute(Constants.STO_GESTION_DATA, gestion);
		
		
		this.stoMensajeErrorList=messageList;	
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		BaseMantenimientoSTOGestionForm f =(BaseMantenimientoSTOGestionForm)this.formBusqueda;		
		f.setCodigoPais(gestion.getCodigoPais());
		f.setTipoDocumento(gestion.getTipoDocumento());
		f.setLote(gestion.getLote());
		f.setNumeroDocumento(gestion.getNumDocumento());
		f.setValidacion(gestion.getValidacion());
		f.setDetalle(gestion.getDetalle());
		f.setDocumento(gestion.getDocumento());
		f.setCodigoCliente(gestion.getCodigoCliente());
		f.setDesValidacion(gestion.getDesValidacion());
		f.setDesValidacionLarga(gestion.getDesValidacionLarga());
		f.setMensaje(gestion.getMensaje());		
		this.nombreConsultora=ajax.getNombreCliente(f.getCodigoCliente());
	}
	

	@Override
	protected void setViewAtributes() throws Exception {		
	}  
	
	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	public String getNombreConsultora() {                                  
		return nombreConsultora;
	}

	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}

	public List getStoMensajeErrorList() {
		return stoMensajeErrorList;
	}

	public void setStoMensajeErrorList(List stoMensajeErrorList) {
		this.stoMensajeErrorList = stoMensajeErrorList;
	}

	public Object getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(Object registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}
	
	

}
