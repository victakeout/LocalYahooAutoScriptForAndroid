/*
 * This is automated script about "SearchPartOne".
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
 * .client.android.ecstore.test.SearchPartOne.SearchPartOne -w com.yahoo.mobile.client.
 * android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.SearchPartOne;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
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

/**
 * @author Administrator
 *
 */
@SuppressLint("NewApi")
public class SearchPartOne extends ActivityInstrumentationTestCase2<Activity> {

    /**
     * Declare application main activity.
     */
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME
    = "com.yahoo.mobile.client.android.ecstore.ui.ECSplashActivity";

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
     * @throws ClassNotFoundException
     *             if has error
     */
    @SuppressWarnings("unchecked")
    public SearchPartOne() throws ClassNotFoundException {
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
     *
     * @throws Exception
     *             if has error
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
     *
     * @throws Exception
     *             if has error
     */
    public final void testSearchBarBackgroundText() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);
        assertTrue("Cannot get tips in search bar.",
                solo.searchText(ValidationText.SEARCH_ALL_CATEGORIES));

    }

    /**
     * 1937855:Picture "Super" is shown.
     *
     * @throws Exception
     *             if has error
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
     *
     * @throws Exception
     *             if has error
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
     *
     * @throws Exception
     *             if has error
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
            ArrayList<ListView> listview = solo.getCurrentViews(ListView.class);
            int count = listview.get(0).getCount();
            assertEquals("Auto-complete suggestions number"
                    + "is more than 10.", count, 10);
        } else {
            assertTrue("Suggestions list is not appear.", false);
        }
    }

    /**
     * 1937858:Check auto complete layout.
     *
     * @throws Exception
     *             if has error
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
     *
     * @throws Exception
     *             if has error
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
                "search_suggestion_text", 0);

        // click "+" in list suggestion window.
        Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

        // get the value in search bar.
        String barvalue = Action.getValuesInTextview(solo,
                "search_autocompletetext");
        assertEquals("Add suggestion failed.", barvalue, tvValue);

    }

    /**
     * 1937860:Change suggestion info if clicking "+" icon again.
     *
     * @throws Exception
     *             if has error
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
     * 
     * @throws Exception
     *             if has error
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
     * 1937862:Check when no keyword, see clear input icon.
     *
     * @throws Exception
     *             if has error
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
     * 1937863:Check when has keywords, see clear input icon display.
     *
     * @throws Exception
     *             if has error
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
     * 
     * @throws Exception
     *             if has error
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
        assertTrue("Value in search bar is empty.", barvalue.length() > 0);

        // click clear icon.
        Action.clickView(solo, "search_clear");

        Assert.clearSuccess(solo, "search_autocompletetext");

    }

    /**
     * 1937865:Delete a character by click delete button on keyboard.
     * 
     * @throws Exception
     *             if has error
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
     * 
     * @throws Exception
     *             if has error
     */
    public final void testSearchByKeywords() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        String[] searchKeys = { "hp" };
        Action.addHistoryInfomationInSearchBar(solo, searchKeys);

    }

    /**
     * 1937867:List suggestion.
     * 
     * @throws Exception
     *             if has error
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
     * 
     * @throws Exception
     *             if has error
     */
    public final void testKeywordOnTheFristLineByChoose() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        Action.addInitializeData(solo, 0, "h");
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // value in the first line
        String suggestionRecord = Action.getValuesInTextview(solo,
                "id/search_suggestion_text", 0);

        // click "+" in list suggestion window
        Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);

        // back to home screen after search
        solo.pressSoftKeyboardSearchButton();
        Assert.navigateToResultPage(solo);
        Action.clickHomeButtonOnScreen(solo);

        // click on search button again
        Action.clickSearchButtonOnScreen(solo);

        String historyRecord = Action.getValuesInTextview(solo,
                "id/search_suggestion_text", 0);

        assertEquals("Keyword is not on the first line", suggestionRecord,
                historyRecord);

    }

    /**
     * 1937870:Direct input keyword - check in recent memory search function.
     * 
     * @throws Exception
     *             if has error
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
     * 
     * @throws Exception
     *             if has error
     */
    public final void testRecentRecordsListbyChoose() throws Exception {

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
        assertEquals("Auto-complete suggestions number" + " is more than 10.",
                count, 10);

        for (int j = 0; j < 10; j++) {
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
            String suggestionRecord = Action.getValuesInTextview(solo,
                    "id/search_suggestion_text", 0);
            String searchKey = searchKeys[1 + j];
            assertEquals("The order of suggestion list is not" + " correct.",
                    suggestionRecord, searchKey);

            // click back icon
            Action.clickHomeButtonOnScreen(solo);
        }
    }

    /**
     * 1937872:Display recent 10 records.
     * 
     * @throws Exception
     *             if has error
     */
    public final void testRecentRecordsListbyInput() throws Exception {

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
        assertEquals("Auto-complete suggestions number" + " is more than 10.",
                count, 10);

        // close soft keyboard
        Action.closeSoftKeyBoard(solo);

        /**
         * get the value of suggestion list and verify whether the suggestion
         * list is correct
          */

        for (int j = 0; j < 10; j++) {
            String suggestionRecord = Action.getValuesInTextview(solo,
                    "id/search_suggestion_text", j);
            String searchKey = searchKeys[searchKeys.length - 1 - j];
            assertEquals("The order of suggestion list" + " is not correct.",
                    suggestionRecord, searchKey);
        }

    }

    /**
     * 1937873:By click on the keyword into the search box.
     *
     * @throws Exception
     *             if has error
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

        assertEquals("Auto fill value to search bar failed.", barvalue, "hp");

    }

    /**
     * 1937874:Recent Search by keyword.
     *
     * @throws Exception
     *             if has error
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
     *
     * @throws Exception
     *             if has error
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
     *
     * @throws Exception
     *             if has error
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
     * 
     * @throws Exception
     *             if has error
     */
    public final void testChangeValueByClickPlus() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        String[] searchKeys = { "hp" };
        Action.addHistoryInfomationInSearchBar(solo, searchKeys);

        Action.clickSearchButtonOnScreen(solo);

        String tvValue = "";
        // click "+" twice
        for (int i = 0; i < 2; i++) {
            // value where in front of "+"
            tvValue = Action.getValuesInTextview(solo,
                    "id/search_suggestion_text", 0);
            Action.clickPlusInOpenWindow(solo, "search_fill_up", 0);
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        }
        String barvalue = Action.getValuesInTextview(solo,
                "search_autocompletetext");

        assertEquals("Auto fill value to search bar failed.", barvalue, tvValue);

    }

    /**
     * 1937878:Clear into the recent search keyword.
     *
     * @throws Exception
     *             if has error
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
     * 
     * @throws Exception
     *             if has error
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
     * 
     * @throws Exception
     *             if has error
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
     * 
     * @throws Exception
     *             if has error
     */
    public final void testSearchBarShowInCategory() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        assertTrue("Search icon is hidden.", solo.getView("menu_search", 0)
                .isShown());

    }

    /**
     * 1937888:Verify search icon click.
     *
     * @throws Exception
     *             if has error
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
     *
     * @throws Exception
     *             if has error
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
     *
     * @throws Exception
     *             if has error
     */
    public final void testBackToCostumeList() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        solo.scrollToTop();
        Action.clickText(solo, ValidationText.APPAREL);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // click back(home) screen
        Action.clickHomeButtonOnScreen(solo);

        Assert.costumeL2ListShow(solo);

    }

    /**
     * 1937891:Check click return icon in L3 layer classification.
     *
     * @throws Exception
     *             if has error
     */
    public final void testBackToWomenClothingList() throws Exception {

        // clear history information then back to home screen
        Action.clearHistoryInfomation(solo);

        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        solo.scrollToTop();
        Action.clickText(solo, ValidationText.APPAREL);

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        Action.clickText(solo, ValidationText.POPULAR_WOMEN);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // click back(home) screen
        Action.clickHomeButtonOnScreen(solo);

        // get background text of search bar
        String barvale = Action.getValuesInTextview(solo, "action_bar_title");

        assertEquals("Back to fashion women's clothing List failed",
                barvale.trim(), ValidationText.POPULAR_WOMEN);

        Assert.womenClothingCategoryListShow(solo);

    }

}
