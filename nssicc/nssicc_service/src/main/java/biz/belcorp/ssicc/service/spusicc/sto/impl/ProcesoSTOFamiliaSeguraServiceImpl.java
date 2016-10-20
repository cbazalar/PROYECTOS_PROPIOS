package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOFamiliaSeguraService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * @author Jose Luis Rodriguez
 *
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOFamiliaSeguraServiceImpl extends BaseProcesoSTOAbstractService implements ProcesoSTOFamiliaSeguraService{

	
	/**
	 * Metodo ejecutado despues de rechazar los documentos
	 * @param documentoSTOParams
	 */
	protected void beforeRejectDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		if (log.isDebugEnabled())
			log.debug("Entering 'afterRejectDocumentoSTO' FAS");
		List lista =documentoSTOParams.getStoList();
		Map queryParams = documentoSTOParams.getQueryParams();
		
	    for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	    	GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();

			String tipoDocumento = gestionDocumento.getDocumento();

			String codPais = gestionDocumento.getCodigoPais();
			String numeDocumento = gestionDocumento.getNumeroDocumento();
			String numeroLote = gestionDocumento.getLote();
			String observaciones = (String) queryParams.get("observaciones");
			
			Map map = new HashMap();
			map.put("codPais", codPais);
			map.put("tipoDocumento", tipoDocumento);
			map.put("numeDocumento", numeDocumento);
			map.put("numeroLote", numeroLote);

			List validaciones = procesoSTODAO.getMensajesRechazo(map);
			int contador = 0;
				
				String valMensajeRechazo = "";
				String valObservacion = "";

				if (validaciones.size() > 0) {
					while (contador < 3) {
						Map resultado = (Map) validaciones.get(contador);
						valMensajeRechazo = (String) resultado.get("mensajeRechazo");

						if (valMensajeRechazo != null) {
							valObservacion = valObservacion + valMensajeRechazo	+ ',';
						}

						contador++;

						if (contador == validaciones.size()) {
							break;
						}
					}
				}

				valObservacion = valObservacion + ' ' + observaciones;
				String valObservacion2 = valObservacion.trim();

				if (valObservacion2.length() > 1) {
					String ultimoCaracter = valObservacion2.substring(valObservacion2.length() - 1);

					if (ultimoCaracter.equals(",")) {
						valObservacion = valObservacion2.substring(0,valObservacion2.length() - 1);
					}
				}
				gestionDocumento.setObservaciones(valObservacion);

	  	}
		

	}

	/**
	 * @param documentoSTOParams
	 */
	public void executeDeleteDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		procesoSTODAO.executeEliminarPoliza(documentoSTOParams.getQueryParams());
	};

	
}