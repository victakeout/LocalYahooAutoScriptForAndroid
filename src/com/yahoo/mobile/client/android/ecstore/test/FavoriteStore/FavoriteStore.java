/*
 * This is automated script about "FavoriteStore".
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
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client
 * .android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run FavoriteStore:adb shell am instrument -e class com.yahoo.
 * mobile.client.android.ecstore.test.FavoriteStore.FavoriteStore -w com.yahoo
 * .mobile.client.android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.FavoriteStore;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
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
public class FavoriteStore extends ActivityInstrumentationTestCase2<Activity> {

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
    public FavoriteStore() throws ClassNotFoundException {
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
     * 1959875:Verify user logout,then login again,the display of the favorite
     * store list.
     * @throws Exception if has error
     */
    public final void testFavoriteStoreLoginAndLogout() throws Exception {

        Account.judgementAccountWithoutLogin(solo);
        solo.clickOnView(solo.getView("tab_image", 1));
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.waitForView(solo.getView("no_result_text", 1));
        TextView noResult = (TextView)
                solo.getView("no_result_text", 1);
        assertEquals(ValidationText.WISHLIST_FAVORITE_STORE,
                noResult.getText()
                .toString());

        // Account login
        Account.judgementAccountLogin(solo);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", 1));
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        assertFalse("Text not found.", noResult.isShown());
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

    }

    /**
     * 1959886:Verify the page display when user is logged in and has no
     * favorite stores.
     * @throws Exception if has error
     */
    public final void testNoFavoriteStoreWhenLogin() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.removeFavoriteStore(solo);
    }

    /**
     * 1954571:Verify 18禁items displayed in favorite stores.
     * @throws Exception if has error
     */
    public final void testRestricted() throws Exception {

        Account.judgementAccountLogin(solo);

        // click on search button on home screen
        Action.clickSearchButtonOnScreen(solo);

        // element and test_data
        Action.addInitializeData(solo,
                0, ValidationText.INFLATABLE_DOLL);

        // press search button on keyboard
        solo.pressSoftKeyboardSearchButton();

        solo.waitForView(solo.getView("star_button"));
        View star = (View) solo.getView("star_button", 1);

        solo.clickOnView(star);
        if (solo.waitForText(ValidationText.
                HAS_REMOVED_COLLECTION, 1, ValidationText.WAIT_TIME_MIDDLE)) {
            solo.clickOnView(star);

        }

        // Wait for added to favorite list
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("tab_image",
               Action.VIEW_ID_FOUR));

        solo.clickOnView(solo.getView("profile_bt_favorite_text", 0));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickView(solo, "listitem_productlist_image");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View restricted = solo.getView("content");
        assertTrue("Restrict image not show!", restricted.isShown());

    }

    /**
     * 1959912:Verify there is an indicator to allow user login in.
     * @throws Exception if has error
     */
    public final void testFavoriteStoresWhenLogout() throws Exception {

        Account.judgementAccountWithoutLogin(solo);
        solo.clickOnView(solo.getView("tab_image", 1));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Button loginButton = (Button) solo.getView("option_button", 1);
        assertTrue("No login button displayed!", loginButton.isShown());

    }


    /**
     * 1959922:Verify user can access correct store page from recommendation.
     * @throws Exception if has error
     */
    public final void testAccessRecommendationStore() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", 1));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.MAYBE_LIKE);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        View recommend = (View) solo.getView(
                "listitem_recommended_image1", 0);
        solo.clickOnView(recommend);
        View banner = (View) solo.getView("img_store_banner", 0);
        assertTrue("Not enter to recommended page.", banner.isShown());

    }


    /**
     * 1959907:Verify the number of store items,collected number with my
     * favorite store.
     * @throws Exception if has error
     */
    public final void testNumberOfStoreAndCollectedItems()
            throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", 1));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.MAYBE_LIKE);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);

        TextView productCount = (TextView) solo
                .getView("listitem_recommended_item_count");
        boolean productCounts = productCount.getText().toString()
                .matches("[0-9]+");
        TextView collectedCount = (TextView) solo
            .getView("listitem_recommended_collected_persons");
        boolean collectedCounts = collectedCount.getText().toString()
                .matches("[0-9]+");

        assertTrue(
        "The number of store items,collected number"
                + " displayed incorrectly.",
                productCounts && collectedCounts);

    }


    /**
     * 1959896：Verify user can clicking promotion item link in store promotion
     * page.
     * @throws Exception if has error
     */
    public final void testEnterToPromotionItems()
            throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", 1));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.MAYBE_LIKE);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        View recommend = (View) solo.getView(
                "listitem_recommended_image1", 0);
        solo.clickOnView(recommend);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // Checks if the banner is show.
        View banner = (View) solo.getView("img_store_banner", 0);
        assertTrue("Not enter recommended page.", banner.isShown());

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        View more = (View) solo.getView("menu_storeinfo");
        solo.clickOnView(more);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.SALES_PROMOTION);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        View promotion = (View) solo.getView(
                "productitem_promotion_name", Action.VIEW_ID_ZERO);
        solo.clickOnView(promotion);

        solo.sleep(ValidationText.WAIT_TIME_LONGER);

        assertTrue("Promotion page cannot be opened.",
                solo.getView("webpage", Action.VIEW_ID_ZERO).isShown());

    }

    /**
     * 1959888:Verify Just added favorite store can be displayed on my favorite
     * stores tab.
     * @throws Exception if has error
     */
    public final void testAddFavoriteStore() throws Exception {

        Account.judgementAccountLogin(solo);
        // navigate to category screen
        Action.navigateToCategoryScreen(solo);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        // click on store tab
        Action.clickView(solo, "category_tab_primary_title", 1);

        solo.clickOnView(solo.getView("heart_button", 0));

        boolean alreadyAdd;

        // Get toast text.
        if (solo.waitForText(ValidationText.HAS_ADDED_COMMODITY)) {

            alreadyAdd = solo.waitForText(
                    ValidationText.HAS_ADDED_COMMODITY);
            assertTrue("Add failed.", alreadyAdd);

        } else {

            solo.sleep(ValidationText.WAIT_TIME_SHORT);
            solo.clickOnView(solo.getView("heart_button", 0));
            alreadyAdd = solo.waitForText(
                    ValidationText.HAS_ADDED_COMMODITY);
            assertTrue("Add failed.", alreadyAdd);

        }

        solo.clickOnView(solo.getView("tab_image", 1));
        View shop = (View) solo.getView(
                "listitem_favoritestore_image1", 0);
        assertTrue("Add to favorite store failed.", shop.isShown());
    }
    //2014-06-20

    /**
     * 1954565:Verify pull down to refresh function.
     * @throws Exception if has error
     */
    public final void testFavoriteStoreRefresh()
            throws Exception {

        solo.waitForActivity("ECStoreActivity",
                ValidationText.WAIT_TIME_SHORT);

        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_ONE));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TestHelper.swipeDown(solo, 20);
        assertTrue("Refresh failed", solo.waitForText(ValidationText.WAIT));

    }

    /**
     * 1954610:Verify pull down to refresh function in favorite store.
     * @throws Exception if has error
     */
    public final void testFavoriteStoreAllRefresh()
            throws Exception {
        Account.judgementAccountLogin(solo);
        solo.waitForActivity("ECStoreActivity",
                ValidationText.WAIT_TIME_SHORT);

        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_ONE));
        solo.waitForText(ValidationText.YOUR_FAVORITE, 1,
                ValidationText.WAIT_TIME_MIDDLE);
        TestHelper.swipeDown(solo, 20);

        //Checks if the pull refresh text is shown.
        assertTrue("Refresh failed", solo.waitForText(ValidationText.WAIT));

        solo.clickOnText(ValidationText.MAYBE_LIKE);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TestHelper.swipeDown(solo, 20);

        assertTrue("Refresh failed", solo.waitForText(ValidationText.WAIT));

    }

}
