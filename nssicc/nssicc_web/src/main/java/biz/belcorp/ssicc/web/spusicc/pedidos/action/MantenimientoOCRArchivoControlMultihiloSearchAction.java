package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.ocr.MantenimientoOCRArchivoControlMultihiloService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRArchivoControlMultihiloForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRArchivoControlMultihiloSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoOCRArchivoControlMultihiloSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -1577788682894548753L;
	
	@ManagedProperty(value="#{mantenimientoOCRArchivoControlMultihiloIdProcBatcSearchAction}")	
	private MantenimientoOCRArchivoControlMultihiloIdProcBatcSearchAction mantOCRArchivoControlMultihiloIdProcBatc;
	
	private String numUno=Constants.NUMERO_UNO;
	private String numCero=Constants.NUMERO_CERO;

	@Override
	protected String getSalirForward() {		
		return "mantenimientoOCRArchivoControlMultihiloList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoOCRArchivoControlMultihiloForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoOCRArchivoControlMultihiloSearchForm searchForm = new MantenimientoOCRArchivoControlMultihiloSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {		
		MantenimientoOCRArchivoControlMultihiloSearchForm f= (MantenimientoOCRArchivoControlMultihiloSearchForm) this.formBusqueda;
		MantenimientoOCRArchivoControlMultihiloService service = (MantenimientoOCRArchivoControlMultihiloService) getBean("spusicc.mantenimientoOCRArchivoControlMultihiloService");
		List lista = new ArrayList();
		if(StringUtils.isBlank(f.getNumeroFilas()))		
			lista = service.getArchivoControlMultihiloGeneral();		
		else{
			Map criteria = new HashMap();
			criteria.put("numeroFilas",f.getNumeroFilas());			
			 lista = service.getArchivoControlMultihilo(criteria);
		}		
		//session.setAttribute(Constants.ORC_ARCHIVO_MULTIHILO, lista);
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		MantenimientoOCRArchivoControlMultihiloForm f = (MantenimientoOCRArchivoControlMultihiloForm) this.formMantenimiento;
		MantenimientoOCRArchivoControlMultihiloService service = (MantenimientoOCRArchivoControlMultihiloService) getBean("spusicc.mantenimientoOCRArchivoControlMultihiloService");
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		boolean bGrabarCab = false;		
				
		if(!f.isNewRecord()){
			if (log.isDebugEnabled()) {
				log.debug("EN EL CASO QUE SEA MODIFICACION");
			}
			Map criteria = new HashMap();
			criteria.put("id", f.getOid());
			criteria.put("indicadorCarga", f.getIndicadorCarga());
			service.updateArchivoControl(criteria,usuario);			
			bGrabarCab = true;
		}		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map elemento=(Map)this.beanRegistroSeleccionado;
		MantenimientoOCRArchivoControlMultihiloForm f = new MantenimientoOCRArchivoControlMultihiloForm (); 
		MantenimientoOCRArchivoControlMultihiloService service = (MantenimientoOCRArchivoControlMultihiloService) getBean("spusicc.mantenimientoOCRArchivoControlMultihiloService");
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			String id = elemento.get("oid").toString();
			Map idArchivo = new HashMap();
			idArchivo.put("id", id);			
		
			if (id != null ) {				
				
				Map mapArchivoControlMultihilo = service.getArchivoControl(idArchivo); 				
				f.setOid(mapArchivoControlMultihilo.get("oid").toString());
				f.setCodigoPais(mapArchivoControlMultihilo.get("codigoPais").toString());
				f.setNumeroLote(mapArchivoControlMultihilo.get("numeroLote").toString());
				f.setTipoDocumento(mapArchivoControlMultihilo.get("tipoDocumento").toString());
				f.setNumeroRegistros(mapArchivoControlMultihilo.get("numeroRegistros").toString());
				f.setIndicadorCarga(mapArchivoControlMultihilo.get("indicadorCarga").toString());
				f.setIdProcBatch(mapArchivoControlMultihilo.get("idProcBatch").toString());
				f.setUsuDigi(mapArchivoControlMultihilo.get("usuDigi").toString());
			}
			f.setNewRecord(false);
		}
		
		return f;	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoOCRArchivoControlMultihiloSearchForm f= (MantenimientoOCRArchivoControlMultihiloSearchForm) this.formBusqueda;
		f.setNumeroFilas("");
		this.mostrarBotonNuevo=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonConsultar=false;
		
		//session.setAttribute(Constants.ORC_ARCHIVO_MULTIHILO, new ArrayList());
		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {		
		return "mantenimientoOCRArchivoControlMultihiloForm.updated";
		
	}
	
	public void abrirPopup(ActionEvent event){
		try {
			
			ExternalContext externalContext = FacesContext.getCurrentInstance()	.getExternalContext();
			String valor = externalContext.getRequestParameterMap().get("idProcBatch").toString();
			 mantOCRArchivoControlMultihiloIdProcBatc.setIdValor(valor);
			 mantOCRArchivoControlMultihiloIdProcBatc.inicializarValores();
			 String ventana = "PF('dialogMantenimientoArchivoControl').show()";
			 this.getRequestContext().execute(ventana);
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public MantenimientoOCRArchivoControlMultihiloIdProcBatcSearchAction getMantOCRArchivoControlMultihiloIdProcBatc() {
		return mantOCRArchivoControlMultihiloIdProcBatc;
	}

	public void setMantOCRArchivoControlMultihiloIdProcBatc(
			MantenimientoOCRArchivoControlMultihiloIdProcBatcSearchAction mantOCRArchivoControlMultihiloIdProcBatc) {
		this.mantOCRArchivoControlMultihiloIdProcBatc = mantOCRArchivoControlMultihiloIdProcBatc;
	}

	public String getNumUno() {
		return numUno;
	}

	public void setNumUno(String numUno) {
		this.numUno = numUno;
	}

	public String getNumCero() {
		return numCero;
	}

	public void setNumCero(String numCero) {
		this.numCero = numCero;
	}

	
}
