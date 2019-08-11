package sa.gov.moe.he.moeportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;

/**
 * Created by mazoo_000 on 22/04/2015.
 */

public class ElctrosharingFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_URL = "URL";
    static String URl;

    ImageButton estbanah;
    ImageButton estetla;
    ImageButton fekrah;
    ImageButton archiving;
    ImageButton montda;
    ImageButton modonah;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);
    public static ElctrosharingFragment newInstance(int sectionNumber,String Url) {
        ElctrosharingFragment fragment = new ElctrosharingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_URL, Url);
        URl = Url;
        fragment.setArguments(args);
        return fragment;
    }
    public ElctrosharingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.elctrosharing_fragment, container,
                false);


        estbanah =(ImageButton) rootView.findViewById(R.id.estebeanimageButton);
        estbanah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                estbanahBtnPressed(v);
                v.startAnimation(buttonClick);
               // v.getBackground().clearColorFilter();
                //v.invalidate();
            }

        });


         estetla =(ImageButton) rootView.findViewById(R.id.estetlaImageButton);
        estetla.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                estetlaBtnPressed(v);

                v.startAnimation(buttonClick);
            }
        });

         fekrah = (ImageButton) rootView.findViewById(R.id.fekrahImageButton);
        fekrah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fekrahBtnPressed(v);
                v.startAnimation(buttonClick);
            }
        });

        //ImageButton mosadah = (ImageButton) rootView.findViewById(R.id.
         montda = (ImageButton) rootView.findViewById(R.id.montadaImageButton);
        montda.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                montdaBtnPressed(v);
                v.startAnimation(buttonClick);
            }
        });

         modonah = (ImageButton) rootView.findViewById(R.id.modonah);
        modonah.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                modonahBtnPressed(v);
                v.startAnimation(buttonClick);
            }
        });
        archiving = (ImageButton) rootView.findViewById(R.id.archivingImageButton);
        archiving.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                archivingBtnPressed(v);
                v.startAnimation(buttonClick);
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
    public void estbanahBtnPressed(View v) {
        Intent intent;
        intent = new Intent(getActivity(),ServiceWebViewActivity.class);
        String country = getString(R.string.estbanah);

        intent.putExtra("titel",  country );
        intent.putExtra("URL","http://he.moe.gov.sa/ar/eParticipation/survey/Pages/default.aspx");
        startActivity(intent);
    }
    public void archivingBtnPressed(View v) {
        Intent intent;
        intent = new Intent(getActivity(),ServiceWebViewActivity.class);
        String country ="ارشيف "+ getString(R.string.archiving);

        intent.putExtra("titel",  country );
        intent.putExtra("URL","http://he.moe.gov.sa/ar/eParticipation/Pages/ParticipationCalendar.aspx");
        startActivity(intent);

    }
    public void estetlaBtnPressed(View view) {
        Intent intent;
        intent = new Intent(getActivity(),ServiceWebViewActivity.class);
        String country = getString(R.string.estetla);

        intent.putExtra("titel",  country );
        intent.putExtra("URL","http://he.moe.gov.sa/ar/eParticipation/Pages/Polls.aspx");
        startActivity(intent);
    }
    public void fekrahBtnPressed(View view) {
        Intent intent;
        intent = new Intent(getActivity(),ServiceWebViewActivity.class);
        String country = getString(R.string.fekrah);

        intent.putExtra("titel",  country );
        intent.putExtra("URL","http://moeideas.ideascale.com/");
        startActivity(intent);
    }
    public void montdaBtnPressed(View view) {
        Intent intent;
        intent = new Intent(getActivity(),ServiceWebViewActivity.class);
        String country = getString(R.string.montda);

        intent.putExtra("titel",  country );
        intent.putExtra("URL","http://forum.mohe.gov.sa/Default.aspx");
        startActivity(intent);
    }
    public void modonahBtnPressed(View view) {
        Intent intent;
        intent = new Intent(getActivity(),ServiceWebViewActivity.class);
        String country = getString(R.string.modonah);

        intent.putExtra("titel",  country );
        intent.putExtra("URL","http://he.moe.gov.sa/ar/moheblogs/Pages/default.aspx");
        startActivity(intent);
    }
}