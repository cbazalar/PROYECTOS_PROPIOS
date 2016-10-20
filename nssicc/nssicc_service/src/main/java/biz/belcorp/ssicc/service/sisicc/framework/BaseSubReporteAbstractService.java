package biz.belcorp.ssicc.service.sisicc.framework;

import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;

/**
 * Clase Service abstracta para los Reportes que incluyen un Subreporte.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */

@Service("spusicc.baseSubReporteAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseSubReporteAbstractService extends BaseReporteAbstractService {
	
	protected static final String SUBREPORT_PREFIX = "SUBREPORT_DIR";

	/**
	 * Este metodo debe retornar el nombre del subreporte sin la extension.
	 * 
	 * <p>
	 * El estandar es "reporte[Sistema][Descripcion]".
	 * 
	 * <p>
	 * Ejemplo: <code>return "reporteINCParametrosGenerales";</code>
	 * 
	 * @return nombre del reporte
	 */
	protected abstract String getSubReporteFileName();

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.BaseReporteAbstractService#prepareParameterMap(java.util.Map)
	 */
	protected void prepareParameterMap(Map params) throws Exception {
		String subReporte = this.getSubReporteFileName();
		if (StringUtils.isNotEmpty(subReporte)) {
			ClassPathResource resource = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + subReporte + JASPER_EXTENSION);				
			try {
				params.put(SUBREPORT_PREFIX,  (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )) );
			}
			catch (Exception e) {
				params.put(SUBREPORT_PREFIX, (JasperReport) JRLoader
						.loadObject(resource.getFile()));
			}
		
			this.setNombreReporte(subReporte);
		}
	}
	
	/**
	 * @param lista
	 * @param parametro
	 * @param comilla
	 * @return
	 */
	protected String obtieneCondicion(String[] lista, String parametro,
			String comilla) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isBlank(dato) || StringUtils.equals(dato, "Todos"))
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ")";
			resultado = " AND " + parametro + " IN " + resultado;
			return resultado;
		}
	} 


	/**
	 * @param lista
	 * @param parametro
	 * @param comilla
	 * @return
	 */
	protected String obtieneCondicionIN(String[] lista, String parametro,
			String comilla ) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isBlank(dato) || StringUtils.equals(dato, "Todos"))
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ") ";
			resultado = " IN " + resultado;
			return resultado;
		}
	} 

	

}
