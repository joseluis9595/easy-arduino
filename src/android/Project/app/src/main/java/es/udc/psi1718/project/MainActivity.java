package es.udc.psi1718.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import es.udc.psi1718.project.util.Constants;
import es.udc.psi1718.project.util.MyJSONfileReader;
import es.udc.psi1718.project.util.UserPreferencesManager;

public class MainActivity extends AppCompatActivity {

	public static Boolean active = false;
	private Context context = this;
	private String TAG = "MainActivity";

	private int SETTINGSACTIV_REQUESTCODE = 2;

	// Intents
	private Intent controllersIntent;

	// Layout variables
	//private Button buttonSendCommand;
	private Button examplePannelButton;
	//private TextView textView;
	//private EditText editText;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Change theme of the activity according to user's preferences
		setTheme(UserPreferencesManager.getInstance(this).getAppTheme());
		setContentView(R.layout.activity_main);
		Log.d(TAG, "onCreate");

		// TODO IT3 create a tutorial for first time opening

		// TODO IT2 implement the whole class

		// Prepare JSON file
		MyJSONfileReader.getInstance().loadJsonFileAsync(this);

		// Intents
		controllersIntent = new Intent(context, ControllersActivity.class);

		// Initialize layout
		initializeLayout();
	}


	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
		active = true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.e(TAG, "onActivityResult");
		if (requestCode == SETTINGSACTIV_REQUESTCODE) {
			this.recreate();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
		active = false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_about:
				Intent aboutIntent = new Intent(this, AboutActivity.class);
				startActivity(aboutIntent);
				return true;
			case R.id.action_settings:
				Intent settingsIntent = new Intent(this, SettingsActivity.class);
				startActivityForResult(settingsIntent, SETTINGSACTIV_REQUESTCODE);
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Function to initialize all layout variables
	 */
	private void initializeLayout() {
		//buttonSendCommand = (Button) findViewById(R.id.button_send_command);
		examplePannelButton = (Button) findViewById(R.id.pannel_example);
		//editText = (EditText) findViewById(R.id.editText);
		//textView = (TextView) findViewById(R.id.textView);

		// Create one common listener
		View.OnClickListener onClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				switch (view.getId()) {
					case R.id.pannel_example:
						Log.d(TAG, "ONCLICK : button start");
						// TODO get id of the pannel
						controllersIntent.putExtra(Constants.INTENTCOMM_PANNELID, 2);
						startActivity(controllersIntent);
						break;

					/*case R.id.button_send_command:
						Log.d(TAG, "ONCLICK : button send - " + editText.getText().toString());
						//sendCommand(editText.getText().toString());
						break;*/

					default:
						Log.e(TAG, "ONCLICK : Pressed a non-listened layout");
				}
			}
		};

		// Add listener to different layout items
		//buttonSendCommand.setOnClickListener(onClickListener);
		examplePannelButton.setOnClickListener(onClickListener);


	}


}
