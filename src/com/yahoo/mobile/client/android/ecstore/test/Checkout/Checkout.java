package com.yahoo.mobile.client.android.ecstore.test.Checkout;

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

@SuppressWarnings("rawtypes")
public class Checkout extends ActivityInstrumentationTestCase2 {
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
	public Checkout() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {

		solo = new Solo(getInstrumentation(), getActivity());
		Assert.testFirstLaunch(solo);
	}

	@Override
	public void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

	// 1959918:Verify user can change other delivery places
	public void testChangeOtherDeliveryPlaces() throws Exception {

		/*
		 * Account.JudgementAccountLogin(solo); 
		 * Action.enterToItemPage(solo);
		 * Action.addToShoppingCart(solo);
		 */
		solo.clickOnView(solo.getView("tab_image", 3));
		solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
		solo.sleep(15000);
		TestHelper.swipeUp(solo, 1);
		Action.clickElementsInWebview(solo, "shipping updateItemChange");

		/*
		 * // Search "Confirm"button on alert window. for (WebElement web :
		 * solo.getCurrentWebElements()) { Log.i("number",
		 * web.getText().toString()); if
		 * (web.getText().toString().equals("我要結帳")) {
		 * solo.clickOnWebElement(web); solo.sleep(5000); } }
		 */

	}

}
