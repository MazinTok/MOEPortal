package sa.gov.moe.he.moeportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mazoo_000 on 23/04/2015.
 */

/**
 * Created by mazoo_000 on 23/04/2015.
 */

public class ServiceFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    HashMap<String, List<String>> listDataChildMo;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ServiceFragment newInstance(int sectionNumber) {
        ServiceFragment fragment = new ServiceFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ServiceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.service_fragment, container, false);


        expListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView2);

        PrepareServicesListData();
        listAdapter = new ExpandableListAdapter(getActivity().getApplicationContext(), listDataHeader, listDataChild, listDataChildMo, 0);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //====-=-=-=--=-=-=-= Second  View listener====-=-=-=-=-=-=-=-=-=-=-=-
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2, int arg3, long arg4) {
                // Toast.makeText(getBaseContext(), "Child clicked"+ arg2 +arg3+arg4 , Toast.LENGTH_LONG).show();

                Intent intent;
                intent = new Intent(getActivity(), ServiceWebViewActivity.class);
                String country = (String) listAdapter.getChild(arg2, arg3);

                preparHashMap prepMap = new preparHashMap();
                Map<String, String> ServiceHashMap = new HashMap<String, String>();
                ServiceHashMap = prepMap.preparHashMap();


                intent.putExtra("titel", country);
                String temp = "http://he.moe.gov.sa/ar/Mobile/Det.aspx?&Service=Yes&ServiceID=" + ServiceHashMap.get(country);
                intent.putExtra("URL", temp);//ServiceHashMap.get(country));
                startActivity(intent);
                return false;
            }
        });


        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void PrepareServicesListData() {


        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        ///-=-=-=-=-=-=-=-=-=-=-=-=-=


        for (int i = 1; i < 7; i++) {
            String temp = "Ser" + Integer.toString(i);

            listDataHeader.add(getStringResourceByName(temp));

        }

        listDataChild.put(listDataHeader.get(0), PreparGengalServices());
        listDataChild.put(listDataHeader.get(1), PreparStudentServices());
        listDataChild.put(listDataHeader.get(2), PreparUniServices());
        listDataChild.put(listDataHeader.get(3), PreparComServices());
        listDataChild.put(listDataHeader.get(4), PreparResearchersServices());
        listDataChild.put(listDataHeader.get(5), PreparGoverServices());
    }

    private String getStringResourceByName(String aString) {
        String packageName = getActivity().getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

    public  List<String> PreparGengalServices() {
        List<String> CatItems1 = new ArrayList<String>();

        CatItems1.add("خدمة مؤهل");
        CatItems1.add("خدمة المعارض");
        CatItems1.add("إرسال فعالية علمية");
        CatItems1.add("إرسال توصية");
        CatItems1.add("تواصل");
        return CatItems1;
    }
    public  List<String> PreparStudentServices() {
        List<String> CatItems1 = new ArrayList<String>();

                CatItems1.add("خدمة معادلة الشهادات");
                CatItems1.add("طلب منحة داخلية");
                CatItems1.add("التعليم العالي الأهلية");
                CatItems1.add("خدمة مؤهل");
                CatItems1.add("خدمات بكالوريوس طب الدول العربية");
                CatItems1.add("خدمة انتقال طالب من دولة");
                CatItems1.add("خدمة الانتقال إلى دولة للدارسين على حسابهم الخاص");
                CatItems1.add("خدمة إفادة بالتخرج");
                CatItems1.add("خدمة تعديل بيانات الطالب الشخصية");
                CatItems1.add("خدمة تغيير التخصص");
                CatItems1.add("خدمة اضافة تقرير دراسي");
                CatItems1.add("خدمة ترقية بعثة للموظفين المبتعثين من جهات حكومية");
                CatItems1.add("خدمة تعديل تقرير دراسي");
                CatItems1.add("خدمة تغيير جامعة أو كلية");
                CatItems1.add("خدمة تغيير معهد اللغة");
                CatItems1.add("خدمة حضور مؤتمر أو ندوة");
        CatItems1.add("خدمة الموافقة للدراسة في الخارج على الحساب الخاص");
                CatItems1.add("خدمة طلب دورات تدريبية");
                CatItems1.add("خدمة طلب رحلة علمية");
                CatItems1.add("خدمة فتح ملف لمرافق دارس");
                CatItems1.add("خدمة مصادقة الشهادات");
                CatItems1.add("خدمة إضافة مؤهل دراسي لملف الدارس في الخارج");
                CatItems1.add("خدمة إضافة بيانات جواز السفر للطلاب");
                CatItems1.add("خدمة تعديل بيانات الحساب البنكي للطالب");
                CatItems1.add("خدمة تحديث بيانات الاتصال للطالب");
                CatItems1.add("خدمة فتح ملف طالب");
                CatItems1.add("خدمة طلب تذكرة سفر");
                CatItems1.add("خدمة إعادة صرف للطالب");
                CatItems1.add("خدمة طلب تعويض مالي للطالب");
                CatItems1.add("خدمة صرف فروقات");
                CatItems1.add("خدمة ضمان مالي");
                CatItems1.add("خدمة تمديد الصرف");
                CatItems1.add("خدمة كشف حساب");
                CatItems1.add("خدمة مكافأة التميز للطالب");
                CatItems1.add("خدمة استمرار في دراسة اللغة");
                CatItems1.add("خدمة العودة من التأجيل");
                CatItems1.add("خدمة إعادة لعضوية البعثة");
                CatItems1.add("خدمة إنهاء البعثة");
                CatItems1.add("خدمة تأجيل البعثة");
                CatItems1.add("خدمة تعديل تاريخ بداية البعثة");
                CatItems1.add("خدمة تعديل تاريخ نهاية البعثة");
                CatItems1.add("خدمة إلحاق مرافق بعضوية البعثة");
                CatItems1.add("خدمة إلحاق بالبعثة");
                CatItems1.add("خدمة تمديد بعثة");
                CatItems1.add("خدمة إصدار تعريف للطالب");
                CatItems1.add("خدمة استمرار بعثة مرافق أو محرم");
                CatItems1.add("خدمة تغيير محرم الطالبة");
                CatItems1.add("خدمة اضافة مرافق للطالب");
                CatItems1.add("خدمة فصل بعثة مرافق /محرم");
                CatItems1.add("خدمة إعادة النظر");
                CatItems1.add("خدمة استفسار");
                CatItems1.add("خدمة طلبات عامة للطالب الدارس بالخارج");
                CatItems1.add("خدمة الموافقة على عرض السيرة الذاتية للخريجين من خارج المملكة");
                CatItems1.add("خدمة طلب إجازة");
                CatItems1.add("خدمة إعادة السنة الدراسية");
                CatItems1.add("خدمة الحاق مرافق دارس بعضوية البعثة");
                CatItems1.add("خدمة تجديد التأشيرة");
                CatItems1.add("خدمة تعديل بيانات مرافق");
                CatItems1.add("خدمة طلب منحة داخلية");
        CatItems1.add("خدمة طلب منحة خارجية");
        CatItems1.add("خدمة طلب منح خادم الحرميين الشرفيين للطلبه السوريين");

        return CatItems1;
    }
    public  List<String> PreparUniServices() {
        List<String> CatItems1 = new ArrayList<String>();

        CatItems1.add("طلب منحة داخلية");
        CatItems1.add("خدمة إدارة الندوات والمؤتمرات");
        CatItems1.add("التعليم العالي الأهلية");
        CatItems1.add("خدمة إحصاءات التعليم العالي");
        CatItems1.add("خدمة مقبول");
        CatItems1.add("خدمة المنح الدراسية الحكومية لغير السعوديين في الجامعات الحكومية");
        //CatItems1.add("خدمة مستشارون");
        CatItems1.add("خدمة المعارض");
        CatItems1.add("خدمة مشروع");
        CatItems1.add("خدمة متابعة الجامعات الاهلية");
        CatItems1.add("خدمة طلب منحة داخلية");
        CatItems1.add("خدمة طلب منحة خارجية");
        CatItems1.add("خدمة طلب منح خادم الحرميين الشرفيين للطلبه السوريين");
        return CatItems1;
    }
    public  List<String> PreparComServices() {
        List<String> CatItems1 = new ArrayList<String>();

        CatItems1.add("خدمة المعارض");
        CatItems1.add("خدمات سفير المالي والإداري");
        CatItems1.add("خدمات سفير الوثائق");
        CatItems1.add("خدمة الانتقال إلى دولة للدارسين على حسابهم الخاص");
        CatItems1.add("خدمة تعديل بيانات الطالب الشخصية");
        CatItems1.add("خدمة إضافة مؤهل دراسي لملف الدارس في الخارج");
        CatItems1.add("خدمة إعادة صرف للطالب");
        CatItems1.add("خدمة تسديد رسوم");
        CatItems1.add("خدمة طلب تعويض مالي للطالب");
        CatItems1.add("خدمة صرف فروقات");
        CatItems1.add("خدمة ضمان مالي");
        CatItems1.add("خدمة إضافة بيانات التأمين الطبي للطلاب");
        CatItems1.add("خدمة تمديد الصرف");
        CatItems1.add("خدمة إنهاء البعثة");
        CatItems1.add("خدمة إلحاق أقارب موفدين بعضوية البعثة");
        CatItems1.add("خدمة إلحاق بالبعثة");
        CatItems1.add("خدمة الإفادة عن طالب");
        CatItems1.add("خدمة ضمان مالي مؤقت");
        CatItems1.add("خدمات شؤون التصاديق والجامعات");
       // CatItems1.add("خدمة إدارة الندوات والمؤتمرات");

        return CatItems1;
    }
    public  List<String> PreparResearchersServices() {
        List<String> CatItems1 = new ArrayList<String>();

        CatItems1.add("خدمة إدارة الندوات والمؤتمرات");
        CatItems1.add("لائحة المعادلات");
        CatItems1.add("خدمة الشؤون الدراسية");
        CatItems1.add("خدمة الإستفسار للطلبة المبتعثين");

        return CatItems1;
    }
    public  List<String> PreparGoverServices() {
        List<String> CatItems1 = new ArrayList<String>();

        CatItems1.add("خدمة مؤهل");
        CatItems1.add("خدمة استعلام عن حالة قرار معادلة	");
        CatItems1.add("خدمة الإفادة عن طالب");
        CatItems1.add("خدمة طلب أستاذ زائر");
        CatItems1.add("خدمة طلب حضور مؤتمر");

        return CatItems1;
    }
}