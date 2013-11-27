package fi.foyt.fni.test.selenium;

import org.apache.commons.lang3.StringUtils;

public class GenericTest {

  private static final String STAGING_HOST = "fnistaging-foyt.rhcloud.com"; 
  private static final String STAGING_UNSECURE = "http://" + STAGING_HOST; 
  private static final String STAGING_SECURE = "https://" + STAGING_HOST;
  
  protected String getStagingUnsecureUrl() {
    return STAGING_UNSECURE;
  }

  protected String getStagingSecureUrl() {
    return STAGING_SECURE;
  }
  
  protected String stripLinkJSessionId(String link) {
    if (StringUtils.isNotBlank(link)) {
      link = link.replaceFirst(";jsessionid=[a-zA-Z0-9\\.\\-]*", "");
    }
    
    return link;
  }
}
