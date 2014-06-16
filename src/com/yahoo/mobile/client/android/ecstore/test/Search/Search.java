/*
 * This is automated script about "Search".
 *
 * You can run these test cases either on the emulator or on device.
 *
 * By Eclipse:
 * Right click the test project and select Run As --> Run As Android JUnit Test
 *
 * By Ant:
 * 1.Run "android update test-project -m [path to target application]
 * -p [path to the test folder]"  in command line .
 * 2."ant test"
 *
 * By using instrument command:
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client
 * .android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run Search:adb shell am instrument -e class com.yahoo.mobile
 * .client.android.ecstore.test.Search.Search -w com.yahoo.mobile.client.
 * android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.Search;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

/**
 * @author Administrator
 *
 */
@SuppressLint("NewApi")
public class Search extends ActivityInstrumentationTestCase2<Activity> {

    /**
     * Declare application main activity.
     */
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME =
            "com.yahoo.mobile.client.android.ecstore.ui.ECSplashActivity";

    /**
     * Declare a variable of type Class for start tested program.
     */
    private static Class<?> launcherActivityClass;
     /**
     * Declare a Solo object.
     */
    private Solo solo;
    static {

        try {
            launcherActivityClass = Class
                    .forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @throws ClassNotFoundException if has error
     */
    @SuppressWarnings("unchecked")
    public Search() throws ClassNotFoundException {
        super((Class<Activity>) launcherActivityClass);
    }

    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        Assert.testFirstLaunch(solo);

    }

    @Override
    public final void tearDown() throws Exception {

        solo.finishOpenedActivities();
        super.tearDown();
    }

    /**
     * 1937852:Check search icon.
     * @throws Exception if has error
     */
    public final void testSearchBar() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // focus on search bar
        assertTrue("Fucus is not on search bar.",
        solo.getText(ValidationText.SEARCH_ALL_CATEGORIES).isFocused());

        // soft keyboard is active
        Assert.softKeyboardIsOpen(solo);

    }


    /**
     * 1937854:Check tips text indicated in search bar.
     * @throws Exception if has error
     */
    public final void testSearchBarBackgroundText() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);
        assertTrue("Can not get tips in search bar.",
            solo.searchText(ValidationText.SEARCH_ALL_CATEGORIES));

    }


    /**
     * 1937855:Picture "Super" is shown.
     * @throws Exception if has error
     */
    public final void testPictureChaoIsShown() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        assertTrue("Picture is not shown In the upper left corner",
                Action.getIsViewShown(solo, "home", 1));

    }


    /**
     * 1937856：Back to the previous screen.
     * @throws Exception if has error
     */
    public final void testBackToPreviousScreen() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        Action.clickHomeButtonOnScreen(solo);

        assertFalse("Can not back to the previous screen.",
                solo.getText(ValidationText.NEWS).isFocused());

    }

    /**
     * 1937857:10 auto-complete suggestions under search bar.
     * @throws Exception if has error
     */
    public final void testListUnderSearchBar() throws Exception {

        // clear history information then back to home screen.
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // element and test_data.
        Action.addInitializeData(solo, 0, "h");
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // Get list view numbers.
        int lvNumbers = Action.getListviewOnCurrentScreen(solo);

        if (lvNumbers == 3) {
            // get the number of list
            ArrayList<ListView> listview =
                    solo.getCurrentViews(ListView.class);
            int count = listview.get(0).getCount();
            assertEquals("Auto-complete suggestions number"
                    + "is more than 10.",count,10);
        } else
            assertTrue("Suggestions list is not appear.", false);
    }

    /**
     * 1937858:"+" icon is shown.
     * @throws Exception if has error
     */
    public final void testPlusIsShownOnListView() throws Exception {

        // clear history information then back to home screen.
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen.
        Action.clickSearchButtonOnScreen(solo);

        // element and test_data.
        Action.addInitializeData(solo, 0, "h");
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        assertTrue("Plus is shown on suggest list",
                Action.getIsViewShown(solo, "search_fill_up"));

    }

    /**
     * 1937859:Add suggestion into search bar by clicking "+" icon.
     * @throws Exception if has error
     */
    public final void testAddSuggestionIntoSearchBar() throws Exception {

        // clear history information then back to home screen.
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen.
        Action.clickSearchButtonOnScreen(solo);

        // element and test_data.
        Action.addInitializeData(solo, 0, "h");
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // value where in front of "+".
        String tvValue = Action.getValuesInTextview(solo,
                "id/search_suggestion_text", 0);

        // click "+" in list suggestion window.
        Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

        // get the value in search bar.
        String barvalue = Action.getValuesInTextview(solo,
                "search_autocompletetext");
        assertEquals("Add suggestion failed.", barvalue, tvValue);

    }

    /**
     * 1937860:Change suggestion info if clicking "+" icon again.
     * @throws Exception if has error
     */
    public final void testChangeSuggestionInSearchBar() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // element and test_data
        Action.addInitializeData(solo, 0, "h");

        String tvValue = "";
        // click plus twice
        for (int i = 0; i < 2; i++) {
            // value where in front of "+"
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            tvValue = Action.getValuesInTextview(solo,
                    "id/search_suggestion_text", 0);
            Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);
        }
        // get the value in search bar
        String barvalue = Action.getValuesInTextview(solo,
                "search_autocompletetext");
        assertEquals("Add suggestion failed.", barvalue, tvValue);

    }

    /**
     * 1937861:Navigate to search result Screen.
     * @throws Exception if has error
     */
    public final void testGotoSearchResultScreen() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // element and test_data
        Action.addInitializeData(solo, 0, "h");
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // click "+" in list suggestion window
        Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

        // press search button on keyboard
        solo.pressSoftKeyboardSearchButton();

        // check if navigate to search result page
        Assert.navigateToResultPage(solo);

    }


    /**
     * 1937862:Hide search clear icon.
     * @throws Exception if has error
     */
    public final void testSearchClearHidden() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        assertFalse("Search clear icon is not hidden.",
                Action.getIsViewShown(solo, "search_clear"));

    }

    /**
     * 1937863:Show search clear icon.
     * @throws Exception if has error
     */
    public final void testSearchClearAppear() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // element and test_data
        Action.addInitializeData(solo, 0, "h");
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        assertTrue("Search clear icon is not shown.",
                Action.getIsViewShown(solo, "search_clear"));

    }

    /**
     * 1937864:Clear input value in search bar.
     * @throws Exception if has error
     */
    public final void testClearValueInSearchBar() throws Exception {

        // clear history information then back to home screen.
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen.
        Action.clickSearchButtonOnScreen(solo);

        // element and test_data.
        Action.addInitializeData(solo, 0, "h");
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // get input value in search bar.
        String barvalue = Action.getValuesInTextview(solo,
                "search_autocompletetext");
        assertTrue("Value in search bar is empty."
                , barvalue.length() > 0);

        // click clear icon.
        Action.clickView(solo, "search_clear");

        Assert.clearSuccess(solo, "search_autocompletetext");

    }

    /**
     * 1937865:Delete a character by click delete button on keyboard.
     * @throws Exception if has error
     */
    public final void testDeleteByKeywords() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // element and test_data
        Action.addInitializeData(solo, 0, "hhhhhhhh");
        String barvalue = Action.getValuesInTextview(solo,
                "search_autocompletetext");

        // delete key on keyboard
        solo.sendKey(112);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        String barvalue2 = Action.getValuesInTextview(solo,
                "search_autocompletetext");

        // input keyword then list suggestion in open window.
        assertEquals("Delete more than one characters every times.",
                barvalue.length() - 1, barvalue2.length());

    }

    /**
     * 1937866:Input keywords to search.
     * @throws Exception if has error
     */
    public final void testSearchByKeywords() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        String[] searchKeys = { "hp" };
        Action.addHistoryInfomationInSearchBar(solo, searchKeys);

    }

    /**
     * 1937867:List suggestion.
     * @throws Exception if has error
     */
    public final void testListSuggestionUnderSearchBar() throws Exception {

        // clear history information then back to home screen.
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen.
        Action.clickSearchButtonOnScreen(solo);

        // fill in keyword then click search button.
        Action.addInitializeData(solo, 0, ValidationText.DONG);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // input keyword then list suggestion in open window.
        assertTrue("Suggestion list is not shown",
            Action.getIsViewShown(solo, "search_suggestion_text"));

    }


    /**
     * 1937869:auto-complete - check in recent memory search function.
     * @throws Exception if has error
     */
    public final void testKeywordOnTheFristLineByChoose() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        Action.addInitializeData(solo, 0, "h");
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // value in the first line
        String suggestionRecord =
                Action.getValuesInTextview(solo,
                "id/search_suggestion_text", 0);

        // click "+" in list suggestion window
        Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

        // back to home screen after search
        solo.pressSoftKeyboardSearchButton();
        Assert.navigateToResultPage(solo);
        Action.clickHomeButtonOnScreen(solo);

        // click on search button again
        Action.clickSearchButtonOnScreen(solo);

        String historyRecord =
                Action.getValuesInTextview(solo,
                "id/search_suggestion_text", 0);

        assertEquals("Keyword is not on the first line",
               suggestionRecord, historyRecord);

    }

    /**
     * 1937870:Direct input keyword - check in recent memory search function.
     * @throws Exception if has error
     */
    public final void testKeywordOnTheFristLineByInput() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        String[] searchKeys = { "h" };
        Action.addHistoryInfomationInSearchBar(solo, searchKeys);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        String historyRecord = Action.getValuesInTextview(solo,
                "id/search_suggestion_text", 0);
        assertEquals("Keyword is not on the first line", searchKeys[0],
                historyRecord);

    }

    /**
     * 1937871:Display recent 10 records by auto complete.
     * @throws Exception if has error
     */
    public final void testRecentRecordsListbyChoose() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // prepare search data in search bar
        String[] searchKeys = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k"
                };
        Action.addHistoryInfomationInSearchBar(solo, searchKeys);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // get the number of list
        ArrayList<ListView> listview =
                solo.getCurrentViews(ListView.class);
        int count = listview.get(0).getCount();
        // display 10 search records
        assertEquals("Auto-complete suggestions number"
                + " is more than 10.",
                count, 10);

        for (int j = 0; j < 10; j++){
            // click on search button on home screen
            Action.clickSearchButtonOnScreen(solo);
            // close soft keyboard
            Action.closeSoftKeyBoard(solo);

            Action.clickView(solo, "search_autocompletetext");
            Action.closeSoftKeyBoard(solo);
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
            solo.scrollToBottom();

            // click "+" icon in the last of suggestion list
            Action.clickPlusInOpenWindow(solo, "search_fill_up", 9);

            solo.pressSoftKeyboardSearchButton();
            Assert.navigateToResultPage(solo);

            // click back icon
            Action.clickHomeButtonOnScreen(solo);

            // click on search button on home screen
            Action.clickSearchButtonOnScreen(solo);

            // get the text in the first line of suggestion list
            String suggestionRecord =
                    Action.getValuesInTextview(solo,
                    "id/search_suggestion_text", 0);
            String searchKey = searchKeys[1 + j];
            assertEquals("The order of suggestion list is not"
            + " correct." , suggestionRecord , searchKey);

            // click back icon
            Action.clickHomeButtonOnScreen(solo);
        }
    }


    /**
     * 1937872:Display recent 10 records.
     * @throws Exception if has error
     */
    public final void testRecentRecordsListbyInput() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        String[] searchKeys = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k"
                };
        Action.addHistoryInfomationInSearchBar(solo, searchKeys);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // get the number of list
        ArrayList<ListView> listview =
                solo.getCurrentViews(ListView.class);
        int count = listview.get(0).getCount();

        // display 10 search records
        assertEquals("Auto-complete suggestions number"
                + " is more than 10.",
                count, 10);

        // close soft keyboard
        Action.closeSoftKeyBoard(solo);

         /* get the value of suggestion list and verify
          whether the suggestion list is correct*/

        for (int j = 0; j < 10; j++) {
            String suggestionRecord =
                    Action.getValuesInTextview(solo,
                    "id/search_suggestion_text", j);
            String searchKey =
                    searchKeys[searchKeys.length - 1 - j];
            assertEquals("The order of suggestion list"
            + " is not correct.", suggestionRecord, searchKey);
        }

    }


    /**
     * 1937873:By click on the keyword into the search box.
     * @throws Exception if has error
     */
    public final void testAutoFillValueToSearchBar() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        String[] searchKeys = { "hp" };
        Action.addHistoryInfomationInSearchBar(solo, searchKeys);

        Action.clickSearchButtonOnScreen(solo);

        // Click on the right side of the recent search keyword "+" icon
        Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

        String barvalue = Action.getValuesInTextview(solo,
                "search_autocompletetext");

        assertEquals("Auto fill value to search bar failed.",
                barvalue, "hp");

    }


    /**
     * 1937874:Recent Search by keyword.
     * @throws Exception if has error
     */
    public final void testNavigateToSearchResultScreen() throws Exception {

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

    /**
     * 1937875:No search suggestions displayed View.
     * @throws Exception if has error
     */
    public final void testNoResultByInput() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // list view numbers before clicking search icon
        int listview1 = Action.getListviewOnCurrentScreen(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        Action.addInitializeData(solo, 0, "yeruieujeueu");

        // list view after clicking search icon
        int listview2 = Action.getListviewOnCurrentScreen(solo);

        assertEquals("Suggestion list is shown", listview1, listview2);

    }

    /**
     * 1937876:No recent Search Show View.
     * @throws Exception if has error
     */
    public final void testNoResultInSearchBar() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // list view numbers before clicking search icon
        int listview1 = Action.getListviewOnCurrentScreen(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // list view after clicking search icon
        int listview2 = Action.getListviewOnCurrentScreen(solo);

        assertEquals("Suggestion list is shown", listview1, listview2);

    }

    /**
     * 1937877:2 Tap the right side of the recent search different keyword "+".
     * @throws Exception if has error
     */
    public final void testChangeValueByClickPlus() throws Exception {

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
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        }
        String barvalue = Action.getValuesInTextview(solo,
                "search_autocompletetext");

        assertEquals("Auto fill value to search bar failed.", barvalue,
                tv_value);

    }

    /**
     * 1937878:Clear into the recent search keyword.
     * @throws Exception if has error
     */
    public final void testClearInputInSearchBar() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        String[] searchKeys = { "hp" };
        Action.addHistoryInfomationInSearchBar(solo, searchKeys);

        Action.clickSearchButtonOnScreen(solo);

        // Click on the right side of the recent search keyword "+" icon
        Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

        // click clear icon
        Action.clickView(solo, "search_clear");

        Assert.clearSuccess(solo, "search_autocompletetext");

    }

    /**
     * 1937879:Clear into the keyword search suggestions.
     * @throws Exception if has error
     */
    public final void testClearInputValueInSearchBar() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // element and test_data
        Action.addInitializeData(solo, 0, "h");

        // click "+" in list suggestion window
        Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

        // click clear icon
        Action.clickView(solo, "search_clear");

        Assert.clearSuccess(solo, "search_autocompletetext");

    }


    /**
     * 1937886:Enter any long keyword search.
     * @throws Exception if has error
     */
    public final void testNoSearchResultDisplay() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // no result display by search keywords
        Action.addInitializeData(solo, 0, "JJHGHKJHHHHHJJJJJJHG");

        solo.pressSoftKeyboardSearchButton();

        Assert.noResultDisplay(solo);

    }

    /**
     * 1937887:Whether search icon is shown on category screen.
     * @throws Exception if has error
     */
    public final void testSearchBarShowInCategory() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        assertTrue("Search icon is hidden.",
                solo.getView("menu_search", 0).isShown());

    }

    /**
     * 1937888:Verify search icon click.
     * @throws Exception if has error
     */
    public final void testSearchIconClicking() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        Action.clickSearchButtonOnScreen(solo);

        assertTrue("Go to search page failed",
            solo.getView("search_autocompletetext").hasFocus());

    }


    /**
     * 1937889:Back to L1 layer category list.
     * @throws Exception if has error
     */
    public final void testBackToHomeList() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // click back(home) screen
        Action.clickHomeButtonOnScreen(solo);

        solo.scrollToTop();
        Assert.categoryListShow(solo);

    }

    /**
     * 1937890:Back to layer 2 category list.
     * @throws Exception if has error
     */
    public final void testBackToCostumeList() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        solo.scrollToTop();
        Action.clickText(solo, ValidationText.APPAREL);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // click back(home) screen
        Action.clickHomeButtonOnScreen(solo);

        Assert.costumeL2ListShow(solo);

    }

    /**
     * 1937891:Back to fashion list.
     * @throws Exception if has error
     */
    public final void testBackToWomenClothingList() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        solo.scrollToTop();
        Action.clickText(solo, ValidationText.APPAREL);

        Action.clickText(solo, ValidationText.POPULAR_WOMEN);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // click back(home) screen
        Action.clickHomeButtonOnScreen(solo);

        // get background text of search bar
        String barvale = Action.getValuesInTextview(
                solo, "action_bar_title");

        assertEquals("Back to fashion women's clothing List failed",
                barvale.trim(), ValidationText.POPULAR_WOMEN);

        Assert.womenClothingCategoryListShow(solo);

    }

    /**
     * 1937892:Back to appeal list.
     * @throws Exception if has error
     */
    public final void testBackToCoatList() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        solo.scrollToTop();
        Action.clickText(solo, ValidationText.APPAREL);

        Action.clickText(solo, ValidationText.POPULAR_WOMEN);

        Action.clickText(solo, ValidationText.JACKET);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // click back(home) screen
        Action.clickHomeButtonOnScreen(solo);

        // get background text of search bar
        String barvale = Action.getValuesInTextview(
                solo, "action_bar_title");

        assertEquals("Back to fashion women's clothing List failed",
                barvale.trim(), ValidationText.JACKET);

    }

    /**
     * 1937896:Navigate to no result page.
     * @throws Exception if has error
     */
    public final void testNavigateToNoResultItemListPage()
            throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        solo.scrollToTop();
        Action.clickText(solo, ValidationText.APPAREL);

        Action.clickText(solo, ValidationText.POPULAR_WOMEN);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // add search data
        Action.searchAfterPutData(solo, 0, "YYUIIUYTTTYUU");

        Assert.noResultDisplay(solo);

    }


    /**
     * 1937905:Categories item list search with no result display.
     * @throws Exception if has error
     */
    public final void testNavigateToCategoriesNoResultPage()
            throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        solo.scrollToTop();
        Action.clickText(solo, ValidationText.APPAREL);

        Action.clickText(solo, ValidationText.COMMODITY);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // add search data
        Action.searchAfterPutData(solo, 0, "JJHHJHUIUUH");

        Assert.noResultDisplay(solo);

    }

    /**
     * 1937906:The search results page display all categories.
     * @throws Exception if has error
     */
    public final void testAllCategoriesSearchPage() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        // click clear icon
        Action.clickView(solo, "search_clear");

        assertTrue("The search results is not belong to all"
        + "categories.", solo.searchText(
                ValidationText.SEARCH_ALL_CATEGORIES));

    }


    /**
     * 1937909:Search in L4 classification.
     * @throws Exception if has error
     */
    public final void testSearchInLfour() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        solo.scrollToTop();
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.POPULAR_WOMEN);
        Action.clickText(solo, ValidationText.JACKET);

        // click on goods tab
        Action.clickView(solo, "category_tab_primary_title", 1);
        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        // click clear icon
        Action.clickView(solo, "search_clear");
        assertTrue("The search results is not belong to L4 categories.",
                solo.searchText(ValidationText.SEARCH_TOP));

    }

    /**
     * 1937912:Check search result.
     * @throws Exception if has error
     */
    public final void testSearchResult() throws Exception {

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.APPLE);

        try {
            assertTrue("Not 2 lines.", solo.searchText("phone"));
        } catch (AssertionError e) {

            // input keyword and search
            Action.searchAfterPutData(solo, 0,
                    ValidationText.APPLE);
            assertTrue("Not 2 lines.", solo.searchText("phone"));
        }
    }


    /**
     * 1937893:The L5 layer classification click returns Icon.
     * @throws Exception if has error
     */
    public final void testClickReturnIconInL5Layer() throws Exception {

        Action.enterToJacket(solo);
        Action.clickText(solo, ValidationText.T_SHIRT);
        Action.clickSearchButtonOnScreen(solo);
        solo.goBack();
        TextView searchText = (TextView)
                solo.getView("action_bar_title", 0);
        Log.i("number", searchText.getText().toString());
        assertEquals(ValidationText.T_SHIRT.trim(), searchText.getText()
                .toString().trim());

    }


    /**
     * 1937894:The L6 layer classification click returns Icon.
     * @throws Exception if has error
     */
    public final void testClickReturnIconInL6Layer() throws Exception {

        Action.enterToJacket(solo);
        Action.clickText(solo, ValidationText.T_SHIRT);
        Action.clickText(solo, ValidationText.CATEGORIES);
        Action.clickText(solo, ValidationText.NO_SLEEVE_SHIRT);
        Action.clickSearchButtonOnScreen(solo);
        solo.goBack();
        TextView searchText = (TextView)
                solo.getView("action_bar_title", 0);
        Log.i("number", searchText.getText().toString());
        assertTrue("Not enter to Sleeve Shirt category!",
                searchText.getText()
        .toString().trim().equals(ValidationText.NO_SLEEVE_SHIRT));

    }


    /**
     * 1937909:Search in L4 classification.
     * @throws Exception if has error
     */
    public final void testSearchInL4Layer() throws Exception {

        Action.enterToJacket(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        Action.clickSearchButtonOnScreen(solo);

        // element and test_data
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView searchText = (TextView)
                solo.getView("action_bar_title", 0);
        Log.i("number", searchText.getText().toString());
        assertTrue("Not enter to Jacket category!", searchText.getText()
            .toString().trim().equals(ValidationText.JACKET));

    }

    /**
     * 1937898:Click search icon.
     * @throws Exception if has error
     */
    public final void testClickSearchIcon() throws Exception {

        solo.clickOnView(solo.getView("tab_text", 2));
        Action.clickSearchButtonOnScreen(solo);

        // Input test data.
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);
        assertTrue("Not enter to search page.",
                solo.searchText(ValidationText.RESULTS_VALUE));

    }

    /**
     * 1937899:Click return icon in L2 item list.
     * @throws Exception if has error
     */
    public final void testClickReturnIconInL2() throws Exception {

        Action.clickText(solo, ValidationText.ALL_CATEGORIES);
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.goBack();
        Action.navigateToCategoryScreen(solo);

    }


    /**
     * 1937900:Click return icon in L3 item list.
     * @throws Exception if has error
     */
    public final void testClickReturnIconInL3() throws Exception {

        solo.clickOnView(solo.getView("tab_text", 2));
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.POPULAR_WOMEN);
        Action.clickText(solo, ValidationText.CATEGORIES);
        solo.goBack();
        int size = ValidationText.COSTUMELIST.length;
        for (int i = 0; i < size; i++) {
            boolean textFound = solo.searchText(
                    ValidationText.COSTUMELIST[i]);
            assertTrue(ValidationText.COSTUMELIST[i]
                    + " not found", textFound);
        }

    }

    /**
     * 1937901:Click return icon in L4 item list.
     * @throws Exception if has error
     */
    public final void testClickReturnIconInL4() throws Exception {

        Action.enterToJacket(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.goBack();
        TextView searchText = (TextView)
                solo.getView("action_bar_title", 0);
        Log.i("number", searchText.getText().toString());
        assertTrue("Not back to fashion category!", searchText.getText()
        .toString().trim().equals(ValidationText.POPULAR_WOMEN));

    }


    /**
     * 1937902:Click return icon in L5 item list.
     * @throws Exception if has error
     */
    public final void testClickReturnIconInL5() throws Exception {

        Action.enterToJacket(solo);
        Action.clickText(solo, ValidationText.T_SHIRT);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.goBack();
        TextView searchText = (TextView)
                solo.getView("action_bar_title", 0);
        Log.i("number", searchText.getText().toString());
        assertTrue("Not back to jacket category!", searchText.getText()
            .toString().trim().equals(ValidationText.JACKET));

    }


    /**
     * 1937903:Click return icon in L6 item list.
     * @throws Exception if has error
     */
    public final void testClickReturnIconInL6() throws Exception {

        Action.enterToJacket(solo);
        Action.clickText(solo, ValidationText.T_SHIRT);
        Action.clickText(solo, ValidationText.CATEGORIES);
        Action.clickText(solo, ValidationText.NO_SLEEVE_SHIRT);
        solo.goBack();
        TextView searchText = (TextView)
                solo.getView("action_bar_title", 0);
        Log.i("number", searchText.getText().toString());
        assertTrue("Not back to T_shirt category!", searchText.getText()
                .toString().trim().equals(ValidationText.T_SHIRT));

    }


    /**
     * 1937904:Input keywords and search.
     * @throws Exception if has error
     */
    public final void testInputkeywordsAndSearch() throws Exception {

        Action.clickText(solo, ValidationText.ALL_CATEGORIES);
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.COMMODITY);
        Action.clickSearchButtonOnScreen(solo);
        // Input test data.
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        /*
         * if find product and store tab,we can confirm already
         *  in search result.
         */
        TextView product = (TextView) solo.getView(
                "category_tab_primary_title", 0);
        TextView store = (TextView)
                solo.getView("category_tab_primary_title",
                1);
        assertTrue("Not enter to search result page.", product.isShown()
                && store.isShown());
    }

    /**
     * 1937906:Search in L1 category.
     * @throws Exception if has error
     */
    public final void testSearchInL1Category() throws Exception {

        Action.clickText(solo, ValidationText.ALL_CATEGORIES);
        Action.clickSearchButtonOnScreen(solo);
        // Input test data.
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        /*
         * if find product and store tab,we can confirm already
         * in search result.
         */
        TextView product = (TextView) solo.getView(
                "category_tab_primary_title", 0);
        TextView store = (TextView)
                solo.getView("category_tab_primary_title",
                1);
        assertTrue("Not enter to search result page.", product.isShown()
                && store.isShown());
    }

    /**
     * 1937907:Search in L2 category.
     * @throws Exception if has error
     */
    public final void testSearchInL2Category() throws Exception {

        Action.clickText(solo, ValidationText.ALL_CATEGORIES);
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.COMMODITY);
        Action.clickSearchButtonOnScreen(solo);

        // Input test data.
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        /*
         * if find product and store tab,we can confirm already
         *  in search  result.
         */
        TextView product = (TextView) solo.getView(
                "category_tab_primary_title", 0);
        TextView store = (TextView)
                solo.getView("category_tab_primary_title",
                1);
        assertTrue("Not enter to search result page.", product.isShown()
                && store.isShown());
    }

    /**
     * 1937908:Search in L3 category.
     * @throws Exception if has error
     */
    public final void testSearchInL3Category() throws Exception {

        Action.clickText(solo, ValidationText.ALL_CATEGORIES);
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.POPULAR_WOMEN);
        Action.clickSearchButtonOnScreen(solo);

        // Input test data.
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        TextView searchText = (TextView)
                solo.getView("action_bar_title", 0);

        /*
         * if find product and store tab,we can confirm already
         *  in search result.
         */
        TextView product = (TextView) solo.getView(
                "category_tab_primary_title", 0);
        TextView store = (TextView)
                solo.getView("category_tab_primary_title",
                1);
        assertTrue(
                "Not enter to search result page.",
                product.isShown()
                        && store.isShown()
                && searchText.getText().toString().trim().
                equals(ValidationText.POPULAR_WOMEN));
    }


    /**
     * 1937910:Search in L5 category.
     * @throws Exception if has error
     */
    public final void testSearchInL5Category() throws Exception {

        Action.enterToJacket(solo);
        Action.clickText(solo, ValidationText.T_SHIRT);
        Action.clickSearchButtonOnScreen(solo);

        // Input test data.
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        TextView searchText = (TextView)
                solo.getView("action_bar_title", 0);

        /*
         * if find product and store tab,we can confirm already
         * in search result.
         */
        TextView product = (TextView) solo.getView(
                "category_tab_primary_title", 0);
        TextView store = (TextView)
                solo.getView("category_tab_primary_title",
                1);

        Log.i("number", searchText.getText().toString());
        assertTrue(
                "Not enter to search result page.",
                product.isShown()
                        && store.isShown()
            && searchText.getText().toString().trim().
            equals(ValidationText.T_SHIRT));

    }


    /**
     * 1937911:Search in L6 category.
     * @throws Exception if has error
     */
    public final void testSearchInL6Category() throws Exception {

        Action.enterToJacket(solo);
        Action.clickText(solo, ValidationText.T_SHIRT);
        Action.clickText(solo, ValidationText.CATEGORIES);
        Action.clickText(solo, ValidationText.NO_SLEEVE_SHIRT);
        Action.clickSearchButtonOnScreen(solo);

        // Input test data.
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        /*
         * if find product and store tab,we can confirm already in
         * search result.
         */
        TextView product = (TextView) solo.getView(
                "category_tab_primary_title", 0);
        TextView store = (TextView)
                solo.getView("category_tab_primary_title",
                1);
        TextView searchText = (TextView)
                solo.getView("action_bar_title", 0);
        Log.i("number", searchText.getText().toString());
        assertTrue(
                "Not enter to search result page.",
                product.isShown()
                        && store.isShown()
                && searchText.getText().toString().trim().
                equals(ValidationText.NO_SLEEVE_SHIRT));

    }

    /**
     * 1959905:Verify "Search all the shops" function.
     * @throws Exception if has error
     */
    public final void testSearchAllStore() throws Exception {

        Action.clickSearchButtonOnScreen(solo);

        // Input test data.
        Action.searchAfterPutData(solo, 0, ValidationText.DONG_JING);

        Action.clickText(solo, ValidationText.SHOP);
        ImageView dongjing = (ImageView) solo.getView(
                "listitem_storelist_image", 0);
        solo.clickOnView(dongjing);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        View iv = solo.getView("menu_search");
        solo.clickOnView(iv);

        Action.searchAfterPutData(solo, 0, ValidationText.MODEL);

        solo.clickOnText(ValidationText.SEARCH_ALL_STORE);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Search all store button still exist.",
                solo.getView("option_button").isShown());

    }

    /**
     * 1959914:1977507:Verify search result when enter special
     * characters in search box.
     * @throws Exception if has error
     */
    public final void testEnterSpecialCharactersToSearch()
            throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // Input test data.
        Action.searchAfterPutData(solo, 0, ValidationText.SPECIAL);
        assertTrue("No result note pop up.",
                solo.searchText(ValidationText.RESULTS_VALUE));
    }


    /**
     * 1959914:Verify user can access store page by tapping store LOGO.
     * @throws Exception if has error
     */
    public final void testEnterStorePageByTapLog() throws Exception {

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // fill in keyword then click search button
        Action.searchAfterPutData(solo, 0, ValidationText.DONG_J);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        Action.clickText(solo, ValidationText.SHOP);

        // Get and tap store LOGO.
        ImageView storeLog = (ImageView) solo
                .getView("listitem_storelist_image");
        solo.clickOnView(storeLog);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        //Change the view to list view.
        solo.clickOnView(solo.getView("menu_filter"));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("btn_browse_mode"));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("btn_list_small"));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        TextView category = (TextView) solo.getView(
                "category_tab_primary_title", 0);
        Log.i("number", category.getText().toString());

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView product = (TextView) solo.getView(
                "category_tab_primary_title", 1);
        Log.i("number", product.getText().toString());
        assertTrue(
                "Not tap store logo.",
                category.getText().toString().trim()
                .equals(ValidationText.CATEGORIES)
                && product.getText().toString().trim()
                .equals(ValidationText.COMMODITY));

    }

}