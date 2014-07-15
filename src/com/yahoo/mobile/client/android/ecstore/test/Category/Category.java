/**
 * This is automated script about "Category".
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
 * android.ecstore.test.Category.Category -w com.yahoo.mobile.client.android.
 * ecstore.test/android.test.InstrumentationTestRunner
 *
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 */

package com.yahoo.mobile.client.android.ecstore.test.Category;

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

public class Category extends ActivityInstrumentationTestCase2<Activity> {

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
    public Category() throws ClassNotFoundException {
        super((Class<Activity>) launcherActivityClass);
    }

    @Override
    protected final void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        Assert.testFirstLaunch(solo);
        System.gc();
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
    }

    @Override
    public final void tearDown() throws Exception {


        solo.finishOpenedActivities();
        System.gc();
        super.tearDown();

    }


    /**
     * 1938037:Check back function.
     * @throws Exception if has error
     */
    public final void testBackFunction() throws Exception {

        Action.enterCategoryClothesPage(solo);
        solo.clickOnView(solo.getView("home"));
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        Assert.categoryListShow(solo);

    }

    /**
     *  1938041:Check tab display.
     * @throws Exception if has error
     */
    public final void testTab() throws Exception {

        Action.enterCategoryClothesPage(solo);
        int size = ValidationText.STORE_TITLE.length;

        for (int i = 0; i < size; i++) {
            boolean textFound = solo.searchText(ValidationText.STORE_TITLE[i]);
            assertTrue(ValidationText.STORE_TITLE[i] + " not found", textFound);
        }

    }

    /**
     * 1938036:Check header item.
     * @throws Exception if has error
     */
    public final void testHeader() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        // Get "back" button view.
        ImageView back = (ImageView) solo.getView("home");

        // Get "Search" button view.
        View search = solo.getView("menu_search");

        // Get "Filter" button view.
        View advance = solo.getView("menu_filter");

        boolean views = back.isShown() && search.isShown() && advance.isShown();
        assertTrue("views not found", views);

    }

    /**
     * 1938042:Check sort tab items all display.
     * @throws Exception if has error
     */
    public final void testSortTab() throws Exception {

        Action.enterCategoryClothesPage(solo);
        int size = ValidationText.COSTUMELIST.length;

        for (int i = 0; i < size; i++) {
            boolean textFound = solo.searchText(ValidationText.COSTUMELIST[i]);
            assertTrue(ValidationText.COSTUMELIST[i] + " not found", textFound);
        }

    }

    /**
     * 1938043:Check can return to item list.
     * @throws Exception if has error
     */
    public final void testItemList() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        Action.clickText(solo, ValidationText.CATEGORIES);
        solo.goBack();
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Assert.categoryListShow(solo);

    }

    /**
     * 1938052:Check "Search Clothing" text is show in search bar.
     * @throws Exception if has error
     */
    public final void testSearchbarDefault() throws Exception {

        Action.enterCategoryClothesPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        solo.clickOnView(solo.getView("menu_search"));
        assertTrue("Not find 'Search Clothing' in search bar.",
                solo.searchText(ValidationText.SEARCH_APPAREL, 1));

    }

    /**
     * 1938053:Check can switch to sort tab.
     * @throws Exception if has error
     */
    public final void testSort() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.enterAdvancedPage(solo);
        int size = ValidationText.CATEGORYLIST_TAB1.length;

        for (int i = 0; i < size; i++) {
            boolean textFound = solo
                    .searchText(ValidationText.CATEGORYLIST_TAB1[i]);
            assertTrue(ValidationText.CATEGORYLIST_TAB1[i] + " not found",
                    textFound);
        }

    }

    /**
     * 1938054:Check switch to filter sort tab.
     * @throws Exception if has error
     */
    public final void testFilterSort() throws Exception {

        Action.enterCategoryClothesPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        Action.enterAdvancedPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("btn_filter"));

        int size = ValidationText.CATEGORYLIST_TAB2.length;
        for (int i = 0; i < size; i++) {
            boolean textFound = solo
                    .searchText(ValidationText.CATEGORYLIST_TAB2[i]);
            assertTrue(ValidationText.CATEGORYLIST_TAB2[i] + " not found.",
                    textFound);
        }

    }

    /**
     * 1938060:Check the sort layout.
     * @throws Exception if has error
     */
    public final void testSortLayout() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.enterAdvancedPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("btn_filter"));

        // Get the current page elements.
        View scrollBar = solo.getView("seekbar", 0);
        View tableRowOne = solo.getView("tableRow1", 0);
        View tableRowTwo = solo.getView("tableRow2", 0);
        View tableRowThree = solo.getView("tableRow3", 0);

        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        boolean views = scrollBar.isShown() && tableRowOne.isShown()
                && tableRowTwo.isShown() && tableRowThree.isShown();
        assertTrue("Some views not found.", views);

    }

    /**
     * 1938055: verify the order of sort items.
     * @throws Exception if has error
     */
    public final void testSortOptions() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.enterAdvancedPage(solo);

        ListView lv = (ListView) solo.getView("list_sort", 0);
        lv.getItemAtPosition(0);
        int listviewCount = lv.getCount();
        assertEquals("Not four items in list.", listviewCount,
                Action.VIEW_ID_FOUR);

        for (int i = 0; i < listviewCount; i++) {

            boolean sortList = lv.getItemAtPosition(0).equals(
                    ValidationText.CATEGORYLIST_TAB1[0])
                    && lv.getItemAtPosition(1).equals(
                            ValidationText.CATEGORYLIST_TAB1
                            [Action.VIEW_ID_ONE])
                    && lv.getItemAtPosition(2).equals(
                            ValidationText.CATEGORYLIST_TAB1
                            [Action.VIEW_ID_TWO])
                    && lv.getItemAtPosition(Action.VIEW_ID_THREE).equals(
                            ValidationText.CATEGORYLIST_TAB1
                            [Action.VIEW_ID_THREE]);

            assertTrue("Sort incorrect.", sortList);

        }

    }

    /**
     * 1938063:Check the "OK" button to display.
     * @throws Exception if has error
     */
    public final void testComfirmButtonDisplay() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.enterAdvancedPage(solo);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("btn_filter"));
        Button lv = (Button) solo.getView("btn_ok");
        assertEquals("Not find 'OK' button.", ValidationText.OK, lv.getText()
                .toString());

    }

    /**
     * 1938047:Check default items display.
     * @throws Exception if has error
     */
    public final void testCheckTheDefaultItems() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        GridView lv = (GridView) solo.getView("gridview", 0);
        int defaultItems = lv.getCount();
        assertEquals("The default items incorrect.",
                Action.DEFAULT_LISTVIEW_COUNT, defaultItems);

    }

    /**
     * 1938048:Check auto load more data.
     * @throws Exception if has error
     */
    public final void testAutoLoadMore() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        GridView lv = (GridView) solo.getView("gridview", 0);

        // Scroll the screen to load more data.
        for (int i = 0; i < Action.VIEW_ID_FIVE; i++) {
            TestHelper.swipeUp(solo, 1);
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
        }

        boolean listviewCount2 =
                lv.getCount() > Action.DEFAULT_LISTVIEW_COUNT_PLUS;
        assertTrue("The default items incorrect.", listviewCount2);

    }

    /**
     * 1938069:Check "Credit cards accepted" can changed to unselected.
     * @throws Exception if has error
     */
    public final void testCreditCardMode() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Go to advanced sort page.
        Action.enterAdvancedSortPage(solo);

        // solo.clickOnToggleButton("可刷卡");
        ToggleButton tb = (ToggleButton) solo.getView("tb_cc");

        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue(" 'Credit cards accepted'  button unselected.",
                tb.isChecked());
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("'Credit cards accepted'  button  selected.",
                tb.isChecked());

    }

    /**
     * 1938072:Check "A/V" can changed to unselected.
     * @throws Exception if has error
     */
    public final void testHasVideoMode() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Go to advanced sort page.
        Action.enterAdvancedSortPage(solo);

        // Get "hasVideo" button.
        ToggleButton tb = (ToggleButton) solo.getView("tb_hasvideo");

        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue(" 'A/V'  button unselected.", tb.isChecked());
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("'A/V'  button  selected.", tb.isChecked());

    }

    /**
     * 1938075:Check "Zero Interest Rate" can changed to unselected.
     * @throws Exception if has error
     */
    public final void testZeroInterestMode() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Go to advanced sort page.
        Action.enterAdvancedSortPage(solo);

        // Get "0 Interest" button.
        ToggleButton tb = (ToggleButton) solo.getView("tb_cczeroint");

        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue(" 'Zero Interest Rate'  button unselected.", tb.isChecked());
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("'Zero Interest Rate'  button  selected.", tb.isChecked());
    }

    /**
     * 1938078:Check "Installments" can changed to unselected.
     * @throws Exception if has error
     */
    public final void testInstallmentsMode() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Go to advanced sort page.
        Action.enterAdvancedSortPage(solo);

        // Get "Installments" button.
        ToggleButton tb = (ToggleButton) solo.getView("tb_ccinstall");

        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue(" 'Installments'  button unselected.", tb.isChecked());
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("'Installments'  button  selected.", tb.isChecked());

    }

    /**
     * 1938081:Check "Payments" can changed to unselected.
     * @throws Exception if has error
     */
    public final void testSupermarketPaymentMode() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Go to advanced sort page.
        Action.enterAdvancedSortPage(solo);

        // Get "Payment" button.
        ToggleButton tb = (ToggleButton) solo.getView("tb_cvs_pay");

        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue(" 'Payments'  button unselected.", tb.isChecked());
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("'Payments'  button  selected.", tb.isChecked());

    }

    /**
     * 1938084:Check "Pickup" can changed to unselected.
     * @throws Exception if has error
     */
    public final void testSupermarketPickupMode() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Go to advanced sort page.
        Action.enterAdvancedSortPage(solo);

        // Get "Pickup" button.
        ToggleButton tb = (ToggleButton) solo.getView("tb_cvs_pick");

        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue(" 'Pickup'  button unselected.", tb.isChecked());
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("'Pickup'  button  selected.", tb.isChecked());

    }

    /**
     * 1938087:Check "Has Stock" can changed to unselected.
     * @throws Exception if has error
     */
    public final void testHasStockMode() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Go to advanced sort page.
        Action.enterAdvancedSortPage(solo);

        // Get "Has Stock" button.
        ToggleButton tb = (ToggleButton) solo.getView("tb_hasstock");

        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue(" 'Has Stock'  button unselected.", tb.isChecked());
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("'Has Stock'  button  selected.", tb.isChecked());

    }

    /**
     * 1938090:Check "Has Image" can changed to unselected.
     * @throws Exception if has error
     */
    public final void testHasImageMode() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Go to advanced page.
        Action.enterAdvancedSortPage(solo);

        // Get "HasImage" button.
        ToggleButton tb = (ToggleButton) solo.getView("tb_hasimage");

        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue(" 'Has Image'  button unselected.", tb.isChecked());
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("'Has Image'  button  selected.", tb.isChecked());

    }

    /**
     * 1938093:Check "SuperiorStore" can changed to unselected.
     * @throws Exception if has error
     */
    public final void testSuperiorStoreMode() throws Exception {

        Action.enterCategoryClothesPage(solo);

        // Go to advanced page.
        Action.enterAdvancedSortPage(solo);

        // Get "SuperiorStore" button.
        ToggleButton tb = (ToggleButton) solo.getView("tb_issuperior");

        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertTrue(" 'Superior Store'  button unselected.", tb.isChecked());
        solo.clickOnView(tb);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        assertFalse("'Superior Store'  button  selected.", tb.isChecked());

    }

    /**
     * 1938100:Check the commodity price display.
     * @throws Exception if has error
     */
    public final void testCommodityPriceDisplay() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TextView price;

        try {
            price = (TextView) solo.getView("listitem_productlist_price", 0);
        } catch (AssertionError e) {
            Action.enterCategoryClothesPage(solo);
            Action.clickText(solo, ValidationText.COMMODITY);
            price = (TextView) solo.getView("listitem_productlist_price", 0);
        }

        String sr = price.getText().toString();

        // Judgment whether the price matches the format of '$xxx'.
        boolean isNums = sr.matches("[$][0-9]+");

        assertTrue(
                " Cannot find the commodity price or price "
        + "format is incorrect! ", isNums);

    }

    /**
     * 1938101:Check the Shops score display.
     * @throws Exception if has error
     */
    public final void testShopsScoreDisplay() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TextView price = null;

        try {
            price = (TextView) solo.getView(
                    "listitem_productlist_store_rating", 0);
        } catch (AssertionError e) {
            Action.enterCategoryClothesPage(solo);
            Action.clickText(solo, ValidationText.COMMODITY);
            solo.sleep(ValidationText.WAIT_TIME_SHORT);
        }

        String sr = price.getText().toString();

        // Judgment whether the price matches the format of 'x.x'.
        boolean isNumz = sr.matches("^[0-9].[0-9]+$");

        assertTrue(
                " Cannot find the shops score or score format is incorrect! ",
                isNumz);

    }

    /**
     * 1938102:Check the Star icon display.
     * @throws Exception if has error
     */
    public final void testStarIconDisplay() throws Exception {

        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View star = (View) solo.getView("star_button", 1);
        assertTrue(" Cannot find the star icon ", star.isShown());

    }

    /**
     * 1938130:Check "All Categories" at the bottom of the screen.
     * @throws Exception if has error
     */
    public final void testAllClassificationExist() throws Exception {

        View classificationIcon = (View) solo.getView("tab_image", 2);

        TextView classificationText = (TextView) solo.getView("tab_text", 2);
        boolean text = classificationText.getText().toString()
                .equals(ValidationText.ALL_CATEGORIES);

        assertTrue("All classification does not exist.",
                classificationIcon.isShown() && text);

    }

    /**
     * 1938131:Check all classification item page.
     * @throws Exception if has error
     */
    public final void testAllClassificationItemPage() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        Assert.categoryListShow(solo);

    }

    /**
     * 1938133:Check the screen top text.
     * @throws Exception if has error
     */
    public final void testClassificationTextOnTheTop() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        TextView classificationText = (TextView) solo
                .getView("action_bar_title");
        boolean text = classificationText.getText().toString().trim()
                .equals(ValidationText.ALL_CATEGORIES);

        assertTrue("All classification text does not exist.", text);

    }

    /**
     * 1938141:Check latest update side bar.
     * @throws Exception if has error
     */
    public final void testLatestUpdateSidebar() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));

        TextView mostFavoriteText = (TextView) solo.getView("tab_text", 0);
        boolean text = mostFavoriteText.getText().toString()
                .equals(ValidationText.NEWS);

        View latestUpdateIcon = (View) solo.getView("tab_image", 0);

        assertTrue("Latest update sidebar does not exist.",
                latestUpdateIcon.isShown() && text);

    }

    /**
     * 1938143:Check most favorite store side bar.
     * @throws Exception if has error
     */
    public final void testMostFavoriteSidebar() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));

        TextView mostFavoriteText = (TextView) solo.getView("tab_text", 1);
        boolean text = mostFavoriteText.getText().toString()
                .equals(ValidationText.FAVORITE_STORES);
        String texts = mostFavoriteText.getText().toString();
        Log.i("what", texts);
        View mostFavoriteIcon = (View) solo.getView("tab_image", 1);

        assertTrue("Most favorite sidebar does not exist.",
                mostFavoriteIcon.isShown() && text);

    }

    /**
     * 1938145:Check shopping Cart side bar.
     * @throws Exception if has error
     */
    public final void testShoppingCartSidebar() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));

        TextView shoppingCart = (TextView) solo.getView("tab_text" ,
                Action.VIEW_ID_THREE);
        boolean text = shoppingCart.getText().toString()
                .equals(ValidationText.SHOPPING_CART);

        View shoppingCartIcon = (View) solo.getView("tab_image" ,
                Action.VIEW_ID_THREE);

        assertTrue("Shopping Cart sidebar does not exist.",
                shoppingCartIcon.isShown() && text);

    }

    /**
     * 1938147:Check my account side bar.
     * @throws Exception if has error
     */
    public final void testMyAccountSidebar() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));

        TextView myAccount = (TextView) solo.getView("tab_text",
                Action.VIEW_ID_FOUR);
        boolean text = myAccount.getText().toString().trim()
                .equals(ValidationText.MY_ACCOUNT);

        String temp = myAccount.getText().toString();
        Log.i("what", temp);

        View myAccountIcon = (View) solo.getView("tab_image",
                Action.VIEW_ID_FOUR);

        assertTrue("My account sidebar does not exist.",
                myAccountIcon.isShown() && text);

    }

    /**
     * 1938149:Check 'Apparel' is displayed on the top of the screen.
     * @throws Exception if has error
     */
    public final void testDressDisplayedOnTheScreen() throws Exception {

        Action.enterCategoryClothesPage(solo);
        TextView dressText = (TextView) solo.getView("action_bar_title", 0);
        boolean text = dressText.getText().toString().trim()
                .equals(ValidationText.APPAREL);
        Log.i("what", dressText.getText().toString());
        assertTrue("Dress text does not exist.", text);

    }

    /**
     * 1938150:Check 'Beauty' is displayed on the top of the screen.
     * @throws Exception if has error
     */
    public final void testMakeupDisplayedOnTheScreen() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        solo.clickOnText(ValidationText.BEAUTY);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView makeupText = (TextView) solo.getView("action_bar_title", 0);
        boolean text = makeupText.getText().toString().trim()
                .equals(ValidationText.BEAUTY);
        assertTrue("Makeup text does not exist.", text);

    }

    /**
     * 1938151:Check 'Shoes/Bags/Accessories' is displayed on the top of the
     * screen.
     * @throws Exception if has error
     */
    public final void testAccessoriesDisplayedOnTheScreen() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        solo.clickOnText(ValidationText.SHOES_BAGS_ACCESSORIES);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView accessories = (TextView) solo.getView("action_bar_title", 0);
        boolean text = accessories.getText().toString().trim()
                .equals(ValidationText.SHOES_BAGS_ACCESSORIES);
        assertTrue("Accessories text does not exist.", text);

    }

    /**
     * 1938152:Check 'Mummy/Baby' is displayed on the top of the screen.
     * @throws Exception if has error
     */
    public final void testBabyDisplayedOnTheScreen() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        solo.searchText(ValidationText.COMPUTERS_PERIPHERALS);
        solo.clickOnText(ValidationText.MUMMY_BABY);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView baby = (TextView) solo.getView("action_bar_title", 0);
        boolean text = baby.getText().toString().trim()
                .equals(ValidationText.MUMMY_BABY);

        assertTrue("Mom's Baby text does not exist.", text);

    }

    /**
     * 1938153:Check 'Computers_Peripherals' is displayed on the top of the
     * screen.
     * @throws Exception if has error
     */
    public final void testComputerDisplayedOnTheScreen() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        solo.clickOnText(ValidationText.COMPUTERS_PERIPHERALS);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView computer = (TextView) solo.getView("action_bar_title", 0);
        boolean text = computer.getText().toString().trim()
                .equals(ValidationText.COMPUTERS_PERIPHERALS);

        assertTrue("Computer text does not exist.", text);

    }

    /**
     * 1938154:Check 'Homeappliances_AV' is displayed on the top of the screen.
     * @throws Exception if has error
     */
    public final void testHouseholdAppliancesDisplayedOnTheScreen()
             throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        solo.clickOnText(ValidationText.HOMEAPPLIANCES_AV);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView householdAppliances = (TextView) solo.getView(
                "action_bar_title", 0);
        boolean text = householdAppliances.getText().toString().trim()
                .equals(ValidationText.HOMEAPPLIANCES_AV);

        Log.i("what", householdAppliances.getText().toString());

        assertTrue("Computer text does not exist.", text);

    }

    /**
     * 1938155:Check 'Camera_Mobile_Toys' is displayed on the top of the screen.
     * @throws Exception if has error
     */
    public final void testFasionDigitalDisplayedOnTheScreen()
             throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        solo.searchText(ValidationText.GOURMET_HEALTH_BEVERAGE);
        solo.clickOnText(ValidationText.CAMERA_MOBILE_TOYS);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        TextView digital = (TextView) solo.getView("action_bar_title", 0);
        boolean text = digital.getText().toString().trim()
                .equals(ValidationText.CAMERA_MOBILE_TOYS2);
        Log.i("what", digital.getText().toString());
        assertTrue("Fasion digital text does not exist.", text);

    }

    /**
     * 1938156:Check 'GOURMET/Health/Beverage' is displayed on the top of the
     * screen.
     * @throws Exception if has error
     */
    public final void testFoodDisplayedOnTheScreen() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        solo.clickOnText(ValidationText.GOURMET_HEALTH_BEVERAGE);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView food = (TextView) solo.getView("action_bar_title", 0);
        boolean text = food.getText().toString().trim()
                .equals(ValidationText.GOURMET_HEALTH_BEVERAGE2);
        Log.i("what", food.getText().toString().trim());
        assertTrue("Food text does not exist.", text);

    }

    /**
     * 1938157:Check 'Medical/Commodity/Pet' is displayed on the top of the
     * screen.
     * @throws Exception if has error
     */
    public final void testCleanDisplayedOnTheScreen() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        solo.clickOnText(ValidationText.MEDICAL_COMMODITY_PET);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView clean = (TextView) solo.getView("action_bar_title", 0);
        boolean text = clean.getText().toString().trim()
                .equals(ValidationText.MEDICAL_COMMODITY_PET2);

        assertTrue("Clean text does not exist.", text);

    }

    /**
     * 1938158:Check 'Home/Bedding/Furniture' is displayed on the top of the
     * screen.
     * @throws Exception if has error
     */
    public final void testHomeDisplayedOnTheScreen() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        solo.scrollToBottom();
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.scrollToBottom();
        solo.searchText(ValidationText.HOME_BEDDING_FURNITURE);
        solo.clickOnText(ValidationText.HOME_BEDDING_FURNITURE);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        TextView home = (TextView) solo.getView("action_bar_title", 0);
        boolean text = home.getText().toString().trim()
                .equals(ValidationText.HOME_BEDDING_FURNITURE2);

        assertTrue("home text does not exist.", text);

    }

    /**
     * 1938160:Check 'Books/STATIONERY/Video' is displayed on the top of the
     * screen.
     * @throws Exception if has error
     */
    public final void testBookDisplayedOnTheScreen() throws Exception {

        solo.clickOnView(solo.getView("tab_image", 2));
        solo.clickOnText(ValidationText.BOOKS_STATIONERY_VIDEO);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);

        TextView book = (TextView) solo.getView("action_bar_title", 0);
        boolean text = book.getText().toString().trim()
                .equals(ValidationText.BOOKS_STATIONERY_VIDEO2);
        assertTrue("book text does not exist.", text);

    }

    /**
     * 1938103:Check to click the start icon without login.
     * @throws Exception if has error
     */
    public final void testStarIconWithoutLogin() throws Exception {

        Account.judgementAccountWithoutLogin(solo);
        Action.enterCategoryClothesPage(solo);
        Action.clickText(solo, ValidationText.COMMODITY);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        View star = (View) solo.getView("star_button", 0);
        solo.clickOnView(star);

        // Get toast text.
        TextView toastTextView = (TextView) solo.getView("message", 0);
        if (toastTextView != null) {
            String toastText = toastTextView.getText().toString();
            assertEquals(toastText, ValidationText.PLEASE_LOGIN_ACCOUNT);
        }

    }

    /**
     * 1938104:Check to click the start icon when login.
     * @throws Exception if has error
     */
    public final void testStartIconWhenLogin() throws Exception {

        Account.judgementAccountLogin(solo);
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
        
      /*  // Select two item to compare the position.
        boolean flag = TestHelper.positionCompare(solo,
                ValidationText.COSTUMELIST[0], 1,
                ValidationText.COSTUMELIST[1], 2, 1);
        assertTrue(
                "Item position is not right,need confirm the"
        + " default browse mode.",
                flag);*/

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

        assertTrue("The default tab is incorrect.", btnSort.isShown());

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
        Action.clickText(solo, ValidationText.EIGHTEEN_AREA);
        TextView restrictNote = (TextView) solo
                .getView("restrict_category_bottom_text");
        assertTrue("Restrict note not exist.", restrictNote.isShown());

    }

    /**
     * 1938159:Verify Leisure / traffic tab correctly displayed on the page.
     * @throws Exception if has error
     */
    public final void testLeisureTrafficTabDisplayed() throws Exception {

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

        // Get "0 Interest" button.
        ToggleButton tb = (ToggleButton) solo.getView("tb_cczeroint");

        solo.clickOnView(tb);
        Action.clickText(solo, ValidationText.OK);
        assertFalse("Back to search result page failed.", tb.isShown());

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
        solo.scrollToBottom();
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
