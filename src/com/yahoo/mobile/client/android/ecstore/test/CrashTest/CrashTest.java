package com.yahoo.mobile.client.android.ecstore.test.CrashTest;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

public class CrashTest extends ActivityInstrumentationTestCase2<Activity> {

    /**
     * Declare application main activity.
     */
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.yahoo.mobile.client.android.ecstore.ui.ECSplashActivity";

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
    public CrashTest() throws ClassNotFoundException {
        super((Class<Activity>) launcherActivityClass);
    }

    @Override
    protected final void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());

    }

    @Override
    public final void tearDown() throws Exception {

        solo.finishOpenedActivities();
        super.tearDown();
    }

 
    public final void testCancelFunctionInOptionsLayers() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers1() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers2() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers3() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers4() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers5() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers6() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers7() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers8() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers9() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers10() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers11() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers12() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers13() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers14() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers15() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers16() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers17() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers18() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers19() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers20() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers21() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers22() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers23() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers24() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers25() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers26() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers27() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers28() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers29() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers30() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers31() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers32() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers33() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers34() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers35() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers36() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers37() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers38() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers39() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers40() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers41() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers42() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers43() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers44() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers45() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers46() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers47() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers48() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers49() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers50() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers51() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers52() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers53() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers54() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers55() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers56() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers57() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers58() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers59() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers60() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers61() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers62() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers63() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers64() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers65() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers66() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers67() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers68() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers69() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers70() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers71() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers72() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers73() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers74() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers75() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers76() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers77() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers78() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers79() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
    public final void testCancelFunctionInOptionsLayers80() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.C);
        solo.sleep(ValidationText.WAIT_TIME_LONG);

        // Advanced button view.
        View advanced = (View) solo.getView("menu_filter");
        solo.clickOnView(advanced);

        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Get seek bar view.
        View seekBar = (View) solo.getView("seekbar");
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("Options layers is show", seekBar.isShown());

    }
}
