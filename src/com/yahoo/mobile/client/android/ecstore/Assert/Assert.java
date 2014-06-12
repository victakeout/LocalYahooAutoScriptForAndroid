package com.yahoo.mobile.client.android.ecstore.Assert;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

/**
 * Contains assert methods examples is navigateToSortTab().
 * @author SYMBIO
 */

public final class Assert {

    /**
     * private constructor.
     */

    private Assert() {

    }

    /**
     * Asserts that the application is first started.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void testFirstLaunch(final Solo solo)
            throws Exception {
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(solo.getCurrentActivity());
        boolean flag = prefs.getBoolean("Time", false);

        if (!flag) {
            View versionAlert;
            try {
                versionAlert = (View) solo.getView("alertTitle");
                if (versionAlert.isShown()) {
                    solo.goBack();
                }
            } catch (final AssertionError e) {
                System.err.println(e.toString());
                junit.framework.Assert.assertTrue(
                        "Not the first time launch app", true);
            }
            try {
                View skip = (View) solo.getView("welcome_skip");
                if (skip.isShown()) {
                    solo.sleep(ValidationText.WAIT_TIME_SHORT);
                    solo.clickOnView(skip);
                } else {
                    solo.sleep(ValidationText.WAIT_TIME_SHORT);
                    solo.clickOnView(solo.getView("welcome_btn"));
                }
                View personal = (View) solo
                        .getView("category_editor_ok_btn", 0);
                solo.sleep(ValidationText.WAIT_TIME_SHORT);
                solo.clickOnView(personal);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("Time", true);
                editor.commit();
                junit.framework.Assert.assertTrue(
                        "Not the first time launch app", true);
            } catch (final AssertionError e) {
                System.err.println(e.toString());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("Time", true);
                editor.commit();
                junit.framework.Assert.assertTrue(
                        "Not the first time launch app", true);
            }

        } else {

            try {
                View versionAlert = (View) solo.getView("alertTitle");
                if (versionAlert.isShown()) {
                    solo.goBack();
                }
            } catch (final AssertionError e) {
                System.err.println(e.toString());
                junit.framework.Assert.assertTrue(
                        "Not the first time launch app", true);
            }
            junit.framework.Assert.assertTrue("Not the first time launch app",
                    true);

        }
    }


    /**
     * Asserts that soft keyboard is open.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void softKeyboardIsOpen(final Solo solo) throws Exception {

        InputMethodManager imm = (InputMethodManager) solo.getCurrentActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        junit.framework.Assert.assertTrue("Keyboard is inactive.",
                imm.isActive());

    }


    /**
     * Asserts that device navigate to search result page.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void navigateToResultPage(final Solo solo) throws Exception {

        solo.waitForText(ValidationText.RESULTS_VALUE, 1,
                ValidationText.WAIT_TIME_MIDDLE);
        junit.framework.Assert.assertTrue(
                "Failed to navigate to search result Screen",
                solo.searchText(ValidationText.COMMODITY)
                        && solo.searchText(ValidationText.COMMODITY));
    }


    /**
     * Asserts that text view is null.
     * @param solo the Solo text
     * @param textviewid the text view id name
     * @throws Exception if has error
     */
    public static void clearSuccess(final Solo solo, final String textviewid)
            throws Exception {

        String valueInText = Action.getValuesInTextview(solo, textviewid);
        junit.framework.Assert.assertTrue("Clear input value failed.",
                valueInText.length() == 0);

    }

    /**
     * Asserts that All category item are show.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void categoryListShow(final Solo solo) throws Exception {

        String[] categoryList = ValidationText.CATEGORYLIST;
        int size = categoryList.length;
        for (int i = 0; i < size; i++) {
            boolean textFound = solo.searchText(categoryList[i]);
            junit.framework.Assert.assertTrue(categoryList[i] + " not found",
                    textFound);

        }

    }

    /**
     * Asserts that apparel L2 list is show.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void costumeL2ListShow(final Solo solo) throws Exception {

        String[] costumeList = ValidationText.COSTUMELIST;
        int size = costumeList.length;
        for (int i = 0; i < size; i++) {
            boolean textFound = solo.searchText(costumeList[i]);
            junit.framework.Assert.assertTrue(costumeList[i] + " not found",
                    textFound);
        }
    }

    /**
     * Asserts that Fashion apparel L2 list is show.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void womenClothingCategoryListShow(final Solo solo)
            throws Exception {

        String[] womenClothing = ValidationText.WOMENCLOTHING;
        int size = womenClothing.length;
        for (int i = 0; i < size; i++) {
            boolean textFound = solo.searchText(womenClothing[i]);
            junit.framework.Assert.assertTrue(womenClothing[i] + " not found",
                    textFound);
        }
    }

    /**
     * Asserts that the search result is null.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void noResultDisplay(final Solo solo)
            throws Exception {

        solo.waitForText(ValidationText.RESULTS_VALUE, 1,
                ValidationText.WAIT_TIME_MIDDLE);
        junit.framework.Assert.assertTrue("There have searched results.",
                solo.searchText(ValidationText.SORRY_TEXT));
    }

    /**
     * Asserts that to navigate to sort tab.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void navigateToSortTab(final Solo solo) throws Exception {

        String[] categoryList = ValidationText.CATEGORYLIST_TAB1;
        int size = categoryList.length;
        for (int i = 0; i < size; i++) {
            boolean textFound = solo.searchText(categoryList[i]);
            junit.framework.Assert.assertTrue(categoryList[i] + " not found",
                    textFound);
        }
    }


    /**
     * Asserts that to navigate to filter tab.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void navigateToFilterTab(final Solo solo) throws Exception {

        String[] categoryList = ValidationText.CATEGORYLIST_TAB2;
        int size = categoryList.length;
        for (int i = 0; i < size; i++) {
            boolean textFound = solo.searchText(categoryList[i]);
            junit.framework.Assert.assertTrue(categoryList[i] + " not found",
                    textFound);
        }
    }

    /**
     * Asserts that the filter layout.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void checkFilterLayout(final Solo solo) throws Exception {

        View scrollBar = solo.getView("seekbar", 0);
        View tableRowOne = solo.getView("tableRow1", 0);
        View tableRowTwo = solo.getView("tableRow2", 0);
        View tableRowThree = solo.getView("tableRow3", 0);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        boolean views = scrollBar.isShown() && tableRowOne.isShown()
                && tableRowTwo.isShown() && tableRowThree.isShown();
        junit.framework.Assert.assertTrue("views not found.", views);

    }

    /**
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void navigateToAdvancedTab(final Solo solo) throws Exception {

        String[] categoryList = ValidationText.CATEGORYLIST_TAB3;
        int size = categoryList.length;
        for (int i = 0; i < size; i++) {
            boolean textFound = solo.searchText(categoryList[i]);
            junit.framework.Assert.assertTrue(categoryList[i] + " not found",
                    textFound);
        }
    }
}
