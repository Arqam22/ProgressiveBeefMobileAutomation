package com.folio3.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ExpExcelPage {

    private final Page page;

    // Bottom menu
    private final Locator dashboardMenu;

    // Export card
    private final Locator excelExportCard;

    // Form fields
    private final Locator emailInput;
    private final Locator startDatefield;
    private final Locator endDatefield;

    // Actions
    private final Locator proceedButton;
    private final Locator successMessage;
    private final Locator okButton;

    public ExpExcelPage(Page page) {
        this.page = page;

        // Bottom menu
        dashboardMenu = page.locator(":text(\"DASHBOARD\")");

        // Export card
        excelExportCard = page.locator("//div[@class='export-compliance-card']");

        // Form fields
        emailInput = page.locator("//input[@id='email']");
        startDatefield = page.locator("//div[@id='scrollableDiv']//div[2]//div[2]");
        endDatefield = page.locator("//div[@id='scrollableDiv']//div[3]//div[2]");

        // Actions
        proceedButton = page.locator(":text(\"Proceed\")");
        successMessage = page.locator(":text(\"Your request will be processed shortly.\")");
        okButton = page.locator("//button[normalize-space()='Ok']");
    }

    // Navigate to Dashboard
    public void goToDashboard() {
        dashboardMenu.waitFor();
        dashboardMenu.click();
    }

    // Click Excel Export card
    public void clickExcelExportCard() {
        excelExportCard.waitFor();
        excelExportCard.click();
    }

    // Fill Excel Export form
    public void fillExportForm(String email) {
        emailInput.fill(email);
    }

    // Click Proceed
    public void clickProceed() {
        proceedButton.click();
    }

    // Verify success message
    public void verifySuccessMessage() {
        successMessage.waitFor();
        assertThat(successMessage).isVisible();
        assertThat(successMessage).hasText("Your request will be processed shortly.");
    }

    // Click OK
    public void clickOk() {
        okButton.waitFor();
        okButton.click();
        System.out.println("Excel Export Successfully");
    }
}
