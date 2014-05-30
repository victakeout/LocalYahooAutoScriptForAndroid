package com.yahoo.mobile.client.android.ecstore.test.SRP;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.RadioButton;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SRP extends ActivityInstrumentationTestCase2 {
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

	public SRP() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());

	}

	@Override
	public void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

	// 1937914:back to category tab
	public void testBackToCategoryTab() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		Action.clickHomeButtonOnScreen(solo);

		solo.scrollToTop();
		Assert.CategoryListShow(solo);

	}

	// 1937918:check 'Tab' display.
	public void testTabDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		assertTrue(
				"Goods or stores is not display.",
				solo.searchText(ValidationText.Commodity)
						&& solo.searchText(ValidationText.Commodity));

	}

	// 1937919:Default to choose “商品” Tab.
	public void testDefaultTab() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		assertTrue("Goods tab is not selected.",
				Action.getIsViewShown(solo, "star_button"));

	}

	// 1937920:Navigate to store tab.
	public void testStoreTab() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		assertTrue("Store tab is not selected.",
				Action.getIsViewShown(solo, "heart_button"));

	}

	// 1937921:go back to goods tab.
	public void testBackToGoodsTab() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 0);

		assertTrue("Store tab is not selected.",
				Action.getIsViewShown(solo, "star_button"));

	}

	// 1937924:check Goods result text
	public void testSearchGoodsResultText() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// get the text of search result
		String str = Action.getValuesInTextview(solo,
				"category_tab_secondary_title", 0).replace(
				ValidationText.Results_value, "");

		assertTrue("The search result number format is incorrect! ",
				str.matches("[0-9]+"));

	}

	// 1937925:check store result text
	public void testSearchStoreResultText() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		// get the text of search result
		String str = Action.getValuesInTextview(solo,
				"category_tab_secondary_title", 1).replace(
				ValidationText.Results_value, "");
		assertTrue("The search result number format is incorrect! ",
				str.matches("[0-9]+"));

	}

	// 1937927:default show 20 items.
	public void testDefaultDisplay20Items() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		GridView gv = (GridView) solo.getView("gridview");

		// default count is 20
		assertEquals("Store tab is not selected:", gv.getCount(), 21);

	}

	// 1937928:Load more items.
	public void testLoadMoreItems() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// Action.closeSoftKeyBoard(solo);

		// swipe 4 times
		for (int k = 0; k < 4; k++) {
			TestHelper.swipeDown(solo, 1);
		}
		solo.sleep(3000);

		// get the numbers of grid view
		GridView gv = (GridView) solo.getView("gridview");
		int gv_count = gv.getCount();

		assertTrue("Store tab is not selected:", gv_count > 21);

	}

	// 1937931:check advanced page
	public void testCheckAdvancedPage() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Advanced screen
		Action.enterAdvancedPage(solo);

		Assert.navigateToAdvancedTab(solo);

	}

	// 1937932:Check if default to choose “sort” Tab
	public void testDefaultChooseSortTab() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);
		// navigate to Advanced screen
		Action.enterAdvancedPage(solo);

		TextView tv = (TextView) solo.getView("indicator_sort", 0);

		assertEquals("Sort tab is not the default option.", tv.getVisibility(),
				0);

	}

	// 1937933:Display sort tab
	public void testNavigateToSortTab() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Advanced screen
		Action.enterAdvancedPage(solo);

		// check if go to sort screen
		Assert.navigateToSortTab(solo);

	}

	// 1937934: display Filter tab
	public void testNavigateToFilterTab() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		// check if go to filter screen
		Assert.navigateToFilterTab(solo);

	}

	// 1937935: check sort tab items
	public void testCheckSortTabItems() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Advanced screen
		Action.enterAdvancedPage(solo);

		// check sort table items
		Assert.navigateToSortTab(solo);

		for (int i = 0; i < 3; i++) {
			// compare the position of two views
			boolean flag = TestHelper.positionCompare(solo, "text1", i,
					"text1", i + 1, 1);

			if (!flag) {
				assertTrue(
						"Store name is not on the right of store evaluation.",
						false);
			}
		}

	}

	// 1937940: check Layout
	public void testCheckLayoutOfFilterTab() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		// check if go to filter screen
		Assert.checkFilterLayout(solo);

	}

	// 1937944:check the function of “確定”button
	public void testCheckConfirmButtonFunction() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		// Action.closeSoftKeyBoard(solo);

		// Click on confirm button
		solo.clickOnView(solo.getView("btn_ok"));

		solo.sleep(3000);

		// get the number of gridview
		ArrayList<GridView> gv_list = solo.getCurrentViews(GridView.class);
		assertEquals("Go back to search result screen failed", gv_list.size(),
				1);

	}

	// 1937949:unselected “可刷卡”
	public void testUnselectedCanSwipeFunction() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		String view_id = "tb_cc";
		Action.clickView(solo, view_id);
		assertTrue("Can_Swipe button is not selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

		Action.clickView(solo, view_id);
		assertFalse("Can_Swipe button is selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());
	}

	// 1937950: check HasVideo display
	public void testHasVideoDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		// Action.closeSoftKeyBoard(solo);

		String view_id = "tb_hasvideo";
		Action.clickView(solo, view_id);
		assertTrue("Has video button is not selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

	}

	// 1937952:unselected “有影音”
	public void testUnselectedHasVideoFunction() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		// Action.closeSoftKeyBoard(solo);

		String view_id = "tb_hasvideo";
		Action.clickView(solo, view_id);
		assertTrue("Has video button is not selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

		Action.clickView(solo, view_id);
		assertFalse("Has video button is selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

	}

	// 1937955:unselected "0利率"
	public void testUnselectedZeroIntFunction() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		String view_id = "tb_cczeroint";
		Action.clickView(solo, view_id);
		assertTrue("CC zero button is not selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

		Action.clickView(solo, view_id);
		assertFalse("CC zero button is selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

	}

	// 1937958:unselected "可分期"
	public void testUnselectedccInstallFunction() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		String view_id = "tb_ccinstall";
		Action.clickView(solo, view_id);
		assertTrue("CC install button is not selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

		Action.clickView(solo, view_id);
		assertFalse("CC install button is selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

	}

	// 1937961:unselected "超商付款"
	public void testUnselectedCvsPayFunction() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		// Action.closeSoftKeyBoard(solo);

		String view_id = "tb_cvs_pay";
		Action.clickView(solo, view_id);
		assertTrue("Cvs pay button is not selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

		Action.clickView(solo, view_id);
		assertFalse("Cvs pay button is selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

	}

	/*
	 * // 1937964:unselected "超商取貨" public void testCvsPickDisplay() throws
	 * Exception {
	 * 
	 * // navigate to category screen Action.navigateToCategoryScreen(solo);
	 * 
	 * // click search button Action.clickSearchButtonOnScreen(solo);
	 * 
	 * // input keyword and search Action.searchAfterPutData(solo, 0,
	 * ValidationText.Jacket);
	 * 
	 * // navigate to Filter screen Action.enterAdvancedSortPage(solo);
	 * 
	 * // Action.closeSoftKeyBoard(solo);
	 * 
	 * String view_id = "tb_cvs_pick"; Action.clickView(solo, view_id);
	 * assertTrue("Cvs pay button is not selected.", ((ToggleButton)
	 * solo.getView(view_id)).isChecked());
	 * 
	 * }
	 */

	// 1937964:unselected "超商取貨"
	public void testUnselectedCvsPickFunction() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		// Action.closeSoftKeyBoard(solo);

		String view_id = "tb_cvs_pick";
		Action.clickView(solo, view_id);
		assertTrue("Cvs pay button is not selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

		Action.clickView(solo, view_id);
		assertFalse("Cvs pay button is selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

	}

	// 1937965:HasStock option display
	public void testHasStockOptionDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		// Action.closeSoftKeyBoard(solo);

		String view_id = "tb_hasstock";
		Action.clickView(solo, view_id);
		assertTrue("Has stock button is not selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

	}

	// 1937967:unselected "有現貨"
	public void testUnselectedHasStockFunction() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		// Action.closeSoftKeyBoard(solo);

		String view_id = "tb_hasstock";
		Action.clickView(solo, view_id);
		assertTrue("Has stock button is not selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

		Action.clickView(solo, view_id);
		assertFalse("Has stock button is selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

	}

	// 1937970:unselected "有圖片"
	public void testUnselectedHasImageFunction() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		// Action.closeSoftKeyBoard(solo);

		solo.sleep(300000);

		String view_id = "tb_hasimage";
		Action.clickView(solo, view_id);
		assertTrue("Has stock button is not selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

		Action.clickView(solo, view_id);
		assertFalse("Has stock button is selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

	}

	// 1937973:unselected "優良商店"
	public void testUnselectedisSuperiorFunction() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// navigate to Filter screen
		Action.enterAdvancedSortPage(solo);

		String view_id = "tb_issuperior";
		Action.clickView(solo, view_id);

		// Action.closeSoftKeyBoard(solo);

		assertTrue("Issuperior button is not selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

		Action.clickView(solo, view_id);
		assertFalse("Issuperior button is selected.",
				((ToggleButton) solo.getView(view_id)).isChecked());

	}

	// 1937976:Navigate to itempage
	public void testNavigateToItemPageByClickPicture() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		Action.setListViewStyleAfterSearch(solo);

		Action.clickView(solo, "listitem_productlist_image", 0);

		assertTrue("Navigate to item page failed.",
				solo.getView("productitem_btn_purchase_now", 0).isShown());

	}

	// 1937979:Navigate to itempage
	public void testNavigateToItemPageByClickGoodsName() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		Action.setListViewStyleAfterSearch(solo);

		Action.clickView(solo, "listitem_productlist_store_name", 0);

		assertTrue("Navigate to item page failed.",
				solo.getView("productitem_btn_purchase_now", 0).isShown());

	}

	// 1937980:Check the commodity price display.
	public void testCommodityPriceDisplayInListStyle() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// list style
		Action.setListViewStyleAfterSearch(solo);

		String sr = Action.getValuesInTextview(solo,
				"listitem_productlist_price", 0);

		// Judgment whether the price matches the format of '$xxx'.
		boolean isNum = sr.matches("[$][0-9]+");

		assertTrue(
				" Cannot find the commodity price or price format is incorrect! ",
				isNum);

	}

	// 1937981:Store name on the right of store evaluation
	public void testGoodsEvaluationDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// list style
		Action.setListViewStyleAfterSearch(solo);

		// compare the position of two views
		boolean flag = TestHelper.positionCompare(solo,
				"listitem_productlist_store_name", 0,
				"listitem_productlist_store_rating", 0, 3);

		if (!flag) {
			assertTrue("Store name is not on the right of store evaluation.",
					false);
		}

	}

	// 1937982:Commodity prices are on the left of stars
	public void testStarsDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// list style
		Action.setListViewStyleAfterSearch(solo);

		// compare the position of two views
		boolean flag = TestHelper.positionCompare(solo,
				"listitem_productlist_price", 0, "star_button", 0, 3);

		if (!flag) {
			assertTrue("Commodity prices are not on the left of stars.", false);
		}
	}

	// 1937983:Check to click the start icon without login.
	public void testStarIconWithoutLogin() throws Exception {

		// Account.accountLogIn(solo);
		Account.JudgementAccountWithoutLogin(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		Action.setListViewStyleAfterSearch(solo);

		View star = (View) solo.getView("star_button", 0);
		solo.clickOnView(star);

		if (!solo.waitForText(ValidationText.Please_login_account, 1, 12000))
			assertTrue("Error tips.", false);

	}

	// 1937984:Commodity joined successfully collection list
	public void testAddGoodsIntoList() throws Exception {

		// Account.accountLogIn(solo);
		Account.JudgementAccountWithoutLogin(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		Action.setListViewStyleAfterSearch(solo);

		// login
		Account.overAccountLogIn(solo);

		// click category view
		Action.clickView(solo, "tab_image", 2);

		Action.clickView(solo, "star_button", 0);

		if (solo.waitForText(ValidationText.Has_added_collection, 1, 12000)
				|| solo.waitForText(ValidationText.Has_removed_collection, 1,
						12000))
			solo.sleep(1000);
		else
			assertTrue("Add failed.", false);
	}

	// 1937985: check browser mode icon display
	public void testBroserModelDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		Action.setListViewStyleAfterSearch(solo);

		Action.enterAdvancedBrowserModePage(solo);

		assertTrue("List option is not selected! ",
				((RadioButton) solo.getView("btn_list_small")).isChecked());

	}

	// 1937986:check small picture icon
	public void testSmallPictureDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// small picture style
		Action.setSmallPhotoViewStyleAfterSearch(solo);

		Action.enterAdvancedBrowserModePage(solo);

		assertTrue("Small pictrue icon is not selected! ",
				((RadioButton) solo.getView("btn_list_grid")).isChecked());

	}

	// 1937993:Check the commodity price display.
	public void testCommodityPriceDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// small picture style
		Action.setSmallPhotoViewStyleAfterSearch(solo);

		String sr = Action.getValuesInTextview(solo,
				"listitem_productlist_price", 0);

		// Judgment whether the price matches the format of '$xxx'.
		boolean isNum = sr.matches("[$][0-9]+");

		assertTrue(
				" Cannot find the commodity price or price format is incorrect! ",
				isNum);

	}

	// 1937995:Check the Star icon display
	public void testStarIconDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		Action.setSmallPhotoViewStyleAfterSearch(solo);

		View star = (View) solo.getView("star_button", 0);
		assertTrue(" Cannot find the star icon ", star.isShown());

	}

	// 1937996:Check to click the start icon without login.
	public void testStarIconWithoutLoginInSmallPictureStyle() throws Exception {

		// Account.accountLogIn(solo);
		Account.JudgementAccountWithoutLogin(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		Action.setSmallPhotoViewStyleAfterSearch(solo);

		View star = (View) solo.getView("star_button", 0);
		solo.clickOnView(star);

		if (!solo.waitForText(ValidationText.Please_login_account, 1, 12000))
			assertTrue("Error tips.", false);

	}

	// 1937997:Commodity joined successfully collection list
	public void testAddGoodsIntoListInSmallPictureStyle() throws Exception {

		// Account.accountLogIn(solo);
		Account.JudgementAccountWithoutLogin(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		Action.setSmallPhotoViewStyleAfterSearch(solo);

		// login
		Account.overAccountLogIn(solo);

		// click category view
		Action.clickView(solo, "tab_image", 2);

		Action.clickView(solo, "star_button", 0);

		if (solo.waitForText(ValidationText.Has_added_collection, 1, 12000)
				|| solo.waitForText(ValidationText.Has_removed_collection, 1,
						12000))
			solo.sleep(1000);
		else
			assertTrue("Add failed.", false);
	}

	// 1937998:check large picture icon
	public void testLargePictureDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		Action.setLargePhotoViewStyleAfterSearch(solo);

		Action.enterAdvancedBrowserModePage(solo);

		assertTrue("Large pictrue icon is not selected! ",
				((RadioButton) solo.getView("btn_list_large")).isChecked());

	}

	// 1938001:navigate to item page
	public void testNavigateToItemPageInLargeStyle() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		Action.setLargePhotoViewStyleAfterSearch(solo);

		Action.clickView(solo, "listitem_productlist_image", 0);

		assertTrue("Navigate to item page failed.",
				solo.getView("productitem_btn_purchase_now", 0).isShown());

	}

	// 1938005:Check the commodity price display.
	public void testCommodityPriceDisplayInLargeStyle() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// large picture style
		Action.setLargePhotoViewStyleAfterSearch(solo);

		String sr = Action.getValuesInTextview(solo,
				"listitem_productlist_price", 0);

		// Judgment whether the price matches the format of '$xxx'.
		boolean isNum = sr.matches("[$][0-9]+");

		assertTrue(
				" Cannot find the commodity price or price format is incorrect! ",
				isNum);

		Action.setListViewStyleAfterSearch(solo);

	}

	// 1938006:Store name on the right of store evaluation
	public void testGoodsEvaluationDisplayInLargeStyle() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// large picture style
		Action.setLargePhotoViewStyleAfterSearch(solo);

		// compare the position of two views
		boolean flag = TestHelper.positionCompare(solo,
				"listitem_productlist_store_name", 0,
				"listitem_productlist_store_rating", 0, 3);

		if (!flag) {
			assertTrue("Store name is not on the right of store evaluation.",
					false);
		}

	}

	// 1938007:Commodity prices are on the left of stars
	public void testStarsDisplayInLargeStyle() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// large picture style
		Action.setLargePhotoViewStyleAfterSearch(solo);

		// compare the position of two views
		boolean flag = TestHelper.positionCompare(solo,
				"listitem_productlist_price", 0, "star_button", 0, 3);

		if (!flag) {
			assertTrue("Commodity prices are not on the left of stars.", false);
		}

	}

	// 1938008:Check to click the start icon without login.
	public void testStarIconWithoutLoginInLargeStyle() throws Exception {

		// Account.accountLogIn(solo);
		Account.JudgementAccountWithoutLogin(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// large picture style
		Action.setLargePhotoViewStyleAfterSearch(solo);

		View star = (View) solo.getView("star_button", 0);
		solo.clickOnView(star);

		if (!solo.waitForText(ValidationText.Please_login_account, 1, 12000))
			assertTrue("Error tips.", false);

	}

	// 1938013:"共XXX筆" is displayed
	public void testSearchResultDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		// get the text of search result
		String str = Action.getValuesInTextview(solo,
				"category_tab_secondary_title", 1).replace(
				ValidationText.Results_value, "");

		assertTrue("The search result number format is incorrect! ",
				str.matches("[0-9]+"));

	}

	// 1938015:back to category tab
	public void testBackToCategoryTabFromStoreTab() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		Action.clickHomeButtonOnScreen(solo);

		solo.scrollToTop();
		Assert.CategoryListShow(solo);

	}

	// 1938023:Store Logo below store name
	public void testStoreNameDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		// compare the position of two views
		boolean flag = TestHelper.positionCompare(solo,
				"listitem_storelist_image", 0,
				"listitem_storelist_store_namerow", 0, 1);

		if (!flag) {
			assertTrue("Store Logo is not below store name.", false);
		}

	}

	// 1938024:navigate to store page
	public void testNavigateToStorePage() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		// click on store icon
		Action.clickView(solo, "listitem_storelist_image", 0);

		String[] store_title = ValidationText.store_title;

		for (int i = 0; i < 2; i++) {

			String textview_value = Action.getValuesInTextview(solo,
					"category_tab_primary_title", i);
			assertEquals("Navigate to store page failed.", store_title[i],
					textview_value);
		}

	}

	// 1938025:store name blew store count
	public void testStoreCountDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		// compare the position of two views
		boolean flag = TestHelper.positionCompare(solo,
				"listitem_storelist_store_namerow", 0,
				"listitem_storelist_store_item_count", 0, 1);

		if (!flag) {
			assertTrue("Store name is not below store count.", false);
		}

	}

	// 1938026:navigate to store item list page
	public void testNavigateToStoreItemListPage() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		// click on store icon
		Action.clickView(solo, "listitem_storelist_store_item_count");

		String[] store_title = ValidationText.store_title;

		for (int i = 0; i < 2; i++) {

			String textview_value = Action.getValuesInTextview(solo,
					"category_tab_primary_title", i);
			assertEquals("Navigate to store page failed.", store_title[i],
					textview_value);
		}

	}

	// 1938027:Goods number on the right of evaluate
	public void testEvaluateDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		// compare the position of two views
		boolean flag = TestHelper.positionCompare(solo,
				"listitem_storelist_store_item_count", 0,
				"listitem_storelist_store_rating", 0, 3);

		if (!flag) {
			assertTrue("Goods number is not on the right of evaluate.", false);
		}

	}

	// 1938029: Goods evaluate on the right of heart icon
	public void testHeartIconDisplay() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		// compare the position of two views
		boolean flag = TestHelper.positionCompare(solo,
				"listitem_storelist_store_rating", 0, "heart_button", 0, 3);

		if (!flag) {
			assertTrue("Goods evaluate is not on the right of heart icon.",
					false);
		}

	}

	// 1938030:Navigate to login screen
	public void testNavigateToLoginScreen() throws Exception {

		// Account.accountLogIn(solo);
		Account.JudgementAccountWithoutLogin(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		View heart = (View) solo.getView("heart_button", 0);
		solo.clickOnView(heart);

		if (!solo.waitForText(ValidationText.Please_login_account, 1, 12000))
			assertTrue("Error tips.", false);

	}

	// 1938031:Shop to join the collection list,
	public void testAddStoreIntoCollectList() throws Exception {

		// Account.accountLogIn(solo);
		Account.JudgementAccountWithoutLogin(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click on store tab
		Action.clickView(solo, "category_tab_primary_title", 1);

		// login
		Account.overAccountLogIn(solo);

		// click category view
		Action.clickView(solo, "tab_image", 2);

		solo.clickOnView(solo.getView("heart_button", 0));

		boolean alreadyAdd;

		// Get toast text.
		if (solo.waitForText(ValidationText.Has_added_Commodity)) {
			alreadyAdd = solo.waitForText(ValidationText.Has_added_Commodity);
			junit.framework.Assert.assertTrue("Add failed.", alreadyAdd);
		} else {
			solo.sleep(1000);
			solo.clickOnView(solo.getView("heart_button", 0));
			alreadyAdd = solo.waitForText(ValidationText.Has_added_Commodity);
			junit.framework.Assert.assertTrue("Add failed.", alreadyAdd);

		}
	}

	// 1937978:check products name display.
	public void testProductsNameTwoLine() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		try {
			TextView tv = (TextView) solo.getView("listitem_productlist_title",
					1);
			Log.i("number", String.valueOf(tv.getMaxLines()));
			assertTrue("Not 2 lines.", tv.getMaxLines() == 2);
		} catch (AssertionError e) {
			// input keyword and search
			Action.searchAfterPutData(solo, 0, ValidationText.Jacket);
			TextView tv = (TextView) solo.getView("listitem_productlist_title",
					1);
			assertTrue("Not 2 lines.", tv.getMaxLines() == 2);
		}
	}

	// 1937913:check header display.
	public void testHeaderResult() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		assertTrue("some icon not exists.", solo.getView("").isShown());
	}

	// 1977511:verify shouldn't duplicate keyword in search box
	public void testInputKeywords() throws Exception {

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.one);

		solo.goBack();
		Action.clickSearchButtonOnScreen(solo);
		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.two);
		solo.goBack();
		Action.clickSearchButtonOnScreen(solo);
		assertTrue(ValidationText.two + " not found.",
				solo.searchText(ValidationText.two));

	}
}