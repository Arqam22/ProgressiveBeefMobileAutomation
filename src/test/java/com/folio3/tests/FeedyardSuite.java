package com.folio3.tests;

import com.folio3.utils.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FeedyardSuite {

    private Page page;

    @BeforeAll
    void setUp() {
        page = PlaywrightFactory.initBrowser();
    }

    @Test
    @Order(1)
    void testLogin() {
        new LoginTest(page).testLogin();
    }

    @Test
    @Order(2)
    void testSelectFeedyard() {
        new SelectFeedyardTest(page).testSelectFeedyard();
    }

    @Test
    @Order(3)
    void testDashboard() {
        new DashboardTest(page).testDashboardElementsVisible();
    }

    @Test
    @Order(4)
    void testSOPListingAndAddDocument() {
        new com.folio3.tests.SOPTest(page).testAddSOPDocument();
    }


    @Test
    @Order(5)
    void testAddNewUser(){
        new com.folio3.tests.UserTest(page).testAddNewUser();
    }

    @Test
    @Order(6)
    void testActivitiesList() {
        new ActivitiesTest(page).testActivitySubmissionFlow();
    }

    @Test
    @Order(7)
    void testExcelExport(){
        new com.folio3.tests.ExpExcelTest(page).testExcelExport();
    }

    @AfterAll
    void tearDown() {
        PlaywrightFactory.closeBrowser();
    }
}

// Test comment