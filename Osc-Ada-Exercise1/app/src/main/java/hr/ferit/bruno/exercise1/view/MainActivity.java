package hr.ferit.bruno.exercise1.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hr.ferit.bruno.exercise1.R;
import hr.ferit.bruno.exercise1.TasksRepository;
import hr.ferit.bruno.exercise1.model.Task;

public class MainActivity extends AppCompatActivity {

	TasksRepository mRepository;

	EditText mSummary, mImportance,mTitle;
	TextView displayTasksTextView;
	StringBuilder stringBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeUI();
        displayTasksTextView.setText("");
		mRepository = TasksRepository.getInstance();
	}

	private void initializeUI() {
		mTitle = findViewById(R.id.editText_main_title);
		mSummary = findViewById(R.id.editText_main_summary);
        mImportance = findViewById(R.id.editText_main_importance);
        displayTasksTextView=findViewById(R.id.textView_main_noTasksMassage);
        stringBuilder =new StringBuilder();
	}

	public void saveTask(View view){
        Task task=null;
		// ToDo: 	store the task
		// Parse data from the widgets and store it in a task
		// Store the task in the fake database using the repository
        String taskTitle=mTitle.getText().toString(),
                taskSummary=mSummary.getText().toString();

        if(!taskSummary.equals("")&&!taskTitle.equals("")&&!mImportance.getText().toString().equals("")) {

            int taskImportance=Integer.parseInt(mImportance.getText().toString());
            task = new Task(taskImportance, taskTitle, taskSummary);
            mRepository.save(task);

        }else{
            Toast.makeText(this,R.string.all_emptyFieldWarning,Toast.LENGTH_SHORT).show();
        }
		// ToDo:	clear the UI for the new task
		// Clear all of the editText controls
        mTitle.setText("");
        mSummary.setText("");
        mImportance.setText("");
        if(mRepository.getTasks().size()==0){
            displayTasksTextView.setText(R.string.all_noTasksMassage);
        }
		// ToDo: 	define a method
		// Create a method for displaying the tasks in the textview as strings
		// one below the other and call it on each new task.
        if(task!=null)
        displayTasks(task);
	}

    public void displayTasks(Task instance){
        stringBuilder.append(getString(R.string.all_showTask,instance.getTitle(),
                instance.getSummary(),instance.getImportance()));
        stringBuilder.append("\n");
        displayTasksTextView.setText(stringBuilder.toString());
    }
}
