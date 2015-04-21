package librarysearch.soft;

public class LibrarySearchProxy implements librarysearch.soft.LibrarySearch {
  private String _endpoint = null;
  private librarysearch.soft.LibrarySearch librarySearch = null;
  
  public LibrarySearchProxy() {
    _initLibrarySearchProxy();
  }
  
  public LibrarySearchProxy(String endpoint) {
    _endpoint = endpoint;
    _initLibrarySearchProxy();
  }
  
  private void _initLibrarySearchProxy() {
    try {
      librarySearch = (new librarysearch.soft.LibrarySearchServiceLocator()).getLibrarySearchServicePort();
      if (librarySearch != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)librarySearch)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)librarySearch)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (librarySearch != null)
      ((javax.xml.rpc.Stub)librarySearch)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public librarysearch.soft.LibrarySearch getLibrarySearch() {
    if (librarySearch == null)
      _initLibrarySearchProxy();
    return librarySearch;
  }
  
  public librarysearch.soft.BookList process(javax.xml.rpc.holders.StringHolder query) throws java.rmi.RemoteException{
    if (librarySearch == null)
      _initLibrarySearchProxy();
    return librarySearch.process(query);
  }
  
  
}