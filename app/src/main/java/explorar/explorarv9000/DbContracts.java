package explorar.explorarv9000;

import android.provider.BaseColumns;
import java.util.GregorianCalendar;
import java.util.Date;

/**
 * Created by benja on 17/09/2017.
 */

public final class DbContracts {



    // student database contract
    public static class studentDBentry implements BaseColumns { // implementing BaseColumns automatically creates a unique id for each entry
        public static final String TABLE_NAME = "studentDB";
        public static final String COLUMN_zID = "zID";
        public static final String COLUMN_NAME_STUDENT = "studentFullName";
        protected static final String COLUMN_PASSWORD_STUDENT = "studentPassword";
        public static final String COLUMN_EMAIL_STUDENT = "studentEmail";
        public static final String COLUMN_DEGREE_STUDENT = "studentDegree";
    }
    // organisation database contract
    public static class organisationsDBentry implements BaseColumns {
        public static final String TABLE_NAME = "organisationDB";
        public static final String COLUMN_NAME_ORG = "organiserName";
        public static final String COLUMN_PASSWORD_ORG = "organiserPassword";
        public static final String COLUMN_EMAIL_ORG = "organiserEmail";
    }
    // events database contract
    public static class eventsDBentry implements BaseColumns {
        public static final String TABLE_NAME = "eventsDB";
        public static final String COLUMN_NAME_EVENT = "eventName";
        public static final String COLUMN_NAME_HOSTORG = "hostOrganisation";
        public static final String COLUMN_EVENT_TYPE = "eventType";
        public static final String COLUMN_LOCATION_EVENT = "eventLocation";
        public static final String COLUMN_DATE_EVENT = "eventDate";
        public static final String COLUMN_TIME_EVENT = "eventTime";

    }
    //image db and temporary cache for google markers

}