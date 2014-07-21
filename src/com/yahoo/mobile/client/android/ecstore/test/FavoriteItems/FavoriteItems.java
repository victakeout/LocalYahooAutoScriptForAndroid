/*
 * This is automated script about "FavoriteItems".
 *
 * You can run these test cases either on the emulator or on device.
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
 * Just run FavoriteItems:adb shell am instrument -e class com.yahoo.
 * mobile.client.android.ecstore.test.FavoriteItems.FavoriteItems -w com.yahoo
 * .mobile.client.android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.FavoriteItems;


import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

/**
 * @author Administrator
 *
 */

public class FavoriteItems extends ActivityInstrumentationTestCase2<Activity> {

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
    public FavoriteItems() throws ClassNotFoundException {
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
     * 1959929: Verify that user can add favorite item.
     * @throws Exception if has error
     */
    public final void testVerifyAddFavoriteItem() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickStarIconNote(solo);
        solo.goBack();
        solo.scrollToTop();
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.COMMODITY);

        //Checks if the star button is shown.
        View star = (View) solo.getView("star_button", 0);
        assertTrue("Star icon not checked.", star.isShown());
        solo.clickOnView(star);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        //Check favorite store via familiar steps.
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_ONE));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.MAYBE_LIKE);

        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        View recommend = (View) solo.getView(
                "listitem_recommended_image1", 0);
        solo.clickOnView(recommend);

        // Checks if the banner is show.
        View banner = (View) solo.getView("img_store_banner", 0);
        assertTrue("Not enter recommended page.", banner.isShown());
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("listitem_productlist_content",
                Action.VIEW_ID_ONE));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // Favorite icon
        View favor = (View) solo.getView("productitem_star_button");
        solo.clickOnView(favor);
        boolean alreadyAdd;

        // Get toast text.
        if (solo.waitForText(ValidationText.HAS_ADDED_COLLECTION)) {
            alreadyAdd = solo.waitForText(ValidationText.HAS_ADDED_COLLECTION);
            junit.framework.Assert.assertTrue("Add failed.", alreadyAdd);
        } else {
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
            solo.clickOnView(favor);
            alreadyAdd = solo.waitForText(ValidationText.HAS_ADDED_COLLECTION);
            junit.framework.Assert.assertTrue("Add failed.", alreadyAdd);

        }

    }


    /**
     * 1959923:Verify store rate from items collected.
     * @throws Exception if has error
     */
    public final void testVerifyStoreRate() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickStarIconNote(solo);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.clickOnText(ValidationText.PRODUCT_COLLECTION);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        TextView price = (TextView) solo.getView(
                "listitem_productlist_store_rating", 0);
        Log.i("number", (String) price.getText());
        String sr = price.getText().toString();

        // Judgment whether the price matches the format of 'x.x'
        boolean isNum = sr.matches("^[0-9].[0-9]");

        assertTrue(
        " Cannot find the shops score or score format is incorrect! ",
                isNum);
    }
}
