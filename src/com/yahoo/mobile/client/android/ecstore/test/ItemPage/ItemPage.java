package com.yahoo.mobile.client.android.ecstore.test.ItemPage;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
public class ItemPage extends ActivityInstrumentationTestCase2 {
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

	public ItemPage() throws ClassNotFoundException {
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

	// 1953619:verify for the single commodity discount
	public void testSingleCommodityDiscount() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		TestHelper.swipeUp2(solo, 1);
		solo.clickOnText(ValidationText.SALES_PROMOTION);
		solo.clickOnText(ValidationText.DISCOUNT);
		solo.sleep(15000);
		View webpage = (View) solo.getView("webpage", 0);
		assertTrue("No promotion link displayed. ", webpage.isShown());
		
	}

	// 1953617:verify for full discount
	public void testFullDiscount() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		TestHelper.swipeUp2(solo, 1);
		solo.clickOnText(ValidationText.SALES_PROMOTION);
		solo.clickOnText(ValidationText.FULL);
		solo.sleep(15000);
		View webpage = (View) solo.getView("webpage", 0);
		assertTrue("No promotion link displayed. ", webpage.isShown());
		
	}

	// 1953614:verify for All customers the full discount.
	public void testAllCustomersDiscount() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		TestHelper.swipeUp2(solo, 1);
		solo.clickOnText(ValidationText.SALES_PROMOTION);
		solo.clickOnText(ValidationText.FULL);
		solo.sleep(15000);
		View webpage = (View) solo.getView("webpage", 0);
		assertTrue("No promotion link displayed. ", webpage.isShown());
		
	}

	// 1959927:Verify user can add an item to shopping cart
	public void testAddItemToShoppingCart() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		Action.addToShoppingCart(solo);
	}

	// 1959893:Verify Sharing method can be shown.
	public void testSharingMethod() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		TestHelper.swipeUp(solo, 1);
		solo.clickOnText(ValidationText.SHARE_PRODUCT);
		View share = (View) solo.getView("alertTitle", 1);
		assertEquals("Share frame not pop up. ", share.isShown());
		
	}

	// 1953636:verify favorite items
	public void testVerifyFavoriteitems() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.removeFavoriteItem(solo);
		Action.enterToItemPage(solo);
		solo.goBack();
		Action.clickStarIconNote(solo);
		TextView storeName = (TextView) solo.getView(
				"listitem_productlist_store_name", 0);
		solo.clickOnView(solo.getView("tab_image", 4));
		solo.clickOnText(ValidationText.PRODUCT_COLLECTION);
		solo.sleep(5000);
		TextView collectStoreName = (TextView) solo.getView(
				"listitem_productlist_store_name", 0);
		assertEquals(
				"Product not added to favorite list. ",
				storeName.getText().toString()
						.equals(collectStoreName.getText().toString()));
	}

	// 1953626:verify Payment.
	public void testVerifyPayment() throws Exception {

		Action.enterToItemPage(solo);
		TestHelper.swipeUp(solo, 1);
		Action.clickText(solo, ValidationText.PAYMENT);
		TextView payInfoATM = (TextView) solo.getView("pay_info", 1);
		TextView payInfoVisa = (TextView) solo.getView("pay_info", 2);
		assertTrue(
				"Payment page not contain any item.",
				payInfoATM.isShown() && payInfoVisa.isShown()
						&& solo.searchText(ValidationText.POST));
		
	}

	// 1953627:Verify Shopping methods
	public void testVerifyShoppingMethods() throws Exception {
		
		Action.enterToItemPage(solo);
		TestHelper.swipeUp(solo, 1);
		Action.clickText(solo, ValidationText.SHOPPING_TIPS);
		TextView ShoppingTips = (TextView) solo.getView("text_must_know", 3);
		assertTrue("Shopping tips not show.", ShoppingTips.isShown());

	}

	// 1953629:Verify the classification in current store page.
	public void testVerifyCurrentStorePage() throws Exception {
		
		Action.enterToItemPage(solo);
		TestHelper.swipeUp(solo, 1);
		Action.clickText(solo, ValidationText.SEE_ALL_STORE_PRODUCT);
		Action.clickText(solo, ValidationText.CATEGORIES);
		// TextView ShoppingTips = (TextView)solo.getView("text_must_know",3);
		assertTrue(
				"Shopping tips not show.",
				solo.searchText(ValidationText.CATEGORIES)
						&& solo.searchText(ValidationText.COMMODITY));

	}

	// 1953630:Verify the Commodity in current store page.
	public void testVerifyCommodityPage() throws Exception {
		Action.enterToItemPage(solo);
		TestHelper.swipeUp(solo, 1);
		Action.clickText(solo, ValidationText.SEE_ALL_STORE_PRODUCT);
		assertTrue(
				"Shopping tips not show.",
				solo.searchText(ValidationText.CATEGORIES)
						&& solo.searchText(ValidationText.COMMODITY));

	}

	// 1953631:verify share product Layer.
	public void testShareProductPage() throws Exception {
		
		Action.enterToItemPage(solo);
		TestHelper.swipeUp(solo, 1);
		Action.clickText(solo, ValidationText.SHARE_PRODUCT);

		solo.getCurrentActivity().getClass();

		View share = (View) solo.getView("alertTitle", 1);
		assertTrue("Share window not pop up.", share.isShown());

	}
}
