package com.yahoo.mobile.client.android.ecstore.test.Sidebar;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Action.Action;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

@SuppressLint("NewApi")
public class Sidebar extends ActivityInstrumentationTestCase2 {
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.yahoo.mobile.client.android.ecstore.ui.ECSplashActivity";
	private static Class launcherActivityClass;
	private Solo solo;
	static {

		try {
			launcherActivityClass = Class
					.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

	@SuppressWarnings("unchecked")
	public Sidebar() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
		// Assert.testFirstLaunch(solo);

	}

	@Override
	public void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

	// 1959892:Verify user can edit category preferences.
	public void testEditCategorypreferences() throws Exception {

		Account.JudgementAccountLogin(solo);
		// click on up icon
		Action.clickHomeButtonOnScreen(solo);
		solo.clickOnText(ValidationText.Edit_Favorite_Category);

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

	// 1977532:verify settings screen.
	public void testSettingsButton() throws Exception {
		
		Account.JudgementAccountLogin(solo);
		// click on up icon		
		Action.clickHomeButtonOnScreen(solo);
		Action.clickText(solo, ValidationText.Setting);
		
		//Recent browse text.
		TextView recent = (TextView)solo.getView("title",3);
		assertTrue("Cannot fount recent browse text.",recent.getText().toString().trim().equals(ValidationText.Recent_Browse));
		
		//Get the toggle button status.
		Switch browse_History = (Switch)solo.getView("switchWidget",1);
		assertTrue("Notification switch is off", browse_History.isChecked());
		
		//Disable the toggle button and go to browse product.
		solo.clickOnView(browse_History);
		solo.sleep(1000);
		Action.clickText(solo, ValidationText.Clean_Browse_Record);
		Action.clickText(solo, ValidationText.OK);
		solo.goBack();
		Action.enterToItemPage(solo);
		solo.clickOnView(solo.getView("tab_image", 4));
		solo.sleep(2000);
		Action.clickText(solo, ValidationText.Recent_Browse);
		solo.sleep(15000);
		TextView no_Result = (TextView)solo.getView("no_result_text",1);	
		assertTrue("Exist some browse record displayed",no_Result.isShown());
		solo.goBack();
		
		//Search  product.
		solo.goBack();
		Action.clickSearchButtonOnScreen(solo);
		Action.searchAfterPutData(solo, 0, "a");
		solo.sleep(1000);
		solo.goBack();
		solo.sleep(1000);
		Action.clickHomeButtonOnScreen(solo);
		Action.clickText(solo, ValidationText.Setting);
		Switch browse_Historys = (Switch)solo.getView("switchWidget",1);
		solo.clickOnView(browse_Historys);
		Action.clickText(solo, ValidationText.Clear_Search_History);
		Action.clickText(solo, ValidationText.OK);
		solo.goBack(); 
		Action.clickSearchButtonOnScreen(solo);
		boolean icon = false;
		try{
			View Plugs = (View)solo.getView("search_fill_up",1);
			assertFalse("Search history exist.",Plugs.isShown());
 		}catch(AssertionError e){
			icon =true;
			assertTrue("Search history exist.",icon);
		}
		
		 
		
	}
}
