package com.yahoo.mobile.client.android.ecstore.test.DiscoveryStream;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;

@SuppressLint("NewApi")
public class DiscoveryStream extends ActivityInstrumentationTestCase2 {
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

	public DiscoveryStream() throws ClassNotFoundException {
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

	// 1954564:Verify pull down to refresh .
	public void testPullDownToRefresh() throws Exception {
		
		solo.sleep(20000);
		solo.clickOnView(solo.getView("tab_image", 0));
		//android.widget.ListView listView0 = (android.widget.ListView) solo.getView(android.widget.ListView.class, 0);

		View image = (View)solo.getView("listitem_discoverylist_top10_image",0);
		solo.drag(100, 100, image.getY(), image.getY()+300, 3);
		solo.sleep(5000);
	}
}
