package pl.zzpj;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Action extends AnAction {

    public static Date timerStart;
    public static int delay = 15000;
    private Timer timer;

    public Action() {
        createNewTimer();
    }

    private void createNewTimer() {
        timer = new Timer();
        Action.timerStart = new Date();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                EventQueue.invokeLater(() -> {
                    Messages.showMessageDialog("Zrob sobie przerwe na kawusie", "Przypomnienie", Messages.getInformationIcon());
                    createNewTimer();
                });
            }
        }, Action.delay);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        String minutes =  Messages.showInputDialog("Podaj czas pracy (w minutach)", "Ustawienia", Messages.getInformationIcon());
        if (minutes == null)
            return;
        Action.delay = Integer.parseInt(minutes) * 60 * 1000;
        timer.cancel();
        createNewTimer();
    }
}
