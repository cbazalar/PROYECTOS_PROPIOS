/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.LibretaAhorro;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCOMRecepcionarCodigosPlanillaServiceImpl"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazCOMRecepcionarCodigosPlanillaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOMRecepcionarCodigosPlanillaServiceImpl extends
		BaseInterfazEntradaAbstractService {

	protected void addLine(List data, Map row) {
		data.add(row);
	}

	protected void processData(InterfazParams interfazParams, List data) throws InterfazException {
		if (log.isInfoEnabled()) {
			log.info("Entering 'processData'");
		}
		LibretaAhorro libretaAhorro;
		Usuario usuario = interfazParams.getUsuario();
		int existe = 0;
		String existeStr = "";
		try {
			/* Insertando valores */
			for (int i = 0; i < data.size(); i++) {
				HashMap criteria = (HashMap) data.get(i);
				try {
					libretaAhorro = new LibretaAhorro();
					libretaAhorro.setCodigoPlanilla((String) criteria.get("codigoPlanilla"));
					libretaAhorro.setDocumentoIdentidad((String) criteria.get("dni"));
					
					existeStr = this.interfazSiCCDAO.getCantidadLibretaAhorrobyDNI(libretaAhorro.getDocumentoIdentidad());
					existe = Integer.parseInt(existeStr);
					if (existe != 0)
						this.interfazSiCCDAO.updateLibretaAhorro(libretaAhorro, usuario);
				} catch (Exception e) {
					throw new InterfazException(Constants.ERROR_COPY_PROPERTIES);
				}
			}
		}catch (Exception e) {
			throw new InterfazException(e.getMessage());
		}
	}
	

}
