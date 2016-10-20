package biz.belcorp.ssicc.dao.sisicc.util;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.commons.lang.StringUtils;

public class InterfazNombreVariableFilter implements FilenameFilter {

	private String inicio;

	private String extension;

	public InterfazNombreVariableFilter(String inicio, String extension) {
		this.inicio = inicio;
		this.extension = extension;
	}

	public boolean accept(File dir, String name) {
		if (StringUtils.isNotBlank(inicio) && StringUtils.isBlank(extension)) {
			
			if(name.lastIndexOf(".") > 0)
				return false;
			else
				return name.toUpperCase().startsWith(inicio.toUpperCase());
			
		}
		
		if (StringUtils.isNotBlank(inicio) && StringUtils.isNotBlank(extension)) {
			return name.toUpperCase().startsWith(inicio.toUpperCase())
					&& name.toUpperCase().endsWith(extension.toUpperCase());
		} else {
			return false;
		}

	}
}