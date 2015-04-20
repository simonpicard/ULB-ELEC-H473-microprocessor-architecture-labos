/**
 * SoftLibrarySoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package be.ac.vub.soft;

public class SoftLibrarySoapBindingSkeleton implements be.ac.vub.soft.SoftLibraryPortType, org.apache.axis.wsdl.Skeleton {
    private be.ac.vub.soft.SoftLibraryPortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "all"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"), java.lang.Object.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getAllBooks", _params, new javax.xml.namespace.QName("", "book"));
        _oper.setReturnType(new javax.xml.namespace.QName("urn:soft.vub.ac.be/", "Book"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:soft.vub.ac.be/", "getAllBooks"));
        _oper.setSoapAction("urn:soft.vub.ac.be/getAllBooks");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getAllBooks") == null) {
            _myOperations.put("getAllBooks", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getAllBooks")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "query"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("searchBooks", _params, new javax.xml.namespace.QName("", "book"));
        _oper.setReturnType(new javax.xml.namespace.QName("urn:soft.vub.ac.be/", "Book"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:soft.vub.ac.be/", "searchBooks"));
        _oper.setSoapAction("urn:soft.vub.ac.be/searchBooks");
        _myOperationsList.add(_oper);
        if (_myOperations.get("searchBooks") == null) {
            _myOperations.put("searchBooks", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("searchBooks")).add(_oper);
    }

    public SoftLibrarySoapBindingSkeleton() {
        this.impl = new be.ac.vub.soft.SoftLibrarySoapBindingImpl();
    }

    public SoftLibrarySoapBindingSkeleton(be.ac.vub.soft.SoftLibraryPortType impl) {
        this.impl = impl;
    }
    public be.ac.vub.soft.Book[] getAllBooks(java.lang.Object all) throws java.rmi.RemoteException
    {
        be.ac.vub.soft.Book[] ret = impl.getAllBooks(all);
        return ret;
    }

    public be.ac.vub.soft.Book[] searchBooks(java.lang.String query) throws java.rmi.RemoteException
    {
        be.ac.vub.soft.Book[] ret = impl.searchBooks(query);
        return ret;
    }

}
