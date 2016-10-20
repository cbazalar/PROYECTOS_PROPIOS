package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarPagosBancariosMasivosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCargarArchivosBancoForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class ProcesoCCCCargarArchivosBancoAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5329755284174242845L;
	
	private List siccCuentaCorrienteList;
	private LabelValue[] cccTipoOrigenList = {};
	private String attachment= "";
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {

		ProcesoCCCCargarArchivosBancoForm p = new ProcesoCCCCargarArchivosBancoForm();
		return p;
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
			log.debug("Entering 'view' method");
		}	
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();			
		
		this.mostrarBotonExecute = false;
		//Map para almacenar los parametros
		Map criteria = new HashMap();
																
		//Obteniedo el listado de las Cuentas Corrientes Bancarias	        		
		criteria.put("codigoPais", pais.getCodigo());
        ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
        this.siccCuentaCorrienteList = serviceCCC.getCuentasCorrientesBancariasList(criteria);
		
		ProcesoCCCCargarArchivosBancoForm f = (ProcesoCCCCargarArchivosBancoForm) this.formProceso;
		
		f.setFlagMostrarErrores(false);
		f.setCodigoPais(pais.getCodigo());
								
		if (log.isDebugEnabled()) {
			log.debug("JFA: Finalizando 'view' method");
		}						
	
	}
	
	public void procesar(FileUploadEvent event) throws Exception
	{
		
		ProcesoCCCCargarPagosBancariosMasivosService service = (ProcesoCCCCargarPagosBancariosMasivosService) getBean("spusicc.procesoCCCCargarPagosBancariosMasivosService");
		ProcesoCCCCargarArchivosBancoForm f = (ProcesoCCCCargarArchivosBancoForm) this.formProceso;
		f.setArchivo(event.getFile());
		
		int codClieIni = 0;
		int codClieFin=0;
		int fecPagoIni=0;
		int fecPagoFin=0;
		int impPagoIni=0;
		int impPagoFin=0;
		String fmtFecha = "";
		int factor =1;
		int canDeci = 0;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codBanco", f.getCodigoBanco());
		criteria.put("oidTipoOrigen", f.getTipoOrigen());
		
		List paramList = service.getParamCargaArchivosBanco(criteria);
		
		if (paramList != null && paramList.size() > 0){
			
			codClieIni = Integer.parseInt(((Map)paramList.get(0)).get("codClieIni").toString());			
			codClieFin = Integer.parseInt(((Map)paramList.get(0)).get("codClieFin").toString());
			fecPagoIni = Integer.parseInt(((Map)paramList.get(0)).get("fecPagoIni").toString());
			fecPagoFin = Integer.parseInt(((Map)paramList.get(0)).get("fecPagoFin").toString());
			impPagoIni = Integer.parseInt(((Map)paramList.get(0)).get("impPagoIni").toString());
			impPagoFin = Integer.parseInt(((Map)paramList.get(0)).get("impPagoFin").toString());
			fmtFecha = (String)((Map)paramList.get(0)).get("fmtFecha");
			canDeci = Integer.parseInt(((Map)paramList.get(0)).get("cantDecimales").toString());
							
			service.deleteTablaCargaArchivosBanco();
					
			UploadedFile archivo = f.getArchivo();
			this.setAttachment(f.getArchivo().getFileName());
			
			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			//Long longitudCliente=((Pais)getPais(request.getSession())).getLongitudCodigoCliente();
			
			String linea = "";
			String codigoConsultora = "";
			int numRegistros = 0;
			
			for (int i=0; i<canDeci; i++)
				factor=factor*10;
					
			while (true) {
				linea = br.readLine();
				if (linea == null)
					break;
	
				/*codigoConsultora = StringUtils.leftPad(linea.trim(), longitudPais.intValue(), '0');
				criteria.put("codigoConsultora",codigoConsultora);
				LabelValue bean = new LabelValue(codigoConsultora, service.getCodigoConsultora(criteria));
				listaClientes.add(bean);*/
				Map datos = new HashMap();
				
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(fmtFecha);
				String strFecha = linea.substring(fecPagoIni, fecPagoFin);
				Date fecha = null;
				try{
					fecha =  formatoDelTexto.parse(strFecha);
				}
				catch(Exception e) {
				}
				
				SimpleDateFormat formatoFechaFinal = new SimpleDateFormat("dd/MM/yyyy");
				
				String fechaFinal = null;
				try{
					fechaFinal =  formatoFechaFinal.format(fecha);
				}
				catch(Exception e) {
				}
					
				Double importe = Double.parseDouble(linea.substring(impPagoIni, impPagoFin).toString())/factor;
				
				datos.put("codigoCliente", linea.substring(codClieIni, codClieFin));
				datos.put("codBanco", f.getCodigoBanco());
				datos.put("fechaPago", fechaFinal.toString());
				datos.put("importePago", importe);
				datos.put("numRegistros",numRegistros);
				
				service.insertCargaArchivosBanco(datos);
	
				numRegistros++;
			}
			
			service.executeValidarCargaArchivosBanco();
			
			String msje = getResourceMessage("procesoCCCCargarArchivosBancoAction.msg.cargaOk");
			addInfo("Mensaje: ", msje);
		
		}
		else{
			String msje = getResourceMessage("procesoCCCCargarArchivosBancoAction.msg.cargaFaltaParam");
			addInfo("Error: ", msje);
		}
		
	}
	
	public void muestraTipoOrigen(ValueChangeEvent val){
	         
			ProcesoCCCCargarArchivosBancoForm p = (ProcesoCCCCargarArchivosBancoForm) this.formProceso;
			
			String tipoOrigen = (String)val.getNewValue();

			if(!tipoOrigen.equals(null))
			{
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.cccTipoOrigenList = ajax.getTipoOrigenBanco(tipoOrigen);
			
			}
			else
			{
				this.cccTipoOrigenList = null;
			}
			
	    }
	
	public LabelValue[] getCccTipoOrigenList() {
		return cccTipoOrigenList;
	}

	public void setCccTipoOrigenList(LabelValue[] cccTipoOrigenList) {
		this.cccTipoOrigenList = cccTipoOrigenList;
	}

	public List getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	public void setSiccCuentaCorrienteList(List siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

}
