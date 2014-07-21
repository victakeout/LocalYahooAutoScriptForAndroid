/*
 * This is automated script about "SRPPartOne".
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
 * .android.ecstore.test.SRPPartOne.SRPPartOne -w com.yahoo.mobile.client.android.
 * ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.SRPPartOne;

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

public class SRPPartOne extends ActivityInstrumentationTestCase2 <Activity> {

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
    public SRPPartOne() throws ClassNotFoundException {
        super((Class<Activity>) launcherActivityClass);
    }

    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
     //   Assert.testFirstLaunch(solo);
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
                        && solo.searchText(ValidationText.SHOP));

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
     * 1937921:Go back to product tab.
     * @throws Exception if has error
     */
    public final void testBackToGoodsTab() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        // click on product tab
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

        // get the number of grid view
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

        String viewId = "tb_cc";
        Action.clickView(solo, viewId);
        assertTrue("Can_Swipe button is not selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

        Action.clickView(solo, viewId);
        assertFalse("Can_Swipe button is selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());
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

        String viewId = "tb_hasvideo";
        Action.clickView(solo, viewId);
        assertTrue("Has video button is not selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

        Action.clickView(solo, viewId);
        assertFalse("Has video button is selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

    }

    /**
     * 1937955:Unselected "0 interest rate".
     * @throws Exception if has error
     */
    public final void testUnselectedZeroIntFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Filter screen
        Action.enterAdvancedSortPage(solo);

        String viewId = "tb_cczeroint";
        Action.clickView(solo, viewId);
        assertTrue("CC zero button is not selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

        Action.clickView(solo, viewId);
        assertFalse("CC zero button is selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

    }

    /**
     * 1937958:Unselected "Installment".
     * @throws Exception if has error
     */
    public final void testUnselectedccInstallFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Filter screen
        Action.enterAdvancedSortPage(solo);

        String viewId = "tb_ccinstall";
        Action.clickView(solo, viewId);
        assertTrue("CC install button is not selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

        Action.clickView(solo, viewId);
        assertFalse("CC install button is selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

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

        String viewId = "tb_cvs_pay";
        Action.clickView(solo, viewId);
        assertTrue("Cvs pay button is not selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

        Action.clickView(solo, viewId);
        assertFalse("Cvs pay button is selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

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

        String viewId = "tb_cvs_pick";
        Action.clickView(solo, viewId);
        assertTrue("Cvs pay button is not selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

        Action.clickView(solo, viewId);
        assertFalse("Cvs pay button is selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

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

        String viewId = "tb_hasstock";
        Action.clickView(solo, viewId);
        assertTrue("Has stock button is not selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

        Action.clickView(solo, viewId);
        assertFalse("Has stock button is selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

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

        String viewId = "tb_hasimage";
        Action.clickView(solo, viewId);
        assertTrue("Has stock button is not selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

        Action.clickView(solo, viewId);
        assertFalse("Has stock button is selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

    }

    /**
     * 1937973:Unselected "Superior Shop".
     * @throws Exception if has error
     */
    public final void testUnselectedisSuperiorFunction() throws Exception {

        Action.enterToJacketAfterSearch(solo);

        // navigate to Filter screen
        Action.enterAdvancedSortPage(solo);

        String viewId = "tb_issuperior";
        Action.clickView(solo, viewId);

        // Action.closeSoftKeyBoard(solo);

        assertTrue("Issuperior button is not selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

        Action.clickView(solo, viewId);
        assertFalse("Issuperior button is selected.",
                ((ToggleButton) solo.getView(viewId)).isChecked());

    }

    /**
     * 1937976:Navigate to item page.
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
     * 1937981:Store evaluation on the right side of store name.
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
     * 1937983:Check to click the start icon without login in list view.
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
     * 1937984:Commodity joined successfully collection list in list view.
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
     * 1937985:Check browser mode icon display in list view.
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
     * 1937986:Check small picture icon in photo grid view.
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
     * 1937996:Check to click the start icon without login in photo grid view.
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
     * 1937997:Commodity joined successfully collection list in photo grid view.
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
     * 1937998:Check large picture icon in large photo view.
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
        Action.setListViewStyleAfterSearch(solo);
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
     * 1938006:Store evaluation on the right side of store name.
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

}
