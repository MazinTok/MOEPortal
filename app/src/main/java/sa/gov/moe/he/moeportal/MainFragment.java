package sa.gov.moe.he.moeportal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sa.gov.moe.he.moeportal.DataType.News;
import sa.gov.moe.he.moeportal.DataType.PrepSharedPreferences;

/**
 * Created by mazoo_000 on 23/04/2015.
 */

public class MainFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    ListView listview;
    TextView Newstxt;
    List<News> NewsResList;
    ProgressBar Pbar2;
    ImageView imageViewNews;
    PrepSharedPreferences shredData;
//buttons
private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
    ImageButton Youtube;
    ImageButton Twitter;
    ImageButton Facebook;
    ImageButton Safeerstd;
    ImageButton SafeerGrad;
    ImageButton Rased;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MainFragment newInstance(int sectionNumber) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        listview = (ListView) rootView.findViewById(R.id.listView);
        Newstxt = (TextView) rootView.findViewById(R.id.Newstxt);
        Pbar2 = (ProgressBar) rootView.findViewById(R.id.progressBar2);
        imageViewNews = (ImageView) rootView.findViewById(R.id.imageViewNews);

        Twitter = (ImageButton) rootView.findViewById(R.id.TwitterBtn);
        Twitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TwitterPressed(v);
                v.startAnimation(buttonClick);
            }
        });
        Youtube = (ImageButton) rootView.findViewById(R.id.YoutubeBtn);
        Youtube.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                YoutubePressed(v);
                v.startAnimation(buttonClick);
            }
        });
        Facebook = (ImageButton) rootView.findViewById(R.id.FacebookBtn);
        Facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FacebookPressed(v);
                v.startAnimation(buttonClick);
            }
        });

        SafeerGrad = (ImageButton) rootView.findViewById(R.id.SafeerGradbtn);
        SafeerGrad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SafeergradPressed(v);
                v.startAnimation(buttonClick);
            }
        });
        Safeerstd = (ImageButton) rootView.findViewById(R.id.SafeerStdBtn);
        Safeerstd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SafeerstdPressed(v);
                v.startAnimation(buttonClick);
            }
        });
        Rased = (ImageButton) rootView.findViewById(R.id.rasedBtn);
        Rased.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RasedPressed(v);
                v.startAnimation(buttonClick);
            }
        });



        shredData = new PrepSharedPreferences(getActivity().getApplicationContext());

        NewsResList = shredData.getNewsvalues();
        CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.fucklistrow,   PrepareListViewssListData(NewsResList));
        listview.setAdapter(adapter);

        // setting list adapter

        new LongOperation().execute("s");


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent;
                intent = new Intent(getActivity(),ServiceWebViewActivity.class);
                String NewsUrl = (String) NewsResList.get(position).getUrl();

                intent.putExtra("titel",getString( R.string.news) );
                NewsUrl = NewsUrl.replace("SPSite Url=", "");
                NewsUrl = "http://www.mohe.gov.sa/ar/Mobile/Det.aspx?URL=" + NewsUrl.substring(NewsUrl.lastIndexOf("sa") + 2);
                intent.putExtra("URL", NewsUrl);
                startActivity(intent);


            }
        });

        return rootView;
    }
    public String[] PrepareListViewssListData(List<News> result ) {

        if (result != null ) {
            String[] values = new String[result.size()];

            List<String> list = new ArrayList<>();
            for (int i = 0; i < result.size(); ++i) {
                list.add(result.get(i).getTxt());
                values[i] = result.get(i).getTxt();
            }
            return values;
        }
        else
        {
            return null;
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private class LongOperation extends  AsyncTask<String, Void, List<News>> {
        @Override
        protected List<News> doInBackground(String... params) {

            HTMLRemoverParser ne = new HTMLRemoverParser();

            return   ne.HTMLRemoverParser("https://www.mohe.gov.sa/_layouts/15/feed.aspx?xsl=1&web=/ar/news&page=323b90f5-3fbb-4ba4-aa85-3365d92e55a9&wp=32427045-6290-45e4-b091-9a619510fdca&pageurl=RSSPages/ForRSS.aspx");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (listview == null )
                Pbar2.setVisibility(ProgressBar.VISIBLE);

        }

        protected void onPostExecute(List<News> result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog

            Pbar2.setVisibility(ProgressBar.GONE);

            if (result != null) {
                NewsResList = result;
                shredData.putNewsValue(NewsResList);
               if (getActivity() != null) {
                    CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.fucklistrow, PrepareListViewssListData(result));
                    listview.setAdapter(adapter);
                }
            }
            else
            {
                Toast.makeText(getActivity(), "لا يمكن الاتصال بالانترنت",
                        Toast.LENGTH_SHORT).show();

            }

        }
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        }
        catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    public void TwitterPressed(View v)
    {

        boolean whatsappFound = isAppInstalled(getActivity(), "com.twitter.android");
        //getPackageManager().getPackageInfo("com.twitter.android", 0);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=141477247"));
        if (whatsappFound) {

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=141477247"));
        }
        else{

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/mohe_sa"));
        }
        startActivity(intent);

    }
    public void YoutubePressed(View v)
    {

        boolean whatsappFound = isAppInstalled(getActivity(), "com.google.android.youtube");
        //getPackageManager().getPackageInfo("com.twitter.android", 0);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=141477247"));
        if (whatsappFound) {

            intent = new Intent(Intent.ACTION_VIEW,  Uri.parse("http://www.youtube.com/user/SaudiMOHE"));
        }
        else{

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/SaudiMOHE"));
        }
        startActivity(intent);
    }
    public void FacebookPressed(View v)
    {

        boolean whatsappFound = isAppInstalled(getActivity(), "com.facebook.katana");

        Intent intent ;
        if (whatsappFound) {

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/117414094957679"));
        }
        else{

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/%D9%88%D8%B2%D8%A7%D8%B1%D8%A9-%D8%A7%D9%84%D8%AA%D8%B9%D9%84%D9%8A%D9%85-%D8%A7%D9%84%D8%B9%D8%A7%D9%84%D9%8A/117414094957679"));
        }
        startActivity(intent);

    }
    public void SafeergradPressed(View v)
    {

        boolean whatsappFound = isAppInstalled(getActivity(), "com.mohe.safeeralumni");

        Intent intent ;//
        if (whatsappFound) {

            intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.mohe.safeeralumni");

        }
        else{

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.mohe.safeeralumni"));
        }
        startActivity(intent);

    }
    public void SafeerstdPressed(View v)
    {

        boolean whatsappFound = isAppInstalled(getActivity(), "info.mohe.slidingmenu");

        Intent intent ;
        if (whatsappFound) {
             intent = getActivity().getPackageManager().getLaunchIntentForPackage("info.mohe.slidingmenu");

        }
        else{

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=info.mohe.slidingmenu"));
        }
        startActivity(intent);

    }
    public void RasedPressed(View v)
    {

        boolean whatsappFound = isAppInstalled(getActivity(), "com.ohev1");

        Intent intent ;
        if (whatsappFound) {

            intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.ohev1");
        }
        else{

            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.ohev1"));
        }
        startActivity(intent);

    }

}