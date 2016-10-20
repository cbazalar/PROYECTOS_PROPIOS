package biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia;

public class WsInfoCrediticiaLocator extends org.apache.axis.client.Service implements biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticia {

    public WsInfoCrediticiaLocator() {
    }


    public WsInfoCrediticiaLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsInfoCrediticiaLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for wsInfoCrediticiaSoap
    private java.lang.String wsInfoCrediticiaSoap_address = "http://200.87.204.148/wsBuroCorporativo/wsInfoCrediticia.asmx";

    public java.lang.String getwsInfoCrediticiaSoapAddress() {
        return wsInfoCrediticiaSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wsInfoCrediticiaSoapWSDDServiceName = "wsInfoCrediticiaSoap";

    public java.lang.String getwsInfoCrediticiaSoapWSDDServiceName() {
        return wsInfoCrediticiaSoapWSDDServiceName;
    }

    public void setwsInfoCrediticiaSoapWSDDServiceName(java.lang.String name) {
        wsInfoCrediticiaSoapWSDDServiceName = name;
    }

    public biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoap getwsInfoCrediticiaSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsInfoCrediticiaSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwsInfoCrediticiaSoap(endpoint);
    }

    public biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoap getwsInfoCrediticiaSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoapStub _stub = new biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoapStub(portAddress, this);
            _stub.setPortName(getwsInfoCrediticiaSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwsInfoCrediticiaSoapEndpointAddress(java.lang.String address) {
        wsInfoCrediticiaSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoap.class.isAssignableFrom(serviceEndpointInterface)) {
            	biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoapStub _stub = new biz.belcorp.ssicc.service.spusicc.ocr.ws.infocrediticia.WsInfoCrediticiaSoapStub(new java.net.URL(wsInfoCrediticiaSoap_address), this);
                _stub.setPortName(getwsInfoCrediticiaSoapWSDDServiceName());
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
        java.lang.String inputPortName = portName.getLocalPart();
        if ("wsInfoCrediticiaSoap".equals(inputPortName)) {
            return getwsInfoCrediticiaSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "wsInfoCrediticia");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "wsInfoCrediticiaSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("wsInfoCrediticiaSoap".equals(portName)) {
            setwsInfoCrediticiaSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
