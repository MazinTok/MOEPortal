package sa.gov.moe.he.moeportal;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ShawNewsActivity2Activity extends ActionBarActivity {

    String newsTitle;
    String NewsContent;
    String NewsUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaw_news_activity2);


        setTitle( R.string.news);
        ActionBar bar = this.getSupportActionBar();
        //bar.setTitle(getIntent().getStringExtra("titel"));
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF346266")));

        newsTitle = getIntent().getStringExtra("newstitel");
        NewsContent = getIntent().getStringExtra("content");
        NewsUrl = getIntent().getStringExtra("URL");
        TextView titleTxt = (TextView) findViewById(R.id.newsTitTxt);
        TextView contentTxt = (TextView) findViewById(R.id.newsContTxt);

        titleTxt.setText(newsTitle);
        contentTxt.setText(NewsContent);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shaw_news_activity2, menu);
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
