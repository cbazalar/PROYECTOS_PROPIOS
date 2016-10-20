/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelArchivoDesVentas;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DesarrolladoraVenta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDCargaArchivoDesarrolladoraVentasService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPEDCargaArchivoDesarrolladoraVentasForm;

/**
 * @author Sigcomt
 *
 */

@ManagedBean
@SessionScoped
public class ProcesoPEDCargaArchivoDesarrolladoraVentasAction extends BaseProcesoAbstractAction{
	
	private List pedCargaClienteResuList = new ArrayList();
	private List pedCargaClienteList = new ArrayList();

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPEDCargaArchivoDesarrolladoraVentasForm form = new ProcesoPEDCargaArchivoDesarrolladoraVentasForm();
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
            	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			
		ProcesoPEDCargaArchivoDesarrolladoraVentasForm viewForm = (ProcesoPEDCargaArchivoDesarrolladoraVentasForm)this.formProceso;

		this.mostrarBotonExecute = false;
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);		
		criteria.put("estadoCampanha",Constants.NUMERO_CERO);
		criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO);		
		
		PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
        
		viewForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
//		request.getSession().removeAttribute(Constants.PED_CARGA_CLIENTE_LIST);
//		request.getSession().setAttribute(Constants.PED_CARGA_CLIENTE_LIST,new ArrayList());
//		
//        return mapping.findForward("view");  
		
	}
	
	
	public void loadfile(FileUploadEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}
		
		try {
		ProcesoPEDCargaArchivoDesarrolladoraVentasForm f = (ProcesoPEDCargaArchivoDesarrolladoraVentasForm)this.formProceso;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService)getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		GenericoService serviceGen = (GenericoService) getBean("genericoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.mostrarPanelAdicionalProceso = true;
		//obtener el archivo 
		f.setClienteFile(event.getFile());
		//ruta donde se caragaran los archivos que carga el usuario
		String rutaHistorica = serviceGen.getParametroPais(f.getCodigoPais(), Constants.CED_CODIGO_SISTEMA, Constants.CED_CODIGO_RUTA_HISTORICA);
		log.debug("rutaHistorica "+rutaHistorica);
		List listaClientes = new ArrayList();
		UploadedFile archivo = f.getClienteFile();
		
		f.setNombreArchivo(archivo.getFileName());
		
		/*Copiando archivo al servidor*/
		InputStream is = archivo.getInputstream();
		File srcFile = new File(archivo.getFileName());		
		OutputStream os = new FileOutputStream(srcFile);			
		int count;  
		byte buf[] = new byte[4096];		
		while ((count = is.read(buf)) > -1) {  
			os.write(buf, 0, count);    
		}  
		os.close();		
		
		//String path = StringUt  /ssicc/temp/
		File destFile = new File(rutaHistorica, archivo.getFileName());
		FileUtils.copyFile(srcFile, destFile);
		
		//Evaluando los campos del archivo de texto		
		Map criteria = new HashMap();
		InputStream isRead = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(isRead));
		Long longitudPais=pais.getLongitudCodigoCliente();
		String linea = "";
		String codigoConsultora = "";
		String monto = "";
		int cont = 0;
		int numRegistros = 0;
		
		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;

			String[] registros = linea.split(",");
			
			codigoConsultora = registros[0];
			monto = registros[1];
			
			LabelArchivoDesVentas beanVentas = new LabelArchivoDesVentas();
			
			try{				
				beanVentas.setCodigoCliente(codigoConsultora);
				beanVentas.setMonto(monto);
				
				//Validacion de Monto
				float numericoMonto = Float.parseFloat(monto);

				//Validacion codigo de consultora
				codigoConsultora = StringUtils.leftPad(codigoConsultora, longitudPais.intValue(), '0');
				criteria.put("codigoConsultora",codigoConsultora);			
				LabelValue bean = new LabelValue(codigoConsultora, service.getCodigoConsultora(criteria));
								
				if(bean.getValue() == null){
					beanVentas.setIndicadorValido("0");					
					cont++;
				}else{
					beanVentas.setIndicadorValido("1");
				}
			
			}catch(NumberFormatException e){
				cont++;
				beanVentas.setIndicadorValido("0");
			}
			
			listaClientes.add(beanVentas);
			
			//Validacion de monto
			numRegistros++;
			
		}
		
		f.setCodigosErradosFile("");

		this.pedCargaClienteResuList = null;

		if(cont != 0){
			f.setCodigosErradosFile("Existe(n) "+cont+" registro(s) errado(s)");
			f.setErrados(cont);
		}else{
			this.pedCargaClienteResuList = listaClientes;
		}
		
		this.pedCargaClienteList = null;
		this.pedCargaClienteList = listaClientes;			
		//return mapping.findForward("view");
	
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	public void save (ActionEvent event) {
		
		if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
		
		try {
			String mensaje = null;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			ProcesoPEDCargaArchivoDesarrolladoraVentasForm saveForm = (ProcesoPEDCargaArchivoDesarrolladoraVentasForm)this.formProceso;
	        
	        ProcesoPEDCargaArchivoDesarrolladoraVentasService service = (ProcesoPEDCargaArchivoDesarrolladoraVentasService)getBean("spusicc.procesoPEDCargaArchivoDesarrolladoraVentasService");
	    	
	    	List listaClientes =this.pedCargaClienteList;
	    	log.debug("pedClientesList : "+listaClientes.size());	    	    	
	    	int errados = saveForm.getErrados();
	    	Map criteria = new HashMap();
	    	List listResult = new ArrayList();

	    	try {
	   			
	   			if(errados==0){   				

	   				for (int i = 0; i < listaClientes.size(); i++) {			
	   					
	   					LabelArchivoDesVentas labelArchivoDesVentas = (LabelArchivoDesVentas)listaClientes.get(i);
	   					criteria.put("codigoPeriodo", saveForm.getCodigoPeriodo());
	   					criteria.put("codigoCliente", labelArchivoDesVentas.getCodigoCliente());
	   					
	   					listResult = service.getDesarrolladoraVenta(criteria);
	   					
	   					if(listResult!=null && listResult.size()>0){
	   						
	   						DesarrolladoraVenta desarrolladoraVenta = (DesarrolladoraVenta)listResult.get(0);

	   						service.removeDesarrolladoraVenta(criteria);
	   						
	   						service.insertDesarrolladoraVentaHistorico(desarrolladoraVenta, usuario);
	   						
	   						desarrolladoraVenta.setCodigoPeriodo(saveForm.getCodigoPeriodo());  						
	   						desarrolladoraVenta.setMonto(labelArchivoDesVentas.getMonto());
	   						desarrolladoraVenta.setNombreArchivo(saveForm.getNombreArchivo());
	   						desarrolladoraVenta.setUsuarioCreacion(usuario.getLogin());
	   						
	   						Date date = new Date();
	   						desarrolladoraVenta.setFechaCreacion(new Timestamp(date.getTime()));
	   						
	   						service.insertDesarrolladoraVenta(desarrolladoraVenta, usuario);
	   						
	   					}else{
	   						
	   						DesarrolladoraVenta desarrolladoraVenta = new DesarrolladoraVenta();
	   						
	   						desarrolladoraVenta.setCodigoPeriodo(saveForm.getCodigoPeriodo());  						
	   						desarrolladoraVenta.setCodigoCliente(labelArchivoDesVentas.getCodigoCliente());
	   						desarrolladoraVenta.setMonto(labelArchivoDesVentas.getMonto());
	   						desarrolladoraVenta.setNombreArchivo(saveForm.getNombreArchivo());
	   						desarrolladoraVenta.setUsuarioCreacion(usuario.getLogin());
	   						
	   						Date date = new Date();
	   						desarrolladoraVenta.setFechaCreacion(new Timestamp(date.getTime()));
	   						
	   						service.insertDesarrolladoraVenta(desarrolladoraVenta, usuario);
	   						
	   					}		
	   					
	   				}
	   				
	   				mensaje = "procesoPEDCargaArchivoDesarrolladoraVentasForm.exito";
	   				this.addInfo("Inform", this.getResourceMessage(mensaje));	   				
	   			} 			   				

			}	catch (InvalidIdentifierException iie) {
	            String codigo = iie.getIdentifier().toString();
	            mensaje = "errors.invalid.id";
	            this.addError("Error: ", this.getResourceMessage(mensaje, new Object[]{codigo}));
	            //return mapping.getInputForward();
	        }catch (InvalidDescriptionException ide) {
	            String descripcion = ide.getDescription();
	            mensaje = "errors.invalid.description";
	            this.addError("Error: ", this.getResourceMessage(mensaje, new Object[]{descripcion}));
	            //return mapping.getInputForward();
	        }
	        
	    	this.pedCargaClienteList = null;
	    	this.pedCargaClienteResuList = null;
	    	this.mostrarPanelAdicionalProceso = false;
	        // Regresamos a la página de búsqueda
	        //return mapping.findForward("view");
	    
			
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}	
	}

	/**
	 * @return the pedCargaClienteResuList
	 */
	public List getPedCargaClienteResuList() {
		return pedCargaClienteResuList;
	}

	/**
	 * @param pedCargaClienteResuList the pedCargaClienteResuList to set
	 */
	public void setPedCargaClienteResuList(List pedCargaClienteResuList) {
		this.pedCargaClienteResuList = pedCargaClienteResuList;
	}

	/**
	 * @return the pedCargaClienteList
	 */
	public List getPedCargaClienteList() {
		return pedCargaClienteList;
	}

	/**
	 * @param pedCargaClienteList the pedCargaClienteList to set
	 */
	public void setPedCargaClienteList(List pedCargaClienteList) {
		this.pedCargaClienteList = pedCargaClienteList;
	}

}
