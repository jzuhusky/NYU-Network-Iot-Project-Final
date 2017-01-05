package pl.llp.aircasting.storage;

import pl.llp.aircasting.Intents;
import pl.llp.aircasting.activity.ApplicationState;
import pl.llp.aircasting.activity.SaveOrDiscardRestoredSessionActivity;
import pl.llp.aircasting.model.Session;
import pl.llp.aircasting.storage.repository.SessionRepository;

import android.app.Activity;
import android.content.Intent;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by ags on 28/03/2013 at 18:46
 */
@Singleton
public class UnfinishedSessionChecker
{
  @Inject SessionRepository repo;
  @Inject ApplicationState state;

  AtomicBoolean checkInProgress = new AtomicBoolean(false);

  public void check(Activity context)
  {
    if(checkInProgress.compareAndSet(false, true))
    {
      try
      {
        List<Session> unfinishedSessions = repo.unfinishedSessions();

        for (Session unfinishedSession : unfinishedSessions)
        {
          if(state.saving().isSaving(unfinishedSession.getId()))
          {
            continue;
          }
          displayDialogAskingForTitleEtc(context, unfinishedSession);
          finish(unfinishedSession);
        }
      }
      finally
      {
        checkInProgress.set(false);
      }
    }
  }

  private void displayDialogAskingForTitleEtc(Activity context, Session session)
  {
    Intent intent = new Intent(context, SaveOrDiscardRestoredSessionActivity.class);
    intent.putExtra(Intents.SESSION_ID, session.getId());
    context.startActivityForResult(intent, Intents.SAVE_DIALOG);
  }

  private void finish(Session unfinishedSession)
  {
    repo.complete(unfinishedSession.getId());
   }
}