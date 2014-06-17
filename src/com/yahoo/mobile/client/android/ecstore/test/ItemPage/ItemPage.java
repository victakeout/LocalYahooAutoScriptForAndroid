/*
 * This is automated script about "ItemPage".
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
 * Just run ItemPage:adb shell am instrument -e class com.yahoo.mobile.client
 * .android.ecstore.test.ItemPage.ItemPage -w com.yahoo.mobile.client.android
 * .ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 *
 */

package com.yahoo.mobile.client.android.ecstore.test.ItemPage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
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
public class ItemPage extends ActivityInstrumentationTestCase2<Activity> {

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
    public ItemPage() throws ClassNotFoundException {
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
     * 1953619:verify for the single commodity discount.
     * @throws Exception if has error
     */
    public final void testSingleCommodityDiscount() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.APPAREL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.clickInList(1);
      //  solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        solo.clickOnText(ValidationText.SALES_PROMOTION);
        solo.clickOnText(ValidationText.DISCOUNT);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        View webpage = (View) solo.getView("webpage", 0);
        assertTrue("No promotion link displayed. ", webpage.isShown());

    }


    /**
     * 1953617:verify for full discount.
     * @throws Exception if has error
     */
    public final void testFullDiscount() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        solo.clickOnText(ValidationText.SALES_PROMOTION);
        solo.clickOnText(ValidationText.FULL);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        View webpage = (View) solo.getView("webpage", 0);
        assertTrue("No promotion link displayed. ", webpage.isShown());

    }

    /**
     * 1953614:verify for All customers the full discount.
     * @throws Exception if has error
     */
    public final void testAllCustomersDiscount() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        solo.clickOnText(ValidationText.SALES_PROMOTION);
        solo.clickOnText(ValidationText.FULL);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        View webpage = (View) solo.getView("webpage", 0);
        assertTrue("No promotion link displayed. ", webpage.isShown());

    }

    /**
     * 1959927:Verify user can add an item to shopping cart.
     * @throws Exception if has error
     */
    public final void testAddItemToShoppingCart() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        Action.addToShoppingCart(solo);
    }

  
    /**
     * 1953636:verify favorite items.
     * @throws Exception if has error
     */
    public final void testVerifyFavoriteitems() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.removeFavoriteItem(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.APPAREL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.clickInList(1);
        solo.goBack();
        Action.clickStarIconNote(solo);
        TextView storeName = (TextView) solo.getView(
                "listitem_productlist_store_name", 0);
        Log.i("number", "1"+storeName.getText().toString().trim());
        solo.clickOnView(solo.getView("tab_image",
                Action.VIEW_ID_FOUR));
        solo.clickOnText(ValidationText.PRODUCT_COLLECTION);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        TextView collectStoreName = (TextView) solo.getView(
                "listitem_productlist_store_name", 0);
        Log.i("number", "2"+collectStoreName.getText().toString().trim());
        assertTrue(
                "Product not added to favorite list. ",
                storeName.getText().toString().trim()
                .equals(collectStoreName.getText().toString().trim()));
    }


    /**
     * 1953626:verify Payment.
     * @throws Exception if has error
     */
    public final void testVerifyPayment() throws Exception {

        Action.enterToItemPage(solo);
        solo.clickInList(1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.APPAREL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickInList(1);
        TestHelper.swipeUp(solo, 1);
        Action.clickText(solo, ValidationText.PAYMENT);
        TextView payInfoATM = (TextView) solo.getView("pay_info", 1);
        TextView payInfoVisa = (TextView) solo.getView("pay_info", 2);
        assertTrue(
                "Payment page not contain any item.",
                payInfoATM.isShown() && payInfoVisa.isShown()
                && solo.searchText(ValidationText.POST));

    }

    /**
     * 1953627:Verify Shopping methods.
     * @throws Exception if has error
     */
    public final void testVerifyShoppingMethods() throws Exception {

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.APPAREL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.clickInList(1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        Action.clickText(solo, ValidationText.SHOPPING_TIPS);
        TextView shoppingTips = (TextView)
                solo.getView("text_must_know", Action.VIEW_ID_THREE);
        assertTrue("Shopping tips not show.", shoppingTips.isShown());

    }

    /**
     * 1953629:Verify the classification in current store page.
     * @throws Exception if has error
     */
    public final void testVerifyCurrentStorePage() throws Exception {

        Action.enterToItemPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.APPAREL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.clickInList(1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        Action.clickText(solo, ValidationText.SEE_ALL_STORE_PRODUCT);
        Action.clickText(solo, ValidationText.CATEGORIES);

        assertTrue(
                "Shopping tips not show.",
                solo.searchText(ValidationText.CATEGORIES)
                && solo.searchText(ValidationText.COMMODITY));

    }

    /**
     * 1953630:Verify the Commodity in current store page.
     * @throws Exception if has error
     */
    public final void testVerifyCommodityPage() throws Exception {
        
        Account.judgementAccountLogin(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.APPAREL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.clickInList(1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        Action.clickText(solo, ValidationText.SEE_ALL_STORE_PRODUCT);
        assertTrue(
                "Shopping tips not show.",
                solo.searchText(ValidationText.CATEGORIES)
                && solo.searchText(ValidationText.COMMODITY));

    }


   
   
}
