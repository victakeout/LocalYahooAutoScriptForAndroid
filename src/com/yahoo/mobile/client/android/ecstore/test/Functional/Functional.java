package com.yahoo.mobile.client.android.ecstore.test.Functional;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
public class Functional extends ActivityInstrumentationTestCase2 {
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
	public Functional() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		// Assert.testFirstLaunch(solo);

	}

	@Override
	public void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

	// 1977448:[notification]turn on/off marketing notifications.
	public void testMarktingNotificationTurnOnOff() throws Exception {

		Account.JudgementAccountLogin(solo);
		View iv = solo.getView("home");
		solo.clickOnView(iv);
		// clear history information and back
		solo.waitForText(ValidationText.Setting, 1, 3000);
		solo.clickOnText(ValidationText.Setting);
		solo.sleep(2000);
		Switch notification = (Switch) solo.getView("switchWidget", 2);

		assertTrue("Notification switch is off", notification.isChecked());
		solo.sleep(1000);
		solo.clickOnView(notification);
		solo.sleep(1000);
		assertFalse("Notification switch is on.", notification.isChecked());

	}

	// 1977478:[barcode]Discovery Stream root view
	public void testDiscoveryStreamFromDiscoveryStream() throws Exception {
		Account.JudgementAccountLogin(solo);
		Action.clickSearchButtonOnScreen(solo);
		ImageView barcode = (ImageView) solo.getView("search_barcode_scan");
		solo.clickOnView(barcode);

	}

	// 1977479:[barcode]Discovery Stream root view
	public void testDiscoveryStreamFromFavoriteStore() throws Exception {
		Account.JudgementAccountLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 1));
		Action.clickSearchButtonOnScreen(solo);
		ImageView barcode = (ImageView) solo.getView("search_barcode_scan");
		solo.clickOnView(barcode);

	}

	// 1977480:[barcode]Discovery Stream root view
	public void testDiscoveryStreamFromCategory() throws Exception {
		Account.JudgementAccountLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 2));
		Action.clickSearchButtonOnScreen(solo);
		ImageView barcode = (ImageView) solo.getView("search_barcode_scan");
		solo.clickOnView(barcode);

	}
}
