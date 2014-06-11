/*
 * This is automated script about "ShoppingCart".
 * 
 * You can run these test cases either on the emulator or on device. 
 * By Eclipse:
 * Right click the test project and select Run As --> Run As Android JUnit Test
 * By Ant:
 * 1.Run "android update test-project -m [path to target application] -p [path to the test folder]"  in command line .
 * 2."ant test"
 * By using instrument command:
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client.android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run ShoppingCart:adb shell am instrument -e class com.yahoo.mobile.client.android.ecstore.test.ShoppingCart.ShoppingCart -w com.yahoo.mobile.client.android.ecstore.test/android.test.InstrumentationTestRunner
 * 
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 * 
 */

package com.yahoo.mobile.client.android.ecstore.test.ShoppingCart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.robotium.solo.WebElement;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
public class ShoppingCart extends ActivityInstrumentationTestCase2<Activity> {
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.yahoo.mobile.client.android.ecstore.ui.ECSplashActivity";
    private static Class<?> launcherActivityClass;
    private Solo solo;
    static {

        try {
            launcherActivityClass = Class
                    .forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @SuppressWarnings("unchecked")
    public ShoppingCart() throws ClassNotFoundException {
        super((Class<Activity>) launcherActivityClass);
    }

    @Override
    protected void setUp() throws Exception {
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
     * 1959883:Verify the number of bottom bubble displayed on shopping cart
     * after deleted all products.
     * @throws Exception if has error
     */
    public final void testNumberBubbleDisplayAfterDelete() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        Action.addToShoppingCart(solo);
        Action.removeShoppingCart(solo);

    }


    /**
     * 1959911:Verify shopping cart & next buy.
     * @throws Exception if has error
     */
    public final void testShoppingCartNextBuy() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.removeShoppingCart(solo);
        Action.enterToItemPage(solo);
        Action.addToShoppingCart(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        Action.clickElementsInWebviewByText(solo, "goNextBuy updateItemClick");

        // Search "Confirm"button on alert window.
        Action.clickElementsInWebviewByText(solo, "confirm");
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Tap "Next Buy" button on web view.
        Action.clickElementsInWebviewByText(solo, ValidationText.NEXT_BUY);
        boolean expected = false;
        for (WebElement webs : solo.getCurrentWebElements()) {
            Log.i("number", webs.getClassName().toString());
            if (webs.getClassName().toString().equals("price")) {
                expected = true;
            }

        }
        assertTrue("NextBuy page display incorrect.", expected);
    }


    /**
     * 1959908:Verify numbers under shopping$next buy.
     * @throws Exception if has error
     */
    public final void testShoppingCartAndNextBuyNumber() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.removeShoppingCart(solo);

        for (int i = 0; i < Action.VIEW_ID_THREE; i++) {
            solo.scrollToTop();
            Action.enterToItemPage(solo);
            Action.addToShoppingCart(solo);
        }

        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        Action.clickElementsInWebviewByClassname(solo,
                "goNextBuy updateItemClick");

        solo.sleep(ValidationText.WAIT_TIME_LONG);
        // Search "Confirm"button on alert window.
        Action.clickElementsInWebviewByText(solo, ValidationText.OK);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        TextView shoppingCart = (TextView) solo.getView(
                "ecshopping_cart_store_count", 0);
        TextView nextBuy = (TextView) solo.getView(
                "ecshopping_cart_store_count", 1);

        assertTrue(
                "Total number displayed incorrect",
                Integer.valueOf(shoppingCart.getText().toString())
                        + Integer.valueOf(nextBuy.getText().toString())
                        == Action.VIEW_ID_THREE);
    }


    /**
     * 1977500:Verify the page whether refresh OK.
     * @throws Exception if has error
     */
    public final void testRefreshWhenBack() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickText(solo, ValidationText.ALL_CATEGORIES);
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        Action.clickStarIconNote(solo);

        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        Action.clickElementsInWebviewByClassname(solo, "pimg");
        solo.goBack();
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        View webpage = (View) solo.getView("webpage", 0);
        assertTrue("This page incorrect.", webpage.isShown());

    }

    /**
     * 1977496:Verify check out.
     * @throws Exception if has error
     */
    public final void testCheckout() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        Action.addToShoppingCart(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        TestHelper.swipeUp(solo, 1);
        Action.clickElementsInWebviewByText(solo, ValidationText.WANT_CHECKOUT);
        Action.searchTextOnWebview(solo, ValidationText.BUY_INFO);

    }


    /**
     * 1959885：Verify shopping cart details info.
     * @throws Exception if has error
     */
    public final void testShoppingcartDetail() throws Exception {

        Account.judgementAccountLogin(solo);

        // Action.removeShoppingCart(solo);
        Action.enterToItemPage(solo);

        Action.addToShoppingCart(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        Action.clickElementsInWebviewByClassname(solo, "updateItemChange");

        // Action.searchTextOnWebview(solo, "1");
        CheckedTextView number = (CheckedTextView) solo.getView("text1", 0);
        assertTrue("", number.isChecked());

    }

     /**
     * 1959903：Verify user can view next buy items and view shopping cart items.
     * @throws Exception if has error
     */
    public final void testViewNextbuyAndShoppingCartItem() throws Exception {

        Account.judgementAccountLogin(solo);
        for (int i = 0; i < Action.VIEW_ID_THREE; i++) {
            solo.scrollToTop();
            Action.enterToItemPage(solo);
            Action.addToShoppingCart(solo);
        }
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        Action.clickElementsInWebviewByText(solo, "goNextBuy updateItemClick");

        // Search "Confirm"button on alert window.
        Action.clickElementsInWebviewByText(solo, "confirm");
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

    }

    /**
     * 1977534:verify delete function.
     * @throws Exception if has error
     */
    public final void testVerifyDeleteFunction() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.removeShoppingCart(solo);

    }
}
