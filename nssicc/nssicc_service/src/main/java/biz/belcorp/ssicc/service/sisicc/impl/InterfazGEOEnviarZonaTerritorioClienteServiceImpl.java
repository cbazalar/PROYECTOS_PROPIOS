package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAHistorico;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.ClienteUAHistoricoService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service del Envio de Zona y Territorio de Cliente de la Interfaz GEO.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Service("sisicc.interfazGEOEnviarZonaTerritorioClienteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazGEOEnviarZonaTerritorioClienteServiceImpl extends
        BaseInterfazSalidaAbstractService {

	
	@Resource(name="sisicc.clienteUAGenerarService")
    private ClienteUAGenerarService clienteUAGenerarService;

	@Resource(name="sisicc.clienteUAHistoricoService")
    private ClienteUAHistoricoService clienteUAHistoricoService;

    protected Map prepareQueryParams(InterfazParams interfazParams)
            throws InterfazException {
        Map queryParams = super.prepareQueryParams(interfazParams);
        queryParams.put("usuario", interfazParams.getUsuario());
        return queryParams;
    }

    /**
     * Obtiene la data para el Envio de Zona y Territorio de Cliente de la
     * Interfaz GEO.
     * 
     * @param queryParams
     *            parametros del query.
     * @return List con Maps con los datos de las Zonas
     * @throws InterfazException
     */
    protected List readData(Map queryParams) throws InterfazException {
        if (log.isDebugEnabled())
            log.debug("Entering 'readData' method ");
        List result = null;
        try {
            result = clienteUAGenerarService.getClientesUAGenerar();
            String numeroLote = (String) queryParams.get("numeroLote");
            Usuario usuario = (Usuario) queryParams.get("usuario");
            actualizarHistoricoClientes(result, numeroLote, usuario);
        } catch (Exception e) {
            log.error("Error al leer los datos: " + e.getMessage());
            throw new InterfazException(e.getMessage());
        }
        return result;
    }

    private void actualizarHistoricoClientes(List clientes, String numeroLote,
            Usuario usuario) throws Exception {
        if (log.isDebugEnabled())
            log.debug("Entering 'actualizarHistoricoClientes' method ");
        int existeRegistro = 0;
        for (int i = 0; i < clientes.size(); i++) {
            ClienteUAHistorico historico = new ClienteUAHistorico();
            historico.setNumeroLote(numeroLote);

            Map row = (Map) clientes.get(i);
            BeanUtils.copyProperties(historico, row);
            // Copiamos los campos de auditoria
            Object usuarioDigitacion = MapUtils.getObject(row,
                    "usuarioDigitacion");
            if (usuarioDigitacion != null) {
                BeanUtils.copyProperty(historico, "auditInfo.createdBy",
                        MapUtils.getObject(row, "usuarioDigitacion"));
            }
            Object fechaDigitacion = MapUtils.getObject(row, "fechaDigitacion");
            if (fechaDigitacion != null) {
                BeanUtils.copyProperty(historico, "auditInfo.created", MapUtils
                        .getObject(row, "fechaDigitacion"));
            }
            Object usuarioModificacion = MapUtils.getObject(row,
                    "usuarioModificacion");
            if (usuarioModificacion != null) {
                BeanUtils.copyProperty(historico, "auditInfo.updatedBy",
                        usuarioModificacion);
            }
            Object fechaModificacion = MapUtils.getObject(row,
                    "fechaModificacion");
            if (fechaModificacion != null) {
                BeanUtils.copyProperty(historico, "auditInfo.lastUpdated",
                        fechaModificacion);
            }
            existeRegistro = clienteUAHistoricoService
                    .selectClienteUAHistorico(historico);
            if (existeRegistro == 0)
                clienteUAHistoricoService.insertClienteUAHistorico(historico,
                        usuario);
            else
                clienteUAHistoricoService.updateClienteUAHistorico(historico,
                        usuario);
        }
        clienteUAGenerarService.removeClientesUAGenerar();
    }

    
}