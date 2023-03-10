package ee.rainer.nordpool2;

import java.util.ArrayList;

@lombok.Data
public class NordpoolData{
    private boolean success;
    private Data data;
}

@lombok.Data
class Data{
    private ArrayList<TimestampPrice> ee;
    private ArrayList<TimestampPrice> fi;
    private ArrayList<TimestampPrice> lv;
    private ArrayList<TimestampPrice> lt;
}

@lombok.Data
class TimestampPrice{
    private int timestamp;
    private double price;
}