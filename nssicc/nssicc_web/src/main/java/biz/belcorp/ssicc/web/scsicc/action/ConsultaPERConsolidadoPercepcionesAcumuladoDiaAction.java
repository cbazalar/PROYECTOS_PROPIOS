package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.AccesoCanal;
import biz.belcorp.ssicc.dao.sisicc.model.Subacceso;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPERPercepcionesOtrosCanalesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm;




@ManagedBean
@SessionScoped
public class ConsultaPERConsolidadoPercepcionesAcumuladoDiaAction extends BaseConsultaAbstractAction{
			
	
	private static final long serialVersionUID = -353131735597148225L;
	
	private List siccCanalList;
	private List siccAccesoList;
	private List siccSubaccesoList;
	private List perConsoAcumdiaList;
	
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm form = new ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
	
		ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm f = (ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm) this.formBusqueda;	
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codpais=pais.getCodigo();
		f.setCodigoPais(codpais);	
			
		this.siccCanalList=siccService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());				

		log.debug("Hasta aca todo ok...");
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	
		MantenimientoPERPercepcionesOtrosCanalesService service = (MantenimientoPERPercepcionesOtrosCanalesService) getBean("spusicc.mantenimientoPERPercepcionesOtrosCanalesService");
		ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm f = (ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm) this.formBusqueda;
		
		String ndesde=DateUtil.convertDateToString(f.getFechaDesdeD());
		String nhasta=DateUtil.convertDateToString(f.getFechaHastaD());
		f.setFechaDesde(ndesde);
		f.setFechaHasta(nhasta);
		Map criteria = new HashMap();
		criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPaisLbel", f.getCodigoPais().substring(0,2)+ Constants.FIN_CODIGO_PAIS_LBEL);
		criteria.put("codigoCanal", isNotEmptyArray(f.getCodigoCanal())?(String[])f.getCodigoCanal():null);
		criteria.put("codigoAcceso", (String[])f.getCodigoAcceso());
		criteria.put("codigoSubAcceso", (String[])f.getCodigoSubAcceso());
		criteria.put("fechaDesde", f.getFechaDesde());
		criteria.put("fechaHasta", f.getFechaHasta());
				
		List resultado = service.getConsolidadoPercepcionesAcumuladoDia(criteria);		
		this.perConsoAcumdiaList=resultado;
		return resultado;
	}
	
	public void cargarAcceso(ValueChangeEvent val) {
		log.debug(">>Cargar Acceso");
		log.debug("val: " + val.getNewValue().toString());		
		
		ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm f = (ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm) this.formBusqueda;
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List auxAcceso=new ArrayList();
		List aux=new ArrayList();
		String canal="";
		String[] valores = (String[]) val.getNewValue();
		if (!val.equals(null) && valores.length > 0) {			
			auxAcceso=siccService.getAccesosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());
			for(int i=0;i<valores.length;i++){
				for(int j=0;j<auxAcceso.size();j++){
					canal=((AccesoCanal )auxAcceso.get(j)).getCodigoCanal();
					if(canal.equals(valores[i])){						
						aux.add(auxAcceso.get(j));
					}
				}
			}
			this.siccAccesoList=aux;
			
		} else {
			this.siccAccesoList = null;			
		}
	}
	
	public void cargarSubAcceso(ValueChangeEvent val) {
		log.debug(">>Cargar SubAcceso");
		log.debug("val: " + val.getNewValue().toString());		
		
		ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm f = (ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm) this.formBusqueda;
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List auxSubAcceso=new ArrayList();
		List aux=new ArrayList();
		String acceso="";
		String[] valores = (String[]) val.getNewValue();
		if (!val.equals(null) && valores.length > 0) {						
			auxSubAcceso=siccService.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
			for(int i=0;i<valores.length;i++){
				for(int j=0;j<auxSubAcceso.size();j++){					
					acceso=((Subacceso)auxSubAcceso.get(j)).getCodigoAcceso();					
					if(acceso.equals(valores[i])){						
						aux.add(auxSubAcceso.get(j));
					}
				}
			}
			this.siccSubaccesoList=aux;
			
		} else {
			this.siccSubaccesoList = null;			
		}
	}
	
	public String setValidarConsulta() {		
		ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm f = (ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm) this.formBusqueda;
	    if (f.getFechaHastaD().compareTo(f.getFechaDesdeD()) < 0 ){
	    	String mensaje =  this.getResourceMessage("errors.compare.dates");
			return mensaje;
	    }	    					
	    return null;
	}	
	

	/**
	 * Retorna true si hay data distinta de nulo
	 * @param array
	 * @return
	 */
	private boolean isNotEmptyArray(String[] array){
		if(array!=null && array.length>0){
			if(array.length==1){
				if(StringUtils.isEmpty(array[0])){
				   return false;	
				}
			}
		}
		return true;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}	

	public List getPerConsoAcumdiaList() {
		return perConsoAcumdiaList;
	}

	public void setPerConsoAcumdiaList(List perConsoAcumdiaList) {
		this.perConsoAcumdiaList = perConsoAcumdiaList;
	}

	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	public List getSiccSubaccesoList() {
		return siccSubaccesoList;
	}

	public void setSiccSubaccesoList(List siccSubaccesoList) {
		this.siccSubaccesoList = siccSubaccesoList;
	}
	
	
	
}
