/*
 * This is automated script about "DiscoveryStream".
 * You can run these test cases either on the emulator or on device.
 *
 * By Eclipse:
 * Right click the test project and select Run As --> Run As Android JUnit Test
 *
 * By Ant:
 * 1.Run "android update test-project -m [path to target application] -p
 * [path to the test folder]"  in command line .
 * 2."ant test"
 *
 * By using instrument command:
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client.
 * android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run DiscoveryStream:adb shell am instrument -e class com.yahoo.mobile.
 * client.android.ecstore.test.DiscoveryStream.DiscoveryStream -w com.yahoo.
 * mobile.client.android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.DiscoveryStream;


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
 *
 */

public class DiscoveryStream extends ActivityInstrumentationTestCase2<Activity>
{

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
    public DiscoveryStream() throws ClassNotFoundException {
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
     * 1954564:Verify pull down to refresh.
     * @throws Exception if has error
     */
    public final void testPullDownToRefresh() throws Exception {

        solo.waitForActivity("ECStoreActivity",
                ValidationText.WAIT_TIME_SHORT);
        solo.waitForText(ValidationText.NEWS, 1,
                ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeDown(solo, Action.VIEW_ID_TEN);

        //Checks if the pull refresh text is shown.
        TextView pullRefresh = (TextView)
                solo.getView("pull_to_refresh_text");
        assertTrue("Refresh failed", pullRefresh.isShown());

    }
}
