package com.folio3.tests;

import com.folio3.pages.SelectFeedyardPage;
import com.folio3.utils.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelectFeedyardTest {

    private Page page;
    private SelectFeedyardPage feedyardPage;

    public SelectFeedyardTest(Page sharedPage) {
        this.page = sharedPage;
        this.feedyardPage = new SelectFeedyardPage(page);
    }

    public SelectFeedyardTest() {}

    @BeforeAll
    void setUp() {
        if (page == null) {
            page = PlaywrightFactory.getPage();
            feedyardPage = new SelectFeedyardPage(page);
        }
    }

    @Test
    @DisplayName("Verify user can search, select feedyard and close coachmark")
    void testSelectFeedyard() {
        // Step 1: Verify feedyard page is visible
        assertTrue(feedyardPage.isFeedyardPageVisible(),
                "Feedyard List should be visible after login");

        // Step 2: Search and select PB Feedyard
        feedyardPage.searchFeedyard("pb feedyard");
        feedyardPage.selectFeedyard();
        System.out.println("Feedyard selected successfully");

        // Step 2a: Wait 3 seconds after selecting feedyard
        feedyardPage.waitForMoment(3);

        // Step 3: Close coachmark if it appears
        feedyardPage.closeCoachmark();

        // Step 4: Optional wait briefly to confirm navigation
        feedyardPage.waitForMoment(3);
    }

    @AfterAll
    void tearDown() {
        // Keep session active for next test
    }

    public Page getPage() {
        return this.page;
    }
}
