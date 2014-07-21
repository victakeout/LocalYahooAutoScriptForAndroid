/**
 * This is automated script about "CategoryPartTwo".
 *
 * You can run these test cases either on the emulator or on device.
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
 * Just run category:adb shell am instrument -e class com.yahoo.mobile.client.
 * android.ecstore.test.CategoryPartTwo.CategoryPartTwo -w com.yahoo.mobile.client.android.
 * ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 */

package com.yahoo.mobile.client.android.ecstore.test.CategoryPartTwo;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

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

public class CategoryPartTwo extends ActivityInstrumentationTestCase2<Activity> {

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
     * @throws ClassNotFoundException if has error
     */
    @SuppressWarnings("unchecked")
    public CategoryPartTwo() throws ClassNotFoundException {
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
     * 1938104:Check to click the start icon when login.
     * @throws Exception if has error
     */
    public final void testStartIconWhenLogin() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        solo.goBack();
        Action.setListViewStyleAfterSearch(solo);
        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        Action.clickStarIconNote(solo);
    }

    /**
     * 1938116:Check to click the start icon without login in grid view.
     * @throws Exception if has error
     */

    public final void testStarIconWithoutLoginInGridView() throws Exception {

       // Account.overAccountLogIn(solo);
        Account.judgementAccountWithoutLogin(solo);

        Action.enterCategoryClothesPage(solo);

        Action.setSmallPhotoViewStyleAfterSearch(solo);

        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View star = (View) solo.getView("star_button", 1);
        solo.clickOnView(star);

        // Get toast text.
        TextView toastTextView = (TextView) solo.getView("message", 0);
        if (toastTextView != null) {
            String toastText = toastTextView.getText().toString();
            assertEquals(toastText, ValidationText.PLEASE_LOGIN_ACCOUNT);
        }

        // Restore to list view.
        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1938117:Check to click the start icon in grid view when login.
     * @throws Exception if has error
     */
    public final void testStartIconInGridViewWhenLogin() throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterCategoryClothesPage(solo);

        // Change the item view to photo grid view
        Action.setSmallPhotoViewStyleAfterSearch(solo);

        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        Action.clickStarIconNote(solo);

        // Restore to list view.
        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1938115:Check the Star icon display in grid view.
     * @throws Exception if has error
     */
    public final void testStarIconDisplayInGridView() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Change the item view to photo grid view
        Action.setSmallPhotoViewStyleAfterSearch(solo);

        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View star = (View) solo.getView("star_button", 1);
        assertTrue(" Cannot find star icon ", star.isShown());

        // Restore to list view.
        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1938113:Check the commodity price displays in grid view.
     * @throws Exception if has error
     */
    public final void testCommodityPriceDisplayInGridView() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Change the item view to photo grid view
        Action.setSmallPhotoViewStyleAfterSearch(solo);

        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView price = (TextView) solo.getView("listitem_productlist_price",
                0);
        String sr = price.getText().toString();

        // Judgment whether the price matches the format of '$xxx'.
        boolean isNumMoney = sr.matches("[$][0-9]+");

        assertTrue(
                " Cannot find the commodity price or price format"
        + " is incorrect! ",
        isNumMoney);
        // Restore to list view.
        Action.setListViewStyleAfterSearch(solo);
    }

    /**
     * 1938125:Check the commodity price displays in large photo view.
     * @throws Exception if has error
     */
    public final void testCommodityPriceDisplayInLargePhotoView()
            throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Change the item view to photo large photo view
        Action.setLargePhotoViewStyleAfterSearch(solo);

        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TextView price = (TextView) solo.getView("listitem_productlist_price",
                0);
        String sr = price.getText().toString();

        // Judgment whether the price matches the format of '$xxx'.
        boolean isNumPrice = sr.matches("[$][0-9]+");

        assertTrue(
                " Cannot find the commodity price or price "
        + "format is incorrect! ",
        isNumPrice);

        // Restore to list view.
        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1938126:Check the Shops score displays in large photo view.
     * @throws Exception if has error
     */
    public final void testShopsScoreDisplayInLargePhotoView() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Change the item view to photo large photo view
        Action.setLargePhotoViewStyleAfterSearch(solo);

        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TextView price = null;
        try {
            price = (TextView) solo.getView(
                    "listitem_productlist_store_rating", 0);
        } catch (AssertionError e) {
            solo.clickOnText(ValidationText.COMMODITY);
        }
        String sr = price.getText().toString();

        // Judgment whether the price matches the format of 'x.x'.
        boolean isNumFormat = sr.matches("^[0-9].[0-9]+$");

        assertTrue(
                " Cannot find the shops score or score format is incorrect! ",
                isNumFormat);

        // Restore to list view.
        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1938127:Check the Star icon display in large photo view.
     * @throws Exception if has error
     */
    public final void testStarIconDisplayInLargePhotoView()
            throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Change the item view to photo large photo view
        Action.setLargePhotoViewStyleAfterSearch(solo);

        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View star = (View) solo.getView("star_button", 0);
        assertTrue(" Cannot find star icon ", star.isShown());

        // Restore to list view.
        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1938128:Check to click the start icon without login in large photo view.
     * @throws Exception if has error
     */
    public final void testStarIconWithoutLoginInLargePhotoView()
            throws Exception {

        solo.waitForText(ValidationText.ALL_CATEGORIES);
        Account.judgementAccountWithoutLogin(solo);

        Action.enterCategoryClothesPage(solo);

        // Change the item view to photo large photo view
        Action.setLargePhotoViewStyleAfterSearch(solo);

        Action.enterCategoryClothesPage(solo);
        Action.setSmallPhotoViewStyleAfterSearch(solo);

        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View star = (View) solo.getView("star_button", 0);
        solo.clickOnView(star);
        assertTrue("toastText", solo.waitForText(ValidationText.
                PLEASE_LOGIN_ACCOUNT));

        // Restore to list view.
        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1938129:Check to click the start icon in large photo view when login.
     * @throws Exception if has error
     */
    public final void testStartIconInLargePhotoViewWhenLogin()
            throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterCategoryClothesPage(solo);

        // Change the item view to photo large photo view
        Action.setLargePhotoViewStyleAfterSearch(solo);

        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        try {
            Action.clickStarIconNote(solo);

        } catch (AssertionError e) {
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
            Action.clickStarIconNote(solo);
        }


        // Restore to list view.
        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1938045:Check search result.
     * @throws Exception if has error
     */
    public final void testSearchResult() throws Exception {

        Action.enterCategoryClothesPage(solo);
        solo.clickOnText(ValidationText.COMMODITY);
        solo.waitForView(solo.getView("category_tab_secondary_title", 1));
        TextView result = null;

        try {
            result = (TextView) solo.getView("category_tab_secondary_title", 1);
            assertTrue("Result is not displayed.", result.isShown());
        } catch (AssertionError e) {
            Action.enterCategoryClothesPage(solo);
            solo.clickOnText(ValidationText.COMMODITY);
            solo.waitForView(solo.getView("category_tab_secondary_title"));
            result = (TextView) solo.getView("category_tab_secondary_title", 1);
        }

        assertTrue("Result is not displayed.", result.isShown());
        String x = result.getText().toString();

        boolean isNums = x.matches("[0-9]+" + ValidationText.RESULTS_VALUE);

        if (isNums) {
            assertTrue("Search result format incorrect.", true);
        } else {
            assertTrue("Search result format correct.", false);
        }

    }

    /**
     * 1938049:Check advanced page tab display.
     * @throws Exception if has error
     */
    public final void testAdvancedPage() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Filter button
        solo.clickOnView(solo.getView("menu_filter"));

        // Button sort = (Button) solo.getView("btn_sort", 0);
        TextView sortTv = (TextView) solo.getView("indicator_sort");
        assertTrue("The first tab text is incorrect.", sortTv.isShown());

        // Check the second button and Check the highlight line whether focused.
        Button mode = (Button) solo.getView("btn_browse_mode", 0);
        solo.clickOnView(mode);
        TextView modeTv = (TextView) solo.getView("indicator_browse_mode");
        assertTrue("The second tab text is incorrect.", modeTv.isShown());

        // Check the third button and the highlight line whether focused.
        Button filter = (Button) solo.getView("btn_filter", 0);
        solo.clickOnView(filter);
        TextView filterTv = (TextView) solo.getView("indicator_filter");
        assertTrue("The third tab text is incorrect.", filterTv.isShown());

    }

    /**
     * 1938099:Check enter to item page.
     * @throws Exception if has error
     */
    public final void testTapProductName() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickInList(1);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        View imageView = (View) solo.getView("productitem_images");
        assertTrue("Not in item page.", imageView.isShown());

    }

    /**
     * 1938046:Check the default browse mode.
     * @throws Exception if has error
     */
    public final void testDefaultBrowseMode() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        Action.clickText(solo, ValidationText.APPAREL);
        Assert.categoryListShow(solo);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        int size = ValidationText.COSTUMELIST.length;

        // Make sure all the item displayed correctly.
        for (int i = 0; i < size; i++) {
            boolean textFound = solo.searchText(ValidationText.COSTUMELIST[i]);
            assertTrue(ValidationText.COSTUMELIST[i] + " not found", textFound);
        }

    }

    /**
     * 1938050:Check the default advanced tab.
     * @throws Exception if has error
     */
    public final void testDefaultAdvancedTabMode() throws Exception {

        Action.enterCategoryClothesPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        Action.enterAdvancedPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TextView btnSort = (TextView) solo.getView("indicator_sort");

        assertTrue("The default tab is incorrect.", btnSort.isEnabled());

    }

    /**
     * 1953657:verify side bar edit category function.
     * @throws Exception if has error
     */
    public final void testEditFavoriteCategoryBySidebar() throws Exception {

        Account.judgementAccountLogin(solo);
        // click on up icon
        Action.clickHomeButtonOnScreen(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.EDIT_FAVORITE_CATEGORY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        // Get the grid view count.
        GridView lv = (GridView) solo.getView("category_editor_grid");
        Log.i("number", String.valueOf(lv.getCount()));

        for (int i = 0; i < lv.getCount(); i++) {
            View category = (View) solo.getView("category_editor_grid_button",
                    i);
            solo.clickOnView(category);
            assertTrue("Category item is not selected.", category.isPressed());
        }

    }

    /**
     * 1959882:Verify 18 limit note.
     * @throws Exception if has error
     */
    public final void test18LimitNote() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.scrollToBottom();
        Action.clickText(solo, ValidationText.SPORTS_OUTDOOR_RECREATION);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.clickText(solo, ValidationText.EIGHTEEN_AREA);
        TextView restrictNote = (TextView) solo
                .getView("restrict_category_bottom_text");
        assertTrue("Restrict note not exist.", restrictNote.isShown());

    }

    /**
     * 1938159:Verify Sports/Outdoor/Leisure tab correctly
     *  displayed on the page.
     * @throws Exception if has error
     */
    public final void testSportsOutdoorAndLeisureTabDisplayed()
            throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        assertTrue("Sports/Outdoor/Leisure is not displayed.",
                solo.searchText(ValidationText.SPORTS_OUTDOOR_RECREATION));

    }

    /**
     * 1954573:Verify 18 restrict page cancel button.
     * @throws Exception if has error
     */
    public final void testLeaveRestrictPage() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", 2));
        solo.scrollToBottom();
        Action.clickText(solo, ValidationText.SPORTS_OUTDOOR_RECREATION);
        Action.clickText(solo, ValidationText.EIGHTEEN_AREA);

        TextView restrictNote = (TextView) solo
                .getView("restrict_category_bottom_text");
        assertTrue("Restrict note not exist.", restrictNote.isShown());

        Button leave = (Button) solo.getView("btn_under18_leave");
        solo.clickOnView(leave);
        TextView sport = (TextView) solo.getView("action_bar_title", 0);

        boolean text = sport.getText().toString().trim()
                .equals(ValidationText.SPORTS_OUTDOOR_RECREATION2);

        assertTrue("sport text does not exist.", text);

    }

    /**
     * 1938064:Verify "Confirm" button function.
     * @throws Exception if has error
     */
    public final void testConfirmButtonFuction() throws Exception {

        Action.enterCategoryClothesPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        Action.enterAdvancedPage(solo);
        solo.clickOnView(solo.getView("btn_filter"));

        Action.clickText(solo, ValidationText.OK);

        View star = (View) solo.getView("star_button");
        assertTrue("Back to search result page failed.", star.isShown());

    }

    /**
     * 1938096:Click product image in list view.
     * @throws Exception if has error
     */
    public final void testClickProductImageInListview() throws Exception {

        Action.enterCategoryClothesPage(solo);
        solo.waitForText(ValidationText.COMMODITY, 1,
                ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.COMMODITY);
        View productImage = (View) solo
                .getView("listitem_productlist_image", 0);
        solo.clickOnView(productImage);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        TextView classificationText = (TextView) solo
                .getView("action_bar_title");
        assertFalse("Not enter to production detail page.", classificationText
                .getText().toString().equals(ValidationText.COMMODITY));

    }

    /**
     * 1938109:Click product image in Grid view.
     * @throws Exception if has error
     */
    public final void testClickProductImageInGridview() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Change the item view to photo grid view
        Action.setSmallPhotoViewStyleAfterSearch(solo);

        solo.waitForText(ValidationText.COMMODITY, 1,
                ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.COMMODITY);
        View productImage = (View) solo
                .getView("listitem_productlist_image", 0);
        solo.clickOnView(productImage);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        TextView classificationText = (TextView) solo
                .getView("action_bar_title");
        assertFalse("Not enter to production detail page.", classificationText
                .getText().toString().equals(ValidationText.COMMODITY));
        solo.goBack();

        // Restore to list view.
        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1938121:Click product image in list view.
     * @throws Exception if has error
     */
    public final void testClickProductImageInLargePhotoview() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Change the item view to photo grid view
        Action.setLargePhotoViewStyleAfterSearch(solo);

        solo.waitForText(ValidationText.COMMODITY, 1,
                ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnText(ValidationText.COMMODITY);
        View productImage = (View) solo
                .getView("listitem_productlist_image", 0);
        solo.clickOnView(productImage);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        TextView classificationText = (TextView) solo
                .getView("action_bar_title");
        assertFalse("Not enter the production detail page.", classificationText
                .getText().toString().equals(ValidationText.COMMODITY));
        solo.goBack();
        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1938039:Input keywords and search.
     * @throws Exception if has error
     */
    public final void testInputKeywordsSearch() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.JACKET);

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.goBack();

        // click search button
        Action.clickSearchButtonOnScreen(solo);

        // input keyword and search
        Action.searchAfterPutData(solo, 0, ValidationText.APPLE);

        assertTrue("Keywords not changed to iphone.",
                solo.searchText(ValidationText.APPLE));

    }

    /**
     * 1938044:Check "Advanced" button display.
     * @throws Exception if has error
     */
    public final void testAdvancedButtonDisplay() throws Exception {

        Action.enterCategoryClothesPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.waitForText(ValidationText.COMMODITY, 1,
                ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.COMMODITY);

        View advancedView = (View) solo.getView("menu_filter");
        assertTrue("'Advanced' button not found!", advancedView.isShown());

    }

    /**
     * 1938061:Check unselected button function.
     * @throws Exception if has error
     */
    public final void testUnselectedButtonFunction() throws Exception {

        Action.enterCategoryClothesPage(solo);
        TextView storeName = (TextView) solo
                .getView("listitem_productlist_store_name");
        String original = storeName.getText().toString().trim();
        // Go to advanced sort page.
        Action.enterAdvancedSortPage(solo);

        ToggleButton tb = (ToggleButton) solo.getView("tb_cc");

        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue(" 'Credit cards accepted'  button unselected.",
                tb.isChecked());
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("'Credit cards accepted'  button  selected.",
                tb.isChecked());
        Action.clickText(solo, ValidationText.OK);
        TextView storeNames = (TextView) solo
                .getView("listitem_productlist_store_name");
        assertTrue("Item list has changed.",
                original.equals(storeNames.getText().toString().trim()));

    }

    /**
     * 1938124:Check enter to item page in large photo view.
     * @throws Exception if has error
     */
    public final void testEnterToItemPageInLargeView() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.setLargePhotoViewStyleAfterSearch(solo);
        solo.clickInList(1);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View imageView = (View) solo.getView("productitem_images");
        assertTrue("Not enter the item page in large view.",
                imageView.isShown());

        // Restore to list view.
        Action.setListViewStyleAfterSearch(solo);

    }

    /**
     * 1959881:Verify the sort function.
     * @throws Exception if has error
     */
    public final void testVerifyTheSortFunction() throws Exception {

       Action.enterToItemPage(solo);
       solo.goBack();
       solo.sleep(ValidationText.WAIT_TIME_SHORT);
       solo.clickOnView(solo.getView("menu_filter"));
       solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
       solo.clickOnText(ValidationText.LOW_TO_HIGH);

       solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
       TextView priceOne = (TextView) solo.getView(
               "listitem_productlist_price", 0);
       String priceOneNumber = priceOne.getText().toString().trim();
       Log.i("number", "priceOneNumber" + priceOneNumber);
       TextView priceTwo = (TextView) solo.getView(
               "listitem_productlist_price", 1);
       String priceTwoNumber = priceTwo.getText().toString().trim();
       Log.i("number", "priceTwoNumber" + priceTwoNumber);

       assertTrue("Sort function incorrect.", Integer.valueOf(priceOneNumber
               .substring(1)) <= Integer.valueOf(priceTwoNumber.substring(1)));
    }

    /**
     * 1953648:Verify My account edit category function.
     * @throws Exception if has error
     */
    public final void testEditFavoriteCategoryInMyAccount()
            throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        TestHelper.swipeUp(solo, 1);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("profile_bt_edit_favorite_categories"));

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        // solo.clickOnText(ValidationText.Edit_Favorite_Category);
        // Get the grid view count.
        GridView lv = (GridView) solo.getView("category_editor_grid");
        Log.i("number", String.valueOf(lv.getCount()));

        for (int i = 0; i < lv.getCount(); i++) {
            View category = (View) solo.getView("category_editor_grid_button",
                    i);
            solo.clickOnView(category);
            assertTrue("Category item is not selected.", category.isPressed());
        }

    }

    /**
     * 1938062:Verify "Cancel" button function.
     * @throws Exception if has error
     */
    public final void testVerifyCancelFunction()
            throws Exception {

        Account.judgementAccountLogin(solo);
        Action.enterToItemPage(solo);
        solo.goBack();
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("menu_filter"));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.FILTER);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // solo.clickOnToggleButton("可刷卡");
        ToggleButton tb = (ToggleButton) solo.getView("tb_cc");
        solo.clickOnView(tb);
        solo.clickOnText(ValidationText.CANCEL);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("menu_filter"));
        assertFalse("credit card is selected.", tb.isSelected());
    }

    /**
     * 1953649:Check edit category preferences.
     * @throws Exception if has error
     */
    public final void testEditPreferencesFromAccount() throws Exception {

        Account.judgementAccountLogin(solo);
        solo.clickOnView(solo.getView("tab_image", Action.VIEW_ID_FOUR));
        solo.clickOnText(ValidationText.EDIT_FAVORITE_CATEGORY);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        // Get the grid view count.
        GridView lv = (GridView) solo.getView("category_editor_grid");
        Log.i("number", String.valueOf(lv.getCount()));

        for (int i = 0; i < lv.getCount(); i++) {
            View category = (View) solo.getView("category_editor_grid_button",
                    i);
            solo.clickOnView(category);

            assertFalse("Category item is not selected.",
                    category.isActivated());
        }

        solo.clickOnText(ValidationText.TO_LATEST_STATUS);

        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // click on up icon
        Action.clickHomeButtonOnScreen(solo);
        solo.clickOnText(ValidationText.EDIT_FAVORITE_CATEGORY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        for (int i = 0; i < lv.getCount(); i++) {
            View categorys = (View) solo.getView("category_editor_grid_button",
                    i);
            solo.clickOnView(categorys);
            assertFalse("Category item is selected.", categorys.isActivated());
        }
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnText(ValidationText.TO_LATEST_STATUS);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
    }
}
