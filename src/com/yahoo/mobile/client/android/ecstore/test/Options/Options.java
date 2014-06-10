package com.yahoo.mobile.client.android.ecstore.test.Options;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
public class Options extends ActivityInstrumentationTestCase2 {
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

	public Options() throws ClassNotFoundException {
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

	// 1959919:Verify 0 result function on leaf-category
	public void testZeroResultDisplayed() throws Exception {
		
		solo.clickOnView(solo.getView("tab_image",2));
		Action.clickText(solo, ValidationText.HOME_BEDDING_FURNITURE);
		solo.sleep(15000);
		Action.clickText(solo, ValidationText.HANSHEN_HOME_LIFE);
		solo.sleep(10000);
		
		// Scroll to 10DAYS科技睡眠館
		android.widget.ListView listView1 = (android.widget.ListView) solo.getView(android.widget.ListView.class, 1);
		solo.scrollListToLine(listView1, 13);
		Action.clickText(solo, ValidationText.Hanshen_Ten_Days);
		Action.clickText(solo, ValidationText.CATEGORIES);
		Action.clickText(solo, ValidationText.Hanshen_Memory_Mattress);
		TextView zeroResult = (TextView)solo.getView("category_tab_secondary_title");	
		assertTrue("There are some products displayed.",zeroResult.getText().toString().trim().equals(ValidationText.ZERO_RESULT));
		solo.clickOnView(solo.getView("menu_filter"));
		assertTrue("Advanced page not pop up!",solo.searchText(ValidationText.SORT));
	}
}
