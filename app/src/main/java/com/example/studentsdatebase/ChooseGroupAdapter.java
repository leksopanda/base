package com.example.studentsdatebase;

import static com.example.studentsdatebase.AddGroupActivity.countGroups;
import static com.example.studentsdatebase.AddStudentActivity.countStudents;
import static com.example.studentsdatebase.ChooseGroupActivity.groupsList;
import static com.example.studentsdatebase.ChooseStudentActivity.studentsList;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
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

public class ChooseGroupAdapter extends RecyclerView.Adapter<ChooseGroupAdapter.ViewHolder> implements Filterable {
    private List<GroupModel> groupList;
    private List<GroupModel> FullGroupList;

    public ChooseGroupAdapter(List<GroupModel> groupList) {
        this.groupList = groupList;
        FullGroupList = new ArrayList<>(groupList);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String group_id = "Группа: " + String.valueOf(groupList.get(position).getId());
        String group_name = "Факультет: " + String.valueOf(groupList.get(position).getName());

        holder.viewData(group_id, group_name, position, this);
    }

    @Override
    public int getItemCount() {

        return groupList.size();
    }

    @Override
    public Filter getFilter() {

        return probablyFilter;
    }
    public Filter probablyFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<GroupModel> filteredlist = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0){
                filteredlist.addAll(FullGroupList);
            } else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(GroupModel item: FullGroupList){
                    if (item.getId().toLowerCase().contains(filterPattern)){
                        filteredlist.add(item);
                    }

                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredlist;

            return results;

        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            groupList.clear();
            groupList.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView dBtn;
        private TextView groupId, groupName;
        private Dialog dialog;
        private EditText currentGroupId, currentGroupName;
        private Button changeGroupBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            groupId = itemView.findViewById(R.id.groupItemId);
            groupName = itemView.findViewById(R.id.groupItemName);
            dBtn = itemView.findViewById(R.id.groupItemDelete);

            dialog = new Dialog(itemView.getContext());
            dialog.setContentView(R.layout.edit_group_dialog);
            dialog.setCancelable(true);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            currentGroupId = dialog.findViewById(R.id.editCGroupId);
            currentGroupName = dialog.findViewById(R.id.editCGroupName);
            changeGroupBtn = dialog.findViewById(R.id.cGroupDButton);

        }
        private void viewData(String name_id, String name_name,  int pos, ChooseGroupAdapter adapter){
            groupId.setText(name_id);
            groupName.setText(name_name);

            dBtn.setOnClickListener(view -> {
                deleteGroup(pos, itemView.getContext(), adapter);
            });

            itemView.setOnLongClickListener(view -> {
                currentGroupId.setText(groupList.get(pos).getId());
                currentGroupName.setText(groupList.get(pos).getName());
                dialog.show();

                return false;
            });

            changeGroupBtn.setOnClickListener(view -> {
                if (currentGroupId.getText().toString().isEmpty() && currentGroupName.getText().toString().isEmpty()){
                    currentGroupId.setError("Введите группу");
                    currentGroupName.setError("Введите факультет");
                    return;
                }

                uGroupData(currentGroupId.getText().toString(), currentGroupName.getText().toString(), pos, itemView.getContext(), adapter);

            });

        }

        @SuppressLint("NotifyDataSetChanged")
        private void deleteGroup(int id, Context context, ChooseGroupAdapter adapter){
            groupsList.remove(id);
            countGroups = countGroups - 1;
            adapter.notifyDataSetChanged();
            Toast.makeText(context, groupId.getText().toString() + " удалена!", Toast.LENGTH_SHORT).show();
        }

        @SuppressLint("NotifyDataSetChanged")
        private void uGroupData(String newGroupId, String newGroupName, int pos, Context context, ChooseGroupAdapter adapter){
            dialog.dismiss();
            groupsList.get(pos).setId(newGroupId);
            groupsList.get(pos).setName(newGroupName);
            adapter.notifyDataSetChanged();
            Toast.makeText(context, "Группа и факультет изменены!", Toast.LENGTH_SHORT).show();
        }

    }
}
