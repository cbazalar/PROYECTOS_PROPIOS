package biz.belcorp.ssicc.web.spusicc.pre.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativo;
import biz.belcorp.ssicc.dao.spusicc.pre.model.ProductoMatriz;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPREMatrizAlternativosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pre.form.MantenimientoPREMatrizAlternativosForm;
import biz.belcorp.ssicc.web.spusicc.pre.form.MantenimientoPREMatrizAlternativosSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoPREMatrizAlternativosSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8167297495122896382L;
	
	private String ambos = Constants.TODAS;
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;

	@Override
	protected String getSalirForward() {
		return "mantenimientoPREMatrizAlternativosList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPREMatrizAlternativosForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPREMatrizAlternativosSearchForm searchForm = new MantenimientoPREMatrizAlternativosSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		log.debug("setFindAttributes");
		
		MantenimientoPREMatrizAlternativosSearchForm f = (MantenimientoPREMatrizAlternativosSearchForm) this.formBusqueda;
		MantenimientoPREMatrizAlternativosService service= (MantenimientoPREMatrizAlternativosService) getBean("spusicc.mantenimientoPREMatrizAlternativosService");
		
		Map params = BeanUtils.describe(f);
		
		if(StringUtils.equals(f.getIndicadorActivo(), Constants.TODAS))
			params.put("indicadorActivo", null);
		
		List lista = service.getAlternativos(params);
				
		return lista;	
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		log.debug("MantenimientoPREMatrizAlternativosAction - setSaveAttributes");
		MantenimientoPREMatrizAlternativosForm f = (MantenimientoPREMatrizAlternativosForm) this.formMantenimiento;
		f.setIndicadorMensaje(f.getIndicadorMensajeBool().equals(true)? Constants.NUMERO_UNO: Constants.NUMERO_CERO);
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoPREMatrizAlternativosService service = (MantenimientoPREMatrizAlternativosService) getBean("spusicc.mantenimientoPREMatrizAlternativosService");
		MatrizAlternativo matrizAlternativo = new MatrizAlternativo();

		if (StringUtils.isBlank(f.getIndicadorMensaje()))
			f.setIndicadorMensaje(Constants.NUMERO_CERO);

		BeanUtils.copyProperties(matrizAlternativo, f);

		try {
			if (f.isNewRecord()) {
				service.insertAlternativo(matrizAlternativo, usuario);
				f.setNewRecord(false);
			}
		} catch (InvalidIdentifierException iie) {
			this.addError("Error: ", this.getResourceMessage("mantenimientoPREMatrizAlternativosForm.save.error.unique"));
			return false;
		}

		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		MantenimientoPREMatrizAlternativosForm f = new MantenimientoPREMatrizAlternativosForm();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setCodigoPeriodo("");
		f.setCodigoVentaPrincipal("");
		f.setCodigoSAPPrincipal("");
		f.setDescripcionPrincipal("");
		f.setCodigoVentaAlternativo("");
		f.setCodigoSAPAlternativo("");
		f.setDescripcionAlternativo("");
		f.setIndicadorMensaje("");
		f.setNumeroOrden("");
		f.setOidMatrizPrincipal("");
		f.setOidMatrizAlternativo("");
		f.setCodigoPais(pais.getCodigo());
		f.setIndicadorMensajeBool(false);
		
				
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
       
        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
				
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		MantenimientoPREMatrizAlternativosSearchForm f= (MantenimientoPREMatrizAlternativosSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		limpiarForm(f);
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;		
	}
	
	private void limpiarForm(MantenimientoPREMatrizAlternativosSearchForm f)	
	{
		f.setCodigoPeriodo("");
		f.setCodigoVentaPrincipal("");
		f.setCodigoVentaAlternativo("");
		f.setIndicadorActivo(Constants.TODAS);		
	}
	
	public void actualizarEstado(ActionEvent event)
	{
		try {
			MantenimientoPREMatrizAlternativosService service= (MantenimientoPREMatrizAlternativosService) getBean("spusicc.mantenimientoPREMatrizAlternativosService");
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String estado = externalContext.getRequestParameterMap().get("parametroAccion");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			Map bean = (Map)this.beanRegistroSeleccionado;
			String id = bean.get("oid").toString();
			
			MatrizAlternativo ma= new MatrizAlternativo(); 
			ma.setOid(id);
			ma.setIndicadorActivo(estado);
			service.updateAlternativo(ma, usuario);
			
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			
			if(StringUtils.equals(estado, Constants.NUMERO_CERO))
			{
				this.addInfo("", this.getResourceMessage("mantenimientoPREMatrizAlternativosSearchForm.desactivado"));			
			}
			else if(StringUtils.equals(estado, Constants.NUMERO_UNO))
			{		
				this.addInfo("", this.getResourceMessage("mantenimientoPREMatrizAlternativosSearchForm.activado"));
			}						
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
	public String setValidarConfirmar(String accion) 
	{
		String mensaje = null;
		if(this.beanRegistroSeleccionado == null)
		{
			mensaje = this.getResourceMessage("errors.select.item");			
		}
		
		return mensaje;
	}
	
	public void seteaCodigoVentaPrincipal()
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoPREMatrizAlternativosForm f = (MantenimientoPREMatrizAlternativosForm) this.formMantenimiento;
		
		if(StringUtils.isNotBlank(f.getCodigoPeriodo()) && StringUtils.isNotBlank(f.getCodigoVentaPrincipal()))
		{
			f.setCodigoVentaPrincipal(StringUtils.leftPad(f.getCodigoVentaPrincipal(), 5, "0"));
			ProductoMatriz resultado = ajax.getProductoPREMatrizAlternativo(f.getCodigoPeriodo(), f.getCodigoVentaPrincipal());
			
			if(resultado != null) 
		   	{		
				f.setCodigoSAPPrincipal(resultado.getCodigoSAP());			
				f.setDescripcionPrincipal(resultado.getDescripcion());		
				f.setOidMatrizPrincipal(resultado.getOidMatriz());
		   	}
			else 
			{
				this.addError("Error: ", this.getResourceMessage("mensaje.CUV.noExiste"));
				f.setCodigoVentaPrincipal(null);
				f.setCodigoSAPPrincipal(null);
				f.setDescripcionPrincipal(null);	
				f.setOidMatrizPrincipal(null); 	
			}
		}else
		{
			f.setCodigoVentaPrincipal(null);
			f.setCodigoSAPPrincipal(null);
			f.setDescripcionPrincipal(null);	
			f.setOidMatrizPrincipal(null); 	
		}
	}
	
	public void seteaCodigoVentaAlternativo()
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoPREMatrizAlternativosForm f = (MantenimientoPREMatrizAlternativosForm) this.formMantenimiento;
		
		if(StringUtils.isNotBlank(f.getCodigoPeriodo()) && StringUtils.isNotBlank(f.getCodigoVentaAlternativo()))
		{
			f.setCodigoVentaAlternativo(StringUtils.leftPad(f.getCodigoVentaAlternativo(), 5, "0"));
			ProductoMatriz resultado = ajax.getProductoPREMatrizAlternativo(f.getCodigoPeriodo(), f.getCodigoVentaAlternativo());
			
			if(resultado != null) 
		   	{		
				f.setCodigoSAPAlternativo(resultado.getCodigoSAP());			
				f.setDescripcionAlternativo(resultado.getDescripcion());		
				f.setOidMatrizAlternativo(resultado.getOidMatriz());
		   	}
			else 
			{
				this.addError("Error: ", this.getResourceMessage("mensaje.CUV.noExiste"));
				f.setCodigoVentaAlternativo(null);
				f.setCodigoSAPAlternativo(null);
				f.setDescripcionAlternativo(null);	
				f.setOidMatrizAlternativo(null); 	
			}
		}else
		{
			f.setCodigoVentaAlternativo(null);
			f.setCodigoSAPAlternativo(null);
			f.setDescripcionAlternativo(null);	
			f.setOidMatrizAlternativo(null); 
		}		
	}
	
	@Override
	public String setValidarMantenimiento() 
	{
		String mensaje = null;
		MantenimientoPREMatrizAlternativosForm f = (MantenimientoPREMatrizAlternativosForm) this.formMantenimiento;
		
		if(StringUtils.isBlank(f.getCodigoVentaPrincipal()))
		{
			mensaje = "'Codigo de Venta Principal' es un campo requerido.";
			return mensaje;
		}
		
		if(StringUtils.isBlank(f.getCodigoVentaAlternativo()))
		{
			mensaje = "Codigo de Venta Alternativo' es un campo requerido.";
			return mensaje;
		}
		
		return mensaje;
	}
	
	public String getAmbos() {
		return ambos;
	}

	public void setAmbos(String ambos) {
		this.ambos = ambos;
	}

	public String getNumeroUno() {
		return numeroUno;
	}

	public void setNumeroUno(String numeroUno) {
		this.numeroUno = numeroUno;
	}

	public String getNumeroCero() {
		return numeroCero;
	}

	public void setNumeroCero(String numeroCero) {
		this.numeroCero = numeroCero;
	}

}
