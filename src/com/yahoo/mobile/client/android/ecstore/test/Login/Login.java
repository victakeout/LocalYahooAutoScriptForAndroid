/*
 * This is automated script about "Login".
 * 
 * You can run these test cases either on the emulator or on device. 
 * By Eclipse:
 * Right click the test project and select Run As --> Run As Android JUnit Test
 * By Ant:
 * 1.Run "android update test-project -m [path to target application] -p [path to the test folder]"  in command line .
 * 2."ant test"
 * By using instrument command:
 * Run all test project:adb shell am instrument -w com.yahoo.mobile.client.android.ecstore.test/android.test.InstrumentationTestRunner
 * Just run Login:adb shell am instrument -e class com.yahoo.mobile.client.android.ecstore.test.Login.Login -w com.yahoo.mobile.client.android.ecstore.test/android.test.InstrumentationTestRunner
 * 
 * @author SYMBIO.
 * @version YAHOO APP:1.2.4
 * 
 */

package com.yahoo.mobile.client.android.ecstore.test.Login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.yahoo.mobile.client.android.ecstore.Account.Account;
import com.yahoo.mobile.client.android.ecstore.Assert.Assert;

@SuppressLint("NewApi")
public class Login extends ActivityInstrumentationTestCase2<Activity> {
	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.yahoo.mobile.client.android.ecstore.ui.ECSplashActivity";
	private static Class<?> launcherActivityClass;
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
	public Login() throws ClassNotFoundException {
		super((Class<Activity>)launcherActivityClass);
	}

	@Override
	protected void setUp() throws Exception {

		solo = new Solo(getInstrumentation(), getActivity());
		Assert.testFirstLaunch(solo);
	}

	@Override
	public void tearDown() throws Exception {

		solo.finishOpenedActivities();
		super.tearDown();
	}

	// 1977501:verify login and Logout of interaction
	public void testLoginAndLogoutInteraction()throws Exception {
		
		Account.JudgementAccountLogin(solo);
		Account.accountLogOut(solo);
		Account.JudgementAccountWithoutLogin(solo);
	}

}