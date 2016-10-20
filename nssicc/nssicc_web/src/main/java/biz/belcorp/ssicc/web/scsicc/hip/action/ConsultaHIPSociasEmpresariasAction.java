package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPSociasEmpresariasForm;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultaHIPSociasEmpresariasAction"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 * 
 */

@ManagedBean  
@SessionScoped
public class ConsultaHIPSociasEmpresariasAction extends BasePopupAbstractAction {
		
	private static final long serialVersionUID = 344456628409827243L;

	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private List hipSociasEmpresariasResultadoList;
	private List hipSociasEmpresariasHistorialList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPSociasEmpresariasForm consultaHIPSociasEmpresariasForm = new ConsultaHIPSociasEmpresariasForm();
		return consultaHIPSociasEmpresariasForm;
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAtributes' method");
		}
		
		ConsultaHIPDatosClienteService consultaHIPDatosClienteService = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService" );
		ConsultaHIPSociasEmpresariasForm f = (ConsultaHIPSociasEmpresariasForm)this.formBusqueda;
				
		f.setIndicadorLider(Constants.ESTADO_INACTIVO);
		
		//Datos Cabecera
		ConsultaHIPDatosCliente dtoDatosCliente = this.consultaHIPDatosClienteAction.getHipDtoDatosCliente();
		String codigoConsultora = dtoDatosCliente.getCodigoCliente();
		
		if(StringUtils.isNotBlank(codigoConsultora)){
			
			if(log.isDebugEnabled())
				log.debug("codigoConsultora: " + codigoConsultora);
			
			String campanyaProceso = null;
			Map criteria = new HashMap();			
			criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo()); 
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); 
			criteria.put("indicadorActiva", Constants.NUMERO_UNO);
			
			List lista = mantenimientoOCRPedidoControlFacturacionService.getCampanhasActivasByCriteria(criteria); 
			if (lista != null && lista.size() > 0) { 
				campanyaProceso = (String) lista.get(0); 
			}
			
			if(StringUtils.isNotBlank(campanyaProceso))
			{
				String codigoPrograma = consultaHIPDatosClienteService.getCodigoProgramaLET(campanyaProceso);
				
				criteria.put("campanyaProceso", campanyaProceso);
				criteria.put("codigoPrograma", codigoPrograma);
				criteria.put("codigoConsultora", codigoConsultora);
				
				Map datos = consultaHIPDatosClienteService.getDatosSociaEmpresaria(criteria);
			
				if(datos != null)
				{					
					try{
						BeanUtils.copyProperties(f, datos);
						f.setIndicadorLider(Constants.ESTADO_ACTIVO);
					}
					catch(Exception ex){
						log.warn(ex.getMessage());
					}
					
					f.setNumeroDocumento(dtoDatosCliente.getNumeroDocIdentidad());
					f.setNombre(dtoDatosCliente.getNombreCompleto());

					//Ddatos Resultados, maximo 6 registros
					List resultados = consultaHIPDatosClienteService.getResultadosSociaEmpresaria(criteria);
					List resultadosAux = new ArrayList();
					if(resultados.size()<=6)
						resultadosAux = resultados;
					else {
						for(int i=0;i<resultados.size();i++) {
							resultadosAux.add(resultados.get(i));
							
							if(i==5)
								break;
						}
					}
					
					this.setHipSociasEmpresariasResultadoList(resultadosAux);
					
					//Datos Historico
					criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
					List historial = consultaHIPDatosClienteService.getHistorialSociaEmpresaria(criteria);
					this.setHipSociasEmpresariasHistorialList(historial);
				}
				else
				{
					//No es Lider
		            this.addError("Error: ", this.getResourceMessage("consultaHIPSociasEmpresariasForm.error.no.lider"));
				}							
			}
			else
			{
				//No existe campaña activa
				this.addError("Error: ", this.getResourceMessage("consultaHIPSociasEmpresariasForm.error.no.campanyaActiva"));
			}			
		}		
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setFindAttributes' method");
		}
		
		return null;
	}

	/**
	 * @return the consultaHIPDatosClienteAction
	 */
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	/**
	 * @param consultaHIPDatosClienteAction the consultaHIPDatosClienteAction to set
	 */
	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	/**
	 * @return the hipSociasEmpresariasResultadoList
	 */
	public List getHipSociasEmpresariasResultadoList() {
		return hipSociasEmpresariasResultadoList;
	}

	/**
	 * @param hipSociasEmpresariasResultadoList the hipSociasEmpresariasResultadoList to set
	 */
	public void setHipSociasEmpresariasResultadoList(
			List hipSociasEmpresariasResultadoList) {
		this.hipSociasEmpresariasResultadoList = hipSociasEmpresariasResultadoList;
	}

	/**
	 * @return the hipSociasEmpresariasHistorialList
	 */
	public List getHipSociasEmpresariasHistorialList() {
		return hipSociasEmpresariasHistorialList;
	}

	/**
	 * @param hipSociasEmpresariasHistorialList the hipSociasEmpresariasHistorialList to set
	 */
	public void setHipSociasEmpresariasHistorialList(
			List hipSociasEmpresariasHistorialList) {
		this.hipSociasEmpresariasHistorialList = hipSociasEmpresariasHistorialList;
	}
	
}
