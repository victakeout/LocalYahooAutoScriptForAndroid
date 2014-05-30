package com.yahoo.mobile.client.android.ecstore.test.Search;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Search extends ActivityInstrumentationTestCase2 {
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

	public Search() throws ClassNotFoundException {
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

	// 1937852:check search icon.
	public void testSearchBar() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// focus on search bar
		assertTrue("Fucus is not on search bar.",
				solo.getText(ValidationText.Search_All_categories).isFocused());

		// soft keyboard is active
		Assert.softKeyboardIsOpen(solo);

	}

	// 1937854:check tips text indicated in search bar.
	public void testSearchBarBackgroundText() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);
		assertTrue("Can not get tips in search bar.",
				solo.searchText(ValidationText.Search_All_categories));

	}

	// 1937855:Picture "超" is shown
	public void testPictureChaoIsShown() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		assertTrue("Picture is not shown In the upper left corner",
				Action.getIsViewShown(solo, "id/home", 1));

	}

	// 1937856：back to the previous screen
	public void testBackToPreviousScreen() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		Action.clickHomeButtonOnScreen(solo);

		assertFalse("Can not back to the previous screen.",
				solo.getText(ValidationText.News).isFocused());

	}

	// 1937857:10 auto-complete suggestions under search bar
	public void testListUnderSearchBar() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// element and test_data
		Action.addInitializeData(solo, 0, "h");
		solo.sleep(3000);

		// Get list view numbers
		int Lv_numbers = Action.getListviewOnCurrentScreen(solo);
		if (Lv_numbers == 3) {
			// get the number of list
			ArrayList<ListView> listview = solo.getCurrentViews(ListView.class);
			int count = listview.get(0).getCount();
			assertEquals("Auto-complete suggestions number is more than 10.",
					count, 10);
		} else
			assertTrue("Suggestions list is not appear.", false);

	}

	// 1937858:“+” icon is shown
	public void testPlusIsShownOnListView() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// element and test_data
		Action.addInitializeData(solo, 0, "h");
		solo.sleep(3000);

		assertTrue("Plus is shown on suggest list",
				Action.getIsViewShown(solo, "search_fill_up"));

	}

	// 1937859:add suggestion into search bar by clicking “+” icon
	public void testAddSuggestionIntoSearchBar() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// element and test_data
		Action.addInitializeData(solo, 0, "h");
		solo.sleep(3000);

		// value where in front of "+"
		String tv_value = Action.getValuesInTextview(solo,
				"id/search_suggestion_text", 0);

		// click "+" in list suggestion window
		Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

		// get the value in search bar
		String barvalue = Action.getValuesInTextview(solo,
				"search_autocompletetext");
		assertEquals("Add suggestion failed.", barvalue, tv_value);

	}

	// 1937860:change suggestion info if clicking “+” icon again
	public void testChangeSuggestionInSearchBar() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// element and test_data
		Action.addInitializeData(solo, 0, "h");

		String tv_value = "";
		// click plus twice
		for (int i = 0; i < 2; i++) {
			// value where in front of "+"
			solo.sleep(3000);
			tv_value = Action.getValuesInTextview(solo,
					"id/search_suggestion_text", 0);
			Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);
		}
		// get the value in search bar
		String barvalue = Action.getValuesInTextview(solo,
				"search_autocompletetext");
		assertEquals("Add suggestion failed.", barvalue, tv_value);

	}

	// 1937861:Navigate to search result Screen
	public void testGotoSearchResultScreen() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// element and test_data
		Action.addInitializeData(solo, 0, "h");
		solo.sleep(3000);

		// click "+" in list suggestion window
		Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

		// press search button on keyboard
		solo.pressSoftKeyboardSearchButton();

		// check if navigate to search result page
		Assert.navigateToResultPage(solo);

	}

	// 1937862: hide search clear icon
	public void testSearchClearHidden() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		assertFalse("Search clear icon is not hidden.",
				Action.getIsViewShown(solo, "id/search_clear"));

	}

	// 1937863: show search clear icon
	public void testSearchClearAppear() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// element and test_data
		Action.addInitializeData(solo, 0, "h");
		solo.sleep(3000);

		assertTrue("Search clear icon is not shown.",
				Action.getIsViewShown(solo, "id/search_clear"));

	}

	// 1937864: clear input value in search bar
	public void testClearValueInSearchBar() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// element and test_data
		Action.addInitializeData(solo, 0, "h");
		solo.sleep(3000);

		// get input value in search bar
		String barvalue = Action.getValuesInTextview(solo,
				"search_autocompletetext");
		assertTrue("Value in search bar is empty.", barvalue.length() > 0);

		// click clear icon
		Action.clickView(solo, "id/search_clear");

		Assert.clearSuccess(solo, "search_autocompletetext");

	}

	// 1937865:delete a character by click delete button on keyboard
	public void testDeleteByKeywords() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// element and test_data
		Action.addInitializeData(solo, 0, "hhhhhhhh");
		String barvalue = Action.getValuesInTextview(solo,
				"search_autocompletetext");

		solo.sendKey(112);// delete key on keyboard
		solo.sleep(3000);
		String barvalue2 = Action.getValuesInTextview(solo,
				"search_autocompletetext");

		// input keyword then list suggestion in openwindow
		assertEquals("Delete more than one characters every times.",
				barvalue.length() - 1, barvalue2.length());

	}

	// 1937866: input keywords to search
	public void testSearchByKeywords() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		String[] searchKeys = { "hp" };
		Action.addHistoryInfomationInSearchBar(solo, searchKeys);

	}

	// 1937867 :list suggestion
	public void testListSuggestionUnderSearchBar() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// fill in keyword then click search button
		Action.addInitializeData(solo, 0, ValidationText.Dong);
		solo.sleep(3000);

		// input keyword then list suggestion in openwindow
		assertTrue("Suggestion list is not shown",
				Action.getIsViewShown(solo, "id/search_suggestion_text"));

	}

	// 1937869:auto-complete - check in recent memory search function
	public void testKeywordOnTheFristLineByChoose() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		Action.addInitializeData(solo, 0, "h");
		solo.sleep(3000);

		// value in the first line
		String suggestion_record = Action.getValuesInTextview(solo,
				"id/search_suggestion_text", 0);

		// click "+" in list suggestion window
		Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);
		// back to home screen after search
		solo.pressSoftKeyboardSearchButton();
		Assert.navigateToResultPage(solo);
		Action.clickHomeButtonOnScreen(solo);

		// click on search button again
		Action.clickSearchButtonOnScreen(solo);

		String history_record = Action.getValuesInTextview(solo,
				"id/search_suggestion_text", 0);

		assertEquals("Keyword is not on the first line", suggestion_record,
				history_record);

	}

	// 1937870:Direct input keyword - check in recent memory search function
	public void testKeywordOnTheFristLineByInput() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		String[] searchKeys = { "h" };
		Action.addHistoryInfomationInSearchBar(solo, searchKeys);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		String history_record = Action.getValuesInTextview(solo,
				"id/search_suggestion_text", 0);
		assertEquals("Keyword is not on the first line", searchKeys[0],
				history_record);

	}

	// 1937871:Display recent 10 records by auto complete
	public void testRecentRecordsListbyChoose() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// prepare search data in search bar
		String[] searchKeys = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k" };
		Action.addHistoryInfomationInSearchBar(solo, searchKeys);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// get the number of list
		ArrayList<ListView> listview = solo.getCurrentViews(ListView.class);
		int count = listview.get(0).getCount();
		// display 10 search records
		assertEquals("Auto-complete suggestions number is more than 10.",
				count, 10);

		for (int j = 0; j < 10; j++) {
			// click on search button on home screen
			Action.clickSearchButtonOnScreen(solo);
			// close soft keyboard
			Action.closeSoftKeyBoard(solo);

			Action.clickView(solo, "search_autocompletetext");
			Action.closeSoftKeyBoard(solo);
			solo.sleep(2000);
			solo.scrollToBottom();
			// click "+" icon in the last of suggestion list
			Action.clickPlusInOpenWindow(solo, "search_fill_up", 9);

			// click search button and navigate to search result screen
			solo.pressSoftKeyboardSearchButton();
			Assert.navigateToResultPage(solo);

			// click back icon
			Action.clickHomeButtonOnScreen(solo);

			// click on search button on home screen
			Action.clickSearchButtonOnScreen(solo);
			// get the text in the first line of suggestion list
			String suggestion_record = Action.getValuesInTextview(solo,
					"id/search_suggestion_text", 0);
			String searchKey = searchKeys[1 + j];
			assertEquals("The order of suggestion list is not correct.",
					suggestion_record, searchKey);
			// click back icon
			Action.clickHomeButtonOnScreen(solo);
		}
	}

	// 1937872:Display recent 10 records
	public void testRecentRecordsListbyInput() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		String[] searchKeys = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k" };
		Action.addHistoryInfomationInSearchBar(solo, searchKeys);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// get the number of list
		ArrayList<ListView> listview = solo.getCurrentViews(ListView.class);
		int count = listview.get(0).getCount();
		// display 10 search records
		assertEquals("Auto-complete suggestions number is more than 10.",
				count, 10);

		// close soft keyboard
		Action.closeSoftKeyBoard(solo);

		// get the value of suggestion list and verify whether the suggestion
		// list is correct
		for (int j = 0; j < 10; j++) {
			String suggestion_record = Action.getValuesInTextview(solo,
					"id/search_suggestion_text", j);
			String searchKey = searchKeys[searchKeys.length - 1 - j];
			assertEquals("The order of suggestion list is not correct.",
					suggestion_record, searchKey);
		}

	}

	// 1937873:By click on the keyword into the search box
	public void testAutoFillValueToSearchBar() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		String[] searchKeys = { "hp" };
		Action.addHistoryInfomationInSearchBar(solo, searchKeys);

		Action.clickSearchButtonOnScreen(solo);

		// Click on the right side of the recent search keyword "+" icon
		Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

		String barvalue = Action.getValuesInTextview(solo,
				"search_autocompletetext");

		assertEquals("Auto fill value to search bar failed.", barvalue, "hp");

	}

	// 1937874:Recent Search by keyword
	public void testNavigateToSearchResultScreen() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		String[] searchKeys = { "hp" };
		Action.addHistoryInfomationInSearchBar(solo, searchKeys);

		Action.clickSearchButtonOnScreen(solo);

		// Click on the right side of the recent search keyword "+" icon
		Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

		// Navigate to search result screen
		solo.pressSoftKeyboardSearchButton();

		Assert.navigateToResultPage(solo);

	}

	// 1937875:No search suggestions displayed View
	public void testNoResultByarbInput() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// listview numbers before clicking search icon
		int listview1 = Action.getListviewOnCurrentScreen(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		Action.addInitializeData(solo, 0, "yeruieujeueu");

		// listview after clicking search icon
		int listview2 = Action.getListviewOnCurrentScreen(solo);

		assertEquals("Suggestion list is shown", listview1, listview2);

	}

	// 1937876:No recent Search Show View
	public void testNoResultInSearchBar() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// listview numbers before clicking search icon
		int listview1 = Action.getListviewOnCurrentScreen(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// listview after clicking search icon
		int listview2 = Action.getListviewOnCurrentScreen(solo);

		assertEquals("Suggestion list is shown", listview1, listview2);

	}

	// 1937877:2 Tap the right side of the recent search different keyword "+"
	public void testChangeValueByClickPlus() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		String[] searchKeys = { "hp" };
		Action.addHistoryInfomationInSearchBar(solo, searchKeys);

		Action.clickSearchButtonOnScreen(solo);

		String tv_value = "";
		// click "+" twice
		for (int i = 0; i < 2; i++) {
			// value where in front of "+"
			tv_value = Action.getValuesInTextview(solo,
					"id/search_suggestion_text", 0);
			Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);
			solo.sleep(3000);
		}
		String barvalue = Action.getValuesInTextview(solo,
				"search_autocompletetext");

		assertEquals("Auto fill value to search bar failed.", barvalue,
				tv_value);

	}

	// 1937878:Clear into the recent search keyword
	public void testClearInputInSearchBar() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		String[] searchKeys = { "hp" };
		Action.addHistoryInfomationInSearchBar(solo, searchKeys);

		Action.clickSearchButtonOnScreen(solo);

		// Click on the right side of the recent search keyword "+" icon
		Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

		// click clear icon
		Action.clickView(solo, "id/search_clear");

		Assert.clearSuccess(solo, "search_autocompletetext");

	}

	// 1937879:Clear into the keyword search suggestions
	public void testClearInputValueInSearchBar() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// element and test_data
		Action.addInitializeData(solo, 0, "h");

		// click "+" in list suggestion window
		Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

		// click clear icon
		Action.clickView(solo, "id/search_clear");

		Assert.clearSuccess(solo, "search_autocompletetext");

	}

	// 1937886:Enter any long keyword search
	public void testNoSearchResultDisplay() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click on search button on home screen
		Action.clickSearchButtonOnScreen(solo);

		// no result display by search keywords
		Action.addInitializeData(solo, 0, "JJHGHKJHHHHHJJJJJJHG");

		solo.pressSoftKeyboardSearchButton();

		Assert.noResultDisplay(solo);

	}

	// 1937887:Whether search icon is shown on category screen
	public void testSearchBarShowInCategory() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		assertTrue("Search icon is hidden.", solo.getView("id/menu_search", 0)
				.isShown());

	}

	// 1937888:test search icon clicking
	public void testSearchIconClicking() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		Action.clickSearchButtonOnScreen(solo);

		assertTrue("Go to search page failed",
				solo.getView("search_autocompletetext").hasFocus());

	}

	// 1937889:back to L1層分類 list
	public void testBackToHomeList() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// click back(home) screen
		Action.clickHomeButtonOnScreen(solo);

		solo.scrollToTop();
		Assert.CategoryListShow(solo);

	}

	// 1937890:back to 服飾L2層分類 list
	public void testBackToCostumeList() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		solo.scrollToTop();
		Action.clickText(solo, ValidationText.Apparel);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// click back(home) screen
		Action.clickHomeButtonOnScreen(solo);

		Assert.costumeL2ListShow(solo);

	}

	// 1937891:back to 流行女裝 list
	public void testBackToWomenClothingList() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		solo.scrollToTop();
		Action.clickText(solo, ValidationText.Apparel);

		Action.clickText(solo, ValidationText.Popular_Women);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// click back(home) screen
		Action.clickHomeButtonOnScreen(solo);

		// get background text of search bar
		String barvale = Action.getValuesInTextview(solo, "action_bar_title");

		assertEquals("Back to fashion women's clothing List failed",
				barvale.trim(), ValidationText.Popular_Women);

		Assert.womenClothingCategoryListShow(solo);

	}

	// 1937892:back to 流行女裝>上衣 list
	public void testBackToCoatList() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		solo.scrollToTop();
		Action.clickText(solo, ValidationText.Apparel);

		Action.clickText(solo, ValidationText.Popular_Women);

		Action.clickText(solo, ValidationText.Jacket);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// click back(home) screen
		Action.clickHomeButtonOnScreen(solo);

		// get background text of search bar
		String barvale = Action.getValuesInTextview(solo, "action_bar_title");

		assertEquals("Back to fashion women's clothing List failed",
				barvale.trim(), ValidationText.Jacket);

	}

	// 1937896: navigate to no result page
	public void testNavigateToNoResultItemListPage() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		solo.scrollToTop();
		Action.clickText(solo, ValidationText.Apparel);

		Action.clickText(solo, ValidationText.Popular_Women);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// add search data
		Action.searchAfterPutData(solo, 0, "YYUIIUYTTTYUU");

		Assert.noResultDisplay(solo);

	}

	// 1937905:Categories Tab-Itemlist search with no result display
	public void testNavigateToCategoriesNoResultPage() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		solo.scrollToTop();
		Action.clickText(solo, ValidationText.Apparel);

		Action.clickText(solo, ValidationText.Commodity);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// add search data
		Action.searchAfterPutData(solo, 0, "JJHHJHUIUUH");

		Assert.noResultDisplay(solo);

	}

	// 1937906:The search results page display all categories
	public void testAllCategoriesSearchPage() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click clear icon
		Action.clickView(solo, "id/search_clear");

		assertTrue("The search results is not belong to all categories.",
				solo.searchText(ValidationText.Search_All_categories));

	}

	// 1937909:Search in L4分類
	public void testSearchInLfour() throws Exception {

		// clear history information then back to home screen
		Action.clearHistoryInfomation(solo);

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		solo.scrollToTop();
		Action.clickText(solo, ValidationText.Apparel);
		Action.clickText(solo, ValidationText.Popular_Women);
		Action.clickText(solo, ValidationText.Jacket);

		// click on goods tab
		Action.clickView(solo, "id/category_tab_primary_title", 1);
		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.Jacket);

		// click clear icon
		Action.clickView(solo, "id/search_clear");
		assertTrue("The search results is not belong to L4 categories.",
				solo.searchText(ValidationText.Search_Top));

	}

	// 1937912:check search result.
	public void testSearchResult() throws Exception {

		// navigate to category screen
		Action.navigateToCategoryScreen(solo);

		// click search button
		Action.clickSearchButtonOnScreen(solo);

		// input keyword and search
		Action.searchAfterPutData(solo, 0, ValidationText.apple);

		try {
			assertTrue("Not 2 lines.", solo.searchText("phone"));
		} catch (AssertionError e) {

			// input keyword and search
			Action.searchAfterPutData(solo, 0, ValidationText.apple);
			assertTrue("Not 2 lines.", solo.searchText("phone"));
		}
	}
}