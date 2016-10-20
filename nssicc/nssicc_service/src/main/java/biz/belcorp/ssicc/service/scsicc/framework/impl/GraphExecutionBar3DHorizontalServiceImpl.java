package biz.belcorp.ssicc.service.scsicc.framework.impl;

import java.awt.Font;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.scsicc.model.GraficoBar;
import biz.belcorp.ssicc.service.scsicc.framework.GraphExecutionService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.GraphParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.GraphResult;


/**
 * Implementacion del Service de ejecucin de los Graficos SSiCC (BAR3D Horizontal)
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */
@Service("scsicc.graphExecutionBar3DHorizontalService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class GraphExecutionBar3DHorizontalServiceImpl implements GraphExecutionService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.framework.GraphAbstractService#executeGrafico(biz.belcorp.ssicc.scsicc.service.framework.beans.GraphParams)
	 */
	public final GraphResult generarGrafico(GraphParams graphParam) throws Exception {
		log.debug("Entering 'generarGrafico' method");
		
		GraphResult graphResult = new GraphResult();
		JFreeChart chart = null;
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		List lista = graphParam.getListDataGenerada();
		for (int i=0; i < lista.size(); i++) {
			GraficoBar grafico = (GraficoBar)lista.get(i);
			data.addValue(grafico.getSerie(), grafico.getLeyendaSerie(), grafico.getLeyendaCategoria());
		}
		
		/* Generando grafico */
		chart = ChartFactory.createBarChart3D(
				graphParam.getTituloGrafico(), // chart title
				graphParam.getTituloEjeX(),
				graphParam.getTituloEjeY(),
	            data,                  // data
	            PlotOrientation.HORIZONTAL, // orientation
	            true,                     // include legend
	            true,                     // tooltips
	            false                     // urls
	        );
		
		CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis axis = plot.getDomainAxis();
        plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.setRangeGridlinesVisible(true);
        axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setBaseItemLabelsVisible(true);
        BarRenderer r = (BarRenderer) renderer;
        r.setItemMargin(0.05);
        
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(Boolean.TRUE);
        ItemLabelPosition p = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.TOP_CENTER, 0.0
        );
        renderer.setBasePositiveItemLabelPosition(p);
		
        /* Aumentando tamao de la Barra */
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setCategoryMargin(0.0);
        categoryAxis.setUpperMargin(0.02);
        categoryAxis.setLowerMargin(0.02);
        
      
		/* adicionando subtitulo */
		if (StringUtils.isNotBlank(graphParam.getSubTituloGrafico())) {
			Font font = new Font("Dialog", Font.PLAIN, 12);
			chart.addSubtitle(new TextTitle(
					graphParam.getSubTituloGrafico(), font));
		}
		
        graphResult.setPieChart(chart);
		log.debug("Se genero el Grafico BAR 3D Horizontal");
		return graphResult;
	}
	
	
	

}
