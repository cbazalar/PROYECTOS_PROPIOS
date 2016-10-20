package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPEjecutivasForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPActualizacionDatosClienteAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan Altamirano </a>
 * 
 */

@ManagedBean  
@SessionScoped
public class ConsultaHIPEjecutivasAction extends BasePopupAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private List hipEtapasEjecutivasList = new ArrayList(); //HIP_ETAPAS_EJECUTIVAS_LIST
	private List hipConsultaEjecutivasList = new ArrayList(); //HIP_CONSULTA_EJECUTIVAS_LIST	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPEjecutivasForm form = new ConsultaHIPEjecutivasForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		
		if(log.isDebugEnabled()){
			log.debug("Entro a ConsultaHIPEjecutivasAction - setViewAttributes");
		}
		
		return hipConsultaEjecutivasList;
	}
	
	/**
	 * Metodo que se ejecuta luego que se ejecuta el Constructor de la clase
	 */
	@PostConstruct
	public void view() {
		if (log.isDebugEnabled()) {
            log.debug("Entering 'ConsultaHIPCuentaCorrientesAction - view' method");
        }
		Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();;
		this.parametrosPantalla = new HashMap<String, String>();
		this.parametrosPantalla.putAll(parametros);
		try {
			this.formBusqueda = this.devuelveFormBusqueda();
		}
		catch (Exception e) {
			
		}
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entro a ConsultaHIPEjecutivasAction - setViewAttributes");
		}
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		MantenimientoPEJProgramaEjecutivasService servicePEJ = (MantenimientoPEJProgramaEjecutivasService)this.getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		ConsultaHIPEjecutivasForm f = (ConsultaHIPEjecutivasForm) this.formBusqueda;
		
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		
		String etapa = f.getEtapa();
		String codigoFaseGrilla = "";
		
		if(!StringUtils.equals(etapa, "T")){
			codigoFaseGrilla = etapa;
		}else{
			codigoFaseGrilla = "";
		}
		
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionZona() + " / " + dtoDatosCliente.getDescripcionSeccion());
		
		Map programaPEJ = null;
		Map mapProgramaActivo = new HashMap();
		mapProgramaActivo.put("codigoPais", dtoDatosCliente.getCodigoPais());
		mapProgramaActivo.put("estado", "1");
		List listProgramaActivo = servicePEJ.getProgramasByCriteria(mapProgramaActivo);
		
		if(listProgramaActivo.size() == 1){
			for (int i = 0; i < listProgramaActivo.size(); i++) {
                programaPEJ = (Map) listProgramaActivo.get(i);
            }
			
			Map resultPeriodo = servicePEJ.getPeriodoDefault();
			String campanaProceso = (String) resultPeriodo.get("codigoPeriodo");
			
			Map mapFaseActiva = new HashMap();
			mapFaseActiva.put("campanaProceso", campanaProceso);
			mapFaseActiva.put("codigoPais", dtoDatosCliente.getCodigoPais());
			mapFaseActiva.put("codigoPrograma", MapUtils.getString(programaPEJ, "codigoPrograma"));
			String faseActiva = service.getFaseActivaPrograma(mapFaseActiva);
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
			criteria.put("codigoPrograma", MapUtils.getString(programaPEJ, "codigoPrograma"));
			criteria.put("codigoFase", faseActiva);
			criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
			criteria.put("codigoFaseGrilla", codigoFaseGrilla);

			Map mapDatosEjecutiva = service.getDatosEjecutiva(criteria);
			List listEtapasEjecutivas = service.getEtapasEjecutivas(criteria);
			
			if(mapDatosEjecutiva != null) {
				f.setNivel((String)mapDatosEjecutiva.get("nivelEjecutiva"));
				f.setNumeroContrato((String)mapDatosEjecutiva.get("numeroContrato"));
				f.setPeriodoNombramiento((String)mapDatosEjecutiva.get("campanaNombramiento"));
				f.setFechaIngreso((String)mapDatosEjecutiva.get("fechaIngreso"));
				f.setCoberturaTerritorioDe((String)mapDatosEjecutiva.get("territorioInicial").toString());
				f.setCoberturaTerritorioAl((String)mapDatosEjecutiva.get("territorioFinal").toString());
				f.setCiudad((String)mapDatosEjecutiva.get("ciudadEjecutiva"));
				
				setHipEtapasEjecutivasList(listEtapasEjecutivas);
			} else {
				f.setNivel("");
				f.setNumeroContrato("");
				f.setPeriodoNombramiento("");
				f.setFechaIngreso("");
				f.setCoberturaTerritorioDe("");
				f.setCoberturaTerritorioAl("");
				f.setCiudad("");
				f.setSumMetaPedido("");
				f.setSumRealPedido("");
				f.setSumMetaIngreso("");
				f.setSumRealIngreso("");
				f.setSumMetaReca("");
				f.setSumRealReca("");
				f.setSumComisionBruta("");
				f.setSumComisionNeta("");
				
				setHipEtapasEjecutivasList(new ArrayList());
			}

			List listConsultaEjecutivas = service.getDetalleEvaluacionEjecutiva(criteria);
			this.setHipConsultaEjecutivasList(listConsultaEjecutivas);
			
			if(listConsultaEjecutivas != null && listConsultaEjecutivas.size() > 0){
				int metaPedido = 0, realPedido = 0;
                int totalMetaPedido = 0, totalRealPedido = 0;
                int metaIngreso = 0, realIngreso = 0;
                int totalMetaIngreso = 0, totalRealIngreso = 0;
                BigDecimal metaReca = new BigDecimal(0), realReca = new BigDecimal(0);
                BigDecimal totalMetaReca = new BigDecimal(0), totalRealReca = new BigDecimal(0);
                BigDecimal comisionBruta = new BigDecimal(0), comisionNeta = new BigDecimal(0);
                BigDecimal totalComisionBruta = new BigDecimal(0), totalComisionNeta = new BigDecimal(0);
				
				for (int i = 0; i < listConsultaEjecutivas.size(); i++) {
					Map mapConsultaEjecutivas = (Map) listConsultaEjecutivas.get(i);
	                
	                metaPedido = MapUtils.getIntValue(mapConsultaEjecutivas, "metaPedido");
	                realPedido = MapUtils.getIntValue(mapConsultaEjecutivas, "realPedido");
	                totalMetaPedido = totalMetaPedido + metaPedido;
	                totalRealPedido = totalRealPedido + realPedido;
	                
	                metaIngreso = MapUtils.getIntValue(mapConsultaEjecutivas, "metaIngreso");
	                realIngreso = MapUtils.getIntValue(mapConsultaEjecutivas, "realIngreso");
	                totalMetaIngreso = totalMetaIngreso + metaIngreso;
	                totalRealIngreso = totalRealIngreso + realIngreso;
	                
	                metaReca = (BigDecimal) MapUtils.getNumber(mapConsultaEjecutivas, "metaReca");
	                realReca = (BigDecimal) MapUtils.getNumber(mapConsultaEjecutivas, "realReca");
	                totalMetaReca = totalMetaReca.add(metaReca);
	                totalRealReca = totalRealReca.add(realReca);
	                
	                comisionBruta = (BigDecimal) MapUtils.getNumber(mapConsultaEjecutivas, "comisionBruta");
	                comisionNeta = (BigDecimal) MapUtils.getNumber(mapConsultaEjecutivas, "comisionNeta");
	                totalComisionBruta = totalComisionBruta.add(comisionBruta);
	                totalComisionNeta = totalComisionNeta.add(comisionNeta);
	            }
				
				f.setSumMetaPedido(String.valueOf(totalMetaPedido));
				f.setSumRealPedido(String.valueOf(totalRealPedido));
				f.setSumMetaIngreso(String.valueOf(totalMetaIngreso));
				f.setSumRealIngreso(String.valueOf(totalRealIngreso));
				f.setSumMetaReca(String.valueOf(totalMetaReca));
				f.setSumRealReca(String.valueOf(totalRealReca));
				f.setSumComisionBruta(String.valueOf(totalComisionBruta));
				f.setSumComisionNeta(String.valueOf(totalComisionNeta));
			}
		} else{
			f.setDesRegZonTerri("");
			f.setNivel("");
			f.setNumeroContrato("");
			f.setPeriodoNombramiento("");
			f.setFechaIngreso("");
			f.setCoberturaTerritorioDe("");
			f.setCoberturaTerritorioAl("");
			f.setCiudad("");
			f.setSumMetaPedido("");
			f.setSumRealPedido("");
			f.setSumMetaIngreso("");
			f.setSumRealIngreso("");
			f.setSumMetaReca("");
			f.setSumRealReca("");
			f.setSumComisionBruta("");
			f.setSumComisionNeta("");
			
			this.setHipEtapasEjecutivasList(new ArrayList());
			
			/*MessageResources messageResources = getResources(request);
			String mensajeerror = messageResources.getMessage("consultaHIPEjecutivasForm.valida.programa");
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.detail", mensajeerror));
			saveErrors(request, messages);*/
			
			String msjError = this.getResourceMessage("consultaHIPEjecutivasForm.valida.programa");
			this.addError("Error: ", msjError);
		}
	}
	
	public void ejecutarReporte(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("ejecutarReporte");
		}
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public List getHipEtapasEjecutivasList() {
		return hipEtapasEjecutivasList;
	}

	public void setHipEtapasEjecutivasList(List hipEtapasEjecutivasList) {
		this.hipEtapasEjecutivasList = hipEtapasEjecutivasList;
	}

	public List getHipConsultaEjecutivasList() {
		return hipConsultaEjecutivasList;
	}

	public void setHipConsultaEjecutivasList(List hipConsultaEjecutivasList) {
		this.hipConsultaEjecutivasList = hipConsultaEjecutivasList;
	}

}
