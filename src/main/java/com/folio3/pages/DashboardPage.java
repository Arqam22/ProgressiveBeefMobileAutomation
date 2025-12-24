package com.folio3.pages;

import com.microsoft.playwright.Page;

public class DashboardPage {

    private final Page page;

    // Locators
    private final String dashboardTitle = "text=PB Feedyard";
    private final String feedlotPerformanceCard = "text=Feedlot Performance";
    private final String exportComplianceButton = "text=Export Compliance";
    private final String bottomMenuDashboard = "text=DASHBOARD";
    private final String bottomMenuSOP = "text=SOP";
    private final String bottomMenuUsers = "text=USERS";
    private final String bottomMenuActivities = "text=ACTIVITIES";
    private final String bottomMenuMore = "text=MORE";

    public DashboardPage(Page page) {
        this.page = page;
    }

    public void waitForDashboardToLoad() {
        page.waitForSelector(dashboardTitle);
    }

    public boolean verifyDashboardElements() {
        return page.isVisible(dashboardTitle)
                && page.isVisible(feedlotPerformanceCard)
                && page.isVisible(exportComplianceButton)
                && page.isVisible(bottomMenuDashboard)
                && page.isVisible(bottomMenuSOP)
                && page.isVisible(bottomMenuUsers)
                && page.isVisible(bottomMenuActivities)
                && page.isVisible(bottomMenuMore);
    }
}
