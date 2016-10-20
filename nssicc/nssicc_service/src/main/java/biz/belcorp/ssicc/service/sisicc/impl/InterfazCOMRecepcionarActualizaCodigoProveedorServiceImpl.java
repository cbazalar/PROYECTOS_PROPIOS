/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazCOMRecepcionarActualizaCodigoProveedor;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 *  @author <a>efernandezo</a>
 * <p>
 * <a href="InterfazCOMRecepcionarActualizaCodigoProveedorServiceImpl.java"> <i>View
 * Source </i> </a>
 * </p>
 * 
 */
@Service("sisicc.interfazCOMRecepcionarActualizaCodigoProveedorService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOMRecepcionarActualizaCodigoProveedorServiceImpl extends
		BaseInterfazEntradaAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {

		try {

			Usuario usuario = interfazParams.getUsuario();

			Map params = interfazParams.getQueryParams();
			String codigoPais	   = (String)params.get("codigoPais");

			InterfazCOMRecepcionarActualizaCodigoProveedor resumen = new InterfazCOMRecepcionarActualizaCodigoProveedor();

			String codProveedor = (String) row.get("codProveedor");
			String ruc			= (String) row.get("ruc");
	
			row.remove("codProveedor");
			row.remove("ruc");
			row.put("codProveedor",codProveedor);
			row.put("ruc", ruc);
		    BeanUtils.copyProperties(resumen, row);

			log.debug("codProveedor:"+resumen.getCodProveedor());
			log.debug("ruc:"+resumen.getRuc());
			log.debug("codigoPais:"+codigoPais);
		    
		    Map criteriax = new HashMap();
		    criteriax.put("codProveedor", resumen.getCodProveedor() );
		    criteriax.put("ruc", resumen.getRuc());
		    criteriax.put("codigoPais", codigoPais);
		    resumen.setCodigoPais(codigoPais);
		    
			/* Validaciones del Codigo de Proveedor */
			if (StringUtils.isNotBlank(ruc)) {
				//Base count = interfazSiCCDAO.getSociedadByCodigo(criteriax);
				Integer count = interfazSiCCDAO.getProveedorCount(criteriax);				
				
				if (count.intValue() == 0)  {
					throw new InterfazException("Registro Nro: " + (lineCount) +
							   ". El Cdigo Ruc1 " + resumen.getRuc() + " no existe");
				}
			}
			else {
				throw new InterfazException("Registro Nro: " + (lineCount) + 
						". El Cdigo Ruc2 " + resumen.getRuc() + " no existe");
			}		    
		    
			if (StringUtils.isNotBlank(codProveedor)) {
				
				interfazSiCCDAO.updateInterfazCOMRecepcionarActualizaCodigoProveedor(resumen, usuario);
			}

			
		} catch (Exception e) {
			throw new InterfazException("Registro Nro: " + lineCount + ". " + e.getMessage());
		}
	}

	
	/**
	 * Convierte del formato <i>yyyyMMdd</i> al formato <i>dd/MM/yyyy</i>.
	 * <br />
	 * Ej: 20061231 a 31/12/2006
	 * 
	 * @param fechaProceso
	 *            String con el formato <i>yyyyMMdd</i>
	 * @return String con el formato <i>dd/MM/yyyy</i>
	 */
	public static String convierteFormatoFecha(String fecha) {
		String resultado = "";
		resultado = fecha.substring(6, 8) + "/" + fecha.substring(4, 6) + "/"
				+ fecha.substring(0, 4);
		return resultado;
	}

}
