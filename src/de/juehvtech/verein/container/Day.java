package de.juehvtech.verein.container;

import android.content.Context;

import java.util.GregorianCalendar;

import de.juehvtech.verein.R;

public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    public static Day fromCalendarDayInt(int day) {
        switch (day) {
            case GregorianCalendar.MONDAY:
                return Day.MONDAY;
            case GregorianCalendar.TUESDAY:
                return Day.TUESDAY;
            case GregorianCalendar.WEDNESDAY:
                return Day.WEDNESDAY;
            case GregorianCalendar.THURSDAY:
                return Day.THURSDAY;
            case GregorianCalendar.FRIDAY:
                return Day.FRIDAY;
            case GregorianCalendar.SATURDAY:
                return Day.SATURDAY;
            case GregorianCalendar.SUNDAY:
                return Day.SUNDAY;
            default:
                return Day.MONDAY;
        }
    }

//    public String toLocalizedString(Context context) {
//        switch (this) {
//            case MONDAY:
//                return context.getResources().getString(R.string.day_short_mo);
//            case TUESDAY:
//                return context.getResources().getString(R.string.day_short_tue);
//            case WEDNESDAY:
//                return context.getResources().getString(R.string.day_short_wed);
//            case THURSDAY:
//                return context.getResources().getString(R.string.day_short_thu);
//            case FRIDAY:
//                return context.getResources().getString(R.string.day_short_fr);
//            case SATURDAY:
//                return context.getResources().getString(R.string.day_short_sa);
//            case SUNDAY:
//                return context.getResources().getString(R.string.day_short_su);
//            default:
//                return "";
//
//        }
//    }
}
