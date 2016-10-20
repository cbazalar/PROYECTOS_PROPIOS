package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.EstimadoProductos;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

@Service("sisicc.interfazSATRecepcionarEstimadoProductosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSATRecepcionarEstimadoProductosServiceImpl
		extends BaseInterfazEntradaAbstractService {

	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {

		try {

			Usuario usuario = interfazParams.getUsuario();

			EstimadoProductos resumen = new EstimadoProductos();
			Map mapa = interfazParams.getQueryParams();
			String codigoSAP = (String) row.get("codigoSAP");
			String descripcion			= (String) row.get("descripcion");
			String unidades			= (String) row.get("unidades").toString();
			String linea =mapa.get("codigoLinea").toString();
			String periodo =mapa.get("codigoPeriodo").toString();
			String cruceCampanya ="off";
			String conM="off";
			if(mapa.get("cruceCampanya") != null)
				cruceCampanya =mapa.get("cruceCampanya").toString();
			if(mapa.get("conM")!= null)
				conM =mapa.get("conM").toString();
			row.remove("codigoSAP");
			row.remove("descripcion");
			row.remove("unidades");
			row.put("codigoSAP",codigoSAP);
			row.put("descripcion", descripcion);
			row.put("unidades", unidades.toString());
		    BeanUtils.copyProperties(resumen, row);

			log.debug("codProveedor:"+resumen.getCodigoSAP());
			log.debug("ruc:"+resumen.getDescripcion());
			log.debug("ruc:"+resumen.getUnidades());
		    
		    Map criteriax = new HashMap();
		    criteriax.put("codigoSAP", resumen.getCodigoSAP());
		    criteriax.put("descripcion", resumen.getDescripcion());
		    criteriax.put("unidades", resumen.getUnidades().trim());
		    criteriax.put("linea",linea);
		    criteriax.put("periodo", "%"+periodo);
		    criteriax.put("actualiza", cruceCampanya);
		    criteriax.put("conM", conM);
		    criteriax.put("codigoSAP", resumen.getCodigoSAP());
				EstimadoProductos est = interfazSiCCDAO.getListaEstimadosProducto(criteriax,resumen);
				if(est==null){
					System.out.println("A");
				}
				else{
					System.out.println("Desea sobreescribirlos");
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

	 protected void beforeProcessInterfaz(InterfazParams interfazParams)
		throws InterfazException {
	try {
		Map mapQueryParams = (Map) interfazParams.getQueryParams();

		List lista = interfazSiCCDAO.getListaEstimados(mapQueryParams);
		if(lista == null){
			
		}
//		interfazSiCCDAO.insertVentaBaseConsultoras(mapQueryParams);

	} catch (Exception e) {
		throw new InterfazException(
				"Error al borrar o insertar los registros de la tabla temporal: "
						+ e.getMessage());
	}
	    }

	
	

}
