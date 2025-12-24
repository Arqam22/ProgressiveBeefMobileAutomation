package com.folio3.tests;

import com.folio3.pages.UserPage;
import com.folio3.utils.TestDataUtil;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    private final Page page;

    public UserTest(Page page) {
        this.page = page;
    }

    @Test
    public void testAddNewUser() {

        UserPage userPage = new UserPage(page);

        String username = TestDataUtil.generateUsername();
        String password = "Test@123";

        // Step 1
        userPage.goToUsers();
        page.waitForTimeout(2000);

        // Step 2 & 3
        assertTrue(userPage.areUsertitleVisible(),
                "User tabs user title are not visible");
        page.waitForTimeout(2000);

        // Step 4
        userPage.clickAddUser();
        page.waitForTimeout(2000);

        // Step 5
        userPage.fillAddUserForm(
                "Auto",
                "User",
                username,
                password
        );

        // Step 6 & 7
        userPage.saveUser();

    }
}
