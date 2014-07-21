/*
 * This is automated script about "Functional".
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
 * Just run Functional:adb shell am instrument -e class com.yahoo.mobile
 * .client.android.ecstore.test.Functional.Functional -w com.yahoo.mobile
 * .client.android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.Functional;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
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

public class Functional extends ActivityInstrumentationTestCase2<Activity> {

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
    public Functional() throws ClassNotFoundException {
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
     * 1977448:[notification]turn on/off marketing notifications.
     * @throws Exception if has error
     */
    public final void testMarktingNotificationTurnOnOff() throws Exception {

        Account.judgementAccountLogin(solo);
        View iv = solo.getView("home");
        solo.clickOnView(iv);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // clear history information and back
        solo.waitForText(ValidationText.SETTING, 1,
                ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.SETTING);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Switch notification = (Switch) solo.getView("switchWidget", 2);

        assertTrue("Notification switch is off",
                notification.isChecked());

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(notification);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Notification switch is on.",
                notification.isChecked());
        Switch notificationS = (Switch) solo.getView("switchWidget", 2);
        solo.clickOnView(notificationS);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue("Notification switch is off.",
                notificationS.isChecked());
    }

    /**
     * 1977449:[notification]list of notifications.
     * @throws Exception if has error
     */
    public final void testShowNotificationInList() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.clickOnText(ValidationText.NOTIFICATION);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        TextView notificationBar;
        try {
            notificationBar = (TextView) solo.getView("action_bar_title");
        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_LONG);
            notificationBar = (TextView) solo.getView("action_bar_title");
        }
        assertTrue("Notification is null.", notificationBar.isShown());

    }

    /**
     * 1977478:[Barcode]Discovery Stream root view.
     * @throws Exception if has error
     */

    public final void testDiscoveryStreamFromDiscoveryStream()
            throws Exception {
        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);
        ImageView barcode = (ImageView) solo.getView("search_barcode_scan");
        solo.clickOnView(barcode);

        View finder = (View) solo.getView("viewfinder_view");
        assertTrue("Viewfinder is not show.", finder.isShown());

    }

    /**
     * 1977479:[Barcode]Discovery Stream root view.
     * @throws Exception if has error
     */
    public final void testDiscoveryStreamFromFavoriteStore()
            throws Exception {
        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", 1));
        Action.clickSearchButtonOnScreen(solo);
        ImageView barcode = (ImageView) solo.getView("search_barcode_scan");
        solo.clickOnView(barcode);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View finder = (View) solo.getView("viewfinder_view");
        assertTrue("Viewfinder is not show.", finder.isShown());
    }


    /**
     * 1977480:[Barcode]Discovery Stream root view.
     * @throws Exception if has error
     */
    public final void testDiscoveryStreamFromCategory()
            throws Exception {
        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", 2));
        Action.clickSearchButtonOnScreen(solo);
        ImageView barcode = (ImageView) solo.getView("search_barcode_scan");
        solo.clickOnView(barcode);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View finder = (View) solo.getView("viewfinder_view");
        assertTrue("Viewfinder is not show.", finder.isShown());
    }
}
