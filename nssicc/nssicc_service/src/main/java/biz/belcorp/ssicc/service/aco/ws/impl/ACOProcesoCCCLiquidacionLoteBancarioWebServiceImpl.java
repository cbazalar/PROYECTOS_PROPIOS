/**
 * 
 */
package biz.belcorp.ssicc.service.aco.ws.impl;

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
import biz.belcorp.ssicc.service.aco.ws.ACOProcesoCCCLiquidacionLoteBancarioWebService;
import biz.belcorp.ssicc.service.aco.ws.beans.ACOWebServiceResponse;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCLiquidacionLoteBancarioService;

/**
 * 
 * TODO Include class description here. 
 * 
 * <p>
 * <a href="ACOProcesoCCCLiquidacionLoteBancarioWebServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */

public class ACOProcesoCCCLiquidacionLoteBancarioWebServiceImpl extends
		BaseProcesoHiloAbstractWebService implements
		ACOProcesoCCCLiquidacionLoteBancarioWebService {

	MantenimientoCCCLiquidacionLoteBancarioService mantenimientoCCCLiquidacionLoteBancarioService;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#onInit()
	 */
	@Override
	protected void onInit() throws ServiceException {
		super.onInit();		
		mantenimientoCCCLiquidacionLoteBancarioService = (MantenimientoCCCLiquidacionLoteBancarioService) getApplicationContext().getBean("spusicc.mantenimientoCCCLiquidacionLoteBancarioService");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.ACOProcesoCCCLiquidacionLoteBancarioWebService#ejecutarProcesoCCCLiquidacionLoteBancario(java.lang.String, java.lang.String)
	 */
	public ACOWebServiceResponse ejecutarProcesoCCCLiquidacionLoteBancario(String codigoPais, String codigoUsuario) throws RemoteException {
		ACOWebServiceResponse objetoRespuesta = new ACOWebServiceResponse();
		boolean estado = false;
		String mensajeError = "";		
		final String CODIGO_BATCH = Constants.CCC_CODIGO_PROCESO_BATCH_LIQUIDACION_LOTE_BANCARIO;
		final String CODIGO_SISTEMA = Constants.CCC_CODIGO_SISTEMA;
		
		if(log.isDebugEnabled()){
			log.debug("ejecutarProcesoCCCLiquidacionLoteBancario");
		}
		try{
			Pais pais = this.paisService.getPais(codigoPais);
			if(StringUtils.isBlank(codigoPais) || StringUtils.isEmpty(codigoPais)){
				mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarPais", null,
				getLocaleIdioma(pais.getCodigoIdiomaIso()));
				throw new Exception(mensajeError);
			}
						
			/**
		     * Valida usuario
		     */
		    if(StringUtils.isBlank(codigoUsuario)|| StringUtils.isEmpty(codigoUsuario)){
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.validarUsuario",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);
		    }

		    Usuario usuario = this.obtenerUsuarioByDefault(codigoUsuario);

			Map criteria = new HashMap();
			
			// Asignamos al codigo del periodo el valor por defecto
	        criteria.put("codigoPais", pais.getCodigo());
		    criteria.put("codigoUsuario", codigoUsuario);
		    criteria.put("usuario", usuario);
			criteria.put("codigoProcesoBatch", CODIGO_BATCH);
	        criteria.put("codigoSistema", CODIGO_SISTEMA);
			criteria.put("estadoLote", Constants.CCC_ESTADO_LOTE_PENDIENTE);

			List resultado = mantenimientoCCCLiquidacionLoteBancarioService.getLotesBancariosList(criteria);

			if(resultado == null || resultado.size() == 0)
			{
		    	mensajeError = getWebApplicationContext().getMessage("procesoACOWebService.msg.mensajeNotExisteRegistros",null,getLocaleIdioma(pais.getCodigoIdiomaIso()));
	        	throw new Exception(mensajeError);				
			}
			
    		this.executeProceso(criteria);
 		    objetoRespuesta.setEjecucionExitosa(true);
	     
		}catch (Exception e) {			
			log.error(e.getMessage());
			objetoRespuesta.setMensaje(e.getMessage());
			objetoRespuesta.setRespuestaWebService(null);			
			objetoRespuesta.setEjecucionExitosa(estado);
		}finally{
			log.debug("Estado del servicio: " + estado);
			if(estado){
				log.info("Se ejecuto el servicio con exito.");					
			}else{
				log.error("Excepcion en el servicio.");				
			}						
		}	
		return objetoRespuesta;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.aco.ws.impl.BaseProcesoHiloAbstractWebService#executeProcess(java.util.Map)
	 */
	@Override
	protected Map executeProcess(Map params) throws Exception {

		List resultado = mantenimientoCCCLiquidacionLoteBancarioService.getLotesBancariosList(params);
				
		Map datos = new HashMap();
		datos.put("codigoPais", MapUtils.getString(params, "codigoPais"));		
		datos.put("codigoUsuario", MapUtils.getString(params, "codigoUsuario"));
		
		if(resultado != null && resultado.size() > 0)
		{
			String numeroLote = "";
			for(int i=0; i<resultado.size(); i++)
			{
				datos.put("numeroLote", MapUtils.getString((Map)resultado.get(i), "numeroLote"));				
				mantenimientoCCCLiquidacionLoteBancarioService.executeLiquidarLoteBancario(datos);
				
				if(log.isDebugEnabled())
					log.debug("Nro de lote procesado OK: " + MapUtils.getString((Map)resultado.get(i), "numeroLote"));
			}
		}
		
		return params;
	}
	
}
