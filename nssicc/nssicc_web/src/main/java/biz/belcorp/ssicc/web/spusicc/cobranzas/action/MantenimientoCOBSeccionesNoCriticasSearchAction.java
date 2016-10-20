package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.SeccionNoCritica;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.MantenimientoCOBGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBSeccionesNoCriticasForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBSeccionesNoCriticasSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.MantenimientoCOBZonaNoCriticaForm;

@ManagedBean
@SessionScoped
public class MantenimientoCOBSeccionesNoCriticasSearchAction extends BaseMantenimientoSearchAbstractAction{

	
	private static final long serialVersionUID = -3265394709386333629L;
	
	private List siccEtapaDeudaList;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};
	private boolean indicaActivo;
	private boolean indicaConsultar;

	@Override
	protected String getSalirForward() {		
		return "mantenimientoCOBSeccionesNoCriticasList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoCOBSeccionesNoCriticasForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOBSeccionesNoCriticasSearchForm searchForm = new MantenimientoCOBSeccionesNoCriticasSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) this.getBean("spusicc.mantenimientoCOBGenericoService");
		MantenimientoCOBSeccionesNoCriticasSearchForm f = (MantenimientoCOBSeccionesNoCriticasSearchForm) this.formBusqueda;
		SeccionNoCritica bean = new SeccionNoCritica();
		BeanUtils.copyProperties(bean, f);
		List lista = service.getSeccionNoCriticaList(bean);
		return lista;	
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) this.getBean("spusicc.mantenimientoCOBGenericoService");
		Map registrobusqueda=(Map)this.beanRegistroSeleccionado;	
		String descri=registrobusqueda.get("descriEtapaDeuda").toString();
		for(int i=0;i<this.siccEtapaDeudaList.size();i++){
			Base registro=(Base)this.siccEtapaDeudaList.get(i);
			if(StringUtils.equals(registro.getDescripcion(), descri)){
				registrobusqueda.put("codEtapaDeuda", registro.getCodigo());
				break;
			}
		}
		SeccionNoCritica bean= new SeccionNoCritica();
		BeanUtils.copyProperties(bean, registrobusqueda);
		service.deleteSeccionNoCritica(bean, usuario);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");
		MantenimientoCOBSeccionesNoCriticasForm f = (MantenimientoCOBSeccionesNoCriticasForm) this.formMantenimiento;
		if(this.indicaActivo)
			f.setIndicaActivo(1);
		else
			f.setIndicaActivo(0);
		
		SeccionNoCritica bean= new SeccionNoCritica();
		BeanUtils.copyProperties(bean, f);
		if(f.isNewRecord())
			service.insertSeccionNoCritica(bean, usuario);
		else
			service.updateSeccionNoCritica(bean, usuario);
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoCOBGenericoService service = (MantenimientoCOBGenericoService) getBean("spusicc.mantenimientoCOBGenericoService");
		AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");
		MantenimientoCOBSeccionesNoCriticasForm f = new MantenimientoCOBSeccionesNoCriticasForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setNewRecord(true);
		cargarCombos();
		Base base=(Base)this.siccEtapaDeudaList.get(0);
		f.setCodEtapaDeuda(base.getCodigo());
		
		this.indicaActivo=false;
		this.indicaConsultar=false;
		
		if (!this.accion.equals(this.ACCION_NUEVO)){
			Map registrobusqueda=(Map)this.beanRegistroSeleccionado;
			String descri=registrobusqueda.get("descriEtapaDeuda").toString();
			for(int i=0;i<this.siccEtapaDeudaList.size();i++){
				Base registro=(Base)this.siccEtapaDeudaList.get(i);
				if(StringUtils.equals(registro.getDescripcion(), descri)){
					registrobusqueda.put("codEtapaDeuda", registro.getCodigo());
					break;
				}
			}
			SeccionNoCritica parametro=new SeccionNoCritica();			
			BeanUtils.copyProperties(parametro, registrobusqueda);
			SeccionNoCritica bean=service.getSeccionNoCritica(parametro);
			BeanUtils.copyProperties(f, bean);
			if(f.getIndicaActivo()==1)
				this.indicaActivo=true;
			else
				this.indicaActivo=false;
			f.setNewRecord(false);
			f.setEditable(false);
			this.siccZonaList = ajaxService.getZonasByPaisRegion(pais.getCodigo(), f.getCodigoRegion());			
			this.siccSeccionList = ajaxService.getSeccionesByPaisMarcaCanalRegionZona(pais.getCodigo(), 
					Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoRegion(), f.getCodigoZona());	
		
		}
		if(StringUtils.equals(this.accion, this.ACCION_CONSULTAR)){
			this.indicaConsultar=true;
			f.setNewRecord(false);
		}
		
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {		
		MantenimientoCOBSeccionesNoCriticasSearchForm f = (MantenimientoCOBSeccionesNoCriticasSearchForm) this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda();	
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);

	}
	
	public void loadZonas(ValueChangeEvent value){
		try {			
			MantenimientoCOBSeccionesNoCriticasSearchForm f = (MantenimientoCOBSeccionesNoCriticasSearchForm) this.formBusqueda;
			if(value.getNewValue()!=null){
				String valor = (String) value.getNewValue();
				AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");
				this.siccZonaList = ajaxService.getZonasByPaisRegion(f.getCodigoPais(), valor);
				this.siccSeccionList=null;
			}else{
				this.siccZonaList=null;
				this.siccSeccionList=null;
			}				
			
		} catch (Exception e) {
			this.addError("ERROR: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	public void loadSecciones(ValueChangeEvent value){
		try {			
			MantenimientoCOBSeccionesNoCriticasSearchForm f = (MantenimientoCOBSeccionesNoCriticasSearchForm) this.formBusqueda;
			if(value.getNewValue()!=null){
			String valor = (String) value.getNewValue();
			AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");
			this.siccSeccionList = ajaxService.getSeccionesByPaisMarcaCanalRegionZona(f.getCodigoPais(), 
					Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoRegion(), valor);			
			}else
				this.siccSeccionList=null;			
		} catch (Exception e) {
			this.addError("ERROR: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	public void loadZonasForm(ValueChangeEvent value){
		try {			
			MantenimientoCOBSeccionesNoCriticasForm f = (MantenimientoCOBSeccionesNoCriticasForm) this.formMantenimiento;
			if(value.getNewValue()!=null){
				String valor = (String) value.getNewValue();
				AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");
				this.siccZonaList = ajaxService.getZonasByPaisRegion(f.getCodigoPais(), valor);
				this.siccSeccionList=null;
			}else{
				this.siccZonaList=null;
				this.siccSeccionList=null;
			}				
			
		} catch (Exception e) {
			this.addError("ERROR: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	public void loadSeccionesForm(ValueChangeEvent value){
		try {			
			MantenimientoCOBSeccionesNoCriticasForm f = (MantenimientoCOBSeccionesNoCriticasForm) this.formMantenimiento;
			if(value.getNewValue()!=null){
			String valor = (String) value.getNewValue();
			AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");
			this.siccSeccionList = ajaxService.getSeccionesByPaisMarcaCanalRegionZona(f.getCodigoPais(), 
					Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, f.getCodigoRegion(), valor);			
			}else
				this.siccSeccionList=null;			
		} catch (Exception e) {
			this.addError("ERROR: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	public void cargarCombos(){		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajaxService = (AjaxService)this.getBeanService("ajaxService");
		ConsultaCOBGenericoService consultaCOBService = (ConsultaCOBGenericoService) this.getBean("spusicc.consultaCOBGenericoService");
		this.siccEtapaDeudaList = consultaCOBService.getEtapasDeuda();	
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);		
		Base base2=(Base)this.siccRegionList.get(0);
		
//		this.siccZonaList = ajaxService.getZonasByPaisRegion(pais.getCodigo(), base2.getCodigo());
//		String valor=this.siccZonaList[0].getValue();
//		this.siccSeccionList = ajaxService.getSeccionesByPaisMarcaCanalRegionZona(pais.getCodigo(), 
//				Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, base2.getCodigo(), valor);		
	}

	/**
	 * @return the siccEtapaDeudaList
	 */
	public List getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	/**
	 * @param siccEtapaDeudaList the siccEtapaDeudaList to set
	 */
	public void setSiccEtapaDeudaList(List siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return the indicaActivo
	 */
	public boolean isIndicaActivo() {
		return indicaActivo;
	}

	/**
	 * @param indicaActivo the indicaActivo to set
	 */
	public void setIndicaActivo(boolean indicaActivo) {
		this.indicaActivo = indicaActivo;
	}

	/**
	 * @return the indicaConsultar
	 */
	public boolean isIndicaConsultar() {
		return indicaConsultar;
	}

	/**
	 * @param indicaConsultar the indicaConsultar to set
	 */
	public void setIndicaConsultar(boolean indicaConsultar) {
		this.indicaConsultar = indicaConsultar;
	}
	
	

}
