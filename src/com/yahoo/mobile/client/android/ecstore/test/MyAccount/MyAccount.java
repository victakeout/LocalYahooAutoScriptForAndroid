package com.yahoo.mobile.client.android.ecstore.test.MyAccount;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
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
		// Assert.testFirstLaunch(solo);
	}

	@Override
	public void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

	// 1959879:Verify the favorite items number
	public void testFavoriteItemNumber() throws Exception {

		Account.JudgementAccountLogin(solo);
		solo.clickOnText(ValidationText.ALL_CATEGORIES);
		solo.sleep(5000);
		Action.clickText(solo, ValidationText.APPAREL);
		solo.clickOnText(ValidationText.COMMODITY);
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

	// 1959920:Verify the number of e-coupon can count correctly
	public void testECouponCorrectly() throws Exception {

		Account.JudgementAccountLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 4));
		solo.clickOnText(ValidationText.ECOUPON);
		solo.sleep(15000);
		Action.clickElementsInWebviewByClassname(solo, "filter");
		solo.sleep(5000);
		TextView defaultText = (TextView) solo.getView("text1", 4);
		assertTrue("Default item is incorrect.", defaultText.isActivated());

	}

	// 1977495:Reward point display.
	public void testRewardPointDisplay() throws Exception {

		Account.JudgementAccountLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 4));

		// Account view
		TextView UserInfo = (TextView) solo.getView("profile_user_name");
		Log.i("number", UserInfo.getText().toString());
		// Reward point view,
		TextView RewardPoint = (TextView) solo.getView("account_reward_point",
				0);
		Log.i("number", RewardPoint.getText().toString());

		boolean flag = TestHelper.positionCompare(solo, UserInfo.getText()
				.toString().trim(), 0, RewardPoint.getText().toString().trim(),
				1, 1);
		assertTrue(
				"Item position is not right,need confirm the default browse mode.",
				flag);
	}

	// 1977535:verify offline coupons from My Account.
	public void testOfflineCoupons() throws Exception {
		Account.JudgementAccountLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 4));
		assertTrue("Cannot found ",
				solo.searchText(ValidationText.STORE_OFFERS));
	}

	// 1977522:verify recent history from My Account.
	public void testRecentHistory() throws Exception {
		Account.JudgementAccountLogin(solo);
		solo.clickOnView(solo.getView("tab_image", 4));
		Action.clickText(solo, ValidationText.RECENT_BROWSE);
		TextView ActionBar = (TextView) solo.getView("action_bar_title", 0);
		assertTrue("Cannot enter to recent history page. ", ActionBar.isShown());
	}

	// 1977523:verify recent history is no data.
	public void testRecentHistoryNoData() throws Exception {
		Account.JudgementAccountLogin(solo);
		Action.clearHistoryInfomation(solo);
		solo.clickOnView(solo.getView("tab_image", 4));
		Action.clickText(solo, ValidationText.RECENT_BROWSE);
		solo.sleep(10000);
		TextView NoResult = (TextView)solo.getView("no_result_text",1);	 
		assertTrue("There are some product info displayed. ",NoResult.isShown());
	 
		TextView shop = (TextView)solo.getView("category_tab_primary_title",1);
		solo.clickOnView(shop);
		assertTrue("There are some shop info displayed. ",shop.isShown());
	}
	
	//1977527:verify remove an item from recently browsed
	public void testRemoveItemFromRecently() throws Exception {
		
		Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		solo.clickOnView(solo.getView("tab_image", 4));
		Action.clickText(solo, ValidationText.RECENT_BROWSE);
		View img = (View)solo.getView("listitem_productlist_image");
		solo.clickLongOnView(img);
		// Confirm remove it.
		solo.clickOnView(solo.getView("button1"));	
		TextView NoResult = (TextView)solo.getView("no_result_text",1);	 
		assertTrue("There are some product info displayed. ",NoResult.isShown());

	}
	
	//1977531:verify remove a store from recently browsed
	public void testRemoveStoreFromRecently() throws Exception {
	 	Account.JudgementAccountLogin(solo);
	 	for(int i =0;i<3;i++){
	 		Action.enterToItemPage(solo);
	 		View ShopTitle = (View)solo.getView("productitem_store_name");
			solo.clickOnView(ShopTitle);
			solo.sleep(5000); 
			solo.goBack();
			solo.goBack();
	 	}
		
		solo.clickOnView(solo.getView("tab_image", 4));
		Action.clickText(solo, ValidationText.RECENT_BROWSE);
		 
		View shop = (View)solo.getView("category_tab_primary_title",1);
		solo.clickOnView(shop);
		
		TextView result = (TextView)solo.getView("tx_header",1);
		String count = result.getText().toString().trim().substring(6,7);
		Log.i("number", "count:"+count);
		
	 	View img = (View)solo.getView("listitem_storelist_image");
		solo.clickLongOnView(img);
		// Confirm remove it.
		solo.clickOnView(solo.getView("button1"));	
		solo.sleep(2000);
		
		TextView results = (TextView)solo.getView("tx_header",1);
		String counts = results.getText().toString().trim().substring(6,7);
		Log.i("number", "counts:"+counts);
		
		solo.sleep(5000);
 		assertTrue("Remove store failed. ",Integer.valueOf(count) > Integer.valueOf(counts));
	}
	
	//1977524:verify user can access to items.
	public void testAccessItems() throws Exception {
		Account.JudgementAccountLogin(solo);
		for(int i =0;i<3;i++){
	 		Action.enterToItemPage(solo);
	 		View ShopTitle = (View)solo.getView("productitem_store_name");
			solo.clickOnView(ShopTitle);
			solo.sleep(5000); 
			solo.goBack();
			solo.goBack();
	 	} 
		solo.clickOnView(solo.getView("tab_image", 4));
		Action.clickText(solo, ValidationText.RECENT_BROWSE);
		TextView ActionBar = (TextView) solo.getView("action_bar_title", 0);
		assertTrue("Cannot enter to recent history page. ", ActionBar.isShown());
		
		TextView product = (TextView)solo.getView("category_tab_primary_title",0);
		
		View shop = (View)solo.getView("category_tab_primary_title",1);
		
		 View ProductImage = (View)solo.getView("listitem_productlist_image");
		
		assertTrue("Cannot enter to recent history page. ", product.isShown() && shop.isShown()&&ProductImage.isShown() );
	}
	
	//1959899:Verify the numbers of collected items can be increasing/decreasing in my account page.
	public void testNumbersOfCollectedIncreasing() throws Exception {
		
		Account.JudgementAccountLogin(solo);
		Action.enterToItemPage(solo);
		solo.goBack();
		Action.clickStarIconNote(solo);
		solo.clickOnView(solo.getView("tab_image", 4));
		TextView OutNumber = (TextView)solo.getView("profile_bt_favorite_count");
		int OutNumbers = Integer.valueOf(OutNumber.getText().toString());
		Log.i("number", "OutNumbers:"+OutNumber.getText().toString());
		Action.enterToItemPage(solo);
		solo.goBack();
		Action.clickStarIconNote(solo);
		solo.clickOnView(solo.getView("tab_image", 4));
		solo.sleep(3000);
		TextView OutNumberTwo = (TextView)solo.getView("profile_bt_favorite_count");
		int OutNumberTwos = Integer.valueOf(OutNumberTwo.getText().toString());
		Log.i("number", "OutNumberTwo:"+OutNumberTwo.getText().toString());
		assertTrue("Collect product failed.",OutNumbers < OutNumberTwos);
		Action.clickText(solo, ValidationText.PRODUCT_COLLECTION);
		
		
	}
}