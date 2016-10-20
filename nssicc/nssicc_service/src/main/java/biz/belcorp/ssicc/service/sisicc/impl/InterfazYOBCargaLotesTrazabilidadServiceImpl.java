/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazYOBDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * <p>
 * <a href="InterfazYOBCargaLotesTrazabilidadServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 */

@Service("sisicc.interfazYOBCargaLotesTrazabilidadService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazYOBCargaLotesTrazabilidadServiceImpl extends BaseInterfazEntradaAbstractService{
	
	@Resource(name="sisicc.interfazYOBDAO")
	protected InterfazYOBDAO interfazYOBDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
		try {
			
			String numLoteEnvio = (String)interfazParams.getQueryParams().get("numeroLote");
			row.put("numLoteEnvio", numLoteEnvio);
			row.put("indEnviadoFec", "");
			row.put("nombreArchivoOriginal", MapUtils.getString(interfazParams.getQueryParams(), "nombreArchivo", ""));
			
			interfazYOBDAO.insertYOBCargaLotesTrazabilidad(row);
			
			Map params = interfazParams.getQueryParams();
			String codigoPeriodo = (String) params.get("codigoPeriodo");
			String fechaFacturacion = (String)params.get("fechaFacturacion");
			String numeroPedido = (String) row.get("numeroPedido");
			String numeroPedidoMaximoCodigoPeriodo = (String) row.get("numeroPedidoMaximoCodigoPeriodo");
			numeroPedido = numeroPedido.trim();
			params.put("numeroPedido",numeroPedido);
			
		
			
			if (lineCount==1) {
				params.put("numeroPedidoMaximoCodigoPeriodo",numeroPedido);
			String resultado = new String();
			resultado = interfazYOBDAO.getDevuelvePeriodoFechaFacturacionNumPedido(params);
			if (StringUtils.isNotBlank(resultado)) {
				String listaResultado[] = StringUtils.split(resultado, "-");
				if (listaResultado.length == 2) {
					codigoPeriodo = listaResultado[0];
					fechaFacturacion = listaResultado[1];
					params.put("codigoPeriodo",codigoPeriodo);
					params.put("fechaFacturacion",fechaFacturacion);
					interfazParams.setQueryParams(params);
						this.log.info("Periodo: " + codigoPeriodo + " Fecha Facturacin Pedido: " + fechaFacturacion);
					}
				}
			}
				
				    
		} catch (Exception e) {
			throw new InterfazException("Registro Nro: " + lineCount + ". " + e.getMessage());
		}
		
	}

}
