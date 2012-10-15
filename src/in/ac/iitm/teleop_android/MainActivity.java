package in.ac.iitm.teleop_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// [android_core/android_gingerbread_mr1]
import org.ros.android.MessageCallable;
import org.ros.android.RosActivity;
import org.ros.android.view.RosTextView;

// [rosjava_core/rosjava_bootstrap]
import org.ros.namespace.GraphName;

// [rosjava_core/rosjava]
import org.ros.concurrent.CancellableLoop;
import org.ros.message.MessageFactory;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMain;
import org.ros.node.NodeMainExecutor;
import org.ros.node.topic.Publisher;

/**
 * @author Ashish Ranjan
 *
 */
public class MainActivity extends RosActivity {

	private RosTextView<std_msgs.String> rosTextView;
	private TalkerNode mTalkerNode;

	private Publisher<geometry_msgs.Twist> publisher;
	private geometry_msgs.Twist twist;
	private geometry_msgs.Vector3 linear; 
	private geometry_msgs.Vector3 angular; 

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

	/** Called in a background thread once this [Activity] has been initialized with a master [URI] via the [MasterChooser] and a [NodeMainExecutorService] has started. */
	/** Your [NodeMains] should be started here using the provided [NodeMainExecutor]. */
	@Override
	protected void init(NodeMainExecutor nodeMainExecutor) {
		mTalkerNode = new TalkerNode();

		NodeConfiguration nodeConfiguration = NodeConfiguration.newPrivate();
		geometry_msgs.Vector3 linear = nodeConfiguration.getTopicMessageFactory().newFromType(geometry_msgs.Vector3._TYPE);
		geometry_msgs.Vector3 angular = nodeConfiguration.getTopicMessageFactory().newFromType(geometry_msgs.Vector3._TYPE);
		// At this point, the user has already been prompted to either enter the URI of a master to use or to start a master locally.
		nodeConfiguration.setMasterUri(getMasterUri());

		nodeMainExecutor.execute(mTalkerNode, nodeConfiguration);
		// The RosTextView is also a NodeMain that must be executed in order to start displaying incoming messages.
		nodeMainExecutor.execute(rosTextView, nodeConfiguration);
	}

	/**
	 * Set various listeners.
	 */
	private void setListeners() {
		// Button 1: LEFT FORWARD
		Button button_1 = (Button) findViewById(R.id.button1);
		button_1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				linear.setX(0.1);
				linear.setY(0.0);
				linear.setZ(0.0);

				angular.setX(0.0);
				angular.setY(0.0);
				angular.setZ(0.2);

				twist.setLinear(linear);
				twist.setAngular(angular);
			}
		});

		// Button 2: FORWARD
		Button button_2 = (Button) findViewById(R.id.button2);
		button_2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				linear.setX(0.1);
				linear.setY(0.0);
				linear.setZ(0.0);

				angular.setX(0.0);
				angular.setY(0.0);
				angular.setZ(0.0);

				twist.setLinear(linear);
				twist.setAngular(angular);
			}
		});

		// Button 3: RIGHT FORWARD
		Button button_3 = (Button) findViewById(R.id.button3);
		button_3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				linear.setX(0.1);
				linear.setY(0.0);
				linear.setZ(0.0);

				angular.setX(0.0);
				angular.setY(0.0);
				angular.setZ(-0.2);

				twist.setLinear(linear);
				twist.setAngular(angular);
			}
		});

		// Button 4: TURN ANTICLOCKWISE
		Button button_4 = (Button) findViewById(R.id.button4);
		button_4.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				linear.setX(0.0);
				linear.setY(0.0);
				linear.setZ(0.0);

				angular.setX(0.0);
				angular.setY(0.0);
				angular.setZ(0.2);

				twist.setLinear(linear);
				twist.setAngular(angular);
			}
		});

		// Button 5: STOP
		Button button_5 = (Button) findViewById(R.id.button5);
		button_5.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				linear.setX(0.0);
				linear.setY(0.0);
				linear.setZ(0.0);

				angular.setX(0.0);
				angular.setY(0.0);
				angular.setZ(0.0);

				twist.setLinear(linear);
				twist.setAngular(angular);
			}
		});

		// Button 6: TURN CLOCKWISE
		Button button_6 = (Button) findViewById(R.id.button6);
		button_6.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				linear.setX(0.0);
				linear.setY(0.0);
				linear.setZ(0.0);

				angular.setX(0.0);
				angular.setY(0.0);
				angular.setZ(-0.2);

				twist.setLinear(linear);
				twist.setAngular(angular);
			}
		});

		// Button 7: LEFT BACKWARD
		Button button_7 = (Button) findViewById(R.id.button7);
		button_7.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				linear.setX(-0.1);
				linear.setY(0.0);
				linear.setZ(0.0);

				angular.setX(0.0);
				angular.setY(0.0);
				angular.setZ(-0.2);

				twist.setLinear(linear);
				twist.setAngular(angular);
			}
		});

		// Button 8: BACKWARD
		Button button_8 = (Button) findViewById(R.id.button8);
		button_8.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				linear.setX(-0.1);
				linear.setY(0.0);
				linear.setZ(0.0);

				angular.setX(0.0);
				angular.setY(0.0);
				angular.setZ(0.0);

				twist.setLinear(linear);
				twist.setAngular(angular);
			}
		});

		// Button 9: RIGHT BACKWARD
		Button button_9 = (Button) findViewById(R.id.button9);
		button_9.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				linear.setX(-0.1);
				linear.setY(0.0);
				linear.setZ(0.0);

				angular.setX(0.0);
				angular.setY(0.0);
				angular.setZ(0.2);

				twist.setLinear(linear);
				twist.setAngular(angular);
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

		/** Called when the Node has started and successfully connected to the master. */
		@Override
		public void onStart(final ConnectedNode connectedNode) {
			publisher = connectedNode.newPublisher("cmd_vel", geometry_msgs.Twist._TYPE);
			twist = publisher.newMessage();

			// This CancellableLoop will be canceled automatically when the node shuts down.
			connectedNode.executeCancellableLoop(new CancellableLoop() {
				private int sequenceNumber;

				@Override
				protected void setup() {
					sequenceNumber = 0;
				}

			@Override
			protected void loop() throws InterruptedException {
				publisher.publish(twist);
				sequenceNumber++;
				Thread.sleep(1000);
			}
			});
		}
	}
}
