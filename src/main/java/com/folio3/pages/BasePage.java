package com.folio3.pages;

import com.microsoft.playwright.Page;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    protected void navigateTo(String url) {
        page.navigate(url);
    }
}
