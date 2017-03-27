package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.BaseAuthPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;

import junit.framework.Assert;
import org.testng.annotations.Test;

@Test(groups = "auth-forcedLogin")
public class ForcedLoginTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "ForcedLogin_anonCanLogInViaAuthModalWhenAddingFile")
  public void anonCanLogInViaAuthModalWhenAddingFile() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialNewFilesPage specialPage = base.openSpecialNewFiles(wikiURL);
    specialPage.verifyPageHeader(specialPage.getTitle());
    specialPage.addPhoto();
    BaseAuthPage authModal = new BaseAuthPage();
    Assert.assertTrue(authModal.isModalOpen());

    authModal.navigateToSignIn();
    authModal.login(credentials.userName10, credentials.password10);
    AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
    modal.closeAddPhotoModal();

    specialPage.verifyUserLoggedIn(credentials.userName10);
  }

  @Test(groups = "ForcedLogin_anonCanLogInViaAuthModalWhenAddingVideo")
  public void anonCanLogInViaAuthModalWhenAddingVideo() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialVideosPageObject specialPage = base.openSpecialVideoPage(wikiURL);
    specialPage.clickAddAVideo();
    BaseAuthPage authModal = new BaseAuthPage();
    Assert.assertTrue(authModal.isModalOpen());

    authModal.navigateToSignIn();
    authModal.login(credentials.userName10, credentials.password10);

    AddMediaModalComponentObject modal = new AddMediaModalComponentObject(driver);
    modal.closeAddVideoModal();

    specialPage.verifyUserLoggedIn(credentials.userName10);
  }

  @Test(groups = "ForcedLogin_anonCanLogInViaUserLoginPage")
  public void anonCanLogInViaUserLoginPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openSpecialUpload(wikiURL);
    base.verifyLoginRequiredMessage();
    new AttachedSignInPage()
        .typeUsername(credentials.userName10)
        .typePassword(credentials.password10)
        .clickSignInButton()
        .verifyUserLoggedIn(credentials.userName10);

    Assertion.assertTrue(base.isStringInURL(URLsContent.SPECIAL_UPLOAD));
  }

  @Test(groups = "ForcedLogin_anonCanLogInOnSpecialWatchListPage")
  public void anonCanLogInOnSpecialWatchListPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.openWikiPage();
    base.openSpecialWatchListPage(wikiURL);
    base.verifyNotLoggedInMessage();
    base.clickLoginOnSpecialPage();

    new AttachedSignInPage()
        .typeUsername(credentials.userName10)
        .typePassword(credentials.password10)
        .clickSignInButton()
        .verifyUserLoggedIn(credentials.userName10);

    base.verifyUserLoggedIn(credentials.userName10);
    Assertion.assertTrue(base.isStringInURL(URLsContent.SPECIAL_WATCHLIST));
  }

  @Test(groups = "ForcedLogin_anonCanLogInViaAuthModalWhenAddingPhoto")
  public void anonCanLogInViaAuthModalWhenAddingPhoto() {
    WikiBasePageObject base = new WikiBasePageObject();
    String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject edit = base.navigateToArticleEditPage(wikiURL, articleName);
    edit.clickPhotoButton();
    BaseAuthPage authModal = new BaseAuthPage();
    Assert.assertTrue(authModal.isModalOpen());

    authModal.navigateToSignIn();
    authModal.login(credentials.userName10, credentials.password10);
    edit.verifyUserLoggedIn(credentials.userName10);
    Assertion.assertTrue(edit.isStringInURL(articleName));
    Assertion.assertTrue(edit.isStringInURL(URLsContent.ACTION_EDIT));
    PhotoAddComponentObject addPhoto = edit.clickPhotoButton();
    addPhoto.verifyAddPhotoModal();
  }
}
