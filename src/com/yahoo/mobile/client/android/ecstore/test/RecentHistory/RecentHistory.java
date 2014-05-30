package com.yahoo.mobile.client.android.ecstore.test.RecentHistory;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

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
		solo.waitForText(ValidationText.News, 1, 3000);
		junit.framework.Assert.assertTrue("Navigate to main screen failed.",
				solo.searchText(ValidationText.News));
		// click on up icon
		solo.sleep(3000);
		solo.clickOnView(solo.getView("home"));

		solo.waitForText(ValidationText.Setting, 1, 3000);
		solo.clickOnText(ValidationText.Setting);

		TextView search = (TextView) solo.getView("title");
		TextView search_record = (TextView) solo.getView("title", 1);
		TextView search_clean = (TextView) solo.getView("title", 2);

		assertTrue(
				"Some search text not found.",
				search.getText().toString().equals(ValidationText.Search)
						&& search_record.getText().toString()
								.equals(ValidationText.Search_recorder)
						&& search_clean.getText().toString()
								.equals(ValidationText.Search_clean));

	}
}
