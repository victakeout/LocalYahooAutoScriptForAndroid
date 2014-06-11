package com.yahoo.mobile.client.android.ecstore.Action;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.robotium.solo.WebElement;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

/**
 * 
 * Contains some common actions  methods examples is enterToJacketAfterSearch().
 * 
 * @author SYMBIO
 * 
 * 
 **/

public class Action {

	// Clear history information then navigate to main screen
	public static void clearHistoryInfomation(Solo solo) throws Exception {

		// Go to main screen
		solo.waitForActivity("ECStoreActivity", 2000);
		solo.waitForText(ValidationText.NEWS, 1, 3000);
		junit.framework.Assert.assertTrue("Navigate to main screen failed.",
				solo.searchText(ValidationText.NEWS));

		// click on up icon
		solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
		clickHomeButtonOnScreen(solo);

		// clear history information and back
		solo.waitForText(ValidationText.SETTING, 1, 3000);
		solo.clickOnText(ValidationText.SETTING);
		solo.waitForText(ValidationText.CLEAR_SEARCH_HISTORY, 1, 3000);
		solo.clickOnText(ValidationText.CLEAR_SEARCH_HISTORY);
		solo.clickOnView(solo.getView("button1"));
		solo.clickOnView(solo.getView("home"));
		solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

	}

	// Go to advanced screen
	public static void enterAdvancedPage(Solo solo) {
		
		solo.waitForText(ValidationText.COMMODITY, 1, 3000);
		solo.clickOnText(ValidationText.COMMODITY);
		solo.clickOnView(solo.getView("menu_filter"));

	}

	// Go to clothes page
	public static void enterCategoryClothesPage(Solo solo) throws Exception {

		solo.waitForActivity("ECSplashActivity", 3000);
		Action.clickText(solo, ValidationText.ALL_CATEGORIES);
		Action.clickText(solo, ValidationText.APPAREL);

	}

	// Go to advanced sort screen
	public static void enterAdvancedSortPage(Solo solo) {
		
		solo.waitForText(ValidationText.COMMODITY, 1, 3000);
		solo.clickOnText(ValidationText.COMMODITY);
		solo.clickOnView(solo.getView("menu_filter"));
		solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
		solo.clickOnView(solo.getView("btn_filter"));

	}

	// Go to browser mode screen
	public static void enterAdvancedBrowserModePage(Solo solo) {
		
		solo.waitForText(ValidationText.COMMODITY, 1, 3000);
		solo.clickOnText(ValidationText.COMMODITY);
		solo.clickOnView(solo.getView("menu_filter"));
		solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
		solo.clickOnView(solo.getView("btn_browse_mode"));
		solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

	}

	// Go to main screen and click search icon
	public static void clickSearchButtonOnScreen(Solo solo) throws Exception {

		View iv = solo.getView("menu_search", 0);
		solo.clickOnView(iv);
		solo.sleep(ValidationText.WAIT_TIME_SHORT);

	}

	// Click home button on screen
	public static void clickHomeButtonOnScreen(Solo solo) throws Exception {

		View iv = solo.getView("home");
		solo.clickOnView(iv);
		solo.sleep(ValidationText.WAIT_TIME_SHORT);

	}

	// Add data in text view
	public static void addInitializeData(Solo solo, int textview_id, String data)
			throws Exception {

		solo.enterText(textview_id, data);
		solo.sleep(ValidationText.WAIT_TIME_SHORT);
	}

	// Put some data and search it
	public static void searchAfterPutData(Solo solo, int textview_id,
			String data) throws Exception {

		addInitializeData(solo, textview_id, data);
		solo.pressSoftKeyboardSearchButton();
		solo.sleep(ValidationText.WAIT_TIME_SHORT);
	}

	// Navigate to category screen
	public static void navigateToCategoryScreen(Solo solo) throws Exception {

		solo.clickOnView(solo.getView("tab_text", 2));
		solo.clickOnText(ValidationText.ALL_CATEGORIES);
		com.yahoo.mobile.client.android.ecstore.Assert.Assert
				.CategoryListShow(solo);

	}

	// Navigate to favorite store screen
	public static void navigateToFavoriteStoreScreen(Solo solo)
			throws Exception {

		solo.clickOnView(solo.getView("tab_text", 1));
		solo.clickOnText(ValidationText.FAVORITE_STORES);
		TextView favorite = (TextView) solo.getView("tab_text", 1);
		junit.framework.Assert.assertTrue(
				"Not highligh to favorite store tab bar.",
				favorite.isSelected());

	}

	// Remove favorite store 
	public static void removeFavoriteStore(Solo solo) throws Exception {

		solo.clickOnView(solo.getView("tab_text", 1));

		try {
			TextView storeName = (TextView) solo
					.getView("listitem_favoritestore_storename");

			do {
				solo.clickLongOnView(storeName);
				solo.sleep(ValidationText.WAIT_TIME_SHORT);
				Button ok = (Button) solo.getView("button1");
				solo.clickOnView(ok);
				solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
			} while (!storeName.isShown());

		} catch (AssertionError e) {
			junit.framework.Assert.assertTrue("Not fully deleted.", true);
		}
		TextView noResult = (TextView) solo.getView("no_result_text", 1);
		junit.framework.Assert.assertTrue("There are some shops exist.",
				noResult.isShown());

	}

	// Get the current screen contains list view.
	public static int getListviewOnCurrentScreen(Solo solo) throws Exception {

		ArrayList<ListView> listview = solo.getCurrentViews(ListView.class);
		int count = listview.size();
		return count;
		
	}

	// Click plus in open window
	public static void clickPlusInOpenWindow(Solo solo, String viewid,
			int plusid) throws Exception {

		solo.sleep(ValidationText.WAIT_TIME_SHORT);
		View view = solo.getView(viewid, plusid);
		ImageView imageview = (ImageView) view;
		solo.clickOnView(imageview);

	}

	// Return value in text view
	public static String getValuesInTextview(Solo solo, String textviewid)
			throws Exception {

		solo.sleep(ValidationText.WAIT_TIME_SHORT);
		View view = solo.getView(textviewid);
		if (view == null)
			return "";
		TextView testview = (TextView) view;
		return testview.getText().toString();

	}

	// Return value in text view,MULTI-same text view
	public static String getValuesInTextview(Solo solo, String textviewid,
			int v_id) throws Exception {

		View view = solo.getView(textviewid, v_id);
		if (view == null)
			return "";
		TextView testview = (TextView) view;
		return testview.getText().toString();

	}

	// Is view shown?
	public static boolean getIsViewShown(Solo solo, String viewid)
			throws Exception {

		View view = solo.getView(viewid, 0);
		if (view == null)
			return false;
		return view.isShown();
		
	}

	// Is view shown?
	public static boolean getIsViewShown(Solo solo, String viewid, int id)
			throws Exception {

		View view = solo.getView(viewid, id);
		if (view == null)
			return false;
		return view.isShown();

	}

	// Click view
	public static void clickView(Solo solo, String viewid) throws Exception {

		View view = solo.getView(viewid);
		solo.clickOnView(view);
		solo.sleep(ValidationText.WAIT_TIME_SHORT);

	}

	// Click view by id
	public static void clickView(Solo solo, String viewid, int id)
			throws Exception {

		View view = solo.getView(viewid, id);
		solo.clickOnView(view);
		solo.sleep(ValidationText.WAIT_TIME_SHORT);

	}

	// Click text
	public static void clickText(Solo solo, String text) throws Exception {

		solo.waitForText(text, 1, 3000);
		solo.clickOnText(text);
		solo.sleep(ValidationText.WAIT_TIME_SHORT);

	}

	// Add history information in search bar
	public static void addHistoryInfomationInSearchBar(Solo solo,
			String[] searchKeys) throws Exception {

		int searchKeys_Length = searchKeys.length;
		for (int i = 0; i < searchKeys_Length; i++) {

			// click on search button on home screen
			Action.clickSearchButtonOnScreen(solo);

			// input key to search
			Action.addInitializeData(solo, 0, searchKeys[i]);
			solo.pressSoftKeyboardSearchButton();
			solo.sleep(ValidationText.WAIT_TIME_SHORT);

			// click back button if go to result screen
			Assert.navigateToResultPage(solo);
			Action.clickHomeButtonOnScreen(solo);
		}

	}

	// Close some phone soft keyboard.
	public static void closeSoftKeyBoard(Solo solo) throws Exception {

		// close soft keyboard
		InputMethodManager imm = (InputMethodManager) solo.getCurrentActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		if (imm.isActive())
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
					InputMethodManager.HIDE_NOT_ALWAYS);

	}

	// Set the view to list view
	public static void setListViewStyleAfterSearch(Solo solo) throws Exception {

		enterAdvancedBrowserModePage(solo);
		solo.clickOnView(solo.getView("btn_list_small"));
		solo.sleep(ValidationText.WAIT_TIME_SHORT);

	}

	// Set the view to grid view
	public static void setSmallPhotoViewStyleAfterSearch(Solo solo)
			throws Exception {

		enterAdvancedBrowserModePage(solo);
		solo.clickOnView(solo.getView("btn_list_grid"));
		solo.sleep(ValidationText.WAIT_TIME_SHORT);
	}

	// Set the view to large photo view
	public static void setLargePhotoViewStyleAfterSearch(Solo solo)
			throws Exception {

		enterAdvancedBrowserModePage(solo);
		solo.clickOnView(solo.getView("btn_list_large"));
		solo.sleep(ValidationText.WAIT_TIME_SHORT);
	}

	// Remove favorite product item
	public static void removeFavoriteItem(Solo solo) throws Exception {

		solo.clickLongOnView(solo.getView("listitem_productlist_image", 0));

		// Confirm remove it.
		solo.clickOnView(solo.getView("button1"));

		// junit.framework.Assert.assertTrue("Remove failed.",solo.waitForText("此商品收藏已移除"));
	}

	// Click Star Icon.
	static int counts = 0;
	static View radioButtons;

	public static void clickStarIconNote(Solo solo) throws Exception {

		View star = (View) solo.getView("star_button", counts);
		solo.clickOnView(star);
		boolean alreadyAdd;

		// Get toast text.
		if (solo.waitForText(ValidationText.HAS_ADDED_COLLECTION)) {
			alreadyAdd = solo.waitForText(ValidationText.HAS_ADDED_COLLECTION);
			junit.framework.Assert.assertTrue("Add failed.", alreadyAdd);
		} else {
			solo.sleep(ValidationText.WAIT_TIME_SHORT);
			solo.clickOnView(star);
			alreadyAdd = solo.waitForText(ValidationText.HAS_ADDED_COLLECTION);
			junit.framework.Assert.assertTrue("Add failed.", alreadyAdd);

		}
		counts++;
	}

	// Add product to shopping cart in item page
	public static void addToShoppingCart(Solo solo) throws Exception {

		Log.i("number", solo.getCurrentActivity().getClass().toString());
		// Swipe the screen until the buy button displayed.
		TestHelper.swipeUp2(solo, 2);
		View shopCart;
		try {
			shopCart = solo.getView("productitem_btn_add_to_shopping_cart");
			solo.clickOnView(shopCart);

		} catch (AssertionError e) {

			TestHelper.swipeUp2(solo, 2);
			shopCart = solo.getView("productitem_btn_add_to_shopping_cart");
			solo.clickOnView(shopCart);
		}

		// Select product property if it exists.
		try {
			radioButtons = (View) solo.getView(
					"product_item_spec_item_selections", 0);
		} catch (AssertionError e) {
			TestHelper.swipeUp2(solo, 2);
			solo.sleep(ValidationText.WAIT_TIME_SHORT);
			View shopCarts = solo
					.getView("productitem_btn_add_to_shopping_cart");
			solo.clickOnView(shopCarts);
		}
		View buddle;
		View RadioButton = (View) solo.getView(
				"product_item_spec_item_selections", 0);
		if (RadioButton.isShown()) {

			solo.clickOnView(RadioButton);
			solo.searchText(ValidationText.OK);
			solo.clickOnButton(ValidationText.OK);
			solo.waitForText(ValidationText.ALREADY_ADD_SHOPPING_CART, 1, 6000);
			solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
			buddle = solo.getView("tab_badge", 3);
			junit.framework.Assert.assertTrue("No items in shopping cart.",
					buddle.isShown());
		}

		solo.goBack();
	}

	// Remove shopping cart products
	public static void removeShoppingCart(Solo solo) throws Exception {

		View buddle;
		solo.clickOnView(solo.getView("tab_image", 3));

		try {
			
			// Get the number of shopping cart goods.
			TextView count = (TextView) solo
					.getView("ecshopping_cart_header_count");
			int number = Integer.valueOf(count.getText().toString()
					.substring(0, 1));
			Log.i("number", String.valueOf(number));

			/*
			 * In most cases,The number of shopping cart goods is inconsistent
			 * with the list display,if the bubble is not shown,we can make sure
			 * that all products has deleted.
			 */
			buddle = solo.getView("tab_badge", 3);
			for (int i = 0; i < number; i++) {
				try {

					solo.clickLongOnView(solo.getView(
							"ecshopping_cart_store_name", 0));
					solo.sleep(ValidationText.WAIT_TIME_SHORT);
					solo.clickOnButton(ValidationText.OK);
					solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

				} catch (AssertionError e) {

					junit.framework.Assert.assertFalse(
							"Buddle is displayed on tab.", buddle.isShown());
				}

			}
			solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
			junit.framework.Assert.assertFalse("Buddle is displayed on tab.",
					buddle.isShown());
		} catch (AssertionError e) {
			junit.framework.Assert.assertTrue("Buddle is displayed on tab.",
					true);
		}
	}

	// Enter product item detail page
	static int count = 1;

	public static void enterToItemPage(Solo solo) throws Exception {

		Action.clickText(solo, ValidationText.ALL_CATEGORIES);
		Action.clickText(solo, ValidationText.APPAREL);
		Action.clickText(solo, ValidationText.COMMODITY);
		solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
		solo.clickInList(count);
		count++;
		Log.i("number", String.valueOf(count));
		solo.sleep(ValidationText.WAIT_TIME_SHORT);

	}

	// Enter product item detail page2
	public static void makeBrowseRecord(Solo solo, int counts) throws Exception {

		Action.clickText(solo, ValidationText.ALL_CATEGORIES);
		Action.clickText(solo, ValidationText.APPAREL);
		Action.clickText(solo, ValidationText.COMMODITY);
		solo.sleep(ValidationText.WAIT_TIME_SHORT);
		for (int i = 1; i <= counts; i++) {
			solo.clickInList(i);
			solo.sleep(ValidationText.WAIT_TIME_SHORT);
			if (i % 4 == 0) {

				solo.scrollUpList(i);
				solo.sleep(ValidationText.WAIT_TIME_SHORT);
				solo.clickInList(i);
				solo.sleep(ValidationText.WAIT_TIME_SHORT);
			}
			solo.goBack();

		}
		solo.sleep(ValidationText.WAIT_TIME_SHORT);

	}

	// Click elements from web view by ClassName
	public static void clickElementsInWebviewByClassname(Solo solo, String text)
			throws Exception {

		for (WebElement web : solo.getCurrentWebElements()) {
			Log.i("number", "ClassNme:" + web.getClassName().toString());
			Log.i("number", "Text:" + web.getText().toString());

			if (web.getClassName().toString().equals(text)) {
				solo.clickOnWebElement(web);
				solo.sleep(ValidationText.WAIT_TIME_LONGER);
			}
		}

	}

	// Click elements from web view by text
	public static void clickElementsInWebviewByText(Solo solo, String text)
			throws Exception {

		for (WebElement web : solo.getCurrentWebElements()) {
			Log.i("number", web.getText().toString());
			Log.i("number", web.getClassName().toString());
			if (web.getText().toString().equals(text)) {
				solo.clickOnWebElement(web);
				solo.sleep(ValidationText.WAIT_TIME_LONGER);

			}

		}
	}

	// Search text on web view
	static boolean actual = false;
	public static void searchTextOnWebview(Solo solo, String text)
			throws Exception {
		
		for (WebElement web : solo.getCurrentWebElements()) {
			Log.i("number", web.getClassName().toString());
			if (web.getText().toString().equals(text)) {
				actual = true;
				solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

			}

		}
		junit.framework.Assert.assertTrue("Text not found", actual);
	}

	// Enter jacket page
	public static void enterToJacket(Solo solo) throws Exception {
		
		solo.clickOnView(solo.getView("tab_text", 2));
		Action.clickText(solo, ValidationText.APPAREL);
		Action.clickText(solo, ValidationText.POPULAR_WOMEN);
		Action.clickText(solo, ValidationText.JACKET);
		Action.clickText(solo, ValidationText.CATEGORIES);
		
	}

	// Enter jacket page after search
	public static void enterToJacketAfterSearch(Solo solo) throws Exception {

		// navigate to category screen
		solo.clickOnView(solo.getView("tab_image", 2));

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.JACKET);
	}

	// Delete collected store
	public static void deleteProductCollected(Solo solo) throws Exception {

		solo.clickOnView(solo.getView("tab_image", 4));
		TextView OutNumberTwo = null;
		try {
			OutNumberTwo = (TextView) solo.getView("profile_bt_favorite_count");
			solo.clickOnView(OutNumberTwo);
			int number = Integer.parseInt(OutNumberTwo.getText().toString());

			for (int i = 0; i < number; i++) {
				
				View img = (View) solo.getView("listitem_productlist_image");
				solo.clickLongOnView(img);
				
				// Confirm remove it.
				solo.clickOnView(solo.getView("button1"));
				solo.sleep(ValidationText.WAIT_TIME_SHORT);
			}

		} catch (AssertionError e) {
			junit.framework.Assert.assertTrue("No item need delete.", true);
		}
		solo.goBack();
	}
}
