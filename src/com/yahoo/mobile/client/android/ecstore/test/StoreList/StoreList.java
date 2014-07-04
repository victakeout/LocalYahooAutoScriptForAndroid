/*
 * This is automated script about "StoreList".
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
 * client.android.ecstore.test.StoreList.StoreList -w com.yahoo.mobile.
 * client.android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 */
package com.yahoo.mobile.client.android.ecstore.test.StoreList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

/**
 * @author Administrator
 */
@SuppressLint("NewApi")
public class StoreList extends ActivityInstrumentationTestCase2<Activity> {

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
    public StoreList() throws ClassNotFoundException {
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
     * 1977503:Verify escape character shouldn’t show in store list.
     *
     * @throws Exception
     *             if has error
     */
    public final void testEscapeCharacterShouldnotShow() throws Exception {

        Action.clickSearchButtonOnScreen(solo);
        Action.searchAfterPutData(solo, 0, ValidationText.S);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.SHOP);

        //
        TextView shopOne = (TextView) solo.getView(
                "listitem_storelist_store_name", Action.VIEW_ID_ZERO);
        String one = shopOne.getText().toString().trim();
        Log.i("number", one);
        TextView shopTwo = (TextView) solo.getView(
                "listitem_storelist_store_name", Action.VIEW_ID_ONE);
        String two = shopTwo.getText().toString().trim();
        Log.i("number", two);
        TextView shopThree = (TextView) solo.getView(
                "listitem_storelist_store_name", Action.VIEW_ID_TWO);
        String three = shopThree.getText().toString().trim();
        Log.i("number", three);

        assertFalse(
                "Store name include some escape character.",
                one.contains(ValidationText.ESCAPE_CHARACTER)
                        && two.contains(ValidationText.ESCAPE_CHARACTER)
                        && three.contains(ValidationText.ESCAPE_CHARACTER));
    }

    /**
     * 1977497:Verify store info after search.
     * @throws Exception
     *             if has error
     */
    public final void testStoreinfoAfterSearch() throws Exception {

        Action.clickSearchButtonOnScreen(solo);
        Action.searchAfterPutData(solo, 0, ValidationText.S);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.SHOP);


        TextView productNumber = (TextView) solo.getView(
                "listitem_storelist_store_item_count");

        TextView productNumbers = (TextView) solo.getView(
                "listitem_storelist_store_item_count", Action.VIEW_ID_ONE);
        String one = productNumber.getText().toString().trim();
        String two = productNumbers.getText().toString().trim();
        Log.i("number", one);
        Log.i("number", two);

        try {
           solo.searchText(ValidationText.ZERO_PRODUCT);
           solo.clickOnText(ValidationText.ZERO_PRODUCT);

        } catch (AssertionError e) {
            TestHelper.swipeUp2(solo, 1);
            solo.clickOnText(ValidationText.ZERO_PRODUCT);
        }
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.CATEGORIES);
        solo.goBack();
        assertTrue("Zero product not found.",
                solo.searchText(ValidationText.ZERO_PRODUCT));
    }
}
