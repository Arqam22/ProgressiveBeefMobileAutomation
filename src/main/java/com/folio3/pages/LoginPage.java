package com.folio3.pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    private String username = "#email";
    private String passwordField = "#password";
    private String loginButton = "button[type='submit']";

    public LoginPage(Page page) {
        super(page);
    }

    public void openLoginPage() {
        navigateTo("https://pbqa_mobileweb.folio3.site/Login");
    }

    public void login(String email, String password) {
        page.fill(username, email);
        page.fill(passwordField, password);
        page.click(loginButton);
    }

    public boolean isLoginSuccessful() {
        return page.url().contains("Dashboard") || page.locator("text=Invalid").count() == 0;
    }

}
