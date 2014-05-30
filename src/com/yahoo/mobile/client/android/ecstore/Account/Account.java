package com.yahoo.mobile.client.android.ecstore.Account;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

public class Account {
	
	//Over 18 yeas old account login .
	public static void overAccountLogIn(Solo solo) throws Exception{

		
		solo.clickOnView(solo.getView("tab_image",4));
		
		boolean newAccount = solo.searchText(ValidationText.New_Add_Account);
		if(newAccount){
			
			solo.goBack();
			removeAccount(solo);
		}
		
		//Input Yahoo account in account text field.
		solo.typeText(0, "mobileappstore3");
		
		//Input Yahoo password in password text field.
		solo.typeText(1, "A1234qwer");
	 
		//Click sign button
		solo.clickOnButton(ValidationText.Log_In);
		solo.sleep(15000);
		solo.waitForView(solo.getView("tab_image",4));
		solo.clickOnView(solo.getView("tab_image",4));
		
		//Assert if the accountProfile is visible,then login successfully.
		View accountProfile = (View)solo.getView("profile_photo_image",0);	
		junit.framework.Assert.assertTrue("Log in failed.",accountProfile.isShown());
	 
	}
	
	//Under 18 years of age account login.
	public static void underAccountLogIn(Solo solo) throws Exception{

			
			solo.clickOnView(solo.getView("tab_image",4));
			
			boolean newAccount = solo.searchText(ValidationText.New_Add_Account);
			if(newAccount){
				
				solo.goBack();
				removeAccount(solo);
			}
			
			//Input Yahoo account in account text field.
			solo.typeText(0, "mobileappstore");
			
			//Input Yahoo password in password text field.
			solo.typeText(1, "A1234qwer");
		 
			//Click sign button
			solo.clickOnButton(ValidationText.Log_In);
			solo.sleep(10000);
			solo.clickOnView(solo.getView("tab_image",4));
			
			//Assert if the accountProfile is visible,then login successfully.
			View accountProfile = (View)solo.getView("profile_photo_image",0);	
			junit.framework.Assert.assertTrue("Log in failed.",accountProfile.isShown());
		 
		}
		
	//Account Log out.
	public static void accountLogOut(Solo solo) throws Exception{
		
		solo.clickOnView(solo.getView("tab_image",4));
		solo.sleep(1000);
		solo.clickOnView(solo.getView("profile_photo_image"));
		
		/*Old version log out button.
		 * solo.clickOnButton(ValidationText.Log_Out);
		 * */
 
		//Assert the Logout confirm dialog whether is show.
		View logoutComfirmDialog = (View)solo.getView("parentPanel",0);	
		junit.framework.Assert.assertTrue("Logout confirm dialog not show.",logoutComfirmDialog.isShown());
			
		solo.clickOnButton(ValidationText.OK);
		solo.sleep(2000);	
		junit.framework.Assert.assertFalse("Logout confirm dialog is show.",logoutComfirmDialog.isShown());
			 
	}
		
	//Remove account
	public static void removeAccount(Solo solo) throws Exception{
		
		solo.clickOnView(solo.getView("tab_image",4));
		solo.sleep(2000);
 
		//Click on the button in the upper right-hand corner
		ImageView editButton = (ImageView)solo.getView("edit_account_button");
		solo.clickOnView(editButton);
	
		//Get remove text from menu and click it.
		TextView removeAccountText = (TextView)solo.getView("dropdown_remove");
		solo.clickOnView(removeAccountText);
		
		//Get check box and select it.
		CheckBox cb = (CheckBox)solo.getView("checkbox");
		solo.clickOnView(cb);
		
		//Get remove button from upper right-hand corner and click it.
		TextView comfirmRemoveAccount = (TextView)solo.getView("remove_account_button");
		solo.clickOnView(comfirmRemoveAccount);
		 
		//Get remove button from pop up dialog.
		Button removeButton = (Button)solo.getView("button1");
		Log.i("what", removeButton.getText().toString().trim());
		junit.framework.Assert.assertTrue("Remove account comfirm dialog not show.",removeButton.isShown());

		//The last time to confirm .
		solo.clickOnView(removeButton);
		solo.sleep(2000);
		
		//Assert the remove button whether exists.
		junit.framework.Assert.assertFalse("Remove account failed.",removeButton.isShown());

	}
	
	//Account 
	public static void JudgementAccountWithoutLogin(Solo solo) throws Exception{
		
		solo.clickOnView(solo.getView("tab_image",4));
		solo.sleep(2000);
		
		//Search "建立帳號" aims to verify that not any account log in.
		boolean createAccount = solo.searchText(ValidationText.Create_Account);
		
		//Search "新增帳號" aims to verify that hava account exist but not log in.
		boolean newAccount = solo.searchText(ValidationText.New_Add_Account);
		
		//if createAccount or newAccount button can be found,we can make sure that account status is not log in.
	    if(createAccount ||newAccount ){
	    	solo.goBack();
	    }else{
	    	accountLogOut(solo);
	    } 
	    
	}
	
	//Login if not account or account not login.
	public static void JudgementAccountLogin(Solo solo) throws Exception{
		
		solo.clickOnView(solo.getView("tab_image",4));
		solo.sleep(2000);
		
		//Search "建立帳號" aims to verify that not any account log in.
		boolean createAccount = solo.searchText(ValidationText.Create_Account);
		
		//Search "新增帳號" aims to verify that hava account exist but not log in.
		boolean newAccount = solo.searchText(ValidationText.New_Add_Account);
		
		//if createAccount or newAccount button can be found,we can make sure that account status is not log in.
	    if(createAccount){     	
	    	solo.goBack();
	    	overAccountLogIn(solo);
	    }else if(newAccount){
	    	 ImageView profile = (ImageView)solo.getView("imageProfile");
	    	 solo.clickOnView(profile);
	    	 solo.sleep(5000);
	    }else{
	    	solo.goBack();
	    } 
	    
	}
}
