/* This file was generated by SableCC (http://www.sablecc.org/). */

package compilerHW4.node;

import compilerHW4.analysis.Analysis;

@SuppressWarnings("nls")
public final class TIf extends Token
{
    public TIf()
    {
        super.setText("consider");
    }

    public TIf(int line, int pos)
    {
        super.setText("consider");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TIf(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTIf(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TIf text.");
    }
}
