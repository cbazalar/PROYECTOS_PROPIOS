package biz.belcorp.ssicc.web.framework.util;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class DigitacionCDRDataModel extends ListDataModel<DatosCabeceraCDRTO> implements SelectableDataModel<DatosCabeceraCDRTO> {

	public DigitacionCDRDataModel() {
	}

	public DigitacionCDRDataModel(List<DatosCabeceraCDRTO> data) {
		super(data);
	}

	@Override
	public DatosCabeceraCDRTO getRowData(String rowKey) {
		List<DatosCabeceraCDRTO> cdrtos = (List<DatosCabeceraCDRTO>) getWrappedData();
		for (DatosCabeceraCDRTO datosCabeceraCDRTO : cdrtos) {
			if(datosCabeceraCDRTO.equals(rowKey)){
				return datosCabeceraCDRTO;
			}
		}
		return null;
	}
	
	@Override
	public Object getRowKey(DatosCabeceraCDRTO datosCabeceraCDRTO) {
		return datosCabeceraCDRTO.getCodigoVentaCambia();
	}	
}
