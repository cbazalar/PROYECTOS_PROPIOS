/**
 * IConsultaBelcorpserviceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package biz.belcorp.ssicc.service.spusicc.ocr.ws.axis;

public class IConsultaBelcorpserviceLocator extends org.apache.axis.client.Service implements biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpservice {

    // Use to get a proxy class for IConsultaBelcorpPort
    private final java.lang.String IConsultaBelcorpPort_address = "http://172.16.162.22:81/cgi/consultabelcorp3.exe/soap/IConsultaBelcorp";

    public java.lang.String getIConsultaBelcorpPortAddress() {
        return IConsultaBelcorpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IConsultaBelcorpPortWSDDServiceName = "IConsultaBelcorpPort";

    public java.lang.String getIConsultaBelcorpPortWSDDServiceName() {
        return IConsultaBelcorpPortWSDDServiceName;
    }

    public void setIConsultaBelcorpPortWSDDServiceName(java.lang.String name) {
        IConsultaBelcorpPortWSDDServiceName = name;
    }

    public biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorp getIConsultaBelcorpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IConsultaBelcorpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIConsultaBelcorpPort(endpoint);
    }

    public biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorp getIConsultaBelcorpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpbindingStub _stub = new biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpbindingStub(portAddress, this);
            _stub.setPortName(getIConsultaBelcorpPortWSDDServiceName());
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
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorp.class.isAssignableFrom(serviceEndpointInterface)) {
                biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpbindingStub _stub = new biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpbindingStub(new java.net.URL(IConsultaBelcorpPort_address), this);
                _stub.setPortName(getIConsultaBelcorpPortWSDDServiceName());
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
        if ("IConsultaBelcorpPort".equals(inputPortName)) {
            return getIConsultaBelcorpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "IConsultaBelcorpservice");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("IConsultaBelcorpPort"));
        }
        return ports.iterator();
    }

}
