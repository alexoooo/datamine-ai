package ao.ai.classify.decision.impl.input.raw.example;

import ao.ai.classify.decision.impl.input.processed.data.DataPool;
import ao.ai.classify.decision.impl.input.processed.example.LocalContext;
import ao.ai.classify.decision.impl.input.processed.example.LocalContextImpl;

import java.util.List;

/**
 * User: alex
 * Date: 23-Jun-2009
 * Time: 8:47:14 PM
 */
public class ContextSubtraction implements Context
{
    //--------------------------------------------------------------------
    private final Context DELEGET;
    private final String  HIDE;


    //--------------------------------------------------------------------
    public ContextSubtraction(
            Context subtractFrom, String subtractDatumWithType)
    {
        DELEGET = subtractFrom;
        HIDE    = subtractDatumWithType;
    }


    //--------------------------------------------------------------------
    public Example withTarget(Datum target) {
        return DELEGET.withTarget(target);
    }


    //--------------------------------------------------------------------
    public void add(Datum datum) {
        DELEGET.add(datum);
    }


    //--------------------------------------------------------------------
    public void addAll(Context dataFrom) {
        DELEGET.addAll(dataFrom);
    }

    public Context remove(String type) {
        return new ContextSubtraction(DELEGET, type);
    }


    //--------------------------------------------------------------------
    public List<String> types() {
        return null;
    }

    public List<Datum> data() {
        return null;
    }


    //--------------------------------------------------------------------
    public LocalContext toContext(DataPool pool) {
        LocalContext ctx = new LocalContextImpl();
        for (Datum buff : data())
        {
            ctx.add( buff.toDatum(pool) );
        }
        return ctx;
    }

    
    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return "Hiding: " + HIDE;
    }
}
