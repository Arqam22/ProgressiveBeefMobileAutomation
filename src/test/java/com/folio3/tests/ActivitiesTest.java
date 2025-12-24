package com.folio3.tests;

import com.folio3.pages.ActivitiesPage;
import com.folio3.utils.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ActivitiesTest {

    private Page page;
    private ActivitiesPage activitiesPage;

    public ActivitiesTest(Page sharedPage) {
        this.page = sharedPage;
        this.activitiesPage = new ActivitiesPage(page);
    }

    public ActivitiesTest() {}

    @BeforeAll
    void setUp() {
        if (page == null) {
            page = PlaywrightFactory.getPage();
            activitiesPage = new ActivitiesPage(page);
        }
    }

    @Test
    @DisplayName("Verify Activities list and perform new submission flow")
    void testActivitySubmissionFlow() {
        activitiesPage.openActivities();
        assertTrue(activitiesPage.areTabsVisible(), "All, Assigned, and Unassigned tabs should be visible");

        activitiesPage.searchActivity("Log17a");
        activitiesPage.clickActivity("Log17a");
        activitiesPage.clickViewSubmission();
        assertTrue(activitiesPage.isSubmissionListVisible(), "Submission list should be visible");
        activitiesPage.clickAddSubmissionIcon();
        activitiesPage.fillAndSubmitActivityForm();

    }

    @AfterAll
    void tearDown() {
        PlaywrightFactory.closeBrowser();
    }
}
