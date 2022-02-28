package com.example.knu_info.interfaces;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
public interface BotReply {
    void callback(DetectIntentResponse returnResponse);
}
