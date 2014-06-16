package com.yahoo.mobile.client.android.ecstore.Account;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.test.ValidationText;

/**
 * Contains account login,logout,and account status methods examples is
 * JudgementAccountWithoutLogin().
 * @author SYMBIO
 **/

public final class Account {

    /**
     * private constructor.
     */

    private Account() {

    }

    /**
     * This is elements view ID.
     */
    public static final int VIEW_ID = 4;

    /**
     * Over 18 yeas old account login.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void overAccountLogIn(final Solo solo) throws Exception {
        solo.clickOnView(solo.getView("tab_image", VIEW_ID));

        boolean newAccount = solo.searchText(ValidationText.NEW_ADD_ACCOUNT);
        if (newAccount) {

            solo.goBack();
            removeAccount(solo);
        }

        // Input YAHOO account in account text field.
        solo.typeText(0, "mobileappstore3");

        // Input YAHOO password in password text field.
        solo.typeText(1, "A1234qwer");

        // Click sign button
        solo.clickOnButton(ValidationText.LOG_IN);
        solo.sleep(ValidationText.WAIT_TIME_LONGER);
        solo.waitForView(solo.getView("tab_image", VIEW_ID));
        solo.clickOnView(solo.getView("tab_image", VIEW_ID));

        // Assert if the accountProfile is visible,then login successfully.
        View accountProfile = (View) solo.getView("profile_photo_image", 0);
        junit.framework.Assert.assertTrue("Log in failed.",
                accountProfile.isShown());

    }

    /**
     * Under 18 years of age account login.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void underAccountLogIn(final Solo solo) throws Exception {

        solo.clickOnView(solo.getView("tab_image", VIEW_ID));

        boolean newAccount = solo.searchText(ValidationText.NEW_ADD_ACCOUNT);
        if (newAccount) {

            solo.goBack();
            removeAccount(solo);
        }

        // Input YAHOO account in account text field.
        solo.typeText(0, "mobileappstore");

        // Input YAHOO password in password text field.
        solo.typeText(1, "A1234qwer");

        // Click sign button
        solo.clickOnButton(ValidationText.LOG_IN);
        solo.sleep(ValidationText.WAIT_TIME_LONG);
        solo.clickOnView(solo.getView("tab_image", VIEW_ID));

        // Assert if the accountProfile is visible,then login successfully.
        View accountProfile = (View) solo.getView("profile_photo_image", 0);
        junit.framework.Assert.assertTrue("Log in failed.",
                accountProfile.isShown());

    }

    /**
     * Account Log out.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void accountLogOut(final Solo solo) throws Exception {

        solo.clickOnView(solo.getView("tab_image", VIEW_ID));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("profile_photo_image"));

        // Assert the Logout confirm dialog whether is show.
        View logoutComfirmDialog = (View) solo.getView("parentPanel", 0);
        junit.framework.Assert.assertTrue("Logout confirm dialog not show.",
                logoutComfirmDialog.isShown());

        solo.clickOnButton(ValidationText.OK);
        solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        junit.framework.Assert.assertFalse("Logout confirm dialog is show.",
                logoutComfirmDialog.isShown());

    }

    /**
     * Remove account.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void removeAccount(final Solo solo) throws Exception {

        solo.clickOnView(solo.getView("tab_image", VIEW_ID));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Click on the button in the upper right-hand corner
        ImageView editButton = (ImageView) solo.getView("edit_account_button");
        solo.clickOnView(editButton);

        // Get remove text from menu and click it.
        TextView removeAccountText = (TextView) solo.getView("dropdown_remove");
        solo.clickOnView(removeAccountText);

        // Get check box and select it.
        CheckBox cb = (CheckBox) solo.getView("checkbox");
        solo.clickOnView(cb);

        // Get remove button from upper right-hand corner and click it.
        TextView comfirmRemoveAccount = (TextView) solo
                .getView("remove_account_button");
        solo.clickOnView(comfirmRemoveAccount);

        // Get remove button from pop up dialog.
        Button removeButton = (Button) solo.getView("button1");
        Log.i("what", removeButton.getText().toString().trim());
        junit.framework.Assert.assertTrue(
                "Remove account comfirm dialog not show.",
                removeButton.isShown());

        // The last time to confirm .
        solo.clickOnView(removeButton);
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Assert the remove button exists.
        junit.framework.Assert.assertFalse("Remove account failed.",
                removeButton.isShown());

    }

    /**
     * Judgment account whether logout.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void judgementAccountWithoutLogin(final Solo solo)
            throws Exception {

        solo.clickOnView(solo.getView("tab_image", VIEW_ID));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Search "Set up account" aims to verify that not any account log in.
        boolean createAccount = solo.searchText(ValidationText.CREATE_ACCOUNT);

        /*
         * Search "New account" aims to verify that have account exist but not
         * log in.
         */
        boolean newAccount = solo.searchText(ValidationText.NEW_ADD_ACCOUNT);

        /*
         * if createAccount or newAccount button can be found,we can make sure
         * that account status is not log in.
         */
        if (createAccount || newAccount) {
            junit.framework.Assert.assertTrue("Account has login",
                    createAccount || newAccount);
            solo.goBack();
        } else {
            accountLogOut(solo);
        }

    }

    /**
     * Login if not account or account not login.
     * @param solo the Solo instance
     * @throws Exception if has error
     */
    public static void judgementAccountLogin(final Solo solo) throws Exception {

        solo.sleep(ValidationText.WAIT_TIME_SHORT);
        solo.clickOnView(solo.getView("tab_image", VIEW_ID));
        solo.sleep(ValidationText.WAIT_TIME_SHORT);

        // Search "Set up account" aims to verify that not any account log in.
        boolean createAccount = solo.searchText(ValidationText.CREATE_ACCOUNT);

        /*
         * Search "New account" aims to verify that have account exist but not
         * log in.
         */
        boolean newAccount = solo.searchText(ValidationText.NEW_ADD_ACCOUNT);

        /*
         * if createAccount or newAccount button can be found,we can make sure
         * that account status is not log in.
         */
        if (createAccount) {

            solo.goBack();
            try {
                overAccountLogIn(solo);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (newAccount) {
            ImageView profile = (ImageView) solo.getView("imageProfile");
            solo.clickOnView(profile);
            solo.sleep(ValidationText.WAIT_TIME_MIDDLE);
        } else {
            solo.goBack();
        }

    }

 }

