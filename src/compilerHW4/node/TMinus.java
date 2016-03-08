/* This file was generated by SableCC (http://www.sablecc.org/). */

package compilerHW4.node;

import compilerHW4.analysis.Analysis;

@SuppressWarnings("nls")
public final class TMinus extends Token
{
    public TMinus()
    {
        super.setText("drop");
    }

    public TMinus(int line, int pos)
    {
        super.setText("drop");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TMinus(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMinus(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TMinus text.");
    }
}
