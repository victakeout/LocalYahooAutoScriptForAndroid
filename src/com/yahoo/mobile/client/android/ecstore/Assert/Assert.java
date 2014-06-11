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
 * 
 * Contains assert methods examples is navigateToSortTab().
 * 
 * @author SYMBIO
 * 
 * 
 */


public class Assert {

	// Asserts that the application is first started
	public static void testFirstLaunch(Solo solo) throws Exception {
		solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(solo.getCurrentActivity());
		boolean flag = prefs.getBoolean("Time", false);

		if (!flag) {
			View versionAlert;
			try {
				versionAlert = (View) solo.getView("alertTitle");
				if (versionAlert.isShown())
					solo.goBack();
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
				if (versionAlert.isShown())
					solo.goBack();
			} catch (final AssertionError e) {
				System.err.println(e.toString());
				junit.framework.Assert.assertTrue(
						"Not the first time launch app", true);
			}
			junit.framework.Assert.assertTrue("Not the first time launch app",
					true);

		}
	}

	// Asserts that soft keyboard is open
	public static void softKeyboardIsOpen(Solo solo) throws Exception {

		InputMethodManager imm = (InputMethodManager) solo.getCurrentActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		junit.framework.Assert.assertTrue("Keyboard is inactive.",
				imm.isActive());

	}

	// Asserts that device navigate to search result page
	public static void navigateToResultPage(Solo solo) throws Exception {

		solo.waitForText(ValidationText.RESULTS_VALUE, 1, 3000);
		junit.framework.Assert.assertTrue(
				"Failed to navigate to search result Screen",
				solo.searchText(ValidationText.COMMODITY)
						&& solo.searchText(ValidationText.COMMODITY));
	}

	// Asserts that text view is null
	public static void clearSuccess(Solo solo, String textviewid)
			throws Exception {

		String value_in_text = Action.getValuesInTextview(solo, textviewid);
		junit.framework.Assert.assertTrue("Clear input value failed.",
				value_in_text.length() == 0);

	}

	// Asserts that All category item are show
	public static void CategoryListShow(Solo solo) throws Exception {

		String[] CategoryList = ValidationText.CATEGORYLIST;
		int size = CategoryList.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(CategoryList[i]);
			junit.framework.Assert.assertTrue(CategoryList[i] + " not found",
					textFound);

		}

	}

	// Asserts that apparel L2 list is show
	public static void costumeL2ListShow(Solo solo) throws Exception {

		String[] CostumeList = ValidationText.COSTUMELIST;
		int size = CostumeList.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(CostumeList[i]);
			junit.framework.Assert.assertTrue(CostumeList[i] + " not found",
					textFound);
		}
	}

	// Asserts that Fashion apparel L2 list is show
	public static void womenClothingCategoryListShow(Solo solo)
			throws Exception {

		String[] WomenClothing = ValidationText.WOMENCLOTHING;
		int size = WomenClothing.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(WomenClothing[i]);
			junit.framework.Assert.assertTrue(WomenClothing[i] + " not found",
					textFound);
		}
	}

	// Asserts that the search result is null
	public static void noResultDisplay(Solo solo) throws Exception {

		solo.waitForText(ValidationText.RESULTS_VALUE, 1, 3000);
		junit.framework.Assert.assertTrue("There have searched results.",
				solo.searchText(ValidationText.SORRY_TEXT));
	}

	// Asserts that to navigate to sort tab
	public static void navigateToSortTab(Solo solo) throws Exception {

		String[] CategoryList = ValidationText.CATEGORYLIST_TAB1;
		int size = CategoryList.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(CategoryList[i]);
			junit.framework.Assert.assertTrue(CategoryList[i] + " not found",
					textFound);
		}
	}

	// Asserts that to navigate to filter tab
	public static void navigateToFilterTab(Solo solo) throws Exception {

		String[] CategoryList = ValidationText.CATEGORYLIST_TAB2;
		int size = CategoryList.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(CategoryList[i]);
			junit.framework.Assert.assertTrue(CategoryList[i] + " not found",
					textFound);
		}
	}

	// Asserts that the filter layout.
	public static void checkFilterLayout(Solo solo) throws Exception {

		View ScrollBar = solo.getView("seekbar", 0);
		View TableRowOne = solo.getView("tableRow1", 0);
		View TableRowTwo = solo.getView("tableRow2", 0);
		View TableRowThree = solo.getView("tableRow3", 0);
		solo.sleep(3000);
		boolean views = ScrollBar.isShown() && TableRowOne.isShown()
				&& TableRowTwo.isShown() && TableRowThree.isShown();
		junit.framework.Assert.assertTrue("views not found.", views);

	}

	public static void navigateToAdvancedTab(Solo solo) throws Exception {

		String[] CategoryList = ValidationText.CATEGORYLIST_TAB3;
		int size = CategoryList.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(CategoryList[i]);
			junit.framework.Assert.assertTrue(CategoryList[i] + " not found",
					textFound);
		}
	}
}
