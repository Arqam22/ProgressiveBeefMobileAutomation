package com.folio3.tests;

import com.folio3.pages.DashboardPage;
import com.folio3.utils.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DashboardTest {

    private Page page;
    private DashboardPage dashboardPage;

    // ✅ Constructor to accept shared session
    public DashboardTest(Page sharedPage) {
        this.page = sharedPage;
        this.dashboardPage = new DashboardPage(page);
    }

    // ✅ Default constructor (for independent run)
    public DashboardTest() {}

    @BeforeAll
    void setUp() {
        // ✅ Only initialize if running independently
        if (page == null) {
            page = PlaywrightFactory.getPage();
            dashboardPage = new DashboardPage(page);
        }
    }

    @Test
    @DisplayName("Verify dashboard elements are visible after feedyard selection")
    void testDashboardElementsVisible() {
        // Step 1: Wait for dashboard to load
        dashboardPage.waitForDashboardToLoad();

        // Step 2: Verify dashboard elements
        assertTrue(
                dashboardPage.verifyDashboardElements(),
                "All dashboard elements should be visible"
        );

    }


    @AfterAll
    void tearDown() {
        // ✅ Now safe to close after final test
        PlaywrightFactory.closeBrowser();
    }
}
