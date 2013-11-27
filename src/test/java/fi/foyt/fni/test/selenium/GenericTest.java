package fi.foyt.fni.test.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GenericTest {

  private static final String STAGING_HOST = "fnistaging-foyt.rhcloud.com"; 
  private static final String STAGING_UNSECURE = "http://" + STAGING_HOST; 
  private static final String STAGING_SECURE = "https://" + STAGING_HOST;
  
  protected static LinkedList<DesiredCapabilities[]> getTestedBrowsers() {
    LinkedList<DesiredCapabilities[]> browsers = new LinkedList<>();
    
    Map<String, Object> extraCapabilities = new HashMap<>();
    
    String travisJobNumber = System.getenv("TRAVIS_JOB_NUMBER");
    if (travisJobNumber != null) {
      extraCapabilities.put("tunnel-identifier", travisJobNumber);
      extraCapabilities.put("build", travisJobNumber);
    }
    extraCapabilities.put("general.useragent.locale", "en-US");
    
  //  // Firefox 25
  //  
  //  browsers.add(createBrowser(DesiredCapabilities.firefox(), "Windows 8.1", "25", extraCapabilities));
  //
  //  // Firefox 25
  //  
  //  browsers.add(createBrowser(DesiredCapabilities.chrome(), "Windows 8.1", "31", extraCapabilities));
  
    /* Windows 8 */
    
    // IE 10
    browsers.add(createBrowser(DesiredCapabilities.internetExplorer(), "Windows 8", "10", extraCapabilities));
    
  //  /* Windows 7 */
  //  
  //  // IE 9
  //  browsers.add(createBrowser(DesiredCapabilities.internetExplorer(), "Windows 7", "9", extraCapabilities));
  //
  //  // Opera 12
  //  browsers.add(createBrowser(DesiredCapabilities.internetExplorer(), "Windows 7", "9", extraCapabilities));
  //
  //  // Safari 5
  //  browsers.add(createBrowser(DesiredCapabilities.safari(), "Windows 7", "5", extraCapabilities));
  //
  //  /* Mac */ 
  //  
  //  // Safari 6
  //  
  //  browsers.add(createBrowser(DesiredCapabilities.safari(), "OS X 10.8", "6", extraCapabilities));
  //  
  //  /* Linux */
  //  
  //  // Firefox 25
  //  
  //  browsers.add(createBrowser(DesiredCapabilities.firefox(), Platform.LINUX, "25", extraCapabilities));
  //
  //  // Chrome 30
  //  
  //  browsers.add(createBrowser(DesiredCapabilities.chrome(), Platform.LINUX, "30", extraCapabilities));
    
    return browsers;
  }
  
  private static DesiredCapabilities[] createBrowser(DesiredCapabilities capabilities, Object platform, String version, Map<String, Object> extraCapabilities) {
    capabilities.setCapability("platform", platform);
    capabilities.setCapability("version", version);
    
    for (String capability : extraCapabilities.keySet()) {
      Object value = extraCapabilities.get(capability);
      capabilities.setCapability(capability, value);
    }
    
    return new DesiredCapabilities[] { capabilities };
  }
  
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

  protected WebDriver createDriver(Capabilities capabilities) throws MalformedURLException {
    String username = System.getenv("SAUCE_USERNAME");
    String accessKey = System.getenv("SAUCE_ACCESS_KEY");
    String host = System.getenv("SAUCE_HOST"); 
    String port = System.getenv("SAUCE_PORT"); 
    return new RemoteWebDriver(new URL("http://" + username + ":" + accessKey + "@" + host + ":" + port + "/wd/hub"), capabilities);
  }
  
}
