package dolphine.designs.shoppinglist;

import android.app.Application;

public class CurrentUser extends Application {

	public static CurrentUser currentUser = null;
	
	public static String name, email, gender, jobTitle;
	public static int age, moneyAvailable;
	
	@Override
	public void onCreate() {
		super.onCreate();
		currentUser = this;
	}
	
	public static CurrentUser getInstance() {
		if (currentUser == null) {
			currentUser = new CurrentUser();
		}
		return currentUser;
	}

}
