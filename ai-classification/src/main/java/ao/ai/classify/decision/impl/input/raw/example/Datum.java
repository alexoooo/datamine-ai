package ao.ai.classify.decision.impl.input.raw.example;

import ao.ai.classify.decision.impl.input.processed.data.DataPool;
import ao.ai.classify.decision.impl.input.processed.data.LocalDatum;

/**
 * External Datum
 */
public class Datum
{
    //--------------------------------------------------------------------
    public static final Datum TRUE  = new Datum(true);
    public static final Datum FALSE = new Datum(false);

    public static Datum newInstance(boolean isPositive)
    {
        return isPositive ? Datum.TRUE : Datum.FALSE;
    }


    //--------------------------------------------------------------------
    private final String type;
    private final Object state;
    private final double value;


    //--------------------------------------------------------------------
    public <T extends Enum<T>> Datum(T enumValue)
    {
        this(enumValue.getDeclaringClass().getSimpleName(),
             enumValue);
    }

    public Datum(Object state)
    {
        this(state.getClass().getSimpleName(), state, 0);
    }

    public Datum(String type,
                 Object state)
    {
        this(type, state, 0);
    }

    public Datum(String type,
                 double value)
    {
        this(type, null, value);
    }

    private Datum(String type, Object state, double value)
    {
        this.type  = type;
        this.state = state;
        this.value = value;
    }

    
    //--------------------------------------------------------------------
    public String type()
    {
        return type;
    }

    public Object state()
    {
        assert state != null
                : "continuous data have 'value', not 'state'";
        return state;
    }

    public double value()
    {
        assert state == null
                : "multistate data have 'state', not 'value'";
        return value;
    }


    //--------------------------------------------------------------------
    public LocalDatum toDatum(DataPool inPool)
    {
        return isMultistate()
                ? inPool.newMultistate(type, state)
                : inPool.newContinuous(type, value);
    }

    private boolean isMultistate()
    {
        return (state != null);
    }


    //--------------------------------------------------------------------
    public String toString()
    {
        return type + " = " +
               (isMultistate()
                ? state
                : value);
    }

    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Datum datum = (Datum) o;

        return Double.compare(datum.value, value) == 0 &&
                    !(state != null
               ? !state.equals(datum.state)
               : datum.state != null) &&
                    type.equals(datum.type);
    }

    public int hashCode()
    {
        int result;
        long temp;
        result = type.hashCode();
        result = 31 * result + (state != null ? state.hashCode() : 0);
        temp = value != +0.0d ? Double.doubleToLongBits(value) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
