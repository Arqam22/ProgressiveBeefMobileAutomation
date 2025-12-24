package com.folio3.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class SOPPage {

    private final Page page;

    // Locators
    private final Locator sopMenuButton;
    private final Locator addButton;
    private final Locator documentnumber;
    private final Locator documenttitle;
    private final Locator documentattachment;
    private final Locator saveButton;
    private final Locator successok;
    private final Locator searchsop;

    public SOPPage(Page page) {
        this.page = page;

        // Initialize locators
        this.sopMenuButton = page.locator("//a[@href='/ProgramManual']//div[@class='row d-flex flex-column justify-content-center align-items-center text-center']//span//div//*[name()='svg']");
        this.addButton = page.locator("//div[@class='head-icon right-align']//div[1]//span[1]//div[1]//*[name()='svg']");
        this.documentnumber = page.locator("//input[@id='documentNumber']");
        this.documenttitle = page.locator("//input[@id='englishTitle']");
        this.documentattachment = page.locator("//label[@for='englishFile']");
        this.saveButton = page.locator("//div[normalize-space()='Save']");
        this.successok = page.locator("//button[normalize-space()='Ok']");
        this.searchsop = page.locator("//input[@id='search']");
    }

    // Navigate to SOP List via bottom menu
    public void goToSOPList() {
        sopMenuButton.waitFor(); // Wait until visible
        sopMenuButton.click();
        System.out.println("SOP list open successfully");
    }

    // Click Add button
    public void clickAddSOP() {
        addButton.waitFor();
        addButton.click();
    }

    // Fill Add Document form
    public void fillAddDocumentForm(String number, String title) {
        documentnumber.waitFor();
        documentnumber.fill(number);

        documenttitle.waitFor();
        documenttitle.fill(title);

        // Upload file
        documentattachment.setInputFiles(Paths.get("C:/Users/arqamsultan/Downloads/SOPs/SOP1/audit-summary (43).pdf"));
    }

    // Save the SOP
    public void clickSave() {
        saveButton.waitFor();
        saveButton.click();

        successok.waitFor();
        successok.click();
        System.out.println("Document added successfully");
    }

    // Search SOP by document number
    public void searchSOP(String sopNumber) {
        searchsop.waitFor();
        searchsop.fill(sopNumber);
    }

    // Verify if document is added
    public boolean isDocumentAdded(String title) {
        Locator addedDocument = page.locator("text=" + title);
        addedDocument.waitFor();
        return addedDocument.isVisible();
    }
}
