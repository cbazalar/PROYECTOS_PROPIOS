package biz.belcorp.ssicc.reportes.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;

@Service("reportes.reporteVENLibroVentaMensualService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteVENLibroVentaMensualServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;
	
	@Resource(name = "genericoService")
	private GenericoService genericoService;

	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		String key = null;
		String formatoExportacion = (String) parameterMap
				.get("formatoExportacion");
		String codigoPais = (String) parameterMap
				.get("codigoPais");

		ParametroPais parametroPais = new ParametroPais();

		parametroPais.setCodigoPais(codigoPais);
		parametroPais.setCodigoSistema(Constants.CODIGO_SISTEMA_RUV);
		parametroPais.setCodigoParametro(Constants.CODIGO_PARAMETRO_MUESTRA_REPORTE_LIBRO_VENTAS_VENEZUELA);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);

		List parametros = genericoService.getParametrosPais(parametroPais);

		String parametroReporteVenezuela = "";

		if (parametros != null && parametros.size() > 0) {
			ParametroPais p = (ParametroPais) parametros.get(0);
			parametroReporteVenezuela = p.getValorParametro();
		}

		if (formatoExportacion.equals("OCSV")) {
			if (parametroReporteVenezuela.equals("1")) {
				return "reporteVENLibroVentaMensualParametroActivoForm.tituloReporteCSV";
			} else {
				return "reporteVENLibroVentaMensualForm.tituloReporteCSV";
			}
		}

		return key;

	}

	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		return "reporteRUVLibroVentasMensualCSV";
	}

	public Map generarReporteOracle(Map parameterMap) {

		// -- Variables
		String formatoExportacion = (String) parameterMap.get("formatoExportacion");

		if (formatoExportacion.equals("OCSV"))
			parameterMap = reporteService.generarReporteRUVLibroVentasMensualCSV(parameterMap);

		return parameterMap;
	}

}
