package com.folio3.pages;

import com.microsoft.playwright.Page;

public class SelectFeedyardPage extends BasePage {

    // --- Locators ---
    private String feedyardPageTitle = "text=Select Feedyard"; // Text visible after successful login
    private String searchBox = "#search";                       // Search input field
    private String feedyardItem = "text=PB Feedyard";           // Feedyard name in list (exact match)
    private String coachmarkCloseButton = "//a[normalize-space()='Close']"; // Correct XPath

    public SelectFeedyardPage(Page page) {
        super(page);
    }

    // --- Verify that feedyard page is visible ---
    public boolean isFeedyardPageVisible() {
        page.waitForSelector(feedyardPageTitle);
        return page.isVisible(feedyardPageTitle);
    }

    // --- Search for a specific feedyard ---
    public void searchFeedyard(String name) {
        page.click(searchBox);
        page.fill(searchBox, name);
    }

    // --- Select the feedyard from the list ---
    public void selectFeedyard() {
        page.getByText("PB Feedyard", new Page.GetByTextOptions().setExact(true)).click();
    }

    // --- Close coachmark popup ---
    public void closeCoachmark() {
        try {
            // Wait up to 5 seconds for the coachmark close button to appear
            page.waitForSelector(coachmarkCloseButton, new Page.WaitForSelectorOptions().setTimeout(5000));

            // Click the close button if visible
            if (page.isVisible(coachmarkCloseButton)) {
                page.click(coachmarkCloseButton);
                System.out.println("Coachmark closed successfully");
            }
        } catch (Exception e) {
            System.out.println("Coachmark not visible or already closed, skipping...");
        }
    }

    // --- Optional: small wait to visually confirm ---
    public void waitForMoment(int seconds) {
        page.waitForTimeout(seconds * 1000);
    }
}
