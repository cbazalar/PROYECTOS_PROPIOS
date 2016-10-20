/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 *  @author <a>efernandezo</a>
 * <p>
 * <a href="InterfazLLIRecepcionarActualizaEstimadosYobelServiceImpl.java"> <i>View
 * Source </i> </a>
 * </p>
 * 
 */
@Service("sisicc.interfazLLIRecepcionarActualizaEstimadosYobelService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLLIRecepcionarActualizaEstimadosYobelServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
			String vlflag=null;

		try {
			Usuario usuario = interfazParams.getUsuario();
			
			
			String codCamp		  = (String) row.get("codCamp");
			String codFecha       = (String) row.get("codFecha");
			String numLote        = (String) row.get("numLote");
			String codTipoPeriodo = (String) row.get("codTipoPeriodo");
			String codPeriodo     = (String) row.get("codPeriodo");
			String codSat         = (String) row.get("codSat");
			String desProducto    = (String) row.get("desProducto");
			String codUnidades    = (String) row.get("codUnidades");
			
			/*
			row.remove("codProveedor");
			row.remove("ruc");
			row.put("codProveedor",codProveedor);
			row.put("ruc", ruc);
		    BeanUtils.copyProperties(resumen, row);
		    */

			log.debug("codCamp:"+codCamp);
			log.debug("codFecha:"+codFecha);
			log.debug("numLote:"+numLote);

			Map criterio = new HashMap();
			criterio.put("codCamp",codCamp);
			criterio.put("codFecha",codFecha);
			criterio.put("numLote",numLote);
			criterio.put("codTipoPeriodo",codTipoPeriodo);
			criterio.put("codPeriodo",codPeriodo);
			criterio.put("codSat",codSat);
			criterio.put("desProducto",desProducto);
			criterio.put("codUnidades",codUnidades);


			//vlflag="D";	
			//interfazSiCCDAO.deleteInterfazLLIRecepcionarActualizaEstimadosYobel();
			
			if (StringUtils.isNotBlank(codCamp)) {
//				vlflag="I";
				interfazSiCCDAO.insertInterfazLLIRecepcionarActualizaEstimadosYobel(criterio);
			}
			
			

		} catch (Exception e) {
			
		//	if( vlflag=="I" ){
				throw new InterfazException("Registro Nro: " + lineCount + ". " + e.getMessage());				
//			}
			//else if( vlflag=="D" ){
			//throw new InterfazException("Error al borrar los registros de la tabla temporal: "+ e.getMessage());
			//}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
		// TODO Auto-generated method stub
		try {		
			super.beforeReadData(interfazParams);
		
			interfazSiCCDAO.deleteInterfazLLIRecepcionarActualizaEstimadosYobel();

		} catch (Exception e) {
			throw new InterfazException("Error al borrar los registros de la tabla temporal: "+ e.getMessage());
		}		
	}


	
}
