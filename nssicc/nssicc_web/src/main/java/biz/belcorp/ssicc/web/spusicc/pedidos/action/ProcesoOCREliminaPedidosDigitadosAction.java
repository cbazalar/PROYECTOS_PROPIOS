package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCREliminaPedidosDigitadosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class ProcesoOCREliminaPedidosDigitadosAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4206144898961996801L;
	
	private List siccZonaList;
	private List siccRegionList;
	private List pedidosFacturadosAEliminarList;
	private List stoLevantamientoErroresClientesList;
	private List stoResumenClientesList;
	private DataTableModel listaTablaModel;
	private List columnasSeleccionadas;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoOCREliminaPedidosDigitadosForm searchForm = new ProcesoOCREliminaPedidosDigitadosForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		ProcesoOCREliminaPedidosDigitadosForm eliminaPedidosFacturadosForm = (ProcesoOCREliminaPedidosDigitadosForm)this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// Removemos atributos session		
//		request.getSession().removeAttribute(Constants.PED_PEDIDOS_A_ELIMINAR_FACT_LIST);

        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  

        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);

		// Carga Fecha y Periodo
        eliminaPedidosFacturadosForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

        ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
        
        this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",criteria);
        
        this.mostrarListaBusqueda = false;
        this.mostrarBotonBuscar = false;
        this.mostrarBotonConsultar = false;
        this.mostrarBotonEliminar = false;
        this.mostrarBotonModificar = false;
        this.mostrarBotonNuevo = false;
	}
	
	/**
	 * Metodo que completa el codigo de cliente
	 * @param codigoCliente
	 * @param codigoPais
	 * @return
	 */
	private String completacodigoCliente(String codigoCliente, String codigoPais) {
		PaisService service = (PaisService) getBean("paisService");
		Pais pais = service.getPais(codigoPais);			
		if (codigoCliente.length() < pais.getLongitudCodigoCliente()){			
			for (int i=codigoCliente.length(); i< pais.getLongitudCodigoCliente(); i++)
				codigoCliente = "0"+codigoCliente;							
		}		                       
		return codigoCliente;	
	}
	
	
	/**
	 * Metodo que realiza la busqueda
	 * @param event
	 */
	public void buscar(ActionEvent event)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		ProcesoOCREliminaPedidosDigitadosForm eliminaPedidosFacturadosForm = (ProcesoOCREliminaPedidosDigitadosForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		eliminaPedidosFacturadosForm.setFechaFacturacion(DateUtil.convertDateToString(eliminaPedidosFacturadosForm.getFechaFacturacionDate()));
		eliminaPedidosFacturadosForm.setFechaSolicitud(DateUtil.convertDateToString(eliminaPedidosFacturadosForm.getFechaSolicitudDate()));

		Map criteria = new HashMap();
		String codigoPais = pais.getCodigo();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPeriodo", eliminaPedidosFacturadosForm.getCodigoPeriodo());
		criteria.put("fechaSolicitud", eliminaPedidosFacturadosForm.getFechaSolicitud());
		criteria.put("indOrigen", Constants.STO_ORIGEN_DIGITADO);
		criteria.put("indicadorCarga", Constants.SI);
		criteria.put("codigoCliente", eliminaPedidosFacturadosForm.getCodigoCliente());

		if (StringUtils.isNotBlank(eliminaPedidosFacturadosForm.getCodigoRegion())) {
			criteria.put("codigoRegion", eliminaPedidosFacturadosForm.getCodigoRegion());
		}

		if (StringUtils.isNotBlank(eliminaPedidosFacturadosForm.getCodigoZona())
				&& !Constants.OPCION_TODOS.equals(eliminaPedidosFacturadosForm.getCodigoZona())) {
			criteria.put("codigoZona", eliminaPedidosFacturadosForm.getCodigoZona());
		}

		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList();
		Long longitudPais = pais.getLongitudCodigoCliente();
		String codigoCliente = eliminaPedidosFacturadosForm.getCodigoCliente();
		List listaClientes = this.stoLevantamientoErroresClientesList; // grilla del archivo

		Map map = new HashMap();
		// SI cargo consultoras por el archivo
		if (listaClientes != null) 
		{
			for (int i = 0; i < listaClientes.size(); i++) 
			{
				map = (Map) listaClientes.get(i);
				codigoCliente = (String) map.get("codigoCliente");

				try {
					clienteList.add(codigoCliente);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} else {
			// Si es cargado por la caja de texto
			if (codigoCliente != null && codigoCliente.length() > 0)
				arrlistaClientes = codigoCliente.split(",+");
			else
				clienteList = null;
			for (int i = 0; i < arrlistaClientes.length; i++) {
				try {
					clienteList.add(StringUtils.leftPad(arrlistaClientes[i], longitudPais.intValue(), '0'));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		if (clienteList != null)
			criteria.put("clienteList", clienteList);
		/*-------------------------*/

		log.debug("map: " + criteria.toString());

		List list = service.getPedidosAEliminarByCriteria(criteria);

		this.pedidosFacturadosAEliminarList = list;	
		if(list.size()>0)
			this.listaTablaModel = new DataTableModel(this.pedidosFacturadosAEliminarList);
		else
			this.addWarn("", this.getResourceMessage("mantenimientoRUVDocumentosContablesForm.mensajeBusqueda.sinResultados"));		
	}
	
	 /**
     * Metodo que elimina un pedido digitado
     * @param event
     * @return
     * @throws Exception
     */
	public void eliminar(ActionEvent event) throws Exception
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminar' method");
		}

        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)
        		getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        ProcesoOCREliminaPedidosDigitadosForm f = (ProcesoOCREliminaPedidosDigitadosForm) this.formBusqueda;

        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

        if (this.columnasSeleccionadas!= null && this.columnasSeleccionadas.size() > 0)
        {
	        Map criteria = new HashMap();
	        String[] seleccionados = new String[this.columnasSeleccionadas.size()];
	        for (int i = 0; i <this.columnasSeleccionadas.size(); i++) 
	        {
	        	Map aux = (Map)this.columnasSeleccionadas.get(i);
	        	seleccionados[i] = aux.get("codigoPais").toString()+"-"+aux.get("codigoPeriodo").toString()+"-"+
	        						aux.get("codigoCliente").toString()+"-"+aux.get("numeroLote").toString();				
			}
	        f.setSelectedItems(seleccionados);	        
	        criteria.put("lista",f.getSelectedItems());
	        criteria.put("codigoUsuario",usuario.getLogin());
	
	        //eliminar pedido
	        service.eliminarPedidoDigitado(criteria);
        }
        
        this.pedidosFacturadosAEliminarList = null;		
		this.listaTablaModel = new DataTableModel(this.pedidosFacturadosAEliminarList);
        this.addInfo("", this.getResourceMessage("eliminacion.updated"));
	}
	
	public void loadfile(FileUploadEvent event) throws Exception
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}

		ProcesoOCREliminaPedidosDigitadosForm f = (ProcesoOCREliminaPedidosDigitadosForm) this.formBusqueda;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService)
				getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
		List listaClientes = new ArrayList();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setClienteFile(event.getFile());
		UploadedFile archivo = f.getClienteFile();
		Map criteria = new HashMap();
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Long longitudPais=pais.getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		int cont = 0;
		int numRegistros = 0;

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			codigoConsultora = StringUtils.leftPad(linea.trim(), longitudPais.intValue(), '0');
			criteria.put("codigoConsultora",codigoConsultora);
			LabelValue bean = new LabelValue(codigoConsultora, service.getCodigoConsultora(criteria));
			listaClientes.add(bean);

			if(bean.getValue() == null){
				cont++;
			}

			numRegistros++;
		}

		//Se inserta en la tabla temporal
		String oidCarga = service.getOidCargaCliente();
		criteria.put("oid", oidCarga);
		service.insertaClienteFile(listaClientes, criteria);

		//Se obtiene la lista de la tabla temporal
		List list = new ArrayList();
		list = service.getCargaClienteList(criteria);

		f.setCodigosErradosFile("");
		f.setCodigoRegion(f.getCodigoRegion());
		f.setCodigoZona(f.getCodigoZona());

		if(cont != 0)
			f.setCodigosErradosFile("Existe(n) "+cont+" codigo(s) errado(s)");

		criteria.put("numRegistros",  numRegistros);
		List list2 = new ArrayList();
		list2 = service.getResumenCargaClienteList(criteria);

		this.stoLevantamientoErroresClientesList = null;
		this.stoLevantamientoErroresClientesList = list;
		this.stoResumenClientesList = list2;
	}

	@Override
	public String setValidarConfirmar(String accion) 
	{
		String mensaje = null;
		
		if(accion.equalsIgnoreCase("ELIMINAR"))
		{
			if(this.columnasSeleccionadas.size() == 0)
				mensaje = this.getResourceMessage("errors.select.items");
		}
		
		return mensaje;
	}
	
	public List getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getPedidosFacturadosAEliminarList() {
		return pedidosFacturadosAEliminarList;
	}

	public void setPedidosFacturadosAEliminarList(
			List pedidosFacturadosAEliminarList) {
		this.pedidosFacturadosAEliminarList = pedidosFacturadosAEliminarList;
	}

	public List getStoLevantamientoErroresClientesList() {
		return stoLevantamientoErroresClientesList;
	}

	public void setStoLevantamientoErroresClientesList(
			List stoLevantamientoErroresClientesList) {
		this.stoLevantamientoErroresClientesList = stoLevantamientoErroresClientesList;
	}

	public List getStoResumenClientesList() {
		return stoResumenClientesList;
	}

	public void setStoResumenClientesList(List stoResumenClientesList) {
		this.stoResumenClientesList = stoResumenClientesList;
	}

	public DataTableModel getListaTablaModel() {
		return listaTablaModel;
	}

	public void setListaTablaModel(DataTableModel listaTablaModel) {
		this.listaTablaModel = listaTablaModel;
	}

	public List getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	public void setColumnasSeleccionadas(List columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}
	

}
