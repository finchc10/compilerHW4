/* This file was generated by SableCC (http://www.sablecc.org/). */

package compilerHW4.node;

import compilerHW4.analysis.*;

@SuppressWarnings("nls")
public final class TBegin extends Token
{
    public TBegin()
    {
        super.setText("alpha");
    }

    public TBegin(int line, int pos)
    {
        super.setText("alpha");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TBegin(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTBegin(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TBegin text.");
    }
}
