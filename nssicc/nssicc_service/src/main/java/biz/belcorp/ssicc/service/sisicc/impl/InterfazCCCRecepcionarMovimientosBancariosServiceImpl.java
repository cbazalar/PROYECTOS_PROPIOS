package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazCCCDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarLotesBancariosService;

/**
 * Service para la recepcion de Movimientos Bancarios de la Interfaz CCC.
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio</a>
 */
@Service("sisicc.interfazCCCRecepcionarMovimientosBancariosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCCCRecepcionarMovimientosBancariosServiceImpl extends
        BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazCCCDAO")
    private InterfazCCCDAO interfazCCCDAO;
    
	@Resource(name="spusicc.procesoCCCCargarLotesBancariosService")
	ProcesoCCCCargarLotesBancariosService service;
	
	
	InterfazExecutionResult interfazExecutionResult;
	
    private String numeroLoteInterno;    
    private String numeroLoteExterno;
    private String codigoBancoSicc;
    

    protected Map prepareQueryParams(InterfazParams interfazParams)
    		throws InterfazException {
    	// TODO Auto-generated method stub
    	return super.prepareQueryParams(interfazParams);
    }
       

	protected void beforeProcessInterfaz(InterfazParams interfazParams)throws InterfazException {
    	if (log.isDebugEnabled())
		log.debug("JFA Entering 'beforeProcessInterfaz' method");
    	interfazCCCDAO.deleteInterfazCCCRecepcionarMovimientosBancarios(interfazParams.getQueryParams());
    }
    
    protected void processLine(InterfazParams interfazParams, int lineCount,
            Map row) throws InterfazException {
            	    	
    	row.put("codigoPais", interfazParams.getQueryParams().get(
        "codigoPais"));
    	
    	row.put("codigoSociedad", interfazParams.getQueryParams().get(
		"codigoSociedad"));
    	    	    	
        log.debug("row=" + row);
        
        numeroLoteExterno = row.get("numeroLoteExterno").toString();
        
        codigoBancoSicc  = row.get("codigoBancoSicc").toString();
                        
        interfazCCCDAO.insertInterfazCCCRecepcionarMovimientosBancarios(row);
    }
    
	protected void afterProcessInterfaz(InterfazParams interfazParams)
			throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("JFA :Entering 'afterProcessInterfaz' method");

		Map params = new HashMap();

		params.put("codigoPais",interfazParams.getQueryParams().get("codigoPais"));
		params.put("codigoSociedad",interfazParams.getQueryParams().get("codigoSociedad"));
		params.put("numeroLoteInterno",interfazParams.getQueryParams().get("numeroLoteInterno"));
		params.put("numeroLoteExterno", numeroLoteExterno);
		params.put("codigoBancoSicc", codigoBancoSicc);
		params.put("codigoUsuario", interfazParams.getUsuario().getLogin());
		
		interfazCCCDAO.executeInterfazCCCRecepcionarMovimientosBancarios(params);

		/**/
		String indicadorRuteoPago = interfazCCCDAO.getValParaCCCParamGener(Constants.RUTEA_PAGO_01);
		
		if(log.isDebugEnabled())
			log.debug("indicadorRuteoPago : " + indicadorRuteoPago);
		
		// 2. Si es ecuador, validar que sea BAN_RUTEA_PAGO_01 est activo
		if (StringUtils.equals(indicadorRuteoPago, Constants.ESTADO_ACTIVO)) 
		{
			// 3. Si est activo, hacer el proceso, validar si hay registros en
			// la tabla ccc_movim_banca_ruteo
			Integer cantidadReg = interfazCCCDAO.getCantidadRegistroMovimBancaRuteo();

			if(log.isDebugEnabled())
				log.debug("Cantidad de registros en ccc_movim_banca_ruteo : " + cantidadReg);
			
			if (cantidadReg > 0) 
			{				
				//Inserta en la tabla de ruteo
				interfazCCCDAO.insertCCCMovimBancaRuteo(MapUtils.getString(params, "numeroLoteInterno", ""));
				//
				
				String nuevoCodPais = interfazCCCDAO.getValParaCCCParamGener(Constants.RUTEA_PAGO_03);
				String nuevoCodSoc = interfazCCCDAO.getValParaCCCParamGener(Constants.RUTEA_PAGO_04);
				String nuevoCodBan = interfazCCCDAO.getValParaCCCParamGener(Constants.RUTEA_PAGO_04 + codigoBancoSicc);

				if(log.isDebugEnabled()){
					log.debug("nuevoCodPais: " + nuevoCodPais);
					log.debug("nuevoCodSoc: " + nuevoCodSoc);
					log.debug("nuevoCodBan: " + nuevoCodBan);
				}
				
				Map nuevoParams = new HashMap();
				nuevoParams.put("nuevocodigoPais", nuevoCodPais);
				nuevoParams.put("nuevocodigoSociedad", nuevoCodSoc);
				nuevoParams.put("nuevocodigoBancoSicc", nuevoCodBan);
				nuevoParams.put("tipoOrigen", "INT");				
				nuevoParams.put("numeroLoteInterno", MapUtils.getString(params, "numeroLoteInterno", ""));
				nuevoParams.put("codigoUsuario", MapUtils.getString(params, "codigoUsuario", ""));

				interfazCCCDAO.executeCCCRegistrarLoteBancario(nuevoParams);

			}

		}

	}		
 
	
}
