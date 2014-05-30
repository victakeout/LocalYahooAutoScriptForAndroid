package com.yahoo.mobile.client.android.ecstore.test.Category;

 
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressWarnings("rawtypes")
public class Category extends ActivityInstrumentationTestCase2 {
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.yahoo.mobile.client.android.ecstore.ui.ECSplashActivity";
	private static Class launcherActivityClass;
	private Solo solo;
	private boolean isNum;
	static {
		try {
			launcherActivityClass = Class
					.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Category() throws ClassNotFoundException {
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

	// Go to clothes page.
	public void enterClassification() throws Exception {

		solo.waitForActivity("ECSplashActivity", 3000);
		Action.clickText(solo, ValidationText.All_Categories);
		Action.clickText(solo, ValidationText.Apparel);

	}

	// 1938037:Check back function.
	public void testBackFunction() throws Exception {

		enterClassification();
		solo.clickOnView(solo.getView("up", 0));
		solo.sleep(3000);
		Assert.CategoryListShow(solo);

	}

	// 1938041:Check tab display.
	public void testTab() throws Exception {

		enterClassification();
		int size = ValidationText.store_title.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(ValidationText.store_title[i]);
			assertTrue(ValidationText.store_title[i] + " not found", textFound);
		}

	}

	// 1938036:Check header items.
	public void testHeader() throws Exception {

		enterClassification();
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		// Back button
		ImageView back = (ImageView) solo.getView("home");

		// Search button
		View search = solo.getView("menu_search");

		// Filter button
		View advance = solo.getView("menu_filter");

		boolean views = back.isShown() && search.isShown() && advance.isShown();
		assertTrue("views not found", views);

	}

	// 1938042:Check sort tab items all display.
	public void testSortTab() throws Exception {

		enterClassification();
		int size = ValidationText.CostumeList.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo.searchText(ValidationText.CostumeList[i]);
			assertTrue(ValidationText.CostumeList[i] + " not found", textFound);
		}

	}

	// 1938043:check return to item list.
	public void testItemList() throws Exception {

		enterClassification();
		Action.clickText(solo, ValidationText.Commodity);
		Action.clickText(solo, ValidationText.Categories);
		solo.goBack();
		solo.sleep(1000);
		Assert.CategoryListShow(solo);

	}

	// 1938052:check "搜寻服饰" show in search bar.
	public void testSearchbarDefault() throws Exception {

		enterClassification();
		solo.sleep(3000);
		solo.clickOnView(solo.getView("menu_search"));
		assertTrue("Cannot find text",
				solo.searchText(ValidationText.Search_Apparel, 1));

	}

	// 1938053:check switch to sort tab.
	public void testSort() throws Exception {

		enterClassification();
		Action.enterAdvancedPage(solo);
		int size = ValidationText.CategoryList_Tab1.length;

		for (int i = 0; i < size; i++) {
			boolean textFound = solo
					.searchText(ValidationText.CategoryList_Tab1[i]);
			assertTrue(ValidationText.CategoryList_Tab1[i] + " not found",
					textFound);
		}

	}

	// 1938054:check switch to filter sort tab.
	public void testFilterSort() throws Exception {

		enterClassification();
		solo.sleep(2000);
		Action.enterAdvancedPage(solo);
		solo.sleep(3000);
		solo.clickOnView(solo.getView("btn_filter"));

		int size = ValidationText.CategoryList_Tab2.length;
		for (int i = 0; i < size; i++) {
			boolean textFound = solo
					.searchText(ValidationText.CategoryList_Tab2[i]);
			assertTrue(ValidationText.CategoryList_Tab2[i] + " not found",
					textFound);
		}

	}

	// 1938060:check the sort layout
	public void testSortLayout() throws Exception {

		enterClassification();
		Action.enterAdvancedPage(solo);
		solo.sleep(3000);
		solo.clickOnView(solo.getView("btn_filter"));
		View ScrollBar = solo.getView("seekbar", 0);
		View TableRowOne = solo.getView("tableRow1", 0);
		View TableRowTwo = solo.getView("tableRow2", 0);
		View TableRowThree = solo.getView("tableRow3", 0);
		solo.sleep(3000);
		boolean views = ScrollBar.isShown() && TableRowOne.isShown()
				&& TableRowTwo.isShown() && TableRowThree.isShown();
		assertTrue("views not found.", views);

	}

	// 2014-04-18
	// 1938055: verify the order of 排序 items
	public void testSortOptions() throws Exception {

		enterClassification();
		Action.enterAdvancedPage(solo);

		ListView lv = (ListView) solo.getView("list_sort", 0);
		lv.getItemAtPosition(0);
		int listviewCount = lv.getCount();
		assertEquals("Not four items in list.", listviewCount, 4);
		for (int i = 0; i < listviewCount; i++) {

			boolean sortList = lv.getItemAtPosition(0).equals(
					ValidationText.CategoryList_Tab1[0])
					&& lv.getItemAtPosition(1).equals(
							ValidationText.CategoryList_Tab1[1])
					&& lv.getItemAtPosition(2).equals(
							ValidationText.CategoryList_Tab1[2])
					&& lv.getItemAtPosition(3).equals(
							ValidationText.CategoryList_Tab1[3]);

			assertTrue("Sort incorrect.", sortList);

		}

	}

	// 1938063:Check the "确定" button to display
	public void testComfirmButtonDisplay() throws Exception {

		enterClassification();
		Action.enterAdvancedPage(solo);
		solo.sleep(3000);
		solo.clickOnView(solo.getView("btn_filter"));
		Button lv = (Button) solo.getView("btn_ok");
		assertEquals("Not find confirm button.", ValidationText.OK, lv
				.getText().toString());

	}

	// 1938047:check default items display.
	public void testCheckTheDefaultItems() throws Exception {

		enterClassification();
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		GridView lv = (GridView) solo.getView("gridview", 0);
		int defaultItems = lv.getCount();
		assertEquals("The default items incorrect.", 21, defaultItems);

	}

	// 1938048:check auto load more data.
	public void testAutoLoadMore() throws Exception {

		enterClassification();
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		GridView lv = (GridView) solo.getView("gridview", 0);

		// Scroll the screen to load more data.
		for (int i = 0; i < 8; i++) {
			TestHelper.swipeUp(solo, 1);
		}

		boolean listviewCount2 = lv.getCount() > 22;
		assertTrue("The default items incorrect.", listviewCount2);

	}

	// 1938069:check “可刷卡” can changed to unselected.
	public void testCreditCardMode() throws Exception {

		enterClassification();

		// Go to advanced sort page.
		Action.enterAdvancedSortPage(solo);

		// solo.clickOnToggleButton("可刷卡");
		ToggleButton tb = (ToggleButton) solo.getView("tb_cc");

		solo.clickOnView(tb);
		solo.sleep(3000);
		assertTrue(" '可刷卡'  button unselected.", tb.isChecked());
		solo.clickOnView(tb);
		solo.sleep(3000);
		assertFalse("'可刷卡'  button  selected.", tb.isChecked());

	}

	// 2014-04-22
	// 1938072:check “有影音” can changed to unselected.
	public void testHasVideoMode() throws Exception {

		enterClassification();

		// Go to advanced sort page.
		Action.enterAdvancedSortPage(solo);

		// Get "hasVideo" button.
		ToggleButton tb = (ToggleButton) solo.getView("tb_hasvideo");

		solo.clickOnView(tb);
		solo.sleep(3000);
		assertTrue(" '有影音'  button unselected.", tb.isChecked());
		solo.clickOnView(tb);
		solo.sleep(3000);
		assertFalse("'有影音'  button  selected.", tb.isChecked());

	}

	// 1938075:check “0利率” can changed to unselected.
	public void testZeroInterestMode() throws Exception {

		enterClassification();

		// Go to advanced sort page.
		Action.enterAdvancedSortPage(solo);

		// Get "0 Interest" button.
		ToggleButton tb = (ToggleButton) solo.getView("tb_cczeroint");

		solo.clickOnView(tb);
		solo.sleep(3000);
		assertTrue(" '0利率'  button unselected.", tb.isChecked());
		solo.clickOnView(tb);
		solo.sleep(3000);
		assertFalse("'0利率'  button  selected.", tb.isChecked());
	}

	// 1938078:check “可分期” can changed to unselected.
	public void testInstallmentsMode() throws Exception {

		enterClassification();

		// Go to advanced sort page.
		Action.enterAdvancedSortPage(solo);

		// Get "Installments" button.
		ToggleButton tb = (ToggleButton) solo.getView("tb_ccinstall");

		solo.clickOnView(tb);
		solo.sleep(3000);
		assertTrue(" '可分期'  button unselected.", tb.isChecked());
		solo.clickOnView(tb);
		solo.sleep(3000);
		assertFalse("'可分期'  button  selected.", tb.isChecked());

	}

	// 1938081:check “超商付款” can changed to unselected.
	public void testSupermarketPaymentMode() throws Exception {

		enterClassification();

		// Go to advanced sort page.
		Action.enterAdvancedSortPage(solo);

		// Get "Payment" button.
		ToggleButton tb = (ToggleButton) solo.getView("tb_cvs_pay");

		solo.clickOnView(tb);
		solo.sleep(3000);
		assertTrue(" '超商付款'  button unselected.", tb.isChecked());
		solo.clickOnView(tb);
		solo.sleep(3000);
		assertFalse("'超商付款'  button  selected.", tb.isChecked());

	}

	// 1938084:check “超商取貨” can changed to unselected.
	public void testSupermarketPickupMode() throws Exception {

		enterClassification();

		// Go to advanced sort page.
		Action.enterAdvancedSortPage(solo);

		// Get "Pickup" button.
		ToggleButton tb = (ToggleButton) solo.getView("tb_cvs_pick");

		solo.clickOnView(tb);
		solo.sleep(3000);
		assertTrue(" '超商取貨'  button unselected.", tb.isChecked());
		solo.clickOnView(tb);
		solo.sleep(3000);
		assertFalse("'超商取貨'  button  selected.", tb.isChecked());

	}

	// 1938087:check “有現貨” can changed to unselected.
	public void testHasStockMode() throws Exception {
		enterClassification();

		// Go to advanced sort page.
		Action.enterAdvancedSortPage(solo);

		// Get "Has Stock" button.
		ToggleButton tb = (ToggleButton) solo.getView("tb_hasstock");

		solo.clickOnView(tb);
		solo.sleep(3000);
		assertTrue(" '有現貨'  button unselected.", tb.isChecked());
		solo.clickOnView(tb);
		solo.sleep(3000);
		assertFalse("'有現貨'  button  selected.", tb.isChecked());

	}

	// 1938090:check “有圖片” can changed to unselected.
	public void testHasImageMode() throws Exception {
		enterClassification();

		// Go to advanced page.
		Action.enterAdvancedSortPage(solo);

		// Get "HasImage" button.
		ToggleButton tb = (ToggleButton) solo.getView("tb_hasimage");

		solo.clickOnView(tb);
		solo.sleep(3000);
		assertTrue(" '有圖片'  button unselected.", tb.isChecked());
		solo.clickOnView(tb);
		solo.sleep(3000);
		assertFalse("'有圖片'  button  selected.", tb.isChecked());

	}

	// 1938093:check “優良商店” can changed to unselected.
	public void testSuperiorStoreMode() throws Exception {

		enterClassification();

		// Go to advanced page.
		Action.enterAdvancedSortPage(solo);

		// Get "SuperiorStore" button.
		ToggleButton tb = (ToggleButton) solo.getView("tb_issuperior");

		solo.clickOnView(tb);
		solo.sleep(3000);
		assertTrue(" '優良商店'  button unselected.", tb.isChecked());
		solo.clickOnView(tb);
		solo.sleep(3000);
		assertFalse("'優良商店'  button  selected.", tb.isChecked());

	}

	// 1938100:Check the commodity price display.
	public void testCommodityPriceDisplay() throws Exception {

		enterClassification();
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		TextView price;
		try {
			price = (TextView) solo.getView("listitem_productlist_price", 0);
		} catch (AssertionError e) {
			enterClassification();
			Action.clickText(solo, ValidationText.Commodity);
			price = (TextView) solo.getView("listitem_productlist_price", 0);
		}
		String sr = price.getText().toString();

		// Judgment whether the price matches the format of '$xxx'.
		boolean isNum = sr.matches("[$][0-9]+");

		assertTrue(
				" Cannot find the commodity price or price format is incorrect! ",
				isNum);

	}

	// 1938101:Check the Shops score display.
	public void testShopsScoreDisplay() throws Exception {

		enterClassification();
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		TextView price = null;
		try {
			price = (TextView) solo.getView(
					"listitem_productlist_store_rating", 0);
		} catch (AssertionError e) {
			enterClassification();
			Action.clickText(solo, ValidationText.Commodity);
			solo.sleep(3000);
		}
		String sr = price.getText().toString();

		// Judgment whether the price matches the format of 'x.x'.
		boolean isNum = sr.matches("^[0-9].[0-9]+$");

		assertTrue(
				" Cannot find the shops score or score format is incorrect! ",
				isNum);

	}

	// 2014-04-24
	// 1938102:Check the Star icon display
	public void testStarIconDisplay() throws Exception {

		enterClassification();
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		View star = (View) solo.getView("star_button", 1);
		assertTrue(" Cannot find the star icon ", star.isShown());

	}

	// 2014-04-25
	// 1938130:Check "全部分類" at the bottom of the screen.
	public void testAllClassificationExist() throws Exception {

		View classificationIcon = (View) solo.getView("tab_image", 2);

		TextView classificationText = (TextView) solo.getView("tab_text", 2);
		boolean text = classificationText.getText().toString()
				.equals(ValidationText.All_Categories);

		assertTrue("All classification does not exist.",
				classificationIcon.isShown() && text);

	}

	// 1938131:Check all classification item page.
	public void testAllClassificationItemPage() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		Assert.CategoryListShow(solo);

	}

	// 1938133:Check the screen top text.
	public void testClassificationTextOnTheTop() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		TextView classificationText = (TextView) solo
				.getView("action_bar_title");
		boolean text = classificationText.getText().toString().trim()
				.equals(ValidationText.All_Categories);

		assertTrue("All classification text does not exist.", text);

	}

	// 1938135:Check the search icon on the screen top.
	public void testSearchIconOnTheTop() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		View searchIcon = (View) solo.getView("menu_search", 0);

		assertTrue("Search icon does not exist.", searchIcon.isShown());

	}

	// 1938141:Check latest update side bar.
	public void testLatestUpdateSidebar() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));

		TextView mostFavoriteText = (TextView) solo.getView("tab_text", 0);
		boolean text = mostFavoriteText.getText().toString()
				.equals(ValidationText.News);

		View latestUpdateIcon = (View) solo.getView("tab_image", 0);

		assertTrue("Latest update sidebar does not exist.",
				latestUpdateIcon.isShown() && text);
	}

	// 1938143:Check most favorite store side bar.
	public void testMostFavoriteSidebar() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));

		TextView mostFavoriteText = (TextView) solo.getView("tab_text", 1);
		boolean text = mostFavoriteText.getText().toString()
				.equals(ValidationText.Favorite_Stores);
		String texts = mostFavoriteText.getText().toString();
		Log.i("what", texts);
		View mostFavoriteIcon = (View) solo.getView("tab_image", 1);

		assertTrue("Most favorite sidebar does not exist.",
				mostFavoriteIcon.isShown() && text);
	}

	// 1938145:Check shopping Cart side bar
	public void testShoppingCartSidebar() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));

		TextView shoppingCart = (TextView) solo.getView("tab_text", 3);
		boolean text = shoppingCart.getText().toString()
				.equals(ValidationText.Shopping_Cart);

		View shoppingCartIcon = (View) solo.getView("tab_image", 3);

		assertTrue("Shopping Cart sidebar does not exist.",
				shoppingCartIcon.isShown() && text);
	}

	// 1938147:Check my account side bar.
	public void testMyAccountSidebar() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));

		TextView myAccount = (TextView) solo.getView("tab_text", 4);
		boolean text = myAccount.getText().toString().trim()
				.equals(ValidationText.My_Account);

		String temp = myAccount.getText().toString();
		Log.i("what", temp);

		View myAccountIcon = (View) solo.getView("tab_image", 4);

		assertTrue("My account sidebar does not exist.",
				myAccountIcon.isShown() && text);
	}

	// 1938149:Check '服飾' is displayed on the top of the screen.
	public void testDressDisplayedOnTheScreen() throws Exception {

		enterClassification();
		TextView dressText = (TextView) solo.getView("action_bar_title", 0);
		boolean text = dressText.getText().toString().trim()
				.equals(ValidationText.Apparel);
		Log.i("what", dressText.getText().toString());
		assertTrue("dress does not exist.", text);

	}

	// 1938150:Check '美妝' is displayed on the top of the screen.
	public void testMakeupDisplayedOnTheScreen() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		solo.clickOnText(ValidationText.Beauty);
		solo.sleep(2000);
		TextView makeupText = (TextView) solo.getView("action_bar_title", 0);
		boolean text = makeupText.getText().toString().trim()
				.equals(ValidationText.Beauty);
		assertTrue("Makeup does not exist.", text);

	}

	// 1938151:Check '鞋包配飾' is displayed on the top of the screen.
	public void testAccessoriesDisplayedOnTheScreen() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		solo.clickOnText(ValidationText.Shoes_Bags_Accessories);
		solo.sleep(2000);
		TextView accessories = (TextView) solo.getView("action_bar_title", 0);
		boolean text = accessories.getText().toString().trim()
				.equals(ValidationText.Shoes_Bags_Accessories);
		assertTrue("Accessories does not exist.", text);

	}

	// 1938152:Check '媽咪寶貝' is displayed on the top of the screen.
	public void testBabyDisplayedOnTheScreen() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		solo.searchText(ValidationText.Computers_Peripherals);
		solo.clickOnText(ValidationText.Mommy_Baby);
		solo.sleep(2000);
		TextView baby = (TextView) solo.getView("action_bar_title", 0);
		boolean text = baby.getText().toString().trim()
				.equals(ValidationText.Mommy_Baby);

		assertTrue("Mom's Baby text does not exist.", text);

	}

	// 1938153:Check '電腦/週邊' is displayed on the top of the screen.
	public void testComputerDisplayedOnTheScreen() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		solo.clickOnText(ValidationText.Computers_Peripherals);
		solo.sleep(2000);
		TextView Computer = (TextView) solo.getView("action_bar_title", 0);
		boolean text = Computer.getText().toString().trim()
				.equals(ValidationText.Computers_Peripherals);

		assertTrue("Computer text does not exist.", text);

	}

	// 1938154:Check '家電/視聽' is displayed on the top of the screen.
	public void testHouseholdAppliancesDisplayedOnTheScreen() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		solo.clickOnText(ValidationText.HomeAppliances_AV);
		solo.sleep(2000);
		TextView householdAppliances = (TextView) solo.getView(
				"action_bar_title", 0);
		boolean text = householdAppliances.getText().toString().trim()
				.equals(ValidationText.HomeAppliances_AV);

		Log.i("what", householdAppliances.getText().toString());

		assertTrue("Computer text does not exist.", text);

	}

	// 1938155:Check '相機/手機/玩具' is displayed on the top of the screen.
	public void testFasionDigitalDisplayedOnTheScreen() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		solo.searchText(ValidationText.Gourmet_Health_Beverage);
		solo.clickOnText(ValidationText.Camera_Mobile_Toys);
		solo.sleep(3000);
		TextView digital = (TextView) solo.getView("action_bar_title", 0);
		boolean text = digital.getText().toString().trim()
				.equals(ValidationText.Camera_Mobile_Toys2);
		Log.i("what", digital.getText().toString());
		assertTrue("Fasion digital text does not exist.", text);

	}

	// 1938156:Check '美食/保健/飲料' is displayed on the top of the screen.
	public void testFoodDisplayedOnTheScreen() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		solo.clickOnText(ValidationText.Gourmet_Health_Beverage);
		solo.sleep(2000);
		TextView food = (TextView) solo.getView("action_bar_title", 0);
		boolean text = food.getText().toString().trim()
				.equals(ValidationText.Gourmet_Health_Beverage2);
		Log.i("what", food.getText().toString().trim());
		assertTrue("Food text does not exist.", text);

	}

	// 1938157:Check '醫療/日用品/寵物' is displayed on the top of the screen.
	public void testCleanDisplayedOnTheScreen() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		solo.clickOnText(ValidationText.Medical_Commodity_pet);
		solo.sleep(2000);
		TextView clean = (TextView) solo.getView("action_bar_title", 0);
		boolean text = clean.getText().toString().trim()
				.equals(ValidationText.Medical_Commodity_pet2);

		assertTrue("Clean text does not exist.", text);

	}

	// 1938158:Check '居家/寢具/傢俱' is displayed on the top of the screen.
	public void testHomeDisplayedOnTheScreen() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		solo.clickOnText(ValidationText.Home_Bedding_Furniture);
		solo.sleep(2000);
		TextView home = (TextView) solo.getView("action_bar_title", 0);
		boolean text = home.getText().toString().trim()
				.equals(ValidationText.Home_Bedding_Furniture2);

		assertTrue("home text does not exist.", text);

	}

	// 1938159:Check '運動/戶外/休閒' is displayed on the top of the screen.
	public void testSportDisplayedOnTheScreen() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		solo.clickOnText(ValidationText.Sports_Outdoor_Recreation);
		solo.sleep(2000);
		TextView sport = (TextView) solo.getView("action_bar_title", 0);
		boolean text = sport.getText().toString().trim()
				.equals(ValidationText.Sports_Outdoor_Recreation2);

		assertTrue("sport text does not exist.", text);

	}

	// 1938160:Check '圖書/文具/影音' is displayed on the top of the screen.
	public void testBookDisplayedOnTheScreen() throws Exception {

		solo.clickOnView(solo.getView("tab_image", 2));
		solo.clickOnText(ValidationText.Books_Stationery_Video);
		solo.sleep(2000);
		TextView book = (TextView) solo.getView("action_bar_title", 0);
		boolean text = book.getText().toString().trim()
				.equals(ValidationText.Books_Stationery_Video2);

		assertTrue("book text does not exist.", text);

	}

	// 2014-04-30
	// 1938103:Check to click the start icon without login.
	public void testStarIconWithoutLogin() throws Exception {

		// Account.accountLogIn(solo);
		Account.JudgementAccountWithoutLogin(solo);
		// Account.accountLogIn(solo);
		enterClassification();
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		View star = (View) solo.getView("star_button", 0);
		solo.clickOnView(star);
		solo.sleep(3000);

		// Get toast text.
		TextView toastTextView = (TextView) solo.getView("message", 0);
		if (toastTextView != null) {
			String toastText = toastTextView.getText().toString();
			assertEquals(toastText, ValidationText.Please_login_account);
		}
	}

	// 1938104:Check to click the start icon when login.
	public void testStartIconWhenLogin() throws Exception {

		Account.JudgementAccountLogin(solo);
		enterClassification();
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		Action.clickStarIconNote(solo);
	}

	// 2014-05-13
	// 1938116:Check to click the start icon without login in grid view.
	public void testStarIconWithoutLoginInGridView() throws Exception {

		// Account.accountLogIn(solo);
		Account.JudgementAccountWithoutLogin(solo);

		enterClassification();

		Action.setSmallPhotoViewStyleAfterSearch(solo);

		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		View star = (View) solo.getView("star_button", 1);
		solo.clickOnView(star);

		// Get toast text.
		TextView toastTextView = (TextView) solo.getView("message", 0);
		if (toastTextView != null) {
			String toastText = toastTextView.getText().toString();
			assertEquals(toastText, ValidationText.Please_login_account);
		}

		// Restore to list view.
		Action.setListViewStyleAfterSearch(solo);
	}

	// 1938117:Check to click the start icon in grid view when login.
	public void testStartIconInGridViewWhenLogin() throws Exception {

		Account.JudgementAccountLogin(solo);
		enterClassification();

		// Change the item view to photo grid view
		Action.setSmallPhotoViewStyleAfterSearch(solo);

		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);

		Action.clickStarIconNote(solo);

		// Restore to list view.
		Action.setListViewStyleAfterSearch(solo);
	}

	// 1938115:Check the Star icon display in grid view.
	public void testStarIconDisplayInGridView() throws Exception {

		enterClassification();

		// Change the item view to photo grid view
		Action.setSmallPhotoViewStyleAfterSearch(solo);

		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		View star = (View) solo.getView("star_button", 1);
		assertTrue(" Cannot find star icon ", star.isShown());

		// Restore to list view.
		Action.setListViewStyleAfterSearch(solo);
	}

	// 1938113:Check the commodity price displays in grid view.
	public void testCommodityPriceDisplayInGridView() throws Exception {

		enterClassification();

		// Change the item view to photo grid view
		Action.setSmallPhotoViewStyleAfterSearch(solo);

		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		TextView price = (TextView) solo.getView("listitem_productlist_price",
				0);
		String sr = price.getText().toString();

		// Judgment whether the price matches the format of '$xxx'.
		boolean isNum = sr.matches("[$][0-9]+");

		assertTrue(
				" Cannot find the commodity price or price format is incorrect! ",
				isNum);
		// Restore to list view.
		Action.setListViewStyleAfterSearch(solo);
	}

	// 1938114:Check the Shops score displays in grid view.
	public void testShopsScoreDisplayInGridView() throws Exception {

		enterClassification();

		// Change the item view to photo grid view
		Action.setSmallPhotoViewStyleAfterSearch(solo);

		solo.waitForText(ValidationText.Commodity, 1, 3000);
		solo.clickOnText(ValidationText.Commodity);
		solo.sleep(3000);
		TextView price = (TextView) solo.getView(
				"listitem_productlist_store_rating", 0);
		String sr = price.getText().toString();

		// Judgment whether the price matches the format of 'x.x'. boolean isNum
		isNum = sr.matches("^[0-9].[0-9]+$");

		assertTrue(
				" Cannot find the shops score or score format is incorrect! ",
				isNum);

		// Restore to list view. Action.setListViewStyleAfterSearch(solo);
	}

	// 1938125:Check the commodity price displays in large photo view.
	public void testCommodityPriceDisplayInLargePhotoView() throws Exception {

		enterClassification();

		// Change the item view to photo large photo view
		Action.setLargePhotoViewStyleAfterSearch(solo);

		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		TextView price = (TextView) solo.getView("listitem_productlist_price",
				0);
		String sr = price.getText().toString();

		// Judgment whether the price matches the format of '$xxx'.
		boolean isNum = sr.matches("[$][0-9]+");

		assertTrue(
				" Cannot find the commodity price or price format is incorrect! ",
				isNum);
		// Restore to list view.
		Action.setListViewStyleAfterSearch(solo);
	}

	// 1938126:Check the Shops score displays in large photo view.
	public void testShopsScoreDisplayInLargePhotoView() throws Exception {

		enterClassification();

		// Change the item view to photo large photo view
		Action.setLargePhotoViewStyleAfterSearch(solo);

		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		TextView price = null;
		try {
			price = (TextView) solo.getView(
					"listitem_productlist_store_rating", 0);
		} catch (AssertionError e) {
			solo.clickOnText(ValidationText.Commodity);
		}
		String sr = price.getText().toString();

		// Judgment whether the price matches the format of 'x.x'.
		boolean isNum = sr.matches("^[0-9].[0-9]+$");

		assertTrue(
				" Cannot find the shops score or score format is incorrect! ",
				isNum);

		// Restore to list view.
		Action.setListViewStyleAfterSearch(solo);
	}

	// 1938127:Check the Star icon display in large photo view.
	public void testStarIconDisplayInLargePhotoView() throws Exception {

		enterClassification();

		// Change the item view to photo large photo view
		Action.setLargePhotoViewStyleAfterSearch(solo);

		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		View star = (View) solo.getView("star_button", 0);
		assertTrue(" Cannot find star icon ", star.isShown());

		// Restore to list view.
		Action.setListViewStyleAfterSearch(solo);
	}

	// 1938128:Check to click the start icon without login in large photo view.
	public void testStarIconWithoutLoginInLargePhotoView() throws Exception {

		// Change the item view to photo large photo view
		Action.setLargePhotoViewStyleAfterSearch(solo);

		enterClassification();
		Action.setSmallPhotoViewStyleAfterSearch(solo);

		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		View star = (View) solo.getView("star_button", 1);
		solo.clickOnView(star);

		// Get toast text.
		TextView toastTextView = (TextView) solo.getView("message", 0);
		if (toastTextView != null) {
			String toastText = toastTextView.getText().toString();
			assertEquals(toastText, ValidationText.Please_login_account);
		}

		// Restore to list view.
		Action.setListViewStyleAfterSearch(solo);
	}

	// 1938129:Check to click the start icon in large photo view when login.
	public void testStartIconInLargePhotoViewWhenLogin() throws Exception {

		Account.JudgementAccountLogin(solo);
		enterClassification();

		// Change the item view to photo large photo view
		Action.setLargePhotoViewStyleAfterSearch(solo);

		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		try {
		} catch (AssertionError e) {
			TestHelper.swipeUp2(solo, 1);
		}
		solo.sleep(1000);
		Action.clickStarIconNote(solo);

		// Restore to list view.
		Action.setListViewStyleAfterSearch(solo);
	}

	// 1938045:Check search result.
	public void testSearchResult() throws Exception {
		enterClassification();
		solo.clickOnText(ValidationText.Commodity);
		solo.waitForView(solo.getView("category_tab_secondary_title", 1));
		TextView result = null;
		try {
			result = (TextView) solo.getView("category_tab_secondary_title", 1);
			assertTrue("Result is not displayed.", result.isShown());
		} catch (AssertionError e) {
			enterClassification();
			solo.clickOnText(ValidationText.Commodity);
			solo.waitForView(solo.getView("category_tab_secondary_title"));
			result = (TextView) solo.getView("category_tab_secondary_title", 1);
		}
		assertTrue("Result is not displayed.", result.isShown());
		String X = result.getText().toString();

		boolean isNum = X.matches("[0-9]+" + ValidationText.Results_value);

		if (isNum) {
			assertTrue("Search result format incorrect.", true);
		} else {
			assertTrue("Search result format correct.", false);
		}

	}

	// 1938049:check advanced page tab display.
	public void testAdvancedPage() throws Exception {

		enterClassification();
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		// Filter button
		solo.clickOnView(solo.getView("menu_filter"));
		// Button sort = (Button) solo.getView("btn_sort", 0);
		TextView sort_tv = (TextView) solo.getView("indicator_sort");
		assertTrue("The first tab text is incorrect.", sort_tv.isShown());

		// Check the second button and check the highlight line whether if
		// focused.
		Button mode = (Button) solo.getView("btn_browse_mode", 0);
		solo.clickOnView(mode);
		TextView mode_tv = (TextView) solo.getView("indicator_browse_mode");
		assertTrue("The second tab text is incorrect.", mode_tv.isShown());

		// Check the third button and check the highlight line whether if
		// focused.
		Button filter = (Button) solo.getView("btn_filter", 0);
		solo.clickOnView(filter);
		TextView filter_tv = (TextView) solo.getView("indicator_filter");
		assertTrue("The third tab text is incorrect.", filter_tv.isShown());

	}

	// 1938099:check advanced page tab display.
	public void testTapProductName() throws Exception {

		enterClassification();
		Action.clickText(solo, ValidationText.Commodity);
		solo.sleep(3000);
		solo.clickInList(1);
		solo.sleep(5000);
		View imageView = (View) solo.getView("productitem_images");
		assertTrue("Not in item page.", imageView.isShown());

	}

	// 1938112:check advanced page tab display.
	public void testTapProductNameInGridView() throws Exception {

		enterClassification();
		Action.setSmallPhotoViewStyleAfterSearch(solo);
		solo.sleep(3000);
		solo.clickInList(1);
		solo.sleep(5000);
		View imageView = (View) solo.getView("productitem_images");
		assertTrue("Not in item page.", imageView.isShown());
		Action.setListViewStyleAfterSearch(solo);
	}

	// 1938098:
	public void testTwoLineDisplay() throws Exception {

		enterClassification();
		solo.clickOnText(ValidationText.Commodity);
		try {
			TextView tv = (TextView) solo.getView("listitem_productlist_title",
					1);
			Log.i("number", String.valueOf(tv.getMaxLines()));
			assertTrue("Not 2 lines.", tv.getMaxLines() == 2);
		} catch (AssertionError e) {
			solo.clickOnText(ValidationText.Commodity);
			TextView tv = (TextView) solo.getView("listitem_productlist_title",
					1);
			assertTrue("Not 2 lines.", tv.getMaxLines() == 2);
		}
	}

	//
	public void testTwoLineDisplayInLargeView() throws Exception {

		
		enterClassification();
		solo.clickOnText(ValidationText.Commodity);
		Action.setLargePhotoViewStyleAfterSearch(solo);
		try {
			TextView tv = (TextView) solo.getView("listitem_productlist_title");
			Log.i("number", String.valueOf(tv.getMaxLines()));
			assertTrue("Not 2 lines.", tv.getMaxLines() == 2);
			Action.setListViewStyleAfterSearch(solo);
		} catch (AssertionError e) {
			solo.clickOnText(ValidationText.Commodity);
			TextView tv = (TextView) solo.getView("listitem_productlist_title");
			assertTrue("Not 2 lines.", tv.getMaxLines() == 2);
			Action.setListViewStyleAfterSearch(solo);
		}
		
	}
}
