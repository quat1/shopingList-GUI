package dolphine.designs.shoppinglist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class shopshopActivity extends Activity {

	private MyCustomAdapter dataAdapter = null;
	public ArrayList<Smartphone> smartphonesList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Generate list View from ArrayList
		displayListView();

		checkButtonClick();

	}

	private void displayListView() {

		// Array list of countries
		smartphonesList = new ArrayList<Smartphone>();
		Smartphone smartphone = new Smartphone("Iphone 4", "colour - black, 1gb Ram", 400);
		smartphonesList.add(smartphone);
		smartphone = new Smartphone("Iphone 5S", "colour - Gold, 3gb Ram", 800);
		smartphonesList.add(smartphone);
		smartphone = new Smartphone("Samsung S2", "colour - Gray, 1gb Ram", 300);
		smartphonesList.add(smartphone);
		smartphone = new Smartphone("Samsung S3", "colour - White, 2gb Ram", 500);
		smartphonesList.add(smartphone);
		smartphone = new Smartphone("Samsung S4", "colour - Black, 3gb Ram", 800);
		smartphonesList.add(smartphone);
		smartphone = new Smartphone("HTC Ace", "colour - White, 2gb Ram", 500);
		smartphonesList.add(smartphone);
		smartphone = new Smartphone("HTC wildfire", "colour - Gray, 2gb Ram", 400);
		smartphonesList.add(smartphone);

		// create an ArrayAdaptar from the String Array
		dataAdapter = new MyCustomAdapter(this, R.layout.shoppingl_ist_row, smartphonesList);
		ListView listView = (ListView) findViewById(R.id.listView1);
		// Assign adapter to ListView
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// When clicked, show a toast with the TextView text
				// Smartphone smartphone = (Smartphone)
				// parent.getItemAtPosition(position);
			}
		});

	}

	/** Holds child views for one row. */
	private static class PhoneViewHolder {
		public TextView name;
		public TextView description;
		public TextView price;
		public CheckBox chkBox;

		public PhoneViewHolder() {
		}

		public PhoneViewHolder(TextView name, TextView description, TextView price, CheckBox chkBox) {
			this.name = name;
			this.description = description;
			this.price = price;
			this.chkBox = chkBox;
		}

	}

	private class MyCustomAdapter extends ArrayAdapter<Smartphone> {

		public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Smartphone> smartphonesList) {
			super(context, textViewResourceId, smartphonesList);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Smartphone smartphone = (Smartphone) this.getItem(position);

			Log.e("ConvertView", "-----------------" + String.valueOf(position));
			Log.e("ConvertView", "-----------------" + smartphone.description + smartphone.name + String.valueOf(smartphone.getPrice()));

			TextView name;
			TextView description;
			TextView price;
			CheckBox chkBox;

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.shoppingl_ist_row, null);
				// The child views in each row.
				name = (TextView) convertView.findViewById(R.id.name);
				description = (TextView) convertView.findViewById(R.id.desc);
				price = (TextView) convertView.findViewById(R.id.price);
				chkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);

				// Optimization: Tag the row with it's child views, so we don't
				// have to
				// call findViewById() later when we reuse the row.
				convertView.setTag(new PhoneViewHolder(name, description, price, chkBox));

				chkBox.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox cb = (CheckBox) v;
						Smartphone smartphone = (Smartphone) cb.getTag();
						smartphone.setSelected(cb.isChecked());
					}
				});
			}// Reuse existing row view
			else {
				// Because we use a ViewHolder, we avoid having to call
				// findViewById().
				PhoneViewHolder viewHolder = (PhoneViewHolder) convertView.getTag();
				name = viewHolder.name;
				description = viewHolder.description;
				price = viewHolder.price;
				chkBox = viewHolder.chkBox;
			}

			// Tag the CheckBox with the item it is displaying, so that we can
			// access the item in onClick() when the CheckBox is toggled.
			chkBox.setTag(smartphone);

			PhoneViewHolder viewHolder = (PhoneViewHolder) convertView.getTag();
			// Display smartphone data
			viewHolder.name.setText(smartphone.getName());
			viewHolder.description.setText(smartphone.getDescription());
			viewHolder.price.setText(String.valueOf(smartphone.getPrice()));
			viewHolder.chkBox.setChecked(smartphone.isSelected());

			return convertView;

		}

	}

	private void checkButtonClick() {

		Button myButton = (Button) findViewById(R.id.buy_item);
		myButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String result = "";
				int total_cost = 0;
				for (int i = 0; i < dataAdapter.getCount(); i++) {
					Smartphone smartphone = dataAdapter.getItem(i);
					if (smartphone.isSelected()) {
						total_cost = total_cost + smartphone.price;
					}
				}
				CurrentUser.getInstance();
				if (total_cost > CurrentUser.moneyAvailable) {
					Toast.makeText(getApplicationContext(), "Sorry, the total items cost exceeds available amount of money!", Toast.LENGTH_LONG).show();

				} else {
					notifyUser((total_cost * 20) / 100f);
				}
			}
		});

	}

	void notifyUser(float vat) {
		new AlertDialog.Builder(this).setTitle("Thank you!").setMessage("You sucessfuly bought these items and your VAT is equal to = " + String.valueOf(vat))
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// continue with delete
					}
				}).show();
	}
}