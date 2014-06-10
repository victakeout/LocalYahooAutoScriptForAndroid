package com.yahoo.mobile.client.android.ecstore.test.FavoriteStore;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.robotium.solo.WebElement;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
@SuppressWarnings("rawtypes")
public class FavoriteStore extends ActivityInstrumentationTestCase2 {
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.yahoo.mobile.client.android.ecstore.ui.ECSplashActivity";
	private static Class launcherActivityClass;
	private Solo solo;
	static {

		try {
			launcherActivityClass = Class
					.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public FavoriteStore() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {

		solo = new Solo(getInstrumentation(), getActivity());
		// Assert.testFirstLaunch(solo);
	}

	@Override
	public void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

	// 1959875:Verify user logout,then login again,the display of the favorite
	// store list.
	public void testFavoriteStoreLoginAndLogout() throws Exception {

		Account.JudgementAccountWithoutLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 1));
		solo.sleep(10000);
		solo.waitForView(solo.getView("no_result_text", 1));
		TextView noResult = (TextView) solo.getView("no_result_text", 1);
		assertEquals(ValidationText.WISHLIST_FAVORITE_STORE, noResult.getText()
				.toString());

		// Account login
		Account.JudgementAccountLogin(solo);

		solo.sleep(3000);
		solo.clickOnView(solo.getView("tab_image", 1));
		solo.takeScreenshot("Favorite_Store_List_Show", 100);
		solo.sleep(10000);
		assertFalse("Text not found.", noResult.isShown());
		solo.sleep(3000);

	}

	// 1959886:Verify the page display when user is logged in and has no
	// favorite stores.
	public void testNoFavoriteStoreWhenLogin() throws Exception {

		 Account.JudgementAccountLogin(solo);
		 Action.removeFavoriteStore(solo);
	}

	// 1954571:Verify 18禁items displayed in favorite stores
	public void testRestricted() throws Exception {

		Account.JudgementAccountLogin(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// element and test_data
		Action.addInitializeData(solo, 0, ValidationText.INFLATABLE_DOLL);

		// press search button on keyboard
		solo.pressSoftKeyboardSearchButton();

		solo.waitForView(solo.getView("star_button"));
		View star = (View) solo.getView("star_button", 1);

		solo.clickOnView(star);
		if (solo.waitForText(ValidationText.HAS_REMOVED_COLLECTION, 1, 6000)) {
			solo.clickOnView(star);

		}

		// Wait for added to favorite list
		solo.sleep(3000);
		solo.clickOnView(solo.getView("tab_image", 4));
		solo.clickOnView(solo.getView("profile_bt_favorite_text", 0));
		Action.clickView(solo, "listitem_productlist_image");
		View Restricted = solo.getView("content");
		assertTrue("Restrict image not show!", Restricted.isShown());

	}

	// 1959912:Verify there is an indicator to allow user login in
	public void testFavoriteStoresWhenLogout() throws Exception {

		Account.JudgementAccountWithoutLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 1));
		solo.sleep(2000);
		Button loginButton = (Button) solo.getView("option_button", 1);
		assertTrue("No login button displayed!", loginButton.isShown());

	}

	// 1959922:Verify user can access correct store page from recommendation.
	public void testAccessRecommendationStore() throws Exception {

		Account.JudgementAccountLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 1));
		solo.sleep(2000);
		solo.clickOnText(ValidationText.MAYBE_LIKE);
		solo.sleep(15000);
		View recommend = (View) solo.getView("listitem_recommended_image1", 0);
		solo.clickOnView(recommend);
		View banner = (View) solo.getView("img_store_banner", 0);
		assertTrue("Not enter to recommended page.", banner.isShown());

	}

	// 1959907:Verify the number of store items,collected number with my
	// favorite store
	public void testNumberOfStoreAndCollectedItems() throws Exception {

		Account.JudgementAccountLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 1));
		solo.sleep(2000);
		solo.clickOnText(ValidationText.MAYBE_LIKE);
		solo.sleep(15000);

		TextView productCount = (TextView) solo
				.getView("listitem_recommended_item_count");
		boolean productCounts = productCount.getText().toString()
				.matches("[0-9]+");
		TextView collectedCount = (TextView) solo
				.getView("listitem_recommended_collected_persons");
		boolean collectedCounts = collectedCount.getText().toString()
				.matches("[0-9]+");

		assertTrue(
				"The number of store items,collected number displayed incorrectly.",
				productCounts && collectedCounts);

	}

	// 1959896：Verify user can clicking promotion item link in store promotion
	// page
	public void testEnterToPromotionItems() throws Exception {

		Account.JudgementAccountLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 1));
		solo.sleep(2000);
		solo.clickOnText(ValidationText.MAYBE_LIKE);
		solo.sleep(20000);
		View recommend = (View) solo.getView("listitem_recommended_image1", 0);
		solo.clickOnView(recommend);
		View banner = (View) solo.getView("img_store_banner", 0);
		assertTrue("Not enter to recommended page.", banner.isShown());
		solo.sleep(2000);
		View option = (View) solo.getView("imageButton", 2);
		solo.clickOnView(option);
		solo.sleep(ValidationText.WAIT_TIME_LONG);
		TextView promotion = (TextView) solo.getView(
				"listitem_productlist_title", 1);
		solo.clickOnView(promotion);
		solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
		TestHelper.swipeUp(solo, 1);
		TextView promotionContent = (TextView) solo
				.getView("listitem_btn_desc");
		solo.clickOnView(promotionContent);
		solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

		TextView link = (TextView) solo.getView("productitem_promotion_name");
		assertTrue("Click promotion item link failed. ", link.isShown());
	}

	// 1959888:Verify Just added favorite store can be displayed on my favorite
	// stores tab
	public void testAddFavoriteStore() throws Exception {

		Account.JudgementAccountLogin(solo);
		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		solo.clickOnView(solo.getView("heart_button", 0));

		boolean alreadyAdd;

		// Get toast text.
		if (solo.waitForText(ValidationText.HAS_ADDED_COMMODITY)) {
			alreadyAdd = solo.waitForText(ValidationText.HAS_ADDED_COMMODITY);
			assertTrue("Add failed.", alreadyAdd);
		} else {
			solo.sleep(1000);
			solo.clickOnView(solo.getView("heart_button", 0));
			alreadyAdd = solo.waitForText(ValidationText.HAS_ADDED_COMMODITY);
			assertTrue("Add failed.", alreadyAdd);
		}
		solo.clickOnView(solo.getView("tab_image", 1));
		View shop = (View) solo.getView("listitem_favoritestore_image1", 0);
		assertTrue("Add to favorite store failed.", shop.isShown());
	}
}
