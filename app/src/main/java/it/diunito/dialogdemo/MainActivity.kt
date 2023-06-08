package it.diunito.dialogdemo

import android.os.Bundle
import com.aldebaran.qi.Future
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.builder.ChatBuilder
import com.aldebaran.qi.sdk.builder.QiChatbotBuilder
import com.aldebaran.qi.sdk.builder.TopicBuilder
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayPosition
import com.aldebaran.qi.sdk.design.activity.conversationstatus.SpeechBarDisplayStrategy
import com.aldebaran.qi.sdk.`object`.conversation.*

private const val TAG = "START CHAT ACTIVITY"

class MainActivity : RobotActivity(), RobotLifecycleCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Register the RobotLifecycleCallbacks to this Activity.
        setSpeechBarDisplayStrategy(SpeechBarDisplayStrategy.IMMERSIVE)
        setSpeechBarDisplayPosition(SpeechBarDisplayPosition.TOP)
        setContentView(R.layout.activity_main)

        QiSDK.register(this, this)
    }

    override fun onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this)
        super.onDestroy()
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
        // Create a topic
        val topic: Topic = TopicBuilder.with(qiContext)
            .withResource(R.raw.dialogo)
            .build()

        // Create a QiChatbot
        val qichatbot: QiChatbot = QiChatbotBuilder.with(qiContext)
            .withTopic(topic)
            .build()

        // Create a Chat
        val chat: Chat = ChatBuilder.with(qiContext)
            .withChatbot(qichatbot)
            .build()

        // Execute the chat asynchronously
        val fchat: Future<Void> = chat.async().run()
    }

    override fun onRobotFocusLost() {
    }

    override fun onRobotFocusRefused(reason: String) {
        // The robot focus is refused.
    }
}