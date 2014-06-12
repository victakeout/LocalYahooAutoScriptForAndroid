/*
 * This is automated script about "Options".
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
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client.
 * android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run Options:adb shell am instrument -e class com.yahoo.mobile.
 * client.android.ecstore.test.Options.Options -w com.yahoo.mobile.client.
 * android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */
package com.yahoo.mobile.client.android.ecstore.test.Options;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

/**
 * @author Administrator
 *
 */
@SuppressLint("NewApi")
public class Options extends ActivityInstrumentationTestCase2<Activity> {

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
    public Options() throws ClassNotFoundException {
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
     * 1959919:Verify 0 result function on leaf-category.
     * @throws Exception if has error
     */
    public final void testZeroResultDisplayed() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        Action.clickText(solo, ValidationText.HOME_BEDDING_FURNITURE);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        Action.clickText(solo, ValidationText.HANSHEN_HOME_LIFE);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Scroll to 10DAYS Science and Technology Museum of sleep
        android.widget.ListView listView1 = (android.widget.ListView) solo
                .getView(android.widget.ListView.class, 1);
        solo.scrollListToLine(listView1, 13);

        Action.clickText(solo, ValidationText.Hanshen_Ten_Days);
        Action.clickText(solo, ValidationText.CATEGORIES);
        Action.clickText(solo, ValidationText.Hanshen_Memory_Mattress);

        // Get the result page
        TextView zeroResult = (TextView) solo
                .getView("category_tab_secondary_title");
        assertTrue("There are some products displayed.", zeroResult.getText()
                .toString().trim().equals(ValidationText.ZERO_RESULT));

        solo.clickOnView(solo.getView("menu_filter"));
        assertTrue("Advanced page not pop up!",
                solo.searchText(ValidationText.SORT));

    }

}
