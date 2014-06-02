package com.yahoo.mobile.client.android.ecstore.test.StorePage;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
public class StorePage extends ActivityInstrumentationTestCase2 {
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

	public StorePage() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());

	}

	@Override
	public void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

	// 1959904:Verify user can check purchasing info from store page.
	public void testPurchasingInfoFromStorePage() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		Action.addToShoppingCart(solo);

		solo.clickOnView(solo.getView("tab_image", 3));
		solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
		solo.sleep(15000);
		// click store logo.
		Action.clickElementsInWebviewByClassname(solo, "pimg");
		solo.sleep(10000);
		TestHelper.swipeUp(solo, 1);
		solo.clickOnText(ValidationText.Shopping_tips);
		solo.sleep(2000);

		// Get the shopping tips text view.
		TextView mustKnow = (TextView) solo.getView("text_must_know");
		assertTrue("Shopping Tips not display", mustKnow.isShown());
	}

	// 1959901:Verify all classification and product page.
	public void testClassificationAndProductPage() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		TestHelper.swipeUp(solo, 1);
		solo.clickOnText(ValidationText.See_All_Store_Product);
		solo.sleep(2000);
		solo.clickOnView(solo.getView("imageButton", 2));

		solo.clickOnView(solo.getView("storeinfo_addfav"));
		solo.sleep(1000);
		if (solo.waitForText(ValidationText.Has_removed_Commodity)) {
			solo.clickOnView(solo.getView("storeinfo_addfav"));
		}

		solo.goBack();
		Action.navigateToFavoriteStoreScreen(solo);
		solo.clickOnView(solo.getView("option_button"));
		solo.clickOnText(ValidationText.Categories);
		solo.sleep(5000);
		solo.clickOnText(ValidationText.Commodity);
		solo.sleep(5000);
		solo.clickOnText(ValidationText.Categories);
		View categoryThumb = (View) solo.getView("category_thumb_expand");
		assertTrue("category thumb is not show.", categoryThumb.isShown());
	}

	// 1959887:Verify purchase person-time.
	public void testPurchasePersontime() throws Exception {
		// click search button
		Action.clickSearchButtonOnScreen(solo);
		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Dong_Jing);
		solo.clickOnText(ValidationText.Shop);
		TextView dongjing = (TextView) solo.getView(
				"listitem_storelist_store_name", 0);
		solo.clickOnView(dongjing);

		TextView buyer = (TextView) solo.getView("listitem_productlist_buyers",
				1);
		String buyers = buyer.getText().toString();
		String number = buyers.substring(0, buyers.lastIndexOf("人"));
		if (number.contains(",")) {
			number.replaceAll(",", "");
			boolean isNum = number.matches("[0-9]+");
			assertTrue("buyer infomation format incorrect", isNum);
		}
		boolean isNum = number.matches("[0-9]+");
		Log.i("number", number);
		assertTrue("buyer infomation format incorrect", isNum);
	}
}
