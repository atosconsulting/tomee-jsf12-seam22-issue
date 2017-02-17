# Issue: TomEE 7.0.2 + JSF 1.2 + Seam 2.2.2

I experience an issue on TomEE 7.0.2 while trying to use application using JSF 1.2_12 and Seam 2.2.2.Final.
It is likely that I did something incorrectly or didn't do something what I should.

## There are few issues (they may be related):
### (a)
While opening the application root at http://localhost:8080/web3tomee-1.0-SNAPSHOT/ I see an empty page; page source is broken and looks as follows:
```html
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Refresh" content
```

### (b)
Opening the JSF/Seam page at http://localhost:8080/web3tomee-1.0-SNAPSHOT/login.seam shows also broken HTML.
In addition `catalina.out` includes the following exception:
```
- :uncaught exception, passing to exception handler
 java.lang.IllegalStateException: No phase id bound to current thread (make sure you do not have two SeamPhaseListener instances installed)
        at org.jboss.seam.contexts.PageContext.getPhaseId(PageContext.java:163)
        at org.jboss.seam.contexts.PageContext.isBeforeInvokeApplicationPhase(PageContext.java:175)
        at org.jboss.seam.contexts.PageContext.getCurrentWritableMap(PageContext.java:91)
        at org.jboss.seam.contexts.PageContext.remove(PageContext.java:105)
        at org.jboss.seam.Component.newInstance(Component.java:2167)
        at org.jboss.seam.Component.getInstance(Component.java:2024)
        at org.jboss.seam.Component.getInstance(Component.java:2003)
        at org.jboss.seam.Component.getInstance(Component.java:1997)
        at org.jboss.seam.Component.getInstance(Component.java:1970)
        at org.jboss.seam.Component.getInstance(Component.java:1965)
        at org.jboss.seam.faces.FacesPage.instance(FacesPage.java:92)
        at org.jboss.seam.core.ConversationPropagation.restorePageContextConversationId(ConversationPropagation.java:84)
        at org.jboss.seam.core.ConversationPropagation.restoreConversationId(ConversationPropagation.java:57)
        at org.jboss.seam.jsf.SeamPhaseListener.afterRestoreView(SeamPhaseListener.java:390)
        at org.jboss.seam.jsf.SeamPhaseListener.afterServletPhase(SeamPhaseListener.java:229)
        at org.jboss.seam.jsf.SeamPhaseListener.afterPhase(SeamPhaseListener.java:195)
        at com.sun.faces.lifecycle.Phase.handleAfterPhase(Phase.java:175)
        at com.sun.faces.lifecycle.Phase.doPhase(Phase.java:114)
        at com.sun.faces.lifecycle.RestoreViewPhase.doPhase(RestoreViewPhase.java:103)
        at com.sun.faces.lifecycle.LifecycleImpl.execute(LifecycleImpl.java:118)
        at javax.faces.webapp.FacesServlet.service(FacesServlet.java:265)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:230)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165)
        at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165)
        at org.apache.openejb.server.httpd.EEFilter.doFilter(EEFilter.java:65)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165)
        at org.jboss.seam.servlet.SeamFilter$FilterChainImpl.doFilter(SeamFilter.java:83)
        at org.jboss.seam.web.IdentityFilter.doFilter(IdentityFilter.java:40)
        at org.jboss.seam.servlet.SeamFilter$FilterChainImpl.doFilter(SeamFilter.java:69)
        at org.jboss.seam.web.LoggingFilter.doFilter(LoggingFilter.java:60)
        at org.jboss.seam.servlet.SeamFilter$FilterChainImpl.doFilter(SeamFilter.java:69)
        at org.jboss.seam.servlet.SeamFilter$FilterChainImpl.doFilter(SeamFilter.java:73)
        at org.jboss.seam.web.MultipartFilter.doFilter(MultipartFilter.java:90)
        at org.jboss.seam.servlet.SeamFilter$FilterChainImpl.doFilter(SeamFilter.java:69)
        at org.jboss.seam.web.ExceptionFilter.doFilter(ExceptionFilter.java:64)
        at org.jboss.seam.servlet.SeamFilter$FilterChainImpl.doFilter(SeamFilter.java:69)
        at org.jboss.seam.web.RedirectFilter.doFilter(RedirectFilter.java:45)
        at org.jboss.seam.servlet.SeamFilter$FilterChainImpl.doFilter(SeamFilter.java:69)
        at org.ajax4jsf.webapp.BaseXMLFilter.doXmlFilter(BaseXMLFilter.java:178)
        at org.ajax4jsf.webapp.BaseFilter.handleRequest(BaseFilter.java:290)
        at org.ajax4jsf.webapp.BaseFilter.processUploadsAndHandleRequest(BaseFilter.java:388)
        at org.ajax4jsf.webapp.BaseFilter.doFilter(BaseFilter.java:515)
        at org.jboss.seam.web.Ajax4jsfFilter.doFilter(Ajax4jsfFilter.java:56)
        at org.jboss.seam.servlet.SeamFilter$FilterChainImpl.doFilter(SeamFilter.java:69)
        at org.jboss.seam.servlet.SeamFilter.doFilter(SeamFilter.java:158)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165)
        at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)
        at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:108)
        ...
```

## Path to reproduction

### (1)
Remove JSF2 from TomEE by deleting the following files from $TOMEE_ROOT/lib (actually issues are regardless of presence or non-presence of those files):
```
myfaces-api-2.2.11.jar
myfaces-impl-2.2.11.jar
openwebbeans-jsf-1.7.0.jar
tomee-mojarra-7.0.2.jar
tomee-myfaces-7.0.2.jar
```

### (2)
Add JSF 1.2_12 libs to $TOMEE_ROOT/lib:
```
jsf-api-1.2_12.jar
jsf-impl-1.2_12.jar
```

from the following locations:
- http://repo1.maven.org/maven2/javax/faces/jsf-api/1.2_12/jsf-api-1.2_12.jar
- http://repo1.maven.org/maven2/javax/faces/jsf-impl/1.2_12/jsf-impl-1.2_12.jar

### (3)
Build the "issue reproduction web app" exposed here: https://github.com/atosconsulting/tomee-jsf12-seam22-issue

### (4)
Copy the resulting `web3tomee-1.0-SNAPSHOT.war` to $TOMEE_ROOT/webapps for deployment.

### (5)
Open URLs (a) and (b) mentioned on the beginning.


I will be very glad for any suggestion what might be the reason of this issue.
