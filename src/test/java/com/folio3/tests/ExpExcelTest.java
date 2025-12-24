package com.folio3.tests;

import com.folio3.pages.ExpExcelPage;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

public class ExpExcelTest {

    private final Page page;

    public ExpExcelTest(Page page) {
        this.page = page;
    }

    @Test
    public void testExcelExport() {
        ExpExcelPage excelPage = new ExpExcelPage(page);

        // Step 1: Go to Dashboard
        excelPage.goToDashboard();
        page.waitForTimeout(2000);

        // Step 2: Click Excel Export card
        excelPage.clickExcelExportCard();
        page.waitForTimeout(2000);

        // Step 3: Fill Export form
        String email = "automation@test.com";
        page.waitForTimeout(2000);
        excelPage.fillExportForm(email);

        // Step 4: Click Proceed
        excelPage.clickProceed();
        page.waitForTimeout(2000);


        // Step 5: Verify success message
        excelPage.verifySuccessMessage();
        page.waitForTimeout(2000);

        // Step 6: Click OK
        excelPage.clickOk();
        page.waitForTimeout(2000);
    }
}
