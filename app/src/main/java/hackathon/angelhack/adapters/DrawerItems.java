package hackathon.angelhack.adapters;

import java.util.ArrayList;
import java.util.List;

import hackathon.angelhack.R;

/**
 * Created by aman on 12/5/15.
 */
public class DrawerItems {
    String drawerItemName;
    int drawerListIconId;

    public static List<DrawerItems> getItem() {
        List<DrawerItems> data = new ArrayList<>();
        int[] iconId = {R.drawable.ic_circles,R.drawable.ic_track_friends,R.drawable.ic_events,R.drawable.ic_settings};
        String[] itemName = {"Sell Books","Buy Books","My Profile","History"};
        for(int i=0;i<itemName.length && i<iconId.length;i++){
            DrawerItems current = new DrawerItems();
            current.drawerItemName = itemName[i];
            current.drawerListIconId = iconId[i];
            data.add(current);
        }
        return data;
    }
}
