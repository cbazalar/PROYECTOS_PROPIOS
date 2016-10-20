package biz.belcorp.ssicc.service.sisicc.ws.impl;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.aco.ws.impl.BaseInterfazHiloAbstractWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazPREService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.ws.InterfazPREWebService;

public class InterfazPREWebServiceImpl extends BaseInterfazHiloAbstractWebService implements InterfazPREWebService 
{
	
	InterfazPREService interfazPREService;
	String estadoTipificacionMatriz;
	
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();
		this.interfazPREService = (InterfazPREService) getWebApplicationContext().getBean("sisicc.interfazPREService");
		
	}

	@Override
	public int ejecutarProceso(String codigoPais, String codigoUsuario, String numeroLote, String codigoPeriodo, String medioVenta) throws RemoteException 
	{
		boolean estado = false;
		int objetoRespuesta = 0;
		SSiCC_Desfasado_InterfazExecutionResult executionResult = null;
		InterfazResult interfazResult = null;
		String mensajeError = "";
		String CODIGO_BATCH = Constants.PRE_CODIGO_PROCESO_BATCH_MATR_PLAN;
		String CODIGO_INTERFAZ = Constants.PRE_CODIGO_INTERFAZ_MATR_PLAN;
		String CODIGO_SISTEMA = Constants.PRE_MATR_PLAN_CODIGO_SISTEMA;

		try {
			Pais pais = this.paisService.getPais(codigoPais);
			if (StringUtils.isBlank(codigoPais)	|| StringUtils.isEmpty(codigoPais)) {
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
		    				
			Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);

			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoUsuario", codigoUsuario);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
			criteria.put("codigoSistema", CODIGO_SISTEMA);
			criteria.put("codigoInterfaz", CODIGO_INTERFAZ);
			criteria.put("descripcion", getDescripcionInterfaz(criteria));
			criteria.put("usuario", usuario);
			criteria.put("numeroLoteArch", numeroLote);	
			
			criteria.put("codigoPeriodo", codigoPeriodo);
			criteria.put("codigoCatalogo", medioVenta);
			
			executionResult = this.executeInterfaz(criteria);

			List list = executionResult.getInterfazResults();
			interfazResult = (InterfazResult) list.get(0);

			if (interfazResult != null) {
				if(StringUtils.equals(this.estadoProceso, Constants.OK)){
					estado = true;
				}else if(StringUtils.equals(this.estadoProceso, Constants.ERROR)){
					objetoRespuesta = 1;
				}else if(StringUtils.equals(this.estadoProceso, Constants.NUMERO_DOS)){
					objetoRespuesta = 2;
				}else if(StringUtils.equals(this.estadoProceso, Constants.NUMERO_TRES)){
					objetoRespuesta = 3;
				}
			}

		} catch (Exception e) {
			objetoRespuesta = 1;
			
			if(StringUtils.equals(this.estadoProceso, Constants.NUMERO_DOS)){
				objetoRespuesta = 2;
			}else if(StringUtils.equals(this.estadoProceso, Constants.NUMERO_TRES)){
				objetoRespuesta = 3;
			}
			
			if(StringUtils.equals(this.estadoTipificacionMatriz, Constants.NUMERO_UNO)){
				objetoRespuesta = 1;
			}
			if(StringUtils.equals(this.estadoTipificacionMatriz, Constants.NUMERO_CUATRO)){
				objetoRespuesta = 4;
			}
			
			return objetoRespuesta;
		} finally {
			System.out.println("Estado del servicio: " + estado);
			if (estado) {
				System.out.println("Se ejecuto el servicio con exito.");
			} else {
				System.out.println("Excepcion en el servicio.");
			}
		}

		return objetoRespuesta;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.aco.ws.impl.BaseInterfazAbstractWebService#beforeExecuteInterfaz(java.util.Map)
	 */
	@Override
	protected void beforeExecuteInterfaz(Map params) {
    
		params.put("tipoValidacion", "1");		
		String[] aux = (String[]) params.get("listaNombresArchivos");
		
		String [] aux1 = aux[0].split("\\_");
		params.put("numeroLoteArch", aux1[1].substring(0, aux1[1].length()-4));
		
		interfazPREService.executeValidacionMatrizPlanit(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.aco.ws.impl.BaseInterfazAbstractWebService#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult)
	 */
	@Override
	protected void afterExecuteInterfaz(Map params, SSiCC_Desfasado_InterfazExecutionResult interfazExecutionResult) throws Exception {
		String[] aux = (String[]) params.get("listaNombresArchivos");
		String nombre = "", errorGeneral = "";		
		String [] aux1 = aux[0].split("\\_");
		boolean errorNoControlado = false;
		
		for (String string : aux) {
			nombre = nombre + string + ";";			
		}
		
		params.put("numeroLoteArch", aux1[1].substring(0, aux1[1].length()-4));
		params.put("tipoValidacion", "2");
		params.put("psnombre", nombre);
		
		try{
			String eliminarArchivos = MapUtils.getString(params, "eliminarArchivos");
			
			if(!StringUtils.equals(eliminarArchivos, Constants.SI)){
				interfazPREService.executeValidacionMatrizPlanit(params);
				
				String hayValidaciones = MapUtils.getString(params, "eliminarArchivos");
				if(StringUtils.equals(hayValidaciones, "HV")){
					this.estadoProceso = Constants.NUMERO_UNO;
					this.estadoTipificacionMatriz = Constants.NUMERO_UNO;
					interfazExecutionResult.setInterfazResults(null);
				}
			}else{
				this.estadoProceso = Constants.NUMERO_CUATRO;
				this.estadoTipificacionMatriz = Constants.NUMERO_CUATRO;
				interfazExecutionResult.setInterfazResults(null);
			}
		}catch(Exception e)
		{
			if(e.getCause()!=null) {
				Throwable tex = e.getCause();							
				if(tex.getCause()!=null) {
					String mensaje = tex.getCause().getMessage();
					
					if(mensaje.indexOf("ORA-02292") >= 0 || mensaje.indexOf("ORA-02291") >= 0 || mensaje.indexOf("ORA-20123") >= 0) {
						this.estadoProceso = Constants.NUMERO_DOS;
					}else if(mensaje.indexOf("ORA-12150") >= 0 || mensaje.indexOf("ORA-12151") >= 0 || mensaje.indexOf("ORA-12152") >= 0 || 
							mensaje.indexOf("ORA-12153") >= 0 || mensaje.indexOf("ORA-12154") >= 0 ) {
						this.estadoProceso = Constants.NUMERO_TRES;
					}else if(mensaje.indexOf("Este lote ya ha sido procesado") >= 0){
						this.estadoProceso = Constants.NUMERO_CUATRO;
					}
				}else{
					this.estadoProceso = Constants.ERROR;
				}
			}else{
				this.estadoProceso = Constants.ERROR;
			}
			
			if(StringUtils.equals(this.estadoProceso, Constants.NUMERO_CUATRO)){
				errorGeneral = "Este lote ya ha sido procesado";
			}else{
				errorGeneral = e.getMessage();
			}
			
			params.put("errorNoControlado", errorGeneral);
			errorNoControlado = true;
			//this.estadoProceso = Constants.ERROR;
			interfazExecutionResult.setInterfazResults(null);
			
			if(StringUtils.equals(this.estadoProceso, Constants.NUMERO_CUATRO)){
				interfazPREService.envioCorreo(params);
			}
		}
		
		interfazPREService.envioCorreo(params);
		
		int cantiError = interfazPREService.verificarErrorMatrizPlanit(params);
		
		if(cantiError == 0 && !errorNoControlado)
		{
			try{
			//interfazPREService.executeInterfazPrCargaMatrizPlan(params);
			}catch (Exception e) {
				errorGeneral = e.getMessage();
				params.put("errorNoControlado", errorGeneral);
			}
		}else if(cantiError > 0){
			this.estadoProceso = Constants.ERROR;
		}
	}
}
