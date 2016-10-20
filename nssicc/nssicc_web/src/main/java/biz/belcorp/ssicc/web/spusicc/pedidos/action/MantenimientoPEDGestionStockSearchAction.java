package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DetalleCuvExistencia;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.GestionStock;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDClasificacionesChequeoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDGestionStockService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDGestionStockForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDGestionStockSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoPEDGestionStockSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4566374031963143928L;
	
	private List pedLevantamientoErroresCodigosVentaList;
	private List pedLevantamientoErroresCodigosSapList;
	private List pedGestionStockTipoClienteList;
	private LabelValue[] pedGestionStockClasifList;
	private List pedGestionStockRegionList;
	private LabelValue[] pedGestionStockZonaList;
	private LabelValue[] pedGestionStockSubTipoClieList;
	private LabelValue[] pedGestionStockTipoClasifList;
	private String nombre;
	private DataTableModel dtGestionStock;
	private Object[] beanGestionStock;
	private String attachmentVenta = "";
	private String attachmentSap = "";
	private boolean indicaCierre;
	private boolean disableIndicador;
	
	//pantalla busqueda
	private List listCodigoVenta;
	private List listCodigoSap;
	
	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoPEDGestionStockList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoPEDGestionStockForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		MantenimientoPEDGestionStockSearchForm searchForm = new MantenimientoPEDGestionStockSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering my method 'setFindAttributes' ");
		}
		
		List lista = null;
		MantenimientoPEDGestionStockService service = (MantenimientoPEDGestionStockService) getBean("spusicc.mantenimientoPEDGestionStockService");
		MantenimientoPEDGestionStockSearchForm f = (MantenimientoPEDGestionStockSearchForm) this.formBusqueda;

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoCampanha", f.getCodigoCampanha());
		criteria.put("codigoVentaPrincipal", f.getCodigoVentaPrincipal());
		criteria.put("indicadorActivo", f.getIndicadorActivo());

		UploadedFile codigoFile = null;

		codigoFile = f.getCodigoVentaFile();
		if (codigoFile != null) {
			if (StringUtils.isNotBlank(codigoFile.getFileName())) 
			{
				criteria.put("listCodigoVenta", this.listCodigoVenta);
			}
		}

		codigoFile = f.getCodigoSapFile();
		if (codigoFile != null) {
			if (StringUtils.isNotBlank(codigoFile.getFileName())) 
			{
				criteria.put("listCodigoSap", this.listCodigoSap);
			}
		}

		if (StringUtils.equals(f.getIndicadorActivo(), "2")) {
			criteria.put("indicadorActivoAmbos", f.getIndicadorActivo());
			criteria.put("indicadorActivo", null);
		}

		lista = service.getGestionStockList(criteria);
		this.listCodigoVenta = null;
		this.listCodigoSap = null;
		this.dtGestionStock=new DataTableModel(lista);
		setAttachmentSap(null);
		setAttachmentVenta(null);
		log.debug("Fin lista Gestion Stock ");
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		return false;
	}
	
	public void eliminarLista(ActionEvent event){
		MantenimientoPEDGestionStockService service = (MantenimientoPEDGestionStockService) getBean("spusicc.mantenimientoPEDGestionStockService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		int tamanio = beanGestionStock.length;
		try {
			
			for (int i = 0; i < tamanio; i++) {
				GestionStock registroSeleccionado = (GestionStock) this.beanGestionStock[i];
				String oidGestionStock = registroSeleccionado.getOidGestionStock();
				String indicadorDetalleOferta = registroSeleccionado
						.getIndicadorDetalleOferta();
				Map criteria = new HashMap();

				criteria.put("accionAuditoria", "ELI");
				criteria.put("usuarioLogin", usuario.getLogin());

				criteria.put("oidGestionStock", oidGestionStock);
				criteria.put("indicadorDetalleOferta", indicadorDetalleOferta);
				// Eliminar
				service.updateEliminarGestionStock(criteria);
			}
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.beanGestionStock=null;
			this.addInfo("", this.getResourceMessage("mantenimientoPEDGestionStockForm.message.eliminar"));

		} catch (Exception e) {
			this.addError("", this.obtieneMensajeErrorException(e));
		}
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		String lineaErrores = "";
		try {
			MantenimientoPEDGestionStockService service = (MantenimientoPEDGestionStockService) getBean("spusicc.mantenimientoPEDGestionStockService");
			MantenimientoPEDGestionStockForm f = (MantenimientoPEDGestionStockForm) this.formMantenimiento;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			List lista = new ArrayList();
			Map criteria = new HashMap();
			
			criteria.put("codigoCampanha", f.getCodigoCampanha());
			criteria.put("codigoPais", f.getCodigoPais());
			
			if (!StringUtils.isBlank(f.getCodigoVenta())) {
				criteria.put("codigoVenta",f.getCodigoVenta());
				lista = service.getValidarCuvExistencia(criteria);

				if (lista!= null && lista.size() > 0) {
					log.debug("Cantidad de valores en mi lista: "+ lista.size());
					
					DetalleCuvExistencia det = new DetalleCuvExistencia();
					det = (DetalleCuvExistencia)lista.get(0);
					
					criteria.put("oidDetalleOferta", det.getOidDetalleOferta());
				}else {
					this.addError("", this.getResourceMessage("mantenimientoPEDGestionStockForm.errors.cuv.invalido"));
					limpiarCampos(f);
					return false;
				}
			}
			
			if(this.indicaCierre)
				f.setIndCierreDiario("1");
			else
				f.setIndCierreDiario("0");
			criteria.put("codigoPais", pais.getCodigo());
			
			//Si Tipo de Ingreso es Limite de Venta, colocar el campo VALOR, si no NULL
			criteria.put("valorLimiteControlVenta",StringUtils.equals(f.getTipoIngreso(), "3")? f.getValor(): null);
			
			criteria.put("clasificacionList", f.getOidClasificacion());
			criteria.put("zonaList", f.getZonaList());
			criteria.put("codigoPeriodo", f.getCodigoCampanha());
			criteria.put("indicadorUltimasNoticias", StringUtils.isBlank(f.getFechaActivacion())? "1":"0");
			//Si Tipo de Ingreso es Control de Stock Porcentual, colocar el campo VALOR, si no NULL
			criteria.put("valorPorcentual",StringUtils.equals(f.getTipoIngreso(), "2")? f.getValor(): null);
			//Si Tipo de Ingreso es Control de Stock, colocar el campo VALOR, si no NULL
			criteria.put("valorUnidad",StringUtils.equals(f.getTipoIngreso(), "1")? f.getValor(): null);
			criteria.put("regionList", f.getRegionList());
			criteria.put("subTipoCLienteList",f.getOidSubTipoCliente());
			criteria.put("tipoClasificacionList",f.getOidTipoClasificacion());
			criteria.put("tipoClienteList",f.getOidTipoCliente());
			criteria.put("indicadorActivo", StringUtils.isBlank(f.getFechaActivacion())?"1":"0");
			criteria.put("fechaActivacion", f.getFechaActivacion());
			criteria.put("usuarioLogin", usuario.getLogin());
			criteria.put("listaCodigosVenta", null);
			criteria.put("indCierreDiario",f.getIndCierreDiario());
			
  			List listaCodigosVenta = this.pedLevantamientoErroresCodigosVentaList;
			if (listaCodigosVenta != null && listaCodigosVenta.size() > 0) {
				criteria.put("listaCodigos", listaCodigosVenta);
				criteria.put("tipoLista", "listaCodigosVenta");
			}

			List listaCodigosSap = this.pedLevantamientoErroresCodigosSapList;
			if (listaCodigosSap != null && listaCodigosSap.size() > 0) {
				criteria.put("listaCodigos", listaCodigosSap);
				criteria.put("tipoLista", "listaCodigosSap");
			}		
			
			lineaErrores = service.insertGestionStock(criteria);
			
			if (!StringUtils.isBlank(lineaErrores)) {
				log.debug("Mensaje con la lista los registros erroneos");
				//MOSTRAR LA LISTA CON LOS ERRORES
				this.addError("", this.getResourceMessage("mantenimientoPEDGestionStockForm.message.errorInsert",
						new Object[]{lineaErrores}));
				limpiarCampos(f);
				return false;
			}
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{error}));	
		}
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		MantenimientoPEDClasificacionesChequeoService mantenimientoPEDClasificacionesChequeoService = (MantenimientoPEDClasificacionesChequeoService) getBean("spusicc.pedidos.mantenimientoPEDClasificacionesChequeoService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoPEDGestionStockForm f = new MantenimientoPEDGestionStockForm();

		BeanUtils.copyProperties(f, new MantenimientoPEDGestionStockForm());

		f.setCodigoPais(pais.getCodigo());

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		criteria.put("codigoTipoIng", Constants.CODIGO_TIPO_INGRESO_M);

		this.pedGestionStockTipoClienteList = mantenimientoPEDClasificacionesChequeoService.getTipoCliente();
		this.pedGestionStockRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);

		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		f.setCodigoCampanha(controlFacturacion.getCodigoPeriodo());

		this.pedLevantamientoErroresCodigosVentaList = null;
		this.pedLevantamientoErroresCodigosSapList = null;
		this.indicaCierre=false;
		this.disableIndicador=true;
		return f;
	}
	
	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		
		MantenimientoPEDGestionStockSearchForm f = (MantenimientoPEDGestionStockSearchForm) this.formBusqueda;
		BeanUtils.copyProperties(f, new MantenimientoPEDGestionStockSearchForm());
		Map criteriaPeriodo = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);

		f.setCodigoCampanha(controlFacturacion.getCodigoPeriodo());
		f.setCodigoPais(pais.getCodigo());
		mostrarListaBusqueda=false;
	}
	
	//Cambia el Estado del registro del Stock - ACTIVADO
	public void activar(ActionEvent event) 
	{
		MantenimientoPEDGestionStockService service = (MantenimientoPEDGestionStockService) getBean("spusicc.mantenimientoPEDGestionStockService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		int tamanio = beanGestionStock.length;
		try {
			
			for (int i = 0; i < tamanio; i++) {
				GestionStock registroSeleccionado = (GestionStock) this.beanGestionStock[i];
				String oidGestionStock = registroSeleccionado.getOidGestionStock();
				String indicadorDetalleOferta = registroSeleccionado
						.getIndicadorDetalleOferta();
				Map criteria = new HashMap();
				criteria.put("accionAuditoria", "ACT");
				criteria.put("usuarioLogin", usuario.getLogin());
				criteria.put("oidGestionStock", oidGestionStock);
				criteria.put("indicadorDetalleOferta", indicadorDetalleOferta);
				// Desactivar
				service.updateActivarGestionStock(criteria);
			}

			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.beanGestionStock=null;
			this.addInfo("", this.getResourceMessage("mantenimientoPEDGestionStockForm.message.activar"));

		} catch (Exception e) {
			this.addError("", this.obtieneMensajeErrorException(e));
		}
	}
	
	// Cambia el Estado del registro del Stock - DESACTIVADO
	public void desactivar(ActionEvent event) 
	{
		MantenimientoPEDGestionStockService service = (MantenimientoPEDGestionStockService) getBean("spusicc.mantenimientoPEDGestionStockService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		int tamanio = beanGestionStock.length;
		try {
			for (int i = 0; i < tamanio; i++) {
				GestionStock registroSeleccionado = (GestionStock) this.beanGestionStock[i];
				String oidGestionStock = registroSeleccionado.getOidGestionStock();
				String indicadorDetalleOferta = registroSeleccionado
						.getIndicadorDetalleOferta();
				Map criteria = new HashMap();

				criteria.put("accionAuditoria", "DESACT");
				criteria.put("usuarioLogin", usuario.getLogin());
				criteria.put("oidGestionStock", oidGestionStock);
				criteria.put("indicadorDetalleOferta", indicadorDetalleOferta);
				// Desactivar
				service.updateDesactivarGestionStock(criteria);
			}

			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.beanGestionStock=null;
			this.addInfo("", this.getResourceMessage("mantenimientoPEDGestionStockForm.message.desactivar"));
		} catch (Exception e) {
			this.addError("", this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
	public String setValidarConfirmar(String accion) 
	{
		if (beanGestionStock==null || beanGestionStock.length==0) 
			return this.getResourceMessage("errors.select.item");
				
		return null;
	}
	
	// Metodo que carga los registros de un archivo venta
	public void uploadArchivoVenta(FileUploadEvent event) throws Exception
	{
		MantenimientoPEDGestionStockSearchForm f = (MantenimientoPEDGestionStockSearchForm) this.formBusqueda;
		List lista = new ArrayList();
		
		try{
		UploadedFile archivo = event.getFile();
		f.setCodigoVentaFile(archivo);
		
		// recuperamos el fichero
		log.debug("Nombre Archivo Upload venta: " + archivo.getFileName());

		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();
		BufferedReader di = new BufferedReader(new InputStreamReader(is));
		String linea;
		
		while(true) {
			linea = di.readLine();
			if (linea == null)
				break;
			
			if(StringUtils.isNotBlank(linea.trim()))
				lista.add(linea.trim());
		}
		
		this.listCodigoVenta = lista;
		if(event != null)
			this.setAttachmentVenta(event.getFile().getFileName());
		
		this.addInfo("Información: ", this.getResourceMessage("mantenimientoFDVClusterizacionForm.file.load.ok"));
		
		}catch(Exception e)
		{
			throw new Exception(this.getResourceMessage("mantenimientoFDVClusterizacionForm.file.load.error"));
		}
	}
	
	// Metodo que carga los registros de un archivo sap
	public void uploadArchivoSAP(FileUploadEvent event)  throws Exception
	{
		MantenimientoPEDGestionStockSearchForm f = (MantenimientoPEDGestionStockSearchForm) this.formBusqueda;
		List lista = new ArrayList();
		
		try{
		UploadedFile archivo = event.getFile();
		f.setCodigoSapFile(archivo);
		
		// recuperamos el fichero
		log.debug("Nombre Archivo Upload sap: " + archivo.getFileName());

		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();
		BufferedReader di = new BufferedReader(new InputStreamReader(is));
		String linea;
		
		while(true) {
			linea = di.readLine();
			if (linea == null)
				break;
			
			if(StringUtils.isNotBlank(linea.trim()))
				lista.add(linea.trim());
		}
		
		this.listCodigoSap = lista;
		if(event != null)
			this.setAttachmentSap(event.getFile().getFileName());
		
		this.addInfo("Información: ", this.getResourceMessage("mantenimientoFDVClusterizacionForm.file.load.ok"));
		
		}catch(Exception e)
		{
			throw new Exception(this.getResourceMessage("mantenimientoFDVClusterizacionForm.file.load.error"));
		}
	}	

	public void loadSubTipoCliente(ValueChangeEvent event)
	{
		String[] valores = (String[])event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ArrayList oidTipoCliente = new ArrayList();
		
		for(String valor : valores)
		{
			oidTipoCliente.add(valor);
		}
		
		if(oidTipoCliente.size() > 0)
		{
			this.pedGestionStockSubTipoClieList = ajax.getSubTipoClienteMultipleByOidTipoCliente(oidTipoCliente, Constants.TODAS);
			this.pedGestionStockTipoClasifList = null;
			this.pedGestionStockClasifList = null;
		}
		else
		{
			this.pedGestionStockSubTipoClieList = null;
			this.pedGestionStockTipoClasifList = null;
			this.pedGestionStockClasifList = null;
		}
	}
	
	public void loadTipoClasificacion(ValueChangeEvent event)
	{
		String[] valores = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ArrayList oidSubTipoCliente = new ArrayList();
		
		for(String valor : valores)
		{
			oidSubTipoCliente.add(valor);
		}
		
		if(oidSubTipoCliente.size() > 0)
		{
			this.pedGestionStockTipoClasifList = ajax.getTipoClasificacionMultipleByOidSubTipoCliente(oidSubTipoCliente, Constants.TODAS);
			this.pedGestionStockClasifList = null;
		}
		else
		{
			this.pedGestionStockTipoClasifList = null;
			this.pedGestionStockClasifList = null;
		}
	}
	
	public void loadClasificacion(ValueChangeEvent event)
	{
		String[] valores = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ArrayList oidTipoClasificacion = new ArrayList();
		
		for(String valor : valores)
		{
			oidTipoClasificacion.add(valor);
		}
		
		if(oidTipoClasificacion.size() > 0)
			this.pedGestionStockClasifList = ajax.getClasificacionMultipleByOidTipoClasificacion(oidTipoClasificacion, Constants.TODAS);
		else
			this.pedGestionStockClasifList = null;
	}
	
	public void loadZonas(ValueChangeEvent event)
	{
		String[] valores = (String[]) event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		this.pedGestionStockZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, 
				valores, Constants.TODAS);
	}
	
	public void loadfileCodigoVenta(FileUploadEvent event) throws IOException 
	{
		log.debug("MantenimientoPEDGestionStockAction - loadfileCodigoVenta");

		MantenimientoPEDGestionStockForm f = (MantenimientoPEDGestionStockForm) this.formMantenimiento;
		MantenimientoPEDGestionStockService service = (MantenimientoPEDGestionStockService) getBean("spusicc.mantenimientoPEDGestionStockService");

		this.pedLevantamientoErroresCodigosVentaList = null;
		this.pedLevantamientoErroresCodigosSapList = null;
		f.setCodigoVenta(null);

		UploadedFile archivo = event.getFile();
		f.setCodigoVentaFile(archivo);
		Map criteria = new HashMap();
		criteria.put("codigoCampanha", f.getCodigoCampanha());

		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;
		String codigoVenta = "";
		int cont = 0;

		List lista = null;
		List listaDetalleCuv = new ArrayList();

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;
			codigoVenta = StringUtils.leftPad(linea.trim(), 5, '0');
			criteria.put("codigoVenta", codigoVenta);
			DetalleCuvExistencia det = new DetalleCuvExistencia();
			lista = service.getValidarCuvExistencia(criteria);
			if (lista != null && lista.size() > 0) {
				det = (DetalleCuvExistencia) lista.get(0);
				det.setCodigoVenta(codigoVenta);
				det.setIndicadorValido("1");
			} else {
				det.setCodigoVenta(codigoVenta);
				det.setIndicadorValido("0");
				cont++;
			}
			listaDetalleCuv.add(det);
		}

		f.setCodigosErradosFile("");

		if (cont > 0)
			f.setCodigosErradosFile("Existe(n) " + cont + " codigo(s) errado(s)");

		// Lista del Detalle de los Códigos de Venta del Archivo
		this.pedLevantamientoErroresCodigosVentaList = listaDetalleCuv;
		f.setCodigoVentaFileLabel(archivo.getFileName());		
		f.setCodigoSapFileLabel(null);
	}
	
	public void loadfileCodigoSap(FileUploadEvent event) throws IOException 
	{
		log.debug("MantenimientoPEDGestionStockAction - loadfileCodigoSap");

		MantenimientoPEDGestionStockForm f = (MantenimientoPEDGestionStockForm) this.formMantenimiento;
		MantenimientoPEDGestionStockService service = (MantenimientoPEDGestionStockService) getBean("spusicc.mantenimientoPEDGestionStockService");

		this.pedLevantamientoErroresCodigosVentaList = null;
		this.pedLevantamientoErroresCodigosSapList = null;
		f.setCodigoVenta(null);

		UploadedFile archivo = event.getFile();
		f.setCodigoSapFile(archivo);

		Map criteria = new HashMap();
		criteria.put("codigoCampanha", f.getCodigoCampanha());
		criteria.put("codigoPais", f.getCodigoPais());

		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;
		String codigoSap = "";
		int cont = 0;

		List lista;
		List listaDetalleCuv = new ArrayList();
		// Map detalles = new HashMap();

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;
			codigoSap = StringUtils.leftPad(linea.trim(), 9, '0');
			criteria.put("codigoSap", codigoSap);

			DetalleCuvExistencia det;
			lista = null;

			lista = service.getValidarSapExistencia(criteria);
			if (lista != null && lista.size() > 0) {
				for (int i = 0; i < lista.size(); i++) {
					det = new DetalleCuvExistencia();
					det = (DetalleCuvExistencia) lista.get(i);

					det.setCodigoSap(codigoSap);
					det.setCodigoVenta(det.getCodigoVenta());
					det.setIndicadorValido("1");

					listaDetalleCuv.add(det);
				}
			} else {
				det = new DetalleCuvExistencia();
				det.setCodigoSap(codigoSap);
				det.setCodigoVenta("");
				det.setIndicadorValido("0");
				cont++;

				listaDetalleCuv.add(det);
			}

		}

		f.setCodigosErradosFile("");

		if (cont > 0)
			f.setCodigosErradosFile("Existe(n) " + cont + " codigo(s) errado(s)");

		// Lista del Detalle de los Códigos de Venta del Archivo
		this.pedLevantamientoErroresCodigosSapList = listaDetalleCuv;
		f.setCodigoSapFileLabel(archivo.getFileName());
		f.setCodigoVentaFileLabel(null);
	}
	
	@Override
	public String setValidarMantenimiento() 
	{
		String mensaje = null;
		MantenimientoPEDGestionStockForm f = (MantenimientoPEDGestionStockForm) this.formMantenimiento;
		
		if(StringUtils.isBlank(f.getCodigoVenta()) && StringUtils.isBlank(f.getCodigoSapFileLabel()) 
				&& StringUtils.isBlank(f.getCodigoVentaFileLabel()))
			return mensaje = this.getResourceMessage("mantenimientoPEDGestionStockForm.msg.listaNoCargada");
		
		return mensaje;
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoPEDGestionStockForm f = (MantenimientoPEDGestionStockForm) this.formMantenimiento;
		boolean registro = f.isNewRecord();
		if(registro)
			return "mantenimientoPEDGestionStockForm.added";
		else
			return "";
	}
	
	public void validaExistenciaCuv(AjaxBehaviorEvent e)
	{
		String valor = (String) ((UIOutput)e.getSource()).getValue();		
		MantenimientoPEDGestionStockForm f = (MantenimientoPEDGestionStockForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		if(StringUtils.isBlank(f.getCodigoCampanha()))
			this.addError("", this.getResourceMessage("mantenimientoPEDGestionStockForm.validation.campanha"));
		else
		{
			this.nombre = ajax.getValidarExistenciaCuv(valor, f.getCodigoCampanha());
		}
	}
	
	private void limpiarCampos(MantenimientoPEDGestionStockForm f)
	{
		f.setCodigoVenta("");
		f.setCodigoVentaFile(null);
		f.setCodigoVentaFileLabel("");
		f.setCodigosErradosFile("");
		f.setCodigoSapFile(null);
		f.setCodigoSapFileLabel("");
		f.setTipoIngreso("");
		f.setValor("");
		f.setOidTipoCliente(null);
		f.setOidSubTipoCliente(null);
		f.setOidTipoClasificacion(null);
		f.setOidClasificacion(null);
		f.setRegionList(null);
		f.setZonaList(null);
		this.pedLevantamientoErroresCodigosVentaList = null;
		this.pedLevantamientoErroresCodigosSapList = null;
	}
	
	public void habilitaIndicadorCierre(ValueChangeEvent event){
		try {
			this.disableIndicador=true;
			String valor= event.getNewValue().toString();
			if(StringUtils.equals(valor, "3"))
				this.disableIndicador=false;
			else				
				this.disableIndicador=true;
		} catch (Exception e) {
			this.addError("Error:",this.obtieneMensajeErrorException(e));
		}
	}
	
	public List getPedLevantamientoErroresCodigosVentaList() {
		return pedLevantamientoErroresCodigosVentaList;
	}
	

	public void setPedLevantamientoErroresCodigosVentaList(List pedLevantamientoErroresCodigosVentaList) {
		this.pedLevantamientoErroresCodigosVentaList = pedLevantamientoErroresCodigosVentaList;
	}
	

	public List getPedLevantamientoErroresCodigosSapList() {
		return pedLevantamientoErroresCodigosSapList;
	}
	

	public void setPedLevantamientoErroresCodigosSapList(List pedLevantamientoErroresCodigosSapList) {
		this.pedLevantamientoErroresCodigosSapList = pedLevantamientoErroresCodigosSapList;
	}
	

	public List getPedGestionStockTipoClienteList() {
		return pedGestionStockTipoClienteList;
	}
	

	public void setPedGestionStockTipoClienteList(List pedGestionStockTipoClienteList) {
		this.pedGestionStockTipoClienteList = pedGestionStockTipoClienteList;
	}
	

	public LabelValue[] getPedGestionStockClasifList() {
		return pedGestionStockClasifList;
	}
	

	public void setPedGestionStockClasifList(LabelValue[] pedGestionStockClasifList) {
		this.pedGestionStockClasifList = pedGestionStockClasifList;
	}
	

	public List getPedGestionStockRegionList() {
		return pedGestionStockRegionList;
	}
	

	public void setPedGestionStockRegionList(List pedGestionStockRegionList) {
		this.pedGestionStockRegionList = pedGestionStockRegionList;
	}
	

	public LabelValue[] getPedGestionStockZonaList() {
		return pedGestionStockZonaList;
	}
	

	public void setPedGestionStockZonaList(LabelValue[] pedGestionStockZonaList) {
		this.pedGestionStockZonaList = pedGestionStockZonaList;
	}
	

	public LabelValue[] getPedGestionStockSubTipoClieList() {
		return pedGestionStockSubTipoClieList;
	}
	

	public void setPedGestionStockSubTipoClieList(LabelValue[] pedGestionStockSubTipoClieList) {
		this.pedGestionStockSubTipoClieList = pedGestionStockSubTipoClieList;
	}
	

	public LabelValue[] getPedGestionStockTipoClasifList() {
		return pedGestionStockTipoClasifList;
	}
	

	public void setPedGestionStockTipoClasifList(LabelValue[] pedGestionStockTipoClasifList) {
		this.pedGestionStockTipoClasifList = pedGestionStockTipoClasifList;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DataTableModel getDtGestionStock() {
		return dtGestionStock;
	}

	public void setDtGestionStock(DataTableModel dtGestionStock) {
		this.dtGestionStock = dtGestionStock;
	}

	public Object[] getBeanGestionStock() {
		return beanGestionStock;
	}

	public void setBeanGestionStock(Object[] beanGestionStock) {
		this.beanGestionStock = beanGestionStock;
	}

	public List getListCodigoVenta() {
		return listCodigoVenta;
	}

	public void setListCodigoVenta(List listCodigoVenta) {
		this.listCodigoVenta = listCodigoVenta;
	}

	public List getListCodigoSap() {
		return listCodigoSap;
	}

	public void setListCodigoSap(List listCodigoSap) {
		this.listCodigoSap = listCodigoSap;
	}

	public String getAttachmentVenta() {
		return attachmentVenta;
	}

	public void setAttachmentVenta(String attachmentVenta) {
		this.attachmentVenta = attachmentVenta;
	}

	public String getAttachmentSap() {
		return attachmentSap;
	}

	public void setAttachmentSap(String attachmentSap) {
		this.attachmentSap = attachmentSap;
	}

	/**
	 * @return the indicaCierre
	 */
	public boolean isIndicaCierre() {
		return indicaCierre;
	}

	/**
	 * @param indicaCierre the indicaCierre to set
	 */
	public void setIndicaCierre(boolean indicaCierre) {
		this.indicaCierre = indicaCierre;
	}

	/**
	 * @return the disableIndicador
	 */
	public boolean isDisableIndicador() {
		return disableIndicador;
	}

	/**
	 * @param disableIndicador the disableIndicador to set
	 */
	public void setDisableIndicador(boolean disableIndicador) {
		this.disableIndicador = disableIndicador;
	}


	
	
}