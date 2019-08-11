package sa.gov.moe.he.moeportal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by mazoo_000 on 17/05/2015.
 */




public class ContactUsFragment extends Fragment  {
    private static final String ARG_SECTION_NUMBER = "section_number";

    MapView mapView;
    GoogleMap map;

    public static ContactUsFragment newInstance(int sectionNumber) {
        ContactUsFragment fragment = new ContactUsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }
    public ContactUsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_us_fragment, container,
                false);

        map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
      // map =  ((FragmentActivity) getActivity()).getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
       // if (map == null)
       // map = ((MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();

        // map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.addMarker(new MarkerOptions().position(new LatLng(24.662267, 46.685275))
     );
      //((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

      //  mapView = (MapView) rootView.findViewById(R.id.map);
        //mapView.setBuiltInZoomControls(true);
       //mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        //map = mapView.getMap();

        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(24.66226, 46.685275), 10);
        map.animateCamera(cameraUpdate);

       //map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
       // map = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();


      /*  final LatLng PERTH = new LatLng(-31.90, 115.86);
        Marker perth = map.addMarker(new MarkerOptions()
                .position(PERTH)
                .draggable(true));
                 .position(new LatLng(24.662267, 46.685275))
                 */
        return rootView;
    }

    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(10, 10))
                .title("Hello world"));
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
     /*   MapFragment f = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);

        if (f != null)
        {
            f.onDetach();
            f.onDestroy();
            map.clear();

        }
        */
/*        //getFragmentManager().beginTransaction().remove(f).commit();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Fragment fragment = (fm.findFragmentById(R.id.map));
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();

      Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));
        FragmentTransaction ft =   getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // map.clear();

       //  mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
       // mapView.onLowMemory();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(
                ARG_SECTION_NUMBER));
    }
}