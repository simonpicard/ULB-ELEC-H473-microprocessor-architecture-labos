<?xml version="1.0" encoding="ISO-8859-1" ?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html"
            doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"
            doctype-system="http://www.w3.org/TR/html401/loose.dtd" />

<xsl:template match="Page">
  <html>
    <head>
      <title><xsl:value-of select="Title" /></title>
      <link type="text/css" href="web_portal.css" rel="stylesheet" />
    </head>
    <body>
      <table width="100%">
        <tr>
          <td width="25%" style="text-align: left;"><h1><xsl:value-of select="Title" /></h1></td>
          <td width="75%" style="text-align: right;">
            <xsl:value-of select="Description" /><br />
            <a href="web_portal?Page=Logout">Logout</a>
          </td>
        </tr>
      </table>
      <hr />
      <table width="60%">
        <tr>
          <td>
            <xsl:apply-templates select="RegistrationPage_Get" />
            <xsl:apply-templates select="RegistrationPage_Post" />
            <xsl:apply-templates select="RegistrationPage_Error" />
            <xsl:apply-templates select="LoginPage_Get" />
            <xsl:apply-templates select="LoginPage_Post" />
            <xsl:apply-templates select="LoginPage_Error" />
            <xsl:apply-templates select="QueryPage_Get" />
            <xsl:apply-templates select="QueryPage_Error" />
            <xsl:apply-templates select="AdministrationPage_Get" />
            <xsl:apply-templates select="AdministrationPage_Post" />
            <xsl:apply-templates select="AdministrationPage_Error" />
            <xsl:apply-templates select="OperationPage_Get" />
            <xsl:apply-templates select="OperationPage_Post" />
            <xsl:apply-templates select="OperationPage_Error" />
            <xsl:apply-templates select="LogoutPage_Get" />
            <xsl:apply-templates select="LogoutPage_Error" />
          </td>
        </tr>
      </table>
      <hr />
      <table width="100%">
        <tr>
          <td width="35%" style="text-align: left;"><a href="web_portal">Semantic Web Portal</a> build 2003.05.25</td>
          <td width="30%" style="text-align: center;"><xsl:value-of select="Time" /></td>
          <td width="35%" style="text-align: right;">
            Copyright &#169; 2002&#8211;2003 <a href="http://www.joncheere.be/">Niels Joncheere</a>
          </td>
        </tr>
      </table>
    </body>
  </html>
</xsl:template>

<xsl:template match="RegistrationPage_Get">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Registration Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Subscription</th>
          <td width="50%">
            <input type="radio" name="Subscription" value="Free" checked="checked" />Free Subscription<br />
            <input type="radio" name="Subscription" value="Cheap" />Cheap Subscription<br />
            <input type="radio" name="Subscription" value="Expensive" />Expensive Subscription<br />
          </td>
        </tr>
        <tr>
          <th>Username</th>
          <td><input name="Username" /></td>
        </tr>
        <tr>
          <th>Password (max. 10 characters)</th>
          <td><input type="password" name="Password" /></td>
        </tr>
        <tr>
          <th>Repeat Password</th>
          <td><input type="password" name="RepeatPassword" /></td>
        </tr>
        <tr>
          <th>First Name</th>
          <td><input name="FirstName" /></td>
        </tr>
        <tr>
          <th>Last Name</th>
          <td><input name="LastName" /></td>
        </tr>
        <tr>
          <th>E-mail Address</th>
          <td><input name="EmailAddress" /></td>
        </tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Registration" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="RegistrationPage_Post">
  <p>Your account was created successfully:</p>
  <xsl:apply-templates select="FreeSubscription" />
  <xsl:apply-templates select="CheapSubscription" />
  <xsl:apply-templates select="ExpensiveSubscription" />
  <p>Click <a href="web_portal?Page=Login">here</a> to continue.</p>
</xsl:template>

<xsl:template match="FreeSubscription">
  <fieldset>
    <legend>Free Subscription</legend>
    <table width="100%">
      <tr>
        <th width="50%">Username</th>
        <td width="50%"><xsl:value-of select="username" /></td>
      </tr>
      <tr>
        <th>First Name</th>
        <td><xsl:value-of select="firstName" /></td>
      </tr>
      <tr>
        <th>Last Name</th>
        <td><xsl:value-of select="lastName" /></td>
      </tr>
      <tr>
        <th>E-mail Address</th>
        <td>
          <a>
            <xsl:attribute name="href">mailto:<xsl:value-of select="emailAddress" /></xsl:attribute>
            <xsl:value-of select="emailAddress" />
          </a>
        </td>
      </tr>
      <tr>
        <th>Last Login</th>
	<td><xsl:value-of select="lastLogin" /></td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="CheapSubscription">
  <fieldset>
    <legend>Cheap Subscription</legend>
    <table width="100%">
      <tr>
        <th width="50%">Username</th>
        <td width="50%"><xsl:value-of select="username" /></td>
      </tr>
      <tr>
        <th>First Name</th>
        <td><xsl:value-of select="firstName" /></td>
      </tr>
      <tr>
        <th>Last Name</th>
        <td><xsl:value-of select="lastName" /></td>
      </tr>
      <tr>
        <th>E-mail Address</th>
        <td>
          <a>
            <xsl:attribute name="href">mailto:<xsl:value-of select="emailAddress" /></xsl:attribute>
            <xsl:value-of select="emailAddress" />
          </a>
        </td>
      </tr>
      <tr>
        <th>Last Login</th>
	<td><xsl:value-of select="lastLogin" /></td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="ExpensiveSubscription">
  <fieldset>
    <legend>Expensive Subscription</legend>
    <table width="100%">
      <tr>
        <th width="50%">Username</th>
        <td width="50%"><xsl:value-of select="username" /></td>
      </tr>
      <tr>
        <th>First Name</th>
        <td><xsl:value-of select="firstName" /></td>
      </tr>
      <tr>
        <th>Last Name</th>
        <td><xsl:value-of select="lastName" /></td>
      </tr>
      <tr>
        <th>E-mail Address</th>
        <td>
          <a>
            <xsl:attribute name="href">mailto:<xsl:value-of select="emailAddress" /></xsl:attribute>
            <xsl:value-of select="emailAddress" />
          </a>
        </td>
      </tr>
      <tr>
        <th>Last Login</th>
	<td><xsl:value-of select="lastLogin" /></td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="RegistrationPage_Error">
  <p><xsl:value-of select="Message" /></p>
</xsl:template>

<xsl:template match="LoginPage_Get">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Login Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Username</th>
          <td width="50%"><input name="Username" /></td>
        </tr>
        <tr>
          <th>Password</th>
          <td><input type="password" name="Password" /></td>
        </tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Login" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
  <p><a href="web_portal?Page=Registration">Request an Account</a></p>
</xsl:template>

<xsl:template match="LoginPage_Post">
  <p>Your are now logged in as:</p>
  <xsl:apply-templates select="FreeSubscription" />
  <xsl:apply-templates select="CheapSubscription" />
  <xsl:apply-templates select="ExpensiveSubscription" />
  <xsl:apply-templates select="Operator" />
  <xsl:apply-templates select="ExternalAdministrator" />
  <xsl:apply-templates select="RegularAdministrator" />
  <xsl:apply-templates select="ExpertAdministrator" />
  <p>Click <a><xsl:attribute name="href"><xsl:value-of select="DefaultPage" /></xsl:attribute>here</a> to continue.</p>
</xsl:template>

<xsl:template match="Operator">
  <fieldset>
    <legend>Operator</legend>
    <table width="100%">
      <tr>
        <th width="50%">Username</th>
        <td width="50%"><xsl:value-of select="username" /></td>
      </tr>
      <tr>
        <th>First Name</th>
        <td><xsl:value-of select="firstName" /></td>
      </tr>
      <tr>
        <th>Last Name</th>
        <td><xsl:value-of select="lastName" /></td>
      </tr>
      <tr>
        <th>E-mail Address</th>
        <td>
          <a>
            <xsl:attribute name="href">mailto:<xsl:value-of select="emailAddress" /></xsl:attribute>
            <xsl:value-of select="emailAddress" />
          </a>
        </td>
      </tr>
      <tr>
        <th>Last Login</th>
	<td><xsl:value-of select="lastLogin" /></td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="ExternalAdministrator">
  <fieldset>
    <legend>External Administrator</legend>
    <table width="100%">
      <tr>
        <th width="50%">Username</th>
        <td width="50%"><xsl:value-of select="username" /></td>
      </tr>
      <tr>
        <th>First Name</th>
        <td><xsl:value-of select="firstName" /></td>
      </tr>
      <tr>
        <th>Last Name</th>
        <td><xsl:value-of select="lastName" /></td>
      </tr>
      <tr>
        <th>E-mail Address</th>
        <td>
          <a>
            <xsl:attribute name="href">mailto:<xsl:value-of select="emailAddress" /></xsl:attribute>
            <xsl:value-of select="emailAddress" />
          </a>
        </td>
      </tr>
      <tr>
        <th>Last Login</th>
	<td><xsl:value-of select="lastLogin" /></td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="RegularAdministrator">
  <fieldset>
    <legend>Regular Administrator</legend>
    <table width="100%">
      <tr>
        <th width="50%">Username</th>
        <td width="50%"><xsl:value-of select="username" /></td>
      </tr>
      <tr>
        <th>First Name</th>
        <td><xsl:value-of select="firstName" /></td>
      </tr>
      <tr>
        <th>Last Name</th>
        <td><xsl:value-of select="lastName" /></td>
      </tr>
      <tr>
        <th>E-mail Address</th>
        <td>
          <a>
            <xsl:attribute name="href">mailto:<xsl:value-of select="emailAddress" /></xsl:attribute>
            <xsl:value-of select="emailAddress" />
          </a>
        </td>
      </tr>
      <tr>
        <th>Last Login</th>
	<td><xsl:value-of select="lastLogin" /></td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="ExpertAdministrator">
  <fieldset>
    <legend>Expert Administrator</legend>
    <table width="100%">
      <tr>
        <th width="50%">Username</th>
        <td width="50%"><xsl:value-of select="username" /></td>
      </tr>
      <tr>
        <th>First Name</th>
        <td><xsl:value-of select="firstName" /></td>
      </tr>
      <tr>
        <th>Last Name</th>
        <td><xsl:value-of select="lastName" /></td>
      </tr>
      <tr>
        <th>E-mail Address</th>
        <td>
          <a>
            <xsl:attribute name="href">mailto:<xsl:value-of select="emailAddress" /></xsl:attribute>
            <xsl:value-of select="emailAddress" />
          </a>
        </td>
      </tr>
      <tr>
        <th>Last Login</th>
	<td><xsl:value-of select="lastLogin" /></td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="LoginPage_Error">
  <p><xsl:value-of select="Message" /></p>
</xsl:template>

<xsl:template match="QueryPage_Get">
  <xsl:apply-templates select="QueryForm" />
  <xsl:apply-templates select="QueryResult" />
</xsl:template>

<xsl:template match="QueryForm">
  <p>Follow these links to read the latest additions to the database:</p>
  <p>
    <a href="web_portal?Page=Query&amp;QueryType=LatestAdditions&amp;InformationType=Book">Books</a> |
    <a href="web_portal?Page=Query&amp;QueryType=LatestAdditions&amp;InformationType=Article">Articles</a> |
    <a href="web_portal?Page=Query&amp;QueryType=LatestAdditions&amp;InformationType=Report">Reports</a> |
    <a href="web_portal?Page=Query&amp;QueryType=LatestAdditions&amp;InformationType=Conference">Conferences</a> |
    <a href="web_portal?Page=Query&amp;QueryType=LatestAdditions&amp;InformationType=SoftwareRepository">Software Repositories</a> |
    <a href="web_portal?Page=Query&amp;QueryType=LatestAdditions&amp;InformationType=InterestingWebsite">Interesting Websites</a>
  </p>
  <p>Alternatively, you can use this form to do a full-text search over all information in the database:</p>
  <form method="get" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Query Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Information Type</th>
          <td width="50%">
            <input type="radio" name="InformationType" value="Book" checked="checked" />Books<br />
            <input type="radio" name="InformationType" value="Article" />Articles<br />
            <input type="radio" name="InformationType" value="Report" />Reports<br />
            <input type="radio" name="InformationType" value="Conference" />Conferences<br />
            <input type="radio" name="InformationType" value="SoftwareRepository" />Software Repositories<br />
            <input type="radio" name="InformationType" value="InterestingWebsite" />Interesting Websites<br />
          </td>
        </tr>
        <tr>
          <th>Query String</th>
          <td><input name="QueryString" /></td>
        </tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Query" />
	    <input type="hidden" name="QueryType" value="FullText" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="QueryResult" >
  <p>The following information is the result of your query:</p>
  <xsl:apply-templates select="Book" />
  <xsl:apply-templates select="Article" />
  <xsl:apply-templates select="Report" />
  <xsl:apply-templates select="Conference" />
  <xsl:apply-templates select="SoftwareRepository" />
  <xsl:apply-templates select="InterestingWebsite" />
  <p />
</xsl:template>
 
<xsl:template match="Book">
  <fieldset>
    <legend>Book</legend>
    <table width="100%">
      <tr>
        <th width="50%">Date Added</th>
        <td width="50%"><xsl:value-of select="dateAdded" /></td>
      </tr>
      <tr>
        <th>Author</th>
        <td><xsl:value-of select="author" /></td>
      </tr>
      <tr>
        <th>ISBN</th>
        <td><xsl:value-of select="isbn" /></td>
      </tr>
      <tr>
        <th>Pages</th>
        <td><xsl:value-of select="pages" /></td>
      </tr>
      <tr>
        <th>Publication Date</th>
        <td><xsl:value-of select="publicationDate" /></td>
      </tr>
      <tr>
        <th>Publisher</th>
        <td><xsl:value-of select="publisher" /></td>
      </tr>
      <tr>
        <th>Review</th>
        <td><xsl:value-of select="review" /></td>
      </tr>
      <tr>
        <th>Summary</th>
        <td><xsl:value-of select="summary" /></td>
      </tr>
      <tr>
        <th>Title</th>
        <td><xsl:value-of select="title" /></td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="Article">
  <fieldset>
    <legend>Article</legend>
    <table width="100%">
      <tr>
        <th width="50%">Date Added</th>
        <td width="50%"><xsl:value-of select="dateAdded" /></td>
      </tr>
      <tr>
        <th>Author</th>
        <td><xsl:value-of select="author" /></td>
      </tr>
      <tr>
        <th>Publication Date</th>
        <td><xsl:value-of select="publicationDate" /></td>
      </tr>
      <tr>
        <th>Review</th>
        <td><xsl:value-of select="review" /></td>
      </tr>
      <tr>
        <th>Summary</th>
        <td><xsl:value-of select="summary" /></td>
      </tr>
      <tr>
        <th>Title</th>
        <td><xsl:value-of select="title" /></td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="Report">
  <fieldset>
    <legend>Report</legend>
    <table width="100%">
      <tr>
        <th width="50%">Date Added</th>
        <td width="50%"><xsl:value-of select="dateAdded" /></td>
      </tr>
      <tr>
        <th>Author</th>
        <td><xsl:value-of select="author" /></td>
      </tr>
      <tr>
        <th>Publication Date</th>
        <td><xsl:value-of select="publicationDate" /></td>
      </tr>
      <tr>
        <th>Review</th>
        <td><xsl:value-of select="review" /></td>
      </tr>
      <tr>
        <th>Summary</th>
        <td><xsl:value-of select="summary" /></td>
      </tr>
      <tr>
        <th>Title</th>
        <td><xsl:value-of select="title" /></td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="Conference">
  <fieldset>
    <legend>Conference</legend>
    <table width="100%">
      <tr>
        <th width="50%">Date Added</th>
        <td width="50%"><xsl:value-of select="dateAdded" /></td>
      </tr>
      <tr>
        <th>Date</th>
        <td><xsl:value-of select="date" /></td>
      </tr>
      <tr>
        <th>Location</th>
        <td><xsl:value-of select="location" /></td>
      </tr>
      <tr>
        <th>Name</th>
        <td><xsl:value-of select="name" /></td>
      </tr>
      <tr>
        <th>Review</th>
        <td><xsl:value-of select="review" /></td>
      </tr>
      <tr>
        <th>Summary</th>
        <td><xsl:value-of select="summary" /></td>
      </tr>
      <tr>
        <th>URL</th>
        <td>
          <a><xsl:attribute name="href"><xsl:value-of select="url" /></xsl:attribute><xsl:value-of select="url" /></a>
        </td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="SoftwareRepository">
  <fieldset>
    <legend>Software Repository</legend>
    <table width="100%">
      <tr>
        <th width="50%">Date Added</th>
        <td width="50%"><xsl:value-of select="dateAdded" /></td>
      </tr>
      <tr>
        <th>Author</th>
        <td><xsl:value-of select="author" /></td>
      </tr>
      <tr>
        <th>Name</th>
        <td><xsl:value-of select="name" /></td>
      </tr>
      <tr>
        <th>URL</th>
        <td>
          <a><xsl:attribute name="href"><xsl:value-of select="url" /></xsl:attribute><xsl:value-of select="url" /></a>
        </td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="InterestingWebsite">
  <fieldset>
    <legend>Interesting Website</legend>
    <table width="100%">
      <tr>
        <th width="50%">Date Added</th>
        <td width="50%"><xsl:value-of select="dateAdded" /></td>
      </tr>
      <tr>
        <th>Author</th>
        <td><xsl:value-of select="author" /></td>
      </tr>
      <tr>
        <th>Review</th>
        <td><xsl:value-of select="review" /></td>
      </tr>
      <tr>
        <th>Summary</th>
        <td><xsl:value-of select="summary" /></td>
      </tr>
      <tr>
        <th>Title</th>
        <td><xsl:value-of select="title" /></td>
      </tr>
      <tr>
        <th>URL</th>
        <td>
          <a><xsl:attribute name="href"><xsl:value-of select="url" /></xsl:attribute><xsl:value-of select="url" /></a>
        </td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="QueryPage_Error">
  <p><xsl:value-of select="Message" /></p>
</xsl:template>

<xsl:template match="AdministrationPage_Get">
  <xsl:apply-templates select="AdministrationForm" />
  <xsl:apply-templates select="UnvalidatedInformation" />
  <xsl:apply-templates select="ValidationForm" />
  <xsl:apply-templates select="AssignInformationType" />
  <xsl:apply-templates select="InsertInformation" />
</xsl:template>

<xsl:template match="AdministrationForm">
  <p>Please choose what kind of operation you want to perform:</p>
  <p>
    <a href="web_portal?Page=Administration&amp;QueryType=ViewUnvalidatedInformation">View Unvalidated Information</a> |
    <a href="web_portal?Page=Administration&amp;QueryType=InsertInformation">Insert Information</a>
  </p>
</xsl:template>

<xsl:template match="UnvalidatedInformation">
  <p>This is a list of all unvalidated information:</p>
  <xsl:apply-templates select="RawData" />
  <p />
</xsl:template>

<xsl:template match="RawData">
  <fieldset>
    <legend>
	  <xsl:choose>
	    <xsl:when test="count(structure/Book) &gt; 0">
		  Raw Data [ <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=StructureOrValidate&amp;InformationType=Book&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Structure / Validate</a> | <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=Delete&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Delete</a> ]
		</xsl:when>
	    <xsl:when test="count(structure/Article) &gt; 0">
		  Raw Data [ <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=StructureOrValidate&amp;InformationType=Article&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Structure / Validate</a> | <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=Delete&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Delete</a> ]
		</xsl:when>
	    <xsl:when test="count(structure/Report) &gt; 0">
		  Raw Data [ <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=StructureOrValidate&amp;InformationType=Report&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Structure / Validate</a> | <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=Delete&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Delete</a> ]
		</xsl:when>
	    <xsl:when test="count(structure/Conference) &gt; 0">
		  Raw Data [ <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=StructureOrValidate&amp;InformationType=Conference&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Structure / Validate</a> | <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=Delete&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Delete</a> ]
		</xsl:when>
	    <xsl:when test="count(structure/SoftwareRepository) &gt; 0">
		  Raw Data [ <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=StructureOrValidate&amp;InformationType=SoftwareRepository&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Structure / Validate</a> | <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=Delete&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Delete</a> ]
		</xsl:when>
	    <xsl:when test="count(structure/InterestingWebsite) &gt; 0">
		  Raw Data [ <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=StructureOrValidate&amp;InformationType=InterestingWebsite&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Structure / Validate</a> | <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=Delete&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Delete</a> ]
		</xsl:when>
		<xsl:otherwise>
		  Raw Data [ <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=AssignInformationType&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Assign Information Type</a> | <a><xsl:attribute name="href">web_portal?Page=Administration&amp;QueryType=Delete&amp;ID=<xsl:value-of select="id" /></xsl:attribute>Delete</a> ]
		</xsl:otherwise>
      </xsl:choose>
	</legend>
    <table width="100%">
      <tr>
        <th width="50%">Source</th>
        <td width="50%"><xsl:value-of select="source" /></td>
      </tr>
      <tr>
        <td colspan="2">
          <xsl:apply-templates select="structure/Book" />
          <xsl:apply-templates select="structure/Article" />
          <xsl:apply-templates select="structure/Report" />
          <xsl:apply-templates select="structure/Conference" />
          <xsl:apply-templates select="structure/SoftwareRepository" />
          <xsl:apply-templates select="structure/InterestingWebsite" />
        </td>
      </tr>
    </table>
  </fieldset>
</xsl:template>

<xsl:template match="ValidationForm">
  <xsl:if test="count(RawData/structure/Book) &gt; 0">
    <xsl:call-template name="BookValidationForm" />
  </xsl:if>
  <xsl:if test="count(RawData/structure/Article) &gt; 0">
    <xsl:call-template name="ArticleValidationForm" />
  </xsl:if>
  <xsl:if test="count(RawData/structure/Report) &gt; 0">
    <xsl:call-template name="ReportValidationForm" />
  </xsl:if>
  <xsl:if test="count(RawData/structure/Conference) &gt; 0">
    <xsl:call-template name="ConferenceValidationForm" />
  </xsl:if>
  <xsl:if test="count(RawData/structure/SoftwareRepository) &gt; 0">
    <xsl:call-template name="SoftwareRepositoryValidationForm" />
  </xsl:if>
  <xsl:if test="count(RawData/structure/InterestingWebsite) &gt; 0">
    <xsl:call-template name="InterestingWebsiteValidationForm" />
  </xsl:if>
</xsl:template>

<xsl:template match="BookValidationForm" name="BookValidationForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Validation Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Source</th>
          <td width="50%"><textarea name="Source"><xsl:value-of select="RawData/source" /></textarea></td>
        </tr>
        <tr>
          <td colspan="2">
            <fieldset>
              <legend>Book</legend>
              <table width="100%">
                <tr>
                  <th width="50%">Author</th>
                  <td width="50%"><input name="Author"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Book/author" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>ISBN</th>
                  <td><input name="ISBN"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Book/isbn" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Pages</th>
                  <td><input name="Pages"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Book/pages" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Publication Date (yyyy-mm-dd)</th>
                  <td><input name="PublicationDate"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Book/publicationDate" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Publisher</th>
                  <td><input name="Publisher"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Book/publisher" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Review</th>
                  <td><input name="Review"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Book/review" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Summary</th>
                  <td><input name="Summary"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Book/summary" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Title</th>
                  <td><input name="Title"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Book/title" /></xsl:attribute></input></td>
                </tr>
              </table>
            </fieldset>
          </td>
        </tr>
	<tr><th /><td><input type="checkbox" name="Validate" />Validate This Record</td></tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="StructureOrValidate" />
	    <input type="hidden" name="ID">
              <xsl:attribute name="value"><xsl:value-of select="RawData/id" /></xsl:attribute>
            </input>
			<input type="hidden" name="InformationType" value="Book" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="ArticleValidationForm" name="ArticleValidationForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Validation Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Source</th>
          <td width="50%"><textarea name="Source"><xsl:value-of select="RawData/source" /></textarea></td>
        </tr>
        <tr>
          <td colspan="2">
            <fieldset>
              <legend>Article</legend>
              <table width="100%">
                <tr>
                  <th width="50%">Author</th>
                  <td width="50%"><input name="Author"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Article/author" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Publication Date (yyyy-mm-dd)</th>
                  <td><input name="PublicationDate"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Article/publicationDate" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Review</th>
                  <td><input name="Review"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Article/review" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Summary</th>
                  <td><input name="Summary"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Article/summary" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Title</th>
                  <td><input name="Title"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Article/title" /></xsl:attribute></input></td>
                </tr>
              </table>
            </fieldset>
          </td>
        </tr>
	<tr><th /><td><input type="checkbox" name="Validate" />Validate This Record</td></tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="StructureOrValidate" />
	    <input type="hidden" name="ID">
              <xsl:attribute name="value"><xsl:value-of select="RawData/id" /></xsl:attribute>
            </input>
			<input type="hidden" name="InformationType" value="Article" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="ReportValidationForm" name="ReportValidationForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Validation Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Source</th>
          <td width="50%"><textarea name="Source"><xsl:value-of select="RawData/source" /></textarea></td>
        </tr>
        <tr>
          <td colspan="2">
            <fieldset>
              <legend>Report</legend>
              <table width="100%">
                <tr>
                  <th width="50%">Author</th>
                  <td width="50%"><input name="Author"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Report/author" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Publication Date (yyyy-mm-dd)</th>
                  <td><input name="PublicationDate"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Report/publicationDate" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Review</th>
                  <td><input name="Review"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Report/review" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Summary</th>
                  <td><input name="Summary"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Report/summary" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Title</th>
                  <td><input name="Title"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Report/title" /></xsl:attribute></input></td>
                </tr>
              </table>
            </fieldset>
          </td>
        </tr>
	<tr><th /><td><input type="checkbox" name="Validate" />Validate This Record</td></tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="StructureOrValidate" />
	    <input type="hidden" name="ID">
              <xsl:attribute name="value"><xsl:value-of select="RawData/id" /></xsl:attribute>
            </input>
			<input type="hidden" name="InformationType" value="Report" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="ConferenceValidationForm" name="ConferenceValidationForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Validation Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Source</th>
          <td width="50%"><textarea name="Source"><xsl:value-of select="RawData/source" /></textarea></td>
        </tr>
        <tr>
          <td colspan="2">
            <fieldset>
              <legend>Conference</legend>
              <table width="100%">
                <tr>
                  <th width="50%">Date (yyyy-mm-dd)</th>
                  <td width="50%"><input name="Date"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Conference/date" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Location</th>
                  <td><input name="Location"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Conference/location" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Name</th>
                  <td><input name="Name"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Conference/name" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Review</th>
                  <td><input name="Review"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Conference/review" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Summary</th>
                  <td><input name="Summary"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Conference/summary" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>URL</th>
                  <td><input name="URL"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/Conference/url" /></xsl:attribute></input></td>
                </tr>
              </table>
            </fieldset>
          </td>
        </tr>
	<tr><th /><td><input type="checkbox" name="Validate" />Validate This Record</td></tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="StructureOrValidate" />
	    <input type="hidden" name="ID">
              <xsl:attribute name="value"><xsl:value-of select="RawData/id" /></xsl:attribute>
            </input>
			<input type="hidden" name="InformationType" value="Conference" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="SoftwareRepositoryValidationForm" name="SoftwareRepositoryValidationForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Validation Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Source</th>
          <td width="50%"><textarea name="Source"><xsl:value-of select="RawData/source" /></textarea></td>
        </tr>
        <tr>
          <td colspan="2">
            <fieldset>
              <legend>Software Repository</legend>
              <table width="100%">
                <tr>
                  <th width="50%">Author</th>
                  <td width="50%"><input name="Author"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/SoftwareRepository/author" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Name</th>
                  <td><input name="Name"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/SoftwareRepository/name" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>URL</th>
                  <td><input name="URL"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/SoftwareRepository/url" /></xsl:attribute></input></td>
                </tr>
              </table>
            </fieldset>
          </td>
        </tr>
	<tr><th /><td><input type="checkbox" name="Validate" />Validate This Record</td></tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="StructureOrValidate" />
	    <input type="hidden" name="ID">
              <xsl:attribute name="value"><xsl:value-of select="RawData/id" /></xsl:attribute>
            </input>
			<input type="hidden" name="InformationType" value="SoftwareRepository" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="InterestingWebsiteValidationForm" name="InterestingWebsiteValidationForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Validation Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Source</th>
          <td width="50%"><textarea name="Source"><xsl:value-of select="RawData/source" /></textarea></td>
        </tr>
        <tr>
          <td colspan="2">
            <fieldset>
              <legend>Interesting Website</legend>
              <table width="100%">
                <tr>
                  <th width="50%">Author</th>
                  <td width="50%"><input name="Author"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/InterestingWebsite/author" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Review</th>
                  <td><input name="Review"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/InterestingWebsite/review" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Summary</th>
                  <td><input name="Summary"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/InterestingWebsite/summary" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>Title</th>
                  <td><input name="Title"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/InterestingWebsite/title" /></xsl:attribute></input></td>
                </tr>
                <tr>
                  <th>URL</th>
                  <td><input name="URL"><xsl:attribute name="value"><xsl:value-of select="RawData/structure/InterestingWebsite/url" /></xsl:attribute></input></td>
                </tr>
              </table>
            </fieldset>
          </td>
        </tr>
	<tr><th /><td><input type="checkbox" name="Validate" />Validate This Record</td></tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="StructureOrValidate" />
	    <input type="hidden" name="ID">
              <xsl:attribute name="value"><xsl:value-of select="RawData/id" /></xsl:attribute>
            </input>
			<input type="hidden" name="InformationType" value="InterestingWebsite" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="AssignInformationType">
  <xsl:apply-templates select="AssignInformationTypeForm" />
  <xsl:apply-templates select="BookValidationForm" />
  <xsl:apply-templates select="ArticleValidationForm" />
  <xsl:apply-templates select="ReportValidationForm" />
  <xsl:apply-templates select="ConferenceValidationForm" />
  <xsl:apply-templates select="SoftwareRepositoryValidationForm" />
  <xsl:apply-templates select="InterestingWebsiteValidationForm" />
</xsl:template>

<xsl:template match="AssignInformationTypeForm">
      <p />
      <form method="get" action="web_portal" accept-charset="ISO-8859-1">
        <fieldset>
          <legend>Assign Information Type Form</legend>
          <table width="100%">
            <tr>
              <th width="50%">Source</th>
              <td width="50%"><xsl:value-of select="RawData/source" /></td>
            </tr>
            <tr>
              <th>Information Type</th>
              <td>
                <input type="radio" name="InformationType" value="Book" checked="checked" />Book<br />
                <input type="radio" name="InformationType" value="Article" />Article<br />
                <input type="radio" name="InformationType" value="Report" />Report<br />
                <input type="radio" name="InformationType" value="Conference" />Conference<br />
                <input type="radio" name="InformationType" value="SoftwareRepository" />Software Repository<br />
                <input type="radio" name="InformationType" value="InterestingWebsite" />Interesting Website<br />
              </td>
            </tr>
            <tr>
              <th />
              <td>
                <input type="submit" />
                <input type="reset" />
                <input type="hidden" name="Page" value="Administration" />
                <input type="hidden" name="QueryType" value="AssignInformationType" />
                <input type="hidden" name="ID">
                  <xsl:attribute name="value"><xsl:value-of select="RawData/id" /></xsl:attribute>
                </input>
              </td>
            </tr>
          </table>
        </fieldset>
      </form>
</xsl:template>

<xsl:template match="InsertInformation">
  <xsl:apply-templates select="InformationTypeForm" />
  <xsl:apply-templates select="InsertBookForm" />
  <xsl:apply-templates select="InsertArticleForm" />
  <xsl:apply-templates select="InsertReportForm" />
  <xsl:apply-templates select="InsertConferenceForm" />
  <xsl:apply-templates select="InsertSoftwareRepositoryForm" />
  <xsl:apply-templates select="InsertInterestingWebsiteForm" />
</xsl:template>

<xsl:template match="InformationTypeForm">
  <p>Please choose which type of information you want to add:</p>
  <form method="get" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Information Type Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Information Type</th>
          <td width="50%">
            <input type="radio" name="InformationType" value="Book" checked="checked" />Book<br />
            <input type="radio" name="InformationType" value="Article" />Article<br />
            <input type="radio" name="InformationType" value="Report" />Report<br />
            <input type="radio" name="InformationType" value="Conference" />Conference<br />
            <input type="radio" name="InformationType" value="SoftwareRepository" />Software Repository<br />
            <input type="radio" name="InformationType" value="InterestingWebsite" />Interesting Website<br />
          </td>
        </tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
            <input type="hidden" name="QueryType" value="InsertInformation" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="InsertBookForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Insert Book Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Author</th>
          <td width="50%"><input name="Author" /></td>
        </tr>
        <tr>
          <th>ISBN</th>
          <td><input name="ISBN" /></td>
        </tr>
        <tr>
          <th>Pages</th>
          <td><input name="Pages" /></td>
        </tr>
        <tr>
          <th>Publication Date (yyyy-mm-dd)</th>
          <td><input name="PublicationDate" /></td>
        </tr>
        <tr>
          <th>Publisher</th>
          <td><input name="Publisher" /></td>
        </tr>
        <tr>
          <th>Review</th>
          <td><input name="Review" /></td>
        </tr>
        <tr>
          <th>Summary</th>
          <td><input name="Summary" /></td>
        </tr>
        <tr>
          <th>Title</th>
          <td><input name="Title" /></td>
        </tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="InsertInformation" />
            <input type="hidden" name="InformationType" value="Book" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="InsertArticleForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Insert Article Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Author</th>
          <td width="50%"><input name="Author" /></td>
        </tr>
        <tr>
          <th>Publication Date (yyyy-mm-dd)</th>
          <td><input name="PublicationDate" /></td>
        </tr>
        <tr>
          <th>Review</th>
          <td><input name="Review" /></td>
        </tr>
        <tr>
          <th>Summary</th>
          <td><input name="Summary" /></td>
        </tr>
        <tr>
          <th>Title</th>
          <td><input name="Title" /></td>
        </tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="InsertInformation" />
            <input type="hidden" name="InformationType" value="Article" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="InsertReportForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Insert Report Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Author</th>
          <td width="50%"><input name="Author" /></td>
        </tr>
        <tr>
          <th>Publication Date (yyyy-mm-dd)</th>
          <td><input name="PublicationDate" /></td>
        </tr>
        <tr>
          <th>Review</th>
          <td><input name="Review" /></td>
        </tr>
        <tr>
          <th>Summary</th>
          <td><input name="Summary" /></td>
        </tr>
        <tr>
          <th>Title</th>
          <td><input name="Title" /></td>
        </tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="InsertInformation" />
            <input type="hidden" name="InformationType" value="Report" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="InsertConferenceForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Insert Conference Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Date (yyyy-mm-dd)</th>
          <td width="50%"><input name="Date" /></td>
        </tr>
        <tr>
          <th>Location</th>
          <td><input name="Location" /></td>
        </tr>
        <tr>
          <th>Name</th>
          <td><input name="Name" /></td>
        </tr>
        <tr>
          <th>Review</th>
          <td><input name="Review" /></td>
        </tr>
        <tr>
          <th>Summary</th>
          <td><input name="Summary" /></td>
        </tr>
        <tr>
          <th>URL</th>
          <td><input name="URL" value="http://" /></td>
        </tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="InsertInformation" />
            <input type="hidden" name="InformationType" value="Conference" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="InsertSoftwareRepositoryForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Insert Software Repository Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Author</th>
          <td width="50%"><input name="Author" /></td>
        </tr>
        <tr>
          <th>Name</th>
          <td><input name="Name" /></td>
        </tr>
        <tr>
          <th>URL</th>
          <td><input name="URL" value="http://" /></td>
        </tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="InsertInformation" />
            <input type="hidden" name="InformationType" value="SoftwareRepository" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="InsertInterestingWebsiteForm">
  <p />
  <form method="post" action="web_portal" accept-charset="ISO-8859-1">
    <fieldset>
      <legend>Insert Interesting Website Form</legend>
      <table width="100%">
        <tr>
          <th width="50%">Author</th>
          <td width="50%"><input name="Author" /></td>
        </tr>
        <tr>
          <th>Review</th>
          <td><input name="Review" /></td>
        </tr>
        <tr>
          <th>Summary</th>
          <td><input name="Summary" /></td>
        </tr>
        <tr>
          <th>Title</th>
          <td><input name="Title" /></td>
        </tr>
        <tr>
          <th>URL</th>
          <td><input name="URL" value="http://" /></td>
        </tr>
        <tr>
          <th />
          <td>
            <input type="submit" />
            <input type="reset" />
            <input type="hidden" name="Page" value="Administration" />
	    <input type="hidden" name="QueryType" value="InsertInformation" />
            <input type="hidden" name="InformationType" value="InterestingWebsite" />
          </td>
        </tr>
      </table>
    </fieldset>
  </form>
</xsl:template>

<xsl:template match="AdministrationPage_Post">
  <p>The following record has been created, updated or deleted:</p>
  <xsl:apply-templates select="Book" />
  <xsl:apply-templates select="Article" />
  <xsl:apply-templates select="Report" />
  <xsl:apply-templates select="Conference" />
  <xsl:apply-templates select="SoftwareRepository" />
  <xsl:apply-templates select="InterestingWebsite" />
  <xsl:apply-templates select="RawData" />
  <p>Click <a href="web_portal?Page=Administration">here</a> to continue.</p>
</xsl:template>

<xsl:template match="AdministrationPage_Error">
  <p><xsl:value-of select="Message" /></p>
</xsl:template>

<xsl:template match="OperationPage_Get">
  <p />
  <fieldset>
    <legend>Portal Statistics</legend>
    <table width="100%">
      <tr>
        <th width="50%">No. of Books</th>
        <td width="50%"><xsl:value-of select="NumberOfBooks" /></td>
      </tr>
      <tr>
        <th>No. of Articles</th>
        <td><xsl:value-of select="NumberOfArticles" /></td>
      </tr>
      <tr>
        <th>No. of Reports</th>
        <td><xsl:value-of select="NumberOfReports" /></td>
      </tr>
      <tr>
        <th>No. of Conferences</th>
        <td><xsl:value-of select="NumberOfConferences" /></td>
      </tr>
      <tr>
        <th>No. of Software Repositories</th>
        <td><xsl:value-of select="NumberOfSoftwareRepositories" /></td>
      </tr>
      <tr>
        <th>No. of Interesting Websites</th>
        <td><xsl:value-of select="NumberOfInterestingWebsites" /></td>
      </tr>
      <tr>
        <th>No. of Raw Data</th>
        <td><xsl:value-of select="NumberOfRawData" /></td>
      </tr>
    </table>
  </fieldset>
  <p>These users are currently logged in:</p>
  <xsl:apply-templates select="ActiveUsers/FreeSubscription" />
  <xsl:apply-templates select="ActiveUsers/CheapSubscription" />
  <xsl:apply-templates select="ActiveUsers/ExpensiveSubscription" />
  <xsl:apply-templates select="ActiveUsers/Operator" />
  <xsl:apply-templates select="ActiveUsers/ExternalAdministrator" />
  <xsl:apply-templates select="ActiveUsers/RegularAdministrator" />
  <xsl:apply-templates select="ActiveUsers/ExpertAdministrator" />
  <p />
</xsl:template>

<xsl:template match="OperationPage_Error">
  <p><xsl:value-of select="Message" /></p>
</xsl:template>

<xsl:template match="LogoutPage_Get">
  <p>You are now logged out.  Click <a href="web_portal?Page=Login">here</a> to continue.</p>
</xsl:template>

<xsl:template match="LogoutPage_Error">
  <p><xsl:value-of select="Message" /></p>
</xsl:template>

</xsl:stylesheet>
