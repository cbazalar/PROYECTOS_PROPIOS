package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.runtime.directive.Foreach;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.RegionZonaBoletaRecojoInteligente;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECRegionZonaBoletaRecojoInteligenteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECRegionZonaBoletaRecojoInteligenteForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchForm;


@SuppressWarnings({"rawtypes", "unchecked"})
@ManagedBean
@SessionScoped
public class MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3320908993899335833L;
	private List siccRegionList;
	private LabelValue[] siccZonaList; 
	private List recRegionZonaBoletaRecojoInteligenteList;
	
	private List siccRegionListForm;
	private LabelValue[] siccZonaListForm; 
	
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoRECRegionZonaBoletaRecojoInteligenteList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return "mantenimientoRECRegionZonaBoletaRecojoInteligenteForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		
		MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchForm m = new MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchForm();
		return m;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		
		MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchForm f = (MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchForm) this.formBusqueda;

		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
	     
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);
		
		Map map = BeanUtils.describe(f);

		MantenimientoRECRegionZonaBoletaRecojoInteligenteService mantenimientoRECRegionZonaBoletaRecojoInteligenteService = 
				(MantenimientoRECRegionZonaBoletaRecojoInteligenteService) getBean("spusicc.mantenimientoRECRegionZonaBoletaRecojoInteligenteService");

		map.put("idioma", pais.getCodigoIdiomaIso());
		map.put("codigoPais", pais.getCodigo());
		map.put("codigoRegion", f.getRegionList());
		map.put("codigoZona", f.getZonaList());

		//si selecciono TODOS en Zonas
		if(f.getZonaList()!=null){
			if((f.getZonaList().length==1 && f.getZonaList()[0]=="")){
				map.put("codigoZona", new String[]{});
			}else{
				if((f.getZonaList().length>0)){
					map.put("codigoRegion", new String[]{});
				}
			}
		}
		
		if(f.getRegionList()!=null){
			if((f.getRegionList().length==1 && f.getRegionList()[0]=="")){
				map.put("codigoRegion", new String[]{});
			}
		}
		
		// lleno la lista para el tipo de operacion
		List list = mantenimientoRECRegionZonaBoletaRecojoInteligenteService.getRegionZonaBoletaRecojoInteligenteList(map);

		this.recRegionZonaBoletaRecojoInteligenteList = list;
		
		
		String vacio[] = new String[]{}; 
		f.setRegionList(vacio);
		f.setZonaList(vacio);
		
		return list;
	
		
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		

		MantenimientoRECRegionZonaBoletaRecojoInteligenteService service = (MantenimientoRECRegionZonaBoletaRecojoInteligenteService)getBean("spusicc.mantenimientoRECRegionZonaBoletaRecojoInteligenteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchForm f = (MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchForm) this.formBusqueda;
				
		try {
			
			Map bean = (Map) this.beanRegistroSeleccionado;
//			String idregion =  bean.get("region").toString();
//			String idzona =  bean.get("zona").toString();
			
			if(bean != null )
			{	Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoUsuario", usuario.getLogin());
				criteria.put("codigoRegion", bean.get("region"));
				criteria.put("codigoZona", bean.get("zona"));
				criteria.put("indicadorRegistro", Constants.NUMERO_CERO);
				
				service.updateRegionZonaBoletaRecojoInteligente(criteria);		
						
			String vacio[] = {""}; 
			f.setRegionList(vacio);
			f.setZonaList(vacio);
			f.setSelectedItems(null);
		}
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) 
				error = e.getLocalizedMessage();
			addError("Error: ", getResourceMessage("errors.detail")+error);	
		}	
		
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		
		MantenimientoRECRegionZonaBoletaRecojoInteligenteForm f = (MantenimientoRECRegionZonaBoletaRecojoInteligenteForm) this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		RegionZonaBoletaRecojoInteligente regionZonaBoletaRecojoInteligente = new RegionZonaBoletaRecojoInteligente();

		MantenimientoRECRegionZonaBoletaRecojoInteligenteService mantenimientoRECRegionZonaBoletaRecojoInteligenteService = (MantenimientoRECRegionZonaBoletaRecojoInteligenteService) getBean("spusicc.mantenimientoRECRegionZonaBoletaRecojoInteligenteService");
		
		BeanUtils.copyProperties(regionZonaBoletaRecojoInteligente, f); 
		regionZonaBoletaRecojoInteligente.setCodigoPais(pais.getCodigo());
		regionZonaBoletaRecojoInteligente.setIndicadorRegistro(Constants.ESTADO_ACTIVO);
		
		if (f.isNewRecord()) {

			try {
				//evaluo si existen registros con codigo region o zona ingresada
				if(mantenimientoRECRegionZonaBoletaRecojoInteligenteService.getExisteRegionZonaBoletaRecojoInteligenteByCriteria(regionZonaBoletaRecojoInteligente))
				{
					
					boolean isAsociado = false;
					if(StringUtils.isEmpty(regionZonaBoletaRecojoInteligente.getCodigoZona())){
						isAsociado=mantenimientoRECRegionZonaBoletaRecojoInteligenteService.existeRegionAsociada(regionZonaBoletaRecojoInteligente);
					}
					if(!isAsociado)
					{
						// insertar
						mantenimientoRECRegionZonaBoletaRecojoInteligenteService.insertRegionZonaBoletaRecojoInteligente(regionZonaBoletaRecojoInteligente, usuario);
						
						
					}else{
						addError("Error: ", getResourceMessage("mantenimientoRECRegionZonaBoletaRecojoInteligenteForm.existeRegionZona"));
	    	            return false;
					}
				}else{
						addError("Error: ", getResourceMessage("mantenimientoRECRegionZonaBoletaRecojoInteligenteForm.existeRegistro"));
			            return false;
            	}
			} catch (Exception e) {
				
						addError("Error:", e.getStackTrace().toString());
						return false;
			}

		} 

		return true;
		
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		

		MantenimientoRECRegionZonaBoletaRecojoInteligenteForm f = new MantenimientoRECRegionZonaBoletaRecojoInteligenteForm();
		MantenimientoRECRegionZonaBoletaRecojoInteligenteForm fn = new MantenimientoRECRegionZonaBoletaRecojoInteligenteForm();

		BeanUtils.copyProperties(f, fn);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
	     
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		List regiones = reporteService.getListaGenerico("getRegionesByPais", criteria);
		
		Map map = BeanUtils.describe(f);

		MantenimientoRECRegionZonaBoletaRecojoInteligenteService mantenimientoRECRegionZonaBoletaRecojoInteligenteService = 
				(MantenimientoRECRegionZonaBoletaRecojoInteligenteService) getBean("spusicc.mantenimientoRECRegionZonaBoletaRecojoInteligenteService");

		map.put("idioma", pais.getCodigoIdiomaIso());
		map.put("codigoPais", pais.getCodigo());
		map.put("codigoRegion", new String[]{});
		map.put("codigoZona", new String[]{});
		// lleno la lista para el tipo de operacion
		List regionesIngresadas = mantenimientoRECRegionZonaBoletaRecojoInteligenteService.getRegionZonaBoletaRecojoInteligenteList(map);
		
		for(int i=0;i<regiones.size();i++){
			for(int j=0;j<regionesIngresadas.size();j++){
				String region = ((Base)regiones.get(i)).getCodigo();
				String regionIngresada = MapUtils.getString((Map)regionesIngresadas.get(j), "region");
				if(StringUtils.equalsIgnoreCase(region, regionIngresada)){
					regiones.remove(i);
				}
			}
		}
		
		this.siccRegionListForm = regiones;
			
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		
		try {
			MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchForm f = (MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchForm) this.formBusqueda;
			MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchForm fn = new MantenimientoRECRegionZonaBoletaRecojoInteligenteSearchForm();
			BeanUtils.copyProperties(f, fn);
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			f.setCodigoPais(pais.getCodigo());
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
		     
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			
			this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);
			

		} catch (Exception e) {
			
			String error = e.getMessage();

			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			
			addError("Error: ", getReportResourceMessage("errors.detail") + error);
			
		}
		
//		this.recRegionZonaBoletaRecojoInteligenteList = null;
	
	}
	
	public void loadZonas(ValueChangeEvent val){
			
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String[] regiones = (String[])val.getNewValue();
		
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
			if(regiones.length > 0){				
				this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD",  regiones,"");
			}						
			
	}
	
	public void loadZonasForm(ValueChangeEvent val){
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String valor = (String)val.getNewValue();
		String [] regiones={valor};
		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
			if(regiones.length > 0){				
				this.siccZonaListForm = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD",  regiones,"");
									
			}						
			
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getRecRegionZonaBoletaRecojoInteligenteList() {
		return recRegionZonaBoletaRecojoInteligenteList;
	}

	public void setRecRegionZonaBoletaRecojoInteligenteList(
			List recRegionZonaBoletaRecojoInteligenteList) {
		this.recRegionZonaBoletaRecojoInteligenteList = recRegionZonaBoletaRecojoInteligenteList;
	}

	public LabelValue[] getSiccZonaListForm() {
		return siccZonaListForm;
	}

	public void setSiccZonaListForm(LabelValue[] siccZonaListForm) {
		this.siccZonaListForm = siccZonaListForm;
	}

	public List getSiccRegionListForm() {
		return siccRegionListForm;
	}

	public void setSiccRegionListForm(List siccRegionListForm) {
		this.siccRegionListForm = siccRegionListForm;
	}
	
}
