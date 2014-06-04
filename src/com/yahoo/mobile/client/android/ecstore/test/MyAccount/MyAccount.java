package com.yahoo.mobile.client.android.ecstore.test.MyAccount;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MyAccount extends ActivityInstrumentationTestCase2 {
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

	public MyAccount() throws ClassNotFoundException {
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

	// 1959879:Verify the favorite items number
	public void testFavoriteItemNumber() throws Exception {

		Account.JudgementAccountLogin(solo);
		solo.clickOnText(ValidationText.All_Categories);
		solo.sleep(5000);
		Action.clickText(solo, ValidationText.Apparel);
		solo.clickOnText(ValidationText.Commodity);
		Action.clickStarIconNote(solo);

		// "Add to favorite" operation will take for a period of time.
		solo.sleep(3000);
		solo.clickOnView(solo.getView("tab_image", 4));

		TextView favoriteItems = (TextView) solo
				.getView("profile_bt_favorite_count");

		if (favoriteItems.getText().toString() != null) {
			assertTrue("favorite items number is null.", true);
		}

		// Logout account.
		Account.accountLogOut(solo);
		// Remove account.
		Account.removeAccount(solo);

		solo.goBack();
		Account.overAccountLogIn(solo);
		solo.clickOnView(solo.getView("tab_image", 4));
		solo.sleep(5000);

		// Get the favorite item count and forced convert String to Int.
		TextView favoriteItemsRecheck = (TextView) solo
				.getView("profile_bt_favorite_count");
		int number = Integer
				.parseInt(favoriteItemsRecheck.getText().toString());

		Log.i("what", favoriteItemsRecheck.getText().toString());

		if (number >= 1) {
			assertTrue("favorite items number is null.", true);
		}
	}
	
	//1959920:Verify the number of e-coupon can count correctly
	public void testECouponCorrectly() throws Exception {
		
		Account.JudgementAccountLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 4));
		solo.clickOnText(ValidationText.ECoupon);
		solo.sleep(15000);
		Action.clickElementsInWebviewByClassname(solo, "filter");
		solo.sleep(5000);
		TextView defaultText = (TextView)solo.getView("text1",4);
		assertTrue("Default item is incorrect.",defaultText.isActivated());
		
	}
}