package sg.edu.np.WhackAMole;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
  int score = 0;
  private static final String TAG = "Whack-A-Mole!";
  private static final int[] Buttonid = {
    R.id.button0,
    R.id.button1,
    R.id.button2,
  };
  private static final String[] ButtonText = {
    "Button Left Clicked!",
    "Button Middle Clicked!",
    "Button Right Clicked!",
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.v(TAG, "Finished Pre-Initialisation!");
  }

  @Override
  protected void onStart() {
    super.onStart();
    setNewMole();
    DisplayScore();
    Log.v(TAG, "Starting GUI!");
  }

  public void DisplayScore() {
    TextView msg = findViewById(R.id.message);
    String s = String.valueOf(score);
    msg.setText(s);
  }

  public void setNewMole() {
    Random ran = new Random();
    int mole = ran.nextInt(1000000000) % 3;
    for (int i = 0; i < Buttonid.length; i++) {
      int id = Buttonid[i];
      final int setb = i;

      if (Buttonid[mole] != id) {
        Button button = findViewById(id);

        button.setText("0");

        button.setOnClickListener(
          new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              if (score > 0) {
                score--;
              }
              DisplayScore();
              setNewMole();
              Log.v(TAG, ButtonText[setb] + " Missed, score deducted!");
            }
          }
        );
      } else {
        Button button = findViewById(id);
        button.setText("*");
        button.setOnClickListener(
          new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              score++;
              DisplayScore();
              setNewMole();
              Log.v(TAG, ButtonText[setb] + " Hit, score added!");
            }
          }
        );
      }
    }
  }
}
