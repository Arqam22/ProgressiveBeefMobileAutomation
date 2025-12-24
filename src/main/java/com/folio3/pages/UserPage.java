package com.folio3.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class UserPage {

    private final Page page;

    // Bottom menu
    private final Locator usersMenu;

    // Userlisting
    private final Locator userTitle;

    // Actions
    private final Locator addUserButton;
    private final Locator saveButton;
    private final Locator successOk;

    // Form fields
    private final Locator firstName;
    private final Locator lastName;
    private final Locator username;
    private final Locator password;
    private final Locator confirmPassword;

    // Search
    private final Locator searchInput;

    public UserPage(Page page) {
        this.page = page;

        usersMenu = page.locator("text=USERS");

        userTitle = page.locator("//div[@class='main-header-title']");

        addUserButton = page.locator("//div[@class='head-icon right-align']//span//div//*[name()='svg']");
        saveButton = page.locator("//div[normalize-space()='Save']");
        successOk = page.locator("text=Ok");

        firstName = page.locator("(//input[@type='text'])[1]");
        lastName = page.locator("(//input[@type='text'])[2]");
        username = page.locator("(//input[@type='text'])[3]");
        password = page.locator("//input[@autocomplete='new-password']");
        confirmPassword = page.locator("(//input[@type='password'])[2]");

        searchInput = page.locator("//input[@id='search']");
    }

    public void goToUsers() {
        usersMenu.waitFor();
        usersMenu.click();
    }

    public boolean areUsertitleVisible() {
        return userTitle.isVisible();
    }

    public void clickAddUser() {
        addUserButton.waitFor();
        addUserButton.click();
    }

    public void fillAddUserForm(
            String fName,
            String lName,
            String user,
            String pass
    ) {
        firstName.fill(fName);
        lastName.fill(lName);
        username.fill(user);
        password.fill(pass);
        confirmPassword.fill(pass);
    }

    public void saveUser() {
        saveButton.click();
        successOk.waitFor();
        successOk.click();
        System.out.println("user added successfully");
    }

}
