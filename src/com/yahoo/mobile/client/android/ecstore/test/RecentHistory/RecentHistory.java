/*
 * This is automated script about "RecentHistory".
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
 * Just run RecentHistory:adb shell am instrument -e class com.yahoo.mobile
 * .client.android.ecstore.test.RecentHistory.RecentHistory -w com.yahoo.mobile
 * .client.android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.RecentHistory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
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
 */
@SuppressLint("NewApi")
public class RecentHistory extends ActivityInstrumentationTestCase2<Activity> {
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
    public RecentHistory() throws ClassNotFoundException {
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
     * 1900011: Verify settings screen.
     * @throws Exception
     *             if has error
     */
    public final void testVerifySettingsScreen() throws Exception {

        // Go to main screen
        solo.waitForActivity("ECStoreActivity", ValidationText.WAIT_TIME_SHORT);
        solo.waitForText(ValidationText.NEWS, 1,
                ValidationText.WAIT_TIME_SHORT);
        junit.framework.Assert.assertTrue("Navigate to main screen failed.",
                solo.searchText(ValidationText.NEWS));

        // click "up" icon
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("home"));

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.waitForText(ValidationText.SETTING, 1,
                ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.SETTING);

        TextView recentBrowse = (TextView) solo.getView("title",
                Action.VIEW_ID_THREE);
        TextView browseRecord = (TextView) solo.getView("title",
                Action.VIEW_ID_FOUR);
        TextView cleanBrowseRecord = (TextView) solo.getView("title",
                Action.VIEW_ID_FIVE);
        Switch toggle = (Switch) solo.getView("switchWidget", 1);
        assertTrue(
                "Some search text not found.",
                recentBrowse.getText().toString()
                        .equals(ValidationText.RECENT_BROWSE)
                        && browseRecord.getText().toString()
                                .equals(ValidationText.BROWSE_RECORD)
                        && cleanBrowseRecord.getText().toString()
                                .equals(ValidationText.CLEAN_BROWSE_RECORD)
                        && toggle.isChecked());

    }

    /**
     * 1900004: Verify can browse recent items in「Product」tab .
     * @throws Exception
     *             if has error
     */
    public final void testVerifyCanBrowseRecentItems() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.APPAREL);
        Action.loopBrowse(solo);
        solo.goBack();
        Action.loopBrowsebeauty(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.clickOnText(ValidationText.RECENT_BROWSE);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        TextView headerNumber = (TextView) solo.getView("tx_header",
                Action.VIEW_ID_ZERO);
        int count = Integer.valueOf(headerNumber.getText().toString().trim()
                .substring(6, 8));
        Log.i("number", headerNumber.getText().toString().trim());

        // favorites icon.
        View favoritesIcon = (View) solo.getView("star_button",
                Action.VIEW_ID_ONE);

        // Product image.
        View productImage = (View) solo.getView("listitem_productlist_image",
                Action.VIEW_ID_ZERO);

        // Store name.
        TextView storeName = (TextView) solo.getView(
                "listitem_productlist_store_name", Action.VIEW_ID_ZERO);

        // Store rating.
        TextView storeRate = (TextView) solo.getView(
                "listitem_productlist_store_rating", Action.VIEW_ID_ONE);

        // Item name.'
        TextView itemName = (TextView) solo.getView(
                "listitem_productlist_title", Action.VIEW_ID_TWO);

        assertTrue(
                "Recent items number is less than 20.",
                count >= 20 && favoritesIcon.isShown()
                        && productImage.isShown() && storeName.isShown()
                        && storeRate.isShown() && itemName.isShown());

    }
}
