package com.folio3.utils;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    // 🔹 Initialize browser only once (used in FeedyardSuite @BeforeAll)
    public static Page initBrowser() {
        if (playwright == null) {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)); // set true for CI/CD
            context = browser.newContext();
            page = context.newPage();
        }
        return page;
    }

    // 🔹 Reuse same page/session (used in child test classes)
    public static Page getPage() {
        if (page == null) {
            throw new IllegalStateException("Page not initialized. Call initBrowser() first.");
        }
        return page;
    }

    // 🔹 Optional: if you ever want to reset context or open new tab
    public static Page newPage() {
        page = context.newPage();
        return page;
    }

    // 🔹 Close everything at the end of the suite
    public static void closeBrowser() {
        if (context != null) {
            context.close();
            context = null;
        }
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
        page = null;
    }
}
