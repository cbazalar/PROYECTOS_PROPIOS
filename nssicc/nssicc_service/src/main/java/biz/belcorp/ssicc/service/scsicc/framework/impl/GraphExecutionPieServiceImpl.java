package biz.belcorp.ssicc.service.scsicc.framework.impl;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.scsicc.model.GraficoPie;
import biz.belcorp.ssicc.service.scsicc.framework.GraphExecutionService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.GraphParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.GraphResult;


/**
 * Implementacion del Service de ejecucin de los Graficos SSiCC
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */
@Service("scsicc.graphExecutionPieService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class GraphExecutionPieServiceImpl implements GraphExecutionService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.framework.GraphAbstractService#executeGrafico(biz.belcorp.ssicc.scsicc.service.framework.beans.GraphParams)
	 */
	public final GraphResult generarGrafico(GraphParams graphParam) throws Exception {
		log.debug("Entering 'generarGrafico' method");
		
		GraphResult graphResult = new GraphResult();
		JFreeChart pieChart = null;
		DefaultPieDataset data = new DefaultPieDataset();
		List lista = graphParam.getListDataGenerada();
		for (int i=0; i < lista.size(); i++) {
			GraficoPie graficoPie = (GraficoPie)lista.get(i);
			data.setValue(graficoPie.getLeyenda(), graficoPie.getSerie());
		}
		
		/* Generando grafico */
		pieChart = ChartFactory.createPieChart(graphParam.getTituloGrafico(), // chart title
                data,                // data
                true,                // include legend
                true,
                false
        );
		PiePlot plot = (PiePlot) pieChart.getPlot();
		plot.setNoDataMessage(graphParam.getMensajeNoData());
		PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator(
				"{0} = {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0.00%"));
		plot.setLabelGenerator(generator);
		plot.setToolTipGenerator(new StandardPieToolTipGenerator());
		
		/* adicionando subtitulo */
		if (StringUtils.isNotBlank(graphParam.getSubTituloGrafico())) {
			Font font = new Font("Dialog", Font.PLAIN, 12);
			pieChart.addSubtitle(new TextTitle(
					graphParam.getSubTituloGrafico(), font));
		}
		
        graphResult.setPieChart(pieChart);
		log.debug("Se genero el Grafico PIE");
		return graphResult;
	}
	

}
