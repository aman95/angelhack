package hackathon.angelhack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class BookScan extends AppCompatActivity {

    String isbn;
    TextView bookName;
    TextView bookAuthor;
    TextView bookIsbn;
    TextView bookLang;
    TextView bookPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_scan);

        Intent i = getIntent();
        isbn = i.getStringExtra("isbn");

        bookName = (TextView)findViewById(R.id.book_name);
        bookAuthor = (TextView)findViewById(R.id.book_author);
        bookLang = (TextView)findViewById(R.id.book_lang);
        bookIsbn = (TextView)findViewById(R.id.book_isbn);
        bookPages = (TextView)findViewById(R.id.book_pages);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("books");
        query.whereEqualTo("isbn1", isbn);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> bookList, ParseException e) {
                if (e == null) {
                    if(bookList.size() == 0) {
                        Toast.makeText(getApplicationContext(),"Not data fetched",Toast.LENGTH_SHORT).show();
                        Log.d("score", "Retrieved " + bookList.size() + " scores");
                    } else {

                        bookName.setText(bookList.get(0).getString("name"));
                        bookAuthor.setText(bookList.get(0).getString("author"));
                        bookLang.setText(bookList.get(0).getString("lang"));
                        bookPages.setText(bookList.get(0).getString("pages"));
                        bookIsbn.setText(isbn);
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Error Occured: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_scan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
