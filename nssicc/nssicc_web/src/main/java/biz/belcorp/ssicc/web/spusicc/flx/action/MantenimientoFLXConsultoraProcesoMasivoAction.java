/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.flx.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipago;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoPK;
import biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.flx.form.MantenimientoFLXConsultoraProcesoMasivoForm;

/**
 * @author fochoa
 *
 */

@ManagedBean
@SessionScoped
public class MantenimientoFLXConsultoraProcesoMasivoAction extends BasePopupAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8903895326304485943L;
	
	private String mantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado ;
	private List flxConsultoraCargaMasivaList = new ArrayList();
	private DataTableModel cargaMasivaModel = new DataTableModel();
	private String flxConsultoraCargaMasivaErroneos ;
	private String attachment ;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFLXConsultoraProcesoMasivoForm form = new MantenimientoFLXConsultoraProcesoMasivoForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled())
			log.debug("setViewAtributes");
		
		MantenimientoFLXConsultoraProcesoMasivoForm f = (MantenimientoFLXConsultoraProcesoMasivoForm) this.formBusqueda;		
		f.setTipoProceso(Constants.FLX_CODIGO_ACCION_DESACTIVAR);	
		this.mantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado = Constants.NUMERO_CERO; 
		this.flxConsultoraCargaMasivaList = new ArrayList(); 
		this.cargaMasivaModel = new DataTableModel(this.flxConsultoraCargaMasivaList);
		this.flxConsultoraCargaMasivaErroneos = ""; 
		this.attachment = "";
	}
	
	/**
	 * Carga el archivo
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		MantenimientoFLXConsultoraProcesoMasivoForm f = (MantenimientoFLXConsultoraProcesoMasivoForm) this.formBusqueda;
		ExcelUtil excelUtil = null;
		List listaConsultoras = new ArrayList();
		int erroneos = 0;
		
		try{
			MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");			
			MantenimientoOCRPedidoControlFacturacionService servicePeriodo = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			
			
			UploadedFile archivo = event.getFile();
			
			excelUtil = new ExcelUtil(archivo.getInputstream());
			excelUtil.initSheet(0);
						
			
			while(excelUtil.hasNext()){
				Map mapRow = excelUtil.next();
				String codigoConsultora = ((String)mapRow.get("0")).substring(0,((String)mapRow.get("0")).length());
				String campanyaFacturacion = ((String)mapRow.get("1")).substring(0,((String)mapRow.get("1")).length());				
				String valido = service.validarCodigoConsultora(codigoConsultora);
				
				ConsultoraFlexipago c = new ConsultoraFlexipago();
								
				if(codigoConsultora.endsWith(".0")){
					codigoConsultora = codigoConsultora.substring(0, codigoConsultora.length()-2);
				}
				
				if(campanyaFacturacion.endsWith(".0")){
					campanyaFacturacion = campanyaFacturacion.substring(0, campanyaFacturacion.length()-2);
				}
				
				c.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
				c.setCodigoCliente(codigoConsultora);
				c.setCodigoCampanyaFacturacion(campanyaFacturacion);
				c.setFlagActivo(valido);
				
				Map criteria = new HashMap();
				
				criteria.put("codigoCampanyaFacturacion", c.getCodigoCampanyaFacturacion());
				criteria.put("codigoCliente", c.getCodigoCliente());
				
				ConsultoraFlexipago consultora = service.getConsultora(new ConsultoraFlexipagoPK(c.getCodigoPais(), c.getCodigoCliente(), c.getCodigoCampanyaFacturacion()));
						
				// Si se desactiva cuando ya paso pedido y/o cerro su region, se desactiva a partir de la campaña siguiente
				if( StringUtils.equals(consultora.getFlagActivo(), Constants.SI) && (service.getPasoPedido(criteria).equals("1") || service.getCerroRegion(criteria).equals("1"))){
					Map params = new HashMap();
					params.put("codigoPeriodo", c.getCodigoCampanyaFacturacion());
					params.put("numCampanhas",new BigDecimal(1)); // La siguiente campaña
					
					c.setCodigoCampanyaFacturacion(servicePeriodo.getPedidosNSiguienteCampanha(params));
				}
				
				listaConsultoras.add(c);
				
				if(StringUtils.equals(valido, Constants.NO))
					erroneos++;
			}
			excelUtil.cerrar();
			
			this.attachment = event.getFile().getFileName();
			
			this.flxConsultoraCargaMasivaList = listaConsultoras;
			this.cargaMasivaModel = new DataTableModel(this.flxConsultoraCargaMasivaList);
			if(erroneos > 0)
				this.flxConsultoraCargaMasivaErroneos = Integer.toString(erroneos);
			
			if(log.isDebugEnabled())
				log.debug("Tamaño de Lista: " + listaConsultoras.size());
			
		}
		catch(Exception ex){
			if(excelUtil != null)
				excelUtil.cerrar();
			this.addError("Error:", this.getResourceMessage("mantenimientoFLXConsultoraForm.file.load.error"));
		}
		
	}
	
	/**
	 * Procesa el archivo
	 * 
	 * @param e
	 */
	public void processFile(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("processFile");
		}
		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		String mensaje = new String();
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		MantenimientoFLXConsultoraProcesoMasivoForm f = (MantenimientoFLXConsultoraProcesoMasivoForm) this.formBusqueda;
		
		if(this.getFlxConsultoraCargaMasivaList().size()==0){
			this.addError("Error", this.getResourceMessage("mantenimientoFLXConsultoraProcesoMasivoForm.error.archivo.vacio"));
			return;
		}
		
		try
		{
			
			List listaConsultoras = this.flxConsultoraCargaMasivaList;
			
			if(listaConsultoras != null && listaConsultoras.size() > 0)
				service.updateMasivoConsultoras(listaConsultoras, f.getTipoProceso(), usuario);
			
			mensaje = this.getResourceMessage("mantenimientoFLXConsultoraForm.file.process.ok");
			this.attachment = "";
			
			this.flxConsultoraCargaMasivaList = new ArrayList();
			this.cargaMasivaModel = new DataTableModel(this.flxConsultoraCargaMasivaList);
			this.flxConsultoraCargaMasivaErroneos = "";
			this.mantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado = Constants.NUMERO_UNO;
			
//			Sale un mensaje, el cual al dar en aceptar me retornara a la pantalla inicial
			this.setMensajeAlertaDefaultAction(mensaje);
			RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
			String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
			this.getRequestContext().execute(ventana);
		
		}
		catch(Exception ex){
            mensaje = this.getResourceMessage("mantenimientoFLXConsultoraForm.file.process.error");
            this.addError("Error:", mensaje);            
		}
		
	}	
	
	/**
	 * Limpia la carga de archivo
	 * 
	 * @param event
	 */
	public void clearDataFile(ActionEvent event){

		if(log.isDebugEnabled())
			log.debug("cleardatafile");
		
		this.flxConsultoraCargaMasivaErroneos = "";
		this.flxConsultoraCargaMasivaList = new ArrayList();
		this.cargaMasivaModel = new DataTableModel(this.flxConsultoraCargaMasivaList);
		this.attachment = "";
	
	}

	/**
	 * @return the mantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado
	 */
	public String getMantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado() {
		return mantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado;
	}

	/**
	 * @param mantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado the mantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado to set
	 */
	public void setMantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado(
			String mantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado) {
		this.mantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado = mantenimientoFLXConsultoraProcesoMasivoForm_estadoProcesado;
	}

	/**
	 * @return the flxConsultoraCargaMasivaList
	 */
	public List getFlxConsultoraCargaMasivaList() {
		return flxConsultoraCargaMasivaList;
	}

	/**
	 * @param flxConsultoraCargaMasivaList the flxConsultoraCargaMasivaList to set
	 */
	public void setFlxConsultoraCargaMasivaList(List flxConsultoraCargaMasivaList) {
		this.flxConsultoraCargaMasivaList = flxConsultoraCargaMasivaList;
	}

	/**
	 * @return the cargaMasivaModel
	 */
	public DataTableModel getCargaMasivaModel() {
		return cargaMasivaModel;
	}

	/**
	 * @param cargaMasivaModel the cargaMasivaModel to set
	 */
	public void setCargaMasivaModel(DataTableModel cargaMasivaModel) {
		this.cargaMasivaModel = cargaMasivaModel;
	}

	/**
	 * @return the flxConsultoraCargaMasivaErroneos
	 */
	public String getFlxConsultoraCargaMasivaErroneos() {
		return flxConsultoraCargaMasivaErroneos;
	}

	/**
	 * @param flxConsultoraCargaMasivaErroneos the flxConsultoraCargaMasivaErroneos to set
	 */
	public void setFlxConsultoraCargaMasivaErroneos(
			String flxConsultoraCargaMasivaErroneos) {
		this.flxConsultoraCargaMasivaErroneos = flxConsultoraCargaMasivaErroneos;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	
}
