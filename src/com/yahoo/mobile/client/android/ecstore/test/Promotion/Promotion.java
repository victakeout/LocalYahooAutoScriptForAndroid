/*
 * This is automated script about "Promotion".
 * You can run these test cases either on the emulator or on device.
 *
 * By Eclipse:
 * Right click the test project and select Run As --> Run As Android JUnit Test
 *
 * By Ant:
 * 1.Run "android update test-project -m [path to target application] -p
 * [path to the test folder]"  in command line.
 * 2."ant test"
 *
 * By using instrument command:
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client.
 * android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run TabBar:adb shell am instrument -e class com.yahoo.mobile.
 * client.android.ecstore.test.Promotion.Promotion
 *  -w com.yahoo.mobile.
 * client.android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 */
package com.yahoo.mobile.client.android.ecstore.test.Promotion;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
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
 */
public class Promotion extends ActivityInstrumentationTestCase2<Activity> {

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
    public Promotion() throws ClassNotFoundException {
        super((Class<Activity>) launcherActivityClass);
    }

    @Override
    protected final void setUp() throws Exception {

        /*
         * setUp() is run before a test case is started. This is where the solo
         * object is created.
         */

        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        Assert.testFirstLaunch(solo);
    }

    @Override
    public final void tearDown() throws Exception {

        /*
         * tearDown() is run after a test case has finished.
         * finishOpenedActivities() will finish all the activities that have
         * been opened during the test execution.
         */

        solo.finishOpenedActivities();
        super.tearDown();
    }

    /**
     * 1977505:Verify user can add goods to shopping cart in「Promotion」page
     * after user logout then login..
     *
     * @throws Exception
     *             if has error
     */
    public final void testAddProductToShoppingcartInPromotion()
            throws Exception {

        Account.judgementAccountLogin(solo);
        Action.removeShoppingCart(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_ONE));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.MAYBE_LIKE);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        View recommend = (View) solo.getView("listitem_recommended_image1", 0);
        solo.clickOnView(recommend);

        // Checks if the banner is show.
        View banner = (View) solo.getView("img_store_banner", 0);
        assertTrue("Not enter recommended page.", banner.isShown());

        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        View more = (View) solo.getView("menu_storeinfo");
        solo.clickOnView(more);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.SALES_PROMOTION);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue("Account has login.",
                solo.searchText(ValidationText.SALES_PROMOTION));
        //Log out account.
        Account.judgementAccountWithoutLogin(solo);

        //Log in account.
        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_ONE));
        solo.clickOnView(solo.getView("productitem_promotion_name",
                Action.VIEW_ID_ONE));

        solo.sleep(ValidationText.WAIT_TIME_LONGEST);
        TestHelper.swipeUp2(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        //Click shopping cart icon from class name.
        Action.clickElementsInWebviewByClassname(solo, "shoppingCart");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_THREE));
        TextView shoppingCart = (TextView) solo.getView(
                "ecshopping_cart_store_count", 0);
        assertTrue("Not add to shopping cart.",
                Integer.valueOf(shoppingCart.getText().toString()) >= 1);
    }
}
