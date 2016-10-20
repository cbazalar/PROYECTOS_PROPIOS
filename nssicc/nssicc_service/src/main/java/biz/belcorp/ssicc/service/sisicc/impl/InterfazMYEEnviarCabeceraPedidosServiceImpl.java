package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service de la Interfaz de Envio de Cabecera de Pedidos
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva</a>
 */
@Service("sisicc.interfazMYEEnviarCabeceraPedidosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYEEnviarCabeceraPedidosServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO
				.executeInterfazMYEEnviarCabeceraPedidos(params);
	}
}