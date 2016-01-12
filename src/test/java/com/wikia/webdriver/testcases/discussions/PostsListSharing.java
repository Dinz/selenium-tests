package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

/**
 * @ownership Content X-Wing Wikia
 */

@Test(groups = {"Discussions", "PostListSharing"})
public class PostsListSharing extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  private Credentials credentials = Configuration.getCredentials();

  /**
   * ANONYMOUS USER SECTION
   */

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void socialNetworkIconsAreDisplayedToAnonymousUserOnMobile() {
    toggleShareIconClickDisplaysSocialNetworkIcons(
        new String[]{"Facebook", "Twitter", "Reddit", "Tumblr"});
  }

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void socialNetworkIconsAreDisplayedToAnonymousUserOnDesktop() {
  toggleShareIconClickDisplaysSocialNetworkIcons(
      new String[]{"Facebook", "Twitter", "Reddit", "Tumblr"});}

  /**
   * LOGGED IN USER SECTION
   */
  @Test
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void socialNetworkIconsAreDisplayedToLoggedInUserOnMobile() {
  toggleShareIconClickDisplaysSocialNetworkIcons(
      new String[]{"Facebook", "Twitter", "Reddit", "Tumblr"});
  }

  @Test
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void socialNetworkIconsAreDisplayedToLoggedInUserOnDesktop() {
    toggleShareIconClickDisplaysSocialNetworkIcons(
        new String[]{"Facebook", "Twitter", "Reddit", "Tumblr"});
  }

  /**
   * TESTING METHODS SECTION
   */

  public void toggleShareIconClickDisplaysSocialNetworkIcons(String[] expectedSocialNetworks) {
    PostsListPage postList = new PostsListPage(driver).open();
    int postIndex = 0;
    postList.clickShareIcon(postIndex);
    String[] currentSocialNetworks = postList.getSocialNetworkIconClasses(postIndex);
    for (int i = 0; i < expectedSocialNetworks.length; i++) {
      String currentSocialNetwork = currentSocialNetworks[i];
      String expectedSocialNetwork = expectedSocialNetworks[i];
      Assertion.assertEqualsIgnoreCase(
          currentSocialNetwork, expectedSocialNetwork,
          "Expected network not found on its position. Note that the order of social buttons "
          + "matters, as defined in requirements. Missing network:" + expectedSocialNetwork
      );
    }
  }

}
