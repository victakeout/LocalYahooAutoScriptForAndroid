/*
 * This is automated script about "Smoke".
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
 * Just run Sidebar:adb shell am instrument -e class com.yahoo.mobile.client
 * .android.ecstore.test.Smoke.Smoke -w com.yahoo.mobile.client.android
 * .ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.Smoke;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

public class Smoke extends ActivityInstrumentationTestCase2<Activity> {

    /**
     * Declare application main activity.
     */
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.yahoo.mobile.client.android.ecstore.ui.ECSplashActivity";

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
    public Smoke() throws ClassNotFoundException {
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
     * 1952835:[Personalization] Verify user can use personalization.
     * 
     * @throws Exception
     *             if has error
     */
    public final void testUsePersonalization() throws Exception {

        Account.judgementAccountLogin(solo);

        /* Check from side bar to edit */

        // click on up icon
        Action.clickHomeButtonOnScreen(solo);
        solo.clickOnText(ValidationText.EDIT_FAVORITE_CATEGORY);
        // Get the grid view count.
        GridView lv = (GridView) solo.getView("category_editor_grid");
        Log.i("number", String.valueOf(lv.getCount()));

        for (int i = 0; i < lv.getCount(); i++) {
            View category = (View) solo.getView("category_editor_grid_button",
                    i);
            solo.clickOnView(category);
            assertTrue("Category item is not selected.", category.isPressed());
        }

        solo.clickOnText(ValidationText.TO_LATEST_STATUS);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView titleBar = (TextView) solo.getView("action_bar_title", 0);
        Log.i("number", titleBar.getText().toString().trim());
        assertTrue("Action bar title is wrong.", titleBar.getText().toString()
                .trim().equals(ValidationText.NEWS));

        /* Check from my account to edit */
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.scrollToBottom();
        solo.clickOnView(solo.getView("profile_bt_edit_favorite_categories"));

        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get the grid view count.
        GridView lvs = (GridView) solo.getView("category_editor_grid");
        Log.i("number", String.valueOf(lvs.getCount()));

        for (int i = 0; i < lvs.getCount(); i++) {
            View category = (View) solo.getView("category_editor_grid_button",
                    i);
            solo.clickOnView(category);
            assertTrue("Category item is not selected.", category.isPressed());
        }

        solo.clickOnText(ValidationText.TO_LATEST_STATUS);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView titleBars = (TextView) solo.getView("action_bar_title");
        assertTrue("Action bar title is wrong.", titleBars.getText().toString()
                .trim().equals(ValidationText.NEWS));
    }

    /**
     * 1959535:[Items listings]Verify Cancel button function in options layers..
     * 
     * @throws Exception
     *             if has error
     */
    public final void testCancelFunctionInOptionsLayers() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }

    /**
     * 1952836:[Web Views]verify user can use web views.
     * 
     * @throws Exception
     *             if has error
     */
    public final void testUserCanUseWebviews() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        Action.addToShoppingCart(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        try {
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        }
        TestHelper.swipeUp(solo, 2);
        Action.clickElementsInWebviewByText(solo, ValidationText.WANT_CHECKOUT);
        Action.searchTextOnWebview(solo, ValidationText.BUY_INFO);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.clickOnText(ValidationText.ORDER_INQUIRY);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        try {
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        }
        solo.goBack();
        solo.clickOnText(ValidationText.ECOUPON);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        try {
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        }
    }

    /**
     * 1959541:[Item page]Verify user can click "Buy now"button twice.
     * 
     * @throws Exception
     *             if has error
     */
    public final void testClickBuyButtonTwice() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        TestHelper.swipeUp(solo, 2);
        solo.clickOnText(ValidationText.BUY_NOW);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.goBack();
        solo.clickOnText(ValidationText.BUY_NOW);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.goBack();
        solo.clickOnText(ValidationText.BUY_NOW);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        View radioButton = (View) solo.getView(
                "product_item_spec_item_selections", 0);

        solo.clickOnView(radioButton);
        solo.clickOnText(ValidationText.OK);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        try {
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        }

    }

    /**
     * 1959553:[Shopping cart]Verify Item link function in shopping cart details
     * page.
     * 
     * @throws Exception
     *             if has error
     */
    public final void testItemLinkFunctionInDetailPage() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.removeShoppingCart(solo);
        Action.enterToItemPage(solo);
        Action.addToShoppingCart(solo);
        solo.clickInList(1);
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.ADD_SHOPPING_CART);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View radioButton = (View) solo.getView(
                "product_item_spec_item_selections", 1);
        if (radioButton.isShown()) {

            solo.clickOnView(radioButton);
            solo.searchText(ValidationText.OK);
            solo.clickOnButton(ValidationText.OK);
            solo.waitForText(ValidationText.ALREADY_ADD_SHOPPING_CART, 1,
                    ValidationText.WAIT_TIME_MIDDLE);
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            View buddle = solo.getView("tab_badge", Action.VIEW_ID_THREE);
            junit.framework.Assert.assertTrue("No items in shopping cart.",
                    buddle.isShown());
        }
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);

        Action.clickElementsInWebviewByClassname(solo, "title");
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        solo.goBack();
        solo.sleep(ValidationText.WAIT_TIME_LONGER);

        Action.clickElementsInWebviewByClassname(solo,
                "goNextBuy updateItemClick");

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        // Search "Confirm"button on alert window.
        Action.clickElementsInWebviewByText(solo, ValidationText.OK);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        Action.clickElementsInWebviewByClassname(solo, "title");
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        View productName = (View) solo.getView("productitem_store_name");
        assertTrue("Not back to item page.", productName.isShown());
    }

    /**
     * 1959554:[Shopping cart]Verify user can access shopping cart details page
     * quickly.
     * 
     * @throws Exception
     *             if has error
     */
    public final void testAccessShoppingCartQuickly() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.removeShoppingCart(solo);
        Action.enterToItemPage(solo);
        Action.addToShoppingCart(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.goBack();
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        try {
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        }
    }

    /**
     * 1959555:[Shopping cart]Verify user can access shopping cart details page
     * repeatedly.
     * 
     * @throws Exception
     *             if has error
     */
    public final void testAccessShoppingCartRepeatedly() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.removeShoppingCart(solo);
        Action.enterToItemPage(solo);
        Action.addToShoppingCart(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.goBack();
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);

        solo.goBack();
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        try {
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            View webpage = (View) solo.getView("webpage", 0);
            assertTrue("This page incorrect.", webpage.isShown());
        }
    }

    /**
     * 1977445:[Store Listings]Verify user can see some basic information about
     * the Store in its store listings page.
     * 
     * @throws Exception
     *             if has error
     */
    public final void testSeeBasicInfoAboutStore() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_ONE));

        solo.clickOnText(ValidationText.MAYBE_LIKE);
        View recommend = (View) solo.getView("listitem_recommended_image1", 0);
        solo.clickOnView(recommend);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        // Checks if the banner is show.
        View banner = (View) solo.getView("img_store_banner", 0);
        assertTrue("Not enter recommended page.", banner.isShown());

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        View more = (View) solo.getView("menu_storeinfo");
        solo.clickOnView(more);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.SALES_PROMOTION);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue("Not enter sales page.",
                solo.searchText(ValidationText.SALES_PROMOTION));

        View iconBack = (View) solo.getView("home");
        solo.clickOnView(iconBack);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        View mores = (View) solo.getView("menu_storeinfo");
        solo.clickOnView(mores);

        // Star icon view.
        View storeinfo = (View) solo.getView("storeinfo_addfav");

        // Store rate
        TextView rate = (TextView) solo.getView("storeinfo_rate");

        // Store name
        TextView name = (TextView) solo.getView("storeinfo_name");
        assertTrue("Some view not display.",
                storeinfo.isShown() && rate.isShown() && name.isShown());
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.SHOPPING_TIPS);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // Get the shopping tips text view.
        TextView mustKnow = (TextView) solo.getView("text_must_know");
        assertTrue("Shopping Tips not display", mustKnow.isShown());

    }

    /**
     * 1952828:[Store Listings] Verify user can access the listings page.
     * 
     * @throws Exception
     *             if has error
     */
    public final void testAccessListingsPage() throws Exception {

        Action.clickSearchButtonOnScreen(solo);
        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");

        // Search result view.
        TextView searchResult = (TextView) solo.getView(
                "category_tab_secondary_title", Action.VIEW_ID_ONE);

        // Search result image view.
        View image = (View) solo.getView("listitem_productlist_image");

        assertTrue("Some views not show.",
                advanced.isShown() && searchResult.isShown() && image.isShown());

        // Item name
        TextView itemName = (TextView) solo
                .getView("listitem_productlist_title");
        String itemNames = itemName.getText().toString().trim();
        Log.i("number", "itemNames:" + itemNames);

        // Item price
        TextView storePrice = (TextView) solo
                .getView("listitem_productlist_price");
        String storePrices = storePrice.getText().toString().trim();

        // Store name
        TextView storeName = (TextView) solo
                .getView("listitem_productlist_store_name");
        String storeNames = storeName.getText().toString().trim();

        // Store rate
        TextView storeRate = (TextView) solo
                .getView("listitem_productlist_store_rating");
        String storeRates = storeRate.getText().toString().trim();

        // Favorite icon
        View fav = (View) solo.getView("star_button");
        assertTrue("Favorite icon does not show.", fav.isShown());

        solo.clickOnView(itemName);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Store name
        TextView storeNameDetail = (TextView) solo
                .getView("productitem_store_name");
        String storeNameDetails = storeNameDetail.getText().toString().trim();

        Log.i("number", "itemNames:" + itemNames);
        // Item name
        TextView itemNameDetail = (TextView) solo
                .getView("productitem_product_name");
        String itemNameDetails = itemNameDetail.getText().toString().trim();
        Log.i("number", "itemNameDetails:" + itemNameDetails);

        // Store rate
        TextView storeRateDetail = (TextView) solo
                .getView("productitem_store_rate");
        String storeRateDetails = storeRateDetail.getText().toString().trim();

        // Favorite icon
        View favor = (View) solo.getView("productitem_star_button");

        // Item price
        TextView storePriceDetail = (TextView) solo
                .getView("productitem_original_price");
        String storePriceDetails = storePriceDetail.getText().toString().trim();

        assertTrue(
                "Some elements are inconsistent.",
                itemNames.equals(itemNameDetails)
                        && storePrices.equals(storePriceDetails)
                        && storeNames.equals(storeNameDetails)
                        && storeRates.equals(storeRateDetails)
                        && favor.isShown());
    }

    /**
     * 1961795:[My account]Verify click "commodity Favorites" any goods are able
     * to enter Item page.
     *
     * @throws Exception
     *             if has error
     */
    public final void testAccessItemPageAfterClickAnyGoods() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.removeFavoriteItem(solo);
        solo.goBack();
        Action.enterToItemPage(solo);
        solo.goBack();
        for (int i = 0; i < 3 ; i++) {
            Action.clickStarIconNote(solo);
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
        }

        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.PRODUCT_COLLECTION);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        View img = (View) solo.getView("listitem_productlist_image");
        solo.clickLongOnView(img);

        // Confirm remove it.
        solo.clickOnView(solo.getView("button1"));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.goBack();
        solo.clickOnText(ValidationText.PRODUCT_COLLECTION);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        View imgs = (View) solo.getView("listitem_productlist_image");
        solo.clickOnView(imgs);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View star = (View) solo.getView("productitem_star_button",
                Action.VIEW_ID_ZERO);
        assertTrue("Cannot back to item page!", star.isShown());
    }
}
