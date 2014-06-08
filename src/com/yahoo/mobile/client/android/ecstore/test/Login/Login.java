package com.yahoo.mobile.client.android.ecstore.test.Login;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;

@SuppressLint("NewApi")
public class Login extends ActivityInstrumentationTestCase2 {
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

	public Login() throws ClassNotFoundException {
		super(launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {

		solo = new Solo(getInstrumentation(), getActivity());
		//Assert.testFirstLaunch(solo);
	}

	@Override
	public void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

	// 1977501:verify login and Logout of interaction.
	public void testLoginAndLogoutInteraction()throws Exception {
		
		Account.JudgementAccountLogin(solo);
		Account.accountLogOut(solo);
		Account.JudgementAccountWithoutLogin(solo);
	}

}
