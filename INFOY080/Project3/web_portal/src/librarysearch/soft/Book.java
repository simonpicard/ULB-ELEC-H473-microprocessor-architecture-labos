/**
 * Book.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package librarysearch.soft;

public class Book  implements java.io.Serializable {
    private java.lang.String author;

    private java.math.BigInteger isbn;

    private int pages;

    private java.util.Calendar publicationDate;

    private java.lang.String publisher;

    private java.lang.String review;

    private java.lang.String summary;

    private java.lang.String title;

    public Book() {
    }

    public Book(
           java.lang.String author,
           java.math.BigInteger isbn,
           int pages,
           java.util.Calendar publicationDate,
           java.lang.String publisher,
           java.lang.String review,
           java.lang.String summary,
           java.lang.String title) {
           this.author = author;
           this.isbn = isbn;
           this.pages = pages;
           this.publicationDate = publicationDate;
           this.publisher = publisher;
           this.review = review;
           this.summary = summary;
           this.title = title;
    }


    /**
     * Gets the author value for this Book.
     * 
     * @return author
     */
    public java.lang.String getAuthor() {
        return author;
    }


    /**
     * Sets the author value for this Book.
     * 
     * @param author
     */
    public void setAuthor(java.lang.String author) {
        this.author = author;
    }


    /**
     * Gets the isbn value for this Book.
     * 
     * @return isbn
     */
    public java.math.BigInteger getIsbn() {
        return isbn;
    }


    /**
     * Sets the isbn value for this Book.
     * 
     * @param isbn
     */
    public void setIsbn(java.math.BigInteger isbn) {
        this.isbn = isbn;
    }


    /**
     * Gets the pages value for this Book.
     * 
     * @return pages
     */
    public int getPages() {
        return pages;
    }


    /**
     * Sets the pages value for this Book.
     * 
     * @param pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }


    /**
     * Gets the publicationDate value for this Book.
     * 
     * @return publicationDate
     */
    public java.util.Calendar getPublicationDate() {
        return publicationDate;
    }


    /**
     * Sets the publicationDate value for this Book.
     * 
     * @param publicationDate
     */
    public void setPublicationDate(java.util.Calendar publicationDate) {
        this.publicationDate = publicationDate;
    }


    /**
     * Gets the publisher value for this Book.
     * 
     * @return publisher
     */
    public java.lang.String getPublisher() {
        return publisher;
    }


    /**
     * Sets the publisher value for this Book.
     * 
     * @param publisher
     */
    public void setPublisher(java.lang.String publisher) {
        this.publisher = publisher;
    }


    /**
     * Gets the review value for this Book.
     * 
     * @return review
     */
    public java.lang.String getReview() {
        return review;
    }


    /**
     * Sets the review value for this Book.
     * 
     * @param review
     */
    public void setReview(java.lang.String review) {
        this.review = review;
    }


    /**
     * Gets the summary value for this Book.
     * 
     * @return summary
     */
    public java.lang.String getSummary() {
        return summary;
    }


    /**
     * Sets the summary value for this Book.
     * 
     * @param summary
     */
    public void setSummary(java.lang.String summary) {
        this.summary = summary;
    }


    /**
     * Gets the title value for this Book.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this Book.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Book)) return false;
        Book other = (Book) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.author==null && other.getAuthor()==null) || 
             (this.author!=null &&
              this.author.equals(other.getAuthor()))) &&
            ((this.isbn==null && other.getIsbn()==null) || 
             (this.isbn!=null &&
              this.isbn.equals(other.getIsbn()))) &&
            this.pages == other.getPages() &&
            ((this.publicationDate==null && other.getPublicationDate()==null) || 
             (this.publicationDate!=null &&
              this.publicationDate.equals(other.getPublicationDate()))) &&
            ((this.publisher==null && other.getPublisher()==null) || 
             (this.publisher!=null &&
              this.publisher.equals(other.getPublisher()))) &&
            ((this.review==null && other.getReview()==null) || 
             (this.review!=null &&
              this.review.equals(other.getReview()))) &&
            ((this.summary==null && other.getSummary()==null) || 
             (this.summary!=null &&
              this.summary.equals(other.getSummary()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle())));
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
        if (getAuthor() != null) {
            _hashCode += getAuthor().hashCode();
        }
        if (getIsbn() != null) {
            _hashCode += getIsbn().hashCode();
        }
        _hashCode += getPages();
        if (getPublicationDate() != null) {
            _hashCode += getPublicationDate().hashCode();
        }
        if (getPublisher() != null) {
            _hashCode += getPublisher().hashCode();
        }
        if (getReview() != null) {
            _hashCode += getReview().hashCode();
        }
        if (getSummary() != null) {
            _hashCode += getSummary().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Book.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:soft.librarysearch", "Book"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("author");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:soft.librarysearch", "author"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isbn");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:soft.librarysearch", "isbn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pages");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:soft.librarysearch", "pages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publicationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:soft.librarysearch", "publicationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publisher");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:soft.librarysearch", "publisher"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("review");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:soft.librarysearch", "review"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summary");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:soft.librarysearch", "summary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:soft.librarysearch", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
