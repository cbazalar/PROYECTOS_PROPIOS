/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidUploadException;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDCargarDemandaAnticipadaService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPEDCargarDemandaAnticipadaForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ProcesoPEDCargarDemandaAnticipadaAction extends BaseProcesoAbstractAction{

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPEDCargarDemandaAnticipadaForm form = new ProcesoPEDCargarDemandaAnticipadaForm ();
			return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
            log.debug("Entering 'edit' method");
        }

		ProcesoPEDCargarDemandaAnticipadaForm viewForm = (ProcesoPEDCargarDemandaAnticipadaForm)this.formProceso;
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
						
		Map criteria = new HashMap();	
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);		
		criteria.put("estadoCampanha",Constants.NUMERO_CERO);
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO);	
		
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);        
		viewForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		this.mostrarBotonBuscar = false;
		this.mostrarBotonExecute = false;
		//return mapping.findForward("view");
		
	}
	
	
	public void loadfile(FileUploadEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}		
		try {
			
			String mensajes = null;			
	        ProcesoPEDCargarDemandaAnticipadaService service = (ProcesoPEDCargarDemandaAnticipadaService)getBean("spusicc.procesoPEDCargarDemandaAnticipadaService");
			ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
			ProcesoPEDCargarDemandaAnticipadaForm f = (ProcesoPEDCargarDemandaAnticipadaForm)this.formProceso;
			f.setClienteFile(event.getFile());
			String directorioTemp = serviceUnidad.obtenerPathUpload(f.getCodigoPais());			
			String archivoProcesar = uploadArchivo(f, directorioTemp);						
			int numeroRegistros = service.executeProcesarArchivo(directorioTemp, archivoProcesar, f.getCodigoPais(), f.getCodigoPeriodo());
			mensajes = "procesoPEDCargarDemandaAnticipadaForm.exito";
			this.addInfo("Info", this.getResourceMessage(mensajes, new Object[] {numeroRegistros}));

			//return mapping.findForward("view");
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}			
	}
	
	private String uploadArchivo(ProcesoPEDCargarDemandaAnticipadaForm form, String directorioTemp) throws Exception{

		String fileName = null;
		UploadedFile file = form.getClienteFile();
		OutputStream os = null;		
		
		try{
			
			// verificamos que el archivo adjuntado sea del formato indicado
			if(!file.getFileName().toLowerCase().endsWith(".xlsx")){
				throw new InvalidUploadException(file.getFileName(),"extension");
			}else{
				// modificamos el nombre del archivo y lo concatenamos con la fecha y hora actual para que sea unico
				String[] arrayName = file.getFileName().split(".xlsx");
				fileName = arrayName[0] + " " + DateUtil.getDateTimeNow("yyyyMMdd_HHmmss",
				new Date(System.currentTimeMillis())) + ".xlsx";
			}
			
			// Leemos el stream de entrada
			InputStream is = file.getInputstream();
			
			// Abrimos el stream de escritura, ubicacion al cual se grabara el archivo
			os = new FileOutputStream(new File(directorioTemp, fileName));
			
			// Grabamos cada 1024 bytes
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			
			os.close();
			
			return fileName;
		
		}catch(Exception e){
			
			if(os != null){
				try {
					os.close();
				} catch (IOException e1) {}
			}
			
			if(e instanceof InvalidUploadException){
				throw e;
			}else{
				throw new InvalidUploadException(file.getFileName(),"error");
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = null;
		ProcesoPEDCargarDemandaAnticipadaForm viewForm = (ProcesoPEDCargarDemandaAnticipadaForm)this.formProceso;
		if(accion.equalsIgnoreCase("CARGAR_ARCHIVO"))
		{
			if(viewForm.getClienteFile()==null)
			{
				mensaje = "Debe ingresar un archivo con extension .xls o .xlsx";
				
			}else {
				this.mostrarPanelAdicionalProceso= true;
			}
		
		}
		return mensaje;
	}
	
	

}
