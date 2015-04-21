/**
 * LibrarySearchResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package librarysearch.soft;

public class LibrarySearchResponse  implements java.io.Serializable {
    private java.lang.String query;

    private librarysearch.soft.BookList books;

    public LibrarySearchResponse() {
    }

    public LibrarySearchResponse(
           java.lang.String query,
           librarysearch.soft.BookList books) {
           this.query = query;
           this.books = books;
    }


    /**
     * Gets the query value for this LibrarySearchResponse.
     * 
     * @return query
     */
    public java.lang.String getQuery() {
        return query;
    }


    /**
     * Sets the query value for this LibrarySearchResponse.
     * 
     * @param query
     */
    public void setQuery(java.lang.String query) {
        this.query = query;
    }


    /**
     * Gets the books value for this LibrarySearchResponse.
     * 
     * @return books
     */
    public librarysearch.soft.BookList getBooks() {
        return books;
    }


    /**
     * Sets the books value for this LibrarySearchResponse.
     * 
     * @param books
     */
    public void setBooks(librarysearch.soft.BookList books) {
        this.books = books;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LibrarySearchResponse)) return false;
        LibrarySearchResponse other = (LibrarySearchResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.query==null && other.getQuery()==null) || 
             (this.query!=null &&
              this.query.equals(other.getQuery()))) &&
            ((this.books==null && other.getBooks()==null) || 
             (this.books!=null &&
              this.books.equals(other.getBooks())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getQuery() != null) {
            _hashCode += getQuery().hashCode();
        }
        if (getBooks() != null) {
            _hashCode += getBooks().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LibrarySearchResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:soft.librarysearch", ">LibrarySearchResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("query");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:soft.librarysearch", "query"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("books");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:soft.librarysearch", "books"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:soft.librarysearch", "BookList"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
