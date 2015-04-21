package librarysearch.soft;

public class LibrarySearchServicePortTypeProxy implements librarysearch.soft.LibrarySearchServicePortType {
  private String _endpoint = null;
  private librarysearch.soft.LibrarySearchServicePortType librarySearchServicePortType = null;
  
  public LibrarySearchServicePortTypeProxy() {
    _initLibrarySearchServicePortTypeProxy();
  }
  
  public LibrarySearchServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initLibrarySearchServicePortTypeProxy();
  }
  
  private void _initLibrarySearchServicePortTypeProxy() {
    try {
      librarySearchServicePortType = (new librarysearch.soft.LibrarySearchServiceLocator()).getLibrarySearchServicePort();
      if (librarySearchServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)librarySearchServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)librarySearchServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (librarySearchServicePortType != null)
      ((javax.xml.rpc.Stub)librarySearchServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public librarysearch.soft.LibrarySearchServicePortType getLibrarySearchServicePortType() {
    if (librarySearchServicePortType == null)
      _initLibrarySearchServicePortTypeProxy();
    return librarySearchServicePortType;
  }
  
  public librarysearch.soft.LibrarySearchResponse process(librarysearch.soft.LibrarySearchRequest body) throws java.rmi.RemoteException{
    if (librarySearchServicePortType == null)
      _initLibrarySearchServicePortTypeProxy();
    return librarySearchServicePortType.process(body);
  }
  
  
}