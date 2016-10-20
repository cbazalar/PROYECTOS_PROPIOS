/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 *  @author <a>efernandezo</a>
 * <p>
 * <a href="InterfazLARRecepcionarActualizaSecuenciacionzonasServiceImpl.java"> <i>View
 * Source </i> </a>
 * </p>
 * 
 */
@Service("sisicc.interfazLARRecepcionarActualizaSecuenciacionzonasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLARRecepcionarActualizaSecuenciacionzonasServiceImpl extends
		BaseInterfazEntradaAbstractService {

	Integer OIDcodPais= null;
	private InterfazExecutionResult interfazExecutionResultx = new InterfazExecutionResult();
	String varNombreArchivo;

	/**
	 * @return the interfazExecutionResultx
	 */
	public InterfazExecutionResult getInterfazExecutionResultx() {
		return interfazExecutionResultx;
	}

	/**
	 * @param interfazExecutionResultx the interfazExecutionResultx to set
	 */
	public void setInterfazExecutionResultx(
			InterfazExecutionResult interfazExecutionResultx) {
		this.interfazExecutionResultx = interfazExecutionResultx;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {

		try {

			String codPais			= (String) row.get("codPais");
			String codZona		  	= (String) row.get("codZona");
			String numSecuencia		= (String)	row.get("numSecuencia");

			Map params = interfazParams.getQueryParams();
			String codigoPaisJSP	   = (String)params.get("codigoPais");

			log.debug("codPais:"+codPais);
			log.debug("codZona:"+codZona);
			log.debug("numSecuencia:"+numSecuencia);
			log.debug("codigoPaisJSP:"+codigoPaisJSP);
			log.debug("OIDcodPais:"+OIDcodPais);
			
			Map criterio = new HashMap();
			criterio.put("codZona",codZona);
			criterio.put("numSecuencia",numSecuencia);
			criterio.put("OIDcodPais",OIDcodPais);
			
			
			if (!codigoPaisJSP.equals(codPais)) {
				throw new InterfazException("Pais no Valido.");
			}

			if (StringUtils.isNotBlank(codPais)) {
				try {
				interfazSiCCDAO.updateInterfazLARRecepcionarActualizaSecuenciacionzonas(criterio);
				} catch (Exception e) {
					throw new InterfazException("Registro Nro: " + lineCount + ". " + "numSecuencia" + numSecuencia + e.getMessage());
				}				
			}
			
		} catch (Exception e) {
			throw new InterfazException("Registro Nro: " + lineCount + ". "  + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
		// TODO Auto-generated method stub
		super.beforeReadData(interfazParams);

		Map params = interfazParams.getQueryParams();
		String codigoPais	= (String)params.get("codigoPais");
		/*Se reutiliza la funcion*/
		OIDcodPais = interfazSiCCDAO.getOidPaisInterfazLARRecepcionarActualizaResultadoChequeo(codigoPais);
	}
	
	protected void afterProcessInterfaz(InterfazParams interfazParams)
	throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'afterProcessInterfaz' method");
		
		List servicioList = interfazSiCCDAO.getWebServiceLAREnvioSecuenciacionzonasList();

		interfazExecutionResultx.setNumeroLote(varNombreArchivo);
		//interfazExecutionResultx.setInterfazResults(servicioList);
		
		log.debug("servicioList " + servicioList.size());
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#getNombreArchivo(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.io.File)
	 */
	protected void getNombreArchivo(InterfazParams interfazParams)
			throws InterfazException {
		// TODO Auto-generated method stub
		super.getNombreArchivo(interfazParams);
		varNombreArchivo = interfazParams.getArchivoEntradaFileName();
	}



}
