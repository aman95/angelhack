package hackathon.angelhack.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hackathon.angelhack.R;

/**
 * Created by nitin on 21/6/15.
 */
public class BooksSellAdapter extends RecyclerView.Adapter<BooksSellVH>{
    private List<BooksSellItems> feedItemList;

    private Context mContext;

    public BooksSellAdapter(Context context, List<BooksSellItems> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;


    }


    @Override
    public BooksSellVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_sell_item, parent, false);
        BooksSellVH mh = new BooksSellVH(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(BooksSellVH holder, int position) {

        BooksSellItems feedItem = feedItemList.get(position);

        //holder.companyLogo.setImageUrl("http://10.100.86.148/payuapi/public/logos/"+feedItem.getId()+".png", AppController.getInstance().getImageLoader());

        holder.bookName.setText(feedItem.getBookName());
        holder.bookAuthor.setText(feedItem.getAuthor());
        holder.bookPrice.setText(feedItem.getPrice());
    }

    @Override
    public int getItemCount() {


        return (null != feedItemList ? feedItemList.size() : 0);
    }

}
