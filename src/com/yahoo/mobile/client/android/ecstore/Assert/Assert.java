package com.yahoo.mobile.client.android.ecstore.Assert;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

public class Assert {

	// Check whether the application is first started.
	public static void testFirstLaunch(Solo solo) throws Exception {
		solo.sleep(3000);
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(solo.getCurrentActivity());
		boolean flag = prefs.getBoolean("Time", false);

		if (!flag) {
			View versionAlert ;
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
					solo.sleep(2000);
					solo.clickOnView(skip);
				} else {
					solo.sleep(1000);
					solo.clickOnView(solo.getView("welcome_btn"));
				}
				View personal = (View) solo
						.getView("category_editor_ok_btn", 0);
				solo.sleep(1000);
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

	// check if soft keyboard is open
	public static void softKeyboardIsOpen(Solo solo) throws Exception {

		InputMethodManager imm = (InputMethodManager) solo.getCurrentActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		junit.framework.Assert.assertTrue("Keyboard is inactive.",
				imm.isActive());

	}

	// check if navigate to search result page
	public static void navigateToResultPage(Solo solo) throws Exception {

		solo.waitForText(ValidationText.Results_value, 1, 3000);
		junit.framework.Assert.assertTrue(
				"Failed to navigate to search result Screen",
				solo.searchText(ValidationText.Commodity)
						&& solo.searchText(ValidationText.Commodity));
	}

	//
	public static void clearSuccess(Solo solo, String textviewid)
			throws Exception {

		String value_in_text = Action.getValuesInTextview(solo, textviewid);
		junit.framework.Assert.assertTrue("Clear input value failed.",
				value_in_text.length() == 0);

	}

	// check all Category item are show.
	public static void CategoryListShow(Solo solo) throws Exception {

		String[] CategoryList = ValidationText.CategoryList;
		int size = CategoryList.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(CategoryList[i]);
			junit.framework.Assert.assertTrue(CategoryList[i] + " not found",
					textFound);

		}

	}

	// is 服飾L2層分類 list show
	public static void costumeL2ListShow(Solo solo) throws Exception {

		String[] CostumeList = ValidationText.CostumeList;
		int size = CostumeList.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(CostumeList[i]);
			junit.framework.Assert.assertTrue(CostumeList[i] + " not found",
					textFound);
		}
	}

	// is 流行女裝category list show
	public static void womenClothingCategoryListShow(Solo solo)
			throws Exception {

		String[] WomenClothing = ValidationText.WomenClothing;
		int size = WomenClothing.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(WomenClothing[i]);
			junit.framework.Assert.assertTrue(WomenClothing[i] + " not found",
					textFound);
		}
	}

	public static void noResultDisplay(Solo solo) throws Exception {

		solo.waitForText(ValidationText.Results_value, 1, 3000);
		junit.framework.Assert.assertTrue("There have searched esults.",
				solo.searchText(ValidationText.Sorry_Text));
	}

	public static void navigateToSortTab(Solo solo) throws Exception {

		String[] CategoryList = ValidationText.CategoryList_Tab1;
		int size = CategoryList.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(CategoryList[i]);
			junit.framework.Assert.assertTrue(CategoryList[i] + " not found",
					textFound);
		}
	}

	public static void navigateToFilterTab(Solo solo) throws Exception {

		String[] CategoryList = ValidationText.CategoryList_Tab2;
		int size = CategoryList.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(CategoryList[i]);
			junit.framework.Assert.assertTrue(CategoryList[i] + " not found",
					textFound);
		}
	}

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

		String[] CategoryList = ValidationText.CategoryList_Tab3;
		int size = CategoryList.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(CategoryList[i]);
			junit.framework.Assert.assertTrue(CategoryList[i] + " not found",
					textFound);
		}
	}
}
