package com.folio3.tests;

import com.folio3.pages.LoginPage;
import com.folio3.utils.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    private Page page;
    private LoginPage loginPage;

    // ✅ Constructor added — allows suite to pass existing Page session
    public LoginTest(Page sharedPage) {
        this.page = sharedPage;
        this.loginPage = new LoginPage(page);
    }

    // ✅ Default constructor (for independent runs)
    public LoginTest() {}

    @BeforeAll
    void setUp() {
        // ✅ Only initialize if running standalone (not from suite)
        if (page == null) {
            page = PlaywrightFactory.initBrowser();
            loginPage = new LoginPage(page);
        }
    }

    @Test
    @DisplayName("Verify user can successfully login on Mobile Web")
    void testLogin() {
        loginPage.openLoginPage();
        loginPage.login("marqam", "Click12345");

        // Wait until feedyard page is visible
        page.waitForSelector("text=Select Feedyard");

        assertTrue(
                loginPage.isLoginSuccessful(),
                "Login should succeed and navigate to Select Feedyard page"
        );
    }

    @AfterAll
    void tearDown() {
        // ❌ Don't close browser here if running as part of suite
        // ✅ Keep session active for the next test
        // PlaywrightFactory.closeBrowser();
    }

    // ✅ Helper method to expose page for next test (SelectFeedyard)
    public Page getPage() {
        return this.page;
    }
}


