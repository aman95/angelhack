package hackathon.angelhack.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import hackathon.angelhack.R;

/**
 * Created by nitin on 21/6/15.
 */
public class BooksSellVH extends RecyclerView.ViewHolder {

    //ImageView drawerListIcon;
    TextView bookName;
    TextView bookAuthor;
    TextView bookPrice;
    //RelativeLayout drawerListContainer;

    public BooksSellVH(View itemView) {
        super(itemView);
        this.bookName = (TextView) itemView.findViewById(R.id.bookName);
        this.bookAuthor = (TextView) itemView.findViewById(R.id.bookAuthor);
        this.bookPrice = (TextView) itemView.findViewById(R.id.price);
        //drawerListContainer = (RelativeLayout) itemView.findViewById(R.id.container);
    }
}