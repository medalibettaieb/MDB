package tn.esprit.ejbMDBProjectJboss7.mdb;


import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

@Singleton
@Startup
@LocalBean
public class TraitementsPeriodiques3 {

  @Resource
  TimerService timerService;

  private DateFormat mediumDateFormat = 
    DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);

  @PostConstruct
  public void creerTimer() {
    Logger.getLogger(TraitementsPeriodiques3.class.getName()).log(Level.INFO, 
      "Creation du Timer");
      ScheduleExpression scheduleExp =
        new ScheduleExpression().second("*/10").minute("*").hour("*");
      Timer timer = timerService.createCalendarTimer(scheduleExp);
  }

  @Timeout
  public void executerTraitement(Timer timer) {
    Logger.getLogger(TraitementsPeriodiques3.class.getName()).log(Level.INFO, 
      "Execution du traitement toutes les 10 secondes "+mediumDateFormat.format(new Date()));
    }
}