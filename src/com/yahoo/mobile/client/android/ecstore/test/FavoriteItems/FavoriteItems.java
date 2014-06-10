/*
 * This is automated script about "FavoriteItems".
 * 
 * You can run these test cases either on the emulator or on device. 
 * By Eclipse:
 * Right click the test project and select Run As --> Run As Android JUnit Test
 * By Ant:
 * 1.Run "android update test-project -m [path to target application] -p [path to the test folder]"  in command line .
 * 2."ant test"
 * By using instrument command:
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client.android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run category:adb shell am instrument -e class com.yahoo.mobile.client.android.ecstore.test.FavoriteItems.FavoriteItems -w com.yahoo.mobile.client.android.ecstore.test/android.test.InstrumentationTestRunner
 * 
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 * 
 */

package com.yahoo.mobile.client.android.ecstore.test.FavoriteItems;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
public class FavoriteItems extends ActivityInstrumentationTestCase2<Activity> {
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
	public FavoriteItems() throws ClassNotFoundException {
		super((Class<Activity>)launcherActivityClass);
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

	// 1959929: Verify that user can add favorite item.
	public void testVerifyAddFavoriteItem() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.enterCategoryClothesPage(solo);
		Action.clickText(solo, ValidationText.COMMODITY);
		solo.sleep(ValidationText.WAIT_TIME_SHORT);
		Action.clickStarIconNote(solo);
		solo.goBack();
		solo.scrollToTop();
		Action.clickText(solo, ValidationText.APPAREL);
		Action.clickText(solo, ValidationText.COMMODITY);
		View star = (View) solo.getView("star_button", 0);
		assertTrue("Star icon not checked.", star.isShown());

	}

	// 1959923:Verify store rate from items collected.
	public void testVerifyStoreRate() throws Exception {

		Account.JudgementAccountLogin(solo);
		Action.enterCategoryClothesPage(solo);
		Action.clickText(solo, ValidationText.COMMODITY);
		solo.sleep(ValidationText.WAIT_TIME_SHORT);
		Action.clickStarIconNote(solo);

		solo.clickOnView(solo.getView("tab_image", 4));
		solo.clickOnText(ValidationText.PRODUCT_COLLECTION);
		solo.sleep(10000);
		TextView price = (TextView) solo.getView(
				"listitem_productlist_store_rating", 0);
		Log.i("number", (String) price.getText());
		String sr = price.getText().toString();

		// Judgment whether the price matches the format of 'x.x'.
		boolean isNum = sr.matches("^[0-9].[0-9]");

		assertTrue(
				" Cannot find the shops score or score format is incorrect! ",
				isNum);
	}
}
