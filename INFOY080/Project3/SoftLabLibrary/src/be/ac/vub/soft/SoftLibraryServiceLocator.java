/**
 * SoftLibraryServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package be.ac.vub.soft;

public class SoftLibraryServiceLocator extends org.apache.axis.client.Service implements be.ac.vub.soft.SoftLibraryService {

    public SoftLibraryServiceLocator() {
    }


    public SoftLibraryServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SoftLibraryServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for soap
    private java.lang.String soap_address = "http://localhost:8080/SoftLibraryService";

    public java.lang.String getsoapAddress() {
        return soap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String soapWSDDServiceName = "soap";

    public java.lang.String getsoapWSDDServiceName() {
        return soapWSDDServiceName;
    }

    public void setsoapWSDDServiceName(java.lang.String name) {
        soapWSDDServiceName = name;
    }

    public be.ac.vub.soft.SoftLibraryPortType getsoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(soap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsoap(endpoint);
    }

    public be.ac.vub.soft.SoftLibraryPortType getsoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            be.ac.vub.soft.SoftLibrarySoapBindingStub _stub = new be.ac.vub.soft.SoftLibrarySoapBindingStub(portAddress, this);
            _stub.setPortName(getsoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsoapEndpointAddress(java.lang.String address) {
        soap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (be.ac.vub.soft.SoftLibraryPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                be.ac.vub.soft.SoftLibrarySoapBindingStub _stub = new be.ac.vub.soft.SoftLibrarySoapBindingStub(new java.net.URL(soap_address), this);
                _stub.setPortName(getsoapWSDDServiceName());
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
        if ("soap".equals(inputPortName)) {
            return getsoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:soft.vub.ac.be/", "SoftLibraryService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:soft.vub.ac.be/", "soap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("soap".equals(portName)) {
            setsoapEndpointAddress(address);
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
