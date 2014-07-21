package com.yahoo.mobile.client.android.ecstore.Action;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.robotium.solo.WebElement;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;
import com.yahoo.mobile.client.android.ecstore.test.TestHelper;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

/**
 * Contains some common actions methods examples is enterToJacketAfterSearch().
 *
 * @author SYMBIO
 **/

public final class Action {

    /**
     * private constructor.
     */

    private Action() {

    }

    /**
     * This is elements view ID.
     */
    public static final int VIEW_ID_ZERO = 0, VIEW_ID_ONE = 1, VIEW_ID_TWO = 2,
            VIEW_ID_THREE = 3, VIEW_ID_FOUR = 4, VIEW_ID_FIVE = 5,VIEW_ID_EIGHT = 8,VIEW_ID_TEN = 10,VIEW_ID_TWENTY = 20,
            DEFAULT_LISTVIEW_COUNT = 21, DEFAULT_LISTVIEW_COUNT_PLUS = 22;

    /**
     * Clear history information then navigate to main screen.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void clearHistoryInfomation(final Solo solo)
            throws Exception {

        // Go to main screen
        solo.waitForActivity("ECStoreActivity", ValidationText.WAIT_TIME_SHORT);
        solo.waitForText(ValidationText.NEWS, 1,
                ValidationText.WAIT_TIME_SHORT);
        junit.framework.Assert.assertTrue("Navigate to main screen failed.",
                solo.searchText(ValidationText.NEWS));

        // click on up icon
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        clickHomeButtonOnScreen(solo);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // clear history information and back
        solo.waitForText(ValidationText.SETTING, 1,
                ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.SETTING);
        solo.waitForText(ValidationText.CLEAR_SEARCH_HISTORY, 1,
                ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.CLEAR_SEARCH_HISTORY);
        solo.clickOnView(solo.getView("button1"));
        solo.clickOnView(solo.getView("home"));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

    }

    /**
     * Go to advanced screen.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void enterAdvancedPage(final Solo solo) throws Exception {

        solo.waitForText(ValidationText.COMMODITY, 1,
                ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.COMMODITY);
        solo.clickOnView(solo.getView("menu_filter"));

    }

    /**
     * Go to Clothes screen.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void enterCategoryClothesPage(final Solo solo)
            throws Exception {

        solo.waitForActivity("ECSplashActivity",
                ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.ALL_CATEGORIES);
        Action.clickText(solo, ValidationText.APPAREL);

    }

    /**
     * Go to advanced sort screen.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void enterAdvancedSortPage(final Solo solo) throws Exception {

        solo.waitForText(ValidationText.COMMODITY, 1,
                ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.COMMODITY);
        solo.clickOnView(solo.getView("menu_filter"));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("btn_filter"));

    }

    /**
     * Go to browser mode screen.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */

    public static void enterAdvancedBrowserModePage(final Solo solo)
            throws Exception {

        solo.waitForText(ValidationText.COMMODITY, 1,
                ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("menu_filter"));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("btn_browse_mode"));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

    }

    /**
     * Go to main screen then click search icon.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void clickSearchButtonOnScreen(final Solo solo)
            throws Exception {
        View iv;
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        try {
             iv = solo.getView("menu_search", 0);
        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
             iv = solo.getView("menu_search", 0);
        }

        solo.clickOnView(iv);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

    }

    /**
     * Click home button on screen.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void clickHomeButtonOnScreen(final Solo solo)
            throws Exception {

        View iv = solo.getView("home");
        solo.clickOnView(iv);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

    }

    /**
     * Add data in text view.
     *
     * @param solo
     *            the Solo instance
     * @param textviewId
     *            the text view id name
     * @param data
     *            the data will input to text view
     * @throws Exception
     *             if has error
     */
    public static void addInitializeData(final Solo solo, final int textviewId,
            final String data) throws Exception {
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.enterText(textviewId, data);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
    }

    /**
     * Put some data and search it.
     *
     * @param solo
     *            the Solo instance
     * @param textviewId
     *            the text view id name
     * @param data
     *            the data will input to text view
     * @throws Exception
     *             if has error
     */
    public static void searchAfterPutData(final Solo solo,
            final int textviewId, final String data) throws Exception {

        addInitializeData(solo, textviewId, data);
        solo.pressSoftKeyboardSearchButton();
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
    }

    /**
     * Navigate to category screen.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void navigateToCategoryScreen(final Solo solo)
            throws Exception {

        solo.clickOnView(solo.getView("tab_text", 2));
        solo.clickOnText(ValidationText.ALL_CATEGORIES);
        com.yahoo.mobile.client.android.ecstore.Assert.Assert
                .categoryListShow(solo);

    }

    /**
     * Navigate to favorite store screen.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void navigateToFavoriteStoreScreen(final Solo solo)
            throws Exception {

        solo.clickOnView(solo.getView("tab_text", 1));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.FAVORITE_STORES);
        TextView favorite = (TextView) solo.getView("tab_text", 1);
        junit.framework.Assert.assertTrue(
                "Not highligh to favorite store tab bar.",
                favorite.isSelected());

    }

    /**
     * Remove favorite store.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void removeFavoriteStore(final Solo solo) throws Exception {

        solo.clickOnView(solo.getView("tab_text", 1));
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        View storeName;
        try {
             storeName = (View) solo
                    .getView("listitem_favoritestore_storename");
            View url = (View) solo.getView("listitem_favoritestore_image1");
            for (int i = 0; i <= 10; i++){
                solo.clickLongOnView(storeName);
                solo.sleep(ValidationText.WAIT_TIME_SHORT);
                Button ok = (Button) solo.getView("button1");
                solo.clickOnView(ok);
                solo.sleep(ValidationText.WAIT_TIME_LONG);

            }
        } catch (AssertionError e) {
            junit.framework.Assert.assertTrue("Not fully deleted.", true);
        }
        TextView noResult = (TextView) solo.getView("no_result_text", 1);
        junit.framework.Assert.assertTrue("There are some shops exist.",
                noResult.isShown());

    }

    /**
     * Get the current screen contains list view.
     *
     * @param solo
     *            the Solo instance
     * @return return the list view default size
     * @throws Exception
     *             if has error
     */
    public static int getListviewOnCurrentScreen(final Solo solo)
            throws Exception {

        ArrayList<ListView> listview = solo.getCurrentViews(ListView.class);
        int listviewSize = listview.size();
        return listviewSize;

    }

    /**
     * Click plus in open window.
     *
     * @param solo
     *            the Solo instance
     * @param viewId
     *            the search menu list view
     * @param plusId
     *            the plusId is "+" icon in view
     * @throws Exception
     *             if has error
     */
    public static void clickPlusInOpenWindow(final Solo solo,
            final String viewId, final int plusId) throws Exception {

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View view = solo.getView(viewId, plusId);
        ImageView imageview = (ImageView) view;
        solo.clickOnView(imageview);

    }

    /**
     * Return value in text view.
     *
     * @param solo
     *            the Solo instance
     * @param textviewId
     *            get the view text
     * @return the view result
     * @throws Exception
     *             if has error
     */
    public static String getValuesInTextview(final Solo solo,
            final String textviewId) throws Exception {

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View view = solo.getView(textviewId);
        if (view == null) {
            return "";
        }
        TextView testview = (TextView) view;
        return testview.getText().toString();

    }

    /**
     * value in text view,MULTI-same text view Return view status.
     *
     * @param solo
     *            the Solo instance
     * @param textviewid
     *            the view text will be fetch
     * @param vId
     *            the vId is view id
     * @return return the view status
     * @throws Exception
     *             if has error
     */
    public static String getValuesInTextview(final Solo solo,
            final String textviewid, final int vId) throws Exception {

        View view = solo.getView(textviewid, vId);
        if (view == null) {
            return "";
        }
        TextView testview = (TextView) view;
        return testview.getText().toString();

    }

    /**
     * Is view shown?
     *
     * @param solo
     *            the Solo instance
     * @param viewid
     *            the searching view id name
     * @return return view status
     * @throws Exception
     *             if has error
     */
    public static boolean getIsViewShown(final Solo solo, final String viewid)
            throws Exception {

        View view = solo.getView(viewid, 0);
        if (view == null) {
            return false;
        }

        return view.isShown();

    }

    /**
     * Is view shown?
     *
     * @param solo
     *            the Solo instance
     * @param viewid
     *            the searching view id name
     * @param id
     *            the searching view id
     * @return return the view status
     * @throws Exception
     *             if has error
     */
    public static boolean getIsViewShown(final Solo solo, final String viewid,
            final int id) throws Exception {

        View view = solo.getView(viewid, id);
        if (view == null) {
            return false;
        }
        return view.isShown();

    }

    /**
     * Click view.
     *
     * @param solo
     *            the Solo instance
     * @param viewId
     *            By clicking on the view
     * @throws Exception
     *             if has error
     */
    public static void clickView(final Solo solo, final String viewId)
            throws Exception {

        View view = solo.getView(viewId);
        solo.clickOnView(view);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

    }

    /**
     * Click view by id.
     *
     * @param solo
     *            the Solo instance
     * @param viewId
     *            By clicking on the view
     * @param id
     *            viewId By clicking on the view id
     * @throws Exception
     *             if has error
     */
    public static void clickView(final Solo solo, final String viewId,
            final int id) throws Exception {

        View view = solo.getView(viewId, id);
        solo.clickOnView(view);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

    }

    /**
     * Click text.
     *
     * @param solo
     *            the Solo instance
     * @param text
     *            the text is given to click
     * @throws Exception
     *             if has error
     */
    public static void clickText(final Solo solo, final String text)
            throws Exception {

        solo.waitForText(text, 1, ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(text);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

    }

    /**
     * Add history information in search bar.
     *
     * @param solo
     *            the Solo instance
     * @param searchKeys
     *            the searchKeys array contains search elements
     * @throws Exception
     *             if has error
     */
    public static void addHistoryInfomationInSearchBar(final Solo solo,
            final String[] searchKeys) throws Exception {

        int searchKeysLength = searchKeys.length;
        for (int i = 0; i < searchKeysLength; i++) {

            // click on search button on home screen
            Action.clickSearchButtonOnScreen(solo);

            // input key to search
            Action.addInitializeData(solo, 0, searchKeys[i]);
            solo.pressSoftKeyboardSearchButton();
            solo.sleep(ValidationText.WAIT_TIME_SHORT);

            // click back button if go to result screen
            Assert.navigateToResultPage(solo);
            Action.clickHomeButtonOnScreen(solo);
        }

    }

    /**
     * Close some phone soft keyboard.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void closeSoftKeyBoard(final Solo solo) throws Exception {

        // close soft keyboard
        InputMethodManager imm = (InputMethodManager) solo.getCurrentActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm.isActive()) {

            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * Set the view to list view.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void setListViewStyleAfterSearch(final Solo solo)
            throws Exception {

        enterAdvancedBrowserModePage(solo);
        solo.clickOnView(solo.getView("btn_list_small"));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

    }

    /**
     * Set the view to grid view.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void setSmallPhotoViewStyleAfterSearch(final Solo solo)
            throws Exception {

        enterAdvancedBrowserModePage(solo);
        solo.clickOnView(solo.getView("btn_list_grid"));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
    }

    /**
     * Set the view to large photo view.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void setLargePhotoViewStyleAfterSearch(final Solo solo)
            throws Exception {

        enterAdvancedBrowserModePage(solo);
        solo.clickOnView(solo.getView("btn_list_large"));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
    }

    /**
     * Remove favorite product item.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void removeFavoriteItem(final Solo solo) throws Exception {

        solo.clickOnView(solo.getView("tab_image", VIEW_ID_FOUR));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.PRODUCT_COLLECTION);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        if (solo.searchText(ValidationText.ALREAD_ADDED)) {
            TextView title = (TextView) solo.getView("tx_header", 0);
            Log.i("number", title.getText().toString().trim().substring(5, 6));
            if (title.isShown()) {
                //Extracting the number of favorite items from text header.
                String number = title.getText().toString().trim()
                        .substring(5, 6);
                int numbers = Integer.parseInt(number);
                for (int f = 0; f < numbers; f++) {
                    solo.clickLongOnView(solo.getView(
                            "listitem_productlist_image", 0));
                    solo.sleep(ValidationText.WAIT_TIME_SHORT);
                    solo.clickOnButton(ValidationText.OK);
                    solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
                }
                junit.framework.Assert.assertFalse("Did not remove all.",
                        title.isShown());

            } else {
                junit.framework.Assert.assertTrue("Did not remove all", true);
            }
        } else {
            junit.framework.Assert.assertTrue("Did not remove all", true);
        }
    }

    /**
     * Define the counts default value.
     */
    private static int counts = 0;

    /**
     * Define called radioButtons view.
     */
    @SuppressWarnings("unused")
    private static View radioButtons;

    /**
     * Click Star Icon.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void clickStarIconNote(final Solo solo) throws Exception {

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View star = (View) solo.getView("star_button", counts);
        solo.clickOnView(star);
        boolean alreadyAdd;

        // Get toast text.
        if (solo.waitForText(ValidationText.HAS_ADDED_COLLECTION)){
            alreadyAdd = solo.waitForText(ValidationText.HAS_ADDED_COLLECTION);
            junit.framework.Assert.assertTrue("Add failed.", alreadyAdd);
        } else {
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
            solo.clickOnView(star);
            alreadyAdd = solo.waitForText(ValidationText.HAS_ADDED_COLLECTION);
            junit.framework.Assert.assertTrue("Add failed.", alreadyAdd);

        }
        counts++;
    }

    /**
     * Add product to shopping cart in item page.
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void addToShoppingCart(final Solo solo) throws Exception {

        Log.i("number", solo.getCurrentActivity().getClass().toString());
        // Swipe the screen until the buy button display.
        // TestHelper.swipeUp2(solo, 1);
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View shopCart;
        try {
            shopCart = solo.getView("productitem_btn_add_to_shopping_cart");
            solo.clickOnView(shopCart);
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
        } catch (AssertionError e) {

            TestHelper.swipeUp2(solo, 2);

            shopCart = solo.getView("productitem_btn_add_to_shopping_cart");
            solo.clickOnView(shopCart);

        }

        // Select product property if it exists.
        try {
            radioButtons = (View) solo.getView(
                    "product_item_spec_item_selections", 0);
        } catch (AssertionError e) {
            TestHelper.swipeUp2(solo, 2);
            // solo.sleep(ValidationText.WAIT_TIME_SHORT);
            View shopCarts = solo
                    .getView("productitem_btn_add_to_shopping_cart");
            solo.clickOnView(shopCarts);
        }
        View buddle;
        View radioButton = null;
        try {
            radioButton  = (View) solo.getView(
                    "product_item_spec_item_selections", 0);
            solo.clickOnView(radioButton);
            solo.searchText(ValidationText.OK);
            solo.clickOnButton(ValidationText.OK);
            solo.waitForText(ValidationText.ALREADY_ADD_SHOPPING_CART, 1,
                    ValidationText.WAIT_TIME_MIDDLE);
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            buddle = solo.getView("tab_badge", VIEW_ID_THREE);
            junit.framework.Assert.assertTrue("No items in shopping cart.",
                    buddle.isShown());
        } catch (AssertionError e) {
            junit.framework.Assert.assertTrue("Add failed.", true);
        }

        solo.goBack();
    }

    /**
     * Add product to shopping cart in item page.
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void addToShoppingCartForSmallScreen(final Solo solo)
            throws Exception {


        // Swipe the screen until the buy button display.
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View shopCart;
        try {
            shopCart = solo.getView("productitem_btn_add_to_shopping_cart");
            solo.clickOnView(shopCart);
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
        } catch (AssertionError e) {

            TestHelper.swipeUp2(solo, 2);

            shopCart = solo.getView("productitem_btn_add_to_shopping_cart");
            solo.clickOnView(shopCart);

        }

        // Select product property if it exists.
        try {
            radioButtons = (View) solo.getView(
                    "product_item_spec_item_selections", 0);
        } catch (AssertionError e) {
            TestHelper.swipeUp2(solo, 2);
            // solo.sleep(ValidationText.WAIT_TIME_SHORT);
            View shopCarts = solo
                    .getView("productitem_btn_add_to_shopping_cart");
            solo.clickOnView(shopCarts);
        }
        View buddle;
        View radioButton = null;
        try {
            radioButton  = (View) solo.getView(
                    "product_item_spec_item_selections", 0);
            solo.clickOnView(radioButton);
            solo.searchText(ValidationText.OK);
            solo.clickOnButton(ValidationText.OK);
            solo.waitForText(ValidationText.ALREADY_ADD_SHOPPING_CART, 1,
                    ValidationText.WAIT_TIME_MIDDLE);
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            buddle = solo.getView("tab_badge", VIEW_ID_THREE);
            junit.framework.Assert.assertTrue("No items in shopping cart.",
                    buddle.isShown());
        } catch (AssertionError e) {
            junit.framework.Assert.assertTrue("Add failed.", true);
        }

        solo.goBack();
    }

    /**
     * Remove shopping cart products.
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void removeShoppingCart(final Solo solo) throws Exception {

        View buddle;
        solo.clickOnView(solo.getView("tab_image", VIEW_ID_THREE));

        try {

            // Get the number of shopping cart goods.
            TextView countShopping = (TextView) solo
                    .getView("ecshopping_cart_header_count");
            int number = Integer.valueOf(countShopping.getText().toString()
                    .substring(0, 1));
            Log.i("number", String.valueOf(number));

            /*
             * In most cases,The number of shopping cart goods is inconsistent
             * with the list display,if the bubble is not shown,we can make sure
             * that all products has deleted.
             */
            buddle = solo.getView("tab_badge", VIEW_ID_THREE);
            for (int i = 0; i < number; i++) {
                try {

                    solo.clickLongOnView(solo.getView(
                            "ecshopping_cart_store_name", 0));
                    solo.sleep(ValidationText.WAIT_TIME_SHORT);
                    solo.clickOnButton(ValidationText.OK);
                    solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

                } catch (AssertionError e) {

                    junit.framework.Assert.assertFalse(
                            "Buddle is displayed on tab.", buddle.isShown());
                }

            }
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            junit.framework.Assert.assertFalse("Buddle is displayed on tab.",
                    buddle.isShown());
        } catch (AssertionError e) {
            junit.framework.Assert.assertTrue("Buddle is displayed on tab.",
                    true);
        }
    }
    /**
     * Buy now.
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void buyNow(final Solo solo) throws Exception {

        Log.i("number", solo.getCurrentActivity().getClass().toString());
        // Swipe the screen until the buy button display.
        // TestHelper.swipeUp2(solo, 1);
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View shopCart;
        try {
            shopCart = solo.getView("productitem_btn_purchase_now");
            solo.clickOnView(shopCart);

        } catch (AssertionError e) {

            TestHelper.swipeUp2(solo, 2);

            shopCart = solo.getView("productitem_btn_purchase_now");
            solo.clickOnView(shopCart);

        }

        // Select product property if it exists.
        try {
            radioButtons = (View) solo.getView(
                    "product_item_spec_item_selections", 0);
        } catch (AssertionError e) {
            TestHelper.swipeUp2(solo, 2);
            // solo.sleep(ValidationText.WAIT_TIME_SHORT);
            View shopCarts = solo
                    .getView("productitem_btn_purchase_now");
            solo.clickOnView(shopCarts);
        }
        View buddle;
        View radioButton = (View) solo.getView(
                "product_item_spec_item_selections", 0);
        if (radioButton.isShown()) {

            solo.clickOnView(radioButton);
            solo.searchText(ValidationText.OK);
            solo.clickOnButton(ValidationText.OK);
            solo.waitForText(ValidationText.ALREADY_ADD_SHOPPING_CART, 1,
                    ValidationText.WAIT_TIME_MIDDLE);
            solo.sleep(ValidationText.WAIT_TIME_LONG);
            buddle = solo.getView("tab_badge", VIEW_ID_THREE);
            junit.framework.Assert.assertTrue("No items in shopping cart.",
                    buddle.isShown());
        } else {
            junit.framework.Assert.assertTrue("Add failed.", true);
        }


    }
    /**
     * Enter product item detail page.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void enterToItemPage(final Solo solo) throws Exception {

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.APPAREL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.clickInList(1);
        // solo.sleep(ValidationText.WAIT_TIME_SHORT);

    }

    /**
     * define the count initial value.
     */
    private static int Counts = 1;

    /**
     * Enter product item detail page.
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void enterToItemPages(final Solo solo) throws Exception {

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.APPAREL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        try {

            solo.clickInList(Counts);

        } catch (AssertionError e) {
            TestHelper.swipeUp2(solo, 1);
            solo.clickInList(Counts);
        }
        Counts++;
        Log.i("number", String.valueOf(Counts));
        // solo.sleep(ValidationText.WAIT_TIME_SHORT);

    }

    /**
     * define the count initial value.
     */
    private static int COUNT = 1;

    /**
     * Enter product item detail page.
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void loopEnterAndBack(final Solo solo) throws Exception {

        for (int i = 0; i < Action.VIEW_ID_THREE; i++) {

            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
            solo.clickInList(COUNT);
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
            solo.goBack();
            COUNT++;

            if (COUNT % 4 == 0) {
                COUNT = 1;
            }
        }

    }

    /**
     * define the count initial value.
     */
    private static int LINE = 0;

    /**
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void loopBrowse(final Solo solo) throws Exception {
        for (int z = 0; z < 6; z++) {
            TextView subCategory = (TextView) solo.getView("sub_category_name",
                    LINE);
            Log.i("number", subCategory.getText().toString());

            solo.clickOnView(subCategory);

            solo.sleep(ValidationText.WAIT_TIME_SHORT);
            Action.clickText(solo, ValidationText.COMMODITY);
            Action.loopEnterAndBack(solo);
            LINE++;
            solo.goBack();
        }

    }

    /**
     * define the count initial value.
     */
    private static int LINES = 1;

    /**
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void loopBrowsebeauty(final Solo solo) throws Exception {
        for (int z = 0; z < 1; z++) {

            solo.clickInList(2);
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
            Action.clickText(solo, ValidationText.COMMODITY);
            Action.loopEnterAndBack(solo);
            LINES++;
            solo.goBack();
        }

    }

    /**
     * Enter product item detail page2.
     *
     * @param solo
     *            the Solo instance
     * @param countsBrowse
     *            the counts stand for how many browse record will be created
     * @throws Exception
     *             if has error.
     */
    public static void makeBrowseRecord(final Solo solo, final int countsBrowse)
            throws Exception {

        Action.clickText(solo, ValidationText.ALL_CATEGORIES);
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        for (int i = 1; i <= countsBrowse; i++) {
            solo.clickInList(i);
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
            if (i % VIEW_ID_FOUR == 0) {
                solo.scrollUpList(i);
                solo.sleep(ValidationText.WAIT_TIME_SHORT);
                solo.clickInList(i);
                solo.sleep(ValidationText.WAIT_TIME_SHORT);
            }
            solo.goBack();

        }
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

    }

    /**
     * Click elements from web view by ClassName.
     *
     * @param solo
     *            the Solo instance
     * @param text
     *            the class name text will be clicked on the web view
     * @throws Exception
     *             if has error
     */
    public static void clickElementsInWebviewByClassname(final Solo solo,
            final String text) throws Exception {

        for (WebElement web : solo.getCurrentWebElements()) {
            Log.i("number", "ClassNme:" + web.getClassName().toString());
            Log.i("number", "Text:" + web.getText().toString());

            if (web.getClassName().toString().equals(text)) {
                solo.clickOnWebElement(web);
                solo.sleep(ValidationText.WAIT_TIME_LONGER);
            }
        }

    }

    // Click elements from web view by text
    /**
     * @param solo
     *            the Solo instance
     * @param text
     *            the text will be clicked on the web view
     * @throws Exception
     *             if has error.
     */
    public static void clickElementsInWebviewByText(final Solo solo,
            final String text) throws Exception {

        for (WebElement web : solo.getCurrentWebElements()) {
            Log.i("number", web.getText().toString());
            Log.i("number", web.getClassName().toString());
            if (web.getText().toString().equals(text)) {
                solo.clickOnWebElement(web);
                solo.sleep(ValidationText.WAIT_TIME_LONGER);

            }

        }
    }

    /**
     * Set the default value is false.
     */
    private static boolean actual = false;

    /**
     * Search text on web view.
     *
     * @param solo
     *            the Solo instance
     * @param text
     *            the text will be searched on the web view
     * @throws Exception
     *             if has error
     */
    public static void searchTextOnWebview(final Solo solo, final String text)
            throws Exception {

        for (WebElement web : solo.getCurrentWebElements()) {
            Log.i("number", web.getText().toString());
            Log.i("number", web.getClassName().toString());
            if (web.getText().toString().equals(text)) {

                actual = true;
                solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

            }

        }
        junit.framework.Assert.assertTrue("Text not found", actual);
    }

    /**
     * Set the default value is false.
     */
    private static boolean actuals = false;

    /**
     * Search text on web view.
     *
     * @param solo
     *            the Solo instance
     * @param text
     *            the text will be searched on the web view
     * @throws Exception
     *             if has error
     */
    public static void searchClassNameOnWebview(final Solo solo, final String text)
            throws Exception {

        for (WebElement web : solo.getCurrentWebElements()) {
            Log.i("number", web.getText().toString());
            Log.i("number", web.getClassName().toString());
            if (web.getClassName().toString().trim().equals(text)) {

                actual = true;
                solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

            }

        }
        junit.framework.Assert.assertTrue("Class name not found", actuals);
    }

    /**
     * Enter jacket page.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void enterToJacket(final Solo solo) throws Exception {

        solo.clickOnView(solo.getView("tab_text", VIEW_ID_TWO));
        Action.clickText(solo, ValidationText.APPAREL);
        Action.clickText(solo, ValidationText.POPULAR_WOMEN);
        Action.clickText(solo, ValidationText.JACKET);
        Action.clickText(solo, ValidationText.CATEGORIES);

    }

    /**
     * Enter jacket page after search.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void enterToJacketAfterSearch(final Solo solo)
            throws Exception {

        // navigate to category screen
        solo.clickOnView(solo.getView("tab_image", VIEW_ID_TWO));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // click search button
        Action.clickSearchButtonOnScreen(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);
    }

    /**
     * Delete collected store.
     *
     * @param solo
     *            the Solo instance
     * @throws Exception
     *             if has error
     */
    public static void deleteProductCollected(final Solo solo)
            throws Exception {

        solo.clickOnView(solo.getView("tab_image", VIEW_ID_FOUR));
        TextView outNumberTwo = null;
        try {
            outNumberTwo = (TextView) solo.getView("profile_bt_favorite_count");
            solo.clickOnView(outNumberTwo);
            int number = Integer.parseInt(outNumberTwo.getText().toString());

            for (int i = 0; i < number; i++) {

                View img = (View) solo.getView("listitem_productlist_image");
                solo.clickLongOnView(img);

                // Confirm remove it.
                solo.clickOnView(solo.getView("button1"));
                solo.sleep(ValidationText.WAIT_TIME_SHORT);
            }

        } catch (AssertionError e) {
            junit.framework.Assert.assertTrue("No item need delete.", true);
        }
        solo.goBack();
    }
}
