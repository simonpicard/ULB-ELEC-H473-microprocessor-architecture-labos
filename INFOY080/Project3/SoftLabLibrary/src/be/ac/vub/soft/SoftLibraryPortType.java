/**
 * SoftLibraryPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package be.ac.vub.soft;

public interface SoftLibraryPortType extends java.rmi.Remote {
    public be.ac.vub.soft.Book[] getAllBooks(java.lang.Object all) throws java.rmi.RemoteException;
    public be.ac.vub.soft.Book[] searchBooks(java.lang.String query) throws java.rmi.RemoteException;
}
