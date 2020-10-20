package com.example.zmns.baiusttransport;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends ArrayAdapter {

    List studentList = new ArrayList();

    public StudentAdapter(Context context, int resource) {

        super(context, resource);

    }


    public void add(StudentModelClass object) {
        super.add(object);
        studentList.add(object);
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        StudentHolder studentHolder;

        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.student_list_view_id, parent, false);
            studentHolder = new StudentHolder();
            studentHolder.vID = (TextView) row.findViewById(R.id.idStudentListViewId);
            studentHolder.vName = (TextView) row.findViewById(R.id.idStudentListViewName);
            studentHolder.vDept = (TextView) row.findViewById(R.id.idStudentListViewDept);
            studentHolder.vLevel = (TextView) row.findViewById(R.id.idStudentListViewLevel);
            studentHolder.vTerm = (TextView) row.findViewById(R.id.idStudentListViewTerm);
            studentHolder.vType = (TextView) row.findViewById(R.id.idStudentListViewType);
            studentHolder.vBusFee = (TextView) row.findViewById(R.id.idStudentListViewBusFee);
            row.setTag(studentHolder);

        }

        else {

            studentHolder = (StudentHolder)row.getTag();

        }

        StudentModelClass studentModelClass = (StudentModelClass)this.getItem(position);
        studentHolder.vID.setText(studentModelClass.getId());
        studentHolder.vName.setText(studentModelClass.getName());
        studentHolder.vDept.setText(studentModelClass.getDept());
        studentHolder.vLevel.setText("0"+studentModelClass.getLevel());
        studentHolder.vTerm.setText(studentModelClass.getTerm());
        studentHolder.vType.setText(studentModelClass.getType());
        studentHolder.vBusFee.setText(studentModelClass.getBusFee());

        return row;
    }

    static class StudentHolder{

        TextView vID, vName, vDept, vLevel, vTerm, vType, vBusFee;

    }

}