/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class TDivide extends Token
{
    public TDivide()
    {
        super.setText("nerf");
    }

    public TDivide(int line, int pos)
    {
        super.setText("nerf");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TDivide(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTDivide(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TDivide text.");
    }
}
