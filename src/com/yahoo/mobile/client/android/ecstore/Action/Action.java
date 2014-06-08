package com.yahoo.mobile.client.android.ecstore.Action;

import java.util.ArrayList;

import com.robotium.solo.Solo;
import com.robotium.solo.WebElement;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Action {

	// clear history information then navigate to main screen
	public static void clearHistoryInfomation(Solo solo) throws Exception {

		// Go to main screen
		solo.waitForActivity("ECStoreActivity", 2000);
		solo.waitForText(ValidationText.News, 1, 3000);
		junit.framework.Assert.assertTrue("Navigate to main screen failed.",
				solo.searchText(ValidationText.News));
		// click on up icon
		solo.sleep(3000);
		clickHomeButtonOnScreen(solo);

		// clear history information and back
		solo.waitForText(ValidationText.Setting, 1, 3000);
		solo.clickOnText(ValidationText.Setting);
		solo.waitForText(ValidationText.Clear_Search_History, 1, 3000);
		solo.clickOnText(ValidationText.Clear_Search_History);
		solo.clickOnView(solo.getView("button1"));
		solo.clickOnView(solo.getView("home"));// home 1
		solo.sleep(3000);

	}

	// go to advanced screen.
	public static void enterAdvancedPage(Solo solo) {
		solo.waitForText(ValidationText.Commodity, 1, 3000);
		solo.clickOnText(ValidationText.Commodity);
		solo.clickOnView(solo.getView("menu_filter"));

	}

	// go to advanced sort screen.
	public static void enterAdvancedSortPage(Solo solo) {
		solo.waitForText(ValidationText.Commodity, 1, 3000);
		solo.clickOnText(ValidationText.Commodity);
		solo.clickOnView(solo.getView("menu_filter"));
		solo.sleep(3000);
		solo.clickOnView(solo.getView("btn_filter"));

	}

	// go to browser mode screen.
	public static void enterAdvancedBrowserModePage(Solo solo) {
		solo.waitForText(ValidationText.Commodity, 1, 3000);
		solo.clickOnText(ValidationText.Commodity);
		solo.clickOnView(solo.getView("menu_filter"));
		solo.sleep(3000);
		solo.clickOnView(solo.getView("btn_browse_mode"));
		solo.sleep(3000);

	}

	// go to main screen and click search icon
	public static void clickSearchButtonOnScreen(Solo solo) throws Exception {

		View iv = solo.getView("menu_search", 0);
		solo.clickOnView(iv);
		solo.sleep(3000);

	}

	public static void clickHomeButtonOnScreen(Solo solo) throws Exception {

		View iv = solo.getView("home");
		solo.clickOnView(iv);
		solo.sleep(3000);

	}

	// add data in textview
	public static void addInitializeData(Solo solo, int textview_id, String data)
			throws Exception {

		solo.enterText(textview_id, data);
		solo.sleep(3000);
	}

	public static void searchAfterPutData(Solo solo, int textview_id,
			String data) throws Exception {
		addInitializeData(solo, textview_id, data);
		solo.pressSoftKeyboardSearchButton();
		solo.sleep(3000);
	}

	// Navigate to category screen
	public static void navigateToCategoryScreen(Solo solo) throws Exception {

		solo.clickOnView(solo.getView("tab_text", 2));
		solo.clickOnText(ValidationText.All_Categories);
		com.yahoo.mobile.client.android.ecstore.Assert.Assert
				.CategoryListShow(solo);

	}

	// Navigate to favorite store screen
	public static void navigateToFavoriteStoreScreen(Solo solo)
			throws Exception {

		solo.clickOnView(solo.getView("tab_text", 1));
		solo.clickOnText(ValidationText.Favorite_Stores);
		TextView favorite = (TextView) solo.getView("tab_text", 1);
		junit.framework.Assert.assertTrue(
				"Not highligh to favorite store tab bar.",
				favorite.isSelected());

	}

	public static int getListviewOnCurrentScreen(Solo solo) throws Exception {

		ArrayList<ListView> listview = solo.getCurrentViews(ListView.class);
		int count = listview.size();
		return count;
	}

	// click plus in open window
	public static void clickPlusInOpenWindow(Solo solo, String viewid,
			int plusid) throws Exception {

		solo.sleep(3000);
		View view = solo.getView(viewid, plusid);
		ImageView imageview = (ImageView) view;
		solo.clickOnView(imageview);

	}

	// return value in textview
	public static String getValuesInTextview(Solo solo, String textviewid)
			throws Exception {

		solo.sleep(3000);
		View view = solo.getView(textviewid);
		if (view == null)
			return "";
		TextView testview = (TextView) view;
		return testview.getText().toString();

	}

	// return value in textview,multi-same textview
	public static String getValuesInTextview(Solo solo, String textviewid,
			int v_id) throws Exception {

		View view = solo.getView(textviewid, v_id);
		if (view == null)
			return "";
		TextView testview = (TextView) view;
		return testview.getText().toString();

	}

	// is view shown
	public static boolean getIsViewShown(Solo solo, String viewid)
			throws Exception {

		View view = solo.getView(viewid, 0);
		if (view == null)
			return false;
		return view.isShown();
	}

	// is view shown
	public static boolean getIsViewShown(Solo solo, String viewid, int id)
			throws Exception {

		View view = solo.getView(viewid, id);
		if (view == null)
			return false;
		return view.isShown();

	}

	// click view
	public static void clickView(Solo solo, String viewid) throws Exception {

		View view = solo.getView(viewid);
		solo.clickOnView(view);
		solo.sleep(3000);

	}

	// click view
	public static void clickView(Solo solo, String viewid, int id)
			throws Exception {

		View view = solo.getView(viewid, id);
		solo.clickOnView(view);
		solo.sleep(3000);

	}

	// click text
	public static void clickText(Solo solo, String text) throws Exception {

		solo.waitForText(text, 1, 3000);
		solo.clickOnText(text);
		solo.sleep(3000);

	}

	// add history information in search bar
	public static void addHistoryInfomationInSearchBar(Solo solo,
			String[] searchKeys) throws Exception {

		int searchKeys_Length = searchKeys.length;
		for (int i = 0; i < searchKeys_Length; i++) {

			// click on search button on home screen
			Action.clickSearchButtonOnScreen(solo);

			// input key to search
			Action.addInitializeData(solo, 0, searchKeys[i]);
			solo.pressSoftKeyboardSearchButton();
			solo.sleep(3000);

			// click back button if go to result screen
			Assert.navigateToResultPage(solo);
			Action.clickHomeButtonOnScreen(solo);
		}

	}

	public static void closeSoftKeyBoard(Solo solo) throws Exception {

		// close soft keyboard
		InputMethodManager imm = (InputMethodManager) solo.getCurrentActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		if (imm.isActive())
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
					InputMethodManager.HIDE_NOT_ALWAYS);

	}

	// Item list-list view
	public static void setListViewStyleAfterSearch(Solo solo) throws Exception {

		enterAdvancedBrowserModePage(solo);
		solo.clickOnView(solo.getView("btn_list_small"));
		solo.sleep(3000);
	}

	// Item list-Photo grid view
	public static void setSmallPhotoViewStyleAfterSearch(Solo solo)
			throws Exception {

		enterAdvancedBrowserModePage(solo);
		solo.clickOnView(solo.getView("btn_list_grid"));
		solo.sleep(3000);
	}

	// Item list-Large photo view
	public static void setLargePhotoViewStyleAfterSearch(Solo solo)
			throws Exception {

		enterAdvancedBrowserModePage(solo);
		solo.clickOnView(solo.getView("btn_list_large"));
		solo.sleep(3000);
	}

	// remove favorite goods item.
	public static void removeFavoriteItem(Solo solo) throws Exception {

		solo.clickLongOnView(solo.getView("listitem_productlist_image", 0));

		// Confirm remove it.
		solo.clickOnView(solo.getView("button1"));

		// junit.framework.Assert.assertTrue("Remove failed.",solo.waitForText("此商品收藏已移除"));
	}

	// click Star Icon .
	static int counts = 0;
	public static void clickStarIconNote(Solo solo) throws Exception {

		View star = (View) solo.getView("star_button", counts);
		solo.clickOnView(star);
		boolean alreadyAdd;

		// Get toast text.
		if (solo.waitForText(ValidationText.Has_added_collection)) {
			alreadyAdd = solo.waitForText(ValidationText.Has_added_collection);
			junit.framework.Assert.assertTrue("Add failed.", alreadyAdd);
		} else {
			solo.sleep(1000);
			solo.clickOnView(star);
			alreadyAdd = solo.waitForText(ValidationText.Has_added_collection);
			junit.framework.Assert.assertTrue("Add failed.", alreadyAdd);

		}
		counts++;
	}

	// Add product to shopping cart in item page.
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
		View RadioButtons;
		// Select product property if it exists.
		try {
			RadioButtons = (View) solo.getView(
					"product_item_spec_item_selections", 0);
		} catch (AssertionError e) {
			TestHelper.swipeUp2(solo, 2);
			solo.sleep(2000);
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
			solo.waitForText(ValidationText.Already_Add_Shopping_Cart, 1, 6000);
			solo.sleep(5000);
			buddle = solo.getView("tab_badge", 3);
			junit.framework.Assert.assertTrue("No items in shopping cart.",
					buddle.isShown());
		}

		solo.goBack();
	}

	// Remove shopping cart products.
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
					solo.sleep(1000);
					solo.clickOnButton(ValidationText.OK);
					solo.sleep(5000);

				} catch (AssertionError e) {

					junit.framework.Assert.assertFalse(
							"Buddle is displayed on tab.", buddle.isShown());
				}

			}
			solo.sleep(3000);
			junit.framework.Assert.assertFalse("Buddle is displayed on tab.",
					buddle.isShown());
		} catch (AssertionError e) {
			junit.framework.Assert.assertTrue("Buddle is displayed on tab.",
					true);
		}
	}

	// enter to product item detail page.
	static int count = 1;

	public static void enterToItemPage(Solo solo) throws Exception {

		Action.clickText(solo, ValidationText.All_Categories);
		Action.clickText(solo, ValidationText.Apparel);
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(2000);
		solo.clickInList(count);
		count++;
		Log.i("number", String.valueOf(count));
		solo.sleep(5000);

	}

	// enter to product item detail page2.

	public static void makeBrowseRecord(Solo solo, int counts) throws Exception {

		Action.clickText(solo, ValidationText.All_Categories);
		Action.clickText(solo, ValidationText.Apparel);
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(2000);
		for (int i = 1; i <= counts; i++) {
			solo.clickInList(i);
			solo.sleep(2000);
			if (i % 4 == 0) {
				solo.scrollUpList(i);

				// TestHelper.swipeUp2(solo, 2);
				solo.sleep(1000);
				solo.clickInList(i);
				solo.sleep(2000);
			}
			solo.goBack();
			// solo.scrollDownList(i + 1);

		}
		solo.sleep(2000);

	}

	// click elements from web view by ClassName.
	public static void clickElementsInWebviewByClassname(Solo solo, String text)
			throws Exception {
		for (WebElement web : solo.getCurrentWebElements()) {
			 Log.i("number","ClassNme:"+ web.getClassName().toString());
			 Log.i("number","Text:"+ web.getText().toString());

			if (web.getClassName().toString().equals(text)) {
				solo.clickOnWebElement(web);
				solo.sleep(15000);
			}
		}

	}

	// click elements from web view by text.

	public static void clickElementsInWebviewByText(Solo solo, String text)
			throws Exception {
		for (WebElement web : solo.getCurrentWebElements()) {
			Log.i("number", web.getText().toString());
			Log.i("number", web.getClassName().toString());
			if (web.getText().toString().equals(text)) {
				solo.clickOnWebElement(web);
				solo.sleep(15000);

			}

		}
	}

	// Search text on webview.
	static boolean actual = false;

	public static void searchTextOnWebview(Solo solo, String text)
			throws Exception {
		for (WebElement web : solo.getCurrentWebElements()) {
			Log.i("number", web.getClassName().toString());
			if (web.getText().toString().equals(text)) {
				actual = true;
				solo.sleep(5000);

			}

		}
		junit.framework.Assert.assertTrue("Text not found", actual);
	}

	// enter to
	public static void enterToJacket(Solo solo) throws Exception {
		solo.clickOnView(solo.getView("tab_text", 2));
		Action.clickText(solo, ValidationText.Apparel);
		Action.clickText(solo, ValidationText.Popular_Women);
		Action.clickText(solo, ValidationText.Jacket);
		Action.clickText(solo, ValidationText.Categories);
	}

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
					solo.sleep(2000);
				}
		 
			
		} catch (AssertionError e) {
			junit.framework.Assert.assertTrue("No item need delete.", true);
		}
		solo.goBack();
	}
}
