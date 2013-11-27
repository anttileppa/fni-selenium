package fi.foyt.fni.test.selenium;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.LinkedList;

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
public class FrontPageTests extends GenericTest {
  
  @Parameterized.Parameters
  public static LinkedList<DesiredCapabilities[]> browsers() throws Exception {
    return getTestedBrowsers();
  }
  
  public FrontPageTests(DesiredCapabilities capabilities) {
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
  public void testFrontPage() throws Exception {
    driver.get(getStagingUnsecureUrl());
    assertEquals("Forge & Illusion", driver.getTitle());
    
    // Check titles
    assertEquals("Forge & Illusion is an open platform built for roleplaying and roleplayers.", driver.findElement(By.cssSelector("p.index-description-text")).getText());
    assertEquals("LATEST GAME LIBRARY PUBLICATIONS", driver.findElement(By.cssSelector(".index-publications-panel>h3>a")).getText());
    assertEquals("LATEST FORUM TOPICS", driver.findElement(By.cssSelector(".index-forum-panel>h3>a")).getText());
    assertEquals("NEWS", driver.findElement(By.cssSelector(".index-blog-panel>h3>a")).getText());
    
    // Check more links
    assertEquals("More >>", driver.findElement(By.cssSelector(".index-gamelibrary-more")).getText());
    assertEquals("More >>", driver.findElement(By.cssSelector("a.index-forum-more")).getText());
    assertEquals("More >>", driver.findElement(By.cssSelector("a.index-blog-more")).getText());
  }
  
  @Test
  public void testNavigationMenu() {
    driver.get(getStagingUnsecureUrl());
    
    // Navigation link texts

    WebElement logoLink = driver.findElement(By.cssSelector(".index-menu>a:first-child"));
    WebElement forgeMenuLink = driver.findElement(By.cssSelector(".index-menu .menu-navigation-container>a:nth-child(1)"));
    WebElement gameLibraryMenuLink = driver.findElement(By.cssSelector(".index-menu .menu-navigation-container>a:nth-child(2)"));
    WebElement forumMenuLink = driver.findElement(By.cssSelector(".index-menu .menu-navigation-container>a:nth-child(3)"));

    assertEquals("Forge", forgeMenuLink.getText());
    assertEquals("Game Library", gameLibraryMenuLink.getText());
    assertEquals("Forum", forumMenuLink.getText());

    // Check menu links
    
    assertEquals(getStagingUnsecureUrl() + "/", stripLinkJSessionId(logoLink.getAttribute("href")));
    assertEquals(getStagingUnsecureUrl() + "/forge/", stripLinkJSessionId(forgeMenuLink.getAttribute("href")));
    assertEquals(getStagingUnsecureUrl() + "/gamelibrary/", stripLinkJSessionId(gameLibraryMenuLink.getAttribute("href")));
    assertEquals(getStagingUnsecureUrl() + "/forum/", stripLinkJSessionId(forumMenuLink.getAttribute("href")));
  }

  @Test
  public void testAboutMenu() {
    driver.get(getStagingUnsecureUrl());
    
    WebElement aboutMenuLink = driver.findElement(By.cssSelector(".index-menu .menu-navigation-container>div.menu-about-container>a"));
    WebElement aboutMenuList = driver.findElement(By.cssSelector(".menu-about-list"));

    assertEquals("About", aboutMenuLink.getText());
    
    // Menu list should be hidden by default
    assertEquals(false, aboutMenuList.isDisplayed());
    
    // Check about menu
    aboutMenuLink.click();

    assertEquals(true, aboutMenuList.isDisplayed());
    
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
    
    assertEquals("Contacting us", aboutMenuContact.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#contact", stripLinkJSessionId(aboutMenuContact.getAttribute("href")));
    
    assertEquals("Acknowledgements", aboutMenuAcknowledgements.getText());
    assertEquals(getStagingUnsecureUrl() + "/about#acknowledgements", stripLinkJSessionId(aboutMenuAcknowledgements.getAttribute("href")));

    // Click somewhere else and the menu list should disappear
    driver.findElement(By.cssSelector(".index-banner")).click();
    assertEquals(false, aboutMenuList.isDisplayed());

    // Click link again and the menu list should reappear
    aboutMenuLink.click();
    assertEquals(true, aboutMenuList.isDisplayed());
    
    // ... and stay visible after another click
    aboutMenuLink.click();
    assertEquals(true, aboutMenuList.isDisplayed());
  }

  @Test
  public void testLocaleMenu() {
    driver.get(getStagingUnsecureUrl());
    
    WebElement localeMenuLink = driver.findElement(By.cssSelector(".index-menu .menu-tools-container .menu-tools-locale"));
    WebElement localeMenuList = driver.findElement(By.cssSelector(".menu-tools-locale-list"));

    assertEquals("EN", localeMenuLink.getText());
    
    // Menu list should be hidden by default
    assertEquals(false, localeMenuList.isDisplayed());
    
    // Click menu should make the list appear
    localeMenuLink.click();
    assertEquals(true, localeMenuList.isDisplayed());
    
    WebElement fiItem = driver.findElement(By.cssSelector(".menu-tools-locale-list>ul:nth-child(1)>a"));
    WebElement enItem = driver.findElement(By.cssSelector(".menu-tools-locale-list>ul:nth-child(2)>a"));
    
    assertEquals("Suomi", fiItem.getText());
    assertEquals("English", enItem.getText());

    // Click somewhere else and the menu list should disappear
    driver.findElement(By.cssSelector(".index-banner")).click();
    assertEquals(false, localeMenuList.isDisplayed());

    // Click link again and the menu list should reappear
    localeMenuLink.click();
    assertEquals(true, localeMenuList.isDisplayed());
    
    // ... and stay visible after another click
    localeMenuLink.click();
    assertEquals(true, localeMenuList.isDisplayed());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
  
  private WebDriver driver;
  private DesiredCapabilities capabilities;
}
