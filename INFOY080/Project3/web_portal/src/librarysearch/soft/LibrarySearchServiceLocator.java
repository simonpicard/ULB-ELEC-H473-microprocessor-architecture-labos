/**
 * LibrarySearchServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package librarysearch.soft;

public class LibrarySearchServiceLocator extends org.apache.axis.client.Service implements librarysearch.soft.LibrarySearchService {

    public LibrarySearchServiceLocator() {
    }


    public LibrarySearchServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LibrarySearchServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LibrarySearchServicePort
    private java.lang.String LibrarySearchServicePort_address = "http://localhost:8080/ode/processes/LibrarySearchService";

    public java.lang.String getLibrarySearchServicePortAddress() {
        return LibrarySearchServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LibrarySearchServicePortWSDDServiceName = "LibrarySearchServicePort";

    public java.lang.String getLibrarySearchServicePortWSDDServiceName() {
        return LibrarySearchServicePortWSDDServiceName;
    }

    public void setLibrarySearchServicePortWSDDServiceName(java.lang.String name) {
        LibrarySearchServicePortWSDDServiceName = name;
    }

    public librarysearch.soft.LibrarySearch getLibrarySearchServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LibrarySearchServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLibrarySearchServicePort(endpoint);
    }

    public librarysearch.soft.LibrarySearch getLibrarySearchServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            librarysearch.soft.LibrarySearchSOAPBindingStub _stub = new librarysearch.soft.LibrarySearchSOAPBindingStub(portAddress, this);
            _stub.setPortName(getLibrarySearchServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLibrarySearchServicePortEndpointAddress(java.lang.String address) {
        LibrarySearchServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (librarysearch.soft.LibrarySearch.class.isAssignableFrom(serviceEndpointInterface)) {
                librarysearch.soft.LibrarySearchSOAPBindingStub _stub = new librarysearch.soft.LibrarySearchSOAPBindingStub(new java.net.URL(LibrarySearchServicePort_address), this);
                _stub.setPortName(getLibrarySearchServicePortWSDDServiceName());
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
        if ("LibrarySearchServicePort".equals(inputPortName)) {
            return getLibrarySearchServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:soft.librarysearch", "LibrarySearchService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:soft.librarysearch", "LibrarySearchServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LibrarySearchServicePort".equals(portName)) {
            setLibrarySearchServicePortEndpointAddress(address);
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
