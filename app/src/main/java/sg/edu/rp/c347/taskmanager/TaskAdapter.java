package sg.edu.rp.c347.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> task;
    private Context context;
    private TextView tvId;
    private TextView tvName;
    private TextView tvDescription;

    public TaskAdapter(Context context, int resource, ArrayList<Task> objects){
        super(context, resource, objects);
        task = objects;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvId = rowView.findViewById(R.id.tvID);
        tvName = rowView.findViewById(R.id.tvName);
        tvDescription = rowView.findViewById(R.id.tvDescription);

        Task currentTask = task.get(position);

        tvId.setText(currentTask.getId());
        tvName.setText(currentTask.getName());
        tvDescription.setText(currentTask.getDescription());

        return rowView;
    }


}
