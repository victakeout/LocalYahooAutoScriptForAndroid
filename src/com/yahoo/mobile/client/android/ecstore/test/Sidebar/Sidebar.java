/*
 * This is automated script about "Sidebar".
 *
 * You can run these test cases either on the emulator or on device.
 *
 * By Eclipse:
 * Right click the test project and select Run As --> Run As Android JUnit Test
 *
 * By Ant:
 * 1.Run "android update test-project -m [path to target application]
 * -p [path to the test folder]"  in command line .
 * 2."ant test"
 *
 * By using instrument command:
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client
 * .android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run Sidebar:adb shell am instrument -e class com.yahoo.mobile.client
 * .android.ecstore.test.Sidebar.Sidebar -w com.yahoo.mobile.client.android
 * .ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.Sidebar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
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
@SuppressLint("NewApi")
public class Sidebar extends ActivityInstrumentationTestCase2<Activity> {

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
    public Sidebar() throws ClassNotFoundException {
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
     *  1959892:Verify user can edit category preferences.
     * @throws Exception  if has error
     */
    public final void testEditCategorypreferences() throws Exception {

        Account.judgementAccountLogin(solo);
        // click on up icon
        Action.clickHomeButtonOnScreen(solo);

        solo.clickOnText(ValidationText.EDIT_FAVORITE_CATEGORY);

        // Get the grid view count.
        GridView lv = (GridView) solo.getView("category_editor_grid");
        assertTrue("Not enter to edit category screen. ", lv.isShown());
        Log.i("number", String.valueOf(lv.getCount()));

        for (int i = 0; i < lv.getCount(); i++) {
            View category = (View) solo.getView("category_editor_grid_button",
                    i);
            solo.clickOnView(category);
            assertTrue("Category item is not selected.", category.isPressed());
        }
    }

    /**
     * 1977532:Verify settings screen.
     * @throws Exception  if has error
     */
    public final void testSettingsButton() throws Exception {

        Account.judgementAccountLogin(solo);
        // click on up icon
        Action.clickHomeButtonOnScreen(solo);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        Action.clickText(solo, ValidationText.SETTING);

        // Recent browse text.
        TextView recent = (TextView) solo.getView(
                "title", Action.VIEW_ID_THREE);
        assertTrue("Cannot fount recent browse text.", recent.getText()
                .toString().trim().equals(ValidationText.RECENT_BROWSE));

        // Get the toggle button status.
        Switch browseHistory = (
                Switch) solo.getView("switchWidget", 1);
        assertTrue("Notification switch is off", browseHistory.isChecked());

        // Disable the toggle button and go to browse product.
        solo.clickOnView(browseHistory);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.CLEAN_BROWSE_RECORD);
        Action.clickText(solo, ValidationText.OK);
        solo.goBack();
        Action.enterToItemPage(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.RECENT_BROWSE);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView noResult = (
                TextView) solo.getView("no_result_text", 1);
        assertTrue("Exist some browse record displayed", noResult.isShown());
        solo.goBack();

        // Search product.
        solo.goBack();
        Action.clickSearchButtonOnScreen(solo);
        Action.searchAfterPutData(solo, 0, "a");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.goBack();

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickHomeButtonOnScreen(solo);
        Action.clickText(solo, ValidationText.SETTING);
        Switch browseHistorys = (Switch) solo.getView("switchWidget", 1);
        solo.clickOnView(browseHistorys);
        Action.clickText(solo, ValidationText.CLEAR_SEARCH_HISTORY);
        Action.clickText(solo, ValidationText.OK);
        solo.goBack();
        Action.clickSearchButtonOnScreen(solo);

        boolean icon = false;
        try {
            View plugs = (View) solo.getView("search_fill_up", 1);
            assertFalse("Search history exist.", plugs.isShown());
        } catch (AssertionError e) {
            icon = true;
            assertTrue("Search history exist.", icon);
        }

    }
}
