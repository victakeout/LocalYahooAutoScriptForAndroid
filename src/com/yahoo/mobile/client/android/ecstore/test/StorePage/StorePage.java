/*
 * This is automated script about "StorePage".
 *
 * You can run these test cases either on the emulator or on device.
 *
 * By Eclipse:
 * Right click the test project and select Run As --> Run As Android JUnit Test
 *
 * By Ant:
 * 1.Run "android update test-project -m [path to target application]
 *  -p [path to the test folder]"  in command line .
 * 2."ant test"
 *
 * By using instrument command:
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client
 * .android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run StorePage:adb shell am instrument -e class com.yahoo.mobile.
 * client.android.ecstore.test.StorePage.StorePage -w com.yahoo.mobile.
 * client.android.ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */
package com.yahoo.mobile.client.android.ecstore.test.StorePage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

/**
 * @author Administrator
 *
 */
@SuppressLint("NewApi")
public class StorePage extends ActivityInstrumentationTestCase2<Activity> {

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
    public StorePage() throws ClassNotFoundException {
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

    /**
     * 1959904:Verify user can check purchasing info from store page.
     * @throws Exception if has error
     */
    public final void testPurchasingInfoFromStorePage() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        Action.addToShoppingCart(solo);

        solo.clickOnView(solo.getView("tab_image",
                Action.VIEW_ID_THREE));
        solo.clickOnView(solo.getView("ecshopping_cart_store_name", 0));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);

        // click store LOGO.
        Action.clickElementsInWebviewByClassname(solo, "pimg");
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        TestHelper.swipeUp(solo, 1);
        solo.clickOnText(ValidationText.SHOPPING_TIPS);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // Get the shopping tips text view.
        TextView mustKnow = (TextView) solo.getView("text_must_know");
        assertTrue("Shopping Tips not display", mustKnow.isShown());
    }


    /**
     * 1959901:Verify all classification and product page.
     * @throws Exception if has error
     */
    public final void testClassificationAndProductPage() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        TestHelper.swipeUp(solo, 1);
        try {
        solo.clickOnText(ValidationText.SEE_ALL_STORE_PRODUCT);
        } catch (AssertionError e) {
            TestHelper.swipeUp(solo, 1);
            solo.clickOnText(ValidationText.SEE_ALL_STORE_PRODUCT);
        }
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        View more = (View) solo.getView("menu_storeinfo");
        solo.clickOnView(more);

        solo.clickOnView(solo.getView("storeinfo_addfav"));

        if (solo.waitForText(ValidationText.HAS_REMOVED_COMMODITY)) {
            solo.clickOnView(solo.getView("storeinfo_addfav"));
        }

        solo.goBack();

        solo.clickOnText(ValidationText.CATEGORIES);

        View categoryThumb = (View) solo.getView(
                "category_thumb_expand");
        assertTrue("category thumb is not show.",
                categoryThumb.isShown());

    }


    /**
     * 1959887:Verify purchase person-time.
     * @throws Exception if has error
     */
    public final void testPurchasePersontime() throws Exception {

        // click search button
        Action.clickSearchButtonOnScreen(solo);
        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.DONG_JING);
        solo.clickOnText(ValidationText.SHOP);
        TextView dongjing = (TextView) solo.getView(
                "listitem_storelist_store_name", 0);
        solo.clickOnView(dongjing);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        TextView buyer = (TextView) solo.getView(
                "listitem_productlist_buyers",
                1);
        String buyers = buyer.getText().toString();
        String number = buyers.substring(0, buyers.lastIndexOf("人"));
        if (number.contains(",")) {
           String numbers = number.replaceAll(",", "");
            boolean isNum = numbers.matches("[0-9]+");
            Log.i("number", numbers);
            assertTrue("buyer infomation format incorrect", isNum);
        } else {
            boolean isNum = number.matches("[0-9]+");
            Log.i("number", number);
            assertTrue("buyer infomation format incorrect", isNum);
        }
    }
    /**
     * 1959898:Verify add favorite store without login.
     * @throws Exception if has error
     */
    public final void testAddFavoriteStoreWithoutLogin() throws Exception {

        Account.judgementAccountWithoutLogin(solo);
        solo.clickOnView(solo.getView("tab_image",1));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.MAYBE_LIKE);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        View recommend = (View) solo.getView(
                "listitem_recommended_image1", 0);
        solo.clickOnView(recommend);

        // Checks if the banner is show.
        View banner = (View) solo.getView("img_store_banner", 0);
        assertTrue("Not enter recommended page.", banner.isShown());

        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        View more = (View) solo.getView("menu_storeinfo");
        solo.clickOnView(more);

        solo.clickOnView(solo.getView("storeinfo_addfav"));
        assertTrue("Account has login.", solo.waitForText(
                ValidationText.PLEASE_LOGIN_ACCOUNT));
    }

    /**
     * 1959924:Verify user can view store promotion.
     * @throws Exception if has error
     */
    public final void testViewStorePromotion() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image",1));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.MAYBE_LIKE);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        View recommend = (View) solo.getView(
                "listitem_recommended_image1", 0);
        solo.clickOnView(recommend);

        // Checks if the banner is show.
        View banner = (View) solo.getView("img_store_banner", 0);
        assertTrue("Not enter recommended page.", banner.isShown());

        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        View more = (View) solo.getView("menu_storeinfo");
        solo.clickOnView(more);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.SALES_PROMOTION);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue("Account has login.", solo.searchText(
                ValidationText.SALES_PROMOTION));
    }
}
