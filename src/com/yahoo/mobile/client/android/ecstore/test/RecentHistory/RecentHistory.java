package com.yahoo.mobile.client.android.ecstore.test.RecentHistory;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
@SuppressWarnings("rawtypes")
public class RecentHistory extends ActivityInstrumentationTestCase2 {
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
	public RecentHistory() throws ClassNotFoundException {
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

	// 1900011: verify settings screen
	public void testVerifySettingsScreen() throws Exception {

		// Go to main screen
		solo.waitForActivity("ECStoreActivity", 2000);
		solo.waitForText(ValidationText.NEWS, 1, 3000);
		junit.framework.Assert.assertTrue("Navigate to main screen failed.",
				solo.searchText(ValidationText.NEWS));

		// click "up" icon
		solo.sleep(3000);
		solo.clickOnView(solo.getView("home"));
		solo.waitForText(ValidationText.SETTING, 1, 3000);
		solo.clickOnText(ValidationText.SETTING);

		TextView recent_browse = (TextView) solo.getView("title", 3);
		TextView browse_record = (TextView) solo.getView("title", 4);
		TextView clean_browse_record = (TextView) solo.getView("title", 5);
		Switch toggle = (Switch) solo.getView("switchWidget", 1);
		assertTrue(
				"Some search text not found.",
				recent_browse.getText().toString()
						.equals(ValidationText.RECENT_BROWSE)
						&& browse_record.getText().toString()
								.equals(ValidationText.BROWSE_RECORD)
						&& clean_browse_record.getText().toString()
								.equals(ValidationText.CLEAN_BROWSE_RECORD)
						&& toggle.isChecked());

	}

	// 1900004:verify can browse recent items in「商品」tab
	public void testVerifyBrowseRecentItems() throws Exception {

		// Account.JudgementAccountLogin(solo);
		Action.makeBrowseRecord(solo, 20);
		solo.clickOnView(solo.getView("tab_image", 4));
		solo.clickOnText(ValidationText.RECENT_BROWSE);
	}
}
