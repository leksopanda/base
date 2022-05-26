package com.example.studentsdatebase;


import static com.example.studentsdatebase.AddStudentActivity.countStudents;
import static com.example.studentsdatebase.ChooseGroupActivity.groupsList;
import static com.example.studentsdatebase.ChooseStudentActivity.studentsList;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChooseStudentAdapter extends RecyclerView.Adapter<ChooseStudentAdapter.ViewHolder> implements Filterable {
    private List<StudentModel> studentList;
    private List<StudentModel> FullStudentList;


    public ChooseStudentAdapter(List<StudentModel> studentList) {
        this.studentList = studentList;
        FullStudentList = new ArrayList<>(studentList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new ChooseStudentAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String student_surname = String.valueOf(studentList.get(position).getSurname());
        String student_name = String.valueOf(studentList.get(position).getName());
        String student_middle_name = String.valueOf(studentList.get(position).getMiddleName());
        String student_group = String.valueOf(studentList.get(position).getGroup());
        String student_birth_date = String.valueOf(studentList.get(position).getBirthDate());


        holder.viewData(student_surname, student_name, student_middle_name, student_group, student_birth_date, position, this);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    @Override
    public Filter getFilter() {
        return probableFilter;
    }
    private Filter probableFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<StudentModel> filteredlist = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0){
                filteredlist.addAll(FullStudentList);
            } else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(StudentModel item: FullStudentList){
                    if (item.getSurname().toLowerCase().contains(filterPattern)){
                        filteredlist.add(item);
                    }

                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredlist;

            return results;
    }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            studentList.clear();
            studentList.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sName, sSurname, sMiddleName, sBirthDate, sGroup;
        private ImageView dBtn;
        private Dialog dialog;
        private EditText currentStudentName, currentStudentSurname, currentStudentMiddleName, currentStudentBirthDate, currentStudentGroup;
        private Button changeStudentBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sName = itemView.findViewById(R.id.studentItemName);
            sSurname = itemView.findViewById(R.id.studentItemSurname);
            sMiddleName = itemView.findViewById(R.id.studentItemMiddleName);
            sBirthDate = itemView.findViewById(R.id.studentItemBirthDate);
            sGroup = itemView.findViewById(R.id.studentItemGroup);
            dBtn = itemView.findViewById(R.id.studentItemDelete);

            dialog = new Dialog(itemView.getContext());
            dialog.setContentView(R.layout.edit_student_dialog);
            dialog.setCancelable(true);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            currentStudentName = dialog.findViewById(R.id.editCStudentName);
            currentStudentSurname = dialog.findViewById(R.id.editCStudentSurname);
            currentStudentMiddleName = dialog.findViewById(R.id.editCStudentMiddleName);
            currentStudentBirthDate = dialog.findViewById(R.id.editCStudentBirthDate);
            currentStudentGroup = dialog.findViewById(R.id.editCStudentGroup);
            changeStudentBtn = dialog.findViewById(R.id.cStudentDButton);

        }

        private void viewData(String studentS, String studentN, String studentMN, String studentG, String studentBD, int pos, ChooseStudentAdapter adapter){
            sSurname.setText(studentS);
            sName.setText(studentN);
            sMiddleName.setText(studentMN);
            sGroup.setText(studentG);
            sBirthDate.setText(studentBD);



            dBtn.setOnClickListener(view -> {
                deleteStudent(pos, itemView.getContext(), adapter);
            });

            itemView.setOnLongClickListener(view -> {
                currentStudentName.setText(studentList.get(pos).getName());
                currentStudentSurname.setText(studentList.get(pos).getSurname());
                currentStudentMiddleName.setText(studentList.get(pos).getMiddleName());
                currentStudentBirthDate.setText(studentList.get(pos).getBirthDate());
//                currentStudentGroup.setText(studentList.get(pos).getGroup());
                dialog.show();

                return false;
            });

            changeStudentBtn.setOnClickListener(view -> {
                if (currentStudentName.getText().toString().isEmpty() && currentStudentSurname.getText().toString().isEmpty()
                && currentStudentMiddleName.getText().toString().isEmpty() && currentStudentBirthDate.getText().toString().isEmpty()
                && currentStudentGroup.getText().toString().isEmpty()){
                    currentStudentName.setError("Введите имя");
                    currentStudentSurname.setError("Введите фамилию");
                    currentStudentMiddleName.setError("Введите отчество");
                    currentStudentBirthDate.setError("Введите дату рождения");
                    currentStudentGroup.setError("Введите группу");
                    return;
                }

                uStudentData(currentStudentName.getText().toString(), currentStudentSurname.getText().toString(), currentStudentMiddleName.getText().toString(), currentStudentBirthDate.getText().toString(),
                        currentStudentGroup.getText().toString(), pos, itemView.getContext(), adapter);

            });

        }

        @SuppressLint("NotifyDataSetChanged")
        private void deleteStudent(int id, Context context, ChooseStudentAdapter adapter){
            studentsList.remove(id);
            countStudents = countStudents - 1;
            adapter.notifyDataSetChanged();
            Toast.makeText(context, "Студент " + sSurname.getText().toString() + " " + sName.getText().toString() + " удален(а)!", Toast.LENGTH_SHORT).show();
        }

        @SuppressLint("NotifyDataSetChanged")
        private void uStudentData(String newStudentName, String newStudentSurname, String newStudentMiddleName, String newStudentBirthDate, String newStudentGroup, int pos, Context context, ChooseStudentAdapter adapter){
            dialog.dismiss();
            studentsList.get(pos).setName(newStudentName);
            studentsList.get(pos).setSurname(newStudentSurname);
            studentsList.get(pos).setMiddleName(newStudentMiddleName);
            studentsList.get(pos).setBirthDate(newStudentBirthDate);
            studentsList.get(pos).setGroup(newStudentGroup);

            adapter.notifyDataSetChanged();
            Toast.makeText(context, "Данные о пользователи изменены!", Toast.LENGTH_SHORT).show();
        }

    }
}
