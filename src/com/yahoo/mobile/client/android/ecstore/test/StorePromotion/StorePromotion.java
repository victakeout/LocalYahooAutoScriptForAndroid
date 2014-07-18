/*
 * This is automated script about "StorePromotion".
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
 * client.android.ecstore.test.StorePromotion.StorePromotion
 *  -w com.yahoo.mobile.
 * client.android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 */
package com.yahoo.mobile.client.android.ecstore.test.StorePromotion;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

/**
 * @author Administrator
 */
public class StorePromotion extends ActivityInstrumentationTestCase2<Activity> {

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
    public StorePromotion() throws ClassNotFoundException {
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
     * 1959928:Verify user view promotion item link.
     * @throws Exception
     *             if has error
     */
    public final void testViewPromotionItemLink() throws Exception {

        Action.clickSearchButtonOnScreen(solo);
        Action.searchAfterPutData(solo, 0, ValidationText.SALES_PROMOTION);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickInList(Action.VIEW_ID_ZERO);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.SALES_PROMOTION);
        TextView header = (TextView)
                solo.getView("product_item_promotion_header");
        assertTrue("This product contains more than one promotion link.",
                header.getText().toString().contains("1"));

    }

}

