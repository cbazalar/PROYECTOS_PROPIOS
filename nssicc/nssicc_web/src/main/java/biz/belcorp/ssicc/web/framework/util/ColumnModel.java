package biz.belcorp.ssicc.web.framework.util;

import java.io.Serializable;

public class ColumnModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String header;
    private String property;
        
	public ColumnModel(String header, String property) {
		super();
		this.header = header;
		this.property = property;
	}
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
}
