/*
 * This is automated script about "SRP".
 *
 * You can run these test cases either on the emulator or on device.
 *
 * By Eclipse:
 * Right click the test project and select Run As --> Run As Android JUnit Test
 *
 * By Ant:
 * 1.Run "android update test-project -m [path to target application]
 *  -p [path to the test folder]"  in command line .
 * 2."ant test"
 *
 * By using instrument command:
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client
 * .android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run SRP:adb shell am instrument -e class com.yahoo.mobile.client
 * .android.ecstore.test.SRP.SRP -w com.yahoo.mobile.client.android.
 * ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.SRP;

import java.util.ArrayList;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
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

public class SRP extends ActivityInstrumentationTestCase2 <Activity> {

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
    public SRP() throws ClassNotFoundException {
        super((Class<Activity>) launcherActivityClass);
    }

    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());

    }

    @Override
    public final void tearDown() throws Exception {

        solo.finishOpenedActivities();
        super.tearDown();
    }

    /**
     * 1937918:Check 'Tab' display.
     * @throws Exception if has error
     */
    public final void testTabDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        assertTrue(
                "Goods or stores is not display.",
                solo.searchText(ValidationText.COMMODITY)
                        && solo.searchText(ValidationText.COMMODITY));

    }

    /**
     * 1937920:Navigate to store tab.
     * @throws Exception if has error
     */
    public final void testStoreTab() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        assertTrue("Store tab is not selected.",
                Action.getIsViewShown(solo, "heart_button"));

    }

    /**
     * 1937921:Go back to goods tab.
     * @throws Exception if has error
     */
    public final void testBackToGoodsTab() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 0);

        assertTrue("Store tab is not selected.",
                Action.getIsViewShown(solo, "star_button"));

    }

    /**
     * 1937924:Check Goods result text.
     * @throws Exception if has error
     */
    public final void testSearchGoodsResultText() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        // get the text of search result
        String str = Action.getValuesInTextview(solo,
                "category_tab_secondary_title", 0).replace(
                ValidationText.RESULTS_VALUE, "");

        assertTrue("The search result number format is incorrect! ",
                str.matches("[0-9]+"));

    }

    /**
     * 1937925:Check store result text.
     * @throws Exception  if has error
     */
    public final void testSearchStoreResultText() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        // get the text of search result
        String str = Action.getValuesInTextview(solo,
                "category_tab_secondary_title", 1).replace(
                ValidationText.RESULTS_VALUE, "");
        assertTrue("The search result number format is incorrect! ",
                str.matches("[0-9]+"));

    }

    /**
     * 1937927:default show 20 items.
     * @throws Exception  if has error
     */
    public final void testDefaultDisplay20Items() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        GridView gv = (GridView) solo.getView("gridview");

        // default count is 20
        assertEquals("Store tab is not selected:", gv.getCount(),
                Action.DEFAULT_LISTVIEW_COUNT);

    }

    /**
     * 1937928:Load more items.
     * @throws Exception if has error
     */
    public final void testLoadMoreItems() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // Action.closeSoftKeyBoard(solo);

        // swipe 4 times
        for (int k = 0; k < Action.VIEW_ID_FOUR; k++) {
            TestHelper.swipeUp(solo, 1);
        }
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // get the numbers of grid view
        GridView gv = (GridView) solo.getView("gridview");
        int gvCount = gv.getCount();

        assertTrue("Store tab is not selected:"
                , gvCount > Action.DEFAULT_LISTVIEW_COUNT);

    }


    /**
     * 1937931:Check advanced page.
     * @throws Exception if has error
     */
    public final void testCheckAdvancedPage() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Advanced screen
        Action.enterAdvancedPage(solo);

        Assert.navigateToAdvancedTab(solo);

    }

    /**
     * 1937932:Check if default to choose "sort" tab.
     * @throws Exception if has error
     */
    public final void testDefaultChooseSortTab() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        // navigate to Advanced screen
        Action.enterAdvancedPage(solo);

        TextView tv = (TextView) solo.getView("indicator_sort", 0);

        assertEquals("Sort tab is not the default option.", tv.getVisibility(),
                0);

    }

    /**
     * 1937933:Display sort tab.
     * @throws Exception if has error
     */
    public final void testNavigateToSortTab() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Advanced screen
        Action.enterAdvancedPage(solo);

        // check if go to sort screen
        Assert.navigateToSortTab(solo);

    }

    /**
     * 1937934: Display Filter tab.
     * @throws Exception if has error
     */
    public final void testNavigateToFilterTab() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Filter screen
        Action.enterAdvancedSortPage(solo);

        // check if go to filter screen
        Assert.navigateToFilterTab(solo);

    }

    /**
     * 1937935: Check sort tab items.
     * @throws Exception if has error
     */
    public final void testCheckSortTabItems() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Advanced screen
        Action.enterAdvancedPage(solo);

        // check sort table items
        Assert.navigateToSortTab(solo);

        for (int i = 0; i < Action.VIEW_ID_THREE; i++) {
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

    /**
     * 1937940: Check Layout.
     * @throws Exception if has error
     */
    public final void testCheckLayoutOfFilterTab() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Filter screen
        Action.enterAdvancedSortPage(solo);

        // check if go to filter screen
        Assert.checkFilterLayout(solo);

    }

    /**
     * 1937944:Check the function of "OK" button.
     * @throws Exception if has error
     */
    public final void testCheckConfirmButtonFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Filter screen
        Action.enterAdvancedSortPage(solo);

        // Action.closeSoftKeyBoard(solo);

        // Click on confirm button
        solo.clickOnView(solo.getView("btn_ok"));

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // get the number of gridview
        ArrayList<GridView> gvList = solo.getCurrentViews(GridView.class);
        assertEquals("Go back to search result screen failed", gvList.size(),
                1);

    }

    /**
     * 1937949:Unselected "Credit cards".
     * @throws Exception if has error
     */
    public final void testUnselectedCanSwipeFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

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

    /**
     * 1937950:Check HasVideo display.
     * @throws Exception if has error
     */
    public final void testHasVideoDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Filter screen
        Action.enterAdvancedSortPage(solo);

        // Get "hasVideo" button.
        ToggleButton tb = (ToggleButton) solo.getView("tb_hasvideo");
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        assertTrue("Has video button is not selected.",
                tb.isChecked());

    }

    /**
     * 1937952:Unselected "A/V".
     * @throws Exception if has error
     */
    public final void testUnselectedHasVideoFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

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

    /**
     * 1937955:Unselected "0 interest rate".
     * @throws Exception if has error
     */
    public final void testUnselectedZeroIntFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

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

    /**
     * 1937958:Unselected "Installment".
     * @throws Exception if has error
     */
    public final void testUnselectedccInstallFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

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

    /**
     * 1937961:Unselected "Payment".
     * @throws Exception if has error
     */
    public final void testUnselectedCvsPayFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

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

    /**
     * 1937964:Unselected "Super pickups".
     * @throws Exception if has error
     */
    public final void testUnselectedCvsPickFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

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

    /**
     * 1937965:HasStock option display.
     * @throws Exception if has error
     */
    public final void testHasStockOptionDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Filter screen
        Action.enterAdvancedSortPage(solo);

        // Action.closeSoftKeyBoard(solo);

        String view_id = "tb_hasstock";
        Action.clickView(solo, view_id);
        assertTrue("Has stock button is not selected.",
                ((ToggleButton) solo.getView(view_id)).isChecked());

    }

    /**
     * 1937967:Unselected "Has Stock".
     * @throws Exception if has error
     */
    public final void testUnselectedHasStockFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

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

    /**
     * 1937970:Unselected "Has Image".
     * @throws Exception if has error
     */
    public final void testUnselectedHasImageFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Filter screen
        Action.enterAdvancedSortPage(solo);

        // Action.closeSoftKeyBoard(solo);

        solo.sleep(ValidationText.WAIT_TIME_LONGER);

        String view_id = "tb_hasimage";
        Action.clickView(solo, view_id);
        assertTrue("Has stock button is not selected.",
                ((ToggleButton) solo.getView(view_id)).isChecked());

        Action.clickView(solo, view_id);
        assertFalse("Has stock button is selected.",
                ((ToggleButton) solo.getView(view_id)).isChecked());

    }

    /**
     * 1937973:Unselected "Superior Shop".
     * @throws Exception if has error
     */
    public final void testUnselectedisSuperiorFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

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

    /**
     * 11937976:Navigate to item page.
     * @throws Exception if has error
     */
    public final void testNavigateToItemPageByClickPicture() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        Action.setListViewStyleAfterSearch(solo);

        Action.clickView(solo, "listitem_productlist_image", 0);

        assertTrue("Navigate to item page failed.",
                solo.getView("productitem_btn_purchase_now", 0).isShown());

    }

    /**
     * 1937979:Navigate to item page.
     * @throws Exception if has error
     */
    public final void testNavigateToItemPageByClickGoodsName()
            throws Exception {

        Action.enterToJacketAfterSearch(solo);

        Action.setListViewStyleAfterSearch(solo);

        Action.clickView(solo, "listitem_productlist_store_name", 0);

        assertTrue("Navigate to item page failed.",
                solo.getView("productitem_btn_purchase_now", 0).isShown());

    }

    /**
     * 1937980:Check the commodity price display.
     * @throws Exception if has error
     */
    public final void testCommodityPriceDisplayInListStyle() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // list style
        Action.setListViewStyleAfterSearch(solo);

        String sr = Action.getValuesInTextview(solo,
                "listitem_productlist_price", 0);

        // Judgment whether the price matches the format of '$xxx'.
        boolean isNum = sr.matches("[$][0-9]+");

        assertTrue(
                " Cannot find the commodity price or price format "
        + " is incorrect! " , isNum);

    }

    /**
     * 1937981:Store name on the right of store evaluation.
     * @throws Exception if has error
     */
    public final void testGoodsEvaluationDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // list style
        Action.setListViewStyleAfterSearch(solo);

        // compare the position of two views
        boolean flag = TestHelper.positionCompare(solo,
                "listitem_productlist_store_name", 0,
                "listitem_productlist_store_rating", 0, Action.VIEW_ID_THREE);

        if (!flag) {
            assertTrue("Store name is not on the right of store evaluation.",
                    false);
        }

    }

    /**
     * 1937982:Commodity prices are on the left of stars.
     * @throws Exception if has error
     */
    public final void testStarsDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // list style
        Action.setListViewStyleAfterSearch(solo);

        // compare the position of two views
        boolean flag = TestHelper.positionCompare(solo,
                "listitem_productlist_price", 0, "star_button", 0,
                Action.VIEW_ID_THREE);

        if (!flag) {
            assertTrue("Commodity prices are not on the left of stars.", false);
        }
    }

    /**
     * 1937983:Check to click the start icon without login.
     * @throws Exception if has error
     */
    public final void testStarIconWithoutLogin() throws Exception {

        // Account.accountLogIn(solo);
        Account.judgementAccountWithoutLogin(solo);

        Action.enterToJacketAfterSearch(solo);

        Action.setListViewStyleAfterSearch(solo);

        View star = (View) solo.getView("star_button", 0);
        solo.clickOnView(star);

        if (!solo.waitForText(ValidationText
                .PLEASE_LOGIN_ACCOUNT, 1, ValidationText.WAIT_TIME_LONGER)) {
            assertTrue("Error tips.", false);

        }

    }

    /**
     * 1937984:Commodity joined successfully collection list.
     * @throws Exception if has error
     */
    public final void testAddGoodsIntoList() throws Exception {

        // Account.accountLogIn(solo);
        Account.judgementAccountWithoutLogin(solo);

        Action.enterToJacketAfterSearch(solo);

        Action.setListViewStyleAfterSearch(solo);

        // login
        Account.overAccountLogIn(solo);

        // click category view
        Action.clickView(solo, "tab_image", 2);

        Action.clickView(solo, "star_button", 0);

        if (solo.waitForText(ValidationText.HAS_ADDED_COLLECTION, 1,
                ValidationText.WAIT_TIME_LONG)
                || solo.waitForText(ValidationText.HAS_REMOVED_COLLECTION, 1,
                        ValidationText.WAIT_TIME_LONGER)) {
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
        } else {
            assertTrue("Add failed.", false);
        }
    }

    /**
     * 1937985:Check browser mode icon display.
     * @throws Exception if has error
     */
    public final void testBroserModelDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        Action.setListViewStyleAfterSearch(solo);

        Action.enterAdvancedBrowserModePage(solo);

        assertTrue("List option is not selected! ",
                ((RadioButton) solo.getView("btn_list_small")).isChecked());

    }

    /**
     * 1937986:Check small picture icon.
     * @throws Exception if has error
     */
    public final void testSmallPictureDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // small picture style
        Action.setSmallPhotoViewStyleAfterSearch(solo);

        Action.enterAdvancedBrowserModePage(solo);

        assertTrue("Small pictrue icon is not selected! ",
                ((RadioButton) solo.getView("btn_list_grid")).isChecked());

    }

    /**
     * 1937993:Check the commodity price display.
     * @throws Exception
     *             if has error
     */
    public final void testCommodityPriceDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // small picture style
        Action.setSmallPhotoViewStyleAfterSearch(solo);

        String sr = Action.getValuesInTextview(solo,
                "listitem_productlist_price", 0);

        // Judgment whether the price matches the format of '$xxx'.
        boolean isNum = sr.matches("[$][0-9]+");

        assertTrue(
                " Cannot find the commodity price or price format"
        + " is incorrect! ", isNum);

    }

    /**
     * 1937995:Check the Star icon display.
     * @throws Exception if has error
     */
    public final void testStarIconDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        Action.setSmallPhotoViewStyleAfterSearch(solo);

        View star = (View) solo.getView("star_button", 0);
        assertTrue(" Cannot find the star icon ", star.isShown());

    }

    /**
     * 1937996:Check to click the start icon without login.
     * @throws Exception  if has error
     */
    public final void testStarIconWithoutLoginInSmallPictureStyle()
            throws Exception {

        // Account.accountLogIn(solo);
        Account.judgementAccountWithoutLogin(solo);

        Action.enterToJacketAfterSearch(solo);

        Action.setSmallPhotoViewStyleAfterSearch(solo);

        View star = (View) solo.getView("star_button", 0);
        solo.clickOnView(star);

        if (!solo.waitForText(
ValidationText.PLEASE_LOGIN_ACCOUNT, 1, ValidationText.WAIT_TIME_LONGER)){
            assertTrue("Error tips.", false);
        }


    }

    /**
     * 1937997:Commodity joined successfully collection list.
     * @throws Exception if has error
     */
    public final void testAddGoodsIntoListInSmallPictureStyle()
            throws Exception {

        // Account.accountLogIn(solo);
        Account.judgementAccountWithoutLogin(solo);

        Action.enterToJacketAfterSearch(solo);

        Action.setSmallPhotoViewStyleAfterSearch(solo);

        // login
        Account.overAccountLogIn(solo);

        // click category view
        Action.clickView(solo, "tab_image", 2);

        Action.clickView(solo, "star_button", 0);

        if (solo.waitForText(ValidationText.HAS_ADDED_COLLECTION, 1,
                ValidationText.WAIT_TIME_LONG)
                || solo.waitForText(ValidationText.HAS_REMOVED_COLLECTION, 1,
                        ValidationText.WAIT_TIME_LONGER)) {
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
         } else {
            assertTrue("Add failed.", false);
        }
    }

    /**
     * 1937998:Check large picture icon.
     * @throws Exception  if has error
     */
    public final void testLargePictureDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        Action.setLargePhotoViewStyleAfterSearch(solo);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("menu_filter"));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        assertTrue("Large pictrue icon is not selected! ",
                ((RadioButton) solo.getView("btn_list_large")).isChecked());

        solo.clickOnView(solo.getView("btn_list_small"));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
    }

    /**
     * 1938001:Navigate to item page.
     * @throws Exception if has error
     */
    public final void testNavigateToItemPageInLargeStyle() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        Action.setLargePhotoViewStyleAfterSearch(solo);

        Action.clickView(solo, "listitem_productlist_image", 0);

        assertTrue("Navigate to item page failed.",
                solo.getView("productitem_btn_purchase_now", 0).isShown());

    }

    /**
     * 1938005:Check the commodity price display.
     * @throws Exception if has error
     */
    public final void testCommodityPriceDisplayInLargeStyle() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // large picture style
        Action.setLargePhotoViewStyleAfterSearch(solo);

        String sr = Action.getValuesInTextview(solo,
                "listitem_productlist_price", 0);

        // Judgment whether the price matches the format of '$xxx'.
        boolean isNum = sr.matches("[$][0-9]+");

        assertTrue(
                " Cannot find the commodity price or "
        + "price format is incorrect! ",
                isNum);

        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1938006:Store name on the right of store evaluation.
     * @throws Exception  if has error
     */
    public final void testGoodsEvaluationDisplayInLargeStyle()
            throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // large picture style
        Action.setLargePhotoViewStyleAfterSearch(solo);

        // compare the position of two views
        boolean flag = TestHelper.positionCompare(solo,
                "listitem_productlist_store_name", 0,
                "listitem_productlist_store_rating", 0, Action.VIEW_ID_THREE);

        if (!flag) {
            assertTrue("Store name is not on the right of store evaluation.",
                    false);
        }

    }

    /**
     * 1938007:Commodity prices are on the left of stars.
     * @throws Exception  if has error
     */
    public final void testStarsDisplayInLargeStyle() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // large picture style
        Action.setLargePhotoViewStyleAfterSearch(solo);

        // compare the position of two views
        boolean flag = TestHelper.positionCompare(solo,
                "listitem_productlist_price", 0, "star_button", 0, 3);

        if (!flag) {
            assertTrue("Commodity prices are not on the left of stars.", false);
        }

    }

    /**
     * 1938008:Check to click the start icon without login.
     * @throws Exception   if has error
     */
    public final void testStarIconWithoutLoginInLargeStyle() throws Exception {

        // Account.accountLogIn(solo);
        Account.judgementAccountWithoutLogin(solo);

        Action.enterToJacketAfterSearch(solo);

        // large picture style
        Action.setLargePhotoViewStyleAfterSearch(solo);

        View star = (View) solo.getView("star_button", 0);
        solo.clickOnView(star);

        if (!solo.waitForText(ValidationText.PLEASE_LOGIN_ACCOUNT,
                1, ValidationText.WAIT_TIME_LONGER)) {
            assertTrue("Error tips.", false);
        }


    }

    /**
     * 1938013:"Total XXX " is displayed.
     * @throws Exception  if has error
     */
    public final void testSearchResultDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // get the text of search result
        String str = Action.getValuesInTextview(solo,
                "category_tab_secondary_title", 1).replace(
                ValidationText.RESULTS_VALUE, "");

        assertTrue("The search result number format is incorrect! ",
                str.matches("[0-9]+"));

    }

    /**
     * 1938015:Back to category tab.
     * @throws Exception  if has error
     */
    public final void testBackToCategoryTabFromStoreTab() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        Action.clickHomeButtonOnScreen(solo);

        solo.scrollToTop();
        Assert.categoryListShow(solo);

    }

    /**
     * 1938024:Navigate to store page.
     * @throws Exception   if has error
     */
    public final void testNavigateToStorePage() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        // click on store icon
        Action.clickView(solo, "listitem_storelist_image", 0);

        String[] storeTitle = ValidationText.STORE_TITLE;

        for (int i = 0; i < 2; i++) {

            String textviewValue = Action.getValuesInTextview(solo,
                    "category_tab_primary_title", i);
            assertEquals("Navigate to store page failed.", storeTitle[i],
                    textviewValue);
        }

    }

    /**
     * 1938025:Store name blew store count.
     * @throws Exception  if has error
     */
    public final void testStoreCountDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

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

    /**
     * 1938026:Navigate to store item list page.
     * @throws Exception  if has error
     */
    public final void testNavigateToStoreItemListPage() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        // click on store icon
        Action.clickView(solo, "listitem_storelist_store_item_count");

        String[] storeTitle = ValidationText.STORE_TITLE;

        for (int i = 0; i < 2; i++) {

            String textviewValue = Action.getValuesInTextview(solo,
                    "category_tab_primary_title", i);
            assertEquals("Navigate to store page failed.", storeTitle[i],
                    textviewValue);
        }

    }

    /**
     * 1938027:Goods number on the right of evaluate.
     * @throws Exception  if has error
     */
    public final void testEvaluateDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        // compare the position of two views
        boolean flag = TestHelper.positionCompare(solo,
                "listitem_storelist_store_item_count", 0,
                "listitem_storelist_store_rating", 0, Action.VIEW_ID_THREE);

        if (!flag) {
            assertTrue("Goods number is not on the right of evaluate.", false);
        }

    }

    /**
     * 1938029: Goods evaluate on the right of heart icon.
     * @throws Exception  if has error
     */
    public final void testHeartIconDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        // compare the position of two views
        boolean flag = TestHelper.positionCompare(solo,
                "listitem_storelist_store_rating", 0,
                "heart_button", 0, Action.VIEW_ID_THREE);

        if (!flag) {
            assertTrue("Goods evaluate is not on the right of heart icon.",
                    false);
        }

    }

    /**
     * 1938030:Navigate to login screen.
     * @throws Exception if has error
     */
    public final void testNavigateToLoginScreen() throws Exception {

        // Account.accountLogIn(solo);
        Account.judgementAccountWithoutLogin(solo);

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        View heart = (View) solo.getView("heart_button", 0);
        solo.clickOnView(heart);

        if (!solo.waitForText(ValidationText.PLEASE_LOGIN_ACCOUNT,
                1, ValidationText.WAIT_TIME_LONGER)) {
            assertTrue("Error tips.", false);
        }
    }

    /**
     * 1938031:Shop to join the collection list.
     * @throws Exception  if has error
     */
    public final void testAddStoreIntoCollectList() throws Exception {

        // Account.accountLogIn(solo);
        Account.judgementAccountWithoutLogin(solo);

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        // login
        Account.overAccountLogIn(solo);

        // click category view
        Action.clickView(solo, "tab_image", 2);

        solo.clickOnView(solo.getView("heart_button", 0));

        boolean alreadyAdd;

        // Get toast text.
        if (solo.waitForText(ValidationText.HAS_ADDED_COMMODITY)) {
            alreadyAdd = solo.waitForText(ValidationText.HAS_ADDED_COMMODITY);
            assertTrue("Add failed.", alreadyAdd);
        } else {
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
            solo.clickOnView(solo.getView("heart_button", 0));
            alreadyAdd = solo.waitForText(ValidationText.HAS_ADDED_COMMODITY);
            assertTrue("Add failed.", alreadyAdd);

        }
        Action.setListViewStyleAfterSearch(solo);
    }

    /**
     * 1977511:Verify shouldn't duplicate keyword in search box.
     * @throws Exception  if has error
     */
    public final void testInputKeywords() throws Exception {

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.ONE);

        solo.goBack();
        Action.clickSearchButtonOnScreen(solo);
        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.TWO);
        solo.goBack();
        Action.clickSearchButtonOnScreen(solo);
        assertTrue(ValidationText.TWO + " not found.",
                solo.searchText(ValidationText.TWO));

    }

    /**
     * 1937914:Verify "back" icon function.
     * @throws Exception  if has error
     */
    public final void testClickBackFunction() throws Exception {

        Action.clickText(solo, ValidationText.ALL_CATEGORIES);
        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);
        solo.goBack();
        Action.navigateToCategoryScreen(solo);
    }

    /**
     * 1937989:Verify click product image in grid view.
     * @throws Exception   if has error
     */
    public final void testClickProductImageInGridView() throws Exception {

        Action.clickText(solo, ValidationText.ALL_CATEGORIES);
        Action.clickText(solo, ValidationText.APPAREL);
        Action.setSmallPhotoViewStyleAfterSearch(solo);
        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        View productImage = (View) solo
                .getView("listitem_productlist_image", 0);
        solo.clickOnView(productImage);
        TextView introduct = (TextView) solo
                .getView("productitem_product_name");
        assertTrue("Not enter to product page.", introduct.isShown());
        Action.setListViewStyleAfterSearch(solo);
    }

    /**
     * 1938004:Verify click product name in large photo view.
     * @throws Exception  if has error
     */
    public final void testClickProductNameInLargePhotoView() throws Exception {

        Action.clickText(solo, ValidationText.ALL_CATEGORIES);
        Action.clickText(solo, ValidationText.APPAREL);
        Action.setLargePhotoViewStyleAfterSearch(solo);
        // click search button
        Action.clickSearchButtonOnScreen(solo);
        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        TextView productName = (TextView) solo
                .getView("listitem_productlist_title");
        Log.i("number", productName.getText().toString());
        solo.clickOnView(productName);
        assertTrue("Not enter to product page.", productName.isShown());

        Action.setListViewStyleAfterSearch(solo);
    }

    /**
     * 1938009:Click star icon to add the product to favorite list.
     * @throws Exception  if has error
     */
    public final void testClickStarIconAddToFavoriteList() throws Exception {

        Account.judgementAccountWithoutLogin(solo);
        Action.enterToJacketAfterSearch(solo);
        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        Action.clickStarIconNote(solo);
    }

    /**
     * 1937926:Check the default browse mode.
     * @throws Exception  if has error
     */
    public final void testCheckTheDefaultBrowseMode() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        GridView lv = (GridView) solo.getView("gridview", 0);
        int defaultItems = lv.getCount();
        assertEquals("The default browse mode is listview."
                , Action.DEFAULT_LISTVIEW_COUNT, defaultItems);
    }

    /**
     * 1937943:Check confirm button display.
     * @throws Exception  if has error
     */
    public final void testCheckConfirmButtonInAdvancedPage() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        // navigate to Advanced screen
        solo.clickOnView(solo.getView("menu_filter"));
        Action.clickText(solo, ValidationText.FILTER);
        assertTrue("Confirm button not displayed.",
                solo.searchText(ValidationText.OK));
    }

    /**
     * 1937941:Check cancel button display.
     * @throws Exception  if has error
     */
    public final void testCheckCancelButtonInAdvancedPage() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        // navigate to Advanced screen
        solo.clickOnView(solo.getView("menu_filter"));
        Action.clickText(solo, ValidationText.FILTER);
        String view_id = "tb_cc";
        Action.clickView(solo, view_id);
        assertTrue("Confirm button not displayed.",
                solo.searchText(ValidationText.CANCEL));
    }

    /**
     * 1937929:Click"All classification"button in search result.
     * @throws Exception  if has error
     */
    public final void testClickAllclassificaitonButton() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        solo.clickOnView(solo.getView("tab_image", 2));
        Action.navigateToCategoryScreen(solo);

    }

    /**
     * 1937974:Check product image display.
     * @throws Exception   if has error
     */
    public final void testProductImageDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        ImageView product = (ImageView) solo.getView(
                "listitem_productlist_image", 0);
        assertTrue("Product image not displayed.", product.isShown());
    }

    /**
     * 1938010:Check tab status.
     * @throws Exception   if has error
     */
    public final void testVerifyTabDisplayStatus() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        Action.clickText(solo, ValidationText.SHOP);
        TextView shop = (TextView) solo
                .getView("category_tab_primary_title", 1);
        assertTrue("Shop tab not highlight.", shop.isShown());

    }

    /**
     * 1938011:Check shop tab list show.
     * @throws Exception  if has error
     */
    public final void testShopTabListDisplay() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        Action.clickText(solo, ValidationText.SHOP);
        ImageView storeListImage = (ImageView) solo.getView(
                "listitem_storelist_image", 0);
        assertTrue("Store list displayed incorrect.", storeListImage.isShown());
    }

    /**
     * 1938012:Product list tab display.
     * @throws Exception  if has error
     */
    public final void testProductTabDisplay() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.HANSHEN_BRAND);
        // click search button
        Action.clickSearchButtonOnScreen(solo);
        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);
        Action.clickText(solo, ValidationText.SHOP);
        ImageView storeListImage = (ImageView) solo.getView(
                "listitem_storelist_image", 0);
        assertTrue("Store list displayed incorrect.", storeListImage.isShown());
    }

    /**
     * 1938014:Check header in search result.
     * @throws Exception  if has error
     */
    public final void testHeaderInSearchResult() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        View back = (View) solo.getView("home", 1);

        View keywords = (View) solo.getView("search_autocompletetext");

        View advanced = (View) solo.getView("menu_filter");

        assertTrue("Some component not displayed.",
                back.isShown() && keywords.isShown() && advanced.isShown());
    }

    /**
     * 1938016:Click search icon in search store page.
     * @throws Exception  if has error
     */
    public final void testClickSearchIconInSearchStorePage() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        Action.clickText(solo, ValidationText.SHOP);
        ImageView storeListImage = (ImageView) solo.getView(
                "listitem_storelist_image", 0);
        solo.clickOnView(storeListImage);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View iv = solo.getView("menu_search");
        solo.clickOnView(iv);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(iv);
        View keywords = (View) solo.getView("search_suggestion_text");
        assertTrue("Search component not displayed.", keywords.isShown());

    }

    /**
     * 1938032:Click "Advanced" button in store page.
     * @throws Exception  if has error
     */
    public final void testClickAdvancedButtonInStorePage() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        Action.clickText(solo, ValidationText.SHOP);
        ImageView storeListImage = (ImageView) solo.getView(
                "listitem_storelist_image", 0);
        solo.clickOnView(storeListImage);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View iv = solo.getView("menu_filter");
        solo.clickOnView(iv);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        ListView lv = (ListView) solo.getView("list_sort", 0);

        int listviewCount = lv.getCount();
        assertEquals("Not four items in list.", listviewCount,
                Action.VIEW_ID_FOUR);
        for (int i = 0; i < listviewCount; i++) {

            boolean sortList = lv.getItemAtPosition(0).equals(
                    ValidationText.ADVANCED_SORT[0])
                    && lv.getItemAtPosition(1).equals(
                            ValidationText.ADVANCED_SORT[1])
                    && lv.getItemAtPosition(2).equals(
                            ValidationText.ADVANCED_SORT[2])
                    && lv.getItemAtPosition(Action.VIEW_ID_THREE).equals(
                            ValidationText.ADVANCED_SORT[3]);

            assertTrue("Sort incorrect.", sortList);

        }
    }

    /**
     * 1938033:Verify the price" low to high" Sort.
     * @throws Exception  if has error
     */
    public final void testThePriceLowToHigh() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        Action.clickText(solo, ValidationText.SHOP);
        ImageView storeListImage = (ImageView) solo.getView(
                "listitem_storelist_image", 0);
        solo.clickOnView(storeListImage);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View iv = solo.getView("menu_filter");
        solo.clickOnView(iv);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.LOW_TO_HIGH);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView priceOne = (TextView) solo.getView(
                "listitem_productlist_price", 0);
        String priceOneNumber = priceOne.getText().toString().trim();
        Log.i("number", "priceOneNumber" + priceOneNumber);
        TextView priceTwo = (TextView) solo.getView(
                "listitem_productlist_price", 1);
        String priceTwoNumber = priceTwo.getText().toString().trim();
        Log.i("number", "priceTwoNumber" + priceTwoNumber);
        assertTrue("Sort function incorrect.", Integer.valueOf(priceOneNumber
                .substring(1)) <= Integer.valueOf(priceTwoNumber.substring(1)));
    }

    /**
     * 1938034:Verify the price" high to low" Sort.
     * @throws Exception  if has error
     */
    public final void testThePriceHighToLow() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        Action.clickText(solo, ValidationText.SHOP);
        ImageView storeListImage = (ImageView) solo.getView(
                "listitem_storelist_image", 0);
        solo.clickOnView(storeListImage);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View iv = solo.getView("menu_filter");
        solo.clickOnView(iv);

        //Set the view to list view.
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("btn_browse_mode"));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("btn_list_small"));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        View ivs = solo.getView("menu_filter");
        solo.clickOnView(ivs);
        Action.clickText(solo, ValidationText.SORT);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.HIGH_TO_LOW);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        TextView priceOne = (TextView) solo.getView(
                "listitem_productlist_price", 0);

        String priceOneNumber = priceOne.getText().toString()
                .replaceAll(",", "").trim();
        Log.i("number", "priceOneNumber" + priceOneNumber);

        TextView priceTwo = (TextView) solo.getView(
                "listitem_productlist_price", 1);
        String priceTwoNumber = priceTwo.getText().toString()
                .replaceAll(",", "").trim();

        Log.i("number", "priceTwoNumber" + priceTwoNumber);
        assertTrue("Sort function incorrect.", Integer.valueOf(priceOneNumber
                .substring(1)) >= Integer.valueOf(priceTwoNumber.substring(1)));

    }

    /**
     * 1938017:Input keywords search.
     * @throws Exception  if has error
     */
    public final void testInputKeywordsSearch() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        Action.clickText(solo, ValidationText.SHOP);

        solo.goBack();
        // click search button
        Action.clickSearchButtonOnScreen(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.APPLE);
        assertTrue("Not found iphone relevant info.",
                solo.searchText(ValidationText.APPLE));
    }

    /**
     * 1937930:Verify browse by classified search results.
     * @throws Exception  if has error
     */
    public final void testBrowseByClassifiedSearchResults() throws Exception {

       Action.enterCategoryClothesPage(solo);
       solo.sleep(ValidationText.WAIT_TIME_SHORT);
       solo.clickOnText(ValidationText.HANSHEN_BRAND);
       Action.clickSearchButtonOnScreen(solo);
       Action.searchAfterPutData(solo, 0, ValidationText.JACKET);
       solo.sleep(ValidationText.WAIT_TIME_LONG);
       solo.clickOnView(solo.getView("tab_image",
               Action.VIEW_ID_TWO));
       Action.navigateToCategoryScreen(solo);
    }

    /**
     * 1937938:Verify the price order by "low to high".
     * @throws Exception  if has error
     */
    public final void testPriceOrderByIncrease() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("menu_filter"));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.LOW_TO_HIGH);

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView priceOne = (TextView) solo.getView(
                "listitem_productlist_price", 0);
        String priceOneNumber = priceOne.getText().toString().trim();
        Log.i("number", "priceOneNumber" + priceOneNumber);
        TextView priceTwo = (TextView) solo.getView(
                "listitem_productlist_price", 1);
        String priceTwoNumber = priceTwo.getText().toString().trim();
        Log.i("number", "priceTwoNumber" + priceTwoNumber);

        assertTrue("Sort function incorrect.", Integer.valueOf(priceOneNumber
                .substring(1)) <= Integer.valueOf(priceTwoNumber.substring(1)));

    }

    /**
     * 1937939:Verify the price order by "High to Low".
     * @throws Exception  if has error
     */
    public final void testPriceOrderByDecrease() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("menu_filter"));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.HIGH_TO_LOW);

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView priceOne = (TextView) solo.getView(
                "listitem_productlist_price", 0);
        String priceOneNumber = priceOne.getText().toString()
                .replaceAll(",", "").trim();
        Log.i("number", "priceOneNumber" + priceOneNumber);
        TextView priceTwo = (TextView) solo.getView(
                "listitem_productlist_price", 1);
        String priceTwoNumber = priceTwo.getText().toString()
                .replaceAll(",", "").trim();
        Log.i("number", "priceTwoNumber" + priceTwoNumber);

        assertTrue("Sort function incorrect.", Integer.valueOf(priceOneNumber
                .substring(1)) >= Integer.valueOf(priceTwoNumber.substring(1)));

    }

    /**
     * 1938018:Verify open and close search bar.
     * @throws Exception  if has error
     */
    public final void testSearchbarOpenClose() throws Exception {

        Action.enterToJacketAfterSearch(solo);
        solo.clickOnText(ValidationText.SHOP);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("listitem_storelist_image",
                Action.VIEW_ID_ZERO));
        Action.clickSearchButtonOnScreen(solo);
        solo.goBack();
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View storeName = (View) solo.getView("img_store_banner",
                Action.VIEW_ID_ZERO);
        assertTrue("Not back to store page.", storeName.isShown());
    }

    /**
     * 1938019:Verify default store number.
     * @throws Exception  if has error
     */
    public final void testDefaultStoreNumber() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickSearchButtonOnScreen(solo);
        Action.searchAfterPutData(solo, 0, ValidationText.S);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.clickOnText(ValidationText.SHOP);

        TextView result = (TextView) solo.getView(
                "category_tab_secondary_title", Action.VIEW_ID_ONE);
        String number = result.getText().toString().trim().substring(0,2);
        Log.i("number", number);
        assertTrue("Default item less 20.", Integer.parseInt(number) >= 20);
    }

    /**
     * 1937942:Verify can cancel select item.
     * @throws Exception  if has error
     */
    public final void testCancelSelected() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickSearchButtonOnScreen(solo);
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.clickOnText(ValidationText.COMMODITY);
        View ivs = solo.getView("menu_filter");
        solo.clickOnView(ivs);
        Action.clickText(solo, ValidationText.FILTER);

        // solo.clickOnToggleButton("可刷卡");
        ToggleButton tb = (ToggleButton) solo.getView("tb_cc");
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue(" 'Credit cards accepted'  button unselected.",
                tb.isChecked());
        solo.clickOnText(ValidationText.CANCEL);
        View iv = solo.getView("menu_filter");
        solo.clickOnView(iv);
        Action.clickText(solo, ValidationText.FILTER);
        ToggleButton tbs = (ToggleButton) solo.getView("tb_cc");
        assertFalse("'Credit cards accepted'  button  selected.",
                tbs.isChecked());
    }

    /**
     * 1937945:Verify filter by price.
     * @throws Exception  if has error
     */
    public final void testFilterByPrice() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickSearchButtonOnScreen(solo);
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.clickOnText(ValidationText.COMMODITY);
        View ivs = solo.getView("menu_filter");
        solo.clickOnView(ivs);
        Action.clickText(solo, ValidationText.FILTER);

        solo.clickOnText(ValidationText.OK);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        TextView price;

        price = (TextView) solo.getView("listitem_productlist_price", 0);

        String sr = price.getText().toString().replace("$", "");

        Log.i("number", sr);
        assertTrue(
                "The product price out of range. ",
                Integer.valueOf(sr) <= 100000);
    }
}
