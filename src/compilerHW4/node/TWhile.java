/* This file was generated by SableCC (http://www.sablecc.org/). */

package compilerHW4.node;

import compilerHW4.analysis.Analysis;

@SuppressWarnings("nls")
public final class TWhile extends Token
{
    public TWhile()
    {
        super.setText("under_contract");
    }

    public TWhile(int line, int pos)
    {
        super.setText("under_contract");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TWhile(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTWhile(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TWhile text.");
    }
}
