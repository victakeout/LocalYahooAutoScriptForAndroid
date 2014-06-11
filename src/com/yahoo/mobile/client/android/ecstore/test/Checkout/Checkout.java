/*
 * This is automated script about "Checkout".
 * 
 * You can run these test cases either on the emulator or on device. 
 * By Eclipse:
 * Right click the test project and select Run As --> Run As Android JUnit Test
 * By Ant:
 * 1.Run "android update test-project -m [path to target application] -p [path to the test folder]"  in command line .
 * 2."ant test"
 * By using instrument command:
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client.android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run Checkout:adb shell am instrument -e class com.yahoo.mobile.client.android.ecstore.test.Checkout.Checkout -w com.yahoo.mobile.client.android.ecstore.test/android.test.InstrumentationTestRunner
 * 
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 * 
 */

package com.yahoo.mobile.client.android.ecstore.test.Checkout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
public class Checkout extends ActivityInstrumentationTestCase2<Activity> {
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.yahoo.mobile.client.android.ecstore.ui.ECSplashActivity";
	private static Class<?> launcherActivityClass;
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
		super((Class<Activity>) launcherActivityClass);
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

	// 1959918:Verify that user can change other delivery places
	public void testChangeOtherDeliveryPlaces() throws Exception {

		Account.judgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		Action.addToShoppingCart(solo);

		solo.clickOnView(solo.getView("tab_image", 3));
		solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
		solo.sleep(ValidationText.WAIT_TIME_LONGER);
		TestHelper.swipeUp(solo, 2);
		solo.sleep(ValidationText.WAIT_TIME_LONG);

		// Click check out button on web view.
		Action.clickElementsInWebviewByText(solo, ValidationText.WANT_CHECKOUT);

		solo.sleep(ValidationText.WAIT_TIME_LONG);
		TestHelper.swipeUp(solo, 2);
		solo.sleep(ValidationText.WAIT_TIME_LONG);

		// Click "Select other store" text to re_selection.
		Action.clickElementsInWebviewByText(solo,
				ValidationText.RESELECT_OTHER_STORE);
		solo.sleep(ValidationText.WAIT_TIME_LONGER);
		Action.searchTextOnWebview(solo, ValidationText.TAI_BEI);

	}
}
