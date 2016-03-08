/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.node;

import lexing.analysis.*;

@SuppressWarnings("nls")
public final class TOmega extends Token
{
    public TOmega()
    {
        super.setText("omega");
    }

    public TOmega(int line, int pos)
    {
        super.setText("omega");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TOmega(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTOmega(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TOmega text.");
    }
}
