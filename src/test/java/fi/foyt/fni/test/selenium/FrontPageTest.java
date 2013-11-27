package fi.foyt.fni.test.selenium;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.junit.Parallelized;
  
@RunWith(Parallelized.class)
public class FrontPageTest extends GenericTest {
  
  @Parameterized.Parameters
  public static LinkedList<DesiredCapabilities[]> browsers() throws Exception {
    LinkedList<DesiredCapabilities[]> browsers = new LinkedList<>();
    
    Map<String, Object> extraCapabilities = new HashMap<>();
    
    String travisJobNumber = System.getenv("TRAVIS_JOB_NUMBER");
    if (travisJobNumber != null) {
      extraCapabilities.put("tunnel-identifier", travisJobNumber);
      extraCapabilities.put("build", travisJobNumber);
    }
    extraCapabilities.put("general.useragent.locale", "en-US");
    
//    // Firefox 25
//    
//    browsers.add(createBrowser(DesiredCapabilities.firefox(), "Windows 8.1", "25", extraCapabilities));
//
//    // Firefox 25
//    
//    browsers.add(createBrowser(DesiredCapabilities.chrome(), "Windows 8.1", "31", extraCapabilities));

    /* Windows 8 */
    
    // IE 10
    browsers.add(createBrowser(DesiredCapabilities.internetExplorer(), "Windows 8", "10", extraCapabilities));
    
//    /* Windows 7 */
//    
//    // IE 9
//    browsers.add(createBrowser(DesiredCapabilities.internetExplorer(), "Windows 7", "9", extraCapabilities));
//
//    // Opera 12
//    browsers.add(createBrowser(DesiredCapabilities.internetExplorer(), "Windows 7", "9", extraCapabilities));
//
//    // Safari 5
//    browsers.add(createBrowser(DesiredCapabilities.safari(), "Windows 7", "5", extraCapabilities));
//
//    /* Mac */ 
//    
//    // Safari 6
//    
//    browsers.add(createBrowser(DesiredCapabilities.safari(), "OS X 10.8", "6", extraCapabilities));
//    
//    /* Linux */
//    
//    // Firefox 25
//    
//    browsers.add(createBrowser(DesiredCapabilities.firefox(), Platform.LINUX, "25", extraCapabilities));
//
//    // Chrome 30
//    
//    browsers.add(createBrowser(DesiredCapabilities.chrome(), Platform.LINUX, "30", extraCapabilities));
    
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
  
  public FrontPageTest(DesiredCapabilities capabilities) {
    super();
    
    this.capabilities = capabilities;
  }
  
  @Before
  public void setUp() throws Exception {
    String username = System.getenv("SAUCE_USERNAME");
    String accessKey = System.getenv("SAUCE_ACCESS_KEY");
    String host = System.getenv("SAUCE_HOST"); 
    String port = System.getenv("SAUCE_PORT"); 
    this.driver = new RemoteWebDriver(new URL("http://" + username + ":" + accessKey + "@" + host + ":" + port + "/wd/hub"), capabilities);
  }
  
  @Test
  public void testFrontPageEn() throws Exception {
    driver.get(getStagingUnsecureUrl());
    assertEquals("Forge & Illusion", driver.getTitle());
    
    // Menu
    
    // Navigation link texts

    WebElement logoLink = driver.findElement(By.cssSelector(".index-menu>a:first-child"));
    WebElement forgeMenuLink = driver.findElement(By.cssSelector(".index-menu .menu-navigation-container>a:nth-child(1)"));
    WebElement gameLibraryMenuLink = driver.findElement(By.cssSelector(".index-menu .menu-navigation-container>a:nth-child(2)"));
    WebElement forumMenuLink = driver.findElement(By.cssSelector(".index-menu .menu-navigation-container>a:nth-child(3)"));
    WebElement aboutMenuLink = driver.findElement(By.cssSelector(".index-menu .menu-navigation-container>div.menu-about-container>a"));

    assertEquals("Forge", forgeMenuLink.getText());
    assertEquals("Game Library", gameLibraryMenuLink.getText());
    assertEquals("Forum", forumMenuLink.getText());
    assertEquals("About", aboutMenuLink.getText());

    // Check menu links
    
    assertEquals(getStagingUnsecureUrl() + "/", stripLinkJSessionId(logoLink.getAttribute("href")));
    assertEquals(getStagingUnsecureUrl() + "/forge/", stripLinkJSessionId(forgeMenuLink.getAttribute("href")));
    assertEquals(getStagingUnsecureUrl() + "/gamelibrary/", stripLinkJSessionId(gameLibraryMenuLink.getAttribute("href")));
    assertEquals(getStagingUnsecureUrl() + "/forum/", stripLinkJSessionId(forumMenuLink.getAttribute("href")));
   
    // TODO: About items
    
    // Check titles
    assertEquals("Forge & Illusion is an open platform built for roleplaying and roleplayers.", driver.findElement(By.cssSelector("p.index-description-text")).getText());
    assertEquals("LATEST GAME LIBRARY PUBLICATIONS", driver.findElement(By.cssSelector(".index-publications-panel>h3>a")).getText());
    assertEquals("LATEST FORUM TOPICS", driver.findElement(By.cssSelector(".index-forum-panel>h3>a")).getText());
    assertEquals("NEWS", driver.findElement(By.cssSelector(".index-blog-panel>h3>a")).getText());
    
    // Check more links
    assertEquals("More >>", driver.findElement(By.cssSelector(".index-gamelibrary-more")).getText());
    assertEquals("More >>", driver.findElement(By.cssSelector("a.index-forum-more")).getText());
    assertEquals("More >>", driver.findElement(By.cssSelector("a.index-blog-more")).getText());
    
    // Check about menu
    aboutMenuLink.click();
    
    WebElement aboutMenuVision = driver.findElement(By.cssSelector(".menu-about-list>ul:nth-child(1)>a"));
    WebElement aboutMenuInformation = driver.findElement(By.cssSelector(".menu-about-list>ul:nth-child(2)>a"));
    WebElement aboutMenuForum = driver.findElement(By.cssSelector(".menu-about-list>ul:nth-child(3)>a"));
    WebElement aboutMenuDistribution = driver.findElement(By.cssSelector(".menu-about-list>ul:nth-child(4)>a"));
    WebElement aboutMenuGameplay = driver.findElement(By.cssSelector(".menu-about-list>ul:nth-child(5)>a"));
    WebElement aboutMenuHistory = driver.findElement(By.cssSelector(".menu-about-list>ul:nth-child(6)>a"));
    WebElement aboutMenuCookies = driver.findElement(By.cssSelector(".menu-about-list>ul:nth-child(7)>a"));
    WebElement aboutMenuOpenSource = driver.findElement(By.cssSelector(".menu-about-list>ul:nth-child(8)>a"));
    WebElement aboutMenuContact = driver.findElement(By.cssSelector(".menu-about-list>ul:nth-child(9)>a"));
    WebElement aboutMenuAcknowledgements = driver.findElement(By.cssSelector(".menu-about-list>ul:nth-child(10)>a"));
  
    assertEquals("Our Vision", aboutMenuVision.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#vision", stripLinkJSessionId(aboutMenuVision.getAttribute("href")));
    
    assertEquals("Information", aboutMenuInformation.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#information", stripLinkJSessionId(aboutMenuInformation.getAttribute("href")));
    
    assertEquals("Community participation and forum", aboutMenuForum.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#forum", stripLinkJSessionId(aboutMenuForum.getAttribute("href")));
    
    assertEquals("Distribution", aboutMenuDistribution.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#distribution", stripLinkJSessionId(aboutMenuDistribution.getAttribute("href")));
    
    assertEquals("Gameplay", aboutMenuGameplay.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#gameplay", stripLinkJSessionId(aboutMenuGameplay.getAttribute("href")));
    
    assertEquals("History", aboutMenuHistory.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#history", stripLinkJSessionId(aboutMenuHistory.getAttribute("href")));
    
    assertEquals("Use of cookies", aboutMenuCookies.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#cookies", stripLinkJSessionId(aboutMenuCookies.getAttribute("href")));
    
    assertEquals("Open Source", aboutMenuOpenSource.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#opensource", stripLinkJSessionId(aboutMenuOpenSource.getAttribute("href")));
    
    assertEquals("Contacting Us", aboutMenuContact.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#contact", stripLinkJSessionId(aboutMenuContact.getAttribute("href")));
    
    assertEquals("Acknowledgements", aboutMenuAcknowledgements.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#acknowledgements", stripLinkJSessionId(aboutMenuAcknowledgements.getAttribute("href")));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
  
  private WebDriver driver;
  private DesiredCapabilities capabilities;
}
