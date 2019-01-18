package ao.ai.classify.decision.impl.input.raw.example;

import ao.ai.classify.decision.impl.input.processed.data.DataPool;
import ao.ai.classify.decision.impl.input.processed.example.LocalContext;
import ao.ai.classify.decision.impl.input.processed.example.LocalContextImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ContextImpl implements Context
{
    //--------------------------------------------------------------------
    private List<Datum> data;


    //--------------------------------------------------------------------
    public ContextImpl()
    {
        data = new ArrayList<Datum>();
    }
    public ContextImpl(List<Datum> initialData)
    {
        this();
        data.addAll( initialData );
    }
    public ContextImpl(Context copyContext)
    {
        this(copyContext.data());
    }


    //--------------------------------------------------------------------
    public Example withTarget(Datum target)
    {
        return new ExampleImpl(this, target);
    }

    
    //--------------------------------------------------------------------
    public void add(Datum datum)
    {
        data.add( datum );
    }

    public void addAll(Context dataFrom)
    {
        data.addAll( dataFrom.data() );
    }

    public Context remove(String type) {
        return null;
    }


    //--------------------------------------------------------------------
    public List<String> types()
    {
        List<String> types = new ArrayList<String>();

        for (Datum datum : data)
        {
            types.add( datum.type() );
        }

        return types;
    }

    public List<Datum> data()
    {
        return data;
    }

    public LocalContext toContext(DataPool pool)
    {
        LocalContext ctx = new LocalContextImpl();
        for (Datum buff : data)
        {
            ctx.add( buff.toDatum(pool) );
        }
        return ctx;
    }


    //-------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return data.toString();
    }
}
