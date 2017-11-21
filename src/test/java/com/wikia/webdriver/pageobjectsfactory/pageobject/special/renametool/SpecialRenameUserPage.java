package com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpecialRenameUserPage extends SpecialPageObject {
  @FindBy(css = "input[name=\"newUsername\"]")
  private WebElement newUsernameTextBox;
  @FindBy(css = "input[name=\"newUsernameRepeat\"]")
  private WebElement confirmNewUsernameTextBox;
  @FindBy(css = "input[name=\"submitbutton\"]")
  private WebElement submitButton;
  @FindBy(xpath = "//a[text()=\"Staff log\"")
  private WebElement staffLogLink;
  @FindBy(css = ".errorbox")
  private WebElement errorMessageTextBox;
  @FindBy(css = "#mw-content-text")
  private WebElement contentTextBox;
  @FindBy(css = ".extiw")
  private WebElement helpLink;
  @FindBy(css = "#password")
  private WebElement currentPasswordTextBox;
  @FindBy(css = "#understand-consequences")
  private WebElement termsAndConditionsCheckBox;


  public SpecialRenameUserPage(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public SpecialRenameUserPage open() {
    getUrl(urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_RENAME_TOOL);
    return this;
  }

  public SpecialStaffLogPage openStaffLog() {
    wait.forElementVisible(staffLogLink);
    staffLogLink.click();
    return new SpecialStaffLogPage(driver);
  }

  public SpecialRenameUserPage fillFormData(String newUsername, String reason) {
    return fillFormData(newUsername,newUsername,reason);
  }

  public SpecialRenameUserPage fillFormData(String newUsername, String confirmUsername, String
                                            password) {
    wait.forElementClickable(confirmNewUsernameTextBox);
    newUsernameTextBox.sendKeys(newUsername);
    confirmNewUsernameTextBox.sendKeys(confirmUsername);
    currentPasswordTextBox.sendKeys(password);
    return this;
  }


  public SpecialRenameUserPage submitChange() {
    jsActions.scrollToElement(submitButton);
    submitButton.click();
    return this;
  }

  public String getErrorMessage() {
    return errorMessageTextBox.getText();
  }

  public String getContentText() {
    return contentTextBox.getText();
  }

  public HelpPage goToHelpPage() {
    helpLink.click();
    return new HelpPage(driver);
  }

  public SpecialRenameUserPage agreeToTermsAndConditions() {
    termsAndConditionsCheckBox.click();
    return this;
  }
}