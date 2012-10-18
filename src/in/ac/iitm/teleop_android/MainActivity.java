package in.ac.iitm.teleop_android;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// [android_core/android_gingerbread_mr1]
import org.ros.android.MessageCallable;
import org.ros.android.RosActivity;
import org.ros.android.view.RosTextView;

// [rosjava_core/rosjava_bootstrap]
import org.ros.namespace.GraphName;

// [rosjava_core/rosjava]
import org.ros.address.InetAddressFactory;
import org.ros.concurrent.CancellableLoop;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMain;
import org.ros.node.NodeMainExecutor;
import org.ros.node.topic.Publisher;

/**
 * @author Ashish Ranjan
 *
 */
public class MainActivity extends RosActivity {
	private Handler handler;

	private TalkerNode mTalkerNode;
	private volatile geometry_msgs.Twist twist;
	private RosTextView<std_msgs.String> rosTextView;

	public MainActivity() {
		// The RosActivity constructor configures the notification title and ticker messages.
		super("Teleop Android", "Teleop Android");
	}

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setListeners();

		handler = new Handler();

		rosTextView = (RosTextView<std_msgs.String>) findViewById(R.id.text);
		rosTextView.setTopicName("chatter");
		rosTextView.setMessageType(std_msgs.String._TYPE);
		rosTextView.setMessageToStringCallable(new MessageCallable<String, std_msgs.String>() {
			@Override
			public String call(std_msgs.String message) {
				return message.getData();
			}
		});
	}

	/** Called in a background thread once this {@link Activity} has been initialized with a master {@link URI} via the {@link MasterChooser} and a {@link NodeMainExecutorService} has started. */
	/** Your {@link NodeMains} should be started here using the provided {@link NodeMainExecutor}. */
	@Override
	protected void init(NodeMainExecutor nodeMainExecutor) {
		mTalkerNode = new TalkerNode();

    		NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(InetAddressFactory.newNonLoopback().getHostAddress(), getMasterUri());
		
		// Execute the supplied {@link NodeMain} using the supplied {@link NodeConfiguration}.
		nodeMainExecutor.execute(mTalkerNode, nodeConfiguration);
		nodeMainExecutor.execute(rosTextView, nodeConfiguration); // The RosTextView is also a NodeMain that must be executed in order to start displaying incoming messages.
	}

	/**
	 * Set various listeners.
	 */
	private void setListeners() {
		// Button 1: LEFT FORWARD
		Button button_1 = (Button) findViewById(R.id.button1);
		button_1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				twist.getLinear().setX(0.1);
				twist.getLinear().setY(0.0);
				twist.getLinear().setZ(0.0);

				twist.getAngular().setX(0.0);
				twist.getAngular().setY(0.0);
				twist.getAngular().setZ(0.2);
			}
		});

		// Button 2: FORWARD
		Button button_2 = (Button) findViewById(R.id.button2);
		button_2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				twist.getLinear().setX(0.1);
				twist.getLinear().setY(0.0);
				twist.getLinear().setZ(0.0);

				twist.getAngular().setX(0.0);
				twist.getAngular().setY(0.0);
				twist.getAngular().setZ(0.0);
			}
		});

		// Button 3: RIGHT FORWARD
		Button button_3 = (Button) findViewById(R.id.button3);
		button_3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				twist.getLinear().setX(0.1);
				twist.getLinear().setY(0.0);
				twist.getLinear().setZ(0.0);

				twist.getAngular().setX(0.0);
				twist.getAngular().setY(0.0);
				twist.getAngular().setZ(-0.2);
			}
		});

		// Button 4: TURN ANTICLOCKWISE
		Button button_4 = (Button) findViewById(R.id.button4);
		button_4.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				twist.getLinear().setX(0.0);
				twist.getLinear().setY(0.0);
				twist.getLinear().setZ(0.0);

				twist.getAngular().setX(0.0);
				twist.getAngular().setY(0.0);
				twist.getAngular().setZ(0.2);
			}
		});

		// Button 5: STOP
		Button button_5 = (Button) findViewById(R.id.button5);
		button_5.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				twist.getLinear().setX(0.0);
				twist.getLinear().setY(0.0);
				twist.getLinear().setZ(0.0);

				twist.getAngular().setX(0.0);
				twist.getAngular().setY(0.0);
				twist.getAngular().setZ(0.0);
			}
		});

		// Button 6: TURN CLOCKWISE
		Button button_6 = (Button) findViewById(R.id.button6);
		button_6.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				twist.getLinear().setX(0.0);
				twist.getLinear().setY(0.0);
				twist.getLinear().setZ(0.0);

				twist.getAngular().setX(0.0);
				twist.getAngular().setY(0.0);
				twist.getAngular().setZ(-0.2);
			}
		});

		// Button 7: LEFT BACKWARD
		Button button_7 = (Button) findViewById(R.id.button7);
		button_7.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				twist.getLinear().setX(-0.1);
				twist.getLinear().setY(0.0);
				twist.getLinear().setZ(0.0);

				twist.getAngular().setX(0.0);
				twist.getAngular().setY(0.0);
				twist.getAngular().setZ(-0.2);
			}
		});

		// Button 8: BACKWARD
		Button button_8 = (Button) findViewById(R.id.button8);
		button_8.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				twist.getLinear().setX(-0.1);
				twist.getLinear().setY(0.0);
				twist.getLinear().setZ(0.0);

				twist.getAngular().setX(0.0);
				twist.getAngular().setY(0.0);
				twist.getAngular().setZ(0.0);
			}
		});

		// Button 9: RIGHT BACKWARD
		Button button_9 = (Button) findViewById(R.id.button9);
		button_9.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				twist.getLinear().setX(-0.1);
				twist.getLinear().setY(0.0);
				twist.getLinear().setZ(0.0);

				twist.getAngular().setX(0.0);
				twist.getAngular().setY(0.0);
				twist.getAngular().setZ(0.2);
			}
		});
	}

	/**
	 * @author Ashish Ranjan
	 *
	 */
	private class TalkerNode extends AbstractNodeMain {

		/** Called when ... */
		@Override
		public GraphName getDefaultNodeName() {
			return GraphName.of("teleop_android/talker");
		}

		/** Called when the {@link Node} has started and successfully connected to the master. */
		@Override
		public void onStart(final ConnectedNode connectedNode) {
			// Display "Connected to ROS MASTER" message on the screen
			handler.post(new Runnable() {

				public void run() {
					Toast.makeText(MainActivity.this, "Connected to ROS MASTER", Toast.LENGTH_SHORT).show();
				}
			});

			final Publisher<geometry_msgs.Twist> publisher = connectedNode.newPublisher("cmd_vel", geometry_msgs.Twist._TYPE);
			twist = publisher.newMessage();

			// This CancellableLoop will be canceled automatically when the node shuts down.
			connectedNode.executeCancellableLoop(new CancellableLoop() {
				private int loop_rate;

				@Override
				protected void setup() {
					loop_rate = 100;
				}

				@Override
				protected void loop() throws InterruptedException {
					publisher.publish(twist);
					Thread.sleep(loop_rate);
				}
			});
		}
	}
}
