package sa.gov.moe.he.moeportal;

import android.app.Activity;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sa.gov.moe.he.moeportal.DataType.News;
import sa.gov.moe.he.moeportal.DataType.PrepSharedPreferences;

/**
 * Created by mtokhais on 5/11/2015.
 */



public class EventsFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";



    ListView EventListView;
    TextView Eventstxt;
    List<News> EventsResList;
    ProgressBar Pbar2;
    ProgressBar Pbar3;
    ImageView imageViewEvent;

    PrepSharedPreferences shredData;

    private int Month;


    public static EventsFragment newInstance(int sectionNumber) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }
    public EventsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.events_fragment, container,
                false);



        Calendar calendar = Calendar.getInstance();
        Month = calendar.get(Calendar.MONTH)+1;

        EventListView = (ListView) rootView.findViewById(R.id.EventslistView);

        Eventstxt = (TextView) rootView.findViewById(R.id.Eventstxt);
        Pbar2 = (ProgressBar) rootView.findViewById(R.id.progressBar2);
        Pbar3 = (ProgressBar)rootView. findViewById(R.id.progressBar3);

        imageViewEvent = (ImageView) rootView.findViewById(R.id.imageViewEvent);

        Map<Integer, String> map  = preparHashMap();
        final String eventTitle = getString( R.string.events)+" "+ map.get(Month);
        Eventstxt.setText(eventTitle);
        // pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        //editor = pref.edit();

        shredData = new PrepSharedPreferences(getActivity().getApplicationContext());


        EventsResList = shredData.getEventsvalues();

        CustomListAdapter adapter2 = new CustomListAdapter(getActivity(), R.layout.fucklistrow,  PrepareListViewssListData(EventsResList));
        EventListView.setAdapter(adapter2);

        // setting list adapter


        new LongOperation2().execute("s");


        EventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent;
                intent = new Intent(getActivity(),ServiceWebViewActivity.class);
                String NewsUrl =  EventsResList.get(position).getUrl();


                intent.putExtra("titel",eventTitle );
                NewsUrl = NewsUrl.replace("SPSite Url=", "");
                intent.putExtra("URL", NewsUrl);
                startActivity(intent);

            }
        });



        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
    public Map<Integer,String> preparHashMap() {

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "يناير");
        map.put(2, "فبراير");
        map.put(3, "مارس");
        map.put(4, "ابريل");
        map.put(5, "مايو");
        map.put(6, "يونيو");
        map.put(7, "يوليو");
        map.put(8, "أغسطس");
        map.put(9, "سبتمبر");
        map.put(10, "أكتوبر");
        map.put(11, "نوفمبر");
        map.put(12, "ديسمبر");

        return map;
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
    private class LongOperation2 extends AsyncTask<String, Void, List<News>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (EventListView == null )
                Pbar3.setVisibility(ProgressBar.VISIBLE);

        }
        @Override
        protected List<News> doInBackground(String... params) {


            SoapWS2 sows = new SoapWS2();
            return sows.CallSOAP(Month);

        }
        protected void onPostExecute(List<News> result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            Pbar3.setVisibility(ProgressBar.GONE);

            if (result != null) {
                EventsResList = result;
                shredData.putEventsValue(EventsResList);
                if (getActivity() != null) {
                    CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.fucklistrow, PrepareListViewssListData(result));

                    EventListView.setAdapter(adapter);
                }
            }
            else
            {
                Toast.makeText(getActivity(), "لا يمكن الاتصال بالانترنت",
                        Toast.LENGTH_SHORT).show();


            }


        }
    }
}