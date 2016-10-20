package biz.belcorp.ssicc.web;

import java.awt.GraphicsEnvironment;
import java.util.Arrays;

import javax.swing.JButton;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String directorio = "D:\\reportes\\";
		String jrxml = directorio + "reporteRECEnviarUnidadesAlmacenVirtualCabeceraXLS.jrxml";
		String jasper = directorio + "reporteRECEnviarUnidadesAlmacenVirtualCabeceraXLS.jasper";
		try {
			JasperCompileManager.compileReportToFile(jrxml,	jasper);
			System.out.println("done");
		} catch (JRException e) {
			e.printStackTrace();
		}

		//System.out.println(Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
	    
		
		
	}

}
