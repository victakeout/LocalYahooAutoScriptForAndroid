package com.yahoo.mobile.client.android.ecstore.test.ItemPage;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
public class ItemPage extends ActivityInstrumentationTestCase2 {
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

	public ItemPage() throws ClassNotFoundException {
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

	// 1953619:verify for the single commodity discount
	public void testSingleCommodityDiscount() throws Exception {

		// Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		TestHelper.swipeUp2(solo, 1);
		solo.clickOnText(ValidationText.Sales_Promotion);
		solo.clickOnText(ValidationText.Discount);
		solo.sleep(15000);
		View webpage = (View) solo.getView("webpage", 0);
		assertTrue("No promotion link displayed. ", webpage.isShown());
	}
}
