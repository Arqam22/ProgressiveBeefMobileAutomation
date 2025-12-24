package com.folio3.tests;

import com.folio3.pages.SOPPage;
import com.folio3.utils.TestDataUtil;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

public class SOPTest {

    private final Page page;

    public SOPTest(Page page) {
        this.page = page;
    }

    @Test
    public void testAddSOPDocument() {
        SOPPage sopPage = new SOPPage(page);

        // Generate dynamic test data
        String sopNumber = TestDataUtil.generateUniqueSOPNumber();
        String sopTitle = TestDataUtil.generateUniqueTitle();

        // Navigate to SOP list
        sopPage.goToSOPList();
        page.waitForTimeout(2000);

        // Click Add
        sopPage.clickAddSOP();
        page.waitForTimeout(2000);

        // Fill form and save
        sopPage.fillAddDocumentForm(sopNumber, sopTitle);
        sopPage.clickSave();
        page.waitForTimeout(2000);

        // Search SOP
        sopPage.searchSOP(sopNumber);
        page.waitForTimeout(3000);

        // Verify document added
        boolean isAdded = sopPage.isDocumentAdded(sopTitle);
        assert isAdded : "Document was not added successfully!";

        page.waitForTimeout(3000);
    }
}
