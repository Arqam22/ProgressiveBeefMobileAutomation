package com.folio3.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import java.nio.file.Paths;

public class ActivitiesPage {

    private final Page page;

    // Locators
    private final Locator bottomMenuActivities;
    private final Locator searchBar;
    private final Locator tabAll;
    private final Locator tabAssigned;
    private final Locator tabUnassigned;
    private final Locator viewSubmissionBtn;
    private final Locator submissionList;
    private final Locator addSubmissionIcon;
    private final Locator submitButton;
    private final Locator successPopupOkBtn;

    public ActivitiesPage(Page page) {
        this.page = page;

        // Bottom navigation
        this.bottomMenuActivities = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Activities"));
        this.searchBar = page.locator("input[placeholder='Search']");

        // Tabs
        this.tabAll = page.getByText("All", new Page.GetByTextOptions().setExact(true));
        this.tabAssigned = page.getByText("Assigned", new Page.GetByTextOptions().setExact(true));
        this.tabUnassigned = page.getByText("Unassigned", new Page.GetByTextOptions().setExact(true));

        // Activity actions
        this.viewSubmissionBtn = page.getByText("View Submission");
        this.submissionList = page.getByText("History");
        this.addSubmissionIcon = page.locator("//div[@class='main-header-sec main-header-small']//div[2]//span[1]//div[1]//*[name()='svg']"); // adjust
        this.submitButton = page.getByText("Submit");
        this.successPopupOkBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK"));
    }

    /** ✅ Open the Activities screen from bottom menu and wait for sync */
    public void openActivities() {
        bottomMenuActivities.click();
        page.waitForSelector("input[placeholder='Search']");
        page.waitForTimeout(3000);
    }

    /** ✅ Verify tabs visibility */
    public boolean areTabsVisible() {
        page.waitForTimeout(1000);
        boolean allVisible = tabAll.isVisible();
        boolean assignedVisible = tabAssigned.isVisible();
        boolean unassignedVisible = tabUnassigned.isVisible();

        System.out.println("All tab visible: " + allVisible);
        System.out.println("Assigned tab visible: " + assignedVisible);
        System.out.println("Unassigned tab visible: " + unassignedVisible);

        if (!(allVisible && assignedVisible && unassignedVisible)) {
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("tabs_failed.png")));
        }

        return allVisible && assignedVisible && unassignedVisible;
    }

    /** ✅ Search an activity by title */
    public void searchActivity(String keyword) {
        searchBar.fill(keyword);
        page.keyboard().press("Enter");
    }

    /**  Click Activity (e.g., "Log17a") */
    public void clickActivity(String activityName) {
        Locator activityCard = page.locator("//div[@class='infinite-scroll-component ']//div//div[@class='title'][normalize-space()='LOG17a']");

        if (activityCard.isVisible()) {
            activityCard.click();
            page.waitForTimeout(1000); // wait for options like “View Submission” to appear
        } else {
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("activity_not_found.png")));
        }
    }

    /** ✅ Step 1: Click View Submission */
    public void clickViewSubmission() {
        viewSubmissionBtn.click();
        page.waitForTimeout(1000);
    }

    /** ✅ Step 2: Verify Submission List Appears */
    public boolean isSubmissionListVisible() {
        page.waitForTimeout(2000);
        return submissionList.isVisible();
    }

    /** ✅ Step 3: Click on Tick (To-do Submission) */
    public void clickAddSubmissionIcon() {
        addSubmissionIcon.click();
        page.waitForTimeout(1000);
    }

    /** ✅ Step 4–7: Fill Form, Submit, Handle Popup, Verify */
    public void fillAndSubmitActivityForm() {
        page.waitForTimeout(1000);

        page.locator("//div[@class='parent-scrollable-container']//div[1]//div[2]//textarea[1]").fill("32");

        // Fill "Remarks"
        page.locator("//div[@id='scrollableDiv']//div[2]//div[2]//textarea[1]").fill("hello");

        // Fill "What is the refrigerator temperature in °F today?"
        Locator Numberfield = page.getByPlaceholder("Enter Number");
        Numberfield.fill("3");

        // Select "Was the temperature in between 35 and 45 degrees?" → Yes
        page.locator("body > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(4) > div:nth-child(4) > div:nth-child(2) > div:nth-child(1) > span:nth-child(2)").click();


        // Submit form
        //submitButton.click();
        page.getByText("Submit").click();

        page.locator("//button[normalize-space()='Yes']").click();

        // Wait for success popup
        page.waitForSelector("text=Success");
        page.locator("//button[normalize-space()='Ok']").click();

        // Verify redirected back to submission history
        page.waitForSelector("text=History");
        System.out.println("✅ Successfully submitted activity.");
    }

}
