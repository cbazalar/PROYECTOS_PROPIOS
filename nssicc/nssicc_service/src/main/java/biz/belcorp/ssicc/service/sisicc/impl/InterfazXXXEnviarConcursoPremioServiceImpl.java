package biz.belcorp.ssicc.service.sisicc.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service para el envio de Novedades de la Interfaz ACC.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */

@Service("sisicc.interfazXXXEnviarConcursoPremioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazXXXEnviarConcursoPremioServiceImpl extends	BaseInterfazSalidaAbstractService {
	
	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method ");
		List listConcurso = null;
		try {
			String codigoPais = (String)queryParams.get("codigoPais");

		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return listConcurso;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazAbstractService#executePruebaTransaccion()
	 */
	@Override
	public void executePruebaTransaccion() {
		Historico historico = new Historico();
		Usuario usuario = new Usuario();
		usuario.setCodigo("NSSICC");
		usuario.setLogin("NSSICC");
		historico.setFechaInicioProceso(new Timestamp(System.currentTimeMillis()));
		historico.setCodigoPais("PE");
		historico.setCodigoSistema("GEN");
		historico.setCodigoInterfaz("GEN-30");
		historico.setFlagError(Constants.NO);
		historico.setRegistrosProcesados(new Long(0));
		historico.setRegistrosErroneos(new Long(0));
		historico.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_OK);
		historico.setDescripcionLote("descripcion");
		historico.setObservaciones("observaciones");
		historico.setIdProcesoBatch(new Long("99999999"));
		historico.setNumeroLote("99999999");
		this.interfazSiCCDAO.insertHistorico3(historico, usuario);
		historico.setDescripcionLote("descripcion NSSiCC");
		historico.setObservaciones("observaciones NSSiCC");
		//historico.setEstadoProceso("9999"); //DESCOMENTAR PARA QUE GENERE ERROR Y PRODUZCA ROLLBACK
		this.historicoService.updateHistorico2(historico, usuario);
	}
	
	

}

