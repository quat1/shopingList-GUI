package dolphine.designs.shoppinglist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class UserRegistrationActivity extends Activity implements OnClickListener, OnItemSelectedListener

{
	// Variable Declaration should be in onCreate()
	private Button mSubmit;
	private Button mCancel;

	private EditText name, age, email, moneyAvailable;
	private Spinner gender, jobTitle;
	private String[] genderTypes = { "Male", "Female" };
	private String[] jobTitles = { "Policeman", "Doctor", "Teacher", "Bank manager", "Salesman", "Builder", "Dancer", "Student", "Shef", "Waiter",
			"Baby sitter" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		CurrentUser.getInstance();

		setContentView(R.layout.registration);

		// Assignment of UI fields to the variables
		mSubmit = (Button) findViewById(R.id.submit);
		mSubmit.setOnClickListener(this);
		mCancel = (Button) findViewById(R.id.cancel);
		mCancel.setOnClickListener(this);

		name = (EditText) findViewById(R.id.efname);
		moneyAvailable = (EditText) findViewById(R.id.money);
		age = (EditText) findViewById(R.id.age);
		email = (EditText) findViewById(R.id.eemail);

		gender = (Spinner) findViewById(R.id.gender_spinner);
		jobTitle = (Spinner) findViewById(R.id.job_list_spinner);

		// Spinner method to read the on selected value
		ArrayAdapter<String> genderArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genderTypes);
		gender.setAdapter(genderArrayAdapter);
		gender.setOnItemSelectedListener(this);

		ArrayAdapter<String> jobsArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jobTitles);
		jobTitle.setAdapter(jobsArrayAdapter);
		jobTitle.setOnItemSelectedListener(this);
	}

	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.cancel:
			finish();
			break;

		case R.id.submit:

			CurrentUser.name = name.getText().toString();
			CurrentUser.age = Integer.valueOf(age.getText().toString());
			CurrentUser.email = email.getText().toString();
			CurrentUser.moneyAvailable = Integer.valueOf(moneyAvailable.getText().toString());
			CurrentUser.gender = gender.getSelectedItem().toString();
			CurrentUser.jobTitle = jobTitle.getSelectedItem().toString();

			startActivity(new Intent("dolphine.designs.shoppinglist.SHOPSHOPACTIVITY"));
			this.finish();
			break;
		}
	}

	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		
	}

	public void toastState(String name) {
		
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}