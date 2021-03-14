package pl.zzpj;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class LeftoverTime extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        long secondsLeft = calcLeftoverTime(Action.timerStart, new Date(), Action.delay);
        Messages.showMessageDialog("Pozostalo " + (int) secondsLeft / 60 + " minut i " + secondsLeft % 60 + " sekund", "Pozostaly czas", Messages.getInformationIcon());
    }

    public long calcLeftoverTime(Date timerStart, Date now, int delay) {
        return (((timerStart.getTime() + delay) - now.getTime()) / 1000);
    }
}
