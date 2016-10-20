package biz.belcorp.ssicc.web.framework.util;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class PedidoDataModel extends ListDataModel<CabeceraDetalleTO> implements SelectableDataModel<CabeceraDetalleTO> {

	public PedidoDataModel() {
	}

	public PedidoDataModel(List<CabeceraDetalleTO> data) {
		super(data);
	}

	@Override
	public CabeceraDetalleTO getRowData(String rowKey) {
		List<CabeceraDetalleTO> CabeceraDetalleTOs = (List<CabeceraDetalleTO>) getWrappedData();
		for (CabeceraDetalleTO CabeceraDetalleTO : CabeceraDetalleTOs) {
			if(CabeceraDetalleTO.equals(rowKey)){
				return CabeceraDetalleTO;
			}
		}
		return null;
	}
	
	@Override
	public Object getRowKey(CabeceraDetalleTO CabeceraDetalleTO) {
		return CabeceraDetalleTO.getCodigoVenta();
	}	
}
