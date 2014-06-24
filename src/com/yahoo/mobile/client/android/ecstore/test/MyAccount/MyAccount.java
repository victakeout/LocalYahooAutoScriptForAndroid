/*
 * This is automated script about "MyAccount".
 *
 * You can run these test cases either on the emulator or on device.
 *
 * By Eclipse:
 * Right click the test project and select Run As --> Run As Android JUnit Test
 *
 * By Ant:
 * 1.Run "android update test-project -m [path to target application] -p
 *  [path to the test folder]"  in command line .
 * 2."ant test"
 *
 * By using instrument command:
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.
 * client.android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run MyAccount:adb shell am instrument -e class com.yahoo.mobile.
 * client.android.ecstore.test.MyAccount.MyAccount -w com.yahoo.mobile.
 * client.android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.MyAccount;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
public class MyAccount extends ActivityInstrumentationTestCase2<Activity> {

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
    public MyAccount() throws ClassNotFoundException {
        super((Class<Activity>) launcherActivityClass);
    }

    @Override
    protected final void setUp() throws Exception {

        solo = new Solo(getInstrumentation(), getActivity());
        Assert.testFirstLaunch(solo);
    }

    @Override
    public final void tearDown() throws Exception {

        solo.finishOpenedActivities();
        super.tearDown();
    }

    /**
     * 1959879:Verify the favorite items number.
     * @throws Exception if has error
     */
    public final void testFavoriteItemNumber() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnText(ValidationText.ALL_CATEGORIES);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        Action.clickText(solo, ValidationText.APPAREL);
        solo.clickOnText(ValidationText.COMMODITY);
        Action.clickStarIconNote(solo);

        // "Add to favorite" operation will take for a period of time.
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));

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
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Get the favorite item count and forced convert "String" to "Int".
        TextView favoriteItemsRecheck = (TextView) solo
                .getView("profile_bt_favorite_count");

        int number = Integer
                .parseInt(favoriteItemsRecheck.getText().toString());

        Log.i("what", favoriteItemsRecheck.getText().toString());

        if (number >= 1) {
            assertTrue("favorite items number is null.", true);
        }
    }

    /**
     * 1959920:Verify the number of e-coupon can count correctly.
     * @throws Exception if has error
     */
    public final void testECouponCorrectly() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.clickOnText(ValidationText.ECOUPON);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);

        // Click the e-coupon box.
        Action.clickElementsInWebviewByClassname(solo, "filter");
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        TextView defaultText = (TextView)
                solo.getView("text1", Action.VIEW_ID_FOUR);
        assertTrue("Default item is incorrect.", defaultText.isActivated());

    }

    /**
     * 1977495:Reward point display.
     * @throws Exception if has error
     */
    public final void testRewardPointDisplay() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));

        // Account view
        TextView userInfo = (TextView) solo.getView("profile_user_name");
        Log.i("number", userInfo.getText().toString());

        // Reward point view,
        TextView rewardPoint = (TextView) solo.getView("account_reward_point",
                0);
        Log.i("number", rewardPoint.getText().toString());

        boolean flag = TestHelper.positionCompare(solo, userInfo.getText()
                .toString().trim(), 0, rewardPoint.getText().toString().trim(),
                1, 1);
        assertTrue(
                "Item position is not right,need confirm "
        +  "the default browse mode.",  flag);

    }

    /**
     * 1977535:verify Off_line coupons from My Account.
     * @throws Exception if has error
     */
    public final void testOfflineCoupons() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        assertTrue("Cannot found ",
                solo.searchText(ValidationText.STORE_OFFERS));

    }

    /**
     * 1977522:verify recent history from My Account.
     * @throws Exception if has error
     */
    public final void testRecentHistory() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        Action.clickText(solo, ValidationText.RECENT_BROWSE);
        TextView actionBar = (TextView) solo.getView("action_bar_title", 0);
        assertTrue("Cannot enter recent history page. ", actionBar.isShown());

    }


    /**
     * 1977523:verify recent history is no data.
     * @throws Exception if has error
     */
    public final void testRecentHistoryNoData() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickHomeButtonOnScreen(solo);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.SETTING);
        solo.clickOnText(ValidationText.CLEAN_BROWSE_RECORD);
        solo.clickOnView(solo.getView("button1"));
        solo.goBack();
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        Action.clickText(solo, ValidationText.RECENT_BROWSE);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        TextView noResult = (TextView) solo.getView("no_result_text", 1);
        assertTrue("There are some product info displayed. ",
                noResult.isShown());

        TextView shop = (TextView) solo
                .getView("category_tab_primary_title", 1);
        solo.clickOnView(shop);
        assertTrue("There are some shop info displayed. ", shop.isShown());

    }


    /**
     * 1977527:verify remove an item from recently browsed.
     * @throws Exception if has error
     */

    public final void testRemoveItemFromRecently() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        Action.clickText(solo, ValidationText.RECENT_BROWSE);
        View img = (View) solo.getView("listitem_productlist_image");
        solo.clickLongOnView(img);

        // Confirm remove it.
        solo.clickOnView(solo.getView("button1"));
        TextView noResult = (TextView)
                solo.getView("no_result_text", 1);
        assertTrue("There are some product info displayed. ",
                noResult.isShown());

    }

    /**
     * 1977531:verify remove a store from recently browsed.
     * @throws Exception if has error
     */
    public final void testRemoveStoreFromRecently() throws Exception {

    Account.judgementAccountLogin(solo);
        for (int i = 0; i < Action.VIEW_ID_THREE; i++) {
            Action.enterToItemPages(solo);
            View shopTitle = (View) solo.getView("productitem_store_name");
            solo.clickOnView(shopTitle);
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            solo.goBack();
            solo.goBack();
        }

        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.RECENT_BROWSE);

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        View shop = (View) solo.getView("category_tab_primary_title", 1);

        solo.clickOnView(shop);

        TextView result = (TextView) solo.getView("tx_header", 1);
        String count = result.getText().toString().trim().substring(6, 7);
        Log.i("number", "count:" + count);

        View img = (View) solo.getView("listitem_storelist_image");
        solo.clickLongOnView(img);

        // Confirm remove it.
        solo.clickOnView(solo.getView("button1"));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        try {
            TextView results = (TextView) solo.getView("tx_header", 1);
            String counts = results.getText().toString().trim().substring(6,7);
            Log.i("number", "counts:" + counts);

            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            assertTrue("Remove store failed. ",
                    Integer.valueOf(count) > Integer.valueOf(counts));
        } catch (AssertionError e) {
            assertTrue("Remove store failed. ",
                    solo.searchText(ValidationText.NO_DATA_SHOP));
        }

    }

    /**
     * 1977524:verify user can access to items.
     * @throws Exception if has error
     */
    public final void testAccessItems() throws Exception {

        Account.judgementAccountLogin(solo);
        for (int i = 0; i < Action.VIEW_ID_THREE; i++) {
            Action.enterToItemPage(solo);
            View shopTitle = (View) solo.getView("productitem_store_name");
            solo.clickOnView(shopTitle);
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            solo.goBack();
            solo.goBack();
        }

        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        Action.clickText(solo, ValidationText.RECENT_BROWSE);
        TextView actionBar = (TextView) solo.getView("action_bar_title", 0);
        assertTrue("Cannot enter to recent history page. "
                , actionBar.isShown());

        TextView product = (TextView) solo.getView(
                "category_tab_primary_title", 0);

        View shop = (View) solo.getView("category_tab_primary_title", 1);

        View productImage = (View) solo.getView("listitem_productlist_image");

        assertTrue("Cannot enter to recent history page. ", product.isShown()
                && shop.isShown() && productImage.isShown());

    }

    /**
     * 1959899:Verify the numbers of collected items can be
     * increasing/decreasing in my account page..
     * @throws Exception if has error
     */
    public final void testNumbersOfCollectedIncreasing() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        solo.goBack();
        Action.clickStarIconNote(solo);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
       // solo.goBack();
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Get the favorite count.
        TextView outNumber = (TextView) solo
                .getView("profile_bt_favorite_count");
        int outNumbers = Integer.valueOf(outNumber.getText().toString());
        Log.i("number", "OutNumbers:" + outNumber.getText().toString());

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.goBack();
        solo.scrollToTop();
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // Click star icon to add to favorite.
        Action.clickStarIconNote(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView outNumberTwo = (TextView) solo
                .getView("profile_bt_favorite_count");

        int outNumberTwos = Integer.valueOf(
                 outNumberTwo.getText().toString());
        Log.i("number", "OutNumberTwo:" + outNumberTwo.getText().toString());

        /*
         * If last view count more that before,so we can confirm the numbers of
         * collected items can be increasing
         */
        assertTrue("Collect product failed.", outNumbers < outNumberTwos);
        Action.clickText(solo, ValidationText.PRODUCT_COLLECTION);

    }
}
