package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.CodigoVentaOpera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.CronogramaBR;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECCronogramaBRService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECCronogramaBRForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECCronogramaBRSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoRECCronogramaBRSearchAction extends BaseMantenimientoSearchAbstractAction{
	
	private static final long serialVersionUID = 1727674329604280993L;
	
	private List recCronogramasBrList;
	private List siccRegionList;
	private boolean consultar=false;

	@Override
	protected String getSalirForward() {		
		return "mantenimientoRECCronogramaBRList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoRECCronogramaBRForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECCronogramaBRSearchForm searchForm = new MantenimientoRECCronogramaBRSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		log.info("Entro a MantenimientoRECCronogramaBRSearchAction - setFindAttributes");		
		MantenimientoRECCronogramaBRService mantenimientoRECCronogramaBRService = (MantenimientoRECCronogramaBRService) getBean("spusicc.mantenimientoRECCronogramaBRService");
		MantenimientoRECCronogramaBRSearchForm f = (MantenimientoRECCronogramaBRSearchForm)this.formBusqueda;		
		//-- Crear pojo		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());		
		//-- Logica
		List lista = mantenimientoRECCronogramaBRService.getCronogramaBRList(criteria);
		this.recCronogramasBrList=lista;		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		log.info("Entro a MantenimientoRECCronogramaBRSearchAction - setDeleteAttributes");		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		MantenimientoRECCronogramaBRService mantenimientoRECCronogramaBRService = (MantenimientoRECCronogramaBRService) getBean("spusicc.mantenimientoRECCronogramaBRService");
		Map criteria = null;

		Map sistemaBusqueda= (Map)this.beanRegistroSeleccionado;
		String codigoPais = pais.getCodigo();
		String codigoPeriodo =sistemaBusqueda.get("codigoPeriodo").toString();
		String codigoRegion =sistemaBusqueda.get("codigoRegion").toString();
		String fechaInicio = sistemaBusqueda.get("fechaInicio").toString();
		String fechaFin = sistemaBusqueda.get("fechaFin").toString();		
		
		criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);		
		int resultado = mantenimientoRECCronogramaBRService.getCronogramaBRPeriodoActivo(criteria);		
		try {			
			if(resultado > 0){				
				criteria = new HashMap();
				criteria.put("codigoPais", codigoPais);
				criteria.put("codigoRegion", codigoRegion);
				criteria.put("codigoPeriodo", codigoPeriodo);				
				mantenimientoRECCronogramaBRService.deleteCronogramaBR(criteria);		
			}else{				
				this.addError("ERROR: ", this.getResourceMessage("mantenimientoRECCronogramaBRForm.noDelete"));
				throw new Exception(this.getResourceMessage("mantenimientoRECCronogramaBRForm.noDelete"));	
			}			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
		//-- Logica de actualizar listado ---------------------------		
		criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", codigoPeriodo);		
		//-- Logica
		List lista = mantenimientoRECCronogramaBRService.getCronogramaBRList(criteria);
		this.recCronogramasBrList=lista;		
		return true;		
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.info("Entro a MantenimientoRECCronogramaBRAction - setSaveAttributes");
		
		Usuario usuario =this.mPantallaPrincipalBean.getCurrentUser();		
		MantenimientoRECCronogramaBRService mantenimientoRECCronogramaBRService = (MantenimientoRECCronogramaBRService) getBean("spusicc.mantenimientoRECCronogramaBRService");
		MantenimientoRECCronogramaBRForm f= (MantenimientoRECCronogramaBRForm)this.formMantenimiento;
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioDate()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinDate()));
		CronogramaBR cronograma = new CronogramaBR();
		BeanUtils.copyProperties(cronograma, f);
		
		if(f.isNewRecord()){			
			try {
				//-- Inserción
				mantenimientoRECCronogramaBRService.insertCronogramaBR(cronograma, usuario);
			} catch (Exception e) {
				//this.addError("ERROR: ", this.getResourceMessage("mantenimientoRECCronogramaBRForm.noAdd"));
				throw new Exception(this.getResourceMessage("mantenimientoRECCronogramaBRForm.noAdd"));	
			}			
		}else{
			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());			
			//-- Logica de validación periodo activo ----------------
			int resultado = mantenimientoRECCronogramaBRService.getCronogramaBRPeriodoActivo(criteria);
			if(resultado > 0){
				//-- Actualización
				mantenimientoRECCronogramaBRService.updateCronogramaBR(cronograma, usuario);				
			}else{
				//this.addError("ERROR: ", this.getResourceMessage("mantenimientoRECCronogramaBRForm.noUpdated"));
				throw new Exception(this.getResourceMessage("mantenimientoRECCronogramaBRForm.noUpdated"));	
			}
		}	
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECCronogramaBRForm f = new MantenimientoRECCronogramaBRForm();		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");		
		Map criteria = new HashMap();		
		//-- Pojo
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());		
		List listaRegion = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		this.siccRegionList=listaRegion;
		this.consultar=false;
		//-- Mapeo - incialización
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoPeriodo("");
		f.setCodigoRegion("");
		f.setFechaInicio("");
		f.setFechaFin("");	
	
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			Map sistemaBusqueda= (Map)this.beanRegistroSeleccionado;
			String codigoPais = pais.getCodigo();
			String codigoPeriodo =sistemaBusqueda.get("codigoPeriodo").toString();
			String codigoRegion =sistemaBusqueda.get("codigoRegion").toString();
			String fechaInicio = sistemaBusqueda.get("fechaInicio").toString();
			String fechaFin = sistemaBusqueda.get("fechaFin").toString();			
			
			f.setCodigoPais(codigoPais);
			f.setCodigoPeriodo(codigoPeriodo);
			f.setCodigoRegion(codigoRegion);
			f.setFechaInicioDate(DateUtil.convertStringToDate(fechaInicio));
			f.setFechaFinDate(DateUtil.convertStringToDate(fechaFin));			
			f.setNewRecord(false);
			if(this.accion.equals(this.ACCION_CONSULTAR)){
				this.consultar=true;
			}
		}
			
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {					
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoRECCronogramaBRSearchForm f = (MantenimientoRECCronogramaBRSearchForm) this.formBusqueda;
		String codigoPeriodo = new String();			
			//-- Logica - recupera periodo actual -------------------			
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());			
		List lista = interfazSiCCService.getPeriodoFechaProcesoActual(criteria);
		HashMap mapeo = (HashMap)lista.get(0);
		codigoPeriodo = mapeo.get("cod_peri").toString();		
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoPeriodo(codigoPeriodo);		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoRECCronogramaBRForm f= (MantenimientoRECCronogramaBRForm)this.formMantenimiento;
		if(f.isNewRecord())
			return "mantenimientoRECCronogramaBRForm.add";
		else
			return "mantenimientoRECCronogramaBRForm.updated";	
	}
	
	@Override
	public String setValidarMantenimiento(){
		MantenimientoRECCronogramaBRForm f= (MantenimientoRECCronogramaBRForm)this.formMantenimiento;
	    if (f.getFechaFinDate().compareTo(f.getFechaInicioDate()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;		
	}

	public List getRecCronogramasBrList() {
		return recCronogramasBrList;
	}

	public void setRecCronogramasBrList(List recCronogramasBrList) {
		this.recCronogramasBrList = recCronogramasBrList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public boolean isConsultar() {
		return consultar;
	}

	public void setConsultar(boolean consultar) {
		this.consultar = consultar;
	}
	

}
