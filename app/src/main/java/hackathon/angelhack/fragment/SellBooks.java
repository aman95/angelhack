package hackathon.angelhack.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import hackathon.angelhack.BookScan;
import hackathon.angelhack.R;
import hackathon.angelhack.adapters.BooksSellAdapter;
import hackathon.angelhack.adapters.BooksSellItems;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Circles.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Circles#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellBooks extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE = "Buy Books";
    private static final String ARG_PARAM2 = "param2";


    private int mPosition;
    private String mUsername;

    protected List<BooksSellItems> feedItems = new ArrayList<>();

    RecyclerView sellItems;

    String toast;
    String isbn;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Circles.
     */
    // TODO: Rename and change types and number of parameters
    public static SellBooks newInstance(int param1, String param2) {
        SellBooks fragment = new SellBooks();
        Bundle args = new Bundle();
        args.putInt(TITLE, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SellBooks() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(TITLE);
            mUsername = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_sell_books, container, false);

        sellItems = (RecyclerView)rootView.findViewById(R.id.selling_list);
        sellItems.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        sellItems.setLayoutManager(llm);
        final BooksSellAdapter adapter = new BooksSellAdapter(getActivity().getApplicationContext(), feedItems);
        sellItems.setAdapter(adapter);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("sell");
        query.whereEqualTo("user_email", ParseUser.getCurrentUser().getEmail());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> bookList, ParseException e) {
                if (e == null) {
                    if(bookList.size() == 0) {
                        Toast.makeText(getActivity().getApplicationContext(),"Not data fetched",Toast.LENGTH_SHORT).show();
                        Log.d("score", "Retrieved " + bookList.size() + " scores");
                    } else {

                        for(int i=0;i<bookList.size();i++) {
                            BooksSellItems feedItem = new BooksSellItems(bookList.get(i).getString("name"),bookList.get(i).getString("author"),bookList.get(i).getString("price"));
                            feedItems.add(feedItem);
                        }
//                        bookName.setText(bookList.get(0).getString("name"));
//                        bookAuthor.setText(bookList.get(0).getString("author"));
//                        bookLang.setText(bookList.get(0).getString("lang"));
//                        bookPages.setText(bookList.get(0).getString("pages"));
//                        bookIsbn.setText(isbn);
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                    Toast.makeText(getActivity().getApplicationContext(), "Error Occured: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

                    adapter.notifyDataSetChanged();



//        final TextView tv = (TextView)rootView.findViewById(R.id.textView);
//        tv.setText("Position = "+mPosition+" Username: "+mUsername);


        com.getbase.floatingactionbutton.FloatingActionButton scanBtn = (com.getbase.floatingactionbutton.FloatingActionButton) rootView.findViewById(R.id.btnScan);
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator.forSupportFragment(SellBooks.this).initiateScan();
                //Toast.makeText(getActivity(),"fsdfsfsdf",Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private void displayToast() {
        if(getActivity() != null && toast != null) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
            toast = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                toast = "Cancelled from fragment";
            } else {
                toast = "Scanned from fragment: " + result.getContents();
                displayToast();
                Intent i = new Intent(getActivity(), BookScan.class);
                i.putExtra("isbn",result.getContents());
                getActivity().startActivityForResult(i,1);
            }

        }
        if(requestCode == 22) {
//            isbn = data.getStringExtra("isbn")

        }
    }

//    public void scanBarcode(View v) {
//        new IntentIntegrator(this.getActivity()).initiateScan();
//    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    //
//
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
