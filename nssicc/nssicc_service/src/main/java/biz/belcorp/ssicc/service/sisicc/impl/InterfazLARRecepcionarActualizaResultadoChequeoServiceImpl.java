/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 *  @author <a>efernandezo</a>
 * <p>
 * <a href="InterfazLARRecepcionarActualizaResultadoChequeoServiceImpl.java"> <i>View
 * Source </i> </a>
 * </p>
 * 
 */
@Service("sisicc.interfazLARRecepcionarActualizaResultadoChequeoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLARRecepcionarActualizaResultadoChequeoServiceImpl 
 extends BaseInterfazEntradaAbstractService {
	
		Integer OIDcodPais= null;

		int varFC=0;
		int varTC=0;
		int varPD=0;
		int varSB=0;
		int varNQ=0;
		int varSR=0;
		int varHS=0;
		int varHZ=0;
		int varOK=0;
		private ArrayList listaArchivo=new ArrayList();
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

	/**
		 * @return the result
		 */
/*		
		public InterfazExecutionResult getResult() {
			return result;
		}
*/
		/**
		 * @param result the result to set
		 */
/*		
		public void setResult(InterfazExecutionResult result) {
			this.result = result;
		}
*/
	/**
		 * @return the listaArchivo
		 */
		public ArrayList getListaArchivo() {
			return listaArchivo;
		}

		/**
		 * @param listaArchivo the listaArchivo to set
		 */
		public void setListaArchivo(ArrayList listaArchivo) {
			this.listaArchivo = listaArchivo;
		}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
		try {

			Usuario usuario = interfazParams.getUsuario();
			
			String codPais			   = (String) row.get("codPais");
			String numPedido           = (String) row.get("numPedido");
			String indMarcaChequeo     = (String) row.get("indMarcaChequeo");
			String codResultadoChequeo = (String) row.get("codResultadoChequeo");
			String obsChequeo          = (String) row.get("obsChequeo");
			
			Map params = interfazParams.getQueryParams();
			String codigoSubacceso	   = (String)params.get("codigoSubacceso");
			String codigoPaisJSP	   = (String)params.get("codigoPais");
			
			log.debug("codPais:"+codPais);
			log.debug("numPedido:"+numPedido);
			log.debug("indMarcaChequeo:"+indMarcaChequeo);
			log.debug("codResultadoChequeo:"+codResultadoChequeo);
			log.debug("obsChequeo:"+obsChequeo);
			log.debug("codigoSubacceso:"+codigoSubacceso);
			log.debug("OIDcodPais:"+OIDcodPais);
			log.debug("codigoPaisJSP:"+codigoPaisJSP);
			
			Map criterio = new HashMap();
			criterio.put("codResultadoChequeo",codResultadoChequeo);
			criterio.put("obsChequeo",obsChequeo);
			criterio.put("OIDcodPais",OIDcodPais);
			criterio.put("numPedido",numPedido);
			criterio.put("indMarcaChequeo",indMarcaChequeo);
			criterio.put("codigoSubacceso",codigoSubacceso);

			if (!codigoPaisJSP.equals(codPais)) {
				throw new InterfazException("Pais no Valido.");
			}

			if (StringUtils.isNotBlank(codPais)) {
				log.debug("Update: ");
				interfazSiCCDAO.updateInterfazLARRecepcionarActualizaResultadoChequeo(criterio);
			}
			
			Map paramsx = interfazParams.getQueryParams();
			if( codResultadoChequeo.equals("OK")){
				varOK++;
				paramsx.put("varOK", new Integer(varOK));				
			  }
			else if( codResultadoChequeo.equals("FC")){
				varFC++;
				paramsx.put("varFC", new Integer(varFC));				
			  }
			else if( codResultadoChequeo.equals("TC")){
				varTC++;
				paramsx.put("varTC", new Integer(varTC));				
			  }
			else if( codResultadoChequeo.equals("PD")){
				varPD++;
				paramsx.put("varPD", new Integer(varPD));				
			  }
			else if( codResultadoChequeo.equals("SB")){
				varSB++;
				paramsx.put("varSB", new Integer(varSB));
			  }
			else if( codResultadoChequeo.equals("NQ")){
				varNQ++;
				paramsx.put("varNQ", new Integer(varNQ));
			  }
			else if( codResultadoChequeo.equals("SR")){
				varSR++;
				paramsx.put("varSR", new Integer(varSR));
			  }
			else if( codResultadoChequeo.equals("HS")){
				varHS++;
				paramsx.put("varHS", new Integer(varHS));
			  }
			else if( codResultadoChequeo.equals("HZ")){
				varHZ++;
				paramsx.put("varHZ", new Integer(varHZ));
			  }
		} catch (Exception e) {
				throw new InterfazException("Registro Nro: " + lineCount + ". " + e.getMessage());				
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
		
		OIDcodPais = interfazSiCCDAO.getOidPaisInterfazLARRecepcionarActualizaResultadoChequeo(codigoPais);

	}
	
	protected void afterProcessInterfaz(InterfazParams interfazParams)
	throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'afterProcessInterfaz' method");

		Map paramsf = new HashMap();
		Map paramsx = interfazParams.getQueryParams();

		Map datosMap = prepareQueryParams(interfazParams);
		
		paramsf.put("nombreArchivo",varNombreArchivo);
		paramsf.put("varFC",( ( paramsx.get("varFC")==null)? "0":paramsx.get("varFC")) +" Ped c/Faltante");
		paramsf.put("varTC",( ( paramsx.get("varTC")==null)? "0":paramsx.get("varTC"))+" Ped c/Trueque-lleg otro pdcto");
		paramsf.put("varPD",( ( paramsx.get("varPD")==null)? "0":paramsx.get("varPD"))+" Ped c/pdcto Derr/Roto/Incom");
		paramsf.put("varSB",( ( paramsx.get("varSB")==null)? "0":paramsx.get("varSB"))+" Ped c/Sobrante");
		paramsf.put("varNQ",( ( paramsx.get("varNQ")==null)? "0":paramsx.get("varNQ"))+" Consultora No Quiso Chequeo");
		paramsf.put("varSR",( ( paramsx.get("varSR")==null)? "0":paramsx.get("varSR"))+" Cheq S/Resultado");
		paramsf.put("varHS",( ( paramsx.get("varHS")==null)? "0":paramsx.get("varHS"))+" Cheq Susp x SAC");
		paramsf.put("varHZ",( ( paramsx.get("varHZ")==null)? "0":paramsx.get("varHZ"))+" Cheq Susp x Zona Peligr/Alej");
		paramsf.put("varOK",( ( paramsx.get("varOK")==null)? "0":paramsx.get("varOK"))+" Cheq Conforme");
		listaArchivo.add(paramsf);
		//interfazExecutionResultx.setInterfazResultsInter(listaArchivo);
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
