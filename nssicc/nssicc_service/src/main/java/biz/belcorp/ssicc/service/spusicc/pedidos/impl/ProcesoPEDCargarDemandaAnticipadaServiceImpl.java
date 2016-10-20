/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidTransactionProcessFileException;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDCargarDemandaAnticipadaService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.service.util.XlsxUtil;

/**
 * @author itocto
 *
 */
@Service("spusicc.procesoPEDCargarDemandaAnticipadaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDCargarDemandaAnticipadaServiceImpl extends BaseService implements ProcesoPEDCargarDemandaAnticipadaService{

	@Resource(name="spusicc.procesoPEDDAO")
	ProcesoPEDDAO procesoPEDDAO;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargarDemandaAnticipadaService#executeProcesarArchivo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int executeProcesarArchivo(String directorioTemp, String archivoProcesar, String codigoPais, String codigoPeriodo) throws Exception {

		int numeroRegistros = 0;

		//Eliminamos la data existente
		Map params = new HashMap();
		params.put("codigoPais", codigoPais);
		params.put("codigoPeriodo", codigoPeriodo);
		
		procesoPEDDAO.deleteDemandaAnticipada(params);
		//
		
		if(StringUtils.isNotBlank(archivoProcesar)){
			
			XlsxUtil excelUtil = null;
			List zonasList = null;
			int fila = 1;
			
			try {
				
				// Abrimos el archivo Excel para su procesamiento
				excelUtil = new XlsxUtil(directorioTemp, archivoProcesar);
				for (int i = 0; i < Constants.NUMERO_MAX_HOJAS_EXCEL; i++) {
					
					try{
						// Nos colocamos en la primera hoja del documento Excel
						excelUtil.initSheet(i);
						
						// Nos pasamos a la segunda fila, ya que en el primero se encuentra la cabecera
						excelUtil.next();
					}catch(Exception e){
						// Si no encuentra la hoja iterada o no encuentra datos en la hoja, terminar la iteracion
						break;
					}
														
					Map mapValores = null;				
					
					while(excelUtil.hasNext()){
						
						Map mapRow = excelUtil.next();					
						mapValores = new HashMap();				
						fila = fila + 1;
						
						if (this.log.isDebugEnabled()) {
							this.log.debug("Carga Archivo mapRow: "+fila+" - "+mapRow.toString());
						}
						
						//Insertamos en la tabla
						String codigoPaisArchivo = (String.valueOf(mapRow.get("0"))).trim();
						String codigoPeriodoArchivo = (String.valueOf(mapRow.get("1"))).trim();
						String codigoZona = (String.valueOf(mapRow.get("2"))).trim();
						String codigoCliente = (String.valueOf(mapRow.get("3"))).trim();
						String codigoEstr = (String.valueOf(mapRow.get("4"))).trim();
						String desEstr = (String.valueOf(mapRow.get("5"))).trim();
						String codigoVenta = (String.valueOf(mapRow.get("6"))).trim();
						String codigoSAP = (String.valueOf(mapRow.get("7"))).trim();
						String codigoTipoOfer = (String.valueOf(mapRow.get("8"))).trim();
						String desSAP = (String.valueOf(mapRow.get("9"))).trim();
						String numUnid = (String.valueOf(mapRow.get("10"))).trim();
						String valVenta = (String.valueOf(mapRow.get("11"))).trim();
												
						BigDecimal nNumUnid = BigDecimal.ZERO;
						BigDecimal nValVenta = BigDecimal.ZERO;
						
						if(numUnid != null){
							try{
								nNumUnid = new BigDecimal(numUnid);
							}catch(Exception pe){
								nNumUnid = BigDecimal.ZERO;
							}
						}

						if(valVenta != null){
							try{
								nValVenta = new BigDecimal(valVenta);
							}catch(Exception pe){
								nValVenta = BigDecimal.ZERO;
							}
						}

						mapValores.put("codigoPais", codigoPaisArchivo); 
						mapValores.put("codigoPeriodo", limpiarValor(codigoPeriodoArchivo));
						mapValores.put("codigoZona", limpiarValor(codigoZona));
						mapValores.put("codigoCliente", limpiarValor(codigoCliente));
						mapValores.put("codigoEstr", limpiarValor(codigoEstr));
						mapValores.put("desEstr", desEstr);
						mapValores.put("codigoVenta", limpiarValor(codigoVenta));
						mapValores.put("codigoSAP", limpiarValor(codigoSAP));
						mapValores.put("codigoTipoOfer", limpiarValor(codigoTipoOfer));
						mapValores.put("desSAP", desSAP);
						mapValores.put("numUnid", nNumUnid);
						mapValores.put("valVenta", nValVenta);			
						
						procesoPEDDAO.insertDemandaAnticipada(mapValores);
						//
						numeroRegistros++;
					}
					
				}			
				
				excelUtil.cerrar();			
				
			} catch (Exception e) {
				
				if(excelUtil != null){
					excelUtil.cerrar();
				}
				
				// Si existe algun error debemos de borrar el archivo de la carpeta temporal
				this.borrarFichero(directorioTemp, archivoProcesar);
				
				if(e.getCause() instanceof DataIntegrityViolationException){
					// Se lanzara una excepcion si existe algun fallo en la integridad de los datos
					throw new InvalidTransactionProcessFileException(
					e.getCause().getCause().getCause().getMessage(), archivoProcesar);					
				}else if(e.getCause() instanceof UncategorizedSQLException){
					throw new InvalidTransactionProcessFileException(
					e.getCause().getCause().getCause().getMessage(), archivoProcesar);
				}else{
					throw e;
				}			
			}
		}

		return numeroRegistros;
	}
	
	/**
	 * elimina el fichero del temporal
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			
			if (this.log.isDebugEnabled()) {
				this.log.debug("Se elimino el archivo");
			}
		}	
		catch(Exception ex) {
			
			if (this.log.isDebugEnabled()) {
				this.log.debug("No se pudo eliminar el archivo "+ex.getMessage());
			}
		}
	}	
	
	private String limpiarValor(String valor)
	{
		if(valor.endsWith(".0")){
			valor = valor.substring(0, valor.length()-2);
		}
		
		return valor;
	}
}
