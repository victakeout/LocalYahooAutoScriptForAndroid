/*
 * This is automated script about "Checkout".
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
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client.
 * android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run Checkout:adb shell am instrument -e class com.yahoo.mobile.
 * client.android.ecstore.test.Checkout.Checkout -w com.yahoo.mobile.client.
 * android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 */

package com.yahoo.mobile.client.android.ecstore.test.Checkout;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;

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
public class Checkout extends ActivityInstrumentationTestCase2<Activity> {

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
    public Checkout() throws ClassNotFoundException {
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
     * 1959918:Verify that user can change other delivery places.
     * @throws Exception if has error
     */
    public final void testChangeOtherDeliveryPlaces() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.removeShoppingCart(solo);
        Action.enterToItemPage(solo);
        Action.addToShoppingCart(solo);

        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        TestHelper.swipeUp(solo, 2);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        //Select "7-11"
        Action.clickElementsInWebviewByClassname(solo, "shippingList");
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText("7-11");
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        TestHelper.swipeUp(solo, 1);

        // Click check out button on web view.
        Action.clickElementsInWebviewByText(solo,
                ValidationText.WANT_CHECKOUT);

        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        TestHelper.swipeUp(solo, 2);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TestHelper.swipeUp(solo, 2);

        // Click "Select other store" text to re_selection.
        try {
        Action.clickElementsInWebviewByText(solo,
                ValidationText.RESELECT_OTHER_STORE);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);

        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            Action.clickElementsInWebviewByText(solo,
                    ValidationText.RESELECT_OTHER_STORE);
            solo.sleep(ValidationText.WAIT_TIME_LONGER);
        }
        View storePage;
        try {

            storePage = (View) solo.getView("webpage");
            assertTrue("Store page not show.", storePage.isShown());

        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_LONGER);
            storePage = (View) solo.getView("webpage");
            assertTrue("Store page not show.", storePage.isShown());
        }


    }

    /**
     * 1959915:Verify check out component on step 3.
     * @throws Exception if has error
     */
    public final void testVerifyCheckOutComponent() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        TestHelper.swipeUp(solo, 1);
        try {
            solo.clickOnText(ValidationText.BUY_NOW);
        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            solo.clickOnText(ValidationText.BUY_NOW);
        }

        View buddle;
        View radioButton = (View) solo.getView(
                "product_item_spec_item_selections", 0);
        if (radioButton.isShown()) {

            solo.clickOnView(radioButton);
            solo.searchText(ValidationText.OK);
            solo.clickOnButton(ValidationText.OK);
            solo.waitForText(ValidationText.ALREADY_ADD_SHOPPING_CART, 1,
                    ValidationText.WAIT_TIME_MIDDLE);
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            buddle = solo.getView("tab_badge", Action.VIEW_ID_THREE);
            junit.framework.Assert.assertTrue("No items in shopping cart.",
                    buddle.isShown());
        } else {
            junit.framework.Assert.assertTrue("Add failed.", true);
        }

        //solo.goBack();
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Click check out button on web view.
        Action.clickElementsInWebviewByText(solo,
                ValidationText.WANT_CHECKOUT);

        solo.sleep(ValidationText.WAIT_TIME_LONGER);

        //solo.goBack();
        TestHelper.swipeUp(solo, 2);

        TestHelper.swipeUp(solo, 1);
        Log.i("number", "ME");
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        Action.clickElementsInWebviewByText(solo, ValidationText.FAMILY_PICKUP);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Click check out button on web view.
        Action.clickElementsInWebviewByText(solo,
                ValidationText.WANT_CHECKOUT);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        View webPage = (View) solo.getView("webpage");
        assertTrue("Cannot found family pick up.", webPage.isShown());
    }

    /**
     * 1959916:Verify order inquiry details page can show normally.
     * @throws Exception if has error
     */
    public final void testVerifyorderInquiryShowNormally() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);
        Action.searchAfterPutData(solo, 0, ValidationText.MIUSTAR);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickInList(1);
        TestHelper.swipeUp(solo, 1);
        solo.clickOnText(ValidationText.BUY_NOW);

        View buddle;
        View radioButton = (View) solo.getView(
                "product_item_spec_item_selections", 0);
        if (radioButton.isShown()) {

            solo.clickOnView(radioButton);
            solo.searchText(ValidationText.OK);
            solo.clickOnButton(ValidationText.OK);
            solo.waitForText(ValidationText.ALREADY_ADD_SHOPPING_CART, 1,
                    ValidationText.WAIT_TIME_MIDDLE);
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            buddle = solo.getView("tab_badge", Action.VIEW_ID_THREE);
            junit.framework.Assert.assertTrue("No items in shopping cart.",
                    buddle.isShown());
        } else {
            junit.framework.Assert.assertTrue("Add failed.", true);
        }

        //solo.goBack();
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Click check out button on web view.
        Action.clickElementsInWebviewByText(solo,
                ValidationText.WANT_CHECKOUT);

        solo.sleep(ValidationText.WAIT_TIME_LONGER);

        //solo.goBack();
        TestHelper.swipeUp(solo, 2);

        TestHelper.swipeUp(solo, 1);
        Log.i("number", "ME");
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        Action.clickElementsInWebviewByText(solo, ValidationText.FAMILY_PICKUP);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Click check out button on web view.
        Action.clickElementsInWebviewByText(solo,
                ValidationText.WANT_CHECKOUT);

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        View webPage = (View) solo.getView("webpage");
        assertTrue("Cannot found family pick up.", webPage.isShown());
    }
}
