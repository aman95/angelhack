package hackathon.angelhack;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by nitin on 21/6/15.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "HKnr3F6mF4rpAdqFReoHPzQdSnhmIUTZJST3n6tZ", "GNsd16YRvZYYY4tWfNS03gPmmBrajb8YzfIsatoJ");

    }
}
