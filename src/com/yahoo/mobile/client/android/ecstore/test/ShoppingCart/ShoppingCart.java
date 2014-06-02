package com.yahoo.mobile.client.android.ecstore.test.ShoppingCart;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
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
public class ShoppingCart extends ActivityInstrumentationTestCase2 {
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
	public ShoppingCart() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		Assert.testFirstLaunch(solo);

	}

	@Override
	public void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

	// 1959883:Verify the number of bottom bubble on shopping cart after deleted
	// all products.
	public void testNumberBubbleDisplayAfterDelete() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		Action.addToShoppingCart(solo);
		Action.removeShoppingCart(solo);
	}

	public void testNumberBubbleDisplay() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);

		Log.i("number", solo.getCurrentActivity().getClass().toString());
		// Swipe the screen until the buy button displayed.
		TestHelper.swipeUp2(solo, 2);
		View shopCart;
		try {
			shopCart = solo.getView("productitem_btn_purchase_now");
			solo.clickOnView(shopCart);

		} catch (AssertionError e) {

			TestHelper.swipeUp2(solo, 2);
			shopCart = solo.getView("productitem_btn_purchase_now");
			solo.clickOnView(shopCart);
		}

		TextView actionBar = (TextView) solo.getView("action_bar_title", 3);
		Log.i("number", actionBar.getText().toString());
		assertTrue("Not enter the shopping page", actionBar.getText()
				.toString().equals(ValidationText.Own_Shopping_Cart));

	}

	// 1959911:Verify shopping cart & next buy
	public void testShoppingCartNextBuy() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.removeShoppingCart(solo);
		Action.enterToItemPage(solo);
		Action.addToShoppingCart(solo);
		solo.clickOnView(solo.getView("tab_image", 3));
		solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
		solo.sleep(10000);
		Action.clickElementsInWebviewByText(solo, "goNextBuy updateItemClick");

		// Search "Confirm"button on alert window.
		Action.clickElementsInWebviewByText(solo, "confirm");
		solo.sleep(5000);
			 
	 
		
		// Tap "Next Buy" button on web view.
		for (WebElement web : solo.getCurrentWebElements()) {
			if (web.getText().toString().equals(ValidationText.Next_buy)) {
				solo.clickOnWebElement(web);
				solo.sleep(10000);

			}
		}
		boolean expected = false;
		for (WebElement webs : solo.getCurrentWebElements()) {
			Log.i("number", webs.getClassName().toString());
			if (webs.getClassName().toString().equals("price")) {
				expected = true;
			}

		}
		assertTrue("", expected);
	}

	// 1959908:Verify numbers under shopping$next buy
	public void testShoppingCartAndNextBuyNumber() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.removeShoppingCart(solo);

		for (int i = 0; i < 3; i++) {
			solo.scrollToTop();
			Action.enterToItemPage(solo);
			Action.addToShoppingCart(solo);
		}

		solo.clickOnView(solo.getView("tab_image", 3));
		solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
		solo.sleep(10000);
	}
}
