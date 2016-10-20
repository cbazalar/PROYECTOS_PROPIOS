package biz.belcorp.ssicc.web.sisicc.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLETEnviarPagosForm;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */

@ManagedBean
@SessionScoped
public class InterfazLETEnviarPagosAction extends BaseInterfazAbstractAction{
	
	private static final long serialVersionUID = 1L;
	
	private List siccRegionList = new ArrayList();
	private boolean muestraFechaReenvio;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazLETEnviarPagosForm f = new  InterfazLETEnviarPagosForm();
		
		return f;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		InterfazLETEnviarPagosForm f = (InterfazLETEnviarPagosForm) formInterfaz;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);
		
		f.setFechaReenvioDate(DateUtil.convertStringToDate(DateUtil.getDatePattern(), DateUtil.convertDateToString(new Date())));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActual = sdf.format(f.getFechaReenvioDate());
		
		f.setFechaReenvio(fechaActual);
		f.setTipoEnvio("N");
		setMuestraFechaReenvio(false);
		
		this.esInterfazSalida = true;
		this.mostrarMensajeInterfazOK = false;
	}
	
	public void executeInterfaz(ActionEvent actionEvent) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'executeInterfaz' method");
			}
			
			Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
	        Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
	        super.executeInterfaz(actionEvent);
			
			//código para el zipeado de los archivos
			String[] archivos = new String[4];			 
			archivos[0] = (String)this.getParamfiltros().get("nombreArchivoCabecera").toString()+"."+(String)this.getParamfiltros().get("extension");
			archivos[1] = (String)this.getParamfiltros().get("nombreArchivoCabecera1").toString()+"."+(String)this.getParamfiltros().get("extension");
			archivos[2] = (String)this.getParamfiltros().get("nombreArchivoDetalle").toString()+"."+(String)this.getParamfiltros().get("extension");
			archivos[3] = (String)this.getParamfiltros().get("nombreArchivoDetalle1").toString()+"."+(String)this.getParamfiltros().get("extension");
			
			if (log.isDebugEnabled()) {
				log.debug("Archivos: "+(String)this.getParamfiltros().get("nombreArchivoCabecera").toString()+"."+(String)this.getParamfiltros().get("extension")
						+","+(String)this.getParamfiltros().get("nombreArchivoCabecera1").toString()+"."+(String)this.getParamfiltros().get("extension")
						+","+(String)this.getParamfiltros().get("nombreArchivoDetalle").toString()+"."+(String)this.getParamfiltros().get("extension")
						+","+(String)this.getParamfiltros().get("nombreArchivoDetalle1").toString()+"."+(String)this.getParamfiltros().get("extension"));
			}
			
			ByteBuffer[] buffers = new ByteBuffer[archivos.length];
			
			for (int i = 0; i < archivos.length; i++) {
				RandomAccessFile rafCabecera = new RandomAccessFile(FileUtil.formatDirectory((String)this.getParamfiltros().get("directorioCabecera")) + archivos[i], "r");
				FileChannel channelCabecera = rafCabecera.getChannel();
				buffers[i] = channelCabecera.map(FileChannel.MapMode.READ_ONLY, 0, rafCabecera.length());
				channelCabecera.close();
				rafCabecera.close();
			}
			
			if (log.isDebugEnabled()) {
				log.debug("Buffers :"+buffers);
				log.debug("Entering 'Uniendo archivos' method");
			}
			
			String nombreArchivoConsolidado = String.format("%s%s.%s", "Dispersion_", (String)this.getParamfiltros().get("numeroLote"), (String)this.getParamfiltros().get("extension"));
			FileOutputStream outFile = new FileOutputStream(FileUtil.formatDirectory((String)this.getParamfiltros().get("directorioCabecera")) + nombreArchivoConsolidado);
			FileChannel out = outFile.getChannel();
			out.write(buffers);
			out.close();
			
			if (log.isDebugEnabled()) {
				log.debug("Nombre archivo consolidado :'"+nombreArchivoConsolidado+"' method");
				log.debug("Ubicacion archivo consolidado :'"+(String)this.getParamfiltros().get("directorioCabecera")+"' method");
				log.debug("Entering 'Borrando archivos' method");
			}
			
			for (int i = 0; i < archivos.length; i++) {
				File fileCabecera = new File(FileUtil.formatDirectory((String)this.getParamfiltros().get("directorioCabecera")) + archivos[i]);
				
				if(fileCabecera.exists()){
					fileCabecera.delete();
				}
			}
			

			if (log.isDebugEnabled()) {
				log.debug("Entering 'Enviando Mail' method");
			}
	         
			BaseMailService mailService = (BaseMailService) this.getBean(this.getMailService());
	        
			//correoOrigen
			//correoDefault
			this.getParamfiltros().put("correosDestinos", (String) this.getParamfiltros().get("correoDefault"));
			//subject
			//fileAttachment	         
			File archivoAtachado = new File((String)this.getParamfiltros().get("directorioCabecera"),nombreArchivoConsolidado);
			
			this.getParamfiltros().put("fileAttachment", archivoAtachado);
			this.getParamfiltros().put("nombreArchivoReporte", nombreArchivoConsolidado);
			this.getParamfiltros().put("bodyTxt", "MailTxtMensajeLibre.vm");
			this.getParamfiltros().put("bodyHtml", "MailTxtMensajeLibre.vm");
			
			String linea="<html> <head> <meta content='text/html; charset=ISO-8859-1' http-equiv='content-type'> <title> Reportes SAPFI</title> </head> <body> <table style='text-align: left; width: 490px; height: 461px;' border='0' cellpadding='0' cellspacing='0'> <tbody> <tr> <td style='height: 172px; width: 256px;'> <font color='#4188b2' face='Arial' size='3'>Se ha realizado el envķo de <strong> Pagos </strong>&nbsp;desde el Sistema SSiCC correspondiente al pais&nbsp;<strong>"+pais.getDescripcion()+"</strong> <strong> </font> </td> </tr> </tbody> </table> <font face='Arial' size='1'><br> <strong> NOTA: Por favor no responda a este mensaje, es generado automįticamente desde una cuenta no monitoreada. </strong> <br> Proceso ejecutado por el Usuario <strong>"+usuario.getLogin()+"</strong>. <br> <br> Se adjunta el archivo correspondiente: </font> <br> <br> <br> </body> </html>";
		        
			this.getParamfiltros().put("body", linea);
			this.getParamfiltros().put("parameterMap", this.getParamfiltros());
	         
			MailParams mailParams = new MailParams();	
			mailParams.setQueryParams(this.getParamfiltros());
	         
			mailService.enviarMail(mailParams);
	         
			if (log.isDebugEnabled()) {
				log.debug("Entering 'Envio exitoso' method");
			}
			
			this.addInfo("Información", this.getResourceMessage("interfaz.concluido"));
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		InterfazLETEnviarPagosForm f = (InterfazLETEnviarPagosForm) formInterfaz;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActual = sdf.format(f.getFechaReenvioDate());
		f.setFechaReenvio(fechaActual);
		
		params = super.prepareParamsBeforeExecute(params, form);
		
		return params;
    }
	
	public void loadFechaReenvio(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadFechaReenvio");
		}
		
		InterfazLETEnviarPagosForm f = (InterfazLETEnviarPagosForm) formInterfaz;
		
		String opcionTipoEnvio = val.getNewValue().toString();
		log.debug("opcionTipoEnvio: -----> " + opcionTipoEnvio);

		if (StringUtils.equals(opcionTipoEnvio, "N")) {
			setMuestraFechaReenvio(false);
		} else if(StringUtils.equals(opcionTipoEnvio, "R")){
			setMuestraFechaReenvio(true);
		}
		
		f.setTipoEnvio(opcionTipoEnvio);
	}

	/**
	 * @return the muestraFechaReenvio
	 */
	public boolean isMuestraFechaReenvio() {
		return muestraFechaReenvio;
	}

	/**
	 * @param muestraFechaReenvio the muestraFechaReenvio to set
	 */
	public void setMuestraFechaReenvio(boolean muestraFechaReenvio) {
		this.muestraFechaReenvio = muestraFechaReenvio;
	}
	
	public String getMailService () {
		String service = "sisicc.mailUtil";		
		
		return service;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
}