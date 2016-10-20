/**
 * WsPortalesPROLLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package biz.belcorp.ssicc.service.spusicc.pedido.ws.axis;

public class WsPortalesPROLLocator extends org.apache.axis.client.Service implements biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROL {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Use to get a proxy class for wsPortalesPROLSoap
    private String wsPortalesPROLSoap_address = "http://172.16.229.29/wsprol/wsPortalesPROL.asmx";

    public java.lang.String getwsPortalesPROLSoapAddress() {
        return wsPortalesPROLSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wsPortalesPROLSoapWSDDServiceName = "wsPortalesPROLSoap";

    public java.lang.String getwsPortalesPROLSoapWSDDServiceName() {
        return wsPortalesPROLSoapWSDDServiceName;
    }

    public void setwsPortalesPROLSoapWSDDServiceName(java.lang.String name) {
        wsPortalesPROLSoapWSDDServiceName = name;
    }

    public biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap getwsPortalesPROLSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsPortalesPROLSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwsPortalesPROLSoap(endpoint);
    }

    public biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap getwsPortalesPROLSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoapStub _stub = new biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoapStub(portAddress, this);
            _stub.setPortName(getwsPortalesPROLSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }


    // Use to get a proxy class for wsPortalesPROLSoap12
    private java.lang.String wsPortalesPROLSoap12_address = "http://172.16.229.29/wsprol/wsPortalesPROL.asmx";

    public java.lang.String getwsPortalesPROLSoap12Address() {
        return wsPortalesPROLSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wsPortalesPROLSoap12WSDDServiceName = "wsPortalesPROLSoap12";

    public java.lang.String getwsPortalesPROLSoap12WSDDServiceName() {
        return wsPortalesPROLSoap12WSDDServiceName;
    }

    public void setwsPortalesPROLSoap12WSDDServiceName(java.lang.String name) {
        wsPortalesPROLSoap12WSDDServiceName = name;
    }

    public biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap getwsPortalesPROLSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsPortalesPROLSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwsPortalesPROLSoap12(endpoint);
    }

    public biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap getwsPortalesPROLSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap12Stub _stub = new biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap12Stub(portAddress, this);
            _stub.setPortName(getwsPortalesPROLSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoapStub _stub = new biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoapStub(new java.net.URL(wsPortalesPROLSoap_address), this);
                _stub.setPortName(getwsPortalesPROLSoapWSDDServiceName());
                return _stub;
            }
            if (biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap.class.isAssignableFrom(serviceEndpointInterface)) {
            	biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap12Stub _stub = new biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap12Stub(new java.net.URL(wsPortalesPROLSoap12_address), this);
                _stub.setPortName(getwsPortalesPROLSoap12WSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("wsPortalesPROLSoap".equals(inputPortName)) {
            return getwsPortalesPROLSoap();
        }
        else if ("wsPortalesPROLSoap12".equals(inputPortName)) {
            return getwsPortalesPROLSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "wsPortalesPROL");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("wsPortalesPROLSoap"));
            ports.add(new javax.xml.namespace.QName("wsPortalesPROLSoap12"));
        }
        return ports.iterator();
    }

	/**
	 * @param wsPortalesPROLSoap_address the wsPortalesPROLSoap_address to set
	 */
	public void setWsPortalesPROLSoapAddress(String wsPortalesPROLSoap_address) {
		this.wsPortalesPROLSoap_address = wsPortalesPROLSoap_address;
	}

}
