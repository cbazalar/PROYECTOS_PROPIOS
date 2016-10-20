/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDNumerosFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDNumerosFacturacionForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDNumerosFacturacionSearchForm;

/**
 * @author Sigcomt
 *
 */

@ManagedBean
@SessionScoped
public class MantenimientoPEDNumerosFacturacionSearchAction extends BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6155897175059543292L;
	
	private List siccTipoDocumentoList = new ArrayList();
	private List siccSociedadList = new ArrayList();
	
	private DataTableModel listaModelPedNumerosFacturacionList = new DataTableModel();
	private List pedNumerosFacturacionList = new ArrayList();
	private Map columnasSeleccionadas = new HashMap();

	@Override
	protected String getSalirForward() {
		return "mantenimientoPEDNumerosFacturacionSearchForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoPEDNumerosFacturacionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPEDNumerosFacturacionSearchForm form = new MantenimientoPEDNumerosFacturacionSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'MantenimientoPEDNumerosFacturacionSearchAction - setFindAttributes' method");
		}

		MantenimientoPEDNumerosFacturacionSearchForm f = (MantenimientoPEDNumerosFacturacionSearchForm)this.formBusqueda;
		MantenimientoPEDNumerosFacturacionService service = (MantenimientoPEDNumerosFacturacionService) getBean("spusicc.mantenimientoPEDNumerosFacturacionService");
		
		Map map = BeanUtils.describe(f);
		List list = service.getNumerosFacturacionList(map);
		
		this.pedNumerosFacturacionList = list;
		this.listaModelPedNumerosFacturacionList = new DataTableModel(this.pedNumerosFacturacionList);
//		this.mostrarListaBusqueda= true;
		
		return list;	
	}


	@Override
	protected boolean setDeleteAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'MantenimientoPEDNumerosFacturacionSearchAction - delete' method");
		}
		
		try {
			MantenimientoPEDNumerosFacturacionService service = (MantenimientoPEDNumerosFacturacionService) getBean("spusicc.mantenimientoPEDNumerosFacturacionService");

			Map criteria = (HashMap)this.beanRegistroSeleccionado;
			service.deleteNumerosFacturacion(criteria);
			reset();
			this.pedNumerosFacturacionList = null;
			this.listaModelPedNumerosFacturacionList = null;
			this.listaBusqueda = null;
			this.datatableBusqueda = null;
//			this.mostrarListaBusqueda= false; 
			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));
		}
		
		return true;
	}
	
	private void reset(){
		
		MantenimientoPEDNumerosFacturacionSearchForm f = (MantenimientoPEDNumerosFacturacionSearchForm)this.formBusqueda;
		f.setOidTipoDocumento("");
		f.setOidSociedad("");
		
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		
		MantenimientoPEDNumerosFacturacionForm f = (MantenimientoPEDNumerosFacturacionForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));		
		
		String oidDocumentoSubacceso = f.getOidDocumentoSubacceso();
		String oidSociedad = f.getOidSociedad();
		String oidTipoDocumento = f.getOidTipoDocumento();
		String numeroInterno = f.getNumeroInterno();
		String limiteNumero = f.getLimiteNumero();
		String annio = f.getAnnio();
		String serieInterno = f.getSerieInterno();
		String oidPais = f.getOidPais();
		String numeroAutorizacion = f.getNumeroAutorizacion();
		String llave = f.getLlave();			
		String fechaFin = f.getFechaFin();
		String observaciones = f.getObservaciones();
		String usuario = f.getUsuario();
		String valida = null;
		
		if(StringUtils.equals(this.accion, this.ACCION_NUEVO))
			valida = Constants.NUMERO_UNO;
		else
			valida = Constants.NUMERO_DOS;
		
		ajax.insertNumerosFacturacion(oidDocumentoSubacceso, oidSociedad, oidTipoDocumento, numeroInterno, limiteNumero, 
										annio, serieInterno, oidPais, numeroAutorizacion, llave, fechaFin, observaciones, usuario, valida);
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		String mensaje = "mantenimientoPEDNumerosFacturacionForm.registro.insertado";
		return mensaje;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {

		String mensaje = null;
		MantenimientoPEDNumerosFacturacionForm f = (MantenimientoPEDNumerosFacturacionForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		if (StringUtils.equals(this.accion, this.ACCION_NUEVO)) 
		{
			String oidPais = f.getOidPais();
			String oidTipoDocumento = f.getOidTipoDocumento();
			String oidSociedad = f.getOidSociedad();
			String data = ajax.getValidarNumerosFacturacion(oidPais, oidTipoDocumento, oidSociedad) ;
			
			if(StringUtils.equals(data, Constants.NUMERO_UNO)){
				mensaje = "mantenimientoPEDNumerosFacturacionForm.existe.registro";
				return this.getResourceMessage(mensaje);				
			}
		}
		
		return mensaje;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'MantenimientoPEDNumerosFacturacionSearchAction - setObtenerRegistroAttributes' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
				
		MantenimientoPEDNumerosFacturacionForm f = new MantenimientoPEDNumerosFacturacionForm();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Calendar fecha = new GregorianCalendar();
        int ianhio = fecha.get(Calendar.YEAR);
		String anhio = StringUtils.substring(String.valueOf(ianhio), 2);

		f.setOidTipoDocumento(null);
		f.setOidSociedad(null);
		f.setLimiteNumero("999999999");
		f.setNumeroInterno("1");
		f.setFechaFin(null);
		f.setSerieInterno(null);
		f.setAnnio(anhio);
		f.setNumeroAutorizacion(null);
		f.setLlave(null);
		f.setObservaciones(null);
		
		
		f.setCodigoPais(pais.getCodigo());
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		f.setOidPais(reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion));
		f.setUsuario(usuario.getLogin());
		
		if (StringUtils.equals(this.accion, this.ACCION_MODIFICAR)) 
		{
			Map criteria =	(Map) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, criteria);
			f.setFechaFinD(DateUtil.convertStringToDate(f.getFechaFin()));
		}
		return f;
	}
	
	

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'MantenimientoPEDNumerosFacturacionSearchAction - setViewAttributes' method");
		}
		
		MantenimientoPEDNumerosFacturacionService service = (MantenimientoPEDNumerosFacturacionService) getBean("spusicc.mantenimientoPEDNumerosFacturacionService");			
		this.siccTipoDocumentoList = service.getTipoDocumentoList(); 
		this.siccSociedadList = service.getSociedadList();
		this.pedNumerosFacturacionList = null;

		this.mostrarBotonConsultar = false;
//		this.mostrarListaBusqueda= false;
		reset();
	
	}

	/**
	 * @return the siccTipoDocumentoList
	 */
	public List getSiccTipoDocumentoList() {
		return siccTipoDocumentoList;
	}

	/**
	 * @param siccTipoDocumentoList the siccTipoDocumentoList to set
	 */
	public void setSiccTipoDocumentoList(List siccTipoDocumentoList) {
		this.siccTipoDocumentoList = siccTipoDocumentoList;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the pedNumerosFacturacionList
	 */
	public List getPedNumerosFacturacionList() {
		return pedNumerosFacturacionList;
	}

	/**
	 * @param pedNumerosFacturacionList the pedNumerosFacturacionList to set
	 */
	public void setPedNumerosFacturacionList(List pedNumerosFacturacionList) {
		this.pedNumerosFacturacionList = pedNumerosFacturacionList;
	}

	/**
	 * @return the listaModelPedNumerosFacturacionList
	 */
	public DataTableModel getListaModelPedNumerosFacturacionList() {
		return listaModelPedNumerosFacturacionList;
	}

	/**
	 * @param listaModelPedNumerosFacturacionList the listaModelPedNumerosFacturacionList to set
	 */
	public void setListaModelPedNumerosFacturacionList(
			DataTableModel listaModelPedNumerosFacturacionList) {
		this.listaModelPedNumerosFacturacionList = listaModelPedNumerosFacturacionList;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public Map getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(Map columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}
	
	

}
