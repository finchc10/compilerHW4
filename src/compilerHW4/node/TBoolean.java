/* This file was generated by SableCC (http://www.sablecc.org/). */

package compilerHW4.node;

import compilerHW4.analysis.Analysis;

@SuppressWarnings("nls")
public final class TBoolean extends Token
{
    public TBoolean()
    {
        super.setText("dichotomy");
    }

    public TBoolean(int line, int pos)
    {
        super.setText("dichotomy");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TBoolean(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTBoolean(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TBoolean text.");
    }
}
