package biz.belcorp.ssicc.web.scsicc.mav.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mav.ProcesoMAVAsignacionReemplazoGerenteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.mav.form.ConsultaMAVAbastecimientoMaterialSearchForm;

/**
 * The Class ConsultaMAVAbastecimientoMaterialSearchAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 03/02/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class ConsultaMAVAbastecimientoMaterialSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 3270323334258517513L;
	private List siccActividadList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaMAVAbastecimientoMaterialSearchForm searchForm = new ConsultaMAVAbastecimientoMaterialSearchForm();
		return searchForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		ConsultaMAVAbastecimientoMaterialSearchForm f = (ConsultaMAVAbastecimientoMaterialSearchForm) this.formBusqueda;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date hoy = new Date(System.currentTimeMillis());
		f.setFechaFacturacionD(hoy);
		f.setFechaFacturacion(sdf.format(hoy));
		
		ProcesoMAVAsignacionReemplazoGerenteService service = (ProcesoMAVAsignacionReemplazoGerenteService) 
							getBean("spusicc.procesoMAVAsignacionReemplazoGerenteService");
		
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		f.setCodigoPais(codigoPais);
		
		Map criteria = new HashMap();
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoTipoCliente", Constants.MAE_TIPO_CLIENTE_GERENTE);
		this.siccActividadList = service.getListaActividades(criteria);
		
		f.setCodigoPeriodo("");
		f.setOidActividad("");
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		ConsultaMAVAbastecimientoMaterialSearchForm f = (ConsultaMAVAbastecimientoMaterialSearchForm) this.formBusqueda;
		ProcesoMAVAsignacionReemplazoGerenteService service = (ProcesoMAVAsignacionReemplazoGerenteService) 
																getBean("spusicc.procesoMAVAsignacionReemplazoGerenteService");
		
		//Recuperamos el idioma
		IdiomaService idiomaService = (IdiomaService) getBean("idiomaService");
		String s = this.mPantallaPrincipalBean.getLocaleKey().getLanguage();
		Idioma idioma = idiomaService.getIdiomaByCodigoISO(s);
		Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String oidIdioma = reporteService.getOidString("getOidIdiomaByCodigoIdiomaIso", parameterMap);
		
		/* obteniendo valores */
		Map criterios = new HashMap();
		criterios.put("codigoPais", f.getCodigoPais());
		criterios.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criterios.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criterios.put("oidActividad", f.getOidActividad());
		criterios.put("oidIdioma", oidIdioma);
		
		if(f.getFechaFacturacionD() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			f.setFechaFacturacion(sdf.format(f.getFechaFacturacionD()));				
			if(!f.getFechaFacturacion().equalsIgnoreCase("")) {
				criterios.put("fechaFacturacion", f.getFechaFacturacion());
			}
		}
		if(!f.getCodigoPeriodo().equalsIgnoreCase("")) {
			criterios.put("codigoPeriodo", f.getCodigoPeriodo());
		}
		/* Obteniendo Lista */
		List resultado = service.getAbastecimientoMaterial(criterios);	
		return resultado;
	}

	@Override
	protected String getSalirForward() {
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}
	
	/**
	 * @return the siccActividadList
	 */
	public List getSiccActividadList() {
		return siccActividadList;
	}

	/**
	 * @param siccActividadList the siccActividadList to set
	 */
	public void setSiccActividadList(List siccActividadList) {
		this.siccActividadList = siccActividadList;
	}
	
}

