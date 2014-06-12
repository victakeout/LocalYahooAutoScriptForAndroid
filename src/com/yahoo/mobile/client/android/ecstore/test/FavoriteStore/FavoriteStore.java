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
                ValidationText.WAIT_TIME_MIDDLE));
        solo.clickOnView(solo.getView("profile_bt_favorite_text", 0));
        Action.clickView(solo, "listitem_productlist_image");
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

        // Checks if the banner is show.
        View banner = (View) solo.getView("img_store_banner", 0);
        assertTrue("Not enter recommended page.", banner.isShown());

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View option = (View) solo.getView("imageButton", 2);
        solo.clickOnView(option);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        TextView promotion = (TextView) solo.getView(
                "listitem_productlist_title", 1);
        solo.clickOnView(promotion);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        TestHelper.swipeUp(solo, 1);
        TextView promotionContent = (TextView) solo
                .getView("listitem_btn_desc");
        solo.clickOnView(promotionContent);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        TextView link = (TextView) solo.getView(
                "productitem_promotion_name");
        assertTrue("Click promotion item link failed. ",
                link.isShown());

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
}
