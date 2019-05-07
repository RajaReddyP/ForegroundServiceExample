package com.polamrapps.foregroundservice;

public class Constants {
    public interface ACTION {
        String MAIN_ACTION = "com.polamrapps.foregroundservice.action.main";
        String PREV_ACTION = "com.polamrapps.foregroundservice.action.prev";
        String PLAY_ACTION = "com.polamrapps.foregroundservice.action.play";
        String NEXT_ACTION = "com.polamrapps.foregroundservice.action.next";
        String STARTFOREGROUND_ACTION = "com.polamrapps.foregroundservice.action.startforeground";
        String STOPFOREGROUND_ACTION = "com.polamrapps.foregroundservice.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        int FOREGROUND_SERVICE = 101;
    }
}
