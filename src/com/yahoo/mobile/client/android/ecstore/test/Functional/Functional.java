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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Switch;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

/**
 * @author Administrator
 *
 */
@SuppressLint("NewApi")
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
     * 1977448:[notification]turn on/off marketing notifications.
     * @throws Exception if has error
     */
    public final void testMarktingNotificationTurnOnOff() throws Exception {

        Account.judgementAccountLogin(solo);
        View iv = solo.getView("home");
        solo.clickOnView(iv);
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

    }

}
